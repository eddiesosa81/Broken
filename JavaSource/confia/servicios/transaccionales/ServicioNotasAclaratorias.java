package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.NotasAclaratorias;

@Stateless
public class ServicioNotasAclaratorias {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<NotasAclaratorias> consultaNotasAclaratorias(String cdRamoCot) {
		List<NotasAclaratorias> resultado;
		String sql = "select * from notas_aclaratorias_tbl where cd_ramo_cotizacion = "+cdRamoCot;
		try {
			Query query = em.createNativeQuery(sql, NotasAclaratorias.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public Integer insertarNotaAclaratoria(NotasAclaratorias obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return 0;
		}

		return 1;
	}

	public Integer actualizaNotasAclaratorias(NotasAclaratorias obj) {
		try {
			em.merge(obj);
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			return 0;
		}
		return 1;
	}

	public void eliminaNotasAclaratorias(NotasAclaratorias obj) {
		em.remove(em.merge(obj));
	}

}
