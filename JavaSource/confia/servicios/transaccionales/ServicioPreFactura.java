package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.PreFactura;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioPreFactura {
	@PersistenceContext
	private EntityManager em;
	
	public PreFactura recuperaPreFacturaPorCodigo(int cdPreFac) {
		String sql = "select * from pre_factura_tbl where cd_pre_factura = "+cdPreFac;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, PreFactura.class);
		 return (PreFactura) q.getSingleResult();
	}
	public Integer insertaPreFactura(PreFactura obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public int codigoMaxPreFactura() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_pre_factura),1) as cd_pre_factura from pre_factura_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_pre_factura");
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

	public void actualizaPreFactura(PreFactura obj) {
		em.merge(obj);
	}

	public void eliminaPreFactura(PreFactura obj) {
		em.remove(em.merge(obj));
	}


}
