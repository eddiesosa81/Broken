package confia.servicios.basicos;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.PlanTasa;

@Stateless
public class ServicioPlanTasa {
	@PersistenceContext
	private EntityManager em;
	
	public PlanTasa consultaPlanTasa(String cdPlan) {
		PlanTasa resultado;
		String sql = "select * from plantasa_tbl where cd_plan = "+cdPlan;
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanTasa.class);
			resultado = (PlanTasa) query.getSingleResult();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}	
	public Integer insertarPlanTasa(PlanTasa obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaPlanTasa(PlanTasa obj) {
		em.merge(obj);
	}

	public void eliminaPlanTasa(PlanTasa obj) {
		em.remove(em.merge(obj));
	}



}
