package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoCantidadValidator implements ProductoValidator<Object> {

    /**
     * TODO: Validar la cantidad de cada forma de venta
     * @param dto
     */
    @Override
    public void validate(Object dto) {
        /*Integer cantidad = null;
        if (dto instanceof CrearProductoDTO crearProductoDTO) {
            cantidad =  crearProductoDTO.cantidad();
        } else if (dto instanceof ActualizarProductoDTO actualizarProductoDTO) {
            cantidad = actualizarProductoDTO.cantidad();
        }

        if (cantidad == null || cantidad < 0) {
            throw new ProductoCantidadException("La cantidad del producto no puede ser menor a 0");
        }*/
    }
}
