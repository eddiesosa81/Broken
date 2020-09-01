package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " plan_coberturas_view")
public class CoberturasPlanView {
	@Id
	@Column(name="cd_cobertura")
	private String cd_cobertura;
	@Column(name="cd_asegcob")
	private String cd_asegcob;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="cd_plancobertura")
	private String cd_plancobertura;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="desc_cobertura")
	private String desc_cobertura;
	@Column(name="fc_ini_plancobertura")
	private String fc_ini_plancobertura;
	@Column(name="fc_fin_plancobertura")
	private String fc_fin_plancobertura;
	@Column(name="porcentajeplancobertura")
	private String porcentajeplancobertura;
	@Column(name="valor_plancobertura")
	private String valor_plancobertura;
	public String getCd_cobertura() {
		return cd_cobertura;
	}
	public void setCd_cobertura(String cd_cobertura) {
		this.cd_cobertura = cd_cobertura;
	}
	public String getCd_asegcob() {
		return cd_asegcob;
	}
	public void setCd_asegcob(String cd_asegcob) {
		this.cd_asegcob = cd_asegcob;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getCd_plancobertura() {
		return cd_plancobertura;
	}
	public void setCd_plancobertura(String cd_plancobertura) {
		this.cd_plancobertura = cd_plancobertura;
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
	public String getDesc_cobertura() {
		return desc_cobertura;
	}
	public void setDesc_cobertura(String desc_cobertura) {
		this.desc_cobertura = desc_cobertura;
	}
	public String getFc_ini_plancobertura() {
		return fc_ini_plancobertura;
	}
	public void setFc_ini_plancobertura(String fc_ini_plancobertura) {
		this.fc_ini_plancobertura = fc_ini_plancobertura;
	}
	public String getFc_fin_plancobertura() {
		return fc_fin_plancobertura;
	}
	public void setFc_fin_plancobertura(String fc_fin_plancobertura) {
		this.fc_fin_plancobertura = fc_fin_plancobertura;
	}
	public String getPorcentajeplancobertura() {
		return porcentajeplancobertura;
	}
	public void setPorcentajeplancobertura(String porcentajeplancobertura) {
		this.porcentajeplancobertura = porcentajeplancobertura;
	}
	public String getValor_plancobertura() {
		return valor_plancobertura;
	}
	public void setValor_plancobertura(String valor_plancobertura) {
		this.valor_plancobertura = valor_plancobertura;
	}
	

}
