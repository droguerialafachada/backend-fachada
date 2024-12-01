package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoCodigoException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoCodigoValidator implements ProductoValidator<Object> {

    @Override
    public void validate(Object dto) {
        String codigo = null;
        if (dto instanceof CrearProductoDTO crearProductoDTO) {
            codigo = crearProductoDTO.codigo();
        } else if (dto instanceof ActualizarProductoDTO actualizarProductoDTO) {
            codigo = actualizarProductoDTO.codigo();
        }

        if (codigo == null || codigo.isEmpty()) {
            throw new ProductoCodigoException("El código del producto no puede estar vacío");
        }
    }

}
