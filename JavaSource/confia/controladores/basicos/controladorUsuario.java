package confia.controladores.basicos;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import confia.entidades.basicos.Usuarios;
import confia.servicios.basicos.ServicioUsuarios;

@ManagedBean(name = "controladorUsuario")
@ViewScoped
public class controladorUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioUsuarios srvUsr;

	private String usuario;
	private String clave;
	private String lsCompania;

	public controladorUsuario() {
		srvUsr = new ServicioUsuarios();
		lsCompania = "1";
	}

	public String userLogin() {
		FacesMessage mensajebienv;

		Usuarios user = srvUsr.validarUsuario(usuario, clave);

		if (user == null) {
			mensajebienv = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos incorrectos.");
			return "/login.jsf";
		} else {
			mensajebienv = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",
					user.getUsrnombres().toUpperCase());

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("COMPANIA", lsCompania);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", user.getUsrid());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIONM", user.getUsrlogin());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("EMPRESA", user.getEmpresa());
			
			// RequestContext.getCurrentInstance().execute("PF('wLogin').hide()");
			if (user.getEmpresa().equals("CONFIA")) {
				return "/index.jsf";
			}else if(user.getEmpresa().equals("UNINOVA")) {
				return "/emisionMensualizado/comercial.jsf";
			} else {
				return "/emisionMensualizado/indexMensualizado.jsf";
			}
		}

		// FacesContext.getCurrentInstance().addMessage(null, mensajebienv);
	}
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
