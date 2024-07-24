package org.facturacion.facturacion.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class Producto {

    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Column(length = 200, nullable = false)
    private String nombre;

    @Column(precision = 4, nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private Date fechaCreacion;

    @Column(nullable = false)
    private boolean eliminado;
}
