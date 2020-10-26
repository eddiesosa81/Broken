package confia.servicios.basicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Canton;

@Stateless
public class ServicioCanton {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Canton> listaCantones(String codProvincia)
	{
		List<Canton> resultado = new ArrayList<Canton>();
		String sql = "select * from canton_tbl where cd_provincia = "+codProvincia+" order by desc_canton";
		System.out.println("SQL ->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Canton.class);
			resultado = query.getResultList();
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

}
