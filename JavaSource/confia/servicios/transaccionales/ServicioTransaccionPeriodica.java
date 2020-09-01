package confia.servicios.transaccionales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import confia.entidades.transaccionales.TransaccionesPeriodicas;

@Stateless
public class ServicioTransaccionPeriodica {
	@PersistenceContext
	private EntityManager em;
	
	
	public Integer insertarTransaccionesPeriodicas(TransaccionesPeriodicas obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaTransaccionesPeriodicas(TransaccionesPeriodicas obj) {
		em.merge(obj);
	}

	public void eliminaTransaccionesPeriodicas(TransaccionesPeriodicas obj) {
		em.remove(em.merge(obj));
	}

}
