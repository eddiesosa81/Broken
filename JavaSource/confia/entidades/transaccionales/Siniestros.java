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
@Table(name = "siniestro_tbl")
public class Siniestros {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_siniestro")
	@SequenceGenerator(sequenceName = "secuencia_cd_siniestro", name = "secuencia_cd_siniestro", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_siniestro")
	private Integer cdSiniestro;
	@Column(name="ano_siniestro")
	private Integer anoSiniestro;
	@Column(name="cd_aseguradora")
	private Integer cdAseguradora;
	@Column(name="cd_ramo")
	private Integer cdRamo;
	@Column(name="cd_compania")
	private Integer cdCompania;
	@Column(name="cd_ramo_cotizacion")
	private Integer cdRamoCotizacion;
	@Column(name="cd_cliente")
	private Integer cdCliente;
	@Column(name="fc_creacion_jul")
	private Integer fcCreacionJul;
	@Column(name="fc_creacion")
	private Date fcCreacion;
	@Column(name="fc_siniestro_jul")
	private Integer fcSiniestroJul;
	@Column(name="fc_siniestro")
	private Date fcSiniestro;
	@Column(name="numero_reporte_aseg")
	private String numeroReporteAseg;
	@Column(name="numero_siniestro_aseg")
	private String numeroSiniestroAseg;
	@Column(name="causa")
	private String causa;
	@Column(name="poliza")
	private String poliza;
	@Column(name="usrid")
	private Integer usrid;
	@Column(name="estado")
	private String estado;
	@Column(name="bloqueo")
	private Integer bloqueo;
	@Column(name="nm_Ramo")
	private String nm_Ramo;
	@Column(name="nm_aseguradora")
	private String nm_aseguradora;
	@Column(name="nm_cliente")
	private String nm_cliente;
	@Column(name="fc_vig_pol_desde")
	private Date fc_vig_pol_desde;
	@Column(name="fc_vig_pol_hasta")
	private Date fc_vig_pol_hasta;
	@Column(name="referencias")
	private String referencias;
	@Column(name="identificacion_cliente")
	private String identificacion_cliente;
	@Column(name="cd_subagente")
	private Integer cd_subagente;
	@Column(name="nm_subagente")
	private String nm_subagente;
	@Column(name="cd_ciudad")
	private Integer cd_ciudad;
	@Column(name="nm_ciudad")
	private String nm_ciudad;
	@Column(name="correo")
	private String correo;
	@Column(name="fecha_autorizacion")
	private Date fechaAutorizacion;
	@Column(name="fecha_recepcion")
	private Date fechaRecepcion;
	@Column(name="caso")
	private String caso;
	@Column(name="califica_atencion")
	private String califica_atencion;
	@Column(name="observacion_atencion")
	private String observacion_atencion;
	
	@Column(name="conductorNm")
	private String conductorNm;
	@Column(name="conductorCed")
	private String conductorCed;
	@Column(name="conductorVigLic")
	private String conductorVigLic;
	@Column(name="conductorTipoLic")
	private String conductorTipoLic;
	@Column(name="conductorDir")
	private String conductorDir;
	@Column(name="conductorTelf")
	private String conductorTelf;
	
	@Column(name="cd_provincia")
	private Integer cd_provincia;
	@Column(name="desc_provincia")
	private String desc_provincia;
	@Column(name="cd_canton")
	private Integer cd_canton;
	@Column(name="desc_canton")
	private String desc_canton;
	@Column(name="cd_parroquia")
	private Integer cd_parroquia;
	@Column(name="desc_parroquia")
	private String desc_parroquia;
	
	

	
	public Integer getCd_provincia() {
		return cd_provincia;
	}
	public void setCd_provincia(Integer cd_provincia) {
		this.cd_provincia = cd_provincia;
	}
	public String getDesc_provincia() {
		return desc_provincia;
	}
	public void setDesc_provincia(String desc_provincia) {
		this.desc_provincia = desc_provincia;
	}
	public Integer getCd_canton() {
		return cd_canton;
	}
	public void setCd_canton(Integer cd_canton) {
		this.cd_canton = cd_canton;
	}
	public String getDesc_canton() {
		return desc_canton;
	}
	public void setDesc_canton(String desc_canton) {
		this.desc_canton = desc_canton;
	}
	public Integer getCd_parroquia() {
		return cd_parroquia;
	}
	public void setCd_parroquia(Integer cd_parroquia) {
		this.cd_parroquia = cd_parroquia;
	}
	public String getDesc_parroquia() {
		return desc_parroquia;
	}
	public void setDesc_parroquia(String desc_parroquia) {
		this.desc_parroquia = desc_parroquia;
	}
	public String getConductorNm() {
		return conductorNm;
	}
	public void setConductorNm(String conductorNm) {
		this.conductorNm = conductorNm;
	}
	public String getConductorCed() {
		return conductorCed;
	}
	public void setConductorCed(String conductorCed) {
		this.conductorCed = conductorCed;
	}
	public String getConductorVigLic() {
		return conductorVigLic;
	}
	public void setConductorVigLic(String conductorVigLic) {
		this.conductorVigLic = conductorVigLic;
	}
	public String getConductorTipoLic() {
		return conductorTipoLic;
	}
	public void setConductorTipoLic(String conductorTipoLic) {
		this.conductorTipoLic = conductorTipoLic;
	}
	public String getConductorDir() {
		return conductorDir;
	}
	public void setConductorDir(String conductorDir) {
		this.conductorDir = conductorDir;
	}
	public String getConductorTelf() {
		return conductorTelf;
	}
	public void setConductorTelf(String conductorTelf) {
		this.conductorTelf = conductorTelf;
	}
	public String getCalifica_atencion() {
		return califica_atencion;
	}
	public void setCalifica_atencion(String califica_atencion) {
		this.califica_atencion = califica_atencion;
	}
	public String getObservacion_atencion() {
		return observacion_atencion;
	}
	public void setObservacion_atencion(String observacion_atencion) {
		this.observacion_atencion = observacion_atencion;
	}
	public Integer getCdSiniestro() {
		return cdSiniestro;
	}
	public void setCdSiniestro(Integer cdSiniestro) {
		this.cdSiniestro = cdSiniestro;
	}
	public Integer getAnoSiniestro() {
		return anoSiniestro;
	}
	public void setAnoSiniestro(Integer anoSiniestro) {
		this.anoSiniestro = anoSiniestro;
	}
	public Integer getCdAseguradora() {
		return cdAseguradora;
	}
	public void setCdAseguradora(Integer cdAseguradora) {
		this.cdAseguradora = cdAseguradora;
	}
	public Integer getCdRamo() {
		return cdRamo;
	}
	public void setCdRamo(Integer cdRamo) {
		this.cdRamo = cdRamo;
	}
	public Integer getCdCompania() {
		return cdCompania;
	}
	public void setCdCompania(Integer cdCompania) {
		this.cdCompania = cdCompania;
	}
	public Integer getCdRamoCotizacion() {
		return cdRamoCotizacion;
	}
	public void setCdRamoCotizacion(Integer cdRamoCotizacion) {
		this.cdRamoCotizacion = cdRamoCotizacion;
	}
	public Integer getCdCliente() {
		return cdCliente;
	}
	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}
	public Integer getFcCreacionJul() {
		return fcCreacionJul;
	}
	public void setFcCreacionJul(Integer fcCreacionJul) {
		this.fcCreacionJul = fcCreacionJul;
	}
	public Date getFcCreacion() {
		return fcCreacion;
	}
	public void setFcCreacion(Date fcCreacion) {
		this.fcCreacion = fcCreacion;
	}
	public Integer getFcSiniestroJul() {
		return fcSiniestroJul;
	}
	public void setFcSiniestroJul(Integer fcSiniestroJul) {
		this.fcSiniestroJul = fcSiniestroJul;
	}
	public Date getFcSiniestro() {
		return fcSiniestro;
	}
	public void setFcSiniestro(Date fcSiniestro) {
		this.fcSiniestro = fcSiniestro;
	}
	public String getNumeroReporteAseg() {
		return numeroReporteAseg;
	}
	public void setNumeroReporteAseg(String numeroReporteAseg) {
		this.numeroReporteAseg = numeroReporteAseg;
	}
	public String getNumeroSiniestroAseg() {
		return numeroSiniestroAseg;
	}
	public void setNumeroSiniestroAseg(String numeroSiniestroAseg) {
		this.numeroSiniestroAseg = numeroSiniestroAseg;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(Integer bloqueo) {
		this.bloqueo = bloqueo;
	}
	public String getNm_Ramo() {
		return nm_Ramo;
	}
	public void setNm_Ramo(String nm_Ramo) {
		this.nm_Ramo = nm_Ramo;
	}
	public String getNm_aseguradora() {
		return nm_aseguradora;
	}
	public void setNm_aseguradora(String nm_aseguradora) {
		this.nm_aseguradora = nm_aseguradora;
	}
	public String getNm_cliente() {
		return nm_cliente;
	}
	public void setNm_cliente(String nm_cliente) {
		this.nm_cliente = nm_cliente;
	}
	public Date getFc_vig_pol_desde() {
		return fc_vig_pol_desde;
	}
	public void setFc_vig_pol_desde(Date fc_vig_pol_desde) {
		this.fc_vig_pol_desde = fc_vig_pol_desde;
	}
	public Date getFc_vig_pol_hasta() {
		return fc_vig_pol_hasta;
	}
	public void setFc_vig_pol_hasta(Date fc_vig_pol_hasta) {
		this.fc_vig_pol_hasta = fc_vig_pol_hasta;
	}
	public String getReferencias() {
		return referencias;
	}
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}
	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}
	public String getNm_subagente() {
		return nm_subagente;
	}
	public void setNm_subagente(String nm_subagente) {
		this.nm_subagente = nm_subagente;
	}
	public Integer getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(Integer cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	public Integer getCd_ciudad() {
		return cd_ciudad;
	}
	public void setCd_ciudad(Integer cd_ciudad) {
		this.cd_ciudad = cd_ciudad;
	}
	public String getNm_ciudad() {
		return nm_ciudad;
	}
	public void setNm_ciudad(String nm_ciudad) {
		this.nm_ciudad = nm_ciudad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}
	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public String getCaso() {
		return caso;
	}
	public void setCaso(String caso) {
		this.caso = caso;
	}
	
}
