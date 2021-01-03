package confia.controladores.transaccionales;

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

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.PagoDetallePago;
import confia.entidades.vistas.ConsultaPagoRealizadosView;
import confia.entidades.vistas.liquidacionCanalPolView;
import confia.procedures.ProcedimientosAlmacenadosDB;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.vistas.ServicioConsultaPagoRealizadosView;

@ViewScoped
@ManagedBean(name = "ControladorExpedicionPagos")
public class ControladorExpedicionPagos extends AbstractReportBean {
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioConsultaPagoRealizadosView srvConsultaPagoView;
	private ProcedimientosAlmacenadosDB srvSp;

	private String remiteAseguradora;
	private List<Aseguradoras> listAseguradoras;
	private Date pagoFcDesde;
	private Date pagoFcHasta;
	private String numExpedicion;
	private boolean flgBotonImprime;
	private List<ConsultaPagoRealizadosView> lstConsPagoRealView;
	private List<ConsultaPagoRealizadosView> selectedlstConsPagoRealView;
	
	// Imprime
	private String ls_num_liq;

	// --------------- PROGRAMACION IMPRESIONES ------------------//
	private final String COMPILE_FILE_NAME = "expedicionPago"; ;

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}

	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numExp", numExpedicion);
		return parametros;

	}

	public String execute() {
		System.out.println("---------------------------- NUMERO PREFACT:" + numExpedicion);
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public String executeLiquidados() {
		try {
			if(ls_num_liq.isEmpty() || ls_num_liq == null){
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Ingrese el número de Recibo" ));
				return null;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese el número de Recibo" ));
			return null;
		}
		numExpedicion = ls_num_liq;
		System.out.println("---------------------------- NUMERO PREFACT:" + numExpedicion);
		
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	// -----------------------------------------------------------//
	
	public void sumaRegistros() {
		Double valSel = 0.0;

		for (ConsultaPagoRealizadosView detPagSel : selectedlstConsPagoRealView) {
			valSel = valSel + Double.valueOf(detPagSel.getValor_pago());
		}

		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Total Valores Pago Seleccionados:" + redondear(valSel)));

	}

	public ControladorExpedicionPagos() {
		listAseguradoras = new ArrayList<Aseguradoras>();
		pagoFcDesde = new Date();
		pagoFcHasta = new Date();
		flgBotonImprime = true;
		lstConsPagoRealView = new ArrayList<ConsultaPagoRealizadosView>();
		srvSp = new ProcedimientosAlmacenadosDB();
	}

	@PostConstruct
	public void datosIniciales() {
		listAseguradoras = srvAseguradoras.listaAseguradoras();

	}

	public void consultaPago() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fcIni = formatter.format(pagoFcDesde);
		String fcFin = formatter.format(pagoFcHasta);
		lstConsPagoRealView = new ArrayList<ConsultaPagoRealizadosView>();
		lstConsPagoRealView = srvConsultaPagoView.consultaPagoAseg(remiteAseguradora,fcIni,fcFin);

	}
	
	public void consultaLiquidacionGenerada() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fcIni = formatter.format(pagoFcDesde);
		String fcFin = formatter.format(pagoFcHasta);
		try {
			if(ls_num_liq.isEmpty() || ls_num_liq == null)
				ls_num_liq="%";
		} catch (Exception e) {
			ls_num_liq="%";
		}
		lstConsPagoRealView = new ArrayList<ConsultaPagoRealizadosView>();
		lstConsPagoRealView = srvConsultaPagoView.consultaliquidacionesGen(remiteAseguradora,fcIni,fcFin,ls_num_liq);

	}

	public void generaExpedicionPago() {
		System.out.println("selectedlstConsPagoRealView:" + selectedlstConsPagoRealView.size());

		try {
			if (selectedlstConsPagoRealView.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione Pagos Generados ..."));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione Pagos Generados ..."));
			return;
		}
		String codPago;
		Integer res = 0;

		numExpedicion = String.valueOf(srvSp.numReciboExpedicion());
		for (ConsultaPagoRealizadosView pagosSel : selectedlstConsPagoRealView) {
			codPago = pagosSel.getCd_pago();
			res = srvSp.actualizaNumExpedicion(codPago, numExpedicion);
			System.out.println("REgistro Actualizado 1-SI:" + res);
		}

		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null, new FacesMessage("Advertencia", "Expedici�n Generada N�mero: " + numExpedicion));
		lstConsPagoRealView = new ArrayList<ConsultaPagoRealizadosView>();
		selectedlstConsPagoRealView = new ArrayList<ConsultaPagoRealizadosView>();
		flgBotonImprime = false;
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
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

	public Date getPagoFcDesde() {
		return pagoFcDesde;
	}

	public void setPagoFcDesde(Date pagoFcDesde) {
		this.pagoFcDesde = pagoFcDesde;
	}

	public Date getPagoFcHasta() {
		return pagoFcHasta;
	}

	public void setPagoFcHasta(Date pagoFcHasta) {
		this.pagoFcHasta = pagoFcHasta;
	}

	public boolean isFlgBotonImprime() {
		return flgBotonImprime;
	}

	public void setFlgBotonImprime(boolean flgBotonImprime) {
		this.flgBotonImprime = flgBotonImprime;
	}

	public List<ConsultaPagoRealizadosView> getLstConsPagoRealView() {
		return lstConsPagoRealView;
	}

	public void setLstConsPagoRealView(List<ConsultaPagoRealizadosView> lstConsPagoRealView) {
		this.lstConsPagoRealView = lstConsPagoRealView;
	}

	public List<ConsultaPagoRealizadosView> getSelectedlstConsPagoRealView() {
		return selectedlstConsPagoRealView;
	}

	public void setSelectedlstConsPagoRealView(List<ConsultaPagoRealizadosView> selectedlstConsPagoRealView) {
		this.selectedlstConsPagoRealView = selectedlstConsPagoRealView;
	}

	public String getLs_num_liq() {
		return ls_num_liq;
	}

	public void setLs_num_liq(String ls_num_liq) {
		this.ls_num_liq = ls_num_liq;
	}
	
}
