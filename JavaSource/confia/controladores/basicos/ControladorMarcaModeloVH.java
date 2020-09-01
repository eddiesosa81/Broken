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

import confia.entidades.basicos.Marca;
import confia.entidades.basicos.Modelo;
import confia.servicios.basicos.ServicioMarca;
import confia.servicios.basicos.ServicioModelo;

@ManagedBean(name = "ControladorMarcaModeloVH")
@ViewScoped
public class ControladorMarcaModeloVH {
	@EJB
	private ServicioMarca srvMarca;
	@EJB
	private ServicioModelo srvModelo;

	private List<Marca> lstMarca;
	private List<Marca> lstFilteredMarca;
	private List<Modelo> lstModelo;
	private Marca selectedMarca;
	private Marca selectedModelo;
	private String descModelo;

	public ControladorMarcaModeloVH() {
		lstMarca = new ArrayList<Marca>();
		lstModelo = new ArrayList<Modelo>();
	}

	@PostConstruct
	public void inicio() {
		lstMarca = srvMarca.listaMarca();
	}

	public void onRowSelect(SelectEvent event) {
		Integer codMarca;
		codMarca = ((Marca) event.getObject()).getCd_marca();
		System.out.println("INGRESOOO: codMarca:"+codMarca);
		lstModelo = new ArrayList<Modelo>();
		lstModelo = srvModelo.modelosConsMarca(codMarca);
	}

	public void salir() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./indexGestion.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onRowEditModelo(RowEditEvent event) {
		Modelo auxMod = new Modelo();
		auxMod = (Modelo) event.getObject();
		auxMod.setEstado_modelo("A");
		srvModelo.actualizaModelo(auxMod);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));
		lstModelo = new ArrayList<Modelo>();
	}
	
	public void guardaModelo() {
		Modelo auxMod = new Modelo();
		auxMod.setCd_marca(selectedMarca.getCd_marca());
		auxMod.setDesc_modelo(descModelo);
		auxMod.setEstado_modelo("A");
		srvModelo.insertarModelo(auxMod);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Inserción Exitosa"));
		lstModelo = new ArrayList<Modelo>();
		PrimeFaces.current().executeScript("PF('wDlgModelo').hide();");
	}
	
	public void nuevoModelo() {
		PrimeFaces.current().executeScript("PF('wDlgModelo').show();");
	}
	
	public void eliminaModelo() {
		Modelo auxMod = new Modelo();
		auxMod.setEstado_modelo("E");
		srvModelo.actualizaModelo(auxMod);
		lstModelo = new ArrayList<Modelo>();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));
	}
	
	public List<Marca> getLstMarca() {
		return lstMarca;
	}

	public void setLstMarca(List<Marca> lstMarca) {
		this.lstMarca = lstMarca;
	}

	public List<Modelo> getLstModelo() {
		return lstModelo;
	}

	public void setLstModelo(List<Modelo> lstModelo) {
		this.lstModelo = lstModelo;
	}

	public Marca getSelectedMarca() {
		return selectedMarca;
	}

	public void setSelectedMarca(Marca selectedMarca) {
		this.selectedMarca = selectedMarca;
	}

	public List<Marca> getLstFilteredMarca() {
		return lstFilteredMarca;
	}

	public void setLstFilteredMarca(List<Marca> lstFilteredMarca) {
		this.lstFilteredMarca = lstFilteredMarca;
	}

	public Marca getSelectedModelo() {
		return selectedModelo;
	}

	public void setSelectedModelo(Marca selectedModelo) {
		this.selectedModelo = selectedModelo;
	}

	public String getDescModelo() {
		return descModelo;
	}

	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}

}
