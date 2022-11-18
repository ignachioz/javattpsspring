package ttps.spring.manguitoClases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ttps.spring.manguitoClases.Post;



@Entity
@Table(name = "emprendimiento")
@Proxy(lazy = false)
public class Emprendimiento{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@OneToOne(mappedBy = "miEmprendimiento")
	@JsonBackReference
	private Usuario duenio;
	
	@OneToMany(mappedBy = "emprendimiento")
	@JsonIgnore
	private List<Plan> planes;
	
	@OneToMany
	@JoinColumn(name = "emprendimiento_id")
	@JsonIgnore
	private List<Post> posteos;
	
	@ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	@JoinTable(name = "emprendimiento_categorias",
    joinColumns = @JoinColumn(name = "emprendimiento_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id")) 
	private List<Categoria> categorias;
	
	@ManyToMany
	@JoinTable(name = "emprendimiento_medio_de_pago",
    joinColumns = @JoinColumn(name = "emprendimiento_id"),
    inverseJoinColumns = @JoinColumn(name = "medio_de_pago_id"))
	@JsonIgnore
	private List<MedioDePago> pagosAceptados;
	
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "banner")
	private String banner;
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emprendimiento_id")
	@JsonIgnore
	private List<RedSocial> redes;
	
	@Column(name = "top_donadores")
	private Boolean topDonadores;
	@Column(name = "manguitos_recibidos")
	private Boolean mangitosRecibidos;
	@Column(name = "precio_manguito")
	private Double precioManguito;
	@Column(name = "esta_activo")
	private Boolean isActivo;
	@Column(name = "mensaje_suspension")
	private String mensajeSuspension;
	@Column(name = "cantidad_visitas")
	private Integer cantidadVisitas;
	@Column(name = "cantidad_manguitos_recibidos")
	private Integer cantidadManguitosRecibidos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emprendimiento_id")
	@JsonIgnore
	private List<Donacion> listaDonaciones;
	
	public Emprendimiento(Usuario duenio,String nombre, String banner, String descripcion,List<Categoria> categorias, Double precioManguito) {
		this.duenio = duenio;
		this.nombre = nombre;
		this.banner = banner;
		this.descripcion = descripcion;
		this.planes = new ArrayList<>();
		this.posteos = new ArrayList<>();
		this.categorias = categorias;
		this.pagosAceptados = new ArrayList<>(); //arrancaria con algun medio de pago por default, agregar Mercado Pago
		this.redes = new ArrayList<>();
		this.cantidadVisitas = 0;
		this.cantidadManguitosRecibidos = 0;
		this.listaDonaciones = new ArrayList<>();
		this.topDonadores = true;
		this.mangitosRecibidos = true;
		this.isActivo = true;
		this.mensajeSuspension = "";
		this.precioManguito = precioManguito;
	}
		
	public Emprendimiento(String nombre, String banner, String descripcion,List<Categoria> categorias, Double precioManguito) {
		this.nombre = nombre;
		this.banner = banner;
		this.descripcion = descripcion;
		this.planes = new ArrayList<>();
		this.posteos = new ArrayList<>();
		this.categorias = categorias;
		this.pagosAceptados = new ArrayList<>(); //arrancaria con algun medio de pago por default, agregar Mercado Pago
		this.redes = new ArrayList<>();
		this.cantidadVisitas = 0;
		this.cantidadManguitosRecibidos = 0;
		this.listaDonaciones = new ArrayList<>();
		this.topDonadores = true;
		this.mangitosRecibidos = true;
		this.isActivo = true;
		this.mensajeSuspension = "";
		this.precioManguito = precioManguito;
	}
	
	public Emprendimiento() {}
	
	public Emprendimiento(Long id) {
		super();
		this.Id=id;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}


	public Usuario getDuenio() {
		return duenio;
	}

	public void setDuenio(Usuario duenio) {
		this.duenio = duenio;
	}

	public List<Plan> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Plan> planes) {
		this.planes = planes;
	}

	public List<Post> getPosteos() {
		return posteos;
	}

	public void setPosteos(List<Post> posteos) {
		this.posteos = posteos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<MedioDePago> getPagosAceptados() {
		return pagosAceptados;
	}

	public void setPagosAceptados(List<MedioDePago> pagosAceptados) {
		this.pagosAceptados = pagosAceptados;
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

	public List<RedSocial> getRedes() {
		return redes;
	}

	public void setRedes(List<RedSocial> redes) {
		this.redes = redes;
	}

	public Boolean getTopDonadores() {
		return topDonadores;
	}

	public void setTopDonadores(Boolean topDonadores) {
		this.topDonadores = topDonadores;
	}

	public Boolean getMangitosRecibidos() {
		return mangitosRecibidos;
	}

	public void setMangitosRecibidos(Boolean mangitosRecibidos) {
		this.mangitosRecibidos = mangitosRecibidos;
	}

	public Double getPrecioManguito() {
		return precioManguito;
	}

	public void setPrecioManguito(Double precioManguito) {
		this.precioManguito = precioManguito;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public String getMensajeSuspension() {
		return mensajeSuspension;
	}

	public void setMensajeSuspension(String mensajeSuspension) {
		this.mensajeSuspension = mensajeSuspension;
	}

	public Integer getCantidadVisitas() {
		return cantidadVisitas;
	}

	public void setCantidadVisitas(Integer cantidadVisitas) {
		this.cantidadVisitas = cantidadVisitas;
	}

	public Integer getCantidadManguitosRecibidos() {
		return cantidadManguitosRecibidos;
	}

	public void setCantidadManguitosRecibidos(Integer cantidadManguitosRecibidos) {
		this.cantidadManguitosRecibidos = cantidadManguitosRecibidos;
	}

	public List<Donacion> getListaDonaciones() {
		return listaDonaciones;
	}

	public void setListaDonaciones(List<Donacion> listaDonaciones) {
		this.listaDonaciones = listaDonaciones;
	}
	
	public String toString() {
		return "User [id=" + this.getId() + ", email=" + this.getNombre()+ "]";
	}
		
}
