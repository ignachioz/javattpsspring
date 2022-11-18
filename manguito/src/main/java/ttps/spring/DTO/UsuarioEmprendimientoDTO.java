package ttps.spring.DTO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ttps.spring.manguitoClases.Categoria;


public class UsuarioEmprendimientoDTO {
	
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("fechaNac")
	private Date fechaNac;
	@JsonProperty("dni")
	private Integer dni;
	
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("banner")
	private String banner;
	@JsonProperty("descripcion")
	private String descripcion;
	@JsonProperty("categorias")
	private List<Categoria> categorias;
	@JsonProperty("precioManguito")
	private Double precioManguito;
	
	
	public UsuarioEmprendimientoDTO(String email,String password,Date fechaNac,Integer dni,String nombre,String banner,String descripcion,List<Categoria>categorias,Double precioManguito) {
		this.categorias = categorias;
		this.email = email;
		this.password = password;
		this.fechaNac = new Date();
		this.dni = dni;
		this.nombre = nombre;
		this.banner = banner;
		this.descripcion = descripcion;
		this.precioManguito = precioManguito;
	}

	public UsuarioEmprendimientoDTO() {}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Double getPrecioManguito() {
		return precioManguito;
	}

	public void setPrecioManguito(Double precioManguito) {
		this.precioManguito = precioManguito;
	}

	

	
	
}
