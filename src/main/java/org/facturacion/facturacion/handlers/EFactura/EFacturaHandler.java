package org.facturacion.facturacion.handlers.EFactura;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.efactura.EFacturaNoExisteException;
import org.facturacion.facturacion.exceptions.efactura.VentaCanceladaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.facturacion.facturacion.utils.Methods.buildError;

/**
 * Esta clase se encarga de manejar las excepciones arrrojadas por la clase EFacturaService
 * @see org.facturacion.facturacion.services.implementation.IFacturaElectronicaService
 */
@ControllerAdvice
public class EFacturaHandler {

    /**
     * Este metodo se encarga de manejar la excepcion EFacturaNoExisteException
     * @param e Excepcion EFacturaNoExisteException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity
     * con un mensaje de error y un codigo de estado HTTP 400
     */
    @ExceptionHandler(EFacturaNoExisteException.class)
    public ResponseEntity<ValidationError> handleEFacturaNoExisteException(EFacturaNoExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/efacturas"), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    /**
     * Este metodo se encarga de manejar la excepcion VentaCanceladaException
     * @param e Excepcion VentaCanceladaException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity
     * con un mensaje de error y un codigo de estado HTTP 400
     */

    @ExceptionHandler(VentaCanceladaException.class)
    public ResponseEntity<ValidationError> handleVentaCanceladaException(VentaCanceladaException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/efacturas"), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

}
