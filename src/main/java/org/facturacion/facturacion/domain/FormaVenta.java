package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FormaVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double precio; // Precio para esta unidad de medida

    @Column(nullable = false)
    private Integer cantidad;
}
