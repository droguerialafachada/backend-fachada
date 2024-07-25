package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Cliente;
import org.facturacion.facturacion.dto.cliente.ActualizarClienteDTO;
import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.dto.cliente.CrearClienteDTO;
import org.facturacion.facturacion.exceptions.cliente.ClienteExisteException;
import org.facturacion.facturacion.exceptions.cliente.ClienteNoExisteException;
import org.facturacion.facturacion.repositories.ClienteRepository;
import org.facturacion.facturacion.services.specification.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IClienteService implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> listarClientes() {

        return clienteRepository.findAllByEliminadoIsFalse().stream().map(
                cliente ->
                        new ClienteDTO(cliente.getCedula(), cliente.getDireccion(), cliente.getCorreo(), cliente.isActivo(), cliente.getFechaCreacion(), cliente.getNombre(), cliente.getId()+"")
        ).toList();
    }

    @Override
    public ClienteDTO obtenerClientePorCedula(String id) {
        Cliente cliente = clienteRepository.findByCedula(id);

        if(cliente == null) throw  new ClienteNoExisteException("No existe un cliente con esa cedula");

        return new ClienteDTO(cliente.getCedula(), cliente.getDireccion(), cliente.getCorreo(), cliente.isActivo(),
                cliente.getFechaCreacion(), cliente.getNombre(), cliente.getId()+"");
    }

    @Override
    public Boolean verificarSiExiteCliente(String cedula){
        return clienteRepository.existsByCedula(cedula);
    }

    @Override
    public ClienteDTO crearCliente(CrearClienteDTO crearClienteDTO) {

        if(clienteRepository.findByCedula(crearClienteDTO.cedula()) != null){
            throw new ClienteExisteException("El cliente con cedula "+crearClienteDTO.cedula()+" ya existe");
        }
        Cliente cliente = crearClienteDTO.ToEntity();
        clienteRepository.save(cliente);
        return new ClienteDTO(cliente.getCedula(), cliente.getDireccion(), cliente.getCorreo(), cliente.isActivo(), cliente.getFechaCreacion(), cliente.getNombre(), cliente.getId()+"");

    }

    @Override
    public ClienteDTO actualizarCliente(ActualizarClienteDTO clienteDTO, Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty()) throw new ClienteExisteException("El cliente con id "+id+" no existe");

        Cliente clienteActualizado = cliente.get();
        clienteActualizado.setNombre(clienteDTO.nombre());
        clienteActualizado.setDireccion(clienteDTO.direccion());
        clienteActualizado.setCorreo(clienteDTO.correo());
        clienteActualizado.setActivo(clienteDTO.activo());
        clienteRepository.save(clienteActualizado);
        return new ClienteDTO(clienteActualizado.getCedula(), clienteActualizado.getDireccion(), clienteActualizado.getCorreo(), clienteActualizado.isActivo(), clienteActualizado.getFechaCreacion(), clienteActualizado.getNombre(), clienteActualizado.getId()+"");
    }

    @Override
    public Boolean eliminarCliente(Integer id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()) throw new ClienteExisteException("El cliente con id "+id+" no existe");

        Cliente clienteEliminado = cliente.get();
        clienteEliminado.setEliminado(true);
        clienteRepository.save(clienteEliminado);
        return true;

    }

    @Override
    public Boolean verificarEliminado(String cedula) {
        Cliente cliente = this.clienteRepository.findByCedula(cedula);
        if(cliente != null) return cliente.isEliminado();
        return false;
    }

    @Override
    public void recuperarCliente(String cedula) {
        Cliente cliente = this.clienteRepository.findByCedula(cedula);
        cliente.setEliminado(false);
        this.clienteRepository.save(cliente);
    }

    @Override
    public Cliente findByCedula(Integer cliente) {
        return clienteRepository.findByCedula(cliente+"");
    }
}
