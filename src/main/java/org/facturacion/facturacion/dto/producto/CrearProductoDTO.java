package org.facturacion.facturacion.dto.producto;

import org.facturacion.facturacion.domain.Producto;

public record CrearProductoDTO(
        String codigo,
        String nombre,
        Double precio,
        Integer cantidad,
        String activo,
        String impuesto
) {

    public Producto toEntity() {
        Producto producto = new Producto();
        producto.setId(codigo);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(cantidad);
        producto.setActivo(activo.equals("1"));
        producto.setEliminado(false);
        producto.setFechaCreacion(new java.util.Date());

        return producto;
    }
}
