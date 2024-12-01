package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoNombreException;
import org.facturacion.facturacion.validators.producto.interfaces.ActualizarProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.CrearProductoValidator;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoNombreValidator implements ProductoValidator, CrearProductoValidator, ActualizarProductoValidator {

    @Override
    public void validate(CrearProductoDTO productoDTO) {
        if(productoDTO.nombre() == null || productoDTO.nombre().isEmpty()){
            throw new ProductoNombreException("El nombre del producto no puede estar vacio");
        }
    }

}
