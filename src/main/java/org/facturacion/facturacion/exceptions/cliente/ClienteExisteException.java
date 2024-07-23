package org.facturacion.facturacion.exceptions.cliente;

public class ClienteExisteException extends RuntimeException{

    public ClienteExisteException(String mensaje){
        super(mensaje);
    }
}
