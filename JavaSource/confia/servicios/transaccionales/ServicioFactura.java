/**
 * 
 */
package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Factura;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioFactura {

	@PersistenceContext
	private EntityManager em;
	
	public Factura recuperaFacturaPorCodigo(int cdFact) {
		String sql = "select * from facturas_tbl where fc_anula is null and cd_factura = "+cdFact;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Factura.class);
		 return (Factura) q.getSingleResult();
	}
	public Integer insertarFactura(Factura obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public int codigoMaxFactura() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_factura),1) as cd_factura from facturas_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_factura");
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

	public void actualizaFactura(Factura obj) {
		em.merge(obj);
	}

	public void eliminaFactura(Factura obj) {
		em.remove(em.merge(obj));
	}

}
