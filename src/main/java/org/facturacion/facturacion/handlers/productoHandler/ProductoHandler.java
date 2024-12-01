package org.facturacion.facturacion.handlers.productoHandler;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.producto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

import static org.facturacion.facturacion.utils.Methods.buildError;

/**
 * Esta clase se encarga de manejar las excepciones arrrojadas por la clase ProductoService
 * @see org.facturacion.facturacion.services.implementation.IProductoService
 */
@ControllerAdvice
public class ProductoHandler {

    /**
     * Este metodo se encarga de manejar la excepcion ProductoNoEncontradoException
     * @param e Excepcion ProductoNoEncontradoException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity con un mensaje de error
     * y un codigo de estado HTTP 400
     */
    @ExceptionHandler(ProductoCantidadException.class)
    public ResponseEntity<ValidationError> handleProductoCantidadException(ProductoCantidadException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Este metodo se encarga de manejar la excepcion ProductoCodigoException
     * que se lanza cuando el codigo del producto ya existe
     * @param e Excepcion ProductoCodigoException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity con un mensaje de error
     */
    @ExceptionHandler(ProductoCodigoException.class)
    public ResponseEntity<ValidationError> handleProductoCodigoException(ProductoCodigoException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Este metodo se encarga de manejar la excepcion ProductoNombreException
     * que se lanza cuando el nombre de un producto es nulo o vacio
     * @param e Excepcion ProductoNombreException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity con un mensaje de error
     */
    @ExceptionHandler(ProductoNombreException.class)
    public ResponseEntity<ValidationError> handleProductoNombreException(ProductoNombreException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Este metodo se encarga de manejar la excepcion ProductoPrecioException que se
     * lanza cuando el precio de un producto es menor o igual a 0
     * @param e Excepcion ProductoPrecioException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity con un mensaje de error
     */
    @ExceptionHandler(ProductoPrecioException.class)
    public ResponseEntity<ValidationError> handleProductoPrecioException(ProductoPrecioException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoImpuestoException.class)
    public ResponseEntity<ValidationError> handleProductoImpuestoException(ProductoImpuestoException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/productos/guardar"), HttpStatus.BAD_REQUEST);
    }


}
