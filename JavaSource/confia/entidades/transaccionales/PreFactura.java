/**
 * 
 */
package confia.entidades.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Guido Guerrero
 *
 */

@Entity
@Table(name = "PRE_FACTURA_TBL")
public class PreFactura {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_PRE_FACTURA")
	@SequenceGenerator(sequenceName = "secuencia_CD_PRE_FACTURA", name = "secuencia_CD_PRE_FACTURA", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_pre_factura")
	private Integer cd_pre_factura;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="fc_pre_factura")
	private Date fc_pre_factura;
	@Column(name="val_pre_factura")
	private Double val_pre_factura;
	@Column(name="val_pre_iva")
	private Double val_pre_iva;
	@Column(name="flg_factura")
	private Integer flg_factura;
	@Column(name="flg_saldar_centavo")
	private Integer flg_saldar_centavo;
	
	
	
	@Transient
	private String nombreAseg;
	
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
	public String getNombreAseg() {
		return nombreAseg;
	}
	public void setNombreAseg(String nombreAseg) {
		this.nombreAseg = nombreAseg;
	}
	public Double getVal_pre_iva() {
		return val_pre_iva;
	}
	public void setVal_pre_iva(Double val_pre_iva) {
		this.val_pre_iva = val_pre_iva;
	}
	public Integer getFlg_factura() {
		return flg_factura;
	}
	public void setFlg_factura(Integer flg_factura) {
		this.flg_factura = flg_factura;
	}
	public Integer getFlg_saldar_centavo() {
		return flg_saldar_centavo;
	}
	public void setFlg_saldar_centavo(Integer flg_saldar_centavo) {
		this.flg_saldar_centavo = flg_saldar_centavo;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((cd_aseguradora == null) ? 0 : cd_aseguradora.hashCode());
//		result = prime * result + ((cd_compania == null) ? 0 : cd_compania.hashCode());
//		result = prime * result + ((cd_pre_factura == null) ? 0 : cd_pre_factura.hashCode());
//		result = prime * result + ((fc_pre_factura == null) ? 0 : fc_pre_factura.hashCode());
//		result = prime * result + ((val_pre_factura == null) ? 0 : val_pre_factura.hashCode());
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
//		PreFactura other = (PreFactura) obj;
//		if (cd_aseguradora == null) {
//			if (other.cd_aseguradora != null)
//				return false;
//		} else if (!cd_aseguradora.equals(other.cd_aseguradora))
//			return false;
//		if (cd_compania == null) {
//			if (other.cd_compania != null)
//				return false;
//		} else if (!cd_compania.equals(other.cd_compania))
//			return false;
//		if (cd_pre_factura == null) {
//			if (other.cd_pre_factura != null)
//				return false;
//		} else if (!cd_pre_factura.equals(other.cd_pre_factura))
//			return false;
//		if (fc_pre_factura == null) {
//			if (other.fc_pre_factura != null)
//				return false;
//		} else if (!fc_pre_factura.equals(other.fc_pre_factura))
//			return false;
//		if (val_pre_factura == null) {
//			if (other.val_pre_factura != null)
//				return false;
//		} else if (!val_pre_factura.equals(other.val_pre_factura))
//			return false;
//		return true;
//	}
//	@Override
//	public String toString() {
//		return "PreFactura [cd_pre_factura=" + cd_pre_factura + ", cd_compania=" + cd_compania + ", cd_aseguradora="
//				+ cd_aseguradora + ", fc_pre_factura=" + fc_pre_factura + ", val_pre_factura=" + val_pre_factura + "]";
//	}

	
	
}
