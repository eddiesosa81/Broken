package confia.controladores.transaccionales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Cotizacion;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.FormaPago;
import confia.entidades.transaccionales.ObjetoCotizacion;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.transaccionales.SubObjetoCotizacion;
import confia.entidades.transaccionales.Ubicacion;
import confia.entidades.vistas.EndososProvisionalesView;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.transaccionales.ServicioCotizacion;
import confia.servicios.transaccionales.ServicioFormaPago;
import confia.servicios.transaccionales.ServicioObjetoCotizacion;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.transaccionales.ServicioSubObjetoCotizacion;
import confia.servicios.transaccionales.ServicioUbicacion;
import confia.servicios.vistas.ServicioEndososProvisionales;

@ManagedBean(name = "ControladorAnexoProvisional")
@ViewScoped
public class ControladorAnexoProvisional {
	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioEndososProvisionales srvEndosoProv;
	@EJB
	private ServicioUbicacion srvUbicacion;
	@EJB
	private ServicioSubObjetoCotizacion srvSubObjetoCotizacion;
	@EJB
	private ServicioObjetoCotizacion srvObjetoCotizacion;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioFormaPago srvFormaPago;
	@EJB
	private ServicioUsuarios srvUsuario;
	@EJB
	private ServicioCotizacion srvCotizacion;
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;

	private String tipoAnexoSelec;
	private String numPoliza;
	private String str_cliente;

	private Integer identificacion_cliente;

	private Clientes datosCliente;
	private List<Clientes> listaClientes;
	private List<EndososProvisionalesView> lstCotProvisiol;
	private List<EndososProvisionalesView> selectedlstCotProvisiol;
	private List<Ubicacion> listadoUbicaciones;
	private List<ObjetoCotizacion> lstObjetoConsulta;
	private List<SubObjetoCotizacion> lstSubObjetoConsulta;
	private Ubicacion selectedDatosUbicacion;
	private ObjetoCotizacion selectedObjetoConsulta;
	private String codAseg;

	// variables forma de pago
	private String tpFromaPago;
	private Double frmPagoPrimaTot;
	private Double frmPagoDerechoEmision;
	private Double frmPagoSegCampesino;
	private Double frmPagoOtroValor;
	private Double frmPagoSuperBancos;
	private Double frmPagoIva;
	private Double frmPagoTotal;
	private Double frmPagoCuotaIni;
	private Integer frmPagoNumPago;
	private boolean aplicaIva;
	private boolean calculaIva;
	private boolean btnIncAsis;
	private String polizaAnexoNoCot;
	private String facturaNo;

	public ControladorAnexoProvisional() {
		datosCliente = new Clientes();
		listaClientes = new ArrayList<Clientes>();
	}

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

	public void consultaProv() {

		if (numPoliza.isEmpty() || numPoliza == null) {
			FacesMessage msg = new FacesMessage("Advertencia", "Ingrese el número de póliza");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			if (datosCliente.getCd_cliente() == null) {
				FacesMessage msg = new FacesMessage("Advertencia", "Ingrese Datos del Contratante");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Advertencia", "Ingrese Datos del Contratante");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		System.out.println("datosCliente.getCd_cliente():" + datosCliente.getCd_cliente());
		System.out.println("tipoAnexoSelec:" + tipoAnexoSelec);
		System.out.println("numPoliza:" + numPoliza);
		lstCotProvisiol = new ArrayList<EndososProvisionalesView>();
		lstCotProvisiol = srvEndosoProv.endososProvisionalesCons(String.valueOf(datosCliente.getCd_cliente()),
				tipoAnexoSelec, numPoliza.trim());

	}

	public void onRowSelectRamCot(SelectEvent<EndososProvisionalesView> event) {

		lstObjetoConsulta = new ArrayList<ObjetoCotizacion>();
		lstSubObjetoConsulta = new ArrayList<SubObjetoCotizacion>();
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion
				.listarUbicaciones(((EndososProvisionalesView) event.getObject()).getCd_ramo_cotizacion());

	}

	public void onRowSelectUbc(SelectEvent<Ubicacion> event) {
		lstObjetoConsulta = new ArrayList<ObjetoCotizacion>();
		lstSubObjetoConsulta = new ArrayList<SubObjetoCotizacion>();
		lstObjetoConsulta = srvObjetoCotizacion.recuperaObjetosPorUbicacion(event.getObject().getCd_ubicacion(),
				event.getObject().getCd_compania());

	}

	public void onRowSelectObj(SelectEvent<ObjetoCotizacion> event) {
		lstSubObjetoConsulta = new ArrayList<SubObjetoCotizacion>();
		lstSubObjetoConsulta = srvSubObjetoCotizacion.recuperaSubObjCot(event.getObject().getCd_obj_cotizacion(),
				event.getObject().getCd_compania());
	}

	public void agregaFormaPago() {
		System.out.println("INGRESA A FORMA PAGO ANEXOS");
		int res = 0;
		Double primaTot = 0.0;
		List<FormaPago> lstFrmPago = new ArrayList<FormaPago>();
		List<DetalleFormaPago> lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		// recorro reqistros seleccionados
		for (EndososProvisionalesView anex : selectedlstCotProvisiol) {
			primaTot = primaTot + anex.getTotal_prima();
			codAseg = String.valueOf(anex.getCd_aseguradora());
		}
		// encero Variables
		tpFromaPago = null;
		facturaNo = null;
		frmPagoPrimaTot = 0.0;

		frmPagoOtroValor = 0.0;
		frmPagoIva = 0.0;
		frmPagoTotal = 0.0;
		frmPagoCuotaIni = 0.0;
		frmPagoNumPago = 1;
		frmPagoPrimaTot = primaTot;
		if (frmPagoPrimaTot > 4000) {
			frmPagoDerechoEmision = 9.0;
		}
		if (frmPagoPrimaTot > 2000 && frmPagoPrimaTot <= 4000) {
			frmPagoDerechoEmision = 7.0;
		}
		if (frmPagoPrimaTot > 1000 && frmPagoPrimaTot <= 2000) {
			frmPagoDerechoEmision = 5.0;
		}
		if (frmPagoPrimaTot > 500 && frmPagoPrimaTot <= 1000) {
			frmPagoDerechoEmision = 3.0;
		}
		if (frmPagoPrimaTot > 250 && frmPagoPrimaTot <= 500) {
			frmPagoDerechoEmision = 1.0;
		}
		if (frmPagoPrimaTot <= 250) {
			frmPagoDerechoEmision = 0.5;
		}

		frmPagoSegCampesino = frmPagoPrimaTot * (0.5 / 100);
		frmPagoSegCampesino = redondear(frmPagoSegCampesino);
		frmPagoSuperBancos = frmPagoPrimaTot * (3.5 / 100);
		frmPagoSuperBancos = redondear(frmPagoSuperBancos);
		calculaPagoTotal();

		PrimeFaces.current().executeScript("PF('nuevaFormaPago').show();");
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void calculaPagoTotal() {

		Double Iva, frmPagoSubtotal;
		Iva = Double.valueOf(srvRubros.recuperaIva());
		frmPagoSubtotal = 0.0;
		System.out.println("APLICA IVA:" + aplicaIva);
		System.out.println("VALOR IVA:" + frmPagoIva);
		if (aplicaIva) {
			frmPagoSubtotal = frmPagoPrimaTot + frmPagoSuperBancos + frmPagoDerechoEmision + frmPagoSegCampesino
					+ frmPagoOtroValor;
			if (calculaIva == true) {
				frmPagoIva = 0.00;
			} else {
				frmPagoIva = frmPagoSubtotal * (Iva / 100);
				frmPagoIva = redondear(frmPagoIva);
			}
			frmPagoTotal = frmPagoSubtotal + frmPagoIva;
			System.out.println("INGRESO:frmPagoSubtotal=" + frmPagoSubtotal + " frmPagoIva=" + frmPagoIva);
		} else {
			frmPagoSubtotal = frmPagoPrimaTot + frmPagoSuperBancos + frmPagoDerechoEmision + frmPagoSegCampesino;
			if (calculaIva == true) {
				frmPagoIva = 0.00;
			} else {
				frmPagoIva = frmPagoSubtotal * (Iva / 100);
				frmPagoIva = redondear(frmPagoIva);
			}
			frmPagoTotal = frmPagoSubtotal + frmPagoIva + frmPagoOtroValor;
			System.out.println("INGRESO:frmPagoSubtotal=" + frmPagoSubtotal + " frmPagoIva=" + frmPagoIva
					+ " frmPagoOtroValor=" + frmPagoOtroValor);
		}

		frmPagoTotal = redondear(frmPagoTotal);
	}

	public void guardaFormaPago() {
		FormaPago formaPagoAux = new FormaPago();
		calculaPagoTotal();
		// datos de la compania
		String codigoCompania;
		try {
			codigoCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
					.toString();
		} catch (Exception e) {
			codigoCompania = "1";
		}
		// consulta usuario que ingresa
		String usuarioId, nmUsr, numeroCarta;
		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		System.out.println("GUARDAR CARTA USuario:" + usuarioId);
		Usuarios usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(usuarioId);
		// crear una nueva cotización
		Cotizacion nuevaCot = new Cotizacion();
		Integer codCot;
		nuevaCot.setCd_compania(Integer.parseInt(codigoCompania));
		nuevaCot.setCd_cliente(datosCliente.getCd_cliente());
		nuevaCot.setCd_cliente_asegurado(datosCliente.getCd_cliente());
		nuevaCot.setCd_aseguradora(Integer.parseInt(codAseg));
		nuevaCot.setCd_rubro(950);
		nuevaCot.setFc_ini_cot_date(new Date());
		nuevaCot.setFc_fin_cot_date(new Date());
		nuevaCot.setFact_periodica_cot(0);
		nuevaCot.setUsrid(usr.getUsrid());
		srvCotizacion.insertarCotizacion(nuevaCot);
		System.out.println("------Ingreso Cotizacion------");
		codCot = srvCotizacion.codigoMaxCotizacion();
		System.out.println("codCot:" + codCot);

		// crear ramos
		formaPagoAux = new FormaPago();
		formaPagoAux.setCd_cotizacion(codCot);
		formaPagoAux.setCd_compania(Integer.parseInt(codigoCompania));
		formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
		formaPagoAux.setSuperBanco_forma_Pago(frmPagoSuperBancos);
		formaPagoAux.setDerecho_Emision_formaPago(frmPagoDerechoEmision);
		formaPagoAux.setSeguroCampesion_forma_Pago(frmPagoSegCampesino);
		formaPagoAux.setOtro_valor_forma_Pago(frmPagoOtroValor);
		formaPagoAux.setIva_Forma_Pago(frmPagoIva);
		formaPagoAux.setTotal_Pago_FormaPago(frmPagoTotal);
		formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
		formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
		formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
		if (aplicaIva) {
			formaPagoAux.setSin_iva(0);
		} else {
			formaPagoAux.setSin_iva(1);
		}

		int res = 0;
		res = srvFormaPago.insertaFormaPago(formaPagoAux);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
			return;
		}
		// ramos de la cotización Provisional
		for (EndososProvisionalesView anex : selectedlstCotProvisiol) {
			System.out.println("Inserta Ramo Cotización");
			// actualizo el número de factura en la póliza original
			RamoCotizacion ramOri = new RamoCotizacion();
			ramOri = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(anex.getCd_ramo_cotizacion(),
					anex.getCd_compania());
			ramOri.setFactura_aseguradora(facturaNo);
			ramOri.setCd_cotizacion(codCot);
			srvRamoCotizacion.actualizaRamoCotizacion(ramOri);
		}

		// elimino la cotización provisional
		for (EndososProvisionalesView anex : selectedlstCotProvisiol) {
			Cotizacion coti = new Cotizacion();
			coti = srvCotizacion.recuperaCotizacionPorCodigo(anex.getCd_cotizacion(), anex.getCd_compania());
			srvCotizacion.eliminaCotizacion(coti);
		}

		tpFromaPago = null;
		frmPagoPrimaTot = 0.0;

		frmPagoOtroValor = 0.0;
		frmPagoIva = 0.0;
		frmPagoTotal = 0.0;
		frmPagoCuotaIni = 0.0;
		frmPagoNumPago = 1;

		lstCotProvisiol = new ArrayList<EndososProvisionalesView>();
		listadoUbicaciones = new ArrayList<Ubicacion>();
		lstObjetoConsulta = new ArrayList<ObjetoCotizacion>();

		PrimeFaces.current().executeScript("PF('nuevaFormaPago').hide();");

	}

	public String getTipoAnexoSelec() {
		return tipoAnexoSelec;
	}

	public void setTipoAnexoSelec(String tipoAnexoSelec) {
		this.tipoAnexoSelec = tipoAnexoSelec;
	}

	public Clientes getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(Clientes datosCliente) {
		this.datosCliente = datosCliente;
	}

	public String getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
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

	public List<EndososProvisionalesView> getLstCotProvisiol() {
		return lstCotProvisiol;
	}

	public void setLstCotProvisiol(List<EndososProvisionalesView> lstCotProvisiol) {
		this.lstCotProvisiol = lstCotProvisiol;
	}

	public List<EndososProvisionalesView> getSelectedlstCotProvisiol() {
		return selectedlstCotProvisiol;
	}

	public void setSelectedlstCotProvisiol(List<EndososProvisionalesView> selectedlstCotProvisiol) {
		this.selectedlstCotProvisiol = selectedlstCotProvisiol;
	}

	public List<Ubicacion> getListadoUbicaciones() {
		return listadoUbicaciones;
	}

	public void setListadoUbicaciones(List<Ubicacion> listadoUbicaciones) {
		this.listadoUbicaciones = listadoUbicaciones;
	}

	public List<ObjetoCotizacion> getLstObjetoConsulta() {
		return lstObjetoConsulta;
	}

	public void setLstObjetoConsulta(List<ObjetoCotizacion> lstObjetoConsulta) {
		this.lstObjetoConsulta = lstObjetoConsulta;
	}

	public List<SubObjetoCotizacion> getLstSubObjetoConsulta() {
		return lstSubObjetoConsulta;
	}

	public void setLstSubObjetoConsulta(List<SubObjetoCotizacion> lstSubObjetoConsulta) {
		this.lstSubObjetoConsulta = lstSubObjetoConsulta;
	}

	public Ubicacion getSelectedDatosUbicacion() {
		return selectedDatosUbicacion;
	}

	public void setSelectedDatosUbicacion(Ubicacion selectedDatosUbicacion) {
		this.selectedDatosUbicacion = selectedDatosUbicacion;
	}

	public ObjetoCotizacion getSelectedObjetoConsulta() {
		return selectedObjetoConsulta;
	}

	public void setSelectedObjetoConsulta(ObjetoCotizacion selectedObjetoConsulta) {
		this.selectedObjetoConsulta = selectedObjetoConsulta;
	}

	public String getTpFromaPago() {
		return tpFromaPago;
	}

	public void setTpFromaPago(String tpFromaPago) {
		this.tpFromaPago = tpFromaPago;
	}

	public Double getFrmPagoPrimaTot() {
		return frmPagoPrimaTot;
	}

	public void setFrmPagoPrimaTot(Double frmPagoPrimaTot) {
		this.frmPagoPrimaTot = frmPagoPrimaTot;
	}

	public Double getFrmPagoDerechoEmision() {
		return frmPagoDerechoEmision;
	}

	public void setFrmPagoDerechoEmision(Double frmPagoDerechoEmision) {
		this.frmPagoDerechoEmision = frmPagoDerechoEmision;
	}

	public Double getFrmPagoSegCampesino() {
		return frmPagoSegCampesino;
	}

	public void setFrmPagoSegCampesino(Double frmPagoSegCampesino) {
		this.frmPagoSegCampesino = frmPagoSegCampesino;
	}

	public Double getFrmPagoOtroValor() {
		return frmPagoOtroValor;
	}

	public void setFrmPagoOtroValor(Double frmPagoOtroValor) {
		this.frmPagoOtroValor = frmPagoOtroValor;
	}

	public Double getFrmPagoSuperBancos() {
		return frmPagoSuperBancos;
	}

	public void setFrmPagoSuperBancos(Double frmPagoSuperBancos) {
		this.frmPagoSuperBancos = frmPagoSuperBancos;
	}

	public Double getFrmPagoIva() {
		return frmPagoIva;
	}

	public void setFrmPagoIva(Double frmPagoIva) {
		this.frmPagoIva = frmPagoIva;
	}

	public Double getFrmPagoTotal() {
		return frmPagoTotal;
	}

	public void setFrmPagoTotal(Double frmPagoTotal) {
		this.frmPagoTotal = frmPagoTotal;
	}

	public Double getFrmPagoCuotaIni() {
		return frmPagoCuotaIni;
	}

	public void setFrmPagoCuotaIni(Double frmPagoCuotaIni) {
		this.frmPagoCuotaIni = frmPagoCuotaIni;
	}

	public Integer getFrmPagoNumPago() {
		return frmPagoNumPago;
	}

	public void setFrmPagoNumPago(Integer frmPagoNumPago) {
		this.frmPagoNumPago = frmPagoNumPago;
	}

	public boolean isAplicaIva() {
		return aplicaIva;
	}

	public void setAplicaIva(boolean aplicaIva) {
		this.aplicaIva = aplicaIva;
	}

	public boolean isCalculaIva() {
		return calculaIva;
	}

	public void setCalculaIva(boolean calculaIva) {
		this.calculaIva = calculaIva;
	}

	public boolean isBtnIncAsis() {
		return btnIncAsis;
	}

	public void setBtnIncAsis(boolean btnIncAsis) {
		this.btnIncAsis = btnIncAsis;
	}

	public String getFacturaNo() {
		return facturaNo;
	}

	public void setFacturaNo(String facturaNo) {
		this.facturaNo = facturaNo;
	}

}
