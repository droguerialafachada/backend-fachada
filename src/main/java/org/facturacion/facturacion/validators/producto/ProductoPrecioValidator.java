package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoPrecioException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoPrecioValidator implements ProductoValidator<Object> {

    /**
     * TODO: Validar el precio de cada forma de venta
     * @param dto
     */
    @Override
    public void validate(Object dto) {
        /*Double precio = null;
        if (dto instanceof CrearProductoDTO crearProductoDTO) {
            precio = crearProductoDTO.precio();
        } else if (dto instanceof ActualizarProductoDTO actualizarProductoDTO) {
            precio = actualizarProductoDTO.precio();
        }

        if (precio == null || precio < 0) {
            throw new ProductoPrecioException("El precio del producto no puede ser negativo o nulo");
        }*/
    }

}
