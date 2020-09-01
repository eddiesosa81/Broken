package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " plan_ramo_aseg_view")
public class PlanRamoAseguradoraView {
	@Id
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="cd_ramoaseg")
	private String cd_ramoaseg;
	@Column(name="descripcion_plan")
	private String descripcionPlan;
	@Column(name="cd_ramo")
	private String cd_ramo;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="desc_ramo")
	private String descRamo;
	@Column(name="nombre_corto_aseguradora")
	private String nombreCortoAseguradora;
	public String getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(String cd_plan) {
		this.cd_plan = cd_plan;
	}
	public String getCd_ramoaseg() {
		return cd_ramoaseg;
	}
	public void setCd_ramoaseg(String cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}
	public String getDescripcionPlan() {
		return descripcionPlan;
	}
	public void setDescripcionPlan(String descripcionPlan) {
		this.descripcionPlan = descripcionPlan;
	}
	public String getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(String cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getDescRamo() {
		return descRamo;
	}
	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}
	public String getNombreCortoAseguradora() {
		return nombreCortoAseguradora;
	}
	public void setNombreCortoAseguradora(String nombreCortoAseguradora) {
		this.nombreCortoAseguradora = nombreCortoAseguradora;
	}
	

}
