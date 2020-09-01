package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ramoaseguradora_tbl")
public class RamoAseguradora {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_ramoaseg")
	@SequenceGenerator(sequenceName = "secuencia_cd_ramoaseg", name = "secuencia_cd_ramoaseg", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_ramoaseg")
	private Integer cd_ramoaseg;
	@Column(name="cd_ramo")
	private Integer cd_ramo;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="estado_asegcob")
	private String estado_asegcob;
	public Integer getCd_ramoaseg() {
		return cd_ramoaseg;
	}
	public void setCd_ramoaseg(Integer cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}
	public Integer getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(Integer cd_ramo) {
		this.cd_ramo = cd_ramo;
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
