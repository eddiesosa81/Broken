package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "deducibles_emitidos_tbl")
public class DeduciblesEmitidas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_deducible_emitido")
	@SequenceGenerator(sequenceName = "secuencia_cd_deducible_emitido", name = "secuencia_cd_deducible_emitido", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_deducible_emitido") 
	private Integer cd_deducible_emitido;
	@Column(name="cd_deducible") 
	private Integer cd_deducible;
	@Column(name="cd_plan") 
	private Integer cd_plan;
	@Column(name="cd_ramo_cotizacion") 
	private Integer cd_ramo_cotizacion;
	@Column(name="desc_deducible") 
	private String desc_deducible;
	@Column(name="porcentaje_valor_siniestro") 
	private Double porcentaje_valor_siniestro;
	@Column(name="porcentaje_valor_asegurado") 
	private Double porcentaje_valor_asegurado;
	@Column(name="valor_minimo") 
	private Double valor_minimo;
	@Column(name="valor_fijo") 
	private Double valor_fijo;
	@Column(name="cd_compania") 
	private Integer cd_compania;
	@Column(name = "especificacion")
	private String especificacion;
	
	
	
	public Integer getCd_deducible() {
		return cd_deducible;
	}
	public void setCd_deducible(Integer cd_deducible) {
		this.cd_deducible = cd_deducible;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public String getDesc_deducible() {
		return desc_deducible;
	}
	public void setDesc_deducible(String desc_deducible) {
		this.desc_deducible = desc_deducible;
	}
	public Double getPorcentaje_valor_siniestro() {
		return porcentaje_valor_siniestro;
	}
	public void setPorcentaje_valor_siniestro(Double porcentaje_valor_siniestro) {
		this.porcentaje_valor_siniestro = porcentaje_valor_siniestro;
	}
	public Double getPorcentaje_valor_asegurado() {
		return porcentaje_valor_asegurado;
	}
	public void setPorcentaje_valor_asegurado(Double porcentaje_valor_asegurado) {
		this.porcentaje_valor_asegurado = porcentaje_valor_asegurado;
	}
	public Double getValor_minimo() {
		return valor_minimo;
	}
	public void setValor_minimo(Double valor_minimo) {
		this.valor_minimo = valor_minimo;
	}
	public Double getValor_fijo() {
		return valor_fijo;
	}
	public void setValor_fijo(Double valor_fijo) {
		this.valor_fijo = valor_fijo;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public String getEspecificacion() {
		return especificacion;
	}
	public void setEspecificacion(String especificacion) {
		this.especificacion = especificacion;
	}
	public Integer getCd_deducible_emitido() {
		return cd_deducible_emitido;
	}
	public void setCd_deducible_emitido(Integer cd_deducible_emitido) {
		this.cd_deducible_emitido = cd_deducible_emitido;
	}

}
