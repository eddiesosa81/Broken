package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ReporteCuotasIniImpaga;
import confia.entidades.vistas.ReporteGestionSiniestrosView;

@Stateless
public class ServicioReporteGestionSiniestrosView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ReporteGestionSiniestrosView> recuperaReporteGestionSinies(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,String tipo) {
		String sql;
			sql = "select * from REPORTE_GESTION_SINIESTRO_VIEW "
				+ "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" 
				+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
				+ " and to_char(cd_ramo) like '" + codRamo + "'" 
				+ " and to_char(cd_canal) like '"+ codSubagen + "'" 
				+ " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and fechajuliana_func(to_char(FECHA_CONTACTO,'dd/MM/yyyy')) >= fechajuliana_func('" + fcDesde + "')"
				+ " and fechajuliana_func(to_char(FECHA_CONTACTO,'dd/MM/yyyy')) <= fechajuliana_func('" + fcHasta + "')";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ReporteGestionSiniestrosView.class);
		return q.getResultList();
	}

}
