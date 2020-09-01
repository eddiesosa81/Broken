package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Marca;

@Stateless
public class ServicioMarca {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Marca> listaMarca()
	{
		List<Marca> resultado;
		
		String sql = "select * from marca_tbl where estado_marca = 'A' order by desc_marca";
		try
		{
			Query query = em.createNativeQuery(sql,Marca.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
		
	}
	
	public Marca buscaMarcaXId(Integer cd_marca)
	{
		Marca resultado;
		
		String sql = "select * from marca_tbl where estado_marca = 'A' and cd_marca = :cd_marca order by desc_marca";
		try
		{
			Query query = em.createNativeQuery(sql,Marca.class);
			query.setParameter("cd_marca", cd_marca);
			
			resultado = (Marca) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
		
		
	}
	
	
}
