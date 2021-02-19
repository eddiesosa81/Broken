package confia.controladores.transaccionales;

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

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.transaccionales.ComisionesPoliza;
import confia.entidades.transaccionales.Factura;
import confia.entidades.transaccionales.FacturaDetalle;
import confia.entidades.transaccionales.PreFactura;
import confia.entidades.transaccionales.PreFacturaDetalle;
import confia.entidades.vistas.PreFacturaDetalleView;
import confia.entidades.vistas.PrefacturarView;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.transaccionales.ServicioComisionesPoliza;
import confia.servicios.transaccionales.ServicioFactura;
import confia.servicios.transaccionales.ServicioFacturaDetalle;
import confia.servicios.transaccionales.ServicioPreFactura;
import confia.servicios.transaccionales.ServicioPreFacturaDetalle;
import confia.servicios.vistas.ServicioPreFacturaDetalleView;
import confia.servicios.vistas.ServicioPrefacturarView;

@ManagedBean(name = "ControladorFacturas")
@ViewScoped
public class ControladorFacturas extends AbstractReportBean {
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioPrefacturarView srvPreFactView;
	@EJB
	private ServicioPreFacturaDetalleView srvPreFactDetView;
	@EJB
	private ServicioPreFactura srvPreFactura;
	@EJB
	private ServicioPreFacturaDetalle srvPreFactDet;
	@EJB
	private ServicioFactura srvFactura;
	@EJB
	private ServicioFacturaDetalle srvFacturaDetalle;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioComisionesPoliza srvComisionPol;

	private String remiteAseguradora;
	private List<Aseguradoras> listAseguradoras;
	private List<PrefacturarView> lstPreFactView;
	private List<PrefacturarView> selectedlstPreFactView;
	private List<PrefacturarView> lstPreFactViewFact;
	private PrefacturarView selectedPreFactView;
	private List<PreFacturaDetalleView> lstPreFactDetaView;
	private String numFactura;
	private Boolean flgBotonImprime;
	private String numFacturaPrint;
	private Date fcFacturaCom;
	private boolean conIva;

	public ControladorFacturas() {
		listAseguradoras = new ArrayList<Aseguradoras>();
		lstPreFactView = new ArrayList<PrefacturarView>();
		lstPreFactDetaView = new ArrayList<PreFacturaDetalleView>();
		flgBotonImprime = true;
		fcFacturaCom = new Date();
		conIva = true;
	}

	@PostConstruct
	public void datosIniciales() {
		listAseguradoras = srvAseguradoras.listaAseguradoras();
		numFactura = null;
	}

	// --------------- PROGRAMACION IMPRESIONES ------------------//
	private final String COMPILE_FILE_NAME = "factura";

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}

	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numFact", numFacturaPrint);
		return parametros;

	}

	public String execute() {
		System.out.println("---------------------------- NUMERO factura:" + numFacturaPrint);
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	// -----------------------------------------------------------//

	public void sumaPreFacturaSel() {
		Double valPreFac = 0.0;
		for (PrefacturarView preFac : selectedlstPreFactView) {
			valPreFac = valPreFac + preFac.getVal_pre_factura();
		}
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Total Comisiones Seleccionadas:" + redondear(valPreFac)));
	}

	public void consultaPreFactura() {
		lstPreFactView = srvPreFactView.lstPrefacturarView(remiteAseguradora);
	}

	public void validaFactura() {
		try {
			if (selectedlstPreFactView.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Seleccione registros para Generar la pre liquidación"));
				return;
			}

		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione registros para Generar la pre liquidación"));
			return;
		}
		lstPreFactViewFact = new ArrayList<PrefacturarView>();
		lstPreFactViewFact = selectedlstPreFactView;
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('numFactConfia').show()");
		PrimeFaces.current().executeScript("PF('numFactConfia').show()");
	}

	public void facturar() {
		try {
			if (numFactura.isEmpty() || numFactura == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el nómero de Factura "));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el nómero de Factura "));
			return;
		}
		System.out.println("INGRESOOOO");
		System.out.println("SIZE:" + lstPreFactViewFact.size());
		Factura factAux = new Factura();
		factAux.setCd_compania(lstPreFactViewFact.get(0).getCd_compania());
		factAux.setCd_aseguradora(lstPreFactViewFact.get(0).getCd_aseguradora());
		factAux.setNum_factura(numFactura);
		factAux.setFc_factura(fcFacturaCom);
		numFacturaPrint = numFactura;
		System.out.println("FACTURA A IMPRIMIR:" + numFacturaPrint);
		srvFactura.insertarFactura(factAux);
		Integer codFac = 0;
		codFac = srvFactura.codigoMaxFactura();
		factAux = new Factura();
		factAux = srvFactura.recuperaFacturaPorCodigo(codFac);
		Double valor = 0.0;
		FacturaDetalle factDetAxus = new FacturaDetalle();
		PreFactura preFacturaSel = new PreFactura();
		List<PreFacturaDetalle> lstDetPreFact = new ArrayList<PreFacturaDetalle>();
		Integer cd_comision_pol;
		ComisionesPoliza comisionPoliza = new ComisionesPoliza();

		for (PrefacturarView prefactAux : lstPreFactViewFact) {
			factDetAxus = new FacturaDetalle();
			factDetAxus.setCd_compania(factAux.getCd_compania());
			factDetAxus.setCd_factura(factAux.getCd_factura());
			factDetAxus.setVal_comision(prefactAux.getVal_pre_factura());
			valor = valor + prefactAux.getVal_pre_factura();
			factDetAxus.setCd_pre_factura(prefactAux.getCd_pre_factura());
			srvFacturaDetalle.insertarFacturaDetalle(factDetAxus);
			// actualiza pre factura
			preFacturaSel = new PreFactura();
			preFacturaSel = srvPreFactura.recuperaPreFacturaPorCodigo(prefactAux.getCd_pre_factura());
			preFacturaSel.setFlg_factura(1);
			srvPreFactura.actualizaPreFactura(preFacturaSel);
			// comisiones poliza
			lstDetPreFact = new ArrayList<PreFacturaDetalle>();
			lstDetPreFact = srvPreFactDet.recuperaPreFacturaDetallePorPrefa(prefactAux.getCd_pre_factura());
			cd_comision_pol = 0;
			for (PreFacturaDetalle preFacturaDetalle : lstDetPreFact) {
				cd_comision_pol = preFacturaDetalle.getCd_comision_poliza();
				comisionPoliza = new ComisionesPoliza();
				comisionPoliza = srvComisionPol.comisionesPolizaxCod(cd_comision_pol);
				comisionPoliza.setFlg_factura("1");
				srvComisionPol.actualizaComisionesPoliza(comisionPoliza);
			}
		}
		factAux.setVal_factura(valor);
		String rubroAux = srvRubros.recuperaIva();
		Double iva = Double.valueOf(rubroAux);
		System.out.println("IVA:"+conIva);
		
		if (conIva) {
			iva = iva / 100;
			iva = redondear(iva);
			iva = iva * valor;
		} else {
			iva = 0.0;
		}
		
		System.out.println("VAL-IVA:"+iva);
		
		factAux.setIva(iva);
		valor = iva + valor;
		factAux.setTot_factura(valor);
		srvFactura.actualizaFactura(factAux);
		flgBotonImprime = false;
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Trasnacción Exitosa", "Si desea imprimir el documento presione el boton Imprimir"));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('numFactConfia').hide()");
		PrimeFaces.current().executeScript("PF('numFactConfia').hide()");
		lstPreFactView = new ArrayList<PrefacturarView>();

	}

	public void salir() {
//		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
//		try {
//			ctx.redirect("./index.jsf");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void recuperaDetallePreFact() {
		lstPreFactDetaView = new ArrayList<PreFacturaDetalleView>();
		lstPreFactDetaView = srvPreFactDetView.lstPreFacturaDetalleView(selectedPreFactView.getCd_pre_factura());
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('detallePreFact').show()");
		PrimeFaces.current().executeScript("PF('detallePreFact').show()");
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
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

	public List<PrefacturarView> getLstPreFactView() {
		return lstPreFactView;
	}

	public void setLstPreFactView(List<PrefacturarView> lstPreFactView) {
		this.lstPreFactView = lstPreFactView;
	}

	public List<PrefacturarView> getSelectedlstPreFactView() {
		return selectedlstPreFactView;
	}

	public void setSelectedlstPreFactView(List<PrefacturarView> selectedlstPreFactView) {
		this.selectedlstPreFactView = selectedlstPreFactView;
	}

	public PrefacturarView getSelectedPreFactView() {
		return selectedPreFactView;
	}

	public void setSelectedPreFactView(PrefacturarView selectedPreFactView) {
		this.selectedPreFactView = selectedPreFactView;
	}

	public List<PreFacturaDetalleView> getLstPreFactDetaView() {
		return lstPreFactDetaView;
	}

	public void setLstPreFactDetaView(List<PreFacturaDetalleView> lstPreFactDetaView) {
		this.lstPreFactDetaView = lstPreFactDetaView;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public Boolean getFlgBotonImprime() {
		return flgBotonImprime;
	}

	public void setFlgBotonImprime(Boolean flgBotonImprime) {
		this.flgBotonImprime = flgBotonImprime;
	}

	public Date getFcFacturaCom() {
		return fcFacturaCom;
	}

	public void setFcFacturaCom(Date fcFacturaCom) {
		this.fcFacturaCom = fcFacturaCom;
	}

	public boolean isConIva() {
		return conIva;
	}

	public void setConIva(boolean conIva) {
		this.conIva = conIva;
	}

}
