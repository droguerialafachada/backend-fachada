package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.usuario.UsuarioDTO;
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
     * Este método se encarga de realizar el login de un usuario.
     * @param username Nombre de usuario.
     *                 Ejemplo: "admin"
     * @param password Contraseña del usuario.
     *                 Ejemplo: "admin"
     * @return Usuario logueado.
     */
    @GetMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestParam() String username, @RequestParam() String password) {
        return ResponseEntity.ok(usuarioService.login(username, password));
    }

}
