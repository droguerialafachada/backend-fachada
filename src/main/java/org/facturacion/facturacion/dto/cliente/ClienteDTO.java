package org.facturacion.facturacion.dto.cliente;

import org.facturacion.facturacion.domain.Cliente;

import java.util.Date;

public record ClienteDTO(
       String cedula,
       String direccion,
       String correo,
       boolean activo,
       Date fechaCreacion,
       String nombre,
       Integer id
) {

    public static ClienteDTO fromEntity(Cliente cliente) {
        return new ClienteDTO(
                cliente.getCedula(),
                cliente.getDireccion(),
                cliente.getCorreo(),
                cliente.isActivo(),
                cliente.getFechaCreacion(),
                cliente.getNombre(),
                cliente.getId()
        );
    }

}
