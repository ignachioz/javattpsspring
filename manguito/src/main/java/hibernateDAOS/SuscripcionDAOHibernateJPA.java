package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.SuscripcionDAO;
import ttps.spring.manguitoClases.Suscripcion;

@Repository
public class SuscripcionDAOHibernateJPA extends GenericDAOHibernateJPA<Suscripcion> implements SuscripcionDAO {

	public SuscripcionDAOHibernateJPA() {
		super(Suscripcion.class);
		// TODO Auto-generated constructor stub
	}

}
