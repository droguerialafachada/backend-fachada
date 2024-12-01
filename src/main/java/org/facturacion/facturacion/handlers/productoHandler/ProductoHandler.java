package org.facturacion.facturacion.handlers.productoHandler;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.producto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

import static org.facturacion.facturacion.utils.Methods.buildError;

@ControllerAdvice
public class ProductoHandler {

    @ExceptionHandler(ProductoCantidadException.class)
    public ResponseEntity<ValidationError> handleProductoCantidadException(ProductoCantidadException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoCodigoException.class)
    public ResponseEntity<ValidationError> handleProductoCodigoException(ProductoCodigoException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoNombreException.class)
    public ResponseEntity<ValidationError> handleProductoNombreException(ProductoNombreException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoPrecioException.class)
    public ResponseEntity<ValidationError> handleProductoPrecioException(ProductoPrecioException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoImpuestoException.class)
    public ResponseEntity<ValidationError> handleProductoImpuestoException(ProductoImpuestoException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }


}
