package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.Telefono;


@Stateless
public class ServicioContacto {
	@PersistenceContext
	private EntityManager em;
	
	public Contacto consultaContactosCdCliente(String codClie) {
		String sql = "select * from contacto_tbl where nvl(cd_aseguradora,0) = 0 and  cd_cliente = "+codClie
				+" and cd_contacto in (select max(cd_contacto) from contacto_tbl where nvl(cd_aseguradora,0) = 0 and cd_cliente = "+codClie+")";
		try {
			Query query = em.createNativeQuery(sql, Contacto.class);
			return (Contacto) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Contacto> consultaContactos() {
		String sql = "select * from contacto_tbl";
		try {
			Query query = em.createNativeQuery(sql, Contacto.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	public List<Contacto> listaContactosAseguradora(int codAseg) {
		String sql = "select * from contacto_tbl where actestado ='A' and nvl(cd_cliente,0) = 0 and cd_aseguradora = "
				+ codAseg + " order by departamento_contacto,cargo_contacto,nombre_contacto";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Contacto.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Contacto> listaContactosCliente(int codClie) {
		String sql = "select * from contacto_tbl where actestado ='A' and nvl(cd_aseguradora,0) = 0 and cd_cliente = "
				+ codClie + " order by departamento_contacto,cargo_contacto,nombre_contacto";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Contacto.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Contacto> BuscaContactos(Integer codASeg) 
	{
		List<Contacto> resultado;  
		String sql = "select * from contacto_tbl where cd_aseguradora = "+codASeg;
		System.out.println("SQL->"+sql);
		try
		{
			Query query = em.createNativeQuery(sql,Contacto.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}


	public int insertarContacto(Contacto col) {
		try {
			em.persist(col);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	

	public void actualizaContacto(Contacto col) {
		String resultados;
		resultados = col.getTelefono_contacto();
		col.setTelefono_contacto(resultados);
		resultados = col.getCelular_contacto();
		col.setCelular_contacto(resultados);
		resultados = col.getMail_contacto();
		col.setMail_contacto(resultados);
		resultados = col.getCargo_contacto();
		col.setCargo_contacto(resultados);
		resultados = col.getDepartamento_contacto();
		col.setDepartamento_contacto(resultados);
		resultados = col.getDireccion_contacto();
		col.setDireccion_contacto(resultados);
		resultados = col.getCiudad();
		col.setCiudad(resultados);
		
		
		em.merge(col);
	}

	public void eliminaContacto(Contacto obj) {
		em.remove(em.merge(obj));
	}

}
