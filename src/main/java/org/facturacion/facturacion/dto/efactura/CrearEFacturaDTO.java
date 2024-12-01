package org.facturacion.facturacion.dto.efactura;

import org.facturacion.facturacion.domain.FacturaElectronica;
import org.facturacion.facturacion.domain.Venta;

public record CrearEFacturaDTO(
        Integer idVenta
) {
    public FacturaElectronica toEntity(Venta venta) {
        FacturaElectronica facturaElectronica = new FacturaElectronica();
        facturaElectronica.setCliente(venta.getCliente());
        facturaElectronica.setUsuario(venta.getUsuario());
        facturaElectronica.setDetalleVentaList(venta.getDetalleVentaList());
        facturaElectronica.setTotal(venta.getTotal());
        //La fecha de la factura es la fecha de creación no la fecha de la venta
        facturaElectronica.setFecha(new java.util.Date());
        return facturaElectronica;
    }
}
