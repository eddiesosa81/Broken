package confia.entidades.basicos;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO_TBL")
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "USUARIOS_SEQ")
	@SequenceGenerator(sequenceName = "USUARIOS_SEQ", name = "USUARIOS_SEQ", initialValue= 1, allocationSize = 1000)
	@Column(name="usrid")
	private int usrid;
	@Column(name="usrnombres")
	private String usrnombres;
	@Column(name="usrapellidos")
	private String usrapellidos;
	@Column(name="usrlogin")
	private String usrlogin;
	@Column(name="usrclave")
	private String usrclave;
	@Column(name="usrultimaconexion")
	private String usrultimaconexion;
	@Column(name="usrfechacreacion")
	private String usrfechacreacion;
	@Column(name="estid")
	private String estid;
	@Column(name="cargo")
	private String cargo;
	@Column(name="empresa")
	private String empresa;
	@Column(name="correo")
	private String correo;
	
	
	public int getUsrid() {
		return usrid;
	}
	public void setUsrid(int usrid) {
		this.usrid = usrid;
	}
	public String getUsrnombres() {
		return usrnombres;
	}
	public void setUsrnombres(String usrnombres) {
		this.usrnombres = usrnombres;
	}
	public String getUsrapellidos() {
		return usrapellidos;
	}
	public void setUsrapellidos(String usrapellidos) {
		this.usrapellidos = usrapellidos;
	}
	public String getUsrlogin() {
		return usrlogin;
	}
	public void setUsrlogin(String usrlogin) {
		this.usrlogin = usrlogin;
	}
	public String getUsrclave() {
		return usrclave;
	}
	public void setUsrclave(String usrclave) {
		this.usrclave = usrclave;
	}
	public String getUsrultimaconexion() {
		return usrultimaconexion;
	}
	public void setUsrultimaconexion(String usrultimaconexion) {
		this.usrultimaconexion = usrultimaconexion;
	}
	public String getUsrfechacreacion() {
		return usrfechacreacion;
	}
	public void setUsrfechacreacion(String usrfechacreacion) {
		this.usrfechacreacion = usrfechacreacion;
	}
	public String getEstid() {
		return estid;
	}
	public void setEstid(String estid) {
		this.estid = estid;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
