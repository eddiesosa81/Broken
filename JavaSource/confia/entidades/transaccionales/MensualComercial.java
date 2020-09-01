package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mensual_comercial_tbl")
public class MensualComercial {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_men_com")
	@SequenceGenerator(sequenceName = "secuencia_cd_men_com", name = "secuencia_cd_men_com", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_men_com")
	private Integer cd_men_com;
	@Column(name="cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="valor_asegurado")
	private Integer valorAsegurado;
	@Column(name="placa_o_ram")
	private String placaRam;
	@Column(name="anio")
	private Integer anio;
	@Column(name="color")
	private String color;
	@Column(name="modelo_Marca")
	private String modelo_Marca;
	public Integer getCd_men_com() {
		return cd_men_com;
	}
	public void setCd_men_com(Integer cd_men_com) {
		this.cd_men_com = cd_men_com;
	}
	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getValorAsegurado() {
		return valorAsegurado;
	}
	public void setValorAsegurado(Integer valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}
	public String getPlacaRam() {
		return placaRam;
	}
	public void setPlacaRam(String placaRam) {
		this.placaRam = placaRam;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getModelo_Marca() {
		return modelo_Marca;
	}
	public void setModelo_Marca(String modelo_Marca) {
		this.modelo_Marca = modelo_Marca;
	}


}
