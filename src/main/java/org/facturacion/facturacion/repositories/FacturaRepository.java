package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
