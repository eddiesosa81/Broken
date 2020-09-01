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
@Table(name = "Correspondencia_tbl")
public class Correspondencia {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_correspondencia")
	@SequenceGenerator(sequenceName = "secuencia_cd_correspondencia", name = "secuencia_cd_correspondencia", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_correspondencia")
	private Integer cd_correspondencia;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="ANO")
	private Integer anio;
	@Column(name="num_Carta")
	private String num_Carta;
	@Column(name="FECHA")
	private Date fecha;
	@Column(name="ELABORADO_POR")
	private String elaboradorPor;
	@Column(name="MODULO_GENERA")
	private String moduloGenera;
	@Column(name="TIPO")
	private String tipo;
	@Column(name="notas_adicionales")
	private String notasAdicionales;
	@Column(name="REF_CARTA")
	private String refCarta;
	@Column(name="CD_CLIENTE")
	private Integer cdCliente;
	@Column(name="CD_ASEGURADORA")
	private Integer cdAseguradora;
	@Column(name="cd_cotizacion")
	private Integer cdCotizacion;
	@Column(name="cd_rubro")
	private Integer cdRubro;
	@Column(name="nm_reporte")
	private String nmReporte;
	@Column(name="cd_contacto")
	private Integer cdContacto;
	@Column(name="cargoUsuario")
	private String cargoUsuario;
	@Column(name="nmContacto")
	private String nmContacto;
	@Column(name="ciudadContacto")
	private String ciudadContacto;
	@Column(name="usrid")
	private Integer usrid;
	@Column(name="cargo_contacto")
	private String cargoContacto;
	@Column(name="estado_impresion")
	private String estado_impresion;
	@Column(name="fecha_impresion")
	private Date fecha_impresion;
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	@Column(name="cd_cot_ane")
	private Integer cd_cot_ane;
	
	
	public Integer getCd_cot_ane() {
		return cd_cot_ane;
	}
	public void setCd_cot_ane(Integer cd_cot_ane) {
		this.cd_cot_ane = cd_cot_ane;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public Integer getCd_correspondencia() {
		return cd_correspondencia;
	}
	public void setCd_correspondencia(Integer cd_correspondencia) {
		this.cd_correspondencia = cd_correspondencia;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getNum_Carta() {
		return num_Carta;
	}
	public void setNum_Carta(String num_Carta) {
		this.num_Carta = num_Carta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getElaboradorPor() {
		return elaboradorPor;
	}
	public void setElaboradorPor(String elaboradorPor) {
		this.elaboradorPor = elaboradorPor;
	}
	public String getModuloGenera() {
		return moduloGenera;
	}
	public void setModuloGenera(String moduloGenera) {
		this.moduloGenera = moduloGenera;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNotasAdicionales() {
		return notasAdicionales;
	}
	public void setNotasAdicionales(String notasAdicionales) {
		this.notasAdicionales = notasAdicionales;
	}
	public String getRefCarta() {
		return refCarta;
	}
	public void setRefCarta(String refCarta) {
		this.refCarta = refCarta;
	}
	public Integer getCdCliente() {
		return cdCliente;
	}
	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}
	public Integer getCdAseguradora() {
		return cdAseguradora;
	}
	public void setCdAseguradora(Integer cdAseguradora) {
		this.cdAseguradora = cdAseguradora;
	}
	public Integer getCdCotizacion() {
		return cdCotizacion;
	}
	public void setCdCotizacion(Integer cdCotizacion) {
		this.cdCotizacion = cdCotizacion;
	}
	public Integer getCdRubro() {
		return cdRubro;
	}
	public void setCdRubro(Integer cdRubro) {
		this.cdRubro = cdRubro;
	}
	public String getNmReporte() {
		return nmReporte;
	}
	public void setNmReporte(String nmReporte) {
		this.nmReporte = nmReporte;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCdContacto() {
		return cdContacto;
	}
	public void setCdContacto(Integer cdContacto) {
		this.cdContacto = cdContacto;
	}
	public String getCargoUsuario() {
		return cargoUsuario;
	}
	public void setCargoUsuario(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}
	public String getNmContacto() {
		return nmContacto;
	}
	public void setNmContacto(String nmContacto) {
		this.nmContacto = nmContacto;
	}
	public String getCiudadContacto() {
		return ciudadContacto;
	}
	public void setCiudadContacto(String ciudadContacto) {
		this.ciudadContacto = ciudadContacto;
	}
	public String getCargoContacto() {
		return cargoContacto;
	}
	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}
	public String getEstado_impresion() {
		return estado_impresion;
	}
	public void setEstado_impresion(String estado_impresion) {
		this.estado_impresion = estado_impresion;
	}
	public Date getFecha_impresion() {
		return fecha_impresion;
	}
	public void setFecha_impresion(Date fecha_impresion) {
		this.fecha_impresion = fecha_impresion;
	}
	public Integer getCd_siniestro() {
		return cd_siniestro;
	}
	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
	}
	
	
}
