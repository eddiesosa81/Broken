package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aseguradoracobertura_tbl")
public class AseguradoraCobertura {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_asegcob")
	@SequenceGenerator(sequenceName = "secuencia_cd_asegcob", name = "secuencia_cd_asegcob", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_asegcob")
	private Integer cd_asegcob;
	@Column(name="cd_cobertura")
	private Integer cd_cobertura;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="estado_asegcob")
	private String estado_asegcob;
	public Integer getCd_asegcob() {
		return cd_asegcob;
	}
	public void setCd_asegcob(Integer cd_asegcob) {
		this.cd_asegcob = cd_asegcob;
	}
	public Integer getCd_cobertura() {
		return cd_cobertura;
	}
	public void setCd_cobertura(Integer cd_cobertura) {
		this.cd_cobertura = cd_cobertura;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getEstado_asegcob() {
		return estado_asegcob;
	}
	public void setEstado_asegcob(String estado_asegcob) {
		this.estado_asegcob = estado_asegcob;
	}
	
	
}
