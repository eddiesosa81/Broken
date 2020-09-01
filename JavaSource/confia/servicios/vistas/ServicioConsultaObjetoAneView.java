package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaObjetoAneView;

@Stateless
public class ServicioConsultaObjetoAneView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaObjetoAneView> consultaObjetosXUbc (String cdUbicacion) {
		List<ConsultaObjetoAneView> resultado = new ArrayList<ConsultaObjetoAneView>();
		String sql = "select * from consulta_objeto_ane_view where cd_ubicacion =  "+cdUbicacion;
		try {
			Query query = em.createNativeQuery(sql, ConsultaObjetoAneView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
