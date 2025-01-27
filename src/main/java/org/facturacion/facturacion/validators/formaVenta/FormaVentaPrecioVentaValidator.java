package org.facturacion.facturacion.validators.formaVenta;

import org.facturacion.facturacion.dto.formaVenta.ActualizarFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaPrecioException;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Component;

@Component
public class FormaVentaPrecioVentaValidator implements FormaVentaValidator<Object> {

    @Override
    public void validate(Object dto) {
        Double precioVenta = null;
        if (dto instanceof CrearFormaVentaDTO crearFormaVentaDTO) {
            precioVenta = crearFormaVentaDTO.precioVenta();
        } else if (dto instanceof ActualizarFormaVentaDTO actualizarFormaVentaDTO) {
            precioVenta = actualizarFormaVentaDTO.precioVenta();
        }

        if (precioVenta == null) {
            throw new FormaVentaPrecioException("El precio de venta de la forma venta no puede estar vacío");
        }
        try{
            Double.parseDouble(String.valueOf(precioVenta));
        }catch (NumberFormatException e){
            throw new FormaVentaPrecioException("El precio de venta del producto debe ser un número");
        }
        if(precioVenta < 0) {
            throw new FormaVentaPrecioException("El precio de venta del producto no puede ser negativo");
        }
    }
}
