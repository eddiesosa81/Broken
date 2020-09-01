package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COBERTURAS_ADICIONALES_TBL")
public class CoberturasAdicionales {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_cob_adc")
	@SequenceGenerator(sequenceName = "secuencia_cd_cob_adc", name = "secuencia_cd_cob_adc", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_cob_adc") 
	private Integer cd_cob_adc;
	@Column(name="cd_compania") 
	private Integer  cd_compania;
	@Column(name="cd_cobertura") 
	private Integer cd_cobertura;
	@Column(name="cd_aseguradora") 
	private Integer cd_aseguradora;
    @Column(name="cd_ramo") 
	private Integer cd_ramo;
	@Column(name="cd_ramo_cotizacion") 
	private Integer  cd_ramo_cotizacion;
	@Column(name="cd_ubicacion") 
	private Integer cd_ubicacion;
    @Column(name="cd_obj_cotizacion") 
	private Integer  cd_obj_cotizacion;
	@Column(name="cd_subobj_cotizacion") 
	private Integer  cd_subobj_cotizacion;
	@Column(name="afecta_va") 
	private String afecta_va;
    @Column(name="afecta_prima") 
	private String afecta_prima;
	@Column(name="adicional") 
	private String  adicional;
	@Column(name="val_limite") 
	private Double val_limite;
	@Column(name="unidad") 
	private String unidad;
	@Column(name="tasa") 
	private Double  tasa;
    @Column(name="factor") 
	private Integer factor;
	@Column(name="val_prima") 
	private Double val_prima; 
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="desc_cobertura")
	private String descCobertura;
	public Integer getCd_cob_adc() {
		return cd_cob_adc;
	}
	public void setCd_cob_adc(Integer cd_cob_adc) {
		this.cd_cob_adc = cd_cob_adc;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_cobertura() {
		return cd_cobertura;
	}
	public void setCd_cobertura(Integer cd_cobertura) {
		this.cd_cobertura = cd_cobertura;
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
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public Integer getCd_ubicacion() {
		return cd_ubicacion;
	}
	public void setCd_ubicacion(Integer cd_ubicacion) {
		this.cd_ubicacion = cd_ubicacion;
	}
	public Integer getCd_obj_cotizacion() {
		return cd_obj_cotizacion;
	}
	public void setCd_obj_cotizacion(Integer cd_obj_cotizacion) {
		this.cd_obj_cotizacion = cd_obj_cotizacion;
	}
	public Integer getCd_subobj_cotizacion() {
		return cd_subobj_cotizacion;
	}
	public void setCd_subobj_cotizacion(Integer cd_subobj_cotizacion) {
		this.cd_subobj_cotizacion = cd_subobj_cotizacion;
	}
	public String getAfecta_va() {
		return afecta_va;
	}
	public void setAfecta_va(String afecta_va) {
		this.afecta_va = afecta_va;
	}
	public String getAfecta_prima() {
		return afecta_prima;
	}
	public void setAfecta_prima(String afecta_prima) {
		this.afecta_prima = afecta_prima;
	}
	public String getAdicional() {
		return adicional;
	}
	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}
	public Double getVal_limite() {
		return val_limite;
	}
	public void setVal_limite(Double val_limite) {
		this.val_limite = val_limite;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public Double getTasa() {
		return tasa;
	}
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	public Integer getFactor() {
		return factor;
	}
	public void setFactor(Integer factor) {
		this.factor = factor;
	}
	public Double getVal_prima() {
		return val_prima;
	}
	public void setVal_prima(Double val_prima) {
		this.val_prima = val_prima;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getDescCobertura() {
		return descCobertura;
	}
	public void setDescCobertura(String descCobertura) {
		this.descCobertura = descCobertura;
	}
	

}
