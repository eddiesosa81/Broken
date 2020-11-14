package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.DetallePagoCanal;

@Stateless
public class ServicioDetallePagoCanal {
	@PersistenceContext
	private EntityManager em;
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<DetallePagoCanal> detallePAgoCanañ(String codComSuba ) {
		
		String sql = "Select * from detalle_pagos_canal_Tbl where cd_comisuba_pol = "+codComSuba;
  
		System.out.println("************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, DetallePagoCanal.class);
		return q.getResultList();
	}
	
	
	
	public void insertarDetallePagoCanal(DetallePagoCanal obj) {
		em.persist(obj);
	}

	public void actualizaDetallePagoCanal(DetallePagoCanal obj) {
		em.merge(obj);
	}

	public void eliminaDetallePagoCanal(DetallePagoCanal obj) {
		em.remove(em.merge(obj));
	}

}
