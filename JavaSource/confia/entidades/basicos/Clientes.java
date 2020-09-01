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
@Table(name = "CLIENTE_TBL")
public class Clientes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_cliente")
	@SequenceGenerator(sequenceName = "secuencia_cd_cliente", name = "secuencia_cd_cliente", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	
	@Column(name="tipo_persona_cliente")
	private String tipo_persona_cliente;
	
	@Column(name="tipo_identificacion_cliente")
	private String tipo_identificacion_cliente;
	
	@Column(name="identificacion_cliente")
	private String identificacion_cliente;
	
	@Column(name="primer_nombre_cliente")
	private String primer_nombre_cliente;
	
	@Column(name="segundo_nombre_cliente")
	private String segundo_nombre_cliente;
	
	@Column(name="primer_apellido_cliente")
	private String primer_apellido_cliente;
	
	@Column(name="segundo_apellido_cliente")
	private String segundo_apellido_cliente;
	
	@Column(name="razon_social_cliente")
	private String razon_social_cliente;
	
	@Column(name="genero_cliente")
	private String genero_cliente;
	
	@Column(name="vivienda_propia_cliente")
	private String vivienda_propia_cliente;
	
	@Column(name="tamano_familia_cliente")
	private Integer tamano_familia_cliente;
	
	@Column(name="nivel_socio_economico_cliente")
	private String nivel_socio_economico_cliente;
	
	@Column(name="estado_civil_cliente")
	private String estado_civil_cliente;
	
	@Column(name="fecha_nacimiento_cliente")
	private Date fecha_nacimiento_cliente;
	
	@Column(name="calificacion_cliente")
	private String calificacion_cliente;
	
	@Column(name="cd_nacionalidad")
	private Integer cd_nacionalidad;
	
	@Column(name="cd_canton")
	private Integer cd_canton;
	
	@Column(name="tipo_cliente")
	private String tipo_cliente;
	
	@Column(name="fecha_creacion_cliente")
	private Date fecha_creacion_cliente;
	
	@Column(name="estado_cliente")
	private String estado_cliente;
	
	@Column(name="empresa")
	private String empresa;

	public Integer getCd_cliente() {
		return cd_cliente;
	}

	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}

	public String getTipo_persona_cliente() {
		return tipo_persona_cliente;
	}

	public void setTipo_persona_cliente(String tipo_persona_cliente) {
		this.tipo_persona_cliente = tipo_persona_cliente;
	}

	public String getTipo_identificacion_cliente() {
		return tipo_identificacion_cliente;
	}

	public void setTipo_identificacion_cliente(String tipo_identificacion_cliente) {
		this.tipo_identificacion_cliente = tipo_identificacion_cliente;
	}

	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}

	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}

	public String getPrimer_nombre_cliente() {
		return primer_nombre_cliente;
	}

	public void setPrimer_nombre_cliente(String primer_nombre_cliente) {
		this.primer_nombre_cliente = primer_nombre_cliente;
	}

	public String getSegundo_nombre_cliente() {
		return segundo_nombre_cliente;
	}

	public void setSegundo_nombre_cliente(String segundo_nombre_cliente) {
		this.segundo_nombre_cliente = segundo_nombre_cliente;
	}

	public String getPrimer_apellido_cliente() {
		return primer_apellido_cliente;
	}

	public void setPrimer_apellido_cliente(String primer_apellido_cliente) {
		this.primer_apellido_cliente = primer_apellido_cliente;
	}

	public String getSegundo_apellido_cliente() {
		return segundo_apellido_cliente;
	}

	public void setSegundo_apellido_cliente(String segundo_apellido_cliente) {
		this.segundo_apellido_cliente = segundo_apellido_cliente;
	}

	public String getRazon_social_cliente() {
		return razon_social_cliente;
	}

	public void setRazon_social_cliente(String razon_social_cliente) {
		this.razon_social_cliente = razon_social_cliente;
	}

	public String getGenero_cliente() {
		return genero_cliente;
	}

	public void setGenero_cliente(String genero_cliente) {
		this.genero_cliente = genero_cliente;
	}

	public String getVivienda_propia_cliente() {
		return vivienda_propia_cliente;
	}

	public void setVivienda_propia_cliente(String vivienda_propia_cliente) {
		this.vivienda_propia_cliente = vivienda_propia_cliente;
	}

	public Integer getTamano_familia_cliente() {
		return tamano_familia_cliente;
	}

	public void setTamano_familia_cliente(Integer tamano_familia_cliente) {
		this.tamano_familia_cliente = tamano_familia_cliente;
	}

	public String getNivel_socio_economico_cliente() {
		return nivel_socio_economico_cliente;
	}

	public void setNivel_socio_economico_cliente(String nivel_socio_economico_cliente) {
		this.nivel_socio_economico_cliente = nivel_socio_economico_cliente;
	}

	public String getEstado_civil_cliente() {
		return estado_civil_cliente;
	}

	public void setEstado_civil_cliente(String estado_civil_cliente) {
		this.estado_civil_cliente = estado_civil_cliente;
	}

	public Date getFecha_nacimiento_cliente() {
		return fecha_nacimiento_cliente;
	}

	public void setFecha_nacimiento_cliente(Date fecha_nacimiento_cliente) {
		this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
	}

	public String getCalificacion_cliente() {
		return calificacion_cliente;
	}

	public void setCalificacion_cliente(String calificacion_cliente) {
		this.calificacion_cliente = calificacion_cliente;
	}

	public Integer getCd_nacionalidad() {
		return cd_nacionalidad;
	}

	public void setCd_nacionalidad(Integer cd_nacionalidad) {
		this.cd_nacionalidad = cd_nacionalidad;
	}

	public Integer getCd_canton() {
		return cd_canton;
	}

	public void setCd_canton(Integer cd_canton) {
		this.cd_canton = cd_canton;
	}

	public String getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

	public Date getFecha_creacion_cliente() {
		return fecha_creacion_cliente;
	}

	public void setFecha_creacion_cliente(Date fecha_creacion_cliente) {
		this.fecha_creacion_cliente = fecha_creacion_cliente;
	}

	public String getEstado_cliente() {
		return estado_cliente;
	}

	public void setEstado_cliente(String estado_cliente) {
		this.estado_cliente = estado_cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	
}
