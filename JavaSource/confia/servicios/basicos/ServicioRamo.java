/**
 * 
 */
package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Ramo;
import confia.procedures.ConectarBase;

/**
 * @author Guido Guerrero
 *
 */
@Stateless
public class ServicioRamo {

	@PersistenceContext
	private EntityManager em;
	
	public Ramo ramoId(String ramId) {
		Ramo resultado;
		String sql = "select * from ramo_tbl where estado_ramo = 'A' and cd_ramo = "+ramId;
		try
		{
			Query query = em.createNativeQuery(sql,Ramo.class);
			
			resultado = (Ramo) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ramo> listaRamos() {
		List<Ramo> resultado;
		String sql = "select * from ramo_tbl where estado_ramo = 'A' order by desc_ramo";
		try
		{
			Query query = em.createNativeQuery(sql,Ramo.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public int tipoRamo(Integer codRamo) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(periodico,1) as periodico from ramo_tbl where cd_ramo = "+codRamo;
			System.out.println("SQL->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("periodico");
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
