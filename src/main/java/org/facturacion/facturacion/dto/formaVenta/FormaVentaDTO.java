package org.facturacion.facturacion.dto.formaVenta;

import org.facturacion.facturacion.domain.FormaVenta;
import org.facturacion.facturacion.domain.Producto;

public record FormaVentaDTO(
        String nombre,
        Double precio,
        Integer cantidad
) {

    public static FormaVentaDTO fromEntity(FormaVenta formaVenta) {
        return new FormaVentaDTO(
                formaVenta.getNombre(),
                formaVenta.getPrecio(),
                formaVenta.getCantidad()
        );
    }

    public static FormaVenta toEntity( FormaVentaDTO formaVentaDTO, Producto producto) {
        FormaVenta formaVenta = new FormaVenta();
        formaVenta.setNombre(formaVentaDTO.nombre());
        formaVenta.setPrecio(formaVentaDTO.precio());
        formaVenta.setCantidad(formaVentaDTO.cantidad());
        formaVenta.setProducto(producto);
        return formaVenta;
    }
}
