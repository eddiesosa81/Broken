package confia.servicios.basicos;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Ciudad;

@Stateless
public class ServicioCiudad {
	@PersistenceContext
	private EntityManager em;
	
	public Ciudad recuperaCiudad(String cdCiudad ) {
		String sql = "select * from ciudad_tbl where cd_ciudad = "+cdCiudad;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Ciudad.class);
		 return (Ciudad) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ciudad> recuperaListaCiudad( ) {
		String sql = "select * from ciudad_tbl order by nm_ciudad";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Ciudad.class);
		 return q.getResultList();
		 
	}
	
	public Integer insertarCiudad(Ciudad obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	

	public void actualizaCiudad(Ciudad obj) {
		em.merge(obj);
	}

	public void eliminaCiudad(Ciudad obj) {
		em.remove(em.merge(obj));
	}

}
