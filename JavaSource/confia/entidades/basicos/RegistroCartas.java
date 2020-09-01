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
@Table(name = "registro_cartas_tbl")
public class RegistroCartas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_direccion")
	@SequenceGenerator(sequenceName = "secuencia_cd_direccion", name = "secuencia_cd_direccion", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_carta")
	private Integer cd_carta;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_contacto")
	private Integer cd_contacto;
	@Column(name="cd_mensajero")
	private Integer cd_mensajero;
	@Column(name="cd_rubro")
	private Integer cd_rubro;
	@Column(name="cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name="cd_ruta")
	private Integer cd_ruta;
	@Column(name="ano")
	private Integer ano;
	@Column(name="fecha_gen")
	private Integer fecha_gen;
	@Column(name="fecha_gen_date")
	private Date fecha_gen_date;
	@Column(name="elaborado_por")
	private String  elaborado_por;
	@Column(name="tipo")
	private String tipo;
	@Column(name="ubicaccion")
	private String ubicaccion;
	@Column(name="ref_carta")
	private String ref_carta;
	@Column(name="persona_envia")
	private String persona_envia;
	@Column(name="firma")
	private String firma;
	@Column(name="fecha_carta_envia")
	private Integer fecha_carta_envia;
	@Column(name="fecha_carta_envia_date")
	private Date fecha_carta_envia_date;
	@Column(name="estado_carta")
	private String estado_carta;
	@Column(name="fecha_carta_recibe")
	private Integer fecha_carta_recibe;
	@Column(name="fecha_carta_recibe_date")
	private Date fecha_carta_recibe_date;
	@Column(name="persona_recibe")
	private String persona_recibe;
	@Column(name="numero_ultima_ruta")
	private Integer numero_ultima_ruta;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="aprobado_correspondencia")
	private Integer aprobado_correspondencia;
	@Column(name="fc_aprueba")
	private Integer fc_aprueba;
	@Column(name="fc_aprueba_date")
	private Date fc_aprueba_date;
	@Column(name="envio_correo")
	private String envio_correo;
	public Integer getCd_carta() {
		return cd_carta;
	}
	public void setCd_carta(Integer cd_carta) {
		this.cd_carta = cd_carta;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public Integer getCd_contacto() {
		return cd_contacto;
	}
	public void setCd_contacto(Integer cd_contacto) {
		this.cd_contacto = cd_contacto;
	}
	public Integer getCd_mensajero() {
		return cd_mensajero;
	}
	public void setCd_mensajero(Integer cd_mensajero) {
		this.cd_mensajero = cd_mensajero;
	}
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public Integer getCd_ruta() {
		return cd_ruta;
	}
	public void setCd_ruta(Integer cd_ruta) {
		this.cd_ruta = cd_ruta;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getFecha_gen() {
		return fecha_gen;
	}
	public void setFecha_gen(Integer fecha_gen) {
		this.fecha_gen = fecha_gen;
	}
	public Date getFecha_gen_date() {
		return fecha_gen_date;
	}
	public void setFecha_gen_date(Date fecha_gen_date) {
		this.fecha_gen_date = fecha_gen_date;
	}
	public String getElaborado_por() {
		return elaborado_por;
	}
	public void setElaborado_por(String elaborado_por) {
		this.elaborado_por = elaborado_por;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUbicaccion() {
		return ubicaccion;
	}
	public void setUbicaccion(String ubicaccion) {
		this.ubicaccion = ubicaccion;
	}
	public String getRef_carta() {
		return ref_carta;
	}
	public void setRef_carta(String ref_carta) {
		this.ref_carta = ref_carta;
	}
	public String getPersona_envia() {
		return persona_envia;
	}
	public void setPersona_envia(String persona_envia) {
		this.persona_envia = persona_envia;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public Integer getFecha_carta_envia() {
		return fecha_carta_envia;
	}
	public void setFecha_carta_envia(Integer fecha_carta_envia) {
		this.fecha_carta_envia = fecha_carta_envia;
	}
	public Date getFecha_carta_envia_date() {
		return fecha_carta_envia_date;
	}
	public void setFecha_carta_envia_date(Date fecha_carta_envia_date) {
		this.fecha_carta_envia_date = fecha_carta_envia_date;
	}
	public String getEstado_carta() {
		return estado_carta;
	}
	public void setEstado_carta(String estado_carta) {
		this.estado_carta = estado_carta;
	}
	public Integer getFecha_carta_recibe() {
		return fecha_carta_recibe;
	}
	public void setFecha_carta_recibe(Integer fecha_carta_recibe) {
		this.fecha_carta_recibe = fecha_carta_recibe;
	}
	public Date getFecha_carta_recibe_date() {
		return fecha_carta_recibe_date;
	}
	public void setFecha_carta_recibe_date(Date fecha_carta_recibe_date) {
		this.fecha_carta_recibe_date = fecha_carta_recibe_date;
	}
	public String getPersona_recibe() {
		return persona_recibe;
	}
	public void setPersona_recibe(String persona_recibe) {
		this.persona_recibe = persona_recibe;
	}
	public Integer getNumero_ultima_ruta() {
		return numero_ultima_ruta;
	}
	public void setNumero_ultima_ruta(Integer numero_ultima_ruta) {
		this.numero_ultima_ruta = numero_ultima_ruta;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getAprobado_correspondencia() {
		return aprobado_correspondencia;
	}
	public void setAprobado_correspondencia(Integer aprobado_correspondencia) {
		this.aprobado_correspondencia = aprobado_correspondencia;
	}
	public Integer getFc_aprueba() {
		return fc_aprueba;
	}
	public void setFc_aprueba(Integer fc_aprueba) {
		this.fc_aprueba = fc_aprueba;
	}
	public Date getFc_aprueba_date() {
		return fc_aprueba_date;
	}
	public void setFc_aprueba_date(Date fc_aprueba_date) {
		this.fc_aprueba_date = fc_aprueba_date;
	}
	public String getEnvio_correo() {
		return envio_correo;
	}
	public void setEnvio_correo(String envio_correo) {
		this.envio_correo = envio_correo;
	}
	

}
