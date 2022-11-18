package ttps.spring.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ttps.spring.manguitoClases.Categoria;
import ttps.spring.manguitoClases.Emprendimiento;
import ttps.spring.manguitoClases.MedioDePago;
import ttps.spring.manguitoClases.RedSocial;

public class EmprendimientoDTO {

	@JsonProperty("banner")
	private String banner;
	@JsonProperty("descripcion")
	private String descripcion;
	@JsonProperty("facebook") //List<RedSocial>
	private String facebook;
	@JsonProperty("twitter")
	private String twitter;
	@JsonProperty("instagram")
	private String instagram;
	@JsonProperty("youtube")
	private String youtube;
	@JsonProperty("redes")
	private List<RedSocial> redes;
	@JsonProperty("categorias")
	private List<Categoria> categorias;
	@JsonProperty("topDonadores")
	private Boolean topDonadores;
	@JsonProperty("manguitosRecibidos")
	private Boolean mangitosRecibidos;
	@JsonProperty("pagosAceptados")
	private List<MedioDePago> pagosAceptados;
	
	
	public EmprendimientoDTO(String banner, String descripcion, String youtube, String facebook, String twitter, String instagram, List<Categoria> categorias, Boolean topDonadores, Boolean mangitosRecibidos, List<MedioDePago> pagosAceptados, List<RedSocial> redes) {
		this.banner = banner;
		this.descripcion = descripcion;
		/*this.youtube = youtube;
		this.facebook = facebook;
		this.twitter = twitter;
		this.instagram = instagram;*/
		this.redes=redes;
		this.categorias = categorias;
		this.topDonadores = topDonadores;
		this.mangitosRecibidos = mangitosRecibidos;
		this.pagosAceptados = pagosAceptados;
	}
	
	public List<MedioDePago> getPagosAceptados() {
		return pagosAceptados;
	}

	public void setPagosAceptados(List<MedioDePago> pagosAceptados) {
		this.pagosAceptados = pagosAceptados;
	}

	public EmprendimientoDTO() {}

	public String getBanner() {
		return banner;
	}
	
	public List<RedSocial> getRedes() {
		return redes;
	}

	public void setRedes(List<RedSocial> redes) {
		this.redes = redes;
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

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
	
	
	
	
}
