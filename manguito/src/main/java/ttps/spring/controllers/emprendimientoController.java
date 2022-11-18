package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ttps.spring.DTO.EmprendimientoDTO;
import ttps.spring.jwtConfiguracion.JWTDecode;
import ttps.spring.manguitoClases.Categoria;
import ttps.spring.manguitoClases.Donacion;
import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.manguitoClases.RedSocial;
import ttps.spring.manguitoClases.Suscripcion;
import ttps.spring.manguitoClases.TipoRedSocial;
import ttps.spring.manguitoClases.Usuario;
import ttps.spring.repositorios.CategoriaRepositorio;
import ttps.spring.repositorios.DonacionRepositorio;
import ttps.spring.repositorios.EmprendimientoRepositorio;
import ttps.spring.repositorios.RedSocialRepositorio;
import ttps.spring.repositorios.SuscripcionRepositorio;
import ttps.spring.services.EmprendimientoService;
import ttps.spring.services.TipoRedSocialService;
import ttps.spring.validadores.ClaseValidadora;

@RestController
@RequestMapping(path="/emprendimiento", produces = MediaType.APPLICATION_JSON_VALUE)
public class emprendimientoController {
	
	@Autowired
	private EmprendimientoService emprendimientoService;
	
	@Autowired
	private TipoRedSocialService tipoRedSocialService;
	
	private ClaseValidadora<Emprendimiento> claseValidadora = new ClaseValidadora<Emprendimiento>();
	
	
	@GetMapping
	@Transactional
	public ResponseEntity<String> obtenerDatosEmprendimiento(@RequestHeader(value = "token",defaultValue = "") String token ) {
		try {
			DecodedJWT jwt = JWTDecode.decodeJWT(token); //fijarse si expiro
			Emprendimiento emprendimiento = emprendimientoService.buscarEmprendimientoPorId(jwt.getClaim("idEmprendimiento").asLong());
			if(emprendimiento==null){
				return new ResponseEntity<String>(claseValidadora.armarRespCustom("error", "Emprendimiento inexistente"),HttpStatus.NOT_FOUND);
			 }
		 	return new ResponseEntity<String>(claseValidadora.respOKObjectToJson(emprendimiento), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(claseValidadora.internalServerError(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<String> actualizarEmprendimiento(@RequestHeader(value = "token",defaultValue = "") String token, @RequestBody EmprendimientoDTO emprendimientoActualizado) {
		String resp="";
		try {
			DecodedJWT jwt = JWTDecode.decodeJWT(token);
			Long id = jwt.getClaim("idEmprendimiento").asLong();
			resp = claseValidadora.validarEmprendimiento(emprendimientoActualizado);
			if(resp!="") {
				return new ResponseEntity<String>(resp,HttpStatus.CONFLICT);
			}
			Emprendimiento emprendimiento = emprendimientoService.buscarEmprendimientoPorId(id);
			if(emprendimiento==null){
				return new ResponseEntity<String>(claseValidadora.armarRespCustom("error", "Emprendimiento inexistente"),HttpStatus.NOT_FOUND);
			 }

			for(RedSocial red: emprendimientoActualizado.getRedes()) {
				RedSocial redFiltrada = emprendimiento.getRedes().stream().filter(r->r.getTipoRedSocial().getId()==red.getId()).findFirst().orElse(null);
				if(redFiltrada != null) {
					redFiltrada.setDescripcion(red.getDescripcion());
				}else {
					TipoRedSocial tipoRed = tipoRedSocialService.obtenerTipoRedPorId(red.getId());
					RedSocial redSocial = new RedSocial(red.getDescripcion(),tipoRed);
					List<RedSocial> redes = emprendimiento.getRedes();
					redes.add(redSocial);
					emprendimiento.setRedes(redes);
				}
			}
			
			emprendimiento.setCategorias(emprendimientoActualizado.getCategorias());
			emprendimiento.setBanner(emprendimientoActualizado.getBanner());
			emprendimiento.setDescripcion(emprendimientoActualizado.getDescripcion());
			emprendimiento.setTopDonadores(emprendimientoActualizado.getTopDonadores());
			emprendimiento.setMangitosRecibidos(emprendimientoActualizado.getMangitosRecibidos());
			emprendimientoService.actualizarEmprendimiento(emprendimiento);
			return new ResponseEntity<String>(claseValidadora.respOKObjectToJson(emprendimiento), HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e);
			return new ResponseEntity<String>(claseValidadora.internalServerError(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/donar/{id}")
	@Transactional
	public ResponseEntity<String> donarEmprendimiento(@PathVariable("id") Long id, @RequestBody Donacion donacion ) {
		String resp="";
		try {
			resp = claseValidadora.validarDonacion(donacion);
			if(resp!="") {
				return new ResponseEntity<String>(resp,HttpStatus.CONFLICT);
			}
			Emprendimiento emprendimiento = emprendimientoService.buscarEmprendimientoPorId(id);
			if(emprendimiento == null) {
				return new ResponseEntity<String>(claseValidadora.armarRespCustom("error", "Emprendimiento inexistente"),HttpStatus.NOT_FOUND);
			}
			Hibernate.initialize(emprendimiento.getListaDonaciones());
			List<Donacion> donaciones = emprendimiento.getListaDonaciones();
			donacion.setFecha(new Date());
			donaciones.add(donacion);
			donacion.setPrecioManguito(emprendimiento.getPrecioManguito()); //seteo el precio del manguito del momento.
			emprendimiento.setListaDonaciones(donaciones);
			emprendimientoService.actualizarEmprendimiento(emprendimiento);
			return new ResponseEntity<String>(claseValidadora.armarRespCustom("OK", "Donacion realizada"), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(claseValidadora.internalServerError(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listar-donaciones")
	@Transactional
	public ResponseEntity<String> todasLasDonaciones(@RequestHeader(value = "token",defaultValue = "") String token) {
		try {			
			DecodedJWT jwt = JWTDecode.decodeJWT(token);
			Long id = jwt.getClaim("idEmprendimiento").asLong();
			Emprendimiento emprendimiento = emprendimientoService.buscarEmprendimientoPorId(id);
			if(emprendimiento == null) {
				return new ResponseEntity<String>(claseValidadora.armarRespCustom("error", "Emprendimiento inexistente"),HttpStatus.NOT_FOUND);
			}
			Hibernate.initialize(emprendimiento.getListaDonaciones());
			
			return new ResponseEntity<String>(claseValidadora.respOKObjectToJsonDonaciones(emprendimiento.getListaDonaciones()), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(claseValidadora.internalServerError(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}
