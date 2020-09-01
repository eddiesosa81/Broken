package confia.servicios.transaccionales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.transaccionales.Reservas;
import confia.procedures.ConectarBase;

@Stateless
public class ServiciosReservas {
	@PersistenceContext
	private EntityManager em;
	
	
	public Reservas recuperaReserva(int cdReserva , int cdCompania) {
		String sql = "select * from reservas_tbl where cd_reserva = "+cdReserva+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Reservas.class);
		 return (Reservas) q.getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public List<Reservas> recuperaReservasSinies(int cdSiniestro , int cdCompania) {
		String sql = "select * from reservas_tbl where cd_siniestro = "+cdSiniestro+" and cd_compania = "+cdCompania;
		 System.out.println("********************-----QUERY: " + sql);
		 Query q = em.createNativeQuery(sql, Reservas.class);
		 return q.getResultList();
	}
	
	public Integer insertarReservasSiniestros(Reservas obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public int codigoMaxReserva() {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nvl(max(cd_reserva),1) as cd_reserva from reservas_tbl";
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cd_reserva");
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

	public void actualizaReservasSiniestros(Reservas obj) {
		em.merge(obj);
	}

	public void eliminaReservasSiniestros(Reservas obj) {
		em.remove(em.merge(obj));
	}

}
