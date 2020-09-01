package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.FormaPago;

@Stateless
public class ServicioDetalleFormaPago {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DetalleFormaPago> recuperaDetalleCodFormaPago(int cdFrmP, int cdCompania) {
		String sql = "select * from detalle_forma_pago_tbl where cd_forma_pago = "+cdFrmP+" and cd_compania = "+cdCompania+" order by cd_det_forma_pago";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, DetalleFormaPago.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<DetalleFormaPago> recuperaDetalleFrmPago(String cdCotizacion, String cdCompania) {
		String sql = "select * from detalle_forma_pago_tbl where cd_compania = "+cdCompania+" and cd_forma_pago in ( "
				+ "select distinct cd_forma_pago from forma_pago_tbl where cd_cotizacion =  "+cdCotizacion+") order by cd_det_forma_pago";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, DetalleFormaPago.class);
		 return q.getResultList();
	}
	
	public DetalleFormaPago recuperaDetallexCdFrmPago(int cdDetFrmP, int cdCompania) {
		String sql = "select * from detalle_forma_pago_tbl where cd_det_forma_pago = "+cdDetFrmP+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, DetalleFormaPago.class);
		 return (DetalleFormaPago) q.getSingleResult();
	}
	
	public DetalleFormaPago recuperaDetalleUltimoReg(Integer cdFrmP, Integer cdCompania) {
		String sql = "select * from detalle_forma_pago_tbl where cd_det_forma_pago in(select max(cd_det_forma_pago) "
																					+ " from detalle_forma_pago_tbl "
																					+ " where cd_forma_pago = "+cdFrmP
																					+ " and cd_compania ="+cdCompania+")";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, DetalleFormaPago.class);
		 return (DetalleFormaPago) q.getSingleResult();
	}
	
	public void actualizaDetFormaPago(DetalleFormaPago obj) {
		em.merge(obj);
	}
	public Integer insertaDetalleFormaPago(DetalleFormaPago obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
}
