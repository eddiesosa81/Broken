package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prod_emitida_mensualizado_view")
public class ProduccionEmitidaMensualizadoView {
	@Id
	@Column(name="codId")
	public String codId;
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
	@Column(name="ejecutivo")
	public String ejecutivo;
	@Column(name="tipo")
	public String tipo;
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
	@Column(name="fc_desde")
	public String fc_desde;
	@Column(name="fc_hasta")
	public String fc_hasta;
	@Column(name="fc_emision")
	public String fc_emision;
	@Column(name="cd_rubro")
	public String cd_rubro;
	@Column(name="cd_ramo_cotizacion")
	public String cd_ramo_cotizacion;
	@Column(name="fact_periodica_cot")
	public String fact_periodica_cot;
	@Column(name="factura_aseguradora")
	public String factura_aseguradora;
	@Column(name="fecha_vencimiento_date")
	public String fecha_vencimiento_date;
	@Column(name="cuota")
	public String cuota;
	@Column(name="valor_asegurado_mensual")
	public String valor_asegurado_mensual;
	@Column(name="prima_neta_mensual")
	public String prima_neta_mensual;
	@Column(name="super_bancos_mensual")
	public String super_bancos_mensual;
	@Column(name="seguro_campesino_mensual")
	public String seguro_campesino_mensual;
	@Column(name="derechos_emision_mensual")
	public String derechos_emision_mensual;
	@Column(name="subtotal_mensual")
	public String subtotal_mensual;
	@Column(name="iva_mensual")
	public String iva_mensual;
	@Column(name="prima_total_costo_real_mensual")
	public String prima_total_costo_real_mensual;
	@Column(name="cuota_fija_nivelacion")
	public String cuota_fija_nivelacion;
	@Column(name="diferencia_nivelacion")
	public String diferencia_nivelacion;
	@Column(name="nivelacion_acumulada")
	public String nivelacion_acumulada;
	@Column(name="prima_neta_registro")
	public String prima_neta_registro;
	@Column(name="super_bancos_registro")
	public String super_bancos_registro;
	@Column(name="seguro_campesino_registro")
	public String seguro_campesino_registro;
	@Column(name="derecho_emision_registro")
	public String derecho_emision_registro;
	@Column(name="subtotal_registro")
	public String subtotal_registro;
	@Column(name="iva_registro")
	public String iva_registro;
	@Column(name="total_registro")
	public String total_registro;
	@Column(name="modelo")
	public String modelo;
	@Column(name="marca")
	public String marca;
	@Column(name="no_de_chasis")
	public String no_de_chasis;
	@Column(name="no_de_motor")
	public String no_de_motor;
	@Column(name="color")
	public String color;
	@Column(name="anio_de_fabricacion")
	public String anio_de_fabricacion;
	@Column(name="ranv_cpn")
	public String ranv_cpn;
	@Column(name="placa")
	public String placa;
	@Column(name="cedula_asegurado")
	public String cedula_asegurado;
	@Column(name="telefono_asegurado")
	public String telefono_asegurado;
	@Column(name="tasa_objeto")
	public String tasa_objeto;
	@Column(name="direccion_asegurado")
	public String direccion_asegurado;
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
	public String getFc_desde() {
		return fc_desde;
	}
	public void setFc_desde(String fc_desde) {
		this.fc_desde = fc_desde;
	}
	public String getFc_hasta() {
		return fc_hasta;
	}
	public void setFc_hasta(String fc_hasta) {
		this.fc_hasta = fc_hasta;
	}
	public String getFc_emision() {
		return fc_emision;
	}
	public void setFc_emision(String fc_emision) {
		this.fc_emision = fc_emision;
	}
	public String getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(String cd_rubro) {
		this.cd_rubro = cd_rubro;
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
	public String getFactura_aseguradora() {
		return factura_aseguradora;
	}
	public void setFactura_aseguradora(String factura_aseguradora) {
		this.factura_aseguradora = factura_aseguradora;
	}
	public String getFecha_vencimiento_date() {
		return fecha_vencimiento_date;
	}
	public void setFecha_vencimiento_date(String fecha_vencimiento_date) {
		this.fecha_vencimiento_date = fecha_vencimiento_date;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	public String getValor_asegurado_mensual() {
		return valor_asegurado_mensual;
	}
	public void setValor_asegurado_mensual(String valor_asegurado_mensual) {
		this.valor_asegurado_mensual = valor_asegurado_mensual;
	}
	public String getPrima_neta_mensual() {
		return prima_neta_mensual;
	}
	public void setPrima_neta_mensual(String prima_neta_mensual) {
		this.prima_neta_mensual = prima_neta_mensual;
	}
	public String getSuper_bancos_mensual() {
		return super_bancos_mensual;
	}
	public void setSuper_bancos_mensual(String super_bancos_mensual) {
		this.super_bancos_mensual = super_bancos_mensual;
	}
	public String getSeguro_campesino_mensual() {
		return seguro_campesino_mensual;
	}
	public void setSeguro_campesino_mensual(String seguro_campesino_mensual) {
		this.seguro_campesino_mensual = seguro_campesino_mensual;
	}
	public String getDerechos_emision_mensual() {
		return derechos_emision_mensual;
	}
	public void setDerechos_emision_mensual(String derechos_emision_mensual) {
		this.derechos_emision_mensual = derechos_emision_mensual;
	}
	public String getSubtotal_mensual() {
		return subtotal_mensual;
	}
	public void setSubtotal_mensual(String subtotal_mensual) {
		this.subtotal_mensual = subtotal_mensual;
	}
	public String getIva_mensual() {
		return iva_mensual;
	}
	public void setIva_mensual(String iva_mensual) {
		this.iva_mensual = iva_mensual;
	}
	public String getPrima_total_costo_real_mensual() {
		return prima_total_costo_real_mensual;
	}
	public void setPrima_total_costo_real_mensual(String prima_total_costo_real_mensual) {
		this.prima_total_costo_real_mensual = prima_total_costo_real_mensual;
	}
	public String getCuota_fija_nivelacion() {
		return cuota_fija_nivelacion;
	}
	public void setCuota_fija_nivelacion(String cuota_fija_nivelacion) {
		this.cuota_fija_nivelacion = cuota_fija_nivelacion;
	}
	public String getDiferencia_nivelacion() {
		return diferencia_nivelacion;
	}
	public void setDiferencia_nivelacion(String diferencia_nivelacion) {
		this.diferencia_nivelacion = diferencia_nivelacion;
	}
	public String getNivelacion_acumulada() {
		return nivelacion_acumulada;
	}
	public void setNivelacion_acumulada(String nivelacion_acumulada) {
		this.nivelacion_acumulada = nivelacion_acumulada;
	}
	public String getPrima_neta_registro() {
		return prima_neta_registro;
	}
	public void setPrima_neta_registro(String prima_neta_registro) {
		this.prima_neta_registro = prima_neta_registro;
	}
	public String getSuper_bancos_registro() {
		return super_bancos_registro;
	}
	public void setSuper_bancos_registro(String super_bancos_registro) {
		this.super_bancos_registro = super_bancos_registro;
	}
	public String getSeguro_campesino_registro() {
		return seguro_campesino_registro;
	}
	public void setSeguro_campesino_registro(String seguro_campesino_registro) {
		this.seguro_campesino_registro = seguro_campesino_registro;
	}
	public String getDerecho_emision_registro() {
		return derecho_emision_registro;
	}
	public void setDerecho_emision_registro(String derecho_emision_registro) {
		this.derecho_emision_registro = derecho_emision_registro;
	}
	public String getSubtotal_registro() {
		return subtotal_registro;
	}
	public void setSubtotal_registro(String subtotal_registro) {
		this.subtotal_registro = subtotal_registro;
	}
	public String getIva_registro() {
		return iva_registro;
	}
	public void setIva_registro(String iva_registro) {
		this.iva_registro = iva_registro;
	}
	public String getTotal_registro() {
		return total_registro;
	}
	public void setTotal_registro(String total_registro) {
		this.total_registro = total_registro;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getNo_de_chasis() {
		return no_de_chasis;
	}
	public void setNo_de_chasis(String no_de_chasis) {
		this.no_de_chasis = no_de_chasis;
	}
	public String getNo_de_motor() {
		return no_de_motor;
	}
	public void setNo_de_motor(String no_de_motor) {
		this.no_de_motor = no_de_motor;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAnio_de_fabricacion() {
		return anio_de_fabricacion;
	}
	public void setAnio_de_fabricacion(String anio_de_fabricacion) {
		this.anio_de_fabricacion = anio_de_fabricacion;
	}
	public String getRanv_cpn() {
		return ranv_cpn;
	}
	public void setRanv_cpn(String ranv_cpn) {
		this.ranv_cpn = ranv_cpn;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCedula_asegurado() {
		return cedula_asegurado;
	}
	public void setCedula_asegurado(String cedula_asegurado) {
		this.cedula_asegurado = cedula_asegurado;
	}
	public String getTelefono_asegurado() {
		return telefono_asegurado;
	}
	public void setTelefono_asegurado(String telefono_asegurado) {
		this.telefono_asegurado = telefono_asegurado;
	}
	public String getTasa_objeto() {
		return tasa_objeto;
	}
	public void setTasa_objeto(String tasa_objeto) {
		this.tasa_objeto = tasa_objeto;
	}
	public String getDireccion_asegurado() {
		return direccion_asegurado;
	}
	public void setDireccion_asegurado(String direccion_asegurado) {
		this.direccion_asegurado = direccion_asegurado;
	}
	
	

}
