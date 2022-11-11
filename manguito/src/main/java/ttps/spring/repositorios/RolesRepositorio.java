package ttps.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.spring.manguitoClases.Rol;

@Repository
public interface RolesRepositorio extends JpaRepository<Rol,Long> {

}
