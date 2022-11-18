package ttps.spring.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.manguitoClases.Usuario;
import ttps.spring.repositorios.UsuarioRepositorio;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public List<Usuario> todosLosUsuarios(){
		return usuarioRepositorio.findAll();
	}
	
	public Boolean existeUsuarioPorEmail(String email) {
		return usuarioRepositorio.existsByEmail(email);
	}
	
	public Usuario crearUsuario(Usuario usuario) {
		Usuario user = usuarioRepositorio.save(usuario);
		return user;
	}
	
	public Usuario buscarUsuarioPorEmail(String email) {
		return usuarioRepositorio.findByEmail(email);
	}
	
}
