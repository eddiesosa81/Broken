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
@Table(name = "FACTURAS_TBL")
public class Factura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_FACTURA")
	@SequenceGenerator(sequenceName = "secuencia_CD_FACTURA", name = "secuencia_CD_FACTURA", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_factura")
	private Integer cd_factura;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="num_factura")
	private String num_factura;
	@Column(name="fc_factura")
	private Date fc_factura;
	@Column(name="val_factura")
	private Double val_factura;
	@Column(name="iva")
	private Double iva;
	@Column(name="tot_factura")
	private Double tot_factura;
	@Column(name="fc_anula")
	private Date fc_anula;
	
	@Transient
	private String nom_aseguradora;
	
	public Integer getCd_factura() {
		return cd_factura;
	}
	public void setCd_factura(Integer cd_factura) {
		this.cd_factura = cd_factura;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public String getNum_factura() {
		return num_factura;
	}
	public void setNum_factura(String num_factura) {
		this.num_factura = num_factura;
	}
	public Date getFc_factura() {
		return fc_factura;
	}
	public void setFc_factura(Date fc_factura) {
		this.fc_factura = fc_factura;
	}
	public Double getVal_factura() {
		return val_factura;
	}
	public void setVal_factura(Double val_factura) {
		this.val_factura = val_factura;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getTot_factura() {
		return tot_factura;
	}
	public void setTot_factura(Double tot_factura) {
		this.tot_factura = tot_factura;
	}
	public Date getFc_anula() {
		return fc_anula;
	}
	public void setFc_anula(Date fc_anula) {
		this.fc_anula = fc_anula;
	}
	
	@Transient
	public String getNom_aseguradora() {
		return nom_aseguradora;
	}
	
	@Transient
	public void setNom_aseguradora(String nom_aseguradora) {
		this.nom_aseguradora = nom_aseguradora;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd_aseguradora == null) ? 0 : cd_aseguradora.hashCode());
		result = prime * result + ((cd_compania == null) ? 0 : cd_compania.hashCode());
		result = prime * result + ((cd_factura == null) ? 0 : cd_factura.hashCode());
		result = prime * result + ((fc_anula == null) ? 0 : fc_anula.hashCode());
		result = prime * result + ((fc_factura == null) ? 0 : fc_factura.hashCode());
		result = prime * result + ((iva == null) ? 0 : iva.hashCode());
		result = prime * result + ((num_factura == null) ? 0 : num_factura.hashCode());
		result = prime * result + ((tot_factura == null) ? 0 : tot_factura.hashCode());
		result = prime * result + ((val_factura == null) ? 0 : val_factura.hashCode());
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
		Factura other = (Factura) obj;
		if (cd_aseguradora == null) {
			if (other.cd_aseguradora != null)
				return false;
		} else if (!cd_aseguradora.equals(other.cd_aseguradora))
			return false;
		if (cd_compania == null) {
			if (other.cd_compania != null)
				return false;
		} else if (!cd_compania.equals(other.cd_compania))
			return false;
		if (cd_factura == null) {
			if (other.cd_factura != null)
				return false;
		} else if (!cd_factura.equals(other.cd_factura))
			return false;
		if (fc_anula == null) {
			if (other.fc_anula != null)
				return false;
		} else if (!fc_anula.equals(other.fc_anula))
			return false;
		if (fc_factura == null) {
			if (other.fc_factura != null)
				return false;
		} else if (!fc_factura.equals(other.fc_factura))
			return false;
		if (iva == null) {
			if (other.iva != null)
				return false;
		} else if (!iva.equals(other.iva))
			return false;
		if (num_factura == null) {
			if (other.num_factura != null)
				return false;
		} else if (!num_factura.equals(other.num_factura))
			return false;
		if (tot_factura == null) {
			if (other.tot_factura != null)
				return false;
		} else if (!tot_factura.equals(other.tot_factura))
			return false;
		if (val_factura == null) {
			if (other.val_factura != null)
				return false;
		} else if (!val_factura.equals(other.val_factura))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Factura [cd_factura=" + cd_factura + ", cd_aseguradora=" + cd_aseguradora + ", cd_compania="
				+ cd_compania + ", num_factura=" + num_factura + ", fc_factura=" + fc_factura + ", val_factura="
				+ val_factura + ", iva=" + iva + ", tot_factura=" + tot_factura + ", fc_anula=" + fc_anula + "]";
	}

	
}
