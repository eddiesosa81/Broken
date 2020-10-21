package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.GrupoContratante;

@Stateless
public class ServicioGrupoContratante {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<GrupoContratante> listaGruposContratantes()
	{
		List<GrupoContratante> resultado;
		String sql = "select * from grupo_contratante_tbl where estado_grupo_contratante = 'A' order by 2";
		
		try
		{
			Query query = em.createNativeQuery(sql,GrupoContratante.class);
			
			resultado = query.getResultList();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public GrupoContratante buscaGruposContratantes(Integer cd_grupo_contratante)
	{
		GrupoContratante resultado;
		String sql = "select * from grupo_contratante_tbl where estado_grupo_contratante = 'A' and cd_grupo_contratante = :cd_grupo_contratante order by 2";
		
		try
		{
			Query query = em.createNativeQuery(sql,GrupoContratante.class);
			query.setParameter("cd_grupo_contratante", cd_grupo_contratante);
			
			resultado = (GrupoContratante) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public GrupoContratante buscaGruposContratanteCrC(Integer cdRamoCot)
	{
		GrupoContratante resultado;
		String sql = "select * from grupo_contratante_tbl "
				+ "where estado_grupo_contratante = 'A' and cd_grupo_contratante in ( "
				+ " select distinct cd_grupo_contratante from ramo_cotizacion_tbl where cd_ramo_cotizacion = "+cdRamoCot
				+" )";
		
		try
		{
			Query query = em.createNativeQuery(sql,GrupoContratante.class);
			
			resultado = (GrupoContratante) query.getSingleResult();
			
			return resultado;
		}
		catch(Exception ex)
		{
			resultado = new GrupoContratante();
			resultado.setNombre_corto_grupo_contratante("Sin Grupo Contratante");
			return resultado;
		}
	}
}
