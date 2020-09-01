package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Ejecutivos;

@Stateless
public class ServicioEjecutivos {
	@PersistenceContext
	private EntityManager em;
	
	public Ejecutivos ejecutivoId(String codEje) 
	{
		Ejecutivos resultado;  
		String sql = "select * from ejecutivo_tbl where estado_ejecutivo = 'A' and cd_ejecutivo = "+codEje;
		try
		{
			Query query = em.createNativeQuery(sql,Ejecutivos.class);
			
			resultado = (Ejecutivos) query.getSingleResult();
			
			/*for (Ejecutivos aseguradoraRamo : resultado) {
				System.out.println(aseguradoraRamo.getPrimer_apellido_ejecutivo() + " - " + aseguradoraRamo.getCd_ejecutivo());
			}*/
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ejecutivos> listaEjecutivos() 
	{
		List<Ejecutivos> resultado;  
		String sql = "select * from ejecutivo_tbl where estado_ejecutivo = 'A' order by primer_apellido_ejecutivo,segundo_apellido_ejecutivo,primer_nombre_ejecutivo,segundo_nombre_ejecutivo";
		try
		{
			Query query = em.createNativeQuery(sql,Ejecutivos.class);
			
			resultado = query.getResultList();
			
			/*for (Ejecutivos aseguradoraRamo : resultado) {
				System.out.println(aseguradoraRamo.getPrimer_apellido_ejecutivo() + " - " + aseguradoraRamo.getCd_ejecutivo());
			}*/
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
