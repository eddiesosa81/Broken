package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Plan;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioPlan {
	@PersistenceContext
	private EntityManager em;
	
	public Plan consultaPlan(Integer cdPlan)
	{
		String sql = "select * from plan_tbl where cd_plan = "+cdPlan;
		System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Plan.class);
		 return (Plan) q.getSingleResult();
	}
	
	public Plan consultaPlanRamoCotizacion(Integer codRamCot)
	{
		String sql = "select *  from plan_tbl where cd_plan =  "
				+ "(select distinct cd_plan from ramo_cotizacion_tbl where cd_ramo_cotizacion = " + codRamCot
				+" )";
		System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Plan.class);
		 return (Plan) q.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Plan> listaPlanes(String cdramo,String cdAseguradora)
	{
		String sql = "select * from plan_tbl where estado_plan = 'A' and cd_ramoaseg in "
				+ "( select cd_ramoaseg from ramoaseguradora_tbl where cd_ramo ="+cdramo+" and cd_aseguradora = "+cdAseguradora+")";
		System.out.println("SQL->"+sql);
		Query q = em.createNativeQuery(sql, Plan.class);
		return q.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	public List<Plan> listaPlanesTot()
	{
		List<Plan> resultado;
		String sql = "select * from plan_tbl where estado_plan = 'A' order by 3";
		try
		{
			Query query = em.createNativeQuery(sql,Plan.class);
			resultado = query.getResultList();
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Integer insertarPlan(Plan obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public int codigoMaxPlan() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_plan),1) as cd_plan from plan_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_plan");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return Integer.parseInt(resultado);
	}

	public void actualizaPlan(Plan obj) {
		em.merge(obj);
	}

	public void eliminaPlan(Plan obj) {
		em.remove(em.merge(obj));
	}
	
	
}
