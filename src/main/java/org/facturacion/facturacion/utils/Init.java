package org.facturacion.facturacion.utils;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Usuario;
import org.facturacion.facturacion.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Init implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        usuarioRepository.deleteAll();
        Usuario usuario = new Usuario();
        usuario.setContrasenia(new BCryptPasswordEncoder().encode("admin"));
        usuario.setNombre("admin@gmail.com");
        usuarioRepository.save(usuario);
    }
}
