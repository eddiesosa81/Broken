package confia.procedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProcedimientosAlmacenadosDB {
	public int numReciboExpedicion() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select secuencia_num_expedicion.nextval as numExp from dual ";
			System.out.println("SQL Fecha Juliana:" + sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("numExp");
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

	public int actualizaNumExpedicion(String codPago, String numExpedicion) {
		Connection conn;
		String sql;
		int retorno = 0;
		// ejecuta el update
		try {
			System.out.println("Ingreso datos ORACLE -> update pago_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update pago_tbl set fc_num_recibo = sysdate, num_recibo = " + numExpedicion
					+ " where num_recibo is null and cd_pago = " + codPago;
			System.out.println("QUERY update ->:" + sql);
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
			conn.close();
			retorno = 1;
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			retorno = 0;
		}

		return retorno;
	}

	public int bajaSiniestrosObjeto(Integer cdCompania, Integer cdObjCot) {
		Connection conn;
		String sql;
		int retorno = 0;
		// ejecuta el update
		try {
			System.out.println("Ingreso datos ORACLE -> update objeto_cotizacion_pol_tbl");
			conn = ConectarBase.getOracleConnection();
			sql = "update objeto_cotizacion_pol_tbl set tipo = 'BAJA SINIESTROS' " + " where CD_COMPANIA = "
					+ cdCompania + " and cd_obj_cotizacion = " + cdObjCot;
			System.out.println("QUERY objeto_cotizacion_pol_tbl ->:" + sql);
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
			conn.close();
			retorno = 1;
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			retorno = 0;
		}

		return retorno;
	}

	public int nivelacionFactSiniestrosPerdidaTotal(Integer cdRamCot, Integer cdCompania) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println(
					"Ingreso datos ORACLE -> sinies_liq_mensualizado_sp ( codRamCot IN VARCHAR2, codComp IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call sinies_liq_mensualizado_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setInt(1, cdRamCot);
			cs.setInt(2, cdCompania);
			cs.execute();
			cs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 0;
		}
		return 1;
	}

	public int recalculoDiferencialPol(String codCompania, String codComision, String codRamoCot) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("Ingreso datos ORACLE -> recalculo_diferenciales_pol_sp(codCompania: " + codCompania
					+ ", codComision:" + codComision + ",codRamoCot: " + codRamoCot + ")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call recalculo_diferenciales_pol_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCompania);
			cs.setString(2, codComision);
			cs.setString(3, codRamoCot);
			cs.execute();
			cs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 0;
		}
		return 1;
	}
}
