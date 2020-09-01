package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prefacturadetalle_view")
public class PreFacturaDetalleView {
	
	@Id
	@Column(name="cd_detalle_pre_factura")
	private Integer cd_detalle_pre_factura;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_pre_factura")
	private Integer cd_pre_factura;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="cd_ramo")
	private Integer cd_ramo;
	@Column(name="poliza")
	private String poliza;
	@Column(name="factura")
	private String factura;
	@Column(name="tot_prima")
	private Double tot_prima;
	@Column(name="pct_com_broker")
	private Double pct_com_broker;
	@Column(name="val_com_broker")
	private Double val_com_broker;
	@Column(name="cd_comision_poliza")
	private Integer cd_comision_poliza;
	@Column(name="cliente")
	private String cliente;
	@Column(name="identificacion_cliente")
	private String identificacion_cliente;
	@Column(name="desc_ramo")
	private String desc_ramo;
	public Integer getCd_detalle_pre_factura() {
		return cd_detalle_pre_factura;
	}
	public void setCd_detalle_pre_factura(Integer cd_detalle_pre_factura) {
		this.cd_detalle_pre_factura = cd_detalle_pre_factura;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_pre_factura() {
		return cd_pre_factura;
	}
	public void setCd_pre_factura(Integer cd_pre_factura) {
		this.cd_pre_factura = cd_pre_factura;
	}
	public Integer getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public Integer getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(Integer cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public Double getTot_prima() {
		return tot_prima;
	}
	public void setTot_prima(Double tot_prima) {
		this.tot_prima = tot_prima;
	}
	public Double getPct_com_broker() {
		return pct_com_broker;
	}
	public void setPct_com_broker(Double pct_com_broker) {
		this.pct_com_broker = pct_com_broker;
	}
	public Double getVal_com_broker() {
		return val_com_broker;
	}
	public void setVal_com_broker(Double val_com_broker) {
		this.val_com_broker = val_com_broker;
	}
	public Integer getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(Integer cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}
	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}
	public String getDesc_ramo() {
		return desc_ramo;
	}
	public void setDesc_ramo(String desc_ramo) {
		this.desc_ramo = desc_ramo;
	}
	

}
