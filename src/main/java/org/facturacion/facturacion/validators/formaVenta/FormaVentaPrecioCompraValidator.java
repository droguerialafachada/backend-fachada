package org.facturacion.facturacion.validators.formaVenta;

import org.facturacion.facturacion.dto.formaVenta.ActualizarFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoPrecioCompraException;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Component;

@Component
public class FormaVentaPrecioCompraValidator implements FormaVentaValidator<Object> {

    @Override
    public void validate(Object dto) {
        Double precioCompra = null;
        if (dto instanceof CrearFormaVentaDTO crearFormaVentaDTO) {
            precioCompra = crearFormaVentaDTO.precioCompra();
        } else if (dto instanceof ActualizarFormaVentaDTO actualizarFormaVentaDTO) {
            precioCompra = actualizarFormaVentaDTO.precioCompra();
        }

        if (precioCompra == null) {
            throw new ProductoPrecioCompraException("El precio de compra de la forma venta producto no puede estar vacío");
        }
        try{
            Double.parseDouble(String.valueOf(precioCompra));
        }catch (NumberFormatException e){
            throw new ProductoPrecioCompraException("El precio de compra del producto debe ser un número");
        }
        if(precioCompra < 0) {
            throw new ProductoPrecioCompraException("El precio de compra del producto no puede ser negativo");
        }
    }
}
