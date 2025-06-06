package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.facturacion.facturacion.dto.cliente.ActualizarClienteDTO;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 15, unique = true)
    private String cedula;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private boolean eliminado;

    @Column(nullable = false)
    private Date fechaCreacion;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventasList;

    public void actualizarCliente(ActualizarClienteDTO clienteDTO) {
        this.setNombre(clienteDTO.nombre());
        this.setDireccion(clienteDTO.direccion());
        this.setCorreo(clienteDTO.correo());
        this.setActivo(clienteDTO.activo());
    }
}
