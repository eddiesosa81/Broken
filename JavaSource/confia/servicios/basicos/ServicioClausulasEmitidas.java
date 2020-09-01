package confia.servicios.basicos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.ClausulasEmitidas;
import confia.entidades.basicos.CoberturasAdicionales;
import confia.entidades.basicos.DeduciblesEmitidas;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioClausulasEmitidas {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ClausulasEmitidas> recuperaClausulasEmitidas(Integer cdCompania, Integer cdRamoCot) {
		String sql="SELECT * FROM clausulas_emitidas_tbl where cd_compania = "+cdCompania+" and cd_ramo_cotizacion = "+cdRamoCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ClausulasEmitidas.class);
		return q.getResultList();
	
	}
	
	public ClausulasEmitidas clausulaEmitidas(Integer cdCompania, Integer cdRamoCot, Integer cdclau) {
		String sql = "SELECT * FROM clausulas_emitidas_tbl where cd_compania = " + cdCompania
				+ " and cd_ramo_cotizacion = " + cdRamoCot + " and cd_clausula = " + cdclau;
		System.out.println("********************-----QUERY: " + sql);
		try {
			Query q = em.createNativeQuery(sql, ClausulasEmitidas.class);
			 return (ClausulasEmitidas) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return null;
		}

	}
	
	public Integer insertaClausulasEmitidas(ClausulasEmitidas obj)
	{
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public Integer actualizaClausulasEmitidas(ClausulasEmitidas obj)
	{
		Connection conn;
		String sql;
		try {
			System.out
					.println("Ingreso datos ORACLE -> update clausulas_emitidas_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update clausulas_emitidas_tbl  "
					+"set porcentaje_planclausula = "+obj.getPorcentaje_planclausula()
					+" ,valor_planclausula = "+obj.getValor_planclausula()
					+" where cd_compania = "+obj.getCd_compania()
					+" and cd_clausula = "+obj.getCd_clausula()
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
	
	public Integer eliminaClausulasEmitidas(ClausulasEmitidas obj)
	{
		Connection conn;
		String sql;
		try {
			System.out
					.println("Ingreso datos ORACLE -> Delete clausulas_emitidas_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "delete clausulas_emitidas_tbl  "
					+" where cd_compania = "+obj.getCd_compania()
					+" and cd_clausula = "+obj.getCd_clausula()
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
