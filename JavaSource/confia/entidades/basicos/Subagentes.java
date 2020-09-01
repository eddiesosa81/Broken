package confia.entidades.basicos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SUBAGENTE_TBL")
public class Subagentes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_subagente")
	@SequenceGenerator(sequenceName = "secuencia_cd_subagente", name = "secuencia_cd_subagente", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_subagente")
	private Integer cd_subagente;
	
	@Column(name="tipo_persona_subagente")
	private String tipo_persona_subagente;
	
	@Column(name="tipo_identificacion_subagente")
	private String tipo_identificacion_subagente;
	
	@Column(name="identificacion_subagente")
	private String identificacion_subagente;
	
	@Column(name="primer_nombre_subagente")
	private String primer_nombre_subagente;
	
	@Column(name="segundo_nombre_subagente")
	private String segundo_nombre_subagente;
	
	@Column(name="primer_apellido_subagente")
	private String primer_apellido_subagente;
	
	@Column(name="segundo_apellido_subagente")
	private String segundo_apellido_subagente;
	
	@Column(name="razonsocial_subagente")
	private String razonSocial_subagente;
	
	@Column(name="fecha_nacimiento_subagente")
	private Date fecha_nacimiento_subagente;
	
	@Column(name="fecha_creacion_subagente")
	private Date fecha_creacion_subagente;
	
	@Column(name="estado_subagente")
	private String estado_subagente;

	@Column(name="ciudad_canal")
	private String ciudad_canal;
	
	

	public String getCiudad_canal() {
		return ciudad_canal;
	}

	public void setCiudad_canal(String ciudad_canal) {
		this.ciudad_canal = ciudad_canal;
	}

	public Integer getCd_subagente() {
		return cd_subagente;
	}

	public void setCd_subagente(Integer cd_subagente) {
		this.cd_subagente = cd_subagente;
	}

	public String getTipo_persona_subagente() {
		return tipo_persona_subagente;
	}

	public void setTipo_persona_subagente(String tipo_persona_subagente) {
		this.tipo_persona_subagente = tipo_persona_subagente;
	}

	public String getTipo_identificacion_subagente() {
		return tipo_identificacion_subagente;
	}

	public void setTipo_identificacion_subagente(String tipo_identificacion_subagente) {
		this.tipo_identificacion_subagente = tipo_identificacion_subagente;
	}

	public String getIdentificacion_subagente() {
		return identificacion_subagente;
	}

	public void setIdentificacion_subagente(String identificacion_subagente) {
		this.identificacion_subagente = identificacion_subagente;
	}

	public String getPrimer_nombre_subagente() {
		return primer_nombre_subagente;
	}

	public void setPrimer_nombre_subagente(String primer_nombre_subagente) {
		this.primer_nombre_subagente = primer_nombre_subagente;
	}

	public String getSegundo_nombre_subagente() {
		return segundo_nombre_subagente;
	}

	public void setSegundo_nombre_subagente(String segundo_nombre_subagente) {
		this.segundo_nombre_subagente = segundo_nombre_subagente;
	}

	public String getPrimer_apellido_subagente() {
		return primer_apellido_subagente;
	}

	public void setPrimer_apellido_subagente(String primer_apellido_subagente) {
		this.primer_apellido_subagente = primer_apellido_subagente;
	}

	public String getSegundo_apellido_subagente() {
		return segundo_apellido_subagente;
	}

	public void setSegundo_apellido_subagente(String segundo_apellido_subagente) {
		this.segundo_apellido_subagente = segundo_apellido_subagente;
	}

	public String getRazonSocial_subagente() {
		return razonSocial_subagente;
	}

	public void setRazonSocial_subagente(String razonsocial_subagente) {
		this.razonSocial_subagente = razonsocial_subagente;
	}
	
	public Date getFecha_nacimiento_subagente() {
		return fecha_nacimiento_subagente;
	}

	public void setFecha_nacimiento_subagente(Date fecha_nacimiento_subagente) {
		this.fecha_nacimiento_subagente = fecha_nacimiento_subagente;
	}

	public Date getFecha_creacion_subagente() {
		return fecha_creacion_subagente;
	}

	public void setFecha_creacion_subagente(Date fecha_creacion_subagente) {
		this.fecha_creacion_subagente = fecha_creacion_subagente;
	}

	public String getEstado_subagente() {
		return estado_subagente;
	}

	public void setEstado_subagente(String estado_subagente) {
		this.estado_subagente = estado_subagente;
	}

}
