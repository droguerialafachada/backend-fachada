package org.facturacion.facturacion.utils;

import org.facturacion.facturacion.dto.validationError.ValidationError;

import java.util.Date;

public class Methods {

    private Methods() {}

    /**
     * Metodo que construye un objeto ValidationError que se utiliza para devolver errores de validacion
     * @param mensaje mensaje de error
     * @param path path del error
     * @return ValidationError
     */
    public static ValidationError buildError(String mensaje, String path){
        return new ValidationError(new Date(),mensaje, path);
    }
}
