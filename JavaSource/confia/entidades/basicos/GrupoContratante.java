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
@Table(name = "GRUPO_CONTRATANTE_TBL")
public class GrupoContratante implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_grupo_contratante")
	@SequenceGenerator(sequenceName = "secuencia_grupo_contratante", name = "secuencia_grupo_contratante", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_grupo_contratante")
	private Integer cd_grupo_contratante;
	
	@Column(name="razon_social_grupo_contratante")
	private String razon_social_grupo_contratante;
	
	@Column(name="ruc_grupo_contratante")
	private String ruc_grupo_contratante;
	
	@Column(name="estado_grupo_contratante")
	private String estado_grupo_contratante;
	
	@Column(name="nombre_corto_grupo_contratante")
	private String nombre_corto_grupo_contratante;

	@Column(name="facturacionperiodica")
	private Integer facturacionperiodica;
	
	public Integer getFacturacionperiodica() {
		return facturacionperiodica;
	}

	public void setFacturacionperiodica(Integer facturacionperiodica) {
		this.facturacionperiodica = facturacionperiodica;
	}

	public Integer getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}

	public void setCd_grupo_contratante(Integer cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}

	public String getRazon_social_grupo_contratante() {
		return razon_social_grupo_contratante;
	}

	public void setRazon_social_grupo_contratante(String razon_social_grupo_contratante) {
		this.razon_social_grupo_contratante = razon_social_grupo_contratante;
	}

	public String getRuc_grupo_contratante() {
		return ruc_grupo_contratante;
	}

	public void setRuc_grupo_contratante(String ruc_grupo_contratante) {
		this.ruc_grupo_contratante = ruc_grupo_contratante;
	}

	public String getEstado_grupo_contratante() {
		return estado_grupo_contratante;
	}

	public void setEstado_grupo_contratante(String estado_grupo_contratante) {
		this.estado_grupo_contratante = estado_grupo_contratante;
	}

	public String getNombre_corto_grupo_contratante() {
		return nombre_corto_grupo_contratante;
	}

	public void setNombre_corto_grupo_contratante(String nombre_corto_grupo_contratante) {
		this.nombre_corto_grupo_contratante = nombre_corto_grupo_contratante;
	}
	
}
