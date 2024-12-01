package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.FacturaElectronica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaElectronicaRepository extends JpaRepository<FacturaElectronica, Integer> {
    boolean existsById(Integer id);

    Optional<FacturaElectronica> findById(Integer id);
}
