package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.RedSocialDAO;
import ttps.spring.manguitoClases.RedSocial;

@Repository
public class RedSocialDAOHibernateJPA extends GenericDAOHibernateJPA<RedSocial> implements RedSocialDAO {

	public RedSocialDAOHibernateJPA() {
		super(RedSocial.class);
		// TODO Auto-generated constructor stub
	}

}
