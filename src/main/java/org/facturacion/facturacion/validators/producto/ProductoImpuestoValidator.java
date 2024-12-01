package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoImpuestoException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoImpuestoValidator implements ProductoValidator {

    @Override
    public void validate(Object dto) {
        String impuesto = ((CrearProductoDTO) dto).impuesto();
        if(impuesto == null || impuesto.isEmpty()){
            throw new ProductoImpuestoException("El impuesto del producto no puede estar vacio");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CrearProductoDTO.class.isAssignableFrom(clazz);
    }
}
