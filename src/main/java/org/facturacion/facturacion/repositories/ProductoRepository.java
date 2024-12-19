package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    Page<Producto> findAllByEliminadoIsFalse(Pageable pageable);

}
