package confia.entidades.basicos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provincia_tbl")
public class Provincias {

	@Id
	@Column(name="cd_provincia")
	private Integer cd_provincia;
	@Column(name="CD_NACIONALIDAD")
	private String nacionalidad;
	@Column(name="DESC_PROVINCIA")
	private String desc_provincia;
	@Column(name="CODIGO_REFERENCIA_PROVINCIA")
	private String codigoReferenciaProvincia;
	@Column(name="ESTADO_PROVINCIA")
	private String estadoProvincia;
	public Integer getCd_provincia() {
		return cd_provincia;
	}
	public void setCd_provincia(Integer cd_provincia) {
		this.cd_provincia = cd_provincia;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getDesc_provincia() {
		return desc_provincia;
	}
	public void setDesc_provincia(String desc_provincia) {
		this.desc_provincia = desc_provincia;
	}
	public String getCodigoReferenciaProvincia() {
		return codigoReferenciaProvincia;
	}
	public void setCodigoReferenciaProvincia(String codigoReferenciaProvincia) {
		this.codigoReferenciaProvincia = codigoReferenciaProvincia;
	}
	public String getEstadoProvincia() {
		return estadoProvincia;
	}
	public void setEstadoProvincia(String estadoProvincia) {
		this.estadoProvincia = estadoProvincia;
	}
}
