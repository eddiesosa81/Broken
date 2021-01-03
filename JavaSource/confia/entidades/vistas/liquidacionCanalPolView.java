package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "liquidacion_canal_POL_VIEW")
public class liquidacionCanalPolView {
	@Id
	@Column(name="numrow")
	private String numrow;
	@Column(name="cd_cliente")
	private String cd_cliente;
	@Column(name="datos_cliente")
	private String datos_cliente;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="cd_ramo_cotizacion")
	private String cd_ramo_cotizacion;
	@Column(name="cd_compania")
	private String cd_compania;
	@Column(name="cd_ramo")
	private String cd_ramo;
	@Column(name="desc_ramo")
	private String desc_ramo;
	@Column(name="cd_ejecutivo")
	private String cd_ejecutivo;
	
	@Column(name="datos_ejecutivo")
	private String datos_ejecutivo;
	@Column(name="cd_subagente")
	private String cd_subagente;
	@Column(name="datos_subagente")
	private String datos_subagente;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="descripcion_plan")
	private String descripcion_plan;
	@Column(name="cd_grupo_contratante")
	private String cd_grupo_contratante;
	@Column(name="nombre_corto_grupo_contratante")
	private String nombre_corto_grupo_contratante;
	@Column(name="poliza")
	private String poliza;
	@Column(name="factura_aseguradora")
	private String factura_aseguradora;
	
	@Column(name="fc_ini_vigencia")
	private String fc_ini_vigencia;
	@Column(name="fc_fin_vigencia")
	private String fc_fin_vigencia;
	@Column(name="total_asegurado")
	private String total_asegurado;
	@Column(name="total_prima")
	private String total_prima;
	@Column(name="pct_com_suba")
	private String pct_com_suba;
	@Column(name="val_com_suba")
	private String val_com_suba;
	@Column(name="cd_comisuba_pol")
	private String cd_comisuba_pol;
	@Column(name="cedula_canal")
	private String cedula_canal;
	@Column(name="fecha_factura_brk")
	private String fecha_factura_brk;
	@Column(name="numero_factura_brk")
	private String numero_factura_brk;
	@Column(name="ciudad_canal")
	private String ciudad_canal;
	@Column(name="fecha_emision")
	private String fecha_emision;
	@Column(name="pct_com_brk")
	private String pct_com_brk;
	@Column(name="val_com_brk")
	private String val_com_brk;
	@Column(name="fc_pago_canal")
	private String fc_pago_canal;
	@Column(name="num_factura_canal")
	private String num_factura_canal;
	
	@Column(name="cd_comision_poliza")
	private String cd_comision_poliza;
	@Column(name="flg_pago_canal")
	private String flg_pago_canal;	
	@Column(name="saldo_COM_SUBA")
	private String saldo_COM_SUBA;
	@Column(name="valor_liquidado")
	private String valor_liquidado;
	
	
	public String getSaldo_COM_SUBA() {
		return saldo_COM_SUBA;
	}
	public void setSaldo_COM_SUBA(String saldo_COM_SUBA) {
		this.saldo_COM_SUBA = saldo_COM_SUBA;
	}
	public String getFlg_pago_canal() {
		return flg_pago_canal;
	}
	public void setFlg_pago_canal(String flg_pago_canal) {
		this.flg_pago_canal = flg_pago_canal;
	}
	public String getNumrow() {
		return numrow;
	}
	public void setNumrow(String numrow) {
		this.numrow = numrow;
	}
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public String getDatos_cliente() {
		return datos_cliente;
	}
	public void setDatos_cliente(String datos_cliente) {
		this.datos_cliente = datos_cliente;
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
	public String getCd_ejecutivo() {
		return cd_ejecutivo;
	}
	public void setCd_ejecutivo(String cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}
	public String getDatos_ejecutivo() {
		return datos_ejecutivo;
	}
	public void setDatos_ejecutivo(String datos_ejecutivo) {
		this.datos_ejecutivo = datos_ejecutivo;
	}
	public String getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(String cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	public String getDatos_subagente() {
		return datos_subagente;
	}
	public void setDatos_subagente(String datos_subagente) {
		this.datos_subagente = datos_subagente;
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
	public String getFc_ini_vigencia() {
		return fc_ini_vigencia;
	}
	public void setFc_ini_vigencia(String fc_ini_vigencia) {
		this.fc_ini_vigencia = fc_ini_vigencia;
	}
	public String getFc_fin_vigencia() {
		return fc_fin_vigencia;
	}
	public void setFc_fin_vigencia(String fc_fin_vigencia) {
		this.fc_fin_vigencia = fc_fin_vigencia;
	}
	public String getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(String total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public String getTotal_prima() {
		return total_prima;
	}
	public void setTotal_prima(String total_prima) {
		this.total_prima = total_prima;
	}
	public String getPct_com_suba() {
		return pct_com_suba;
	}
	public void setPct_com_suba(String pct_com_suba) {
		this.pct_com_suba = pct_com_suba;
	}
	public String getVal_com_suba() {
		return val_com_suba;
	}
	public void setVal_com_suba(String val_com_suba) {
		this.val_com_suba = val_com_suba;
	}
	public String getCd_comisuba_pol() {
		return cd_comisuba_pol;
	}
	public void setCd_comisuba_pol(String cd_comisuba_pol) {
		this.cd_comisuba_pol = cd_comisuba_pol;
	}
	public String getCedula_canal() {
		return cedula_canal;
	}
	public void setCedula_canal(String cedula_canal) {
		this.cedula_canal = cedula_canal;
	}
	public String getFecha_factura_brk() {
		return fecha_factura_brk;
	}
	public void setFecha_factura_brk(String fecha_factura_brk) {
		this.fecha_factura_brk = fecha_factura_brk;
	}
	public String getNumero_factura_brk() {
		return numero_factura_brk;
	}
	public void setNumero_factura_brk(String numero_factura_brk) {
		this.numero_factura_brk = numero_factura_brk;
	}
	public String getCiudad_canal() {
		return ciudad_canal;
	}
	public void setCiudad_canal(String ciudad_canal) {
		this.ciudad_canal = ciudad_canal;
	}
	public String getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public String getPct_com_brk() {
		return pct_com_brk;
	}
	public void setPct_com_brk(String pct_com_brk) {
		this.pct_com_brk = pct_com_brk;
	}
	public String getVal_com_brk() {
		return val_com_brk;
	}
	public void setVal_com_brk(String val_com_brk) {
		this.val_com_brk = val_com_brk;
	}
	public String getFc_pago_canal() {
		return fc_pago_canal;
	}
	public void setFc_pago_canal(String fc_pago_canal) {
		this.fc_pago_canal = fc_pago_canal;
	}
	public String getNum_factura_canal() {
		return num_factura_canal;
	}
	public void setNum_factura_canal(String num_factura_canal) {
		this.num_factura_canal = num_factura_canal;
	}
	public String getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(String cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}
	public String getValor_liquidado() {
		return valor_liquidado;
	}
	public void setValor_liquidado(String valor_liquidado) {
		this.valor_liquidado = valor_liquidado;
	}

	
}
