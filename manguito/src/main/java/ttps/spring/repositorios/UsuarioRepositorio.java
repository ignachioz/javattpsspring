package ttps.spring.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.spring.manguitoClases.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
	Boolean existsByEmail(String email);
	Usuario findByEmail(String email);
}
