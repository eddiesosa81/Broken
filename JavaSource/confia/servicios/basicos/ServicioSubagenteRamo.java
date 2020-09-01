package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.SubagenteRamo;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioSubagenteRamo {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<SubagenteRamo> listaSubagenteRamo(String codRamo) {
		String sql = "select * from subagenteRamo_tbl where cd_ramo = "+codRamo;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, SubagenteRamo.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public SubagenteRamo consultaSubagenteRamo(Integer codSuba, Integer codRamo) {
		String sql = "select * from subagenteRamo_tbl where cd_ramo = "+codRamo
				+" and cd_subagente = "+codSuba;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, SubagenteRamo.class);
			return (SubagenteRamo) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public int codigoMaxSubagenteRamo() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_subagenteramo),1) as cd_subagenteramo from subagenteRamo_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_subagenteramo");
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
	
	
	public Integer insertarSubagenteRamo(SubagenteRamo obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaSubagenteRamo(SubagenteRamo obj) {
		em.merge(obj);
	}

	public void eliminaSubagenteRamo(SubagenteRamo obj) {
		em.remove(em.merge(obj));
	}


}
