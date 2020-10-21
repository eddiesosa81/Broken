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
@Table(name = "EJECUTIVO_TBL")
public class Ejecutivos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_ejecutivo")
	@SequenceGenerator(sequenceName = "secuencia_cd_ejecutivo", name = "secuencia_cd_ejecutivo", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_ejecutivo")
	private Integer cd_ejecutivo;
	
	@Column(name="cedula_ejecutivo")
	private String cedula_ejecutivo;
	
	@Column(name="primer_nombre_ejecutivo")
	private String primer_nombre_ejecutivo;
	
	@Column(name="segundo_nombre_ejecutivo")
	private String segundo_nombre_ejecutivo;
	
	@Column(name="primer_apellido_ejecutivo")
	private String primer_apellido_ejecutivo;
	
	@Column(name="segundo_apellido_ejecutivo")
	private String segundo_apellido_ejecutivo;
	
	@Column(name="estado_ejecutivo")
	private String estado_ejecutivo;
	
	@Column(name="CORREO")
	private String correo;
	
	
	
	
	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getCd_ejecutivo() {
		return cd_ejecutivo;
	}

	public void setCd_ejecutivo(Integer cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}

	public String getCedula_ejecutivo() {
		return cedula_ejecutivo;
	}

	public void setCedula_ejecutivo(String cedula_ejecutivo) {
		this.cedula_ejecutivo = cedula_ejecutivo;
	}

	public String getPrimer_nombre_ejecutivo() {
		return primer_nombre_ejecutivo;
	}

	public void setPrimer_nombre_ejecutivo(String primer_nombre_ejecutivo) {
		this.primer_nombre_ejecutivo = primer_nombre_ejecutivo;
	}

	public String getSegundo_nombre_ejecutivo() {
		return segundo_nombre_ejecutivo;
	}

	public void setSegundo_nombre_ejecutivo(String segundo_nombre_ejecutivo) {
		this.segundo_nombre_ejecutivo = segundo_nombre_ejecutivo;
	}

	public String getPrimer_apellido_ejecutivo() {
		return primer_apellido_ejecutivo;
	}

	public void setPrimer_apellido_ejecutivo(String primer_apellido_ejecutivo) {
		this.primer_apellido_ejecutivo = primer_apellido_ejecutivo;
	}

	public String getSegundo_apellido_ejecutivo() {
		return segundo_apellido_ejecutivo;
	}

	public void setSegundo_apellido_ejecutivo(String segundo_apellido_ejecutivo) {
		this.segundo_apellido_ejecutivo = segundo_apellido_ejecutivo;
	}

	public String getEstado_ejecutivo() {
		return estado_ejecutivo;
	}

	public void setEstado_ejecutivo(String estado_ejecutivo) {
		this.estado_ejecutivo = estado_ejecutivo;
	}

}
