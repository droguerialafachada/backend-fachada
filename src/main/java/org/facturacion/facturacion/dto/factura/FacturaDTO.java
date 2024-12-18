package org.facturacion.facturacion.dto.factura;

import org.facturacion.facturacion.domain.Factura;
import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.utils.Constants;

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
        Factura factura = new Factura();
        factura.setCliente(venta.getCliente());
        factura.setUsuario(venta.getUsuario());
        factura.setDetalleVentaList(venta.getDetalleVentaList());
        factura.setTotal(venta.getTotal());
        //La fecha de la factura es la fecha de creación no la fecha de la venta
        factura.setFecha(new java.util.Date());
        factura.setEstado(Constants.ESTADO_FACTURA_GENERADA);
        factura.setSubTotal(venta.getSubTotal());
        return factura;
    }
}
