package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.liquidacionCanalPolView;

@Stateless
public class ServicioLiquidacionCanalPolView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<liquidacionCanalPolView> consultaLiquidacionCanalView(String numFactura) {
		List<liquidacionCanalPolView> resultado;
		String sql = "select * from liquidacion_canal_POL_VIEW "
				+ "WHERE num_factura_canal like '%"+numFactura+"' order by fc_pago_canal";
		System.out.println("QUERY :"+sql);
		try {
			Query query = em.createNativeQuery(sql, liquidacionCanalPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
}
