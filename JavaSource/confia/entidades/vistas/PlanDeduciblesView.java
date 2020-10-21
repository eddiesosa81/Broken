package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " plan_deducibles_view")
public class PlanDeduciblesView {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="cd_deducible")
	private String cd_deducible;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="cd_asegded")
	private String cd_asegded;
	@Column(name="cd_plandeducible")
	private String cd_plandeducible;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
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
	@Column(name="especificacion_ded")
	private String especificacionDed;
	
	public String getCd_deducible() {
		return cd_deducible;
	}
	public void setCd_deducible(String cd_deducible) {
		this.cd_deducible = cd_deducible;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getCd_asegded() {
		return cd_asegded;
	}
	public void setCd_asegded(String cd_asegded) {
		this.cd_asegded = cd_asegded;
	}
	public String getCd_plandeducible() {
		return cd_plandeducible;
	}
	public void setCd_plandeducible(String cd_plandeducible) {
		this.cd_plandeducible = cd_plandeducible;
	}
	public String getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(String cd_plan) {
		this.cd_plan = cd_plan;
	}
	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}
	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
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
	public String getEspecificacionDed() {
		return especificacionDed;
	}
	public void setEspecificacionDed(String especificacionDed) {
		this.especificacionDed = especificacionDed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
}
