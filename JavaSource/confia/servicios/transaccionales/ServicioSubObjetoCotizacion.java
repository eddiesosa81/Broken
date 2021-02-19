package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.entidades.transaccionales.SubObjetoCotizacion;

@Stateless
public class ServicioSubObjetoCotizacion {
	@PersistenceContext
	private EntityManager em;
	

//	public Cotizacion recuperaEvaluacionDesempenoCdg(
//			int cdEvaluacion, int anio) {
//		String sql = "select * from cotizacion where anio = "
//				+ anio + " and cd_evaluacion_d = " + cdEvaluacion;
//		System.out.println("********************-----QUERY: " + sql);
//		Query q = em.createNativeQuery(sql, Cotizacion.class);
//		return (Cotizacion) q.getSingleResult();
//	}
	@SuppressWarnings("unchecked")
	public List<SubObjetoCotizacion> recuperaSubObjCot(int cdObjCo, int cdCompania) {
		String sql = "select * from subobjeto_cotizacion_tbl where cd_compania = "+cdCompania+" and cd_obj_cotizacion = "+cdObjCo;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, SubObjetoCotizacion.class);
		return q.getResultList();
	
	}
	public SubObjetoCotizacion recuperaSubObjCotPros(int cdObjCo, int cdCompania) {
		String sql = "select * from subobjeto_cotizacion_tbl where cd_compania = "+cdCompania+" and cd_obj_cotizacion = "+cdObjCo;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, SubObjetoCotizacion.class);
		return (SubObjetoCotizacion) q.getSingleResult();
	
	}
	
	public Integer insertarSubObjetoCotizacion(SubObjetoCotizacion obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}

	public void actualizaSubObjetoCotizacion(SubObjetoCotizacion obj) {
		em.merge(obj);
	}

	public void eliminaSubObjetoCotizacion(SubObjetoCotizacion obj) {
		em.remove(em.merge(obj));
	}


}
