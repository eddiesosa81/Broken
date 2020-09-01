package confia.servicios.vistas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ComisionDiferencialPolView;

@Stateless
public class ServicioComisionDiferencialPolView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ComisionDiferencialPolView> listaComiAsegPolView() {
		String sql = "select * from comision_aseguradora_pol_view";
		System.out.println("SQL ----->" + sql);
		Query query = em.createNativeQuery(sql, ComisionDiferencialPolView.class);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ComisionDiferencialPolView> listaAsegComiAsegPolView(String codAseg, String codSuba, Date fcDesde, Date fcHasta) {
		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		String asFechaDesde = formato.format(fcDesde);
		String asFechaHasta = formato.format(fcHasta);
		
		
		String sql = "select * from comision_aseguradora_pol_view where cd_aseguradora like '" + codAseg
				+ "' and CD_SUBAGENTE like '" + codSuba 
				+ "' and fechajuliana_func(to_char(fc_emision_date,'dd/mm/yyyy')) BETWEEN fechajuliana_func('"+asFechaDesde
				+"') and  fechajuliana_func('"+asFechaHasta+"')"
				+" and flg_pre_factura = 1 and flg_factura = 1 and flg_liquidadiferencial=0";

		System.out.println("SQL ----->" + sql);
		Query query = em.createNativeQuery(sql, ComisionDiferencialPolView.class);
		return query.getResultList();
	}
	
	

	public ComisionDiferencialPolView buscaComisionAsegPol(Integer cd_comision_poliza) {
		String sql = "select * from comision_aseguradora_pol_view where cd_comision_poliza=" + cd_comision_poliza;
		System.out.println("Mauri-----Query:" + sql);
		Query q = em.createNativeQuery(sql, ComisionDiferencialPolView.class);
		return (ComisionDiferencialPolView) q.getSingleResult();
	}

	public void actualizaDiferencialPolView(ComisionDiferencialPolView obj) {
		em.merge(obj);
	}

}
