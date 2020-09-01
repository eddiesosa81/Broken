package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaObjetoPolView;

@Stateless
public class ServicioConsultaObjetoPolView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaObjetoPolView> consultaObjetosXUbc (String cdUbicacion) {
		List<ConsultaObjetoPolView> resultado = new ArrayList<ConsultaObjetoPolView>();
		String sql = "select * from CONSULTA_OBJETO_POL_VIEW where cd_ubicacion =  "+cdUbicacion+" order by descripcion_objeto";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaObjetoPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ConsultaObjetoPolView> consultaObjetosAnexoXUbc (String cdUbicacion) {
		List<ConsultaObjetoPolView> resultado = new ArrayList<ConsultaObjetoPolView>();
		String sql = "select * from consulta_objeto_ane_view where cd_ubicacion =  "+cdUbicacion+" order by descripcion_objeto";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaObjetoPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
}
