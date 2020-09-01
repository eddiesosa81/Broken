package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "clausula_tbl")
public class Clausulas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_clausula")
	@SequenceGenerator(sequenceName = "secuencia_cd_clausula", name = "secuencia_cd_clausula", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_clausula")
	private Integer cd_clausula;
	@Column(name="desc_clausula")
	private String desc_clausula;
	@Column(name="estado_clausula")
	private String estado_clausula;
	public Integer getCd_clausula() {
		return cd_clausula;
	}
	public void setCd_clausula(Integer cd_clausula) {
		this.cd_clausula = cd_clausula;
	}
	public String getDesc_clausula() {
		return desc_clausula;
	}
	public void setDesc_clausula(String desc_clausula) {
		this.desc_clausula = desc_clausula;
	}
	public String getEstado_clausula() {
		return estado_clausula;
	}
	public void setEstado_clausula(String estado_clausula) {
		this.estado_clausula = estado_clausula;
	}

}
