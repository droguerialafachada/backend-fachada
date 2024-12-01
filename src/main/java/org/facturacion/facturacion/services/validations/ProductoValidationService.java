package org.facturacion.facturacion.services.validations;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoValidationService {

    private final List<ProductoValidator> validators;

    public void validate(Object dto) {
        for (ProductoValidator validator : validators) {
            validator.validate(dto);
        }
    }

}
