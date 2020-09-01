package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reporte_vencimientos_view")
public class ReporteVencimientoView {
	@Id
	@Column(name="codId")
	public String codid;
	@Column(name="canal")
	public String canal;
	@Column(name="cliente")
	public String cliente;
	@Column(name="identificacion_cliente")
	public String identificacion_cliente;
	@Column(name="telefono_cliente")
	public String telefono_cliente;
	@Column(name="ciudad")
	public String ciudad;
	@Column(name="correo")
	public String correo;
	@Column(name="aseguradora")
	public String aseguradora;
	@Column(name="ramo")
	public String ramo;
	@Column(name="grupo_contratante")
	public String grupo_contratante;
	@Column(name="poliza")
	public String poliza;
	@Column(name="pct_com_brk")
	public String pct_com_brk;
	@Column(name="val_com_brk")
	public String val_com_brk;
	@Column(name="factura_aseguradora")
	public String factura_aseguradora;
	@Column(name="fc_emision")
	public String fc_emision;
	@Column(name="prima_neta")
	public String prima_neta;
	@Column(name="fc_ini_vig_date")
	public String fc_ini_vig_date;
	@Column(name="fc_fin_vig_date")
	public String fc_fin_vig_date;
	@Column(name="dias_vigencia")
	public String dias_vigencia;
	@Column(name="ejecutivo")
	public String ejecutivo;
	@Column(name="tipo")
	public String tipo;
	@Column(name="num_renovacion")
	public String num_renovacion;
	@Column(name="tasa_cliente")
	public String tasa_cliente;
	@Column(name="tasa_canal")
	public String tasa_canal;
	@Column(name="tasa_confia")
	public String tasa_confia;
	@Column(name="forma_pago")
	public String forma_pago;
	@Column(name="siniestro")
	public String siniestro;
	@Column(name="perdida_total")
	public String perdida_total;
	@Column(name="extras_objeto")
	public String extras_objeto;
	@Column(name="cuotas_iniciales_pendiente")
	public String cuotas_iniciales_pendiente;
	@Column(name="cuotas_pendiente")
	public String cuotas_pendiente;
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
	@Column(name="mensualizado")
	public String mensualizado;
	@Column(name="fc_hasta_jul")
	public String fc_hasta_jul;
	@Column(name="total_asegurado")
	public String total_asegurado;
	@Column(name="motivo_no_renovacion")
	public String motivo_no_renovacion;
	
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
	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}
	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}
	public String getTelefono_cliente() {
		return telefono_cliente;
	}
	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	public String getFactura_aseguradora() {
		return factura_aseguradora;
	}
	public void setFactura_aseguradora(String factura_aseguradora) {
		this.factura_aseguradora = factura_aseguradora;
	}
	public String getFc_emision() {
		return fc_emision;
	}
	public void setFc_emision(String fc_emision) {
		this.fc_emision = fc_emision;
	}
	public String getPrima_neta() {
		return prima_neta;
	}
	public void setPrima_neta(String prima_neta) {
		this.prima_neta = prima_neta;
	}
	public String getFc_ini_vig_date() {
		return fc_ini_vig_date;
	}
	public void setFc_ini_vig_date(String fc_ini_vig_date) {
		this.fc_ini_vig_date = fc_ini_vig_date;
	}
	public String getFc_fin_vig_date() {
		return fc_fin_vig_date;
	}
	public void setFc_fin_vig_date(String fc_fin_vig_date) {
		this.fc_fin_vig_date = fc_fin_vig_date;
	}
	public String getDias_vigencia() {
		return dias_vigencia;
	}
	public void setDias_vigencia(String dias_vigencia) {
		this.dias_vigencia = dias_vigencia;
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
	public String getNum_renovacion() {
		return num_renovacion;
	}
	public void setNum_renovacion(String num_renovacion) {
		this.num_renovacion = num_renovacion;
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
	public String getTasa_confia() {
		return tasa_confia;
	}
	public void setTasa_confia(String tasa_confia) {
		this.tasa_confia = tasa_confia;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public String getSiniestro() {
		return siniestro;
	}
	public void setSiniestro(String siniestro) {
		this.siniestro = siniestro;
	}
	public String getPerdida_total() {
		return perdida_total;
	}
	public void setPerdida_total(String perdida_total) {
		this.perdida_total = perdida_total;
	}
	public String getExtras_objeto() {
		return extras_objeto;
	}
	public void setExtras_objeto(String extras_objeto) {
		this.extras_objeto = extras_objeto;
	}
	public String getCuotas_iniciales_pendiente() {
		return cuotas_iniciales_pendiente;
	}
	public void setCuotas_iniciales_pendiente(String cuotas_iniciales_pendiente) {
		this.cuotas_iniciales_pendiente = cuotas_iniciales_pendiente;
	}
	public String getCuotas_pendiente() {
		return cuotas_pendiente;
	}
	public void setCuotas_pendiente(String cuotas_pendiente) {
		this.cuotas_pendiente = cuotas_pendiente;
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
	public String getMensualizado() {
		return mensualizado;
	}
	public void setMensualizado(String mensualizado) {
		this.mensualizado = mensualizado;
	}
	public String getFc_hasta_jul() {
		return fc_hasta_jul;
	}
	public void setFc_hasta_jul(String fc_hasta_jul) {
		this.fc_hasta_jul = fc_hasta_jul;
	}
	public String getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(String total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public String getMotivo_no_renovacion() {
		return motivo_no_renovacion;
	}
	public void setMotivo_no_renovacion(String motivo_no_renovacion) {
		this.motivo_no_renovacion = motivo_no_renovacion;
	}
}
