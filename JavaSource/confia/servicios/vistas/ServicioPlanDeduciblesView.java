package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.PlanDeduciblesView;

@Stateless
public class ServicioPlanDeduciblesView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PlanDeduciblesView> consultaDeduciblePlan(String cdPlan,String CodAseg) {
		List<PlanDeduciblesView> resultado;
		String sql = "select * from plan_deducibles_view where cd_plan = " + cdPlan+" and cd_aseguradora = "+CodAseg;
		try {
			Query query = em.createNativeQuery(sql, PlanDeduciblesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<PlanDeduciblesView> consultaDeduciblePlanAsisMed(String codSiniestro,String CodAseg) {
		List<PlanDeduciblesView> resultado;
		String sql = "select * from plan_deducibles_view where cd_aseguradora = "+CodAseg
				+" and cd_plan = (select nvl(cd_plan,0) from ubicacion_tbl "
				+ "where cd_ubicacion = (select distinct cd_ubicacion from objeto_cotizacion_tbl "
				+ "WHERE cd_obj_cotizacion in (select distinct cd_obj_cotizacion from detalle_siniestros_tbl where cd_siniestro ="+codSiniestro+")))";
		try {
			Query query = em.createNativeQuery(sql, PlanDeduciblesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlanDeduciblesView> consultaDeduciblePlanCrc(String cdRamoCotizacion) {
		List<PlanDeduciblesView> resultado;
		String sql = "select * from plan_deducibles_view where cd_plan in ( select distinct cd_plan "
				+ "from ramo_cotizacion_pol_tbl where cd_ramo_cotizacion = "+ cdRamoCotizacion + ")";
		try {
			Query query = em.createNativeQuery(sql, PlanDeduciblesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
}
