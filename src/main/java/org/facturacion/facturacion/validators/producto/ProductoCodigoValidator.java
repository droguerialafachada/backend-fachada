package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoCodigoException;
import org.facturacion.facturacion.validators.producto.interfaces.CrearProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoCodigoValidator implements ProductoValidator, CrearProductoValidator {

    @Override
    public void validate(CrearProductoDTO productoDTO) {
        if(productoDTO.codigo() == null || productoDTO.codigo().isEmpty()){
            throw new ProductoCodigoException("El codigo del producto no puede estar vacio");
        }
    }
}
