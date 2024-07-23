package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.usuario.UsuarioDTO;
import org.facturacion.facturacion.services.specification.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestParam() String username, @RequestParam() String password) {
        return ResponseEntity.ok(usuarioService.login(username, password));
    }

}
