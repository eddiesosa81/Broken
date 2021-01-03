package confia.entidades.basicos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "planclausula_tbl")
public class PlanClausula {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_cd_planclausula")
	@SequenceGenerator(sequenceName = "secuencia_cd_planclausula", name = "secuencia_cd_planclausula", initialValue = 1, allocationSize = 1000)
	@Column(name = "cd_planclausula")
	private Integer cd_planclausula;
	@Column(name = "cd_plan")
	private Integer cd_plan;
	@Column(name = "cd_asegclau")
	private Integer cd_asegclau;
	@Column(name = "fc_ini_planclausula")
	private Date fc_ini_planclausula;
	@Column(name = "fc_fin_planclausula")
	private Date fc_fin_planclausula;
	@Column(name = "porcentaje_planclausula")
	private Double porcentaje_planclausula;
	@Column(name = "valor_planclausula")
	private Double valor_planclausula;
	@Column(name = "estado_planclausula")
	private String estado_planclausula;
	@Column(name="especificacion")
	private String especificacion;
	
	
	
	public String getEspecificacion() {
		return especificacion;
	}
	public void setEspecificacion(String especificacion) {
		this.especificacion = especificacion;
	}
	public Integer getCd_planclausula() {
		return cd_planclausula;
	}
	public void setCd_planclausula(Integer cd_planclausula) {
		this.cd_planclausula = cd_planclausula;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Integer getCd_asegclau() {
		return cd_asegclau;
	}
	public void setCd_asegclau(Integer cd_asegclau) {
		this.cd_asegclau = cd_asegclau;
	}
	public Date getFc_ini_planclausula() {
		return fc_ini_planclausula;
	}
	public void setFc_ini_planclausula(Date fc_ini_planclausula) {
		this.fc_ini_planclausula = fc_ini_planclausula;
	}
	public Date getFc_fin_planclausula() {
		return fc_fin_planclausula;
	}
	public void setFc_fin_planclausula(Date fc_fin_planclausula) {
		this.fc_fin_planclausula = fc_fin_planclausula;
	}
	public Double getPorcentaje_planclausula() {
		return porcentaje_planclausula;
	}
	public void setPorcentaje_planclausula(Double porcentaje_planclausula) {
		this.porcentaje_planclausula = porcentaje_planclausula;
	}
	public Double getValor_planclausula() {
		return valor_planclausula;
	}
	public void setValor_planclausula(Double valor_planclausula) {
		this.valor_planclausula = valor_planclausula;
	}
	public String getEstado_planclausula() {
		return estado_planclausula;
	}
	public void setEstado_planclausula(String estado_planclausula) {
		this.estado_planclausula = estado_planclausula;
	}
	

}
