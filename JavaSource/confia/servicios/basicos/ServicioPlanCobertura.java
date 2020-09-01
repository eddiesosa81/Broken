package confia.servicios.basicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.PlanCobertura;

@Stateless
public class ServicioPlanCobertura {
	@PersistenceContext
	private EntityManager em;
	
	public PlanCobertura consultaPlanCobertura(String codAsegCob, String cdPlan) {
		String sql = "select * from plancobertura_tbl where cd_asegcob = "+codAsegCob+" and cd_plan = "+cdPlan;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanCobertura.class);
			return (PlanCobertura) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public PlanCobertura consultaPlanCoberturaCodPlanCob(String codPlanCOb) {
		String sql = "select * from plancobertura_tbl where cd_plancobertura = "+codPlanCOb;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanCobertura.class);
			return (PlanCobertura) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
		
	public Integer insertarPlanCobertura(PlanCobertura obj) {
		System.out.println("PLAN");
		System.out.println("cd_ramoaseg");
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaPlanCobertura(PlanCobertura obj) {
		em.merge(obj);
	}

	public void eliminaPlanCobertura(PlanCobertura obj) {
		em.remove(em.merge(obj));
	}


}
