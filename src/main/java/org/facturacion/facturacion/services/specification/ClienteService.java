package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.cliente.ClienteDTO;

public interface ClienteService {

    void  listarClientes();

    void  obtenerClientePorId(Integer id);

    void verificarSiExiteCliente(String cedula);

    void crearCliente(ClienteDTO clienteDTO);

    void actualizarCliente(ClienteDTO clienteDTO);

    void eliminarCliente(Integer id);
}
