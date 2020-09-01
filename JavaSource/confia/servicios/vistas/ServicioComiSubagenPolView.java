package confia.servicios.vistas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ComiSubagenPolView;

@Stateless
public class ServicioComiSubagenPolView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ComiSubagenPolView> consultaComiSubagenPolView(String codSuba,String asPol,String asFact,String codAseg) {
		List<ComiSubagenPolView> resultado;
		String sql = "select * from comision_subagente_pol_view WHERE flg_pre_factura = 1 and flg_factura = 1 and flg_pago_canal = 0 "
				+ "and cd_subagente = "+codSuba
				+" and poliza like '"+asPol.trim()+"' and factura_aseguradora like '%"+asFact.trim()+"%' and cd_aseguradora = "+codAseg;
		System.out.println("QUERY :"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComiSubagenPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	public List<ComiSubagenPolView> consultaComiSubagenPolView(String codSuba,String asFact) {
		List<ComiSubagenPolView> resultado;
		String sql = "select * from comision_subagente_pol_view WHERE flg_pre_factura = 1 and flg_factura = 1 and flg_pago_canal = 0"
				+ " and cd_subagente = "+codSuba
				+" and factura_aseguradora like '%"+asFact.trim()+"%'";
		System.out.println("QUERY :"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComiSubagenPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	public List<ComiSubagenPolView> consultaComiSubagenPolizaView(String codSuba,String asPol,String codAseg) {
		List<ComiSubagenPolView> resultado;
		String sql = "select * from comision_subagente_pol_view WHERE flg_pre_factura = 1 and flg_factura = 1 and flg_pago_canal = 0"
				+ " and cd_subagente = "+codSuba
				+" and poliza like '%"+asPol.trim()+"%' and cd_aseguradora = "+codAseg;
		System.out.println("QUERY :"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComiSubagenPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	public List<ComiSubagenPolView> consultaComiCanalComPolView(Integer codComPol) {
		List<ComiSubagenPolView> resultado;
		String sql = "select * from comision_subagente_pol_view WHERE cd_comision_poliza = "+codComPol;
		System.out.println("QUERY :"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComiSubagenPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
}
