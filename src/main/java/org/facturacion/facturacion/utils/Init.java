package org.facturacion.facturacion.utils;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.TipoImpuesto;
import org.facturacion.facturacion.domain.Usuario;
import org.facturacion.facturacion.repositories.TipoImpuestoRepository;
import org.facturacion.facturacion.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para inicializar la base de datos
 * cuando se inicia la aplicación
 */
@AllArgsConstructor
@Component
public class Init implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final TipoImpuestoRepository tipoImpuestoRepository;

    /**
     * Este metodo se ejecuta al iniciar la aplicación
     */
    @Override
    public void run(String... args) throws Exception {
        saveUser();
        saveTipoImpuesto();
    }

    /**
     * Este metodo guarda los tipos de impuestos en la base de datos
     */
    private void saveTipoImpuesto() {
        List<TipoImpuesto> list = new ArrayList<>();

        if(tipoImpuestoRepository.findByNombre("IVA").isEmpty()){
            TipoImpuesto tipoImpuesto = new TipoImpuesto();
            tipoImpuesto.setNombre("IVA");
            tipoImpuesto.setPorcentaje(0.19);
            list.add(tipoImpuesto);
        }
        if(tipoImpuestoRepository.findByNombre("Excento de IVA").isEmpty()){
            TipoImpuesto tipoImpuesto2 = new TipoImpuesto();
            tipoImpuesto2.setNombre("Excento de IVA");
            tipoImpuesto2.setPorcentaje(0.0);
            list.add(tipoImpuesto2);
        }

        if(!list.isEmpty()) tipoImpuestoRepository.saveAll(list);
    }

    /**
     * Este metodo guarda un usuario en la base de datos
     * con contraseña encriptada
     */
    private void saveUser() {
        if(usuarioRepository.findByNombre("admin@gmail.com") != null){
            Usuario usuario = new Usuario();
            usuario.setContrasenia(new BCryptPasswordEncoder().encode("admin"));
            usuario.setNombre("admin@gmail.com");
            usuarioRepository.save(usuario);
        }
    }
}
