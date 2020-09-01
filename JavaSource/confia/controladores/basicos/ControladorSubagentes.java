package confia.controladores.basicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.ComisionSubagente;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Plan;
import confia.entidades.basicos.Ramo;
import confia.entidades.basicos.SubagenteRamo;
import confia.entidades.basicos.Subagentes;
import confia.entidades.vistas.ComisionesSubagentesView;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioComisionSubagente;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioPlan;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioSubagenteRamo;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.vistas.ServicioComisionesSubagentesView;

@ViewScoped
@ManagedBean

public class ControladorSubagentes {

	@EJB
	private ServicioSubagentes srvSubagentes;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioAseguradoras srvAseguradora;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioPlan srvPlan;
	@EJB
	private ServicioSubagenteRamo srvSubagenteRamo;
	@EJB
	private ServicioComisionSubagente srvComisionSubagente;
	@EJB
	private ServicioComisionesSubagentesView srvComisionesSubagenView;

	private Subagentes getServObj;
	private Subagentes datosSuagente;
	private List<Subagentes> lstSubagentes;
	private List<Subagentes> selectedLstSubagentes;
	private Subagentes selectedSuagente;

	private String tipo_persona_subagente;
	private String tipo_identificacion_subagente;
	private Integer identificacion_subagente;
	private String razonsocial_subagente;
	private String primer_nombre_subagente;
	private String segundo_nombre_subagente;
	private String primer_apellido_subagente;
	private String segundo_apellido_subagente;
	private Date fecha_nacimiento_subagente;
	private String estado_subagente;
	private String fecha_creacion_subagente;
	private String tipoId;
	private List<Ramo> lstRamo;
	private List<Ramo> selectedLstRamo;
	private List<Aseguradoras> lstAseguradoras;
	private String selectedAseg;
	private List<GrupoContratante> lstGrupoContra;
	private String selectedGrpContr;
	private List<Plan> lstPlan;
	private String selectedPlan;
	private Double valComi;
	private List<ComisionesSubagentesView> lstComisionesSubagenteView;
	private ComisionesSubagentesView selecterComisionSubaView;
	private String canalCiudad;

	public ControladorSubagentes() {
		lstSubagentes = new ArrayList<Subagentes>();
		tipoId = null;
		lstRamo = new ArrayList<Ramo>();
		lstAseguradoras = new ArrayList<Aseguradoras>();
		lstGrupoContra = new ArrayList<GrupoContratante>();
		lstPlan = new ArrayList<Plan>();
		lstComisionesSubagenteView = new ArrayList<ComisionesSubagentesView>();
	}

	@PostConstruct
	public void recuperaDatos() {
		lstSubagentes = srvSubagentes.recuperaSubagente();
	}

	public void AgregarSubagente() {
		canalCiudad = null;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgSubagen').show();");
		PrimeFaces.current().executeScript("PF('wDlgSubagen').show();");
	}

	public void AgregarComSuba() {
		if (selectedSuagente == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione un Subagente para ingresar la Comisión"));
		} else {
			lstRamo = new ArrayList<Ramo>();
			lstRamo = srvRamo.listaRamos();
			lstAseguradoras = new ArrayList<Aseguradoras>();
			lstAseguradoras = srvAseguradora.listaAseguradoras();
			lstGrupoContra = new ArrayList<GrupoContratante>();
			lstGrupoContra = srvGrupoContratante.listaGruposContratantes();
			lstPlan = new ArrayList<Plan>();
			lstPlan = srvPlan.listaPlanesTot();
			valComi = 0.0;
			lstComisionesSubagenteView = new ArrayList<ComisionesSubagentesView>();
			lstComisionesSubagenteView = srvComisionesSubagenView
					.consultaComisionesSubagentesView(selectedSuagente.getCd_subagente());
//			RequestContext contextDlg = RequestContext.getCurrentInstance();
//			contextDlg.execute("PF('wDlgComSuba').show();");
			PrimeFaces.current().executeScript("PF('wDlgComSuba').show();");
		}
	}

	public void guardaComSuba() {
		Integer codSubagenteramo = 0, codAseg, codGrpCom, codPlan;
		if (selectedLstRamo.isEmpty()) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Ramo"));
			return;
		}
		for (Ramo ramoAux : selectedLstRamo) {
			// verifica si existe el RamoSubagente creado
			SubagenteRamo subaRamAux = new SubagenteRamo();
			subaRamAux = srvSubagenteRamo.consultaSubagenteRamo(selectedSuagente.getCd_subagente(),
					ramoAux.getCd_ramo());
			if (subaRamAux == null) {
				subaRamAux = new SubagenteRamo();
				subaRamAux.setCd_ramo(ramoAux.getCd_ramo());
				subaRamAux.setCd_subagente(selectedSuagente.getCd_subagente());
				srvSubagenteRamo.insertarSubagenteRamo(subaRamAux);
				codSubagenteramo = srvSubagenteRamo.codigoMaxSubagenteRamo();
			}
		}
		for (Ramo ramoAux : selectedLstRamo) {
			// verifica si existe el RamoSubagente creado
			SubagenteRamo subaRamAux = new SubagenteRamo();
			subaRamAux = srvSubagenteRamo.consultaSubagenteRamo(selectedSuagente.getCd_subagente(),
					ramoAux.getCd_ramo());
			codSubagenteramo = subaRamAux.getCd_subagenteramo();
			// Inserto la comisión del subagente
			if (selectedAseg.equals("%")) {
				codAseg = 0;
			} else {
				codAseg = Integer.valueOf(selectedAseg);
			}
			if (selectedGrpContr.equals("%")) {
				codGrpCom = 0;
			} else {
				codGrpCom = Integer.valueOf(selectedGrpContr);
			}
			if (selectedPlan.equals("%")) {
				codPlan = 0;
			} else {
				codPlan = Integer.valueOf(selectedPlan);
			}
			ComisionSubagente comiSubaAux = new ComisionSubagente();
			comiSubaAux = srvComisionSubagente.consultaComisionSubagente(codSubagenteramo, codAseg, codGrpCom, codPlan);
			if (comiSubaAux == null) {
				comiSubaAux = new ComisionSubagente();
				comiSubaAux.setCd_subagenteramo(codSubagenteramo);
				comiSubaAux.setCd_aseguradora(codAseg);
				comiSubaAux.setCd_grupo_contratante(codGrpCom);
				comiSubaAux.setCd_plan(codPlan);
				comiSubaAux.setPorc_comision(valComi);
				srvComisionSubagente.insertarComisionSubagente(comiSubaAux);
				lstComisionesSubagenteView = new ArrayList<ComisionesSubagentesView>();
				lstComisionesSubagenteView = srvComisionesSubagenView
						.consultaComisionesSubagentesView(selectedSuagente.getCd_subagente());
			} else {
				FacesContext contextMsj = FacesContext.getCurrentInstance();
				contextMsj.addMessage(null,
						new FacesMessage("Advertencia", "El registro ya existe en la Base de Datos"));
				return;
			}
		}
	}

	public void eliminaComision() {
		if (selecterComisionSubaView == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el registro para eliminar"));
			return;
		}
		ComisionSubagente comSuba = new ComisionSubagente();
		comSuba = srvComisionSubagente
				.consultaComisionSubagenteXCod(selecterComisionSubaView.getCd_comisionsubagente());
		srvComisionSubagente.eliminaComisionSubagente(comSuba);
		lstComisionesSubagenteView = new ArrayList<ComisionesSubagentesView>();
		lstComisionesSubagenteView = srvComisionesSubagenView
				.consultaComisionesSubagentesView(selectedSuagente.getCd_subagente());
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro eliminado"));
	}

	public void guardaSubagente() {
		try {
			if(canalCiudad.isEmpty() || canalCiudad == null){
				canalCiudad = "S/N";
			}
		} catch (Exception e) {
			canalCiudad = "S/N";
		}
		

		Subagentes sub = new Subagentes();
		sub.setIdentificacion_subagente(String.valueOf(identificacion_subagente));
		sub.setPrimer_nombre_subagente(primer_nombre_subagente);
		sub.setSegundo_nombre_subagente(segundo_nombre_subagente);
		sub.setPrimer_apellido_subagente(primer_apellido_subagente);
		sub.setSegundo_apellido_subagente(segundo_apellido_subagente);
		sub.setRazonSocial_subagente(razonsocial_subagente);
		sub.setTipo_identificacion_subagente(tipo_identificacion_subagente);
		sub.setTipo_persona_subagente(tipo_persona_subagente);
		sub.setFecha_nacimiento_subagente(fecha_nacimiento_subagente);
		// sub.setFecha_creacion_subagente(fecha_creacion_subagente);
		sub.setEstado_subagente("A");
		sub.setCiudad_canal(canalCiudad);

		srvSubagentes.insertarSuagentes(sub);

		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgSubagen').hide();");
		PrimeFaces.current().executeScript("PF('wDlgSubagen').hide();");

	}

	public void onEdit(RowEditEvent event) {
		getServObj = ((Subagentes) event.getObject());
		srvSubagentes.actualizaSubagentes(getServObj);
		FacesMessage msg = new FacesMessage("Registro Editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void encerarDatos() {

	}

	public void EliminaSubagente() {

		Subagentes subaux1 = new Subagentes();
		try {
			selectedSuagente.getCd_subagente();
			subaux1 = srvSubagentes.consultaSubagente(selectedSuagente.getCd_subagente());

		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro para eliminar"));
			return;
		}
		subaux1.setEstado_subagente("D");

		srvSubagentes.actualizaSubagentes(subaux1);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstSubagentes = new ArrayList<Subagentes>();
		lstSubagentes = srvSubagentes.recuperaSubagente();

	}

	public void salirSubagente() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./indexGestion.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Subagentes> getLstSubagentes() {
		return lstSubagentes;
	}

	public void setLstSubagentes(List<Subagentes> lstSubagentes) {
		this.lstSubagentes = lstSubagentes;
	}

	public List<Subagentes> getSelectedLstSubagentes() {
		return selectedLstSubagentes;
	}

	public void setSelectedLstSubagentes(List<Subagentes> selectedLstSubagentes) {
		this.selectedLstSubagentes = selectedLstSubagentes;
	}

	public String getTipo_persona_subagente() {
		return tipo_persona_subagente;
	}

	public void setTipo_persona_subagente(String tipo_persona_subagente) {
		this.tipo_persona_subagente = tipo_persona_subagente;
	}

	public String getTipo_identificacion_subagente() {
		return tipo_identificacion_subagente;
	}

	public void setTipo_identificacion_subagente(String tipo_identificacion_subagente) {
		this.tipo_identificacion_subagente = tipo_identificacion_subagente;
	}

	public Integer getIdentificacion_subagente() {
		return identificacion_subagente;
	}

	public void setIdentificacion_subagente(Integer identificacion_subagente) {
		this.identificacion_subagente = identificacion_subagente;
	}

	public String getRazonsocial_subagente() {
		return razonsocial_subagente;
	}

	public void setRazonsocial_subagente(String razonsocial_subagente) {
		this.razonsocial_subagente = razonsocial_subagente;
	}

	public String getPrimer_nombre_subagente() {
		return primer_nombre_subagente;
	}

	public void setPrimer_nombre_subagente(String primer_nombre_subagente) {
		this.primer_nombre_subagente = primer_nombre_subagente;
	}

	public String getPrimer_apellido_subagente() {
		return primer_apellido_subagente;
	}

	public void setPrimer_apellido_subagente(String primer_apellido_subagente) {
		this.primer_apellido_subagente = primer_apellido_subagente;
	}

	public Date getFecha_nacimiento_subagente() {
		return fecha_nacimiento_subagente;
	}

	public void setFecha_nacimiento_subagente(Date fecha_nacimiento_subagente) {
		this.fecha_nacimiento_subagente = fecha_nacimiento_subagente;
	}

	public String getEstado_subagente() {
		return estado_subagente;
	}

	public void setEstado_subagente(String estado_subagente) {
		this.estado_subagente = estado_subagente;
	}

	public String getFecha_creacion_subagente() {
		return fecha_creacion_subagente;
	}

	public void setFecha_creacion_subagente(String fecha_creacion_subagente) {
		this.fecha_creacion_subagente = fecha_creacion_subagente;
	}

	public String getSegundo_nombre_subagente() {
		return segundo_nombre_subagente;
	}

	public void setSegundo_nombre_subagente(String segundo_nombre_subagente) {
		this.segundo_nombre_subagente = segundo_nombre_subagente;
	}

	public String getSegundo_apellido_subagente() {
		return segundo_apellido_subagente;
	}

	public void setSegundo_apellido_subagente(String segundo_apellido_subagente) {
		this.segundo_apellido_subagente = segundo_apellido_subagente;
	}

	public Subagentes getDatosSuagente() {
		return datosSuagente;
	}

	public void setDatosSuagente(Subagentes datosSuagente) {
		this.datosSuagente = datosSuagente;
	}

	public Subagentes getGetServObj() {
		return getServObj;
	}

	public void setGetServObj(Subagentes getServObj) {
		this.getServObj = getServObj;
	}

	public Subagentes getSelectedSuagente() {
		return selectedSuagente;
	}

	public void setSelectedSuagente(Subagentes selectedSuagente) {
		this.selectedSuagente = selectedSuagente;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public List<Ramo> getLstRamo() {
		return lstRamo;
	}

	public void setLstRamo(List<Ramo> lstRamo) {
		this.lstRamo = lstRamo;
	}

	public List<Ramo> getSelectedLstRamo() {
		return selectedLstRamo;
	}

	public void setSelectedLstRamo(List<Ramo> selectedLstRamo) {
		this.selectedLstRamo = selectedLstRamo;
	}

	public List<Aseguradoras> getLstAseguradoras() {
		return lstAseguradoras;
	}

	public void setLstAseguradoras(List<Aseguradoras> lstAseguradoras) {
		this.lstAseguradoras = lstAseguradoras;
	}

	public String getSelectedAseg() {
		return selectedAseg;
	}

	public void setSelectedAseg(String selectedAseg) {
		this.selectedAseg = selectedAseg;
	}

	public List<GrupoContratante> getLstGrupoContra() {
		return lstGrupoContra;
	}

	public void setLstGrupoContra(List<GrupoContratante> lstGrupoContra) {
		this.lstGrupoContra = lstGrupoContra;
	}

	public String getSelectedGrpContr() {
		return selectedGrpContr;
	}

	public void setSelectedGrpContr(String selectedGrpContr) {
		this.selectedGrpContr = selectedGrpContr;
	}

	public List<Plan> getLstPlan() {
		return lstPlan;
	}

	public void setLstPlan(List<Plan> lstPlan) {
		this.lstPlan = lstPlan;
	}

	public String getSelectedPlan() {
		return selectedPlan;
	}

	public void setSelectedPlan(String selectedPlan) {
		this.selectedPlan = selectedPlan;
	}

	public Double getValComi() {
		return valComi;
	}

	public void setValComi(Double valComi) {
		this.valComi = valComi;
	}

	public List<ComisionesSubagentesView> getLstComisionesSubagenteView() {
		return lstComisionesSubagenteView;
	}

	public void setLstComisionesSubagenteView(List<ComisionesSubagentesView> lstComisionesSubagenteView) {
		this.lstComisionesSubagenteView = lstComisionesSubagenteView;
	}

	public ComisionesSubagentesView getSelecterComisionSubaView() {
		return selecterComisionSubaView;
	}

	public void setSelecterComisionSubaView(ComisionesSubagentesView selecterComisionSubaView) {
		this.selecterComisionSubaView = selecterComisionSubaView;
	}

	public String getCanalCiudad() {
		return canalCiudad;
	}

	public void setCanalCiudad(String canalCiudad) {
		this.canalCiudad = canalCiudad;
	}

}
