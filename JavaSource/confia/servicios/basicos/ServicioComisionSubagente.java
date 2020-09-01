package confia.servicios.basicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.ComisionSubagente;

@Stateless
public class ServicioComisionSubagente {
	@PersistenceContext
	private EntityManager em;
	
	
	public ComisionSubagente consultaComisionSubagente(Integer codSubaRamo, Integer codAseg, Integer codGrpCom, Integer codPlan){
		String sql = "select * from comisionsubagente_tbl where cd_subagenteramo = "+codSubaRamo
				+" and nvl(cd_aseguradora,0) = "+codAseg
				+" and nvl(cd_grupo_contratante,0) = "+codGrpCom
				+" and nvl(cd_plan,0) = "+codPlan;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComisionSubagente.class);
			return (ComisionSubagente) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	public ComisionSubagente consultaComisionSubagenteXCod(String codComisionsubagente){
		String sql = "select * from comisionsubagente_tbl where cd_comisionsubagente = "+codComisionsubagente;
		System.out.println("SQL -->"+sql);
		try {
			Query query = em.createNativeQuery(sql, ComisionSubagente.class);
			return (ComisionSubagente) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public Integer insertarComisionSubagente(ComisionSubagente obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public void actualizaComisionSubagente(ComisionSubagente obj) {
		em.merge(obj);
	}

	public void eliminaComisionSubagente(ComisionSubagente obj) {
		em.remove(em.merge(obj));
	}

}
