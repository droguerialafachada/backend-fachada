package org.facturacion.facturacion.handlers.EFactura;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.efactura.EFacturaNoExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.facturacion.facturacion.utils.Methods.buildError;

@ControllerAdvice
public class EFacturaHandler {

    @ExceptionHandler(EFacturaNoExisteException.class)
    public ResponseEntity<ValidationError> handleEFacturaNoExisteException(EFacturaNoExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/efacturas"), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

}
