/**
 * 
 */
package confia.entidades.transaccionales;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "remision_tbl")
public class Remision implements Serializable {
	private static final long serialVersionUID = -2886912502106505467L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_remision")
	@SequenceGenerator(sequenceName = "secuencia_cd_remision", name = "secuencia_cd_remision", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_remision")
	private Long cd_remision;
	@Column(name="cd_compania")
	private Long cd_compania;
	@Column(name="cd_det_pago")
	private Long cd_det_pago;
	@Column(name="cd_cliente")
	private Long cd_cliente;
	@Column(name="cd_aseguradora")
	private Long cd_aseguradora;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="estado")
	private Integer estado;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="num_remision")
	private String num_remision;
	
	public Long getCd_remision() {
		return cd_remision;
	}
	public void setCd_remision(Long cd_remision) {
		this.cd_remision = cd_remision;
	}
	public Long getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Long cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Long getCd_det_pago() {
		return cd_det_pago;
	}
	public void setCd_det_pago(Long cd_det_pago) {
		this.cd_det_pago = cd_det_pago;
	}
	public Long getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(Long cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public Long getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Long cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNum_remision() {
		return num_remision;
	}
	public void setNum_remision(String num_remision) {
		this.num_remision = num_remision;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd_aseguradora == null) ? 0 : cd_aseguradora.hashCode());
		result = prime * result + ((cd_cliente == null) ? 0 : cd_cliente.hashCode());
		result = prime * result + ((cd_compania == null) ? 0 : cd_compania.hashCode());
		result = prime * result + ((cd_det_pago == null) ? 0 : cd_det_pago.hashCode());
		result = prime * result + ((cd_remision == null) ? 0 : cd_remision.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((num_remision == null) ? 0 : num_remision.hashCode());
		result = prime * result + ((observaciones == null) ? 0 : observaciones.hashCode());
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
		Remision other = (Remision) obj;
		if (cd_aseguradora == null) {
			if (other.cd_aseguradora != null)
				return false;
		} else if (!cd_aseguradora.equals(other.cd_aseguradora))
			return false;
		if (cd_cliente == null) {
			if (other.cd_cliente != null)
				return false;
		} else if (!cd_cliente.equals(other.cd_cliente))
			return false;
		if (cd_compania == null) {
			if (other.cd_compania != null)
				return false;
		} else if (!cd_compania.equals(other.cd_compania))
			return false;
		if (cd_det_pago == null) {
			if (other.cd_det_pago != null)
				return false;
		} else if (!cd_det_pago.equals(other.cd_det_pago))
			return false;
		if (cd_remision == null) {
			if (other.cd_remision != null)
				return false;
		} else if (!cd_remision.equals(other.cd_remision))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (num_remision == null) {
			if (other.num_remision != null)
				return false;
		} else if (!num_remision.equals(other.num_remision))
			return false;
		if (observaciones == null) {
			if (other.observaciones != null)
				return false;
		} else if (!observaciones.equals(other.observaciones))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Remision [cd_remision=" + cd_remision + ", cd_compania=" + cd_compania + ", cd_det_pago=" + cd_det_pago
				+ ", cd_cliente=" + cd_cliente + ", cd_aseguradora=" + cd_aseguradora + ", observaciones="
				+ observaciones + ", estado=" + estado + ", fecha=" + fecha + ", num_remision=" + num_remision + "]";
	}

	
	
}
