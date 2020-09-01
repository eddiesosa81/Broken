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
@Table(name = "objeto_cotizacion_tbl")
public class ObjetoCotizacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_CD_OBJ_COT")
	@SequenceGenerator(sequenceName = "secuencia_CD_OBJ_COT", name = "secuencia_CD_OBJ_COT", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_obj_cotizacion")
	private Integer cd_obj_cotizacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_ubicacion")
	private Integer cd_ubicacion;
	@Column(name="descripcion_objeto")
	private String descripcion_objeto;
	@Column(name="fc_ini_obj_cot")
	private Integer fc_ini_obj_cot;
	@Column(name="fc_fin_obj_cot")
	private Integer fc_fin_obj_cot;
	@Column(name="fc_ini_obj_cot_date")
	private Date fc_ini_obj_cot_date;
	@Column(name="fc_fin_obj_cot_date")
	private Date fc_fin_obj_cot_date;
	@Column(name="dias_vigencia")
	private Integer dias_vigencia;
	@Column(name="valor_asegurador_objeto")
	private Double valor_asegurador_objeto;
	@Column(name="tasa_objeto")
	private Double tasa_objeto;
	@Column(name="factor_objeto")
	private Double factor_objeto;
	@Column(name="prima_objeto")
	private Double prima_objeto;
	@Column(name="observaciones_objeto")
	private String  observaciones_objeto;
	@Column(name="item_aseguradora")
	private String item_aseguradora;
	@Column(name="total_asegurado_objeto")
	private Double total_asegurado_objeto;
	@Column(name="cd_obj_ori")
	private Integer cdObjOri;
	@Column(name="deducible_Minimo")
	private Double deducibleMinimo;
	@Column(name="fecha_nacimiento_obj")
	private Date fecha_nacimiento_obj ;
	@Column(name="cedula_obj")
	private String cedula_obj;
	@Column(name="edad_obj")
	private String edad_obj;
	
	public Integer getCd_obj_cotizacion() {
		return cd_obj_cotizacion;
	}
	public void setCd_obj_cotizacion(Integer cd_obj_cotizacion) {
		this.cd_obj_cotizacion = cd_obj_cotizacion;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_ubicacion() {
		return cd_ubicacion;
	}
	public void setCd_ubicacion(Integer cd_ubicacion) {
		this.cd_ubicacion = cd_ubicacion;
	}
	public String getDescripcion_objeto() {
		return descripcion_objeto;
	}
	public void setDescripcion_objeto(String descripcion_objeto) {
		this.descripcion_objeto = descripcion_objeto;
	}
	public Integer getFc_ini_obj_cot() {
		return fc_ini_obj_cot;
	}
	public void setFc_ini_obj_cot(Integer fc_ini_obj_cot) {
		this.fc_ini_obj_cot = fc_ini_obj_cot;
	}
	public Integer getFc_fin_obj_cot() {
		return fc_fin_obj_cot;
	}
	public void setFc_fin_obj_cot(Integer fc_fin_obj_cot) {
		this.fc_fin_obj_cot = fc_fin_obj_cot;
	}
	public Date getFc_ini_obj_cot_date() {
		return fc_ini_obj_cot_date;
	}
	public void setFc_ini_obj_cot_date(Date fc_ini_obj_cot_date) {
		this.fc_ini_obj_cot_date = fc_ini_obj_cot_date;
	}
	public Date getFc_fin_obj_cot_date() {
		return fc_fin_obj_cot_date;
	}
	public void setFc_fin_obj_cot_date(Date fc_fin_obj_cot_date) {
		this.fc_fin_obj_cot_date = fc_fin_obj_cot_date;
	}
	public Integer getDias_vigencia() {
		return dias_vigencia;
	}
	public void setDias_vigencia(Integer dias_vigencia) {
		this.dias_vigencia = dias_vigencia;
	}
	public Double getValor_asegurador_objeto() {
		return valor_asegurador_objeto;
	}
	public void setValor_asegurador_objeto(Double valor_asegurador_objeto) {
		this.valor_asegurador_objeto = valor_asegurador_objeto;
	}
	public Double getTasa_objeto() {
		return tasa_objeto;
	}
	public void setTasa_objeto(Double tasa_objeto) {
		this.tasa_objeto = tasa_objeto;
	}
	public Double getFactor_objeto() {
		return factor_objeto;
	}
	public void setFactor_objeto(Double factor_objeto) {
		this.factor_objeto = factor_objeto;
	}
	public Double getPrima_objeto() {
		return prima_objeto;
	}
	public void setPrima_objeto(Double prima_objeto) {
		this.prima_objeto = prima_objeto;
	}
	public String getObservaciones_objeto() {
		return observaciones_objeto;
	}
	public void setObservaciones_objeto(String observaciones_objeto) {
		this.observaciones_objeto = observaciones_objeto;
	}
	public String getItem_aseguradora() {
		return item_aseguradora;
	}
	public void setItem_aseguradora(String item_aseguradora) {
		this.item_aseguradora = item_aseguradora;
	}
	public Double getTotal_asegurado_objeto() {
		return total_asegurado_objeto;
	}
	public void setTotal_asegurado_objeto(Double total_asegurado_objeto) {
		this.total_asegurado_objeto = total_asegurado_objeto;
	}
	public Integer getCdObjOri() {
		return cdObjOri;
	}
	public void setCdObjOri(Integer cdObjOri) {
		this.cdObjOri = cdObjOri;
	}
	public Double getDeducibleMinimo() {
		return deducibleMinimo;
	}
	public void setDeducibleMinimo(Double deducibleMinimo) {
		this.deducibleMinimo = deducibleMinimo;
	}
	
	public Date getFecha_nacimiento_obj() {
		return fecha_nacimiento_obj;
	}
	public void setFecha_nacimiento_obj(Date fecha_nacimiento_obj) {
		this.fecha_nacimiento_obj = fecha_nacimiento_obj;
	}
	public String getCedula_obj() {
		return cedula_obj;
	}
	public void setCedula_obj(String cedula_obj) {
		this.cedula_obj = cedula_obj;
	}
	public String getEdad_obj() {
		return edad_obj;
	}
	public void setEdad_obj(String edad_obj) {
		this.edad_obj = edad_obj;
	}
}
