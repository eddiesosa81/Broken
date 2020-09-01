package confia.servicios.vistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ComisionRamoAseguradoraView;
import confia.procedures.ConectarBase;


@Stateless
public class ServicioComisionRamoAseg {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ComisionRamoAseguradoraView> consultaComisionRamoAseguradora() {
		List<ComisionRamoAseguradoraView> resultado;
		String sql = "select * from comision_ramo_aseg_view order by nombre_corto_aseguradora,cd_grupo_contratante";
		System.out.println("SQL ->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComisionRamoAseguradoraView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public int verificaComision(String codRamo,String codAseg) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as numReg from comision_ramo_aseg_view where cd_aseguradora = "+codAseg+" and cd_ramo = "+codRamo;
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("numReg");
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
