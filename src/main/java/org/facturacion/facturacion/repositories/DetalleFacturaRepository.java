package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
}
