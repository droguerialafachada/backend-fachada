package org.facturacion.facturacion.dto.efactura;

import org.facturacion.facturacion.domain.FacturaElectronica;
import org.facturacion.facturacion.domain.Venta;

import java.util.Date;

public record CrearEFacturaDTO(
        Integer idVenta,
        Date fecha
) {
    public FacturaElectronica toEntity() {
        FacturaElectronica facturaElectronica = new FacturaElectronica();
        //La fecha de la factura es la fecha de creación no la fecha de la venta
        facturaElectronica.setFecha(new java.util.Date());
        return facturaElectronica;
    }
}
