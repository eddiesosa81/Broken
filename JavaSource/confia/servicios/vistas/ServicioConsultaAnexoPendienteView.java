package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaAnexoPendienteView;

@Stateless
public class ServicioConsultaAnexoPendienteView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaAnexoPendienteView> consultaAnexoPendiente(String nmcliente,String numPol) {
		List<ConsultaAnexoPendienteView> resultado = new ArrayList<ConsultaAnexoPendienteView>();
		String sql = "select * from consulta_anexo_pendiente_view where cliente like '%"+nmcliente.trim().toUpperCase()+"%' and poliza = '"+numPol.trim()+"'";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaAnexoPendienteView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ConsultaAnexoPendienteView> consultaAnexoPendientePolizaNumcot(String numcot) {
		List<ConsultaAnexoPendienteView> resultado = new ArrayList<ConsultaAnexoPendienteView>();
		String sql = "select * from consulta_anexo_pendiente_view where num_cotizacion = '"+numcot.trim()+"'";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaAnexoPendienteView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ConsultaAnexoPendienteView> consultaAnexoPendientePoliza(String numPol) {
		List<ConsultaAnexoPendienteView> resultado = new ArrayList<ConsultaAnexoPendienteView>();
		String sql = "select * from consulta_anexo_pendiente_view where poliza = '"+numPol.trim()+"'";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaAnexoPendienteView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ConsultaAnexoPendienteView> consultaAnexoPendiente(String nmcliente) {
		List<ConsultaAnexoPendienteView> resultado = new ArrayList<ConsultaAnexoPendienteView>();
		String sql = "select * from consulta_anexo_pendiente_view where cliente like '%"+nmcliente.trim().toUpperCase()+"%'";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaAnexoPendienteView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public ConsultaAnexoPendienteView consultaAnexoPendienteRamCot(String codRamCot) {
		ConsultaAnexoPendienteView resultado = new ConsultaAnexoPendienteView();
		String sql = "select * from consulta_anexo_pendiente_view where cd_ramo_cotizacion = "+codRamCot;
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaAnexoPendienteView.class);
			resultado = (ConsultaAnexoPendienteView) query.getSingleResult();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
