package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoPrecioException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoPrecioValidator implements ProductoValidator {

    @Override
    public void validate(Object dto) {
        Double precio = (dto instanceof CrearProductoDTO crearProductoDTO)
                ? crearProductoDTO.precio()
                : ((ActualizarProductoDTO) dto).precio();

        if(precio == null || precio<0){
            throw new ProductoPrecioException("El precio del producto no puede estar vacio");
        }
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return CrearProductoDTO.class.isAssignableFrom(clazz) || ActualizarProductoDTO.class.isAssignableFrom(clazz);
    }
}
