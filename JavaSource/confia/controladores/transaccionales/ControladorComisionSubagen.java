/**
 * 
 */
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

import org.jboss.resteasy.plugins.interceptors.encoding.GZIPEncodingInterceptor.EndableGZIPOutputStream;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.ComisionSubagentePol;
import confia.entidades.basicos.Subagentes;
import confia.entidades.transaccionales.DetallePagoCanal;
import confia.entidades.vistas.ComiSubagenPolView;
import confia.entidades.vistas.liquidacionCanalPolView;
import confia.reportes.AbstractReportBean;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioComisionSubagenPol;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.transaccionales.ServicioDetallePagoCanal;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.vistas.ServicioComiSubagenPolView;
import confia.servicios.vistas.ServicioLiquidacionCanalPolView;

@ManagedBean(name = "ControladorComisionSubagen")
@ViewScoped
public class ControladorComisionSubagen extends AbstractReportBean {
	@EJB
	private ServicioSubagentes srvSubagentes;
	@EJB
	private ServicioComiSubagenPolView svrComisionSubagenPolView;
	@EJB
	private ServicioComisionSubagenPol srvComPolSuba;
	@EJB
	private ServicioRamoCotizacion srvRamCot;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioDetallePagoCanal srvDetallePagoCanal;
	@EJB
	private ServicioLiquidacionCanalPolView srvLiquidacionesCanal;

	private List<ComiSubagenPolView> listComisionSubagenPol;
	private List<ComiSubagenPolView> selectedListComiaionSubagenPol;
	private List<ComiSubagenPolView> listComisionSubagenPolPArcial;
	private List<ComiSubagenPolView> filteredComiaionSubagenPol;
	private ComiSubagenPolView selectedComisionSubagenPol;
	private List<Subagentes> listSubagentes;
	public List<ComiSubagenPolView> selectedListComiaionSubagenPolMod;

	private Integer flg_pago;
	private Integer saldo_com_sub;
	private String codSubagnete;
	private String codNuevoSubagnete;
	private String numFactura;
	private String numFacturaAseg, numPoliza;
	private Date fcFacturaCom;
	private String numFacturaPrint;
	private Boolean btnPrint;
	private List<Aseguradoras> listadoAseguradoras;
	private String codigoAseguradora;
	
	// Imprime
	
	private String ls_num_liq;
	private List<liquidacionCanalPolView> lstDetallePagoCanal;
	private liquidacionCanalPolView selectedDetallePAgoCanal;

	public ControladorComisionSubagen() {
		listSubagentes = new ArrayList<Subagentes>();
		listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
		fcFacturaCom = new Date();
		btnPrint = true;
		listadoAseguradoras = new ArrayList<Aseguradoras>();
		ls_num_liq = "";

	}

	// --------------- PROGRAMACION IMPRESIONES ------------------//

	private  String COMPILE_FILE_NAME ;

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}

	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numeroFact", numFacturaPrint);
		return parametros;

	}

	public String execute() {
		COMPILE_FILE_NAME = "FacturaCanal";
		System.out.println("---------------------------- NUMERO factura:" + numFacturaPrint);
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		btnPrint = true;
		return null;
	}
	public String executeLiquidacion() {
		COMPILE_FILE_NAME = "FacturaCanalLiquidado";
		try {
			if(selectedDetallePAgoCanal != null)
				numFacturaPrint = selectedDetallePAgoCanal.getNum_factura_canal();
		} catch (Exception e) {
			return null;
		}
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

	@PostConstruct
	public void datosIniciales() {
		listSubagentes = srvSubagentes.recuperaSubagente1();
		numFactura = null;
		listadoAseguradoras = srvAseguradoras.listaAseguradoras();
		listComisionSubagenPolPArcial = new ArrayList<ComiSubagenPolView>();
	}

	public void cargarComiSubagenPol() {
		try {
			if (numFacturaAseg.isEmpty() || numFacturaAseg == null) {
				numFacturaAseg = "%";
			}

		} catch (Exception e) {
			numFacturaAseg = "%";
		}
		System.out.println("factura:" + numFacturaAseg);
		try {
			if (numPoliza.isEmpty() || numPoliza == null) {
				numPoliza = "%";
			}
		} catch (Exception e) {
			numPoliza = "%";
		}
		System.out.println("numPoliza:" + numPoliza);

		listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
		System.out.println("SUBAGENTE:" + codSubagnete);
		if (!numFacturaAseg.equals("%") && !numPoliza.equals("%")) {
			listComisionSubagenPol = svrComisionSubagenPolView.consultaComiSubagenPolView(codSubagnete, numPoliza,
					numFacturaAseg, codigoAseguradora);
		}
		if (!numFacturaAseg.equals("%") && numPoliza.equals("%")) {
			listComisionSubagenPol = svrComisionSubagenPolView.consultaComiSubagenPolView(codSubagnete, numPoliza,
					numFacturaAseg, codigoAseguradora);
		}
		if (numFacturaAseg.equals("%") && !numPoliza.equals("%")) {
			listComisionSubagenPol = svrComisionSubagenPolView.consultaComiSubagenPolizaView(codSubagnete, numPoliza,
					codigoAseguradora);
		}
		if (numFacturaAseg.equals("%") && numPoliza.equals("%")) {
			listComisionSubagenPol = svrComisionSubagenPolView.consultaComiSubagenPolView(codSubagnete, numPoliza,
					numFacturaAseg, codigoAseguradora);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		ComiSubagenPolView comSuba = new ComiSubagenPolView();
		comSuba = (ComiSubagenPolView) event.getObject();
		ComisionSubagentePol comPol = new ComisionSubagentePol();
		
		comPol = srvComPolSuba.consultaSubagentePol(Integer.valueOf(comSuba.getCd_comisuba_pol()));
		System.out.println("comPol.getVal_com_suba():"+comPol.getVal_com_suba());
		System.out.println("comPol.getSaldo_com_suba():"+comPol.getSaldo_com_suba());
		if(comPol.getVal_com_suba().equals(comPol.getSaldo_com_suba())) {
			comPol.setVal_com_suba(Double.valueOf(comSuba.getVal_com_suba()));
			comPol.setPct_com_suba(Double.valueOf(comSuba.getPct_com_suba()));
			comPol.setSaldo_com_suba(Double.valueOf(comSuba.getVal_com_suba()));
			srvComPolSuba.actualizaComisionSubagentePol(comPol);
			listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
			cargarComiSubagenPol();	
		}else {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "No se puede modificar el valor." ));
			
			listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
		}
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void sumaRegistros() {
		Double valSel = 0.0;

		for (ComiSubagenPolView detPagSel : selectedListComiaionSubagenPol) {
			valSel = valSel + Double.valueOf(detPagSel.getVal_com_suba());
		}

		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null,
				new FacesMessage("Advertencia", "Total Valor Comisión Seleccionados:" + redondear(valSel)));

	}

	public void cambiaSubagente() {
		System.out.println("TAMA�O:" + selectedListComiaionSubagenPol.size());
		try {
			if (selectedListComiaionSubagenPol.size() == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Advertencia", "Seleccione registros para Generar el pago"));
				return;

			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione registros para Generar el pago"));
			return;
		}
		selectedListComiaionSubagenPolMod = new ArrayList<ComiSubagenPolView>();
		selectedListComiaionSubagenPolMod = selectedListComiaionSubagenPol;
		PrimeFaces.current().executeScript("PF('cambiaSuba').show()");
	}

	public void guardarComiSubagenPol() {
		numFacturaPrint = numFactura;
		System.out.println("Numero Factura:"+numFacturaPrint);
		if (listComisionSubagenPolPArcial.size() > 0) {
			System.out.println("Ingreso Pago Parcial");
			try {
				if (numFactura.isEmpty() || numFactura == null) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el número de Factura "));
					return;
				}
			} catch (Exception e) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el número de Factura "));
				return;
			}
			System.out.println("TAMA�O:" + selectedListComiaionSubagenPol.size());
			System.out.println("Factura No.:" + numFactura);
			Double valPagoDouble,saldoAnt;
			for (ComiSubagenPolView comCanObj : listComisionSubagenPolPArcial) {
				valPagoDouble = Double.valueOf(comCanObj.getSaldo_COM_SUBA());
				
				ComisionSubagentePol comSubPolAux = new ComisionSubagentePol();
				comSubPolAux = srvComPolSuba.consultaSubagentePol(Integer.valueOf(comCanObj.getCd_comisuba_pol()));
				saldoAnt = comSubPolAux.getSaldo_com_suba() - valPagoDouble;
				if(saldoAnt <= 0) {
					comSubPolAux.setFlg_pago(1);
					comSubPolAux.setSaldo_com_suba(0.0);
					comSubPolAux.setNum_Factura_suba(numFactura);
					comSubPolAux.setFc_pago_suba(fcFacturaCom);
				}else {
					comSubPolAux.setFlg_pago(0);
					comSubPolAux.setSaldo_com_suba(saldoAnt);
				}
				comSubPolAux.setNum_Factura_suba(numFactura);
				comSubPolAux.setFc_pago_suba(fcFacturaCom);
				srvComPolSuba.actualizaComisionSubagentePol(comSubPolAux);
				//inserto detalle pago canal
				DetallePagoCanal detallePagoCanal = new DetallePagoCanal();
				detallePagoCanal.setCd_comisuba_pol(comSubPolAux.getCd_comisuba_pol());
				detallePagoCanal.setFecha_Factura(fcFacturaCom);
				detallePagoCanal.setNum_Factura_suba(numFactura);
				detallePagoCanal.setValor_liquidado(valPagoDouble);
				srvDetallePagoCanal.insertarDetallePagoCanal(detallePagoCanal);
			}
			
			
			numFacturaPrint = numFactura;
			numFactura = null;
			listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
			listComisionSubagenPolPArcial = new ArrayList<ComiSubagenPolView>();
			PrimeFaces.current().executeScript("PF('numFactSuba').hide()");
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Proceso exitoso"));
			listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
			btnPrint = false;
			salir();
		} else {
			try {
				if (selectedListComiaionSubagenPol.size() == 0) {
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
			try {
				if (numFactura.isEmpty() || numFactura == null) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el número de Factura "));
					return;
				}
			} catch (Exception e) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el número de Factura "));
				return;
			}
			System.out.println("TAMA�O:" + selectedListComiaionSubagenPol.size());
			System.out.println("Factura No.:" + numFactura);
			for (ComiSubagenPolView comSUbPolLst : selectedListComiaionSubagenPol) {
				ComisionSubagentePol comSubPolAux = new ComisionSubagentePol();
				comSubPolAux = srvComPolSuba.consultaSubagentePol(Integer.valueOf(comSUbPolLst.getCd_comisuba_pol()));
				comSubPolAux.setFlg_pago(1);
				comSubPolAux.setSaldo_com_suba(0.0);
				comSubPolAux.setNum_Factura_suba(numFactura);
				comSubPolAux.setFc_pago_suba(fcFacturaCom);
				srvComPolSuba.actualizaComisionSubagentePol(comSubPolAux);
				
				//inserto detalle pago canal
				DetallePagoCanal detallePagoCanal = new DetallePagoCanal();
				detallePagoCanal.setCd_comisuba_pol(comSubPolAux.getCd_comisuba_pol());
				detallePagoCanal.setFecha_Factura(fcFacturaCom);
				detallePagoCanal.setNum_Factura_suba(numFactura);
				detallePagoCanal.setValor_liquidado(comSubPolAux.getVal_com_suba());
				srvDetallePagoCanal.insertarDetallePagoCanal(detallePagoCanal);
			}
			numFacturaPrint = numFactura;
			numFactura = null;
			listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
			PrimeFaces.current().executeScript("PF('numFactSuba').hide()");
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Proceso exitoso"));
			listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
			btnPrint = false;
			salir();
		}
	}

	public void salir() {
	}

	public void modificaSubagenPol() {
		System.out.println("Tama�o_selecicion:" + selectedListComiaionSubagenPolMod.size());
		for (ComiSubagenPolView auxCom : selectedListComiaionSubagenPolMod) {
			ComisionSubagentePol comSuba = new ComisionSubagentePol();
			comSuba = srvComPolSuba.consultaSubagentePol(Integer.valueOf(auxCom.getCd_comisuba_pol()));
			srvRamCot.actualizaCanalRamCot(comSuba.getCd_compania(), comSuba.getCd_ramo_cotizacion(),
					codNuevoSubagnete);
		}
		listComisionSubagenPol = new ArrayList<ComiSubagenPolView>();
		PrimeFaces.current().executeScript("PF('cambiaSuba').hide()");

	}

	public void visualizaFactSelec() {
		PrimeFaces.current().executeScript("PF('wFactSelected').show();");
	}

	public void onRowEditValCom(RowEditEvent event) {
		System.out.println("getCd_comisuba_pol:" + ((ComiSubagenPolView) event.getObject()).getCd_comisuba_pol());
	}

	public void generaComision() {
		System.out.println("TAMAñO:" + selectedListComiaionSubagenPol.size());
		PrimeFaces.current().executeScript("PF('numFactSuba').show()");
	}

	public void generaPagoPArcial() {
		System.out.println("TAMAñO:" + selectedListComiaionSubagenPol.size());

		if (selectedListComiaionSubagenPol.size() > 0) {
			listComisionSubagenPolPArcial = new ArrayList<ComiSubagenPolView>();
			listComisionSubagenPolPArcial = selectedListComiaionSubagenPol;
			PrimeFaces.current().executeScript("PF('wComSubaSelected').show()");
		} else {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione Comisiones para liquidar"));
		}
	}
	
	public void consultaLiquidacionCanal() {
		try {
			if (ls_num_liq.isEmpty() || ls_num_liq == null) {
				ls_num_liq = "%";
			}
		} catch (Exception e) {
			ls_num_liq = "%";
		}
		
		lstDetallePagoCanal = new ArrayList<liquidacionCanalPolView>();
		lstDetallePagoCanal = srvLiquidacionesCanal.consultaLiquidacionCanalView(ls_num_liq);
	}

	public void eliminaFactConfia(ComiSubagenPolView aux) {
		listComisionSubagenPolPArcial.remove(aux);
	}

	public List<Subagentes> getListSubagentes() {
		return listSubagentes;
	}

	public void setListSubagentes(List<Subagentes> listSubagentes) {
		this.listSubagentes = listSubagentes;
	}

	public List<ComiSubagenPolView> getListComisionSubagenPol() {
		return listComisionSubagenPol;
	}

	public void setListComisionSubagenPol(List<ComiSubagenPolView> listComisionSubagenPol) {
		this.listComisionSubagenPol = listComisionSubagenPol;
	}

	public List<ComiSubagenPolView> getSelectedListComiaionSubagenPol() {
		return selectedListComiaionSubagenPol;
	}

	public void setSelectedListComiaionSubagenPol(List<ComiSubagenPolView> selectedListComiaionSubagenPol) {
		this.selectedListComiaionSubagenPol = selectedListComiaionSubagenPol;
	}

	public ComiSubagenPolView getSelectedComisionSubagenPol() {
		return selectedComisionSubagenPol;
	}

	public void setSelectedComisionSubagenPol(ComiSubagenPolView selectedComisionSubagenPol) {
		this.selectedComisionSubagenPol = selectedComisionSubagenPol;
	}

	public Integer getFlg_pago() {
		return flg_pago;
	}

	public void setFlg_pago(Integer flg_pago) {
		this.flg_pago = flg_pago;
	}

	public Integer getSaldo_com_sub() {
		return saldo_com_sub;
	}

	public void setSaldo_com_sub(Integer saldo_com_sub) {
		this.saldo_com_sub = saldo_com_sub;
	}

	public String getCodSubagnete() {
		return codSubagnete;
	}

	public void setCodSubagnete(String codSubagnete) {
		this.codSubagnete = codSubagnete;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public List<ComiSubagenPolView> getFilteredComiaionSubagenPol() {
		return filteredComiaionSubagenPol;
	}

	public void setFilteredComiaionSubagenPol(List<ComiSubagenPolView> filteredComiaionSubagenPol) {
		this.filteredComiaionSubagenPol = filteredComiaionSubagenPol;
	}

	public String getNumFacturaAseg() {
		return numFacturaAseg;
	}

	public void setNumFacturaAseg(String numFacturaAseg) {
		this.numFacturaAseg = numFacturaAseg;
	}

	public String getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Date getFcFacturaCom() {
		return fcFacturaCom;
	}

	public void setFcFacturaCom(Date fcFacturaCom) {
		this.fcFacturaCom = fcFacturaCom;
	}

	public String getCodNuevoSubagnete() {
		return codNuevoSubagnete;
	}

	public void setCodNuevoSubagnete(String codNuevoSubagnete) {
		this.codNuevoSubagnete = codNuevoSubagnete;
	}

	public Boolean getBtnPrint() {
		return btnPrint;
	}

	public void setBtnPrint(Boolean btnPrint) {
		this.btnPrint = btnPrint;
	}

	public List<Aseguradoras> getListadoAseguradoras() {
		return listadoAseguradoras;
	}

	public void setListadoAseguradoras(List<Aseguradoras> listadoAseguradoras) {
		this.listadoAseguradoras = listadoAseguradoras;
	}

	public String getCodigoAseguradora() {
		return codigoAseguradora;
	}

	public void setCodigoAseguradora(String codigoAseguradora) {
		this.codigoAseguradora = codigoAseguradora;
	}

	public List<ComiSubagenPolView> getListComisionSubagenPolPArcial() {
		return listComisionSubagenPolPArcial;
	}

	public void setListComisionSubagenPolPArcial(List<ComiSubagenPolView> listComisionSubagenPolPArcial) {
		this.listComisionSubagenPolPArcial = listComisionSubagenPolPArcial;
	}

	public String getLs_num_liq() {
		return ls_num_liq;
	}

	public void setLs_num_liq(String ls_num_liq) {
		this.ls_num_liq = ls_num_liq;
	}

	public List<liquidacionCanalPolView> getLstDetallePagoCanal() {
		return lstDetallePagoCanal;
	}

	public void setLstDetallePagoCanal(List<liquidacionCanalPolView> lstDetallePagoCanal) {
		this.lstDetallePagoCanal = lstDetallePagoCanal;
	}

	public liquidacionCanalPolView getSelectedDetallePAgoCanal() {
		return selectedDetallePAgoCanal;
	}

	public void setSelectedDetallePAgoCanal(liquidacionCanalPolView selectedDetallePAgoCanal) {
		this.selectedDetallePAgoCanal = selectedDetallePAgoCanal;
	}
	
	

}
