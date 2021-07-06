/**
 * 
 */
package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Factura;
import confia.entidades.transaccionales.Gestion;

@Stateless
public class ServicioGestion {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Gestion> recuperaGestionPoliza(String cdCotizacion) {
		String sql = "select * from gestion_tbl where cd_cotizacion = "+cdCotizacion+" order by fecha_contacto desc";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Gestion.class);
		 return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Gestion> recuperaGestionSiniestro(Integer cdSiniestro) {
		String sql = "select * from gestion_tbl where CD_SINIESTRO = "+cdSiniestro+" order by fecha_contacto desc";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Gestion.class);
		 return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Gestion> recuperaGestionSiniesGen() {
		String sql = "select * from gestion_tbl where tipo = 'SINIESTRO' order by fecha_contacto desc";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Gestion.class);
		 return q.getResultList();
	}
	
	public Integer insertarGestion(Gestion obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}

	public void actualizaGestion(Gestion obj) {
		em.merge(obj);
	}

	public void eliminaGestion(Gestion obj) {
		em.remove(em.merge(obj));
	}

}
