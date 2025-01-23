package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class Producto {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(length = 200, nullable = false)
    private String nombre;

    @Column(precision = 4, nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private Date fechaCreacion;

    @Column(nullable = false)
    private boolean eliminado;

    @ManyToOne
    @JoinColumn(name = "tipo_impuesto_id")
    private TipoImpuesto impuesto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormaVenta> formaVentas;

}
