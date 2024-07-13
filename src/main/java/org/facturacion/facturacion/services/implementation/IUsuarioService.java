package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.facturacion.facturacion.repositories.UsuarioRepository;
import org.facturacion.facturacion.services.specification.UsuarioService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class IUsuarioService implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void login(String usuario, String contrasena) {
        usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena);
    }

}
