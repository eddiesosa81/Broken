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
@Table(name = "pago_siniestro_tbl")
public class PagoSiniestro {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_pago_siniestro")
	@SequenceGenerator(sequenceName = "secuencia_cd_pago_siniestro", name = "secuencia_cd_pago_siniestro", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_pago_siniestro")
	private Integer cd_pago_siniestro;
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="val_pago")
	private Double val_pago;
	@Column(name="fc_pago")
	private Date fc_pago;
	@Column(name="forma_pago")
	private String forma_pago;
	@Column(name="banco")
	private String banco;
	@Column(name="cheque")
	private String cheque;
	@Column(name="cta_cte")
	private String cta_cte;
	@Column(name="finiquito")
	private Integer finiquito;
	@Column(name="USRID")
	private String usrid;
	@Column(name="estado")
	private String estado;
	@Column(name="cd_reserva")
	private Integer cd_reserva;
	@Column(name="fc_estado_siniestro")
	private Date fc_estado_siniestro;
	
	public Integer getCd_pago_siniestro() {
		return cd_pago_siniestro;
	}
	public void setCd_pago_siniestro(Integer cd_pago_siniestro) {
		this.cd_pago_siniestro = cd_pago_siniestro;
	}
	public Integer getCd_siniestro() {
		return cd_siniestro;
	}
	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
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
	public Double getVal_pago() {
		return val_pago;
	}
	public void setVal_pago(Double val_pago) {
		this.val_pago = val_pago;
	}
	public Date getFc_pago() {
		return fc_pago;
	}
	public void setFc_pago(Date fc_pago) {
		this.fc_pago = fc_pago;
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
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getFiniquito() {
		return finiquito;
	}
	public void setFiniquito(Integer finiquito) {
		this.finiquito = finiquito;
	}
	public Integer getCd_reserva() {
		return cd_reserva;
	}
	public void setCd_reserva(Integer cd_reserva) {
		this.cd_reserva = cd_reserva;
	}
	public Date getFc_estado_siniestro() {
		return fc_estado_siniestro;
	}
	public void setFc_estado_siniestro(Date fc_estado_siniestro) {
		this.fc_estado_siniestro = fc_estado_siniestro;
	}
	

}
