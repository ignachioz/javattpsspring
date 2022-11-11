package ttps.spring.manguitoClases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "red_social")
public class RedSocial {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name = "tipo_red_social_id")
	private TipoRedSocial tipoRedSocial;
	
	public RedSocial(String descripcion, TipoRedSocial tipoRed) {
		this.descripcion = descripcion;
		this.tipoRedSocial = tipoRed;
	}
	
	public RedSocial() {}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoRedSocial getTipoRedSocial() {
		return tipoRedSocial;
	}

	public void setTipoRedSocial(TipoRedSocial tipoRedSocial) {
		this.tipoRedSocial = tipoRedSocial;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	
}
