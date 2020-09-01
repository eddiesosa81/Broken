package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaSubObjetoAneView;

@Stateless
public class ServicioConsultaSubObjetoAneView {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSubObjetoAneView> consultaSubObjetoxCdObj (String cdObje) {
		List<ConsultaSubObjetoAneView> resultado = new ArrayList<ConsultaSubObjetoAneView>();
		String sql = "select * from CONSULTA_SUBOJBETO_ane_VIEW where cd_obj_cotizacion =  "+cdObje;
		try {
			Query query = em.createNativeQuery(sql, ConsultaSubObjetoAneView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
