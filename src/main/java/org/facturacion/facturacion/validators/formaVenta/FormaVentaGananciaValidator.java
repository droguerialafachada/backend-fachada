package org.facturacion.facturacion.validators.formaVenta;

import org.facturacion.facturacion.dto.formaVenta.ActualizarFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaGananciaException;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Component;

@Component
public class FormaVentaGananciaValidator implements FormaVentaValidator<Object> {

    @Override
    public void validate(Object dto) {
        Double precioVenta = null;
        Double precioCompra = null;
        if (dto instanceof CrearFormaVentaDTO crearFormaVentaDTO) {
            precioVenta = crearFormaVentaDTO.precioVenta();
            precioCompra = crearFormaVentaDTO.precioCompra();
        } else if (dto instanceof ActualizarFormaVentaDTO actualizarFormaVentaDTO) {
            precioVenta = actualizarFormaVentaDTO.precioVenta();
            precioCompra = actualizarFormaVentaDTO.precioCompra();
        }

        if (precioVenta != null && precioCompra != null && precioVenta < precioCompra) {
            throw new FormaVentaGananciaException("El precio de venta no puede ser menor al precio de compra");
        }
    }
}
