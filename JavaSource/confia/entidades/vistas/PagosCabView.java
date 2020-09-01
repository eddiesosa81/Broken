package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pagos_cab_view")
public class PagosCabView {
	@Id
	@Column(name="cd_pago")
	private String cd_pago;
	@Column(name="cd_aseguradora")
	private String cd_aseguradora;
	@Column(name="usrid")
	private String usrid;
	@Column(name="cd_cliente")
	private String cd_cliente;
	@Column(name="fecha_pago")
	private String fecha_pago;
	@Column(name="forma_pago")
	private String forma_pago;
	@Column(name="banco")
	private String banco;
	@Column(name="cheque")
	private String cheque;
	@Column(name="cta_cte")
	private String cta_cte;
	@Column(name="no_retencion")
	private String no_retencion;
	@Column(name="valor_retencion")
	private String valor_retencion;
	@Column(name="valor_total_pago")
	private String valor_total_pago;
	@Column(name="razon_social_aseguradora")
	private String razon_social_aseguradora;
	@Column(name="cliente")
	private String cliente;
	@Column(name="usuario")
	private String usuario;
	@Column(name="num_recibo")
	private String num_recibo;
	@Column(name="fc_num_recibo")
	private String fc_num_recibo;
	
	public String getCd_pago() {
		return cd_pago;
	}
	public void setCd_pago(String cd_pago) {
		this.cd_pago = cd_pago;
	}
	public String getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(String cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public String getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getCheque() {
		return cheque;
	}
	public void setCheque(String cheque) {
		this.cheque = cheque;
	}
	public String getCta_cte() {
		return cta_cte;
	}
	public void setCta_cte(String cta_cte) {
		this.cta_cte = cta_cte;
	}
	public String getNo_retencion() {
		return no_retencion;
	}
	public void setNo_retencion(String no_retencion) {
		this.no_retencion = no_retencion;
	}
	public String getValor_retencion() {
		return valor_retencion;
	}
	public void setValor_retencion(String valor_retencion) {
		this.valor_retencion = valor_retencion;
	}
	public String getValor_total_pago() {
		return valor_total_pago;
	}
	public void setValor_total_pago(String valor_total_pago) {
		this.valor_total_pago = valor_total_pago;
	}
	public String getRazon_social_aseguradora() {
		return razon_social_aseguradora;
	}
	public void setRazon_social_aseguradora(String razon_social_aseguradora) {
		this.razon_social_aseguradora = razon_social_aseguradora;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNum_recibo() {
		return num_recibo;
	}
	public void setNum_recibo(String num_recibo) {
		this.num_recibo = num_recibo;
	}
	public String getFc_num_recibo() {
		return fc_num_recibo;
	}
	public void setFc_num_recibo(String fc_num_recibo) {
		this.fc_num_recibo = fc_num_recibo;
	}
	
}
