package org.facturacion.facturacion.validators.formaVenta;

import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaNombreException;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Component;

@Component
public class FormaVentaNombreValidator implements FormaVentaValidator<Object> {

    @Override
    public void validate(Object dto) {
        String nombre = null;
        if (dto instanceof CrearFormaVentaDTO crearFormaVentaDTO) {
            nombre = crearFormaVentaDTO.nombre();
        } /*else if (dto instanceof ActualizarFormaVentaDTO actualizarFormaVentaDTO) {
            nombre = actualizarFormaVentaDTO.getNombre();
        }*/

        if (nombre == null || nombre.isBlank()) {
            throw new FormaVentaNombreException("El nombre de la forma de venta no puede estar vacío");
        }

        if(nombre.length() < 3) {
            throw new FormaVentaNombreException("El nombre de la forma de venta debe tener al menos 3 caracteres");
        }

        if(nombre.length() > 50) {
            throw new FormaVentaNombreException("El nombre de la forma de venta no puede tener más de 50 caracteres");
        }
    }
}
