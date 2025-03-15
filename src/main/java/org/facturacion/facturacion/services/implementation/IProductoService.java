package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.facturacion.facturacion.domain.FormaVenta;
import org.facturacion.facturacion.domain.Producto;
import org.facturacion.facturacion.domain.TipoImpuesto;
import org.facturacion.facturacion.dto.formaVenta.ActualizarFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.FormaVentaDTO;
import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.dto.producto.FullProductoDTO;
import org.facturacion.facturacion.dto.producto.ProductoDTO;
import org.facturacion.facturacion.exceptions.producto.*;
import org.facturacion.facturacion.repositories.FormaVentaRepository;
import org.facturacion.facturacion.repositories.ProductoRepository;
import org.facturacion.facturacion.repositories.TipoImpuestoRepository;
import org.facturacion.facturacion.services.specification.ProductoService;
import org.facturacion.facturacion.services.validations.FormaVentaValidationService;
import org.facturacion.facturacion.services.validations.ProductoValidationService;
import org.facturacion.facturacion.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * Implementación del servicio de productos.
 */
@Service
@AllArgsConstructor
public class IProductoService implements ProductoService {

    private final ProductoRepository productoRepository;
    private final TipoImpuestoRepository tipoImpuestoRepository;
    private final ProductoValidationService productoValidationService;
    private final FormaVentaValidationService formaVentaValidationService;
    private final FormaVentaRepository formaVentaRepository;

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
     * Crea un producto.
     * @param productoDTO DTO con los datos del producto.
     * @return Producto creado.
     */
    @Override
    public ProductoDTO crearProducto(CrearProductoDTO productoDTO) {

        if(productoDTO == null) throw new ProductoException("El producto no puede estar vacío");
        if(productoDTO.formasVenta() == null)
            throw new ProductoFormaVentaException("La forma de venta no puede estar vacía");

        if(productoDTO.formasVenta().isEmpty())
            throw new ProductoFormaVentaException("El producto debe tener al menos una forma de venta");

        verificarNombresFormasVenta(productoDTO.formasVenta());

        productoValidationService.validate(productoDTO);
        productoDTO.formasVenta().forEach(formaVentaValidationService::validate);
        TipoImpuesto impuesto = validarImpuesto(productoDTO.impuesto());
        Producto producto = prepararProductoParaCrear(productoDTO, impuesto);
        IProductoService.setHayCambiosProducto(true);
        return ProductoDTO.fromEntity(productoRepository.save(producto));
    }

    /**
     * Este método verifica que los nombres de las formas de venta no estén duplicados.
     * @param crearFormaVentaDTOS Lista de formas de venta.
     */
    private void verificarNombresFormasVenta(List<CrearFormaVentaDTO> crearFormaVentaDTOS) {
        for (int i = 0; i < crearFormaVentaDTOS.size(); i++) {
            for (int j = i + 1; j < crearFormaVentaDTOS.size(); j++) {
                if (crearFormaVentaDTOS.get(i).nombre().trim().equalsIgnoreCase(
                        crearFormaVentaDTOS.get(j).nombre().trim())) {
                    throw new ProductoFormaVentaException("Los nombres de las formas de venta no pueden estar duplicados");
                }
            }
        }
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
        TipoImpuesto impuesto = validarImpuesto(productoDTO.impuesto());
        producto.actualizar(productoDTO);
        producto.setImpuesto(impuesto);
        producto.setLote(productoDTO.lote());
        producto.setFechaVencimiento(productoDTO.fechaVencimiento());
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
     *
     * @param cantidad         Cantidad a verificar.
     * @param codigo           Código del producto.
     * @param nombreFormaVenta
     * @return Booleano que indica si la cantidad es suficiente.
     */
    @Override
    public Boolean verificarCantidad(Integer cantidad, String codigo, String nombreFormaVenta) {
        Producto producto = obtenerProducto(codigo);
        FormaVenta formaVenta = producto.getFormaVentas().stream()
                .filter(fv -> fv.getNombre().equalsIgnoreCase(nombreFormaVenta))
                .findFirst()
                .orElseThrow(() -> new ProductoFormaVentaException("La forma de venta no existe"));
        return formaVenta.getCantidad() >= cantidad;
    }

    /**
     * Verifica si un producto fue eliminado.
     * @param codigo del producto.
     * @return Booleano que indica si el producto fue eliminado.
     */
    @Override
    public Boolean fueEliminado(String codigo) {
        return productoRepository.findByCodigo(codigo)
                .map(Producto::isEliminado)
                .orElseThrow(() -> new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo));
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
     * @param codigo codigo del producto.
     * @return Producto encontrado.
     */
    private Producto obtenerProducto(String codigo) {
        return productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo));
    }

    /**
     * Válida y obtiene un tipo de impuesto por su nombre.
     * @param nombreImpuesto Nombre del tipo de impuesto.
     * @return Tipo de impuesto encontrado.
     */
    private TipoImpuesto validarImpuesto(String nombreImpuesto) {
        System.out.println("nombreImpuesto = " + nombreImpuesto);
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
     * Este método devuelve un producto por su código.
     * @param codigo Código del producto.
     * @return Producto encontrado.
     */
    @Override
    public Producto findByCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo));
    }

    @Override
    public FullProductoDTO actualizarFormaVenta(String codigo, ActualizarFormaVentaDTO formaVentaDTO, String nombreFormaVenta) {

        if(formaVentaDTO == null) throw new ProductoFormaVentaException("La forma de venta no puede estar vacía");

        Producto producto = findByCodigo(codigo);
        if(producto == null) throw new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo);

        FormaVenta formaVenta = producto.getFormaVentas().stream()
                .filter(fv -> fv.getNombre().equalsIgnoreCase(nombreFormaVenta))
                .findFirst()
                .orElseThrow(() -> new ProductoFormaVentaException("La forma de venta no existe"));

        formaVentaValidationService.validate(formaVentaDTO);
        formaVenta.actualizarFormaVenta(formaVentaDTO);
        formaVentaRepository.save(formaVenta);

        return obtenerProductoCompletoPorCodigo(codigo);
    }

    @Override
    public FullProductoDTO obtenerProductoCompletoPorCodigo(String codigo) {

        if(codigo == null) throw new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo);
        if (productoRepository.findByCodigo(codigo).isEmpty()) throw new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo);
        Producto producto = productoRepository.findByCodigo(codigo).get();
        return FullProductoDTO.fromEntity(producto);
    }

    @Override
    public FormaVenta findFormaVentaByProductoAndNombre(Producto producto, String nombre) {
        if(producto == null) throw new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO);
        return producto.getFormaVentas().stream()
                .filter(fv -> fv.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new ProductoFormaVentaException("La forma de venta no existe"));
    }

    @Override
    public List<FormaVentaDTO> obtenerFormasVenta(String codigo) {
        if(codigo == null) throw new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo);
        if (productoRepository.findByCodigo(codigo).isEmpty()) throw new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo);
        Producto producto = productoRepository.findByCodigo(codigo).get();
        return producto.getFormaVentas().stream()
                .filter(fv -> !fv.getEliminado())
                .map(FormaVentaDTO::fromEntity).toList();
    }

    @Override
    public Boolean eliminarFormaVenta(String codigo, String nombreFormaVenta) {
       Producto producto = findByCodigo(codigo);
        if(producto == null) throw new ProductoNoEncontradoException(Constants.ERROR_PRODUCTO_NO_ENCONTRADO + codigo);
        if(producto.getFormaVentas().isEmpty()) throw new ProductoFormaVentaException("El producto no tiene formas de venta");
        int cantidadFormasVenta = (int) producto.getFormaVentas().stream().filter(fv -> !fv.getEliminado()).count();
        if(cantidadFormasVenta == 1) throw new ProductoFormaVentaException("El producto debe tener al menos una forma de venta");
        FormaVenta formaVenta = producto.getFormaVentas().stream()
                .filter(fv -> fv.getNombre().equalsIgnoreCase(nombreFormaVenta))
                .findFirst()
                .orElseThrow(() -> new ProductoFormaVentaException("La forma de venta no existe"));
        formaVenta.setEliminado(true);
        productoRepository.save(producto);
        return true;
    }

    @Override
    public Boolean guardarFormaVenta(String codigo, CrearFormaVentaDTO crearFormaVentaDTO) {
        Producto producto = findByCodigo(codigo);
        formaVentaValidationService.validate(crearFormaVentaDTO);
        producto.getFormaVentas().stream()
                .filter(fv -> fv.getNombre().trim().equalsIgnoreCase(crearFormaVentaDTO.nombre().trim()))
                .findFirst()
                .ifPresentOrElse(
                        fv -> {
                            fv.setEliminado(false);
                            fv.setCantidad(crearFormaVentaDTO.cantidad());
                            fv.setPrecioCompra(crearFormaVentaDTO.precioCompra());
                            fv.setPrecioVenta(crearFormaVentaDTO.precioVenta());
                            formaVentaRepository.save(fv); // Este guardado es válido porque estamos actualizando una entidad existente
                        },
                        () -> {
                            FormaVenta formaVenta = CrearFormaVentaDTO.toEntity(crearFormaVentaDTO, producto);
                            formaVenta.setProducto(producto);
                            producto.getFormaVentas().add(formaVenta);
                            productoRepository.save(producto); // Esto debería ser suficiente para persistir `formaVenta`
                        }
                );

        return true;
    }



}
