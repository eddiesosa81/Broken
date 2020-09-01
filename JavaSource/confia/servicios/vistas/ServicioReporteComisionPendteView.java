package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ReporteComisionPendteView;

@Stateless
public class ServicioReporteComisionPendteView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ReporteComisionPendteView> recuperaReporteComisionesPendientes(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,String tipo) {
		String sql;
			sql = "select * from reporte_comision_pendte_view "
				+ "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" 
				+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
				+ " and to_char(cd_ramo) like '" + codRamo + "'" 
				+ " and to_char(cd_subagente) like '"+ codSubagen + "'" 
				+ " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and fc_desde_jul >= fechajuliana_func('" + fcDesde + "')"
				+ " and fc_hasta_jul <= fechajuliana_func('" + fcHasta + "')";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ReporteComisionPendteView.class);
		return q.getResultList();
	}
}
