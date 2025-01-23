package org.facturacion.facturacion.services.validations;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.validators.formaVenta.interfaces.FormaVentaValidator;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class FormaVentaValidationService {

    private final List<FormaVentaValidator> validators;

    public void validate(Object dto) {
        for (FormaVentaValidator validator : validators) {
            validator.validate(dto);
        }
    }
}
