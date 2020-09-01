/**
 * 
 */
package confia.entidades.basicos;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pago_detalle_pago_view")
public class PagoDetallePago{
	
	@Id
	@Column(name="cd_comision_poliza")
	private Integer cd_comision_poliza;
	@Column(name="total_asegurado")
	private String total_asegurado;
	@Column(name="total_prima")
	private String total_prima;
	@Column(name="pct_com_brk")
	private Double pct_com_brk;
	@Column(name="val_com_brk")
	private Double val_com_brk;
	@Column(name="sal_com_brk")
	private Double sal_com_brk;
	@Column(name="valor_liquidado")
	private Double valor_liquidado;
	@Column(name="cd_cliente")
	private String cd_cliente;
	@Column(name="cliente")
	private String cliente;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="cd_ramo")
	private String cd_ramo;
	@Column(name="desc_ramo")
	private String desc_ramo;
	@Column(name="poliza")
	private String poliza;
	@Column(name="factura_aseg")
	private String factura_aseg;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="cd_compania")
	private String cd_compania;
	@Column(name="fc_ini_vigencia_mensual")
	private Date fc_ini_vigencia_mensual;
	@Column(name="fc_fin_vigencia_mensual")
	private Date fc_fin_vigencia_mensual;
	@Column(name="FC_INI_VIGENCIA_MENSUAL_JUL")
	private String FC_INI_VIGENCIA_MENSUAL_JUL;
	@Column(name="FC_FIN_VIGENCIA_MENSUAL_JUL")
	private String FC_FIN_VIGENCIA_MENSUAL_JUL;
	public Integer getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(Integer cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
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
	public Double getPct_com_brk() {
		return pct_com_brk;
	}
	public void setPct_com_brk(Double pct_com_brk) {
		this.pct_com_brk = pct_com_brk;
	}
	public Double getVal_com_brk() {
		return val_com_brk;
	}
	public void setVal_com_brk(Double val_com_brk) {
		this.val_com_brk = val_com_brk;
	}
	public Double getSal_com_brk() {
		return sal_com_brk;
	}
	public void setSal_com_brk(Double sal_com_brk) {
		this.sal_com_brk = sal_com_brk;
	}
	public Double getValor_liquidado() {
		return valor_liquidado;
	}
	public void setValor_liquidado(Double valor_liquidado) {
		this.valor_liquidado = valor_liquidado;
	}
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFactura_aseg() {
		return factura_aseg;
	}
	public void setFactura_aseg(String factura_aseg) {
		this.factura_aseg = factura_aseg;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(String cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Date getFc_ini_vigencia_mensual() {
		return fc_ini_vigencia_mensual;
	}
	public void setFc_ini_vigencia_mensual(Date fc_ini_vigencia_mensual) {
		this.fc_ini_vigencia_mensual = fc_ini_vigencia_mensual;
	}
	public Date getFc_fin_vigencia_mensual() {
		return fc_fin_vigencia_mensual;
	}
	public void setFc_fin_vigencia_mensual(Date fc_fin_vigencia_mensual) {
		this.fc_fin_vigencia_mensual = fc_fin_vigencia_mensual;
	}
	public String getFC_INI_VIGENCIA_MENSUAL_JUL() {
		return FC_INI_VIGENCIA_MENSUAL_JUL;
	}
	public void setFC_INI_VIGENCIA_MENSUAL_JUL(String fC_INI_VIGENCIA_MENSUAL_JUL) {
		FC_INI_VIGENCIA_MENSUAL_JUL = fC_INI_VIGENCIA_MENSUAL_JUL;
	}
	public String getFC_FIN_VIGENCIA_MENSUAL_JUL() {
		return FC_FIN_VIGENCIA_MENSUAL_JUL;
	}
	public void setFC_FIN_VIGENCIA_MENSUAL_JUL(String fC_FIN_VIGENCIA_MENSUAL_JUL) {
		FC_FIN_VIGENCIA_MENSUAL_JUL = fC_FIN_VIGENCIA_MENSUAL_JUL;
	}
	
	
}
