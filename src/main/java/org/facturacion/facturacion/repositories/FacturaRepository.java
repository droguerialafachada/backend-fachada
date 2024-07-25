package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query("SELECT COALESCE(MAX(f.id), 0) + 1 FROM Factura f")
    Integer obtenerSiguienteId();
}
