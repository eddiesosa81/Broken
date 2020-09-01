package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ComisionesSubagentesView;

@Stateless
public class ServicioComisionesSubagentesView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ComisionesSubagentesView> consultaComisionesSubagentesView(Integer cdSubagen) {
		List<ComisionesSubagentesView> resultado;
		String sql = "select * from COMISIONES_SUBAGENTE_VIEW where cd_subagente = "+cdSubagen
				+" order by cd_aseguradora,cd_ramo,cd_grupo_contratante,cd_plan";
		try {
			Query query = em.createNativeQuery(sql, ComisionesSubagentesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}


}
