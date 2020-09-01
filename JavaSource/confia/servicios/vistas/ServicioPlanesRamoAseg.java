package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.PlanRamoAseguradoraView;

@Stateless
public class ServicioPlanesRamoAseg {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PlanRamoAseguradoraView> consultaPlanRamoAseguradora() {
		List<PlanRamoAseguradoraView> resultado;
		String sql = "select * from plan_ramo_aseg_view where estado_plan = 'A'  order by nombre_corto_aseguradora,desc_ramo,descripcion_plan";
		try {
			Query query = em.createNativeQuery(sql, PlanRamoAseguradoraView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public PlanRamoAseguradoraView consultaPlanRamoAseguradora(String codPlan) {
		PlanRamoAseguradoraView resultado = new PlanRamoAseguradoraView();
		String sql = "select * from plan_ramo_aseg_view "
				+ "where cd_plan = "+codPlan
				+ " and estado_plan = 'A'  order by nombre_corto_aseguradora,desc_ramo,descripcion_plan";
		try {
			Query query = em.createNativeQuery(sql, PlanRamoAseguradoraView.class);
			resultado = (PlanRamoAseguradoraView) query.getSingleResult();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public PlanRamoAseguradoraView consultaPlanRamAsegCod(String cosAseg,String codRam) {
		String sql = "select * from plan_ramo_aseg_view where cd_ramo = "+codRam
				+ " and cd_aseguradora = "+cosAseg
				+ " order by nombre_corto_aseguradora,desc_ramo,descripcion_plan";
		try {
			Query query = em.createNativeQuery(sql, PlanRamoAseguradoraView.class);
			return (PlanRamoAseguradoraView) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

}
