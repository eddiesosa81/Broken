package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.PlanDeducible;
import confia.entidades.vistas.PlanClausulasView;

@Stateless
public class ServicioPlanClausulasView {
	@PersistenceContext
	private EntityManager em;

	
	
	@SuppressWarnings("unchecked")
	public List<PlanClausulasView> consultaPlanClausulasView(String cdPlan,String codAseg) {
		String sql = " select * from plan_clausulas_view where cd_plan = " + cdPlan+" and cd_aseguradora = "+codAseg;
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanClausulasView.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
}
