package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoCodigoException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoCodigoValidator implements ProductoValidator {

    @Override
    public void validate(Object dto) {

        String codigo = (dto instanceof CrearProductoDTO crearProductoDTO)
                ? crearProductoDTO.codigo()
                : ((ActualizarProductoDTO) dto).codigo();

        if(codigo == null || codigo.isEmpty()){
            throw new ProductoCodigoException("El codigo del producto no puede estar vacio");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CrearProductoDTO.class.isAssignableFrom(clazz);
    }

}
