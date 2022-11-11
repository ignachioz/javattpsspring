package ttps.spring.manguitoClases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medio_de_pago")
public class MedioDePago {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "nombre")
	private String nombre;
	
	public MedioDePago(String nombre) {
		this.nombre = nombre;
	}
	
	public MedioDePago(Long id) {
		this.Id=id;
	}
	
	public MedioDePago() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	
	
	
	
	
}
