package org.facturacion.facturacion.exceptions.venta;

public class VentaDescuentoNegativo extends RuntimeException{

    public VentaDescuentoNegativo(String mensaje){
        super(mensaje);
    }
}
