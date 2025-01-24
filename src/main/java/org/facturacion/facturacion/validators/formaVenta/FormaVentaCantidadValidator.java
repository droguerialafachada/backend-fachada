package org.facturacion.facturacion.validators.formaVenta;

import org.facturacion.facturacion.dto.formaVenta.ActualizarFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.dto.formaVenta.FormaVentaDTO;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaCantidadInvalidaException;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Component;

@Component
public class FormaVentaCantidadValidator implements FormaVentaValidator<Object> {

    @Override
    public void validate(Object formaVentaDTO) {
        Integer cantidad = null;

        if (formaVentaDTO instanceof FormaVentaDTO formaVenta) cantidad = formaVenta.cantidad();
        if(formaVentaDTO instanceof CrearFormaVentaDTO crearFormaVentaDTO)cantidad = crearFormaVentaDTO.cantidad();
        if(formaVentaDTO instanceof ActualizarFormaVentaDTO actualizarFormaVentaDTO)
            cantidad = actualizarFormaVentaDTO.cantidad();

        if (cantidad == null)
            throw new FormaVentaCantidadInvalidaException("La cantidad de la forma de venta no puede estar vacía");
        if (cantidad < 0)
            throw new FormaVentaCantidadInvalidaException("La cantidad de la forma de venta no puede ser negativa");

    }
}
