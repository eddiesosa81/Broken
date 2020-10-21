package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.CoberturasPlanView;

@Stateless
public class ServicioCoberturasPlanView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CoberturasPlanView> consultaCoberturasPlan(String cdPlan, String codAseg) {
		List<CoberturasPlanView> resultado;
		String sql = "select * from plan_coberturas_view where cd_plan = " + cdPlan+ " and cd_aseguradora = "+codAseg;
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, CoberturasPlanView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CoberturasPlanView> consultaCoberturasPlanAsisMed(String codSiniestro, String codAseg) {
		List<CoberturasPlanView> resultado;
		String sql = "select * from plan_coberturas_view where cd_aseguradora = "+codAseg
				+" and cd_plan = (select nvl(cd_plan,0) from ubicacion_tbl "
				+ "where cd_ubicacion = (select distinct cd_ubicacion from objeto_cotizacion_tbl "
				+ "WHERE cd_obj_cotizacion in (select distinct cd_obj_cotizacion from detalle_siniestros_tbl where cd_siniestro ="+codSiniestro+")))";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, CoberturasPlanView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CoberturasPlanView> consultaCoberturasPlanCrc(String cdRamoCot) {
		List<CoberturasPlanView> resultado;
		String sql = "select * from plan_coberturas_view where cd_plan in (select distinct cd_plan "
				+ "from ramo_cotizacion_pol_tbl where cd_ramo_cotizacion = "+ cdRamoCot + ")";
		try {
			Query query = em.createNativeQuery(sql, CoberturasPlanView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
}
