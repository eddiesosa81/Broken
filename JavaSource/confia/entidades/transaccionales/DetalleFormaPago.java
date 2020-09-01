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
@Table(name = "detalle_forma_pago_tbl")
public class DetalleFormaPago {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_DET_FORMA_PAGO")
	@SequenceGenerator(sequenceName = "secuencia_CD_DET_FORMA_PAGO", name = "secuencia_CD_DET_FORMA_PAGO", initialValue= 1, allocationSize = 1000)
	@Column(name="CD_DET_FORMA_PAGO")
	private Integer CD_DET_FORMA_PAGO;
	@Column(name="CD_COMPANIA")
	private Integer CD_COMPANIA;
	@Column(name="CD_FORMA_PAGO")
	private Integer CD_FORMA_PAGO;
	@Column(name="FECHA_VENCIMIENTO")
	private Integer FECHA_VENCIMIENTO;
	@Column(name="FECHA_VENCIMIENTO_DATE")
	private Date FECHA_VENCIMIENTO_DATE;
	@Column(name="VALOR")
	private Double VALOR;
	@Column(name="SALDO")
	private Double SALDO;
	@Column(name="FACTURA_ASEGURADORA")
	private String FACTURA_ASEGURADORA;
	@Column(name="FECHA_INGRESO_FACTURA")
	private Integer FECHA_INGRESO_FACTURA;
	@Column(name="FECHA_INGRESO_FACTURA_DATE")
	private Date FECHA_INGRESO_FACTURA_DATE;
	@Column(name="OBSERVACIONES_DET_FORMA_PAGO")
	private String OBSERVACIONES_DET_FORMA_PAGO;
	@Column(name="FLG_PAGO")
	private Integer FLG_PAGO;
	@Column(name="tipo")
	private String tipo;
	@Column(name="flg_comision")
	private String flg_comision;
	@Column(name="valor_asegurado_mensual")
	private Double valor_asegurado_mensual;
	@Column(name="prima_neta_mensual")
	private Double prima_neta_mensual;
	@Column(name="super_bancos_mensual")
	private Double super_bancos_mensual;
	@Column(name="seguro_campesino_mensual")
	private Double seguro_campesino_mensual;
	@Column(name="derechos_emision_mensual")
	private Double derechos_emision_mensual;
	@Column(name="subtotal_mensual")
	private Double subtotal_mensual;
	@Column(name="iva_mensual")
	private Double iva_mensual;
	@Column(name="prima_total_costo_real_mensual")
	private Double prima_total_costo_real_mensual;
	@Column(name="cuota_fija_nivelacion")
	private Double cuota_fija_nivelacion;
	@Column(name="diferencia_nivelacion")
	private Double diferencia_nivelacion;
	@Column(name="nivelacion_acumulada")
	private Double nivelacion_acumulada;
	@Column(name="prima_neta_registro")
	private Double prima_neta_registro;
	@Column(name="super_bancos_registro")
	private Double super_bancos_registro;
	@Column(name="seguro_campesino_registro")
	private Double seguro_campesino_registro;
	@Column(name="derecho_emision_registro")
	private Double derecho_emision_registro;
	@Column(name="subtotal_registro")
	private Double subtotal_registro;
	@Column(name="iva_registro")
	private Double iva_registro;
	@Column(name="total_registro")
	private Double total_registro;
	@Column(name="fc_inicio_vigencia")
	private Date fc_inicio_vigencia;
	@Column(name="fc_fin_vigencia")
	private Date fc_fin_vigencia;
	
	
	public Integer getCD_DET_FORMA_PAGO() {
		return CD_DET_FORMA_PAGO;
	}
	public void setCD_DET_FORMA_PAGO(Integer cD_DET_FORMA_PAGO) {
		CD_DET_FORMA_PAGO = cD_DET_FORMA_PAGO;
	}
	public Integer getCD_COMPANIA() {
		return CD_COMPANIA;
	}
	public void setCD_COMPANIA(Integer cD_COMPANIA) {
		CD_COMPANIA = cD_COMPANIA;
	}
	public Integer getCD_FORMA_PAGO() {
		return CD_FORMA_PAGO;
	}
	public void setCD_FORMA_PAGO(Integer cD_FORMA_PAGO) {
		CD_FORMA_PAGO = cD_FORMA_PAGO;
	}
	public Integer getFECHA_VENCIMIENTO() {
		return FECHA_VENCIMIENTO;
	}
	public void setFECHA_VENCIMIENTO(Integer fECHA_VENCIMIENTO) {
		FECHA_VENCIMIENTO = fECHA_VENCIMIENTO;
	}
	public Date getFECHA_VENCIMIENTO_DATE() {
		return FECHA_VENCIMIENTO_DATE;
	}
	public void setFECHA_VENCIMIENTO_DATE(Date fECHA_VENCIMIENTO_DATE) {
		FECHA_VENCIMIENTO_DATE = fECHA_VENCIMIENTO_DATE;
	}
	public Double getVALOR() {
		return VALOR;
	}
	public void setVALOR(Double vALOR) {
		VALOR = vALOR;
	}
	public Double getSALDO() {
		return SALDO;
	}
	public void setSALDO(Double sALDO) {
		SALDO = sALDO;
	}
	public String getFACTURA_ASEGURADORA() {
		return FACTURA_ASEGURADORA;
	}
	public void setFACTURA_ASEGURADORA(String fACTURA_ASEGURADORA) {
		FACTURA_ASEGURADORA = fACTURA_ASEGURADORA;
	}
	public Integer getFECHA_INGRESO_FACTURA() {
		return FECHA_INGRESO_FACTURA;
	}
	public void setFECHA_INGRESO_FACTURA(Integer fECHA_INGRESO_FACTURA) {
		FECHA_INGRESO_FACTURA = fECHA_INGRESO_FACTURA;
	}
	public Date getFECHA_INGRESO_FACTURA_DATE() {
		return FECHA_INGRESO_FACTURA_DATE;
	}
	public void setFECHA_INGRESO_FACTURA_DATE(Date fECHA_INGRESO_FACTURA_DATE) {
		FECHA_INGRESO_FACTURA_DATE = fECHA_INGRESO_FACTURA_DATE;
	}
	public String getOBSERVACIONES_DET_FORMA_PAGO() {
		return OBSERVACIONES_DET_FORMA_PAGO;
	}
	public void setOBSERVACIONES_DET_FORMA_PAGO(String oBSERVACIONES_DET_FORMA_PAGO) {
		OBSERVACIONES_DET_FORMA_PAGO = oBSERVACIONES_DET_FORMA_PAGO;
	}
	public Integer getFLG_PAGO() {
		
		return FLG_PAGO;
	}
	public void setFLG_PAGO(Integer fLG_PAGO) {
		FLG_PAGO = fLG_PAGO;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFlg_comision() {
		return flg_comision;
	}
	public void setFlg_comision(String flg_comision) {
		this.flg_comision = flg_comision;
	}
	public Double getValor_asegurado_mensual() {
		return valor_asegurado_mensual;
	}
	public void setValor_asegurado_mensual(Double valor_asegurado_mensual) {
		this.valor_asegurado_mensual = valor_asegurado_mensual;
	}
	public Double getPrima_neta_mensual() {
		return prima_neta_mensual;
	}
	public void setPrima_neta_mensual(Double prima_neta_mensual) {
		this.prima_neta_mensual = prima_neta_mensual;
	}
	public Double getSuper_bancos_mensual() {
		return super_bancos_mensual;
	}
	public void setSuper_bancos_mensual(Double super_bancos_mensual) {
		this.super_bancos_mensual = super_bancos_mensual;
	}
	public Double getSeguro_campesino_mensual() {
		return seguro_campesino_mensual;
	}
	public void setSeguro_campesino_mensual(Double seguro_campesino_mensual) {
		this.seguro_campesino_mensual = seguro_campesino_mensual;
	}
	public Double getDerechos_emision_mensual() {
		return derechos_emision_mensual;
	}
	public void setDerechos_emision_mensual(Double derechos_emision_mensual) {
		this.derechos_emision_mensual = derechos_emision_mensual;
	}
	public Double getSubtotal_mensual() {
		return subtotal_mensual;
	}
	public void setSubtotal_mensual(Double subtotal_mensual) {
		this.subtotal_mensual = subtotal_mensual;
	}
	public Double getIva_mensual() {
		return iva_mensual;
	}
	public void setIva_mensual(Double iva_mensual) {
		this.iva_mensual = iva_mensual;
	}
	public Double getPrima_total_costo_real_mensual() {
		return prima_total_costo_real_mensual;
	}
	public void setPrima_total_costo_real_mensual(Double prima_total_costo_real_mensual) {
		this.prima_total_costo_real_mensual = prima_total_costo_real_mensual;
	}
	public Double getCuota_fija_nivelacion() {
		return cuota_fija_nivelacion;
	}
	public void setCuota_fija_nivelacion(Double cuota_fija_nivelacion) {
		this.cuota_fija_nivelacion = cuota_fija_nivelacion;
	}
	public Double getDiferencia_nivelacion() {
		return diferencia_nivelacion;
	}
	public void setDiferencia_nivelacion(Double diferencia_nivelacion) {
		this.diferencia_nivelacion = diferencia_nivelacion;
	}
	public Double getNivelacion_acumulada() {
		return nivelacion_acumulada;
	}
	public void setNivelacion_acumulada(Double nivelacion_acumulada) {
		this.nivelacion_acumulada = nivelacion_acumulada;
	}
	public Double getPrima_neta_registro() {
		return prima_neta_registro;
	}
	public void setPrima_neta_registro(Double prima_neta_registro) {
		this.prima_neta_registro = prima_neta_registro;
	}
	public Double getSuper_bancos_registro() {
		return super_bancos_registro;
	}
	public void setSuper_bancos_registro(Double super_bancos_registro) {
		this.super_bancos_registro = super_bancos_registro;
	}
	public Double getSeguro_campesino_registro() {
		return seguro_campesino_registro;
	}
	public void setSeguro_campesino_registro(Double seguro_campesino_registro) {
		this.seguro_campesino_registro = seguro_campesino_registro;
	}
	public Double getDerecho_emision_registro() {
		return derecho_emision_registro;
	}
	public void setDerecho_emision_registro(Double derecho_emision_registro) {
		this.derecho_emision_registro = derecho_emision_registro;
	}
	public Double getSubtotal_registro() {
		return subtotal_registro;
	}
	public void setSubtotal_registro(Double subtotal_registro) {
		this.subtotal_registro = subtotal_registro;
	}
	public Double getIva_registro() {
		return iva_registro;
	}
	public void setIva_registro(Double iva_registro) {
		this.iva_registro = iva_registro;
	}
	public Double getTotal_registro() {
		return total_registro;
	}
	public void setTotal_registro(Double total_registro) {
		this.total_registro = total_registro;
	}
	public Date getFc_inicio_vigencia() {
		return fc_inicio_vigencia;
	}
	public void setFc_inicio_vigencia(Date fc_inicio_vigencia) {
		this.fc_inicio_vigencia = fc_inicio_vigencia;
	}
	public Date getFc_fin_vigencia() {
		return fc_fin_vigencia;
	}
	public void setFc_fin_vigencia(Date fc_fin_vigencia) {
		this.fc_fin_vigencia = fc_fin_vigencia;
	}
	
	
	
}
