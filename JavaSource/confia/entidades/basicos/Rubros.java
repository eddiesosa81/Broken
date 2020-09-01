package confia.entidades.basicos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rubro_tbl")
public class Rubros {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_rubro")
	@SequenceGenerator(sequenceName = "secuencia_cd_rubro", name = "secuencia_cd_rubro", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_rubro")
	private Integer cd_rubro;
	@Column(name="codigo")
	private Integer codigo;
	@Column(name="desc_general")
	private String desc_general;
	@Column(name="desc_rubro")
	private String desc_rubro;
	@Column(name="orden_rubro")
	private Integer orden_rubro;
	@Column(name="porcentaje_rubro")
	private Double porcentaje_rubro;
	@Column(name="valor_rubro")
	private Double valor_rubro;
	@Column(name="genera_iva_rubro")
	private Double genera_iva_rubro;
	@Column(name="tipo_rubro")
	private String tipo_rubro;
	@Column(name="fc_ini_rubro")
	private Date fc_ini_rubro;
	@Column(name="fc_fin_rubro")
	private Date fc_fin_rubro;
	@Column(name="estado_rubro")
	private String estado_rubro;
	@Column(name="objeto_rubro")
	private String objeto_rubro;
	@Column(name="cd_ramo")
	private Integer cdRamo;
	@Column(name="usrid")
	private Integer usrid;
	@Column(name="passwd")
	private Integer passwd;
	@Column(name="nm_reporte")
	private String nm_reporte;
	@Column(name="referenciacarta")
	private String referenciaCarta;
	
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDesc_general() {
		return desc_general;
	}
	public void setDesc_general(String desc_general) {
		this.desc_general = desc_general;
	}
	public String getDesc_rubro() {
		return desc_rubro;
	}
	public void setDesc_rubro(String desc_rubro) {
		this.desc_rubro = desc_rubro;
	}
	public Integer getOrden_rubro() {
		return orden_rubro;
	}
	public void setOrden_rubro(Integer orden_rubro) {
		this.orden_rubro = orden_rubro;
	}
	public Double getPorcentaje_rubro() {
		return porcentaje_rubro;
	}
	public void setPorcentaje_rubro(Double porcentaje_rubro) {
		this.porcentaje_rubro = porcentaje_rubro;
	}
	public Double getValor_rubro() {
		return valor_rubro;
	}
	public void setValor_rubro(Double valor_rubro) {
		this.valor_rubro = valor_rubro;
	}
	public Double getGenera_iva_rubro() {
		return genera_iva_rubro;
	}
	public void setGenera_iva_rubro(Double genera_iva_rubro) {
		this.genera_iva_rubro = genera_iva_rubro;
	}
	public String getTipo_rubro() {
		return tipo_rubro;
	}
	public void setTipo_rubro(String tipo_rubro) {
		this.tipo_rubro = tipo_rubro;
	}
	public Date getFc_ini_rubro() {
		return fc_ini_rubro;
	}
	public void setFc_ini_rubro(Date fc_ini_rubro) {
		this.fc_ini_rubro = fc_ini_rubro;
	}
	public Date getFc_fin_rubro() {
		return fc_fin_rubro;
	}
	public void setFc_fin_rubro(Date fc_fin_rubro) {
		this.fc_fin_rubro = fc_fin_rubro;
	}
	public String getEstado_rubro() {
		return estado_rubro;
	}
	public void setEstado_rubro(String estado_rubro) {
		this.estado_rubro = estado_rubro;
	}
	public String getObjeto_rubro() {
		return objeto_rubro;
	}
	public void setObjeto_rubro(String objeto_rubro) {
		this.objeto_rubro = objeto_rubro;
	}
	public Integer getCdRamo() {
		return cdRamo;
	}
	public void setCdRamo(Integer cdRamo) {
		this.cdRamo = cdRamo;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public Integer getPasswd() {
		return passwd;
	}
	public void setPasswd(Integer passwd) {
		this.passwd = passwd;
	}
	public String getNm_reporte() {
		return nm_reporte;
	}
	public void setNm_reporte(String nm_reporte) {
		this.nm_reporte = nm_reporte;
	}
	public String getReferenciaCarta() {
		return referenciaCarta;
	}
	public void setReferenciaCarta(String referenciaCarta) {
		this.referenciaCarta = referenciaCarta;
	}
	

}
