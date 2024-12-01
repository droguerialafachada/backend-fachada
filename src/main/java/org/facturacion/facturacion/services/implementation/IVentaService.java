package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.*;
import org.facturacion.facturacion.dto.factura.VentaItemDTO;
import org.facturacion.facturacion.dto.factura.CrearVentaDTO;
import org.facturacion.facturacion.dto.detalleFactura.DetalleVentaDTO;
import org.facturacion.facturacion.dto.factura.VentaDTO;
import org.facturacion.facturacion.exceptions.cliente.ClienteNoExisteException;
import org.facturacion.facturacion.exceptions.producto.ProductoCantidadException;
import org.facturacion.facturacion.repositories.VentaRepository;
import org.facturacion.facturacion.services.specification.ClienteService;
import org.facturacion.facturacion.services.specification.VentaService;
import org.facturacion.facturacion.services.specification.ProductoService;
import org.facturacion.facturacion.services.specification.UsuarioService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IVentaService implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoService productoService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final IDetalleVentaService detalleFacturaService;
    private final Double IVA = 0.19;

    @Override
    public Integer obtenerSiguienteId() {
        return ventaRepository.obtenerSiguienteId();
    }

    @Override
    public VentaDTO guardarVenta(CrearVentaDTO ventaDTO) {

        Venta venta = new Venta();
        venta.setDetalleVentaList(new ArrayList<>());

        agregarDetalleVenta(venta, ventaDTO);
        venta.setTotal(venta.getDetalleVentaList().stream().mapToDouble(DetalleVenta::getValor).sum());

        agregarClienteVentas(venta, ventaDTO);
        agregarUsuarioVenta(venta, ventaDTO);
        venta.setFecha(new java.util.Date());

        venta.setSubTotal(venta.getTotal() - venta.getTotal() * IVA);
        ventaRepository.save(venta);
        venta.getDetalleVentaList().forEach(this.detalleFacturaService::save);

        return VentaDTO.fromEntity(venta);
    }

    private void agregarDetalleVenta(Venta venta, CrearVentaDTO ventaDTO){
        ventaDTO.listDetalleFactura().forEach(detalle -> {
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


    private void agregarUsuarioVenta(Venta venta, CrearVentaDTO ventaDTO){
        Usuario usuario = this.usuarioService.findById(ventaDTO.usuario());

        if(usuario == null){
            throw new ClienteNoExisteException("No se ha encontrado el usuario con el id "+ ventaDTO.usuario());
        }
        venta.setUsuario(usuario);
    }


    private void agregarClienteVentas(Venta venta, CrearVentaDTO ventaDTO){
        Cliente cliente = this.clienteService.findByCedula(ventaDTO.cliente());
        if(cliente == null){
            throw new ClienteNoExisteException("No se ha encontrado el cliente con la cedula "+ ventaDTO.cliente());
        }
        venta.setCliente(cliente);
    }


    @Override
    public List<VentaItemDTO> obtenerVentas() {
        return ventaRepository.findAll().stream().map(VentaItemDTO::fromEntity).toList();
    }


}
