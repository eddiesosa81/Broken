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
@Table(name = "DEPRECIACION_tbl")
public class Depreciacion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cD_DEPRECIACION")
	@SequenceGenerator(sequenceName = "secuencia_cD_DEPRECIACION", name = "secuencia_cD_DEPRECIACION", initialValue= 1, allocationSize = 1000)
	@Column(name="CD_DEPRECIACION")
	private Integer CD_DEPRECIACION;
	
	@Column(name="CD_COMPANIA")
	private Integer CD_COMPANIA;
	@Column(name="CD_OBJ_COTIZACION")
	private Integer CD_OBJ_COTIZACION;
	@Column(name="ITEM")
	private Integer ITEM;
	@Column(name="ANO")
	private Integer ANO;
	@Column(name="VAL_ASEGURADO")
	private Double VAL_ASEGURADO;
	@Column(name="PORCENTAJE")
	private Double PORCENTAJE;
	@Column(name="TASA")
	private Double TASA;
	@Column(name="FACTOR")
	private Double FACTOR;
	@Column(name="PRIMA")
	private Double PRIMA;
	@Column(name="FC_DESDE")
	private Date FC_DESDE;
	@Column(name="FC_HASTA")
	private Date FC_HASTA;
	@Column(name="DIAS")
	private Integer DIAS;
	@Column(name="OBSERVACION")
	private String OBSERVACION;
	public Integer getCD_DEPRECIACION() {
		return CD_DEPRECIACION;
	}
	public void setCD_DEPRECIACION(Integer cD_DEPRECIACION) {
		CD_DEPRECIACION = cD_DEPRECIACION;
	}
	public Integer getCD_COMPANIA() {
		return CD_COMPANIA;
	}
	public void setCD_COMPANIA(Integer cD_COMPANIA) {
		CD_COMPANIA = cD_COMPANIA;
	}
	public Integer getCD_OBJ_COTIZACION() {
		return CD_OBJ_COTIZACION;
	}
	public void setCD_OBJ_COTIZACION(Integer cD_OBJ_COTIZACION) {
		CD_OBJ_COTIZACION = cD_OBJ_COTIZACION;
	}
	public Integer getITEM() {
		return ITEM;
	}
	public void setITEM(Integer iTEM) {
		ITEM = iTEM;
	}
	public Integer getANO() {
		return ANO;
	}
	public void setANO(Integer aNO) {
		ANO = aNO;
	}
	public Double getVAL_ASEGURADO() {
		return VAL_ASEGURADO;
	}
	public void setVAL_ASEGURADO(Double vAL_ASEGURADO) {
		VAL_ASEGURADO = vAL_ASEGURADO;
	}
	public Double getPORCENTAJE() {
		return PORCENTAJE;
	}
	public void setPORCENTAJE(Double pORCENTAJE) {
		PORCENTAJE = pORCENTAJE;
	}
	public Double getTASA() {
		return TASA;
	}
	public void setTASA(Double tASA) {
		TASA = tASA;
	}
	public Double getFACTOR() {
		return FACTOR;
	}
	public void setFACTOR(Double fACTOR) {
		FACTOR = fACTOR;
	}
	public Double getPRIMA() {
		return PRIMA;
	}
	public void setPRIMA(Double pRIMA) {
		PRIMA = pRIMA;
	}
	public Date getFC_DESDE() {
		return FC_DESDE;
	}
	public void setFC_DESDE(Date fC_DESDE) {
		FC_DESDE = fC_DESDE;
	}
	public Date getFC_HASTA() {
		return FC_HASTA;
	}
	public void setFC_HASTA(Date fC_HASTA) {
		FC_HASTA = fC_HASTA;
	}
	public Integer getDIAS() {
		return DIAS;
	}
	public void setDIAS(Integer dIAS) {
		DIAS = dIAS;
	}
	public String getOBSERVACION() {
		return OBSERVACION;
	}
	public void setOBSERVACION(String oBSERVACION) {
		OBSERVACION = oBSERVACION;
	}
	
	

}
