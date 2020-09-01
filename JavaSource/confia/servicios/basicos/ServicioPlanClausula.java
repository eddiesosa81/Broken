package confia.servicios.basicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.PlanClausula;
import confia.entidades.basicos.PlanCobertura;

@Stateless
public class ServicioPlanClausula {
	@PersistenceContext
	private EntityManager em;
	
	public PlanClausula consultaPlanClausula(String codaseclau, String cdPlan) {
		String sql = "select * from planclausula_tbl where cd_asegclau = "+codaseclau+" and cd_plan = "+cdPlan;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanClausula.class);
			return (PlanClausula) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	public PlanClausula consultaPlanClausulaCodPlanCla(String codPlanClau) {
		String sql = "select * from planclausula_tbl where cd_planclausula = "+codPlanClau;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanClausula.class);
			return (PlanClausula) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
		
	public Integer insertarPlanClausula(PlanClausula obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaPlanClausula(PlanClausula obj) {
		em.merge(obj);
	}

	public void eliminaPlanClausula(PlanClausula obj) {
		em.remove(em.merge(obj));
	}

}
