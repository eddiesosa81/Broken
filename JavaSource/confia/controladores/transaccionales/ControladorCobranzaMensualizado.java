package confia.controladores.transaccionales;

import java.text.ParseException;
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

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.CuotasXCobrar;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Ramo;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.DetallePagos;
import confia.entidades.transaccionales.Pagos;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioCuotasXCobrar;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.transaccionales.ServicioDetalleFormaPago;
import confia.servicios.transaccionales.ServicioDetallePagos;
import confia.servicios.transaccionales.ServicioPagos;

@ManagedBean(name = "ControladorCobranzaMensualizado")
@ViewScoped
public class ControladorCobranzaMensualizado {
	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioCuotasXCobrar srvCuotas;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioPagos srvPago;
	@EJB
	private ServicioDetalleFormaPago srvDetalleFormaPago;
	@EJB
	private ServicioDetallePagos srvDetallePagos;

	private Clientes datosCliente;
	private String str_cliente;
	private List<Clientes> listaClientes;
	private Integer identificacion_cliente;
	private String numPoliza;
	private Integer cdRamo;
	private List<Ramo> listRamos;
	private Integer cdContratante;
	private List<GrupoContratante> listGrupoContratante;
	private Integer cdAseguradora;
	private List<Aseguradoras> listAseguradoras;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<CuotasXCobrar> listaCuotasXCobrar;
	private List<CuotasXCobrar> selectedCuotasXCobrar;
	private Date remiteFechaHasta;
	private Date remiteFechaDesde;

	public ControladorCobranzaMensualizado() {
		listaClientes = new ArrayList<Clientes>();
		datosCliente = new Clientes();
		listRamos = new ArrayList<Ramo>();
		listGrupoContratante = new ArrayList<GrupoContratante>();
		listAseguradoras = new ArrayList<Aseguradoras>();
		listaCuotasXCobrar = new ArrayList<CuotasXCobrar>();
	}

	@PostConstruct
	public void datosIniciales() {
		listRamos = srvRamo.listaRamos();
		listAseguradoras = srvAseguradoras.listaAseguradoras();
		listGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		listaClientes = srvClientes.listaClientes("");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString = "01-01-2021";
		String dateFinString = "31-01-2021";

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

		try {
			if (!datosCliente.getRazon_social_cliente().isEmpty() && datosCliente.getCd_cliente() > 0)
				clienteCd = datosCliente.getCd_cliente().toString();
		} catch (Exception ex) {
			clienteCd = "%";
		}

		listaCuotasXCobrar = srvCuotas.listarCuotasXCobrarMensualizada(clienteCd, poliza, factura, ramoCd,
				grpContratanteCd, aseguradoraCd, fechaDesde, fechaHasta, condicion, "1");
		
		System.out.println("SalioConsulta");
		System.err.println(listaCuotasXCobrar.size());
	}
	public void valotTotalPago() {
		List<CuotasXCobrar> lstSel = new ArrayList<CuotasXCobrar>();
		try {
			lstSel = selectedCuotasXCobrar;
			if (lstSel.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione los registros a pagar"));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione los registros a pagar"));
			return;
		}
		// calcula el valor total de pago
		Double VapTotPag = 0.0;
		for (CuotasXCobrar cuotasXCobrar : lstSel) {
			VapTotPag = VapTotPag + cuotasXCobrar.getValor();
		}
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null, new FacesMessage("Advertencia", "Total a Pagar: $"+VapTotPag));
		
	}
	public void sumaTotalCobranza() {
		List<CuotasXCobrar> lstSel = new ArrayList<CuotasXCobrar>();
		try {
			lstSel = selectedCuotasXCobrar;
			if (lstSel.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione los registros a pagar"));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione los registros a pagar"));
			return;
		}
		Date fechaPag = new Date();
		int usrcd = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO");
		String usrname = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIONM")
				.toString();
		// verifico que el pago pertenezca a la misma aseguradora y cliente
		int cont = 1;
		Integer clieAnt = 0, asegAnt = 0,clie = 0, aseg = 0;
		for (CuotasXCobrar cuotasXCobrar : lstSel) {
			clie = cuotasXCobrar.getCd_cliente();
			aseg = cuotasXCobrar.getCd_aseguradora();
			if (cont == 1) {
				clieAnt = cuotasXCobrar.getCd_cliente();
				asegAnt = cuotasXCobrar.getCd_aseguradora();
			} else {
				System.out.println("CLIENTE:"+clie);
				System.out.println("CLIENTE ANT:"+clieAnt);
				System.out.println("ASEGURADORA:"+aseg);
				System.out.println("ASEGURADORA ANT:"+asegAnt);
				if ( !aseg.equals(asegAnt)) {
					System.out.println("Seleccione una misma aseguradora");
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione una misma aseguradora"));
					return;
				}

//				if (!clie.equals(clieAnt)) {
//					FacesContext fContextObj = FacesContext.getCurrentInstance();
//					fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el mismo cliente"));
//					return;
//				}
			}
			cont = cont + 1;
		}
		// calcula el valor total de pago
		Double VapTotPag = 0.0;
		for (CuotasXCobrar cuotasXCobrar : lstSel) {
			VapTotPag = VapTotPag + cuotasXCobrar.getValor();
		}

		Pagos pago = new Pagos();
		pago.setCd_compania(1);
		pago.setCd_aseguradora(asegAnt);
		pago.setCd_cliente(clieAnt);
		pago.setValor_total_pago(VapTotPag);
		pago.setFecha_pago(new Date());
		pago.setCd_moneda(1);
		pago.setForma_pago("Contado");
		pago.setUsrid(usrcd);
		pago.setUsrlogin(usrname);

		srvPago.insertarPago(pago);
		Integer codPago = srvPago.codigoMaxPago();
		Double valorCuota;
		// Inserto detalle del pago
		for (CuotasXCobrar cuotaAux : lstSel) {
			DetalleFormaPago CuotasPolizaAux = new DetalleFormaPago();
			CuotasPolizaAux = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
					cuotaAux.getCd_compania());

			valorCuota = CuotasPolizaAux.getSALDO();
			valorCuota = redondear(valorCuota);

			DetallePagos detallePagos = new DetallePagos();
			DetalleFormaPago detFrmPago = new DetalleFormaPago();
			detallePagos.setCd_pago(codPago);
			detallePagos.setCd_compania(cuotaAux.getCd_compania());
			detallePagos.setCd_det_forma_pago(cuotaAux.getCd_det_forma_pago());
			detallePagos.setPoliza(cuotaAux.getPoliza());
			detallePagos.setFacturas(cuotaAux.getFactura_aseguradora());
			detallePagos.setSaldo_anterior(CuotasPolizaAux.getSALDO());
			System.out.println("INGRESO ------ : valTotPago == valorCuota");
			detallePagos.setSaldo_actual(0.0);
			detallePagos.setValor_pago(valorCuota);

			detFrmPago = srvDetalleFormaPago.recuperaDetallexCdFrmPago(cuotaAux.getCd_det_forma_pago(),
					cuotaAux.getCd_compania());
			detFrmPago.setSALDO(0.0);
			detFrmPago.setFLG_PAGO(1);
			srvDetallePagos.guardarDetallePago(detallePagos);
			srvDetalleFormaPago.actualizaDetFormaPago(detFrmPago);
		}
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null, new FacesMessage("Advertencia", "Pago Exitoso"));

	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public String getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Integer getIdentificacion_cliente() {
		return identificacion_cliente;
	}

	public void setIdentificacion_cliente(Integer identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}

	public Integer getCdRamo() {
		return cdRamo;
	}

	public void setCdRamo(Integer cdRamo) {
		this.cdRamo = cdRamo;
	}

	public Clientes getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(Clientes datosCliente) {
		this.datosCliente = datosCliente;
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

	public List<Ramo> getListRamos() {
		return listRamos;
	}

	public void setListRamos(List<Ramo> listRamos) {
		this.listRamos = listRamos;
	}

	public Integer getCdContratante() {
		return cdContratante;
	}

	public void setCdContratante(Integer cdContratante) {
		this.cdContratante = cdContratante;
	}

	public List<GrupoContratante> getListGrupoContratante() {
		return listGrupoContratante;
	}

	public void setListGrupoContratante(List<GrupoContratante> listGrupoContratante) {
		this.listGrupoContratante = listGrupoContratante;
	}

	public Integer getCdAseguradora() {
		return cdAseguradora;
	}

	public void setCdAseguradora(Integer cdAseguradora) {
		this.cdAseguradora = cdAseguradora;
	}

	public List<Aseguradoras> getListAseguradoras() {
		return listAseguradoras;
	}

	public void setListAseguradoras(List<Aseguradoras> listAseguradoras) {
		this.listAseguradoras = listAseguradoras;
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

	public List<CuotasXCobrar> getListaCuotasXCobrar() {
		return listaCuotasXCobrar;
	}

	public void setListaCuotasXCobrar(List<CuotasXCobrar> listaCuotasXCobrar) {
		this.listaCuotasXCobrar = listaCuotasXCobrar;
	}

	public List<CuotasXCobrar> getSelectedCuotasXCobrar() {
		return selectedCuotasXCobrar;
	}

	public void setSelectedCuotasXCobrar(List<CuotasXCobrar> selectedCuotasXCobrar) {
		this.selectedCuotasXCobrar = selectedCuotasXCobrar;
	}

}
