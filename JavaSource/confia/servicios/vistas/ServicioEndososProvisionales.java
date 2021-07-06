package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.EndososProvisionalesView;

@Stateless
public class ServicioEndososProvisionales {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<EndososProvisionalesView> endososProvisionalesCons(String codClie,String descRubro,String pol) {
		List<EndososProvisionalesView> resultado;
		String sql = "select * from ENDOSOS_PROVISIONALES_VIEW where DESC_RUBRO = '"+descRubro
				+"' and cd_cliente="+codClie
				+" and poliza like '%"+pol+"%'";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, EndososProvisionalesView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	
}
