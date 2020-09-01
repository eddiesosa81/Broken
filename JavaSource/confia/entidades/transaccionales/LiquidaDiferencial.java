package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "liquida_diferencial_tbl")
public class LiquidaDiferencial {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_liq_diferencial")
	@SequenceGenerator(sequenceName = "secuencia_cd_liq_diferencial", name = "secuencia_cd_liq_diferencial", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_liq_diferencial")
	private Integer cd_liq_diferencial;
	@Column(name="cd_comision_poliza")
	private Integer cd_comision_poliza;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="fecha_liquidacion")
	private String fecha_liquidacion;
	@Column(name="num_liquidacion")
	private Integer num_liquidacion;
	
	public Integer getCd_liq_diferencial() {
		return cd_liq_diferencial;
	}
	public void setCd_liq_diferencial(Integer cd_liq_diferencial) {
		this.cd_liq_diferencial = cd_liq_diferencial;
	}
	public Integer getCd_comision_poliza() {
		return cd_comision_poliza;
	}
	public void setCd_comision_poliza(Integer cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	
	public String getFecha_liquidacion() {
		return fecha_liquidacion;
	}
	public void setFecha_liquidacion(String fecha_liquidacion) {
		this.fecha_liquidacion = fecha_liquidacion;
	}
	public Integer getNum_liquidacion() {
		return num_liquidacion;
	}
	public void setNum_liquidacion(Integer num_liquidacion) {
		this.num_liquidacion = num_liquidacion;
	}
	
}
