package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.cliente.ActualizarClienteDTO;
import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.dto.cliente.CrearClienteDTO;
import org.facturacion.facturacion.services.specification.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es un controlador que expone los servicios relacionados con los clientes
 * de la aplicación. Se encarga de recibir las peticiones HTTP y delegar la lógica de negocio
 * al servicio correspondiente.
 */
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    /**
     * Este método se encarga de listar todos los clientes registrados en la base de datos.
     * @return Lista de clientes registrados.
     */
    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> listarClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(clienteService.listarClientes(page, size));
    }

    /**
     * Este método se encarga de obtener todos los clientes registrados en la base de datos.
     * sin paginación.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    /**
     * Este método se encarga de obtener un cliente por su cédula.
     * @param cedula Cédula del cliente a buscar.
     * @return Cliente encontrado.
     */
    @GetMapping("/{cedula}")
    public ResponseEntity<ClienteDTO> obtenerClienteCedula(@PathVariable String cedula){
        return ResponseEntity.ok(clienteService.obtenerClientePorCedula(cedula));
    }

    /**
     * Este método se encarga de verificar si un cliente existe en la base de datos.
     * @param cedula Cédula del cliente a verificar.
     * @return True si el cliente existe, false en caso contrario.
     */
    @GetMapping("/verificar-cliente/{cedula}")
    public ResponseEntity<Boolean> verificarSiExiteCliente(@PathVariable String cedula){
        return ResponseEntity.ok(clienteService.verificarExisteCliente(cedula));
    }

    /**
     * Este método se encarga de crear un nuevo cliente.
     * @param crearClienteDTO Datos del cliente a crear.
     * @return Cliente creado.
     */
    @PostMapping("/guardar")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody CrearClienteDTO crearClienteDTO){
        return ResponseEntity.ok(clienteService.crearCliente(crearClienteDTO));
    }

    /**
     * Este método se encarga de actualizar un cliente.
     * @param clienteDTO Datos del cliente a actualizar.
     * @param id Identificador del cliente a actualizar.
     * @return Cliente actualizado.
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@RequestBody ActualizarClienteDTO clienteDTO, @PathVariable Integer id){
        return ResponseEntity.ok(clienteService.actualizarCliente(clienteDTO, id));
    }

    /**
     * Este método se encarga de eliminar un cliente.
     * @param id Identificador del cliente a eliminar.
     * @return True si el cliente fue eliminado, false en caso contrario.
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarCliente(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.eliminarCliente(id));
    }

    /**
     * Este método se encarga de verificar si un cliente fue eliminado.
     * @param cedula Cédula del cliente a verificar.
     * @return True si el cliente fue eliminado, false en caso contrario.
     */
    @GetMapping("/verificar-eliminado/{cedula}")
    public ResponseEntity<Boolean> verificarEliminado(@PathVariable String cedula){
        return ResponseEntity.ok(clienteService.verificarEliminado(cedula));
    }

    /**
     * Este método se encarga de recuperar un cliente eliminado.
     * @param cedula Cédula del cliente a recuperar.
     */
    @GetMapping("/recuperar-cliente/{cedula}")
    public void recuperarCliente(@PathVariable String cedula){
        clienteService.recuperarCliente(cedula);
    }

    /**
     * Este método se encarga de verificar si hubo cambios en la entidad Cliente.
     * @return True si hubo cambios, false en caso contrario.
     */
    @GetMapping("/verificar-cambios")
    public ResponseEntity<Boolean> verificarCambios(){
        return ResponseEntity.ok(clienteService.hayCambiosCliente());
    }
}
