package Inicializador;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ttps.spring.manguitoClases.Usuario;
import ttps.spring.repositorios.UsuarioRepositorio;

@Component
public class DataLoader implements CommandLineRunner {

    private UsuarioRepositorio usuarioRepositorio;

    public DataLoader(UsuarioRepositorio userRepository) {
        this.usuarioRepositorio = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
         LoadUsers();
    }

    private void LoadUsers() {
    	usuarioRepositorio.save(new Usuario("ignaciocampot4@gmail.com","123456",new Date(),43305648,null,null));
    }
}