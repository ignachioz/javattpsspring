package ttps.spring.repositorios;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.spring.manguitoClases.RedSocial;


public interface RedSocialRepositorio  extends JpaRepository<RedSocial,Long> {
}
