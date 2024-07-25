package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.domain.Usuario;
import org.facturacion.facturacion.dto.usuario.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO login(String usuario, String contrasena);

    Usuario findById(Integer usuario);
}
