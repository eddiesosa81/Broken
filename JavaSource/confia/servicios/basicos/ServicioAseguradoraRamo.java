package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.AseguradoraRamo;

@Stateless
public class ServicioAseguradoraRamo {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<AseguradoraRamo> listaAseguradoraRamos(int cd_aseguradora) 
	{
		List<AseguradoraRamo> resultado;  
		String sql = "select * from aseguradora_ramo_view where cd_aseguradora = :cd_aseguradora order by desc_ramo desc";
		try
		{
			Query query = em.createNativeQuery(sql,AseguradoraRamo.class);
			query.setParameter("cd_aseguradora", cd_aseguradora);
			System.out.println("Aseguradora "+cd_aseguradora);
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<AseguradoraRamo> listaAseguradoraRamos(int cd_aseguradora, int cd_ramo) 
	{
		List<AseguradoraRamo> resultado;  
		String sql = "select * from aseguradora_ramo_view where cd_aseguradora = :cd_aseguradora and cd_ramo = :cd_ramo order by desc_ramo desc";
		try
		{
			Query query = em.createNativeQuery(sql,AseguradoraRamo.class);
			query.setParameter("cd_aseguradora", cd_aseguradora);
			query.setParameter("cd_ramo", cd_ramo);
			
			System.out.println("cd_aseguradora "+cd_aseguradora);
			System.out.println("cd_ramo "+cd_ramo);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
