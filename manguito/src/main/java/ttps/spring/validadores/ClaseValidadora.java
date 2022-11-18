package ttps.spring.validadores;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ttps.spring.DTO.EmprendimientoDTO;
import ttps.spring.DTO.UsuarioEmprendimientoDTO;
import ttps.spring.manguitoClases.Donacion;
import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.services.EmprendimientoService;
import ttps.spring.services.UsuarioService;

public class ClaseValidadora<T> {

	private ObjectMapper obj;

	public ClaseValidadora() {
		this.obj = new ObjectMapper();
	}

	public String respOKObjectToJson(T objeto) throws JsonProcessingException {
			
		return obj.writeValueAsString(objeto);
		
	}

	public String validarUsuarioEmprendimientoDTO(UsuarioEmprendimientoDTO u) throws JsonProcessingException {
		String cadenaJson;
		JSONObject cadena = null;
		if (GenericValidador.validarVacios(u.getBanner()) || GenericValidador.validarVacios(u.getDescripcion())
				|| GenericValidador.validarVacios(u.getEmail()) || GenericValidador.validarVacios(u.getNombre())
				|| GenericValidador.validarVacios(u.getCategorias()) || GenericValidador.validarVacios(u.getPassword())
				|| GenericValidador.validarVacios(String.valueOf(u.getDni()))
				|| GenericValidador.validarVacios(u.getPrecioManguito())) {
			return this.armarRespCustom("error", "No puede haber campos vacios");
		}

		return "";

	}

	public String respOKObjectToJson(List<T> objetos) throws JsonProcessingException {
		return obj.writeValueAsString(objetos);
	}
	
	public String respOKObjectToJsonDonaciones(List<Donacion> donaciones) throws JsonProcessingException {
		return obj.writeValueAsString(donaciones);
	}

	public String armarRespVacia() throws JsonProcessingException {
		String cadenaJson = this.armarRespCustom("ok", "Sin usuarios");
		JSONObject cadena = new JSONObject(cadenaJson);
		return cadena.toString();
	}

	public String internalServerError() {
		return this.armarRespCustom("error", "Problemas internos del servidor");
	}

	public String armarRespCustom(String status, String msg) {
		String cadenaJson = "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\"}";
		JSONObject cadena = new JSONObject(cadenaJson);
		return cadena.toString();
	}
	
	public String validarLogin(String password,String email) {
		if(GenericValidador.validarVacios(password) || GenericValidador.validarVacios(email)) {
			return this.armarRespCustom("error", "El campo email y/o password no pueden ser vacios");
		}
		return "";
	}
	
	public String validarDonacion(Donacion d) {
		if(GenericValidador.validarVacios(String.valueOf(d.getCantidad())) || GenericValidador.validarVacios(d.getMensaje())){
			return this.armarRespCustom("error", "El campo cantidad y/o mensaje no pueden ser vacios");
		}
		if(d.getCantidad()<=0) {
			return this.armarRespCustom("error", "No se pueden donar menos de 1 manguitos");
		}
		return "";
	}
	
	public String validarEmprendimiento(EmprendimientoDTO emprendimientoActualizado) {
		if(emprendimientoActualizado.getMangitosRecibidos() == null || emprendimientoActualizado.getTopDonadores() == null || emprendimientoActualizado.getBanner().isEmpty() || emprendimientoActualizado.getCategorias().size()==0 || emprendimientoActualizado.getDescripcion().isEmpty()) {
			return this.armarRespCustom("error", "No puede haber campos vacios");
		}
		return "";
	}
	public String armarRespJWT(String jwt) {
		String cadenaJson = "{\"status\":\"OK\",\"jwt\":\"" + jwt + "\"}";
		JSONObject cadena = new JSONObject(cadenaJson);
		return cadena.toString();
	}
	

}
