package confia.entidades.vistas;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENDOSOS_PROVISIONALES_VIEW")
public class EndososProvisionalesView  {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name="num_cotizacion")
	private String num_cotizacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="identificacion_cliente")
	private String  identificacion_cliente;
	@Column(name="primer_nombre_cliente")
	private String  primer_nombre_cliente;
	@Column(name="segundo_nombre_cliente")
	private String  segundo_nombre_cliente;
	@Column(name="primer_apellido_cliente")
	private String  primer_apellido_cliente;
	@Column(name="segundo_apellido_cliente")
	private String  segundo_apellido_cliente;
	@Column(name="razon_social_cliente")
	private String  razon_social_cliente;
	@Column(name="cd_aseguradora")
	private Integer  cd_aseguradora;
	@Column(name="nombre_corto_aseguradora")
	private String  nombre_corto_aseguradora;
	@Column(name="cd_rubro")
	private Integer  cd_rubro;
	@Column(name="desc_rubro")
	private String  desc_rubro;
	@Column(name="fact_periodica_cot")
	private String  fact_periodica_cot;
	@Column(name="num_renovaciones_cot")
	private String  num_renovaciones_cot;
	@Column(name="fc_creacion")
	private String  fc_creacion;
	@Column(name="cd_cliente_asegurado")
	private Integer  cd_cliente_asegurado;
	@Column(name="DATOSCLIENTE")
	private String  datosCliente;
	@Column(name="CD_RAMO")
	private Integer  cd_ramo;
	@Column(name="DESC_RAMO")
	private String  desc_ramo;
	@Column(name="CD_RAMO_COTIZACION")
	private Integer  cd_ramo_cotizacion;
	@Column(name="total_asegurado")
	private Double  total_asegurado;
	@Column(name="total_prima")
	private Double  total_prima;
	@Column(name="cd_plan")
	private Integer  cd_plan;
	@Column(name="descripcion_plan")
	private String  descripcion_plan;
	@Column(name="fc_ini_vig")
	private Date  fc_ini_vig;
	@Column(name="fc_fin_vig")
	private Date  fc_fin_vig;
	@Column(name="FC_INI_VIG_JUL")
	private Integer  FC_INI_VIG_JUL;
	@Column(name="FC_FIN_VIG_JUL")
	private Integer  FC_FIN_VIG_JUL;
	@Column(name="DIAS_VIGENCIA")
	private Integer  DIAS_VIGENCIA;
	@Column(name="poliza")
	private String  poliza;
	@Column(name="factura_aseguradora")
	private String factura;
	@Column(name="anexo")
	private String anexo;
	@Column(name="fc_emision_aseguradora")
	private String fc_emision_aseguradora;
	@Column(name="fc_emision_aseguradora_date")
	private Date fc_emision_aseguradora_date;
	@Column(name="numero_asistencia")
	private String numero_asistencia;
	@Column(name="cd_grupo_contratante")
	private String cd_grupo_contratante;
	@Column(name="nm_grupo_contratante")
	private String nm_grupo_contratante;
	@Column(name="cd_subagente")
	private String cd_subagente;
	@Column(name="nm_subagente")
	private String nm_subagente;
	@Column(name="cd_ejecutivo")
	private String cd_ejecutivo;
	
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public String getNum_cotizacion() {
		return num_cotizacion;
	}
	public void setNum_cotizacion(String num_cotizacion) {
		this.num_cotizacion = num_cotizacion;
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
	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}
	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}
	public String getPrimer_nombre_cliente() {
		return primer_nombre_cliente;
	}
	public void setPrimer_nombre_cliente(String primer_nombre_cliente) {
		this.primer_nombre_cliente = primer_nombre_cliente;
	}
	public String getSegundo_nombre_cliente() {
		return segundo_nombre_cliente;
	}
	public void setSegundo_nombre_cliente(String segundo_nombre_cliente) {
		this.segundo_nombre_cliente = segundo_nombre_cliente;
	}
	public String getPrimer_apellido_cliente() {
		return primer_apellido_cliente;
	}
	public void setPrimer_apellido_cliente(String primer_apellido_cliente) {
		this.primer_apellido_cliente = primer_apellido_cliente;
	}
	public String getSegundo_apellido_cliente() {
		return segundo_apellido_cliente;
	}
	public void setSegundo_apellido_cliente(String segundo_apellido_cliente) {
		this.segundo_apellido_cliente = segundo_apellido_cliente;
	}
	public String getRazon_social_cliente() {
		return razon_social_cliente;
	}
	public void setRazon_social_cliente(String razon_social_cliente) {
		this.razon_social_cliente = razon_social_cliente;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}
	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public String getDesc_rubro() {
		return desc_rubro;
	}
	public void setDesc_rubro(String desc_rubro) {
		this.desc_rubro = desc_rubro;
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
	public Integer getCd_cliente_asegurado() {
		return cd_cliente_asegurado;
	}
	public void setCd_cliente_asegurado(Integer cd_cliente_asegurado) {
		this.cd_cliente_asegurado = cd_cliente_asegurado;
	}
	public String getDatosCliente() {
		return datosCliente;
	}
	public void setDatosCliente(String datosCliente) {
		this.datosCliente = datosCliente;
	}
	public Integer getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(Integer cd_ramo) {
		this.cd_ramo = cd_ramo;
	}

	public String getDesc_ramo() {
		return desc_ramo;
	}
	public void setDesc_ramo(String desc_ramo) {
		this.desc_ramo = desc_ramo;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
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
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public String getDescripcion_plan() {
		return descripcion_plan;
	}
	public void setDescripcion_plan(String descripcion_plan) {
		this.descripcion_plan = descripcion_plan;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFc_ini_vig() {
		return fc_ini_vig;
	}
	public void setFc_ini_vig(Date fc_ini_vig) {
		this.fc_ini_vig = fc_ini_vig;
	}
	public Date getFc_fin_vig() {
		return fc_fin_vig;
	}
	public void setFc_fin_vig(Date fc_fin_vig) {
		this.fc_fin_vig = fc_fin_vig;
	}
	public Integer getFC_INI_VIG_JUL() {
		return FC_INI_VIG_JUL;
	}
	public void setFC_INI_VIG_JUL(Integer fC_INI_VIG_JUL) {
		FC_INI_VIG_JUL = fC_INI_VIG_JUL;
	}
	public Integer getFC_FIN_VIG_JUL() {
		return FC_FIN_VIG_JUL;
	}
	public void setFC_FIN_VIG_JUL(Integer fC_FIN_VIG_JUL) {
		FC_FIN_VIG_JUL = fC_FIN_VIG_JUL;
	}
	public Integer getDIAS_VIGENCIA() {
		return DIAS_VIGENCIA;
	}
	public void setDIAS_VIGENCIA(Integer dIAS_VIGENCIA) {
		DIAS_VIGENCIA = dIAS_VIGENCIA;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getFc_emision_aseguradora() {
		return fc_emision_aseguradora;
	}
	public void setFc_emision_aseguradora(String fc_emision_aseguradora) {
		this.fc_emision_aseguradora = fc_emision_aseguradora;
	}
	public Date getFc_emision_aseguradora_date() {
		return fc_emision_aseguradora_date;
	}
	public void setFc_emision_aseguradora_date(Date fc_emision_aseguradora_date) {
		this.fc_emision_aseguradora_date = fc_emision_aseguradora_date;
	}
	public String getNumero_asistencia() {
		return numero_asistencia;
	}
	public void setNumero_asistencia(String numero_asistencia) {
		this.numero_asistencia = numero_asistencia;
	}
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public String getNm_grupo_contratante() {
		return nm_grupo_contratante;
	}
	public void setNm_grupo_contratante(String nm_grupo_contratante) {
		this.nm_grupo_contratante = nm_grupo_contratante;
	}
	public String getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(String cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	public String getNm_subagente() {
		return nm_subagente;
	}
	public void setNm_subagente(String nm_subagente) {
		this.nm_subagente = nm_subagente;
	}
	public String getCd_ejecutivo() {
		return cd_ejecutivo;
	}
	public void setCd_ejecutivo(String cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}

}
