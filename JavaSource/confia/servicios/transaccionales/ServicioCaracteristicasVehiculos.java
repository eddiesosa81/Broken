package confia.servicios.transaccionales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioCaracteristicasVehiculos {
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<CaracteristicasVehiculos> recuperaCaractVHporObjCot(int cdObjCo, int cdCompania) {
		String sql = "select * from caracteristica_vehiculos_tbl where cd_compania = "+cdCompania+" and cd_obj_cotizacion = "+cdObjCo;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CaracteristicasVehiculos.class);
		return q.getResultList();
	
	}
	
	public CaracteristicasVehiculos recuperaCaractVH(int cdObjCo, int cdCompania) {
		String sql = "select * from caracteristica_vehiculos_tbl where cd_compania = "+cdCompania+" and cd_obj_cotizacion = "+cdObjCo;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CaracteristicasVehiculos.class);
		return (CaracteristicasVehiculos) q.getSingleResult();
	
	}
	
	public int existeCaracteristica(String codObjCot) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as contador from caracteristica_vehiculos_tbl where cd_obj_cotizacion = "+codObjCot;
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("contador");
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
	
	
	public Integer insertaCaracteristicaVehiculos(CaracteristicasVehiculos obj)
	{
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public void actualizaCaracteristicaVehiculos(CaracteristicasVehiculos obj)
	{
		em.merge(obj);
	}
	
	public void eliminaCaracteristicaVehiculos(CaracteristicasVehiculos obj)
	{
		em.remove(em.merge(obj));
	}
	
	public int insertaCaraAneExclusion(String codObjCot,String codObjCotOri, String codCompania) { 
		
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println(
					"Ingreso datos ORACLE -> act_caracterVH_exc_obj_sp ( codObjCot IN varchar2, codObjCotOri IN varchar2, codCompania IN varchar2) IS");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call act_caracterVH_exc_obj_sp(?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, codObjCot);
			cs.setString(2, codObjCotOri);
			cs.setString(3, codCompania);
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
