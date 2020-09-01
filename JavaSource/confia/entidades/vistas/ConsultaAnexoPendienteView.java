package confia.entidades.vistas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_anexo_pendiente_view")
public class ConsultaAnexoPendienteView {
	@Id
	@Column(name="cod")
	private String cod;
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
	@Column(name="desc_ramo")
	private String desc_ramo;
	@Column(name="cd_ramo_cotizacion")
	private String cd_ramo_cotizacion;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="descripcion_plan")
	private String descripcion_plan;
	@Column(name="fc_ini_vig_date")
	private String fc_ini_vig_date;
	
	@Column(name="fc_fin_vig_date")
	private String fc_fin_vig_date;
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
	private String cd_subagente;
	@Column(name="cd_ejecutivo")
	private String cd_ejecutivo;
	@Column(name="cd_grupo_contratante")
	private String cd_grupo_contratante;
	
	@Column(name="total_asegurado")
	private String total_asegurado;
	
	@Column(name="total_prima")
	private String total_prima;
	@Column(name="nmsubagente")
	private String nmsubagente;
	@Column(name="nm_grupo_contratante")
	private String nm_grupo_contratante;
	@Column(name="cd_ram_cot_ori")
	private String cd_ram_cot_ori;
	@Column(name="afecta_anexo")
	private String afecta_anexo;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
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
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public String getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(String total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public String getNmsubagente() {
		return nmsubagente;
	}
	public void setNmsubagente(String nmsubagente) {
		this.nmsubagente = nmsubagente;
	}
	public String getNm_grupo_contratante() {
		return nm_grupo_contratante;
	}
	public void setNm_grupo_contratante(String nm_grupo_contratante) {
		this.nm_grupo_contratante = nm_grupo_contratante;
	}
	public String getTotal_prima() {
		return total_prima;
	}
	public void setTotal_prima(String total_prima) {
		this.total_prima = total_prima;
	}
	public String getCd_ram_cot_ori() {
		return cd_ram_cot_ori;
	}
	public void setCd_ram_cot_ori(String cd_ram_cot_ori) {
		this.cd_ram_cot_ori = cd_ram_cot_ori;
	}
	public String getAfecta_anexo() {
		return afecta_anexo;
	}
	public void setAfecta_anexo(String afecta_anexo) {
		this.afecta_anexo = afecta_anexo;
	}
	

}
