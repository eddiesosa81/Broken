package confia.servicios.vistas;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.vistas.ConsultaCaractPolView;

@Stateless
public class ServicioConsultaCaractPolView {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public ConsultaCaractPolView consultaCaracteristicaObjPol (String placa , String motor) {
		ConsultaCaractPolView resultado = new ConsultaCaractPolView();
		String sql = "select distinct * from CONSULTA_CARACT_POL_VIEW where placa = '"+placa+"'"+
		" and no_de_motor like '"+motor+"'";
		System.out.println("SQL:"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaCaractPolView.class);
			resultado = (ConsultaCaractPolView) query.getSingleResult();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaCaractPolView> consultaCaracteristicaObjPolPlaca (String placa) {
		List<ConsultaCaractPolView> resultado = new ArrayList<ConsultaCaractPolView>();
		String sql = "select distinct * from CONSULTA_CARACT_POL_VIEW where placa like '"+placa+"'";
		System.out.println("SQL:"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaCaractPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaCaractPolView> consultaCaracteristicaPol (String placa , String motor) {
		List<ConsultaCaractPolView> resultado = new ArrayList<ConsultaCaractPolView>();
		String sql = "select distinct * from CONSULTA_CARACT_POL_VIEW where placa = '"+placa+"'"+
		" and no_de_motor like '"+motor+"'";
		System.out.println("SQL:"+sql);
		try {
			Query query = em.createNativeQuery(sql, ConsultaCaractPolView.class);
			resultado = query.getResultList();
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}


}
