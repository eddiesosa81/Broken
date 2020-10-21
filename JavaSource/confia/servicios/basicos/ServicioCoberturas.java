package confia.servicios.basicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.Coberturas;
import confia.entidades.transaccionales.Cotizacion;

@Stateless
public class ServicioCoberturas {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Coberturas> consultaCoberturas() {
		String sql = "select * from cobertura_tbl where estado_cobertura = 'A' order by desc_cobertura";
		try {
			Query query = em.createNativeQuery(sql, Coberturas.class);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

	public Coberturas consultaCoberturasxId(Integer cdCobAseg) {
		String sql = "select * from cobertura_tbl where cd_cobertura = (select cd_cobertura from aseguradoracobertura_tbl where cd_asegcob = "
				+ cdCobAseg + " )";
		try {
			Query query = em.createNativeQuery(sql, Coberturas.class);
			return (Coberturas) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	public void actualizaCoberturas(Coberturas obj) {
		em.merge(obj);
	}

}
