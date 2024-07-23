package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Usuario;
import org.facturacion.facturacion.dto.usuario.UsuarioDTO;
import org.facturacion.facturacion.exceptions.usuario.InformacionIncorrectaException;
import org.facturacion.facturacion.exceptions.usuario.UsuarioNoEncontradoException;
import org.facturacion.facturacion.repositories.UsuarioRepository;
import org.facturacion.facturacion.services.specification.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IUsuarioService implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO login(String usuario, String contrasena) {

        if(usuario == null || contrasena == null) throw new InformacionIncorrectaException("Hay campos vacíos en el login");
        Usuario user = usuarioRepository.findByNombre(usuario);
        if(user == null) throw new UsuarioNoEncontradoException("El usuario no se ha encontrado");
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(contrasena, user.getContrasenia()) ) throw new InformacionIncorrectaException("La contraseña es incorrecta");
        
        return new UsuarioDTO(user.getId());
    }

}
