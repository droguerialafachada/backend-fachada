package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, precision = 2)
    private Integer cantidad;

    @Column(nullable = false, precision = 4)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "forma_venta_id", nullable = false)
    private FormaVenta formaVenta;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

}
