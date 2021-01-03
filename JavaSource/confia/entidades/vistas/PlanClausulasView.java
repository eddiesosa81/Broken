package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " plan_clausulas_view")
public class PlanClausulasView {
	@Id
	@Column(name="cd_clausula")
	private String cd_clausula;
	@Column(name="cd_asegclau")
	private String cd_asegclau;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="cd_planclausula")
	private String cd_planclausula;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="desc_clausula")
	private String desc_clausula;
	@Column(name="fc_ini_planclausula")
	private String fc_ini_planclausula;
	@Column(name="fc_fin_planclausula")
	private String fc_fin_planclausula;
	@Column(name="porcentaje_planclausula")
	private String porcentaje_planclausula;
	@Column(name="valor_planclausula")
	private String valor_planclausula;
	@Column(name="ESPECIFICACION_CLA")
	private String especificacion_cla;
	
	
	
	public String getEspecificacion_cla() {
		return especificacion_cla;
	}
	public void setEspecificacion_cla(String especificacion_cla) {
		this.especificacion_cla = especificacion_cla;
	}
	public String getCd_clausula() {
		return cd_clausula;
	}
	public void setCd_clausula(String cd_clausula) {
		this.cd_clausula = cd_clausula;
	}
	public String getCd_asegclau() {
		return cd_asegclau;
	}
	public void setCd_asegclau(String cd_asegclau) {
		this.cd_asegclau = cd_asegclau;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getCd_planclausula() {
		return cd_planclausula;
	}
	public void setCd_planclausula(String cd_planclausula) {
		this.cd_planclausula = cd_planclausula;
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
	public String getDesc_clausula() {
		return desc_clausula;
	}
	public void setDesc_clausula(String desc_clausula) {
		this.desc_clausula = desc_clausula;
	}
	public String getFc_ini_planclausula() {
		return fc_ini_planclausula;
	}
	public void setFc_ini_planclausula(String fc_ini_planclausula) {
		this.fc_ini_planclausula = fc_ini_planclausula;
	}
	public String getFc_fin_planclausula() {
		return fc_fin_planclausula;
	}
	public void setFc_fin_planclausula(String fc_fin_planclausula) {
		this.fc_fin_planclausula = fc_fin_planclausula;
	}
	public String getPorcentaje_planclausula() {
		return porcentaje_planclausula;
	}
	public void setPorcentaje_planclausula(String porcentaje_planclausula) {
		this.porcentaje_planclausula = porcentaje_planclausula;
	}
	public String getValor_planclausula() {
		return valor_planclausula;
	}
	public void setValor_planclausula(String valor_planclausula) {
		this.valor_planclausula = valor_planclausula;
	}

	

}
