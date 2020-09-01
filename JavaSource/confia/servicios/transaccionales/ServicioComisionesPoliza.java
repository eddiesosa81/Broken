package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.ComisionesPoliza;

@Stateless
public class ServicioComisionesPoliza {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ComisionesPoliza> consultaComisionesPoliza(String cdReserva) {
		List<ComisionesPoliza> resultado;
		String sql = "select * from comision_poliza_tbl where cd_reserva = "+cdReserva;
		try {
			Query query = em.createNativeQuery(sql, ComisionesPoliza.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public ComisionesPoliza comisionesPolizaxCod(Integer cdComPol) {
		String sql = "select * from comision_poliza_tbl where cd_comision_poliza = "+cdComPol;
		try {
			Query query = em.createNativeQuery(sql, ComisionesPoliza.class);
			return (ComisionesPoliza) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public ComisionesPoliza comisionesPolizaAsisMed(Integer cdCotizacion) {
		String sql = "select * from comision_poliza_tbl "
				+ "where cd_det_forma_pago in ( select min(cd_det_forma_pago) "
											+ "from detalle_forma_pago_tbl "
											+ "where cd_forma_pago in( select cd_forma_pago "
																	+ "	from forma_pago_tbl "
																	+ "where cd_cotizacion = "+cdCotizacion+"))";
		try {
			Query query = em.createNativeQuery(sql, ComisionesPoliza.class);
			return (ComisionesPoliza) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	 

	public ComisionesPoliza comisionesPolizaxCdDetFrmPag(Integer cdDetFormaPago) {
		String sql = "select * from comision_poliza_tbl where cd_det_forma_pago = "+cdDetFormaPago;
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComisionesPoliza.class);
			return (ComisionesPoliza) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public Integer insertarComisionesPoliza(ComisionesPoliza obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaComisionesPoliza(ComisionesPoliza obj) {
		em.merge(obj);
	}

	public void eliminaComisionesPoliza(ComisionesPoliza obj) {
		em.remove(em.merge(obj));
	}

}
