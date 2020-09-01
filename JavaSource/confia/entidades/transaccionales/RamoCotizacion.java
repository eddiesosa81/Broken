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
@Table(name = "RAMO_cotizacion_tbl")
public class RamoCotizacion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_Cd_RAMO_COTIZACION")
	@SequenceGenerator(sequenceName = "secuencia_Cd_RAMO_COTIZACION", name = "secuencia_Cd_RAMO_COTIZACION", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name="cd_ramo")
	private Integer cd_ramo;
	@Column(name="cd_ejecutivo")
	private Integer cd_ejecutivo;
	@Column(name="cd_subagente")
	private Integer cd_subagente;
	@Column(name="cd_plan")
	private Integer cd_plan;
	@Column(name="cd_grupo_contratante")
	private Integer cd_grupo_contratante;
	@Column(name="poliza")
	private String poliza;
	@Column(name="factura_aseguradora")
	private String factura_aseguradora;
	@Column(name="fc_ini_vigencia")
	private Integer fc_ini_vigencia;
	@Column(name="fc_fin_vigencia")
	private Integer fc_fin_vigencia;
	@Column(name="fc_ini_vig_date")
	private Date fc_ini_vig_date;
	@Column(name="fc_fin_vig_date")
	private Date fc_fin_vig_date;
	@Column(name="fc_emision")
	private Integer fc_emision;
	@Column(name="fc_emision_date")
	private Date fc_emision_date;
	@Column(name="total_asegurado")
	private Double total_asegurado;
	@Column(name="total_prima")
	private Double total_prima;
	@Column(name="fc_factura_date")
	private Date fc_factura_date;
	@Column(name="fc_factura")
	private Integer fc_factura;
	@Column(name="fc_renovacion")
	private Integer fc_renovacion;
	@Column(name="fc_renovacion_date")
	private Date fc_renovacion_date;
	@Column(name="anexo")
	private String anexo;
	@Column(name="diasVigencia")
	private Integer diasVigencia;
	@Column(name="tasa")
	private Double tasa;
	@Column(name="factor")
	private Double factor;
	@Column(name="cd_ram_cot_ori")
	private Double cdRamCotOri;
	@Column(name="tipo")
	private String tipo;
	@Column(name="fc_emision_aseguradora")
	private Integer fc_emision_aseguradora;
	@Column(name="fc_emision_aseguradora_date")
	private Date fc_emision_aseguradora_date;
	@Column(name="numero_asistencia")
	private String numero_asistencia;
	
	
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public Integer getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(Integer cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public Integer getCd_ejecutivo() {
		return cd_ejecutivo;
	}
	public void setCd_ejecutivo(Integer cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}
	public Integer getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(Integer cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Integer getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(Integer cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFactura_aseguradora() {
		return factura_aseguradora;
	}
	public void setFactura_aseguradora(String factura_aseguradora) {
		this.factura_aseguradora = factura_aseguradora;
	}
	public Integer getFc_ini_vigencia() {
		return fc_ini_vigencia;
	}
	public void setFc_ini_vigencia(Integer fc_ini_vigencia) {
		this.fc_ini_vigencia = fc_ini_vigencia;
	}
	public Integer getFc_fin_vigencia() {
		return fc_fin_vigencia;
	}
	public void setFc_fin_vigencia(Integer fc_fin_vigencia) {
		this.fc_fin_vigencia = fc_fin_vigencia;
	}
	public Integer getFc_emision() {
		return fc_emision;
	}
	public void setFc_emision(Integer fc_emision) {
		this.fc_emision = fc_emision;
	}
	public Date getFc_emision_date() {
		return fc_emision_date;
	}
	public void setFc_emision_date(Date fc_emision_date) {
		this.fc_emision_date = fc_emision_date;
	}
	public Double getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(Double total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public Double getTotal_prima() {
		return total_prima;
	}
	public void setTotal_prima(Double total_prima) {
		this.total_prima = total_prima;
	}
	public Date getFc_factura_date() {
		return fc_factura_date;
	}
	public void setFc_factura_date(Date fc_factura_date) {
		this.fc_factura_date = fc_factura_date;
	}
	public Integer getFc_factura() {
		return fc_factura;
	}
	public void setFc_factura(Integer fc_factura) {
		this.fc_factura = fc_factura;
	}
	public Integer getFc_renovacion() {
		return fc_renovacion;
	}
	public void setFc_renovacion(Integer fc_renovacion) {
		this.fc_renovacion = fc_renovacion;
	}
	public Date getFc_renovacion_date() {
		return fc_renovacion_date;
	}
	public void setFc_renovacion_date(Date fc_renovacion_date) {
		this.fc_renovacion_date = fc_renovacion_date;
	}
	public Date getFc_ini_vig_date() {
		return fc_ini_vig_date;
	}
	public void setFc_ini_vig_date(Date date) {
		this.fc_ini_vig_date = date;
	}
	public Date getFc_fin_vig_date() {
		return fc_fin_vig_date;
	}
	public void setFc_fin_vig_date(Date fc_fin_vig_date) {
		this.fc_fin_vig_date = fc_fin_vig_date;
	}
	public Integer getDiasVigencia() {
		return diasVigencia;
	}
	public void setDiasVigencia(Integer diasVigencia) {
		this.diasVigencia = diasVigencia;
	}
	public Double getTasa() {
		return tasa;
	}
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	public Double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
	}
	public Double getCdRamCotOri() {
		return cdRamCotOri;
	}
	public void setCdRamCotOri(Double cdRamCotOri) {
		this.cdRamCotOri = cdRamCotOri;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getFc_emision_aseguradora() {
		return fc_emision_aseguradora;
	}
	public void setFc_emision_aseguradora(Integer fc_emision_aseguradora) {
		this.fc_emision_aseguradora = fc_emision_aseguradora;
	}
	public Date getFc_emision_aseguradora_date() {
		return fc_emision_aseguradora_date;
	}
	public void setFc_emision_aseguradora_date(Date fc_emision_aseguradora_date) {
		this.fc_emision_aseguradora_date = fc_emision_aseguradora_date;
	}
	public String getNumero_asistencia() {
		return numero_asistencia;
	}
	public void setNumero_asistencia(String numero_asistencia) {
		this.numero_asistencia = numero_asistencia;
	}
	
	
}
