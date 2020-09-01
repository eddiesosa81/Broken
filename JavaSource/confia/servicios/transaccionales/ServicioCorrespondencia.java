package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Correspondencia;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioCorrespondencia {
	@PersistenceContext
	private EntityManager em;
	
	public String numCartaMax(String usrID) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select num_Carta from Correspondencia_tbl "
					+ "where cd_correspondencia = (select max(cd_correspondencia) "
												+ "from Correspondencia_tbl where usrid = "+usrID+" )";
			System.out.println("SQL->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("num_Carta");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return resultado;
	}
	public String codCorresMax(String usrID) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select cd_correspondencia from Correspondencia_tbl "
					+ "where cd_correspondencia = (select max(cd_correspondencia) "
												+ "from Correspondencia_tbl where usrid = "+usrID+" )";
			System.out.println(sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_correspondencia");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Correspondencia> buscaCorrespondencia(String usrid, String cd_cliente,
	String cd_aseguradora, String tipo, String doc ) {
		
		String sql = "Select * from Correspondencia_tbl where usrid like '"+usrid+"'"
				+ " and cd_cliente like '"+cd_cliente+"'"
				+ " and cd_aseguradora like '"+cd_aseguradora+"'"
				+ " and modulo_genera like '"+tipo+"'"
				+ " and num_carta like '"+doc+"'"
        		+" order by cd_correspondencia desc";
  
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Correspondencia.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Correspondencia> listaCorrespon()
	{
		List<Correspondencia> resultado;
		String sql = "select * from correspondencia_tbl";
		try
		{
			Query query = em.createNativeQuery(sql,Correspondencia.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Correspondencia documentoGenerado(Integer codCorres)
	{
		Correspondencia resultado = new Correspondencia();
		String sql = "select * from correspondencia_tbl where cd_correspondencia = "+codCorres;
		try
		{
			Query query = em.createNativeQuery(sql,Correspondencia.class);

			resultado = (Correspondencia) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	public Correspondencia documentoNumCarta(String numCar)
	{
		Correspondencia resultado = new Correspondencia();
		String sql = "select * from correspondencia_tbl where num_carta = '"+numCar+"'";
		try
		{
			Query query = em.createNativeQuery(sql,Correspondencia.class);

			resultado = (Correspondencia) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public void insertarCorrespondencia(Correspondencia obj) {
		em.persist(obj);
	}

	public void actualizaCorrespondencia(Correspondencia obj) {
		em.merge(obj);
	}

	public void eliminaCorrespondencia(Correspondencia obj) {
		em.remove(em.merge(obj));
	}

}
