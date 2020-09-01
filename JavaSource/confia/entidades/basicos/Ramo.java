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

/**
 * @author Guido Guerrero
 *
 */

@Entity
@Table(name = "ramo_tbl")
public class Ramo{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_ramo")
	@SequenceGenerator(sequenceName = "secuencia_cd_ramo", name = "secuencia_cd_ramo", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_ramo")
	private Integer cd_ramo;
	
	@Column(name="desc_ramo")
	private String desc_ramo;
	
	@Column(name="tipo_categoria_ramo")
	private String tipo_categoria_ramo;
	
	@Column(name="numero_aprobacion_ramo")
	private String numero_aprobacion_ramo;
	
	@Column(name="fecha_aprobacion_ramo")
	private Date fecha_aprobacion_ramo;
	
	@Column(name="codigo_super_bancos_ramo")
	private String codigo_super_bancos_ramo;
	
	@Column(name="indicador_iva_ramo")
	private String indicador_iva_ramo;
	
	@Column(name="estado_ramo")
	private String estado_ramo;
	
	@Column(name="tipo_ramo")
	private String tipo_ramo;
	
	@Column(name="periodico")
	private Integer periodico;
	

	public Integer getCd_ramo() {
		return cd_ramo;
	}

	public void setCd_ramo(Integer cd_ramo) {
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

	public Date getFecha_aprobacion_ramo() {
		return fecha_aprobacion_ramo;
	}

	public void setFecha_aprobacion_ramo(Date fecha_aprobacion_ramo) {
		this.fecha_aprobacion_ramo = fecha_aprobacion_ramo;
	}

	public String getCodigo_super_bancos_ramo() {
		return codigo_super_bancos_ramo;
	}

	public void setCodigo_super_bancos_ramo(String codigo_super_bancos_ramo) {
		this.codigo_super_bancos_ramo = codigo_super_bancos_ramo;
	}

	public String getIndicador_iva_ramo() {
		return indicador_iva_ramo;
	}

	public void setIndicador_iva_ramo(String indicador_iva_ramo) {
		this.indicador_iva_ramo = indicador_iva_ramo;
	}

	public String getEstado_ramo() {
		return estado_ramo;
	}

	public void setEstado_ramo(String estado_ramo) {
		this.estado_ramo = estado_ramo;
	}

	public String getTipo_ramo() {
		return tipo_ramo;
	}

	public void setTipo_ramo(String tipo_ramo) {
		this.tipo_ramo = tipo_ramo;
	}

	public Integer getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Integer periodico) {
		this.periodico = periodico;
	}

	
}
