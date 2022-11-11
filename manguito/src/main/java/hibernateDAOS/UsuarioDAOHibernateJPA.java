package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.UsuarioDAO;
import ttps.spring.manguitoClases.Usuario;


@Repository
public class UsuarioDAOHibernateJPA extends GenericDAOHibernateJPA<Usuario> implements UsuarioDAO {

	public UsuarioDAOHibernateJPA() {
		super(Usuario.class);
		// TODO Auto-generated constructor stub
	}

	
	
}
