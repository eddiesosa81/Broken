package confia.entidades.vistas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comision_aseguradora_pol_view")
public class ComisionDiferencialPolView {
	@Id
	@Column(name = "numrow")
	private String numrow;
	
	@Column(name = "cd_comision_poliza")
	private String cd_comision_poliza;
	@Column(name = "cd_ramo_cotizacion")
	private String cd_ramo_cotizacion;
	@Column(name = "cd_compania")
	private String cd_compania;
	@Column(name = "cd_aseguradora")
	private String cd_aseguradora;
	@Column(name = "nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name = "informacion_cliente")
	private String informacion_cliente;
	@Column(name = "poliza")
	private String poliza;
	@Column(name = "factura_aseguradora")
	private String factura_aseguradora;
	@Column(name = "total_prima")
	private String total_prima;
	
	@Column(name = "tasa_cliente")
	private String tasa_cliente;
	@Column(name = "tasa_canal")
	private String tasa_canal;
	@Column(name = "prima_neta_canal")
	private String prima_neta_canal;
	@Column(name = "tasa_confia")
	private String tasa_confia;
	@Column(name = "prima_neta_confia")
	private String prima_neta_confia;
	@Column(name = "diferencial_canal")
	private String diferencial_canal;
	@Column(name = "diferencial_confia")
	private String diferencial_confia;
	@Column(name = "cd_subagente")
	private String cd_subagente;
	@Column(name = "nm_subagente")
	private String nm_subagente;
	@Column(name = "cuota_mensual")
	private String cuota_mensual;
	@Column(name = "fc_inicio_vig_mensual")
	private String fc_inicio_vig_mensual;
	@Column(name = "fc_fin_vig_mensual")
	private String fc_fin_vig_mensual;
	
	@Column(name = "fc_emision_date")
	private Date fc_emision_date;
	@Column(name = "flg_pre_factura")
	private String flg_pre_factura;
	@Column(name = "flg_factura")
	private String flg_factura;
	@Column(name = "flg_liquidadiferencial")
	private String flg_liquidadiferencial;
	@Column(name = "fecha_factura_brk")
	private Date fecha_factura_brk;
	@Column(name = "num_factura_brk")
	private String num_factura_brk;
	@Column(name = "cd_plan")
	private String cd_plan;
	@Column(name = "plan")
	private String plan;
	@Column(name = "total_asegurado")
	private String total_asegurado;
	
	
	
	
	
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(String cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Date getFc_emision_date() {
		return fc_emision_date;
	}
	public void setFc_emision_date(Date fc_emision_date) {
		this.fc_emision_date = fc_emision_date;
	}
	public Date getFecha_factura_brk() {
		return fecha_factura_brk;
	}
	public void setFecha_factura_brk(Date fecha_factura_brk) {
		this.fecha_factura_brk = fecha_factura_brk;
	}
	public String getNum_factura_brk() {
		return num_factura_brk;
	}
	public void setNum_factura_brk(String num_factura_brk) {
		this.num_factura_brk = num_factura_brk;
	}
	public String getNumrow() {
		return numrow;
	}
	public void setNumrow(String numrow) {
		this.numrow = numrow;
	}
	public String getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(String cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}
	public String getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(String cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public String getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(String cd_compania) {
		this.cd_compania = cd_compania;
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
	public String getInformacion_cliente() {
		return informacion_cliente;
	}
	public void setInformacion_cliente(String informacion_cliente) {
		this.informacion_cliente = informacion_cliente;
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
	public String getTotal_prima() {
		return total_prima;
	}
	public void setTotal_prima(String total_prima) {
		this.total_prima = total_prima;
	}
	public String getTasa_cliente() {
		return tasa_cliente;
	}
	public void setTasa_cliente(String tasa_cliente) {
		this.tasa_cliente = tasa_cliente;
	}
	public String getTasa_canal() {
		return tasa_canal;
	}
	public void setTasa_canal(String tasa_canal) {
		this.tasa_canal = tasa_canal;
	}
	public String getPrima_neta_canal() {
		return prima_neta_canal;
	}
	public void setPrima_neta_canal(String prima_neta_canal) {
		this.prima_neta_canal = prima_neta_canal;
	}
	public String getTasa_confia() {
		return tasa_confia;
	}
	public void setTasa_confia(String tasa_confia) {
		this.tasa_confia = tasa_confia;
	}
	public String getPrima_neta_confia() {
		return prima_neta_confia;
	}
	public void setPrima_neta_confia(String prima_neta_confia) {
		this.prima_neta_confia = prima_neta_confia;
	}
	public String getDiferencial_canal() {
		return diferencial_canal;
	}
	public void setDiferencial_canal(String diferencial_canal) {
		this.diferencial_canal = diferencial_canal;
	}
	public String getDiferencial_confia() {
		return diferencial_confia;
	}
	public void setDiferencial_confia(String diferencial_confia) {
		this.diferencial_confia = diferencial_confia;
	}
	public String getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(String cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	public String getNm_subagente() {
		return nm_subagente;
	}
	public void setNm_subagente(String nm_subagente) {
		this.nm_subagente = nm_subagente;
	}
	public String getCuota_mensual() {
		return cuota_mensual;
	}
	public void setCuota_mensual(String cuota_mensual) {
		this.cuota_mensual = cuota_mensual;
	}
	public String getFc_inicio_vig_mensual() {
		return fc_inicio_vig_mensual;
	}
	public void setFc_inicio_vig_mensual(String fc_inicio_vig_mensual) {
		this.fc_inicio_vig_mensual = fc_inicio_vig_mensual;
	}
	public String getFc_fin_vig_mensual() {
		return fc_fin_vig_mensual;
	}
	public void setFc_fin_vig_mensual(String fc_fin_vig_mensual) {
		this.fc_fin_vig_mensual = fc_fin_vig_mensual;
	}

	public String getFlg_factura() {
		return flg_factura;
	}
	public void setFlg_factura(String flg_factura) {
		this.flg_factura = flg_factura;
	}
	public String getFlg_liquidadiferencial() {
		return flg_liquidadiferencial;
	}
	public void setFlg_liquidadiferencial(String flg_liquidadiferencial) {
		this.flg_liquidadiferencial = flg_liquidadiferencial;
	}
	public String getFlg_pre_factura() {
		return flg_pre_factura;
	}
	public void setFlg_pre_factura(String flg_pre_factura) {
		this.flg_pre_factura = flg_pre_factura;
	}
	public String getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(String total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	
	
}
