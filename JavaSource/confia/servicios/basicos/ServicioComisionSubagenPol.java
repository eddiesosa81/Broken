package confia.servicios.basicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.ComisionSubagentePol;
import confia.entidades.transaccionales.ComisionesPoliza;

@Stateless
public class ServicioComisionSubagenPol {

	@PersistenceContext
	private EntityManager em;

	public ComisionSubagentePol consultaSubagentePol(Integer cod_comisuba_pol) {
		String sql = "select * from comision_subagente_pol_tbl where cd_comisuba_pol =" + cod_comisuba_pol;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ComisionSubagentePol.class);
		return (ComisionSubagentePol) q.getSingleResult();
	}
	
	public ComisionSubagentePol consultaSubagentePolAsisMed(Integer codFrmPago) {
		String sql = "select * from comision_subagente_pol_tbl "
				+ "where cd_comision_poliza in ( select cd_comision_poliza 	from comision_poliza_tbl "
											 + " where cd_det_forma_pago in (select min(cd_det_forma_pago)"
											 								+ " from detalle_forma_pago_tbl "
											 								+ " where cd_forma_pago = "+codFrmPago+" ))";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, ComisionSubagentePol.class);
		return (ComisionSubagentePol) q.getSingleResult();
	}
	
	public Integer insertarComisionSubagentePol(ComisionSubagentePol obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public void actualizaComisionSubagentePol(ComisionSubagentePol obj) {
		em.merge(obj);
	}

}
