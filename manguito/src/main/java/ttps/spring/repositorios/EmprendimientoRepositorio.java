package ttps.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.spring.manguitoClases.Emprendimiento;

@Repository
public interface EmprendimientoRepositorio extends JpaRepository<Emprendimiento,Long> {
	Boolean existsByNombre(String name);
}
