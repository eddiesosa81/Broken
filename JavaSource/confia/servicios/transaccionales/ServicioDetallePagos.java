/**
 * 
 */
package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import confia.entidades.transaccionales.DetallePagos;

/**
 * @author Guido Guerrero
 *
 */
@Stateless
public class ServicioDetallePagos {
	@PersistenceContext
	private EntityManager em;
	
	public boolean guardarDetallePago(DetallePagos detallePago) {
		try {
			em.persist(detallePago);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public boolean guardarListaDetallePago(List<DetallePagos> listaDetallePago) {
		try {
			for(int x=0;x<listaDetallePago.size();x++) {
				em.persist(listaDetallePago.get(x));
			}
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
}
