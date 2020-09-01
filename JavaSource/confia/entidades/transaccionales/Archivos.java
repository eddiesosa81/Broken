package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "archivos_tbl")
public class Archivos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_archivo")
	@SequenceGenerator(sequenceName = "secuencia_cd_archivo", name = "secuencia_cd_archivo", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_archivo")
	private Integer cd_archivo;
	@Column(name="cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="ubicacion")
	private String ubicacion;
	@Column(name="modulo")
	private String modulo;
	@Column(name="cd_cliente")
	private String cd_cliente;
	@Column(name="cd_rubro")
	private String cd_rubro;
	@Column(name="desc_docu")
	private String desc_docu;
	
	@Column(name="factura_aseguradora")
	private String factura_aseguradora;
	
	@Column(name="poliza")
	private String poliza;
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public Integer getCd_archivo() {
		return cd_archivo;
	}
	public void setCd_archivo(Integer cd_archivo) {
		this.cd_archivo = cd_archivo;
	}
	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public String getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(String cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public String getDesc_docu() {
		return desc_docu;
	}
	public void setDesc_docu(String desc_docu) {
		this.desc_docu = desc_docu;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFactura_aseguradora() {
		return factura_aseguradora;
	}
	public void setFactura_aseguradora(String factura_aseguradora) {
		this.factura_aseguradora = factura_aseguradora;
	}

}

