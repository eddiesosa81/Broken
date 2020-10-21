package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.CoberturasAdicionales;

@Stateless
public class ServicioCoberturasAdicionales {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<CoberturasAdicionales> recuperaCoberturasAdcSiniestros(Integer cdCompania, Integer cdObjCot) {
		String sql = "select * from COBERTURAS_ADICIONALES_TBL where cd_compania = "+cdCompania+" and cd_obj_cotizacion = "+ cdObjCot +
					" union " + 
					"select * from COBERTURAS_ADICIONALES_TBL where cd_compania = "+cdCompania+
					" and cd_ubicacion in (select cd_ubicacion from objeto_cotizacion_tbl where cd_obj_cotizacion = "+ cdObjCot+")";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasAdicionales.class);
		return q.getResultList();
	
	}
	@SuppressWarnings("unchecked")
	public List<CoberturasAdicionales> recuperaCoberturasRamoCotizacion(String cdCompania, String cdRamoCot) {
		String sql = "SELECT * FROM COBERTURAS_ADICIONALES_TBL where cd_compania = "+cdCompania+" and cd_ramo_cotizacion = "+cdRamoCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasAdicionales.class);
		return q.getResultList();
	
	}
	
	@SuppressWarnings("unchecked")
	public List<CoberturasAdicionales> recuperaCoberturasUbicacion(Integer cdCompania, Integer cdUbc) {
		String sql = "SELECT * FROM COBERTURAS_ADICIONALES_TBL where cd_compania = "+cdCompania+" and cd_ubicacion = "+cdUbc;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasAdicionales.class);
		return q.getResultList();
	
	}
	@SuppressWarnings("unchecked")
	public List<CoberturasAdicionales> recuperaCoberturasObjeto(Integer cdCompania, Integer cdObjCot) {
		String sql = "SELECT * FROM COBERTURAS_ADICIONALES_TBL where cd_compania = "+cdCompania+" and cd_obj_cotizacion = "+cdObjCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasAdicionales.class);
		return q.getResultList();
	
	}
	@SuppressWarnings("unchecked")
	public List<CoberturasAdicionales> recuperaCoberturasSubObjeto(String cdCompania, String cdSubObjCot) {
		String sql = "SELECT * FROM COBERTURAS_ADICIONALES_TBL where cd_compania = "+cdCompania+" and cd_subobj_cotizacion = "+cdSubObjCot;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CoberturasAdicionales.class);
		return q.getResultList();
	
	}
	
	
	public Integer insertaCoberturasNegocio(CoberturasAdicionales obj)
	{
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
	
	public void actualizaCoberturasNegocio(CoberturasAdicionales obj)
	{
		em.merge(obj);
	}
	
	public void eliminaCoberturasNegocio(CoberturasAdicionales obj)
	{
		em.remove(em.merge(obj));
	}

}
