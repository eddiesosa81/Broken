package confia.controladores.vistas;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Ejecutivos;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Ramo;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.Subagentes;
import confia.entidades.vistas.ProduccionEmisionesPendientesView;
import confia.entidades.vistas.ProduccionEmitidaMensualizadoView;
import confia.entidades.vistas.ProduccionEmitidaView;
import confia.entidades.vistas.ProduccionGeneralView;
import confia.entidades.vistas.ProduccionPagadaView;
import confia.entidades.vistas.ProduccionPendientePagoView;
import confia.entidades.vistas.ReporteCanalesPendienteView;
import confia.entidades.vistas.ReporteCanalesView;
import confia.entidades.vistas.ReporteComisionPendteView;
import confia.entidades.vistas.ReporteComisionesLiquidadasView;
import confia.entidades.vistas.ReporteCuotasIniImpaga;
import confia.entidades.vistas.ReporteRepositorio;
import confia.entidades.vistas.ReporteRepositorioSiniestro;
import confia.entidades.vistas.ReporteSiniestralidadView;
import confia.entidades.vistas.ReporteVencimientoMenView;
import confia.entidades.vistas.ReporteVencimientoView;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioEjecutivos;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.vistas.ServicioProduccionEmitidaMensualizadaView;
import confia.servicios.vistas.ServicioProduccionEmitidaView;
import confia.servicios.vistas.ServicioProduccionGeneralView;
import confia.servicios.vistas.ServicioProduccionPagadaView;
import confia.servicios.vistas.ServicioProduccionPendienteEmisionView;
import confia.servicios.vistas.ServicioProduccionPendientePagoView;
import confia.servicios.vistas.ServicioReporteCanalesPendView;
import confia.servicios.vistas.ServicioReporteCanalesView;
import confia.servicios.vistas.ServicioReporteComisionPendteView;
import confia.servicios.vistas.ServicioReporteComisionesLiquidadasView;
import confia.servicios.vistas.ServicioReporteCuotasIniImpaga;
import confia.servicios.vistas.ServicioReporteSiniestralidadView;
import confia.servicios.vistas.ServicioReporteSiniestroView;
import confia.servicios.vistas.ServicioReporteVencimientoMenView;
import confia.servicios.vistas.ServicioReporteVencimientoView;

@ManagedBean(name = "ControladorReporteria")
@ViewScoped
public class ControladorReporteria {
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioClientes srvCliente;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioSubagentes srvSubagente;
	@EJB
	private ServicioEjecutivos srvEjecutivos;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioProduccionEmitidaView srvProduccionEmitida;
	@EJB
	private ServicioProduccionPagadaView srvProdPagada;
	@EJB
	private ServicioProduccionPendientePagoView srvProdPrenPago;
	@EJB
	private ServicioReporteSiniestroView srvRepGeneralSiniestro;
	@EJB
	private ServicioReporteSiniestralidadView srvRepSiniestralidad;
	@EJB
	private ServicioProduccionGeneralView srvProduccionGeneral;
	@EJB
	private ServicioProduccionEmitidaMensualizadaView srvProdEmitidaMensual;
	@EJB
	private ServicioReporteCuotasIniImpaga srvCuotaIniImpaga;
	@EJB
	private ServicioReporteComisionPendteView srvReporteComisionPendteView;
	@EJB
	private ServicioReporteComisionesLiquidadasView srvReporteComisionesLiquidadasView;
	@EJB
	private ServicioReporteCanalesView srvReporteCanalesView;
	@EJB
	private ServicioProduccionPendienteEmisionView srvProdePendEmiView;
	@EJB
	private ServicioReporteCanalesPendView srvCanalPendView;
	@EJB
	private ServicioReporteVencimientoView srvVencimientoView;

	@EJB
	private ServicioReporteVencimientoMenView srvVencimientoViewMen;

	public String lscdAseguradora;
	private List<Aseguradoras> listAseguradoras;
	public String lscdCliente;
	private List<Clientes> listClientes;
	public String lscdRamo;
	private List<Ramo> lstRamo;
	public String lscdGrupoContratante;
	public List<GrupoContratante> lstGrupocontratante;
	public String lscdSubagente;
	public List<Subagentes> lstSubagente;
	public String lscdEjecutivo;
	public List<Ejecutivos> lstEjecutivos;
	public String lscdRubro;
	public List<Rubros> lstRubros;
	public SimpleDateFormat formato;
	public String patron = "dd/MM/yyyy";
	public Date fcDesde;
	public Date fcHasta;
	public List<ReporteRepositorio> lstRepositorio;
	public String tipo;
	public Boolean flgRepProd;
	public Boolean flgRepProdPag;
	public Boolean flgRepSiniestro;
	public Boolean flgRepSiniestralidad;
	public Boolean flgRepProdGeneral;;
	public List<ReporteRepositorioSiniestro> lstRepGeneralSiniestro;
	public List<ReporteSiniestralidadView> lstRepSiniestralidad;
	public List<ProduccionGeneralView> lstRepProdGen;
	public Boolean flgRepProdMens;
	public List<ProduccionEmitidaMensualizadoView> lstProdEmiMEnsualizado;
	public Boolean flgRepCuotaIniImpaga;
	public List<ReporteCuotasIniImpaga> lstCuotaIniImpaga;
	public Boolean flgComisionesPendienteConfia;
	public List<ReporteComisionPendteView> lstReporteComisionPendteView;
	public Boolean flgComisionesLiquidadas;
	public List<ReporteComisionesLiquidadasView> lstReporteComisionesLiquidadasView;
	public Boolean flgReporteCanales;
	public List<ReporteCanalesView> lstReporteCanalesView;
	public Boolean flgRepProdPendEmi;
	public List<ProduccionEmisionesPendientesView> lstProdPendEmi;
	public Boolean flgRepVencimiento;
	public List<ReporteVencimientoView> lstRepVencimiento;

	public Boolean flgRepVencimientoMens;
	public List<ReporteVencimientoMenView> lstRepVencimientoMen;

	public Boolean flgReporteCanalesPend;
	public List<ReporteCanalesPendienteView> lstCanalesPend;

	public ControladorReporteria() {
		listAseguradoras = new ArrayList<Aseguradoras>();
		listClientes = new ArrayList<Clientes>();
		lstRamo = new ArrayList<Ramo>();
		lstGrupocontratante = new ArrayList<GrupoContratante>();
		lstSubagente = new ArrayList<Subagentes>();
		lstEjecutivos = new ArrayList<Ejecutivos>();
		lstRubros = new ArrayList<Rubros>();
		formato = new SimpleDateFormat(patron);
		fcDesde = new Date();
		fcHasta = new Date();
		lstRepositorio = new ArrayList<ReporteRepositorio>();
		flgRepProd = false;
		flgRepProdPag = false;
		flgRepSiniestro = false;
		flgRepSiniestralidad = false;
		lstRepGeneralSiniestro = new ArrayList<ReporteRepositorioSiniestro>();
		lstRepSiniestralidad = new ArrayList<ReporteSiniestralidadView>();
		lstRepProdGen = new ArrayList<ProduccionGeneralView>();
		flgRepProdGeneral = false;
		flgRepProdMens = false;
		lstProdEmiMEnsualizado = new ArrayList<ProduccionEmitidaMensualizadoView>();
		lstCuotaIniImpaga = new ArrayList<ReporteCuotasIniImpaga>();
		flgRepCuotaIniImpaga = false;
		lstReporteComisionPendteView = new ArrayList<ReporteComisionPendteView>();
		flgComisionesPendienteConfia = false;
		lstReporteComisionesLiquidadasView = new ArrayList<ReporteComisionesLiquidadasView>();
		flgComisionesLiquidadas = false;
		lstReporteCanalesView = new ArrayList<ReporteCanalesView>();
		flgReporteCanales = false;
		flgRepProdPendEmi = false;
		lstProdPendEmi = new ArrayList<ProduccionEmisionesPendientesView>();
		flgReporteCanalesPend = false;
		lstCanalesPend = new ArrayList<ReporteCanalesPendienteView>();
		flgRepVencimiento = false;
		lstRepVencimiento = new ArrayList<ReporteVencimientoView>();
		flgRepVencimientoMens = false;
		lstRepVencimientoMen = new ArrayList<ReporteVencimientoMenView>();
	}

	@PostConstruct
	public void recuperaFiltros() {
		System.out.println("Ingreso al reporte");
		listAseguradoras = srvAseguradoras.BuscaAseguradoras();
		listClientes = srvCliente.listaClientesCorrespon();
		lstRamo = srvRamo.listaRamos();
		lstGrupocontratante = srvGrupoContratante.listaGruposContratantes();
		lstSubagente = srvSubagente.recuperaSubagente();
		lstEjecutivos = srvEjecutivos.listaEjecutivos();
		lstRubros = srvRubros.listadoRubrosCod("200");
		System.out.println("finalizo ingreso al reporte");
		tipo = "0";
	}

	public void salir() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void consultar() {
		System.out.println("Ingreso a consultar poliza");
		String asFechaDesde, asFechaHasta;
		Integer auxTip;
		asFechaDesde = formato.format(fcDesde);
		asFechaHasta = formato.format(fcHasta);
		ReporteRepositorio auxRepositorio;
		lstRepositorio = new ArrayList<ReporteRepositorio>();
		lstRepGeneralSiniestro = new ArrayList<ReporteRepositorioSiniestro>();
		lstRepSiniestralidad = new ArrayList<ReporteSiniestralidadView>();
		lstRepProdGen = new ArrayList<ProduccionGeneralView>();
		auxTip = Integer.valueOf(tipo);
		System.out.println("TIPO:" + auxTip);
		System.out.println("REPORTE:" + lscdRubro);

		if (lscdRubro.equals("REPORTEPRODUCCIONEMITIDA")) {
			if (auxTip.equals(0)) {
				flgRepProd = true;
				flgRepProdPag = false;
				flgRepProdMens = false;
				flgRepSiniestro = false;
				flgRepSiniestralidad = false;
				flgRepProdGeneral = false;
				flgRepCuotaIniImpaga = false;
				flgComisionesPendienteConfia = false;
				flgComisionesLiquidadas = false;
				flgReporteCanales = false;
				flgRepProdPendEmi = false;
				flgReporteCanalesPend = false;
				flgRepVencimiento = false;
				flgRepVencimientoMens = false;

				List<ProduccionEmitidaView> lstRepProdEmi = new ArrayList<ProduccionEmitidaView>();
				lstRepProdEmi = srvProduccionEmitida.recuperaPRoduccionEmitida(lscdCliente, lscdGrupoContratante,
						lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

				for (ProduccionEmitidaView repAux : lstRepProdEmi) {
					auxRepositorio = new ReporteRepositorio();
					auxRepositorio.setAseguradoraRepo(repAux.getAseguradora());
					auxRepositorio.setCanalRepo(repAux.getCanal());
					auxRepositorio.setCd_aseguradora(repAux.cd_aseguradora);
					auxRepositorio.setCd_cliente(repAux.cd_cliente);
					auxRepositorio.setCd_ejecutivo(repAux.cd_ejecutivo);
					auxRepositorio.setCd_grupo_contratante(repAux.cd_grupo_contratante);
					auxRepositorio.setCd_ramo(repAux.cd_ramo);
					auxRepositorio.setCd_subagente(repAux.cd_subagente);
					auxRepositorio.setClienteRepo(repAux.cliente);
					auxRepositorio.setCodIdRepo(repAux.codId);
					auxRepositorio.setComsionBrokerRepo(repAux.comsionBroker);
					auxRepositorio.setEjecutivoRepo(repAux.ejecutivo);
					auxRepositorio.setFacturaRepo(repAux.factura);
					auxRepositorio.setFc_desde_jul(repAux.fc_desde_jul);
					auxRepositorio.setFc_hasta_jul(repAux.fc_hasta_jul);
					auxRepositorio.setFechaEmisionRepo(repAux.fechaEmision);
					auxRepositorio.setGrupoContratanteRepo(repAux.grupoContratante);
					auxRepositorio.setNumRenovaRepo(repAux.numRenova);
					auxRepositorio.setPolizaRepo(repAux.poliza);
					auxRepositorio.setPorcComBrkRepo(repAux.porcComBrk);
					auxRepositorio.setPrimaNetaRepo(repAux.primaNeta);
					auxRepositorio.setPrimaTotalRepo(repAux.primaTotal);
					auxRepositorio.setRamoRepo(repAux.ramo);
					auxRepositorio.setTipoRepo(repAux.tipo);
					auxRepositorio.setTotalAseguradoRepo(repAux.totalAsegurado);
					auxRepositorio.setVigenciaDesdeRepo(repAux.vigenciaDesde);
					auxRepositorio.setVigenciaHastaRepo(repAux.vigenciaHasta);
					auxRepositorio.setFc_emision_jul(repAux.fc_emision_jul);
					auxRepositorio.setForma_pago(repAux.forma_pago);
					auxRepositorio.setNumero_identificacion(repAux.getNumero_identificacion());
					auxRepositorio.setTelefono_cliente(repAux.getTelefono_cliente());
					auxRepositorio.setCorreo_cliente(repAux.getCorreo_cliente());
					lstRepositorio.add(auxRepositorio);
				}
				FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Fecha de Emisión - ");
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} else {
				// reporte mensualizado
				System.out.println("INGRESO REPORTE MENSUALIZADO");
				flgRepProd = false;
				flgRepProdPag = false;
				flgRepProdMens = true;
				flgRepSiniestro = false;
				flgRepSiniestralidad = false;
				flgRepProdGeneral = false;
				flgRepCuotaIniImpaga = false;
				flgComisionesPendienteConfia = false;
				flgComisionesLiquidadas = false;
				flgReporteCanales = false;
				flgRepProdPendEmi = false;
				flgReporteCanalesPend = false;
				flgRepVencimiento = false;
				flgRepVencimientoMens = false;

				lstProdEmiMEnsualizado = new ArrayList<ProduccionEmitidaMensualizadoView>();
				lstProdEmiMEnsualizado = srvProdEmitidaMensual.recuperaPRoduccionEmitidaMensualizada(lscdCliente,
						lscdGrupoContratante, lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde,
						asFechaHasta, tipo);
				FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Fecha Inicio de Vigencia - ");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

		if (lscdRubro.equals("REPORTEPRODUCCIONPAGADA")) {
			flgRepProd = true;
			flgRepProdPag = true;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			List<ProduccionPagadaView> lstRepProdPag = new ArrayList<ProduccionPagadaView>();
			lstRepProdPag = srvProdPagada.recuperaProduccionPagadaView(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			for (ProduccionPagadaView repAux : lstRepProdPag) {
				auxRepositorio = new ReporteRepositorio();
				auxRepositorio.setAseguradoraRepo(repAux.getAseguradora());
				auxRepositorio.setCanalRepo(repAux.getCanal());
				auxRepositorio.setCd_aseguradora(repAux.cd_aseguradora);
				auxRepositorio.setCd_cliente(repAux.cd_cliente);
				auxRepositorio.setCd_ejecutivo(repAux.cd_ejecutivo);
				auxRepositorio.setCd_grupo_contratante(repAux.cd_grupo_contratante);
				auxRepositorio.setCd_ramo(repAux.cd_ramo);
				auxRepositorio.setCd_subagente(repAux.cd_subagente);
				auxRepositorio.setClienteRepo(repAux.cliente);
				auxRepositorio.setCodIdRepo(repAux.codId);
				auxRepositorio.setComsionBrokerRepo(repAux.comsionBroker);
				auxRepositorio.setEjecutivoRepo(repAux.ejecutivo);
				auxRepositorio.setFacturaRepo(repAux.factura);
				auxRepositorio.setFc_desde_jul(repAux.fc_desde_jul);
				auxRepositorio.setFc_hasta_jul(repAux.fc_hasta_jul);
				auxRepositorio.setFechaEmisionRepo(repAux.fechaEmision);
				auxRepositorio.setGrupoContratanteRepo(repAux.grupoContratante);
				auxRepositorio.setNumRenovaRepo(repAux.numRenova);
				auxRepositorio.setPolizaRepo(repAux.poliza);
				auxRepositorio.setPorcComBrkRepo(repAux.porcComBrk);
				auxRepositorio.setPrimaNetaRepo(repAux.primaNeta);
				auxRepositorio.setPrimaTotalRepo(repAux.primaTotal);
				auxRepositorio.setRamoRepo(repAux.ramo);
				auxRepositorio.setTipoRepo(repAux.tipo);
				auxRepositorio.setTotalAseguradoRepo(repAux.totalAsegurado);
				auxRepositorio.setVigenciaDesdeRepo(repAux.vigenciaDesde);
				auxRepositorio.setVigenciaHastaRepo(repAux.vigenciaHasta);
				auxRepositorio.setFc_emision_jul(repAux.fc_emision_jul);
				auxRepositorio.setFc_pago(repAux.fecha_pago);
				auxRepositorio.setNumero_expedicion_pago(repAux.num_expedicion);
				lstRepositorio.add(auxRepositorio);
			}

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Fecha de Pago - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		if (lscdRubro.equals("REPORTEPRODUCCIONPENDIENTEPAGO")) {
			flgRepProd = true;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			List<ProduccionPendientePagoView> lstRepProdPenPago = new ArrayList<ProduccionPendientePagoView>();
			lstRepProdPenPago = srvProdPrenPago.recuperaProduccionPendientePago(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			for (ProduccionPendientePagoView repAux : lstRepProdPenPago) {
				auxRepositorio = new ReporteRepositorio();
				auxRepositorio.setAseguradoraRepo(repAux.getAseguradora());
				auxRepositorio.setCanalRepo(repAux.getCanal());
				auxRepositorio.setCd_aseguradora(repAux.cd_aseguradora);
				auxRepositorio.setCd_cliente(repAux.cd_cliente);
				auxRepositorio.setCd_ejecutivo(repAux.cd_ejecutivo);
				auxRepositorio.setCd_grupo_contratante(repAux.cd_grupo_contratante);
				auxRepositorio.setCd_ramo(repAux.cd_ramo);
				auxRepositorio.setCd_subagente(repAux.cd_subagente);
				auxRepositorio.setClienteRepo(repAux.cliente);
				auxRepositorio.setCodIdRepo(repAux.codId);
				auxRepositorio.setComsionBrokerRepo(repAux.comsionBroker);
				auxRepositorio.setEjecutivoRepo(repAux.ejecutivo);
				auxRepositorio.setFacturaRepo(repAux.factura);
				auxRepositorio.setFc_desde_jul(repAux.fc_desde_jul);
				auxRepositorio.setFc_hasta_jul(repAux.fc_hasta_jul);
				auxRepositorio.setFechaEmisionRepo(repAux.fechaEmision);
				auxRepositorio.setGrupoContratanteRepo(repAux.grupoContratante);
				auxRepositorio.setNumRenovaRepo(repAux.numRenova);
				auxRepositorio.setPolizaRepo(repAux.poliza);
				auxRepositorio.setPorcComBrkRepo(repAux.porcComBrk);
				auxRepositorio.setPrimaNetaRepo(repAux.primaNeta);
				auxRepositorio.setPrimaTotalRepo(repAux.primaTotal);
				auxRepositorio.setRamoRepo(repAux.ramo);
				auxRepositorio.setTipoRepo(repAux.tipo);
				auxRepositorio.setTotalAseguradoRepo(repAux.totalAsegurado);
				auxRepositorio.setVigenciaDesdeRepo(repAux.vigenciaDesde);
				auxRepositorio.setVigenciaHastaRepo(repAux.vigenciaHasta);
				auxRepositorio.setFc_emision_jul(repAux.fc_emision_jul);
				lstRepositorio.add(auxRepositorio);
			}

			FacesMessage msg = new FacesMessage("Advertencia",
					"Fecha de corte - Mensualizado Fecha de Vigencia / Otros Fecha de Emisión- ");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

		if (lscdRubro.equals("REPORTEGENERALSINIESTROS")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = true;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			List<ReporteRepositorioSiniestro> lstRepGenSinies = new ArrayList<ReporteRepositorioSiniestro>();
			lstRepGenSinies = srvRepGeneralSiniestro.recuperaReporteRepositorioSiniestro(lscdCliente,
					lscdGrupoContratante, lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde,
					asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Fecha Reporte del Siniestro - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);

			for (ReporteRepositorioSiniestro repAux : lstRepGenSinies) {
				lstRepGeneralSiniestro.add(repAux);
			}
		}
		if (lscdRubro.equals("REPORTESINIESTRALIDAD")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = true;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			List<ReporteSiniestralidadView> lstRepSiniesAux = new ArrayList<ReporteSiniestralidadView>();
			lstRepSiniesAux = srvRepSiniestralidad.recuperaReporteSiniestralidad(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Fecha Siniestro - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);

			for (ReporteSiniestralidadView repAux : lstRepSiniesAux) {
				lstRepSiniestralidad.add(repAux);
			}
		}
		if (lscdRubro.equals("REPORTEGENERALCONFIA")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = true;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			List<ProduccionGeneralView> lstRepGenAux = new ArrayList<ProduccionGeneralView>();
			lstRepGenAux = srvProduccionGeneral.recuperaProduccionGeneralView(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Emisión de la Póliza - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);

			for (ProduccionGeneralView repAux : lstRepGenAux) {
				lstRepProdGen.add(repAux);
			}
		}
		if (lscdRubro.equals("REPORTECUOTASINICIALESIMPAGAS")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = true;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			lstCuotaIniImpaga = new ArrayList<ReporteCuotasIniImpaga>();
			lstCuotaIniImpaga = srvCuotaIniImpaga.recuperaReporteCuotasIniImp(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Emisión de la Póliza - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (lscdRubro.equals("REPORTECOMISIONESPENDIENTESCONFIA")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = true;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			lstReporteComisionPendteView = new ArrayList<ReporteComisionPendteView>();
			lstReporteComisionPendteView = srvReporteComisionPendteView.recuperaReporteComisionesPendientes(lscdCliente,
					lscdGrupoContratante, lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde,
					asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Vigencia de la Póliza - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (lscdRubro.equals("REPORTECOMISIONESLIQUIDADAS")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = true;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			lstReporteComisionesLiquidadasView = new ArrayList<ReporteComisionesLiquidadasView>();
			lstReporteComisionesLiquidadasView = srvReporteComisionesLiquidadasView
					.recuperaReporteComisionesLiquidadasViewRep(lscdCliente, lscdGrupoContratante, lscdAseguradora,
							lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Emisión de la Póliza - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (lscdRubro.equals("REPORTECANALES")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = true;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			lstReporteCanalesView = new ArrayList<ReporteCanalesView>();
			lstReporteCanalesView = srvReporteCanalesView.recuperaReporteCanalesView(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Emisión de la Póliza - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (lscdRubro.equals("REPORTEPRODUCCIONPENDIENTEEMISION")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = true;
			flgReporteCanalesPend = false;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			lstProdPendEmi = new ArrayList<ProduccionEmisionesPendientesView>();
			lstProdPendEmi = srvProdePendEmiView.recuperaEmisionesPendientesView(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Creación Cotización - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (lscdRubro.equals("REPORTECANALESPENDIENTE")) {
			flgRepProd = false;
			flgRepProdPag = false;
			flgRepSiniestro = false;
			flgRepSiniestralidad = false;
			flgRepProdGeneral = false;
			flgRepProdMens = false;
			flgRepCuotaIniImpaga = false;
			flgComisionesPendienteConfia = false;
			flgComisionesLiquidadas = false;
			flgReporteCanales = false;
			flgRepProdPendEmi = false;
			flgReporteCanalesPend = true;
			flgRepVencimiento = false;
			flgRepVencimientoMens = false;

			lstCanalesPend = new ArrayList<ReporteCanalesPendienteView>();
			lstCanalesPend = srvCanalPendView.recuperaReporteCanalesPenView(lscdCliente, lscdGrupoContratante,
					lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

			FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Emisión de la Póliza - ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (lscdRubro.equals("REPORTEVENCIMIENTOS")) {
			if (tipo.equals("0")) {
				flgRepProd = false;
				flgRepProdPag = false;
				flgRepSiniestro = false;
				flgRepSiniestralidad = false;
				flgRepProdGeneral = false;
				flgRepProdMens = false;
				flgRepCuotaIniImpaga = false;
				flgComisionesPendienteConfia = false;
				flgComisionesLiquidadas = false;
				flgReporteCanales = false;
				flgRepProdPendEmi = false;
				flgReporteCanalesPend = false;
				flgRepVencimiento = true;
				flgRepVencimientoMens = false;

				lstRepVencimiento = new ArrayList<ReporteVencimientoView>();
				lstRepVencimiento = srvVencimientoView.recuperaVencimiento(lscdCliente, lscdGrupoContratante,
						lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

				FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Fin de Vigencia - ");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				flgRepProd = false;
				flgRepProdPag = false;
				flgRepSiniestro = false;
				flgRepSiniestralidad = false;
				flgRepProdGeneral = false;
				flgRepProdMens = false;
				flgRepCuotaIniImpaga = false;
				flgComisionesPendienteConfia = false;
				flgComisionesLiquidadas = false;
				flgReporteCanales = false;
				flgRepProdPendEmi = false;
				flgReporteCanalesPend = false;
				flgRepVencimiento = false;
				flgRepVencimientoMens = true;

				lstRepVencimientoMen = new ArrayList<ReporteVencimientoMenView>();
				lstRepVencimientoMen = srvVencimientoViewMen.recuperaVencimientoMensual(lscdCliente, lscdGrupoContratante,
						lscdAseguradora, lscdRamo, lscdSubagente, lscdEjecutivo, asFechaDesde, asFechaHasta, tipo);

				FacesMessage msg = new FacesMessage("Advertencia", "Fecha de corte - Fin de Vigencia Cuota Mensual - ");
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}
		}

		System.out.println("TAMA�O PRODUCCION:" + lstRepositorio.size());
		System.out.println("TAMA�O SINIESTRO:" + lstRepGeneralSiniestro.size());
		System.out.println("TAMA�O SINEISTRALIDAD:" + lstRepSiniestralidad.size());
		System.out.println("TAMA�O General:" + lstRepProdGen.size());
		System.out.println("TAMA�O Cuotas Iniciales Impagas:" + lstCuotaIniImpaga.size());
		System.out.println("TAMA�O COMISIONES PENDIENTES CONFIA:" + lstReporteComisionPendteView.size());
		System.out.println("TAMA�O COMISIONES LIQUIDADAS:" + lstReporteComisionesLiquidadasView.size());
		System.out.println("TAMA�O CANALES:" + lstReporteCanalesView.size());
	}

	
	public Boolean getFlgRepProdPag() {
		return flgRepProdPag;
	}

	public void setFlgRepProdPag(Boolean flgRepProdPag) {
		this.flgRepProdPag = flgRepProdPag;
	}

	public Boolean getFlgRepVencimiento() {
		return flgRepVencimiento;
	}

	public void setFlgRepVencimiento(Boolean flgRepVencimiento) {
		this.flgRepVencimiento = flgRepVencimiento;
	}

	public List<ReporteVencimientoView> getLstRepVencimiento() {
		return lstRepVencimiento;
	}

	public void setLstRepVencimiento(List<ReporteVencimientoView> lstRepVencimiento) {
		this.lstRepVencimiento = lstRepVencimiento;
	}

	public Boolean getFlgReporteCanales() {
		return flgReporteCanales;
	}

	public void setFlgReporteCanales(Boolean flgReporteCanales) {
		this.flgReporteCanales = flgReporteCanales;
	}

	public List<ReporteCanalesView> getLstReporteCanalesView() {
		return lstReporteCanalesView;
	}

	public void setLstReporteCanalesView(List<ReporteCanalesView> lstReporteCanalesView) {
		this.lstReporteCanalesView = lstReporteCanalesView;
	}

	public Boolean getFlgComisionesLiquidadas() {
		return flgComisionesLiquidadas;
	}

	public void setFlgComisionesLiquidadas(Boolean flgComisionesLiquidadas) {
		this.flgComisionesLiquidadas = flgComisionesLiquidadas;
	}

	public List<ReporteComisionesLiquidadasView> getLstReporteComisionesLiquidadasView() {
		return lstReporteComisionesLiquidadasView;
	}

	public void setLstReporteComisionesLiquidadasView(
			List<ReporteComisionesLiquidadasView> lstReporteComisionesLiquidadasView) {
		this.lstReporteComisionesLiquidadasView = lstReporteComisionesLiquidadasView;
	}

	public Boolean getFlgComisionesPendienteConfia() {
		return flgComisionesPendienteConfia;
	}

	public void setFlgComisionesPendienteConfia(Boolean flgComisionesPendienteConfia) {
		this.flgComisionesPendienteConfia = flgComisionesPendienteConfia;
	}

	public List<ReporteComisionPendteView> getLstReporteComisionPendteView() {
		return lstReporteComisionPendteView;
	}

	public void setLstReporteComisionPendteView(List<ReporteComisionPendteView> lstReporteComisionPendteView) {
		this.lstReporteComisionPendteView = lstReporteComisionPendteView;
	}

	public Boolean getFlgRepProdGeneral() {
		return flgRepProdGeneral;
	}

	public void setFlgRepProdGeneral(Boolean flgRepProdGeneral) {
		this.flgRepProdGeneral = flgRepProdGeneral;
	}

	public List<ProduccionGeneralView> getLstRepProdGen() {
		return lstRepProdGen;
	}

	public void setLstRepProdGen(List<ProduccionGeneralView> lstRepProdGen) {
		this.lstRepProdGen = lstRepProdGen;
	}

	public Boolean getFlgRepSiniestralidad() {
		return flgRepSiniestralidad;
	}

	public void setFlgRepSiniestralidad(Boolean flgRepSiniestralidad) {
		this.flgRepSiniestralidad = flgRepSiniestralidad;
	}

	public List<ReporteSiniestralidadView> getLstRepSiniestralidad() {
		return lstRepSiniestralidad;
	}

	public void setLstRepSiniestralidad(List<ReporteSiniestralidadView> lstRepSiniestralidad) {
		this.lstRepSiniestralidad = lstRepSiniestralidad;
	}

	public List<ReporteRepositorioSiniestro> getLstRepGeneralSiniestro() {
		return lstRepGeneralSiniestro;
	}

	public void setLstRepGeneralSiniestro(List<ReporteRepositorioSiniestro> lstRepGeneralSiniestro) {
		this.lstRepGeneralSiniestro = lstRepGeneralSiniestro;
	}

	public String getLscdAseguradora() {
		return lscdAseguradora;
	}

	public void setLscdAseguradora(String lscdAseguradora) {
		this.lscdAseguradora = lscdAseguradora;
	}

	public List<Aseguradoras> getListAseguradoras() {
		return listAseguradoras;
	}

	public void setListAseguradoras(List<Aseguradoras> listAseguradoras) {
		this.listAseguradoras = listAseguradoras;
	}

	public String getLscdCliente() {
		return lscdCliente;
	}

	public void setLscdCliente(String lscdCliente) {
		this.lscdCliente = lscdCliente;
	}

	public List<Clientes> getListClientes() {
		return listClientes;
	}

	public void setListClientes(List<Clientes> listClientes) {
		this.listClientes = listClientes;
	}

	public String getLscdRamo() {
		return lscdRamo;
	}

	public void setLscdRamo(String lscdRamo) {
		this.lscdRamo = lscdRamo;
	}

	public List<Ramo> getLstRamo() {
		return lstRamo;
	}

	public void setLstRamo(List<Ramo> lstRamo) {
		this.lstRamo = lstRamo;
	}

	public String getLscdGrupoContratante() {
		return lscdGrupoContratante;
	}

	public void setLscdGrupoContratante(String lscdGrupoContratante) {
		this.lscdGrupoContratante = lscdGrupoContratante;
	}

	public List<GrupoContratante> getLstGrupocontratante() {
		return lstGrupocontratante;
	}

	public void setLstGrupocontratante(List<GrupoContratante> lstGrupocontratante) {
		this.lstGrupocontratante = lstGrupocontratante;
	}

	public String getLscdSubagente() {
		return lscdSubagente;
	}

	public void setLscdSubagente(String lscdSubagente) {
		this.lscdSubagente = lscdSubagente;
	}

	public List<Subagentes> getLstSubagente() {
		return lstSubagente;
	}

	public void setLstSubagente(List<Subagentes> lstSubagente) {
		this.lstSubagente = lstSubagente;
	}

	public String getLscdEjecutivo() {
		return lscdEjecutivo;
	}

	public void setLscdEjecutivo(String lscdEjecutivo) {
		this.lscdEjecutivo = lscdEjecutivo;
	}

	public List<Ejecutivos> getLstEjecutivos() {
		return lstEjecutivos;
	}

	public void setLstEjecutivos(List<Ejecutivos> lstEjecutivos) {
		this.lstEjecutivos = lstEjecutivos;
	}

	public String getLscdRubro() {
		return lscdRubro;
	}

	public void setLscdRubro(String lscdRubro) {
		this.lscdRubro = lscdRubro;
	}

	public List<Rubros> getLstRubros() {
		return lstRubros;
	}

	public void setLstRubros(List<Rubros> lstRubros) {
		this.lstRubros = lstRubros;
	}

	public Date getFcDesde() {
		return fcDesde;
	}

	public void setFcDesde(Date fcDesde) {
		this.fcDesde = fcDesde;
	}

	public Date getFcHasta() {
		return fcHasta;
	}

	public void setFcHasta(Date fcHasta) {
		this.fcHasta = fcHasta;
	}

	public List<ReporteRepositorio> getLstRepositorio() {
		return lstRepositorio;
	}

	public void setLstRepositorio(List<ReporteRepositorio> lstRepositorio) {
		this.lstRepositorio = lstRepositorio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getFlgRepProd() {
		return flgRepProd;
	}

	public void setFlgRepProd(Boolean flgRepProd) {
		this.flgRepProd = flgRepProd;
	}

	public Boolean getFlgRepSiniestro() {
		return flgRepSiniestro;
	}

	public void setFlgRepSiniestro(Boolean flgRepSiniestro) {
		this.flgRepSiniestro = flgRepSiniestro;
	}

	public List<ProduccionEmitidaMensualizadoView> getLstProdEmiMEnsualizado() {
		return lstProdEmiMEnsualizado;
	}

	public void setLstProdEmiMEnsualizado(List<ProduccionEmitidaMensualizadoView> lstProdEmiMEnsualizado) {
		this.lstProdEmiMEnsualizado = lstProdEmiMEnsualizado;
	}

	public Boolean getFlgRepProdMens() {
		return flgRepProdMens;
	}

	public void setFlgRepProdMens(Boolean flgRepProdMens) {
		this.flgRepProdMens = flgRepProdMens;
	}

	public Boolean getFlgRepCuotaIniImpaga() {
		return flgRepCuotaIniImpaga;
	}

	public void setFlgRepCuotaIniImpaga(Boolean flgRepCuotaIniImpaga) {
		this.flgRepCuotaIniImpaga = flgRepCuotaIniImpaga;
	}

	public List<ReporteCuotasIniImpaga> getLstCuotaIniImpaga() {
		return lstCuotaIniImpaga;
	}

	public void setLstCuotaIniImpaga(List<ReporteCuotasIniImpaga> lstCuotaIniImpaga) {
		this.lstCuotaIniImpaga = lstCuotaIniImpaga;
	}

	public Boolean getFlgRepProdPendEmi() {
		return flgRepProdPendEmi;
	}

	public void setFlgRepProdPendEmi(Boolean flgRepProdPendEmi) {
		this.flgRepProdPendEmi = flgRepProdPendEmi;
	}

	public List<ProduccionEmisionesPendientesView> getLstProdPendEmi() {
		return lstProdPendEmi;
	}

	public void setLstProdPendEmi(List<ProduccionEmisionesPendientesView> lstProdPendEmi) {
		this.lstProdPendEmi = lstProdPendEmi;
	}

	public List<ReporteCanalesPendienteView> getLstCanalesPend() {
		return lstCanalesPend;
	}

	public void setLstCanalesPend(List<ReporteCanalesPendienteView> lstCanalesPend) {
		this.lstCanalesPend = lstCanalesPend;
	}

	public Boolean getFlgReporteCanalesPend() {
		return flgReporteCanalesPend;
	}

	public void setFlgReporteCanalesPend(Boolean flgReporteCanalesPend) {
		this.flgReporteCanalesPend = flgReporteCanalesPend;
	}

	public Boolean getFlgRepVencimientoMens() {
		return flgRepVencimientoMens;
	}

	public void setFlgRepVencimientoMens(Boolean flgRepVencimientoMens) {
		this.flgRepVencimientoMens = flgRepVencimientoMens;
	}

	public List<ReporteVencimientoMenView> getLstRepVencimientoMen() {
		return lstRepVencimientoMen;
	}

	public void setLstRepVencimientoMen(List<ReporteVencimientoMenView> lstRepVencimientoMen) {
		this.lstRepVencimientoMen = lstRepVencimientoMen;
	}

}
