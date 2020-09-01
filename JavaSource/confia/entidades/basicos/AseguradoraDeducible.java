package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aseguradoradeducible_tbl")
public class AseguradoraDeducible {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_asegded")
	@SequenceGenerator(sequenceName = "secuencia_cd_asegded", name = "secuencia_cd_asegded", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_asegded")
	private Integer cd_asegded;
	@Column(name="cd_deducible")
	private Integer cd_deducible;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="estado_asegded")
	private String estado_asegded;
	public Integer getCd_asegded() {
		return cd_asegded;
	}
	public void setCd_asegded(Integer cd_asegded) {
		this.cd_asegded = cd_asegded;
	}
	public Integer getCd_deducible() {
		return cd_deducible;
	}
	public void setCd_deducible(Integer cd_deducible) {
		this.cd_deducible = cd_deducible;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getEstado_asegded() {
		return estado_asegded;
	}
	public void setEstado_asegded(String estado_asegded) {
		this.estado_asegded = estado_asegded;
	}
	

}
