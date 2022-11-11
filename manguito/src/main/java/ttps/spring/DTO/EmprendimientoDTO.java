package ttps.spring.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ttps.spring.manguitoClases.Emprendimiento;

public class EmprendimientoDTO {

	@JsonProperty("banner")
	private String banner;
	@JsonProperty("descripcion")
	private String descripcion;
	@JsonProperty("facebook")
	private String facebook;
	@JsonProperty("twitter")
	private String twitter;
	@JsonProperty("instagram")
	private String instagram;
	@JsonProperty("youtube")
	private String youtube;
	@JsonProperty("categorias")
	private List<Integer> categorias;
	@JsonProperty("topDonadores")
	private Boolean topDonadores;
	@JsonProperty("manguitosRecibidos")
	private Boolean mangitosRecibidos;
	
	
	public EmprendimientoDTO(String banner, String descripcion, String youtube, String facebook, String twitter, String instagram, List<Integer> categorias, Boolean topDonadores, Boolean mangitosRecibidos) {
		this.banner = banner;
		this.descripcion = descripcion;
		this.youtube = youtube;
		this.facebook = facebook;
		this.twitter = twitter;
		this.instagram = instagram;
		this.categorias = categorias;
		this.topDonadores = topDonadores;
		this.mangitosRecibidos = mangitosRecibidos;
	}
	
	public EmprendimientoDTO() {}

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

	public List<Integer> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Integer> categorias) {
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
