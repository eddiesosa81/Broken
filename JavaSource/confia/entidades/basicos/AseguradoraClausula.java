package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aseguradoraclausula_tbl")
public class AseguradoraClausula {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_asegclau")
	@SequenceGenerator(sequenceName = "secuencia_cd_asegclau", name = "secuencia_cd_asegclau", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_asegclau")
	private Integer cd_asegclau;
	@Column(name="cd_clausula")
	private Integer cd_clausula;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="estado_asegclau")
	private String estado_asegclau;
	public Integer getCd_asegclau() {
		return cd_asegclau;
	}
	public void setCd_asegclau(Integer cd_asegclau) {
		this.cd_asegclau = cd_asegclau;
	}
	public Integer getCd_clausula() {
		return cd_clausula;
	}
	public void setCd_clausula(Integer cd_clausula) {
		this.cd_clausula = cd_clausula;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getEstado_asegclau() {
		return estado_asegclau;
	}
	public void setEstado_asegclau(String estado_asegclau) {
		this.estado_asegclau = estado_asegclau;
	}

	
}
