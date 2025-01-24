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

    public void setValues(String completada, CrearVentaDTO ventaDTO, Double IVA) {
        this.fecha = new Date();
        this.estado = completada;
        this.subTotal = this.total - this.total * IVA;
        if(ventaDTO.descuento() == null)
            throw new VentaDescuentoNullException("El descuento de la venta no puede ser nulo");
        if(ventaDTO.descuento() < 0 )
            throw new VentaDescuentoNegativoException("El descuento de la venta no puede ser negativo");
        this.total = this.total - ventaDTO.descuento();
        this.descuento = ventaDTO.descuento();
        if(ventaDTO.cambio() == null) throw new VentaCambioNullException("El cambio de la venta no puede ser nulo");
        if(ventaDTO.cambio() < 0) throw new VentaCambioNegativoException("El cambio de la venta no puede ser negativo");
        this.cambio = ventaDTO.cambio();
        if(ventaDTO.dineroRecibido() == null)
            throw new VentaDineroNullException("El dinero recibido no puede ser nulo");
        if(ventaDTO.dineroRecibido() < total - descuento)
            throw new VentaCambioNegativoException("El dinero recibido no puede ser menor al total menos el descuento");
        if(ventaDTO.dineroRecibido() < 0)
            throw new VentaCambioNegativoException("El dinero recibido no puede ser negativo");
        this.dineroRecibido = ventaDTO.dineroRecibido();
    }
}
