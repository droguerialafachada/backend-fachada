package org.facturacion.facturacion.exceptions.usuario;

public class UsuarioNoEncontradoException extends RuntimeException{

    public UsuarioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
