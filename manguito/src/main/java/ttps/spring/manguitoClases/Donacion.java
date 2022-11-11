package ttps.spring.manguitoClases;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "donacion")
public class Donacion {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "precio_manguito")
	private Double precioManguito;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "mensaje")
	private String mensaje;
	@Column(name = "contacto")
	private String contacto;
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@OneToOne //preguntar esto
	@JoinColumn(name = "medio_de_pago_id")
	private MedioDePago medioDePago;

	
	
	public Donacion(Double precioManguito, String nombre, String mensaje, String contacto, Integer cantidad, MedioDePago medioDePago) {
		this.fecha = new Date();
		this.precioManguito = precioManguito;
		this.nombre = nombre;
		this.mensaje = mensaje;
		this.contacto = contacto;
		this.cantidad = cantidad;
		this.medioDePago = medioDePago;
	}
	
	public Donacion() {}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getPrecioManguito() {
		return precioManguito;
	}
	public void setPrecioManguito(Double precioManguito) {
		this.precioManguito = precioManguito;
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
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public MedioDePago getMedioDePago() {
		return medioDePago;
	}
	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	
	
}
