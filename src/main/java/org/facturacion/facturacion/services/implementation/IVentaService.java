package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.*;
import org.facturacion.facturacion.dto.venta.FullVentaDTO;
import org.facturacion.facturacion.dto.venta.VentaItemDTO;
import org.facturacion.facturacion.dto.venta.CrearVentaDTO;
import org.facturacion.facturacion.dto.detalleVenta.DetalleVentaDTO;
import org.facturacion.facturacion.dto.venta.VentaDTO;
import org.facturacion.facturacion.exceptions.cliente.ClienteNoExisteException;
import org.facturacion.facturacion.exceptions.producto.ProductoCantidadException;
import org.facturacion.facturacion.exceptions.venta.VentaCanceladaException;
import org.facturacion.facturacion.exceptions.venta.VentaNoExisteException;
import org.facturacion.facturacion.repositories.VentaRepository;
import org.facturacion.facturacion.services.specification.ClienteService;
import org.facturacion.facturacion.services.specification.VentaService;
import org.facturacion.facturacion.services.specification.ProductoService;
import org.facturacion.facturacion.services.specification.UsuarioService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * VentaService implementation
 */
@Service
@AllArgsConstructor
public class IVentaService implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoService productoService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final IDetalleVentaService detalleFacturaService;
    private final Double IVA = 0.19;

    /**
     * Este metodo obtiene el siguiente id de la venta
     * @return Integer Siguiente id de la venta
     */
    @Override
    public Integer obtenerSiguienteId() {
        return ventaRepository.obtenerSiguienteId();
    }

    /**
     * Este metodo guarda una venta en la base de datos
     * @param ventaDTO Venta a guardar
     * @return VentaDTO Venta guardada
     */
    @Override
    public VentaDTO guardarVenta(CrearVentaDTO ventaDTO) {

        Venta venta = new Venta();
        venta.setDetalleVentaList(new ArrayList<>());

        agregarDetalleVenta(venta, ventaDTO);
        venta.setTotal(venta.getDetalleVentaList().stream().mapToDouble(DetalleVenta::getValor).sum());

        agregarClienteVentas(venta, ventaDTO);
        agregarUsuarioVenta(venta, ventaDTO);
        venta.setFecha(new java.util.Date());
        venta.setEstado(EstadoVenta.COMPLETADA);

        venta.setSubTotal(venta.getTotal() - venta.getTotal() * IVA);
        ventaRepository.save(venta);
        venta.getDetalleVentaList().forEach(this.detalleFacturaService::save);

        return VentaDTO.fromEntity(venta);
    }
    /**
     * Este metodo agregar los detalles de la venta
     * @param venta Venta a la que se le agregaran los detalles
     */
    private void agregarDetalleVenta(Venta venta, CrearVentaDTO ventaDTO){
        ventaDTO.listDetalleVenta().forEach(detalle -> {
            DetalleVenta detalleVenta = DetalleVentaDTO.toEntity(detalle);
            Producto producto = productoService.findById(detalle.codigoProducto());

            if(producto.getStock() < detalle.cantidad()){
                throw new ProductoCantidadException("No hay suficiente cantidad del producto "+ producto.getNombre()+ "para la factura");
            }else producto.setStock(producto.getStock() - detalle.cantidad());

            detalleVenta.setValor(producto.getPrecio() * detalle.cantidad());

            detalleVenta.setProducto(producto);
            detalleVenta.setVenta(venta);
            venta.getDetalleVentaList().add(detalleVenta);
        });
    }

    /**
     * Este metodo agrega el usuario a la venta
     * @param venta Venta a la que se le agregara el usuario
     * @param ventaDTO VentaDTO con la informacion del usuario
     */
    private void agregarUsuarioVenta(Venta venta, CrearVentaDTO ventaDTO){
        Usuario usuario = this.usuarioService.findById(ventaDTO.usuario());
        if(usuario == null) throw new ClienteNoExisteException("No se ha encontrado el usuario con el id "+ ventaDTO.usuario());
        venta.setUsuario(usuario);
    }

    /**
     * Este metodo agrega el cliente a la venta
     * @param venta Venta a la que se le agregara el cliente
     * @param ventaDTO VentaDTO que contiene el ID del cliente
     */
    private void agregarClienteVentas(Venta venta, CrearVentaDTO ventaDTO){
        Cliente cliente = this.clienteService.findByCedula(ventaDTO.cliente());
        if(cliente == null){
            throw new ClienteNoExisteException("No se ha encontrado el cliente con la cedula "+ ventaDTO.cliente());
        }
        venta.setCliente(cliente);
    }

    /**
     * Este metodo obtiene una venta por su id
     * @return VentaItemDTO Venta
     */
    @Override
    public List<VentaItemDTO> obtenerVentas() {
        return ventaRepository.findAll().stream().map(VentaItemDTO::fromEntity).toList();
    }

    /**
     * Este metodo cancela una venta por su id
     * Para cancelar una venta implica que se debe devolver la cantidad de productos a la tienda
     * y cambiar el estado de la venta a CANCELADA
     * @param id Id de la venta
     * @return Boolean True si se cancelo la venta, False si no
     */
    @Override
    public Boolean cancelarVenta(Integer id) {

        Venta venta = ventaRepository.findById(id).orElse(null);
        if(venta == null) throw new VentaNoExisteException("No se ha encontrado la venta con el id "+ id);

        if(venta.getEstado().equals(EstadoVenta.CANCELADA)) {
            throw new VentaCanceladaException("La venta ya ha sido cancelada");
        }

        venta.getDetalleVentaList().forEach(detalle -> {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoService.guardar(producto);
        });

        venta.setEstado(EstadoVenta.CANCELADA);
        ventaRepository.save(venta);
        return true;
    }

    /**
     * Este metodo obtiene las ventas en estado COMPLETADA
     * @return List<VentaDTO> Lista de ventas completadas
     */
    @Override
    public List<VentaDTO> obtenerVentasCompletadas() {
       return ventaRepository.findAllByEstado(EstadoVenta.COMPLETADA).stream().map(VentaDTO::fromEntity).toList();
    }

    /**
     * Este metodo obtiene una venta con toda la información dado su id
     * @param id Id de la venta
     * @return FullVentaDTO
     */
    @Override
    public FullVentaDTO obtenerVentaPorId(Integer id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if(venta == null) throw new VentaNoExisteException("No se ha encontrado la venta con el id "+ id);
        return FullVentaDTO.fromEntity(venta);
    }


}
