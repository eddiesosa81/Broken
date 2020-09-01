package confia.entidades.basicos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "comisionsubagente_tbl")
public class ComisionSubagente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_comisionsubagente")
	@SequenceGenerator(sequenceName = "secuencia_cd_comisionsubagente", name = "secuencia_cd_comisionsubagente", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_comisionsubagente")
	private Integer cd_comisionsubagente;
	@Column(name="cd_subagenteramo")
	private Integer cd_subagenteramo;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_grupo_contratante")
	private Integer cd_grupo_contratante;
	@Column(name="cd_plan")
	private Integer cd_plan;
	@Column(name="porc_comision")
	private Double porc_comision;
	public Integer getCd_comisionsubagente() {
		return cd_comisionsubagente;
	}
	public void setCd_comisionsubagente(Integer cd_comisionsubagente) {
		this.cd_comisionsubagente = cd_comisionsubagente;
	}
	public Integer getCd_subagenteramo() {
		return cd_subagenteramo;
	}
	public void setCd_subagenteramo(Integer cd_subagenteramo) {
		this.cd_subagenteramo = cd_subagenteramo;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public Integer getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(Integer cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Double getPorc_comision() {
		return porc_comision;
	}
	public void setPorc_comision(Double porc_comision) {
		this.porc_comision = porc_comision;
	}
	
}
