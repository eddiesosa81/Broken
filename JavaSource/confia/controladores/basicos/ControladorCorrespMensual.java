/**
 * 
 */
package confia.controladores.basicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.TipoModuloCarta;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Correspondencia;
import confia.entidades.transaccionales.Pagos;
import confia.entidades.vistas.PagosCabView;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.transaccionales.ServicioCorrespondencia;
import confia.servicios.transaccionales.ServicioPagos;
import confia.servicios.vistas.ServicioPagosCabView;

@ManagedBean(name = "ControladorCorrespMensual")
@ViewScoped
public class ControladorCorrespMensual extends AbstractReportBean {
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioClientes srvCliente;
	@EJB
	private ServicioUsuarios srvUsuario;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioCorrespondencia srvCorrespond;
	@EJB
	private ServicioPagosCabView srvPagosView;

	private List<Aseguradoras> listAseguradoras;
	private List<Usuarios> listUsuarios;
	private List<Clientes> listClientes;
	private List<Rubros> listRubros;
	private List<Correspondencia> listCorrespon;
	private Correspondencia selectedCorrespond;

	public String lscdAseguradora;
	public String lsusrid;
	public String lscdCliente;
	public String lstipo;
	private String numDoc;
	private List<TipoModuloCarta> lstTipoCarta;
	private List<PagosCabView> lstPagosView;
	private PagosCabView selectedPagosView;
	private String usuarioId, nmUsr;
	private Usuarios usr;
	// private Integer cd_aseguradora;
	// private Integer cd_cliente;
	// private Integer usrid;
	// private Integer cd_rubro;
	// private Integer cd_correspondencia;

	public ControladorCorrespMensual() {
		listAseguradoras = new ArrayList<Aseguradoras>();
		listUsuarios = new ArrayList<Usuarios>();
		listClientes = new ArrayList<Clientes>();
		listRubros = new ArrayList<Rubros>();
		listCorrespon = new ArrayList<Correspondencia>();
		lstTipoCarta = new ArrayList<TipoModuloCarta>();
		lstPagosView = new ArrayList<PagosCabView>();
	}

	@PostConstruct
	public void datosIniciales() {
		listAseguradoras = srvAseguradoras.listaAseguradoras();
		listClientes = srvCliente.listaClientesCorrespon();
		listUsuarios = srvUsuario.listaUsuariosCorrespon();
		lstTipoCarta = srvRubros.recuperaTipoModuloCarta();
		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		System.out.println("USuario Logeado:" + usuarioId);
		usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(usuarioId);
		nmUsr = usr.getUsrnombres();
		nmUsr = nmUsr.concat(" ");
		nmUsr = nmUsr.concat(usr.getUsrapellidos());
	}

	// --------------- PROGRAMACION IMPRESIONES ------------------//
	private String COMPILE_FILE_NAME;
	private Integer codCorrespondencia;

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}

	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		System.out.println("codCorrespondencia: " + codCorrespondencia);
		parametros.put("codCorrespondencia", codCorrespondencia);
		return parametros;

	}

	public String execute() {
		Correspondencia auxDoc = new Correspondencia();
		try {
			if (selectedCorrespond != null) {
				COMPILE_FILE_NAME = selectedCorrespond.getNmReporte().trim();
				codCorrespondencia = selectedCorrespond.getCd_correspondencia();
				auxDoc = srvCorrespond.documentoGenerado(codCorrespondencia);
				auxDoc.setEstado_impresion("SI");
				srvCorrespond.actualizaCorrespondencia(auxDoc);
				System.out.println("---------------------------- NomReporte:" + COMPILE_FILE_NAME);
				try {
					super.prepareReport();
				} catch (Exception e) {
					// logger.error("Error execute Exception "+e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	// -----------------------------------------------------------//

	public void cargarCorrespondencia() {
		String docAux;
		try {
			if (numDoc.isEmpty() || numDoc == null) {
				docAux = "%";
			} else {
				docAux = "%";
				docAux = docAux.concat(numDoc.trim());
				docAux = docAux.concat("%");
			}
		} catch (Exception e) {
			// TODO: handle exception
			docAux = "%";
		}

		listCorrespon = srvCorrespond.buscaCorrespondencia(usuarioId, "%", "%", "MODULO_EMISION_MENSUALIZADO", docAux);
	}
	public void cargarRecibos() {
		lstPagosView = new ArrayList<PagosCabView>();
		lstPagosView = srvPagosView.consultaPagosCab(lscdCliente, lscdAseguradora, lsusrid);
	}


	public List<Aseguradoras> getListAseguradoras() {
		return listAseguradoras;
	}

	public void setListAseguradoras(List<Aseguradoras> listAseguradoras) {
		this.listAseguradoras = listAseguradoras;
	}

	public List<Usuarios> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuarios> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public List<Clientes> getListClientes() {
		return listClientes;
	}

	public void setListClientes(List<Clientes> listClientes) {
		this.listClientes = listClientes;
	}

	public List<Correspondencia> getListCorrespon() {
		return listCorrespon;
	}

	public void setListCorrespon(List<Correspondencia> listCorrespon) {
		this.listCorrespon = listCorrespon;
	}

	public Correspondencia getSelectedCorrespond() {
		return selectedCorrespond;
	}

	public void setSelectedCorrespond(Correspondencia selectedCorrespond) {
		this.selectedCorrespond = selectedCorrespond;
	}

	public String getLstipo() {
		return lstipo;
	}

	public void setLstipo(String lstipo) {
		this.lstipo = lstipo;
	}

	public String getLscdAseguradora() {
		return lscdAseguradora;
	}

	public void setLscdAseguradora(String lscdAseguradora) {
		this.lscdAseguradora = lscdAseguradora;
	}

	public String getLsusrid() {
		return lsusrid;
	}

	public void setLsusrid(String lsusrid) {
		this.lsusrid = lsusrid;
	}

	public String getLscdCliente() {
		return lscdCliente;
	}

	public void setLscdCliente(String lscdCliente) {
		this.lscdCliente = lscdCliente;
	}

	public List<Rubros> getListRubros() {
		return listRubros;
	}

	public void setListRubros(List<Rubros> listRubros) {
		this.listRubros = listRubros;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public List<TipoModuloCarta> getLstTipoCarta() {
		return lstTipoCarta;
	}

	public void setLstTipoCarta(List<TipoModuloCarta> lstTipoCarta) {
		this.lstTipoCarta = lstTipoCarta;
	}

	public List<PagosCabView> getLstPagosView() {
		return lstPagosView;
	}

	public void setLstPagosView(List<PagosCabView> lstPagosView) {
		this.lstPagosView = lstPagosView;
	}

	public PagosCabView getSelectedPagosView() {
		return selectedPagosView;
	}

	public void setSelectedPagosView(PagosCabView selectedPagosView) {
		this.selectedPagosView = selectedPagosView;
	}

}
