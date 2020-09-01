package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.PlanDepreciacion;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioPlanDepreciacion {
	@PersistenceContext
	private EntityManager em;
	
	public Double consultaTasaObjPlanPrimerAno(Integer codPlan) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select distinct nvl(tasa_obj,0) as tasaObj from plan_depreciacion_tbl where anio = 1 and cd_plan = "+codPlan;
			System.out.println("SQL -> "+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("tasaObj");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return Double.valueOf(resultado);
	}
	public PlanDepreciacion consultaPlanDepreciacion(Integer codPlanDep) {
		String sql = "select * from plan_depreciacion_tbl where cd_plandepecia = "+codPlanDep;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanDepreciacion.class);
			return (PlanDepreciacion) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	public PlanDepreciacion consultaPlanDepreciacionAnio1(Integer codPlan) {
		String sql = "select * from plan_depreciacion_tbl where cd_plan = "+codPlan+" and anio = 1";
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanDepreciacion.class);
			return (PlanDepreciacion) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PlanDepreciacion> consultaListaPlanDepreciacion(String codPlan) {
		String sql = "select * from plan_depreciacion_tbl where cd_plan = "+codPlan;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PlanDepreciacion.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public Integer insertarPlanDepreciacion(PlanDepreciacion obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaPlanDepreciacion(PlanDepreciacion obj) {
		em.merge(obj);
	}

	public void eliminaPlanDepreciacion(PlanDepreciacion obj) {
		em.remove(em.merge(obj));
	}

}
