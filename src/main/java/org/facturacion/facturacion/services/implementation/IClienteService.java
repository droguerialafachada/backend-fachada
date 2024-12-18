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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion de la interfaz ClienteService
 * @see org.facturacion.facturacion.services.specification.ClienteService
 * Clase que implementa los metodos de la interfaz ClienteService y se encarga de la logica de negocio
 * de los clientes
 */
@Service
@AllArgsConstructor
public class IClienteService implements ClienteService {

    private final ClienteRepository clienteRepository;

    /**
     * Metodo que se encarga de listar todos los clientes
     * iterando sobre la lista de clientes y creando un objeto de tipo ClienteDTO
     * @return List<ClienteDTO> Retorna una lista de objetos de tipo ClienteDTO
     */
    @Override
    public Page<ClienteDTO> listarClientes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.findAllByEliminadoIsFalse(pageable).map(ClienteDTO::fromEntity);
    }

    /**
     * Metodo que se encarga de obtener un cliente por su cedula
     * Si el cliente no existe se lanza una excepcion
     * @param id Cedula del cliente
     * @return ClienteDTO Retorna un objeto de tipo ClienteDTO
     */
    @Override
    public ClienteDTO obtenerClientePorCedula(String id) {
        Cliente cliente = clienteRepository.findByCedula(id);
        if(cliente == null) throw  new ClienteNoExisteException("No existe un cliente con esa cedula");
        return ClienteDTO.fromEntity(cliente);
    }

    /**
     * Metodo que se encarga de verificar si un cliente existe
     * @param cedula Cedula del cliente
     * @return Boolean Retorna un valor booleano
     */
    @Override
    public Boolean verificarExisteCliente(String cedula){
        return clienteRepository.existsByCedula(cedula);
    }

    /**
     * Metodo que se encarga de crear un cliente y guardarlo en la base de datos
     * Si el cliente ya existe se lanza una excepcion
     * @param crearClienteDTO Objeto de tipo CrearClienteDTO
     * @return ClienteDTO Retorna un objeto de tipo ClienteDTO
     */
    @Override
    public ClienteDTO crearCliente(CrearClienteDTO crearClienteDTO) {

        if(clienteRepository.findByCedula(crearClienteDTO.cedula()) != null){
            throw new ClienteExisteException("El cliente con cedula "+crearClienteDTO.cedula()+" ya existe");
        }
        Cliente cliente = crearClienteDTO.toEntity();
        clienteRepository.save(cliente);
        return ClienteDTO.fromEntity(cliente);

    }

    /**
     * Metodo que se encarga de actualizar un cliente y guardarlo en la base de datos
     * @param clienteDTO Objeto de tipo ActualizarClienteDTO
     * @param id Id del cliente a actualizar
     * @return ClienteDTO Retorna un objeto de tipo ClienteDTO
     */
    @Override
    public ClienteDTO actualizarCliente(ActualizarClienteDTO clienteDTO, Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()) throw new ClienteExisteException("El cliente con id "+id+" no existe");
        Cliente clienteActualizado = cliente.get();
        clienteActualizado.actualizarCliente(clienteDTO);
        clienteRepository.save(clienteActualizado);
        return ClienteDTO.fromEntity(clienteActualizado);
    }

    /**
     * Metodo que se encarga de eliminar un cliente
     * Si no hay un cliente con el parametro id se lanza una excepcion
     * @param id Id del cliente a eliminar
     * @return Boolean Retorna un valor booleano
     */
    @Override
    public Boolean eliminarCliente(Integer id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()) throw new ClienteExisteException("El cliente con id "+id+" no existe");
        Cliente clienteEliminado = cliente.get();
        clienteEliminado.setEliminado(true);
        clienteRepository.save(clienteEliminado);
        return true;

    }

    /**
     * Metodo que se encarga de verificar si un cliente ha sido eliminado
     * @param cedula Cedula del cliente
     * @return Boolean Retorna un valor booleano
     */
    @Override
    public Boolean verificarEliminado(String cedula) {
        Cliente cliente = this.clienteRepository.findByCedula(cedula);
        if(cliente != null) return cliente.isEliminado();
        return false;
    }

    /**
     * Metodo que se encarga de recuperar un cliente eliminado
     * @param cedula Cedula del cliente
     */
    @Override
    public void recuperarCliente(String cedula) {
        Cliente cliente = this.clienteRepository.findByCedula(cedula);
        cliente.setEliminado(false);
        this.clienteRepository.save(cliente);
    }

    /**
     * Metodo que se encarga de obtener un cliente por su cedula
     * @param cedula Objeto cliente que tiene la cedula
     * @return Cliente Retorna un objeto de tipo Cliente
     */
    @Override
    public Cliente findByCedula(String cedula) {return clienteRepository.findByCedula(cedula);}
}
