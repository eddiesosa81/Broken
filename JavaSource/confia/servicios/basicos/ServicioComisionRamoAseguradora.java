/**
 * 
 */
package confia.servicios.basicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import confia.entidades.basicos.CoberturasAdicionales;
import confia.entidades.basicos.ComisionRamoAseguradora;
import confia.entidades.basicos.RamoAseguradora;
import confia.entidades.vistas.ComisionRamoAseguradoraView;

/**
 * @author Guido Guerrero
 *
 */

@Stateless
public class ServicioComisionRamoAseguradora {
	@PersistenceContext
	private EntityManager em;

	public ComisionRamoAseguradora ComisionRamoAseguradora(int cd_ramoaseg, int codGrupoEconomico) 
	{ 
		String sql = "select * from COMISIONRAMOASEGURADORA_TBL where CD_RAMOASEG = "+cd_ramoaseg+" and nvl(cd_grupo_contratante,0) ="+codGrupoEconomico;
		System.out.println("SQL -->"+sql);
		 try {
			 Query q = em.createNativeQuery(sql, ComisionRamoAseguradora.class);
			 return (ComisionRamoAseguradora) q.getSingleResult();
			} catch (Exception ex) {
				return null;
			}
		 
	}
	
	public Integer insertarComisionRamoAseguradora(ComisionRamoAseguradora obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public void actualizaComisionRamoAseguradora(ComisionRamoAseguradora obj)
	{
		em.merge(obj);
	}
	
	public void eliminaComisionRamoAseguradora(ComisionRamoAseguradora obj)
	{
		em.remove(em.merge(obj));
	}
}
