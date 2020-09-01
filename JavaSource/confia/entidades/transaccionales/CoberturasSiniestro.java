package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cobertura_siniestro_tbl")
public class CoberturasSiniestro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_cob_siniestro")
	@SequenceGenerator(sequenceName = "secuencia_cd_cob_siniestro", name = "secuencia_cd_cob_siniestro", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_cob_siniestro")
	private Integer cd_cob_siniestro;
	
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="cd_cobertura")
	private Integer cd_cobertura;
	@Column(name="cd_ubicacion")
	private Integer cd_ubicacion;
	@Column(name="cd_obj_cotizacion")
	private Integer cd_obj_cotizacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="val_siniestro")
	private Double val_siniestro;
	@Column(name="nm_cobertura")
	private String nm_cobertura;
	@Column(name="val_deducible")
	private Double val_deducible;
	@Column(name="val_limite")
	private Double val_limite;
	@Column(name="val_minimo")
	private Double val_minimo;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="val_fijo")
	private Double val_fijo;
	@Column(name="aceptado")
	private Integer aceptado;
	@Column(name="pct_v_aseg")
	private Double pct_v_aseg;
	@Column(name="pct_reclamo")
	private Double pct_reclamo;
	@Column(name="val_monto_coa")
	private Double val_monto_coa;
	@Column(name="val_total")
	private Double val_total;
	@Column(name="num_liquidacion")
	private String num_liquidacion;
	@Column(name="val_exceso")
	private Double val_exceso;
	@Column(name="val_noelegible")
	private Double val_noelegible;
	@Column(name="val_cubierto")
	private Double val_cubierto;
	@Column(name="cd_deducible")
	private Integer cd_deducible;
	@Column(name="porcentaje_valor_siniestro")
    private Double porcentaje_valor_siniestro;
    @Column(name="porcentaje_valor_asegurado")
    private Double porcentaje_valor_asegurado;
	
	
	public Integer getCd_cob_siniestro() {
		return cd_cob_siniestro;
	}
	public void setCd_cob_siniestro(Integer cd_cob_siniestro) {
		this.cd_cob_siniestro = cd_cob_siniestro;
	}
	public Integer getCd_siniestro() {
		return cd_siniestro;
	}
	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public Integer getCd_cobertura() {
		return cd_cobertura;
	}
	public void setCd_cobertura(Integer cd_cobertura) {
		this.cd_cobertura = cd_cobertura;
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
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Double getVal_siniestro() {
		return val_siniestro;
	}
	public void setVal_siniestro(Double val_siniestro) {
		this.val_siniestro = val_siniestro;
	}
	public String getNm_cobertura() {
		return nm_cobertura;
	}
	public void setNm_cobertura(String nm_cobertura) {
		this.nm_cobertura = nm_cobertura;
	}
	public Double getVal_deducible() {
		return val_deducible;
	}
	public void setVal_deducible(Double val_deducible) {
		this.val_deducible = val_deducible;
	}
	public Double getVal_limite() {
		return val_limite;
	}
	public void setVal_limite(Double val_limite) {
		this.val_limite = val_limite;
	}
	public Double getVal_minimo() {
		return val_minimo;
	}
	public void setVal_minimo(Double val_minimo) {
		this.val_minimo = val_minimo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Double getVal_fijo() {
		return val_fijo;
	}
	public void setVal_fijo(Double val_fijo) {
		this.val_fijo = val_fijo;
	}
	public Integer getAceptado() {
		return aceptado;
	}
	public void setAceptado(Integer aceptado) {
		this.aceptado = aceptado;
	}
	public Double getPct_v_aseg() {
		return pct_v_aseg;
	}
	public void setPct_v_aseg(Double pct_v_aseg) {
		this.pct_v_aseg = pct_v_aseg;
	}
	public Double getPct_reclamo() {
		return pct_reclamo;
	}
	public void setPct_reclamo(Double pct_reclamo) {
		this.pct_reclamo = pct_reclamo;
	}
	public Double getVal_monto_coa() {
		return val_monto_coa;
	}
	public void setVal_monto_coa(Double val_monto_coa) {
		this.val_monto_coa = val_monto_coa;
	}
	public Double getVal_total() {
		return val_total;
	}
	public void setVal_total(Double val_total) {
		this.val_total = val_total;
	}
	public String getNum_liquidacion() {
		return num_liquidacion;
	}
	public void setNum_liquidacion(String num_liquidacion) {
		this.num_liquidacion = num_liquidacion;
	}
	public Double getVal_exceso() {
		return val_exceso;
	}
	public void setVal_exceso(Double val_exceso) {
		this.val_exceso = val_exceso;
	}
	public Double getVal_noelegible() {
		return val_noelegible;
	}
	public void setVal_noelegible(Double val_noelegible) {
		this.val_noelegible = val_noelegible;
	}
	public Double getVal_cubierto() {
		return val_cubierto;
	}
	public void setVal_cubierto(Double val_cubierto) {
		this.val_cubierto = val_cubierto;
	}
	public Integer getCd_deducible() {
		return cd_deducible;
	}
	public void setCd_deducible(Integer cd_deducible) {
		this.cd_deducible = cd_deducible;
	}
	public Double getPorcentaje_valor_siniestro() {
		return porcentaje_valor_siniestro;
	}
	public void setPorcentaje_valor_siniestro(Double porcentaje_valor_siniestro) {
		this.porcentaje_valor_siniestro = porcentaje_valor_siniestro;
	}
	public Double getPorcentaje_valor_asegurado() {
		return porcentaje_valor_asegurado;
	}
	public void setPorcentaje_valor_asegurado(Double porcentaje_valor_asegurado) {
		this.porcentaje_valor_asegurado = porcentaje_valor_asegurado;
	}

	
	
}
