package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NACIONALIDAD_tbl")
public class Nacionalidad {
	@Id
	@Column(name="cd_nacionalidad")
	private Integer cd_nacionalidad;
	@Column(name="paiid")
	private Integer paiid;
	@Column(name="nacdescripcion")
	private String nacdescripcion;
	@Column(name="nacponderacion")
	private Integer nacponderacion;
	@Column(name="naccodigoreferencia")
	private String naccodigoreferencia;
	public Integer getCd_nacionalidad() {
		return cd_nacionalidad;
	}
	public void setCd_nacionalidad(Integer cd_nacionalidad) {
		this.cd_nacionalidad = cd_nacionalidad;
	}
	public Integer getPaiid() {
		return paiid;
	}
	public void setPaiid(Integer paiid) {
		this.paiid = paiid;
	}
	public String getNacdescripcion() {
		return nacdescripcion;
	}
	public void setNacdescripcion(String nacdescripcion) {
		this.nacdescripcion = nacdescripcion;
	}
	public Integer getNacponderacion() {
		return nacponderacion;
	}
	public void setNacponderacion(Integer nacponderacion) {
		this.nacponderacion = nacponderacion;
	}
	public String getNaccodigoreferencia() {
		return naccodigoreferencia;
	}
	public void setNaccodigoreferencia(String naccodigoreferencia) {
		this.naccodigoreferencia = naccodigoreferencia;
	}
	
	
	
}
