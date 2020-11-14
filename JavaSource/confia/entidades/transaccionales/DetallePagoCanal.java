/**
 * 
 */
package confia.entidades.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "detalle_pagos_canal_Tbl")
public class DetallePagoCanal{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_det_pago_canal")
	@SequenceGenerator(sequenceName = "secuencia_cd_det_pago_canal", name = "secuencia_cd_det_pago_canal", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_det_pago_canal")
	private Integer cd_det_pago_canal;
	@Column(name="cd_comisuba_pol")
	private Integer cd_comisuba_pol;
	@Column(name="Num_Factura_suba")
	private String num_Factura_suba;
	@Column(name="fecha_Factura")
	private Date fecha_Factura;
	@Column(name="valor_liquidado")
	private Double valor_liquidado;
	public Integer getCd_det_pago_canal() {
		return cd_det_pago_canal;
	}
	public void setCd_det_pago_canal(Integer cd_det_pago_canal) {
		this.cd_det_pago_canal = cd_det_pago_canal;
	}
	public Integer getCd_comisuba_pol() {
		return cd_comisuba_pol;
	}
	public void setCd_comisuba_pol(Integer cd_comisuba_pol) {
		this.cd_comisuba_pol = cd_comisuba_pol;
	}
	public String getNum_Factura_suba() {
		return num_Factura_suba;
	}
	public void setNum_Factura_suba(String num_Factura_suba) {
		this.num_Factura_suba = num_Factura_suba;
	}
	public Date getFecha_Factura() {
		return fecha_Factura;
	}
	public void setFecha_Factura(Date fecha_Factura) {
		this.fecha_Factura = fecha_Factura;
	}
	public Double getValor_liquidado() {
		return valor_liquidado;
	}
	public void setValor_liquidado(Double valor_liquidado) {
		this.valor_liquidado = valor_liquidado;
	}
	
	
	
	
}
