package org.facturacion.facturacion.handlers.clienteHandler;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.cliente.ClienteExisteException;
import org.facturacion.facturacion.exceptions.cliente.ClienteNoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

import static org.facturacion.facturacion.utils.Methods.buildError;

/**
 * Esta clase se encarga de manejar las excepciones arrrojadas por la clase ClienteService
 * @see org.facturacion.facturacion.services.implementation.IClienteService
 */
@ControllerAdvice
public class ClienteHandler {

    /**
     * Este metodo se encarga de manejar la excepcion ClienteExisteException
     * @param e Excepcion ClienteExisteException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity con un mensaje de error y un codigo de estado HTTP 400
     */
    @ExceptionHandler(ClienteExisteException.class)
    public ResponseEntity<ValidationError> handleClienteExisteException(ClienteExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/clientes/verificar-cliente"), HttpStatus.NOT_FOUND);
    }
    /**
     * Este metodo se encarga de manejar la excepcion ClienteNoExisteException
     * @param e Excepcion ClienteNoExisteException
     * @return ResponseEntity<ValidationError> Retorna un objeto de tipo ResponseEntity con un mensaje de error y un codigo de estado HTTP 400
     */
    @ExceptionHandler(ClienteNoExisteException.class)
    public ResponseEntity<ValidationError> handlerClienteNoExisteException(ClienteNoExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/clientes/"), HttpStatus.BAD_REQUEST);
    }

}
