package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Cotizacion;

@Stateless
public class ServicioUsuarios {
	@PersistenceContext
	private EntityManager em;
	
	public Usuarios recuperaUsuario(String userId) {
		String sql = "select * from USUARIO_TBL where usrid = "+userId;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Usuarios.class);
		 return (Usuarios) q.getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public List<Usuarios> listaUsuariosActivos()
	{
		List<Usuarios> resultado;
		String sql = "select * from usuario_tbl where estid = 1 and empresa = 'CONFIA'";
		try
		{
			Query query = em.createNativeQuery(sql,Usuarios.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Usuarios> listaUsuariosCorrespon()
	{
		List<Usuarios> resultado;
		String sql = "select * from usuario_tbl";
		try
		{
			Query query = em.createNativeQuery(sql,Usuarios.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Usuarios validarUsuario(String usuario, String clave) {
		Usuarios user;
		
		String sql = "SELECT * FROM USUARIO_TBL WHERE ESTID = 1 and USRLOGIN = :usuario and USRCLAVE = :clave";
		/*System.out.println("Entra");
		 * */
		try
		{
			Query query = em.createNativeQuery(sql,Usuarios.class);
			query.setParameter("usuario", usuario);
			query.setParameter("clave", clave);
			
			user =  (Usuarios) query.getSingleResult();
			
			if(user.getUsrid() > 0) {
				return user;
			}
		}
		catch(Exception ex)
		{
			return null;
		}
		
		return null;
	}
}
