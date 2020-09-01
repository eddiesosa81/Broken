package confia.entidades.basicos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASEGURADORA_RAMO_VIEW")
public class AseguradoraRamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idfila")
	private String idfila;
	
	@Column(name="cd_aseguradora")
	private int cd_aseguradora;

	@Column(name="ruc_aseguradora")
	private String ruc_aseguradora;

	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	
	@Column(name="cd_ramo")
	private int cd_ramo;

	@Column(name="desc_ramo")
	private String desc_ramo;

	@Column(name="tipo_categoria_ramo")
	private String tipo_categoria_ramo;
	
	@Column(name="numero_aprobacion_ramo")
	private String numero_aprobacion_ramo;

	@Column(name="tipo_ramo")
	private String tipo_ramo;

	@Column(name="indicador_iva_ramo")
	private String indicador_iva_ramo;

	public int getCd_aseguradora() {
		return cd_aseguradora;
	}
	
	@Column(name="cd_ramoaseg")
	private int cd_ramoaseg;

	public void setCd_aseguradora(int cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}

	public String getRuc_aseguradora() {
		return ruc_aseguradora;
	}

	public void setRuc_aseguradora(String ruc_aseguradora) {
		this.ruc_aseguradora = ruc_aseguradora;
	}

	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}

	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}

	public int getCd_ramo() {
		return cd_ramo;
	}

	public void setCd_ramo(int cd_ramo) {
		this.cd_ramo = cd_ramo;
	}

	public String getDesc_ramo() {
		return desc_ramo;
	}

	public void setDesc_ramo(String desc_ramo) {
		this.desc_ramo = desc_ramo;
	}

	public String getTipo_categoria_ramo() {
		return tipo_categoria_ramo;
	}

	public void setTipo_categoria_ramo(String tipo_categoria_ramo) {
		this.tipo_categoria_ramo = tipo_categoria_ramo;
	}

	public String getNumero_aprobacion_ramo() {
		return numero_aprobacion_ramo;
	}

	public void setNumero_aprobacion_ramo(String numero_aprobacion_ramo) {
		this.numero_aprobacion_ramo = numero_aprobacion_ramo;
	}

	public String getTipo_ramo() {
		return tipo_ramo;
	}

	public void setTipo_ramo(String tipo_ramo) {
		this.tipo_ramo = tipo_ramo;
	}

	public String getIndicador_iva_ramo() {
		return indicador_iva_ramo;
	}

	public void setIndicador_iva_ramo(String indicador_iva_ramo) {
		this.indicador_iva_ramo = indicador_iva_ramo;
	}

	public String getIdfila() {
		return idfila;
	}

	public void setIdfila(String idfila) {
		this.idfila = idfila;
	}

	public int getCd_ramoaseg() {
		return cd_ramoaseg;
	}

	public void setCd_ramoaseg(int cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}
}
