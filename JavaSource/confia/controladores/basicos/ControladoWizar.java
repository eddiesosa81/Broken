package confia.controladores.basicos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

@ManagedBean(name = "ControladoWizar")
@ViewScoped
public class ControladoWizar {
	
	private boolean skip;
	
	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	
	public ControladoWizar() {
		// TODO Auto-generated constructor stub
	}

}
