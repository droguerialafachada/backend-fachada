package org.facturacion.facturacion.services.validations;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoValidationService {

    private final List<ProductoValidator> validators;

    public void validate(CrearProductoDTO productoDTO, Class<?> validationGroup) {
        for (ProductoValidator validator : validators) {

            if (validationGroup.isInstance(validator)) validator.validate(productoDTO);

        }
    }
}
