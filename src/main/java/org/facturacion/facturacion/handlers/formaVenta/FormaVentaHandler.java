package org.facturacion.facturacion.handlers.formaVenta;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaNombreException;
import org.facturacion.facturacion.exceptions.formaVenta.FormaVentaPrecioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.facturacion.facturacion.utils.Methods.buildError;

@ControllerAdvice
public class FormaVentaHandler {


    @ExceptionHandler(FormaVentaNombreException.class)
    public ResponseEntity<ValidationError> handleFormaVentaNombreException(FormaVentaNombreException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos"), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FormaVentaPrecioException.class)
    public ResponseEntity<ValidationError> handleFormaVentaPrecioException(FormaVentaPrecioException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos"), org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
