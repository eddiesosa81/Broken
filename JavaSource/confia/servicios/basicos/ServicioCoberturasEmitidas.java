package confia.servicios.basicos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.CoberturasEmitidas;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioCoberturasEmitidas {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CoberturasEmitidas> recuperaCoberturasEmitidas(Integer cdCompania, Integer cdRamoCot) {
		String sql = "SELECT * FROM coberturas_emitidas_tbl where cd_compania = " + cdCompania
				+ " and cd_ramo_cotizacion = " + cdRamoCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasEmitidas.class);
		return q.getResultList();

	}

	public CoberturasEmitidas coberturasEmitidas(Integer cdCompania, Integer cdRamoCot, Integer cdcob) {
		String sql = "SELECT * FROM coberturas_emitidas_tbl where cd_compania = " + cdCompania
				+ " and cd_ramo_cotizacion = " + cdRamoCot + " and cd_cobertura = " + cdcob;
		System.out.println("********************-----QUERY: " + sql);
		try {
			Query q = em.createNativeQuery(sql, CoberturasEmitidas.class);
			 return (CoberturasEmitidas) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return null;
		}

	}

	public Integer insertaCoberturasEmitidas(CoberturasEmitidas obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

	public Integer actualizaCoberturasEmitidas(CoberturasEmitidas obj) {
		Connection conn;
		String sql;
		try {
			System.out.println("Ingreso datos ORACLE -> update coberturas_emitidas_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update coberturas_emitidas_tbl  " + "set porcentajeplancobertura = "
					+ obj.getPorcentajeplancobertura() + " ,valor_plancobertura = " + obj.getValor_plancobertura()
					+ " where cd_compania = " + obj.getCd_compania() + " and cd_cobertura = " + obj.getCd_cobertura()
					+ " and cd_ramo_cotizacion = " + obj.getCd_ramo_cotizacion();
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

	public Integer eliminaCoberturasEmitidas(CoberturasEmitidas obj) {
		Connection conn;
		String sql;
		try {
			System.out.println("Ingreso datos ORACLE -> delete coberturas_emitidas_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "delete from coberturas_emitidas_tbl  " + " where cd_compania = " + obj.getCd_compania()
					+ " and cd_cobertura = " + obj.getCd_cobertura() + " and cd_ramo_cotizacion = "
					+ obj.getCd_ramo_cotizacion();
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
