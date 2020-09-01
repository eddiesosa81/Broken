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
@Table(name = "cotizacion_tbl")
public class Cotizacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_cOTIZACION")
	@SequenceGenerator(sequenceName = "secuencia_cd_cOTIZACION", name = "secuencia_cd_cOTIZACION", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_cotizacion")
	private Integer cd_cotizacion;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_rubro")
	private Integer cd_rubro;
	@Column(name="fc_ini_cotizacion")
	private Integer fc_ini_cotizacion;
	@Column(name="fc_fin_cotizacion")
	private Integer fc_fin_cotizacion;
	@Column(name="fc_ini_cot_date")
	private Date fc_ini_cot_date;
	@Column(name="fc_fin_cot_date")
	private Date fc_fin_cot_date;
	@Column(name="fact_periodica_cot")
	private Integer fact_periodica_cot;
	@Column(name="num_renovaciones_cot")
	private Integer num_renovaciones_cot;
	@Column(name="fc_creacion")
	private Date fc_creacion;
	@Column(name="num_cotizacion")
	private String num_cotizacion;
	@Column(name="cd_cliente_asegurado")
	private Integer cd_cliente_asegurado;
	@Column(name="usrid")
	private Integer usrid;
	@Column(name="afecta_anexo")
	private String afecta_anexo;
	@Column(name="observaciones")
	private String observaciones;
	@Column(name="tipo_cliente")
	private String tipo_cliente;
	
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getCd_cotizacion() {
		return cd_cotizacion;
	}
	public void setCd_cotizacion(Integer cd_cotizacion) {
		this.cd_cotizacion = cd_cotizacion;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public Integer getCd_aseguradora() {
		return cd_aseguradora;
	}
	public void setCd_aseguradora(Integer cd_aseguradora) {
		this.cd_aseguradora = cd_aseguradora;
	}
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public Integer getFc_ini_cotizacion() {
		return fc_ini_cotizacion;
	}
	public void setFc_ini_cotizacion(Integer fc_ini_cotizacion) {
		this.fc_ini_cotizacion = fc_ini_cotizacion;
	}
	public Integer getFc_fin_cotizacion() {
		return fc_fin_cotizacion;
	}
	public void setFc_fin_cotizacion(Integer fc_fin_cotizacion) {
		this.fc_fin_cotizacion = fc_fin_cotizacion;
	}
	public Date getFc_ini_cot_date() {
		return fc_ini_cot_date;
	}
	public void setFc_ini_cot_date(Date fc_ini_cot_date) {
		this.fc_ini_cot_date = fc_ini_cot_date;
	}
	public Date getFc_fin_cot_date() {
		return fc_fin_cot_date;
	}
	public void setFc_fin_cot_date(Date fc_fin_cot_date) {
		this.fc_fin_cot_date = fc_fin_cot_date;
	}
	public Integer getFact_periodica_cot() {
		return fact_periodica_cot;
	}
	public void setFact_periodica_cot(Integer fact_periodica_cot) {
		this.fact_periodica_cot = fact_periodica_cot;
	}
	public Integer getNum_renovaciones_cot() {
		return num_renovaciones_cot;
	}
	public void setNum_renovaciones_cot(Integer num_renovaciones_cot) {
		this.num_renovaciones_cot = num_renovaciones_cot;
	}
	public Date getFc_creacion() {
		return fc_creacion;
	}
	public void setFc_creacion(Date fc_creacion) {
		this.fc_creacion = fc_creacion;
	}
	public String getNum_cotizacion() {
		return num_cotizacion;
	}
	public void setNum_cotizacion(String num_cotizacion) {
		this.num_cotizacion = num_cotizacion;
	}
	public Integer getCd_cliente_asegurado() {
		return cd_cliente_asegurado;
	}
	public void setCd_cliente_asegurado(Integer cd_cliente_asegurado) {
		this.cd_cliente_asegurado = cd_cliente_asegurado;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public String getAfecta_anexo() {
		return afecta_anexo;
	}
	public void setAfecta_anexo(String afecta_anexo) {
		this.afecta_anexo = afecta_anexo;
	}
	public String getTipo_cliente() {
		return tipo_cliente;
	}
	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}
	
	
}
	
