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
@Table(name = "PLAN_TBL")
public class Plan {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_plan")
	@SequenceGenerator(sequenceName = "secuencia_cd_plan", name = "secuencia_cd_plan", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_plan")
	private Integer cd_plan;
	
	@Column(name="cd_ramoaseg")
	private Integer cd_ramoaseg;
	
	@Column(name="descripcion_plan")
	private String descripcion_plan;
	
	@Column(name="fc_ini_plan")
	private Date fc_ini_plan;
	
	@Column(name="fc_fin_plan")
	private Date fc_fin_plan;
	
	@Column(name="prima_minima_plan")
	private Double prima_minima_plan;
	
	@Column(name="estado_plan")
	private String estado_plan;

	public Integer getCd_plan() {
		return cd_plan;
	}

	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}

	public Integer getCd_ramoaseg() {
		return cd_ramoaseg;
	}

	public void setCd_ramoaseg(Integer cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}

	public String getDescripcion_plan() {
		return descripcion_plan;
	}

	public void setDescripcion_plan(String descripcion_plan) {
		this.descripcion_plan = descripcion_plan;
	}

	public Date getFc_ini_plan() {
		return fc_ini_plan;
	}

	public void setFc_ini_plan(Date fc_ini_plan) {
		this.fc_ini_plan = fc_ini_plan;
	}

	public Date getFc_fin_plan() {
		return fc_fin_plan;
	}

	public void setFc_fin_plan(Date fc_fin_plan) {
		this.fc_fin_plan = fc_fin_plan;
	}

	public Double getPrima_minima_plan() {
		return prima_minima_plan;
	}

	public void setPrima_minima_plan(Double prima_minima_plan) {
		this.prima_minima_plan = prima_minima_plan;
	}

	public String getEstado_plan() {
		return estado_plan;
	}

	public void setEstado_plan(String estado_plan) {
		this.estado_plan = estado_plan;
	}
}
