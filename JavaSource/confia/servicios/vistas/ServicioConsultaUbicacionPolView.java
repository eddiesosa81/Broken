package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaUbicacionPolView;

@Stateless
public class ServicioConsultaUbicacionPolView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaUbicacionPolView> consultaUbicacionxCrc(String cdRamoCot) {
		List<ConsultaUbicacionPolView> resultado = new ArrayList<ConsultaUbicacionPolView>();
		String sql = "select * from consulta_ubicacion_pol_view where cd_ramo_cotizacion = "+cdRamoCot+" order by dsc_ubicacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaUbicacionPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaUbicacionPolView> consultaUbicacionAnexoxCrc(String cdRamoCot) {
		List<ConsultaUbicacionPolView> resultado = new ArrayList<ConsultaUbicacionPolView>();
		String sql = "select * from consulta_ubicacion_anexo_view where cd_ramo_cotizacion = "+cdRamoCot;
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaUbicacionPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
}
