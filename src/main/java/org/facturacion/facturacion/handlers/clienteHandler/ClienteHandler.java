package org.facturacion.facturacion.handlers.clienteHandler;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.cliente.ClienteExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ClienteHandler {

    @ExceptionHandler(ClienteExisteException.class)
    public ResponseEntity<ValidationError> handleClienteExisteException(ClienteExisteException e){
        return new ResponseEntity<>(buildError(e.getMessage(), "/clientes/verificar-cliente"), HttpStatus.BAD_REQUEST);
    }

    private ValidationError buildError(String mensaje, String path){
        return new ValidationError(new Date(), mensaje, path);
    }
}
