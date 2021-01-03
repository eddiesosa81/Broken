package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clausulas_emitidas_tbl")
public class ClausulasEmitidas {
	@Id
	@Column(name="cd_clausula") 
	private Integer cd_clausula;
	@Column(name="cd_plan") 
	private Integer cd_plan;
	@Column(name="cd_ramo_cotizacion") 
	private Integer cd_ramo_cotizacion;
	@Column(name="desc_clausula") 
	private String desc_clausula;
	@Column(name="porcentaje_planclausula") 
	private Double porcentaje_planclausula;
	@Column(name="valor_planclausula") 
	private Double valor_planclausula;
	@Column(name="cd_compania") 
	private Integer cd_compania;
	@Column(name="especificacion_cla") 
	private String especificacion_cla;
	@Column(name="cd_ubicacion") 
	private Integer cd_ubicacion;
	
	
	
	public Integer getCd_ubicacion() {
		return cd_ubicacion;
	}
	public void setCd_ubicacion(Integer cd_ubicacion) {
		this.cd_ubicacion = cd_ubicacion;
	}
	public String getEspecificacion_cla() {
		return especificacion_cla;
	}
	public void setEspecificacion_cla(String especificacion_cla) {
		this.especificacion_cla = especificacion_cla;
	}
	public Integer getCd_clausula() {
		return cd_clausula;
	}
	public void setCd_clausula(Integer cd_clausula) {
		this.cd_clausula = cd_clausula;
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
	public String getDesc_clausula() {
		return desc_clausula;
	}
	public void setDesc_clausula(String desc_clausula) {
		this.desc_clausula = desc_clausula;
	}
	public Double getPorcentaje_planclausula() {
		return porcentaje_planclausula;
	}
	public void setPorcentaje_planclausula(Double porcentaje_planclausula) {
		this.porcentaje_planclausula = porcentaje_planclausula;
	}
	public Double getValor_planclausula() {
		return valor_planclausula;
	}
	public void setValor_planclausula(Double valor_planclausula) {
		this.valor_planclausula = valor_planclausula;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	
	

}
