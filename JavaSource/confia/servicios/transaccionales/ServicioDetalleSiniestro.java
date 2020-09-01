package confia.servicios.transaccionales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.DetalleSiniestros;
import confia.entidades.transaccionales.Siniestros;

@Stateless
public class ServicioDetalleSiniestro {
	
	@PersistenceContext
	private EntityManager em;
	
	public DetalleSiniestros recuperaDetSiniestrosxCdSini(int cdsinies, int cdCompania) {
		String sql = "select * from detalle_siniestros_tbl where cd_siniestro = "+cdsinies
				+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, DetalleSiniestros.class);
		 return (DetalleSiniestros) q.getSingleResult();
		 
	}
	
	public Integer insertarDetalleSiniestros(DetalleSiniestros obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaDetalleSiniestros(DetalleSiniestros obj) {
		em.merge(obj);
	}

	public void eliminaDetalleSiniestros(DetalleSiniestros obj) {
		em.remove(em.merge(obj));
	}

}
