package ttps.spring.manguitoClases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false, name = "email")
	private String email;
	@Column(nullable = false, name = "password")
	private String password;
	@Column(nullable = false, name = "fecha_nacimiento")
	private Date fechaNac;
	@Column(nullable = false, name = "dni")
	private Integer dni;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emprendimiento_id")
	@JsonManagedReference
	private Emprendimiento miEmprendimiento;
	
	@OneToMany()
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private List<Suscripcion> planesComprados;
	
	@ManyToMany()
	@JoinTable(name = "usuario_roles",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id")) 
	@JsonIgnore
	private List<Rol> roles;
	
	public Usuario(String email, String password, Date fechaNac, Integer dni, Emprendimiento emprendimiento, List<Rol> roles) {
		this.email = email;
		this.password = password;
		this.fechaNac = fechaNac;
		this.dni = dni;
		this.miEmprendimiento = emprendimiento;
		this.planesComprados = new ArrayList<>();
		this.roles = roles;
	}
	
	public Usuario(String email, String password, Date fechaNac, Integer dni, List<Rol> roles) {
		this.email = email;
		this.password = password;
		this.fechaNac = fechaNac;
		this.dni = dni;
		this.planesComprados = new ArrayList<>();
		this.roles = roles;
	}
	
	
	
	public Usuario() {}

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
	
	public Emprendimiento getMiEmprendimiento() {
		return miEmprendimiento;
	}

	public void setMiEmprendimiento(Emprendimiento miEmprendimiento) {
		this.miEmprendimiento = miEmprendimiento;
	}

	public List<Suscripcion> getPlanesComprados() {
		return planesComprados;
	}

	public void setPlanesComprados(List<Suscripcion> planesComprados) {
		this.planesComprados = planesComprados;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String toString() {
		return this.getEmail() + " "+this.getRoles();
	}
	
}
