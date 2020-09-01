package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cotizacion_tbl")
public class CaracteristicasObjeto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_caract_obj")
	@SequenceGenerator(sequenceName = "secuencia_cd_caract_obj", name = "secuencia_cd_caract_obj", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_caract_obj")
	private Integer cd_caract_obj;
	@Column(name="cd_obj_cotizacion")
	private Integer cd_obj_cotizacion;
	@Column(name="cd_rubro")
	private Integer cd_rubro;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="dsc_caract_obj")
	private String dsc_caract_obj;
	@Column(name="val_caract_obj")
	private Double val_caract_obj;
	@Column(name="orden_imp")
	private Integer orden_imp;
	public Integer getCd_caract_obj() {
		return cd_caract_obj;
	}
	public void setCd_caract_obj(Integer cd_caract_obj) {
		this.cd_caract_obj = cd_caract_obj;
	}
	public Integer getCd_obj_cotizacion() {
		return cd_obj_cotizacion;
	}
	public void setCd_obj_cotizacion(Integer cd_obj_cotizacion) {
		this.cd_obj_cotizacion = cd_obj_cotizacion;
	}
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public String getDsc_caract_obj() {
		return dsc_caract_obj;
	}
	public void setDsc_caract_obj(String dsc_caract_obj) {
		this.dsc_caract_obj = dsc_caract_obj;
	}
	public Double getVal_caract_obj() {
		return val_caract_obj;
	}
	public void setVal_caract_obj(Double val_caract_obj) {
		this.val_caract_obj = val_caract_obj;
	}
	public Integer getOrden_imp() {
		return orden_imp;
	}
	public void setOrden_imp(Integer orden_imp) {
		this.orden_imp = orden_imp;
	}
	
}
