package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "deducible_tbl")
public class Deducibles {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_deducible")
	@SequenceGenerator(sequenceName = "secuencia_cd_deducible", name = "secuencia_cd_deducible", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_deducible")
	private Integer cd_deducible;
	@Column(name="desc_deducible")
	private String desc_deducible;
	@Column(name="aplica_deducible")
	private String aplica_deducible;
	@Column(name="estado_deducible")
	private String estado_deducible;
	
	public Integer getCd_deducible() {
		return cd_deducible;
	}
	public void setCd_deducible(Integer cd_deducible) {
		this.cd_deducible = cd_deducible;
	}
	public String getDesc_deducible() {
		return desc_deducible;
	}
	public void setDesc_deducible(String desc_deducible) {
		this.desc_deducible = desc_deducible;
	}
	public String getAplica_deducible() {
		return aplica_deducible;
	}
	public void setAplica_deducible(String aplica_deducible) {
		this.aplica_deducible = aplica_deducible;
	}
	public String getEstado_deducible() {
		return estado_deducible;
	}
	public void setEstado_deducible(String estado_deducible) {
		this.estado_deducible = estado_deducible;
	}
	
	

}
