package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.MensualComercial;

@Stateless
public class ServicioMensualComercial {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<MensualComercial> recuperaMensualComercial(String cdCotizacion) {
		String sql = "select * from gestion_tbl where cd_cotizacion = "+cdCotizacion+" order by fecha_contacto desc";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, MensualComercial.class);
		 return q.getResultList();
	}
	
	public Integer insertarMensualComercial(MensualComercial obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}

	public void actualizaMensualComercial(MensualComercial obj) {
		em.merge(obj);
	}

	public void eliminaMensualComercial(MensualComercial obj) {
		em.remove(em.merge(obj));
	}


}
