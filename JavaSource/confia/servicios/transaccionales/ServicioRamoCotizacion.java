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

import confia.entidades.transaccionales.RamoCotizacion;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioRamoCotizacion {
	@PersistenceContext
	private EntityManager em;

	public int verificoExisteFactura(String numPol, String numFact) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as cd_ramo_cotizacion from ramo_cotizacion_pol_tbl " + "where poliza = '"
					+ numPol.trim().toUpperCase() + "' and factura_aseguradora = '" + numFact.trim().toUpperCase()
					+ "'";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_ramo_cotizacion");
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

	public int verificoExisteFacturaAnexo(String numPol, String numFact) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as cd_ramo_cotizacion from ramo_cotizacion_tbl " + "where poliza = '" + numPol.trim()
					+ "' and factura_aseguradora = '" + numFact.trim() + "'";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_ramo_cotizacion");
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

	public RamoCotizacion obtieneIdRamoCotizacionXCotizacion(Integer cd_cotizacion) {
		String sql = "Select * from ramo_cotizacion_tbl where cd_cotizacion = " + cd_cotizacion
				+ " and cd_ramo_cotizacion = ( select max(cd_ramo_cotizacion) from ramo_cotizacion_tbl where cd_cotizacion = "
				+ cd_cotizacion + ")";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, RamoCotizacion.class);
		return (RamoCotizacion) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<RamoCotizacion> ramoCotizacionPolizaFactura(String poliza, String factura, String cdramcot) {
		String sql = "Select * from ramo_cotizacion_tbl where poliza like '" + poliza
				+ "' and factura_aseguradora like '" + factura + "' and cd_ramo_cotizacion like '" + cdramcot + "'";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, RamoCotizacion.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<RamoCotizacion> ramoCotizacionPolizaFacturaPeriodica(String poliza, String factura, String cdramcot) {
		String sql = "Select * from ramo_cotizacion_tbl where poliza like '" + poliza
				+ "' and cd_ramo_cotizacion like '" + cdramcot + "' and cd_cotizacion in (select cd_cotizacion "
				+ "from forma_pago_tbl where cd_forma_pago in(select cd_forma_pago " + "from detalle_forma_pago_tbl "
				+ "where factura_aseguradora like '" + factura + "'))";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, RamoCotizacion.class);
		return q.getResultList();
	}

	public RamoCotizacion recuperaRamoCotizacionPorCodigo(int cdramcot, int cdCompania) {
		String sql = "select * from ramo_cotizacion_tbl where cd_ramo_cotizacion = " + cdramcot + " and cd_compania = "
				+ cdCompania;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, RamoCotizacion.class);
		return (RamoCotizacion) q.getSingleResult();
	}

	public RamoCotizacion recuperaRamoCotizacionAnexo(int cdcot, int cdCompania) {
		String sql = "select * from ramo_cotizacion_tbl where cd_cotizacion = " + cdcot + " and cd_compania = "
				+ cdCompania;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, RamoCotizacion.class);
		return (RamoCotizacion) q.getSingleResult();
	}

	public Integer insertarRamoCotizacion(RamoCotizacion obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			System.out.println("ERROR INSERT RAMOCOT" + e.getMessage());
			return 0;
		}
		return 1;
	}

	public int actualizaCanalRamCot(Integer cdCompania, Integer cdRamCot, String cdSubAgen) {
		Connection conn;
		String sql;
		int retorno = 0;
		//
		try {
			System.out.println("Ingreso datos ORACLE -> update ramo_cotizacion_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update ramo_cotizacion_pol_tbl set cd_subagente = " + cdSubAgen + " where cd_compania = " + cdCompania
					+ " and cd_ramo_cotizacion =" + cdRamCot;
			System.out.println("QUERY ramo_cotizacion_tbl ->:" + sql);
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
			conn.close();
			retorno = 1;
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			retorno = 0;
		}
		// ejecuta el update
		try {
			System.out.println("Ingreso datos ORACLE -> update ramo_cotizacion_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update ramo_cotizacion_tbl set cd_subagente = " + cdSubAgen + " where cd_compania = " + cdCompania
					+ " and cd_ramo_cotizacion =" + cdRamCot;
			System.out.println("QUERY ramo_cotizacion_tbl ->:" + sql);
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

	public void actualizaRamoCotizacion(RamoCotizacion obj) {
		em.merge(obj);
	}

	public void eliminaRamoCotizacion(RamoCotizacion obj) {
		em.remove(em.merge(obj));
	}

	public RamoCotizacion saveDetached(final RamoCotizacion entity) {
		return em.merge(entity);
	}

}
