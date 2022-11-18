package ttps.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.manguitoClases.TipoRedSocial;
import ttps.spring.repositorios.TipoRedSocialRepositorio;

@Service
@Transactional
public class TipoRedSocialService {

	@Autowired
	private TipoRedSocialRepositorio tipoRedSocialRepositorio;
	
	public TipoRedSocial obtenerTipoRedPorId(Long id) {
		return tipoRedSocialRepositorio.getById(id);
	}
	
}
