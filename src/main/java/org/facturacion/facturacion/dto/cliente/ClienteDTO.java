package org.facturacion.facturacion.dto.cliente;

import java.util.Date;

public record ClienteDTO(
       String cedula,
       String direccion,
       String correo,
       boolean activo,
       Date fechaCreacion,
       String nombre,
       String id
) {

}
