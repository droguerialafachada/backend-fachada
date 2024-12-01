package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoCantidadException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoCantidadValidator implements ProductoValidator<Object> {

    @Override
    public void validate(Object dto) {
        Integer cantidad = null;
        if (dto instanceof CrearProductoDTO crearProductoDTO) {
            cantidad =  crearProductoDTO.cantidad();
        } else if (dto instanceof ActualizarProductoDTO actualizarProductoDTO) {
            cantidad = actualizarProductoDTO.cantidad();
        }

        if (cantidad == null || cantidad < 0) {
            throw new ProductoCantidadException("La cantidad del producto no puede ser menor a 0");
        }
    }
}
