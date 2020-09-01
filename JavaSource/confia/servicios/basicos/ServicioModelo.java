package confia.servicios.basicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Modelo;

@Stateless
public class ServicioModelo {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Modelo> listaModelo(Integer cd_marca)
	{
		List<Modelo> resultado;

		String sql = "select * from modelo_tbl where estado_modelo = 'A' and cd_marca = :cd_marca order by desc_modelo";
		
		try
		{
			Query query = em.createNativeQuery(sql,Modelo.class);
			query.setParameter("cd_marca", cd_marca);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Modelo buscaModeloXId(Integer cd_modelo)
	{
		Modelo resultado;

		String sql = "select * from modelo_tbl where estado_modelo = 'A' and cd_modelo = :cd_modelo order by desc_modelo";
		try
		{
			Query query = em.createNativeQuery(sql,Modelo.class);
			query.setParameter("cd_modelo", cd_modelo);

			resultado = (Modelo) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public List<Modelo> modelosConsMarca(Integer codMarca) 
	{
		String sql = "select * from modelo_tbl where estado_modelo= 'A' and cd_marca = "+codMarca+"  order by desc_modelo";
		System.out.println("QUERY: -->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Modelo.class);
			
			List<Modelo> resultado = new ArrayList<Modelo>(); 
			return resultado = query.getResultList();
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Integer insertarModelo(Modelo obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaModelo(Modelo obj) {
		em.merge(obj);
	}

	public void eliminaModelo(Modelo obj) {
		em.remove(em.merge(obj));
	}
}
