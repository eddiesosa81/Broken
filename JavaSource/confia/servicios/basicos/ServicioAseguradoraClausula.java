package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.AseguradoraClausula;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioAseguradoraClausula {
	@PersistenceContext
	private EntityManager em;
	
	public AseguradoraClausula consultaAseguradoraClausula(String codClau,String codAseg) {
		String sql = "select * from aseguradoraclausula_tbl where cd_clausula = "+codClau
				+ " and cd_aseguradora = "+codAseg;
		System.out.println("SQL-> "+sql);
		try {
			Query query = em.createNativeQuery(sql, AseguradoraClausula.class);
			return (AseguradoraClausula) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public int codigoMaxAseguradoraClausula() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_asegclau),1) as cd_asegclau from aseguradoraclausula_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_asegclau");
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

	
	public Integer insertarAseguradoraClausula(AseguradoraClausula obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaAseguradoraClausula(AseguradoraClausula obj) {
		em.merge(obj);
	}

	public void eliminaAseguradoraClausula(AseguradoraClausula obj) {
		em.remove(em.merge(obj));
	}


}
