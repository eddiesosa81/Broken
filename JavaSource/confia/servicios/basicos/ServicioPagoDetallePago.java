/**
 * 
 */
package confia.servicios.basicos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.PagoDetallePago;
import confia.entidades.vistas.ConsultaPagoPolView;
import confia.procedures.servicioProcedures;

/**
 * @author Guido Guerrero
 *
 */

@Stateless
public class ServicioPagoDetallePago {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<PagoDetallePago> listarPagosXRemitir(String cdAseg) {
		
		String sql = "select * from pago_detalle_pago_view where cd_aseguradora = "+cdAseg+" order by  nombre_corto_aseguradora,cliente";
		try {
			Query query = em.createNativeQuery(sql, PagoDetallePago.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<PagoDetallePago> listarPagosXRemitir(String cdAseg,String factAseg) {
		String sql = "select * from pago_detalle_pago_view where cd_aseguradora = "+cdAseg
				+"and factura_aseg like '%"+factAseg+"%' order by  nombre_corto_aseguradora,cliente";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PagoDetallePago.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<PagoDetallePago> listarPagosXRemitirMensualizado(String cdAseg,String factAseg,Date fcDesdeMen,Date fcHastaMen) {
		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		String lsfcDesde = formato.format(fcDesdeMen);
		String lsfcHasta = formato.format(fcHastaMen);
		servicioProcedures srvProcedimientos = new servicioProcedures();
		Integer llfcDesde = srvProcedimientos.fechaJuliana(lsfcDesde);
		Integer llfcHasta = srvProcedimientos.fechaJuliana(lsfcHasta);
		
		String sql = "select * from pago_detalle_pago_view where cd_aseguradora = "+cdAseg
				+"and factura_aseg like '%"+factAseg+"%' "
				+"and FC_INI_VIGENCIA_MENSUAL_JUL >= "+llfcDesde+" "
				+"and FC_FIN_VIGENCIA_MENSUAL_JUL <= "+llfcHasta+" "
				+"and fc_ini_vigencia_mensual is not null and fc_fin_vigencia_mensual is not null "
				+"order by  fc_ini_vigencia_mensual,nombre_corto_aseguradora,cliente";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PagoDetallePago.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<PagoDetallePago> listarPagosXRemitir(String cdAseg,String factAseg,String pol) {
		
		String sql = "select * from pago_detalle_pago_view where cd_aseguradora = "+cdAseg
				+" and factura_aseg like '%"+factAseg+"%' and poliza like '%"+pol+"%' order by  nombre_corto_aseguradora,cliente";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PagoDetallePago.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<PagoDetallePago> listarPagosXRemitirMensualizado(String cdAseg,String factAseg,String pol,Date fcDesdeMen,Date fcHastaMen) {
		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		String lsfcDesde = formato.format(fcDesdeMen);
		String lsfcHasta = formato.format(fcHastaMen);
		servicioProcedures srvProcedimientos = new servicioProcedures();
		Integer llfcDesde = srvProcedimientos.fechaJuliana(lsfcDesde);
		Integer llfcHasta = srvProcedimientos.fechaJuliana(lsfcHasta);
				
		String sql = "select * from pago_detalle_pago_view where cd_aseguradora = "+cdAseg
				+" and factura_aseg like '%"+factAseg+"%' and poliza like '%"+pol+"%' "
				+"and FC_INI_VIGENCIA_MENSUAL_JUL >= "+llfcDesde+" "
				+"and FC_FIN_VIGENCIA_MENSUAL_JUL <= "+llfcHasta+" "
				+"and fc_ini_vigencia_mensual is not null and fc_fin_vigencia_mensual is not null "
				+"order by  fc_ini_vigencia_mensual,nombre_corto_aseguradora,cliente";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, PagoDetallePago.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PagoDetallePago> guardarPago(Long cd_aseguradora) {
		String sql = "Select e from PagoDetallePago e where e.cd_aseguradora = :cd_aseguradora";
		
		Query query = em.createQuery(sql);
		query.setParameter("cd_aseguradora", cd_aseguradora);
		
		return query.getResultList();
	}
}
