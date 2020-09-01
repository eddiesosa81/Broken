package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Emision_masiva_tmp")
public class EmisionMasiva {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_ID_EMISION_MAS")
	@SequenceGenerator(sequenceName = "secuencia_ID_EMISION_MAS", name = "secuencia_ID_EMISION_MAS", initialValue= 1, allocationSize = 1000)
	@Column(name="ID_EMISION_MAS")
	private Integer ID_EMISION_MAS;
	@Column(name="TIPO_PERSONA_CLIENTE")
	private String TIPO_PERSONA_CLIENTE;
	@Column(name="TIPO_IDENTIFICACION_CLIENTE")
	private String TIPO_IDENTIFICACION_CLIENTE;
	@Column(name="IDENTIFICACION_CLIENTE")
	private String IDENTIFICACION_CLIENTE;
	@Column(name="PRIMER_NOMBRE_CLIENTE")
	private String PRIMER_NOMBRE_CLIENTE;
	@Column(name="SEGUNDO_NOMBRE_CLIENTE")
	private String SEGUNDO_NOMBRE_CLIENTE;
	@Column(name="PRIMER_APELLIDO_CLIENTE")
	private String PRIMER_APELLIDO_CLIENTE;
	@Column(name="SEGUNDO_APELLIDO_CLIENTE")
	private String SEGUNDO_APELLIDO_CLIENTE;
	@Column(name="RAZON_SOCIAL_CLIENTE")
	private String RAZON_SOCIAL_CLIENTE;
	@Column(name="GENERO_CLIENTE")
	private String GENERO_CLIENTE;
	@Column(name="ESTADO_CIVIL_CLIENTE")
	private String ESTADO_CIVIL_CLIENTE;
	@Column(name="TIPO_CLIENTE")
	private String TIPO_CLIENTE;
	@Column(name="CD_NACIONALIDAD")
	private String CD_NACIONALIDAD;
	@Column(name="EMPRESA")
	private String EMPRESA;
	@Column(name="CD_ASEGURADORA")
	private String CD_ASEGURADORA;
	@Column(name="CD_RAMO")
	private String CD_RAMO;
	@Column(name="CD_EJECUTIVO")
	private String CD_EJECUTIVO;
	@Column(name="CD_CANAL")
	private String CD_CANAL;
	@Column(name="CD_PLAN")
	private String CD_PLAN;
	@Column(name="CD_GRUPO_CONTRATANTE")
	private String CD_GRUPO_CONTRATANTE;
	@Column(name="POLIZA")
	private String POLIZA;
	@Column(name="FACTURA_ASEGURADORA")
	private String FACTURA_ASEGURADORA;
	@Column(name="FC_INI_VIG_DATE")
	private String FC_INI_VIG_DATE;
	@Column(name="FC_FIN_VIG_DATE")
	private String FC_FIN_VIG_DATE;
	@Column(name="DIAS_VIGENCIA")
	private String DIAS_VIGENCIA;
	@Column(name="TOTAL_ASEGURADO")
	private String TOTAL_ASEGURADO;
	@Column(name="TOTAL_PRIMA")
	private String TOTAL_PRIMA;
	@Column(name="ANEXO")
	private String ANEXO;
	@Column(name="FC_EMISION_ASEGURADORA_DATE")
	private String FC_EMISION_ASEGURADORA_DATE;
	@Column(name="DSC_UBICACION")
	private String DSC_UBICACION;
	@Column(name="DESCRIPCION_OBJETO")
	private String DESCRIPCION_OBJETO;
	@Column(name="VALOR_ASEGURADOR_OBJETO")
	private String VALOR_ASEGURADOR_OBJETO;
	@Column(name="TASA_OBJETO")
	private String TASA_OBJETO;
	@Column(name="FACTOR_OBJETO")
	private String FACTOR_OBJETO;
	@Column(name="PRIMA_OBJETO")
	private String PRIMA_OBJETO;
	@Column(name="OBSERVACIONES_OBJETO")
	private String OBSERVACIONES_OBJETO;
	@Column(name="DEDUCIBLE_MINIMO")
	private String DEDUCIBLE_MINIMO;
	@Column(name="NUM_ALTERNATIVA_FORMAPAGO")
	private String NUM_ALTERNATIVA_FORMAPAGO;
	@Column(name="NUM_PAGOS_FORMAPAGO")
	private String NUM_PAGOS_FORMAPAGO;
	@Column(name="TOTAL_PRIMA_FORMAPAGO")
	private String TOTAL_PRIMA_FORMAPAGO;
	@Column(name="TOTAL_PAGO_FORMAPAGO")
	private String TOTAL_PAGO_FORMAPAGO;
	@Column(name="DERECHO_EMISION_FORMAPAGO")
	private String DERECHO_EMISION_FORMAPAGO;
	@Column(name="IVA_FORMAPAGO")
	private String IVA_FORMAPAGO;
	@Column(name="SUPER_BANCOS_FORMAPAGO")
	private String SUPER_BANCOS_FORMAPAGO;
	@Column(name="SEGURO_CAMPESINO")
	private String SEGURO_CAMPESINO;
	@Column(name="OBSERVACIONES")
	private String OBSERVACIONES;
	@Column(name="usrlogin")
	private String usrlogin;
	@Column(name="ESTADO")
	private String ESTADO;
	@Column(name="fecha_proceso")
	private String fecha_proceso;
	@Column(name="fecha_creacion")
	private String fecha_creacion;
	
	
	public String getFecha_proceso() {
		return fecha_proceso;
	}
	public void setFecha_proceso(String fecha_proceso) {
		this.fecha_proceso = fecha_proceso;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Integer getID_EMISION_MAS() {
		return ID_EMISION_MAS;
	}
	public void setID_EMISION_MAS(Integer iD_EMISION_MAS) {
		ID_EMISION_MAS = iD_EMISION_MAS;
	}
	public String getTIPO_PERSONA_CLIENTE() {
		return TIPO_PERSONA_CLIENTE;
	}
	public void setTIPO_PERSONA_CLIENTE(String tIPO_PERSONA_CLIENTE) {
		TIPO_PERSONA_CLIENTE = tIPO_PERSONA_CLIENTE;
	}
	public String getTIPO_IDENTIFICACION_CLIENTE() {
		return TIPO_IDENTIFICACION_CLIENTE;
	}
	public void setTIPO_IDENTIFICACION_CLIENTE(String tIPO_IDENTIFICACION_CLIENTE) {
		TIPO_IDENTIFICACION_CLIENTE = tIPO_IDENTIFICACION_CLIENTE;
	}
	public String getIDENTIFICACION_CLIENTE() {
		return IDENTIFICACION_CLIENTE;
	}
	public void setIDENTIFICACION_CLIENTE(String iDENTIFICACION_CLIENTE) {
		IDENTIFICACION_CLIENTE = iDENTIFICACION_CLIENTE;
	}
	public String getPRIMER_NOMBRE_CLIENTE() {
		return PRIMER_NOMBRE_CLIENTE;
	}
	public void setPRIMER_NOMBRE_CLIENTE(String pRIMER_NOMBRE_CLIENTE) {
		PRIMER_NOMBRE_CLIENTE = pRIMER_NOMBRE_CLIENTE;
	}
	public String getSEGUNDO_NOMBRE_CLIENTE() {
		return SEGUNDO_NOMBRE_CLIENTE;
	}
	public void setSEGUNDO_NOMBRE_CLIENTE(String sEGUNDO_NOMBRE_CLIENTE) {
		SEGUNDO_NOMBRE_CLIENTE = sEGUNDO_NOMBRE_CLIENTE;
	}
	public String getPRIMER_APELLIDO_CLIENTE() {
		return PRIMER_APELLIDO_CLIENTE;
	}
	public void setPRIMER_APELLIDO_CLIENTE(String pRIMER_APELLIDO_CLIENTE) {
		PRIMER_APELLIDO_CLIENTE = pRIMER_APELLIDO_CLIENTE;
	}
	public String getSEGUNDO_APELLIDO_CLIENTE() {
		return SEGUNDO_APELLIDO_CLIENTE;
	}
	public void setSEGUNDO_APELLIDO_CLIENTE(String sEGUNDO_APELLIDO_CLIENTE) {
		SEGUNDO_APELLIDO_CLIENTE = sEGUNDO_APELLIDO_CLIENTE;
	}
	public String getRAZON_SOCIAL_CLIENTE() {
		return RAZON_SOCIAL_CLIENTE;
	}
	public void setRAZON_SOCIAL_CLIENTE(String rAZON_SOCIAL_CLIENTE) {
		RAZON_SOCIAL_CLIENTE = rAZON_SOCIAL_CLIENTE;
	}
	public String getGENERO_CLIENTE() {
		return GENERO_CLIENTE;
	}
	public void setGENERO_CLIENTE(String gENERO_CLIENTE) {
		GENERO_CLIENTE = gENERO_CLIENTE;
	}
	public String getESTADO_CIVIL_CLIENTE() {
		return ESTADO_CIVIL_CLIENTE;
	}
	public void setESTADO_CIVIL_CLIENTE(String eSTADO_CIVIL_CLIENTE) {
		ESTADO_CIVIL_CLIENTE = eSTADO_CIVIL_CLIENTE;
	}
	public String getTIPO_CLIENTE() {
		return TIPO_CLIENTE;
	}
	public void setTIPO_CLIENTE(String tIPO_CLIENTE) {
		TIPO_CLIENTE = tIPO_CLIENTE;
	}
	public String getCD_NACIONALIDAD() {
		return CD_NACIONALIDAD;
	}
	public void setCD_NACIONALIDAD(String cD_NACIONALIDAD) {
		CD_NACIONALIDAD = cD_NACIONALIDAD;
	}
	public String getEMPRESA() {
		return EMPRESA;
	}
	public void setEMPRESA(String eMPRESA) {
		EMPRESA = eMPRESA;
	}
	public String getCD_ASEGURADORA() {
		return CD_ASEGURADORA;
	}
	public void setCD_ASEGURADORA(String cD_ASEGURADORA) {
		CD_ASEGURADORA = cD_ASEGURADORA;
	}
	public String getCD_RAMO() {
		return CD_RAMO;
	}
	public void setCD_RAMO(String cD_RAMO) {
		CD_RAMO = cD_RAMO;
	}
	public String getCD_EJECUTIVO() {
		return CD_EJECUTIVO;
	}
	public void setCD_EJECUTIVO(String cD_EJECUTIVO) {
		CD_EJECUTIVO = cD_EJECUTIVO;
	}
	public String getCD_CANAL() {
		return CD_CANAL;
	}
	public void setCD_CANAL(String cD_CANAL) {
		CD_CANAL = cD_CANAL;
	}
	public String getCD_PLAN() {
		return CD_PLAN;
	}
	public void setCD_PLAN(String cD_PLAN) {
		CD_PLAN = cD_PLAN;
	}
	public String getCD_GRUPO_CONTRATANTE() {
		return CD_GRUPO_CONTRATANTE;
	}
	public void setCD_GRUPO_CONTRATANTE(String cD_GRUPO_CONTRATANTE) {
		CD_GRUPO_CONTRATANTE = cD_GRUPO_CONTRATANTE;
	}
	public String getPOLIZA() {
		return POLIZA;
	}
	public void setPOLIZA(String pOLIZA) {
		POLIZA = pOLIZA;
	}
	public String getFACTURA_ASEGURADORA() {
		return FACTURA_ASEGURADORA;
	}
	public void setFACTURA_ASEGURADORA(String fACTURA_ASEGURADORA) {
		FACTURA_ASEGURADORA = fACTURA_ASEGURADORA;
	}
	public String getFC_INI_VIG_DATE() {
		return FC_INI_VIG_DATE;
	}
	public void setFC_INI_VIG_DATE(String fC_INI_VIG_DATE) {
		FC_INI_VIG_DATE = fC_INI_VIG_DATE;
	}
	public String getFC_FIN_VIG_DATE() {
		return FC_FIN_VIG_DATE;
	}
	public void setFC_FIN_VIG_DATE(String fC_FIN_VIG_DATE) {
		FC_FIN_VIG_DATE = fC_FIN_VIG_DATE;
	}
	public String getDIAS_VIGENCIA() {
		return DIAS_VIGENCIA;
	}
	public void setDIAS_VIGENCIA(String dIAS_VIGENCIA) {
		DIAS_VIGENCIA = dIAS_VIGENCIA;
	}
	public String getTOTAL_ASEGURADO() {
		return TOTAL_ASEGURADO;
	}
	public void setTOTAL_ASEGURADO(String tOTAL_ASEGURADO) {
		TOTAL_ASEGURADO = tOTAL_ASEGURADO;
	}
	public String getTOTAL_PRIMA() {
		return TOTAL_PRIMA;
	}
	public void setTOTAL_PRIMA(String tOTAL_PRIMA) {
		TOTAL_PRIMA = tOTAL_PRIMA;
	}
	public String getANEXO() {
		return ANEXO;
	}
	public void setANEXO(String aNEXO) {
		ANEXO = aNEXO;
	}
	public String getFC_EMISION_ASEGURADORA_DATE() {
		return FC_EMISION_ASEGURADORA_DATE;
	}
	public void setFC_EMISION_ASEGURADORA_DATE(String fC_EMISION_ASEGURADORA_DATE) {
		FC_EMISION_ASEGURADORA_DATE = fC_EMISION_ASEGURADORA_DATE;
	}
	public String getDSC_UBICACION() {
		return DSC_UBICACION;
	}
	public void setDSC_UBICACION(String dSC_UBICACION) {
		DSC_UBICACION = dSC_UBICACION;
	}
	public String getDESCRIPCION_OBJETO() {
		return DESCRIPCION_OBJETO;
	}
	public void setDESCRIPCION_OBJETO(String dESCRIPCION_OBJETO) {
		DESCRIPCION_OBJETO = dESCRIPCION_OBJETO;
	}
	public String getVALOR_ASEGURADOR_OBJETO() {
		return VALOR_ASEGURADOR_OBJETO;
	}
	public void setVALOR_ASEGURADOR_OBJETO(String vALOR_ASEGURADOR_OBJETO) {
		VALOR_ASEGURADOR_OBJETO = vALOR_ASEGURADOR_OBJETO;
	}
	public String getTASA_OBJETO() {
		return TASA_OBJETO;
	}
	public void setTASA_OBJETO(String tASA_OBJETO) {
		TASA_OBJETO = tASA_OBJETO;
	}
	public String getFACTOR_OBJETO() {
		return FACTOR_OBJETO;
	}
	public void setFACTOR_OBJETO(String fACTOR_OBJETO) {
		FACTOR_OBJETO = fACTOR_OBJETO;
	}
	public String getPRIMA_OBJETO() {
		return PRIMA_OBJETO;
	}
	public void setPRIMA_OBJETO(String pRIMA_OBJETO) {
		PRIMA_OBJETO = pRIMA_OBJETO;
	}
	public String getOBSERVACIONES_OBJETO() {
		return OBSERVACIONES_OBJETO;
	}
	public void setOBSERVACIONES_OBJETO(String oBSERVACIONES_OBJETO) {
		OBSERVACIONES_OBJETO = oBSERVACIONES_OBJETO;
	}
	public String getDEDUCIBLE_MINIMO() {
		return DEDUCIBLE_MINIMO;
	}
	public void setDEDUCIBLE_MINIMO(String dEDUCIBLE_MINIMO) {
		DEDUCIBLE_MINIMO = dEDUCIBLE_MINIMO;
	}
	public String getNUM_ALTERNATIVA_FORMAPAGO() {
		return NUM_ALTERNATIVA_FORMAPAGO;
	}
	public void setNUM_ALTERNATIVA_FORMAPAGO(String nUM_ALTERNATIVA_FORMAPAGO) {
		NUM_ALTERNATIVA_FORMAPAGO = nUM_ALTERNATIVA_FORMAPAGO;
	}
	public String getNUM_PAGOS_FORMAPAGO() {
		return NUM_PAGOS_FORMAPAGO;
	}
	public void setNUM_PAGOS_FORMAPAGO(String nUM_PAGOS_FORMAPAGO) {
		NUM_PAGOS_FORMAPAGO = nUM_PAGOS_FORMAPAGO;
	}
	public String getTOTAL_PRIMA_FORMAPAGO() {
		return TOTAL_PRIMA_FORMAPAGO;
	}
	public void setTOTAL_PRIMA_FORMAPAGO(String tOTAL_PRIMA_FORMAPAGO) {
		TOTAL_PRIMA_FORMAPAGO = tOTAL_PRIMA_FORMAPAGO;
	}
	public String getTOTAL_PAGO_FORMAPAGO() {
		return TOTAL_PAGO_FORMAPAGO;
	}
	public void setTOTAL_PAGO_FORMAPAGO(String tOTAL_PAGO_FORMAPAGO) {
		TOTAL_PAGO_FORMAPAGO = tOTAL_PAGO_FORMAPAGO;
	}
	public String getDERECHO_EMISION_FORMAPAGO() {
		return DERECHO_EMISION_FORMAPAGO;
	}
	public void setDERECHO_EMISION_FORMAPAGO(String dERECHO_EMISION_FORMAPAGO) {
		DERECHO_EMISION_FORMAPAGO = dERECHO_EMISION_FORMAPAGO;
	}
	public String getIVA_FORMAPAGO() {
		return IVA_FORMAPAGO;
	}
	public void setIVA_FORMAPAGO(String iVA_FORMAPAGO) {
		IVA_FORMAPAGO = iVA_FORMAPAGO;
	}
	public String getSUPER_BANCOS_FORMAPAGO() {
		return SUPER_BANCOS_FORMAPAGO;
	}
	public void setSUPER_BANCOS_FORMAPAGO(String sUPER_BANCOS_FORMAPAGO) {
		SUPER_BANCOS_FORMAPAGO = sUPER_BANCOS_FORMAPAGO;
	}
	public String getSEGURO_CAMPESINO() {
		return SEGURO_CAMPESINO;
	}
	public void setSEGURO_CAMPESINO(String sEGURO_CAMPESINO) {
		SEGURO_CAMPESINO = sEGURO_CAMPESINO;
	}
	public String getOBSERVACIONES() {
		return OBSERVACIONES;
	}
	public void setOBSERVACIONES(String oBSERVACIONES) {
		OBSERVACIONES = oBSERVACIONES;
	}
	public String getUsrlogin() {
		return usrlogin;
	}
	public void setUsrlogin(String usrlogin) {
		this.usrlogin = usrlogin;
	}
	public String getESTADO() {
		return ESTADO;
	}
	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}
	

}
