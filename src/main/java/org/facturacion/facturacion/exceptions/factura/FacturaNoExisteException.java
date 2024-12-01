package org.facturacion.facturacion.exceptions.factura;

public class FacturaNoExisteException extends RuntimeException {
    public FacturaNoExisteException(String message) {
        super(message);
    }
}
