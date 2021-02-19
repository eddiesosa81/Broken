package upp.confia.servicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hslf.dev.SlideAndNotesAtomListing;

import confia.procedures.ConectarBase;
import upp.confia.controladores.UppSolicitud;
import upp.confia.entidades.ConectarBaseUpp;

public class ServicioUppNuevaSolicitud {
	
	public int codigoUsuario(String codUsuario) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBaseUpp.getOracleConnection();
			sql = "select distinct usuario_cd from UPP.usuarios where estados_id = 1 and cod_usr = '"+codUsuario+"'";
			System.out.println("Sql->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("usuario_cd");
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
	
	public int ingresa_solicitud_upp(UppSolicitud uppSolicitud) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println(
					"ORACLE PROCEDURE-> inserta_solicitud_UPP_sp ");
			/*
			"ORACLE PROCEDURE-> inserta_solicitud_UPP_sp ( ll_almacen_id in	VARCHAR2,\n" + 
			"					ll_tecnico_reporta in	VARCHAR2,\n" + 
			"					ll_tecnico_asignado in	VARCHAR2,\n" + 
			"					ll_detalle_problema in	VARCHAR2,\n" + 
			"					ll_solucion_tecnica in	VARCHAR2,\n" + 
			"					ll_problema	in VARCHAR2,\n" + 
			"					ll_estados_id in	VARCHAR2,\n" + 
			"					ll_fecha in	VARCHAR2,\n" + 
			"					ll_fecha_asignada in	VARCHAR2,\n" + 
			"					ll_cliente_id in	VARCHAR2,\n" + 
			"					ll_id_padre	in VARCHAR2,\n" + 
			"					ll_usrsupervisa in	VARCHAR2,\n" + 
			"					ll_cod_comite_subcomite in	VARCHAR2,\n" + 
			"					ll_solicitud_proy in	VARCHAR2");*/
			
			SimpleDateFormat formato;
			String patron = "dd/MM/yyyy";
			String fcDesde;
			String fcHasta;
			
			formato = new SimpleDateFormat(patron);
			fcDesde = formato.format(uppSolicitud.getFecha());
			fcHasta = formato.format(uppSolicitud.getFecha_asignada());
			System.out.println("fcDesde:"+fcDesde);
			System.out.println("fcHasta:"+fcHasta);
			
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call inserta_solicitud_UPP_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, String.valueOf(uppSolicitud.getAlmacen_id()));
			cs.setString(2, String.valueOf(uppSolicitud.getTecnico_reporta()));
			cs.setString(3, String.valueOf(uppSolicitud.getTecnico_asignado()));
			cs.setString(4, uppSolicitud.getDetalle_problema());
			cs.setString(5, uppSolicitud.getSolucion_tecnica());
			cs.setString(6, uppSolicitud.getProblema());
			cs.setString(7, String.valueOf(uppSolicitud.getEstados_id()));
			cs.setString(8, fcDesde);
			cs.setString(9, fcHasta);
			cs.setString(10, String.valueOf(uppSolicitud.getCliente_id()));
			cs.setString(11, String.valueOf(uppSolicitud.getId_padre()));
			cs.setString(12, String.valueOf(uppSolicitud.getUsrsupervisa()));
			cs.setString(13, String.valueOf(uppSolicitud.getCod_comite_subcomite()));
			cs.setString(14, String.valueOf(uppSolicitud.getSolicitud_proy()));
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
