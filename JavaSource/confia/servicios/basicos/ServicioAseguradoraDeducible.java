package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.AseguradoraDeducible;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioAseguradoraDeducible {
	@PersistenceContext
	private EntityManager em;
	
	public AseguradoraDeducible consultaAseguradoraDeducible(String codAseg, String codDed) {
		String sql = "select * from aseguradoradeducible_tbl where cd_aseguradora = "+codAseg+" and cd_deducible= "+codDed;
		try {
			Query query = em.createNativeQuery(sql, AseguradoraDeducible.class);
			return (AseguradoraDeducible) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public int codigoMaxAseguradoraDeducible() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_asegded),1) as cd_asegded from aseguradoradeducible_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_asegded");
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
	
		
	public Integer insertarAseguradoraDeducible(AseguradoraDeducible obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaAseguradoraDeducible(AseguradoraDeducible obj) {
		em.merge(obj);
	}

	public void eliminaAseguradoraDeducible(AseguradoraDeducible obj) {
		em.remove(em.merge(obj));
	}

}
