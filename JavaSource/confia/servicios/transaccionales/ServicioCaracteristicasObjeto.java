package confia.servicios.transaccionales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import confia.entidades.transaccionales.CaracteristicasObj;

@Stateless
public class ServicioCaracteristicasObjeto {

	@PersistenceContext
	private EntityManager em;
	
	
	
	public void insertaCaracteristicas(CaracteristicasObj obj)
	{
		em.persist(obj);
	}
	
	public void actualizaCaracteristicas(CaracteristicasObj obj)
	{
		em.merge(obj);
	}
	
	public void eliminaCaracteristicas(CaracteristicasObj obj)
	{
		em.remove(em.merge(obj));
	}
}
