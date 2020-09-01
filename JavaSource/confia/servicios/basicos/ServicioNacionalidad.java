package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Nacionalidad;

@Stateless
public class ServicioNacionalidad {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Nacionalidad> recuperaListaNacionalidad( ) {
		String sql = "select * from nacionalidad_tbl order by nacponderacion";
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Nacionalidad.class);
		 return q.getResultList();
		 
	}

}
