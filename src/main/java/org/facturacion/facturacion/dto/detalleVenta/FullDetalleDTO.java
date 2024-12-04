package org.facturacion.facturacion.dto.detalleVenta;

import org.facturacion.facturacion.domain.DetalleVenta;

import java.util.List;

public record FullDetalleDTO(
        Integer id,
        Integer cantidad,
        Double precio,
        Double total,
        String producto
) {
    public static List<FullDetalleDTO> fromEntityList(List<DetalleVenta> detalleVentaList) {
        return detalleVentaList.stream()
                .map(FullDetalleDTO::fromEntity)
                .toList();
    }

    public static FullDetalleDTO fromEntity(DetalleVenta detalleVenta) {
        return new FullDetalleDTO(
                detalleVenta.getId(),
                detalleVenta.getCantidad(),
                detalleVenta.getValor(),
                detalleVenta.getValor() * detalleVenta.getCantidad(),
                detalleVenta.getProducto().getNombre()
        );
    }
}
