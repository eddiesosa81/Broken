package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COBERTURA_RAMO_ASEG_VIEW")
public class CoberturasRamoAsegView {
	@Id
	@Column(name="codigo")
	private Integer codigo;
	@Column(name="cd_cobertura")
	private Integer cd_cobertura;
	@Column(name="desc_cobertura")
	private String desc_cobertura;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="nombre_corto_aseguradora")
	private String nombre_corto_aseguradora;
	@Column(name="cd_plan")
	private String cd_plan;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCd_cobertura() {
		return cd_cobertura;
	}
	public void setCd_cobertura(Integer cd_cobertura) {
		this.cd_cobertura = cd_cobertura;
	}
	public String getDesc_cobertura() {
		return desc_cobertura;
	}
	public void setDesc_cobertura(String desc_cobertura) {
		this.desc_cobertura = desc_cobertura;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}
	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}
	public String getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(String cd_plan) {
		this.cd_plan = cd_plan;
	}
	
}
