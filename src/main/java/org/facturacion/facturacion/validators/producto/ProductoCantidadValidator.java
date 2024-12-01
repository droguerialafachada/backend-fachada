package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoCantidadException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoCantidadValidator implements ProductoValidator {

    @Override
    public void validate(Object dto) {
        Integer cantidad = (dto instanceof CrearProductoDTO crearProductoDTO)
                ? crearProductoDTO.cantidad()
                : ((ActualizarProductoDTO) dto).cantidad();

        if (cantidad < 0) {
            throw new ProductoCantidadException("La cantidad del producto no puede ser menor a 0");
        }
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return CrearProductoDTO.class.isAssignableFrom(clazz) || ActualizarProductoDTO.class.isAssignableFrom(clazz);
    }
}
