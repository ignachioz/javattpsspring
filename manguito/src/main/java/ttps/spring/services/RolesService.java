package ttps.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.manguitoClases.Rol;
import ttps.spring.repositorios.RolesRepositorio;

@Service
@Transactional
public class RolesService {

	@Autowired
	private RolesRepositorio roleRepositorio;
	
	
	public Rol buscarRol(Long id) {
		return roleRepositorio.getById(Long.valueOf(2));
	}
	
}
