/**
 * 
 */
package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.ComisionPoliza;
import confia.entidades.transaccionales.DetalleFormaPago;

/**
 * @author Guido Guerrero
 *
 */

@Stateless
public class ServicioComisionPoliza {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ComisionPoliza> recuperaComisionPol(int cdRamoCot) {
		String sql = "select * from comision_poliza_tbl where cd_ramo_cotizacion = "+cdRamoCot;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, ComisionPoliza.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<ComisionPoliza> recuperaComisionPolDetPag(int cdDetFrmPag) {
		String sql = "select * from comision_poliza_tbl where cd_det_forma_pago = "+cdDetFrmPag;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, ComisionPoliza.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<ComisionPoliza> lstComisionPoliza() {
		String sql = "Select cp from ComisionPoliza cp where cp.flg_pre_factura is null and cp.flg_factura is null";
		
		Query query = em.createQuery(sql);
		
		return query.getResultList();
	}
	
	public Boolean guardaComisionPoliza(ComisionPoliza compo) {
		ComisionPoliza cp = new ComisionPoliza();
		
		String sql = "select * from COMISION_POLIZA_TBL where CD_RAMO_COTIZACION = :cd_ramoCotizacion";
		
		Query query = em.createQuery(sql);
		query.setParameter("cd_ramoCotizacion", compo.getCd_ramo_cotizacion());
		
		try {
			cp = (ComisionPoliza) query.getSingleResult();
			if(cp == null) {
				em.persist(compo);
				return true;
			}
			else {
				return true;
			}
		}
		catch(NoResultException ex){
			return false;
		}
		
		
	}
	
	public Boolean actualizaComisionPoliza(ComisionPoliza compo) {
		em.merge(compo);
		return true;
	}
}
