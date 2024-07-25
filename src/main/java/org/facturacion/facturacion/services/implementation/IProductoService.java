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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class IProductoService implements ProductoService {

    private final ProductoRepository productoRepository;
    private final TipoImpuestoRepository tipoImpuestoRepository;

    public List<ProductoDTO> listarProducto() {
        return productoRepository.findAllByEliminadoIsFalse().stream().map(ProductoDTO::fromEntity).toList();
    }

    public ProductoDTO obtenerProductoPorId(String id) {

        Optional<Producto> productoOptional = this.productoRepository.findById(id);

        if(productoOptional.isEmpty()) throw new ProductoNoEncontradoException("No se ha encontrado el producto con el id "+ id);
        Producto producto = productoOptional.get();

        return ProductoDTO.fromEntity(producto);

    }

    @Override
    public ProductoDTO crearProducto(CrearProductoDTO productoDTO) {
        //TODO: Se deberia cambiar los if por validaciones inyectadas
        if(productoDTO.codigo() == null || productoDTO.codigo().isEmpty()){
            throw new ProductoCodigoException("El codigo del producto no puede ser vacio");
        }

        if(productoDTO.cantidad() < 0 ){
            throw new ProductoCantidadException("La cantidad del producto no puede ser menor a 0");
        }

        if(productoDTO.precio() < 0 ){
            throw new ProductoPrecioException("El precio del producto no puede ser menor a 0");
        }

        if(productoDTO.nombre() == null || productoDTO.nombre().isEmpty()){
            throw new ProductoNombreException("El nombre del producto no puede ser vacio");
        }

        if(productoDTO.impuesto() == null || productoDTO.impuesto().isEmpty()){
            throw new ProductoImpuestoException("El impuesto no debe estar vacio");
        }

        if(tipoImpuestoRepository.findByNombre(productoDTO.impuesto()) == null){
            throw  new ProductoImpuestoException("El tipo de impuesto no se ha encontrado");
        }


        Producto producto = productoDTO.toEntity();
        TipoImpuesto impuesto = tipoImpuestoRepository.findByNombre(productoDTO.impuesto());
        producto.setImpuesto(impuesto);

        return ProductoDTO.fromEntity(this.productoRepository.save(producto));

    }

    public ProductoDTO actualizarProducto(ActualizarProductoDTO productoDTO) {
        Optional<Producto> productoAct = this.productoRepository.findById(productoDTO.codigo());

        if(productoAct.isEmpty()) throw new ProductoNoEncontradoException("El producto no se encuentra registrado");

        if(productoDTO.cantidad() < 0 ){
            throw new ProductoCantidadException("La cantidad del producto no puede ser menor a 0");
        }

        if(productoDTO.precio() < 0 ){
            throw new ProductoPrecioException("El precio del producto no puede ser menor a 0");
        }

        if(productoDTO.nombre() == null || productoDTO.nombre().isEmpty()){
            throw new ProductoNombreException("El nombre del producto no puede ser vacio");
        }

        Producto producto = productoAct.get();
        producto.setNombre(productoDTO.nombre());
        producto.setStock(productoDTO.cantidad());
        producto.setPrecio(productoDTO.precio());
        producto.setActivo(productoDTO.activo());

        return ProductoDTO.fromEntity(this.productoRepository.save(producto));
    }

    @Override
    public Boolean eliminarProducto(String id) {
        Optional<Producto> producto = this.productoRepository.findById(id);

        if(producto.isEmpty()) throw new ProductoNoEncontradoException("El producto no se encuentra registrado");

        producto.get().setEliminado(true);
        this.productoRepository.save(producto.get());
        return true;
    }

    public Boolean verificarSiExiteElCodProducto(String cod_producto) {
        return this.productoRepository.findById(cod_producto).isPresent();

    }

    @Override
    public List<String> getTiposImpuestos() {
        return tipoImpuestoRepository.findAll().stream().map(TipoImpuesto::getNombre).toList();
    }

    @Override
    public Boolean verificarCantidad(Integer cantidad, String id) {
        Optional<Producto> producto = this.productoRepository.findById(id);

        return producto.filter(value -> value.getStock() >= cantidad).isPresent();

    }

    @Override
    public Boolean isActivo(String id) {
        return this.obtenerProductoPorId(id).activo();
    }

}
