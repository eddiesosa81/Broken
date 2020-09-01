package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaPolizaView;
import confia.entidades.vistas.PagosCabView;

@Stateless
public class ServicioPagosCabView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PagosCabView> consultaPagosCab(String cliente,String aseg,String usuario) {
		List<PagosCabView> resultado;
		String sql = "select * from pagos_cab_view where cd_cliente like '%"+cliente
				+"%' and cd_aseguradora like '%"+aseg+"%' and usrid like '%"+usuario+"%' order by cd_pago DESC";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PagosCabView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
