package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, precision = 2)
    private Integer cantidad;

    @Column(nullable = false, precision = 4)
    private Double valor;

    @OneToOne
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;
}
