package confia.entidades.vistas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_subojbeto_pol_view")
public class ConsultaSubObjetoPolView {
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
	@Column(name="cd_subobj_cotizacion")
	private String cd_subobj_cotizacion;
	@Column(name="descripcion_subobjeto")
	private String descripcion_subobjeto;
	@Column(name="fc_ini_subobj_cot_date")
	private Date fc_ini_subobj_cot_date;
	@Column(name="fc_fin_subobj_cot_date")
	private String fc_fin_subobj_cot_date;
	@Column(name="fc_vig_ini_jul")
	private String fc_vig_ini_jul;
	@Column(name="fc_vig_fin_jul")
	private String fc_vig_fin_jul;
	@Column(name="dias_vigencia")
	private String dias_vigencia;
	@Column(name="valor_asegurador_subobjeto")
	private String valor_asegurador_subobjeto;
	@Column(name="tasa_subobjeto")
	private String tasa_subobjeto;
	@Column(name="prima_subobjeto")
	private String prima_subobjeto;
	@Column(name="fecha_nacimiento_subobj")
	private Date fecha_nacimiento_subobj;
	@Column(name="parentesco")
	private String parentesco;
	@Column(name="cedula_subobj")
	private String cedula_subobj;
	@Column(name="edad_subobj")
	private String edad_subobj;
	
	
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
	public String getCd_subobj_cotizacion() {
		return cd_subobj_cotizacion;
	}
	public void setCd_subobj_cotizacion(String cd_subobj_cotizacion) {
		this.cd_subobj_cotizacion = cd_subobj_cotizacion;
	}
	public String getDescripcion_subobjeto() {
		return descripcion_subobjeto;
	}
	public void setDescripcion_subobjeto(String descripcion_subobjeto) {
		this.descripcion_subobjeto = descripcion_subobjeto;
	}

	public Date getFc_ini_subobj_cot_date() {
		return fc_ini_subobj_cot_date;
	}
	public void setFc_ini_subobj_cot_date(Date fc_ini_subobj_cot_date) {
		this.fc_ini_subobj_cot_date = fc_ini_subobj_cot_date;
	}
	public String getFc_fin_subobj_cot_date() {
		return fc_fin_subobj_cot_date;
	}
	public void setFc_fin_subobj_cot_date(String fc_fin_subobj_cot_date) {
		this.fc_fin_subobj_cot_date = fc_fin_subobj_cot_date;
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
	public String getValor_asegurador_subobjeto() {
		return valor_asegurador_subobjeto;
	}
	public void setValor_asegurador_subobjeto(String valor_asegurador_subobjeto) {
		this.valor_asegurador_subobjeto = valor_asegurador_subobjeto;
	}
	public String getTasa_subobjeto() {
		return tasa_subobjeto;
	}
	public void setTasa_subobjeto(String tasa_subobjeto) {
		this.tasa_subobjeto = tasa_subobjeto;
	}
	public String getPrima_subobjeto() {
		return prima_subobjeto;
	}
	public void setPrima_subobjeto(String prima_subobjeto) {
		this.prima_subobjeto = prima_subobjeto;
	}
	
	
	public Date getFecha_nacimiento_subobj() {
		return fecha_nacimiento_subobj;
	}
	public void setFecha_nacimiento_subobj(Date fecha_nacimiento_subobj) {
		this.fecha_nacimiento_subobj = fecha_nacimiento_subobj;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	public String getCedula_subobj() {
		return cedula_subobj;
	}
	public void setCedula_subobj(String cedula_subobj) {
		this.cedula_subobj = cedula_subobj;
	}
	public String getEdad_subobj() {
		return edad_subobj;
	}
	public void setEdad_subobj(String edad_subobj) {
		this.edad_subobj = edad_subobj;
	}
	

}
