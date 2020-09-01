/**
 * 
 */
package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DETALLE_FACTURA_TBL")
public class FacturaDetalle {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_DETALLE_FAC")
	@SequenceGenerator(sequenceName = "secuencia_CD_DETALLE_FAC", name = "secuencia_CD_DETALLE_FAC", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_detalle_fact")
	private Integer cd_detalle_fact;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_factura")
	private Integer cd_factura;
	@Column(name="val_comision")
	private Double val_comision;
	@Column(name="cd_pre_factura")
	private Integer cd_pre_factura;
	public Integer getCd_detalle_fact() {
		return cd_detalle_fact;
	}
	public void setCd_detalle_fact(Integer cd_detalle_fact) {
		this.cd_detalle_fact = cd_detalle_fact;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_factura() {
		return cd_factura;
	}
	public void setCd_factura(Integer cd_factura) {
		this.cd_factura = cd_factura;
	}
	public Double getVal_comision() {
		return val_comision;
	}
	public void setVal_comision(Double val_comision) {
		this.val_comision = val_comision;
	}
	public Integer getCd_pre_factura() {
		return cd_pre_factura;
	}
	public void setCd_pre_factura(Integer cd_pre_factura) {
		this.cd_pre_factura = cd_pre_factura;
	}
	
}
