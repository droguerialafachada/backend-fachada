package org.facturacion.facturacion.exceptions.cliente;

public class ClienteExisteException extends RuntimeException{

    public ClienteExisteException(String cedula){
        super("El cliente con cedula " + cedula + " ya existe");
    }
}
