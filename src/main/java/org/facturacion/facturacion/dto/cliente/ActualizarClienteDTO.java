package org.facturacion.facturacion.dto.cliente;

public record ActualizarClienteDTO(
        String cedula,
        String direccion,
        String correo,
        String nombre,
        boolean activo
) {
}
