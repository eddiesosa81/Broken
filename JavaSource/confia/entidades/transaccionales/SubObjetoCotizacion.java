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
@Table(name = "subobjeto_cotizacion_tbl")
public class SubObjetoCotizacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_SUBOBJ_COT")
	@SequenceGenerator(sequenceName = "secuencia_CD_SUBOBJ_COT", name = "secuencia_CD_SUBOBJ_COT", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_subobj_cotizacion")
	private Integer cd_subobj_cotizacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_obj_cotizacion")
	private Integer cd_obj_cotizacion;
	@Column(name="descripcion_subobjeto")
	private String descripcion_subobjeto;
	@Column(name="fc_ini_subobj_cot")
	private Integer fc_ini_subobj_cot;
	@Column(name="fc_fin_subobj_cot")
	private Integer fc_fin_subobj_cot;
	@Column(name="fc_ini_subobj_cot_date")
	private Date fc_ini_subobj_cot_date;
	@Column(name="fc_fin_subobj_cot_date")
	private Date fc_fin_subobj_cot_date;
	@Column(name="dias_vigencia")
	private Integer dias_vigencia;
	@Column(name="valor_asegurador_subobjeto")
	private Double valor_asegurador_subobjeto;
	@Column(name="tasa_subobjeto")
	private Double tasa_subobjeto;
	@Column(name="factor_subobjeto")
	private Double factor_subobjeto;
	@Column(name="prima_subobjeto")
	private Double prima_subobjeto;
	@Column(name="observaciones_subobjeto")
	private String observaciones_subobjeto;
	@Column(name="item_aseguradora_subobjeto")
	private Integer item_aseguradora_subobjeto;
	@Column(name="cd_subobj_ori")
	private Integer cdSubObjOri;
	@Column(name="fecha_nacimiento_subobj")
	private Date fecha_nacimiento_subobj;
	@Column(name="parentesco")
	private String parentesco;
	@Column(name="cedula_subobj")
	private String cedula_subobj;
	@Column(name="edad_subobj")
	private String edad_subobj;
	
	public Integer getCd_subobj_cotizacion() {
		return cd_subobj_cotizacion;
	}
	public void setCd_subobj_cotizacion(Integer cd_subobj_cotizacion) {
		this.cd_subobj_cotizacion = cd_subobj_cotizacion;
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
	public String getDescripcion_subobjeto() {
		return descripcion_subobjeto;
	}
	public void setDescripcion_subobjeto(String descripcion_subobjeto) {
		this.descripcion_subobjeto = descripcion_subobjeto;
	}
	public Integer getFc_ini_subobj_cot() {
		return fc_ini_subobj_cot;
	}
	public void setFc_ini_subobj_cot(Integer fc_ini_subobj_cot) {
		this.fc_ini_subobj_cot = fc_ini_subobj_cot;
	}
	public Integer getFc_fin_subobj_cot() {
		return fc_fin_subobj_cot;
	}
	public void setFc_fin_subobj_cot(Integer fc_fin_subobj_cot) {
		this.fc_fin_subobj_cot = fc_fin_subobj_cot;
	}
	public Date getFc_ini_subobj_cot_date() {
		return fc_ini_subobj_cot_date;
	}
	public void setFc_ini_subobj_cot_date(Date fc_ini_subobj_cot_date) {
		this.fc_ini_subobj_cot_date = fc_ini_subobj_cot_date;
	}
	public Date getFc_fin_subobj_cot_date() {
		return fc_fin_subobj_cot_date;
	}
	public void setFc_fin_subobj_cot_date(Date fc_fin_subobj_cot_date) {
		this.fc_fin_subobj_cot_date = fc_fin_subobj_cot_date;
	}
	public Integer getDias_vigencia() {
		return dias_vigencia;
	}
	public void setDias_vigencia(Integer dias_vigencia) {
		this.dias_vigencia = dias_vigencia;
	}
	public Double getValor_asegurador_subobjeto() {
		return valor_asegurador_subobjeto;
	}
	public void setValor_asegurador_subobjeto(Double valor_asegurador_subobjeto) {
		this.valor_asegurador_subobjeto = valor_asegurador_subobjeto;
	}
	public Double getTasa_subobjeto() {
		return tasa_subobjeto;
	}
	public void setTasa_subobjeto(Double tasa_subobjeto) {
		this.tasa_subobjeto = tasa_subobjeto;
	}
	public Double getPrima_subobjeto() {
		return prima_subobjeto;
	}
	public void setPrima_subobjeto(Double prima_subobjeto) {
		this.prima_subobjeto = prima_subobjeto;
	}
	public String getObservaciones_subobjeto() {
		return observaciones_subobjeto;
	}
	public void setObservaciones_subobjeto(String observaciones_subobjeto) {
		this.observaciones_subobjeto = observaciones_subobjeto;
	}
	public Integer getItem_aseguradora_subobjeto() {
		return item_aseguradora_subobjeto;
	}
	public void setItem_aseguradora_subobjeto(Integer item_aseguradora_subobjeto) {
		this.item_aseguradora_subobjeto = item_aseguradora_subobjeto;
	}
	public Double getFactor_subobjeto() {
		return factor_subobjeto;
	}
	public void setFactor_subobjeto(Double factor_subobjeto) {
		this.factor_subobjeto = factor_subobjeto;
	}
	public Integer getCdSubObjOri() {
		return cdSubObjOri;
	}
	public void setCdSubObjOri(Integer cdSubObjOri) {
		this.cdSubObjOri = cdSubObjOri;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	
	public Date getFecha_nacimiento_subobj() {
		return fecha_nacimiento_subobj;
	}
	public void setFecha_nacimiento_subobj(Date fecha_nacimiento_subobj) {
		this.fecha_nacimiento_subobj = fecha_nacimiento_subobj;
	}
	public String getCedula_subobj() {
		return cedula_subobj;
	}
	public void setCedula_subobj(String cedula_subobj) {
		this.cedula_subobj = cedula_subobj;
	}
	public String getEdad_subobj() {
		return edad_subobj;
	}
	public void setEdad_subobj(String edad_subobj) {
		this.edad_subobj = edad_subobj;
	}

}
