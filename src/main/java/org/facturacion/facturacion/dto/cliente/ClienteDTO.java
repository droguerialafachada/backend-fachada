package org.facturacion.facturacion.dto.cliente;

import org.facturacion.facturacion.domain.Cliente;

import java.util.Date;

public record ClienteDTO(
       String cedula,
       String direccion,
       String correo,
       boolean activo,
       Date fechaCreacion
) {

}
