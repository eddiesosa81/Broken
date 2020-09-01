package confia.servicios.basicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import confia.entidades.basicos.RegistroCartas;

@Stateless
public class ServicoRegistroCartas {
	@PersistenceContext
	private EntityManager em;
	
	// public Cotizacion recuperaEvaluacionDesempenoCdg(
		// int cdEvaluacion, int anio) {
		// String sql = "select * from cotizacion where anio = "
		// + anio + " and cd_evaluacion_d = " + cdEvaluacion;
		// System.out.println("********************-----QUERY: " + sql);
		// Query q = em.createNativeQuery(sql, Cotizacion.class);
		// return (Cotizacion) q.getSingleResult();
		// }
		// @SuppressWarnings("unchecked")
		// public List<Cotizacion> recuperaEvaluacionDesemDetCdEvaluacion(int
		// cdEvaluacion) {
		// String sql = "select * from ms_evaluacion_desepeno_det where
		// cd_evaluacion_d = "+cdEvaluacion+" order by num";
		// System.out.println("********************-----QUERY: " + sql);
		// Query q = em.createNativeQuery(sql, Cotizacion.class);
		// return q.getResultList();
		//
		// }

		public void insertarRegistroCartas(RegistroCartas obj) {
			em.persist(obj);
		}

		public void actualizaRegistroCartas(RegistroCartas obj) {
			em.merge(obj);
		}

		public void eliminaRegistroCartas(RegistroCartas obj) {
			em.remove(em.merge(obj));
		}

}
