package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.facturacion.facturacion.dto.venta.CrearVentaDTO;
import org.facturacion.facturacion.exceptions.venta.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false, precision = 4)
    private Double subTotal;

    @Column(nullable = false, precision = 4)
    private Double total;

    @Column(nullable = false, precision = 4)
    private Double descuento;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalleVentaList = new ArrayList<>();

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Cliente cliente;

    @Column(nullable = false)
    private Double dineroRecibido;

    @Column(nullable = false)
    private Double cambio;

    @Column(nullable = false)
    private String estado;

    @OneToOne(mappedBy = "venta")
    private Factura factura;

    @OneToOne(mappedBy = "venta")
    private FacturaElectronica facturaElectronica;

    public void setValues(String completada,  Double IVA) {
        this.fecha = new Date();
        this.estado = completada;
        this.subTotal = this.total - this.total * IVA;
    }

    public void calcularValores(CrearVentaDTO ventaDTO){
        this.dineroRecibido = ventaDTO.dineroRecibido();
        this.total = this.total - ventaDTO.descuento();
    }
}
