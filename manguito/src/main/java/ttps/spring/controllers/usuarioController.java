package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import ttps.spring.DTO.UsuarioEmprendimientoDTO;
import ttps.spring.manguitoClases.Categoria;
import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.manguitoClases.RedSocial;
import ttps.spring.manguitoClases.Rol;
import ttps.spring.manguitoClases.TipoRedSocial;
import ttps.spring.manguitoClases.Usuario;
import ttps.spring.repositorios.CategoriaRepositorio;
import ttps.spring.repositorios.EmprendimientoRepositorio;
import ttps.spring.repositorios.RolesRepositorio;
import ttps.spring.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping(path="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class usuarioController {

	private UsuarioRepositorio usuarioRepositorio;
	private EmprendimientoRepositorio emprendimientoRepositorio;
	private RolesRepositorio rolesRepositorio;
	private CategoriaRepositorio categoriaRepositorio;
	private TipoRedSocialRepositorio tRedSocialRepositorio;
	
	@Autowired
	public usuarioController(UsuarioRepositorio usuarioRepositorio, EmprendimientoRepositorio emprendimientoRepositorio,  RolesRepositorio rolesRepositorio, CategoriaRepositorio categoriaRepositorio, TipoRedSocialRepositorio tRedSocialRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
		this.emprendimientoRepositorio = emprendimientoRepositorio;
		this.rolesRepositorio = rolesRepositorio;
		this.categoriaRepositorio = categoriaRepositorio;
		this.tRedSocialRepositorio = tRedSocialRepositorio;
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listAllUsers() {
		List<Usuario> users = usuarioRepositorio.findAll();
		if(users.isEmpty()){
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		 }
	 	return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	 }
	
	  // Crea un usuario y su emprendimiento
	  @PostMapping
	  public ResponseEntity<UsuarioEmprendimientoDTO> crearUsuario(@RequestBody UsuarioEmprendimientoDTO usuarioEmprendimientoDTO) {
		  if (usuarioEmprendimientoDTO.getEmail().isEmpty() || usuarioEmprendimientoDTO.getPassword().isEmpty() || String.valueOf(usuarioEmprendimientoDTO.getDni()).isEmpty() || usuarioEmprendimientoDTO.getBanner().isEmpty() || usuarioEmprendimientoDTO.getDescripcion().isEmpty() || usuarioEmprendimientoDTO.getCategorias().size()==0 || String.valueOf(usuarioEmprendimientoDTO.getPrecioManguito()).isEmpty()) {
			  System.out.println("Faltan datos para crear al usuario ");
			  return new ResponseEntity<UsuarioEmprendimientoDTO>(HttpStatus.NOT_FOUND); 
		  }
		  		  
		  if ( usuarioRepositorio.existsByEmail(usuarioEmprendimientoDTO.getEmail())   ) {
			  System.out.println("Ya existe un usuario con email " + usuarioEmprendimientoDTO.getEmail());
			  return new ResponseEntity<UsuarioEmprendimientoDTO>(HttpStatus.CONFLICT); 
		  }
		  
		  if (emprendimientoRepositorio.existsByNombre(usuarioEmprendimientoDTO.getNombre())) {
			  System.out.println("Ya existe un emprendimiento con ese nombre " + usuarioEmprendimientoDTO.getNombre());
			  return new ResponseEntity<UsuarioEmprendimientoDTO>(HttpStatus.CONFLICT);
		  }
		  
		  List<Rol> roles = new ArrayList<>();
		  Rol rolUsuario = rolesRepositorio.getById(new Long(2));
		  roles.add(rolUsuario);
		  
		  //categorias [1,2,3,4]
		  List<Categoria>categorias = new ArrayList<>();
		  Categoria cat = null;
		  for(Integer idCategoria:usuarioEmprendimientoDTO.getCategorias()) {
			  cat = categoriaRepositorio.findById(new Long(idCategoria)).orElse(null);
			  if(cat != null) {				  
				  categorias.add(cat);
			  }else {
				  System.out.println("Categoria inexistente");
				  return new ResponseEntity<UsuarioEmprendimientoDTO>(HttpStatus.CONFLICT);
			  }
		  }
		  Emprendimiento emprendimiento = new Emprendimiento(usuarioEmprendimientoDTO.getNombre(),usuarioEmprendimientoDTO.getBanner(),usuarioEmprendimientoDTO.getDescripcion(),categorias,usuarioEmprendimientoDTO.getPrecioManguito());
		  Usuario usuario = new Usuario(usuarioEmprendimientoDTO.getEmail(),usuarioEmprendimientoDTO.getPassword(),usuarioEmprendimientoDTO.getFechaNac(),usuarioEmprendimientoDTO.getDni(),roles);
		  
		  List<RedSocial> redes = new ArrayList<>();
		  List<TipoRedSocial> tipoRedes = tRedSocialRepositorio.findAll();
		  for(TipoRedSocial tRed: tipoRedes) {
			  redes.add(new RedSocial("",tRed));
		  }
		  emprendimiento.setRedes(redes);
		  emprendimiento.setDuenio(usuario);
		  usuario.setMiEmprendimiento(emprendimiento);
		  usuarioRepositorio.save(usuario);
		  return new ResponseEntity<UsuarioEmprendimientoDTO>(usuarioEmprendimientoDTO, HttpStatus.CREATED);
	  }
	  
		
		@PostMapping("/login")
		public ResponseEntity<Usuario> login(@RequestBody Usuario usuarioIngreso) {
			String password = usuarioIngreso.getPassword();
			String email = usuarioIngreso.getEmail();
			Usuario usuario = usuarioRepositorio.findByEmail(email);
			if(usuario == null) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
			if(!usuario.getPassword().equals(password)) {
				System.out.println("HOLA "+usuario.getPassword()+" "+password);
				return new ResponseEntity<Usuario>(HttpStatus.NOT_ACCEPTABLE);
			}
			Hibernate.initialize(usuario.getRoles());
			return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
		}
	

}
