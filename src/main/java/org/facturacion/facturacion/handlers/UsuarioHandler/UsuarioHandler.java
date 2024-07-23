package org.facturacion.facturacion.handlers.UsuarioHandler;

import org.facturacion.facturacion.dto.validationError.ValidationError;
import org.facturacion.facturacion.exceptions.usuario.InformacionIncorrectaException;
import org.facturacion.facturacion.exceptions.usuario.UsuarioNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class UsuarioHandler {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ValidationError> handleUsuarioNoEncontradoException(UsuarioNoEncontradoException e){
        return new ResponseEntity<>(buildError("Usuario no encontrado con esas credenciales", "/login"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InformacionIncorrectaException.class)
    public ResponseEntity<ValidationError> handleInformacionIncorrectaException(InformacionIncorrectaException e){
        return new ResponseEntity<>(buildError("Información incorrecta", "/login"), HttpStatus.BAD_REQUEST);
    }

    private ValidationError buildError(String mensaje, String path){
        return new ValidationError(new Date(), mensaje, path);
    }
}
