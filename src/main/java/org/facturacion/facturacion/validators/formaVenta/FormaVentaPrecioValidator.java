package org.facturacion.facturacion.validators.formaVenta;

import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaPrecioException;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Component;

@Component
public class FormaVentaPrecioValidator implements FormaVentaValidator<Object> {

    @Override
    public void validate(Object dto) {
        Double precio = null;
        if (dto instanceof CrearFormaVentaDTO crearFormaVentaDTO) {
            precio = crearFormaVentaDTO.precio();
        } /*else if (dto instanceof ActualizarFormaVentaDTO actualizarFormaVentaDTO) {
            precio = actualizarFormaVentaDTO.getPrecio();
        }*/

        if (precio == null) {
            throw new FormaVentaPrecioException("El precio de la forma de venta no puede estar vacío");
        }

        if(precio < 0) {
            throw new FormaVentaPrecioException("El precio de la forma de venta no puede ser negativo");
        }
    }
}
