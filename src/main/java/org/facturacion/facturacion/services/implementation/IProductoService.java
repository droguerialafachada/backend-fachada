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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * ProductoService implementation
 */
@Service
@AllArgsConstructor
public class IProductoService implements ProductoService {

    private final ProductoRepository productoRepository;
    private final TipoImpuestoRepository tipoImpuestoRepository;
    private final ProductoValidationService productoValidationService;

    /**
     * Este metodo obtiene todos los productos
     * @return List<ProductoDTO> Lista de productos
     */
    public List<ProductoDTO> listarProducto() {
        return productoRepository.findAllByEliminadoIsFalse().stream().map(ProductoDTO::fromEntity).toList();
    }

    /**
     * Este metodo obtiene un producto por su id
     * @param id Id del producto
     * @return ProductoDTO Producto
     */
    public ProductoDTO obtenerProductoPorId(String id) {

        Optional<Producto> productoOptional = this.productoRepository.findById(id);

        if(productoOptional.isEmpty())
            throw new ProductoNoEncontradoException("No se ha encontrado el producto con el id "+ id);
        Producto producto = productoOptional.get();

        return ProductoDTO.fromEntity(producto);

    }

    /**
     * Este metodo crea un producto
     * @param productoDTO Producto a crear
     * @return ProductoDTO Producto creado
     */
    @Override
    public ProductoDTO crearProducto(CrearProductoDTO productoDTO) {

        productoValidationService.validate(productoDTO);
        if(tipoImpuestoRepository.findByNombre(productoDTO.impuesto()) == null){
            throw  new ProductoImpuestoException("El tipo de impuesto no se ha encontrado");
        }

        Producto producto = productoDTO.toEntity();
        TipoImpuesto impuesto = tipoImpuestoRepository.findByNombre(productoDTO.impuesto());
        producto.setImpuesto(impuesto);

        return ProductoDTO.fromEntity(this.productoRepository.save(producto));

    }

    /**
     * Este metodo actualiza un producto
     * @param productoDTO Producto a actualizar
     * @return ProductoDTO Producto actualizado
     */
    public ProductoDTO actualizarProducto(ActualizarProductoDTO productoDTO) {
        Optional<Producto> productoAct = this.productoRepository.findById(productoDTO.codigo());

        if(productoAct.isEmpty()) throw new ProductoNoEncontradoException("El producto no se encuentra registrado");

        productoValidationService.validate(productoDTO);

        Producto producto = productoAct.get();
        producto.setNombre(productoDTO.nombre());
        producto.setStock(productoDTO.cantidad());
        producto.setPrecio(productoDTO.precio());
        producto.setActivo(productoDTO.activo());

        return ProductoDTO.fromEntity(this.productoRepository.save(producto));
    }

    /**
     * Este metodo elimina un producto, solo cambia el estado a eliminado
     * @param id Id del producto a eliminar
     * @return Boolean Retorna un valor booleano
     */
    @Override
    public Boolean eliminarProducto(String id) {
        Optional<Producto> producto = this.productoRepository.findById(id);

        if(producto.isEmpty()) throw new ProductoNoEncontradoException("El producto no se encuentra registrado");

        producto.get().setEliminado(true);
        this.productoRepository.save(producto.get());
        return true;
    }

    /**
     * Este metodo verifica si un producto existe por su codigo
     * @param codProducto Codigo del producto
     * @return Boolean Retorna un valor booleano
     */
    public Boolean verificarExisteElCodProducto(String codProducto) {
        return this.productoRepository.findById(codProducto).isPresent();
    }

    /**
     * Este metodo obtiene los tipos de impuestos disponibles
     * @return List<String> Lista de tipos de impuestos
     */
    @Override
    public List<String> getTiposImpuestos() {
        return tipoImpuestoRepository.findAll().stream().map(TipoImpuesto::getNombre).toList();
    }

    /**
     * Este metodo verifica si la cantidad de un producto es suficiente para la venta
     * @param cantidad Cantidad del producto
     * @param id Id del producto
     * @return Boolean Retorna un valor booleano
     */
    @Override
    public Boolean verificarCantidad(Integer cantidad, String id) {
        Optional<Producto> producto = this.productoRepository.findById(id);
        return producto.filter(value -> value.getStock() >= cantidad).isPresent();

    }

    /**
     * Este metodo verifica si un producto está activo
     * @param id Id del producto
     * @return Boolean Retorna un valor booleano
     */
    @Override
    public Boolean isActivo(String id) {
        return this.obtenerProductoPorId(id).activo();
    }

    /**
     * Este metodo verifica si un producto ha sido eliminado
     * @param id Id del producto
     * @return Boolean Retorna un valor booleano
     */
    @Override
    public Boolean fueEliminado(String id) {

        Optional<Producto> productoOptional = this.productoRepository.findById(id);

        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            return producto.isEliminado();
        }

        return false;
    }
    /**
     * Este metodo recupera un producto eliminado
     * @param id Id del producto
     */
    @Override
    public void recuperarProducto(String id) {
        Optional<Producto> productoOptional = this.productoRepository.findById(id);
        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            producto.setEliminado(false);
            this.productoRepository.save(producto);
        }
    }
    /**
     * Este metodo obtiene un producto por su id
     * @param s Id del producto
     * @return Producto el producto encontrado o lanza una excepcion
     */
    @Override
    public Producto findById(String s) {

        Optional<Producto> productoOptional = this.productoRepository.findById(s);
        if(productoOptional.isEmpty()) throw new ProductoNoEncontradoException("No se ha encontrado el producto con el id "+ s);
        return productoOptional.get();

    }

}
