package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.LiquidaDiferencial;
import confia.entidades.transaccionales.PreFacturaDetalle;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioLiquidaDiferencial {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public int numLiqDif() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select secuencia_cd_num_liquidacion.nextval as codsec from dual";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("codsec");
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
	
	public LiquidaDiferencial consultaLiquidaDiferencial(Integer cd_comision_poliza) {
		String sql = "select * from liquida_diferencial_tbl where cd_comision_poliza =" + cd_comision_poliza;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, LiquidaDiferencial.class);
		return (LiquidaDiferencial) q.getSingleResult();
	}
	
	public Integer insertaComiDiferencial(LiquidaDiferencial obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	

	public void actualizaDiferencial(LiquidaDiferencial obj) {
		em.merge(obj);
	}

}
