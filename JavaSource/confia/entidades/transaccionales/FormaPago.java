package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FORMA_PAGO_TBL")
public class FormaPago {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_FORMA_PAGO")
	@SequenceGenerator(sequenceName = "secuencia_CD_FORMA_PAGO", name = "secuencia_CD_FORMA_PAGO", initialValue= 1, allocationSize = 1000)
	@Column(name="CD_FORMA_PAGO")
	private Integer cd_forma_Pago;
	@Column(name="CD_COMPANIA")
	private Integer cd_compania;
	@Column(name="CD_COTIZACION")
	private Integer cd_cotizacion;
	@Column(name="NUM_ALTERNATIVA_FORMAPAGO")
	private String num_alternativa_formaPago;
	@Column(name="NUM_PAGOS_FORMAPAGO")
	private Integer num_pago_formaPago;
	@Column(name="TOTAL_PRIMA_FORMAPAGO")
	private Double total_prima_frmPago;
	@Column(name="TOTAL_PAGO_FORMAPAGO")
	private Double total_Pago_FormaPago;
	@Column(name="PCT_CUOTA_INICIAL_FORMAPAGO")
	private Double pct_cuota_Inicial_frmPago;
	@Column(name="DERECHO_EMISION_FORMAPAGO")
	private Double derecho_Emision_formaPago;
	@Column(name="IVA_FORMAPAGO")
	private Double iva_Forma_Pago;
	@Column(name="SUPER_BANCOS_FORMAPAGO")
	private Double superBanco_forma_Pago;
	@Column(name="SEGURO_CAMPESINO")
	private Double seguroCampesion_forma_Pago;
	@Column(name="CUOTA_LETRA")
	private Double cuotaLetraFormaPago;
	@Column(name="sin_iva")
	private Integer sin_iva;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="otro_valor")
	private Double otro_valor_forma_Pago;
	
	
	public Integer getCd_forma_Pago() {
		return cd_forma_Pago;
	}
	public void setCd_forma_Pago(Integer cd_forma_Pago) {
		this.cd_forma_Pago = cd_forma_Pago;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public String getNum_alternativa_formaPago() {
		return num_alternativa_formaPago;
	}
	public void setNum_alternativa_formaPago(String num_alternativa_formaPago) {
		this.num_alternativa_formaPago = num_alternativa_formaPago;
	}
	public Integer getNum_pago_formaPago() {
		return num_pago_formaPago;
	}
	public void setNum_pago_formaPago(Integer num_pago_formaPago) {
		this.num_pago_formaPago = num_pago_formaPago;
	}
	public Double getTotal_prima_frmPago() {
		return total_prima_frmPago;
	}
	public void setTotal_prima_frmPago(Double total_prima_frmPago) {
		this.total_prima_frmPago = total_prima_frmPago;
	}
	public Double getTotal_Pago_FormaPago() {
		return total_Pago_FormaPago;
	}
	public void setTotal_Pago_FormaPago(Double total_Pago_FormaPago) {
		this.total_Pago_FormaPago = total_Pago_FormaPago;
	}
	public Double getPct_cuota_Inicial_frmPago() {
		return pct_cuota_Inicial_frmPago;
	}
	public void setPct_cuota_Inicial_frmPago(Double pct_cuota_Inicial_frmPago) {
		this.pct_cuota_Inicial_frmPago = pct_cuota_Inicial_frmPago;
	}
	public Double getDerecho_Emision_formaPago() {
		return derecho_Emision_formaPago;
	}
	public void setDerecho_Emision_formaPago(Double derecho_Emision_formaPago) {
		this.derecho_Emision_formaPago = derecho_Emision_formaPago;
	}
	public Double getIva_Forma_Pago() {
		return iva_Forma_Pago;
	}
	public void setIva_Forma_Pago(Double iva_Forma_Pago) {
		this.iva_Forma_Pago = iva_Forma_Pago;
	}
	public Double getSuperBanco_forma_Pago() {
		return superBanco_forma_Pago;
	}
	public void setSuperBanco_forma_Pago(Double superBanco_forma_Pago) {
		this.superBanco_forma_Pago = superBanco_forma_Pago;
	}
	public Double getSeguroCampesion_forma_Pago() {
		return seguroCampesion_forma_Pago;
	}
	public void setSeguroCampesion_forma_Pago(Double seguroCampesion_forma_Pago) {
		this.seguroCampesion_forma_Pago = seguroCampesion_forma_Pago;
	}
	public Double getOtro_valor_forma_Pago() {
		return otro_valor_forma_Pago;
	}
	public void setOtro_valor_forma_Pago(Double otro_valor_forma_Pago) {
		this.otro_valor_forma_Pago = otro_valor_forma_Pago;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getSin_iva() {
		return sin_iva;
	}
	public void setSin_iva(Integer sin_iva) {
		this.sin_iva = sin_iva;
	}
	public Double getCuotaLetraFormaPago() {
		return cuotaLetraFormaPago;
	}
	public void setCuotaLetraFormaPago(Double cuotaLetraFormaPago) {
		this.cuotaLetraFormaPago = cuotaLetraFormaPago;
	}
	
	
}
