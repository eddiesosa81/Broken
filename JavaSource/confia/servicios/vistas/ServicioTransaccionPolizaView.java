package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.TransaccionPolizaView;

@Stateless
public class ServicioTransaccionPolizaView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<TransaccionPolizaView> recuperaTransaccionesPol(String codRamCot) {
		String sql;
			sql = "select * from transacciones_pol_view "
				+ "where cd_ramo_cotizacion = "+codRamCot+" order by cd_ramo_cotizacion,cd_ubicacion,cd_obj_cotizacion,cd_subobj_cotizacion";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, TransaccionPolizaView.class);
		return q.getResultList();
	}

}
