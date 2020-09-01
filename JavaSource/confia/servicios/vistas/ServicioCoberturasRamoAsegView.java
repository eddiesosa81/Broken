package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.CoberturasRamoAsegView;

@Stateless
public class ServicioCoberturasRamoAsegView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CoberturasRamoAsegView> consultaCoberturasNegocio(Integer cdPlan, Integer codAseg) {
		List<CoberturasRamoAsegView> resultado;
		String sql = "select * from COBERTURA_RAMO_ASEG_VIEW where cd_plan = "+cdPlan
				+" and cd_aseguradora = "+codAseg+" order by desc_cobertura";
		try {
			Query query = em.createNativeQuery(sql, CoberturasRamoAsegView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
