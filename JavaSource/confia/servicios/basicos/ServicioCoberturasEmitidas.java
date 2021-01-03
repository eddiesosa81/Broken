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
	public List<CoberturasEmitidas> recuperaCoberturasSiniestro(Integer cdCompania, Integer cdRamoCot) {
		String sql = "SELECT * FROM coberturas_emitidas_tbl where cd_compania = " + cdCompania
				+ " and cd_ramo_cotizacion = " + cdRamoCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasEmitidas.class);
		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<CoberturasEmitidas> recuperaCoberturasSiniestroUbicacion(Integer cdCompania, Integer cdRamoCot, Integer cdObjCotizacion) {
		String sql = "SELECT * FROM coberturas_emitidas_tbl where cd_compania = " + cdCompania
				+ " and cd_ramo_cotizacion = " + cdRamoCot
				+ " and cd_ubicacion in ( select cd_ubicacion from objeto_cotizacion_tbl where cd_obj_cotizacion = "+cdObjCotizacion+")";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasEmitidas.class);
		return q.getResultList();

	}
	
	
	@SuppressWarnings("unchecked")
	public List<CoberturasEmitidas> recuperaCoberturasEmitidas(Integer cdCompania, Integer cdRamoCot) {
		String sql = "SELECT * FROM coberturas_emitidas_tbl where nvl(cd_ubicacion,0) = 0 and cd_compania = " + cdCompania
				+ " and cd_ramo_cotizacion = " + cdRamoCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasEmitidas.class);
		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<CoberturasEmitidas> recuperaCoberturasEmitidasUbc(Integer cdCompania, Integer cdRamoCot,Integer cdUbicacion) {
		String sql = "SELECT * FROM coberturas_emitidas_tbl where cd_ubicacion = "+cdUbicacion+" and cd_compania = " + cdCompania
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
	public CoberturasEmitidas coberturasEmitidasUbicacion(Integer cdCompania, Integer cdRamoCot, Integer cdcob, Integer cdUbicacion) {
		String sql = "SELECT * FROM coberturas_emitidas_tbl where cd_ubicacion = "+cdUbicacion+" and cd_compania = " + cdCompania
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
		String sql = null;
		Integer ubcCodInteger = 0;
		try {
			if(obj.getCd_ubicacion() == 0 || obj.getCd_ubicacion() == null)
				ubcCodInteger = 0;
		} catch (Exception e) {
			ubcCodInteger = 0;
		}
		
		try {
			if(obj.getPorcentajeplancobertura()== null )
				obj.setPorcentajeplancobertura(0.0);
		} catch (Exception e) {
			obj.setPorcentajeplancobertura(0.0);
		}
		
		try {
			if(obj.getValor_plancobertura() == null)
				obj.setValor_plancobertura(0.00);
		} catch (Exception e) {
			obj.setValor_plancobertura(0.00);
		}
		try {
			if(obj.getEspecificacion_cob() == null)
				obj.setEspecificacion_cob("-");
		} catch (Exception e) {
			obj.setEspecificacion_cob("-");
		}
		System.out.println("obj.getPorcentajeplancobertura()"+obj.getPorcentajeplancobertura());
		System.out.println("obj.getValor_plancobertura()"+obj.getValor_plancobertura());
		System.out.println("obj.getEspecificacion_cob()"+obj.getEspecificacion_cob());
		System.out.println("obj.getEspecificacion_cob()"+obj.getEspecificacion_cob());
		System.out.println("obj.getCd_compania()"+obj.getCd_compania());
		System.out.println("obj.getCd_cobertura()"+obj.getCd_cobertura());
		System.out.println("obj.getCd_ramo_cotizacion()"+obj.getCd_ramo_cotizacion());
		System.out.println("obj.getCd_ubicacion()"+obj.getCd_ubicacion());
		System.out.println("ubcCodInteger:"+ubcCodInteger);
		try {
			if(ubcCodInteger == 0) {
				sql = "update coberturas_emitidas_tbl  " 
						+ "set porcentajeplancobertura = "+ obj.getPorcentajeplancobertura() 
						+ " ,valor_plancobertura = " + obj.getValor_plancobertura()
						+ " ,especificacion_cob = '" + obj.getEspecificacion_cob()+"'"
						+ " where cd_compania = " + obj.getCd_compania() 
						+ " and cd_cobertura = " + obj.getCd_cobertura()
						+ " and cd_ramo_cotizacion = " + obj.getCd_ramo_cotizacion();
			}else {
				System.out.println("INGRESO UBICACION");
				sql = "update coberturas_emitidas_tbl  " 
						+ "set porcentajeplancobertura = "+ obj.getPorcentajeplancobertura()
						+ " ,valor_plancobertura = " + obj.getValor_plancobertura()
						+ " ,especificacion_cob = '" + obj.getEspecificacion_cob()+"'"
						+ " where cd_compania = " + obj.getCd_compania()
						+ " and cd_cobertura = " + obj.getCd_cobertura()
						+ " and cd_ramo_cotizacion = " + String.valueOf(obj.getCd_ramo_cotizacion())
						+ " and cd_ubicacion = " + String.valueOf(obj.getCd_ubicacion());
			}
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}

		System.out.println("sql->:"+sql );
		try {
			
			conn = ConectarBase.getOracleConnection();
			System.out.println("Ingreso datos ORACLE -> update coberturas_emitidas_tbl");
			
			
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
