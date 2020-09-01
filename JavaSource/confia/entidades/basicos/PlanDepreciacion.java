package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "plan_depreciacion_tbl")
public class PlanDepreciacion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_depreciacion")
	@SequenceGenerator(sequenceName = "secuencia_cd_depreciacion", name = "secuencia_cd_depreciacion", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_plandepecia")
	private Integer cd_plandepecia;
	@Column(name="cd_plan")
	private Integer cd_plan;
	@Column(name="anio")
	private Integer anio;
	@Column(name="porcentaje")
	private Double porcentaje;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="factor")
	private Double factor;
	@Column(name="tasa_obj")
	private Double tasa_obj;
	@Column(name="superbancos")
	private Double superbancos;
	@Column(name="derechoemision")
	private Double derechoemision;
	@Column(name="segurocampesino")
	private Double segurocampesino;

	public Integer getCd_plandepecia() {
		return cd_plandepecia;
	}
	public void setCd_plandepecia(Integer cd_plandepecia) {
		this.cd_plandepecia = cd_plandepecia;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
	}
	public Double getTasa_obj() {
		return tasa_obj;
	}
	public void setTasa_obj(Double tasa_obj) {
		this.tasa_obj = tasa_obj;
	}
	public Double getSuperbancos() {
		return superbancos;
	}
	public void setSuperbancos(Double superbancos) {
		this.superbancos = superbancos;
	}
	public Double getDerechoemision() {
		return derechoemision;
	}
	public void setDerechoemision(Double derechoemision) {
		this.derechoemision = derechoemision;
	}
	public Double getSegurocampesino() {
		return segurocampesino;
	}
	public void setSegurocampesino(Double segurocampesino) {
		this.segurocampesino = segurocampesino;
	}

	
}
