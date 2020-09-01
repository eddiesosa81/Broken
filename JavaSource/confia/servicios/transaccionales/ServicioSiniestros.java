package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Siniestros;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioSiniestros {
	@PersistenceContext
	private EntityManager em;

	public Siniestros recuperaCodSiniestros(int cdsinies) {
		String sql = "select * from siniestro_tbl where cd_siniestro = " + cdsinies + " order by cd_siniestro";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Siniestros.class);
		return (Siniestros) q.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<Siniestros> recuperaSiniestrosNmClie(String nmClie) {
		String sql = "select * from siniestro_tbl where bloqueo = 0 and nm_cliente like '%" + nmClie + "%'"
				+ " order by cd_siniestro";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Siniestros.class);
		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Siniestros> recuperaSiniestros(String nmClie,String pol,String numSinies) {
		String sql = "select * from siniestro_tbl where bloqueo = 0 and nm_cliente like '%" + nmClie 
				+ "%' and to_char(cd_siniestro) like '"+numSinies+"' and poliza like '"+pol+"' order by cd_siniestro";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Siniestros.class);
		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Siniestros> recuperaSiniestrosCerrados(String nmClie, String numSinies) {
		String sql = "select * from siniestro_tbl where bloqueo = 1 and nm_cliente like '%" + nmClie
				+ "%' and to_char(cd_siniestro) like '%" + numSinies + "%'" + " order by cd_siniestro";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Siniestros.class);
		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Siniestros> recuperaSiniestrosRamoCot(String cdRamoCotizacion) {
		String sql = "select * from siniestro_tbl where bloqueo = 0 and cd_ramo_cotizacion in(" + cdRamoCotizacion + ")"
				+ " order by cd_siniestro";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Siniestros.class);
		return q.getResultList();

	}

	public Integer insertarSiniestros(Siniestros obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public int codigoMaxSiniestro() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_siniestro),1) as cd_siniestro from siniestro_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_siniestro");
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

	public void actualizaSiniestros(Siniestros obj) {
		em.merge(obj);
	}

	public void eliminaSiniestros(Siniestros obj) {
		em.remove(em.merge(obj));
	}
}
