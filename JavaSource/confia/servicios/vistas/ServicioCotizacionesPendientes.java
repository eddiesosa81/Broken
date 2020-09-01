package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.CotizacionesPendientes;

@Stateless
public class ServicioCotizacionesPendientes {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<CotizacionesPendientes> cotizacionesPendientesCot(String apellido) {
		List<CotizacionesPendientes> resultado;
		String sql = "select * from cotizacion_pendiente_view where primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%' and cd_rubro in (8,9) order by num_cotizacion ";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, CotizacionesPendientes.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CotizacionesPendientes> cotizacionesPendientes(String apellido) {
		List<CotizacionesPendientes> resultado;
		String sql = "select * from cotizacion_pendiente_view where primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%' order by num_cotizacion ";
		try {
			Query query = em.createNativeQuery(sql, CotizacionesPendientes.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CotizacionesPendientes> cotizacionesPendientesNumMCot(String numCot) {
		List<CotizacionesPendientes> resultado;
		String sql = "select * from cotizacion_pendiente_view where num_cotizacion like '%"+numCot+"%' and cd_rubro in(8,9) order by num_cotizacion ";
		try {
			Query query = em.createNativeQuery(sql, CotizacionesPendientes.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CotizacionesPendientes> cotizacionesPendientesNumCot(String numCot) {
		List<CotizacionesPendientes> resultado;
		String sql = "select * from cotizacion_pendiente_view where num_cotizacion like '%"+numCot+"%' order by num_cotizacion ";
		try {
			Query query = em.createNativeQuery(sql, CotizacionesPendientes.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CotizacionesPendientes> cotizacionesPendientesApeNumCot(String apellido,String numCot) {
		List<CotizacionesPendientes> resultado;
		String sql = "select * from cotizacion_pendiente_view where primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%' and num_cotizacion like '%"+numCot+"%' order by num_cotizacion ";
		try {
			Query query = em.createNativeQuery(sql, CotizacionesPendientes.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CotizacionesPendientes> cotizacionesPendientesMensualizado(String apellido, String numCotizacion) {
		List<CotizacionesPendientes> resultado;
		String sql = "select * from cotizacion_pendiente_view where fact_periodica_cot = 1 and (primer_apellido_cliente like '%"
				+ apellido + "%' or razon_social_cliente like '%"+apellido+"%') and num_cotizacion like '%"
				+ numCotizacion+ "%' order by num_cotizacion ";
		try {
			Query query = em.createNativeQuery(sql, CotizacionesPendientes.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CotizacionesPendientes> cotizacionesPendientesXCdCot(Integer cdCotizacion) {
		List<CotizacionesPendientes> resultado;
		String sql = "select * from cotizacion_pendiente_view where cd_cotizacion = "+cdCotizacion;
		try {
			Query query = em.createNativeQuery(sql, CotizacionesPendientes.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
}
