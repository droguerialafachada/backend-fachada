package org.facturacion.facturacion.exceptions.venta;

public class VentaDineroNegativoException extends RuntimeException {
    public VentaDineroNegativoException(String message) {
        super(message);
    }
}
