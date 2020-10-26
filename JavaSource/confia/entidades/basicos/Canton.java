package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "canton_tbl")
public class Canton {
	@Id
	@Column(name="CD_CANTON")
	private Integer cd_canton;
	@Column(name="CD_PROVINCIA")
	private Integer cd_provincia;
	@Column(name="DESC_CANTON")
	private String descCanton;
	@Column(name="CODIGO_REFERENCIA_CANTON")
	private String codigoReferenciaCanton;
	@Column(name="ESTADO_CANTON")
	private String estadoCanton;
	public Integer getCd_canton() {
		return cd_canton;
	}
	public void setCd_canton(Integer cd_canton) {
		this.cd_canton = cd_canton;
	}
	public Integer getCd_provincia() {
		return cd_provincia;
	}
	public void setCd_provincia(Integer cd_provincia) {
		this.cd_provincia = cd_provincia;
	}
	public String getDescCanton() {
		return descCanton;
	}
	public void setDescCanton(String descCanton) {
		this.descCanton = descCanton;
	}
	public String getCodigoReferenciaCanton() {
		return codigoReferenciaCanton;
	}
	public void setCodigoReferenciaCanton(String codigoReferenciaCanton) {
		this.codigoReferenciaCanton = codigoReferenciaCanton;
	}
	public String getEstadoCanton() {
		return estadoCanton;
	}
	public void setEstadoCanton(String estadoCanton) {
		this.estadoCanton = estadoCanton;
	}
}
