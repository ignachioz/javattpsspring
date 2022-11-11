package Inicializador;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import ttps.spring.manguitoClases.Usuario;
import ttps.spring.repositorios.UsuarioRepositorio;

@SpringBootApplication
public class InicializadorSpring implements CommandLineRunner {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(InicializadorSpring.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	usuarioRepositorio.save(new Usuario("ignaciocampot4@gmail.com","123456",new Date(),43305648,null,null));
    }

}


