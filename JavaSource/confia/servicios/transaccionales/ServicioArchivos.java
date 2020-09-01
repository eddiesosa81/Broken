package confia.servicios.transaccionales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Archivos;

@Stateless
public class ServicioArchivos {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Archivos> recuperaArchivosCliente(String tipo, String objeto, String desc, String codCliente) {

		String sql = "select * from archivos_tbl where cd_rubro in "
				+ "(select cd_rubro from rubro_tbl where tipo_rubro like '" + tipo + "' and objeto_rubro like '"
				+ objeto + "' and desc_rubro like '" + desc + "')" + " and cd_cliente = "+codCliente;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Archivos.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Archivos> recuperaArchivosCotizacion(String tipo, String objeto, String desc, String codCotiza) {

		String sql = "select * from archivos_tbl where cd_rubro in "
				+ "(select cd_rubro from rubro_tbl where tipo_rubro like '" + tipo + "' and objeto_rubro like '"
				+ objeto + "' and desc_rubro like '" + desc + "')" + " and cd_cotizacion = "+codCotiza;
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Archivos.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Archivos> recuperaArchivosCotizacionTipo(String tipo, String codCotiza, String codCliente) {

		String sql = "select * from archivos_tbl where cd_rubro in "
				+ "(select cd_rubro from rubro_tbl where tipo_rubro like '" + tipo + "')" 
				+ " and to_char(cd_cotizacion) like '"+codCotiza+"'"
				+ " and to_char(cd_cliente) like '"+codCliente+"'"
				;
	
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Archivos.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Archivos> recuperaArchivosCedula(String tipo, String codCotiza, String cedCliente) {

		String sql = "select * from archivos_tbl where cd_rubro in "
				+ "(select cd_rubro from rubro_tbl where tipo_rubro like '" + tipo + "')" 
				+ " and to_char(cd_cotizacion) like '"+codCotiza+"'"
				+ " and cd_cliente in( select cd_cliente from cliente_tbl where identificacion_cliente = '"+cedCliente+"')"
				;
	
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Archivos.class);
		return q.getResultList();
	}

	public void insertaArchivos(Archivos obj) {
		em.persist(obj);
	}

	public void actualizaArchivos(Archivos obj) {
		em.merge(obj);
	}

	public void eliminaArchivos(Archivos obj) {
		em.remove(em.merge(obj));
	}

}
