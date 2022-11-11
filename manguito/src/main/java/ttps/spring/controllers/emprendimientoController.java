package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.DTO.EmprendimientoDTO;
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

@RestController
@RequestMapping(path="/emprendimiento", produces = MediaType.APPLICATION_JSON_VALUE)
public class emprendimientoController {
	
	
	private EmprendimientoRepositorio emprendimientoRepositorio;
	private DonacionRepositorio donacionRepositorio;
	private RedSocialRepositorio redSocialRepositorio;
	private CategoriaRepositorio categoriaRepositorio;
	private SuscripcionRepositorio suscripcionRepositorio;
	@Autowired
	public emprendimientoController(EmprendimientoRepositorio emprendimientoRepositorio,RedSocialRepositorio redSocialRepositorio,CategoriaRepositorio categoriaRepositorio,DonacionRepositorio donacionRepositorio,SuscripcionRepositorio suscripcionRepositorio) {
		this.emprendimientoRepositorio = emprendimientoRepositorio;
		this.donacionRepositorio = donacionRepositorio;
		this.redSocialRepositorio=redSocialRepositorio;
		this.categoriaRepositorio = categoriaRepositorio;
		this.suscripcionRepositorio = suscripcionRepositorio;
	}
	
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Emprendimiento> obtenerDatosEmprendimiento(@PathVariable("id") Long id ) {
		Emprendimiento emprendimiento = emprendimientoRepositorio.findById(id).orElse(null);
		if(emprendimiento==null){
			return new ResponseEntity<Emprendimiento>(HttpStatus.NOT_FOUND);
		 }
		Hibernate.initialize(emprendimiento.getCategorias());
	 	return new ResponseEntity<Emprendimiento>(emprendimiento, HttpStatus.OK);
	}
	
	//Hago un DTO, porque al mandar categoria [1,2], tenia error de parseo y no me dejaba recibirlo en entero tira error de una.
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Emprendimiento> actualizarEmprendimiento(@PathVariable("id") Long id,@RequestBody EmprendimientoDTO emprendimientoActualizado) {
		
		if(emprendimientoActualizado.getMangitosRecibidos() == null || emprendimientoActualizado.getTopDonadores() == null || emprendimientoActualizado.getBanner().isEmpty() || emprendimientoActualizado.getCategorias().size()==0 || emprendimientoActualizado.getDescripcion().isEmpty()) {
			System.out.println("Faltan campos que mandar");
			return new ResponseEntity<Emprendimiento>(HttpStatus.CONFLICT);
		}
		
		Emprendimiento emprendimiento = emprendimientoRepositorio.findById(id).orElse(null);
		if(emprendimiento==null){
			System.out.println("No existe el emprendimiento");
			return new ResponseEntity<Emprendimiento>(HttpStatus.NOT_FOUND);
		 }
		List<Categoria> categorias = new ArrayList<>();
		for(Integer categoria: emprendimientoActualizado.getCategorias()) {			
			Categoria cat = categoriaRepositorio.findById(new Long(categoria)).orElse(null);
			if(cat != null) {
				categorias.add(cat);
			}else {
				return new ResponseEntity<Emprendimiento>(HttpStatus.CONFLICT);
			}
		}
		String instagram = emprendimientoActualizado.getInstagram();
		String youtube = emprendimientoActualizado.getYoutube();
		String twitter = emprendimientoActualizado.getTwitter();
		String facebook = emprendimientoActualizado.getFacebook();

		List<RedSocial>redes = new ArrayList<RedSocial>();
		List<RedSocial> redesSinActualizar = emprendimiento.getRedes();
		for(RedSocial red:redesSinActualizar) {
			System.out.println(red.getId());
			switch (red.getTipoRedSocial().getNombre()) {
				case "Instagram": {
					System.out.println("HOLAAAAa");
					red.setDescripcion(instagram);
				}
				case "Facebook":{
					red.setDescripcion(facebook);
				}
				case "Twitter":{
					red.setDescripcion(twitter);
				}
				case "Youtube":{
					red.setDescripcion(youtube);
				}
				default:
				redes.add(red);
			}
		}		
		emprendimiento.setCategorias(categorias);
		emprendimiento.setBanner(emprendimientoActualizado.getBanner());
		emprendimiento.setDescripcion(emprendimientoActualizado.getDescripcion());
		emprendimiento.setTopDonadores(emprendimientoActualizado.getTopDonadores());
		emprendimiento.setMangitosRecibidos(emprendimientoActualizado.getMangitosRecibidos());
		emprendimiento.setRedes(redes);
		emprendimientoRepositorio.save(emprendimiento);
		/*Hibernate.initialize(emprendimiento.getRedes());
		Hibernate.initialize(emprendimiento.getCategorias());*/
	 	return new ResponseEntity<Emprendimiento>(emprendimiento, HttpStatus.OK);
	}
	
	@GetMapping("/donar/{id}")
	@Transactional
	public ResponseEntity<String> donarEmprendimiento(@PathVariable("id") Long id, @RequestBody Donacion donacion ) {
		Emprendimiento emprendimiento = emprendimientoRepositorio.findById(id).orElse(null);
		if(emprendimiento == null) {
			return new ResponseEntity<String>("{status:error}", HttpStatus.NOT_FOUND);
		}
		Hibernate.initialize(emprendimiento.getListaDonaciones());
		List<Donacion> donaciones = emprendimiento.getListaDonaciones();
		donaciones.add(donacion);
		emprendimiento.setListaDonaciones(donaciones);
		emprendimientoRepositorio.save(emprendimiento);
		return new ResponseEntity<String>("{status:ok}", HttpStatus.OK);
	}
	
	@GetMapping("/listar-donaciones/{id}")
	@Transactional
	public ResponseEntity<List<Donacion>> todasLasDonaciones(@PathVariable("id")Long id) {
		Emprendimiento emprendimiento = emprendimientoRepositorio.findById(id).orElse(null);
		Hibernate.initialize(emprendimiento.getListaDonaciones());
		List<Donacion> listaDonaciones = emprendimiento.getListaDonaciones();
		return new ResponseEntity<List<Donacion>>(listaDonaciones, HttpStatus.OK);
	}
	
}
