package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.DiagnosticoReclamo;

@Stateless
public class ServicioDiagnosticoReclamo {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DiagnosticoReclamo> consultaDiagnosticoReclamo() {
		String sql = "select * from diagnostico_reclamo_tbl order by diagnistico ";
		try {
			Query query = em.createNativeQuery(sql, DiagnosticoReclamo.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<DiagnosticoReclamo> consultaDiagnosticoReclamo(String codSiniestro) {
		String sql = "select * from diagnostico_reclamo_tbl where cd_siniestro =  "+codSiniestro;
		try {
			Query query = em.createNativeQuery(sql, DiagnosticoReclamo.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	
		
	public Integer insertarDiagnosticoReclamo(DiagnosticoReclamo obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaDiagnosticoReclamo(DiagnosticoReclamo obj) {
		em.merge(obj);
	}

	public void eliminaDiagnosticoReclamo(DiagnosticoReclamo obj) {
		em.remove(em.merge(obj));
	}


}
