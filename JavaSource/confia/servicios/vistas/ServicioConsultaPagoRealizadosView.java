package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaPagoRealizadosView;

@Stateless
public class ServicioConsultaPagoRealizadosView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaPagoRealizadosView> consultaPagoCodFrmPAgo(String codDetPago) {
		List<ConsultaPagoRealizadosView> resultado;
		String sql = "select * from consulta_pagos_realizados_view where cd_det_forma_pago = " + codDetPago ;
		try {
			Query query = em.createNativeQuery(sql, ConsultaPagoRealizadosView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaPagoRealizadosView> consultaPagoAseg(String codAseg,String fcDesde,String fcHasta) {
		List<ConsultaPagoRealizadosView> resultado;
		String sql = "select * from consulta_pagos_realizados_view where cd_aseguradora = " + codAseg
				+" and fechajuliana_func(to_char(fecha_pago,'dd/mm/yyyy')) >= fechajuliana_func('"+fcDesde+"')"
				+" and fechajuliana_func(to_char(fecha_pago,'dd/mm/yyyy')) <= fechajuliana_func('"+fcHasta+"')"
				+" and nvl(num_recibo,0) = 0" ;
		try {
			Query query = em.createNativeQuery(sql, ConsultaPagoRealizadosView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ConsultaPagoRealizadosView> consultaliquidacionesGen(String codAseg,String fcDesde,String fcHasta,String numRecibo) {
		List<ConsultaPagoRealizadosView> resultado;
		String sql = "select * from consulta_pagos_realizados_view where cd_aseguradora = " + codAseg
				+" and fechajuliana_func(to_char(fecha_pago,'dd/mm/yyyy')) >= fechajuliana_func('"+fcDesde+"')"
				+" and fechajuliana_func(to_char(fecha_pago,'dd/mm/yyyy')) <= fechajuliana_func('"+fcHasta+"')"
				+" and nvl(num_recibo,'*') <> '*'"
				+" and num_recibo like '%"+numRecibo+"%'" ;
		try {
			Query query = em.createNativeQuery(sql, ConsultaPagoRealizadosView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	

}
