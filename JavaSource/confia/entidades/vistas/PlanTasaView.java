package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "planes_tasa_view")
public class PlanTasaView {
	@Id
	@Column(name="cd_plan")
	private String cd_plan;
	@Column(name="cd_ramoaseg")
	private String cd_ramoaseg;
	@Column(name="cd_plantasa")
	private String cd_plantasa;
	@Column(name="descripcion_plan")
	private String descripcion_plan;
	@Column(name="tasa_cliente")
	private String tasa_cliente;
	@Column(name="tasa_canal")
	private String tasa_canal;
	@Column(name="tasa_confia")
	private String tasa_confia;
	public String getCd_plan() {
		return cd_plan;
	}
	public void setCd_plan(String cd_plan) {
		this.cd_plan = cd_plan;
	}
	public String getCd_ramoaseg() {
		return cd_ramoaseg;
	}
	public void setCd_ramoaseg(String cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}
	public String getCd_plantasa() {
		return cd_plantasa;
	}
	public void setCd_plantasa(String cd_plantasa) {
		this.cd_plantasa = cd_plantasa;
	}
	public String getDescripcion_plan() {
		return descripcion_plan;
	}
	public void setDescripcion_plan(String descripcion_plan) {
		this.descripcion_plan = descripcion_plan;
	}
	public String getTasa_cliente() {
		return tasa_cliente;
	}
	public void setTasa_cliente(String tasa_cliente) {
		this.tasa_cliente = tasa_cliente;
	}
	public String getTasa_canal() {
		return tasa_canal;
	}
	public void setTasa_canal(String tasa_canal) {
		this.tasa_canal = tasa_canal;
	}
	public String getTasa_confia() {
		return tasa_confia;
	}
	public void setTasa_confia(String tasa_confia) {
		this.tasa_confia = tasa_confia;
	}
}
