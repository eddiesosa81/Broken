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
@Table(name = "detalles_pago_tbl")
public class DetallePagos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_det_pago")
	@SequenceGenerator(sequenceName = "secuencia_CD_det_pago", name = "secuencia_CD_det_pago", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_det_pago")
	private Integer cd_det_pago;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_pago")
	private Integer cd_pago;
	@Column(name="cd_det_forma_pago")
	private Integer cd_det_forma_pago;
	@Column(name="saldo_anterior")
	private Double saldo_anterior;
	@Column(name="valor_pago")
	private Double valor_pago;
	@Column(name="saldo_actual")
	private Double saldo_actual;
	@Column(name="poliza")
	private String poliza;
	@Column(name="facturas")
	private String facturas;
	@Column(name="anexos")
	private String anexos;
	@Column(name="fc_remision")
	private Date fc_remision;
	@Column(name="num_remision")
	private Integer num_remision;
	
	public Integer getCd_det_pago() {
		return cd_det_pago;
	}
	public void setCd_det_pago(Integer cd_det_pago) {
		this.cd_det_pago = cd_det_pago;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_pago() {
		return cd_pago;
	}
	public void setCd_pago(Integer cd_pago) {
		this.cd_pago = cd_pago;
	}
	public Integer getCd_det_forma_pago() {
		return cd_det_forma_pago;
	}
	public void setCd_det_forma_pago(Integer cd_det_forma_pago) {
		this.cd_det_forma_pago = cd_det_forma_pago;
	}
	public Double getSaldo_anterior() {
		return saldo_anterior;
	}
	public void setSaldo_anterior(Double saldo_anterior) {
		this.saldo_anterior = saldo_anterior;
	}
	public Double getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(Double valor_pago) {
		this.valor_pago = valor_pago;
	}
	public Double getSaldo_actual() {
		return saldo_actual;
	}
	public void setSaldo_actual(Double saldo_actual) {
		this.saldo_actual = saldo_actual;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFacturas() {
		return facturas;
	}
	public void setFacturas(String facturas) {
		this.facturas = facturas;
	}
	public String getAnexos() {
		return anexos;
	}
	public void setAnexos(String anexos) {
		this.anexos = anexos;
	}
	public Date getFc_remision() {
		return fc_remision;
	}
	public void setFc_remision(Date fc_remision) {
		this.fc_remision = fc_remision;
	}
	public Integer getNum_remision() {
		return num_remision;
	}
	public void setNum_remision(Integer num_remision) {
		this.num_remision = num_remision;
	}
	
	
}
