package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.FormaPago;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioFormaPago {
	@PersistenceContext
	private EntityManager em;
	
	public FormaPago recuperaFormaPagoxCod(int cdFrmP, int cdCompania) {
		String sql = "select * from forma_pago_tbl where cd_forma_pago = "+cdFrmP+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, FormaPago.class);
		 return (FormaPago) q.getSingleResult();
	}
	public FormaPago recuperaFormaPagoxCdCot(int cdcot, int cdCompania) {
		String sql = "select * from forma_pago_tbl where cd_cotizacion = "+cdcot+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, FormaPago.class);
		 return (FormaPago) q.getSingleResult();
	}
	public Integer insertaFormaPago(FormaPago obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public int codigoMaxFormaPago() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_forma_pago),1) as cd_forma_pago from forma_pago_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_forma_pago");
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
	
	public int numeroAniosVigencia(Date fcDesde, Date fcHasta) {
		Connection conn;
		String sql;
		String resultado = "0";
		SimpleDateFormat formato;
		String patron = "dd/MMM/yyyy";
		
		formato = new SimpleDateFormat(patron);
		String FechaDesde = formato.format(fcDesde);
		String FechaHasta = formato.format(fcHasta);
		
		
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select calcAnios('"+FechaDesde+"','"+FechaHasta+"') as anios from dual";
			System.out.println("SQL->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("anios");
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
	
	public int verificaFormaPago(Integer cdcot, Integer cdCompa) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(cd_forma_pago) as cd_forma_pago from forma_pago_tbl where cd_cotizacion = "+
					cdcot+" and cd_compania = "+cdCompa;
			System.out.println("SQL ->:"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_forma_pago");
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

	public void actualizaFormaPago(FormaPago obj) {
		em.merge(obj);
	}

	public void eliminaFormaPago(FormaPago obj) {
		em.remove(em.merge(obj));
	}

}
