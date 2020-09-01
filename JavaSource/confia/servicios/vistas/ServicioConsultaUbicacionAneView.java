package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaUbicacionAneView;

@Stateless
public class ServicioConsultaUbicacionAneView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaUbicacionAneView> consultaUbicacionxCrc(String cdRamoCot) {
		List<ConsultaUbicacionAneView> resultado = new ArrayList<ConsultaUbicacionAneView>();
		String sql = "select * from consulta_ubicacion_anexo_view where cd_ramo_cotizacion = "+cdRamoCot;
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaUbicacionAneView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
}
