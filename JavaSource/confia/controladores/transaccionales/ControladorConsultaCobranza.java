package confia.controladores.transaccionales;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.ComisionSubagentePol;
import confia.entidades.transaccionales.ComisionPoliza;
import confia.entidades.transaccionales.ComisionesPoliza;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.Factura;
import confia.entidades.transaccionales.FacturaDetalle;
import confia.entidades.transaccionales.PreFacturaDetalle;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.vistas.ComiSubagenPolView;
import confia.entidades.vistas.ComisionesSubagentesView;
import confia.entidades.vistas.PreFacturaDetalleView;
import confia.procedures.servicioProcedures;
import confia.servicios.transaccionales.ServicioComisionPoliza;
import confia.servicios.transaccionales.ServicioDetalleFormaPago;
import confia.servicios.transaccionales.ServicioFactura;
import confia.servicios.transaccionales.ServicioFacturaDetalle;
import confia.servicios.transaccionales.ServicioFormaPago;
import confia.servicios.transaccionales.ServicioPreFacturaDetalle;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.vistas.ServicioComiSubagenPolView;
import confia.servicios.vistas.ServicioPreFacturaDetalleView;

@ViewScoped
@ManagedBean(name = "ControladorConsultaCobranza")
public class ControladorConsultaCobranza {
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;
	@EJB
	private ServicioFormaPago srvFormPago;
	@EJB
	private ServicioDetalleFormaPago srvDetalleFormaPago;
	@EJB
	private ServicioComisionPoliza srvComisionPoliza;
	@EJB
	private ServicioPreFacturaDetalle srvDetallePreFactura;
	@EJB
	private ServicioFacturaDetalle srvDetalleFactura;
	@EJB
	private ServicioFactura srvFactura;
	@EJB
	private ServicioPreFacturaDetalleView srvPreFactDetView;
	private servicioProcedures srvProcedures;
	@EJB
	private ServicioComiSubagenPolView srvComisionSubagenteView;

	private List<DetalleFormaPago> lstDetalleFromaPago;
	private List<DetalleFormaPago> filteredlstDetalleFromaPago;
	private DetalleFormaPago selectedFormPago;
	private List<RamoCotizacion> lstRamoCotizacion;
	private RamoCotizacion selectedRamoCotizacion;
	private List<ComisionPoliza> lstComisionesPoliza;
	private List<ComiSubagenPolView> lstComisionesCanal;
	private ComisionPoliza selectedComisionesPoliza;
	private ComiSubagenPolView selectedComisionesCanal;
	private List<PreFacturaDetalle> lstDetallePreFactura;
	private PreFacturaDetalle selectedDetallePreFactura;
	private List<Factura> lstDetalleFactura;
	private String crc;
	private String numfact;
	private String poliza;
	private boolean flgFactPerio;
	private String selectedCodPrefact;
	private List<PreFacturaDetalleView> lstPreFactDetaView;
	private ComisionPoliza comisionBrkSeleccted;

	public ControladorConsultaCobranza() {
		lstDetalleFromaPago = new ArrayList<DetalleFormaPago>();
		lstRamoCotizacion = new ArrayList<RamoCotizacion>();
		lstComisionesPoliza = new ArrayList<ComisionPoliza>();
		lstDetallePreFactura = new ArrayList<PreFacturaDetalle>();
		lstDetalleFactura = new ArrayList<Factura>();
		srvProcedures = new servicioProcedures();
		flgFactPerio = false;
		lstPreFactDetaView = new ArrayList<PreFacturaDetalleView>();
		lstComisionesCanal = new ArrayList<ComiSubagenPolView>();
	}

	public void consultaFactura() {
		lstRamoCotizacion = new ArrayList<RamoCotizacion>();
		try {
			if (numfact.isEmpty()) {
				numfact = "%";
			}
		} catch (Exception e) {
			numfact = "%";
		}
		try {
			if (poliza.isEmpty()) {
				poliza = "%";
			}
		} catch (Exception e) {
			poliza = "%";
		}
		try {
			if (crc.isEmpty()) {
				crc = "%";
			}
		} catch (Exception e) {
			crc = "%";
		}
		if (flgFactPerio == false) {
			lstRamoCotizacion = srvRamoCotizacion.ramoCotizacionPolizaFactura(poliza.trim(), numfact.trim(),
					crc.trim());
		} else {
			lstRamoCotizacion = srvRamoCotizacion.ramoCotizacionPolizaFacturaPeriodica(poliza.trim(), numfact.trim(),
					crc.trim());
		}
	}

	public void reversaPol() {
		int res = 0;
		try {
			if (selectedRamoCotizacion.getCd_compania() == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la P�liza a Reversar"));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la P�liza a Reversar"));
			return;
		}
		System.out.println("Poliza a Reversar crc:" + selectedRamoCotizacion.getCd_ramo_cotizacion());
		res = srvProcedures.reversa_poliza_emitida(String.valueOf(selectedRamoCotizacion.getCd_ramo_cotizacion()));
		System.out.println("ERROR:" + res);
		if (res == 1) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Error",
					"No se puede reversar la p�liza. Comun�quese con el Administrador del Sistema"));
			return;
		} else {
			lstRamoCotizacion = new ArrayList<RamoCotizacion>();
		}
	}

	public void reversaPreFactura() {
		Integer factGen = 0;

		System.out.println("INGRESO 1");
		try {
			factGen = lstDetalleFactura.size();
		} catch (Exception e) {
			factGen = 0;
		}
		if (factGen > 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia",
					"No se puede reversar la preFactura ya que existe Facturaci�n emitida"));
			System.out.println("INGRESO 2");
			return;

		} else {
			try {
				if (selectedCodPrefact.isEmpty() || selectedCodPrefact == null) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Error", "Seleccione la pre factura"));
					System.out.println("INGRESO 3");
					return;
				}
			} catch (Exception e) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Error", "Seleccione la pre factura"));
				System.out.println("INGRESO 4");
				return;
			}
			System.out.println("INGRESO 5");
			srvProcedures.reversa_prefactura_sp(selectedCodPrefact);
			System.out.println("INGRESO 6");
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		}
	}

	public void reversaFactura() {
		Integer factGen = 0;

		System.out.println("INGRESO ReversaFact");
		try {
			factGen = lstDetalleFactura.size();
		} catch (Exception e) {
			factGen = 0;
		}
		if (factGen <= 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia",
					"No se puede reversar la Factura ya que No existe Facturaci�n emitida"));
			System.out.println("INGRESO 2");
			return;

		} else {
			for (Factura numfacConfia : lstDetalleFactura) {
				srvProcedures.reversa_factura_sp(numfacConfia.getNum_factura());
			}
			lstDetalleFactura = new ArrayList<Factura>();

			System.out.println("selectedCodPrefact:" + selectedCodPrefact);
			List<FacturaDetalle> lstFactDet = new ArrayList<FacturaDetalle>();
			lstFactDet = srvDetalleFactura.recuperaFacturaDetalle(Integer.valueOf(selectedCodPrefact));
			Integer num = 0;
			try {
				num = lstFactDet.size();
				if (num > 0) {
					for (FacturaDetalle facturaDetalle : lstFactDet) {
						Factura factAux = new Factura();
						try {
							factAux = srvFactura.recuperaFacturaPorCodigo(facturaDetalle.getCd_factura());
							num = 0;
						} catch (Exception e) {
							num = 1;
						}
						if (num.equals(0)) {
							lstDetalleFactura.add(factAux);
						}

					}
				}else{
					lstDetalleFactura = new ArrayList<Factura>();
				}
			} catch (Exception e) {
				lstDetalleFactura = new ArrayList<Factura>();
			}

			FacesContext fContextObj = FacesContext.getCurrentInstance();
			Integer numeroFila = 0;
			numeroFila = lstDetalleFactura.size();
			if (numeroFila.equals(0)) {
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
			} else {
				fContextObj.addMessage(null, new FacesMessage("Comun�quese con el Administrador del Sistema",
						"No se puede reversar la factura debido a que existe comisiones liquidadas en el Canal "));
			}

		}
	}

	public void onRowSelect(SelectEvent event) {
		RamoCotizacion getObj = new RamoCotizacion();
		getObj = (RamoCotizacion) event.getObject();

		lstDetalleFromaPago = new ArrayList<DetalleFormaPago>();
		lstDetalleFromaPago = srvDetalleFormaPago.recuperaDetalleFrmPago(String.valueOf(getObj.getCd_cotizacion()),
				String.valueOf(getObj.getCd_compania()));
		lstComisionesPoliza = new ArrayList<ComisionPoliza>();
		lstComisionesPoliza = srvComisionPoliza.recuperaComisionPol(getObj.getCd_ramo_cotizacion());
		lstDetallePreFactura = new ArrayList<PreFacturaDetalle>();
		lstDetalleFactura = new ArrayList<Factura>();
	}

	public void onRowSelectFrmPago(SelectEvent event) {
		System.out.println("INGRESOOO");
		DetalleFormaPago getObj = new DetalleFormaPago();
		getObj = (DetalleFormaPago) event.getObject();
		lstComisionesPoliza = new ArrayList<ComisionPoliza>();
		lstComisionesPoliza = srvComisionPoliza.recuperaComisionPolDetPag(getObj.getCD_DET_FORMA_PAGO());
		lstDetallePreFactura = new ArrayList<PreFacturaDetalle>();
		lstDetalleFactura = new ArrayList<Factura>();

	}

	public void onRowSelectComPol(SelectEvent event) {
		ComisionPoliza getObj = new ComisionPoliza();
		getObj = (ComisionPoliza) event.getObject();
		comisionBrkSeleccted = new ComisionPoliza();
		comisionBrkSeleccted = getObj;
		lstDetallePreFactura = new ArrayList<PreFacturaDetalle>();
		lstDetallePreFactura = srvDetallePreFactura.recuperaPreFacturaDetalleComPol(getObj.getCd_comision_poliza());
		lstDetalleFactura = new ArrayList<Factura>();
		lstComisionesCanal = new ArrayList<ComiSubagenPolView>();
		lstComisionesCanal = srvComisionSubagenteView.consultaComiCanalComPolView(getObj.getCd_comision_poliza());
		System.out.println("Tamaño:"+lstComisionesCanal.size());
		System.out.println("comisionBrkSeleccted:"+comisionBrkSeleccted.getCd_comision_poliza());
	}

	public void onRowSelectPreFact(SelectEvent event) {
		PreFacturaDetalle getObj = new PreFacturaDetalle();
		getObj = (PreFacturaDetalle) event.getObject();
		selectedCodPrefact = String.valueOf(getObj.getCd_pre_factura());
		List<FacturaDetalle> lstFactDet = new ArrayList<FacturaDetalle>();
		lstFactDet = srvDetalleFactura.recuperaFacturaDetalle(getObj.getCd_pre_factura());
		Integer num = 0;
		try {
			num = lstFactDet.size();
			if (num > 0) {
				lstDetalleFactura = new ArrayList<Factura>();
				for (FacturaDetalle facturaDetalle : lstFactDet) {
					Factura factAux = new Factura();
					factAux = srvFactura.recuperaFacturaPorCodigo(facturaDetalle.getCd_factura());
					lstDetalleFactura.add(factAux);
				}
			}else{
				lstDetalleFactura = new ArrayList<Factura>();
			}
		} catch (Exception e) {
			lstDetalleFactura = new ArrayList<Factura>();
		}
	}
	public void recuperaDetallePreFact() {
		lstPreFactDetaView = new ArrayList<PreFacturaDetalleView>();
		lstPreFactDetaView = srvPreFactDetView.lstPreFacturaDetalleView(selectedDetallePreFactura.getCd_pre_factura());
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('detallePreFact').show()");
		PrimeFaces.current().executeScript("PF('detallePreFact').show()");
	}

	public void calculaComision() {
		try {
			if (selectedRamoCotizacion == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro"));
				return;

			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro"));
			return;
		}

		Integer err = srvProcedures.recalculo_comision_pol(
				String.valueOf(selectedRamoCotizacion.getCd_ramo_cotizacion()),
				String.valueOf(selectedRamoCotizacion.getCd_compania()));
		System.out.println("ERROR=1 :" + err);
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Registro Exitoso Seleccione Nuevamente el Registro"));

	}
	
	public void calculaComisionCanal() {
		try {
			if (comisionBrkSeleccted == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro"));
				return;

			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro"));
			return;
		}

		Integer err = srvProcedures.recalculo_comision_canla(String.valueOf(comisionBrkSeleccted.getCd_comision_poliza()),String.valueOf(comisionBrkSeleccted.getCd_compania()));
		System.out.println("ERROR=1 :" + err);
		if(err == 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Registro Exitoso"));
		}else {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Error Comuníquese con el Administrador del sistemas"));
		}
		

	}

	public String getNumfact() {
		return numfact;
	}

	public void setNumfact(String numfact) {
		this.numfact = numfact;
	}

	public List<DetalleFormaPago> getLstDetalleFromaPago() {
		return lstDetalleFromaPago;
	}

	public void setLstDetalleFromaPago(List<DetalleFormaPago> lstDetalleFromaPago) {
		this.lstDetalleFromaPago = lstDetalleFromaPago;
	}

	public List<RamoCotizacion> getLstRamoCotizacion() {
		return lstRamoCotizacion;
	}

	public void setLstRamoCotizacion(List<RamoCotizacion> lstRamoCotizacion) {
		this.lstRamoCotizacion = lstRamoCotizacion;
	}

	public RamoCotizacion getSelectedRamoCotizacion() {
		return selectedRamoCotizacion;
	}

	public void setSelectedRamoCotizacion(RamoCotizacion selectedRamoCotizacion) {
		this.selectedRamoCotizacion = selectedRamoCotizacion;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public List<ComisionPoliza> getLstComisionesPoliza() {
		return lstComisionesPoliza;
	}

	public void setLstComisionesPoliza(List<ComisionPoliza> lstComisionesPoliza) {
		this.lstComisionesPoliza = lstComisionesPoliza;
	}

	public List<PreFacturaDetalle> getLstDetallePreFactura() {
		return lstDetallePreFactura;
	}

	public void setLstDetallePreFactura(List<PreFacturaDetalle> lstDetallePreFactura) {
		this.lstDetallePreFactura = lstDetallePreFactura;
	}

	public List<Factura> getLstDetalleFactura() {
		return lstDetalleFactura;
	}

	public void setLstDetalleFactura(List<Factura> lstDetalleFactura) {
		this.lstDetalleFactura = lstDetalleFactura;
	}

	public DetalleFormaPago getSelectedFormPago() {
		return selectedFormPago;
	}

	public void setSelectedFormPago(DetalleFormaPago selectedFormPago) {
		this.selectedFormPago = selectedFormPago;
	}

	public ComisionPoliza getSelectedComisionesPoliza() {
		return selectedComisionesPoliza;
	}

	public void setSelectedComisionesPoliza(ComisionPoliza selectedComisionesPoliza) {
		this.selectedComisionesPoliza = selectedComisionesPoliza;
	}

	public PreFacturaDetalle getSelectedDetallePreFactura() {
		return selectedDetallePreFactura;
	}

	public void setSelectedDetallePreFactura(PreFacturaDetalle selectedDetallePreFactura) {
		this.selectedDetallePreFactura = selectedDetallePreFactura;
	}

	public boolean isFlgFactPerio() {
		return flgFactPerio;
	}

	public void setFlgFactPerio(boolean flgFactPerio) {
		this.flgFactPerio = flgFactPerio;
	}

	public List<DetalleFormaPago> getFilteredlstDetalleFromaPago() {
		return filteredlstDetalleFromaPago;
	}

	public void setFilteredlstDetalleFromaPago(List<DetalleFormaPago> filteredlstDetalleFromaPago) {
		this.filteredlstDetalleFromaPago = filteredlstDetalleFromaPago;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public List<PreFacturaDetalleView> getLstPreFactDetaView() {
		return lstPreFactDetaView;
	}

	public void setLstPreFactDetaView(List<PreFacturaDetalleView> lstPreFactDetaView) {
		this.lstPreFactDetaView = lstPreFactDetaView;
	}

	public List<ComiSubagenPolView> getLstComisionesCanal() {
		return lstComisionesCanal;
	}

	public void setLstComisionesCanal(List<ComiSubagenPolView> lstComisionesCanal) {
		this.lstComisionesCanal = lstComisionesCanal;
	}

	public ComiSubagenPolView getSelectedComisionesCanal() {
		return selectedComisionesCanal;
	}

	public void setSelectedComisionesCanal(ComiSubagenPolView selectedComisionesCanal) {
		this.selectedComisionesCanal = selectedComisionesCanal;
	}

}
