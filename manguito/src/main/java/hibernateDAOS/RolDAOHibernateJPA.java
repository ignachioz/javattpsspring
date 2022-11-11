package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.RolDAO;
import ttps.spring.manguitoClases.Rol;

@Repository
public class RolDAOHibernateJPA extends GenericDAOHibernateJPA<Rol> implements RolDAO {

	public RolDAOHibernateJPA() {
		super(Rol.class);
		// TODO Auto-generated constructor stub
	}

}
