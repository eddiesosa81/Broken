package confia.entidades.basicos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuotasxcobrar_view")
public class CuotasXCobrar {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name = "cd_compania")
	private Integer cd_compania;
	@Column(name = "cd_cliente")
	private Integer cd_cliente;
	@Column(name = "cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name = "cd_rubro")
	private Integer cd_rubro;
	@Column(name = "fc_ini_cot_date")
	private String fc_ini_cot_date;
	@Column(name = "fc_fin_cot_date")
	private String fc_fin_cot_date;
	@Column(name = "fact_periodica_cot")
	private String fact_periodica_cot;
	@Column(name = "num_renovaciones_cot")
	private String num_renovaciones_cot;
	@Column(name = "fc_creacion")
	private String fc_creacion;
	@Column(name = "num_cotizacion")
	private String num_cotizacion;
	@Column(name = "nombre_compania")
	private String nombre_compania;
	@Column(name = "identificacion_cliente")
	private String identificacion_cliente;
	@Column(name = "razon_social_cliente")
	private String razon_social_cliente;
	@Column(name = "razon_social_aseguradora")
	private String razon_social_aseguradora;
	@Column(name = "desc_rubro")
	private String desc_rubro;
	@Column(name = "usrid")
	private String usrid;
	@Column(name = "usrlogin")
	private String usrlogin;
	@Column(name = "num_pagos_formapago")
	private String num_pagos_formapago;
	@Column(name = "total_prima_formapago")
	private Double total_prima_formapago;
	@Column(name = "total_pago_formapago")
	private Double total_pago_formapago;
	@Column(name = "cd_ramo")
	private Integer cd_ramo;
	@Column(name = "cd_ejecutivo")
	private Integer cd_ejecutivo;
	@Column(name = "cd_subagente")
	private Integer cd_subagente;
	@Column(name = "cd_plan")
	private Integer cd_plan;
	@Column(name = "cd_grupo_contratante")
	private Integer cd_grupo_contratante;
	@Column(name = "poliza")
	private String poliza;
	@Column(name = "rc_factura_aseguradora")
	private String factura_aseguradora;
	@Column(name = "total_asegurado")
	private Double total_asegurado;
	@Column(name = "total_prima")
	private Double total_prima;
	@Column(name = "fc_factura_date")
	private String fc_factura_date;
	@Column(name = "fc_renovacion_date")
	private String fc_renovacion_date;
	@Column(name = "fc_emision_date")
	private String fc_emision_date;
	@Column(name = "pct_cuota_inicial_formapago")
	private Integer pct_cuota_inicial_formapago;
	@Column(name = "cd_det_forma_pago")
	private Integer cd_det_forma_pago;
	@Column(name = "cd_forma_pago")
	private Integer cd_forma_pago;
	@Column(name = "fecha_vencimiento_date")
	private Date fecha_vencimiento_date;
	@Column(name = "valor")
	private Double valor;
	@Column(name = "saldo")
	private Double saldo;
	@Column(name = "numero_pago")
	private String ncuotas;
	@Column(name = "fecha_vencimiento_Jul")
	private Integer fecha_vencimiento_Jul;
	@Column(name = "facturacion_periodica")
	private String facturacion_periodica;
	@Column(name = "fc_inicio_vigencia")
	private Date fc_inicio_vigencia;
	@Column(name = "fc_fin_vigencia")
	private Date fc_fin_vigencia;
	@Column(name = "dfp_factura_aseguradora")
	private String dfp_factura_aseguradora;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}

	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}

	public Integer getCd_compania() {
		return cd_compania;
	}

	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}

	public Integer getCd_cliente() {
		return cd_cliente;
	}

	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}

	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}

	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}

	public Integer getCd_rubro() {
		return cd_rubro;
	}

	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}

	public String getFc_ini_cot_date() {
		return fc_ini_cot_date;
	}

	public void setFc_ini_cot_date(String fc_ini_cot_date) {
		this.fc_ini_cot_date = fc_ini_cot_date;
	}

	public String getFc_fin_cot_date() {
		return fc_fin_cot_date;
	}

	public void setFc_fin_cot_date(String fc_fin_cot_date) {
		this.fc_fin_cot_date = fc_fin_cot_date;
	}

	public String getFact_periodica_cot() {
		return fact_periodica_cot;
	}

	public void setFact_periodica_cot(String fact_periodica_cot) {
		this.fact_periodica_cot = fact_periodica_cot;
	}

	public String getNum_renovaciones_cot() {
		return num_renovaciones_cot;
	}

	public void setNum_renovaciones_cot(String num_renovaciones_cot) {
		this.num_renovaciones_cot = num_renovaciones_cot;
	}

	public String getFc_creacion() {
		return fc_creacion;
	}

	public void setFc_creacion(String fc_creacion) {
		this.fc_creacion = fc_creacion;
	}

	public String getNum_cotizacion() {
		return num_cotizacion;
	}

	public void setNum_cotizacion(String num_cotizacion) {
		this.num_cotizacion = num_cotizacion;
	}

	public String getNombre_compania() {
		return nombre_compania;
	}

	public void setNombre_compania(String nombre_compania) {
		this.nombre_compania = nombre_compania;
	}

	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}

	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}

	public String getRazon_social_cliente() {
		return razon_social_cliente;
	}

	public void setRazon_social_cliente(String razon_social_cliente) {
		this.razon_social_cliente = razon_social_cliente;
	}

	public String getRazon_social_aseguradora() {
		return razon_social_aseguradora;
	}

	public void setRazon_social_aseguradora(String razon_social_aseguradora) {
		this.razon_social_aseguradora = razon_social_aseguradora;
	}

	public String getDesc_rubro() {
		return desc_rubro;
	}

	public void setDesc_rubro(String desc_rubro) {
		this.desc_rubro = desc_rubro;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getUsrlogin() {
		return usrlogin;
	}

	public void setUsrlogin(String usrlogin) {
		this.usrlogin = usrlogin;
	}

	public String getNum_pagos_formapago() {
		return num_pagos_formapago;
	}

	public void setNum_pagos_formapago(String num_pagos_formapago) {
		this.num_pagos_formapago = num_pagos_formapago;
	}

	public Double getTotal_prima_formapago() {
		return total_prima_formapago;
	}

	public void setTotal_prima_formapago(Double total_prima_formapago) {
		this.total_prima_formapago = total_prima_formapago;
	}

	public Double getTotal_pago_formapago() {
		return total_pago_formapago;
	}

	public void setTotal_pago_formapago(Double total_pago_formapago) {
		this.total_pago_formapago = total_pago_formapago;
	}

	public Integer getCd_ramo() {
		return cd_ramo;
	}

	public void setCd_ramo(Integer cd_ramo) {
		this.cd_ramo = cd_ramo;
	}

	public Integer getCd_ejecutivo() {
		return cd_ejecutivo;
	}

	public void setCd_ejecutivo(Integer cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}

	public Integer getCd_subagente() {
		return cd_subagente;
	}

	public void setCd_subagente(Integer cd_subagente) {
		this.cd_subagente = cd_subagente;
	}

	public Integer getCd_plan() {
		return cd_plan;
	}

	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}

	public Integer getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}

	public void setCd_grupo_contratante(Integer cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public String getFactura_aseguradora() {
		return factura_aseguradora;
	}

	public void setFactura_aseguradora(String factura_aseguradora) {
		this.factura_aseguradora = factura_aseguradora;
	}

	public Double getTotal_asegurado() {
		return total_asegurado;
	}

	public void setTotal_asegurado(Double total_asegurado) {
		this.total_asegurado = total_asegurado;
	}

	public Double getTotal_prima() {
		return total_prima;
	}

	public void setTotal_prima(Double total_prima) {
		this.total_prima = total_prima;
	}

	public String getFc_factura_date() {
		return fc_factura_date;
	}

	public void setFc_factura_date(String fc_factura_date) {
		this.fc_factura_date = fc_factura_date;
	}

	public String getFc_renovacion_date() {
		return fc_renovacion_date;
	}

	public void setFc_renovacion_date(String fc_renovacion_date) {
		this.fc_renovacion_date = fc_renovacion_date;
	}

	public String getFc_emision_date() {
		return fc_emision_date;
	}

	public void setFc_emision_date(String fc_emision_date) {
		this.fc_emision_date = fc_emision_date;
	}

	public Integer getPct_cuota_inicial_formapago() {
		return pct_cuota_inicial_formapago;
	}

	public void setPct_cuota_inicial_formapago(Integer pct_cuota_inicial_formapago) {
		this.pct_cuota_inicial_formapago = pct_cuota_inicial_formapago;
	}

	public Integer getCd_det_forma_pago() {
		return cd_det_forma_pago;
	}

	public void setCd_det_forma_pago(Integer cd_det_forma_pago) {
		this.cd_det_forma_pago = cd_det_forma_pago;
	}

	public Integer getCd_forma_pago() {
		return cd_forma_pago;
	}

	public void setCd_forma_pago(Integer cd_forma_pago) {
		this.cd_forma_pago = cd_forma_pago;
	}

	public Date getFecha_vencimiento_date() {
		return fecha_vencimiento_date;
	}

	public void setFecha_vencimiento_date(Date fecha_vencimiento_date) {
		this.fecha_vencimiento_date = fecha_vencimiento_date;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getNcuotas() {
		return ncuotas;
	}

	public void setNcuotas(String ncuotas) {
		this.ncuotas = ncuotas;
	}

	public Integer getFecha_vencimiento_Jul() {
		return fecha_vencimiento_Jul;
	}

	public void setFecha_vencimiento_Jul(Integer fecha_vencimiento_Jul) {
		this.fecha_vencimiento_Jul = fecha_vencimiento_Jul;
	}

	public String getFacturacion_periodica() {
		return facturacion_periodica;
	}

	public void setFacturacion_periodica(String facturacion_periodica) {
		this.facturacion_periodica = facturacion_periodica;
	}

	public Date getFc_inicio_vigencia() {
		return fc_inicio_vigencia;
	}

	public void setFc_inicio_vigencia(Date fc_inicio_vigencia) {
		this.fc_inicio_vigencia = fc_inicio_vigencia;
	}

	public Date getFc_fin_vigencia() {
		return fc_fin_vigencia;
	}

	public void setFc_fin_vigencia(Date fc_fin_vigencia) {
		this.fc_fin_vigencia = fc_fin_vigencia;
	}

	public String getDfp_factura_aseguradora() {
		return dfp_factura_aseguradora;
	}

	public void setDfp_factura_aseguradora(String dfp_factura_aseguradora) {
		this.dfp_factura_aseguradora = dfp_factura_aseguradora;
	}

}
