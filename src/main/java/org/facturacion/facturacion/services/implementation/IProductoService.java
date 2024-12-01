package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Producto;
import org.facturacion.facturacion.domain.TipoImpuesto;
import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.dto.producto.ProductoDTO;
import org.facturacion.facturacion.exceptions.producto.*;
import org.facturacion.facturacion.repositories.ProductoRepository;
import org.facturacion.facturacion.repositories.TipoImpuestoRepository;
import org.facturacion.facturacion.services.specification.ProductoService;
import org.facturacion.facturacion.services.validations.ProductoValidationService;
import org.facturacion.facturacion.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de productos.
 */
@Service
@AllArgsConstructor
public class IProductoService implements ProductoService {

    private final ProductoRepository productoRepository;
    private final TipoImpuestoRepository tipoImpuestoRepository;
    private final ProductoValidationService productoValidationService;



    @Override
    public List<ProductoDTO> listarProducto() {
        return productoRepository.findAllByEliminadoIsFalse()
                .stream()
                .map(ProductoDTO::fromEntity)
                .toList();
    }

    @Override
    public ProductoDTO obtenerProductoPorId(String id) {
        Producto producto = obtenerProducto(id);
        return ProductoDTO.fromEntity(producto);
    }

    @Override
    public ProductoDTO crearProducto(CrearProductoDTO productoDTO) {
        productoValidationService.validate(productoDTO);
        TipoImpuesto impuesto = validarImpuesto(productoDTO.impuesto());
        Producto producto = prepararProductoParaCrear(productoDTO, impuesto);
        return ProductoDTO.fromEntity(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(ActualizarProductoDTO productoDTO) {
        Producto producto = obtenerProducto(productoDTO.codigo());
        productoValidationService.validate(productoDTO);
        actualizarDatosProducto(producto, productoDTO);
        return ProductoDTO.fromEntity(productoRepository.save(producto));
    }

    @Override
    public Boolean eliminarProducto(String id) {
        Producto producto = obtenerProducto(id);
        producto.setEliminado(true);
        productoRepository.save(producto);
        return true;
    }

    @Override
    public Boolean verificarExisteElCodProducto(String codProducto) {
        return productoRepository.findById(codProducto).isPresent();
    }

    @Override
    public List<String> getTiposImpuestos() {
        return tipoImpuestoRepository.findAll()
                .stream()
                .map(TipoImpuesto::getNombre)
                .toList();
    }

    @Override
    public Boolean verificarCantidad(Integer cantidad, String id) {
        return productoRepository.findById(id)
                .map(producto -> producto.getStock() >= cantidad)
                .orElse(false);
    }

    @Override
    public Boolean isActivo(String id) {
        return obtenerProductoPorId(id).activo();
    }

    @Override
    public Boolean fueEliminado(String id) {
        return productoRepository.findById(id)
                .map(Producto::isEliminado)
                .orElse(false);
    }

    @Override
    public void recuperarProducto(String id) {
        Producto producto = obtenerProducto(id);
        producto.setEliminado(false);
        productoRepository.save(producto);
    }

    @Override
    public Producto findById(String id) {
        return obtenerProducto(id);
    }

    /**
     * Obtiene un producto por su ID o lanza una excepción si no se encuentra.
     * @param id ID del producto.
     * @return Producto encontrado.
     */
    private Producto obtenerProducto(String id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + id));
    }

    /**
     * Valida y obtiene un tipo de impuesto por su nombre.
     *
     * @param nombreImpuesto Nombre del tipo de impuesto.
     * @return Tipo de impuesto encontrado.
     */
    private TipoImpuesto validarImpuesto(String nombreImpuesto) {
        return tipoImpuestoRepository.findByNombre(nombreImpuesto)
                .orElseThrow(() -> new ProductoImpuestoException(Constants.ERROR_IMPUESTO_NO_ENCONTRADO));
    }

    /**
     * Prepara un producto para ser creado.
     *
     * @param dto      DTO con los datos del producto.
     * @param impuesto Tipo de impuesto asociado.
     * @return Producto preparado.
     */
    private Producto prepararProductoParaCrear(CrearProductoDTO dto, TipoImpuesto impuesto) {
        Producto producto = dto.toEntity();
        producto.setImpuesto(impuesto);
        return producto;
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param producto    Producto a actualizar.
     * @param productoDTO DTO con los nuevos datos del producto.
     */
    private void actualizarDatosProducto(Producto producto, ActualizarProductoDTO productoDTO) {
        producto.setNombre(productoDTO.nombre());
        producto.setStock(productoDTO.cantidad());
        producto.setPrecio(productoDTO.precio());
        producto.setActivo(productoDTO.activo());
    }
}
