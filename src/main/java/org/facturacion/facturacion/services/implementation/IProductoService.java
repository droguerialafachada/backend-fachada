package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de productos.
 */
@Service
@AllArgsConstructor
public class IProductoService implements ProductoService {

    private final ProductoRepository productoRepository;
    private final TipoImpuestoRepository tipoImpuestoRepository;
    private final ProductoValidationService productoValidationService;
    @Getter
    @Setter
    private static  boolean hayCambiosProducto = false;

    /**
     * Lista todos los productos paginados.
     * @return Lista de productos.
     */
    @Override
    public Page<ProductoDTO> listarProducto(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fechaCreacion"));
        return productoRepository.findAllByEliminadoIsFalse(pageable).map(ProductoDTO::fromEntity);
    }

    /**
     * Lista todos los productos.
     * @return Lista de productos.
     */
    @Override
    public List<ProductoDTO> listarProducto() {
        IProductoService.setHayCambiosProducto(false);
        return productoRepository.findAllByEliminadoIsFalse().stream().map(ProductoDTO::fromEntity).toList();
    }

    @Override
    public Boolean verificarCambios() {
       return IProductoService.isHayCambiosProducto();
    }

    /**
     * Obtiene un producto por su ID.
     * @param id Id del producto.
     * @return Producto encontrado.
     */
    @Override
    public ProductoDTO obtenerProductoPorId(String id) {
        Producto producto = obtenerProducto(id);
        return ProductoDTO.fromEntity(producto);
    }

    /**
     * Crea un producto.
     * @param productoDTO DTO con los datos del producto.
     * @return Producto creado.
     */
    @Override
    public ProductoDTO crearProducto(CrearProductoDTO productoDTO) {
        productoValidationService.validate(productoDTO);
        TipoImpuesto impuesto = validarImpuesto(productoDTO.impuesto());
        Producto producto = prepararProductoParaCrear(productoDTO, impuesto);
        IProductoService.setHayCambiosProducto(true);
        return ProductoDTO.fromEntity(productoRepository.save(producto));
    }

    /**
     * Actualiza un producto.
     * @param productoDTO DTO con los datos del producto.
     * @return Producto actualizado.
     */
    @Override
    public ProductoDTO actualizarProducto(ActualizarProductoDTO productoDTO) {
        Producto producto = obtenerProducto(productoDTO.codigo());
        productoValidationService.validate(productoDTO);
        actualizarDatosProducto(producto, productoDTO);
        IProductoService.setHayCambiosProducto(true);
        return ProductoDTO.fromEntity(productoRepository.save(producto));
    }

    /**
     * Elimina un producto (Cambia un estado) pero no lo elimina de la base de datos.
     * @param id  Id del producto.
     * @return Booleano que indica si se eliminó el producto.
     */
    @Override
    public Boolean eliminarProducto(String id) {
        Producto producto = obtenerProducto(id);
        producto.setEliminado(true);
        productoRepository.save(producto);
        IProductoService.setHayCambiosProducto(true);
        return true;
    }

    /**
     * Verifica si existe un producto por su código.
     * @param codProducto Código del producto.
     * @return Booleano que indica si existe el producto.
     */
    @Override
    public Boolean verificarExisteElCodProducto(String codProducto) {
        return productoRepository.findByCodigo(codProducto).isPresent();
    }

    /**
     * Obtiene los tipos de impuestos.
     * @return Lista de tipos de impuestos.
     */
    @Override
    public List<String> getTiposImpuestos() {
        return tipoImpuestoRepository.findAll()
                .stream()
                .map(TipoImpuesto::getNombre)
                .toList();
    }

    /**
     * Verifica si la cantidad de un producto es suficiente para realizar una venta.
     * @param cantidad Cantidad a verificar.
     * @param id Id del producto.
     * @return Booleano que indica si la cantidad es suficiente.
     */
    @Override
    public Boolean verificarCantidad(Integer cantidad, String id) {
        return productoRepository.findById(id)
                .map(producto -> producto.getStock() >= cantidad)
                .orElse(false);
    }

    /**
     * Verifica si un producto está activo.
     * @param id Id del producto.
     * @return Booleano que indica si el producto está activo.
     */
    @Override
    public Boolean isActivo(String id) {return obtenerProductoPorId(id).activo();}

    /**
     * Verifica si un producto fue eliminado.
     * @param id Id del producto.
     * @return Booleano que indica si el producto fue eliminado.
     */
    @Override
    public Boolean fueEliminado(String id) {
        return productoRepository.findById(id)
                .map(Producto::isEliminado)
                .orElse(false);
    }

    /**
     * Recupera un producto eliminado.
     * @param id Id del producto.
     */
    @Override
    public void recuperarProducto(String id) {
        Producto producto = obtenerProducto(id);
        producto.setEliminado(false);
        productoRepository.save(producto);
    }

    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto.
     * @return Producto encontrado.
     */
    @Override
    public Producto findById(String id) {
        return obtenerProducto(id);
    }

    /**
     * Este método guarda un objeto de la Entidad Producto.
     * @param producto Producto a guardar.
     */
    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
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
     * Válida y obtiene un tipo de impuesto por su nombre.
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
     * @param dto DTO con los datos del producto.
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

    /**
     * Este método devuelve un producto por su código.
     * @param codigo Código del producto.
     * @return Producto encontrado.
     */
    @Override
    public ProductoDTO findByCodigo(String codigo) {
        Optional<Producto> optProducto = productoRepository.findByCodigo(codigo);
        if(optProducto.isEmpty()) throw new ProductoNoEncontradoException("No existe un producto con ese código");
        Producto producto = productoRepository.findByCodigo(codigo).get();
        return ProductoDTO.fromEntity(producto);
    }

}
