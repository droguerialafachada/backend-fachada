package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoPrecioCompraException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoPrecioCompraValidator implements ProductoValidator<Object> {

    @Override
    public void validate(Object dto) {
        Double precioCompra = null;
        if (dto instanceof CrearProductoDTO crearProductoDTO) {
            precioCompra = crearProductoDTO.precioCompra();
        } else if (dto instanceof ActualizarProductoDTO actualizarProductoDTO) {
            precioCompra = actualizarProductoDTO.precioCompra();
        }

        if (precioCompra == null) {
            throw new ProductoPrecioCompraException("El precio de compra del producto no puede estar vacío");
        }

        if(precioCompra < 0) {
            throw new ProductoPrecioCompraException("El precio de compra del producto no puede ser negativo");
        }
    }
}
