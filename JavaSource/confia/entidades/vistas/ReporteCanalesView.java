package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reporte_canal_view")
public class ReporteCanalesView {
	@Id
	@Column(name="codid")
	private String codid;
	@Column(name="cliente")
	private String cliente;
	@Column(name="cedula_ruc")
	private String cedula_ruc;
	@Column(name="factura_aseguradora")
	private String factura_aseguradora;
	@Column(name="ramo")
	private String ramo;
	@Column(name="aseguradora")
	private String aseguradora;
	@Column(name="grupo_contratante")
	private String grupo_contratante;
	@Column(name="poliza")
	private String poliza;
	@Column(name="prima_neta")
	private String prima_neta;
	@Column(name="prima_total")
	private String prima_total;
	@Column(name="fecha_emision")
	private String fecha_emision;
	
	@Column(name="froma_pago")
	private String froma_pago;
	@Column(name="total_asegurado")
	private String total_asegurado;
	@Column(name="correo_electronico")
	private String correo_electronico;
	@Column(name="telefono_cliente")
	private String telefono_cliente;
	@Column(name="ejecutivo")
	private String ejecutivo;
	@Column(name="tipo")
	private String tipo;
	@Column(name="comision_broker")
	private String comision_broker;
	@Column(name="porentaje_comision_broker")
	private String porentaje_comision_broker;
	@Column(name="canal")
	private String canal;
	@Column(name="cedula_canal")
	private String cedula_canal;
	
	@Column(name="ciudad_canal")
	private String ciudad_canal;
	@Column(name="comision_liquidada")
	private String comision_liquidada;
	@Column(name="fecha_pre_liquidacion")
	private String fecha_pre_liquidacion;
	@Column(name="numero_pre_liquidacion")
	private String numero_pre_liquidacion;
	@Column(name="fecha_factura_confia")
	private String fecha_factura_confia;
	@Column(name="numero_factura_confia")
	private String numero_factura_confia;
	@Column(name="pct_comision_canal")
	private String pct_comision_canal;
	@Column(name="valor_comision_canal")
	private String valor_comision_canal;
	@Column(name="fecha_pago_canal")
	private String fecha_pago_canal;
	@Column(name="num_factura_pago_canal")
	private String num_factura_pago_canal;
	
	@Column(name="vigencia_desde_cuota")
	private String vigencia_desde_cuota;
	@Column(name="vigencia_hasta_cuota")
	private String vigencia_hasta_cuota;
	@Column(name="vigencia_desde_general")
	private String vigencia_desde_general;
	@Column(name="vigencia_hasta_general")
	private String vigencia_hasta_general;
	@Column(name="cd_cliente")
	private String cd_cliente;
	@Column(name="cd_grupo_contratante")
	private String cd_grupo_contratante;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="cd_ramo")
	private String cd_ramo;
	@Column(name="cd_subagente")
	private String cd_subagente;
	@Column(name="cd_ejecutivo")
	private String cd_ejecutivo;
	
	@Column(name="fc_desde_jul")
	private String fc_desde_jul;
	@Column(name="fc_hasta_jul")
	private String fc_hasta_jul;
	@Column(name="fc_emision_jul")
	private String fc_emision_jul;
	@Column(name="cd_det_forma_pago")
	private String cd_det_forma_pago;
	@Column(name="cd_comision_poliza")
	private String cd_comision_poliza;
	public String getCodid() {
		return codid;
	}
	public void setCodid(String codid) {
		this.codid = codid;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCedula_ruc() {
		return cedula_ruc;
	}
	public void setCedula_ruc(String cedula_ruc) {
		this.cedula_ruc = cedula_ruc;
	}
	public String getFactura_aseguradora() {
		return factura_aseguradora;
	}
	public void setFactura_aseguradora(String factura_aseguradora) {
		this.factura_aseguradora = factura_aseguradora;
	}
	public String getRamo() {
		return ramo;
	}
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}
	public String getAseguradora() {
		return aseguradora;
	}
	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
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
	public String getPrima_neta() {
		return prima_neta;
	}
	public void setPrima_neta(String prima_neta) {
		this.prima_neta = prima_neta;
	}
	public String getPrima_total() {
		return prima_total;
	}
	public void setPrima_total(String prima_total) {
		this.prima_total = prima_total;
	}
	public String getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public String getFroma_pago() {
		return froma_pago;
	}
	public void setFroma_pago(String froma_pago) {
		this.froma_pago = froma_pago;
	}
	public String getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(String total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getTelefono_cliente() {
		return telefono_cliente;
	}
	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
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
	public String getComision_broker() {
		return comision_broker;
	}
	public void setComision_broker(String comision_broker) {
		this.comision_broker = comision_broker;
	}
	public String getPorentaje_comision_broker() {
		return porentaje_comision_broker;
	}
	public void setPorentaje_comision_broker(String porentaje_comision_broker) {
		this.porentaje_comision_broker = porentaje_comision_broker;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getCedula_canal() {
		return cedula_canal;
	}
	public void setCedula_canal(String cedula_canal) {
		this.cedula_canal = cedula_canal;
	}
	public String getCiudad_canal() {
		return ciudad_canal;
	}
	public void setCiudad_canal(String ciudad_canal) {
		this.ciudad_canal = ciudad_canal;
	}
	public String getComision_liquidada() {
		return comision_liquidada;
	}
	public void setComision_liquidada(String comision_liquidada) {
		this.comision_liquidada = comision_liquidada;
	}
	public String getFecha_pre_liquidacion() {
		return fecha_pre_liquidacion;
	}
	public void setFecha_pre_liquidacion(String fecha_pre_liquidacion) {
		this.fecha_pre_liquidacion = fecha_pre_liquidacion;
	}
	public String getNumero_pre_liquidacion() {
		return numero_pre_liquidacion;
	}
	public void setNumero_pre_liquidacion(String numero_pre_liquidacion) {
		this.numero_pre_liquidacion = numero_pre_liquidacion;
	}
	public String getFecha_factura_confia() {
		return fecha_factura_confia;
	}
	public void setFecha_factura_confia(String fecha_factura_confia) {
		this.fecha_factura_confia = fecha_factura_confia;
	}
	public String getNumero_factura_confia() {
		return numero_factura_confia;
	}
	public void setNumero_factura_confia(String numero_factura_confia) {
		this.numero_factura_confia = numero_factura_confia;
	}
	public String getPct_comision_canal() {
		return pct_comision_canal;
	}
	public void setPct_comision_canal(String pct_comision_canal) {
		this.pct_comision_canal = pct_comision_canal;
	}
	public String getValor_comision_canal() {
		return valor_comision_canal;
	}
	public void setValor_comision_canal(String valor_comision_canal) {
		this.valor_comision_canal = valor_comision_canal;
	}
	public String getFecha_pago_canal() {
		return fecha_pago_canal;
	}
	public void setFecha_pago_canal(String fecha_pago_canal) {
		this.fecha_pago_canal = fecha_pago_canal;
	}
	public String getNum_factura_pago_canal() {
		return num_factura_pago_canal;
	}
	public void setNum_factura_pago_canal(String num_factura_pago_canal) {
		this.num_factura_pago_canal = num_factura_pago_canal;
	}
	public String getVigencia_desde_cuota() {
		return vigencia_desde_cuota;
	}
	public void setVigencia_desde_cuota(String vigencia_desde_cuota) {
		this.vigencia_desde_cuota = vigencia_desde_cuota;
	}
	public String getVigencia_hasta_cuota() {
		return vigencia_hasta_cuota;
	}
	public void setVigencia_hasta_cuota(String vigencia_hasta_cuota) {
		this.vigencia_hasta_cuota = vigencia_hasta_cuota;
	}
	public String getVigencia_desde_general() {
		return vigencia_desde_general;
	}
	public void setVigencia_desde_general(String vigencia_desde_general) {
		this.vigencia_desde_general = vigencia_desde_general;
	}
	public String getVigencia_hasta_general() {
		return vigencia_hasta_general;
	}
	public void setVigencia_hasta_general(String vigencia_hasta_general) {
		this.vigencia_hasta_general = vigencia_hasta_general;
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

	   
	
}
