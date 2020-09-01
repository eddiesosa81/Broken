package confia.servicios.transaccionales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import confia.entidades.transaccionales.CaracteristicasVh;

@Stateless
public class ServicioCaracteristicasVh {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarCaracteristicasVh(CaracteristicasVh obj) {
		em.persist(obj);
	}

	public void actualizaCaracteristicasVh(CaracteristicasVh obj) {
		em.merge(obj);
	}

	public void eliminaCaracteristicasVh(CaracteristicasVh obj) {
		em.remove(em.merge(obj));
	}

}
