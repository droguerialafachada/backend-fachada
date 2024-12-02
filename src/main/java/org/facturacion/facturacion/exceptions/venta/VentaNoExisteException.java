package org.facturacion.facturacion.exceptions.venta;

public class VentaNoExisteException extends RuntimeException {
    public VentaNoExisteException(String message) {
        super(message);
    }
}
