package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.EmprendimientoDAO;
import ttps.spring.manguitoClases.Emprendimiento;

@Repository
public class EmprendimientoDAOHibernateJPA extends GenericDAOHibernateJPA<Emprendimiento> implements EmprendimientoDAO{

	public EmprendimientoDAOHibernateJPA() {
		super(Emprendimiento.class);
		// TODO Auto-generated constructor stub
	}

}
