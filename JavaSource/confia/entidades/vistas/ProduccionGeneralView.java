package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reporte_general_view")
public class ProduccionGeneralView {
	@Id
	@Column(name="codId")
	public String codId;
	@Column(name="CANAL")
	public String canal;
	@Column(name="CLIENTE")
	public String cliente;
	@Column(name="ASEGURADORA")
	public String aseguradora;
	@Column(name="RAMO")
	public String ramo;
	@Column(name="GRUPO_CONTRATANTE")
	public String grupoContratante;
	@Column(name="POLIZA")
	public String poliza;
	@Column(name="COMISION_BROKER")
	public String comsionBroker;
	@Column(name="POR_COMISION_BROKER")
	public String porcComBrk;
	@Column(name="FACTURA")
	public String factura;
	
	@Column(name="fecha_solicitud_emision")
	public String fecha_solicitud_emision;
	@Column(name="fecha_emision")
	public String fecha_emision;
	@Column(name="prima_neta")
	public String prima_neta;
	@Column(name="vigencia_desde")
	public String vigencia_desde;
	@Column(name="vigencia_hasta")
	public String vigencia_hasta;
	@Column(name="ejecutivo")
	public String ejecutivo;
	@Column(name="tipo")
	public String tipo;
	@Column(name="prima_total")
	public String prima_total;
	@Column(name="total_asegurado")
	public String total_asegurado;
	@Column(name="num_renova")
	public String num_renova;
	@Column(name="usuario")
	public String usuario;
	@Column(name="fecha_aseguradora")
	public String fecha_aseguradora;
	@Column(name="fecha_pago_cliente")
	public String fecha_pago_cliente;
	@Column(name="fecha_facturacion_comisiones")
	public String fecha_facturacion_comisiones;
	@Column(name="forma_pago_cliente")
	public String forma_pago_cliente;
	@Column(name="reporta_siniestro")
	public String reporta_siniestro;
	@Column(name="estado_cartera")
	public String estado_cartera;
	@Column(name="cd_cliente")
	public String cd_cliente;
	@Column(name="cd_grupo_contratante")
	public String cd_grupo_contratante;
	@Column(name="cd_aseguradora")
	public String cd_aseguradora;
	@Column(name="cd_ramo")
	public String cd_ramo;
	
	@Column(name="cd_subagente")
	public String cd_subagente;
	@Column(name="cd_ejecutivo")
	public String cd_ejecutivo;
	@Column(name="fc_desde_jul")
	public String fc_desde_jul;
	@Column(name="fc_hasta_jul")
	public String fc_hasta_jul;
	@Column(name="fc_emision_jul")
	public String fc_emision_jul;
	@Column(name="cd_rubro")
	public String cd_rubro;
	@Column(name="anexo")
	public String anexo;
	@Column(name="cd_ramo_cotizacion")
	public String cd_ramo_cotizacion;
	@Column(name="fact_periodica_cot")
	public String fact_periodica_cot;
	@Column(name="cd_det_forma_pago")
	public String cd_det_forma_pago;
	@Column(name="cd_comision_poliza")
	public String cd_comision_poliza;
	
	@Column(name="ANIO_VIG_DESDE")
	public String ANIO_VIG_DESDE;
	@Column(name="MES_VIG_DESDE")
	public String MES_VIG_DESDE;
	@Column(name="TIPO_CLIENTE_POLIZA")
	public String TIPO_CLIENTE_POLIZA;
	@Column(name="FACTURADO_ASEGURADORA")
	public String FACTURADO_ASEGURADORA;
	
	@Column(name="porcentaje_comision_canal")
	public String porcentaje_comision_canal;
	@Column(name="valor_comision_canal")
	public String valor_comision_canal;
	@Column(name="saldo_comision_canal")
	public String saldo_comision_canal;
	@Column(name="fecha_pago_comision_canal")
	public String fecha_pago_comision_canal;
	
	
	
	
	public String getANIO_VIG_DESDE() {
		return ANIO_VIG_DESDE;
	}
	public void setANIO_VIG_DESDE(String aNIO_VIG_DESDE) {
		ANIO_VIG_DESDE = aNIO_VIG_DESDE;
	}
	public String getMES_VIG_DESDE() {
		return MES_VIG_DESDE;
	}
	public void setMES_VIG_DESDE(String mES_VIG_DESDE) {
		MES_VIG_DESDE = mES_VIG_DESDE;
	}
	public String getTIPO_CLIENTE_POLIZA() {
		return TIPO_CLIENTE_POLIZA;
	}
	public void setTIPO_CLIENTE_POLIZA(String tIPO_CLIENTE_POLIZA) {
		TIPO_CLIENTE_POLIZA = tIPO_CLIENTE_POLIZA;
	}
	public String getFACTURADO_ASEGURADORA() {
		return FACTURADO_ASEGURADORA;
	}
	public void setFACTURADO_ASEGURADORA(String fACTURADO_ASEGURADORA) {
		FACTURADO_ASEGURADORA = fACTURADO_ASEGURADORA;
	}
	public String getCodId() {
		return codId;
	}
	public void setCodId(String codId) {
		this.codId = codId;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getAseguradora() {
		return aseguradora;
	}
	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}
	public String getRamo() {
		return ramo;
	}
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}
	public String getGrupoContratante() {
		return grupoContratante;
	}
	public void setGrupoContratante(String grupoContratante) {
		this.grupoContratante = grupoContratante;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getComsionBroker() {
		return comsionBroker;
	}
	public void setComsionBroker(String comsionBroker) {
		this.comsionBroker = comsionBroker;
	}
	public String getPorcComBrk() {
		return porcComBrk;
	}
	public void setPorcComBrk(String porcComBrk) {
		this.porcComBrk = porcComBrk;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getFecha_solicitud_emision() {
		return fecha_solicitud_emision;
	}
	public void setFecha_solicitud_emision(String fecha_solicitud_emision) {
		this.fecha_solicitud_emision = fecha_solicitud_emision;
	}
	public String getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public String getPrima_neta() {
		return prima_neta;
	}
	public void setPrima_neta(String prima_neta) {
		this.prima_neta = prima_neta;
	}
	public String getVigencia_desde() {
		return vigencia_desde;
	}
	public void setVigencia_desde(String vigencia_desde) {
		this.vigencia_desde = vigencia_desde;
	}
	public String getVigencia_hasta() {
		return vigencia_hasta;
	}
	public void setVigencia_hasta(String vigencia_hasta) {
		this.vigencia_hasta = vigencia_hasta;
	}
	public String getEjecutivo() {
		return ejecutivo;
	}
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPrima_total() {
		return prima_total;
	}
	public void setPrima_total(String prima_total) {
		this.prima_total = prima_total;
	}
	public String getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(String total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public String getNum_renova() {
		return num_renova;
	}
	public void setNum_renova(String num_renova) {
		this.num_renova = num_renova;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFecha_aseguradora() {
		return fecha_aseguradora;
	}
	public void setFecha_aseguradora(String fecha_aseguradora) {
		this.fecha_aseguradora = fecha_aseguradora;
	}
	public String getFecha_pago_cliente() {
		return fecha_pago_cliente;
	}
	public void setFecha_pago_cliente(String fecha_pago_cliente) {
		this.fecha_pago_cliente = fecha_pago_cliente;
	}
	public String getFecha_facturacion_comisiones() {
		return fecha_facturacion_comisiones;
	}
	public void setFecha_facturacion_comisiones(String fecha_facturacion_comisiones) {
		this.fecha_facturacion_comisiones = fecha_facturacion_comisiones;
	}
	public String getForma_pago_cliente() {
		return forma_pago_cliente;
	}
	public void setForma_pago_cliente(String forma_pago_cliente) {
		this.forma_pago_cliente = forma_pago_cliente;
	}
	public String getReporta_siniestro() {
		return reporta_siniestro;
	}
	public void setReporta_siniestro(String reporta_siniestro) {
		this.reporta_siniestro = reporta_siniestro;
	}
	public String getEstado_cartera() {
		return estado_cartera;
	}
	public void setEstado_cartera(String estado_cartera) {
		this.estado_cartera = estado_cartera;
	}
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
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
	public String getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(String cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	public String getCd_ejecutivo() {
		return cd_ejecutivo;
	}
	public void setCd_ejecutivo(String cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}
	public String getFc_desde_jul() {
		return fc_desde_jul;
	}
	public void setFc_desde_jul(String fc_desde_jul) {
		this.fc_desde_jul = fc_desde_jul;
	}
	public String getFc_hasta_jul() {
		return fc_hasta_jul;
	}
	public void setFc_hasta_jul(String fc_hasta_jul) {
		this.fc_hasta_jul = fc_hasta_jul;
	}
	public String getFc_emision_jul() {
		return fc_emision_jul;
	}
	public void setFc_emision_jul(String fc_emision_jul) {
		this.fc_emision_jul = fc_emision_jul;
	}
	public String getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(String cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public String getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(String cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public String getFact_periodica_cot() {
		return fact_periodica_cot;
	}
	public void setFact_periodica_cot(String fact_periodica_cot) {
		this.fact_periodica_cot = fact_periodica_cot;
	}
	public String getCd_det_forma_pago() {
		return cd_det_forma_pago;
	}
	public void setCd_det_forma_pago(String cd_det_forma_pago) {
		this.cd_det_forma_pago = cd_det_forma_pago;
	}
	public String getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(String cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}
	public String getPorcentaje_comision_canal() {
		return porcentaje_comision_canal;
	}
	public void setPorcentaje_comision_canal(String porcentaje_comision_canal) {
		this.porcentaje_comision_canal = porcentaje_comision_canal;
	}
	public String getValor_comision_canal() {
		return valor_comision_canal;
	}
	public void setValor_comision_canal(String valor_comision_canal) {
		this.valor_comision_canal = valor_comision_canal;
	}
	public String getSaldo_comision_canal() {
		return saldo_comision_canal;
	}
	public void setSaldo_comision_canal(String saldo_comision_canal) {
		this.saldo_comision_canal = saldo_comision_canal;
	}
	public String getFecha_pago_comision_canal() {
		return fecha_pago_comision_canal;
	}
	public void setFecha_pago_comision_canal(String fecha_pago_comision_canal) {
		this.fecha_pago_comision_canal = fecha_pago_comision_canal;
	}
	
	
}
