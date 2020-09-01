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

/**
 * @author Guido Guerrero
 *
 */

@Entity
@Table(name = "DETALLE_PRE_FACTURA_TBL")
public class PreFacturaDetalle {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_DET_PRE_FACTURA")
	@SequenceGenerator(sequenceName = "secuencia_CD_DET_PRE_FACTURA", name = "secuencia_CD_DET_PRE_FACTURA", initialValue= 1, allocationSize = 1000)
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
	@Column(name="saldo_com_broker")
	private Double saldo_com_broker;
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
	public Double getSaldo_com_broker() {
		return saldo_com_broker;
	}
	public void setSaldo_com_broker(Double saldo_com_broker) {
		this.saldo_com_broker = saldo_com_broker;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((cd_cliente == null) ? 0 : cd_cliente.hashCode());
//		result = prime * result + ((cd_comision_poliza == null) ? 0 : cd_comision_poliza.hashCode());
//		result = prime * result + ((cd_compania == null) ? 0 : cd_compania.hashCode());
//		result = prime * result + ((cd_detalle_pre_factura == null) ? 0 : cd_detalle_pre_factura.hashCode());
//		result = prime * result + ((cd_grupo_contratante == null) ? 0 : cd_grupo_contratante.hashCode());
//		result = prime * result + ((cd_pre_factura == null) ? 0 : cd_pre_factura.hashCode());
//		result = prime * result + ((cd_ramo == null) ? 0 : cd_ramo.hashCode());
//		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
//		result = prime * result + ((pct_com_broker == null) ? 0 : pct_com_broker.hashCode());
//		result = prime * result + ((poliza == null) ? 0 : poliza.hashCode());
//		result = prime * result + ((tot_prima == null) ? 0 : tot_prima.hashCode());
//		result = prime * result + ((val_com_broker == null) ? 0 : val_com_broker.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		PreFacturaDetalle other = (PreFacturaDetalle) obj;
//		if (cd_cliente == null) {
//			if (other.cd_cliente != null)
//				return false;
//		} else if (!cd_cliente.equals(other.cd_cliente))
//			return false;
//		if (cd_comision_poliza == null) {
//			if (other.cd_comision_poliza != null)
//				return false;
//		} else if (!cd_comision_poliza.equals(other.cd_comision_poliza))
//			return false;
//		if (cd_compania == null) {
//			if (other.cd_compania != null)
//				return false;
//		} else if (!cd_compania.equals(other.cd_compania))
//			return false;
//		if (cd_detalle_pre_factura == null) {
//			if (other.cd_detalle_pre_factura != null)
//				return false;
//		} else if (!cd_detalle_pre_factura.equals(other.cd_detalle_pre_factura))
//			return false;
//		if (cd_grupo_contratante == null) {
//			if (other.cd_grupo_contratante != null)
//				return false;
//		} else if (!cd_grupo_contratante.equals(other.cd_grupo_contratante))
//			return false;
//		if (cd_pre_factura == null) {
//			if (other.cd_pre_factura != null)
//				return false;
//		} else if (!cd_pre_factura.equals(other.cd_pre_factura))
//			return false;
//		if (cd_ramo == null) {
//			if (other.cd_ramo != null)
//				return false;
//		} else if (!cd_ramo.equals(other.cd_ramo))
//			return false;
//		if (factura == null) {
//			if (other.factura != null)
//				return false;
//		} else if (!factura.equals(other.factura))
//			return false;
//		if (pct_com_broker == null) {
//			if (other.pct_com_broker != null)
//				return false;
//		} else if (!pct_com_broker.equals(other.pct_com_broker))
//			return false;
//		if (poliza == null) {
//			if (other.poliza != null)
//				return false;
//		} else if (!poliza.equals(other.poliza))
//			return false;
//		if (tot_prima == null) {
//			if (other.tot_prima != null)
//				return false;
//		} else if (!tot_prima.equals(other.tot_prima))
//			return false;
//		if (val_com_broker == null) {
//			if (other.val_com_broker != null)
//				return false;
//		} else if (!val_com_broker.equals(other.val_com_broker))
//			return false;
//		return true;
//	}
//	@Override
//	public String toString() {
//		return "PreFacturaDetalle [cd_detalle_pre_factura=" + cd_detalle_pre_factura + ", cd_compania=" + cd_compania
//				+ ", cd_pre_factura=" + cd_pre_factura + ", cd_cliente=" + cd_cliente + ", cd_ramo=" + cd_ramo
//				+ ", poliza=" + poliza + ", factura=" + factura + ", tot_prima=" + tot_prima + ", pct_com_broker="
//				+ pct_com_broker + ", val_com_broker=" + val_com_broker + ", cd_comision_poliza=" + cd_comision_poliza
//				+ ", cd_grupo_contratante=" + cd_grupo_contratante + "]";
//	}
//
	
}
