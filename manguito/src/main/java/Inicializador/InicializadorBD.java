package Inicializador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import genericosDAOS.CategoriaDAO;
import genericosDAOS.DAOFactory;
import genericosDAOS.EMF;
import genericosDAOS.EmprendimientoDAO;
import genericosDAOS.MedioDePagoDAO;
import genericosDAOS.RolDAO;
import genericosDAOS.TipoRedSocialDAO;
import genericosDAOS.UsuarioDAO;
import hibernateDAOS.TipoRedSocialDAOHibernateJPA;
import ttps.spring.manguitoClases.Categoria;
import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.manguitoClases.MedioDePago;
import ttps.spring.manguitoClases.Rol;
import ttps.spring.manguitoClases.TipoRedSocial;
import ttps.spring.manguitoClases.Usuario;

public class InicializadorBD {
	
	static void inicializador() {
		//EMF.getEMF();
		//Roles del sistema
		Rol rolAdmin = new Rol("admin","Este rol se encarga de administrar el sistema");
		Rol rolEmprendedor = new Rol("emprendedor","Este rol es del emprendedor que tiene un emprendimiento");	
		RolDAO rolDAOAdmin = DAOFactory.getRolDAO();
		RolDAO rolDAOEmprendedor = DAOFactory.getRolDAO();
		rolDAOAdmin.persistir(rolAdmin);
		rolDAOEmprendedor.persistir(rolEmprendedor);
		List<Rol> rolesUsuario= new ArrayList<>();
		//
		
		//Creacion de 1 admin
		rolesUsuario.add(rolAdmin);
		Usuario usuario1Admin = new Usuario("ignaciocampot4@gmail.com","123456",new Date(),43305648,rolesUsuario);
		UsuarioDAO usuarioDAOAdmin = DAOFactory.getUsuarioDAO();
		usuarioDAOAdmin.persistir(usuario1Admin);
		
		//Creacion de las categorias
		CategoriaDAO categoriaDAO = DAOFactory.getCategoriaDAO();
		Categoria categoriaArte= new Categoria("Arte");
		Categoria categoriaBlog= new Categoria("Blog");
		Categoria categoriaComics= new Categoria("Comics");
		Categoria categoriaDeportes= new Categoria("Deportes");
		Categoria categoriaPeriodismo= new Categoria("Periodismo");
		Categoria categoriaEducacion= new Categoria("Educacion");
		Categoria categoriaDinero= new Categoria("Dinero");
		Categoria categoriaLiteratura= new Categoria("Literatura");
		Categoria categoriaSoftware= new Categoria("Software");
		Categoria categoriaTeatro= new Categoria("Teatro");
		categoriaDAO.persistir(categoriaArte);
		categoriaDAO.persistir(categoriaBlog);
		categoriaDAO.persistir(categoriaComics);
		categoriaDAO.persistir(categoriaDeportes);
		categoriaDAO.persistir(categoriaPeriodismo);
		categoriaDAO.persistir(categoriaEducacion);
		categoriaDAO.persistir(categoriaDinero);
		categoriaDAO.persistir(categoriaLiteratura);
		categoriaDAO.persistir(categoriaSoftware);
		categoriaDAO.persistir(categoriaTeatro);
		List<Categoria> categorias = new ArrayList<>();
		//
				
		//Creacion de las 4 tipos de red social
		TipoRedSocial youtube = new TipoRedSocial("Youtube","/icons/youtube.png");
		TipoRedSocial instagram = new TipoRedSocial("Instagram","/icons/instagram.png");
		TipoRedSocial twitter = new TipoRedSocial("Twitter","/icons/tiwtter.png");
		TipoRedSocial facebook = new TipoRedSocial("Facebook","/icons/facebook.png");
		TipoRedSocialDAO tipoRedSocialDAO = new DAOFactory().getTipoRedSocialDAO();
		tipoRedSocialDAO.persistir(youtube);
		tipoRedSocialDAO.persistir(instagram);
		tipoRedSocialDAO.persistir(twitter);
		tipoRedSocialDAO.persistir(facebook);
		
		
		//Creacion medio de pagos
		MedioDePago tarjetaCredito = new MedioDePago("Tarjeta de credito");
		MedioDePago tarjetaDebito = new MedioDePago("Tarjeta de debito");
		MedioDePago blockchain = new MedioDePago("Blockchain");
		MedioDePago mercadoPago = new MedioDePago("Mercado Pago");
		MedioDePagoDAO medioPagoDAO = DAOFactory.getMedioDePagoDAO();
		medioPagoDAO.persistir(tarjetaCredito);
		medioPagoDAO.persistir(tarjetaDebito);
		medioPagoDAO.persistir(blockchain);
		medioPagoDAO.persistir(mercadoPago);
		
	}
	
}
