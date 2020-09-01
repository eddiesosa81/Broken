package confia.controladores.basicos;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Terminal {
	public String handleCommand(String command, String[] params) {
        if(command.equals("yo")) {
            if(params.length > 0)
                return "Hola " + params[0];
            else
                return "Hola Extraño";
        }
        else if(command.equals("hoy"))
            return new Date().toString();
        else
            return command + " not found";
    }
}
