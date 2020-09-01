package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Aseguradoras;


@Stateless
public class ServicioAseguradoras {
	@PersistenceContext
	private EntityManager em;
	
	public Aseguradoras aseguradorasId(String codAseg) 
	{
		String sql = "select * from aseguradora_tbl where cd_aseguradora = "+codAseg;
		try
		{
			Query query = em.createNativeQuery(sql,Aseguradoras.class);
			
			Aseguradoras resultado = new Aseguradoras(); 
			resultado = (Aseguradoras) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Aseguradoras> listaAseguradoras() 
	{
		List<Aseguradoras> resultado;  
		String sql = "select * from aseguradora_tbl order by RAZON_SOCIAL_ASEGURADORA";
		try
		{
			Query query = em.createNativeQuery(sql,Aseguradoras.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Aseguradoras> BuscaAseguradoras() 
	{
		List<Aseguradoras> resultado;  
		String sql = "select * from aseguradora_tbl order by RAZON_SOCIAL_ASEGURADORA";
		try
		{
			Query query = em.createNativeQuery(sql,Aseguradoras.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public void actualizaAseguradoras(Aseguradoras col) {
		String resultados;
		resultados = col.getRuc_aseguradora();
		col.setRuc_aseguradora(resultados);
		resultados = col.getRazon_social_aseguradora();
		col.setRazon_social_aseguradora(resultados);
		resultados = col.getNombre_corto_aseguradora();
		col.setNombre_corto_aseguradora(resultados);
		em.merge(col);
	}
	
	public int insertarAseguradoras(Aseguradoras col) {
		try {
			em.persist(col);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
}
