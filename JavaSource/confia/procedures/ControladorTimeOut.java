package confia.procedures;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ControladorTimeOut {
	public String cerrarSesion() {

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("USUARIO");
		System.out.println("Cerrando sesion");
		return "/login.jsf";
	}

}
