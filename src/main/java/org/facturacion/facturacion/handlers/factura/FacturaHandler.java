package org.facturacion.facturacion.handlers.factura;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.efactura.EFacturaNoExisteException;
import org.facturacion.facturacion.exceptions.factura.FacturaNoExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.facturacion.facturacion.utils.Methods.buildError;

@ControllerAdvice
public class FacturaHandler {

    @ExceptionHandler(FacturaNoExisteException.class)
    public ResponseEntity<ValidationError> handleEFacturaNoExisteException(EFacturaNoExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/efacturas"), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

}
