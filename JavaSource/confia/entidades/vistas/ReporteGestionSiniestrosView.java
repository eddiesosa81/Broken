package confia.entidades.vistas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reporte_gestion_siniestro_view")
public class ReporteGestionSiniestrosView {
	
	@Id
	@Column(name="CODID")
	public String codid;	
	@Column(name="CLIENTE")
	public String	CLIENTE;
	@Column(name="NUMERO_SINIESTRO")
	public Integer	NUMERO_SINIESTRO;
	@Column(name="EJECUTIVO_COMERCIAL")
	public String	EJECUTIVO_COMERCIAL;
	@Column(name="RAMO")
	public String	RAMO;
	@Column(name="ASEGURADORA")
	public String	ASEGURADORA;
	@Column(name="POLIZA")
	public String	POLIZA;
	@Column(name="PLACA")
	public String	PLACA;
	@Column(name="PRIMA_NETA")
	public Double	PRIMA_NETA;
	@Column(name="DEDUCIBLE")
	public Double	DEDUCIBLE;
	@Column(name="VALOR_PERDIDA")
	public Double	VALOR_PERDIDA;
	@Column(name="TIPO_CLIENTE_POLIZA")
	public String	TIPO_CLIENTE_POLIZA;
	@Column(name="ANIO_VH")
	public String	ANIO_VH;
	@Column(name="CEDULA")
	public String	CEDULA;
	@Column(name="GRUPOCONTRATANTE")
	public String	GRUPOCONTRATANTE;
	@Column(name="INICIO_VIGENCIA")
	public Date	INICIO_VIGENCIA;
	@Column(name="FIN_VIGENCIA")
	public Date	FIN_VIGENCIA;
	@Column(name="ESTADO")
	public String	ESTADO;
	@Column(name="FECHA_REPORTE_SINIESTRO")
	public String	FECHA_REPORTE_SINIESTRO;
	@Column(name="CANAL")
	public String	CANAL;
	@Column(name="DSC_OBJETO")
	public String	DSC_OBJETO;
	@Column(name="DESCRIPCION_SUBOBJETO")
	public String	DESCRIPCION_SUBOBJETO;
	@Column(name="FECHA_OCURRENCIA")
	public Date	FECHA_OCURRENCIA;
	@Column(name="DESCRIPCION_GESTION")
	public String	DESCRIPCION_GESTION;
	@Column(name="FECHA_CONTACTO")
	public Date	FECHA_CONTACTO;
	@Column(name="TIPO_GESTION")
	public String	TIPO_GESTION;
	@Column(name="MEDIO")
	public String	MEDIO;
	@Column(name="FECHA_SEGUIMIENTO")
	public Date	FECHA_SEGUIMIENTO;
	@Column(name="EJECUTIVO_SINIESTROS")
	public String	EJECUTIVO_SINIESTROS;
	@Column(name="nm_grupo_contratante")
	public String nm_grupo_contratante;
	@Column(name="cd_cliente")
	public Integer cd_cliente;
	@Column(name="cd_grupo_contratante")
	public Integer cd_grupo_contratante;
	@Column(name="cd_aseguradora")
	public Integer cd_aseguradora;
	@Column(name="cd_ramo")
	public Integer cd_ramo;
	@Column(name="cd_canal")
	public Integer cd_canal;
	@Column(name="cd_ejecutivo")
	public Integer cd_ejecutivo;
	
	
	
	public String getNm_grupo_contratante() {
		return nm_grupo_contratante;
	}
	public void setNm_grupo_contratante(String nm_grupo_contratante) {
		this.nm_grupo_contratante = nm_grupo_contratante;
	}
	public Integer getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public Integer getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(Integer cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public Integer getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(Integer cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public Integer getCd_canal() {
		return cd_canal;
	}
	public void setCd_canal(Integer cd_canal) {
		this.cd_canal = cd_canal;
	}
	public Integer getCd_ejecutivo() {
		return cd_ejecutivo;
	}
	public void setCd_ejecutivo(Integer cd_ejecutivo) {
		this.cd_ejecutivo = cd_ejecutivo;
	}
	public String getCodid() {
		return codid;
	}
	public void setCodid(String codid) {
		this.codid = codid;
	}
	public String getCLIENTE() {
		return CLIENTE;
	}
	public void setCLIENTE(String cLIENTE) {
		CLIENTE = cLIENTE;
	}
	public Integer getNUMERO_SINIESTRO() {
		return NUMERO_SINIESTRO;
	}
	public void setNUMERO_SINIESTRO(Integer nUMERO_SINIESTRO) {
		NUMERO_SINIESTRO = nUMERO_SINIESTRO;
	}
	public String getEJECUTIVO_COMERCIAL() {
		return EJECUTIVO_COMERCIAL;
	}
	public void setEJECUTIVO_COMERCIAL(String eJECUTIVO_COMERCIAL) {
		EJECUTIVO_COMERCIAL = eJECUTIVO_COMERCIAL;
	}
	public String getRAMO() {
		return RAMO;
	}
	public void setRAMO(String rAMO) {
		RAMO = rAMO;
	}
	public String getASEGURADORA() {
		return ASEGURADORA;
	}
	public void setASEGURADORA(String aSEGURADORA) {
		ASEGURADORA = aSEGURADORA;
	}
	public String getPOLIZA() {
		return POLIZA;
	}
	public void setPOLIZA(String pOLIZA) {
		POLIZA = pOLIZA;
	}
	public String getPLACA() {
		return PLACA;
	}
	public void setPLACA(String pLACA) {
		PLACA = pLACA;
	}
	public Double getPRIMA_NETA() {
		return PRIMA_NETA;
	}
	public void setPRIMA_NETA(Double pRIMA_NETA) {
		PRIMA_NETA = pRIMA_NETA;
	}
	public Double getDEDUCIBLE() {
		return DEDUCIBLE;
	}
	public void setDEDUCIBLE(Double dEDUCIBLE) {
		DEDUCIBLE = dEDUCIBLE;
	}
	public Double getVALOR_PERDIDA() {
		return VALOR_PERDIDA;
	}
	public void setVALOR_PERDIDA(Double vALOR_PERDIDA) {
		VALOR_PERDIDA = vALOR_PERDIDA;
	}
	public String getTIPO_CLIENTE_POLIZA() {
		return TIPO_CLIENTE_POLIZA;
	}
	public void setTIPO_CLIENTE_POLIZA(String tIPO_CLIENTE_POLIZA) {
		TIPO_CLIENTE_POLIZA = tIPO_CLIENTE_POLIZA;
	}
	public String getANIO_VH() {
		return ANIO_VH;
	}
	public void setANIO_VH(String aNIO_VH) {
		ANIO_VH = aNIO_VH;
	}
	public String getCEDULA() {
		return CEDULA;
	}
	public void setCEDULA(String cEDULA) {
		CEDULA = cEDULA;
	}
	public String getGRUPOCONTRATANTE() {
		return GRUPOCONTRATANTE;
	}
	public void setGRUPOCONTRATANTE(String gRUPOCONTRATANTE) {
		GRUPOCONTRATANTE = gRUPOCONTRATANTE;
	}
	public Date getINICIO_VIGENCIA() {
		return INICIO_VIGENCIA;
	}
	public void setINICIO_VIGENCIA(Date iNICIO_VIGENCIA) {
		INICIO_VIGENCIA = iNICIO_VIGENCIA;
	}
	public Date getFIN_VIGENCIA() {
		return FIN_VIGENCIA;
	}
	public void setFIN_VIGENCIA(Date fIN_VIGENCIA) {
		FIN_VIGENCIA = fIN_VIGENCIA;
	}
	public String getESTADO() {
		return ESTADO;
	}
	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}
	public String getFECHA_REPORTE_SINIESTRO() {
		return FECHA_REPORTE_SINIESTRO;
	}
	public void setFECHA_REPORTE_SINIESTRO(String fECHA_REPORTE_SINIESTRO) {
		FECHA_REPORTE_SINIESTRO = fECHA_REPORTE_SINIESTRO;
	}
	public String getCANAL() {
		return CANAL;
	}
	public void setCANAL(String cANAL) {
		CANAL = cANAL;
	}
	public String getDSC_OBJETO() {
		return DSC_OBJETO;
	}
	public void setDSC_OBJETO(String dSC_OBJETO) {
		DSC_OBJETO = dSC_OBJETO;
	}
	public String getDESCRIPCION_SUBOBJETO() {
		return DESCRIPCION_SUBOBJETO;
	}
	public void setDESCRIPCION_SUBOBJETO(String dESCRIPCION_SUBOBJETO) {
		DESCRIPCION_SUBOBJETO = dESCRIPCION_SUBOBJETO;
	}
	public Date getFECHA_OCURRENCIA() {
		return FECHA_OCURRENCIA;
	}
	public void setFECHA_OCURRENCIA(Date fECHA_OCURRENCIA) {
		FECHA_OCURRENCIA = fECHA_OCURRENCIA;
	}
	public String getDESCRIPCION_GESTION() {
		return DESCRIPCION_GESTION;
	}
	public void setDESCRIPCION_GESTION(String dESCRIPCION_GESTION) {
		DESCRIPCION_GESTION = dESCRIPCION_GESTION;
	}
	public Date getFECHA_CONTACTO() {
		return FECHA_CONTACTO;
	}
	public void setFECHA_CONTACTO(Date fECHA_CONTACTO) {
		FECHA_CONTACTO = fECHA_CONTACTO;
	}
	public String getTIPO_GESTION() {
		return TIPO_GESTION;
	}
	public void setTIPO_GESTION(String tIPO_GESTION) {
		TIPO_GESTION = tIPO_GESTION;
	}
	public String getMEDIO() {
		return MEDIO;
	}
	public void setMEDIO(String mEDIO) {
		MEDIO = mEDIO;
	}
	public Date getFECHA_SEGUIMIENTO() {
		return FECHA_SEGUIMIENTO;
	}
	public void setFECHA_SEGUIMIENTO(Date fECHA_SEGUIMIENTO) {
		FECHA_SEGUIMIENTO = fECHA_SEGUIMIENTO;
	}
	public String getEJECUTIVO_SINIESTROS() {
		return EJECUTIVO_SINIESTROS;
	}
	public void setEJECUTIVO_SINIESTROS(String eJECUTIVO_SINIESTROS) {
		EJECUTIVO_SINIESTROS = eJECUTIVO_SINIESTROS;
	}
	
	

}
