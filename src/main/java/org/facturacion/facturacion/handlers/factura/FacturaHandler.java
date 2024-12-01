package org.facturacion.facturacion.handlers.factura;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.efactura.EFacturaNoExisteException;
import org.facturacion.facturacion.exceptions.factura.FacturaNoExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.facturacion.facturacion.utils.Methods.buildError;

/**
 * Esta clase se encarga de manejar las excepciones arrrojadas por la clase FacturaService
 * @see org.facturacion.facturacion.services.implementation.IFacturaService
 */
@ControllerAdvice
public class FacturaHandler {

    /**
     * Este metodo se encarga de manejar la excepcion FacturaNoExisteException
     * @param e Excepcion FacturaNoExisteException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity con un
     * mensaje de error y un codigo de estado HTTP 400
     */
    @ExceptionHandler(FacturaNoExisteException.class)
    public ResponseEntity<ValidationError> handleEFacturaNoExisteException(EFacturaNoExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/efacturas"), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

}
