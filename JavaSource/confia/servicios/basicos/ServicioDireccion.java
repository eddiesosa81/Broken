package confia.servicios.basicos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Direccion;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioDireccion {
	@PersistenceContext
	private EntityManager em;
	
	 
	
	public Direccion BuscaDireccionCodCliente(String codClie) 
	{
		Direccion resultado;  
		String sql = "select * from direccion_tbl where nvl(cd_aseguradora,0) = 0 and  cd_cliente = "+codClie
				+" and cd_direccion in(select max(cd_direccion) from direccion_tbl where nvl(cd_aseguradora,0) = 0 and cd_cliente = "+codClie+")";
		System.out.println("SQL->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Direccion.class);
			
			resultado = (Direccion) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Direccion> BuscaDireccionCliente(Integer codClie) 
	{
		List<Direccion> resultado;  
		String sql = "select * from direccion_tbl where nvl(cd_aseguradora,0) = 0 and  cd_cliente = "+codClie;
		System.out.println("SQL->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Direccion.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Direccion> BuscaDireccion(Integer codASeg) 
	{
		List<Direccion> resultado;  
		String sql = "select * from direccion_tbl where nvl(cd_cliente,0) = 0 and cd_aseguradora = "+codASeg;
		System.out.println("SQL->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Direccion.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Direccion> consultaDireccion() {
		String sql = "select * from direccion_tbl";
		try {
			Query query = em.createNativeQuery(sql, Direccion.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

	public int insertarDireccion(Direccion col) {
		try {
			em.persist(col);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaDireccion(Direccion col) {
		Connection conn;
		String sql,codAseg,codCiu,codClie,codProv,codRub,codDir,codRef;
		try {
			codAseg = String.valueOf(col.getCd_aseguradora());
		} catch (Exception e) {
			codAseg = "0";
		}
		System.err.println("codAseg:"+codAseg);
		
		try {
			codCiu = String.valueOf(col.getCd_ciudad());
		} catch (Exception e) {
			codCiu = "0";
		}
		System.err.println("codCiu:"+codCiu);
		try {
			codClie = String.valueOf(col.getCd_cliente());
		} catch (Exception e) {
			codClie = "0";
		}
		System.err.println("codClie:"+codClie);
		try {
			codProv = col.getCd_provincia();
		} catch (Exception e) {
			codProv = "0";
		}
		System.err.println("codProv:"+codProv);
		try {
			codRub = String.valueOf(col.getCd_rubro());
		} catch (Exception e) {
			codRub = "0";
		}
		System.err.println("codRub:"+codRub);
		try {
			codDir = col.getDireccion();
		} catch (Exception e) {
			codDir = "S/N";
		}
		System.err.println("codDir:"+codDir);
		try {
			codRef = col.getReferencia();
		} catch (Exception e) {
			codRef = "S/N";
		}
		System.err.println("codRef:"+codRef);
		int retorno = 0;
		// ejecuta el update
		try {
			System.out.println("Ingreso datos ORACLE -> update direccion_tbll");
			conn = ConectarBase.getOracleConnection();
			sql = "update direccion_tbl set "
					+ "cd_cliente = "+codClie+", "
					+ "cd_aseguradora = "+codAseg+", "
					+ "cd_ciudad = "+codCiu+", "
					+ "cd_provincia = "+codProv+", "
					+ "direccion = '"+codDir+"', "
					+ "cd_rubro = "+codRub+", "
					+ "referencia = '"+codRef+"' "
					+ "where cd_direccion = "+ col.getCd_direccion();
			System.out.println("QUERY -->:" + sql);
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
			conn.close();
			retorno = 1;
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
			retorno = 0;
		}

	}

	public void eliminaDireccion(Direccion obj) {
		em.remove(em.merge(obj));
	}

}
