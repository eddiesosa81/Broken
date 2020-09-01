package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "comision_poliza_tbl")
public class ComisionesPoliza {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_comision_poliza")
	@SequenceGenerator(sequenceName = "secuencia_cd_comision_poliza", name = "secuencia_cd_comision_poliza", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_comision_poliza")
	private Integer cd_comision_poliza;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="total_asegurado")
	private Double total_asegurado;
	@Column(name="total_prima")
	private Double total_prima;
	@Column(name="pct_com_brk")
	private Double pct_com_brk;
	@Column(name="val_com_brk")
	private Double val_com_brk;
	@Column(name="saldo_com_brk")
	private Double saldo_com_brk;
	@Column(name="flg_pre_factura")
	private String flg_pre_factura;
	@Column(name="flg_factura")
	private String flg_factura;
	@Column(name="cd_reserva")
	private Integer cd_reserva;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="valor_liquidado")
	private Double valorLiquidado;
	@Column(name="flg_liquidadiferencial")
	private String flg_liquidadiferencial;
	@Column(name="cd_det_forma_pago")
	private Integer cd_det_forma_pago;
	
	public Integer getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(Integer cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public Double getTotal_asegurado() {
		return total_asegurado;
	}
	public void setTotal_asegurado(Double total_asegurado) {
		this.total_asegurado = total_asegurado;
	}
	public Double getTotal_prima() {
		return total_prima;
	}
	public void setTotal_prima(Double total_prima) {
		this.total_prima = total_prima;
	}
	public Double getPct_com_brk() {
		return pct_com_brk;
	}
	public void setPct_com_brk(Double pct_com_brk) {
		this.pct_com_brk = pct_com_brk;
	}
	public Double getVal_com_brk() {
		return val_com_brk;
	}
	public void setVal_com_brk(Double val_com_brk) {
		this.val_com_brk = val_com_brk;
	}
	public Double getSaldo_com_brk() {
		return saldo_com_brk;
	}
	public void setSaldo_com_brk(Double saldo_com_brk) {
		this.saldo_com_brk = saldo_com_brk;
	}
	public String getFlg_pre_factura() {
		return flg_pre_factura;
	}
	public void setFlg_pre_factura(String flg_pre_factura) {
		this.flg_pre_factura = flg_pre_factura;
	}
	public String getFlg_factura() {
		return flg_factura;
	}
	public void setFlg_factura(String flg_factura) {
		this.flg_factura = flg_factura;
	}
	public Integer getCd_reserva() {
		return cd_reserva;
	}
	public void setCd_reserva(Integer cd_reserva) {
		this.cd_reserva = cd_reserva;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Double getValorLiquidado() {
		return valorLiquidado;
	}
	public void setValorLiquidado(Double valorLiquidado) {
		this.valorLiquidado = valorLiquidado;
	}
	public String getFlg_liquidadiferencial() {
		return flg_liquidadiferencial;
	}
	public void setFlg_liquidadiferencial(String flg_liquidadiferencial) {
		this.flg_liquidadiferencial = flg_liquidadiferencial;
	}
	public Integer getCd_det_forma_pago() {
		return cd_det_forma_pago;
	}
	public void setCd_det_forma_pago(Integer cd_det_forma_pago) {
		this.cd_det_forma_pago = cd_det_forma_pago;
	}
	
}
