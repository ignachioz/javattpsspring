package Inicializador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import genericosDAOS.CategoriaDAO;
import genericosDAOS.DAOFactory;
import genericosDAOS.EmprendimientoDAO;
import genericosDAOS.MedioDePagoDAO;
import genericosDAOS.RolDAO;
import genericosDAOS.UsuarioDAO;
import ttps.spring.manguitoClases.Categoria;
import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.manguitoClases.MedioDePago;
import ttps.spring.manguitoClases.Rol;
import ttps.spring.manguitoClases.Usuario;

public class InicializadorMain {

	public static void main(String[] args) {
		InicializadorBD.inicializador();

		//Obtengo los roles del sistema
		RolDAO rolDAO = DAOFactory.getRolDAO();
		List<Rol> rolesSistema = rolDAO.recuperarTodos("Id");
		List<Rol> rolesAsigna = new ArrayList<>();
		rolesAsigna.add(rolesSistema.get(1));
		//Creacion de usuario
		Usuario usuarioEmprendedor = new Usuario("martin@gmail.com","1234",new Date(),40502368,rolesAsigna);
		//obtengo las categorias del sistema.
		CategoriaDAO categoriaDAO = DAOFactory.getCategoriaDAO();
		List<Categoria> categoriasSistema = categoriaDAO.recuperarTodos("Id");
		//Creacion Emprendimiento del emprendedor
		List<Categoria> categoriasAsigna = new ArrayList<>();
		categoriasAsigna.add(categoriasSistema.get(0));
		categoriasAsigna.add(categoriasSistema.get(1));
		
		//Creacion del emprendimiento
		Emprendimiento emprendimiento1 = new Emprendimiento("Casa de arte","/imgs/emp1.png","Este emprendimiento es de arte",categoriasAsigna,580.0);
		
		//Agrego al usuario su emprendimiento
		usuarioEmprendedor.setMiEmprendimiento(emprendimiento1);
		
		//Agrego al emprendimiento el usuario dueño
		emprendimiento1.setDuenio(usuarioEmprendedor);
		
		//Persisto al usuario y al tener cascade.Type All lo persiste tambien al emprendimiento
		UsuarioDAO usuarioDAO1 = DAOFactory.getUsuarioDAO();
		usuarioDAO1.persistir(usuarioEmprendedor);
		
		//Obtener todos los usuarios ordenados por fecha de nacimiento
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		List<Usuario> usuariosOrdenados = usuarioDAO.recuperarTodos("fechaNac");
		for(Usuario usuario:usuariosOrdenados) {
			System.out.println(usuario.toString());
		}
		
		//ELIMINACION de un metodo de pago.
		MedioDePagoDAO medioDePagoDAO = DAOFactory.getMedioDePagoDAO();
		//borro el medio de pago 1: Tarjeta de credito
		long idMedioDePago = 48;
		boolean existe = medioDePagoDAO.existe(idMedioDePago);
		if(existe) {			
			medioDePagoDAO.borrar(idMedioDePago);
		}else {
			System.out.println("NO SE PUDO ELIMINAR YA QUE NO EXISTE");
		}
		
		//MODIFICACION de dni y password de usuario admin
		long idUsuarioBuscar = 1; //admin
		if(usuarioDAO.existe(idUsuarioBuscar)) {
			Usuario usuario = usuarioDAO.recuperar(idUsuarioBuscar);
			usuario.setDni(48969789);
			usuario.setPassword("pelota");
			usuarioDAO.actualizar(usuario);
		}else {
			System.out.println("NO SE PUDO ELIMINAR YA QUE NO EXISTE");
		}
		
		
		
	}

}
