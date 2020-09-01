package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Direccion;
import confia.entidades.basicos.Telefono;

@Stateless
public class ServicioTelefono {
	@PersistenceContext
	private EntityManager em;
	
	public Telefono BuscaTelefonoCodCliente(String codClie) 
	{
		Telefono resultado;  
		String sql = "select * from telefono_tbl where cd_aseguradora = 0 "
					+ "and cd_cliente  = "+codClie
					+" and cd_telefono in(select max(cd_telefono) from telefono_tbl where nvl(cd_aseguradora,0) = 0 and cd_cliente = "+codClie+")";
		System.out.println("SQL->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Telefono.class);
			
			resultado = (Telefono) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Telefono> BuscaTelefonosCliente(Integer codClie) 
	{
		List<Telefono> resultado;  
		String sql = "select * from telefono_tbl where cd_aseguradora = 0 "
					+ "and cd_cliente  = "+codClie;
		System.out.println("SQL->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Telefono.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Telefono> BuscaTelefonos(Integer codASeg) 
	{
		List<Telefono> resultado;  
		String sql = "select * from telefono_tbl where cd_cliente = 0 "
					+ "and cd_aseguradora = "+codASeg;
		System.out.println("SQL->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Telefono.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefono> consultaTelefono() {
		String sql = "select * from telefono_tbl";
		try {
			Query query = em.createNativeQuery(sql, Telefono.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

		public void actualizaTelefono(Telefono col) {
			String resultados;
			resultados = col.getTipo();
			col.setTipo(resultados);
			resultados = col.getTelefono();
			col.setTelefono(resultados);
			resultados = col.getExt();
			col.setExt(resultados);
			resultados = col.getNombrerelacion();
			col.setNombrerelacion(resultados);
			resultados = col.getCorreo();
			col.setCorreo(resultados);
			
			em.merge(col);
		}
		
		public int insertarTelefonos(Telefono col) {
			try {
				em.persist(col);
			} catch (Exception e) {
				return 0;
			}
			return 1;
		}

		public void eliminaTelefono(Telefono obj) {
			em.remove(em.merge(obj));
		}
}
