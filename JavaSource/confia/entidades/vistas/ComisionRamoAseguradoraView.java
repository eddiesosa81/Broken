package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " comision_ramo_aseg_view")
public class ComisionRamoAseguradoraView {
	@Id
	@Column(name="numrow")
	private String numrow;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="cd_ramo")
	private String cd_ramo;
	@Column(name="cd_ramoaseg")
	private String cd_ramoaseg;
	@Column(name="cd_com_ramaseg")
	private String cd_com_ramaseg;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="desc_ramo")
	private String descRamo;
	@Column(name="porcentaje_com_ramaseg")
	private String porcentaje_com_ramaseg;
	@Column(name="fc_ini_com_ramaseg")
	private String fc_ini_com_ramaseg;
	@Column(name="fc_fin_com_ramaseg")
	private String fc_fin_com_ramaseg;
	
	@Column(name="cd_grupo_contratante")
	private String cd_grupo_contratante;
	@Column(name="nombre_corto_grupo_contratante")
	private String nombre_corto_grupo_contratante;


	public String getNumrow() {
		return numrow;
	}
	public void setNumrow(String numrow) {
		this.numrow = numrow;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(String cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public String getCd_ramoaseg() {
		return cd_ramoaseg;
	}
	public void setCd_ramoaseg(String cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}
	public String getCd_com_ramaseg() {
		return cd_com_ramaseg;
	}
	public void setCd_com_ramaseg(String cd_com_ramaseg) {
		this.cd_com_ramaseg = cd_com_ramaseg;
	}
	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}
	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}
	public String getDescRamo() {
		return descRamo;
	}
	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}
	public String getPorcentaje_com_ramaseg() {
		return porcentaje_com_ramaseg;
	}
	public void setPorcentaje_com_ramaseg(String porcentaje_com_ramaseg) {
		this.porcentaje_com_ramaseg = porcentaje_com_ramaseg;
	}
	public String getFc_ini_com_ramaseg() {
		return fc_ini_com_ramaseg;
	}
	public void setFc_ini_com_ramaseg(String fc_ini_com_ramaseg) {
		this.fc_ini_com_ramaseg = fc_ini_com_ramaseg;
	}
	public String getFc_fin_com_ramaseg() {
		return fc_fin_com_ramaseg;
	}
	public void setFc_fin_com_ramaseg(String fc_fin_com_ramaseg) {
		this.fc_fin_com_ramaseg = fc_fin_com_ramaseg;
	}
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public String getNombre_corto_grupo_contratante() {
		return nombre_corto_grupo_contratante;
	}
	public void setNombre_corto_grupo_contratante(String nombre_corto_grupo_contratante) {
		this.nombre_corto_grupo_contratante = nombre_corto_grupo_contratante;
	}
	
}
