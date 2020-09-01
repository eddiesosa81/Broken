package confia.controladores.transaccionales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.UnselectEvent;

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Ejecutivos;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Ramo;
import confia.entidades.basicos.Subagentes;
import confia.entidades.vistas.ConsultaPolizaView;
import confia.procedures.servicioProcedures;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioEjecutivos;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.vistas.ServicioConsultaPolizaView;

@ViewScoped
@ManagedBean(name = "ControladorRenuevaPoliza")
public class ControladorRenuevaPoliza {
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioSubagentes srvSubagente;
	@EJB
	private ServicioEjecutivos srvEjecutivos;
	@EJB
	private ServicioConsultaPolizaView srvConsultaPolizaView;
	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioRamo srvRamo;
	private servicioProcedures srvProcedimientos;

	private String codGrupoContratante;
	private List<GrupoContratante> listaGrupoContratante;
	public String lscdSubagente;
	public List<Subagentes> lstSubagente;
	public String lscdEjecutivo;
	public List<Ejecutivos> lstEjecutivos;
	public SimpleDateFormat formato;
	public String patron = "dd/MM/yyyy";
	public Date fcDesde;
	public Date fcHasta;
	private List<ConsultaPolizaView> lstConsultaPoliza;
	private List<ConsultaPolizaView> selectedLstConsultaPoliza;
	private String motivoNoRenu;
	private List<ConsultaPolizaView> lstPolSel;
	private List<Clientes> listaClientes;
	private String str_cliente;
	private Integer identificacion_asegurado;
	private Clientes datosClienteSol;
	private String codAseguradora;
	private List<Aseguradoras> listadoAseguradoras;
	private List<Ramo> listadoAsegRamo;
	private String[] selectedRamos;

	public ControladorRenuevaPoliza() {
		listaGrupoContratante = new ArrayList<GrupoContratante>();
		lstSubagente = new ArrayList<Subagentes>();
		lstEjecutivos = new ArrayList<Ejecutivos>();
		formato = new SimpleDateFormat(patron);
		fcDesde = new Date();
		fcHasta = new Date();
		srvProcedimientos = new servicioProcedures();
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();
		datosClienteSol = new Clientes();
		codAseguradora = "";
		listadoAseguradoras = new ArrayList<Aseguradoras>();
		listadoAsegRamo = new ArrayList<Ramo>();
	}

	@PostConstruct
	public void recuperaInicio() {
		listaGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		lstSubagente = srvSubagente.recuperaSubagente();
		lstEjecutivos = srvEjecutivos.listaEjecutivos();
		motivoNoRenu = "";
		codGrupoContratante = "%";
		lscdEjecutivo = "%";
		lscdSubagente = "%";
		listadoAseguradoras = srvAseguradoras.listaAseguradoras();
		listadoAsegRamo = srvRamo.listaRamos();
	}

	public void consultar() {
		int numRamo= 0;
		String ramosSel="";
		String coma=",";
		String codClie;
		for (String ramos : selectedRamos) {
			System.out.println("ramo Array:"+ramos);
			ramosSel = ramosSel + ramos.concat(coma);
			System.out.println("ramosSel:"+ramosSel);
		}
		try{
			ramosSel = ramosSel.substring(0, ramosSel.length()-1);
		}catch (Exception e) {
			ramosSel = "%";
		}
		System.out.println("ramo:"+ramosSel);
		System.out.println("aseguradora:"+codAseguradora);
		try {
			if(datosClienteSol.getCd_cliente() != null) {
				codClie = String.valueOf(datosClienteSol.getCd_cliente());
			}else{
				codClie = "%";
			}
			
		} catch (Exception e) {
			codClie = "%";
		}
		
		System.out.println("cliente:"+codClie);
		
		String asFechaDesde, asFechaHasta;
		asFechaDesde = formato.format(fcDesde);
		asFechaHasta = formato.format(fcHasta);
		System.out.println("lscdEjecutivo" + lscdEjecutivo);
		System.out.println("lscdSubagente" + lscdSubagente);
		System.out.println("codGrupoContratante" + codGrupoContratante);

		lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasRenovacion(lscdEjecutivo, codGrupoContratante,
				lscdSubagente, asFechaDesde, asFechaHasta,codAseguradora,ramosSel,codClie);

	}

	public void renuevaPol() {
		Integer ll_cod = 0, ll_comp = 0;
		String res;
		lstPolSel = new ArrayList<ConsultaPolizaView>();
		lstPolSel = selectedLstConsultaPoliza;
		if (lstPolSel.size() == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Póliza"));
			return;
		}
		res = "";
		Integer ll_fecha_actual_jul, ll_fecha_renovacion;
		for (ConsultaPolizaView consultaPolizaView : lstPolSel) {

			ll_fecha_renovacion = Integer.valueOf(consultaPolizaView.getFc_vig_fin_jul()) - 30;
			ll_fecha_actual_jul = Integer.valueOf(srvProcedimientos.fechaJulianaActual());

			ll_cod = 0;
			ll_comp = 0;
			ll_cod = Integer.valueOf(consultaPolizaView.getCd_cotizacion());
			ll_comp = Integer.valueOf(consultaPolizaView.getCd_compania());
			res = res.concat("-");
			// solo puede renovar 30 dia antes del fin de vigencia
			// if (ll_fecha_actual_jul <= ll_fecha_renovacion) {
			res = res.concat(srvProcedimientos.renuevaPoliza(consultaPolizaView.getCd_cotizacion(),
					consultaPolizaView.getCd_compania()));
			// }
			System.out.println(res);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Número Renovaciones Generadas:" + res));
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();
	}

	public void noRenueva() {
		lstPolSel = new ArrayList<ConsultaPolizaView>();
		lstPolSel = selectedLstConsultaPoliza;
		if (lstPolSel.size() == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Póliza "));
			return;
		}

		motivoNoRenu = "";
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('noRenueva').show();");
		PrimeFaces.current().executeScript("PF('noRenueva').show();");
	}

	public void procesaNoRenueva() {
		System.out.println("MOTIVO:" + motivoNoRenu);
		if (motivoNoRenu.equals("")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Motivo"));
			return;
		}
		for (ConsultaPolizaView auxLst : lstPolSel) {
			srvProcedimientos.act_estado_no_renovado(auxLst.getCd_cotizacion(), motivoNoRenu, auxLst.getCd_compania());
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();

		// RequestContext context2 = RequestContext.getCurrentInstance();
		// context2.execute("PF('noRenueva').hide();");
		PrimeFaces.current().executeScript("PF('noRenueva').hide();");

	}

	public void buscarClientes() {
		System.out.println("INGRESO EL CLIENTE");
		if (str_cliente == null) {
			str_cliente = "";
		}
		listaClientes = srvClientes.listaClientes(str_cliente.toUpperCase());
	}

	public void buscarAseguradosXId() {
		System.out.println("INGRESOOOO CLIENTE ASEGURADO");
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		identificacion_asegurado = Integer.parseInt(requestParameterMap.get("identificacion_asegurado"));

		datosClienteSol = srvClientes.listaClientesXId(identificacion_asegurado);

		try {
			datosClienteSol.setRazon_social_cliente(datosClienteSol.getRazon_social_cliente().trim());
		} catch (Exception ex) {
			//
		}

		if (datosClienteSol.getRazon_social_cliente() == null || datosClienteSol.getRazon_social_cliente().isEmpty()) {
			datosClienteSol.setRazon_social_cliente(
					datosClienteSol.getPrimer_nombre_cliente() + ' ' + datosClienteSol.getPrimer_apellido_cliente());
		}
		str_cliente = "";
	}

	public void onItemUnselect(UnselectEvent event) {
//		FacesContext context = FacesContext.getCurrentInstance();
//		FacesMessage msg = new FacesMessage();
//		msg.setSummary("Item unselected: " + event.getObject().toString());
//		msg.setSeverity(FacesMessage.SEVERITY_INFO);
//		context.addMessage(null, msg);
		System.out.println("Item unselected:"+ event.getObject().toString());
	}

	public String getCodGrupoContratante() {
		return codGrupoContratante;
	}

	public void setCodGrupoContratante(String codGrupoContratante) {
		this.codGrupoContratante = codGrupoContratante;
	}

	public List<GrupoContratante> getListaGrupoContratante() {
		return listaGrupoContratante;
	}

	public void setListaGrupoContratante(List<GrupoContratante> listaGrupoContratante) {
		this.listaGrupoContratante = listaGrupoContratante;
	}

	public String getLscdSubagente() {
		return lscdSubagente;
	}

	public void setLscdSubagente(String lscdSubagente) {
		this.lscdSubagente = lscdSubagente;
	}

	public List<Subagentes> getLstSubagente() {
		return lstSubagente;
	}

	public void setLstSubagente(List<Subagentes> lstSubagente) {
		this.lstSubagente = lstSubagente;
	}

	public String getLscdEjecutivo() {
		return lscdEjecutivo;
	}

	public void setLscdEjecutivo(String lscdEjecutivo) {
		this.lscdEjecutivo = lscdEjecutivo;
	}

	public List<Ejecutivos> getLstEjecutivos() {
		return lstEjecutivos;
	}

	public void setLstEjecutivos(List<Ejecutivos> lstEjecutivos) {
		this.lstEjecutivos = lstEjecutivos;
	}

	public Date getFcDesde() {
		return fcDesde;
	}

	public void setFcDesde(Date fcDesde) {
		this.fcDesde = fcDesde;
	}

	public Date getFcHasta() {
		return fcHasta;
	}

	public void setFcHasta(Date fcHasta) {
		this.fcHasta = fcHasta;
	}

	public List<ConsultaPolizaView> getLstConsultaPoliza() {
		return lstConsultaPoliza;
	}

	public void setLstConsultaPoliza(List<ConsultaPolizaView> lstConsultaPoliza) {
		this.lstConsultaPoliza = lstConsultaPoliza;
	}

	public List<ConsultaPolizaView> getSelectedLstConsultaPoliza() {
		return selectedLstConsultaPoliza;
	}

	public void setSelectedLstConsultaPoliza(List<ConsultaPolizaView> selectedLstConsultaPoliza) {
		this.selectedLstConsultaPoliza = selectedLstConsultaPoliza;
	}

	public String getMotivoNoRenu() {
		return motivoNoRenu;
	}

	public void setMotivoNoRenu(String motivoNoRenu) {
		this.motivoNoRenu = motivoNoRenu;
	}

	public Clientes getDatosClienteSol() {
		return datosClienteSol;
	}

	public void setDatosClienteSol(Clientes datosClienteSol) {
		this.datosClienteSol = datosClienteSol;
	}

	public String getStr_cliente() {
		return str_cliente;
	}

	public void setStr_cliente(String str_cliente) {
		this.str_cliente = str_cliente;
	}

	public List<Clientes> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Clientes> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public String getCodAseguradora() {
		return codAseguradora;
	}

	public void setCodAseguradora(String codAseguradora) {
		this.codAseguradora = codAseguradora;
	}

	public List<Aseguradoras> getListadoAseguradoras() {
		return listadoAseguradoras;
	}

	public void setListadoAseguradoras(List<Aseguradoras> listadoAseguradoras) {
		this.listadoAseguradoras = listadoAseguradoras;
	}

	public List<Ramo> getListadoAsegRamo() {
		return listadoAsegRamo;
	}

	public void setListadoAsegRamo(List<Ramo> listadoAsegRamo) {
		this.listadoAsegRamo = listadoAsegRamo;
	}

	public String[] getSelectedRamos() {
		return selectedRamos;
	}

	public void setSelectedRamos(String[] selectedRamos) {
		this.selectedRamos = selectedRamos;
	}

}
