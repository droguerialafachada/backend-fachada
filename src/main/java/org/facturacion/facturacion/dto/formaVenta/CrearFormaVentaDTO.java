package org.facturacion.facturacion.dto.formaVenta;

import org.facturacion.facturacion.domain.FormaVenta;
import org.facturacion.facturacion.domain.Producto;

public record CrearFormaVentaDTO(
        String nombre,
        Double precio,
        Integer cantidad,
        Boolean activo
) {

    public static CrearFormaVentaDTO fromEntity(FormaVenta formaVenta) {
        return new CrearFormaVentaDTO(
                formaVenta.getNombre(),
                formaVenta.getPrecio(),
                formaVenta.getCantidad(),
                formaVenta.getActivo()
        );
    }

    public static FormaVenta toEntity(CrearFormaVentaDTO formaVentaDTO, Producto producto) {
        FormaVenta formaVenta = new FormaVenta();
        formaVenta.setNombre(formaVentaDTO.nombre());
        formaVenta.setPrecio(formaVentaDTO.precio());
        formaVenta.setCantidad(formaVentaDTO.cantidad());
        formaVenta.setActivo(formaVentaDTO.activo());
        formaVenta.setProducto(producto);
        return formaVenta;
    }
}
