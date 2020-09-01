package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ProduccionPagadaView;

@Stateless
public class ServicioProduccionPagadaView {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProduccionPagadaView> recuperaProduccionPagadaView(String codClie, String codGrpCont, String codAseg,
			String codRamo, String codSubagen, String codEjec, String fcDesde, String fcHasta,String tipo) {
		String sql = "select * from produccion_pagada_view " + "where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_grupo_contratante) like '" + codGrpCont + "'" + " and to_char(cd_aseguradora) like '"
				+ codAseg + "'" + " and to_char(cd_ramo) like '" + codRamo + "'" + " and to_char(cd_subagente) like '"
				+ codSubagen + "'" + " and to_char(cd_ejecutivo) like '" + codEjec + "'"
				+ " and fecha_pago_jul >= fechajuliana_func('" + fcDesde + "')"
				+ " and fecha_pago_jul <= fechajuliana_func('" + fcHasta + "')"
				+ " and facturacion_periodica like '"+tipo+"'";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ProduccionPagadaView.class);
		return q.getResultList();
	}
}
