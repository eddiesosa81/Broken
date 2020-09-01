package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.AseguradoraCobertura;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioAseguradoraCobertura {
	@PersistenceContext
	private EntityManager em;
	
	public AseguradoraCobertura consultaAseguradoraCobertura(String codCob,String codAseg) {
		String sql = "select * from aseguradoracobertura_tbl where cd_cobertura = "+codCob
				+ " and cd_aseguradora = "+codAseg;
		try {
			Query query = em.createNativeQuery(sql, AseguradoraCobertura.class);
			return (AseguradoraCobertura) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public int codigoMaxAseguradoraCobertura() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_asegcob),1) as cd_asegcob from aseguradoracobertura_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_asegcob");
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

	
	public Integer insertarAseguradoraCobertura(AseguradoraCobertura obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaAseguradoraCobertura(AseguradoraCobertura obj) {
		em.merge(obj);
	}

	public void eliminaAseguradoraCobertura(AseguradoraCobertura obj) {
		em.remove(em.merge(obj));
	}

}
