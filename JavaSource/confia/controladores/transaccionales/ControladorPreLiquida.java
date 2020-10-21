/**
 * 
 */
package confia.controladores.transaccionales;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.PagoDetallePago;
import confia.entidades.transaccionales.ComisionesPoliza;
import confia.entidades.transaccionales.PreFactura;
import confia.entidades.transaccionales.PreFacturaDetalle;
import confia.entidades.vistas.ConsultaFactConifaView;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioPagoDetallePago;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.transaccionales.ServicioComisionesPoliza;
import confia.servicios.transaccionales.ServicioPreFactura;
import confia.servicios.transaccionales.ServicioPreFacturaDetalle;
import confia.servicios.vistas.ServicioConsultaFactConifaView;
import confia.servicios.vistas.ServicioPrefacturarView;

@ViewScoped
@ManagedBean(name = "ControladorPreLiquida")
public class ControladorPreLiquida extends AbstractReportBean {
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioPagoDetallePago srvPagoDetallePago;
	@EJB
	private ServicioPrefacturarView svrPrefacturarView;
	@EJB
	private ServicioComisionesPoliza srvComisionPoliza;
	@EJB
	private ServicioPreFactura srvPreFactura;
	@EJB
	private ServicioPreFacturaDetalle srvPreFacturaDet;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioConsultaFactConifaView srvConsFactConf;

	private String remiteAseguradora;
	private List<Aseguradoras> listAseguradoras;
	private Date remiteFechaHasta;
	private Date remiteFechaDesde;

	private List<PagoDetallePago> listPagoDetallePago;
	private List<PagoDetallePago> filteredPago;
	private List<PagoDetallePago> selectedListPagoDetallePago;
	private PagoDetallePago selectedPagoDetallePago;
	private String passwd;
	private boolean flgEdita;
	private boolean flgSaldarCentavo;
	private boolean flgBotonImprime;
	private boolean flgBotonEdit;
	private String numPreFactPrint;
	private String numFacturaAseg;
	private String numPoliza;

	private List<ConsultaFactConifaView> lstConsultaFacCon;

	private Boolean flgMensualizado;
	private Date fcDesdeMensualizado;
	private Date fcHstasMensualizado;

	public ControladorPreLiquida() {
		listAseguradoras = new ArrayList<Aseguradoras>();
		remiteFechaDesde = new Date();
		remiteFechaHasta = new Date();
		listPagoDetallePago = new ArrayList<PagoDetallePago>();
		flgSaldarCentavo = false;
		numPreFactPrint = "0";
		flgBotonImprime = true;
		flgBotonEdit = true;
		selectedListPagoDetallePago = new ArrayList<PagoDetallePago>();
		lstConsultaFacCon = new ArrayList<ConsultaFactConifaView>();
		fcDesdeMensualizado = new Date();
		fcHstasMensualizado = new Date();
	}

	@PostConstruct
	public void datosIniciales() {
		listAseguradoras = srvAseguradoras.listaAseguradoras();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString = "01-01-2017";
		try {

			Date date = formatter.parse(dateInString);
			remiteFechaDesde = date;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		flgMensualizado = false;
	}

	// --------------- PROGRAMACION IMPRESIONES ------------------//
	private final String COMPILE_FILE_NAME = "preLiquidaComision";

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}

	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codPreFact", numPreFactPrint);
		return parametros;

	}

	public String execute() {
		System.out.println("---------------------------- NUMERO PREFACT:" + numPreFactPrint);
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	// -----------------------------------------------------------//

	public void activaMensualizado() {
		System.out.println("flgMensualizado:" + flgMensualizado);
	}

	public void cargarListaPagoDetallePago() {
		listPagoDetallePago = new ArrayList<PagoDetallePago>();
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		try {
			if (numFacturaAseg.isEmpty() || numFacturaAseg == null) {
				numFacturaAseg = "";
			}
		} catch (Exception e) {
			numFacturaAseg = "";
		}

		try {
			if (numPoliza.isEmpty() || numPoliza == null) {
				numPoliza = "";
			}
		} catch (Exception e) {
			numPoliza = "";
		}
		
		if (numFacturaAseg.equals("") && numPoliza.equals("")) {
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Número de Factura o Póliza"));
			return;
		}
		System.out.println("Ingresooo");
		
		try {
			System.out.println("flgMensualizado:"+flgMensualizado);
			
		} catch (Exception e) {
			flgMensualizado = false;
		}
		if (flgMensualizado) {
			if (numPoliza == "") {
				listPagoDetallePago = srvPagoDetallePago.listarPagosXRemitirMensualizado(remiteAseguradora, numFacturaAseg,fcDesdeMensualizado,fcHstasMensualizado);
			} else {
				listPagoDetallePago = srvPagoDetallePago.listarPagosXRemitirMensualizado(remiteAseguradora, numFacturaAseg,
						numPoliza,fcDesdeMensualizado,fcHstasMensualizado);
			}

		} else {
			
			if (numPoliza == "") {
				listPagoDetallePago = srvPagoDetallePago.listarPagosXRemitir(remiteAseguradora, numFacturaAseg);
			} else {
				listPagoDetallePago = srvPagoDetallePago.listarPagosXRemitir(remiteAseguradora, numFacturaAseg,
						numPoliza);
			}
		}

	}

	public void seleccionarFacturaLista() {
		// valido si el registro ya se incluyo en la lista
		if (selectedListPagoDetallePago.size() > 0) {
			for (PagoDetallePago pagoDetPagoAux : selectedListPagoDetallePago) {
				if (listPagoDetallePago.size() == 1) {
					if (pagoDetPagoAux.getCd_comision_poliza()
							.equals(listPagoDetallePago.get(0).getCd_comision_poliza())) {
						FacesContext fContextObj = FacesContext.getCurrentInstance();
						fContextObj.addMessage(null, new FacesMessage("Advertencia",
								"La Factura No." + pagoDetPagoAux.getFactura_aseg() + " se Encuentra en la Lista."));
						return;
					}
				} else {
					if (pagoDetPagoAux.getCd_comision_poliza()
							.equals(selectedPagoDetallePago.getCd_comision_poliza())) {
						FacesContext fContextObj = FacesContext.getCurrentInstance();
						fContextObj.addMessage(null, new FacesMessage("Advertencia",
								"La Factura No." + pagoDetPagoAux.getFactura_aseg() + " se Encuentra en la Lista."));
						return;
					}
				}
			}
		}

		System.out.println("tama�o lista:" + selectedListPagoDetallePago.size());
		if (listPagoDetallePago.size() == 1) {
			System.out.println(listPagoDetallePago.get(0).getPoliza());
			selectedListPagoDetallePago.add(listPagoDetallePago.get(0));
		} else {
			selectedListPagoDetallePago.add(selectedPagoDetallePago);
		}
		System.out.println("tama�o lista:" + selectedListPagoDetallePago.size());
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null, new FacesMessage("Advertencia", "Registro Añadido Exitosamente."));
	}

	public void visualizaFactSelec() {
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wFactSelected').show();");
		PrimeFaces.current().executeScript("PF('wFactSelected').show();");
	}

	public void controlAcceso() {
		if (flgEdita == true) {
			// RequestContext contextDlg = RequestContext.getCurrentInstance();
			// contextDlg.execute("PF('wControlAcceso').show();");
			PrimeFaces.current().executeScript("PF('wControlAcceso').show();");
			flgBotonEdit = false;
		} else {
			flgBotonEdit = true;
		}
	}

	public void aceptaAcceso() {
		// recupera el usuario logeado
		Integer usrId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO");
		System.out.println("USUARIO:" + usrId);

		Integer acceso;
		acceso = srvRubros.accesoPantalla("105", "PRE LIQUIDACION DE COMISIONES", String.valueOf(usrId), passwd);
		if (acceso.equals(0)) {
			flgEdita = false;
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia",
					"No tiene permisos para ejecutar este proceso o su contraseña es incorrecta."));
			return;
		} else {
			flgEdita = true;
			// RequestContext contextDlg = RequestContext.getCurrentInstance();
			// contextDlg.execute("PF('wControlAcceso').hide();");
			PrimeFaces.current().executeScript("PF('wControlAcceso').hide();");
		}
	}

	public void cancelarAcceso() {
		flgEdita = false;
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wControlAcceso').hide();");
		PrimeFaces.current().executeScript("PF('wControlAcceso').hide();");
	}

	public void onRowEdit(RowEditEvent event) {
		Integer cdComPol, flgComision;
		Double porCom, valCom, salCom, valComOri, valLiqOri, salLiqOri, valLiqCom;
		String ObsPReliq;
		porCom = 0.0;
		valCom = 0.0;
		salCom = 0.0;
		salLiqOri = 0.0;
		valLiqCom = 0.0;
		flgComision = 0;

		if (flgEdita == false) {
			System.out.println("SWITHC:" + flgEdita);
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Active la Edición de Comisiones para realizar este proceso"));
			listPagoDetallePago = new ArrayList<PagoDetallePago>();
			return;
		} else {
			System.out.println("VALOR COMISION:" + ((PagoDetallePago) event.getObject()).getVal_com_brk());
			System.out.println("SALDO COMISION:" + ((PagoDetallePago) event.getObject()).getSal_com_brk());
			System.out.println("VALOR LIQU COMISION:" + ((PagoDetallePago) event.getObject()).getValor_liquidado());
			if (!((PagoDetallePago) event.getObject()).getSal_com_brk()
					.equals(((PagoDetallePago) event.getObject()).getValor_liquidado())) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia",
						"Existen Pagos Realizados. Comuníquese con el Administrador del Sistema"));
				listPagoDetallePago = new ArrayList<PagoDetallePago>();
				return;
			}

			cdComPol = ((PagoDetallePago) event.getObject()).getCd_comision_poliza();
			// datos Originales
			ComisionesPoliza comPolOri = new ComisionesPoliza();
			comPolOri = srvComisionPoliza.comisionesPolizaxCod(cdComPol);
			valComOri = comPolOri.getVal_com_brk();
			valLiqOri = comPolOri.getValorLiquidado();
			salLiqOri = comPolOri.getSaldo_com_brk();
			// Nuevos datos
			porCom = Double.valueOf(((PagoDetallePago) event.getObject()).getPct_com_brk());
			valCom = Double.valueOf(((PagoDetallePago) event.getObject()).getVal_com_brk());
			salCom = Double.valueOf(((PagoDetallePago) event.getObject()).getSal_com_brk());
			valLiqCom = Double.valueOf(((PagoDetallePago) event.getObject()).getValor_liquidado());
			if (valCom != valComOri && salLiqOri < valComOri && valLiqOri.equals(valLiqCom)) {
				// verifico si existe pre liquidaciones realizadas
				flgComision = srvPreFacturaDet.preFacturaRealizada(comPolOri.getCd_comision_poliza());
				if (flgComision > 0) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia",
							"No puede modificar la comisión del broker ya que se encuentran Pre liquidaciones Realizadas"));
					listPagoDetallePago = new ArrayList<PagoDetallePago>();
					return;
				}
			}

			// comparo registros
			System.out.println("------------------------------");
			System.out.println("valCom:" + valCom);
			System.out.println("ValLiqCom:" + valLiqCom);
			System.out.println("salCom:" + salCom);
			System.out.println("------------------------------");
			System.out.println("valComOri:" + valComOri);
			System.out.println("valLiqOri:" + valLiqOri);
			System.out.println("salLiqOri:" + salLiqOri);
			System.out.println("------------------------------");

			if (!valComOri.equals(valCom)) {
				salCom = valCom;
				valLiqCom = valCom;
			} else if (valComOri.equals(valCom)) {
				valLiqOri = valLiqCom;
			} else {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia",
						"Error al Actualizar las comisiones. Comuníquese con el Administrador del Sistema"));
				listPagoDetallePago = new ArrayList<PagoDetallePago>();
				return;
			}

			ComisionesPoliza comAux = new ComisionesPoliza();
			comAux = srvComisionPoliza.comisionesPolizaxCod(cdComPol);
			try {
				ObsPReliq = ((PagoDetallePago) event.getObject()).getObservaciones();
				if (ObsPReliq == null || ObsPReliq.isEmpty()) {
					ObsPReliq = "S/N";
				}

			} catch (Exception e) {
				ObsPReliq = "S/N";
			}

			comAux.setObservaciones(ObsPReliq.trim().toUpperCase());
			comAux.setPct_com_brk(porCom);
			comAux.setVal_com_brk(valCom);
			comAux.setValorLiquidado(valLiqCom);
			comAux.setSaldo_com_brk(salCom);
			srvComisionPoliza.actualizaComisionesPoliza(comAux);
			listPagoDetallePago = new ArrayList<PagoDetallePago>();
			// selectedListPagoDetallePago = new ArrayList<PagoDetallePago>();
			cargarListaPagoDetallePago();
		}

	}

	public void onRowEditValCom(RowEditEvent event) {
		System.out.println("Factura:" + ((PagoDetallePago) event.getObject()).getFactura_aseg());
		System.out.println("MODIFICADO:" + ((PagoDetallePago) event.getObject()).getSal_com_brk());
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void sumaPreliquidacion() {
		Double valPReFac = 0.0;

		for (PagoDetallePago detPagSel : selectedListPagoDetallePago) {
			valPReFac = valPReFac + Double.valueOf(detPagSel.getSal_com_brk());
		}

		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Total de Comisiones Ingresadas:" + redondear(valPReFac)));

	}

	public void guardarPrefacturacion() {
		Double valPReFac = 0.0;
		try {
			if (selectedListPagoDetallePago.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Ingrese registros para Generar la pre liquidación"));
				return;

			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione registros para Generar la pre liquidación"));
			return;
		}
		PreFactura prefac = new PreFactura();
		prefac.setCd_compania(Integer.valueOf(selectedListPagoDetallePago.get(0).getCd_compania()));
		prefac.setCd_aseguradora(Integer.valueOf(selectedListPagoDetallePago.get(0).getCd_aseguradora()));
		if (flgSaldarCentavo == true) {
			prefac.setFlg_saldar_centavo(1);
		} else {
			prefac.setFlg_saldar_centavo(0);
		}
		Integer codPreFac = 0;
		srvPreFactura.insertaPreFactura(prefac);
		codPreFac = srvPreFactura.codigoMaxPreFactura();
		prefac = new PreFactura();
		prefac = srvPreFactura.recuperaPreFacturaPorCodigo(codPreFac);
		Double valCom, valLiqCom, saldoCom, valLiqComAux = 0.0, saldoComAux = 0.0;

		for (PagoDetallePago polSeleccionada : selectedListPagoDetallePago) {
			// realiza calculos de saldos
			valCom = Double.valueOf(polSeleccionada.getVal_com_brk());
			valLiqCom = Double.valueOf(polSeleccionada.getValor_liquidado());
			saldoCom = Double.valueOf(polSeleccionada.getSal_com_brk());
			if (valLiqCom.equals(valCom)) {
				// saldoCom = valCom - ((valCom - saldoCom) + valLiqCom);
				valLiqComAux = 0.0;
			} else {
				valLiqComAux = valLiqCom;
			}
			System.out.println("VALOR COMISION:" + valCom);
			System.out.println("VALOR LIQUIDADO:" + valLiqComAux);
			System.out.println("VALOR SALDO:" + saldoComAux);
			saldoComAux = saldoCom;
			valLiqComAux = valLiqComAux + saldoComAux;
			saldoComAux = valCom - valLiqComAux;

			System.out.println("VALOR LIQUIDADO ACT:" + valLiqComAux);
			System.out.println("VALOR SALDO ACT:" + saldoComAux);

			PreFacturaDetalle detPreFac = new PreFacturaDetalle();
			detPreFac.setCd_compania(prefac.getCd_compania());
			detPreFac.setCd_pre_factura(codPreFac);
			detPreFac.setCd_cliente(Integer.valueOf(polSeleccionada.getCd_cliente()));
			detPreFac.setCd_ramo(Integer.valueOf(polSeleccionada.getCd_ramo()));
			detPreFac.setPoliza(polSeleccionada.getPoliza());
			detPreFac.setFactura(polSeleccionada.getFactura_aseg());
			detPreFac.setTot_prima(Double.valueOf(polSeleccionada.getTotal_prima()));
			detPreFac.setPct_com_broker(polSeleccionada.getPct_com_brk());
			detPreFac.setVal_com_broker(saldoCom);
			detPreFac.setSaldo_com_broker(saldoComAux);
			valPReFac = valPReFac + saldoCom;
			detPreFac.setCd_comision_poliza(polSeleccionada.getCd_comision_poliza());
			srvPreFacturaDet.insertaPreFacturaDetalle(detPreFac);
			// Actualiza flg Comision como facturada
			ComisionesPoliza comAux = new ComisionesPoliza();
			comAux = srvComisionPoliza.comisionesPolizaxCod(polSeleccionada.getCd_comision_poliza());
			comAux.setValorLiquidado(valLiqComAux);
			if (flgSaldarCentavo == true) {
				comAux.setFlg_pre_factura("1");
				comAux.setSaldo_com_brk(0.00);
			} else {
				comAux.setFlg_pre_factura("0");
				comAux.setSaldo_com_brk(saldoComAux);
			}
			srvComisionPoliza.actualizaComisionesPoliza(comAux);
		}
		prefac.setVal_pre_factura(

				redondear(valPReFac));
		String rubroAux = srvRubros.recuperaIva();
		Double iva = Double.valueOf(rubroAux);
		iva = iva / 100;
		iva = redondear(iva);
		System.out.println("IVA-->" + iva);
		System.out.println("Valor-->" + valPReFac);
		valPReFac = (valPReFac * iva) + valPReFac;
		prefac.setVal_pre_iva(redondear(valPReFac));
		prefac.setFlg_factura(0);
		srvPreFactura.actualizaPreFactura(prefac);
		numPreFactPrint = String.valueOf(prefac.getCd_pre_factura());
		flgBotonImprime = false;
		selectedListPagoDetallePago = new ArrayList<PagoDetallePago>();
		listPagoDetallePago = new ArrayList<PagoDetallePago>();
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Transacción Exitosa", "Si desea imprimir el documento presione el boton Imprimir"));
	}

	public void salir() {
		// ExternalContext ctx =
		// FacesContext.getCurrentInstance().getExternalContext();
		// try {
		// ctx.redirect("./index.jsf");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public void recuperaFactConfia(PagoDetallePago aux) {
		lstConsultaFacCon = new ArrayList<ConsultaFactConifaView>();
		lstConsultaFacCon = srvConsFactConf.consultaFactConfia(aux.getPoliza(), aux.getFactura_aseg());
		System.out.println("numreo de reg:" + lstConsultaFacCon.size());
	}

	public void eliminaFactConfia(PagoDetallePago aux) {
		selectedListPagoDetallePago.remove(aux);
	}

	public String getRemiteAseguradora() {
		return remiteAseguradora;
	}

	public void setRemiteAseguradora(String remiteAseguradora) {
		this.remiteAseguradora = remiteAseguradora;
	}

	public List<Aseguradoras> getListAseguradoras() {
		return listAseguradoras;
	}

	public void setListAseguradoras(List<Aseguradoras> listAseguradoras) {
		this.listAseguradoras = listAseguradoras;
	}

	public Date getRemiteFechaHasta() {
		return remiteFechaHasta;
	}

	public void setRemiteFechaHasta(Date remiteFechaHasta) {
		this.remiteFechaHasta = remiteFechaHasta;
	}

	public Date getRemiteFechaDesde() {
		return remiteFechaDesde;
	}

	public void setRemiteFechaDesde(Date remiteFechaDesde) {
		this.remiteFechaDesde = remiteFechaDesde;
	}

	public List<PagoDetallePago> getListPagoDetallePago() {
		return listPagoDetallePago;
	}

	public void setListPagoDetallePago(List<PagoDetallePago> listPagoDetallePago) {
		this.listPagoDetallePago = listPagoDetallePago;
	}

	public List<PagoDetallePago> getSelectedListPagoDetallePago() {
		return selectedListPagoDetallePago;
	}

	public void setSelectedListPagoDetallePago(List<PagoDetallePago> selectedListPagoDetallePago) {
		this.selectedListPagoDetallePago = selectedListPagoDetallePago;
	}

	public List<PagoDetallePago> getFilteredPago() {
		return filteredPago;
	}

	public void setFilteredPago(List<PagoDetallePago> filteredPago) {
		this.filteredPago = filteredPago;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean isFlgEdita() {
		return flgEdita;
	}

	public void setFlgEdita(boolean flgEdita) {
		this.flgEdita = flgEdita;
	}

	public boolean isFlgSaldarCentavo() {
		return flgSaldarCentavo;
	}

	public void setFlgSaldarCentavo(boolean flgSaldarCentavo) {
		this.flgSaldarCentavo = flgSaldarCentavo;
	}

	public String getNumPreFactPrint() {
		return numPreFactPrint;
	}

	public void setNumPreFactPrint(String numPreFactPrint) {
		this.numPreFactPrint = numPreFactPrint;
	}

	public boolean isFlgBotonImprime() {
		return flgBotonImprime;
	}

	public void setFlgBotonImprime(boolean flgBotonImprime) {
		this.flgBotonImprime = flgBotonImprime;
	}

	public String getNumFacturaAseg() {
		return numFacturaAseg;
	}

	public void setNumFacturaAseg(String numFacturaAseg) {
		this.numFacturaAseg = numFacturaAseg;
	}

	public boolean isFlgBotonEdit() {
		return flgBotonEdit;
	}

	public void setFlgBotonEdit(boolean flgBotonEdit) {
		this.flgBotonEdit = flgBotonEdit;
	}

	public PagoDetallePago getSelectedPagoDetallePago() {
		return selectedPagoDetallePago;
	}

	public void setSelectedPagoDetallePago(PagoDetallePago selectedPagoDetallePago) {
		this.selectedPagoDetallePago = selectedPagoDetallePago;
	}

	public String getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	public List<ConsultaFactConifaView> getLstConsultaFacCon() {
		return lstConsultaFacCon;
	}

	public void setLstConsultaFacCon(List<ConsultaFactConifaView> lstConsultaFacCon) {
		this.lstConsultaFacCon = lstConsultaFacCon;
	}

	public Boolean getFlgMensualizado() {
		return flgMensualizado;
	}

	public void setFlgMensualizado(Boolean flgMensualizado) {
		this.flgMensualizado = flgMensualizado;
	}

	public Date getFcDesdeMensualizado() {
		return fcDesdeMensualizado;
	}

	public void setFcDesdeMensualizado(Date fcDesdeMensualizado) {
		this.fcDesdeMensualizado = fcDesdeMensualizado;
	}

	public Date getFcHstasMensualizado() {
		return fcHstasMensualizado;
	}

	public void setFcHstasMensualizado(Date fcHstasMensualizado) {
		this.fcHstasMensualizado = fcHstasMensualizado;
	}

}
