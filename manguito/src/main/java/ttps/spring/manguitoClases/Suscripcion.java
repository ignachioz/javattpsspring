package ttps.spring.manguitoClases;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "suscripcion")
public class Suscripcion {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "contacto")
	private String contacto;
	@Column(name = "precio")
	private Double precio;
	@Column(name = "mensaje")
	private String mensaje;
	
	@OneToOne //ver esto si es manytoone
	@JoinColumn(name = "medio_de_pago_id")
	private MedioDePago medioDePago;
	
	@OneToOne //ver esto si es manytoone
	@JoinColumn(name = "plan_id")
	private Plan plan;	
	

	public Suscripcion(Date fecha, String contacto, Double precio, MedioDePago medioDePago, Plan plan, String mensaje, String nombre) {
		this.fecha = fecha;
		this.contacto = contacto;
		this.precio = precio;
		this.medioDePago = medioDePago;
		this.plan = plan;
		this.nombre = nombre;
		this.mensaje = mensaje;
	}
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		Id = id;
	}
	
	public Suscripcion() {}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
