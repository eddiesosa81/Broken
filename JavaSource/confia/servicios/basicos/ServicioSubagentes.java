package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Plan;
import confia.entidades.basicos.SubagenteRamo;
import confia.entidades.basicos.Subagentes;
import confia.entidades.transaccionales.CoberturasSiniestro;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioSubagentes {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Subagentes> listaSubagentes(String str_subagente) {
		List<Subagentes> resultado;
		String sql = "select * from subagente_tbl where estado_subagente = 'A' and ( "
				+ "identificacion_subagente like '%" + str_subagente + "%' or " + "primer_nombre_subagente like '%"
				+ str_subagente + "%' or " + "segundo_nombre_subagente like '%" + str_subagente + "%' or "
				+ "primer_apellido_subagente like '%" + str_subagente + "%' or " + "segundo_apellido_subagente like '%"
				+ str_subagente + "%' or " + "razonsocial_subagente like '%" + str_subagente + "%' ) "
				+ "order by primer_apellido_subagente,segundo_apellido_subagente,primer_nombre_subagente,segundo_nombre_subagente,razonsocial_subagente";
		try {
			Query query = em.createNativeQuery(sql, Subagentes.class);

			resultado = query.getResultList();

			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public Subagentes buscaSubagenteXId(Integer cd_subagente) {
		Subagentes resultado;
		String sql = "select * from subagente_tbl where estado_subagente = 'A' and cd_subagente = :cd_subagente and rownum = 1 "
				+ "order by primer_apellido_subagente,segundo_apellido_subagente,primer_nombre_subagente,segundo_nombre_subagente,razonsocial_subagente";
		try {
			Query query = em.createNativeQuery(sql, Subagentes.class);
			query.setParameter("cd_subagente", cd_subagente);

			resultado = (Subagentes) query.getSingleResult();

			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public Subagentes consultaSubagente(Integer cd_subagente) {
		String sql = "select * from subagente_tbl where cd_subagente = " + cd_subagente;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Subagentes.class);
		return (Subagentes) q.getSingleResult();
	}

	public Subagentes consultaSubagenteBrkDirecto() {
		String sql = "select * from subagente_tbl where   estado_subagente = 'A' and razonsocial_subagente LIKE '%CONFIA%DIRECTO%'";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Subagentes.class);
		return (Subagentes) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Subagentes> recuperaSubagente1() {
		String sql = "select * from subagente_tbl";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Subagentes.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Subagentes> recuperaSubagenteRamo(List<SubagenteRamo> lstRamSuba) {
		String codSuba = "";
		Integer tama = 0;
		

		for (SubagenteRamo subagenteRamo : lstRamSuba) {
			codSuba = codSuba.concat(String.valueOf(subagenteRamo.getCd_subagente()));
			codSuba = codSuba.concat(",");
		}
		tama = codSuba.length();
		codSuba = codSuba.substring(0, tama-1);

		String sql = "select * from subagente_tbl where estado_subagente = 'A' and cd_subagente  in (" + codSuba
				+ ") order by primer_apellido_subagente,razonsocial_subagente";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Subagentes.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Subagentes> recuperaSubagente() {
		String sql = "select * from subagente_tbl where estado_subagente = 'A' order by primer_apellido_subagente,razonsocial_subagente";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Subagentes.class);
		return q.getResultList();
	}

	public int insertarSuagentes(Subagentes col) {
		try {
			em.persist(col);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaSubagentes(Subagentes col) {
		String resultados;
		resultados = col.getIdentificacion_subagente();
		col.setIdentificacion_subagente(resultados);
		resultados = col.getRazonSocial_subagente();
		col.setRazonSocial_subagente(resultados);
		resultados = col.getPrimer_nombre_subagente();
		col.setPrimer_nombre_subagente(resultados);
		resultados = col.getSegundo_nombre_subagente();
		col.setSegundo_nombre_subagente(resultados);
		resultados = col.getPrimer_apellido_subagente();
		col.setPrimer_apellido_subagente(resultados);
		resultados = col.getSegundo_apellido_subagente();
		col.setSegundo_apellido_subagente(resultados);
		col.setTipo_persona_subagente(col.getTipo_persona_subagente());
		col.setTipo_identificacion_subagente(col.getTipo_identificacion_subagente());

		em.merge(col);
	}

	public void eliminaSubagentes(Subagentes obj) {
		em.remove(em.merge(obj));
	}

	public int consultaComisionesIngresadas(String codramo, String codSuba) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as comision from comisionsubagente_tbl where "
					+ "cd_subagenteramo in (select cd_subagenteramo from subagenteRamo_tbl " + "where cd_ramo = "
					+ codramo + " and cd_subagente = " + codSuba + ")";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("comision");
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
