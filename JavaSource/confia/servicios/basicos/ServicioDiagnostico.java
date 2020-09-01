package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Diagnostico;

@Stateless
public class ServicioDiagnostico {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Diagnostico> consultaDiagnostico() {
		String sql = "select * from diagnostico_tbl order by diagnistico ";
		try {
			Query query = em.createNativeQuery(sql, Diagnostico.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	
		
	public Integer insertarDiagnostico(Diagnostico obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaDiagnostico(Diagnostico obj) {
		em.merge(obj);
	}

	public void eliminaDiagnostico(Diagnostico obj) {
		em.remove(em.merge(obj));
	}


}
