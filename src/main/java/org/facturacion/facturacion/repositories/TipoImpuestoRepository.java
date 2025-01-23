package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.TipoImpuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoImpuestoRepository extends JpaRepository<TipoImpuesto, Long> {

    Optional<TipoImpuesto> findByNombre(String nombre);

    boolean existsByNombre(String impuesto);
}
