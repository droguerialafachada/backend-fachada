package org.facturacion.facturacion.validators.producto.interfaces;

public interface ProductoValidator {
    void validate(Object objectDTO);
    boolean supports(Class<?> clazz);
}
