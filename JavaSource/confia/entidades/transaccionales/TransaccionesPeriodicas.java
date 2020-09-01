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
@Table(name = "transacciones_periodica_tbl")
public class TransaccionesPeriodicas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_transaccion")
	@SequenceGenerator(sequenceName = "secuencia_cd_transaccion", name = "secuencia_cd_transaccion", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_transaccion")
	private Integer cd_transaccion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_det_forma_pago")
	private Integer cd_det_forma_pago;
	@Column(name="cd_det_forma_pago_anexo")
	private Integer cd_det_forma_pago_anexo;
	@Column(name="tipo")
	private String tipo;
	@Column(name="factura")
	private String factura;
	@Column(name="anexo")
	private String anexo;
	@Column(name="prima_neta")
	private Double prima_neta;
	@Column(name="valor_financiamiento")
	private Double valor_financiamiento;
	@Column(name="derecho_emision")
	private Double derecho_emision;
	@Column(name="seguto_campesino")
	private Double seguto_campesino;
	@Column(name="otros")
	private Double otros;
	@Column(name="iva")
	private Double iva;
	@Column(name="total")
	private Double total;
	@Column(name="fecha_afectacion")
	private Date fecha_afectacion;
	@Column(name="tipo_transaccion")
	private String tipo_transaccion;
	@Column(name="super_bancos")
	private Double super_bancos;
	public Integer getCd_transaccion() {
		return cd_transaccion;
	}
	public void setCd_transaccion(Integer cd_transaccion) {
		this.cd_transaccion = cd_transaccion;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_det_forma_pago() {
		return cd_det_forma_pago;
	}
	public void setCd_det_forma_pago(Integer cd_det_forma_pago) {
		this.cd_det_forma_pago = cd_det_forma_pago;
	}
	public Integer getCd_det_forma_pago_anexo() {
		return cd_det_forma_pago_anexo;
	}
	public void setCd_det_forma_pago_anexo(Integer cd_det_forma_pago_anexo) {
		this.cd_det_forma_pago_anexo = cd_det_forma_pago_anexo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public Double getPrima_neta() {
		return prima_neta;
	}
	public void setPrima_neta(Double prima_neta) {
		this.prima_neta = prima_neta;
	}
	public Double getValor_financiamiento() {
		return valor_financiamiento;
	}
	public void setValor_financiamiento(Double valor_financiamiento) {
		this.valor_financiamiento = valor_financiamiento;
	}
	public Double getDerecho_emision() {
		return derecho_emision;
	}
	public void setDerecho_emision(Double derecho_emision) {
		this.derecho_emision = derecho_emision;
	}
	public Double getSeguto_campesino() {
		return seguto_campesino;
	}
	public void setSeguto_campesino(Double seguto_campesino) {
		this.seguto_campesino = seguto_campesino;
	}
	public Double getOtros() {
		return otros;
	}
	public void setOtros(Double otros) {
		this.otros = otros;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getFecha_afectacion() {
		return fecha_afectacion;
	}
	public void setFecha_afectacion(Date fecha_afectacion) {
		this.fecha_afectacion = fecha_afectacion;
	}
	public String getTipo_transaccion() {
		return tipo_transaccion;
	}
	public void setTipo_transaccion(String tipo_transaccion) {
		this.tipo_transaccion = tipo_transaccion;
	}
	public Double getSuper_bancos() {
		return super_bancos;
	}
	public void setSuper_bancos(Double super_bancos) {
		this.super_bancos = super_bancos;
	}
	
	

}
