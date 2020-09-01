package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "telefono_tbl")
public class Telefono {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_telefono")
	@SequenceGenerator(sequenceName = "secuencia_cd_telefono", name = "secuencia_cd_telefono", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_telefono")
	private Integer cd_telefono;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="cd_ciudad")
	private Integer cd_ciudad;
	@Column(name="tipo")
	private String tipo;
	@Column(name="telefono")
	private String telefono;
	@Column(name="ext")
	private String ext;
	@Column(name="nombrerelacion")
	private String nombrerelacion;
	@Column(name="actestado")
	private String actestado;
	@Column(name="correo")
	private String correo;
	@Column(name="telefono_oficina")
	private String telefono_oficina;
	@Column(name="telefono_celular")
	private String telefono_celular;
	
	
	
	public Integer getCd_telefono() {
		return cd_telefono;
	}
	public void setCd_telefono(Integer cd_telefono) {
		this.cd_telefono = cd_telefono;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getNombrerelacion() {
		return nombrerelacion;
	}
	public void setNombrerelacion(String nombrerelacion) {
		this.nombrerelacion = nombrerelacion;
	}
	public String getActestado() {
		return actestado;
	}
	public void setActestado(String actestado) {
		this.actestado = actestado;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono_oficina() {
		return telefono_oficina;
	}
	public void setTelefono_oficina(String telefono_oficina) {
		this.telefono_oficina = telefono_oficina;
	}
	public String getTelefono_celular() {
		return telefono_celular;
	}
	public void setTelefono_celular(String telefono_celular) {
		this.telefono_celular = telefono_celular;
	}

	
}
