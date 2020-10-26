package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parroquia_tbl")
public class Parroquia {
	@Id
	@Column(name="CD_PARROQUIA")
	private Integer cd_parroquia;
	@Column(name="CD_CANTON")
	private Integer cd_canton;
	@Column(name="DESC_PARROQUIA")
	private String descParroquia;
	@Column(name="CODIGO_REFERENCIA_PARROQUIA")
	private String codigoReferenciaParroquia;
	@Column(name="ESTADO_PARROQUIA")
	private String estadoParroquia;
	public Integer getCd_parroquia() {
		return cd_parroquia;
	}
	public void setCd_parroquia(Integer cd_parroquia) {
		this.cd_parroquia = cd_parroquia;
	}
	public Integer getCd_canton() {
		return cd_canton;
	}
	public void setCd_canton(Integer cd_canton) {
		this.cd_canton = cd_canton;
	}
	public String getDescParroquia() {
		return descParroquia;
	}
	public void setDescParroquia(String descParroquia) {
		this.descParroquia = descParroquia;
	}
	public String getCodigoReferenciaParroquia() {
		return codigoReferenciaParroquia;
	}
	public void setCodigoReferenciaParroquia(String codigoReferenciaParroquia) {
		this.codigoReferenciaParroquia = codigoReferenciaParroquia;
	}
	public String getEstadoParroquia() {
		return estadoParroquia;
	}
	public void setEstadoParroquia(String estadoParroquia) {
		this.estadoParroquia = estadoParroquia;
	}
	
	
	
	
	

}
