package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_pagos_realizados_view")
public class ConsultaPagoRealizadosView {
	@Id
	@Column(name="cd_det_pago")
	private String cd_det_pago;
	@Column(name="cd_pago")
	private String cd_pago;
	@Column(name="fecha_pago")
	private String fecha_pago;
	@Column(name="forma_pago")
	private String forma_pago;
	@Column(name="banco")
	private String banco;
	@Column(name="cta_cte")
	private String cta_cte;
	@Column(name="no_retencion")
	private String no_retencion;
	@Column(name="cd_det_forma_pago")
	private String cd_det_forma_pago;
	@Column(name="poliza")
	private String poliza;
	@Column(name="facturas")
	private String facturas;
	@Column(name="valor_pago")
	private String valor_pago;
	@Column(name="CD_ASEGURADORA")
	private String CD_ASEGURADORA;
	@Column(name="USRID")
	private String USRID;
	@Column(name="CD_CLIENTE")
	private String CD_CLIENTE;
	@Column(name="ASEGURADORA")
	private String ASEGURADORA;
	@Column(name="CLIENTE")
	private String CLIENTE;
	@Column(name="USRLOGIN")
	private String USRLOGIN;
	@Column(name="NUM_RECIBO")
	private String NUM_RECIBO;
	@Column(name="FC_NUM_RECIBO")
	private String FC_NUM_RECIBO;
	
	public String getCd_det_pago() {
		return cd_det_pago;
	}
	public void setCd_det_pago(String cd_det_pago) {
		this.cd_det_pago = cd_det_pago;
	}
	public String getCd_pago() {
		return cd_pago;
	}
	public void setCd_pago(String cd_pago) {
		this.cd_pago = cd_pago;
	}
	public String getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getCta_cte() {
		return cta_cte;
	}
	public void setCta_cte(String cta_cte) {
		this.cta_cte = cta_cte;
	}
	public String getNo_retencion() {
		return no_retencion;
	}
	public void setNo_retencion(String no_retencion) {
		this.no_retencion = no_retencion;
	}
	public String getCd_det_forma_pago() {
		return cd_det_forma_pago;
	}
	public void setCd_det_forma_pago(String cd_det_forma_pago) {
		this.cd_det_forma_pago = cd_det_forma_pago;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFacturas() {
		return facturas;
	}
	public void setFacturas(String facturas) {
		this.facturas = facturas;
	}
	public String getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(String valor_pago) {
		this.valor_pago = valor_pago;
	}
	public String getCD_ASEGURADORA() {
		return CD_ASEGURADORA;
	}
	public void setCD_ASEGURADORA(String cD_ASEGURADORA) {
		CD_ASEGURADORA = cD_ASEGURADORA;
	}
	public String getUSRID() {
		return USRID;
	}
	public void setUSRID(String uSRID) {
		USRID = uSRID;
	}
	public String getCD_CLIENTE() {
		return CD_CLIENTE;
	}
	public void setCD_CLIENTE(String cD_CLIENTE) {
		CD_CLIENTE = cD_CLIENTE;
	}
	public String getASEGURADORA() {
		return ASEGURADORA;
	}
	public void setASEGURADORA(String aSEGURADORA) {
		ASEGURADORA = aSEGURADORA;
	}
	public String getCLIENTE() {
		return CLIENTE;
	}
	public void setCLIENTE(String cLIENTE) {
		CLIENTE = cLIENTE;
	}
	public String getUSRLOGIN() {
		return USRLOGIN;
	}
	public void setUSRLOGIN(String uSRLOGIN) {
		USRLOGIN = uSRLOGIN;
	}
	public String getNUM_RECIBO() {
		return NUM_RECIBO;
	}
	public void setNUM_RECIBO(String nUM_RECIBO) {
		NUM_RECIBO = nUM_RECIBO;
	}
	public String getFC_NUM_RECIBO() {
		return FC_NUM_RECIBO;
	}
	public void setFC_NUM_RECIBO(String fC_NUM_RECIBO) {
		FC_NUM_RECIBO = fC_NUM_RECIBO;
	}
	

}
