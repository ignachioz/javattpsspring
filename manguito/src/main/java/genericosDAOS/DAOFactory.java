package genericosDAOS;

import hibernateDAOS.CategoriaDAOHibernateJPA;
import hibernateDAOS.DonacionDAOHibernateJPA;
import hibernateDAOS.EmprendimientoDAOHibernateJPA;
import hibernateDAOS.MedioDePagoDAOHibernateJPA;
import hibernateDAOS.PlanDAOHibernateJPA;
import hibernateDAOS.PostDAOHibernateJPA;
import hibernateDAOS.RedSocialDAOHibernateJPA;
import hibernateDAOS.RolDAOHibernateJPA;
import hibernateDAOS.SuscripcionDAOHibernateJPA;
import hibernateDAOS.TipoRedSocialDAOHibernateJPA;
import hibernateDAOS.UsuarioDAOHibernateJPA;

public class DAOFactory {
	
	public static CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAOHibernateJPA();
	}
	
	public static DonacionDAO getDonacionDAO() {
		return new DonacionDAOHibernateJPA();
	}
	
	public static EmprendimientoDAO getEmprendimientoDAO() {
		return new EmprendimientoDAOHibernateJPA();
	}
	
	public static MedioDePagoDAO getMedioDePagoDAO() {
		return new MedioDePagoDAOHibernateJPA();
	}
	
	public static PlanDAO getPlanDAO() {
		return new PlanDAOHibernateJPA();
	}
	
	public static PostDAO getPostDAO() {
		return new PostDAOHibernateJPA();
	}
	
	public static RedSocialDAO getRedSocialDAO() {
		return new RedSocialDAOHibernateJPA();
	}
	
	public static RolDAO getRolDAO() {
		return new RolDAOHibernateJPA();
	}
	
	public static SuscripcionDAO getSuscripcionDAO() {
		return new SuscripcionDAOHibernateJPA();
	}
	
	public static TipoRedSocialDAO getTipoRedSocialDAO() {
		return new TipoRedSocialDAOHibernateJPA();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOHibernateJPA();
	}
	
}
