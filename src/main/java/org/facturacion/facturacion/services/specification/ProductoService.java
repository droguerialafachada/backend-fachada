package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.dto.producto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarProducto();

    void obtenerProductoPorId(Integer id);

    ProductoDTO crearProducto(CrearProductoDTO productoDTO);

    ProductoDTO actualizarProducto(ActualizarProductoDTO productoDTO);

    Boolean eliminarProducto(String id);

    void verificarSiExiteElCodProducto(String cod_producto);

    List<String> getTiposImpuestos();
}
