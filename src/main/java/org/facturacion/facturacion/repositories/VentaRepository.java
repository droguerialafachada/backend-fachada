package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT COALESCE(MAX(f.id), 0) + 1 FROM Venta f")
    Integer obtenerSiguienteId();
}
