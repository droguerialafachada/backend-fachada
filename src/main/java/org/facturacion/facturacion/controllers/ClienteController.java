package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.cliente.ClienteDTO;
import org.facturacion.facturacion.services.specification.ClienteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public void  listarClientes(){
        clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public void  obtenerClienteId(@PathVariable Integer id){
        clienteService.obtenerClientePorId(id);
    }

    @GetMapping("/verificar-cliente/{cedula}")
    public void verificarSiExiteCliente(@PathVariable String cedula){
        clienteService.verificarSiExiteCliente(cedula);
    }

    @PostMapping("/crear")
    public void crearCliente(@RequestBody ClienteDTO clienteDTO){
        clienteService.crearCliente(clienteDTO);
    }

    @PutMapping("/actualizar")
    public void actualizarCliente(@RequestBody ClienteDTO clienteDTO){
        clienteService.actualizarCliente(clienteDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCliente(@PathVariable Integer id){
        clienteService.eliminarCliente(id);
    }
}
