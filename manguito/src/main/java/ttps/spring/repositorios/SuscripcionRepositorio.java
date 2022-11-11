package ttps.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.spring.manguitoClases.Suscripcion;

public interface SuscripcionRepositorio extends JpaRepository<Suscripcion,Long>{
	
}
