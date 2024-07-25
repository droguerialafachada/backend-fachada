package org.facturacion.facturacion.exceptions.cliente;

public class ClienteNoExisteException extends RuntimeException{

    public ClienteNoExisteException(String mensaje){
        super(mensaje);
    }
}
