package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.facturacion.facturacion.dto.formaVenta.ActualizarFormaVentaDTO;

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
    private Double precioVenta; // Precio para esta unidad de medida

    @Column(nullable = false)
    private Double precioCompra;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Boolean activo;

    /**
     * Actualiza los datos de una forma de venta existente.
     * @param formaVentaDTO DTO con los nuevos datos de la forma de venta.
     */
    public void actualizarFormaVenta(ActualizarFormaVentaDTO formaVentaDTO) {
        this.nombre = formaVentaDTO.nombre().toUpperCase();
        this.precioCompra = formaVentaDTO.precioCompra();
        this.cantidad = formaVentaDTO.cantidad();
        this.activo = true;
    }
}
