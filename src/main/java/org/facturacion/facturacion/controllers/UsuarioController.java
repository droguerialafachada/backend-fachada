package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.usuario.UsuarioDTO;
import org.facturacion.facturacion.dto.usuario.UsuarioLoginDTO;
import org.facturacion.facturacion.services.specification.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Esta clase es un controlador que expone los servicios relacionados con los usuarios
 * de la aplicación. Se encarga de recibir las peticiones HTTP y delegar la lógica de negocio
 * al servicio correspondiente.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Este método recibe una petición POST en la ruta /login y se encarga de realizar
     * el login de un usuario. Recibe un objeto UsuarioLoginDTO con el nombre de usuario
     * y la contraseña. Retorna un objeto UsuarioDTO con el ID del usuario logueado.
     * @param usuario el objeto UsuarioLoginDTO con el nombre de usuario y la contraseña
     * @return UsuarioDTO el objeto UsuarioDTO con el ID del usuario logueado
     */
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioLoginDTO usuario) {
        return ResponseEntity.ok(usuarioService.login(usuario));
    }

}
