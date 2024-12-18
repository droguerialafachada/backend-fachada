package org.facturacion.facturacion.repositories;

import org.facturacion.facturacion.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByCedula(String cedula);
    Boolean existsByCedula(String cedula);
    Page<Cliente> findAllByEliminadoIsFalse(Pageable pageable);
}
