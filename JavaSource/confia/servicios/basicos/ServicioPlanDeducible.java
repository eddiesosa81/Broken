package confia.servicios.basicos;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.PlanDeducible;

@Stateless
public class ServicioPlanDeducible {
	@PersistenceContext
	private EntityManager em;
	
	
	public PlanDeducible consultaPlanDeducible(String codAsegded, String codPlan) {
		String sql = "select * from plandeducible_tbl where cd_asegded = "+codAsegded+" and cd_plan = "+codPlan;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanDeducible.class);
			return (PlanDeducible) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	public PlanDeducible consultaPlanDeducibleCodPlanDe(String codPlanDed) {
		String sql = "select * from plandeducible_tbl where cd_plandeducible = "+codPlanDed;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanDeducible.class);
			return (PlanDeducible) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public Integer insertarPlanDeducible(PlanDeducible obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaPlanDeducible(PlanDeducible obj) {
		em.merge(obj);
	}

	public void eliminaPlanDeducible(PlanDeducible obj) {
		em.remove(em.merge(obj));
	}


}
