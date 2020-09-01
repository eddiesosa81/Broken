package confia.entidades.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica_vehiculos_tbl")
public class CaracteristicasVh {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_carac_vh")
	@SequenceGenerator(sequenceName = "secuencia_cd_carac_vh", name = "secuencia_cd_carac_vh", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_carac_vh")
	private Integer cd_carac_vh;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_obj_cotizacion")
	private Integer cd_obj_cotizacion;
	@Column(name="color")
	private String color;
	@Column(name="cd_marca")
	private Integer cd_marca;
	@Column(name="marca")
	private String marca;
	@Column(name="no_de_chasis")
	private String no_de_chasis;
	@Column(name="no_de_motor")
	private String no_de_motor;
	@Column(name="placa")
	private String placa;
	@Column(name="cd_modelo")
	private Integer cd_modelo;
	@Column(name="modelo")
	private String modelo;
	@Column(name="propietario")
	private String propietario;
	@Column(name="tipo_combustible")
	private String tipo_combustible;
	@Column(name="anio_de_fabricacion")
	private String anio_de_fabricacion;
	@Column(name="concesionario")
	private String concesionario;
	@Column(name="deudor")
	private String deudor;
	@Column(name="uso_vehiculo")
	private String uso_vehiculo;
	@Column(name="dispositivo_seg")
	private String dispositivo_seg;
	@Column(name="tonelaje")
	private String tonelaje;
	@Column(name="cilindraje")
	private String cilindraje;
	
	@Column(name="num_asientos")
	private Integer num_asientos;
	@Column(name="fecha_endoso")
	private Date fecha_endoso;
	@Column(name="avaluo")
	private Double avaluo;
	@Column(name="valor_primer_anio")
	private Double valor_primer_anio;
	@Column(name="valor_segundo_anio")
	private Double valor_segundo_anio;
	@Column(name="valor_tercer_anio")
	private Double valor_tercer_anio;
	@Column(name="adicional1")
	private String adicional1;
	@Column(name="adicional2")
	private String adicional2;
	@Column(name="adicional3")
	private String adicional3;
	@Column(name="adicional4")
	private String adicional4;
	@Column(name="adicional5")
	private String adicional5;
	@Column(name="segundo_color")
	private String segundo_color;
	@Column(name="ranv_cpn")
	private String ranv_cpn;
	@Column(name="observaciones")
	private String observaciones;
	public Integer getCd_carac_vh() {
		return cd_carac_vh;
	}
	public void setCd_carac_vh(Integer cd_carac_vh) {
		this.cd_carac_vh = cd_carac_vh;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_obj_cotizacion() {
		return cd_obj_cotizacion;
	}
	public void setCd_obj_cotizacion(Integer cd_obj_cotizacion) {
		this.cd_obj_cotizacion = cd_obj_cotizacion;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getCd_marca() {
		return cd_marca;
	}
	public void setCd_marca(Integer cd_marca) {
		this.cd_marca = cd_marca;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getNo_de_chasis() {
		return no_de_chasis;
	}
	public void setNo_de_chasis(String no_de_chasis) {
		this.no_de_chasis = no_de_chasis;
	}
	public String getNo_de_motor() {
		return no_de_motor;
	}
	public void setNo_de_motor(String no_de_motor) {
		this.no_de_motor = no_de_motor;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Integer getCd_modelo() {
		return cd_modelo;
	}
	public void setCd_modelo(Integer cd_modelo) {
		this.cd_modelo = cd_modelo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public String getTipo_combustible() {
		return tipo_combustible;
	}
	public void setTipo_combustible(String tipo_combustible) {
		this.tipo_combustible = tipo_combustible;
	}
	public String getAnio_de_fabricacion() {
		return anio_de_fabricacion;
	}
	public void setAnio_de_fabricacion(String anio_de_fabricacion) {
		this.anio_de_fabricacion = anio_de_fabricacion;
	}
	public String getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}
	public String getDeudor() {
		return deudor;
	}
	public void setDeudor(String deudor) {
		this.deudor = deudor;
	}
	public String getUso_vehiculo() {
		return uso_vehiculo;
	}
	public void setUso_vehiculo(String uso_vehiculo) {
		this.uso_vehiculo = uso_vehiculo;
	}
	public String getDispositivo_seg() {
		return dispositivo_seg;
	}
	public void setDispositivo_seg(String dispositivo_seg) {
		this.dispositivo_seg = dispositivo_seg;
	}
	public String getTonelaje() {
		return tonelaje;
	}
	public void setTonelaje(String tonelaje) {
		this.tonelaje = tonelaje;
	}
	public String getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}
	public Integer getNum_asientos() {
		return num_asientos;
	}
	public void setNum_asientos(Integer num_asientos) {
		this.num_asientos = num_asientos;
	}
	public Date getFecha_endoso() {
		return fecha_endoso;
	}
	public void setFecha_endoso(Date fecha_endoso) {
		this.fecha_endoso = fecha_endoso;
	}
	public Double getAvaluo() {
		return avaluo;
	}
	public void setAvaluo(Double avaluo) {
		this.avaluo = avaluo;
	}
	public Double getValor_primer_anio() {
		return valor_primer_anio;
	}
	public void setValor_primer_anio(Double valor_primer_anio) {
		this.valor_primer_anio = valor_primer_anio;
	}
	public Double getValor_segundo_anio() {
		return valor_segundo_anio;
	}
	public void setValor_segundo_anio(Double valor_segundo_anio) {
		this.valor_segundo_anio = valor_segundo_anio;
	}
	public Double getValor_tercer_anio() {
		return valor_tercer_anio;
	}
	public void setValor_tercer_anio(Double valor_tercer_anio) {
		this.valor_tercer_anio = valor_tercer_anio;
	}
	public String getAdicional1() {
		return adicional1;
	}
	public void setAdicional1(String adicional1) {
		this.adicional1 = adicional1;
	}
	public String getAdicional2() {
		return adicional2;
	}
	public void setAdicional2(String adicional2) {
		this.adicional2 = adicional2;
	}
	public String getAdicional3() {
		return adicional3;
	}
	public void setAdicional3(String adicional3) {
		this.adicional3 = adicional3;
	}
	public String getAdicional4() {
		return adicional4;
	}
	public void setAdicional4(String adicional4) {
		this.adicional4 = adicional4;
	}
	public String getAdicional5() {
		return adicional5;
	}
	public void setAdicional5(String adicional5) {
		this.adicional5 = adicional5;
	}
	public String getSegundo_color() {
		return segundo_color;
	}
	public void setSegundo_color(String segundo_color) {
		this.segundo_color = segundo_color;
	}
	public String getRanv_cpn() {
		return ranv_cpn;
	}
	public void setRanv_cpn(String ranv_cpn) {
		this.ranv_cpn = ranv_cpn;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	

}
