package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.DonacionDAO;
import ttps.spring.manguitoClases.Donacion;

@Repository
public class DonacionDAOHibernateJPA extends GenericDAOHibernateJPA<Donacion> implements DonacionDAO {

	public DonacionDAOHibernateJPA() {
		super(Donacion.class);
		// TODO Auto-generated constructor stub
	}

}
