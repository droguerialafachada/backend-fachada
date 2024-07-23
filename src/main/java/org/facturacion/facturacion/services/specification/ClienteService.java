package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.dto.cliente.CrearClienteDTO;

public interface ClienteService {

    void  listarClientes();

    void  obtenerClientePorId(Integer id);

    void verificarSiExiteCliente(String cedula);

    ClienteDTO crearCliente(CrearClienteDTO crearClienteDTO);

    void actualizarCliente(ClienteDTO clienteDTO);

    void eliminarCliente(Integer id);
}
