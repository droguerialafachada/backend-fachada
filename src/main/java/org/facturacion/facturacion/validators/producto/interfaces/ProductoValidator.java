package org.facturacion.facturacion.validators.producto.interfaces;

import org.facturacion.facturacion.dto.producto.CrearProductoDTO;

public interface ProductoValidator {
    void validate(CrearProductoDTO productoDTO);
}
