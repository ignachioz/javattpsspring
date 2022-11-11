package hibernateDAOS;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import genericosDAOS.EMF;
import genericosDAOS.MedioDePagoDAO;
import ttps.spring.manguitoClases.MedioDePago;

@Repository
public class MedioDePagoDAOHibernateJPA extends GenericDAOHibernateJPA<MedioDePago> implements MedioDePagoDAO {

	public MedioDePagoDAOHibernateJPA() {
		super(MedioDePago.class);
	}

	public MedioDePago obtenerMedioDePagoPorNombre(String nombre) {
		EntityManager em = EMF.getEMF();
		return (MedioDePago) em
				.createQuery("select m from " + this.getPersistentClass().getSimpleName()+" m where nombre='" + nombre + "'")
				.getSingleResult();
	}
	
	
}
