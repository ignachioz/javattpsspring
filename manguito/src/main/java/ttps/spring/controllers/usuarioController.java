package ttps.spring.controllers;

import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ttps.spring.DTO.UsuarioEmprendimientoDTO;
import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.manguitoClases.Rol;
import ttps.spring.manguitoClases.Usuario;
import ttps.spring.services.EmprendimientoService;
import ttps.spring.services.RolesService;
import ttps.spring.services.UsuarioService;
import ttps.spring.validadores.ClaseValidadora;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class usuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmprendimientoService emprendimientoService;

	@Autowired
	private RolesService rolesService;
	
	private String KEY = "privada";
	
	private ClaseValidadora<Usuario> claseValidadora = new ClaseValidadora<Usuario>();
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public usuarioController() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@GetMapping
	public ResponseEntity<String> listAllUsers(@RequestHeader(value = "token",defaultValue = "") String token){
		String resp;
		try {
			List<Usuario> users = usuarioService.todosLosUsuarios();
			if (users.isEmpty()) {
				resp = claseValidadora.armarRespVacia();
				return new ResponseEntity<String>(resp,HttpStatus.NOT_ACCEPTABLE);
			}
			resp = claseValidadora.respOKObjectToJson(users);
			return new ResponseEntity<String>(resp, HttpStatus.OK);
		} catch (Exception e) {
			resp = claseValidadora.internalServerError();
			return new ResponseEntity<String>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crea un usuario y su emprendimiento
	@PostMapping
	public ResponseEntity<String> crearUsuario(@RequestBody UsuarioEmprendimientoDTO usuarioEmprendimientoDTO){
		String resp="";
		
		try {
			resp = claseValidadora.validarUsuarioEmprendimientoDTO(usuarioEmprendimientoDTO);
			if(resp!="") {	
				return new ResponseEntity<String>(resp,HttpStatus.NOT_FOUND);
			}else if(usuarioService.existeUsuarioPorEmail(usuarioEmprendimientoDTO.getEmail())) {
				resp = claseValidadora.armarRespCustom("error", "Ya existe el email ingresado");
			}else if(emprendimientoService.existeEmprendimientoPorNombre(usuarioEmprendimientoDTO.getNombre())) {
				resp = claseValidadora.armarRespCustom("error", "Ya existe un emprendimiento con el nombre");
			}
			if(resp!="") {	
				return new ResponseEntity<String>(resp,HttpStatus.CONFLICT);
			}

			// Asigno el rol de usuario.
			List<Rol> roles = new ArrayList<>();
			Rol rolUsuario = rolesService.buscarRol(Long.valueOf(2));
			roles.add(rolUsuario);
			
			String passwordEncriptada = bCryptPasswordEncoder.encode(usuarioEmprendimientoDTO.getPassword());
			
			Emprendimiento emprendimiento = new Emprendimiento(usuarioEmprendimientoDTO.getNombre(),
					usuarioEmprendimientoDTO.getBanner(), usuarioEmprendimientoDTO.getDescripcion(),
					usuarioEmprendimientoDTO.getCategorias(), usuarioEmprendimientoDTO.getPrecioManguito());
			Usuario usuario = new Usuario(usuarioEmprendimientoDTO.getEmail(), passwordEncriptada ,
					usuarioEmprendimientoDTO.getFechaNac(), usuarioEmprendimientoDTO.getDni(), roles);
			
			emprendimiento.setDuenio(usuario);
			usuario.setMiEmprendimiento(emprendimiento);
			Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
			return new ResponseEntity<String>(claseValidadora.respOKObjectToJson(usuarioCreado),HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>(claseValidadora.internalServerError(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Usuario usuarioIngreso){
		String password = usuarioIngreso.getPassword();
		String email = usuarioIngreso.getEmail();
		String resp="";
		try {
			resp = claseValidadora.validarLogin(password, email);
			if(resp!="") {
				return new ResponseEntity<String>(resp,HttpStatus.CONFLICT);
			}
			Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
			if (usuario == null) {
				return new ResponseEntity<String>(claseValidadora.armarRespCustom("error", "Problemas con el email/password"),HttpStatus.NOT_FOUND);
			}
			if (!this.bCryptPasswordEncoder.matches(password,usuario.getPassword())) {
				return new ResponseEntity<String>(claseValidadora.armarRespCustom("error", "Problemas con el email/password"),HttpStatus.NOT_ACCEPTABLE);
			}
			String jwt = JWT.create()
	                .withSubject("User Details")
	                .withClaim("idEmprendimiento", usuario.getMiEmprendimiento().getId())
	                .withClaim("email", usuario.getEmail())
	                .withIssuedAt(new Date())
	                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
	                .sign(Algorithm.HMAC256(this.KEY));
			return new ResponseEntity<String>(claseValidadora.armarRespJWT(jwt),HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>(claseValidadora.internalServerError(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
