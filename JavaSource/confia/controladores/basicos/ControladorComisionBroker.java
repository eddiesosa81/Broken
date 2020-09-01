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
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.ComisionRamoAseguradora;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Ramo;
import confia.entidades.basicos.RamoAseguradora;
import confia.entidades.vistas.ComisionRamoAseguradoraView;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioComisionRamoAseguradora;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioRamoAseguradora;

//import org.primefaces.event.RowEditEvent;
//import org.primefaces.event.SelectEvent;

import confia.servicios.vistas.ServicioComisionRamoAseg;

@ViewScoped
@ManagedBean

public class ControladorComisionBroker {
 
	@EJB
	private ServicioComisionRamoAseguradora srvComisionRamoAsegu;
	@EJB
	private ServicioComisionRamoAseg srvComisionRamoAsegView;
	@EJB
	private ServicioAseguradoras srvAseguradora;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioRamoAseguradora srvRamoAseg;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;

	private List<ComisionRamoAseguradoraView> lstComisionRamAseg;
	private List<Aseguradoras> lstAseguradora;
	private List<Ramo> lstRamo;
	private List<Ramo> selectedLstRamo;
	private List<ComisionRamoAseguradoraView> selectedLstComisionRamAseg;
	private ComisionRamoAseguradoraView selectedViewComisionRamAsegu;
	private Aseguradoras selectedAseguradora;
	private List<Aseguradoras> selectedLstAseguradora;

	private String nombre_corto_aseguradora;
	private String desc_ramo;
	private String porcentaje_com_ramaseg;
	private Double valComi;
	private Integer cd_ramoaseg;
	private List<GrupoContratante> listaGrupoContratante;
	private String codGrupoEconomico;

	public ControladorComisionBroker() {

		valComi = 0.0;
		lstComisionRamAseg = new ArrayList<ComisionRamoAseguradoraView>();
		lstAseguradora = new ArrayList<Aseguradoras>();
		lstRamo = new ArrayList<Ramo>();
		listaGrupoContratante = new ArrayList<GrupoContratante>();
	}

	@PostConstruct
	public void recuperaDatos() {
		System.out.println("Ingreso a recuperar");
		listaGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		lstComisionRamAseg = srvComisionRamoAsegView.consultaComisionRamoAseguradora();
		lstAseguradora = srvAseguradora.BuscaAseguradoras();
		lstRamo = srvRamo.listaRamos();
		codGrupoEconomico = "0";
	}
	
	

 public void onRowSelectComi(SelectEvent event) {
	 lstComisionRamAseg = srvComisionRamoAsegView.consultaComisionRamoAseguradora();

	}

	public void Aseguradora() {
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgComision').show();");
		PrimeFaces.current().executeScript("PF('wDlgComision').show();");

	}

	public void guardaValComi() {
		Integer codAse, codRamAseg = null;
		codAse = selectedAseguradora.getCd_aseguradora();
		System.out.println("aseguradora" + codAse);
		if (valComi == null) {
			valComi = 0.0;
		}
		RamoAseguradora ramAsegAux = new RamoAseguradora();
		for (Ramo ramAux : selectedLstRamo) {
			// consulto si existe en la tabla ramoAseguradora_tbl
			ramAsegAux = new RamoAseguradora();
			ramAsegAux = srvRamoAseg.consultaRamoAseguradora(String.valueOf(codAse),
					String.valueOf(ramAux.getCd_ramo()));
			if (ramAsegAux == null) {
				// inserta el registro
				ramAsegAux = new RamoAseguradora();
				ramAsegAux.setCd_aseguradora(codAse);
				ramAsegAux.setCd_ramo(ramAux.getCd_ramo());
				ramAsegAux.setEstado_asegcob("A");
				srvRamoAseg.insertarRamoAseguradora(ramAsegAux);
				codRamAseg = srvRamoAseg.codigoMaxRamoAseguradora();
				System.out.println("ramAsegAux:" +ramAsegAux);
			} else {
				codRamAseg = ramAsegAux.getCd_ramoaseg();
			}
			
			// consulto si exite ingresada la comision
			System.out.println("codRamAseg:" + codRamAseg);
			ComisionRamoAseguradora comiAux = new ComisionRamoAseguradora();
			comiAux = srvComisionRamoAsegu.ComisionRamoAseguradora(codRamAseg,Integer.valueOf(codGrupoEconomico));
			if (comiAux == null) {
				System.out.println("codRamAseg:" + codRamAseg);
				ComisionRamoAseguradora comaux = new ComisionRamoAseguradora();
				comaux.setCd_ramoaseg(codRamAseg);
				comaux.setEstado_com_ramaseg("A");
				comaux.setPorcentaje_com_ramaseg(valComi);
				try {
					if(codGrupoEconomico.isEmpty() || codGrupoEconomico == null){
						codGrupoEconomico = "0";
					}
				} catch (Exception e) {
					codGrupoEconomico = "0";
				}
				if(!codGrupoEconomico.equals("0")){
					comaux.setCd_grupo_contratante(codGrupoEconomico);
					GrupoContratante grp = new GrupoContratante();
					grp = srvGrupoContratante.buscaGruposContratantes(Integer.valueOf(codGrupoEconomico));
					comaux.setNombre_corto_grupo_contratante(grp.getNombre_corto_grupo_contratante());
				}else{
					comaux.setNombre_corto_grupo_contratante("TODOS");
					comaux.setCd_grupo_contratante("0");
				}
				srvComisionRamoAsegu.insertarComisionRamoAseguradora(comaux);
				System.out.println("comaux:"+comaux);
				
			}
			 lstComisionRamAseg = new ArrayList<ComisionRamoAseguradoraView>();
			 lstComisionRamAseg = srvComisionRamoAsegView.consultaComisionRamoAseguradora();
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
//			 RequestContext contextDlg = RequestContext.getCurrentInstance();
//			 contextDlg.execute("PF('wDlgComision').hide();");
			 PrimeFaces.current().executeScript("PF('wDlgComision').hide();");
		}
	}

//	public void LimpiarComi() {
//	    
//		selectedLstAseguradora.clear();
//		//selectedLstRamo.clear();
//		valComi = null;
//	}
	
	public void EliminaComision() {
		Integer cdgrp;
		ComisionRamoAseguradora comaux1 = new ComisionRamoAseguradora();
		try {
			selectedViewComisionRamAsegu.getCd_ramoaseg();
			try {
				cdgrp = Integer.valueOf(selectedViewComisionRamAsegu.getCd_grupo_contratante());
			} catch (Exception e) {
				cdgrp = 0;
			}
			comaux1 = srvComisionRamoAsegu.ComisionRamoAseguradora(Integer.valueOf(selectedViewComisionRamAsegu.getCd_ramoaseg()),cdgrp);
		} catch (Exception e) {
			System.out.println("ERROR:"+e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Registro para eliminar"));
			return;
		}
		srvComisionRamoAsegu.eliminaComisionRamoAseguradora(comaux1);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstComisionRamAseg = new ArrayList<ComisionRamoAseguradoraView>();
		lstComisionRamAseg = srvComisionRamoAsegView.consultaComisionRamoAseguradora();
	}

	public void guardaComi() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./indexGestion.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}

	public List<ComisionRamoAseguradoraView> getLstComisionRamAseg() {
		return lstComisionRamAseg;
	}

	public void setLstComisionRamAseg(List<ComisionRamoAseguradoraView> lstComisionRamAseg) {
		this.lstComisionRamAseg = lstComisionRamAseg;
	}

	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}

	public String getDesc_ramo() {
		return desc_ramo;
	}

	public void setDesc_ramo(String desc_ramo) {
		this.desc_ramo = desc_ramo;
	}

	public String getPorcentaje_com_ramaseg() {
		return porcentaje_com_ramaseg;
	}

	public void setPorcentaje_com_ramaseg(String porcentaje_com_ramaseg) {
		this.porcentaje_com_ramaseg = porcentaje_com_ramaseg;
	}

	public List<Aseguradoras> getLstAseguradora() {
		return lstAseguradora;
	}

	public void setLstAseguradora(List<Aseguradoras> lstAseguradora) {
		this.lstAseguradora = lstAseguradora;
	}

	public List<Ramo> getLstRamo() {
		return lstRamo;
	}

	public void setLstRamo(List<Ramo> lstRamo) {
		this.lstRamo = lstRamo;
	}

	public Double getValComi() {
		return valComi;
	}

	public void setValComi(Double valComi) {
		this.valComi = valComi;
	}

	public List<Ramo> getSelectedLstRamo() {
		return selectedLstRamo;
	}

	public void setSelectedLstRamo(List<Ramo> selectedLstRamo) {
		this.selectedLstRamo = selectedLstRamo;
	}

	public List<ComisionRamoAseguradoraView> getSelectedLstComisionRamAseg() {
		return selectedLstComisionRamAseg;
	}

	public void setSelectedLstComisionRamAseg(List<ComisionRamoAseguradoraView> selectedLstComisionRamAseg) {
		this.selectedLstComisionRamAseg = selectedLstComisionRamAseg;
	}

	public Integer getCd_ramoaseg() {
		return cd_ramoaseg;
	}

	public void setCd_ramoaseg(Integer cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}

	public Aseguradoras getSelectedAseguradora() {
		return selectedAseguradora;
	}

	public void setSelectedAseguradora(Aseguradoras selectedAseguradora) {
		this.selectedAseguradora = selectedAseguradora;
	}

	public ComisionRamoAseguradoraView getSelectedViewComisionRamAsegu() {
		return selectedViewComisionRamAsegu;
	}

	public void setSelectedViewComisionRamAsegu(ComisionRamoAseguradoraView selectedViewComisionRamAsegu) {
		this.selectedViewComisionRamAsegu = selectedViewComisionRamAsegu;
	}

	public List<Aseguradoras> getSelectedLstAseguradora() {
		return selectedLstAseguradora;
	}

	public void setSelectedLstAseguradora(List<Aseguradoras> selectedLstAseguradora) {
		this.selectedLstAseguradora = selectedLstAseguradora;
	}

	public List<GrupoContratante> getListaGrupoContratante() {
		return listaGrupoContratante;
	}

	public void setListaGrupoContratante(List<GrupoContratante> listaGrupoContratante) {
		this.listaGrupoContratante = listaGrupoContratante;
	}

	public String getCodGrupoEconomico() {
		return codGrupoEconomico;
	}

	public void setCodGrupoEconomico(String codGrupoEconomico) {
		this.codGrupoEconomico = codGrupoEconomico;
	}
	
}
