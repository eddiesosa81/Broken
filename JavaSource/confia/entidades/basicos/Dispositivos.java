package confia.entidades.basicos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dispositivo_tbl")
public class Dispositivos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_DISPOSITIVO")
	@SequenceGenerator(sequenceName = "secuencia_CD_DISPOSITIVO", name = "secuencia_CD_DISPOSITIVO", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_dispositivo")
	private Integer cd_dispositivo;
	
	@Column(name="descripcion_dispositivo")
	private String descripcion_dispositivo;
	
	@Column(name="estado_dispositivo")
	private String estado_dispositivo;

	public Integer getCd_dispositivo() {
		return cd_dispositivo;
	}

	public void setCd_dispositivo(Integer cd_dispositivo) {
		this.cd_dispositivo = cd_dispositivo;
	}

	public String getDescripcion_dispositivo() {
		return descripcion_dispositivo;
	}

	public void setDescripcion_dispositivo(String descripcion_dispositivo) {
		this.descripcion_dispositivo = descripcion_dispositivo;
	}

	public String getEstado_dispositivo() {
		return estado_dispositivo;
	}

	public void setEstado_dispositivo(String estado_dispositivo) {
		this.estado_dispositivo = estado_dispositivo;
	}
}
