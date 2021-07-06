package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ReporteComisionPendteView;
import confia.entidades.vistas.ReporteComisionesLiquidadasView;

@Stateless
public class ServicioReporteComisionesLiquidadasView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ReporteComisionesLiquidadasView> recuperaReporteComisionesLiquidadasView(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,String tipo) {
		String sql;
			sql = "select * from reporte_comision_liquida_view "
				+ "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" 
				+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
				+ " and to_char(cd_ramo) like '" + codRamo + "'" 
				+ " and to_char(cd_subagente) like '"+ codSubagen + "'" 
				+ " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and fc_emision_jul >= fechajuliana_func('" + fcDesde + "')"
				+ " and fc_emision_jul <= fechajuliana_func('" + fcHasta + "')";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ReporteComisionesLiquidadasView.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReporteComisionesLiquidadasView> recuperaReporteComisionesLiquidadasViewRep(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,String tipo) {
		String sql;
		sql = "select * from vm_comision_liquida "
				+ "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" 
				+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
				+ " and to_char(cd_ramo) like '" + codRamo + "'" 
				+ " and to_char(cd_subagente) like '"+ codSubagen + "'" 
				+ " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and to_date(fecha_Factura_confia,'dd/mm/yyyy')  between '"+ fcDesde + "' and '"+ fcHasta + "'";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ReporteComisionesLiquidadasView.class);
		return q.getResultList();
	}

}
