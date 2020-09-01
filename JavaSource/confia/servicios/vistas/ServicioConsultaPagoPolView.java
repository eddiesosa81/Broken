package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaPagoPolView;

@Stateless
public class ServicioConsultaPagoPolView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaPagoPolView> consultaPagoPolXCdCot(String cdCot) {
		List<ConsultaPagoPolView> resultado;
		String sql = "select * from consulta_pago_pol_view where cd_cotizacion = "+cdCot+" order by cd_det_forma_pago,tipo";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPagoPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaPagoPolView> consultaPagoPolAnexoXCdCot(String cdCot) {
		List<ConsultaPagoPolView> resultado;
		String sql = "select * from consulta_pago_ane_view where cd_cotizacion = "+cdCot+" order by cd_det_forma_pago,tipo";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPagoPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
}
