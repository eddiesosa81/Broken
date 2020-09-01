package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaSubObjetoPolView;

@Stateless
public class ServicioConsultaSubObjetoPolView {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSubObjetoPolView> consultaSubObjetoxCdObj (String cdObje) {
		List<ConsultaSubObjetoPolView> resultado = new ArrayList<ConsultaSubObjetoPolView>();
		String sql = "select * from CONSULTA_SUBOJBETO_POL_VIEW where cd_obj_cotizacion =  "+cdObje;
		try {
			Query query = em.createNativeQuery(sql, ConsultaSubObjetoPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ConsultaSubObjetoPolView> consultaSubObjetoAnexoxCdObj (String cdObje) {
		List<ConsultaSubObjetoPolView> resultado = new ArrayList<ConsultaSubObjetoPolView>();
		String sql = "select * from consulta_subojbeto_ane_view where cd_obj_cotizacion =  "+cdObje;
		try {
			Query query = em.createNativeQuery(sql, ConsultaSubObjetoPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
