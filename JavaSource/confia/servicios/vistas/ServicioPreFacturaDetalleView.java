package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.PreFacturaDetalleView;

@Stateless
public class ServicioPreFacturaDetalleView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PreFacturaDetalleView> lstPreFacturaDetalleView(Integer cdPreFac) {
		String sql = "select * from prefacturadetalle_view where cd_pre_factura = " + cdPreFac;
		System.out.println("SQL --->" + sql);
		Query query = em.createNativeQuery(sql, PreFacturaDetalleView.class);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PreFacturaDetalleView> lstPreFacturaDetalleViewLiq(String codClie, String codAseg, String lscdRamo,
			String fcDesde, String fcHasta) {
		String sql = "select * from prefacturadetalle_view" 
				+ " where to_char(cd_cliente) like '" + codClie + "'"
				+ " and to_char(cd_aseguradora) like '" + codAseg + "'" 
				+ " and to_char(cd_ramo) like '" + lscdRamo+ "'" 
				+ " and fechajuliana_func(fc_pre_factura) >= fechajuliana_func('" + fcDesde + "')"
				+ " and fechajuliana_func(fc_pre_factura) <= fechajuliana_func('" + fcHasta + "')";
		System.out.println("SQL --->" + sql);
		Query query = em.createNativeQuery(sql, PreFacturaDetalleView.class);

		return query.getResultList();
	}

}
