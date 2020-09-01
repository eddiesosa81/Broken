package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "diagnostico_reclamo_tbl")
public class DiagnosticoReclamo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_diag_reclamo")
	@SequenceGenerator(sequenceName = "secuencia_cd_diag_reclamo", name = "secuencia_cd_diag_reclamo", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_diagnostico_reclamo")
	private Integer cd_diagnostico_reclamo;
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	@Column(name="cd_diagnostico")
	private Integer cd_diagnostico;
	@Column(name="valor_reclamo")
	private Double valor_reclamo;
	@Column(name="diagnistico")
	private String diagnistico;
	public Integer getCd_diagnostico_reclamo() {
		return cd_diagnostico_reclamo;
	}
	public void setCd_diagnostico_reclamo(Integer cd_diagnostico_reclamo) {
		this.cd_diagnostico_reclamo = cd_diagnostico_reclamo;
	}
	public Integer getCd_siniestro() {
		return cd_siniestro;
	}
	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
	}
	public Integer getCd_diagnostico() {
		return cd_diagnostico;
	}
	public void setCd_diagnostico(Integer cd_diagnostico) {
		this.cd_diagnostico = cd_diagnostico;
	}
	public Double getValor_reclamo() {
		return valor_reclamo;
	}
	public void setValor_reclamo(Double valor_reclamo) {
		this.valor_reclamo = valor_reclamo;
	}
	public String getDiagnistico() {
		return diagnistico;
	}
	public void setDiagnistico(String diagnistico) {
		this.diagnistico = diagnistico;
	}
	
	

}
