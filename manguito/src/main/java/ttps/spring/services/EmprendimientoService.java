package ttps.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.repositorios.EmprendimientoRepositorio;

@Service
@Transactional
public class EmprendimientoService {

	@Autowired
	private EmprendimientoRepositorio emprendimientoRepositorio;
	
	
	public Boolean existeEmprendimientoPorNombre(String nombre) {
		return emprendimientoRepositorio.existsByNombre(nombre);
	}
	
	public Emprendimiento buscarEmprendimientoPorId(Long id) {
		return emprendimientoRepositorio.findById(id).orElse(null);
	}
	
	public Emprendimiento actualizarEmprendimiento(Emprendimiento emprendimiento) {
		Emprendimiento e = emprendimientoRepositorio.save(emprendimiento);
		return e;
	}
	
}
