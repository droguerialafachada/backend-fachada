package org.facturacion.facturacion.dto.venta;

import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.dto.detalleVenta.FullDetalleDTO;

import java.util.List;

public record FullVentaDTO(
        Integer id,
        String fecha,
        Double total,
        String nombreCliente,
        String cedulaCliente,
        String direccionCliente,
        String correoCliente,
        String usuario,
        Double dineroRecibido,
        Double cambio,
        Double descuento,
        List<FullDetalleDTO> detalleVentaList
) {
    public static FullVentaDTO fromEntity(Venta venta) {
        return new FullVentaDTO(
                venta.getId(),
                venta.getFecha().toString(),
                venta.getTotal(),
                venta.getCliente().getNombre(),
                venta.getCliente().getCedula(),
                venta.getCliente().getDireccion(),
                venta.getCliente().getCorreo(),
                venta.getUsuario().getNombre(),
                venta.getDineroRecibido(),
                venta.getCambio(),
                venta.getDescuento(),
                FullDetalleDTO.fromEntityList(venta.getDetalleVentaList())
        );
    }
}
