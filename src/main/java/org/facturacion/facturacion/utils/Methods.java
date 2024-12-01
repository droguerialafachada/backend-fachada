package org.facturacion.facturacion.utils;

import org.facturacion.facturacion.dto.validationError.ValidationError;

import java.util.Date;

public class Methods {

    public static ValidationError buildError(String mensaje, String path){
        return new ValidationError(new Date(),mensaje, path);
    }
}
