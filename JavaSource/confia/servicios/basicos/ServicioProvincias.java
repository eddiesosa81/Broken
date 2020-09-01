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
import confia.entidades.basicos.Provincias;
import confia.entidades.transaccionales.Siniestros;
import confia.procedures.ConectarBase;

@Stateless
public class ServicioProvincias {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Provincias> listaProvincias()
	{
		List<Provincias> resultado;
		String sql = "SELECT cd_provincia,desc_provincia FROM provincia_tbl ";
		try
		{
			Query query = em.createNativeQuery(sql,Provincias.class);
			resultado = query.getResultList();
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	
}
