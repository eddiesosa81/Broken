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
import confia.entidades.transaccionales.Factura;
import confia.entidades.vistas.ConsultaPagoRealizadosView;
import confia.entidades.vistas.liquidacionCanalPolView;
import confia.procedures.ProcedimientosAlmacenadosDB;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.transaccionales.ServicioFactura;
import confia.servicios.vistas.ServicioConsultaPagoRealizadosView;

@ViewScoped
@ManagedBean(name = "ControladorConsolidaFactura")
public class ControladorConsolidaFactura  {
	@EJB
	private ServicioFactura srvFactura;
	
	private String numFact;
	private String tipo; 
	private List<Factura> listFactura;
	
	
	

	public ControladorConsolidaFactura() {
		listFactura = new ArrayList<Factura>();
		tipo = "Pendiente";
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}
	
	public void sumaRegistros() {
		Double valSel = 0.0;

		for (Factura factura : listFactura) {
			valSel = valSel + Double.valueOf(factura.getTot_factura());
		}
		System.out.println("VALOR:"+valSel);

		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Total Facturas:  " + redondear(valSel)));
		return;

	}
	
	public void eliminaIva() {
		Double valSel = 0.0;

		for (Factura factura : listFactura) {
			valSel = factura.getVal_factura();
			factura.setIva(0.00);
			factura.setTot_factura(valSel);
			srvFactura.actualizaFactura(factura);
		}
		
		listFactura = new ArrayList<Factura>();
		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Proceso Exitoso"));
		return;

	}
	
	
	public void consultaFactura() {
		
		if(tipo.equals("Pendiente")) {
			listFactura = srvFactura.recuperaFacturaPorNumeroPend(numFact);
		}else {
			listFactura = srvFactura.recuperaFacturaPorNumeroConso(numFact);
		}
		
	}
	
	public void consolidaFactura() {
		if(tipo.equals("Pendiente")) {
			for (Factura factura : listFactura) {
				factura.setFc_consolidacion(new Date());
				srvFactura.actualizaFactura(factura);
			}
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Consolidación Exitosa"));
			listFactura = new ArrayList<Factura>();
			return;
		}else {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Realice Consulta de Facuras Pendientes de Consolidar"));
			return;
		}
		
	}

	public String getNumFact() {
		return numFact;
	}

	public void setNumFact(String numFact) {
		this.numFact = numFact;
	}

	public ServicioFactura getSrvFactura() {
		return srvFactura;
	}

	public void setSrvFactura(ServicioFactura srvFactura) {
		this.srvFactura = srvFactura;
	}

	public List<Factura> getListFactura() {
		return listFactura;
	}

	public void setListFactura(List<Factura> listFactura) {
		this.listFactura = listFactura;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
