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
@Table(name = "plancobertura_tbl")
public class PlanCobertura {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_cd_plancobertura")
	@SequenceGenerator(sequenceName = "secuencia_cd_plancobertura", name = "secuencia_cd_plancobertura", initialValue = 1, allocationSize = 1000)
	@Column(name = "cd_plancobertura")
	private Integer cd_plancobertura;
	@Column(name = "cd_plan")
	private Integer cd_plan;
	@Column(name = "cd_asegcob")
	private Integer cd_asegcob;
	@Column(name = "fc_ini_plancobertura")
	private Date fc_ini_plancobertura;
	@Column(name = "fc_fin_plancobertura")
	private Date fc_fin_plancobertura;
	@Column(name = "porcentajeplancobertura")
	private Double porcentajeplancobertura;
	@Column(name = "valor_plancobertura")
	private Double valor_plancobertura;
	@Column(name = "estado_plancobertura")
	private String estado_plancobertura;
	public Integer getCd_plancobertura() {
		return cd_plancobertura;
	}
	public void setCd_plancobertura(Integer cd_plancobertura) {
		this.cd_plancobertura = cd_plancobertura;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Integer getCd_asegcob() {
		return cd_asegcob;
	}
	public void setCd_asegcob(Integer cd_asegcob) {
		this.cd_asegcob = cd_asegcob;
	}
	public Date getFc_ini_plancobertura() {
		return fc_ini_plancobertura;
	}
	public void setFc_ini_plancobertura(Date fc_ini_plancobertura) {
		this.fc_ini_plancobertura = fc_ini_plancobertura;
	}
	public Date getFc_fin_plancobertura() {
		return fc_fin_plancobertura;
	}
	public void setFc_fin_plancobertura(Date fc_fin_plancobertura) {
		this.fc_fin_plancobertura = fc_fin_plancobertura;
	}
	public Double getPorcentajeplancobertura() {
		return porcentajeplancobertura;
	}
	public void setPorcentajeplancobertura(Double porcentajeplancobertura) {
		this.porcentajeplancobertura = porcentajeplancobertura;
	}
	public Double getValor_plancobertura() {
		return valor_plancobertura;
	}
	public void setValor_plancobertura(Double valor_plancobertura) {
		this.valor_plancobertura = valor_plancobertura;
	}
	public String getEstado_plancobertura() {
		return estado_plancobertura;
	}
	public void setEstado_plancobertura(String estado_plancobertura) {
		this.estado_plancobertura = estado_plancobertura;
	}
	
}
