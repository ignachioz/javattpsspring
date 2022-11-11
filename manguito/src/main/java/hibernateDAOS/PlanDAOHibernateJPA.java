package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.PlanDAO;
import ttps.spring.manguitoClases.Plan;

@Repository
public class PlanDAOHibernateJPA extends GenericDAOHibernateJPA<Plan> implements PlanDAO  {

	public PlanDAOHibernateJPA() {
		super(Plan.class);
		// TODO Auto-generated constructor stub
	}

}
