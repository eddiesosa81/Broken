package confia.servicios.transaccionales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.ObjetoCotizacion;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioObjetoCotizacion {
	@PersistenceContext
	private EntityManager em;

	public ObjetoCotizacion recuperaObjCotPorCodigo(int cdObj, int cdCompania) {
		String sql = "select * from objeto_cotizacion_tbl where cd_obj_cotizacion = " + cdObj + " and cd_compania = "
				+ cdCompania;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ObjetoCotizacion.class);
		return (ObjetoCotizacion) q.getSingleResult();
	}

	public ObjetoCotizacion recuperaObjCotPorUbc(int cdUbc, int cdCompania) {
		String sql = "select * from objeto_cotizacion_tbl where cd_ubicacion = " + cdUbc + " and cd_compania = "
				+ cdCompania;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ObjetoCotizacion.class);
		return (ObjetoCotizacion) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<ObjetoCotizacion> recuperaObjetosPorUbicacion(int cdUbc, int cdCompania) {
		String sql = "select * from objeto_cotizacion_tbl where cd_compania = " + cdCompania + " and cd_ubicacion = "
				+ cdUbc;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ObjetoCotizacion.class);
		return q.getResultList();

	}

	public int codigoMaxObjetoCot(Integer cdUbicacion) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_obj_cotizacion),1) as cd_obj_cotizacion from objeto_cotizacion_tbl where cd_ubicacion = "
					+ cdUbicacion;
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_obj_cotizacion");
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

	public int insertaObjetoAnexo(Integer cdUbc, Integer cdCompania, String descrip,Integer codObjOri) {
		Connection conn;
		String sql;
		int retorno = 0;

		try {
			System.out.println("Ingreso datos ORACLE -> insert objeto_cotizacion_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "insert into objeto_cotizacion_tbl (cd_compania,cd_ubicacion,descripcion_objeto,cd_obj_ori) values ("
					+ cdCompania + "," + cdUbc + ",'" + descrip +"',"+codObjOri+ ")";
			System.out.println("QUERY objeto_cotizacion_tbl ->:" + sql);
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

	public Integer insertarObjetoCotizacion(ObjetoCotizacion obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return 0;
		}

		return 1;
	}

	public Integer actualizaObjetoCotizacion(ObjetoCotizacion obj) {
		try {
			em.merge(obj);
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return 0;
		}
		return 1;
	}

	public void eliminaObjetoCotizacion(ObjetoCotizacion obj) {
		em.remove(em.merge(obj));
	}
	
	public int actPrimaTotAseg(ObjetoCotizacion obj) {
		Connection conn;
		String sql;
		int retorno = 0;
		// ejecuta el update
		try {
			
			System.out.println("Ingreso datos ORACLE -> update objeto_cotizacion_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update objeto_cotizacion_tbl set prima_objeto = "+obj.getPrima_objeto()
				+" ,total_asegurado_objeto = "+obj.getTotal_asegurado_objeto()
				+" where  cd_obj_cotizacion = "+obj.getCd_obj_cotizacion()
				;
			System.out.println("QUERY objeto_cotizacion_tbl ->:" + sql);
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

	public void actualizaValorObjCot(int codramocot, int codcompania) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("Ingreso datos ORACLE -> act_valores_objetocot_sp");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call act_valores_objetocot_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setInt(1, codramocot);
			cs.setInt(2, codcompania);
			cs.execute();
			cs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
	}
}
