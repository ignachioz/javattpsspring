package ttps.spring.manguitoClases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "plan")
public class Plan {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "precio")
	private Double precio;
	@Column(name = "beneficio")
	private String beneficio;
	
	@ManyToOne
	@JoinColumn(name = "emprendimiento_id")
	private Emprendimiento emprendimiento;
	
	
	
	public Plan(String nombre, Double precio, String beneficio, Emprendimiento emprendimiento) {
		this.nombre = nombre;
		this.precio = precio;
		this.beneficio = beneficio;
		this.emprendimiento = emprendimiento;
	}
	
	public Plan() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}

	public Emprendimiento getEmprendimiento() {
		return emprendimiento;
	}

	public void setEmprendimiento(Emprendimiento emprendimiento) {
		this.emprendimiento = emprendimiento;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
}
