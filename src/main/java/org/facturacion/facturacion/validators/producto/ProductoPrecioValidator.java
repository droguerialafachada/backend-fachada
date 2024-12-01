package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoPrecioException;
import org.facturacion.facturacion.validators.producto.interfaces.ActualizarProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.CrearProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoPrecioValidator implements ProductoValidator, CrearProductoValidator, ActualizarProductoValidator {

    @Override
    public void validate(CrearProductoDTO productoDTO) {
        if(productoDTO.precio() == null || productoDTO.precio()<0){
            throw new ProductoPrecioException("El precio del producto no puede estar vacio");
        }
    }
}
