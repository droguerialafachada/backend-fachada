package org.facturacion.facturacion.exceptions.venta;

public class VentaDescuentoNegativoException extends RuntimeException{

    public VentaDescuentoNegativoException(String mensaje){
        super(mensaje);
    }
}
