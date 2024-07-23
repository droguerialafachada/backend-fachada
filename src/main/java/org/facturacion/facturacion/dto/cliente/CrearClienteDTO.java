package org.facturacion.facturacion.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.facturacion.facturacion.domain.Cliente;

import java.util.Date;

public record CrearClienteDTO(
        @NotBlank
        @Pattern(regexp = "^[0-9]{10}$", message = "La cedula debe tener 10 digitos")
        String cedula,
        @NotBlank
        String direccion,
        @NotBlank
        @Email
        String correo,
        @NotBlank
        String nombre
) {

        public  Cliente ToEntity(){
                return new Cliente(null, cedula, nombre, direccion, correo, true,false, new Date(), null);
        }
}
