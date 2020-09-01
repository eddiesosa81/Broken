package confia.servicios.basicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import confia.entidades.basicos.Rubros;

public class ServicioGeneraCartas {
	@EJB
	private ServicioRubros srvRubros;
	
	public List<Rubros> listadoCartas(String nmModulo ) {
		List<Rubros> lst = new ArrayList<Rubros>();
		lst = srvRubros.listadoCartasPorModulo(nmModulo);
		return lst;
	}

}
