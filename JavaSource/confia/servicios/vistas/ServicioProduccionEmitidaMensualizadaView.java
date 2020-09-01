package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ProduccionEmitidaMensualizadoView;
import confia.entidades.vistas.ProduccionEmitidaView;

@Stateless
public class ServicioProduccionEmitidaMensualizadaView {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProduccionEmitidaMensualizadoView> recuperaPRoduccionEmitidaMensualizada(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,String tipo) {
		String sql;
			sql = "select * from prod_emitida_mensualizado_view "
					+ "where to_char(cd_cliente) like '" + codClie + "'"
					+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" 
					+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
					+ " and to_char(cd_ramo) like '" + codRamo + "'" 
					+ " and to_char(cd_subagente) like '"+ codSubagen + "'" 
					+ " and to_char(cd_ejecutivo) like '" + codEjec + "'"
					+ " and fc_desde_jul >= fechajuliana_func('" + fcDesde + "')"
					+ " and fc_desde_jul <= fechajuliana_func('" + fcHasta + "')";
			
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ProduccionEmitidaMensualizadoView.class);
		return q.getResultList();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<ProduccionEmitidaView> recuperaAnexosEmitidosPoliza(String codClie, String codAseg, String codRamo,String poliza) {
//		String sql = "select * from produccion_emitida_view "
//				+ "where to_char(cd_cliente) like '" + codClie + "'"
//				+ " and to_char(cd_aseguradora) like '"+ codAseg + "'" 
//				+ " and to_char(cd_ramo) like '" + codRamo + "'" 
//				+ " and to_char(poliza) like '"+ poliza + "' and cd_rubro not in(8,9)" ;
//		System.out.println("********************-----QUERY: " + sql);
//		Query q = em.createNativeQuery(sql, ProduccionEmitidaView.class);
//		return q.getResultList();
//	}
}
