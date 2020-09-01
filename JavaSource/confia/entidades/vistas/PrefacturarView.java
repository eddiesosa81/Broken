/**
 * 
 */
package confia.entidades.vistas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "prefacturar_view")
public class PrefacturarView {
	
	@Id
	@Column(name="cd_pre_factura")
	private Integer cd_pre_factura;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="fc_pre_factura")
	private Date fc_pre_factura;
	@Column(name="val_pre_factura")
	private Double val_pre_factura;
	@Column(name="val_pre_iva")
	private Double val_pre_iva;
	@Column(name="total")
	private Double total;
	@Column(name="flg_factura")
	private Integer flgFactura;
	@Column(name="flg_saldar_centavo")
	private Integer flg_saldar_centavo;
	public Integer getCd_pre_factura() {
		return cd_pre_factura;
	}
	public void setCd_pre_factura(Integer cd_pre_factura) {
		this.cd_pre_factura = cd_pre_factura;
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
	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}
	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}
	public Date getFc_pre_factura() {
		return fc_pre_factura;
	}
	public void setFc_pre_factura(Date fc_pre_factura) {
		this.fc_pre_factura = fc_pre_factura;
	}
	public Double getVal_pre_factura() {
		return val_pre_factura;
	}
	public void setVal_pre_factura(Double val_pre_factura) {
		this.val_pre_factura = val_pre_factura;
	}
	public Double getVal_pre_iva() {
		return val_pre_iva;
	}
	public void setVal_pre_iva(Double val_pre_iva) {
		this.val_pre_iva = val_pre_iva;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getFlgFactura() {
		return flgFactura;
	}
	public void setFlgFactura(Integer flgFactura) {
		this.flgFactura = flgFactura;
	}
	public Integer getFlg_saldar_centavo() {
		return flg_saldar_centavo;
	}
	public void setFlg_saldar_centavo(Integer flg_saldar_centavo) {
		this.flg_saldar_centavo = flg_saldar_centavo;
	}

	
}
