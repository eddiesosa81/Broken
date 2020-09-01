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
@Table(name = "ASEGURADORA_TBL")
public class Aseguradoras implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_aseguradora")
	@SequenceGenerator(sequenceName = "secuencia_cd_aseguradora", name = "secuencia_cd_aseguradora", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	
	@Column(name="ruc_aseguradora")
	private String ruc_aseguradora;
	
	@Column(name="razon_social_aseguradora")
	private String razon_social_aseguradora;
	
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	
	@Column(name="tipo_contribuyente_aseguradora")
	private String tipo_contribuyente_aseguradora;
	
	@Column(name="estado_aseguradora")
	private String estado_aseguradora;

	

	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}

	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}

	public String getRuc_aseguradora() {
		return ruc_aseguradora;
	}

	public void setRuc_aseguradora(String ruc_aseguradora) {
		this.ruc_aseguradora = ruc_aseguradora;
	}

	public String getRazon_social_aseguradora() {
		return razon_social_aseguradora;
	}

	public void setRazon_social_aseguradora(String razon_social_aseguradora) {
		this.razon_social_aseguradora = razon_social_aseguradora;
	}

	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}

	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}

	public String getTipo_contribuyente_aseguradora() {
		return tipo_contribuyente_aseguradora;
	}

	public void setTipo_contribuyente_aseguradora(String tipo_contribuyente_aseguradora) {
		this.tipo_contribuyente_aseguradora = tipo_contribuyente_aseguradora;
	}

	public String getEstado_aseguradora() {
		return estado_aseguradora;
	}

	public void setEstado_aseguradora(String estado_aseguradora) {
		this.estado_aseguradora = estado_aseguradora;
	}
}
