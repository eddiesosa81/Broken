package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaPolizaAnexoView;

@Stateless
public class ServicioConsultaPolizaAnexosView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaAnexoView> consultaAnexosXCliente(String codcliente, String poliza,String codRamo) {
		List<ConsultaPolizaAnexoView> resultado;
		String sql = "select * from consulta_anexo_poliza_view where cd_cliente = " + codcliente + " and poliza = '"
		+poliza+"' and cd_ramo = "+codRamo+" and factura_aseguradora = 'PAGOFACTURA'";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaAnexoView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
}
