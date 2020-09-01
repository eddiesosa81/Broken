package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Dispositivos;

@Stateless
public class ServicioDispositivos {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Dispositivos> listaDispositivos()
	{
		List<Dispositivos> resultado;
		String sql = "select * from dispositivo_tbl where estado_dispositivo = 'A'";
		
		try
		{
			Query query = em.createNativeQuery(sql,Dispositivos.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
