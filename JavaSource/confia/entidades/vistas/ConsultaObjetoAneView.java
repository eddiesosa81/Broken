package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_objeto_ane_view")
public class ConsultaObjetoAneView {
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
	@Column(name="cd_ramo_cotizacion")
	private String cd_ramo_cotizacion;
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="poliza")
	private String poliza;
	@Column(name="factura_aseguradora")
	private String factura_aseguradora;
	@Column(name="anexo")
	private String anexo;
	@Column(name="cd_ubicacion")
	private String cd_ubicacion;
	@Column(name="dsc_ubicacion")
	private String dsc_ubicacion;
	@Column(name="cd_obj_cotizacion")
	private String cd_obj_cotizacion;
	@Column(name="descripcion_objeto")
	private String descripcion_objeto;
	@Column(name="fc_ini_obj_cot_date")
	private String fc_ini_obj_cot_date;
	@Column(name="fc_fin_obj_cot_date")
	private String fc_fin_obj_cot_date;
	@Column(name="fc_vig_ini_jul")
	private String fc_vig_ini_jul;
	@Column(name="fc_vig_fin_jul")
	private String fc_vig_fin_jul;
	@Column(name="dias_vigencia")
	private String dias_vigencia;
	@Column(name="valor_asegurador_objeto")
	private String valor_asegurador_objeto;
	@Column(name="tasa_objeto")
	private String tasa_objeto;
	@Column(name="prima_objeto")
	private String prima_objeto;
	@Column(name="total_asegurado_objeto")
	private String total_asegurado_objeto;
	@Column(name="ESTADO_OBJETO")
	private String estado_Objeto;
	@Column(name="deducible_Minimo")
	private String deducible_Minimo;
	
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
	public String getCd_ubicacion() {
		return cd_ubicacion;
	}
	public void setCd_ubicacion(String cd_ubicacion) {
		this.cd_ubicacion = cd_ubicacion;
	}
	public String getDsc_ubicacion() {
		return dsc_ubicacion;
	}
	public void setDsc_ubicacion(String dsc_ubicacion) {
		this.dsc_ubicacion = dsc_ubicacion;
	}
	public String getCd_obj_cotizacion() {
		return cd_obj_cotizacion;
	}
	public void setCd_obj_cotizacion(String cd_obj_cotizacion) {
		this.cd_obj_cotizacion = cd_obj_cotizacion;
	}
	public String getDescripcion_objeto() {
		return descripcion_objeto;
	}
	public void setDescripcion_objeto(String descripcion_objeto) {
		this.descripcion_objeto = descripcion_objeto;
	}
	public String getFc_ini_obj_cot_date() {
		return fc_ini_obj_cot_date;
	}
	public void setFc_ini_obj_cot_date(String fc_ini_obj_cot_date) {
		this.fc_ini_obj_cot_date = fc_ini_obj_cot_date;
	}
	public String getFc_fin_obj_cot_date() {
		return fc_fin_obj_cot_date;
	}
	public void setFc_fin_obj_cot_date(String fc_fin_obj_cot_date) {
		this.fc_fin_obj_cot_date = fc_fin_obj_cot_date;
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
	public String getValor_asegurador_objeto() {
		return valor_asegurador_objeto;
	}
	public void setValor_asegurador_objeto(String valor_asegurador_objeto) {
		this.valor_asegurador_objeto = valor_asegurador_objeto;
	}
	public String getTasa_objeto() {
		return tasa_objeto;
	}
	public void setTasa_objeto(String tasa_objeto) {
		this.tasa_objeto = tasa_objeto;
	}
	public String getPrima_objeto() {
		return prima_objeto;
	}
	public void setPrima_objeto(String prima_objeto) {
		this.prima_objeto = prima_objeto;
	}
	public String getTotal_asegurado_objeto() {
		return total_asegurado_objeto;
	}
	public void setTotal_asegurado_objeto(String total_asegurado_objeto) {
		this.total_asegurado_objeto = total_asegurado_objeto;
	}
	public String getEstado_Objeto() {
		return estado_Objeto;
	}
	public void setEstado_Objeto(String estado_Objeto) {
		this.estado_Objeto = estado_Objeto;
	}
	public String getDeducible_Minimo() {
		return deducible_Minimo;
	}
	public void setDeducible_Minimo(String deducible_Minimo) {
		this.deducible_Minimo = deducible_Minimo;
	}
	
}
