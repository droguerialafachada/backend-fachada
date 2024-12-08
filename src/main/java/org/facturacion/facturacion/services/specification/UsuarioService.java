package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.domain.Usuario;
import org.facturacion.facturacion.dto.usuario.UsuarioDTO;
import org.facturacion.facturacion.dto.usuario.UsuarioLoginDTO;

public interface UsuarioService {

    UsuarioDTO login(UsuarioLoginDTO usuario);

    Usuario findById(Integer usuario);
}
