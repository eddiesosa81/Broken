package confia.entidades.vistas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_poliza_view")
public class ConsultaPolizaView {
	@Id
	@Column(name="cod")
	private String codigo;
	@Column(name="num_cotizacion")
	private String num_cotizacion;
	@Column(name="cd_cotizacion")
	private String cd_cotizacion;
	@Column(name="cd_compania")
	private String cd_compania;
	@Column(name="cd_cliente")
	private String cd_cliente;
	@Column(name="identificacion_cliente")
	private String identificacion_cliente;
	@Column(name="cliente")
	private String cliente;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="cd_rubro")
	private String cd_rubro;
	@Column(name="desc_rubro")
	private String desc_rubro;
	@Column(name="fact_periodica_cot")
	private String fact_periodica_cot;
	@Column(name="num_renovaciones_cot")
	private String num_renovaciones_cot;
	@Column(name="fc_creacion")
	private String fc_creacion;
	@Column(name="cd_cliente_asegurado")
	private String cd_cliente_asegurado;
	@Column(name="cd_ramo")
	private String cd_ramo;
	@Column(name="desc_ramo")
	private String desc_ramo;
	@Column(name="cd_ramo_cotizacion")
	private String cd_ramo_cotizacion;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="descripcion_plan")
	private String descripcion_plan;
	@Column(name="fc_ini_vig_date")
	private Date fc_ini_vig_date;
	@Column(name="fc_fin_vig_date")
	private Date fc_fin_vig_date;
	@Column(name="fc_vig_ini_jul")
	private String fc_vig_ini_jul;
	@Column(name="fc_vig_fin_jul")
	private String fc_vig_fin_jul;
	@Column(name="dias_vigencia")
	private String dias_vigencia;
	@Column(name="poliza")
	private String poliza;
	@Column(name="factura_aseguradora")
	private String factura_aseguradora;
	@Column(name="anexo")
	private String anexo;
	@Column(name="cd_subagente")
	private String cdSubagente; 
	@Column(name="CD_EJECUTIVO")
	private String cdEjecutivo;
	@Column(name="CD_GRUPO_CONTRATANTE")
	private String cd_grupo_contratante;
	@Column(name="ramcot_total_asegurado")
	private Double total_asegurado;
	@Column(name="nmsubagente")
	private String nmSubagente;
	@Column(name="nm_grupo_contratante")
	private String nm_grupo_contratante;
	@Column(name="tipo_endoso")
	private String tipo_endoso;
	@Column(name="total_prima")
	private Double total_prima;
	@Column(name="gestiona_renovacion")
	private String gestiona_renovacion;
	
	
	
	
	public String getGestiona_renovacion() {
		return gestiona_renovacion;
	}
	public void setGestiona_renovacion(String gestiona_renovacion) {
		this.gestiona_renovacion = gestiona_renovacion;
	}
	public Double getTotal_prima() {
		return total_prima;
	}
	public void setTotal_prima(Double total_prima) {
		this.total_prima = total_prima;
	}
	public String getTipo_endoso() {
		return tipo_endoso;
	}
	public void setTipo_endoso(String tipo_endoso) {
		this.tipo_endoso = tipo_endoso;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNum_cotizacion() {
		return num_cotizacion;
	}
	public void setNum_cotizacion(String num_cotizacion) {
		this.num_cotizacion = num_cotizacion;
	}
	public String getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(String cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public String getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(String cd_compania) {
		this.cd_compania = cd_compania;
	}
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}
	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}
	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}
	public String getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(String cd_rubro) {
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
	public String getCd_cliente_asegurado() {
		return cd_cliente_asegurado;
	}
	public void setCd_cliente_asegurado(String cd_cliente_asegurado) {
		this.cd_cliente_asegurado = cd_cliente_asegurado;
	}
	public String getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(String cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public String getDesc_ramo() {
		return desc_ramo;
	}
	public void setDesc_ramo(String desc_ramo) {
		this.desc_ramo = desc_ramo;
	}
	public String getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(String cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public String getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(String cd_plan) {
		this.cd_plan = cd_plan;
	}
	public String getDescripcion_plan() {
		return descripcion_plan;
	}
	public void setDescripcion_plan(String descripcion_plan) {
		this.descripcion_plan = descripcion_plan;
	}
	
	public Date getFc_ini_vig_date() {
		return fc_ini_vig_date;
	}
	public void setFc_ini_vig_date(Date fc_ini_vig_date) {
		this.fc_ini_vig_date = fc_ini_vig_date;
	}
	public Date getFc_fin_vig_date() {
		return fc_fin_vig_date;
	}
	public void setFc_fin_vig_date(Date fc_fin_vig_date) {
		this.fc_fin_vig_date = fc_fin_vig_date;
	}
	public String getFc_vig_ini_jul() {
		return fc_vig_ini_jul;
	}
	public void setFc_vig_ini_jul(String fc_vig_ini_jul) {
		this.fc_vig_ini_jul = fc_vig_ini_jul;
	}
	public String getFc_vig_fin_jul() {
		return fc_vig_fin_jul;
	}
	public void setFc_vig_fin_jul(String fc_vig_fin_jul) {
		this.fc_vig_fin_jul = fc_vig_fin_jul;
	}
	public String getDias_vigencia() {
		return dias_vigencia;
	}
	public void setDias_vigencia(String dias_vigencia) {
		this.dias_vigencia = dias_vigencia;
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
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public String getCdSubagente() {
		return cdSubagente;
	}
	public void setCdSubagente(String cdSubagente) {
		this.cdSubagente = cdSubagente;
	}
	public String getCdEjecutivo() {
		return cdEjecutivo;
	}
	public void setCdEjecutivo(String cdEjecutivo) {
		this.cdEjecutivo = cdEjecutivo;
	}
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public Double getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(Double total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public String getNmSubagente() {
		return nmSubagente;
	}
	public void setNmSubagente(String nmSubagente) {
		this.nmSubagente = nmSubagente;
	}
	public String getNm_grupo_contratante() {
		return nm_grupo_contratante;
	}
	public void setNm_grupo_contratante(String nm_grupo_contratante) {
		this.nm_grupo_contratante = nm_grupo_contratante;
	}
	
}
