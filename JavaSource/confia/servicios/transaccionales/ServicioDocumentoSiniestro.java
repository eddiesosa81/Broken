package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.CoberturasSiniestro;
import confia.entidades.transaccionales.DocumentoSiniestro;

@Stateless
public class ServicioDocumentoSiniestro {
	@PersistenceContext
	private EntityManager em;
	
//	public Siniestros recuperaSiniestros(int cdramcot, int cdCompania) {
//		String sql = "select * from ramo_cotizacion_tbl where cd_ramo_cotizacion = "+cdramcot+" and cd_compania = "+cdCompania;
//		 System.out.println("********************-----QUERY: " + sql);
//		 Query q = em.createNativeQuery(sql, Siniestros.class);
//		 return (Siniestros) q.getSingleResult();
//	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentoSiniestro> consultaDocumentosSiniestro(String cdSiniestro) {
		List<DocumentoSiniestro> resultado;
		String sql = "select * from documentos_siniestro_tbl where cd_siniestro = "+cdSiniestro;
		try {
			Query query = em.createNativeQuery(sql, DocumentoSiniestro.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public Integer insertarDocumentoSiniestro(DocumentoSiniestro obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaDocumentoSiniestro(DocumentoSiniestro obj) {
		em.merge(obj);
	}

	public void eliminaDocumentoSiniestro(DocumentoSiniestro obj) {
		em.remove(em.merge(obj));
	}

}
