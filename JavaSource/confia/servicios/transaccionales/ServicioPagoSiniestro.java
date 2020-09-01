package confia.servicios.transaccionales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import confia.entidades.transaccionales.PagoSiniestro;

@Stateless
public class ServicioPagoSiniestro {
	@PersistenceContext
	private EntityManager em;
	
//	public Siniestros recuperaSiniestros(int cdramcot, int cdCompania) {
//		String sql = "select * from ramo_cotizacion_tbl where cd_ramo_cotizacion = "+cdramcot+" and cd_compania = "+cdCompania;
//		 System.out.println("********************-----QUERY: " + sql);
//		 Query q = em.createNativeQuery(sql, Siniestros.class);
//		 return (Siniestros) q.getSingleResult();
//	}
	
	public Integer insertarPagoSiniestro(PagoSiniestro obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaPagoSiniestro(PagoSiniestro obj) {
		em.merge(obj);
	}

	public void eliminaPagoSiniestro(PagoSiniestro obj) {
		em.remove(em.merge(obj));
	}

}
