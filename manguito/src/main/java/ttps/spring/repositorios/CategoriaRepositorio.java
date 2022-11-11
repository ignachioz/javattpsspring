package ttps.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.spring.manguitoClases.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
	Categoria getByNombre(String nombre);
}
