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
@Table(name = "proforma_Siniestro_tbl")
public class ProformaSiniestro {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_proforma")
	@SequenceGenerator(sequenceName = "secuencia_cd_proforma", name = "secuencia_cd_proforma", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_proforma")
	private Integer cd_proforma;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	@Column(name="cd_rubro")
	private Integer cd_rubro;
	@Column(name="descripcion_Proveedor")
	private String descripcion_Proveedor;
	@Column(name="numero_factura")
	private String numero_factura;
	@Column(name="fecha_emision")
	private Date fecha_emision;
	@Column(name="valor_presentado")
	private Double valor_presentado;
	@Column(name="valor_no_cubierto")
	private Double valor_no_cubierto;
	@Column(name="valor_cubierto")
	private Double valor_cubierto;
	@Column(name="valor_pagado")
	private Double valor_pagado;
	@Column(name="saldo")
	private Double saldo;
	@Column(name="ciudad_reparacion")
	private String ciudad_reparacion;
	
	public Integer getCd_proforma() {
		return cd_proforma;
	}
	public void setCd_proforma(Integer cd_proforma) {
		this.cd_proforma = cd_proforma;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_siniestro() {
		return cd_siniestro;
	}
	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
	}
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public String getDescripcion_Proveedor() {
		return descripcion_Proveedor;
	}
	public void setDescripcion_Proveedor(String descripcion_Proveedor) {
		this.descripcion_Proveedor = descripcion_Proveedor;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public Date getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public Double getValor_presentado() {
		return valor_presentado;
	}
	public void setValor_presentado(Double valor_presentado) {
		this.valor_presentado = valor_presentado;
	}
	public Double getValor_no_cubierto() {
		return valor_no_cubierto;
	}
	public void setValor_no_cubierto(Double valor_no_cubierto) {
		this.valor_no_cubierto = valor_no_cubierto;
	}
	public Double getValor_cubierto() {
		return valor_cubierto;
	}
	public void setValor_cubierto(Double valor_cubierto) {
		this.valor_cubierto = valor_cubierto;
	}
	public Double getValor_pagado() {
		return valor_pagado;
	}
	public void setValor_pagado(Double valor_pagado) {
		this.valor_pagado = valor_pagado;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getCiudad_reparacion() {
		return ciudad_reparacion;
	}
	public void setCiudad_reparacion(String ciudad_reparacion) {
		this.ciudad_reparacion = ciudad_reparacion;
	}
	
}
