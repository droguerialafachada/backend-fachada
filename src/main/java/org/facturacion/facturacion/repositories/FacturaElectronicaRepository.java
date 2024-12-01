package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.FacturaElectronica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaElectronicaRepository extends JpaRepository<FacturaElectronica, Integer> {
}
