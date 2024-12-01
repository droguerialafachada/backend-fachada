package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoImpuestoException;
import org.facturacion.facturacion.validators.producto.interfaces.CrearProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoImpuestoValidator implements ProductoValidator, CrearProductoValidator {

    @Override
    public void validate(CrearProductoDTO productoDTO) {
        if(productoDTO.impuesto() == null || productoDTO.impuesto().isEmpty()){
            throw new ProductoImpuestoException("El impuesto del producto no puede estar vacio");
        }
    }
}
