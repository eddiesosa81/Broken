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
@Table(name = "gestion_tbl")
public class Gestion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_gestion")
	@SequenceGenerator(sequenceName = "secuencia_cd_gestion", name = "secuencia_cd_gestion", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_gestion")
	private Integer cd_gestion;
	@Column(name="cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="tipo")
	private String tipo;
	@Column(name="contacto")
	private String contacto;
	@Column(name="resultado")
	private String resultado;
	@Column(name="instruccion")
	private String instruccion;
	@Column(name="fecha_contacto")
	private Date fecha_contacto;
	@Column(name="fecha_seguimiento")
	private Date fecha_seguimiento;
	@Column(name="usrid")
	private String usrid;
	@Column(name="nm_usuario")
	private String nm_usuario;
	@Column(name="fecha_pago")
	private Date fecha_pago;
	@Column(name="medio")
	private String medio;
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	
	
	
	public Integer getCd_siniestro() {
		return cd_siniestro;
	}
	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
	}
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	public Integer getCd_gestion() {
		return cd_gestion;
	}
	public void setCd_gestion(Integer cd_gestion) {
		this.cd_gestion = cd_gestion;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getInstruccion() {
		return instruccion;
	}
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}
	public Date getFecha_contacto() {
		return fecha_contacto;
	}
	public void setFecha_contacto(Date fecha_contacto) {
		this.fecha_contacto = fecha_contacto;
	}
	public Date getFecha_seguimiento() {
		return fecha_seguimiento;
	}
	public void setFecha_seguimiento(Date fecha_seguimiento) {
		this.fecha_seguimiento = fecha_seguimiento;
	}
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getNm_usuario() {
		return nm_usuario;
	}
	public void setNm_usuario(String nm_usuario) {
		this.nm_usuario = nm_usuario;
	}
	public Date getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	
	
	
}
