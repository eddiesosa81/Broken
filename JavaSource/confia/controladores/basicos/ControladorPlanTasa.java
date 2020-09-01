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

import confia.entidades.basicos.Plan;
import confia.entidades.basicos.PlanCobertura;
import confia.entidades.basicos.PlanTasa;
import confia.entidades.vistas.CoberturasPlanView;
import confia.entidades.vistas.PlanTasaView;
import confia.servicios.basicos.ServicioPlan;
import confia.servicios.basicos.ServicioPlanTasa;
import confia.servicios.vistas.ServicioPlanesTasasView;

@ManagedBean(name = "ControladorPlanTasa")
@ViewScoped
public class ControladorPlanTasa {
	@EJB
	private ServicioPlanesTasasView srvPlanTasaView;
	@EJB
	private ServicioPlan srvPlan;
	@EJB
	private ServicioPlanTasa srvPlanTasa;
	
	
	private List<Plan> lstPlan;
	private List<Plan> lstFilteredPlan;
	private Plan selectedPlan;
	private List<PlanTasaView> lstPlanTasaView;
	private PlanTasaView selectedPlanTasaView;
	private Double tasaCliente;
	private Double tasaCanal;
	private Double tasaConfia;

	public ControladorPlanTasa() {
		lstPlan = new ArrayList<Plan>();
		tasaCliente = 0.0;
		tasaCanal = 0.0;
		tasaConfia = 0.0;
	}
	@PostConstruct
	public void recuperaIni() {
		lstPlan = srvPlan.listaPlanesTot();
	}

	public void onRowSelect(SelectEvent event) {
		lstPlanTasaView = new ArrayList<PlanTasaView>();
		lstPlanTasaView = srvPlanTasaView.consultaPlanTasaView(String.valueOf(((Plan)event.getObject()).getCd_plan()));
	}

	public void onRowEditPlanTasa(RowEditEvent event) {
		PlanTasa auxPlanTasa = new PlanTasa();
		auxPlanTasa = srvPlanTasa.consultaPlanTasa(((PlanTasaView)event.getObject()).getCd_plantasa());
		auxPlanTasa.setTasa_canal(Double.valueOf(((PlanTasaView)event.getObject()).getTasa_canal()));
		auxPlanTasa.setTasa_cliente(Double.valueOf(((PlanTasaView)event.getObject()).getTasa_cliente()));
		auxPlanTasa.setTasa_confia(Double.valueOf(((PlanTasaView)event.getObject()).getTasa_confia()));
		srvPlanTasa.actualizaPlanTasa(auxPlanTasa);
	}

	public void nuevaTasa() {
		lstPlanTasaView = new ArrayList<PlanTasaView>();
		lstPlanTasaView = srvPlanTasaView.consultaPlanTasaView(String.valueOf(selectedPlan.getCd_plan()));
		if (lstPlanTasaView.size() != 0) {
			return;
		}
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaTasa').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaTasa').show();");

	}

	public void eliminaTasa() {
		PlanTasa auxPlanTasa = new PlanTasa();
		auxPlanTasa = srvPlanTasa.consultaPlanTasa(String.valueOf(selectedPlan.getCd_plan()));
		if (auxPlanTasa == null) {
			return;
		}
		srvPlanTasa.eliminaPlanTasa(auxPlanTasa);
		lstPlanTasaView = new ArrayList<PlanTasaView>();
		lstPlanTasaView = srvPlanTasaView.consultaPlanTasaView(String.valueOf(selectedPlan.getCd_plan()));
	}
	
	public void guardaTasa() {
		PlanTasa planTasaAux = new PlanTasa();
		planTasaAux.setCd_plan(selectedPlan.getCd_plan());
		planTasaAux.setEstado_plantasa("A");
		planTasaAux.setTasa_canal(tasaCanal);
		planTasaAux.setTasa_cliente(tasaCliente);
		planTasaAux.setTasa_confia(tasaConfia);
		srvPlanTasa.insertarPlanTasa(planTasaAux);
		lstPlanTasaView = new ArrayList<PlanTasaView>();
		lstPlanTasaView = srvPlanTasaView.consultaPlanTasaView(String.valueOf(selectedPlan.getCd_plan()));
		tasaCanal = 0.00;
		tasaCliente = 0.00;
		tasaConfia = 0.00;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaTasa').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaTasa').hide();");

	}

	public void guardaSiniestro() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./indexGestion.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Plan> getLstPlan() {
		return lstPlan;
	}

	public void setLstPlan(List<Plan> lstPlan) {
		this.lstPlan = lstPlan;
	}

	public Plan getSelectedPlan() {
		return selectedPlan;
	}

	public void setSelectedPlan(Plan selectedPlan) {
		this.selectedPlan = selectedPlan;
	}

	public List<Plan> getLstFilteredPlan() {
		return lstFilteredPlan;
	}

	public void setLstFilteredPlan(List<Plan> lstFilteredPlan) {
		this.lstFilteredPlan = lstFilteredPlan;
	}

	public List<PlanTasaView> getLstPlanTasaView() {
		return lstPlanTasaView;
	}

	public void setLstPlanTasaView(List<PlanTasaView> lstPlanTasaView) {
		this.lstPlanTasaView = lstPlanTasaView;
	}

	public PlanTasaView getSelectedPlanTasaView() {
		return selectedPlanTasaView;
	}

	public void setSelectedPlanTasaView(PlanTasaView selectedPlanTasaView) {
		this.selectedPlanTasaView = selectedPlanTasaView;
	}

	public Double getTasaCliente() {
		return tasaCliente;
	}

	public void setTasaCliente(Double tasaCliente) {
		this.tasaCliente = tasaCliente;
	}

	public Double getTasaCanal() {
		return tasaCanal;
	}

	public void setTasaCanal(Double tasaCanal) {
		this.tasaCanal = tasaCanal;
	}

	public Double getTasaConfia() {
		return tasaConfia;
	}

	public void setTasaConfia(Double tasaConfia) {
		this.tasaConfia = tasaConfia;
	}

}
