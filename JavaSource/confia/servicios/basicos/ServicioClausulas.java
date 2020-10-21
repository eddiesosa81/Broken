package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Clausulas;

@Stateless
public class ServicioClausulas {
	@PersistenceContext
	private EntityManager em;
	
	public List<Clausulas> consultaClausulas() {
		String sql = "select * from clausula_tbl where estado_clausula = 'A' ";
		System.out.println("CLAUSULAS;"+sql);
		try {
			Query query = em.createNativeQuery(sql, Clausulas.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	
	
	
	public Integer insertarClausulas(Clausulas obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaClausulas(Clausulas obj) {
		em.merge(obj);
	}

	public void eliminaClausulas(Clausulas obj) {
		em.remove(em.merge(obj));
	}

}
