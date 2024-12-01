package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoNombreException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoNombreValidator implements ProductoValidator {

    @Override
    public void validate(Object dto) {

        String nombre = (dto instanceof CrearProductoDTO crearProductoDTO)
                ? crearProductoDTO.nombre()
                : ((ActualizarProductoDTO) dto).nombre();

        if(nombre == null || nombre.isEmpty()){
            throw new ProductoNombreException("El nombre del producto no puede estar vacio");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CrearProductoDTO.class.isAssignableFrom(clazz) || ActualizarProductoDTO.class.isAssignableFrom(clazz);
    }

}
