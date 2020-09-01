package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Rubros;
import confia.entidades.transaccionales.ProformaSiniestro;

@Stateless
public class ServicioProformaSiniestro {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProformaSiniestro> listadoProformaSiniestro(String codSinies) {
		 String sql = "select * from proforma_Siniestro_tbl where cd_siniestro = "+codSinies;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, ProformaSiniestro.class);
		 return q.getResultList();
		
	}

	public Integer insertarProformaSiniestro(ProformaSiniestro obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaProformaSiniestro(ProformaSiniestro obj) {
		em.merge(obj);
	}

	public void eliminaProformaSiniestro(ProformaSiniestro obj) {
		em.remove(em.merge(obj));
	}

}
