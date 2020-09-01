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

import confia.entidades.transaccionales.Pagos;
import confia.entidades.transaccionales.Ubicacion;
import confia.procedures.ConectarBase;

/**
 * @author Guido Guerrero
 *
 */
@Stateless
public class ServicioPagos {
	@PersistenceContext
	private EntityManager em;
	
	public Integer guardarPago(Pagos pago) {
		em.persist(pago);
		Integer temp = 0;
		String sql = "Select max(e.cd_pago) from Pagos e";
		
		Query query = em.createQuery(sql);
		temp = (Integer) query.getSingleResult();
		
		return temp;
	}
	
	public Integer insertarPago(Pagos obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public int codigoMaxPago() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_pago),1) as cd_pago from pago_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_pago");
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
}
