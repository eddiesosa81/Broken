package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.InclusionObjMasivaTmp;

@Stateless
public class ServicioIncObjMasivaTmp {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<InclusionObjMasivaTmp> recuperaInclusionObjMasivaTmp(String cdCreacion) {
		String sql = "select * from inclusion_obj_masiva_tmp where fechajuliana_func(to_char(fecha_creacion,'dd/mm/yyyy')) = fechajuliana_func('"
				+ cdCreacion + "')";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, InclusionObjMasivaTmp.class);
		return q.getResultList();
	}

	public Integer insertarInclusionObjMasivaTmp(InclusionObjMasivaTmp obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

	public void actualizaInclusionObjMasivaTmp(InclusionObjMasivaTmp obj) {
		em.merge(obj);
	}

	public void eliminaInclusionObjMasivaTmp(InclusionObjMasivaTmp obj) {
		em.remove(em.merge(obj));
	}


}
