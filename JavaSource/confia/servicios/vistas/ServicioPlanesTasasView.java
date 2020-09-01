package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.PlanTasaView;

@Stateless
public class ServicioPlanesTasasView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PlanTasaView> consultaPlanTasaView(String cdPlan) {
		List<PlanTasaView> resultado;
		String sql = "select * from planes_tasa_view where cd_plan = "+cdPlan+" order by descripcion_plan";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanTasaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
}
