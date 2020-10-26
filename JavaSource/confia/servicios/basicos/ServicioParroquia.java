package confia.servicios.basicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Parroquia;

@Stateless
public class ServicioParroquia {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Parroquia> listaParroquia(String codCanton)
	{
		List<Parroquia> resultado = new ArrayList<Parroquia>();
		String sql = "select * from parroquia_tbl where cd_canton = "+codCanton+" order by desc_parroquia";
		System.out.println("SQL ->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Parroquia.class);
			resultado = query.getResultList();
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

}
