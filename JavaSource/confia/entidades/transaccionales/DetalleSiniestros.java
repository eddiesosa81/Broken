package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_siniestros_tbl")
public class DetalleSiniestros {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_cd_det_siniestro")
	@SequenceGenerator(sequenceName = "secuencia_cd_det_siniestro", name = "secuencia_cd_det_siniestro", initialValue = 1, allocationSize = 1000)
	@Column(name = "cd_det_siniestro")
	private Integer cd_det_siniestro;

	@Column(name = "cd_siniestro")
	private Integer cd_siniestro;
	@Column(name = "cd_compania")
	private Integer cd_compania;
	@Column(name = "cd_obj_cotizacion")
	private Integer cd_obj_cotizacion;
	@Column(name = "dsc_objeto")
	private String dsc_objeto;
	@Column(name = "val_asegurado")
	private Double val_asegurado;
	@Column(name = "deducible_Minimo")
	private Double deducibleMinimo;
	@Column(name = "cd_subobj_cotizacion")
	private Integer cd_subobj_cotizacion;
	@Column(name = "descripcion_subobjeto")
	private String descripcion_subobjeto;
	@Column(name = "valor_asegurador_subobjeto")
	private Double valor_asegurador_subobjeto;
	@Column(name = "prima_neta_obj")
	private Double prima_neta_obj;
	@Column(name = "prima_neta_sub_obj")
	private Double prima_neta_sub_obj;

	public Integer getCd_det_siniestro() {
		return cd_det_siniestro;
	}

	public void setCd_det_siniestro(Integer cd_det_siniestro) {
		this.cd_det_siniestro = cd_det_siniestro;
	}

	public Integer getCd_siniestro() {
		return cd_siniestro;
	}

	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
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

	public String getDsc_objeto() {
		return dsc_objeto;
	}

	public void setDsc_objeto(String dsc_objeto) {
		this.dsc_objeto = dsc_objeto;
	}

	public Double getVal_asegurado() {
		return val_asegurado;
	}

	public void setVal_asegurado(Double val_asegurado) {
		this.val_asegurado = val_asegurado;
	}

	public Double getDeducibleMinimo() {
		return deducibleMinimo;
	}

	public void setDeducibleMinimo(Double deducibleMinimo) {
		this.deducibleMinimo = deducibleMinimo;
	}

	public Integer getCd_subobj_cotizacion() {
		return cd_subobj_cotizacion;
	}

	public void setCd_subobj_cotizacion(Integer cd_subobj_cotizacion) {
		this.cd_subobj_cotizacion = cd_subobj_cotizacion;
	}

	public String getDescripcion_subobjeto() {
		return descripcion_subobjeto;
	}

	public void setDescripcion_subobjeto(String descripcion_subobjeto) {
		this.descripcion_subobjeto = descripcion_subobjeto;
	}

	public Double getValor_asegurador_subobjeto() {
		return valor_asegurador_subobjeto;
	}

	public void setValor_asegurador_subobjeto(Double valor_asegurador_subobjeto) {
		this.valor_asegurador_subobjeto = valor_asegurador_subobjeto;
	}

	public Double getPrima_neta_obj() {
		return prima_neta_obj;
	}

	public void setPrima_neta_obj(Double prima_neta_obj) {
		this.prima_neta_obj = prima_neta_obj;
	}

	public Double getPrima_neta_sub_obj() {
		return prima_neta_sub_obj;
	}

	public void setPrima_neta_sub_obj(Double prima_neta_sub_obj) {
		this.prima_neta_sub_obj = prima_neta_sub_obj;
	}

}
