package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ProduccionEmisionesPendientesView;
import confia.entidades.vistas.ProspeccionesView;

@Stateless
public class ServicioProspecciones {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> PospeccionesViewCot(String apellido) {
		List<ProspeccionesView> resultado;
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW where primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%' order by num_cotizacion ";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ProspeccionesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> PospeccionesView(String apellido) {
		List<ProspeccionesView> resultado;
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW where primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%' order by num_cotizacion ";
		try {
			Query query = em.createNativeQuery(sql, ProspeccionesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> PospeccionesViewNumMCot(String numCot) {
		List<ProspeccionesView> resultado;
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW where num_cotizacion like '%"+numCot+"%'  order by num_cotizacion ";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ProspeccionesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> PospeccionesViewNumCot(String numCot) {
		List<ProspeccionesView> resultado;
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW where num_cotizacion like '%"+numCot+"%' order by num_cotizacion ";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ProspeccionesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> PospeccionesViewApeNumCot(String apellido,String numCot) {
		List<ProspeccionesView> resultado;
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW where primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%' and num_cotizacion like '%"+numCot+"%' order by num_cotizacion ";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ProspeccionesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> PospeccionesViewMensualizado(String apellido, String numCotizacion) {
		List<ProspeccionesView> resultado;
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW where fact_periodica_cot = 1 and (primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%') and num_cotizacion like '%"
				+ numCotizacion+ "%' order by num_cotizacion ";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ProspeccionesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> PospeccionesViewXCdCot(Integer cdCotizacion) {
		List<ProspeccionesView> resultado;
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW where cd_cotizacion = "+cdCotizacion;
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ProspeccionesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProspeccionesView> recuperaProspeccionView(String codClie, String codGrpCont,
			String codAseg, String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,
			String tipo) {
		String sql = "select * from PROSPECCION_PENDIENTE_VIEW " + "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" + " and to_char(cd_aseguradora) like '"
				+ codAseg + "'" + " and to_char(cd_ramo) like '" + codRamo + "'" + " and to_char(cd_subagente) like '"
				+ codSubagen + "'" + " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and fechajuliana_func(to_char(fc_creacion,'dd/MM/yyyy')) >= fechajuliana_func('" + fcDesde + "')"
				+ " and fechajuliana_func(to_char(fc_creacion,'dd/MM/yyyy')) <= fechajuliana_func('" + fcHasta + "')";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ProspeccionesView.class);
		return q.getResultList();
	}
}
