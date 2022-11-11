package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.TipoRedSocialDAO;
import ttps.spring.manguitoClases.TipoRedSocial;

@Repository
public class TipoRedSocialDAOHibernateJPA extends GenericDAOHibernateJPA<TipoRedSocial> implements TipoRedSocialDAO {

	public TipoRedSocialDAOHibernateJPA() {
		super(TipoRedSocial.class);
		// TODO Auto-generated constructor stub
	}

}
