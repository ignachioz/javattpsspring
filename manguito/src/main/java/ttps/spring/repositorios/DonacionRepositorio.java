package ttps.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.spring.manguitoClases.Donacion;

public interface DonacionRepositorio extends JpaRepository<Donacion,Long> {

}
