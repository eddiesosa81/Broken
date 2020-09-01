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

import confia.entidades.basicos.CuotasXCobrar;
import confia.entidades.vistas.CotizacionesPendientes;

/**
 * @author Guido Guerrero
 *
 */
@Stateless
public class ServicioCuotasXCobrar {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CuotasXCobrar> listarCuotasXCobrar(String clienteCd, String poliza, String factura, String ramoCd,
			String grpContratanteCd, String aseguradoraCd, Date desde, Date hasta, int condicion, String tipo) {

		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		String asFechaDesde = formato.format(desde);
		String asFechaHasta = formato.format(hasta);

		List<CuotasXCobrar> resultado;
		String cond = " or ";

		// if (condicion == 1) {
		cond = " and ";
		// }
		System.out.println("CLIENTE CONSULTADO:"+clienteCd);
		System.out.println("TIPO CONSULTADO:"+tipo);
		String sql;
		if (tipo.equals("1")) {
			
			sql = "select * from cuotasxcobrar_view where " + " cd_cliente like '" + clienteCd + "'" + cond
					+ " poliza like '" + poliza + "'" + cond + " rc_factura_aseguradora like '" + factura + "'" + cond
					+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
					+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
					+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
					+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
					+ "') and facturacion_periodica = 1 order by cd_cotizacion , numero_pago";
		} else {
			if (clienteCd.equals("%")) {
				 sql = "select * from cuotasxcobrar_view where " + " cd_cliente like '" + clienteCd + "'" + cond
							+ " poliza like '" + poliza + "'" + cond + " rc_factura_aseguradora like '" + factura + "'" + cond
							+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
							+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
							+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
							+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
							+ "') order by cd_cotizacion , numero_pago";
			} else {
				 sql = "select * from cuotasxcobrar_view where " + " cd_cliente = " + clienteCd + " " + cond
							+ " poliza like '" + poliza + "'" + cond + " rc_factura_aseguradora like '" + factura + "'" + cond
							+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
							+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
							+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
							+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
							+ "') order by cd_cotizacion , numero_pago";

			}
			
		}
		System.out.println("SQL->" + sql);
		try {
			Query query = em.createNativeQuery(sql, CuotasXCobrar.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CuotasXCobrar> listarCuotasXCobrarPeriodica(String clienteCd, String poliza, String factura, String ramoCd,
			String grpContratanteCd, String aseguradoraCd, Date desde, Date hasta, int condicion, String tipo) {

		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		String asFechaDesde = formato.format(desde);
		String asFechaHasta = formato.format(hasta);

		List<CuotasXCobrar> resultado;
		String cond = " or ";

		// if (condicion == 1) {
		cond = " and ";
		// }
		System.out.println("CLIENTE CONSULTADO:"+clienteCd);
		System.out.println("TIPO CONSULTADO:"+tipo);
		String sql;
		if (tipo.equals("1")) {
			
			sql = "select * from cuotasxcobrar_view where " + " cd_cliente like '" + clienteCd + "'" + cond
					+ " poliza like '" + poliza + "'" + cond + " dfp_factura_aseguradora like '" + factura + "'" + cond
					+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
					+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
					+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
					+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
					+ "') and facturacion_periodica = 1 order by cd_cotizacion , numero_pago";
		} else {
			if (clienteCd.equals("%")) {
				 sql = "select * from cuotasxcobrar_view where " + " cd_cliente like '" + clienteCd + "'" + cond
							+ " poliza like '" + poliza + "'" + cond + " dfp_factura_aseguradora like '" + factura + "'" + cond
							+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
							+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
							+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
							+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
							+ "') order by cd_cotizacion , numero_pago";
			} else {
				 sql = "select * from cuotasxcobrar_view where " + " cd_cliente = " + clienteCd + " " + cond
							+ " poliza like '" + poliza + "'" + cond + " dfp_factura_aseguradora like '" + factura + "'" + cond
							+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
							+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
							+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
							+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
							+ "') order by cd_cotizacion , numero_pago";

			}
			
		}
		System.out.println("SQL->" + sql);
		try {
			Query query = em.createNativeQuery(sql, CuotasXCobrar.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CuotasXCobrar> listarCuotasXCobrarMensualizada(String clienteCd, String poliza, String factura, String ramoCd,
			String grpContratanteCd, String aseguradoraCd, Date desde, Date hasta, int condicion, String tipo) {

		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		String asFechaDesde = formato.format(desde);
		String asFechaHasta = formato.format(hasta);

		List<CuotasXCobrar> resultado;
		String cond = " or ";

		// if (condicion == 1) {
		cond = " and ";
		// }
		System.out.println("CLIENTE CONSULTADO:"+clienteCd);
		System.out.println("TIPO CONSULTADO:"+tipo);
		String sql;
		if (tipo.equals("1")) {
			
			sql = "select * from cuotasxcobrar_view where " + " cd_cliente like '" + clienteCd + "'" + cond
					+ " poliza like '" + poliza + "'" + cond + " dfp_factura_aseguradora like '" + factura + "'" + cond
					+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
					+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
					+ " fechajuliana_func(to_char(fc_fin_vigencia,'dd/mm/yyyy')) >= fechajuliana_func('" + asFechaDesde + "') and "
					+ " fechajuliana_func(to_char(fc_fin_vigencia,'dd/mm/yyyy')) <= fechajuliana_func('" + asFechaHasta
					+ "') and facturacion_periodica = 1 order by cd_cotizacion , numero_pago";
			System.out.println("Forma PAgo Mensualizado;"+sql);
		} else {
			if (clienteCd.equals("%")) {
				 sql = "select * from cuotasxcobrar_view where " + " cd_cliente like '" + clienteCd + "'" + cond
							+ " poliza like '" + poliza + "'" + cond + " dfp_factura_aseguradora like '" + factura + "'" + cond
							+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
							+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
							+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
							+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
							+ "') order by cd_cotizacion , numero_pago";
			} else {
				 sql = "select * from cuotasxcobrar_view where " + " cd_cliente = " + clienteCd + " " + cond
							+ " poliza like '" + poliza + "'" + cond + " dfp_factura_aseguradora like '" + factura + "'" + cond
							+ " cd_ramo like '%" + ramoCd + "'" + cond + " cd_grupo_contratante like '" + grpContratanteCd + "'"
							+ cond + " cd_aseguradora like '" + aseguradoraCd + "'" + cond
							+ " fecha_vencimiento_Jul >= fechajuliana_func('" + asFechaDesde + "') and "
							+ " fecha_vencimiento_Jul <= fechajuliana_func('" + asFechaHasta
							+ "') order by cd_cotizacion , numero_pago";

			}
			
		}
		System.out.println("SQL->" + sql);
		try {
			Query query = em.createNativeQuery(sql, CuotasXCobrar.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public CuotasXCobrar cuotasXCobrarID(Integer codCot, Integer codComp, Integer codDetPAgo) {
		String sql = "select * from cuotasxcobrar_view " + "where cd_cotizacion = " + codCot + " and cd_compania = "
				+ codComp + " and cd_det_forma_pago = " + codDetPAgo;
		System.out.println("SQL->" + sql);
		try {
			Query query = em.createNativeQuery(sql, CuotasXCobrar.class);
			return (CuotasXCobrar) query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}
}
