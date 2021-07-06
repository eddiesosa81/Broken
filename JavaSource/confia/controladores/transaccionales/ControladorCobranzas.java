/**
 * 
 */
package confia.controladores.transaccionales;

import java.io.IOException;
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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import confia.entidades.basicos.AseguradoraRamo;
import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.ComisionRamoAseguradora;
import confia.entidades.basicos.CuotasXCobrar;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.PagoDetallePago;
import confia.entidades.basicos.Ramo;
import confia.entidades.transaccionales.ComisionPoliza;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.DetallePagos;
import confia.entidades.transaccionales.Factura;
import confia.entidades.transaccionales.FacturaDetalle;
import confia.entidades.transaccionales.Pagos;
import confia.entidades.transaccionales.PreFactura;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.transaccionales.Remision;
import confia.entidades.vistas.PrefacturarView;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoraRamo;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioComisionRamoAseguradora;
import confia.servicios.basicos.ServicioCuotasXCobrar;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioPagoDetallePago;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.transaccionales.ServicioComisionPoliza;
import confia.servicios.transaccionales.ServicioDetalleFormaPago;
import confia.servicios.transaccionales.ServicioDetallePagos;
import confia.servicios.transaccionales.ServicioFactura;
import confia.servicios.transaccionales.ServicioPagos;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.transaccionales.ServicioRemision;
import confia.servicios.vistas.ServicioPrefacturarView;

@ManagedBean(name = "ControladorCobranzas")
@ViewScoped
public class ControladorCobranzas {
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioAseguradoraRamo srvAseguradoraRamo;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioCuotasXCobrar srvCuotas;
	@EJB
	private ServicioPagos srvPagos;
	@EJB
	private ServicioPagoDetallePago srvPagoDetallePago;
	@EJB
	private ServicioDetallePagos srvDetallePagos;
	@EJB
	private ServicioComisionPoliza srvComisionPoliza;
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;
	@EJB
	private ServicioComisionRamoAseguradora svrComisionRamoAseguradora;
	@EJB
	private ServicioRemision svrRemision;
	@EJB
	private ServicioPrefacturarView svrPrefacturarView;
	@EJB
	private ServicioFactura svrFactura;
	@EJB
	private ServicioDetalleFormaPago srvDetalleFormaPago;

	private List<Ramo> listRamos;
	private List<Aseguradoras> listAseguradoras;
	private List<AseguradoraRamo> listAseguradoraRamo;
	private List<GrupoContratante> listGrupoContratante;
	private List<Clientes> listaClientes;
	private List<CuotasXCobrar> listaCuotasXCobrar;
	private List<CuotasXCobrar> lstCuotasXCobrar;
	private List<CuotasXCobrar> selectedCuotasXCobrar;
	private List<Pagos> listPagos;
	private List<PagoDetallePago> listPagoDetallePago;
	private List<PagoDetallePago> selectedListPagoRemitir;
	private List<PagoDetallePago> selectedListPagoDetallePago;
	private List<DetallePagos> listDetallePagos;
	private List<ComisionPoliza> listComisionPoliza;
	private List<ComisionPoliza> selectedListComisionPoliza;
	private List<PrefacturarView> listPrefacturarView;
	private List<PrefacturarView> selectedListPrefacturarView;

	private List<PreFactura> listPrefactura;
	private List<PreFactura> selectedListPrefactura;

	private Integer cdRamo;
	private Integer cdAseguradora;
	private Integer remiteAseguradora;
	private Integer cdContratante;
	private Date fechaDesde;
	private Date fechaHasta;
	private Date remiteFechaHasta;
	private Date remiteFechaDesde;
	private String str_cliente;
	private String numPoliza;
	private String numFactura;
	private Integer identificacion_cliente;
	private Double totalCobranza;
	private Boolean todasCond;
	private Double sumaFormasPagos;
	private Double saldoFormasPagos;

	private Clientes datosCliente;
	private Pagos pagos;
	private Pagos pagos1;
	private DetallePagos detallePagos;
	private ComisionPoliza comisionPoliza;
	private RamoCotizacion ramoCotizacion;
	private ComisionRamoAseguradora comisionRamoAseguradora;
	private Remision remision;
	private PrefacturarView prefacturarView;
	private Factura factura;
	private FacturaDetalle facturaDetalle;
	private Double valorRetencion;
	private Integer noRetencion;
	private Double valorPagoT;
	private Double valorPagoTotSel;
	private String numPagoPrint;
	private boolean flgBotonImprime;
	private String tipo;
	private String cruceFacturas;
	private boolean flgFactPerio;
	private boolean flgCruceFactTot;
	private boolean flgCruceFactTotSloMenu;

	public ControladorCobranzas() {
		srvRamo = new ServicioRamo();
		srvAseguradoras = new ServicioAseguradoras();
		srvAseguradoraRamo = new ServicioAseguradoraRamo();
		srvClientes = new ServicioClientes();
		srvCuotas = new ServicioCuotasXCobrar();
		srvPagos = new ServicioPagos();
		srvPagoDetallePago = new ServicioPagoDetallePago();
		srvComisionPoliza = new ServicioComisionPoliza();
		srvRamoCotizacion = new ServicioRamoCotizacion();
		svrComisionRamoAseguradora = new ServicioComisionRamoAseguradora();
		svrRemision = new ServicioRemision();
		svrPrefacturarView = new ServicioPrefacturarView();
		svrFactura = new ServicioFactura();

		listRamos = new ArrayList<Ramo>();
		listAseguradoras = new ArrayList<Aseguradoras>();
		listAseguradoraRamo = new ArrayList<AseguradoraRamo>();
		listGrupoContratante = new ArrayList<GrupoContratante>();
		listaClientes = new ArrayList<Clientes>();
		listaCuotasXCobrar = new ArrayList<CuotasXCobrar>();
		selectedCuotasXCobrar = new ArrayList<CuotasXCobrar>();
		listPagos = new ArrayList<Pagos>();
		listDetallePagos = new ArrayList<DetallePagos>();
		listPagoDetallePago = new ArrayList<PagoDetallePago>();
		selectedListPagoDetallePago = new ArrayList<PagoDetallePago>();
		selectedListPagoRemitir = new ArrayList<PagoDetallePago>();
		listComisionPoliza = new ArrayList<ComisionPoliza>();
		selectedListComisionPoliza = new ArrayList<ComisionPoliza>();
		listPrefacturarView = new ArrayList<PrefacturarView>();
		selectedListPrefacturarView = new ArrayList<PrefacturarView>();
		listPrefactura = new ArrayList<PreFactura>();
		selectedListPrefactura = new ArrayList<PreFactura>();

		fechaDesde = new Date();
		fechaHasta = new Date();

		remiteFechaDesde = new Date();
		remiteFechaHasta = new Date();

		sumaFormasPagos = 0.00;
		saldoFormasPagos = 0.00;

		pagos = new Pagos();
		pagos1 = new Pagos();
		detallePagos = new DetallePagos();
		comisionRamoAseguradora = new ComisionRamoAseguradora();
		prefacturarView = new PrefacturarView();
		factura = new Factura();
		facturaDetalle = new FacturaDetalle();
		lstCuotasXCobrar = new ArrayList<CuotasXCobrar>();

		str_cliente = null;
		numPoliza = null;
		numFactura = null;

		flgBotonImprime = true;
		flgFactPerio = false;
		flgCruceFactTotSloMenu = true;
	}

	@PostConstruct
	public void datosIniciales() {
		listRamos = srvRamo.listaRamos();
		listAseguradoras = srvAseguradoras.listaAseguradoras();
		listGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		listaClientes = srvClientes.listaClientes("");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString = "01-01-2017";
		String dateFinString = "01-01-2024";
		todasCond = false;
		sumaFormasPagos = 0.00;

		try {

			Date date = formatter.parse(dateInString);
			fechaDesde = date;
			remiteFechaDesde = date;
			Date dateFin = formatter.parse(dateFinString);
			fechaHasta = dateFin;
			System.out.println("FECHA HASTA:" + fechaHasta);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void activaSLOMCruceFact() {
		Double valoAux = 0.00;
		System.out.println("TIPO:" + pagos.getForma_pago());
		System.out.println("totalCobranza:" + totalCobranza);
		valoAux = totalCobranza;
		if (valoAux < 0.00) {
			valoAux = valoAux * -1;
		}
		if (pagos.getForma_pago().equals("Cruce de Facturas") && (valoAux > 0.00 && valoAux < 0.06)) {
			flgCruceFactTotSloMenu = false;
		} else {
			flgCruceFactTotSloMenu = true;
			flgCruceFactTot = false;
		}

	}

	// public void cargarListaPagoDetallePago() {
	// String cadenaRemiteAseguradora = "%";
	// if (remiteAseguradora > 0) {
	// cadenaRemiteAseguradora = '%' + remiteAseguradora.toString() + '%';
	// }
	// listPagoDetallePago =
	// srvPagoDetallePago.listarPagosXRemitir(cadenaRemiteAseguradora,
	// remiteFechaDesde,
	// remiteFechaHasta);
	// }

	public void buscarClientes() {
		if (str_cliente == null) {
			str_cliente = "";
		}
		listaClientes = srvClientes.listaClientes(str_cliente.toUpperCase());
	}

	public void buscarClientesXId() {

		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		identificacion_cliente = Integer.parseInt(requestParameterMap.get("identClienteCobranza"));
		System.out.println("INGRESOOOO:" + identificacion_cliente);
		datosCliente = srvClientes.listaClientesXId(identificacion_cliente);

		try {
			datosCliente.setRazon_social_cliente(datosCliente.getRazon_social_cliente().trim());
		} catch (Exception ex) {
			//
		}

		if (datosCliente.getRazon_social_cliente() == null || datosCliente.getRazon_social_cliente().isEmpty()) {
			datosCliente.setRazon_social_cliente(
					datosCliente.getPrimer_nombre_cliente() + ' ' + datosCliente.getPrimer_apellido_cliente());
		}

	}

	public void buscadorCuotas() {
		String clienteCd = "%";
		String poliza = "%";
		String factura = "%";
		String ramoCd = "%";
		String grpContratanteCd = "%";
		String aseguradoraCd = "%";
		int condicion = 0;

		if (todasCond)
			condicion = 1;

		if (cdAseguradora > 0)
			aseguradoraCd = "%" + cdAseguradora.toString() + "%";
		if (cdRamo > 0)
			ramoCd = "%" + cdRamo.toString() + "%";
		if (cdContratante > 0)
			grpContratanteCd = "%" + cdContratante.toString() + "%";
		if (numPoliza == "" || numPoliza == null) {
			poliza = "%";
		} else {
			poliza = "%" + numPoliza + "%";

		}
		if (numFactura == "" || numFactura == null) {
			factura = "%";
		} else {
			factura = "%" + numFactura + "%";

		}
		try {
			if (!datosCliente.getRazon_social_cliente().isEmpty() && datosCliente.getCd_cliente() > 0)
				clienteCd = datosCliente.getCd_cliente().toString();
		} catch (Exception ex) {
			clienteCd = "%";
		}
		if (tipo.equals("MENSUALIZADO")) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia",
					"Para dar el pago diríjase al módulo Cobranzas Mensualizado"));
		}
		if (flgFactPerio == false) {
			listaCuotasXCobrar = srvCuotas.listarCuotasXCobrar(clienteCd, poliza, factura, ramoCd, grpContratanteCd,
					aseguradoraCd, fechaDesde, fechaHasta, condicion, tipo);
		} else {
			listaCuotasXCobrar = srvCuotas.listarCuotasXCobrarPeriodica(clienteCd, poliza, factura, ramoCd,
					grpContratanteCd, aseguradoraCd, fechaDesde, fechaHasta, condicion, tipo);
		}

	}

	public void sumaTotalCobranza() {
		pagos = new Pagos();
		pagos.setBanco("S/N");
		pagos.setCta_cte("S/N");
		totalCobranza = 0.00;
		for (CuotasXCobrar cuotaTot : selectedCuotasXCobrar) {
			totalCobranza = totalCobranza + cuotaTot.getSaldo();
		}
		totalCobranza = redondear(totalCobranza);
		valorPagoT = totalCobranza;
		valorPagoTotSel = valorPagoT;
		saldoFormasPagos = valorPagoT;
		valorRetencion = 0.0;
		listPagos = new ArrayList<Pagos>();
		lstCuotasXCobrar = new ArrayList<CuotasXCobrar>();
		lstCuotasXCobrar = selectedCuotasXCobrar;
		PrimeFaces.current().executeScript("PF('wcobraCuotasCobranza').show()");

	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void sumaTotalFormasPagoCobrar() {

		for (int x = 0; x < listPagos.size(); x++) {
			sumaFormasPagos = sumaFormasPagos + listPagos.get(x).getValor_total_pago();
		}
	}

	public void calcAbono() {
		valorPagoT = saldoFormasPagos - valorRetencion;
	}

	public void reCalcular() {
		Double abonoRetencion;
		// combo bos
		System.out.println("total x cobrar:" + totalCobranza);
		System.out.println("Valor Retencion:" + valorRetencion);
		System.out.println("Abono:" + valorPagoT);
		abonoRetencion = redondear(valorPagoT) + redondear(valorRetencion);
		abonoRetencion = redondear(abonoRetencion);
		System.out.println("ABONO+RETENCION:" + abonoRetencion);
		if (abonoRetencion.equals(totalCobranza)) {
			saldoFormasPagos = 0.00;
			sumaFormasPagos = totalCobranza;
		}
		//
		System.out.println("Total a pagar:" + sumaFormasPagos);
		System.out.println("Total c Cobrar:" + totalCobranza);
		System.out.println("Saldo Pendiente:" + saldoFormasPagos);
		if (saldoFormasPagos > 0.00) {
			System.out.println("ABONO:" + valorPagoT);
			System.out.println("Retencion:" + valorRetencion);
			Double valTemp = 0.0;
			for (Pagos pagoAux : listPagos) {
				valTemp = valTemp + pagoAux.getValor_total_pago();
			}
			System.out.println("Valor lista:" + valTemp);
			sumaFormasPagos = valTemp + valorPagoT + valorRetencion;
			saldoFormasPagos = totalCobranza - sumaFormasPagos;
		}
	}

	public void agregaPago() {
		reCalcular();
		Date fechaPag = new Date();
		int usrcd = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO");
		String usrname = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIONM")
				.toString();

		// pago exclusivo de notas de cr�dito sin cruce y de una sola factura
		if (pagos.getForma_pago().equals("Devolucion NC")) {
			if (listPagos.size() > 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia",
						"No puede ingresar m�s formas de pago para el tipo seleccionado"));
				return;
			}

			if (selectedCuotasXCobrar.size() > 1) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Solo puede seleccionar una Nota de Cr�dito"));
				return;
			}

			pagos.setCd_compania(selectedCuotasXCobrar.get(0).getCd_compania());
			pagos.setCd_aseguradora(selectedCuotasXCobrar.get(0).getCd_aseguradora());
			pagos.setCd_cliente(selectedCuotasXCobrar.get(0).getCd_cliente());

			pagos.setCd_moneda(1);
			pagos.setUsrid(usrcd);
			pagos.setUsrlogin(usrname);
			pagos.setFecha_pago(fechaPag);
			System.out.println("VALOR TOTAL PAGO NC:" + valorPagoT);
			pagos.setValor_total_pago(valorPagoT);
			listPagos.add(pagos);

		} else if (pagos.getForma_pago().equals("Aplica NC")) {
			if (listPagos.size() > 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia",
						"No puede ingresar m�s formas de pago para el tipo seleccionado"));
				return;
			}

			if (valorRetencion > 0.00) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia",
						"Tipo de Pago seleccionado no permite el ingreso de valor de Retenci�n"));
				return;
			}
			if (totalCobranza <= 0.00) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia",
						"Tipo de Pago seleccionado no permite el ingreso de valores negativos de pago "));
				return;
			}

			pagos.setCd_compania(selectedCuotasXCobrar.get(0).getCd_compania());
			pagos.setCd_aseguradora(selectedCuotasXCobrar.get(0).getCd_aseguradora());
			pagos.setCd_cliente(selectedCuotasXCobrar.get(0).getCd_cliente());

			pagos.setCd_moneda(1);
			pagos.setUsrid(usrcd);
			pagos.setUsrlogin(usrname);
			pagos.setFecha_pago(fechaPag);
			pagos.setObs_pago("APLICA NC A LAS CUOTAS");
			System.out.println("APLICA NC A LAS CUOTAS:" + valorPagoT);
			pagos.setValor_total_pago(0.00);
			listPagos.add(pagos);

		} else {

			// verifica que no exita mas pagos
			Boolean flgCruceFActura = false;
			for (Pagos pagoAux : listPagos) {
				if (pagoAux.getForma_pago().equals("Cruce de Facturas")) {
					flgCruceFActura = true;
				}
			}
			if (flgCruceFActura) {
				return;
			}
			// // verifica que los pagos escogidos sean de la misma aseguradora
			int asegIni = 0, asegNext = 0, cont = 1;
			for (CuotasXCobrar coutaAux : selectedCuotasXCobrar) {
				if (cont == 1) {
					asegIni = coutaAux.getCd_aseguradora();
					asegNext = asegIni;
				} else {
					asegNext = coutaAux.getCd_aseguradora();
				}
				if (asegIni != asegNext) {
					break;
				}
				cont++;
			}
			if (asegIni != asegNext) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Seleccione registros de la misma Aseguradora"));

				return;
			}
			if (listPagos.size() == 0) {
				if (valorPagoT > valorPagoTotSel) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia",
							"El Valor de Pago Ingresado es mayor a las cuotas seleccionadas"));
					return;
				}
			} else {
				Double valTemp = 0.0;
				for (Pagos pagoAux : listPagos) {
					valTemp = valTemp + pagoAux.getValor_total_pago();
				}
				valTemp = valTemp + valorPagoT;
				if (valTemp > valorPagoTotSel) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia",
							"El Valor de Pago Ingresado es mayor a las cuotas seleccionadas"));
					return;
				} else if (valTemp < valorPagoTotSel) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia",
							"El Valor de Pago Ingresado es mayor a las cuotas seleccionadas"));
					return;

				}
			}

			pagos.setCd_compania(selectedCuotasXCobrar.get(0).getCd_compania());
			pagos.setCd_aseguradora(selectedCuotasXCobrar.get(0).getCd_aseguradora());
			pagos.setCd_cliente(selectedCuotasXCobrar.get(0).getCd_cliente());

			pagos.setCd_moneda(1);
			pagos.setUsrid(usrcd);
			pagos.setUsrlogin(usrname);
			pagos.setFecha_pago(fechaPag);
			System.out.println("VALOR TOTAL PAGO:" + valorPagoT);
			if (valorPagoT > 0.00 && !pagos.getForma_pago().equals("Cruce de Facturas")) {
				pagos.setValor_total_pago(valorPagoT);
				listPagos.add(pagos);
			}
			System.out.println("VALOR Retencion:" + valorRetencion);
			if (pagos.getForma_pago().equals("Cruce de Facturas")) {
				Pagos pagoRet = new Pagos();
				pagoRet.setCd_compania(selectedCuotasXCobrar.get(0).getCd_compania());
				pagoRet.setCd_aseguradora(selectedCuotasXCobrar.get(0).getCd_aseguradora());
				pagoRet.setCd_cliente(selectedCuotasXCobrar.get(0).getCd_cliente());
				pagoRet.setCd_moneda(1);
				pagoRet.setUsrid(usrcd);
				pagoRet.setUsrlogin(usrname);
				pagoRet.setFecha_pago(fechaPag);
				pagoRet.setForma_pago("Cruce de Facturas");
				cruceFacturas = "Cruce de Facturas";
				pagoRet.setNo_retencion(noRetencion);
				pagoRet.setValor_retencion(valorRetencion);
				pagoRet.setValor_total_pago(valorRetencion);
				listPagos.add(pagoRet);
			} else {
				if (valorRetencion > 0.00) {
					Pagos pagoRet = new Pagos();
					pagoRet.setCd_compania(selectedCuotasXCobrar.get(0).getCd_compania());
					pagoRet.setCd_aseguradora(selectedCuotasXCobrar.get(0).getCd_aseguradora());
					pagoRet.setCd_cliente(selectedCuotasXCobrar.get(0).getCd_cliente());
					pagoRet.setCd_moneda(1);
					pagoRet.setUsrid(usrcd);
					pagoRet.setUsrlogin(usrname);
					pagoRet.setFecha_pago(fechaPag);
					pagoRet.setForma_pago("Retenci�n");
					cruceFacturas = "Retencion";
					pagoRet.setNo_retencion(noRetencion);
					pagoRet.setValor_retencion(valorRetencion);
					pagoRet.setValor_total_pago(valorRetencion);
					listPagos.add(pagoRet);
				} else {
					cruceFacturas = "otros";
				}
			}
		}
		pagos = new Pagos();
	}

	public void guardaCobranza() {
		Boolean flgNcEfect = false;
		Boolean flgAplicaNc = false;
		Integer cdPago = 0;
		Double valTotPago = 0.0;
		Double valorCuota = 0.0;
		Double saldo = 0.0;
		Double saldoPago = 0.0;
		Double totalPago = 0.00;

		for (Pagos pagoAux : listPagos) {
			if (pagoAux.getForma_pago().equals("Devolucion NC")) {
				flgNcEfect = true;
			}
		}

		for (Pagos pagoAux : listPagos) {
			if (pagoAux.getForma_pago().equals("Aplica NC")) {
				flgAplicaNc = true;
			}
		}

		if (flgNcEfect) {
			if (listPagos.size() > 1) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Forma Pago Seleccionada debe ser �nica"));
				return;
			}

			totalPago = sumaFormasPagos;
			saldoPago = redondear(totalPago);
			System.out.println("SALDO:" + saldoPago);
			System.out.println("TOTAL PAGO:" + totalPago);
			if (!saldoPago.equals(totalPago)) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Saldo Pendiente debe ser igual a Cero"));
				return;
			}

			for (Pagos pagoAux : listPagos) {
				cdPago = srvPagos.guardarPago(pagoAux);
				valTotPago = redondear(pagoAux.getValor_total_pago());
				saldoPago = redondear(saldoPago) + valTotPago;
				System.out.println("SALDO PAGO: -------> " + saldoPago);
				// recorro las cuotas que se va a cobrar e inserto en el
				// detalle
				for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
					DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
					System.out.println("IMPRESION:----- cdDETFORMPAG:" + cuotaAux.getCd_det_forma_pago());
					CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
							cuotaAux.getCd_compania());

					valorCuota = CuotasPolizaAux.getSALDO();

					valorCuota = redondear(valorCuota);
					System.out.println("VALORCUOTA --------- :" + valorCuota);
					detallePagos = new DetallePagos();
					DetalleFormaPago detFrmPago = new DetalleFormaPago();
					detallePagos.setCd_pago(cdPago);

					detallePagos.setCd_compania(cuotaAux.getCd_compania());
					detallePagos.setCd_det_forma_pago(cuotaAux.getCd_det_forma_pago());
					detallePagos.setPoliza(cuotaAux.getPoliza());
					detallePagos.setFacturas(cuotaAux.getFactura_aseguradora());
					detallePagos.setSaldo_anterior(CuotasPolizaAux.getSALDO());

					detFrmPago = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
							cuotaAux.getCd_compania());

					System.out.println("INGRESO ------ : valTotPago == valorCuota");
					detallePagos.setSaldo_actual(0.0);
					detallePagos.setValor_pago(valTotPago);
					detFrmPago.setSALDO(0.0);
					detFrmPago.setFLG_PAGO(1);
					valTotPago = 0.00;

					srvDetallePagos.guardarDetallePago(detallePagos);
					srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
				}
			}

		} else if (flgAplicaNc) {
			// Aplicar pago de la nota de cr�dito a la cuota seleccionada no
			// permite dar de baja con pago adicional
			totalPago = 0.00;
			saldoPago = redondear(totalPago);
			Double valorTotalNc = 0.00;
			Boolean flgPagoNCAplic = true;

			for (Pagos pagoAux : listPagos) {
				cdPago = srvPagos.guardarPago(pagoAux);
				valTotPago = redondear(pagoAux.getValor_total_pago());
				saldoPago = redondear(saldoPago);

				// identifico el valor total de las notas de cr�dito a saldar
				for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
					DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
					CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
							cuotaAux.getCd_compania());

					if (CuotasPolizaAux.getSALDO() < 0.00) {
						valorTotalNc = valorTotalNc + CuotasPolizaAux.getSALDO();
					}
				}
				System.out.println("TOTAL NC SALDAR:" + valorTotalNc);
				for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
					if (flgPagoNCAplic) {
						DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
						CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
								cuotaAux.getCd_compania());

						valorCuota = CuotasPolizaAux.getSALDO();
						valorCuota = redondear(valorCuota);
						System.out.println("VALORCUOTA --------- :" + valorCuota);
						detallePagos = new DetallePagos();
						DetalleFormaPago detFrmPago = new DetalleFormaPago();
						detallePagos.setCd_pago(cdPago);
						detallePagos.setCd_compania(cuotaAux.getCd_compania());
						detallePagos.setCd_det_forma_pago(cuotaAux.getCd_det_forma_pago());
						detallePagos.setPoliza(cuotaAux.getPoliza());
						detallePagos.setFacturas(cuotaAux.getFactura_aseguradora());
						detallePagos.setSaldo_anterior(CuotasPolizaAux.getSALDO());
						detFrmPago = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
								cuotaAux.getCd_compania());
						if (valorCuota < 0.00) {
							detallePagos.setSaldo_actual(0.0);
							detallePagos.setValor_pago(valorCuota);
							detFrmPago.setSALDO(0.0);
							detFrmPago.setFLG_PAGO(1);
						} else {
							valorTotalNc = valorTotalNc + valorCuota;
							System.out.println("VALOR TOTAL NC:" + valorTotalNc);
							if (valorTotalNc < 0.00) {
								// aun existe saldo paga en la totalidad la
								// cuota
								detallePagos.setSaldo_actual(0.0);
								detallePagos.setValor_pago(valorCuota);
								detFrmPago.setSALDO(0.0);
								detFrmPago.setFLG_PAGO(1);
							} else {
								detallePagos.setSaldo_actual(valorTotalNc);
								detallePagos.setValor_pago(valorCuota - valorTotalNc);
								detFrmPago.setSALDO(valorTotalNc);
								flgPagoNCAplic = false;
							}
						}
						srvDetallePagos.guardarDetallePago(detallePagos);
						srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
					}
				}
			}
		} else {

			// verifica que no exita mas pagos
			Boolean flgCruceFActura = false, flgFActura = false;
			for (Pagos pagoAux : listPagos) {
				if (pagoAux.getForma_pago().equals("Cruce de Facturas")) {
					flgCruceFActura = true;
				} else {
					flgFActura = true;
				}
			}
			if (flgCruceFActura && flgFActura) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Solo puede generar Cruces de Facturas como forma Pago"));
				return;
			}

			if (saldoFormasPagos < 0.0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Saldo no puede ser menos que cero"));
				return;
			}
			if (listPagos.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia",
						"Ingrese datos a la lista Detalle del Pago, dando clic en el boton Agregar"));
				listaCuotasXCobrar = new ArrayList<CuotasXCobrar>();
//				RequestContext contextDlg = RequestContext.getCurrentInstance();
//				contextDlg.execute("PF('wcobraCuotasCobranza').hide();");
				PrimeFaces.current().executeScript("PF('wcobraCuotasCobranza').hide();");

				return;
			}

			if (flgCruceFactTot) {
				sumaFormasPagos = 0.00;
				totalCobranza = 0.00;
			}
			totalPago = sumaFormasPagos;
			saldoPago = redondear(totalPago);
			System.out.println("SALDO:" + saldoPago);
			System.out.println("CRUCE DE FACTURA:" + cruceFacturas);

			if (cruceFacturas.equals("Cruce de Facturas")) {
				if (flgCruceFactTot) { // cruce de factura con NC mayor al valor
										// de pago
					for (Pagos pagoAux : listPagos) {
						System.out.println("INGRESO CRUCE FACTURA NC TOT");
						cdPago = srvPagos.guardarPago(pagoAux);
						valTotPago = 0.0;
						saldoPago = 0.0;
						// recorro las cuotas que se va a cobrar e inserto
						// en el detalle

						for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
							DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
							System.out.println("IMPRESION:----- cdDETFORMPAG:" + cuotaAux.getCd_det_forma_pago());
							CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(
									cuotaAux.getCd_det_forma_pago(), cuotaAux.getCd_compania());

							valorCuota = CuotasPolizaAux.getSALDO();

							valorCuota = redondear(valorCuota);
							System.out.println("VALORCUOTA --------- :" + valorCuota);
							detallePagos = new DetallePagos();
							DetalleFormaPago detFrmPago = new DetalleFormaPago();
							detallePagos.setCd_pago(cdPago);
							detallePagos.setCd_compania(cuotaAux.getCd_compania());
							detallePagos.setCd_det_forma_pago(cuotaAux.getCd_det_forma_pago());
							detallePagos.setPoliza(cuotaAux.getPoliza());
							detallePagos.setFacturas(cuotaAux.getFactura_aseguradora());
							detallePagos.setSaldo_anterior(CuotasPolizaAux.getSALDO());

							detFrmPago = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
									cuotaAux.getCd_compania());

							System.out.println("INGRESO ------ : Salda Cuotas en Cero");
							System.out.println("INGRESO ------ : Valor Pago: " + detFrmPago.getSALDO());
							detallePagos.setSaldo_actual(0.0);
							detallePagos.setValor_pago(detFrmPago.getSALDO() * -1);

							detFrmPago.setSALDO(0.0);
							detFrmPago.setFLG_PAGO(1);

							valTotPago = 0.00;
							srvDetallePagos.guardarDetallePago(detallePagos);
							srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
						}
					}

				} else {
					// cruce de facturas con valor cero
					if (saldoPago.equals(0.0)) {
						System.out.println("Ingreso a pagar Cruce con nota de credito igual a cero");
						for (Pagos pagoAux : listPagos) {
							cdPago = srvPagos.guardarPago(pagoAux);
							valTotPago = redondear(pagoAux.getValor_total_pago());
							saldoPago = redondear(saldoPago) - valTotPago;
							System.out.println("SALDO PAGO: -------> " + saldoPago);
							// recorro las cuotas que se va a cobrar e inserto
							// en el
							// detalle

							for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
								DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
								System.out.println("IMPRESION:----- cdDETFORMPAG:" + cuotaAux.getCd_det_forma_pago());
								CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(
										cuotaAux.getCd_det_forma_pago(), cuotaAux.getCd_compania());

								valorCuota = CuotasPolizaAux.getSALDO();

								valorCuota = redondear(valorCuota);
								System.out.println("VALORCUOTA --------- :" + valorCuota);
								detallePagos = new DetallePagos();
								DetalleFormaPago detFrmPago = new DetalleFormaPago();
								detallePagos.setCd_pago(cdPago);
								detallePagos.setCd_compania(cuotaAux.getCd_compania());
								detallePagos.setCd_det_forma_pago(cuotaAux.getCd_det_forma_pago());
								detallePagos.setPoliza(cuotaAux.getPoliza());
								detallePagos.setFacturas(cuotaAux.getFactura_aseguradora());
								detallePagos.setSaldo_anterior(CuotasPolizaAux.getSALDO());

								detFrmPago = srvDetalleFormaPago.recuperaDetallexCdFrmPago(
										cuotaAux.getCd_det_forma_pago(), cuotaAux.getCd_compania());

								System.out.println("INGRESO ------ : Salda Cuotas en Cero");
								System.out.println("INGRESO ------ : Valor Pago: " + detFrmPago.getSALDO());
								detallePagos.setSaldo_actual(0.0);
								detallePagos.setValor_pago(detFrmPago.getSALDO() * -1);

								detFrmPago.setSALDO(0.0);
								detFrmPago.setFLG_PAGO(1);

								valTotPago = 0.00;
								srvDetallePagos.guardarDetallePago(detallePagos);
								srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
							}
						}
					} else if (saldoPago < 0.0) {
						FacesContext fContextObj = FacesContext.getCurrentInstance();
						fContextObj.addMessage(null,
								new FacesMessage("Advertencia", "Saldo no puede ser menos que cero"));
						return;
					} else {
						Double sumValCuotaNeg = 0.00;
						for (Pagos pagoAux : listPagos) {
							cdPago = srvPagos.guardarPago(pagoAux);
							valTotPago = redondear(pagoAux.getValor_total_pago());
							saldoPago = redondear(saldoPago) - valTotPago;
							System.out.println("TOTAL COBRANZA: -------> " + totalCobranza);
							// recorro las cuotas que se va a cobrar e inserto
							// en el
							// detalle
							for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
								// sumo lo valores negativos
								DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
								System.out.println("IMPRESION:----- cdDETFORMPAG:" + cuotaAux.getCd_det_forma_pago());
								CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(
										cuotaAux.getCd_det_forma_pago(), cuotaAux.getCd_compania());
								valorCuota = CuotasPolizaAux.getSALDO();
								valorCuota = redondear(valorCuota);
								if (valorCuota < 0.0) {
									sumValCuotaNeg = sumValCuotaNeg + valorCuota;
									sumValCuotaNeg = redondear(sumValCuotaNeg);
								}
							}
							System.out.println("VALORES NEGATIVOS TOTALES:" + sumValCuotaNeg);
							Boolean flgValPos = false;
							for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
								DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
								System.out.println("IMPRESION:----- cdDETFORMPAG:" + cuotaAux.getCd_det_forma_pago());
								CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(
										cuotaAux.getCd_det_forma_pago(), cuotaAux.getCd_compania());

								valorCuota = CuotasPolizaAux.getSALDO();
								valorCuota = redondear(valorCuota);

								System.out.println("VALORCUOTA --------- :" + valorCuota);
								detallePagos = new DetallePagos();
								DetalleFormaPago detFrmPago = new DetalleFormaPago();
								detallePagos.setCd_pago(cdPago);
								detallePagos.setCd_compania(cuotaAux.getCd_compania());
								detallePagos.setCd_det_forma_pago(cuotaAux.getCd_det_forma_pago());
								detallePagos.setPoliza(cuotaAux.getPoliza());
								detallePagos.setFacturas(cuotaAux.getFactura_aseguradora());
								detallePagos.setSaldo_anterior(CuotasPolizaAux.getSALDO());

								detFrmPago = srvDetalleFormaPago.recuperaDetallexCdFrmPago(
										cuotaAux.getCd_det_forma_pago(), cuotaAux.getCd_compania());
								// salda todos los valores negativos
								if (valorCuota <= 0.0) {
									System.out.println("INGRESO ------ : salda todos los valores negativos");
									detallePagos.setSaldo_actual(0.0);
									detallePagos.setValor_pago(detFrmPago.getSALDO() * -1);
									detFrmPago.setSALDO(0.0);
									detFrmPago.setFLG_PAGO(1);
									valTotPago = 0.00;
									srvDetallePagos.guardarDetallePago(detallePagos);
									srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
								} else {
									System.out.println("INGRESO ------ : Cuota + resta el total NC -");
									sumValCuotaNeg = sumValCuotaNeg + detFrmPago.getSALDO();
									sumValCuotaNeg = redondear(sumValCuotaNeg);
									if (sumValCuotaNeg < 0.0) {
										System.out.println("INGRESO ------ : NOTA DE CREDITO");
										detallePagos.setSaldo_actual(0.0);
										detallePagos.setValor_pago(detFrmPago.getSALDO() * -1);
										detFrmPago.setSALDO(0.0);
										detFrmPago.setFLG_PAGO(1);
										valTotPago = 0.00;
										srvDetallePagos.guardarDetallePago(detallePagos);
										srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
									} else {
										if (flgValPos == false) {
											System.out.println(
													"INGRESO ------ : TOTAL NOTA DE CREDITO:" + sumValCuotaNeg);
											detallePagos.setSaldo_actual(sumValCuotaNeg);
											detallePagos.setValor_pago(detFrmPago.getSALDO() - sumValCuotaNeg);
											detFrmPago.setSALDO(sumValCuotaNeg);
											detFrmPago.setFLG_PAGO(1);
											valTotPago = 0.00;
											flgValPos = true;
											srvDetallePagos.guardarDetallePago(detallePagos);
											srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
										}
									}
								}
							}
						}
					}
				}
			} else {
				for (Pagos pagoAux : listPagos) {
					cdPago = srvPagos.guardarPago(pagoAux);
					valTotPago = redondear(pagoAux.getValor_total_pago());
					saldoPago = redondear(saldoPago) - valTotPago;
					System.out.println("SALDO PAGO: -------> " + saldoPago);
					// recorro las cuotas que se va a cobrar e inserto en el
					// detalle
					for (CuotasXCobrar cuotaAux : lstCuotasXCobrar) {
						DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
						System.out.println("IMPRESION:----- cdDETFORMPAG:" + cuotaAux.getCd_det_forma_pago());
						CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
								cuotaAux.getCd_compania());

						valorCuota = CuotasPolizaAux.getSALDO();

						valorCuota = redondear(valorCuota);
						System.out.println("VALORCUOTA --------- :" + valorCuota);
						detallePagos = new DetallePagos();
						DetalleFormaPago detFrmPago = new DetalleFormaPago();
						detallePagos.setCd_pago(cdPago);

						detallePagos.setCd_compania(cuotaAux.getCd_compania());
						detallePagos.setCd_det_forma_pago(cuotaAux.getCd_det_forma_pago());
						detallePagos.setPoliza(cuotaAux.getPoliza());
						detallePagos.setFacturas(cuotaAux.getFactura_aseguradora());
						detallePagos.setSaldo_anterior(CuotasPolizaAux.getSALDO());

						detFrmPago = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
								cuotaAux.getCd_compania());

						if (valTotPago.equals(valorCuota)) {
							System.out.println("INGRESO ------ : valTotPago == valorCuota");
							detallePagos.setSaldo_actual(0.0);
							detallePagos.setValor_pago(valTotPago);
							detFrmPago.setSALDO(0.0);
							detFrmPago.setFLG_PAGO(1);
							valTotPago = 0.00;
						} else if (valTotPago > valorCuota) {
							System.out.println("INGRESO ------ : valTotPago > valorCuota");
							detallePagos.setSaldo_actual(0.0);
							detallePagos.setValor_pago(valorCuota);
							detFrmPago.setSALDO(0.0);
							detFrmPago.setFLG_PAGO(1);
							valTotPago = valTotPago - valorCuota;
						} else if (valTotPago < valorCuota) {
							System.out.println("INGRESO ------ : valTotPago < valorCuota");
							saldo = valorCuota - valTotPago;
							saldo = redondear(saldo);
							detallePagos.setSaldo_actual(saldo);
							detallePagos.setValor_pago(valTotPago);
							detFrmPago.setSALDO(saldo);
							if (saldo <= 0.0) {
								detFrmPago.setFLG_PAGO(1);
							}
							valTotPago = 0.0;
						}
						srvDetallePagos.guardarDetallePago(detallePagos);
						srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
					}
				}
			}
		}
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null, new FacesMessage("Advertencia", "Transacci�n Exitosa"));
	}

	public void remitirPagosAseg() {
		// // selectedListPagoRemitir
		// Date fecha = new Date();
		// String remiCod = fecha.toString();
		// for (PagoDetallePago pdp : selectedListPagoRemitir) {
		// remision = new Remision();
		//
		// remision.setCd_aseguradora(pdp.getCd_aseguradora());
		// remision.setCd_cliente(pdp.getCd_cliente());
		// remision.setCd_compania(1L);
		// remision.setCd_det_pago(pdp.getCd_det_pago());
		// remision.setEstado(1);
		// remision.setFecha(fecha);
		// remision.setObservaciones("Test guardado");
		// remision.setNum_remision(remiCod);
		//
		// svrRemision.guardaRemision(remision);
		// }
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

	public Double getValorRetencion() {
		return valorRetencion;
	}

	public void setValorRetencion(Double valorRetencion) {
		this.valorRetencion = valorRetencion;
	}

	public Integer getNoRetencion() {
		return noRetencion;
	}

	public void setNoRetencion(Integer noRetencion) {
		this.noRetencion = noRetencion;
	}

	public List<Ramo> getListRamos() {
		return listRamos;
	}

	public void setListRamos(List<Ramo> listRamos) {
		this.listRamos = listRamos;
	}

	public List<Aseguradoras> getListAseguradoras() {
		return listAseguradoras;
	}

	public void setListAseguradoras(List<Aseguradoras> listAseguradoras) {
		this.listAseguradoras = listAseguradoras;
	}

	public List<AseguradoraRamo> getListAseguradoraRamo() {
		return listAseguradoraRamo;
	}

	public void setListAseguradoraRamo(List<AseguradoraRamo> listAseguradoraRamo) {
		this.listAseguradoraRamo = listAseguradoraRamo;
	}

	public Integer getCdRamo() {
		return cdRamo;
	}

	public void setCdRamo(Integer cdRamo) {
		this.cdRamo = cdRamo;
	}

	public Integer getCdAseguradora() {
		return cdAseguradora;
	}

	public void setCdAseguradora(Integer cdAseguradora) {
		this.cdAseguradora = cdAseguradora;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public List<GrupoContratante> getListGrupoContratante() {
		return listGrupoContratante;
	}

	public void setListGrupoContratante(List<GrupoContratante> listGrupoContratante) {
		this.listGrupoContratante = listGrupoContratante;
	}

	public List<Clientes> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Clientes> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public String getStr_cliente() {
		return str_cliente;
	}

	public void setStr_cliente(String str_cliente) {
		this.str_cliente = str_cliente;
	}

	public String getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public Clientes getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(Clientes datosCliente) {
		this.datosCliente = datosCliente;
	}

	public Integer getCdContratante() {
		return cdContratante;
	}

	public void setCdContratante(Integer cdContratante) {
		this.cdContratante = cdContratante;
	}

	public List<CuotasXCobrar> getListaCuotasXCobrar() {
		return listaCuotasXCobrar;
	}

	public void setListaCuotasXCobrar(List<CuotasXCobrar> listaCuotasXCobrar) {
		this.listaCuotasXCobrar = listaCuotasXCobrar;
	}

	public Boolean getTodasCond() {
		return todasCond;
	}

	public void setTodasCond(Boolean todasCond) {
		this.todasCond = todasCond;
	}

	public Double getTotalCobranza() {
		return totalCobranza;
	}

	public void setTotalCobranza(Double totalCobranza) {
		this.totalCobranza = totalCobranza;
	}

	public List<Pagos> getListPagos() {
		return listPagos;
	}

	public void setListPagos(List<Pagos> listPagos) {
		this.listPagos = listPagos;
	}

	public Pagos getPagos() {
		return pagos;
	}

	public void setPagos(Pagos pagos) {
		this.pagos = pagos;
	}

	public List<DetallePagos> getListDetallePagos() {
		return listDetallePagos;
	}

	public void setListDetallePagos(List<DetallePagos> listDetallePagos) {
		this.listDetallePagos = listDetallePagos;
	}

	public DetallePagos getDetallePagos() {
		return detallePagos;
	}

	public void setDetallePagos(DetallePagos detallePagos) {
		this.detallePagos = detallePagos;
	}

	public Double getSumaFormasPagos() {
		return sumaFormasPagos;
	}

	public void setSumaFormasPagos(Double sumaFormasPagos) {
		this.sumaFormasPagos = sumaFormasPagos;
	}

	public Double getSaldoFormasPagos() {
		return saldoFormasPagos;
	}

	public void setSaldoFormasPagos(Double saldoFormasPagos) {
		this.saldoFormasPagos = saldoFormasPagos;
	}

	public List<CuotasXCobrar> getSelectedCuotasXCobrar() {
		return selectedCuotasXCobrar;
	}

	public void setSelectedCuotasXCobrar(List<CuotasXCobrar> selectedCuotasXCobrar) {
		this.selectedCuotasXCobrar = selectedCuotasXCobrar;
	}

	public Pagos getPagos1() {
		return pagos1;
	}

	public void setPagos1(Pagos pagos1) {
		this.pagos1 = pagos1;
	}

	public List<PagoDetallePago> getListPagoDetallePago() {
		return listPagoDetallePago;
	}

	public void setListPagoDetallePago(List<PagoDetallePago> listPagoDetallePago) {
		this.listPagoDetallePago = listPagoDetallePago;
	}

	public Integer getRemiteAseguradora() {
		return remiteAseguradora;
	}

	public void setRemiteAseguradora(Integer remiteAseguradora) {
		this.remiteAseguradora = remiteAseguradora;
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

	public List<PagoDetallePago> getSelectedListPagoDetallePago() {
		return selectedListPagoDetallePago;
	}

	public void setSelectedListPagoDetallePago(List<PagoDetallePago> selectedListPagoDetallePago) {
		this.selectedListPagoDetallePago = selectedListPagoDetallePago;
	}

	public ComisionRamoAseguradora getComisionRamoAseguradora() {
		return comisionRamoAseguradora;
	}

	public void setComisionRamoAseguradora(ComisionRamoAseguradora comisionRamoAseguradora) {
		this.comisionRamoAseguradora = comisionRamoAseguradora;
	}

	public List<PagoDetallePago> getSelectedListPagoRemitir() {
		return selectedListPagoRemitir;
	}

	public void setSelectedListPagoRemitir(List<PagoDetallePago> selectedListPagoRemitir) {
		this.selectedListPagoRemitir = selectedListPagoRemitir;
	}

	public List<ComisionPoliza> getListComisionPoliza() {
		return listComisionPoliza;
	}

	public void setListComisionPoliza(List<ComisionPoliza> listComisionPoliza) {
		this.listComisionPoliza = listComisionPoliza;
	}

	public List<ComisionPoliza> getSelectedListComisionPoliza() {
		return selectedListComisionPoliza;
	}

	public void setSelectedListComisionPoliza(List<ComisionPoliza> selectedListComisionPoliza) {
		this.selectedListComisionPoliza = selectedListComisionPoliza;
	}

	public List<PrefacturarView> getListPrefacturarView() {
		return listPrefacturarView;
	}

	public void setListPrefacturarView(List<PrefacturarView> listPrefacturarView) {
		this.listPrefacturarView = listPrefacturarView;
	}

	public List<PrefacturarView> getSelectedListPrefacturarView() {
		return selectedListPrefacturarView;
	}

	public void setSelectedListPrefacturarView(List<PrefacturarView> selectedListPrefacturarView) {
		this.selectedListPrefacturarView = selectedListPrefacturarView;
	}

	public PrefacturarView getPrefacturarView() {
		return prefacturarView;
	}

	public void setPrefacturarView(PrefacturarView prefacturarView) {
		this.prefacturarView = prefacturarView;
	}

	public List<PreFactura> getListPrefactura() {
		return listPrefactura;
	}

	public void setListPrefactura(List<PreFactura> listPrefactura) {
		this.listPrefactura = listPrefactura;
	}

	public List<PreFactura> getSelectedListPrefactura() {
		return selectedListPrefactura;
	}

	public void setSelectedListPrefactura(List<PreFactura> selectedListPrefactura) {
		this.selectedListPrefactura = selectedListPrefactura;
	}

	public Integer getIdentificacion_cliente() {
		return identificacion_cliente;
	}

	public void setIdentificacion_cliente(Integer identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}

	public ComisionPoliza getComisionPoliza() {
		return comisionPoliza;
	}

	public void setComisionPoliza(ComisionPoliza comisionPoliza) {
		this.comisionPoliza = comisionPoliza;
	}

	public RamoCotizacion getRamoCotizacion() {
		return ramoCotizacion;
	}

	public void setRamoCotizacion(RamoCotizacion ramoCotizacion) {
		this.ramoCotizacion = ramoCotizacion;
	}

	public Remision getRemision() {
		return remision;
	}

	public void setRemision(Remision remision) {
		this.remision = remision;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public FacturaDetalle getFacturaDetalle() {
		return facturaDetalle;
	}

	public void setFacturaDetalle(FacturaDetalle facturaDetalle) {
		this.facturaDetalle = facturaDetalle;
	}

	public Double getValorPagoT() {
		return valorPagoT;
	}

	public void setValorPagoT(Double valorPagoT) {
		this.valorPagoT = valorPagoT;
	}

	public boolean isFlgBotonImprime() {
		return flgBotonImprime;
	}

	public void setFlgBotonImprime(boolean flgBotonImprime) {
		this.flgBotonImprime = flgBotonImprime;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isFlgFactPerio() {
		return flgFactPerio;
	}

	public void setFlgFactPerio(boolean flgFactPerio) {
		this.flgFactPerio = flgFactPerio;
	}

	public boolean isFlgCruceFactTot() {
		return flgCruceFactTot;
	}

	public void setFlgCruceFactTot(boolean flgCruceFactTot) {
		this.flgCruceFactTot = flgCruceFactTot;
	}

	public boolean isFlgCruceFactTotSloMenu() {
		return flgCruceFactTotSloMenu;
	}

	public void setFlgCruceFactTotSloMenu(boolean flgCruceFactTotSloMenu) {
		this.flgCruceFactTotSloMenu = flgCruceFactTotSloMenu;
	}

}
