package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transacciones_pol_view")
public class TransaccionPolizaView {
	@Id
	@Column(name="id")
	public String id;
	@Column(name="desc_ramo")
	public String desc_ramo;
	@Column(name="poliza")
	public String poliza;
	@Column(name="valor_asegurado_ramo")
	public Double valor_asegurado_ramo;
	@Column(name="dsc_ubicacion")
	public String dsc_ubicacion;
	@Column(name="valor_asegurado_ubicacion")
	public Double valor_asegurado_ubicacion;
	@Column(name="descripcion_objeto")
	public String descripcion_objeto;
	@Column(name="valor_asegurador_objeto")
	public Double valor_asegurador_objeto;
	@Column(name="transaccion_objeto")
	public String transaccion_objeto;
	@Column(name="descripcion_subobjeto")
	public String descripcion_subobjeto;
	@Column(name="valor_asegurador_subobjeto")
	public Double valor_asegurador_subobjeto;
	@Column(name="transaccion_subobjeto")
	public String transaccion_subobjeto;
	@Column(name="no_de_motor")
	public String no_de_motor;
	@Column(name="no_de_chasis")
	public String no_de_chasis;
	@Column(name="placa")
	public String placa;
	@Column(name="color")
	public String color;
	@Column(name="modelo")
	public String modelo;
	@Column(name="cd_ramo_cotizacion")
	public String cd_ramo_cotizacion;
	@Column(name="cd_ubicacion")
	public String cd_ubicacion;
	@Column(name="cd_obj_cotizacion")
	public String cd_obj_cotizacion;
	@Column(name="cd_subobj_cotizacion")
	public String cd_subobj_cotizacion;
	@Column(name="cd_carac_vh")
	public String cd_carac_vh;
	@Column(name="fechaIniObj")
	public String fechaIniObj;
	
	
	
	public String getFechaIniObj() {
		return fechaIniObj;
	}
	public void setFechaIniObj(String fechaIniObj) {
		this.fechaIniObj = fechaIniObj;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc_ramo() {
		return desc_ramo;
	}
	public void setDesc_ramo(String desc_ramo) {
		this.desc_ramo = desc_ramo;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public Double getValor_asegurado_ramo() {
		return valor_asegurado_ramo;
	}
	public void setValor_asegurado_ramo(Double valor_asegurado_ramo) {
		this.valor_asegurado_ramo = valor_asegurado_ramo;
	}
	public String getDsc_ubicacion() {
		return dsc_ubicacion;
	}
	public void setDsc_ubicacion(String dsc_ubicacion) {
		this.dsc_ubicacion = dsc_ubicacion;
	}
	public Double getValor_asegurado_ubicacion() {
		return valor_asegurado_ubicacion;
	}
	public void setValor_asegurado_ubicacion(Double valor_asegurado_ubicacion) {
		this.valor_asegurado_ubicacion = valor_asegurado_ubicacion;
	}
	public String getDescripcion_objeto() {
		return descripcion_objeto;
	}
	public void setDescripcion_objeto(String descripcion_objeto) {
		this.descripcion_objeto = descripcion_objeto;
	}
	public Double getValor_asegurador_objeto() {
		return valor_asegurador_objeto;
	}
	public void setValor_asegurador_objeto(Double valor_asegurador_objeto) {
		this.valor_asegurador_objeto = valor_asegurador_objeto;
	}
	public String getTransaccion_objeto() {
		return transaccion_objeto;
	}
	public void setTransaccion_objeto(String transaccion_objeto) {
		this.transaccion_objeto = transaccion_objeto;
	}
	public String getDescripcion_subobjeto() {
		return descripcion_subobjeto;
	}
	public void setDescripcion_subobjeto(String descripcion_subobjeto) {
		this.descripcion_subobjeto = descripcion_subobjeto;
	}
	public Double getValor_asegurador_subobjeto() {
		return valor_asegurador_subobjeto;
	}
	public void setValor_asegurador_subobjeto(Double valor_asegurador_subobjeto) {
		this.valor_asegurador_subobjeto = valor_asegurador_subobjeto;
	}
	public String getTransaccion_subobjeto() {
		return transaccion_subobjeto;
	}
	public void setTransaccion_subobjeto(String transaccion_subobjeto) {
		this.transaccion_subobjeto = transaccion_subobjeto;
	}
	public String getNo_de_motor() {
		return no_de_motor;
	}
	public void setNo_de_motor(String no_de_motor) {
		this.no_de_motor = no_de_motor;
	}
	public String getNo_de_chasis() {
		return no_de_chasis;
	}
	public void setNo_de_chasis(String no_de_chasis) {
		this.no_de_chasis = no_de_chasis;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(String cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public String getCd_ubicacion() {
		return cd_ubicacion;
	}
	public void setCd_ubicacion(String cd_ubicacion) {
		this.cd_ubicacion = cd_ubicacion;
	}
	public String getCd_obj_cotizacion() {
		return cd_obj_cotizacion;
	}
	public void setCd_obj_cotizacion(String cd_obj_cotizacion) {
		this.cd_obj_cotizacion = cd_obj_cotizacion;
	}
	public String getCd_subobj_cotizacion() {
		return cd_subobj_cotizacion;
	}
	public void setCd_subobj_cotizacion(String cd_subobj_cotizacion) {
		this.cd_subobj_cotizacion = cd_subobj_cotizacion;
	}
	public String getCd_carac_vh() {
		return cd_carac_vh;
	}
	public void setCd_carac_vh(String cd_carac_vh) {
		this.cd_carac_vh = cd_carac_vh;
	}

	
}
