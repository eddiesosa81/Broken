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
@Table(name = "plantasa_tbl")
public class PlanTasa {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_plantasa")
	@SequenceGenerator(sequenceName = "secuencia_cd_plantasa", name = "secuencia_cd_plantasa", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_plantasa")
	private Integer cd_plantasa;
	@Column(name="cd_plan")
	private Integer cd_plan;
	@Column(name="fc_ini_plantasa")
	private Date fc_ini_plantasa;
	@Column(name="fc_fin_plantasa")
	private Date fc_fin_plantasa;
	@Column(name="tasa_cliente")
	private Double tasa_cliente;
	@Column(name="tasa_canal")
	private Double tasa_canal;
	@Column(name="tasa_confia")
	private Double tasa_confia;
	@Column(name="estado_plantasa")
	private String estado_plantasa;
	public Integer getCd_plantasa() {
		return cd_plantasa;
	}
	public void setCd_plantasa(Integer cd_plantasa) {
		this.cd_plantasa = cd_plantasa;
	}
	public Integer getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(Integer cd_plan) {
		this.cd_plan = cd_plan;
	}
	public Date getFc_ini_plantasa() {
		return fc_ini_plantasa;
	}
	public void setFc_ini_plantasa(Date fc_ini_plantasa) {
		this.fc_ini_plantasa = fc_ini_plantasa;
	}
	public Date getFc_fin_plantasa() {
		return fc_fin_plantasa;
	}
	public void setFc_fin_plantasa(Date fc_fin_plantasa) {
		this.fc_fin_plantasa = fc_fin_plantasa;
	}
	public Double getTasa_cliente() {
		return tasa_cliente;
	}
	public void setTasa_cliente(Double tasa_cliente) {
		this.tasa_cliente = tasa_cliente;
	}
	public Double getTasa_canal() {
		return tasa_canal;
	}
	public void setTasa_canal(Double tasa_canal) {
		this.tasa_canal = tasa_canal;
	}
	public Double getTasa_confia() {
		return tasa_confia;
	}
	public void setTasa_confia(Double tasa_confia) {
		this.tasa_confia = tasa_confia;
	}
	public String getEstado_plantasa() {
		return estado_plantasa;
	}
	public void setEstado_plantasa(String estado_plantasa) {
		this.estado_plantasa = estado_plantasa;
	}
	

}
