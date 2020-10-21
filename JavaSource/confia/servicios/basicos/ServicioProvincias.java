package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Provincias;

@Stateless
public class ServicioProvincias {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Provincias> listaProvincias()
	{
		List<Provincias> resultado;
		String sql = "SELECT * FROM provincia_tbl ";
		try
		{
			Query query = em.createNativeQuery(sql,Provincias.class);
			resultado = query.getResultList();
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
}
