package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.cliente.ActualizarClienteDTO;
import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.dto.cliente.CrearClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> listarClientes();

    void  obtenerClientePorId(Integer id);

    Boolean verificarSiExiteCliente(String cedula);

    ClienteDTO crearCliente(CrearClienteDTO crearClienteDTO);

    ClienteDTO actualizarCliente(ActualizarClienteDTO clienteDTO, Integer id);

    Boolean eliminarCliente(Integer id);
}
