package confia.entidades.basicos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "plandeducible_tbl")
public class PlanDeducible {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_plandeducible")
	@SequenceGenerator(sequenceName = "secuencia_cd_plandeducible", name = "secuencia_cd_plandeducible", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_plandeducible")
	private Integer cd_plandeducible;
	@Column(name="cd_plan")
	private Integer cd_plan;
	@Column(name="cd_asegded")
	private Integer cd_asegded;
	@Column(name="fc_ini_plandeducible")
	private Date fc_ini_plandeducible;
	@Column(name="fc_fin_plandeducible")
	private Date fc_fin_plandeducible;
	@Column(name="estado_plandeducible")
	private String estado_plandeducible;
	@Column(name="porcentaje_valor_siniestro")
	private Double porcentaje_valor_siniestro;
	@Column(name="porcentaje_valor_asegurado")
	private Double porcentaje_valor_asegurado;
	@Column(name="valor_minimo")
	private Double valor_minimo;
	@Column(name="valor_fijo")
	private Double valor_fijo;
	@Column(name="especificacion")
	private String especificacion;
	
	
	public Integer getCd_plandeducible() {
		return cd_plandeducible;
	}
	public void setCd_plandeducible(Integer cd_plandeducible) {
		this.cd_plandeducible = cd_plandeducible;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Integer getCd_asegded() {
		return cd_asegded;
	}
	public void setCd_asegded(Integer cd_asegded) {
		this.cd_asegded = cd_asegded;
	}
	public Date getFc_ini_plandeducible() {
		return fc_ini_plandeducible;
	}
	public void setFc_ini_plandeducible(Date fc_ini_plandeducible) {
		this.fc_ini_plandeducible = fc_ini_plandeducible;
	}
	public Date getFc_fin_plandeducible() {
		return fc_fin_plandeducible;
	}
	public void setFc_fin_plandeducible(Date fc_fin_plandeducible) {
		this.fc_fin_plandeducible = fc_fin_plandeducible;
	}
	public String getEstado_plandeducible() {
		return estado_plandeducible;
	}
	public void setEstado_plandeducible(String estado_plandeducible) {
		this.estado_plandeducible = estado_plandeducible;
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
	public String getEspecificacion() {
		return especificacion;
	}
	public void setEspecificacion(String especificacion) {
		this.especificacion = especificacion;
	}
	
}
