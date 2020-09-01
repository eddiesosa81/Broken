package confia.controladores.transaccionales;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.PlanDepreciacion;
import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.entidades.transaccionales.Cotizacion;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.FormaPago;
import confia.entidades.transaccionales.ObjetoCotizacion;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.vistas.ConsultaAnexoPendienteView;
import confia.entidades.vistas.ConsultaObjetoPolView;
import confia.entidades.vistas.ConsultaPolizaView;
import confia.entidades.vistas.ConsultaSubObjetoPolView;
import confia.entidades.vistas.ConsultaUbicacionPolView;
import confia.procedures.servicioProcedures;
import confia.servicios.basicos.ServicioPlanDepreciacion;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.transaccionales.ServicioCaracteristicasVehiculos;
import confia.servicios.transaccionales.ServicioCotizacion;
import confia.servicios.transaccionales.ServicioDetalleFormaPago;
import confia.servicios.transaccionales.ServicioFormaPago;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.vistas.ServicioConsultaObjetoPolView;
import confia.servicios.vistas.ServicioConsultaPolizaView;
import confia.servicios.vistas.ServicioConsultaSubObjetoPolView;
import confia.servicios.vistas.ServicioConsultaUbicacionPolView;

@ManagedBean(name = "ControladorNivelaMensualizado")
@ViewScoped
public class ControladorNivelaMensualizado {
	@EJB
	private ServicioConsultaPolizaView srvConsultaPolizaView;
	@EJB
	private ServicioConsultaUbicacionPolView srvConsultaUbicacionView;
	@EJB
	private ServicioConsultaObjetoPolView srvConsObjPolView;
	@EJB
	private ServicioConsultaSubObjetoPolView srvConsSubobjPolView;
	@EJB
	private ServicioCaracteristicasVehiculos srvCaracteristicas;
	@EJB
	private ServicioFormaPago srvFormaPago;
	@EJB
	private ServicioDetalleFormaPago srvDetalleFormaPago;
	@EJB
	private ServicioCotizacion srvCotizacion;
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;
	private servicioProcedures srvProcedimientos;
	@EJB
	private ServicioPlanDepreciacion srvPlanDepre;
	@EJB
	private ServicioRubros srvRubros;

	private String numPolizaAnexo;
	private String clientePolizaAnexo;
	private String ramoPolizaAnexo;
	private String apellidoRazonSocial;
	private ConsultaPolizaView PolizaSeleccionadaParaAnexo;
	private List<ConsultaPolizaView> lstConsultaPoliza;
	private List<ConsultaUbicacionPolView> lstUbicacion;
	private ConsultaUbicacionPolView selectedUbicacion;
	private List<ConsultaObjetoPolView> lstObjetosPoliza;
	private ConsultaPolizaView selectedDatosObjetoCotizacion;
	private ConsultaObjetoPolView selectedDatosObjt;
	private List<ConsultaSubObjetoPolView> lstSubObjetoCot;
	private List<CaracteristicasVehiculos> lstCaracteristicasVh;
	private List<DetalleFormaPago> lstDetalleFormaPago;
	private String codigoCompania;

	// datos del anexo
	private Cotizacion nuevaCot;
	private RamoCotizacion datosRamoCotizacion;
	private String numCotizacion;
	private Date fcAnexo;
	private Integer llfcDesde, llfcHasta;
	private Integer dias;
	private String facturaAseguradora;
	private String anexo;
	Boolean flgCancela;

	public ControladorNivelaMensualizado() {
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();
		lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		lstSubObjetoCot = new ArrayList<ConsultaSubObjetoPolView>();
		lstCaracteristicasVh = new ArrayList<CaracteristicasVehiculos>();
		lstDetalleFormaPago = new ArrayList<DetalleFormaPago>();
		PolizaSeleccionadaParaAnexo = new ConsultaPolizaView();
		srvProcedimientos = new servicioProcedures();
		fcAnexo = new Date();
		flgCancela = false;
	}

	public void consultaPoliza() {
		System.out.println("INGRESOOOO Bonsulta Poliza");
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wbuscaPolizaAneMen').show();");
		PrimeFaces.current().executeScript("PF('wbuscaPolizaAneMen').show();");
	}

	public void cancelaAnexo() {
		ExternalContext ctx2 = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx2.redirect("./indexMensualizado.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void buscaPoliza() {
		try {
			apellidoRazonSocial = apellidoRazonSocial.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			return;
		}
		lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXClienteMen(apellidoRazonSocial);
	}

	public void onRowSelectPol(SelectEvent event) {
		String crc;
		crc = ((ConsultaPolizaView) event.getObject()).getCd_ramo_cotizacion();
		lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
		lstUbicacion = srvConsultaUbicacionView.consultaUbicacionxCrc(crc);
	}

	public void onRowSelectUbc(SelectEvent event) {
		String ubc;
		ubc = ((ConsultaUbicacionPolView) event.getObject()).getCd_ubicacion();
		System.out.println("UBC:" + ubc);
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		lstObjetosPoliza = srvConsObjPolView.consultaObjetosXUbc(ubc);

	}

	public void onRowSelectObj(SelectEvent event) {
		String obj;
		obj = ((ConsultaObjetoPolView) event.getObject()).getCd_obj_cotizacion();
		lstSubObjetoCot = new ArrayList<ConsultaSubObjetoPolView>();
		lstSubObjetoCot = srvConsSubobjPolView.consultaSubObjetoxCdObj(obj);
		lstCaracteristicasVh = new ArrayList<CaracteristicasVehiculos>();
		lstCaracteristicasVh = srvCaracteristicas.recuperaCaractVHporObjCot(Integer.valueOf(obj), 1);
	}

	public void aceptaAnexo() {
		Double saldoDet = 0.00;
		String nmEndoso;
		if (lstDetalleFormaPago.size() == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error ", "Seleccione una Póliza"));
			return;
		}
		// verifico si se trata de una anulación
		for (DetalleFormaPago detFrmPagAux : lstDetalleFormaPago) {
			saldoDet = detFrmPagAux.getSALDO();
			if (saldoDet.equals(0.00)) {
				flgCancela = true;
				break;
			}
		}
		if (flgCancela) {
			try {
				nmEndoso = PolizaSeleccionadaParaAnexo.getTipo_endoso();
				if (nmEndoso.equals("NIVELACION")) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
							new FacesMessage("Advertencia ", "No se puede aplicar la Nivelación de Factura"));
					return;
				}
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia ", "No se puede aplicar la Nivelación de Factura"));
				return;
			}

			for (DetalleFormaPago detFrmPagAux : lstDetalleFormaPago) {
				saldoDet = saldoDet + detFrmPagAux.getSALDO();
			}
			if (saldoDet == 0.00) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia ", "No se puede aplicar la Nivelación de Factura"));
				return;
			}
//			RequestContext context = RequestContext.getCurrentInstance();
//			context.execute("PF('wDlgNivelaPol').show();");
			PrimeFaces.current().executeScript("PF('wDlgNivelaPol').show();");
		} else {
//			RequestContext context = RequestContext.getCurrentInstance();
//			context.execute("PF('wDlgNivelaPol').show();");
			PrimeFaces.current().executeScript("PF('wDlgNivelaPol').show();");
		}
	}

	public void anulaMensual() {
		// ANULACIPON DE POLIZA
		//*************************
		String lsCompania;
		Integer liCompania;
		Integer fcJuliana;
		Boolean flgAnexo = false;
		lsCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA").toString();
		liCompania = Integer.parseInt(lsCompania);
		nuevaCot = new Cotizacion();
		// ingresa nueva cotización
		nuevaCot.setCd_compania(liCompania);
		nuevaCot.setAfecta_anexo("POLIZA");
		nuevaCot.setCd_rubro(51); // anulación póliza
		nuevaCot.setCd_cliente(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_cliente()));
		nuevaCot.setCd_aseguradora(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_aseguradora()));
		nuevaCot.setFact_periodica_cot(0);
		Integer usrId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO");
		System.out.println("USUARIO:" + usrId);
		nuevaCot.setUsrid(usrId);
		Integer cdCotMax = srvCotizacion.insertarCotizacion(nuevaCot);
		if (cdCotMax == 1) {
			cdCotMax = srvCotizacion.codigoMaxCotizacion();
		}
		nuevaCot = new Cotizacion();
		nuevaCot = srvCotizacion.recuperaCotizacionPorCodigo(cdCotMax, liCompania);
		numCotizacion = nuevaCot.getNum_cotizacion();
		System.out.println("NUMERO COTIZACION: " + numCotizacion);
		// INGRESO RAMO - COTIZACION
		Date fcActual = new Date();
		datosRamoCotizacion = new RamoCotizacion();
		datosRamoCotizacion.setCdRamCotOri(Double.valueOf(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion()));
		datosRamoCotizacion.setCd_cotizacion(nuevaCot.getCd_cotizacion());
		datosRamoCotizacion.setCd_compania(nuevaCot.getCd_compania());
		datosRamoCotizacion.setCd_subagente(Integer.decode(PolizaSeleccionadaParaAnexo.getCdSubagente()));
		datosRamoCotizacion.setCd_ramo(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_ramo()));
		datosRamoCotizacion.setCd_ejecutivo(Integer.decode(PolizaSeleccionadaParaAnexo.getCdEjecutivo()));
		datosRamoCotizacion.setPoliza(PolizaSeleccionadaParaAnexo.getPoliza());
		datosRamoCotizacion.setFactura_aseguradora(facturaAseguradora);
		datosRamoCotizacion.setAnexo(anexo);
		datosRamoCotizacion.setFc_emision_aseguradora_date(fcActual);
		datosRamoCotizacion.setCd_plan(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_plan()));
		datosRamoCotizacion
				.setCd_grupo_contratante(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_grupo_contratante()));
		datosRamoCotizacion.setFc_fin_vig_date(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
		datosRamoCotizacion.setTotal_asegurado(PolizaSeleccionadaParaAnexo.getTotal_asegurado());
		datosRamoCotizacion.setFc_ini_vig_date(fcAnexo);
		///////////////////////////////
		// CONTROLA LA VIGENCIA
		//////////////////////////////
		String lsfcDesde, lsfcHasta;
		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		lsfcDesde = formato.format(fcAnexo);
		lsfcHasta = formato.format(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
		System.out.println("FECHA DESDE:" + lsfcDesde);
		System.out.println("FECHA HASTA:" + lsfcHasta);
		llfcDesde = srvProcedimientos.fechaJuliana(lsfcDesde);
		llfcHasta = srvProcedimientos.fechaJuliana(lsfcHasta);
		dias = srvProcedimientos.diasVigencias(llfcDesde, llfcHasta);
		System.out.println("FEC JUL DESDE:" + llfcDesde);
		System.out.println("FEC JUL HASTA:" + llfcHasta);
		System.out.println("DIAS VIGENCIA:" + dias);
		/////////////////////////////////////////////////////////////////////////
		datosRamoCotizacion.setFc_ini_vigencia(llfcDesde);
		datosRamoCotizacion.setFc_fin_vigencia(llfcHasta);
		datosRamoCotizacion.setDiasVigencia(dias);

		Integer resInsert = srvRamoCotizacion.insertarRamoCotizacion(datosRamoCotizacion);
		if (resInsert == 1) {
			datosRamoCotizacion = new RamoCotizacion();
			datosRamoCotizacion = srvRamoCotizacion.recuperaRamoCotizacionAnexo(nuevaCot.getCd_cotizacion(),
					liCompania);
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Error - Inserta/RamoCot", "Comuniquece con el Administrador del Sistema"));
			return;
		}

		// CONSULTO CONFIGURACIÓN DEL PLAN
		PlanDepreciacion planDep = new PlanDepreciacion();
		planDep = srvPlanDepre.consultaPlanDepreciacionAnio1(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_plan()));

		// GENERO LA FORMA DE PAGO DE LA NIVELACION
		int res = 0, contador = 0, cdFrmPago, cdDetFrmPagOri = 0;
		Date fcNivelacioon = new Date();
		Double saldoDet, primaNetaMes = 0.00, nivelacionAcum = 0.00, supBanc, segCamp, derechEmi, subtot, iva, tot,
				valAsegMen = 0.00;

		supBanc = redondear(planDep.getSuperbancos() / 100);
		segCamp = redondear(planDep.getSegurocampesino() / 100);
		derechEmi = planDep.getDerechoemision();

		primaNetaMes = 0.00;
		for (DetalleFormaPago detFrmPagAux : lstDetalleFormaPago) {
			primaNetaMes = primaNetaMes + detFrmPagAux.getPrima_neta_mensual();
		}

		FormaPago frmPag = new FormaPago();
		frmPag.setCd_compania(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
		frmPag.setCd_cotizacion(nuevaCot.getCd_cotizacion());
		frmPag.setNum_alternativa_formaPago("ANULA_FACTURACION_MENSUAL");
		frmPag.setNum_pago_formaPago(0);
		frmPag.setPct_cuota_Inicial_frmPago(0.00);
		frmPag.setDerecho_Emision_formaPago(0.00);
		frmPag.setSuperBanco_forma_Pago(0.00);
		frmPag.setSeguroCampesion_forma_Pago(0.00);
		frmPag.setSin_iva(0);
		res = srvFormaPago.insertaFormaPago(frmPag);
		if (res == 1) {
			cdFrmPago = srvFormaPago.codigoMaxFormaPago();
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Error - FormaPago", "Comuniquece con el Administrador del Sistema"));
			return;
		}

		// primaNetaMes = redondear(primaNetaMes / contador);
//		primaNetaMes = redondear(primaNetaMes) * -1;
//		supBanc = redondear(supBanc * primaNetaMes);
//		segCamp = redondear(segCamp * primaNetaMes);
//		derechEmi = redondear(derechEmi * primaNetaMes) * -1;
//		subtot = redondear(primaNetaMes + supBanc + segCamp + derechEmi);
//		System.out.println("SUBTOTAL:"+subtot);
//		iva = redondear(Double.valueOf(srvRubros.recuperaIva()) / 100);
//		iva = redondear(subtot * iva) * -1;
//		tot = iva + subtot;
//		System.out.println("TOTAL:"+tot);
//		datosRamoCotizacion.setTotal_prima(primaNetaMes);
//		srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
//
//		DetalleFormaPago detFrmPag = new DetalleFormaPago();
//		detFrmPag.setCD_FORMA_PAGO(cdFrmPago);
//		detFrmPag.setCD_COMPANIA(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
//		detFrmPag.setVALOR(tot);
//		detFrmPag.setSALDO(tot);
//		detFrmPag.setFECHA_VENCIMIENTO_DATE(fcAnexo);
//		detFrmPag.setFACTURA_ASEGURADORA(facturaAseguradora);
//		detFrmPag.setFECHA_INGRESO_FACTURA_DATE(fcNivelacioon);
//		detFrmPag.setFLG_PAGO(0);
//		detFrmPag.setFlg_comision("1");
//		detFrmPag.setValor_asegurado_mensual(0.00);
//		detFrmPag.setPrima_neta_mensual(0.00);
//		detFrmPag.setSuper_bancos_mensual(0.00);
//		detFrmPag.setSeguro_campesino_mensual(0.00);
//		detFrmPag.setDerechos_emision_mensual(0.00);
//		detFrmPag.setSubtotal_mensual(0.00);
//		detFrmPag.setIva_mensual(0.00);
//		detFrmPag.setPrima_total_costo_real_mensual(0.00);
//		detFrmPag.setCuota_fija_nivelacion(0.00);
//		detFrmPag.setDiferencia_nivelacion(0.00);
//		detFrmPag.setNivelacion_acumulada(0.00);
//		detFrmPag.setPrima_neta_registro(0.00);
//		detFrmPag.setSuper_bancos_registro(0.00);
//		detFrmPag.setSeguro_campesino_registro(0.00);
//		detFrmPag.setDerecho_emision_registro(0.00);
//		detFrmPag.setSubtotal_registro(0.00);
//		detFrmPag.setIva_registro(0.00);
//		detFrmPag.setTotal_registro(0.00);
//		detFrmPag.setFc_inicio_vigencia(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
//		detFrmPag.setFc_fin_vigencia(fcAnexo);
//		srvDetalleFormaPago.insertaDetalleFormaPago(detFrmPag);
		
		// ACTUALIZA EL RESTO DE CUOTAS DE LA LISTA;
		for (DetalleFormaPago detFrmPagAux : lstDetalleFormaPago) {
			if (detFrmPagAux.getSALDO() != 0.00) {
				detFrmPagAux.setSALDO(0.00);
				detFrmPagAux.setFLG_PAGO(1);
				detFrmPagAux.setFlg_comision("0");
				detFrmPagAux.setOBSERVACIONES_DET_FORMA_PAGO("ANULACION FACTURA MENSUALIZADO");
				srvDetalleFormaPago.actualizaDetFormaPago(detFrmPagAux);
			}
		}
		// actualiza la póliza
		res = srvProcedimientos.anexoAnulaCancelaPoliza(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
				String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
				String.valueOf(datosRamoCotizacion.getCd_compania()));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgNivelaPol').hide();");
//		cancelaAnexo();
	}
	
	public void procesaAnexo() {
		if (flgCancela.equals(true)) {
			anulaniVPol();
		} else {
			anulaMensual();
		}
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgNivelaPol').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNivelaPol').hide();");
		cancelaAnexo();
	}

	public void anulaniVPol() {
		////////////////////////////////////////////////
		// CANCELACION MENSUALIZADO
		// añado la cotización para el anexo
		// COTIZACION
		////////////////////////////////////////////////
		String lsCompania;
		Integer liCompania;
		Integer fcJuliana;
		Boolean flgAnexo = false;
		lsCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA").toString();
		liCompania = Integer.parseInt(lsCompania);
		nuevaCot = new Cotizacion();
		// ingresa nueva cotización
		nuevaCot.setCd_compania(liCompania);
		nuevaCot.setAfecta_anexo("POLIZA");
		nuevaCot.setCd_rubro(590); // endoso de nivelacion factura
		nuevaCot.setCd_cliente(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_cliente()));
		nuevaCot.setCd_aseguradora(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_aseguradora()));
		nuevaCot.setFact_periodica_cot(1);
		Integer usrId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO");
		System.out.println("USUARIO:" + usrId);
		nuevaCot.setUsrid(usrId);
		Integer cdCotMax = srvCotizacion.insertarCotizacion(nuevaCot);
		if (cdCotMax == 1) {
			cdCotMax = srvCotizacion.codigoMaxCotizacion();
		}
		nuevaCot = new Cotizacion();
		nuevaCot = srvCotizacion.recuperaCotizacionPorCodigo(cdCotMax, liCompania);
		numCotizacion = nuevaCot.getNum_cotizacion();
		System.out.println("NUMERO COTIZACION: " + numCotizacion);
		// INGRESO RAMO - COTIZACION
		Date fcActual = new Date();
		datosRamoCotizacion = new RamoCotizacion();
		datosRamoCotizacion.setCdRamCotOri(Double.valueOf(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion()));
		datosRamoCotizacion.setCd_cotizacion(nuevaCot.getCd_cotizacion());
		datosRamoCotizacion.setCd_compania(nuevaCot.getCd_compania());
		datosRamoCotizacion.setCd_subagente(Integer.decode(PolizaSeleccionadaParaAnexo.getCdSubagente()));
		datosRamoCotizacion.setCd_ramo(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_ramo()));
		datosRamoCotizacion.setCd_ejecutivo(Integer.decode(PolizaSeleccionadaParaAnexo.getCdEjecutivo()));
		datosRamoCotizacion.setPoliza(PolizaSeleccionadaParaAnexo.getPoliza());
		datosRamoCotizacion.setFactura_aseguradora(facturaAseguradora);
		datosRamoCotizacion.setAnexo(anexo);
		datosRamoCotizacion.setFc_emision_aseguradora_date(fcActual);
		datosRamoCotizacion.setCd_plan(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_plan()));
		datosRamoCotizacion
				.setCd_grupo_contratante(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_grupo_contratante()));
		datosRamoCotizacion.setFc_fin_vig_date(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
		datosRamoCotizacion.setFc_ini_vig_date(fcAnexo);
		///////////////////////////////
		// CONTROLA LA VIGENCIA
		//////////////////////////////
		String lsfcDesde, lsfcHasta;
		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		lsfcDesde = formato.format(fcAnexo);
		lsfcHasta = formato.format(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
		System.out.println("FECHA DESDE:" + lsfcDesde);
		System.out.println("FECHA HASTA:" + lsfcHasta);
		llfcDesde = srvProcedimientos.fechaJuliana(lsfcDesde);
		llfcHasta = srvProcedimientos.fechaJuliana(lsfcHasta);
		dias = srvProcedimientos.diasVigencias(llfcDesde, llfcHasta);
		System.out.println("FEC JUL DESDE:" + llfcDesde);
		System.out.println("FEC JUL HASTA:" + llfcHasta);
		System.out.println("DIAS VIGENCIA:" + dias);
		/////////////////////////////////////////////////////////////////////////
		datosRamoCotizacion.setFc_ini_vigencia(llfcDesde);
		datosRamoCotizacion.setFc_fin_vigencia(llfcHasta);
		datosRamoCotizacion.setDiasVigencia(dias);

		Integer resInsert = srvRamoCotizacion.insertarRamoCotizacion(datosRamoCotizacion);
		if (resInsert == 1) {
			datosRamoCotizacion = new RamoCotizacion();
			datosRamoCotizacion = srvRamoCotizacion.recuperaRamoCotizacionAnexo(nuevaCot.getCd_cotizacion(),
					liCompania);
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Error - Inserta/RamoCot", "Comuniquece con el Administrador del Sistema"));
			return;
		}
		// CONSULTO CONFIGURACIÓN DEL PLAN
		PlanDepreciacion planDep = new PlanDepreciacion();
		planDep = srvPlanDepre.consultaPlanDepreciacionAnio1(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_plan()));

		// GENERO LA FORMA DE PAGO DE LA NIVELACION
		int res = 0, contador = 0, cdFrmPago, cdDetFrmPagOri = 0;
		Date fcNivelacioon = new Date();
		Double saldoDet, primaNetaMes = 0.00, nivelacionAcum = 0.00, supBanc, segCamp, derechEmi, subtot, iva, tot,
				valAsegMen = 0.00;

		supBanc = redondear(planDep.getSuperbancos() / 100);
		segCamp = redondear(planDep.getSegurocampesino() / 100);
		derechEmi = planDep.getDerechoemision();
		primaNetaMes = 0.00;
		valAsegMen = 0.00;
		for (DetalleFormaPago detFrmPagAux : lstDetalleFormaPago) {
			contador = contador + 1;
			saldoDet = detFrmPagAux.getSALDO();
			//primaNetaMes = detFrmPagAux.getPrima_neta_mensual();
			nivelacionAcum = detFrmPagAux.getNivelacion_acumulada();
			if (nivelacionAcum < 0.00) {
				nivelacionAcum = nivelacionAcum * -1;
			}
			//valAsegMen = detFrmPagAux.getValor_asegurado_mensual();
			if (saldoDet != 0.00) {
				cdDetFrmPagOri = detFrmPagAux.getCD_DET_FORMA_PAGO();
				break;
			}
		}

		FormaPago frmPag = new FormaPago();
		frmPag.setCd_compania(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
		frmPag.setCd_cotizacion(nuevaCot.getCd_cotizacion());
		frmPag.setNum_alternativa_formaPago("FACTURACION_MENSUAL");
		frmPag.setNum_pago_formaPago(1);
		frmPag.setDerecho_Emision_formaPago(derechEmi);
		frmPag.setSuperBanco_forma_Pago(supBanc);
		frmPag.setSeguroCampesion_forma_Pago(segCamp);
		frmPag.setSin_iva(1);
		res = srvFormaPago.insertaFormaPago(frmPag);
		if (res == 1) {
			cdFrmPago = srvFormaPago.codigoMaxFormaPago();
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Error - FormaPago", "Comuniquece con el Administrador del Sistema"));
			return;
		}

//		primaNetaMes = redondear(primaNetaMes / contador);
		primaNetaMes = nivelacionAcum;
		supBanc = redondear(supBanc * primaNetaMes);
		segCamp = redondear(segCamp * primaNetaMes);
		derechEmi = redondear(derechEmi * primaNetaMes);
		subtot = redondear(primaNetaMes + supBanc + segCamp + derechEmi);
		iva = redondear(Double.valueOf(srvRubros.recuperaIva()) / 100);
		iva = redondear(subtot * iva);
		tot = iva + subtot;

		DetalleFormaPago detFrmPag = new DetalleFormaPago();
		detFrmPag.setCD_COMPANIA(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
		detFrmPag.setCD_FORMA_PAGO(cdFrmPago);
		detFrmPag.setVALOR(tot);
		detFrmPag.setSALDO(tot);
		detFrmPag.setFACTURA_ASEGURADORA(facturaAseguradora);
		detFrmPag.setFECHA_INGRESO_FACTURA_DATE(fcNivelacioon);
		detFrmPag.setFLG_PAGO(0);
		detFrmPag.setFlg_comision("1");
		detFrmPag.setValor_asegurado_mensual(valAsegMen);
		detFrmPag.setPrima_neta_mensual(primaNetaMes);
		detFrmPag.setSuper_bancos_mensual(supBanc);
		detFrmPag.setSeguro_campesino_mensual(segCamp);
		detFrmPag.setDerechos_emision_mensual(derechEmi);
		detFrmPag.setSubtotal_mensual(subtot);
		detFrmPag.setIva_mensual(iva);
		detFrmPag.setPrima_total_costo_real_mensual(tot);
		detFrmPag.setCuota_fija_nivelacion(0.00);
		detFrmPag.setDiferencia_nivelacion(0.00);
		detFrmPag.setNivelacion_acumulada(nivelacionAcum);
		detFrmPag.setPrima_neta_registro(primaNetaMes);
		detFrmPag.setSuper_bancos_registro(supBanc);
		detFrmPag.setSeguro_campesino_registro(segCamp);
		detFrmPag.setDerecho_emision_registro(derechEmi);
		detFrmPag.setSubtotal_registro(subtot);
		detFrmPag.setIva_registro(iva);
		detFrmPag.setTotal_registro(tot);
		detFrmPag.setOBSERVACIONES_DET_FORMA_PAGO("NIVELACION CANCELACION FACTURA MENSUALIZADO");
		detFrmPag.setFc_inicio_vigencia(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
		detFrmPag.setFc_fin_vigencia(fcAnexo);
		srvDetalleFormaPago.insertaDetalleFormaPago(detFrmPag);
		datosRamoCotizacion.setTotal_prima(primaNetaMes);
		datosRamoCotizacion.setTotal_asegurado(valAsegMen);
		srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
		// ACTUALIZA EL RESTO DE CUOTAS DE LA LISTA;
		for (DetalleFormaPago detFrmPagAux : lstDetalleFormaPago) {
			if (detFrmPagAux.getSALDO() != 0.00) {
				if (cdDetFrmPagOri != detFrmPagAux.getCD_DET_FORMA_PAGO()) {
					detFrmPagAux.setSALDO(0.00);
					detFrmPagAux.setFLG_PAGO(1);
					detFrmPagAux.setFlg_comision("0");
					detFrmPagAux.setOBSERVACIONES_DET_FORMA_PAGO("CANCELACION FACTURA MENSUALIZADO");
					srvDetalleFormaPago.actualizaDetFormaPago(detFrmPagAux);
				}
			}

		}
		// actualiza la póliza
		res = srvProcedimientos.anexoAnulaCancelaPoliza(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
				String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
				String.valueOf(datosRamoCotizacion.getCd_compania()));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgNivelaPol').hide();");
//		cancelaAnexo();
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void buscarPolizaXIdAneP() {
		// RECUPERO DATOS DE LA COTIZACION

		codigoCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
				.toString();
		String cdCotizacionAne;
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		cdCotizacionAne = requestParameterMap.get("cdCotizacion");
		System.out.println("COTIZACION SELECCIONADA:" + cdCotizacionAne);
		lstDetalleFormaPago = srvDetalleFormaPago.recuperaDetalleFrmPago(cdCotizacionAne, codigoCompania);
		PolizaSeleccionadaParaAnexo = srvConsultaPolizaView.consultaPolizaXCdCot(cdCotizacionAne);
		codigoCompania = PolizaSeleccionadaParaAnexo.getCd_compania();
		numPolizaAnexo = PolizaSeleccionadaParaAnexo.getPoliza();
		clientePolizaAnexo = PolizaSeleccionadaParaAnexo.getCliente();
		ramoPolizaAnexo = PolizaSeleccionadaParaAnexo.getDesc_ramo();
		apellidoRazonSocial = null;

	}

	public List<ConsultaPolizaView> getLstConsultaPoliza() {
		return lstConsultaPoliza;
	}

	public void setLstConsultaPoliza(List<ConsultaPolizaView> lstConsultaPoliza) {
		this.lstConsultaPoliza = lstConsultaPoliza;
	}

	public String getNumPolizaAnexo() {
		return numPolizaAnexo;
	}

	public void setNumPolizaAnexo(String numPolizaAnexo) {
		this.numPolizaAnexo = numPolizaAnexo;
	}

	public String getClientePolizaAnexo() {
		return clientePolizaAnexo;
	}

	public void setClientePolizaAnexo(String clientePolizaAnexo) {
		this.clientePolizaAnexo = clientePolizaAnexo;
	}

	public String getRamoPolizaAnexo() {
		return ramoPolizaAnexo;
	}

	public void setRamoPolizaAnexo(String ramoPolizaAnexo) {
		this.ramoPolizaAnexo = ramoPolizaAnexo;
	}

	public String getApellidoRazonSocial() {
		return apellidoRazonSocial;
	}

	public void setApellidoRazonSocial(String apellidoRazonSocial) {
		this.apellidoRazonSocial = apellidoRazonSocial;
	}

	public List<ConsultaUbicacionPolView> getLstUbicacion() {
		return lstUbicacion;
	}

	public void setLstUbicacion(List<ConsultaUbicacionPolView> lstUbicacion) {
		this.lstUbicacion = lstUbicacion;
	}

	public ConsultaUbicacionPolView getSelectedUbicacion() {
		return selectedUbicacion;
	}

	public void setSelectedUbicacion(ConsultaUbicacionPolView selectedUbicacion) {
		this.selectedUbicacion = selectedUbicacion;
	}

	public ConsultaPolizaView getSelectedDatosObjetoCotizacion() {
		return selectedDatosObjetoCotizacion;
	}

	public void setSelectedDatosObjetoCotizacion(ConsultaPolizaView selectedDatosObjetoCotizacion) {
		this.selectedDatosObjetoCotizacion = selectedDatosObjetoCotizacion;
	}

	public List<ConsultaObjetoPolView> getLstObjetosPoliza() {
		return lstObjetosPoliza;
	}

	public void setLstObjetosPoliza(List<ConsultaObjetoPolView> lstObjetosPoliza) {
		this.lstObjetosPoliza = lstObjetosPoliza;
	}

	public List<ConsultaSubObjetoPolView> getLstSubObjetoCot() {
		return lstSubObjetoCot;
	}

	public void setLstSubObjetoCot(List<ConsultaSubObjetoPolView> lstSubObjetoCot) {
		this.lstSubObjetoCot = lstSubObjetoCot;
	}

	public List<CaracteristicasVehiculos> getLstCaracteristicasVh() {
		return lstCaracteristicasVh;
	}

	public void setLstCaracteristicasVh(List<CaracteristicasVehiculos> lstCaracteristicasVh) {
		this.lstCaracteristicasVh = lstCaracteristicasVh;
	}

	public List<DetalleFormaPago> getLstDetalleFormaPago() {
		return lstDetalleFormaPago;
	}

	public void setLstDetalleFormaPago(List<DetalleFormaPago> lstDetalleFormaPago) {
		this.lstDetalleFormaPago = lstDetalleFormaPago;
	}

	public ConsultaObjetoPolView getSelectedDatosObjt() {
		return selectedDatosObjt;
	}

	public void setSelectedDatosObjt(ConsultaObjetoPolView selectedDatosObjt) {
		this.selectedDatosObjt = selectedDatosObjt;
	}

	public Date getFcAnexo() {
		return fcAnexo;
	}

	public void setFcAnexo(Date fcAnexo) {
		this.fcAnexo = fcAnexo;
	}

	public ConsultaPolizaView getPolizaSeleccionadaParaAnexo() {
		return PolizaSeleccionadaParaAnexo;
	}

	public void setPolizaSeleccionadaParaAnexo(ConsultaPolizaView polizaSeleccionadaParaAnexo) {
		PolizaSeleccionadaParaAnexo = polizaSeleccionadaParaAnexo;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getFacturaAseguradora() {
		return facturaAseguradora;
	}

	public void setFacturaAseguradora(String facturaAseguradora) {
		this.facturaAseguradora = facturaAseguradora;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

}
