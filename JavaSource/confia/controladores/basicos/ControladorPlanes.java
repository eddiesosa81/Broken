package confia.controladores.basicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.AseguradoraClausula;
import confia.entidades.basicos.AseguradoraCobertura;
import confia.entidades.basicos.AseguradoraDeducible;
import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Clausulas;
import confia.entidades.basicos.Coberturas;
import confia.entidades.basicos.Deducibles;
import confia.entidades.basicos.Plan;
import confia.entidades.basicos.PlanClausula;
import confia.entidades.basicos.PlanCobertura;
import confia.entidades.basicos.PlanDeducible;
import confia.entidades.basicos.PlanDepreciacion;
import confia.entidades.basicos.Ramo;
import confia.entidades.basicos.RamoAseguradora;
import confia.entidades.vistas.CoberturasPlanView;
import confia.entidades.vistas.PlanClausulasView;
import confia.entidades.vistas.PlanDeduciblesView;
import confia.entidades.vistas.PlanRamoAseguradoraView;
import confia.procedures.servicioProcedures;
import confia.servicios.basicos.ServicioAseguradoraClausula;
import confia.servicios.basicos.ServicioAseguradoraCobertura;
import confia.servicios.basicos.ServicioAseguradoraDeducible;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioClausulas;
import confia.servicios.basicos.ServicioCoberturas;
import confia.servicios.basicos.ServicioDeducibles;
import confia.servicios.basicos.ServicioPlan;
import confia.servicios.basicos.ServicioPlanClausula;
import confia.servicios.basicos.ServicioPlanCobertura;
import confia.servicios.basicos.ServicioPlanDeducible;
import confia.servicios.basicos.ServicioPlanDepreciacion;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioRamoAseguradora;
import confia.servicios.vistas.ServicioCoberturasPlanView;
import confia.servicios.vistas.ServicioPlanClausulasView;
import confia.servicios.vistas.ServicioPlanDeduciblesView;
import confia.servicios.vistas.ServicioPlanesRamoAseg;

@ManagedBean(name = "ControladorPlanes")
@ViewScoped
public class ControladorPlanes {
	@EJB
	private ServicioPlanesRamoAseg srvPlanesRamoAseg;
	@EJB
	private ServicioAseguradoras srvAseguradora;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioCoberturasPlanView srvPlanCoberturas;
	@EJB
	private ServicioRamoAseguradora srvRamoAseg;
	@EJB
	private ServicioPlan srvPlan;
	@EJB
	private ServicioCoberturas srvCoberturas;
	@EJB
	private ServicioAseguradoraCobertura srvAseguradoraCob;
	@EJB
	private ServicioPlanCobertura srvPlanCobertura;
	@EJB
	private ServicioPlanDeduciblesView srvPlanDeducibleView;
	@EJB
	private ServicioDeducibles srvDeducibles;
	@EJB
	private ServicioAseguradoraDeducible srvAsegDeducible;
	@EJB
	private ServicioPlanDeducible srvPlanDeducible;
	@EJB
	private ServicioClausulas srvClausulas;
	@EJB
	private ServicioPlanClausulasView srvPlanCalusulasView;
	@EJB
	private ServicioAseguradoraClausula srvAsegClausula;
	@EJB
	private ServicioPlanClausula srvPlanClausula;
	private servicioProcedures srvProcedures;
	@EJB
	private ServicioPlanDepreciacion srvPlanDepreciacion;

	private List<PlanRamoAseguradoraView> lstPlanRamAseg;
	private List<PlanRamoAseguradoraView> lstFilteredPlanRamAseg;
	private PlanRamoAseguradoraView selectedPlanRamAseg;
	private List<Aseguradoras> lstAseguradora;
	private List<Ramo> lstRamo;
	private List<CoberturasPlanView> lstCoberturasPlan;
	private List<CoberturasPlanView> lstFilteredCoberturasPlan;
	private CoberturasPlanView selectedCoberturasPlan;
	private List<Coberturas> lstCoberturas;
	private List<Coberturas> selectedLstCoberturas;
	private List<Coberturas> lstFilteredCoberturas;
	private List<PlanDeduciblesView> lstPlanDeducibles;
	private PlanDeduciblesView selectedPlanDeducibles;
	private List<PlanDeduciblesView> lstFilteredPlanDeducibles;
	private List<Deducibles> lstDeducibles;
	private List<Deducibles> selectedlstDeducibles;
	private List<Deducibles> lstFilteredDeducibles;
	private List<PlanClausulasView> lstPlanCalusulaView;
	private PlanClausulasView selectedPlanCalusulaView;
	private List<PlanClausulasView> lstFilteredPlanCalusulaView;
	private List<Clausulas> lstClausulas;
	private List<Clausulas> selectedlstClausulas;
	private List<Clausulas> lstFilteredClausulas;
	private List<PlanDepreciacion> lstPlanDepreciacion;
	private PlanDepreciacion selectedPlanDepreciacion;

	private String codAseg;
	private String codRamo;
	private String descPlan;
	private Double porcCob;
	private Double valorCob;
	private Double porcClau;
	private Double valorClau;
	private String codAsegIns;

	private Double porcDedValSin;
	private Double porcDedValAseg;
	private Double valorDedMin;
	private Double valorDedFijo;
	private String especificacionDed;
	private String especificacionCob;
	private String especificacionCla;
	private String codPlan;
	private Integer numAnioDepre;
	
	private Boolean btnNmCob;
	private String  inptNmCob;

	public ControladorPlanes() {
		lstPlanRamAseg = new ArrayList<PlanRamoAseguradoraView>();
		lstAseguradora = new ArrayList<Aseguradoras>();
		lstRamo = new ArrayList<Ramo>();
		lstCoberturasPlan = new ArrayList<CoberturasPlanView>();
		lstCoberturas = new ArrayList<Coberturas>();
		porcCob = 0.0;
		valorCob = 0.0;
		porcDedValSin = 0.0;
		porcDedValAseg = 0.0;
		valorDedMin = 0.0;
		valorDedFijo = 0.0;
		porcClau = 0.0;
		valorClau = 0.0;
		lstPlanDeducibles = new ArrayList<PlanDeduciblesView>();
		lstDeducibles = new ArrayList<Deducibles>();
		lstPlanCalusulaView = new ArrayList<PlanClausulasView>();
		lstClausulas = new ArrayList<Clausulas>();
		srvProcedures = new servicioProcedures();
		codPlan = "0";
		lstPlanDepreciacion = new ArrayList<PlanDepreciacion>();
		btnNmCob = false;
		inptNmCob = "";
	}

	@PostConstruct
	public void recuperaDatos() {
		lstPlanRamAseg = srvPlanesRamoAseg.consultaPlanRamoAseguradora();
		lstAseguradora = srvAseguradora.listaAseguradoras();
		lstRamo = srvRamo.listaRamos();
	}
	
	public void cambiaNombreCobertura() {
		btnNmCob = true;
		inptNmCob = "";
	}
	public void guardaNmCob() {
		String cdAsegCob = "0";
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			cdAsegCob = selectedCoberturasPlan.getCd_asegcob();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Cobertura"));
			lstCoberturasPlan = new ArrayList<CoberturasPlanView>();
			lstCoberturasPlan = srvPlanCoberturas.consultaCoberturasPlan(selectedPlanRamAseg.getCd_plan(),
					selectedPlanRamAseg.getCd_aseguradora());
			selectedLstCoberturas = new ArrayList<Coberturas>();
			lstFilteredCoberturas = new ArrayList<Coberturas>();
			return;
		}
		try {
			if(inptNmCob.equals("") || inptNmCob == null){
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el nombre de la Cobertura"));
				return;
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el nombre de la Cobertura"));
			return;
		}
		PlanCobertura planCobAux = new PlanCobertura();
		planCobAux = srvPlanCobertura.consultaPlanCobertura(cdAsegCob, selectedPlanRamAseg.getCd_plan());
		Coberturas cob = new Coberturas();
		cob = srvCoberturas.consultaCoberturasxId(planCobAux.getCd_asegcob());
		cob.setDesc_cobertura(inptNmCob);
		srvCoberturas.actualizaCoberturas(cob);
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstCoberturasPlan = new ArrayList<CoberturasPlanView>();
		lstCoberturasPlan = srvPlanCoberturas.consultaCoberturasPlan(selectedPlanRamAseg.getCd_plan(),
				selectedPlanRamAseg.getCd_aseguradora());
		btnNmCob = false;
		inptNmCob = "";
		
	}

	public void onRowSelect(SelectEvent event) {
		String codAseg = null;
		codPlan = ((PlanRamoAseguradoraView) event.getObject()).getCd_plan();
		codAseg = String.valueOf(((PlanRamoAseguradoraView) event.getObject()).getCd_aseguradora());

		System.out.println("Plan seleccionado:" + codPlan);
		System.out.println("ASeguradora:" + codAseg);
		lstCoberturasPlan = srvPlanCoberturas.consultaCoberturasPlan(codPlan, codAseg);
		lstPlanDeducibles = srvPlanDeducibleView.consultaDeduciblePlan(codPlan, codAseg);
		lstPlanCalusulaView = srvPlanCalusulasView.consultaPlanClausulasView(codPlan, codAseg);
	}

	public void guardaSiniestro() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./indexGestion.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nuevoPlan() {
		codAseg = null;
		codRamo = null;
		descPlan = null;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevoPlan').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevoPlan').show();");
	}

	public void copiaPlan() {
		descPlan = null;
		codAsegIns = null;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgCopiaPlan').show();");
		PrimeFaces.current().executeScript("PF('wDlgCopiaPlan').show();");
	}

	public void depreciacionPlan() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			System.out.println("Seleccion plan:" + selectedPlanRamAseg.getCd_plan());
			if(selectedPlanRamAseg.getCd_plan().isEmpty() || selectedPlanRamAseg.getCd_plan() == null ){
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione Plan"));
				return;
			}
		} catch (Exception e) {
			
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione Plan"));
			return;
		}
		
		lstPlanDepreciacion = srvPlanDepreciacion.consultaListaPlanDepreciacion(selectedPlanRamAseg.getCd_plan());
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgDepreciacionPlan').show();");
		PrimeFaces.current().executeScript("PF('wDlgDepreciacionPlan').show();");
	}
	
	public void onRowEditDepreciacion(RowEditEvent event) {
		Double depre, tasa,superBanco,derechEmi,segCamp;
		depre = ((PlanDepreciacion)event.getObject()).getPorcentaje();
		tasa = ((PlanDepreciacion)event.getObject()).getTasa_obj();
		superBanco = ((PlanDepreciacion)event.getObject()).getSuperbancos();
		derechEmi= ((PlanDepreciacion)event.getObject()).getDerechoemision();
		segCamp= ((PlanDepreciacion)event.getObject()).getSegurocampesino();
		PlanDepreciacion planDepAux = new PlanDepreciacion();
		planDepAux = srvPlanDepreciacion.consultaPlanDepreciacion(((PlanDepreciacion)event.getObject()).getCd_plandepecia());
		planDepAux.setPorcentaje(depre);
		planDepAux.setTasa_obj(tasa);
		planDepAux.setSuperbancos(superBanco);
		planDepAux.setDerechoemision(derechEmi);
		planDepAux.setSegurocampesino(segCamp);
		srvPlanDepreciacion.actualizaPlanDepreciacion(planDepAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
	}

	public void generaDepre() {
		Integer lstDepre;
		try {
			lstDepre = lstPlanDepreciacion.size();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el numero de a�os a depreciar"));
			return;
		}
		
		if (lstDepre.equals(0)) {
			for (int i = 0; i < numAnioDepre; i++) {
				PlanDepreciacion depreAux = new PlanDepreciacion();
				depreAux.setAnio(i+1);
				depreAux.setCd_plan(Integer.valueOf(selectedPlanRamAseg.getCd_plan()));
				srvPlanDepreciacion.insertarPlanDepreciacion(depreAux);
			}
		} else {
			for (PlanDepreciacion depreAux : lstPlanDepreciacion) {
				srvPlanDepreciacion.eliminaPlanDepreciacion(depreAux);
			}
			for (int i = 0; i < numAnioDepre; i++) {
				PlanDepreciacion depreAux = new PlanDepreciacion();
				depreAux.setAnio(i+1);
				depreAux.setCd_plan(Integer.valueOf(selectedPlanRamAseg.getCd_plan()));
				srvPlanDepreciacion.insertarPlanDepreciacion(depreAux);
			}
		}
		lstPlanDepreciacion = new ArrayList<PlanDepreciacion>();
		lstPlanDepreciacion = srvPlanDepreciacion.consultaListaPlanDepreciacion(selectedPlanRamAseg.getCd_plan());
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Ingrese los Porcentajes y Tasa del Objeto Asegurado"));
	}

	public void limpiaCoberturas() {

		selectedLstCoberturas = new ArrayList<Coberturas>();
		lstFilteredCoberturas = new ArrayList<Coberturas>();

	}

	public void limpiaClausulas() {
		lstClausulas = new ArrayList<Clausulas>();
		lstClausulas = srvClausulas.consultaClausulas();
		selectedlstClausulas = new ArrayList<Clausulas>();
		lstFilteredClausulas = new ArrayList<Clausulas>();

	}

	public void recuperarCoberturas() {
		lstCoberturas = new ArrayList<Coberturas>();
		lstCoberturas = srvCoberturas.consultaCoberturas();
	}

	public void guardaPlan() {
		// verifica si los datos seleccionados estan registrados
		FacesContext context = FacesContext.getCurrentInstance();
		if (codAseg.equals("0")) {
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Aseguradora"));
			return;
		}
		if (codRamo.equals("0")) {
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Ramo"));
			return;
		}
		if (descPlan.isEmpty() || descPlan == null) {
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la descripci�n del Plan"));
			return;
		}
		RamoAseguradora ramAse = new RamoAseguradora();
		ramAse = srvRamoAseg.consultaRamoAseguradora(codAseg, codRamo);
		int codRamAseg;
		if (ramAse == null) {
			ramAse = new RamoAseguradora();
			ramAse.setCd_aseguradora(Integer.valueOf(codAseg));
			ramAse.setCd_ramo(Integer.valueOf(codRamo));
			ramAse.setEstado_asegcob("A");
			srvRamoAseg.insertarRamoAseguradora(ramAse);
			codRamAseg = srvRamoAseg.codigoMaxRamoAseguradora();
		} else {
			codRamAseg = ramAse.getCd_ramoaseg();
		}
		System.out.println("codRamAseg:" + codRamAseg);
		Plan planAux = new Plan();
		planAux.setCd_ramoaseg(codRamAseg);
		planAux.setDescripcion_plan(descPlan.toUpperCase().trim());
		srvPlan.insertarPlan(planAux);
		lstPlanRamAseg = new ArrayList<PlanRamoAseguradoraView>();
		lstPlanRamAseg = srvPlanesRamoAseg.consultaPlanRamoAseguradora();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevoPlan').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNuevoPlan').hide();");

	}

	public void guardaCopiaPlan() {
		// verifica si los datos seleccionados estan registrados
		FacesContext context = FacesContext.getCurrentInstance();
		if (descPlan.isEmpty() || descPlan == null) {
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la descripci�n del Plan"));
			return;
		}
		RamoAseguradora ramAse = new RamoAseguradora();
		// consulto si existe el cdRamoASeg
		Integer codRamAseg;
		ramAse = srvRamoAseg.consultaRamoAseguradora(codAsegIns, selectedPlanRamAseg.getCd_ramo());

		if (ramAse == null) {
			ramAse = new RamoAseguradora();
			ramAse.setCd_aseguradora(Integer.valueOf(codAsegIns));
			ramAse.setCd_ramo(Integer.valueOf(selectedPlanRamAseg.getCd_ramo()));
			ramAse.setEstado_asegcob("A");
			srvRamoAseg.insertarRamoAseguradora(ramAse);
			codRamAseg = srvRamoAseg.codigoMaxRamoAseguradora();
		} else {
			codRamAseg = ramAse.getCd_ramoaseg();
		}

		System.out.println("codRamAseg:" + codRamAseg);
		Plan planAux = new Plan();
		planAux.setCd_ramoaseg(codRamAseg);
		planAux.setDescripcion_plan(descPlan.toUpperCase().trim());
		srvPlan.insertarPlan(planAux);
		int codPlan, res;
		String codPlanAnt;
		codPlan = srvPlan.codigoMaxPlan();
		codPlanAnt = selectedPlanRamAseg.getCd_plan();
		// llamo al procedimiento para el nuevo plan copiado
		res = srvProcedures.copiaPlan(String.valueOf(codPlan), codPlanAnt, codAsegIns);
		if (res == 1) {
			context.addMessage(null, new FacesMessage("Advertencia", "Error al Copiar el Plan"));
		}
		lstPlanRamAseg = new ArrayList<PlanRamoAseguradoraView>();
		lstPlanRamAseg = srvPlanesRamoAseg.consultaPlanRamoAseguradora();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgCopiaPlan').hide();");
		PrimeFaces.current().executeScript("PF('wDlgCopiaPlan').hide();");
		descPlan = null;

	}

	public void eliminaPlan() {
		Plan planAux = new Plan();
		try {
			selectedPlanRamAseg.getCd_plan();
			planAux = srvPlan.consultaPlan(Integer.valueOf(selectedPlanRamAseg.getCd_plan()));
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro para eliminar"));
			return;
		}
		 planAux.setEstado_plan("D");
		 srvPlan.actualizaPlan(planAux);
//		srvPlan.eliminaPlan(planAux);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstPlanRamAseg = new ArrayList<PlanRamoAseguradoraView>();
		lstPlanRamAseg = srvPlanesRamoAseg.consultaPlanRamoAseguradora();
	}

	public void nuevaCobertura() {
		if (codPlan.equals("0")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un plan"));
			return;
		}

		porcCob = 0.0;
		valorCob = 0.0;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaCobertura').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaCobertura').show();");
		recuperarCoberturas();
	}

	public void guardaCobertura() {
		int codAsegCob;
		AseguradoraCobertura asegCob = new AseguradoraCobertura();
		for (Coberturas cobTmp : selectedLstCoberturas) {
			asegCob = srvAseguradoraCob.consultaAseguradoraCobertura(String.valueOf(cobTmp.getCd_cobertura()),
					String.valueOf(selectedPlanRamAseg.getCd_aseguradora()));
			if (asegCob == null) {
				asegCob = new AseguradoraCobertura();
				asegCob.setCd_aseguradora(Integer.valueOf(selectedPlanRamAseg.getCd_aseguradora()));
				asegCob.setCd_cobertura(cobTmp.getCd_cobertura());
				asegCob.setEstado_asegcob("A");
				srvAseguradoraCob.insertarAseguradoraCobertura(asegCob);
				codAsegCob = srvAseguradoraCob.codigoMaxAseguradoraCobertura();
			} else {
				codAsegCob = asegCob.getCd_asegcob();
			}
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			PlanCobertura planCobAux = new PlanCobertura();
			planCobAux = srvPlanCobertura.consultaPlanCobertura(String.valueOf(codAsegCob),
					selectedPlanRamAseg.getCd_plan());
			if (planCobAux == null) {
				planCobAux = new PlanCobertura();
				planCobAux.setCd_plan(Integer.valueOf(selectedPlanRamAseg.getCd_plan()));
				planCobAux.setCd_asegcob(codAsegCob);
				if (porcCob != 0.0)
					planCobAux.setPorcentajeplancobertura(porcCob);
				if (valorCob != 0.0)
					planCobAux.setValor_plancobertura(valorCob);
				planCobAux.setEstado_plancobertura("A");
				
				try {
					if (!especificacionCob.isEmpty() || especificacionCob != null) {
						planCobAux.setEspecificacion(especificacionCob);
					}
				} catch (Exception e) {
					planCobAux.setEspecificacion("-");
				}
				
				srvPlanCobertura.insertarPlanCobertura(planCobAux);
			}

		}
		lstCoberturasPlan = new ArrayList<CoberturasPlanView>();
		lstCoberturasPlan = srvPlanCoberturas.consultaCoberturasPlan(selectedPlanRamAseg.getCd_plan(),
				selectedPlanRamAseg.getCd_aseguradora());
	}

	public void eliminaCobertura() {

		String cdAsegCob = "0";
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			cdAsegCob = selectedCoberturasPlan.getCd_asegcob();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Cobertura"));
			lstCoberturasPlan = new ArrayList<CoberturasPlanView>();
			lstCoberturasPlan = srvPlanCoberturas.consultaCoberturasPlan(selectedPlanRamAseg.getCd_plan(),
					selectedPlanRamAseg.getCd_aseguradora());
			selectedLstCoberturas = new ArrayList<Coberturas>();
			lstFilteredCoberturas = new ArrayList<Coberturas>();
		}
		PlanCobertura planCobAux = new PlanCobertura();
		planCobAux = srvPlanCobertura.consultaPlanCobertura(cdAsegCob, selectedPlanRamAseg.getCd_plan());
		srvPlanCobertura.eliminaPlanCobertura(planCobAux);
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstCoberturasPlan = new ArrayList<CoberturasPlanView>();
		lstCoberturasPlan = srvPlanCoberturas.consultaCoberturasPlan(selectedPlanRamAseg.getCd_plan(),
				selectedPlanRamAseg.getCd_aseguradora());
	}

	public void nuevoDeducible() {
		try {
			selectedPlanRamAseg.getCd_plan();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un plan"));
			return;
		}

		porcDedValSin = 0.0;
		porcDedValAseg = 0.0;
		valorDedMin = 0.0;
		valorDedFijo = 0.0;
		lstDeducibles = new ArrayList<Deducibles>();
		lstDeducibles = srvDeducibles.consultaDeducibles();
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevoDeducible').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').show();");

	}

	public void guardaDeducible() {
		int codAsegDed;
		AseguradoraDeducible asegDed = new AseguradoraDeducible();
		for (Deducibles dedTmp : selectedlstDeducibles) {
			asegDed = srvAsegDeducible.consultaAseguradoraDeducible(
					String.valueOf(selectedPlanRamAseg.getCd_aseguradora()), String.valueOf(dedTmp.getCd_deducible()));
			if (asegDed == null) {
				asegDed = new AseguradoraDeducible();
				asegDed.setEstado_asegded("A");
				asegDed.setCd_aseguradora(Integer.valueOf(selectedPlanRamAseg.getCd_aseguradora()));
				asegDed.setCd_deducible(dedTmp.getCd_deducible());
				srvAsegDeducible.insertarAseguradoraDeducible(asegDed);
				codAsegDed = srvAsegDeducible.codigoMaxAseguradoraDeducible();
			} else {
				codAsegDed = asegDed.getCd_asegded();
			}
			// VERIFICA SIEL DEDUCIBLE YA SE ENCUENTRA INGRESADO
			PlanDeducible planDedAux = new PlanDeducible();
//			planDedAux = srvPlanDeducible.consultaPlanDeducible(String.valueOf(codAsegDed),
//					selectedPlanRamAseg.getCd_plan());
//			if (planDedAux == null) {
				planDedAux = new PlanDeducible();
				planDedAux.setCd_plan(Integer.valueOf(selectedPlanRamAseg.getCd_plan()));
				planDedAux.setCd_asegded(codAsegDed);
				if (porcDedValAseg != 0.0)
					planDedAux.setPorcentaje_valor_asegurado(porcDedValAseg);
				if (porcDedValSin != 0.0)
					planDedAux.setPorcentaje_valor_siniestro(porcDedValSin);
				if (valorDedFijo != 0.0)
					planDedAux.setValor_fijo(valorDedFijo);
				if (valorDedMin != 0.0)
					planDedAux.setValor_minimo(valorDedMin);
				try {
					if (!especificacionDed.isEmpty() || especificacionDed != null) {
						planDedAux.setEspecificacion(especificacionDed);
					}
				} catch (Exception e) {
					planDedAux.setEspecificacion("-");
				}
				planDedAux.setEstado_plandeducible("A");
				srvPlanDeducible.insertarPlanDeducible(planDedAux);
//			}
		}
		lstPlanDeducibles = new ArrayList<PlanDeduciblesView>();
		lstPlanDeducibles = srvPlanDeducibleView.consultaDeduciblePlan(selectedPlanRamAseg.getCd_plan(),
				selectedPlanRamAseg.getCd_aseguradora());
	}

	public void eliminaDeducible() {
		PlanDeducible planDedAux = new PlanDeducible();
		try {
			selectedPlanDeducibles.getCd_asegded();
			planDedAux = srvPlanDeducible.consultaPlanDeducible(selectedPlanDeducibles.getCd_asegded(),
					selectedPlanRamAseg.getCd_plan());
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro para eliminar"));
			return;
		}
		srvPlanDeducible.eliminaPlanDeducible(planDedAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstPlanDeducibles = new ArrayList<PlanDeduciblesView>();
		lstPlanDeducibles = srvPlanDeducibleView.consultaDeduciblePlan(selectedPlanRamAseg.getCd_plan(),
				selectedPlanRamAseg.getCd_aseguradora());
	}

	public void nuevaClausula() {

		if (codPlan.equals("0")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un plan"));
			return;
		}

		// try {
		// System.out.println("Plan
		// Seleccionado:"+selectedPlanRamAseg.getCd_plan());
		// } catch (Exception e) {
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.addMessage(null, new FacesMessage("Advertencia", "Seleccione
		// un plan"));
		// return;
		// }

		porcClau = 0.0;
		valorClau = 0.0;
		lstClausulas = new ArrayList<Clausulas>();
		lstClausulas = srvClausulas.consultaClausulas();
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaClausula').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaClausula').show();");
	}

	public void guardaClausula() {
		int codAsegClau;
		AseguradoraClausula asegClau = new AseguradoraClausula();
		for (Clausulas caluTmp : selectedlstClausulas) {
			System.out.println("INGRESOO");
			asegClau = srvAsegClausula.consultaAseguradoraClausula(String.valueOf(caluTmp.getCd_clausula()),
					String.valueOf(selectedPlanRamAseg.getCd_aseguradora()));
			if (asegClau == null) {
				asegClau = new AseguradoraClausula();
				asegClau.setEstado_asegclau("A");
				asegClau.setCd_aseguradora(Integer.valueOf(selectedPlanRamAseg.getCd_aseguradora()));
				asegClau.setCd_clausula(caluTmp.getCd_clausula());
				
				
				
				srvAsegClausula.insertarAseguradoraClausula(asegClau);
				codAsegClau = srvAsegClausula.codigoMaxAseguradoraClausula();
			} else {
				codAsegClau = asegClau.getCd_asegclau();
			}
			// VERIFICA SI clausula YA SE ENCUENTRA INGRESADO
			PlanClausula planClauAux = new PlanClausula();
			planClauAux = srvPlanClausula.consultaPlanClausula(String.valueOf(codAsegClau),
					selectedPlanRamAseg.getCd_plan());
			if (planClauAux == null || planClauAux.getEstado_planclausula().equals("D")) {
				planClauAux = new PlanClausula();
				planClauAux.setCd_plan(Integer.valueOf(selectedPlanRamAseg.getCd_plan()));
				planClauAux.setCd_asegclau(codAsegClau);
				if (porcClau != 0.0)
					planClauAux.setPorcentaje_planclausula(porcClau);
				if (valorClau != 0.0)
					planClauAux.setValor_planclausula(valorClau);
				planClauAux.setEstado_planclausula("A");
				try {
					if (!especificacionCla.isEmpty() || especificacionCla != null) {
						planClauAux.setEspecificacion(especificacionCla);
					}
				} catch (Exception e) {
					planClauAux.setEspecificacion("-");
				}
				srvPlanClausula.insertarPlanClausula(planClauAux);
			}
		}
		lstPlanCalusulaView = new ArrayList<PlanClausulasView>();
		lstPlanCalusulaView = srvPlanCalusulasView.consultaPlanClausulasView(selectedPlanRamAseg.getCd_plan(),
				selectedPlanRamAseg.getCd_aseguradora());
	}

	public void eliminaClausula() {
		PlanClausula planClauAux = new PlanClausula();
		try {
			selectedPlanCalusulaView.getCd_asegclau();
			planClauAux = srvPlanClausula.consultaPlanClausula(selectedPlanCalusulaView.getCd_asegclau(),
					selectedPlanRamAseg.getCd_plan());
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro para eliminar"));
			return;
		}
		srvPlanClausula.eliminaPlanClausula(planClauAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstPlanCalusulaView = new ArrayList<PlanClausulasView>();
		lstPlanCalusulaView = srvPlanCalusulasView.consultaPlanClausulasView(selectedPlanRamAseg.getCd_plan(),
				selectedPlanRamAseg.getCd_aseguradora());
	}

	public void onRowEditPlan(RowEditEvent event) {
		Plan planAux = new Plan();
		planAux = srvPlan.consultaPlan(Integer.valueOf(((PlanRamoAseguradoraView) event.getObject()).getCd_plan()));
		String descPlan;
		descPlan = ((PlanRamoAseguradoraView) event.getObject()).getDescripcionPlan().trim().toUpperCase();
		System.out.println("DESCRIPCION INSERTAR:" + descPlan);
		planAux.setDescripcion_plan(descPlan);
		srvPlan.actualizaPlan(planAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
	}

	public void onRowEditCobertura(RowEditEvent event) {
		PlanCobertura planCobAux = new PlanCobertura();
		planCobAux = srvPlanCobertura
				.consultaPlanCoberturaCodPlanCob(((CoberturasPlanView) event.getObject()).getCd_plancobertura());
		Double val, porc;
		try {
			val = Double.valueOf(((CoberturasPlanView) event.getObject()).getValor_plancobertura());
		} catch (Exception e) {
			val = 0.0;
		}
		
		try {
			porc = Double.valueOf(((CoberturasPlanView) event.getObject()).getPorcentajeplancobertura());
		} catch (Exception e) {
			porc = 0.0;
		}
		
		
		planCobAux.setValor_plancobertura(val);
		planCobAux.setPorcentajeplancobertura(porc);
		String espe;
		try {
			espe = ((CoberturasPlanView) event.getObject()).getEspecificacion_cob();
			System.out.println("INGRESO espe:"+espe);
		} catch (Exception e) {
			espe = "-";
		}
		System.out.println("INGRESO espe:"+espe);
		planCobAux.setEspecificacion(espe);
		srvPlanCobertura.actualizaPlanCobertura(planCobAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
	}

	public void onRowEditDeducible(RowEditEvent event) {
		PlanDeducible planDedAux = new PlanDeducible();
		planDedAux = srvPlanDeducible
				.consultaPlanDeducibleCodPlanDe(((PlanDeduciblesView) event.getObject()).getCd_plandeducible());
		Double valmin, valfijo, porcSin, porcASe;
		String espe;
		try {
			valmin = Double.valueOf(((PlanDeduciblesView) event.getObject()).getValor_minimo());
		} catch (Exception e) {
			valmin = 0.0;
		}
		try {
			valfijo = Double.valueOf(((PlanDeduciblesView) event.getObject()).getValor_fijo());
		} catch (Exception e) {
			valfijo = 0.0;
		}
		try {
			porcSin = Double.valueOf(((PlanDeduciblesView) event.getObject()).getPorcentaje_valor_siniestro());
		} catch (Exception e) {
			porcSin = 0.0;
		}
		try {
			porcASe = Double.valueOf(((PlanDeduciblesView) event.getObject()).getPorcentaje_valor_asegurado());
		} catch (Exception e) {
			porcASe = 0.0;
		}
		
		
		try {
			espe = ((PlanDeduciblesView) event.getObject()).getEspecificacionDed();
		} catch (Exception e) {
			espe = "";
		}
		planDedAux.setValor_minimo(valmin);
		planDedAux.setValor_fijo(valfijo);
		planDedAux.setPorcentaje_valor_siniestro(porcSin);
		planDedAux.setPorcentaje_valor_asegurado(porcASe);
		planDedAux.setEspecificacion(espe);
		srvPlanDeducible.actualizaPlanDeducible(planDedAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
	}

	public void onRowEditClausula(RowEditEvent event) {
		PlanClausula planClauAux = new PlanClausula();
		planClauAux = srvPlanClausula
				.consultaPlanClausulaCodPlanCla(((PlanClausulasView) event.getObject()).getCd_planclausula());
		Double val, porc;
		
		try {
			val = Double.valueOf(((PlanClausulasView) event.getObject()).getValor_planclausula());
		} catch (Exception e) {
			val = 0.00;
		}
		try {
			porc = Double.valueOf(((PlanClausulasView) event.getObject()).getPorcentaje_planclausula());
		} catch (Exception e) {
			porc = 0.00;
		}
		
		
		planClauAux.setValor_planclausula(val);
		planClauAux.setPorcentaje_planclausula(porc);
		
		String espe;
		try {
			espe = ((PlanClausulasView) event.getObject()).getEspecificacion_cla();
		} catch (Exception e) {
			espe = "";
		}
		planClauAux.setEspecificacion(espe);
		srvPlanClausula.actualizaPlanClausula(planClauAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
	}

	public List<PlanRamoAseguradoraView> getLstPlanRamAseg() {
		return lstPlanRamAseg;
	}

	public void setLstPlanRamAseg(List<PlanRamoAseguradoraView> lstPlanRamAseg) {
		this.lstPlanRamAseg = lstPlanRamAseg;
	}

	public PlanRamoAseguradoraView getSelectedPlanRamAseg() {
		return selectedPlanRamAseg;
	}

	public void setSelectedPlanRamAseg(PlanRamoAseguradoraView selectedPlanRamAseg) {
		this.selectedPlanRamAseg = selectedPlanRamAseg;
	}

	public List<PlanRamoAseguradoraView> getLstFilteredPlanRamAseg() {
		return lstFilteredPlanRamAseg;
	}

	public void setLstFilteredPlanRamAseg(List<PlanRamoAseguradoraView> lstFilteredPlanRamAseg) {
		this.lstFilteredPlanRamAseg = lstFilteredPlanRamAseg;
	}

	public List<Aseguradoras> getLstAseguradora() {
		return lstAseguradora;
	}

	public void setLstAseguradora(List<Aseguradoras> lstAseguradora) {
		this.lstAseguradora = lstAseguradora;
	}

	public String getCodAseg() {
		return codAseg;
	}

	public void setCodAseg(String codAseg) {
		this.codAseg = codAseg;
	}

	public String getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}

	public List<Ramo> getLstRamo() {
		return lstRamo;
	}

	public void setLstRamo(List<Ramo> lstRamo) {
		this.lstRamo = lstRamo;
	}

	public String getDescPlan() {
		return descPlan;
	}

	public void setDescPlan(String descPlan) {
		this.descPlan = descPlan;
	}

	public List<CoberturasPlanView> getLstCoberturasPlan() {
		return lstCoberturasPlan;
	}

	public void setLstCoberturasPlan(List<CoberturasPlanView> lstCoberturasPlan) {
		this.lstCoberturasPlan = lstCoberturasPlan;
	}

	public List<CoberturasPlanView> getLstFilteredCoberturasPlan() {
		return lstFilteredCoberturasPlan;
	}

	public void setLstFilteredCoberturasPlan(List<CoberturasPlanView> lstFilteredCoberturasPlan) {
		this.lstFilteredCoberturasPlan = lstFilteredCoberturasPlan;
	}

	public CoberturasPlanView getSelectedCoberturasPlan() {
		return selectedCoberturasPlan;
	}

	public void setSelectedCoberturasPlan(CoberturasPlanView selectedCoberturasPlan) {
		this.selectedCoberturasPlan = selectedCoberturasPlan;
	}

	public List<Coberturas> getLstCoberturas() {
		return lstCoberturas;
	}

	public void setLstCoberturas(List<Coberturas> lstCoberturas) {
		this.lstCoberturas = lstCoberturas;
	}

	public List<Coberturas> getSelectedLstCoberturas() {
		return selectedLstCoberturas;
	}

	public void setSelectedLstCoberturas(List<Coberturas> selectedLstCoberturas) {
		this.selectedLstCoberturas = selectedLstCoberturas;
	}

	public Double getPorcCob() {
		return porcCob;
	}

	public void setPorcCob(Double porcCob) {
		this.porcCob = porcCob;
	}

	public Double getValorCob() {
		return valorCob;
	}

	public void setValorCob(Double valorCob) {
		this.valorCob = valorCob;
	}

	public List<Coberturas> getLstFilteredCoberturas() {
		return lstFilteredCoberturas;
	}

	public void setLstFilteredCoberturas(List<Coberturas> lstFilteredCoberturas) {
		this.lstFilteredCoberturas = lstFilteredCoberturas;
	}

	public List<PlanDeduciblesView> getLstPlanDeducibles() {
		return lstPlanDeducibles;
	}

	public void setLstPlanDeducibles(List<PlanDeduciblesView> lstPlanDeducibles) {
		this.lstPlanDeducibles = lstPlanDeducibles;
	}

	public PlanDeduciblesView getSelectedPlanDeducibles() {
		return selectedPlanDeducibles;
	}

	public void setSelectedPlanDeducibles(PlanDeduciblesView selectedPlanDeducibles) {
		this.selectedPlanDeducibles = selectedPlanDeducibles;
	}

	public List<PlanDeduciblesView> getLstFilteredPlanDeducibles() {
		return lstFilteredPlanDeducibles;
	}

	public void setLstFilteredPlanDeducibles(List<PlanDeduciblesView> lstFilteredPlanDeducibles) {
		this.lstFilteredPlanDeducibles = lstFilteredPlanDeducibles;
	}

	public List<Deducibles> getLstDeducibles() {
		return lstDeducibles;
	}

	public void setLstDeducibles(List<Deducibles> lstDeducibles) {
		this.lstDeducibles = lstDeducibles;
	}

	public List<Deducibles> getSelectedlstDeducibles() {
		return selectedlstDeducibles;
	}

	public void setSelectedlstDeducibles(List<Deducibles> selectedlstDeducibles) {
		this.selectedlstDeducibles = selectedlstDeducibles;
	}

	public Double getPorcDedValSin() {
		return porcDedValSin;
	}

	public void setPorcDedValSin(Double porcDedValSin) {
		this.porcDedValSin = porcDedValSin;
	}

	public Double getPorcDedValAseg() {
		return porcDedValAseg;
	}

	public void setPorcDedValAseg(Double porcDedValAseg) {
		this.porcDedValAseg = porcDedValAseg;
	}

	public Double getValorDedMin() {
		return valorDedMin;
	}

	public void setValorDedMin(Double valorDedMin) {
		this.valorDedMin = valorDedMin;
	}

	public Double getValorDedFijo() {
		return valorDedFijo;
	}

	public void setValorDedFijo(Double valorDedFijo) {
		this.valorDedFijo = valorDedFijo;
	}

	public List<Deducibles> getLstFilteredDeducibles() {
		return lstFilteredDeducibles;
	}

	public void setLstFilteredDeducibles(List<Deducibles> lstFilteredDeducibles) {
		this.lstFilteredDeducibles = lstFilteredDeducibles;
	}

	public List<PlanClausulasView> getLstPlanCalusulaView() {
		return lstPlanCalusulaView;
	}

	public void setLstPlanCalusulaView(List<PlanClausulasView> lstPlanCalusulaView) {
		this.lstPlanCalusulaView = lstPlanCalusulaView;
	}

	public PlanClausulasView getSelectedPlanCalusulaView() {
		return selectedPlanCalusulaView;
	}

	public void setSelectedPlanCalusulaView(PlanClausulasView selectedPlanCalusulaView) {
		this.selectedPlanCalusulaView = selectedPlanCalusulaView;
	}

	public List<PlanClausulasView> getLstFilteredPlanCalusulaView() {
		return lstFilteredPlanCalusulaView;
	}

	public void setLstFilteredPlanCalusulaView(List<PlanClausulasView> lstFilteredPlanCalusulaView) {
		this.lstFilteredPlanCalusulaView = lstFilteredPlanCalusulaView;
	}

	public List<Clausulas> getLstClausulas() {
		return lstClausulas;
	}

	public void setLstClausulas(List<Clausulas> lstClausulas) {
		this.lstClausulas = lstClausulas;
	}

	public List<Clausulas> getSelectedlstClausulas() {
		return selectedlstClausulas;
	}

	public void setSelectedlstClausulas(List<Clausulas> selectedlstClausulas) {
		this.selectedlstClausulas = selectedlstClausulas;
	}

	public List<Clausulas> getLstFilteredClausulas() {
		return lstFilteredClausulas;
	}

	public void setLstFilteredClausulas(List<Clausulas> lstFilteredClausulas) {
		this.lstFilteredClausulas = lstFilteredClausulas;
	}

	public Double getPorcClau() {
		return porcClau;
	}

	public void setPorcClau(Double porcClau) {
		this.porcClau = porcClau;
	}

	public Double getValorClau() {
		return valorClau;
	}

	public void setValorClau(Double valorClau) {
		this.valorClau = valorClau;
	}

	public String getCodAsegIns() {
		return codAsegIns;
	}

	public void setCodAsegIns(String codAsegIns) {
		this.codAsegIns = codAsegIns;
	}

	public List<PlanDepreciacion> getLstPlanDepreciacion() {
		return lstPlanDepreciacion;
	}

	public void setLstPlanDepreciacion(List<PlanDepreciacion> lstPlanDepreciacion) {
		this.lstPlanDepreciacion = lstPlanDepreciacion;
	}

	public PlanDepreciacion getSelectedPlanDepreciacion() {
		return selectedPlanDepreciacion;
	}

	public void setSelectedPlanDepreciacion(PlanDepreciacion selectedPlanDepreciacion) {
		this.selectedPlanDepreciacion = selectedPlanDepreciacion;
	}

	public Integer getNumAnioDepre() {
		return numAnioDepre;
	}

	public void setNumAnioDepre(Integer numAnioDepre) {
		this.numAnioDepre = numAnioDepre;
	}

	public Boolean getBtnNmCob() {
		return btnNmCob;
	}

	public void setBtnNmCob(Boolean btnNmCob) {
		this.btnNmCob = btnNmCob;
	}

	public String getInptNmCob() {
		return inptNmCob;
	}

	public void setInptNmCob(String inptNmCob) {
		this.inptNmCob = inptNmCob;
	}

	public String getEspecificacionDed() {
		return especificacionDed;
	}

	public void setEspecificacionDed(String especificacionDed) {
		this.especificacionDed = especificacionDed;
	}

	public String getEspecificacionCob() {
		return especificacionCob;
	}

	public void setEspecificacionCob(String especificacionCob) {
		this.especificacionCob = especificacionCob;
	}

	public String getEspecificacionCla() {
		return especificacionCla;
	}

	public void setEspecificacionCla(String especificacionCla) {
		this.especificacionCla = especificacionCla;
	}
	

}
