package confia.servicios.vistas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaFactConifaView;

@Stateless
public class ServicioConsultaFactConifaView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaFactConifaView> consultaFactConfia (String pol,String fac) {
		List<ConsultaFactConifaView> resultado = new ArrayList<ConsultaFactConifaView>();
		String sql = "select * from consulta_fact_confia_view where poliza = '"+pol.trim()+"' and factura = '"+fac.trim()+"'";
		try {
			Query query = em.createNativeQuery(sql, ConsultaFactConifaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
