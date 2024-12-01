package org.facturacion.facturacion.dto.factura;

import org.facturacion.facturacion.domain.Factura;
import org.facturacion.facturacion.domain.Venta;

public record FacturaDTO(
        Integer id,
        String fecha,
        Double total,
        String cliente,
        String usuario
) {
    public static FacturaDTO fromEntity(Factura factura) {
        return new FacturaDTO(
                factura.getId(),
                factura.getFecha().toString(),
                factura.getTotal(),
                factura.getCliente().getNombre(),
                factura.getUsuario().getNombre()
        );
    }

    public static Factura toEntity(Venta venta) {
        Factura facturaElectronica = new Factura();
        facturaElectronica.setCliente(venta.getCliente());
        facturaElectronica.setUsuario(venta.getUsuario());
        facturaElectronica.setDetalleVentaList(venta.getDetalleVentaList());
        facturaElectronica.setTotal(venta.getTotal());
        //La fecha de la factura es la fecha de creación no la fecha de la venta
        facturaElectronica.setFecha(new java.util.Date());
        return facturaElectronica;
    }
}
