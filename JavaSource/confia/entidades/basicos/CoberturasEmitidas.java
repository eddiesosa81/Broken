package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coberturas_emitidas_tbl")
public class CoberturasEmitidas {
	@Id
	@Column(name="cd_cobertura") 
	private Integer cd_cobertura;
	@Column(name="cd_plan") 
	private Integer cd_plan;
	@Column(name="cd_ramo_cotizacion") 
	private Integer cd_ramo_cotizacion;
	@Column(name="desc_cobertura") 
	private String desc_cobertura;
	@Column(name="porcentajeplancobertura") 
	private Double porcentajeplancobertura;
	@Column(name="valor_plancobertura") 
	private Double valor_plancobertura;
	@Column(name="cd_compania") 
	private Integer cd_compania;
	public Integer getCd_cobertura() {
		return cd_cobertura;
	}
	public void setCd_cobertura(Integer cd_cobertura) {
		this.cd_cobertura = cd_cobertura;
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
	public String getDesc_cobertura() {
		return desc_cobertura;
	}
	public void setDesc_cobertura(String desc_cobertura) {
		this.desc_cobertura = desc_cobertura;
	}
	public Double getPorcentajeplancobertura() {
		return porcentajeplancobertura;
	}
	public void setPorcentajeplancobertura(Double porcentajeplancobertura) {
		this.porcentajeplancobertura = porcentajeplancobertura;
	}
	public Double getValor_plancobertura() {
		return valor_plancobertura;
	}
	public void setValor_plancobertura(Double valor_plancobertura) {
		this.valor_plancobertura = valor_plancobertura;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	
	

}
