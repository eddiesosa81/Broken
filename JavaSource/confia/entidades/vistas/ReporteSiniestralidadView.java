package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCCION_SINIESTRALIDAD_VIEW")
public class ReporteSiniestralidadView {
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
	
	@Column(name="nm_ramo")
	public String nm_ramo;
	@Column(name="grupocontratante")
	public String grupocontratante;
	@Column(name="canal")
	public String canal;
	@Column(name="valor_asegurado")
	public String valor_asegurado;
	@Column(name="fecha_siniestro")
	public String fecha_siniestro;
	@Column(name="vigencia_desde")
	public String vigencia_desde;
	@Column(name="vigencia_hasta")
	public String vigencia_hasta;
	@Column(name="poliza")
	public String poliza;
	@Column(name="dsc_objeto")
	public String dsc_objeto;
	@Column(name="prima_neta_obj")
	public String prima_neta_obj;
	@Column(name="valor_liquidado")
	public String valor_liquidado;
	@Column(name="siniestralidad")
	public String siniestralidad;
	@Column(name="marca")
	public String marca;
	@Column(name="placa")
	public String placa;
	@Column(name="modelo")
	public String modelo;
	@Column(name="fecha_autorizacion")
	public String fecha_autorizacion;
	@Column(name="estado")
	public String estado;
	@Column(name="val_rasa")
	public String val_rasa;
	@Column(name="val_deducible")
	public String val_deducible;
	@Column(name="valor_siniestro")
	public String valor_siniestro;
	@Column(name="gastos_no_cubiertos")
	public String gastos_no_cubiertos;
	@Column(name="copago_val")
	public String copago_val;
	@Column(name="val_indemnizacion")
	public String val_indemnizacion;
	@Column(name="anio_siniestro")
	public String anio_siniestro;
	
	
	
	public String getAnio_siniestro() {
		return anio_siniestro;
	}
	public void setAnio_siniestro(String anio_siniestro) {
		this.anio_siniestro = anio_siniestro;
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
	public String getVal_indemnizacion() {
		return val_indemnizacion;
	}
	public void setVal_indemnizacion(String val_indemnizacion) {
		this.val_indemnizacion = val_indemnizacion;
	}
	public String getValor_siniestro() {
		return valor_siniestro;
	}
	public void setValor_siniestro(String valor_siniestro) {
		this.valor_siniestro = valor_siniestro;
	}
	public String getVal_deducible() {
		return val_deducible;
	}
	public void setVal_deducible(String val_deducible) {
		this.val_deducible = val_deducible;
	}
	public String getVal_rasa() {
		return val_rasa;
	}
	public void setVal_rasa(String val_rasa) {
		this.val_rasa = val_rasa;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha_autorizacion() {
		return fecha_autorizacion;
	}
	public void setFecha_autorizacion(String fecha_autorizacion) {
		this.fecha_autorizacion = fecha_autorizacion;
	}
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
	public String getNm_ramo() {
		return nm_ramo;
	}
	public void setNm_ramo(String nm_ramo) {
		this.nm_ramo = nm_ramo;
	}
	public String getGrupocontratante() {
		return grupocontratante;
	}
	public void setGrupocontratante(String grupocontratante) {
		this.grupocontratante = grupocontratante;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getValor_asegurado() {
		return valor_asegurado;
	}
	public void setValor_asegurado(String valor_asegurado) {
		this.valor_asegurado = valor_asegurado;
	}
	public String getFecha_siniestro() {
		return fecha_siniestro;
	}
	public void setFecha_siniestro(String fecha_siniestro) {
		this.fecha_siniestro = fecha_siniestro;
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
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getDsc_objeto() {
		return dsc_objeto;
	}
	public void setDsc_objeto(String dsc_objeto) {
		this.dsc_objeto = dsc_objeto;
	}
	public String getPrima_neta_obj() {
		return prima_neta_obj;
	}
	public void setPrima_neta_obj(String prima_neta_obj) {
		this.prima_neta_obj = prima_neta_obj;
	}
	public String getValor_liquidado() {
		return valor_liquidado;
	}
	public void setValor_liquidado(String valor_liquidado) {
		this.valor_liquidado = valor_liquidado;
	}
	public String getSiniestralidad() {
		return siniestralidad;
	}
	public void setSiniestralidad(String siniestralidad) {
		this.siniestralidad = siniestralidad;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
}
