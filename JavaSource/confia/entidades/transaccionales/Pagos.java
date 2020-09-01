/**
 * 
 */
package confia.entidades.transaccionales;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Guido Guerrero
 *
 */

@Entity
@Table(name = "pago_tbl")
public class Pagos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_pago")
	@SequenceGenerator(sequenceName = "secuencia_CD_pago", name = "secuencia_CD_pago", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_pago")
	private Integer cd_pago;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="valor_total_pago")
	private Double valor_total_pago;
	@Column(name="fecha_pago")
	private Date fecha_pago;
	@Column(name="cd_moneda")
	private Integer cd_moneda;
	@Column(name="forma_pago")
	private String forma_pago;
	@Column(name="doc_pago")
	private String doc_pago;
	@Column(name="fc_doc_pago")
	private Date fc_doc_pago;
	@Column(name="banco")
	private String banco;
	@Column(name="cheque")
	private String cheque;
	@Column(name="cta_cte")
	private String cta_cte;
	@Column(name="cobrador")
	private String cobrador;
	@Column(name="obs_pago")
	private String obs_pago;
	@Column(name="num_recibo")
	private String num_recibo;
	@Column(name="usrid")
	private Integer usrid;
	@Column(name="usrlogin")
	private String usrlogin;
	@Column(name="no_retencion")
	private Integer no_retencion;
	@Column(name="valor_retencion")
	private Double valor_retencion;
	public Integer getCd_pago() {
		return cd_pago;
	}
	public void setCd_pago(Integer cd_pago) {
		this.cd_pago = cd_pago;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public Integer getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public Double getValor_total_pago() {
		return valor_total_pago;
	}
	public void setValor_total_pago(Double valor_total_pago) {
		this.valor_total_pago = valor_total_pago;
	}
	public Date getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public Integer getCd_moneda() {
		return cd_moneda;
	}
	public void setCd_moneda(Integer cd_moneda) {
		this.cd_moneda = cd_moneda;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public String getDoc_pago() {
		return doc_pago;
	}
	public void setDoc_pago(String doc_pago) {
		this.doc_pago = doc_pago;
	}
	public Date getFc_doc_pago() {
		return fc_doc_pago;
	}
	public void setFc_doc_pago(Date fc_doc_pago) {
		this.fc_doc_pago = fc_doc_pago;
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
	public String getCobrador() {
		return cobrador;
	}
	public void setCobrador(String cobrador) {
		this.cobrador = cobrador;
	}
	public String getObs_pago() {
		return obs_pago;
	}
	public void setObs_pago(String obs_pago) {
		this.obs_pago = obs_pago;
	}
	public String getNum_recibo() {
		return num_recibo;
	}
	public void setNum_recibo(String num_recibo) {
		this.num_recibo = num_recibo;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public String getUsrlogin() {
		return usrlogin;
	}
	public void setUsrlogin(String usrlogin) {
		this.usrlogin = usrlogin;
	}
	
	public Integer getNo_retencion() {
		return no_retencion;
	}
	public void setNo_retencion(Integer no_retencion) {
		this.no_retencion = no_retencion;
	}
	public Double getValor_retencion() {
		return valor_retencion;
	}
	public void setValor_retencion(Double valor_retencion) {
		this.valor_retencion = valor_retencion;
	}
	@Override
	public String toString() {
		return "Pagos [cd_pago=" + cd_pago + ", cd_compania=" + cd_compania + ", cd_aseguradora=" + cd_aseguradora
				+ ", cd_cliente=" + cd_cliente + ", valor_total_pago=" + valor_total_pago + ", fecha_pago=" + fecha_pago
				+ ", cd_moneda=" + cd_moneda + ", forma_pago=" + forma_pago + ", doc_pago=" + doc_pago
				+ ", fc_doc_pago=" + fc_doc_pago + ", banco=" + banco + ", cheque=" + cheque + ", cta_cte=" + cta_cte
				+ ", cobrador=" + cobrador + ", obs_pago=" + obs_pago + ", num_recibo=" + num_recibo + ", usrid="
				+ usrid + ", usrlogin=" + usrlogin + "]";
	}
	

	
}
