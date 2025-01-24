package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.facturacion.facturacion.dto.detalleVenta.DetalleVentaDTO;
import org.facturacion.facturacion.dto.venta.CrearVentaDTO;

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


    public void setValues(DetalleVentaDTO detalle, Producto producto, Venta venta,
                          FormaVenta formaVenta){

        this.valor = detalle.cantidad() * formaVenta.getPrecio();
        this.cantidad = detalle.cantidad();
        this.producto = producto;
        this.formaVenta = formaVenta;
        this.venta = venta;

    }
}
