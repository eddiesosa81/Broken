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

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Subagentes;
import confia.entidades.transaccionales.LiquidaDiferencial;
import confia.entidades.vistas.ComisionDiferencialPolView;
import confia.procedures.ProcedimientosAlmacenadosDB;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.transaccionales.ServicioLiquidaDiferencial;
import confia.servicios.vistas.ServicioComisionDiferencialPolView;

@ViewScoped
@ManagedBean

public class ControladorDiferencialesPol extends AbstractReportBean {
	@EJB
	private ServicioSubagentes srvSubagentes;
	@EJB
	private ServicioComisionDiferencialPolView srvComisionAsegPolView;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioLiquidaDiferencial srvLiquidaDiferencial;
	private ProcedimientosAlmacenadosDB srvProcedimientosAlmacenadosDB;
	
	private List<ComisionDiferencialPolView> lstComisionAsegPolView;
	private List<ComisionDiferencialPolView> selectedLstComisionPolView;
	private ComisionDiferencialPolView selectedComisionPolView;
	private List<Aseguradoras> lstAseguradoras;
	private Aseguradoras selectedAseguradoras;
	private List<Aseguradoras> selectedLstAseguradoras;
	private List<ComisionDiferencialPolView> filteredFacturaAseg;
	private List<Subagentes> listSubagentes;
	
	private String codAseg;
	private Date fecha_liquidacion;
	private Integer cd_comision_poliza;
	private Integer cd_compania;
	private String num_liquidacion;
	private String codSubagnete;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public ControladorDiferencialesPol() {
		lstComisionAsegPolView = new ArrayList<ComisionDiferencialPolView>();
		lstAseguradoras = new ArrayList<Aseguradoras>();
		listSubagentes = new ArrayList<Subagentes>();
		fechaDesde = new Date();
		fechaHasta = new Date();
		srvProcedimientosAlmacenadosDB = new ProcedimientosAlmacenadosDB();
	}
	
	@PostConstruct
	public void datosIniciales() {
		lstAseguradoras = srvAseguradoras.listaAseguradoras();
		listSubagentes = srvSubagentes.recuperaSubagente1();
	}
	
	//--------------- PROGRAMACION IMPRESIONES ------------------//
	private final String COMPILE_FILE_NAME = "diferencial_liq";

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}
	@Override
	protected Map<String, Object> getReportParameters() {
		System.out.println("Numero Liquidación:"+num_liquidacion);
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numLiq", num_liquidacion);
		return parametros;

	}
	public String execute() {
		System.out.println("---------------------------- NUMERO LIQU:"+num_liquidacion);
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	//-----------------------------------------------------------//
	
	public void recuperaDatosDif() {	
		lstComisionAsegPolView = new ArrayList<ComisionDiferencialPolView>();
		System.out.println("ASEGURADORA:"+codAseg);
		lstComisionAsegPolView = srvComisionAsegPolView.listaAsegComiAsegPolView(codAseg,codSubagnete,fechaDesde,fechaHasta);
	}
	
	public void recalculoDiferenciales() {	
		System.out.println("Ingreso al re Calculo");
		for (ComisionDiferencialPolView comSelDif : selectedLstComisionPolView) {
			srvProcedimientosAlmacenadosDB.recalculoDiferencialPol(comSelDif.getCd_compania(), comSelDif.getCd_comision_poliza(),comSelDif.getCd_ramo_cotizacion());
		}
		lstComisionAsegPolView = new ArrayList<ComisionDiferencialPolView>();
		selectedLstComisionPolView = new ArrayList<ComisionDiferencialPolView>();
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Proceso Exitoso"));

	}
	
	public void sumaRegSel() {
		Double valSel = 0.0;
		
		for (ComisionDiferencialPolView comSelDif : selectedLstComisionPolView) {
			valSel = valSel + Double.valueOf(comSelDif.getDiferencial_canal());
		}
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Total diferencial seleccionado: "+String.valueOf(valSel)));
	}
		
	public void guardarComiAsegPol() {
		System.out.println("Tamaño:"+selectedLstComisionPolView.size());
	
		try {
			if (selectedLstComisionPolView.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Seleccione registros para Generar el pago"));
				return;

			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Seleccione registros para Generar el pago"));
			return;
		}	
		Integer numLiq;
		numLiq = srvLiquidaDiferencial.numLiqDif();
		num_liquidacion = String.valueOf(numLiq);
		System.out.println("NUMERO LIQ:"+num_liquidacion);
		System.out.println("TMAÑp:"+selectedLstComisionPolView.size());
		for (ComisionDiferencialPolView comDifPolLst : selectedLstComisionPolView) {
			LiquidaDiferencial comDifPolAux = new LiquidaDiferencial();
			comDifPolAux.setCd_comision_poliza(Integer.valueOf(comDifPolLst.getCd_comision_poliza()));
			comDifPolAux.setCd_compania(Integer.valueOf(comDifPolLst.getCd_compania()));
			comDifPolAux.setNum_liquidacion(numLiq);
			
			srvLiquidaDiferencial.insertaComiDiferencial(comDifPolAux);
	
		}
		
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Proceso exitoso No."+numLiq));
	}
		
	public void salir() {
//		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
//		try {
//			ctx.redirect("./index.jsf");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public List<Aseguradoras> getLstAseguradoras() {
		return lstAseguradoras;
	}

	public void setLstAseguradoras(List<Aseguradoras> lstAseguradoras) {
		this.lstAseguradoras = lstAseguradoras;
	}

	public String getCodAseg() {
		return codAseg;
	}

	public void setCodAseg(String codAseg) {
		this.codAseg = codAseg;
	}

	public List<ComisionDiferencialPolView> getSelectedLstComisionPolView() {
		return selectedLstComisionPolView;
	}

	public void setSelectedLstComisionPolView(List<ComisionDiferencialPolView> selectedLstComisionPolView) {
		this.selectedLstComisionPolView = selectedLstComisionPolView;
	}

	public ComisionDiferencialPolView getSelectedComisionPolView() {
		return selectedComisionPolView;
	}

	public void setSelectedComisionPolView(ComisionDiferencialPolView selectedComisionPolView) {
		this.selectedComisionPolView = selectedComisionPolView;
	}

	public Aseguradoras getSelectedAseguradoras() {
		return selectedAseguradoras;
	}

	public void setSelectedAseguradoras(Aseguradoras selectedAseguradoras) {
		this.selectedAseguradoras = selectedAseguradoras;
	}

	public List<Aseguradoras> getSelectedLstAseguradoras() {
		return selectedLstAseguradoras;
	}

	public void setSelectedLstAseguradoras(List<Aseguradoras> selectedLstAseguradoras) {
		this.selectedLstAseguradoras = selectedLstAseguradoras;
	}

	public List<ComisionDiferencialPolView> getFilteredFacturaAseg() {
		return filteredFacturaAseg;
	}

	public void setFilteredFacturaAseg(List<ComisionDiferencialPolView> filteredFacturaAseg) {
		this.filteredFacturaAseg = filteredFacturaAseg;
	}

	public List<ComisionDiferencialPolView> getLstComisionAsegPolView() {
		return lstComisionAsegPolView;
	}

	public void setLstComisionAsegPolView(List<ComisionDiferencialPolView> lstComisionAsegPolView) {
		this.lstComisionAsegPolView = lstComisionAsegPolView;
	}

	public Integer getCd_comision_poliza() {
		return cd_comision_poliza;
	}

	public void setCd_comision_poliza(Integer cd_comision_poliza) {
		this.cd_comision_poliza = cd_comision_poliza;
	}

	public Integer getCd_compania() {
		return cd_compania;
	}

	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}

	public String getNum_liquidacion() {
		return num_liquidacion;
	}

	public void setNum_liquidacion(String num_liquidacion) {
		this.num_liquidacion = num_liquidacion;
	}

	public Date getFecha_liquidacion() {
		return fecha_liquidacion;
	}

	public void setFecha_liquidacion(Date fecha_liquidacion) {
		this.fecha_liquidacion = fecha_liquidacion;
	}

	public String getCodSubagnete() {
		return codSubagnete;
	}

	public void setCodSubagnete(String codSubagnete) {
		this.codSubagnete = codSubagnete;
	}

	public List<Subagentes> getListSubagentes() {
		return listSubagentes;
	}

	public void setListSubagentes(List<Subagentes> listSubagentes) {
		this.listSubagentes = listSubagentes;
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
	
}
