package org.facturacion.facturacion.handlers.venta;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.venta.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.facturacion.facturacion.utils.Methods.buildError;

/**
 * Clase que maneja las excepciones de las ventas
 */
@ControllerAdvice
public class VentaHandler {

    @ExceptionHandler(VentaNoExisteException.class)
    public ResponseEntity<ValidationError> handleVentaNoEncontradaException(VentaNoExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VentaCanceladaException.class)
    public ResponseEntity<ValidationError> handleVentaCanceladaException(VentaCanceladaException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VentaDescuentoNegativoException.class)
    public ResponseEntity<ValidationError> handleVentaDescuentoNegativoException(VentaDescuentoNegativoException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VentaCambioNegativoException.class)
    public ResponseEntity<ValidationError> handleVentaCambioNegativoException(VentaCambioNegativoException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VentaDineroNegativoException.class)
    public ResponseEntity<ValidationError> handleVentaDineroNegativoException(VentaDineroNegativoException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VentaDineroNullException.class)
    public ResponseEntity<ValidationError> handleVentaDineroNullException(VentaDineroNullException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VentaCambioNullException.class)
    public ResponseEntity<ValidationError> handleVentaCambioNullException(VentaCambioNullException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VentaDescuentoNullException.class)
    public ResponseEntity<ValidationError> handleVentaDescuentoNullException(VentaDescuentoNullException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/venta"), HttpStatus.BAD_REQUEST);
    }
}
