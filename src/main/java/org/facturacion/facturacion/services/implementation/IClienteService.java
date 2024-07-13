package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.services.specification.ClienteService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IClienteService implements ClienteService {

    @Override
    public void listarClientes() {

    }

    @Override
    public void obtenerClientePorId(Integer id) {

    }

    @Override
    public void verificarSiExiteCliente(String cedula){

    }

    @Override
    public void crearCliente(ClienteDTO clienteDTO) {

    }

    @Override
    public void actualizarCliente(ClienteDTO clienteDTO) {

    }

    @Override
    public void eliminarCliente(Integer id) {

    }
}
