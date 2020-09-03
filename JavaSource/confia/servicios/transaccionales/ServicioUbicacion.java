package confia.servicios.transaccionales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.CallableStatement;
//import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import confia.entidades.transaccionales.Ubicacion;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioUbicacion {
	@PersistenceContext
	private EntityManager em;

	public Ubicacion recuperaUbicacionPorCodigo(int cdUbc, int cdCompania) {
		String sql = "select * from ubicacion_tbl where cd_ubicacion = " + cdUbc + " and cd_compania = " + cdCompania;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Ubicacion.class);
		return (Ubicacion) q.getSingleResult();
	}
	
	public Ubicacion recuperaUbicacionPolAne(String cdRamoCot, String cdCompania,String descUbc) {
		String sql = "select distinct * from ubicacion_pol_tbl where cd_ramo_cotizacion = " + cdRamoCot + " and cd_compania = " + cdCompania
				+" and trim(dsc_ubicacion) = '"+descUbc.trim()+"'";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Ubicacion.class);
		return (Ubicacion) q.getSingleResult();
	}

	public Ubicacion recuperaUbicacionAnexoxCrc(int cdRamCot, int cdCompania) {
		String sql = "select * from ubicacion_tbl where cd_ramo_cotizacion = " + cdRamCot + " and cd_compania = "
				+ cdCompania;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Ubicacion.class);
		return (Ubicacion) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Ubicacion> listarUbicaciones(Integer cd_ramo_cotizacion) {
		List<Ubicacion> resultado;
		String sql = "select * from ubicacion_tbl where cd_ramo_cotizacion = :cd_ramo_cotizacion order by dsc_ubicacion";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, Ubicacion.class);
			query.setParameter("cd_ramo_cotizacion", cd_ramo_cotizacion);

			resultado = query.getResultList();

			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public Integer insertarUbicacion(Ubicacion obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int insertaUbicacionAnexo(Integer cdRamoCot, Integer cdCompania, String descrip) {
		Connection conn;
		String sql;
		int retorno = 0;

		try {
			System.out.println("Ingreso datos ORACLE -> insert ubicacion_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "insert into ubicacion_tbl (cd_compania,cd_ramo_cotizacion,dsc_ubicacion) values ("+cdCompania+","+cdRamoCot+",'"+descrip+"')";
			System.out.println("QUERY ubicacion_tbl ->:" + sql);
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
			conn.close();
			retorno = 1;
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			retorno = 0;
		}

		return retorno;

	}

	public int codigoMaxUbc(Integer cdRamCot) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_ubicacion),1) as cd_ubicacion from ubicacion_tbl where cd_ramo_cotizacion = "+cdRamCot;
			System.out.println("SQL -> " + sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_ubicacion");
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
	

	public void actualizaUbicacion(Ubicacion obj) {
		em.merge(obj);
	}

	public void eliminaUbicacion(Ubicacion obj) {
		em.remove(em.merge(obj));
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void actualizaUbicacion(int codramocot, int codcompania) {
		// Connection conn;
		// ejecuta el SP
		try {
			System.out.println("Ingreso datos ORACLE -> act_valores_ubicacion_sp");
			/*
			 * conn = ConectarBase.getOracleConnection(); String
			 * proc3StoredProcedure = "{ call act_valores_ubicacion_sp(?,?) }";
			 * CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			 * cs.setInt(1, codramocot); cs.setInt(2, codcompania);
			 * cs.execute(); cs.close(); conn.close();
			 */
			StoredProcedureQuery query = em.createStoredProcedureQuery("act_valores_ubicacion_sp", Ubicacion.class)
					.registerStoredProcedureParameter("codramocot", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("codcompania", Integer.class, ParameterMode.IN)
					.setParameter("codramocot", codramocot).setParameter("codcompania", codcompania);

			List<Ubicacion> userList = query.getResultList();
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
	}

}
