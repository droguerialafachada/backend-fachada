package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.domain.Cliente;
import org.facturacion.facturacion.dto.cliente.ActualizarClienteDTO;
import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.dto.cliente.CrearClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> listarClientes();

    ClienteDTO  obtenerClientePorCedula(String id);

    Boolean verificarExisteCliente(String cedula);

    ClienteDTO crearCliente(CrearClienteDTO crearClienteDTO);

    ClienteDTO actualizarCliente(ActualizarClienteDTO clienteDTO, Integer id);

    Boolean eliminarCliente(Integer id);

    Boolean verificarEliminado(String cedula);

    void recuperarCliente(String cedula);

    Cliente findByCedula(String cliente);

}
