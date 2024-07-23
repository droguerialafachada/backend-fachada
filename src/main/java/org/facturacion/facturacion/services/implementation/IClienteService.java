package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Cliente;
import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.dto.cliente.CrearClienteDTO;
import org.facturacion.facturacion.exceptions.cliente.ClienteExisteException;
import org.facturacion.facturacion.repositories.ClienteRepository;
import org.facturacion.facturacion.services.specification.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
public class IClienteService implements ClienteService {

    private final ClienteRepository clienteRepository;

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
    public ClienteDTO crearCliente(@Validated CrearClienteDTO crearClienteDTO) {

        if(clienteRepository.findByCedula(crearClienteDTO.cedula()) != null){
            throw new ClienteExisteException(crearClienteDTO.cedula());
        }
        Cliente cliente = crearClienteDTO.ToEntity();
        return new ClienteDTO(cliente.getCedula(), cliente.getDireccion(), cliente.getCorreo(), cliente.isActivo(), cliente.getFechaCreacion());

    }

    @Override
    public void actualizarCliente(ClienteDTO clienteDTO) {

    }

    @Override
    public void eliminarCliente(Integer id) {

    }
}
