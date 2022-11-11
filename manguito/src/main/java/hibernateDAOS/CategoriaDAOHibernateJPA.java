package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.CategoriaDAO;
import ttps.spring.manguitoClases.Categoria;

@Repository
public class CategoriaDAOHibernateJPA extends GenericDAOHibernateJPA<Categoria> implements CategoriaDAO{

	public CategoriaDAOHibernateJPA() {
		super(Categoria.class);	
	}

}
