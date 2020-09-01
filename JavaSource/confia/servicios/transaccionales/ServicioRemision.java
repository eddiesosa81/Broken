/**
 * 
 */
package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Remision;

/**
 * @author Guido Guerrero
 *
 */

@Stateless
public class ServicioRemision {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Remision> listaRemision() 
	{
		List<Remision> resultado;  
		String sql = "select * from REMISION_TBL";
		try
		{
			Query query = em.createNativeQuery(sql,Remision.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Remision obtieneRemision(Long cdRemision) 
	{ 
		String sql = "select * from REMISION_TBL where cd_remision = :cdRemision";
		try
		{
			Query query = em.createNativeQuery(sql,Remision.class);
			query.setParameter("cdRemision", cdRemision);
			
			return (Remision) query.getSingleResult();
		}
		catch(NoResultException ex)
		{
			return new Remision();
		}
	}
	
	public Boolean guardaRemision(Remision rem) {
		try {
			if(rem.getCd_remision() == null) {
				em.persist(rem);
				
			}
			else
			{
				em.merge(rem);
			}
			return true;
		}
		catch(Exception ex) {
			System.out.println(rem.toString());
			return false;
		}
	}
}
