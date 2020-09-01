package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produccion_pendiente_pago_view")
public class ProduccionPendientePagoView {
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
	@Column(name="FECHA_EMISION")
	public String fechaEmision;
	@Column(name="PRIMA_NETA")
	public String primaNeta;
	@Column(name="VIGENCIA_DESDE")
	public String vigenciaDesde;
	@Column(name="VIGENCIA_HASTA")
	public String vigenciaHasta;
	@Column(name="EJECUTIVO")
	public String ejecutivo;
	@Column(name="TIPO")
	public String tipo;
	@Column(name="PRIMA_TOTAL")
	public String primaTotal;
	@Column(name="TOTAL_ASEGURADO")
	public String totalAsegurado;
	@Column(name="NUM_RENOVA")
	public String numRenova;
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
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getPrimaNeta() {
		return primaNeta;
	}
	public void setPrimaNeta(String primaNeta) {
		this.primaNeta = primaNeta;
	}
	public String getVigenciaDesde() {
		return vigenciaDesde;
	}
	public void setVigenciaDesde(String vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}
	public String getVigenciaHasta() {
		return vigenciaHasta;
	}
	public void setVigenciaHasta(String vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
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
	public String getPrimaTotal() {
		return primaTotal;
	}
	public void setPrimaTotal(String primaTotal) {
		this.primaTotal = primaTotal;
	}
	public String getTotalAsegurado() {
		return totalAsegurado;
	}
	public void setTotalAsegurado(String totalAsegurado) {
		this.totalAsegurado = totalAsegurado;
	}
	public String getNumRenova() {
		return numRenova;
	}
	public void setNumRenova(String numRenova) {
		this.numRenova = numRenova;
	}
	public String getCodId() {
		return codId;
	}
	public void setCodId(String codId) {
		this.codId = codId;
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
	
}
