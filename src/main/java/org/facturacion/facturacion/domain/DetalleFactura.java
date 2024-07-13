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
    private double iva;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoImpuesto impuesto;

    @OneToOne
    private Producto productoList;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;
}
