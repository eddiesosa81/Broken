/**
 * 
 */
package confia.entidades.transaccionales;

import java.io.Serializable;

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
@Table(name = "COMISION_POLIZA_TBL")
public class ComisionPoliza implements Serializable {
	private static final long serialVersionUID = 5104079976327284959L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "SECUENCIA_CD_COMISION_POLIZA")
	@SequenceGenerator(sequenceName = "SECUENCIA_CD_COMISION_POLIZA", name = "SECUENCIA_CD_COMISION_POLIZA", initialValue= 1, allocationSize = 1000)
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
	private Double flg_factura;
	
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
	public Double getFlg_factura() {
		return flg_factura;
	}
	public void setFlg_factura(Double flg_factura) {
		this.flg_factura = flg_factura;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd_comision_poliza == null) ? 0 : cd_comision_poliza.hashCode());
		result = prime * result + ((cd_compania == null) ? 0 : cd_compania.hashCode());
		result = prime * result + ((cd_ramo_cotizacion == null) ? 0 : cd_ramo_cotizacion.hashCode());
		result = prime * result + ((flg_factura == null) ? 0 : flg_factura.hashCode());
		result = prime * result + ((flg_pre_factura == null) ? 0 : flg_pre_factura.hashCode());
		result = prime * result + ((pct_com_brk == null) ? 0 : pct_com_brk.hashCode());
		result = prime * result + ((saldo_com_brk == null) ? 0 : saldo_com_brk.hashCode());
		result = prime * result + ((total_asegurado == null) ? 0 : total_asegurado.hashCode());
		result = prime * result + ((total_prima == null) ? 0 : total_prima.hashCode());
		result = prime * result + ((val_com_brk == null) ? 0 : val_com_brk.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComisionPoliza other = (ComisionPoliza) obj;
		if (cd_comision_poliza == null) {
			if (other.cd_comision_poliza != null)
				return false;
		} else if (!cd_comision_poliza.equals(other.cd_comision_poliza))
			return false;
		if (cd_compania == null) {
			if (other.cd_compania != null)
				return false;
		} else if (!cd_compania.equals(other.cd_compania))
			return false;
		if (cd_ramo_cotizacion == null) {
			if (other.cd_ramo_cotizacion != null)
				return false;
		} else if (!cd_ramo_cotizacion.equals(other.cd_ramo_cotizacion))
			return false;
		if (flg_factura == null) {
			if (other.flg_factura != null)
				return false;
		} else if (!flg_factura.equals(other.flg_factura))
			return false;
		if (flg_pre_factura == null) {
			if (other.flg_pre_factura != null)
				return false;
		} else if (!flg_pre_factura.equals(other.flg_pre_factura))
			return false;
		if (pct_com_brk == null) {
			if (other.pct_com_brk != null)
				return false;
		} else if (!pct_com_brk.equals(other.pct_com_brk))
			return false;
		if (saldo_com_brk == null) {
			if (other.saldo_com_brk != null)
				return false;
		} else if (!saldo_com_brk.equals(other.saldo_com_brk))
			return false;
		if (total_asegurado == null) {
			if (other.total_asegurado != null)
				return false;
		} else if (!total_asegurado.equals(other.total_asegurado))
			return false;
		if (total_prima == null) {
			if (other.total_prima != null)
				return false;
		} else if (!total_prima.equals(other.total_prima))
			return false;
		if (val_com_brk == null) {
			if (other.val_com_brk != null)
				return false;
		} else if (!val_com_brk.equals(other.val_com_brk))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ComisionPoliza [cd_comision_poliza=" + cd_comision_poliza + ", cd_compania=" + cd_compania
				+ ", cd_ramo_cotizacion=" + cd_ramo_cotizacion + ", total_asegurado=" + total_asegurado
				+ ", total_prima=" + total_prima + ", pct_com_brk=" + pct_com_brk + ", val_com_brk=" + val_com_brk
				+ ", saldo_com_brk=" + saldo_com_brk + ", flg_pre_factura=" + flg_pre_factura + ", flg_factura="
				+ flg_factura + "]";
	}

	
	
}
