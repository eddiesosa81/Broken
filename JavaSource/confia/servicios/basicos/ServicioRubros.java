package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.TipoModuloCarta;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioRubros {
	@PersistenceContext
	private EntityManager em;
	
	public int accesoPantalla(String codigo,String pantalla,String codUsr,String passwd) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as acceso from rubro_tbl where codigo = "+codigo+" and desc_rubro = '"+pantalla+"' and usrid = "+codUsr+
					" and estado_rubro ='A' and passwd = '"+passwd.trim()+"'";
			System.out.println("SQL ->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("acceso");
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
	
	public Rubros recuperaRubro(String cdRubro) {
		 String sql = "select * from rubro_tbl where cd_rubro = "+cdRubro;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Rubros.class);
		 return (Rubros) q.getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public List<Rubros> listadoRubrosCod(String codigo) {
		 String sql = "select * from rubro_tbl where codigo = "+codigo+" order by orden_rubro";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Rubros.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Rubros> listadoRubrosGestionDocu(String codigo,String tipoDocu,String objetoDocu) {
		 String sql = "select * from rubro_tbl where codigo = "+codigo+" and tipo_rubro = '"+tipoDocu+"' and objeto_rubro = '"+objetoDocu
				 +"' order by orden_rubro";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Rubros.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Rubros> listadoDocumentosSiniestro(String codigo,String cdRamo) {
		 String sql = "select * from rubro_tbl where codigo = "+codigo+" and estado_rubro = 'A' and "
		 		+ "cd_ramo = "+cdRamo+" order by orden_rubro";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Rubros.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Rubros> listadoCartasPorModulo(String modulo) {
		 String sql = "select * from rubro_tbl where desc_general = '"+modulo+"' order by orden_rubro";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Rubros.class);
		 return q.getResultList();
		
	}
	
	public Rubros recuperaCartaPorNombre(String nmCarta) {
		String sql = "select * from rubro_tbl where objeto_rubro = '"+nmCarta+"' order by orden_rubro";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Rubros.class);
		 return (Rubros) q.getSingleResult();
	}

	
	public String recuperaIva() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = " select nvl(porcentaje_rubro,0) as porcentaje_rubro from rubro_tbl where cd_rubro = 5";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("porcentaje_rubro");
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
	
	public List<TipoModuloCarta> recuperaTipoModuloCarta() {
		Connection conn;
		String sql;
		List<TipoModuloCarta> lstTipoCarta = new ArrayList<TipoModuloCarta>();
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select distinct desc_general  from rubro_tbl where codigo = 300";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			while (res.next()) {
				lstTipoCarta.add(new TipoModuloCarta(res.getString(1)));
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lstTipoCarta = null;
		}
		return lstTipoCarta;
	}
	public List<TipoModuloCarta> recuperaTipoGestionDocu() {
		Connection conn;
		String sql;
		List<TipoModuloCarta> lstTipoCarta = new ArrayList<TipoModuloCarta>();
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select distinct tipo_rubro  from rubro_tbl where codigo = 400";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			while (res.next()) {
				lstTipoCarta.add(new TipoModuloCarta(res.getString(1)));
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lstTipoCarta = null;
		}
		return lstTipoCarta;
	}
	
	public List<TipoModuloCarta> recuperaObjetoGestionDocu(String tipoRubro) {
		Connection conn;
		String sql;
		List<TipoModuloCarta> lstTipoCarta = new ArrayList<TipoModuloCarta>();
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select distinct objeto_rubro  from rubro_tbl where codigo = 400 and tipo_rubro = '"+tipoRubro+"' order by objeto_rubro";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			while (res.next()) {
				lstTipoCarta.add(new TipoModuloCarta(res.getString(1)));
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lstTipoCarta = null;
		}
		return lstTipoCarta;
	}
	

	public void insertarRubros(Rubros obj) {
		em.persist(obj);
	}

	public void actualizaRubros(Rubros obj) {
		em.merge(obj);
	}

	public void eliminaRubros(Rubros obj) {
		em.remove(em.merge(obj));
	}

}
