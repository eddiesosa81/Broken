package confia.servicios.vistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaPolizaView;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioConsultaPolizaView {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXCliente(String cliente) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cliente like '%" + cliente
				+ "%' order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXClientePol(String cliente, String poli) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cliente like '%" + cliente + "%' " + "and poliza like '"
				+ poli + "' order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXClienteFactPeriodica(String cliente, String poliza) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cd_ramo in (select cd_ramo from ramo_tbl where periodico = 1)"
				+ " and cliente like '" + cliente + "' and poliza like '"+poliza+"' order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXClienteMen(String cliente) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cliente like '%" + cliente
				+ "%' and fact_periodica_cot = 1 order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXClienteGrpCont(String cliente, String grpContratante) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cliente like '%" + cliente
				+ "%' and to_char(cd_grupo_contratante) like '" + grpContratante
				+ "' order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXClienteGrpCont(String cliente, String grpContratante, String pol) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cliente like '%" + cliente
				+ "%' and to_char(cd_grupo_contratante) like '" + grpContratante + "' and poliza like '" + pol
				+ "' order by cliente,nombre_corto_aseguradora";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasCodCotiza(String codCotizacion) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cd_cotizacion = " + codCotizacion
				+ " order by cd_cliente,cd_cotizacion";
		System.out.println("SQL ->" + sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXClienteGrpCont(String cliente, String grpContratante, String pol,
			String fact) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cliente like '%" + cliente
				+ "%' and to_char(cd_grupo_contratante) like '" + grpContratante + "' and poliza like '" + pol
				+ "' and factura_aseguradora like '" + fact + "'  order by cd_cliente,fc_ini_vig_date DESC";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasRenovacion(String codEjecutivo, String grpContratante,
			String codCanal, String fcDesde, String fcHasta, String codAseg, String codRamo, String codClie) {
		List<ConsultaPolizaView> resultado;
		String sql;
		if (codRamo.equals("%")) {
			sql = "select * from consulta_poliza_view " + "where fc_vig_fin_jul >= fechajuliana_func('" + fcDesde
					+ "') and fc_vig_fin_jul <= fechajuliana_func('" + fcHasta + "') "
					+ "and to_char(cd_subagente) like '" + codCanal + "' and to_char(cd_ejecutivo) like '"
					+ codEjecutivo + "' " + "and to_char(cd_grupo_contratante) like '" + grpContratante
					+ "' and CD_RUBRO <> 770 "
					+ " and  cd_cliente like to_char('"+codClie+"')"
					+ " and cd_aseguradora  like to_char('"+codAseg+"') "
					+ " order by cd_cliente,cd_cotizacion";
		} else {
			sql = "select * from consulta_poliza_view " + "where fc_vig_fin_jul >= fechajuliana_func('" + fcDesde
					+ "') and fc_vig_fin_jul <= fechajuliana_func('" + fcHasta + "') "
					+ "and to_char(cd_subagente) like '" + codCanal + "' and to_char(cd_ejecutivo) like '"
					+ codEjecutivo + "' " + "and to_char(cd_grupo_contratante) like '" + grpContratante
					+ "' and CD_RUBRO <> 770 "
					+ " and  cd_cliente like to_char('"+codClie+"')"
					+ " and cd_aseguradora  like to_char('"+codAseg+"') "
					+ " and cd_ramo in ("+codRamo+") "
					+ " order by cd_cliente,cd_cotizacion";
		}
		System.out.println("SQL->" + sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizaAnexoXClienteGrpCont(String cliente, String grpContratante,
			String pol, String fact) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_anexo_poliza_view where cliente like '%" + cliente
				+ "%' and to_char(cd_grupo_contratante) like '" + grpContratante + "' and poliza like '" + pol
				+ "' and factura_aseguradora like '" + fact + "' order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaAnulaCancelXClienteGrpCont(String cliente, String grpContratante,
			String pol, String fact) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_anula_cancela_view where cliente like '%" + cliente
				+ "%' and to_char(cd_grupo_contratante) like '" + grpContratante + "' and poliza like '" + pol
				+ "' and factura_aseguradora like '" + fact + "' order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXRamoCot(String cdRamCot) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cd_ramo_cotizacion = " + cdRamCot
				+ " order by cd_cliente,cd_cotizacion";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasCaracteObj(String cdRamCot) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cd_ramo_cotizacion in( " + cdRamCot
				+ ")  order by cd_cliente,fc_ini_vig_date DESC";
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPolizaView> consultaPolizasXDescObj(String descObj) {
		List<ConsultaPolizaView> resultado;
		String sql = "select * from consulta_poliza_view where cd_ramo_cotizacion "
				+ "in( select distinct cd_ramo_cotizacion from CONSULTA_OBJETO_POL_VIEW where descripcion_objeto like '%"
				+ descObj + "%')  order by cd_cliente,fc_ini_vig_date DESC";
		System.out.println("SQL -->" + sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public ConsultaPolizaView consultaPolizaXCdRamCot(String cdRamoCotiza) {
		ConsultaPolizaView resultado;
		String sql = "select * from consulta_poliza_view where cd_ramo_cotizacion = " + cdRamoCotiza;
		System.out.println("SQL ->" + sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = (ConsultaPolizaView) query.getSingleResult();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public ConsultaPolizaView consultaPolizaXCdCot(String cdCotiza) {
		ConsultaPolizaView resultado;
		String sql = "select distinct * from consulta_poliza_view where cd_cotizacion = " + cdCotiza;

		try {
			Query query = em.createNativeQuery(sql, ConsultaPolizaView.class);
			resultado = (ConsultaPolizaView) query.getSingleResult();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public int polizaPagada(String codComp, String codCot) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as flgPago from detalle_forma_pago_tbl " + "where flg_pago = 1 and cd_compania = "
					+ codComp + " and cd_forma_pago "
					+ " = (select cd_forma_pago from forma_pago_tbl where cd_compania = " + codComp
					+ "and cd_cotizacion = " + codCot + ")";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("flgPago");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return Integer.parseInt(resultado);
	}

	public int polizaMensualizada(String codComp, String codRamCot) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select distinct fact_periodica_cot from consulta_poliza_view " + "where cd_compania = " + codComp
					+ " and cd_ramo_cotizacion = " + codRamCot;
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("fact_periodica_cot");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return Integer.parseInt(resultado);
	}

}
