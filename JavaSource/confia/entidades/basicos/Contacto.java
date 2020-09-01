package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contacto_tbl")
public class Contacto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_contacto")
	@SequenceGenerator(sequenceName = "secuencia_cd_contacto", name = "secuencia_cd_contacto", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_contacto")
	private Integer cd_contacto;
	@Column(name="cd_cliente")
	private Integer cd_cliente;
	@Column(name="cd_aseguradora")
	private Integer cd_aseguradora;
	@Column(name="nombre_contacto")
	private String nombre_contacto;
	@Column(name="telefono_contacto")
	private String telefono_contacto;
	@Column(name="celular_contacto")
	private String celular_contacto;
	@Column(name="mail_contacto")
	private String mail_contacto;
	@Column(name="cargo_contacto")
	private String cargo_contacto;
	@Column(name="departamento_contacto")
	private String departamento_contacto;
	@Column(name="direccion_contacto")
	private String direccion_contacto;
	@Column(name="actestado")
	private String actestado;

	@Column(name="ciudad")
	private String ciudad;
	


	public Integer getCd_contacto() {
		return cd_contacto;
	}
	public void setCd_contacto(Integer cd_contacto) {
		this.cd_contacto = cd_contacto;
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
	public String getNombre_contacto() {
		return nombre_contacto;
	}
	public void setNombre_contacto(String nombre_contacto) {
		this.nombre_contacto = nombre_contacto;
	}
	public String getTelefono_contacto() {
		return telefono_contacto;
	}
	public void setTelefono_contacto(String telefono_contacto) {
		this.telefono_contacto = telefono_contacto;
	}
	public String getCelular_contacto() {
		return celular_contacto;
	}
	public void setCelular_contacto(String celular_contacto) {
		this.celular_contacto = celular_contacto;
	}
	public String getMail_contacto() {
		return mail_contacto;
	}
	public void setMail_contacto(String mail_contacto) {
		this.mail_contacto = mail_contacto;
	}
	public String getCargo_contacto() {
		return cargo_contacto;
	}
	public void setCargo_contacto(String cargo_contacto) {
		this.cargo_contacto = cargo_contacto;
	}
	public String getDepartamento_contacto() {
		return departamento_contacto;
	}
	public void setDepartamento_contacto(String departamento_contacto) {
		this.departamento_contacto = departamento_contacto;
	}
	public String getDireccion_contacto() {
		return direccion_contacto;
	}
	public void setDireccion_contacto(String direccion_contacto) {
		this.direccion_contacto = direccion_contacto;
	}
	public String getActestado() {
		return actestado;
	}
	public void setActestado(String actestado) {
		this.actestado = actestado;
	}

	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}
