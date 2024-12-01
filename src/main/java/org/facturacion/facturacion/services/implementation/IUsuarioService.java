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

import java.util.Optional;

@Service
@AllArgsConstructor
public class IUsuarioService implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Este metodo realiza el login de un usuario
     * @param usuario el nombre del usuario
     * @param contrasena la contraseña del usuario
     * @return UsuarioDTO el usuario logueado
     */
    @Override
    public UsuarioDTO login(String usuario, String contrasena) {

        if(usuario == null || contrasena == null) throw new InformacionIncorrectaException("Hay campos vacíos en el login");
        Usuario user = usuarioRepository.findByNombre(usuario);
        if(user == null) throw new UsuarioNoEncontradoException("El usuario no se ha encontrado");
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(contrasena, user.getContrasenia()) ) throw new InformacionIncorrectaException("La contraseña es incorrecta");
        
        return new UsuarioDTO(user.getId());
    }

    /**
     * Este metodo busca un usuario por su id y lo retorna
     * @param id El ID del usuario
     * @return Usuario el usuario encontrado
     */
    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElseThrow(() -> new UsuarioNoEncontradoException("El usuario no se ha encontrado"));
    }

}
