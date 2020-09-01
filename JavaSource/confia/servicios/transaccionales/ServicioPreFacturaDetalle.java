package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.PreFacturaDetalle;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioPreFacturaDetalle {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<PreFacturaDetalle> recuperaPreFacturaDetalleComPol(int cdComPol) {
		String sql = "select * from detalle_pre_factura_tbl where cd_comision_poliza = "+cdComPol;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, PreFacturaDetalle.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<PreFacturaDetalle> recuperaPreFacturaDetallePorPrefa(int cdPreFac) {
		String sql = "select * from detalle_pre_factura_tbl where cd_pre_factura = "+cdPreFac;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, PreFacturaDetalle.class);
		 return q.getResultList();
	}
	public PreFacturaDetalle recuperaPreFacturaDetallePorCodigo(int cdPreFacDet) {
		String sql = "select * from detalle_pre_factura_tbl where cd_detalle_pre_factura = "+cdPreFacDet;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, PreFacturaDetalle.class);
		 return (PreFacturaDetalle) q.getSingleResult();
	}
	public Integer insertaPreFacturaDetalle(PreFacturaDetalle obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public int codigoMaxPreFacturaDetalle() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_detalle_pre_factura),1) as cd_detalle_pre_factura from detalle_pre_factura_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_detalle_pre_factura");
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
	public int preFacturaRealizada(Integer cdComPoliza) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "SELECT count(*) as numComPol FROM detalle_pre_factura_tbl where  cd_comision_poliza = "+cdComPoliza;
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("numComPol");
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
	

	public void actualizaPreFacturaDetalle(PreFacturaDetalle obj) {
		em.merge(obj);
	}

	public void eliminaPreFacturaDetalle(PreFacturaDetalle obj) {
		em.remove(em.merge(obj));
	}



}
