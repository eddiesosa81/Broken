package confia.entidades.transaccionales;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reservas_tbl")
public class Reservas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_reserva")
	@SequenceGenerator(sequenceName = "secuencia_cd_reserva", name = "secuencia_cd_reserva", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_reserva")
	private Integer cd_reserva;
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="VALOR_PERDIDA")
	private Double valor_perdida;
	@Column(name="val_depreciacion")
	private Double val_depreciacion;
	@Column(name="val_deducible")
	private Double val_deducible;
	@Column(name="VAL_INDEMNIZACION")
	private Double val_indemnizacion;
	@Column(name="val_rasa")
	private Double val_rasa;
	@Column(name="val_otros")
	private Double val_otros;
	@Column(name="gastos_no_cubiertos")
	private Double gastos_no_cubiertos;
	@Column(name="copago_pct")
	private Double copago_pct;
	@Column(name="copago_val")
	private Double copago_val;
	
	
	public Integer getCd_reserva() {
		return cd_reserva;
	}
	public void setCd_reserva(Integer cd_reserva) {
		this.cd_reserva = cd_reserva;
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
	public Double getValor_perdida() {
		return valor_perdida;
	}
	public void setValor_perdida(Double valor_perdida) {
		this.valor_perdida = valor_perdida;
	}
	public Double getVal_depreciacion() {
		return val_depreciacion;
	}
	public void setVal_depreciacion(Double val_depreciacion) {
		this.val_depreciacion = val_depreciacion;
	}
	public Double getVal_deducible() {
		return val_deducible;
	}
	public void setVal_deducible(Double val_deducible) {
		this.val_deducible = val_deducible;
	}
	public Double getVal_indemnizacion() {
		return val_indemnizacion;
	}
	public void setVal_indemnizacion(Double val_indemnizacion) {
		this.val_indemnizacion = val_indemnizacion;
	}
	public Double getVal_rasa() {
		return val_rasa;
	}
	public void setVal_rasa(Double val_rasa) {
		this.val_rasa = val_rasa;
	}
	public Double getVal_otros() {
		return val_otros;
	}
	public void setVal_otros(Double val_otros) {
		this.val_otros = val_otros;
	}
	public Double getGastos_no_cubiertos() {
		return gastos_no_cubiertos;
	}
	public void setGastos_no_cubiertos(Double gastos_no_cubiertos) {
		this.gastos_no_cubiertos = gastos_no_cubiertos;
	}
	public Double getCopago_pct() {
		return copago_pct;
	}
	public void setCopago_pct(Double copago_pct) {
		this.copago_pct = copago_pct;
	}
	public Double getCopago_val() {
		return copago_val;
	}
	public void setCopago_val(Double copago_val) {
		this.copago_val = copago_val;
	}
	
}
