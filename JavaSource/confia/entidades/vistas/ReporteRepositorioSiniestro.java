package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produccion_siniestros_view")
public class ReporteRepositorioSiniestro {
	
	@Id
	@Column(name="codId")
	public String codid;
	@Column(name="numero_siniestro")
	public String numero_siniestro;
	@Column(name="cd_cliente")
	public String cd_cliente;
	@Column(name="cd_subagente")
	public String cd_subagente;
	@Column(name="cd_aseguradora")
	public String cd_aseguradora;
	@Column(name="cd_ramo")
	public String cd_ramo;
	@Column(name="cd_grupo_contratante")
	public String cd_grupo_contratante;
	@Column(name="cd_ejecutivo")
	public String cd_ejecutivo;
	@Column(name="nm_cliente")
	public String nm_cliente;
	@Column(name="propietario")
	public String propietario;
	@Column(name="dsc_objeto")
	public String dsc_objeto;
	@Column(name="placa")
	public String placa;
	@Column(name="nm_aseguradora")
	public String nm_aseguradora;
	@Column(name="estado")
	public String estado;
	@Column(name="fecha_reporte_siniestro")
	public String fecha_reporte_siniestro;
	@Column(name="fecha_autorizacion")
	public String fecha_autorizacion;
	@Column(name="valor_liquidacion")
	public String valor_liquidacion;
	@Column(name="deducible")
	public String deducible;
	@Column(name="cedula")
	public String cedula;
	@Column(name="grupocontratante")
	public String grupocontratante;
	@Column(name="dias_transcurridos_siniestro")
	public String dias_transcurridos_siniestro;
	@Column(name="CANAL")
	public String canal;
	@Column(name="valor_perdida")
	public String valor_perdida;
	@Column(name="descripcion_subobjeto")
	public String descripcion_subobjeto;
	@Column(name="gastos_no_cubiertos")
	public String gastos_no_cubiertos;
	@Column(name="copago_val")
	public String copago_val;
	@Column(name="desc_taller_diagnostico")
	public String desc_taller_diagnostico;
	@Column(name="nm_ejecutivo")
	public String nm_ejecutivo;
	@Column(name="val_indemnizacion")
	public String val_indemnizacion;
	@Column(name="caso")
	public String caso;
	@Column(name="telefonocliente")
	public String telefonocliente;
	@Column(name="modelo")
	public String modelo;
	
	@Column(name="NM_RAMO")
	public String NM_RAMO;
	@Column(name="FC_VIGENCIAS_DESDE")
	public String FC_VIGENCIAS_DESDE;
	@Column(name="FC_VIGENCIAS_HASTA")
	public String FC_VIGENCIAS_HASTA;
	@Column(name="PRIMA_NETA")
	public String PRIMA_NETA;
	@Column(name="fecha_ocurrencia")
	public String fecha_ocurrencia;
	public String getCodid() {
		return codid;
	}
	public void setCodid(String codid) {
		this.codid = codid;
	}
	public String getNumero_siniestro() {
		return numero_siniestro;
	}
	public void setNumero_siniestro(String numero_siniestro) {
		this.numero_siniestro = numero_siniestro;
	}
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public String getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(String cd_subagente) {
		this.cd_subagente = cd_subagente;
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
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public String getCd_ejecutivo() {
		return cd_ejecutivo;
	}
	public void setCd_ejecutivo(String cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}
	public String getNm_cliente() {
		return nm_cliente;
	}
	public void setNm_cliente(String nm_cliente) {
		this.nm_cliente = nm_cliente;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public String getDsc_objeto() {
		return dsc_objeto;
	}
	public void setDsc_objeto(String dsc_objeto) {
		this.dsc_objeto = dsc_objeto;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNm_aseguradora() {
		return nm_aseguradora;
	}
	public void setNm_aseguradora(String nm_aseguradora) {
		this.nm_aseguradora = nm_aseguradora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha_reporte_siniestro() {
		return fecha_reporte_siniestro;
	}
	public void setFecha_reporte_siniestro(String fecha_reporte_siniestro) {
		this.fecha_reporte_siniestro = fecha_reporte_siniestro;
	}
	public String getFecha_autorizacion() {
		return fecha_autorizacion;
	}
	public void setFecha_autorizacion(String fecha_autorizacion) {
		this.fecha_autorizacion = fecha_autorizacion;
	}
	public String getValor_liquidacion() {
		return valor_liquidacion;
	}
	public void setValor_liquidacion(String valor_liquidacion) {
		this.valor_liquidacion = valor_liquidacion;
	}
	public String getDeducible() {
		return deducible;
	}
	public void setDeducible(String deducible) {
		this.deducible = deducible;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getGrupocontratante() {
		return grupocontratante;
	}
	public void setGrupocontratante(String grupocontratante) {
		this.grupocontratante = grupocontratante;
	}
	public String getDias_transcurridos_siniestro() {
		return dias_transcurridos_siniestro;
	}
	public void setDias_transcurridos_siniestro(String dias_transcurridos_siniestro) {
		this.dias_transcurridos_siniestro = dias_transcurridos_siniestro;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getValor_perdida() {
		return valor_perdida;
	}
	public void setValor_perdida(String valor_perdida) {
		this.valor_perdida = valor_perdida;
	}
	public String getDescripcion_subobjeto() {
		return descripcion_subobjeto;
	}
	public void setDescripcion_subobjeto(String descripcion_subobjeto) {
		this.descripcion_subobjeto = descripcion_subobjeto;
	}
	public String getGastos_no_cubiertos() {
		return gastos_no_cubiertos;
	}
	public void setGastos_no_cubiertos(String gastos_no_cubiertos) {
		this.gastos_no_cubiertos = gastos_no_cubiertos;
	}
	public String getCopago_val() {
		return copago_val;
	}
	public void setCopago_val(String copago_val) {
		this.copago_val = copago_val;
	}
	public String getDesc_taller_diagnostico() {
		return desc_taller_diagnostico;
	}
	public void setDesc_taller_diagnostico(String desc_taller_diagnostico) {
		this.desc_taller_diagnostico = desc_taller_diagnostico;
	}
	public String getNm_ejecutivo() {
		return nm_ejecutivo;
	}
	public void setNm_ejecutivo(String nm_ejecutivo) {
		this.nm_ejecutivo = nm_ejecutivo;
	}
	public String getVal_indemnizacion() {
		return val_indemnizacion;
	}
	public void setVal_indemnizacion(String val_indemnizacion) {
		this.val_indemnizacion = val_indemnizacion;
	}
	public String getCaso() {
		return caso;
	}
	public void setCaso(String caso) {
		this.caso = caso;
	}
	public String getTelefonocliente() {
		return telefonocliente;
	}
	public void setTelefonocliente(String telefonocliente) {
		this.telefonocliente = telefonocliente;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNM_RAMO() {
		return NM_RAMO;
	}
	public void setNM_RAMO(String nM_RAMO) {
		NM_RAMO = nM_RAMO;
	}
	public String getFC_VIGENCIAS_DESDE() {
		return FC_VIGENCIAS_DESDE;
	}
	public void setFC_VIGENCIAS_DESDE(String fC_VIGENCIAS_DESDE) {
		FC_VIGENCIAS_DESDE = fC_VIGENCIAS_DESDE;
	}
	public String getFC_VIGENCIAS_HASTA() {
		return FC_VIGENCIAS_HASTA;
	}
	public void setFC_VIGENCIAS_HASTA(String fC_VIGENCIAS_HASTA) {
		FC_VIGENCIAS_HASTA = fC_VIGENCIAS_HASTA;
	}
	public String getPRIMA_NETA() {
		return PRIMA_NETA;
	}
	public void setPRIMA_NETA(String pRIMA_NETA) {
		PRIMA_NETA = pRIMA_NETA;
	}
	public String getFecha_ocurrencia() {
		return fecha_ocurrencia;
	}
	public void setFecha_ocurrencia(String fecha_ocurrencia) {
		this.fecha_ocurrencia = fecha_ocurrencia;
	}
	
	
}
