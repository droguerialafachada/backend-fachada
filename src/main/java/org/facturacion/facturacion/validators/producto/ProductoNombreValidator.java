package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoNombreException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoNombreValidator implements ProductoValidator<Object> {

    @Override
    public void validate(Object dto) {
        String nombre = null;
        if (dto instanceof CrearProductoDTO crearProductoDTO) {
            nombre = crearProductoDTO.nombre();
        } else if (dto instanceof ActualizarProductoDTO actualizarProductoDTO) {
            nombre = actualizarProductoDTO.nombre();
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new ProductoNombreException("El nombre del producto no puede estar vacío");
        }
    }

}
