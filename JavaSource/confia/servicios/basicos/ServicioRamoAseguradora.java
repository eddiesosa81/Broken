package confia.servicios.basicos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.RamoAseguradora;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioRamoAseguradora {
	@PersistenceContext
	private EntityManager em;
	
	public RamoAseguradora consultaRamoAseguradora(String cosAseg,String codRam) {
		String sql = "select * from ramoaseguradora_tbl where cd_ramo = "+codRam
				+ " and cd_aseguradora = "+cosAseg;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, RamoAseguradora.class);
			return (RamoAseguradora) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public RamoAseguradora consultaCdRamoAseguradora(String codRamAseg) {
		String sql = "select * from ramoaseguradora_tbl where cd_ramoaseg = "+codRamAseg;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, RamoAseguradora.class);
			return (RamoAseguradora) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	
	public int codigoMaxRamoAseguradora() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_ramoaseg),1) as cd_ramoaseg from ramoaseguradora_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_ramoaseg");
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

	
	public Integer insertarRamoAseguradora(RamoAseguradora obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaRamoAseguradora(RamoAseguradora obj) {
		em.merge(obj);
	}

	public void eliminaRamoAseguradora(RamoAseguradora obj) {
		em.remove(em.merge(obj));
	}

}
