package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.FacturaDetalle;
import confia.entidades.transaccionales.PreFacturaDetalle;

@Stateless
public class ServicioFacturaDetalle {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<FacturaDetalle> recuperaFacturaDetalle(int cdPreFact) {
		String sql = "select * from detalle_factura_tbl where cd_pre_factura = "+cdPreFact;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, FacturaDetalle.class);
		 return q.getResultList();
	}
	
	public Integer insertarFacturaDetalle(FacturaDetalle obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public void actualizaFacturaDetalle(FacturaDetalle obj) {
		em.merge(obj);
	}

	public void eliminaFacturaDetalle(FacturaDetalle obj) {
		em.remove(em.merge(obj));
	}

}
