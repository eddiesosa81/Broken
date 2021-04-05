package confia.procedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class servicioProcedures {
	
	
	
	public Integer act_comision_pol_Asis_med(String codCompania,String cdDetFormaPago ) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> act_comision_pol_Asis_med (cdDetFormaPago: "+cdDetFormaPago+",cdCompania:"+codCompania+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call act_comision_pol_Asis_med(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdDetFormaPago);
			cs.setString(2, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	public Integer act_valores_ramo_cot_anexos_sp(String cdRamoCot, String cdCompania ) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> act_valores_ramo_cot_anexos_sp ( cdRamCot IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call act_valores_ramo_cot_anexos_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdRamoCot);
			cs.setString(2, cdCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	public Integer recalculo_comision_pol(String cdRamoCot, String cdCompania ) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> calcula_comision_poliza ( cdRamCot IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call calcula_comision_poliza(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdRamoCot);
			cs.setString(2, cdCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	public Integer recalculo_comision_canla(String cdComisionPol, String cdCompania ) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> recalcula_comision_canal_sp ( "+cdComisionPol+","+cdCompania+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call recalcula_comision_canal_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdComisionPol);
			cs.setString(2, cdCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	public Integer reversa_prefactura_sp(String codPreFac ) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> reversa_preFactura_sp ( codprefac IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call reversa_preFactura_sp(?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codPreFac);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	public Integer reversa_factura_sp(String numFacConfia ) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> reversa_Factura_sp ( numfac IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call reversa_factura_sp(?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, numFacConfia);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer detallFormaPagoMensualizado(String codCompania,String cdFormaPago,String numCuotas,String codCot,String tipoFrmPago ) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> mensualizado_det_frm (cdCompania"+codCompania+",cdFormaPago "+cdFormaPago+",cuotas "+numCuotas+",cdCotizacion "+codCot+",num_alternativa_formapago "+tipoFrmPago+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call mensualizado_det_frm(?,?,?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCompania);
			cs.setString(2, cdFormaPago);
			cs.setString(3, numCuotas);
			cs.setString(4, codCot);
			cs.setString(5, tipoFrmPago);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer generaCobeDedClauPolizas(String codCot, String codCompania) {
		System.out.println("** cdCotizacion:" + codCot);
		System.out.println("** cdCompania:" + codCompania);
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> genera_cob_clau_ded_sp( codCot IN number, codCompania IN number)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call genera_cob_clau_ded_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCot);
			cs.setString(2, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	public Integer elimina_cob_clau_ded_sp(String codRamCot, String codCompania) {
		System.out.println("** cdRamoCotizacion:" + codRamCot);
		System.out.println("** cdCompania:" + codCompania);
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> elimina_cob_clau_ded_sp( codRamCot IN number, codCompania IN number)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call elimina_cob_clau_ded_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codRamCot);
			cs.setString(2, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	
	public Integer generaCobeDedClauPolizasUbc(String codUbc, String codCompania) {
		System.out.println("** cdUbicacion:" + codUbc);
		System.out.println("** cdCompania:" + codCompania);
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> genera_cob_clau_ded_ubc_sp( codUbc IN number, codCompania IN number)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call genera_cob_clau_ded_ubc_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codUbc);
			cs.setString(2, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

//	public Integer actualizaValoresRamoCotizacion(String codramocot, String codcompania) {
//		System.out.println("** cdRamoCotizacion:" + codramocot);
//		System.out.println("** cdCompania:" + codcompania);
//		Connection conn;
//		// ejecuta el SP
//		try {
//			System.out.println(
//					"ORACLE PROCEDURE-> act_valores_ramo_cotizacion_sp( codramocot IN number, codcompania IN number)");
//			conn = ConectarBase.getOracleConnection();
//			String proc3StoredProcedure = "{ call act_valores_ramo_cotizacion_sp(?,?) }";
//			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
//			cs.setString(1, codramocot);
//			cs.setString(2, codcompania);
//			cs.execute();
//			cs.close();
//			conn.close();
//			return 0;
//		} catch (Exception e) {
//			System.out.println("error " + e.getMessage());
//			return 1;
//		}
//	}

	public Integer emitePolizas(String codCot, String codCompania) {
		System.out.println("** cdCotizacion:" + codCot);
		System.out.println("** cdCompania:" + codCompania);
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> emite_poliza_sp( codCot IN number, codCompania IN number)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_poliza_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCot);
			cs.setString(2, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexosInclusionUbc(String cdRamCotPol, String codRamCot, String codCompania) {
		Connection conn;
		System.out.println("cdRamCotPol:" + cdRamCotPol);
		System.out.println("codRamCot:" + codRamCot);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANEXO_INC_UBC_sp ( cdRamCotPol in VARCHAR2, codRamCot IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_anexo_inc_ubc_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdRamCotPol);
			cs.setString(2, codRamCot);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexosInclusionObj(String cdUbcPol, String codUbc, String codCompania) {
		Connection conn;
		System.out.println("cdUbcPol:" + cdUbcPol);
		System.out.println("codUbc:" + codUbc);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANEXO_INC_OBJ_sp ( cdUbcPol in VARCHAR2, codUbc IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_ANEXO_INC_OBJ_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdUbcPol);
			cs.setString(2, codUbc);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexosInclusionSubObj(String cdObjPol, String codObj, String codCompania) {
		Connection conn;
		System.out.println("cdObjPol:" + cdObjPol);
		System.out.println("codObj:" + codObj);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANEXO_INC_SubOBJ_sp ( cdObjPol in VARCHAR2, codObj IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_ANEXO_INC_SubOBJ_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdObjPol);
			cs.setString(2, codObj);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexosExclusionUbj(String cdRamCotPol, String codRamCot, String codCompania) {
		Connection conn;
		System.out.println("cdRamCotPol:" + cdRamCotPol);
		System.out.println("codRamCot:" + codRamCot);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANEXO_EXC_UBC_sp ( cdRamCotPol in VARCHAR2, codRamCot IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_ANEXO_EXC_UBC_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdRamCotPol);
			cs.setString(2, codRamCot);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexoseXclusionObj(String cdUbcPol, String codUbc, String codCompania) {
		Connection conn;
		System.out.println("cdUbcPol:" + cdUbcPol);
		System.out.println("codUbc:" + codUbc);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANEXO_EXC_OBJ_sp ( cdUbcPol in VARCHAR2, codUbc IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_ANEXO_EXC_OBJ_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdUbcPol);
			cs.setString(2, codUbc);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexosExclusionSubObj(String cdObjPol, String codObj, String codCompania) {
		Connection conn;
		System.out.println("cdObjPol:" + cdObjPol);
		System.out.println("codObj:" + codObj);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANEXO_EXC_SubOBJ_sp ( cdObjPol in VARCHAR2, codObj IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_ANEXO_EXC_SubOBJ_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdObjPol);
			cs.setString(2, codObj);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexosMODVALASEGObj(String cdUbcPol, String codUbc, String codCompania) {
		Connection conn;
		System.out.println("cdUbcPol:" + cdUbcPol);
		System.out.println("codUbc:" + codUbc);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANEXO_MODVALASEG_OBJ_sp ( cdUbcPol in VARCHAR2, codUbc IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_ANEXO_MODVALASEG_OBJ_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdUbcPol);
			cs.setString(2, codUbc);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer emiteAnexosMODVALASEGSubObj(String cdObjPol, String codObj, String codCompania) {
		Connection conn;
		System.out.println("cdObjPol:" + cdObjPol);
		System.out.println("codObj:" + codObj);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_ANE_MODVALASEG_SOBJ_sp ( cdObjPol in VARCHAR2, codObj IN VARCHAR2, codCompania IN VARCHAR2)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_ANE_MODVALASEG_SOBJ_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdObjPol);
			cs.setString(2, codObj);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

	public Integer anexoAnulaCancelaPoliza(String cdRamoCotPol, String codRamoCot, String codCompania) {
		Connection conn;
		System.out.println("cdRamoCotPol:" + cdRamoCotPol);
		System.out.println("codRamoCot:" + codRamoCot);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> emite_anexo_anula_Cancela_sp ( cdRamoCotPol IN number, codRamoCot IN number, codCompania IN number)");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call emite_anexo_anula_Cancela_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdRamoCotPol);
			cs.setString(2, codRamoCot);
			cs.setString(3, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	public int fechaJulianaActual() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select fechajuliana_func(to_char(sysdate,'dd/mm/yyyy')) as fecJul from dual ";
			System.out.println("SQL Fecha Juliana:"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("fecJul");
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
	public int fechaJuliana(String fecha) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select fechajuliana_func('" + fecha + "') as fecJul from dual ";
			System.out.println("SQL Fecha Juliana:"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("fecJul");
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

	public int diasVigencias(Integer fcJulIni, Integer fcJulFin) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select dias_vigencia_jul_func( '" + fcJulIni + "','" + fcJulFin + "') as diasVig from dual ";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("diasVig");
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
	
	
	
	public int compruebaPagoCanal(String numPoliza, String numFactura) {
		Connection conn;
		String sql;
		Integer resultado = 0;
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select pagoFacturaCanal ('"+numPoliza+"','"+numFactura+"') as pago from dual ";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getInt("pago");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = 0;
		}
		return resultado;
	}
	
	
	public int actualizaVigenciasCot(String codCot,String fcDesde,String fcHasta,String tipo,String noReno,String codEjecutivo) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> actuliza_Vigencia_Cot_sp ( "+codCot+","+fcDesde+","+fcHasta+","+tipo+","+noReno+","+codEjecutivo+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call actuliza_Vigencia_Cot_sp(?,?,?,?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCot);
			cs.setString(2, fcDesde);
			cs.setString(3, fcHasta);
			cs.setString(4, tipo);
			cs.setString(5, noReno);
			cs.setString(6, codEjecutivo);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}

	}

	public int copiaPlan(String codPlanNuevo, String codPlanAnt, String codAseguradora) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> copia_plan_sp ( " + codPlanNuevo + "," + codPlanAnt + ","
					+ codAseguradora + ")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call copia_plan_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codPlanNuevo);
			cs.setString(2, codPlanAnt);
			cs.setString(3, codAseguradora);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	public String renuevaPoliza(String codCot, String codCompania) {
		Connection conn;
		String resultado = null;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> renueva_poliza_sp ( " + codCot + "," + codCompania + ")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call renueva_poliza_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCot);
			cs.setString(2, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			try {
				conn = ConectarBase.getOracleConnection();
				String sql = "select num_cotizacion from cotizacion_tbl where cd_compania = "+codCompania
						+" and cd_cotizacion = (select max(cd_cotizacion) from cotizacion_tbl where cd_cot_renueva_ori = "+codCot+")";
				System.out.println("SQL->"+sql);
				PreparedStatement a = conn.prepareStatement(sql);
				ResultSet res = a.executeQuery();
				if (res.next()) {
					resultado = res.getString("num_cotizacion");
				}
				res.close();
				a.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultado = "0";
			}
			
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return "Error";
		}
		
		return resultado;
	}
	
	
	
	public int reversa_poliza_emitida(String cdRamoCot) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> reversa_poliza_sp ( "+cdRamoCot+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call reversa_poliza_sp(?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdRamoCot);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}

	}
	
	public Integer formaPagoResumenCorporativo(String cdCorres, String codCompania) {
		Connection conn;
		System.out.println("cdCorrespondencia:" + cdCorres);
		System.out.println("codCompania:" + codCompania);
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> forma_pago_resumen_sp ( cdCorres :"+cdCorres+" codCompania: "+codCompania+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call forma_pago_resumen_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, cdCorres);
			cs.setString(2, codCompania);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}
	
	public int act_estado_no_renovado(String codCotizacion,String motivo,String codComp) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> act_estado_no_renovado_sp ( "+codCotizacion+","+motivo+","+codComp+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call act_estado_no_renovado_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCotizacion);
			cs.setString(2, motivo);
			cs.setString(3, codComp);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}

	}
	
	public int genera_cuadro_Comparativo(String codCorrespondencia,String codComp) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> call genera_cuadro_comparativo_sp("+codCorrespondencia+","+codComp+")");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call genera_cuadro_comparativo_sp(?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codCorrespondencia);
			cs.setString(2, codComp);
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}

	}

}
