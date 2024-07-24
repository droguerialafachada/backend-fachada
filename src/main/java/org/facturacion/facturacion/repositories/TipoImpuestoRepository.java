package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.TipoImpuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoImpuestoRepository extends JpaRepository<TipoImpuesto, Long> {

    TipoImpuesto findByNombre(String nombre);
}
