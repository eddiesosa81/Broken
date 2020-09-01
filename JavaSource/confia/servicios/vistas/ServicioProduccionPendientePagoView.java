package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ProduccionPendientePagoView;

@Stateless
public class ServicioProduccionPendientePagoView {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProduccionPendientePagoView> recuperaProduccionPendientePago(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,String tipo) {
		String sql;
		if (tipo.equals("1")) {
			sql = "select * from produccion_pendiente_pago_view "
				+ "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" 
				+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
				+ " and to_char(cd_ramo) like '" + codRamo + "'" 
				+ " and to_char(cd_subagente) like '"+ codSubagen + "'" 
				+ " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and fc_desde_jul >= fechajuliana_func('" + fcDesde + "')"
				+ " and fc_desde_jul <= fechajuliana_func('" + fcHasta + "')"
				+ " and facturacion_periodica like '"+tipo+"'";
		}else{
			sql = "select * from produccion_pendiente_pago_view "
					+ "where to_char(cd_cliente) like '" + codClie + "'"
					+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" 
					+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
					+ " and to_char(cd_ramo) like '" + codRamo + "'" 
					+ " and to_char(cd_subagente) like '"+ codSubagen + "'" 
					+ " and to_char(cd_ejecutivo) like '" + codEjec + "'"
					+ " and fc_emision_jul >= fechajuliana_func('" + fcDesde + "')"
					+ " and fc_emision_jul <= fechajuliana_func('" + fcHasta + "')"
					+ " and facturacion_periodica like '"+tipo+"'";
		}
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ProduccionPendientePagoView.class);
		return q.getResultList();
	}
}
