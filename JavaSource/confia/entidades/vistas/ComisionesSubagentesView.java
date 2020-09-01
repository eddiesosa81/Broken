package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " COMISIONES_SUBAGENTE_VIEW")
public class ComisionesSubagentesView {
	@Id
	@Column(name="cd_comisionsubagente")
	private String cd_comisionsubagente;
	@Column(name="cd_subagenteramo")
	private String cd_subagenteramo;
	@Column(name="cd_subagente")
	private String cd_subagente;
	@Column(name="nmsubagente")
	private String nmsubagente;
	@Column(name="cd_ramo")
	private String cd_ramo;
	@Column(name="desc_ramo")
	private String desc_ramo;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="cd_grupo_contratante")
	private String cd_grupo_contratante;
	@Column(name="razon_social_grupo_contratante")
	private String razon_social_grupo_contratante;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="descripcion_plan")
	private String descripcion_plan;
	@Column(name="porc_comision")
	private String porc_comision;
	public String getCd_comisionsubagente() {
		return cd_comisionsubagente;
	}
	public void setCd_comisionsubagente(String cd_comisionsubagente) {
		this.cd_comisionsubagente = cd_comisionsubagente;
	}
	public String getCd_subagenteramo() {
		return cd_subagenteramo;
	}
	public void setCd_subagenteramo(String cd_subagenteramo) {
		this.cd_subagenteramo = cd_subagenteramo;
	}
	public String getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(String cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	public String getNmsubagente() {
		return nmsubagente;
	}
	public void setNmsubagente(String nmsubagente) {
		this.nmsubagente = nmsubagente;
	}
	public String getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(String cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public String getDesc_ramo() {
		return desc_ramo;
	}
	public void setDesc_ramo(String desc_ramo) {
		this.desc_ramo = desc_ramo;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}
	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public String getRazon_social_grupo_contratante() {
		return razon_social_grupo_contratante;
	}
	public void setRazon_social_grupo_contratante(String razon_social_grupo_contratante) {
		this.razon_social_grupo_contratante = razon_social_grupo_contratante;
	}
	public String getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(String cd_plan) {
		this.cd_plan = cd_plan;
	}
	public String getDescripcion_plan() {
		return descripcion_plan;
	}
	public void setDescripcion_plan(String descripcion_plan) {
		this.descripcion_plan = descripcion_plan;
	}
	public String getPorc_comision() {
		return porc_comision;
	}
	public void setPorc_comision(String porc_comision) {
		this.porc_comision = porc_comision;
	}
	
}
