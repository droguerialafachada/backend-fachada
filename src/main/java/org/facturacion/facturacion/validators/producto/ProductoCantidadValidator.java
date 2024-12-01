package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoCantidadException;
import org.facturacion.facturacion.validators.producto.interfaces.ActualizarProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.CrearProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoCantidadValidator implements ProductoValidator, CrearProductoValidator, ActualizarProductoValidator {

    @Override
    public void validate(CrearProductoDTO productoDTO) {
        if(productoDTO.cantidad() == null || productoDTO.cantidad() <= 0){
            throw new ProductoCantidadException("La cantidad del producto no puede ser nula o menor a 0");
        }
    }
}
