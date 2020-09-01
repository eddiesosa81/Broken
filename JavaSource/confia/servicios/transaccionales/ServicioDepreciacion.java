package confia.servicios.transaccionales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import confia.entidades.transaccionales.Depreciacion;

@Stateless
public class ServicioDepreciacion {
	@PersistenceContext
	private EntityManager em;
	
	public void insertarDepreciacion(Depreciacion obj) {
		em.persist(obj);
	}

	public void actualizaDepreciacion(Depreciacion obj) {
		em.merge(obj);
	}

	public void eliminaDepreciacion(Depreciacion obj) {
		em.remove(em.merge(obj));
	}

}
