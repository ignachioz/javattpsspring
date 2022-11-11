package genericosDAOS;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final EntityManagerFactory em = Persistence.createEntityManagerFactory("miPersistencia");

	public static EntityManager getEMF() {
		return em.createEntityManager();
	}
}
