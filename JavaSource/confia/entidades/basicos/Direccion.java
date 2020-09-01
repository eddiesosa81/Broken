package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "direccion_tbl")
public class Direccion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_direccion")
	@SequenceGenerator(sequenceName = "secuencia_cd_direccion", name = "secuencia_cd_direccion", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_direccion")
	private Integer cd_direccion;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_ciudad")
	private Integer cd_ciudad;
	@Column(name="direccion")
	private String direccion;
	@Column(name="cd_rubro")
	private Integer cd_rubro;
	@Column(name="referencia")
	private String referencia;
	@Column(name="actestado")
	private String actestado;
	@Column(name="cd_provincia")
	private String cd_provincia;
	
	
	public String getCd_provincia() {
		return cd_provincia;
	}
	public void setCd_provincia(String cd_provincia) {
		this.cd_provincia = cd_provincia;
	}
	public Integer getCd_direccion() {
		return cd_direccion;
	}
	public void setCd_direccion(Integer cd_direccion) {
		this.cd_direccion = cd_direccion;
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
	public Integer getCd_ciudad() {
		return cd_ciudad;
	}
	public void setCd_ciudad(Integer cd_ciudad) {
		this.cd_ciudad = cd_ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getActestado() {
		return actestado;
	}
	public void setActestado(String actestado) {
		this.actestado = actestado;
	}
	
}
