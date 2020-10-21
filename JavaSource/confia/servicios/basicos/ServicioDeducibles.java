package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Deducibles;

@Stateless
public class ServicioDeducibles {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Deducibles> consultaDeducibles() {
		String sql = "select * from deducible_tbl where estado_deducible = 'A' order by desc_deducible";
		try {
			Query query = em.createNativeQuery(sql, Deducibles.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	
		
	public Integer insertarDeducibles(Deducibles obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaDeducibles(Deducibles obj) {
		em.merge(obj);
	}

	public void eliminaDeducibles(Deducibles obj) {
		em.remove(em.merge(obj));
	}


}
