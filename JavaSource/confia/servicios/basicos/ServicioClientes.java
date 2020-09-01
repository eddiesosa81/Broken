package confia.servicios.basicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Clausulas;
import confia.entidades.basicos.Clientes;
import confia.entidades.transaccionales.Siniestros;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioClientes {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Clientes> listaClientesCorrespon()
	{
		List<Clientes> resultado;
		String sql = "select * from cliente_tbl where estado_cliente = 'A'";
		try
		{
			Query query = em.createNativeQuery(sql,Clientes.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Clientes> listaClientes(String str_cliente)
	{
		List<Clientes> resultado;
		String sql = "select * from cliente_tbl where estado_cliente = 'A' and ( "
						+ "nvl(primer_nombre_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(segundo_nombre_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(primer_apellido_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(segundo_apellido_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(razon_social_cliente,'%') like '%"+str_cliente+"%' ) "
						+ "order by primer_apellido_cliente,segundo_apellido_cliente,primer_nombre_cliente,segundo_nombre_cliente,razon_social_cliente";
		try
		{
			Query query = em.createNativeQuery(sql,Clientes.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Clientes> listaClientes(String str_cliente,String empresaUsr)
	{
		List<Clientes> resultado;
		String sql = "select * from cliente_tbl where estado_cliente = 'A' and ( "
						+ "nvl(primer_nombre_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(segundo_nombre_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(primer_apellido_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(segundo_apellido_cliente,'%') like '%"+str_cliente+"%' or "
						+ "nvl(razon_social_cliente,'%') like '%"+str_cliente+"%' ) and "
						+ "empresa = '"+empresaUsr+"' "
						+ "order by primer_apellido_cliente,segundo_apellido_cliente,primer_nombre_cliente,segundo_nombre_cliente,razon_social_cliente";
		try
		{
			Query query = em.createNativeQuery(sql,Clientes.class);

			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Clientes listaClientesXId(Integer cd_cliente)
	{
		Clientes resultado;
		String sql = "select * from cliente_tbl where estado_cliente = 'A' and cd_cliente = :cd_cliente and rownum = 1 "
						+ "order by primer_apellido_cliente,segundo_apellido_cliente,primer_nombre_cliente,segundo_nombre_cliente,razon_social_cliente";
		try
		{
			Query query = em.createNativeQuery(sql,Clientes.class);
			query.setParameter("cd_cliente", cd_cliente);

			resultado = (Clientes) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Clientes listaClientesXIdentClie(String IdentClie)
	{
		Clientes resultado;
		String sql = "select * from cliente_tbl where estado_cliente = 'A' and identificacion_cliente = :IdentClie "
						+ "order by primer_apellido_cliente,segundo_apellido_cliente,primer_nombre_cliente,segundo_nombre_cliente,razon_social_cliente";
		try
		{
			Query query = em.createNativeQuery(sql,Clientes.class);
			query.setParameter("IdentClie", IdentClie);

			resultado = (Clientes) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	public int verificaExisteClientes(String IdentClie) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as cliente from Cliente_tbl where trim(identificacion_cliente) = '"+IdentClie+"'";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cliente");
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
	
	public int codigoMaxClientes() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_cliente),1) as cd_cliente from Cliente_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_cliente");
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
	
	public String nombreCliente(Integer IdentClie) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nombrecliente("+IdentClie+") as cliente from dual";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cliente");
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return resultado;
	}
	
	public Integer insertarClientes(Clientes obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	public void actualizaClientes(Clientes obj) {
		em.merge(obj);
	}

	public void eliminaClientes(Clientes obj) {
		em.remove(em.merge(obj));
	}
}
