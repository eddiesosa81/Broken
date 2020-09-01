package confia.servicios.transaccionales;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.EmisionMasiva;

@Stateless
public class ServicioEmisionesMasivas {
	@PersistenceContext
	private EntityManager em;

	public List<EmisionMasiva> recuperaEmiMasivaFcCrea(String cdCreacion) {
		String sql = "select * from emision_masiva_tmp where fechajuliana_func(to_char(fecha_creacion,'dd/mm/yyyy')) = fechajuliana_func('"
				+ cdCreacion + "')";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, EmisionMasiva.class);
		return q.getResultList();
	}

	public Integer insertarEmisionMasiva(EmisionMasiva obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

	public void actualizaEmisionMasiva(EmisionMasiva obj) {
		em.merge(obj);
	}

	public void eliminaEmisionMasiva(EmisionMasiva obj) {
		em.remove(em.merge(obj));
	}

}
