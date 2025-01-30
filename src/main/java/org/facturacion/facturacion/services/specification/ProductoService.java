package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.domain.FormaVenta;
import org.facturacion.facturacion.domain.Producto;
import org.facturacion.facturacion.dto.formaVenta.ActualizarFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.FormaVentaDTO;
import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.dto.producto.FullProductoDTO;
import org.facturacion.facturacion.dto.producto.ProductoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {
    Page<ProductoDTO> listarProducto(int page, int size);

    ProductoDTO obtenerProductoPorId(String id);

    ProductoDTO crearProducto(CrearProductoDTO productoDTO);

    ProductoDTO actualizarProducto(ActualizarProductoDTO productoDTO);

    Boolean eliminarProducto(String id);

    Boolean verificarExisteElCodProducto(String codProducto);

    List<String> getTiposImpuestos();

    Boolean verificarCantidad(Integer cantidad, String id, String nombreFormaVenta);


    Boolean fueEliminado(String id);

    void recuperarProducto(String id);

    Producto findById(String s);

    void guardar(Producto producto);

    List<ProductoDTO> listarProducto();

    Boolean verificarCambios();

    Producto findByCodigo(String codigo);

    FullProductoDTO actualizarFormaVenta(String codigo, ActualizarFormaVentaDTO formaVentaDTO);

    FullProductoDTO obtenerProductoCompletoPorCodigo(String codigo);

    FormaVenta findFormaVentaByProductoAndNombre(Producto producto, String nombre);

    List<FormaVentaDTO> obtenerFormasVenta(String codigo);
}
