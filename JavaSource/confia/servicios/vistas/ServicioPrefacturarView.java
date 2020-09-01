/**
 * 
 */
package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.PrefacturarView;

@Stateless
public class ServicioPrefacturarView {


	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<PrefacturarView> lstPrefacturarView(String cdAseg) {
		String sql = "select * from PREFACTURAR_VIEW where flg_saldar_centavo = 0 and flg_factura = 0 "
				+ "and cd_aseguradora = "+cdAseg;
		System.out.println("SQL --->"+sql);
		Query query = em.createNativeQuery(sql,PrefacturarView.class);
		
		return query.getResultList();
	}
	
	
	
}
