package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ReporteVencimientoView;

@Stateless
public class ServicioReporteVencimientoView {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ReporteVencimientoView> recuperaVencimiento(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta, String tipo) {
		String sql;
		
		sql = "select * from reporte_vencimientos_view " + "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" + " and to_char(cd_aseguradora) like '"
				+ codAseg + "'" + " and to_char(cd_ramo) like '" + codRamo + "'" + " and to_char(cd_subagente) like '"
				+ codSubagen + "'" + " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and fc_hasta_jul >= fechajuliana_func('" + fcDesde + "')"
				+ " and fc_hasta_jul <= fechajuliana_func('" + fcHasta + "')" + " and mensualizado = " + tipo;
		
		System.out.println("--QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ReporteVencimientoView.class);
		return q.getResultList();
	}

}
