package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prod_pendiente_emision_view")
public class ProduccionEmisionesPendientesView {
	
	@Id
	@Column(name="codid")
	public String codid;
	@Column(name="canal")
	public String canal;
	@Column(name="cliente")
	public String cliente;
	@Column(name="aseguradora")
	public String aseguradora;
	@Column(name="ramo")
	public String ramo;
	@Column(name="grupo_contratante")
	public String grupo_contratante;
	@Column(name="poliza")
	public String poliza;
	@Column(name="comision_broker")
	public String comision_broker;
	@Column(name="por_comision_broker")
	public String por_comision_broker;
	@Column(name="factura")
	public String factura;
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
	@Column(name="mensualizado")
	public String mensualizado;
	@Column(name="numero_Cotizacion")
	public String numero_Cotizacion;
	@Column(name="fecha_creacion_cotizacion")
	public String fecha_creacion_cotizacion;
	@Column(name="fc_creacion_jul")
	public String fc_creacion_jul;
	@Column(name="num_dias")
	public String num_dias;
	
    
	
	
	public String getFc_creacion_jul() {
		return fc_creacion_jul;
	}
	public void setFc_creacion_jul(String fc_creacion_jul) {
		this.fc_creacion_jul = fc_creacion_jul;
	}
	public String getNum_dias() {
		return num_dias;
	}
	public void setNum_dias(String num_dias) {
		this.num_dias = num_dias;
	}
	public String getCodid() {
		return codid;
	}
	public void setCodid(String codid) {
		this.codid = codid;
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
	public String getGrupo_contratante() {
		return grupo_contratante;
	}
	public void setGrupo_contratante(String grupo_contratante) {
		this.grupo_contratante = grupo_contratante;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getComision_broker() {
		return comision_broker;
	}
	public void setComision_broker(String comision_broker) {
		this.comision_broker = comision_broker;
	}
	public String getPor_comision_broker() {
		return por_comision_broker;
	}
	public void setPor_comision_broker(String por_comision_broker) {
		this.por_comision_broker = por_comision_broker;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
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
	
	public String getMensualizado() {
		return mensualizado;
	}
	public void setMensualizado(String mensualizado) {
		this.mensualizado = mensualizado;
	}
	public String getNumero_Cotizacion() {
		return numero_Cotizacion;
	}
	public void setNumero_Cotizacion(String numero_Cotizacion) {
		this.numero_Cotizacion = numero_Cotizacion;
	}
	public String getFecha_creacion_cotizacion() {
		return fecha_creacion_cotizacion;
	}
	public void setFecha_creacion_cotizacion(String fecha_creacion_cotizacion) {
		this.fecha_creacion_cotizacion = fecha_creacion_cotizacion;
	}
	
	   

}
