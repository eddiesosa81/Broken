package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cobertura_tbl")
public class Coberturas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_cobertura")
	@SequenceGenerator(sequenceName = "secuencia_cd_cobertura", name = "secuencia_cd_cobertura", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_cobertura")
	private Integer cd_cobertura;
	@Column(name="desc_cobertura")
	private String desc_cobertura;
	@Column(name="estado_cobertura")
	private String estado_cobertura;
	public Integer getCd_cobertura() {
		return cd_cobertura;
	}
	public void setCd_cobertura(Integer cd_cobertura) {
		this.cd_cobertura = cd_cobertura;
	}
	public String getDesc_cobertura() {
		return desc_cobertura;
	}
	public void setDesc_cobertura(String desc_cobertura) {
		this.desc_cobertura = desc_cobertura;
	}
	public String getEstado_cobertura() {
		return estado_cobertura;
	}
	public void setEstado_cobertura(String estado_cobertura) {
		this.estado_cobertura = estado_cobertura;
	}
	
	
}
