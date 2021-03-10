package confia.servicios.transaccionales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Cotizacion;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioCotizacion {
	
	@PersistenceContext
	private EntityManager em;
	
	public Cotizacion recuperaCotizacionPorCodigo(int cdCot, int cdCompania) {
		String sql = "select * from cotizacion_tbl where cd_cotizacion = "+cdCot+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Cotizacion.class);
		 return (Cotizacion) q.getSingleResult();
	}
	public Cotizacion recuperaCotizacionMensualPorCodigo(int cdCot, int cdCompania) {
		String sql = "select * from cotizacion_tbl where fact_periodica_cot = 1 and cd_cotizacion = "+cdCot+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Cotizacion.class);
		 return (Cotizacion) q.getSingleResult();
	}
	
	public Integer insertarCotizacion(Cotizacion obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public int codigoMaxCotizacion() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_cotizacion),1) as cd_cotizacion from cotizacion_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_cotizacion");
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

	public void actualizaCotizacion(Cotizacion obj) {
		em.merge(obj);
	}

	public void eliminaCotizacion(Cotizacion obj) {
		em.remove(em.merge(obj));
	}
	
	public int actualizaTipoCliente(String cdCompania, String cdCotizacion,String tipoCliente) {
		Connection conn;
		String sql;
		int retorno = 0;
		// ejecuta el update
		try {
			System.out.println("Ingreso datos ORACLE -> update pago_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update cotizacion_tbl set tipo_cliente = '" + tipoCliente
					+ "' where cd_compania = " + cdCompania
					+ " and cd_cotizacion  = " + cdCotizacion;
			System.out.println("QUERY update ->:" + sql);
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

}
