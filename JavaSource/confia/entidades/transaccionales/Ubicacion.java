package confia.entidades.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ubicacion_tbl")
public class Ubicacion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_UBICACION")
	@SequenceGenerator(sequenceName = "secuencia_CD_UBICACION", name = "secuencia_CD_UBICACION", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_ubicacion")
	private Integer cd_ubicacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="fc_ini_ubicacion")
	private Integer fc_ini_ubicacion;
	@Column(name="fc_fin_ubicacion")
	private Integer fc_fin_ubicacion;
	@Column(name="fc_ini_ubc_date")
	private Date fc_ini_ubc_date;
	@Column(name="fc_fin_ubc_date")
	private Date fc_fin_ubc_date;
	@Column(name="valor_asegurado_ubicacion")
	private Double valor_asegurado_ubicacion;
	@Column(name="tasa_ubicacion")
	private Double tasa_ubicacion;
	@Column(name="factor_ubicacion")
	private Double factor_ubicacion;
	@Column(name="valor_prima_ubicacion")
	private Double valor_prima_ubicacion;
	@Column(name="dsc_ubicacion")
	private String dsc_ubicacion;
	@Column(name="dias_vigencia")
	private Integer dias_vigencia;
	@Column(name="cd_ubc_ori")
	private Integer cdUbcOri;
	@Column(name="cd_plan")
	private Integer cd_plan;
	@Column(name="descripcion_plan")
	private String descripcion_plan;
	public Integer getCd_ubicacion() {
		return cd_ubicacion;
	}
	public void setCd_ubicacion(Integer cd_ubicacion) {
		this.cd_ubicacion = cd_ubicacion;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public Integer getFc_ini_ubicacion() {
		return fc_ini_ubicacion;
	}
	public void setFc_ini_ubicacion(Integer fc_ini_ubicacion) {
		this.fc_ini_ubicacion = fc_ini_ubicacion;
	}
	public Integer getFc_fin_ubicacion() {
		return fc_fin_ubicacion;
	}
	public void setFc_fin_ubicacion(Integer fc_fin_ubicacion) {
		this.fc_fin_ubicacion = fc_fin_ubicacion;
	}
	public Date getFc_ini_ubc_date() {
		return fc_ini_ubc_date;
	}
	public void setFc_ini_ubc_date(Date fc_ini_ubc_date) {
		this.fc_ini_ubc_date = fc_ini_ubc_date;
	}
	public Date getFc_fin_ubc_date() {
		return fc_fin_ubc_date;
	}
	public void setFc_fin_ubc_date(Date fc_fin_ubc_date) {
		this.fc_fin_ubc_date = fc_fin_ubc_date;
	}
	public Double getValor_asegurado_ubicacion() {
		return valor_asegurado_ubicacion;
	}
	public void setValor_asegurado_ubicacion(Double valor_asegurado_ubicacion) {
		this.valor_asegurado_ubicacion = valor_asegurado_ubicacion;
	}
	public Double getTasa_ubicacion() {
		return tasa_ubicacion;
	}
	public void setTasa_ubicacion(Double tasa_ubicacion) {
		this.tasa_ubicacion = tasa_ubicacion;
	}
	public Double getFactor_ubicacion() {
		return factor_ubicacion;
	}
	public void setFactor_ubicacion(Double factor_ubicacion) {
		this.factor_ubicacion = factor_ubicacion;
	}
	public Double getValor_prima_ubicacion() {
		return valor_prima_ubicacion;
	}
	public void setValor_prima_ubicacion(Double valor_prima_ubicacion) {
		this.valor_prima_ubicacion = valor_prima_ubicacion;
	}
	public String getDsc_ubicacion() {
		return dsc_ubicacion;
	}
	public void setDsc_ubicacion(String dsc_ubicacion) {
		this.dsc_ubicacion = dsc_ubicacion;
	}
	public Integer getDias_vigencia() {
		return dias_vigencia;
	}
	public void setDias_vigencia(Integer dias_vigencia) {
		this.dias_vigencia = dias_vigencia;
	}
	public Integer getCdUbcOri() {
		return cdUbcOri;
	}
	public void setCdUbcOri(Integer cdUbcOri) {
		this.cdUbcOri = cdUbcOri;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public String getDescripcion_plan() {
		return descripcion_plan;
	}
	public void setDescripcion_plan(String descripcion_plan) {
		this.descripcion_plan = descripcion_plan;
	}
	

}
