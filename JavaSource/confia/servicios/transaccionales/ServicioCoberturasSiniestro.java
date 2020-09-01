package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.CoberturasSiniestro;

@Stateless
public class ServicioCoberturasSiniestro {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<CoberturasSiniestro> consultaCoberturasSiniestro(String cdSiniestro) {
		List<CoberturasSiniestro> resultado;
		String sql = "select * from cobertura_siniestro_tbl where cd_siniestro = "+cdSiniestro;
		try {
			Query query = em.createNativeQuery(sql, CoberturasSiniestro.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public Integer insertarCoberturasSiniestro(CoberturasSiniestro obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaCoberturasSiniestro(CoberturasSiniestro obj) {
		em.merge(obj);
	}

	public void eliminaCoberturasSiniestro(CoberturasSiniestro obj) {
		em.remove(em.merge(obj));
	}

}
