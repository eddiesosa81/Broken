package confia.entidades.vistas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_pago_pol_view")
public class ConsultaPagoPolView {
	@Id
	@Column(name="cod")
	private String cod;
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
	@Column(name="cd_forma_pago")
	private String cd_forma_pago;
	@Column(name="tipo")
	private String tipo;
	@Column(name="estado")
	private String estado;
	@Column(name="valor")
	private String valor;
	@Column(name="saldo")
	private String saldo;
	@Column(name="fecha_vencimiento_date")
	private Date fecha_vencimiento_date;
	@Column(name="cd_det_forma_pago")
	private String cd_det_forma_pago;
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
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
	public String getCd_forma_pago() {
		return cd_forma_pago;
	}
	public void setCd_forma_pago(String cd_forma_pago) {
		this.cd_forma_pago = cd_forma_pago;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public Date getFecha_vencimiento_date() {
		return fecha_vencimiento_date;
	}
	public void setFecha_vencimiento_date(Date fecha_vencimiento_date) {
		this.fecha_vencimiento_date = fecha_vencimiento_date;
	}
	public String getCd_det_forma_pago() {
		return cd_det_forma_pago;
	}
	public void setCd_det_forma_pago(String cd_det_forma_pago) {
		this.cd_det_forma_pago = cd_det_forma_pago;
	}
	
	
}
