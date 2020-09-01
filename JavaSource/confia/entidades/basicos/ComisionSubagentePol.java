package confia.entidades.basicos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "comision_subagente_pol_tbl")

public class ComisionSubagentePol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_comisuba_pol")
	@SequenceGenerator(sequenceName = "secuencia_cd_comisuba_pol", name = "secuencia_cd_comisuba_pol", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_comisuba_pol")
	private Integer cd_comisuba_pol;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="total_prima")
	private Double total_prima;
	@Column(name="pct_com_suba")
	private Double pct_com_suba;
	@Column(name="val_com_suba")
	private Double val_com_suba;
	@Column(name="saldo_com_suba")
	private Double saldo_com_suba;
	@Column(name="flg_pago")
	private Integer flg_pago;
	@Column(name="fc_pago_suba")
	private Date fc_pago_suba;
	@Column(name="num_Factura_suba")
	private String num_Factura_suba;
	@Column(name="cd_comision_poliza")
	private Integer cd_comision_poliza;
	
	public Integer getCd_comisuba_pol() {
		return cd_comisuba_pol;
	}
	public void setCd_comisuba_pol(Integer cd_comisuba_pol) {
		this.cd_comisuba_pol = cd_comisuba_pol;
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
	public Double getTotal_prima() {
		return total_prima;
	}
	public void setTotal_prima(Double total_prima) {
		this.total_prima = total_prima;
	}
	public Double getPct_com_suba() {
		return pct_com_suba;
	}
	public void setPct_com_suba(Double pct_com_suba) {
		this.pct_com_suba = pct_com_suba;
	}
	public Double getVal_com_suba() {
		return val_com_suba;
	}
	public void setVal_com_suba(Double val_com_suba) {
		this.val_com_suba = val_com_suba;
	}
	public Double getSaldo_com_suba() {
		return saldo_com_suba;
	}
	public void setSaldo_com_suba(Double saldo_com_suba) {
		this.saldo_com_suba = saldo_com_suba;
	}
	public Integer getFlg_pago() {
		return flg_pago;
	}
	public void setFlg_pago(Integer flg_pago) {
		this.flg_pago = flg_pago;
	}
	public Date getFc_pago_suba() {
		return fc_pago_suba;
	}
	public void setFc_pago_suba(Date fc_pago_suba) {
		this.fc_pago_suba = fc_pago_suba;
	}
	public String getNum_Factura_suba() {
		return num_Factura_suba;
	}
	public void setNum_Factura_suba(String num_Factura_suba) {
		this.num_Factura_suba = num_Factura_suba;
	}
	public Integer getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(Integer cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}
	
	
}
