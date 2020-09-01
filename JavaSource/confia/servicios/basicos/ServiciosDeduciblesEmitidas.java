package confia.servicios.basicos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.CoberturasEmitidas;
import confia.entidades.basicos.DeduciblesEmitidas;
import confia.procedures.ConectarBase;

@Stateless
public class ServiciosDeduciblesEmitidas {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DeduciblesEmitidas> recuperaDeduciblesEmitidas(Integer cdCompania, Integer cdRamoCot) {
		String sql="SELECT * FROM deducibles_emitidos_tbl where cd_compania = "+cdCompania+" and cd_ramo_cotizacion = "+cdRamoCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, DeduciblesEmitidas.class);
		return q.getResultList();
	
	}
	
	public DeduciblesEmitidas deducibleEmitidas(Integer cdCompania, Integer cdRamoCot, Integer cdded) {
		String sql = "SELECT * FROM deducibles_emitidos_tbl where cd_compania = " + cdCompania
				+ " and cd_ramo_cotizacion = " + cdRamoCot + " and cd_deducible = " + cdded;
		System.out.println("********************-----QUERY: " + sql);
		try {
			Query q = em.createNativeQuery(sql, DeduciblesEmitidas.class);
			 return (DeduciblesEmitidas) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return null;
		}

	}
	
	public Integer insertaDeduciblesEmitidas(DeduciblesEmitidas obj)
	{
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public Integer actualizaDeduciblesEmitidas(DeduciblesEmitidas obj)
	{
		Connection conn;
		String sql;
		try {
			System.out
					.println("Ingreso datos ORACLE -> update deducibles_emitidos_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update deducibles_emitidos_tbl  "
					+"set porcentaje_valor_siniestro = "+obj.getPorcentaje_valor_siniestro()
					+" ,porcentaje_valor_asegurado = "+obj.getPorcentaje_valor_asegurado()
					+" ,valor_minimo = "+obj.getValor_minimo()
					+" ,valor_fijo = "+obj.getValor_fijo()
					+" where cd_compania = "+obj.getCd_compania()
					+" and cd_deducible = "+obj.getCd_deducible()
					+" and cd_ramo_cotizacion = "+obj.getCd_ramo_cotizacion();
			System.out.println("sql->:" + sql);
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return 1;
		}
	}
	
	public Integer eliminaDeduciblesEmitidas(DeduciblesEmitidas obj)
	{
		Connection conn;
		String sql;
		try {
			System.out
					.println("Ingreso datos ORACLE -> delete deducibles_emitidos_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "delete from deducibles_emitidos_tbl "
					+" where cd_compania = "+obj.getCd_compania()
					+" and cd_deducible = "+obj.getCd_deducible()
					+" and cd_ramo_cotizacion = "+obj.getCd_ramo_cotizacion();
			System.out.println("sql->:" + sql);
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return 1;
		}
	}

}
