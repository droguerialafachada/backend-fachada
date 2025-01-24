package org.facturacion.facturacion.validators.formaVenta;

import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaActivoException;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Component;

@Component
public class FormaVentaActivoValidator implements FormaVentaValidator<Object> {


    @Override
    public void validate(Object dto) {
        Boolean activo = null;
        if (dto instanceof CrearFormaVentaDTO crearFormaVentaDTO) {
            activo = crearFormaVentaDTO.activo();

           if(activo == null) {
                throw new FormaVentaActivoException("El campo activo no puede estar vacío");
            }

        }


    }
}
