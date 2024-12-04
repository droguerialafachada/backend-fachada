package org.facturacion.facturacion.dto.venta;

import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.dto.detalleVenta.FullDetalleDTO;

import java.util.List;

public record FullVentaDTO(
        Integer id,
        String fecha,
        Double total,
        String cliente,
        String usuario,
        List<FullDetalleDTO> detalleVentaList
) {
    public static FullVentaDTO fromEntity(Venta venta) {
        return new FullVentaDTO(
                venta.getId(),
                venta.getFecha().toString(),
                venta.getTotal(),
                venta.getCliente().getNombre(),
                venta.getUsuario().getNombre(),
                FullDetalleDTO.fromEntityList(venta.getDetalleVentaList())
        );
    }
}
