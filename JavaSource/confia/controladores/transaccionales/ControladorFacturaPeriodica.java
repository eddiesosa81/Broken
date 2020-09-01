package confia.controladores.transaccionales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import confia.entidades.basicos.ComisionSubagente;
import confia.entidades.basicos.ComisionSubagentePol;
import confia.entidades.transaccionales.ComisionesPoliza;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.transaccionales.TransaccionesPeriodicas;
import confia.entidades.vistas.ConsultaPolizaAnexoView;
import confia.entidades.vistas.ConsultaPolizaView;
import confia.procedures.servicioProcedures;
import confia.servicios.basicos.ServicioComisionSubagenPol;
import confia.servicios.transaccionales.ServicioComisionesPoliza;
import confia.servicios.transaccionales.ServicioDetalleFormaPago;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.transaccionales.ServicioTransaccionPeriodica;
import confia.servicios.vistas.ServicioConsultaPolizaAnexosView;
import confia.servicios.vistas.ServicioConsultaPolizaView;

@ManagedBean(name = "ControladorFacturaPeriodica")
@ViewScoped
public class ControladorFacturaPeriodica {
	@EJB
	private ServicioConsultaPolizaView srvConsultaPolizaView;
	@EJB
	private ServicioDetalleFormaPago srvDetalleFormaPago;
	@EJB
	private ServicioConsultaPolizaAnexosView srvConsultaAnexosView;
	@EJB
	private ServicioTransaccionPeriodica srvTransaccionPer;
	@EJB
	private ServicioRamoCotizacion srvRamoCot;
	@EJB
	private ServicioComisionesPoliza srvComPol;
	@EJB
	private ServicioComisionSubagenPol srvComSubaPol;
	private servicioProcedures srvStoreProcedure;

	private String codigoCompania;
	private Integer cdRamoCotizacion;
	private String numPolizaAnexo;
	private String clientePolizaAnexo;
	private String ramoPolizaAnexo;
	private String apellidoRazonSocial;
	private List<ConsultaPolizaView> lstConsultaPoliza;
	private ConsultaPolizaView PolizaSeleccionadaParaAnexo;
	private List<DetalleFormaPago> lstDetalleFormaPago;
	private List<ConsultaPolizaAnexoView> lstAnexosPoliza;
	private List<DetalleFormaPago> lstDetalleFormaPagoAnexo;
	private List<ConsultaPolizaAnexoView> lstSelectedAnexosPoliza;
	private List<ConsultaPolizaAnexoView> lstSelectedAnexosPolizaProc;
	private List<ConsultaPolizaAnexoView> lstAnexosPolizaSeleccionada;
	private DetalleFormaPago selectedDetalleFormaPago;
	private DetalleFormaPago detalleFormaPagoSeleccionado;
	private String procesoSelected;
	private List<TransaccionesPeriodicas> lstTransaccionesPer;
	private TransaccionesPeriodicas transaccionesPeriodicas;
	private String numFactDetFrm;

	public ControladorFacturaPeriodica() {
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();
		PolizaSeleccionadaParaAnexo = new ConsultaPolizaView();
		lstDetalleFormaPago = new ArrayList<DetalleFormaPago>();
		lstAnexosPoliza = new ArrayList<ConsultaPolizaAnexoView>();
		lstDetalleFormaPagoAnexo = new ArrayList<DetalleFormaPago>();
		procesoSelected = "APLICARTODOS";
		lstTransaccionesPer = new ArrayList<TransaccionesPeriodicas>();
		transaccionesPeriodicas = new TransaccionesPeriodicas();
		detalleFormaPagoSeleccionado = new DetalleFormaPago();
		lstAnexosPolizaSeleccionada = new ArrayList<ConsultaPolizaAnexoView>();
		srvStoreProcedure = new servicioProcedures();
		lstSelectedAnexosPolizaProc = new ArrayList<ConsultaPolizaAnexoView>();
	}

	public void consultaPoliza() {
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wbuscaFactPerPol').show();");
		PrimeFaces.current().executeScript("PF('wbuscaFactPerPol').show();");
	}

	public void buscaPoliza() {
		try {
			apellidoRazonSocial = apellidoRazonSocial.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			return;
		}
		lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXClienteFactPeriodica(apellidoRazonSocial);
	}

	public void seleccionaPol(ConsultaPolizaView pol) {
		// // RECUPERO DATOS DE LA COTIZACION
		System.out.println("cdRamoCotizacionAxu:" + pol.getCd_ramo_cotizacion());
		PolizaSeleccionadaParaAnexo = new ConsultaPolizaView();
		lstDetalleFormaPago = new ArrayList<DetalleFormaPago>();
		lstAnexosPoliza = new ArrayList<ConsultaPolizaAnexoView>();
		PolizaSeleccionadaParaAnexo = srvConsultaPolizaView.consultaPolizaXCdRamCot(pol.getCd_ramo_cotizacion());
		codigoCompania = PolizaSeleccionadaParaAnexo.getCd_compania();
		numPolizaAnexo = PolizaSeleccionadaParaAnexo.getPoliza();
		cdRamoCotizacion = Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
		clientePolizaAnexo = PolizaSeleccionadaParaAnexo.getCliente();
		ramoPolizaAnexo = PolizaSeleccionadaParaAnexo.getDesc_ramo();
		apellidoRazonSocial = null;
		System.out.println("COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_cotizacion());
		lstDetalleFormaPago = srvDetalleFormaPago.recuperaDetalleFrmPago(PolizaSeleccionadaParaAnexo.getCd_cotizacion(),
				PolizaSeleccionadaParaAnexo.getCd_compania());
		lstAnexosPoliza = srvConsultaAnexosView.consultaAnexosXCliente(PolizaSeleccionadaParaAnexo.getCd_cliente(),
				PolizaSeleccionadaParaAnexo.getPoliza(), PolizaSeleccionadaParaAnexo.getCd_ramo());
		System.out.println("ANEXOS:" + lstAnexosPoliza.size());
	}

	public void onRowSelect(SelectEvent event) {
		String codCot, codComp;
		codCot = ((ConsultaPolizaAnexoView) event.getObject()).getCd_cotizacion();
		codComp = ((ConsultaPolizaAnexoView) event.getObject()).getCd_compania();
		lstDetalleFormaPagoAnexo = new ArrayList<DetalleFormaPago>();
		lstDetalleFormaPagoAnexo = srvDetalleFormaPago.recuperaDetalleFrmPago(codCot, codComp);
		ConsultaPolizaAnexoView anexosPolizaSeleccionada = (ConsultaPolizaAnexoView) event.getObject();
		lstAnexosPolizaSeleccionada.add(anexosPolizaSeleccionada);
		System.out.println("tamaño ON: " + lstAnexosPolizaSeleccionada.size());
	}

	public void onRowUnselect(UnselectEvent event) {
		ConsultaPolizaAnexoView anexosPolizaSeleccionada = (ConsultaPolizaAnexoView) event.getObject();
		lstAnexosPolizaSeleccionada.remove(anexosPolizaSeleccionada);
		System.out.println("tamaño: UN " + lstAnexosPolizaSeleccionada.size());
	}

	public void onRowSelectFactMensual(SelectEvent event) {
		lstAnexosPolizaSeleccionada = new ArrayList<ConsultaPolizaAnexoView>();
		detalleFormaPagoSeleccionado = new DetalleFormaPago();
		detalleFormaPagoSeleccionado = (DetalleFormaPago) event.getObject();
		System.out.println("frmPagoSeleccionada:" + detalleFormaPagoSeleccionado.getCD_DET_FORMA_PAGO());
	}

	public void btnAgregaAdicional() {
		transaccionesPeriodicas = new TransaccionesPeriodicas();
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wfactAdcRetro').show();");
		PrimeFaces.current().executeScript("PF('wfactAdcRetro').show();");
	}

	public void btnGuardaAdicional() {
		System.out.println("INGRESAR ADICIONAL");
		if (transaccionesPeriodicas == null) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese toda la información para continuar."));
			return;
		}

		lstTransaccionesPer.add(transaccionesPeriodicas);
		System.out.println("Tamaño lista:"+lstTransaccionesPer.size());
		transaccionesPeriodicas = new TransaccionesPeriodicas();
	}

	public void btnIngresaFact() {
		lstSelectedAnexosPolizaProc = lstSelectedAnexosPoliza;
		System.out.println("lstSelectedAnexosPolizaProc :"+lstSelectedAnexosPolizaProc.size());
		try {
			numFactDetFrm = null;
		} catch (Exception e) {
			numFactDetFrm = null;
		}
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wfactAdcRetroIng').show();");
		PrimeFaces.current().executeScript("PF('wfactAdcRetroIng').show();");
	}

	public void aplicaTransaccion() {
		try {
			if (numFactDetFrm.isEmpty() || numFactDetFrm == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el número de Factura"));
				return;
			}
		} catch (Exception e) {
			System.out.println("entro catch");
		}
		try {
			if (PolizaSeleccionadaParaAnexo == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione una Póliza"));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione una Póliza"));
			return;
		}

		try {
			if (detalleFormaPagoSeleccionado == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el mes de afectación"));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el mes de afectación"));
			return;
		}
//		try {
//			if (!detalleFormaPagoSeleccionado.getFACTURA_ASEGURADORA().isEmpty()
//					|| detalleFormaPagoSeleccionado.getFACTURA_ASEGURADORA() != null) {
//				FacesContext fContextObj = FacesContext.getCurrentInstance();
//				fContextObj.addMessage(null,
//						new FacesMessage("Advertencia", "El mes de afectación se encuentra sin número de Factura"));
//				return;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		System.out.println("procesoSelected:" + procesoSelected);
		Double valorInicial, valorFinal, primaInicial, primaFinal;
		if (procesoSelected.equals("APLICAFACTURA")) {

			valorInicial = detalleFormaPagoSeleccionado.getVALOR();
			valorFinal = 0.0;
			primaInicial = detalleFormaPagoSeleccionado.getPrima_neta_mensual();
			primaFinal = 0.0;
			System.out.println("VALOR INICIAL PRINT:" + valorInicial);
			System.out.println("ORIMA INICIAL PRINT:" + primaInicial);
			// sumo el valor total adicionales
			for (TransaccionesPeriodicas adcAux : lstTransaccionesPer) {
				valorFinal = valorFinal + adcAux.getTotal();
				System.out.println("VALOR FINAL PRINT:" + valorFinal);
				primaFinal = primaFinal + adcAux.getPrima_neta();
				System.out.println("PRIMA FINAL PRINT:"+primaFinal);
			}
			// sumo anexos ingresados
			System.out.println("tamaño seleccion" + lstAnexosPolizaSeleccionada.size());
			if (lstAnexosPolizaSeleccionada.size() > 0) {
				for (ConsultaPolizaAnexoView consultaPolizaAnexoView : lstAnexosPolizaSeleccionada) {
					lstDetalleFormaPagoAnexo = new ArrayList<DetalleFormaPago>();
					lstDetalleFormaPagoAnexo = srvDetalleFormaPago.recuperaDetalleFrmPago(
							consultaPolizaAnexoView.getCd_cotizacion(), consultaPolizaAnexoView.getCd_compania());
					for (DetalleFormaPago anexoAux : lstDetalleFormaPagoAnexo) {
						valorFinal = valorFinal + anexoAux.getVALOR();
						System.out.println("VALOR FINAL PRINT:" + valorFinal);
						primaFinal = primaFinal + anexoAux.getPrima_neta_mensual();
						System.out.println("PRIMA FINAL PRINT:"+primaFinal);
					}
				}
			}
			valorFinal = valorFinal + valorInicial;
			primaFinal = primaFinal + primaInicial;
			System.out.println("VALOR FINAL PRINT:" + valorFinal);
			// actualizo los valores totales en factura mensual de la póliza
			detalleFormaPagoSeleccionado.setFACTURA_ASEGURADORA(numFactDetFrm);
			detalleFormaPagoSeleccionado.setVALOR(valorFinal);
			detalleFormaPagoSeleccionado.setSALDO(valorFinal);
			detalleFormaPagoSeleccionado.setPrima_neta_mensual(primaFinal);

			srvDetalleFormaPago.actualizaDetFormaPago(detalleFormaPagoSeleccionado);
			// ACTUALIZO COMISIÓNES BROKER Y SUBAGENTES
			srvStoreProcedure.act_comision_pol_Asis_med(String.valueOf(detalleFormaPagoSeleccionado.getCD_COMPANIA()),
					String.valueOf(detalleFormaPagoSeleccionado.getCD_DET_FORMA_PAGO()));

			// actualizo detalle de adicionales
			List<TransaccionesPeriodicas> lstTransFinal = new ArrayList<TransaccionesPeriodicas>();
			TransaccionesPeriodicas transFinal = new TransaccionesPeriodicas();
			for (TransaccionesPeriodicas adcAux : lstTransaccionesPer) {
				transFinal = adcAux;
				transFinal.setCd_compania(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
				transFinal.setCd_det_forma_pago(detalleFormaPagoSeleccionado.getCD_DET_FORMA_PAGO());
				transFinal.setCd_det_forma_pago_anexo(0);
				transFinal.setTipo("ADICIONAL/RETROACTIVO");
				transFinal.setFecha_afectacion(new Date());
				transFinal.setTipo_transaccion(procesoSelected);
				lstTransFinal.add(transFinal);
				transFinal = new TransaccionesPeriodicas();
			}
			// actualizo detalle de endosos
			transFinal = new TransaccionesPeriodicas();
			if (lstAnexosPolizaSeleccionada.size() > 0) {
				for (ConsultaPolizaAnexoView consultaPolizaAnexoView : lstAnexosPolizaSeleccionada) {
					lstDetalleFormaPagoAnexo = new ArrayList<DetalleFormaPago>();
					lstDetalleFormaPagoAnexo = srvDetalleFormaPago.recuperaDetalleFrmPago(
							consultaPolizaAnexoView.getCd_cotizacion(), consultaPolizaAnexoView.getCd_compania());
					for (DetalleFormaPago anexoAux : lstDetalleFormaPagoAnexo) {
						transFinal.setCd_compania(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
						System.out.println("P1");
						transFinal.setCd_det_forma_pago(detalleFormaPagoSeleccionado.getCD_DET_FORMA_PAGO());
						System.out.println("P2");
						transFinal.setCd_det_forma_pago_anexo(anexoAux.getCD_DET_FORMA_PAGO());
						System.out.println("P3");
						transFinal.setTipo("ENDOSO - " + consultaPolizaAnexoView.getDesc_rubro());
						System.out.println("P4");
						transFinal.setFecha_afectacion(new Date());
						System.out.println("P5");
						transFinal.setTipo_transaccion(procesoSelected);
						System.out.println("P6");
						transFinal.setPrima_neta(anexoAux.getPrima_neta_mensual());
						System.out.println("P7");
						transFinal.setValor_financiamiento(0.00);
						System.out.println("P8");
						transFinal.setDerecho_emision(anexoAux.getDerechos_emision_mensual());
						System.out.println("P9");
						transFinal.setSeguto_campesino(anexoAux.getSeguro_campesino_mensual());
						System.out.println("P10");
						transFinal.setOtros(0.00);
						System.out.println("P11");
						transFinal.setIva(anexoAux.getIva_mensual());
						System.out.println("P12");
						transFinal.setTotal(anexoAux.getVALOR());
						System.out.println("P13");
						transFinal.setFecha_afectacion(new Date());
						System.out.println("P13");
						transFinal.setTipo_transaccion(procesoSelected);
						System.out.println("P14");
						lstTransFinal.add(transFinal);
						transFinal = new TransaccionesPeriodicas();
					}
				}

			}
			// guardo registros
			for (TransaccionesPeriodicas transaccionesPeriodicas : lstTransFinal) {
				srvTransaccionPer.insertarTransaccionesPeriodicas(transaccionesPeriodicas);
			}
			// ACTUALIZA EL ENDOSO
			if (lstAnexosPolizaSeleccionada.size() > 0) {
				for (ConsultaPolizaAnexoView consultaPolizaAnexoView : lstAnexosPolizaSeleccionada) {
					RamoCotizacion ramAux = new RamoCotizacion();
					ramAux = srvRamoCot.recuperaRamoCotizacionPorCodigo(
							Integer.valueOf(consultaPolizaAnexoView.getCd_ramo_cotizacion()),
							Integer.valueOf(consultaPolizaAnexoView.getCd_compania()));
					ramAux.setFactura_aseguradora("CONSOLIDADO-FACT-PERIODICA");
					srvRamoCot.actualizaRamoCotizacion(ramAux);
				}
			}

		}

		if (procesoSelected.equals("APLICARTODOS")) {
			Integer codDetFrmPagoOri = 0, flgFact = 0;
			codDetFrmPagoOri = detalleFormaPagoSeleccionado.getCD_DET_FORMA_PAGO();
			for (DetalleFormaPago detFrmPagAux : lstDetalleFormaPago) {
				if (detFrmPagAux.getCD_DET_FORMA_PAGO().equals(codDetFrmPagoOri)) {
					flgFact = 1;
				} else {
					flgFact = 0;
				}
				if (detFrmPagAux.getCD_DET_FORMA_PAGO() >= codDetFrmPagoOri && detFrmPagAux.getTipo() != "ADC") {
					System.out.println("INGRESO A LA VARIABLE");
					valorInicial = detFrmPagAux.getVALOR();
					valorFinal = 0.0;
					primaInicial = detFrmPagAux.getPrima_neta_mensual();
					primaFinal = 0.0;
					// sumo el valor total adicionales
					System.out.println("sumo el valor total adicionales");
					for (TransaccionesPeriodicas adcAux : lstTransaccionesPer) {
						valorFinal = valorFinal + adcAux.getTotal();
						primaFinal = primaFinal + adcAux.getPrima_neta();
					}
					System.out.println("sumo el valor total adicionales");
					// sumo anexos ingresados
					System.out.println("valorFinal:"+valorFinal);
					System.out.println("primaFinal:"+primaFinal);

					System.out.println("tamaño seleccion" + lstAnexosPolizaSeleccionada.size());
					if (lstAnexosPolizaSeleccionada.size() > 0) {
						for (ConsultaPolizaAnexoView consultaPolizaAnexoView : lstAnexosPolizaSeleccionada) {
							lstDetalleFormaPagoAnexo = new ArrayList<DetalleFormaPago>();
							lstDetalleFormaPagoAnexo = srvDetalleFormaPago.recuperaDetalleFrmPago(
									consultaPolizaAnexoView.getCd_cotizacion(),
									consultaPolizaAnexoView.getCd_compania());
							for (DetalleFormaPago anexoAux : lstDetalleFormaPagoAnexo) {

								valorFinal = valorFinal + anexoAux.getVALOR();
								primaFinal = primaFinal + anexoAux.getPrima_neta_mensual();
								System.out.println("VALOR FINAL:" + valorFinal);
							}
						}
					}
					valorFinal = valorFinal + valorInicial;
					primaFinal = primaFinal + primaInicial;
					System.out.println("VALOR FINAL:" + valorFinal);

					// actualizo los valores totales en factura mensual de la
					// póliza
					if (flgFact == 1) {
						detFrmPagAux.setFACTURA_ASEGURADORA(numFactDetFrm);
					}
					detFrmPagAux.setVALOR(valorFinal);
					detFrmPagAux.setSALDO(valorFinal);
					detFrmPagAux.setPrima_neta_mensual(primaFinal);
					srvDetalleFormaPago.actualizaDetFormaPago(detFrmPagAux);
					// ACTUALIZO COMISIÓNES BROKER Y SUBAGENTES
					srvStoreProcedure.act_comision_pol_Asis_med(String.valueOf(detFrmPagAux.getCD_COMPANIA()),
							String.valueOf(detFrmPagAux.getCD_DET_FORMA_PAGO()));
					// actualizo detalle de adicionales
					List<TransaccionesPeriodicas> lstTransFinal = new ArrayList<TransaccionesPeriodicas>();
					TransaccionesPeriodicas transFinal = new TransaccionesPeriodicas();
					for (TransaccionesPeriodicas adcAux : lstTransaccionesPer) {
						transFinal = adcAux;
						transFinal.setCd_compania(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
						transFinal.setCd_det_forma_pago(detFrmPagAux.getCD_DET_FORMA_PAGO());
						transFinal.setCd_det_forma_pago_anexo(0);
						transFinal.setTipo("ADICIONAL/RETROACTIVO");
						transFinal.setFecha_afectacion(new Date());
						transFinal.setTipo_transaccion(procesoSelected);
						lstTransFinal.add(transFinal);
						transFinal = new TransaccionesPeriodicas();
					}
					// actualizo detalle de endosos
					transFinal = new TransaccionesPeriodicas();
					if (lstAnexosPolizaSeleccionada.size() > 0) {
						for (ConsultaPolizaAnexoView consultaPolizaAnexoView : lstAnexosPolizaSeleccionada) {
							lstDetalleFormaPagoAnexo = new ArrayList<DetalleFormaPago>();
							lstDetalleFormaPagoAnexo = srvDetalleFormaPago.recuperaDetalleFrmPago(
									consultaPolizaAnexoView.getCd_cotizacion(),
									consultaPolizaAnexoView.getCd_compania());

							for (DetalleFormaPago anexoAux : lstDetalleFormaPagoAnexo) {
								transFinal
										.setCd_compania(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
								transFinal.setCd_det_forma_pago(detFrmPagAux.getCD_DET_FORMA_PAGO());
								transFinal.setCd_det_forma_pago_anexo(anexoAux.getCD_DET_FORMA_PAGO());
								transFinal.setTipo("ENDOSO - " + consultaPolizaAnexoView.getDesc_rubro());
								transFinal.setFecha_afectacion(new Date());
								transFinal.setTipo_transaccion(procesoSelected);
								transFinal.setPrima_neta(anexoAux.getPrima_neta_mensual());
								transFinal.setValor_financiamiento(0.00);
								transFinal.setDerecho_emision(anexoAux.getDerechos_emision_mensual());
								transFinal.setSeguto_campesino(anexoAux.getSeguro_campesino_mensual());
								transFinal.setOtros(0.00);
								transFinal.setIva(anexoAux.getIva_mensual());
								transFinal.setTotal(anexoAux.getVALOR());
								transFinal.setFecha_afectacion(new Date());
								transFinal.setTipo_transaccion(procesoSelected);
								lstTransFinal.add(transFinal);
								transFinal = new TransaccionesPeriodicas();
							}
						}
					}
					// guardo registros
					for (TransaccionesPeriodicas transaccionesPeriodicas : lstTransFinal) {
						srvTransaccionPer.insertarTransaccionesPeriodicas(transaccionesPeriodicas);
					}
				}
			}
			System.out.println("TAMAÑO PENDIENTE:"+lstSelectedAnexosPolizaProc.size());
			// ACTUALIZA EL ENDOSO
			if (lstSelectedAnexosPolizaProc.size() > 0) {
				for (ConsultaPolizaAnexoView consultaPolizaAnexoView : lstSelectedAnexosPolizaProc) {
					RamoCotizacion ramAux = new RamoCotizacion();
					ramAux = srvRamoCot.recuperaRamoCotizacionPorCodigo(
							Integer.valueOf(consultaPolizaAnexoView.getCd_ramo_cotizacion()),
							Integer.valueOf(consultaPolizaAnexoView.getCd_compania()));
					ramAux.setFactura_aseguradora("CONSOLIDADO-FACT-PERIODICA");
					System.out.println("Integer.valueOf(consultaPolizaAnexoView.getCd_ramo_cotizacion())"+Integer.valueOf(consultaPolizaAnexoView.getCd_ramo_cotizacion()));
					System.out.println("Integer.valueOf(consultaPolizaAnexoView.getCd_compania())"+Integer.valueOf(consultaPolizaAnexoView.getCd_compania()));
					srvRamoCot.actualizaRamoCotizacion(ramAux);
				}
			}
		}
		if (procesoSelected.equals("INSERTAFACTURA")) {
			DetalleFormaPago detFrmPagAux = new DetalleFormaPago();
			// actualizo detalle de adicionales
			for (TransaccionesPeriodicas adcAux : lstTransaccionesPer) {
				detFrmPagAux.setCD_COMPANIA(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_compania()));
				detFrmPagAux.setCD_FORMA_PAGO(detalleFormaPagoSeleccionado.getCD_FORMA_PAGO());
				detFrmPagAux.setFECHA_VENCIMIENTO_DATE(new Date());
				detFrmPagAux.setVALOR(adcAux.getTotal());
				detFrmPagAux.setSALDO(adcAux.getTotal());
				detFrmPagAux.setFACTURA_ASEGURADORA(numFactDetFrm);
				detFrmPagAux.setFECHA_INGRESO_FACTURA_DATE(new Date());
				detFrmPagAux.setFLG_PAGO(0);
				detFrmPagAux.setTipo("ADC");
				detFrmPagAux.setFlg_comision("1");
				detFrmPagAux.setPrima_neta_mensual(adcAux.getPrima_neta());
				detFrmPagAux.setSuper_bancos_mensual(adcAux.getSuper_bancos());
				detFrmPagAux.setSeguro_campesino_mensual(adcAux.getSeguto_campesino());
				detFrmPagAux.setDerechos_emision_mensual(adcAux.getDerecho_emision());
				detFrmPagAux.setIva_mensual(adcAux.getIva());
				srvDetalleFormaPago.insertaDetalleFormaPago(detFrmPagAux);
				// Inserto Comision Broker
				DetalleFormaPago detFrmPagInsertada = new DetalleFormaPago();
				ComisionesPoliza comAux = new ComisionesPoliza();
				ComisionesPoliza comAuxOri = new ComisionesPoliza();
				
				detFrmPagInsertada = srvDetalleFormaPago.recuperaDetalleUltimoReg(detFrmPagAux.getCD_FORMA_PAGO(), detFrmPagAux.getCD_COMPANIA());
				comAuxOri = srvComPol.comisionesPolizaAsisMed(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_cotizacion()));
				comAux.setCd_compania(detFrmPagAux.getCD_COMPANIA());
				comAux.setCd_ramo_cotizacion(comAuxOri.getCd_ramo_cotizacion());
				comAux.setTotal_asegurado(0.00);
				comAux.setTotal_prima(detFrmPagAux.getPrima_neta_mensual());
				comAux.setPct_com_brk(comAuxOri.getPct_com_brk());
				comAux.setVal_com_brk(detFrmPagAux.getPrima_neta_mensual() *(comAuxOri.getPct_com_brk()/100));
				comAux.setSaldo_com_brk(detFrmPagAux.getPrima_neta_mensual() *(comAuxOri.getPct_com_brk()/100));
				comAux.setCd_det_forma_pago(detFrmPagInsertada.getCD_DET_FORMA_PAGO());
				srvComPol.insertarComisionesPoliza(comAux);
				comAux = srvComPol.comisionesPolizaxCdDetFrmPag(detFrmPagInsertada.getCD_DET_FORMA_PAGO());
				// Inserto Comision Subagente
				ComisionSubagentePol comSubaAux = new ComisionSubagentePol();
				ComisionSubagentePol comSubaAuxOri = new ComisionSubagentePol();
				comSubaAuxOri = srvComSubaPol.consultaSubagentePolAsisMed(detFrmPagInsertada.getCD_FORMA_PAGO());
				comSubaAux.setCd_compania(comAux.getCd_compania());
				comSubaAux.setCd_ramo_cotizacion(comAux.getCd_ramo_cotizacion());
				comSubaAux.setTotal_prima(comAux.getTotal_prima());
				comSubaAux.setPct_com_suba(comSubaAuxOri.getPct_com_suba());
				comSubaAux.setVal_com_suba(comAux.getTotal_prima()*(comSubaAuxOri.getPct_com_suba()/100));
				comSubaAux.setSaldo_com_suba(comAux.getTotal_prima()*(comSubaAuxOri.getPct_com_suba()/100));
				comSubaAux.setCd_comision_poliza(comAux.getCd_comision_poliza());
				srvComSubaPol.insertarComisionSubagentePol(comSubaAux);
				detFrmPagAux = new DetalleFormaPago();
			}
			
			

		}

//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wfactAdcRetroIng').hide();");
		PrimeFaces.current().executeScript("PF('wfactAdcRetroIng').hide();");
		salir();
	}

	public void salir() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void btnEliminarAdicional() {
		lstTransaccionesPer = new ArrayList<TransaccionesPeriodicas>();
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

	public List<ConsultaPolizaView> getLstConsultaPoliza() {
		return lstConsultaPoliza;
	}

	public void setLstConsultaPoliza(List<ConsultaPolizaView> lstConsultaPoliza) {
		this.lstConsultaPoliza = lstConsultaPoliza;
	}

	public List<DetalleFormaPago> getLstDetalleFormaPago() {
		return lstDetalleFormaPago;
	}

	public void setLstDetalleFormaPago(List<DetalleFormaPago> lstDetalleFormaPago) {
		this.lstDetalleFormaPago = lstDetalleFormaPago;
	}

	public List<ConsultaPolizaAnexoView> getLstAnexosPoliza() {
		return lstAnexosPoliza;
	}

	public void setLstAnexosPoliza(List<ConsultaPolizaAnexoView> lstAnexosPoliza) {
		this.lstAnexosPoliza = lstAnexosPoliza;
	}

	public List<DetalleFormaPago> getLstDetalleFormaPagoAnexo() {
		return lstDetalleFormaPagoAnexo;
	}

	public void setLstDetalleFormaPagoAnexo(List<DetalleFormaPago> lstDetalleFormaPagoAnexo) {
		this.lstDetalleFormaPagoAnexo = lstDetalleFormaPagoAnexo;
	}

	public List<ConsultaPolizaAnexoView> getLstSelectedAnexosPoliza() {
		return lstSelectedAnexosPoliza;
	}

	public void setLstSelectedAnexosPoliza(List<ConsultaPolizaAnexoView> lstSelectedAnexosPoliza) {
		this.lstSelectedAnexosPoliza = lstSelectedAnexosPoliza;
	}

	public DetalleFormaPago getSelectedDetalleFormaPago() {
		return selectedDetalleFormaPago;
	}

	public void setSelectedDetalleFormaPago(DetalleFormaPago selectedDetalleFormaPago) {
		this.selectedDetalleFormaPago = selectedDetalleFormaPago;
	}

	public String getProcesoSelected() {
		return procesoSelected;
	}

	public void setProcesoSelected(String procesoSelected) {
		this.procesoSelected = procesoSelected;
	}

	public List<TransaccionesPeriodicas> getLstTransaccionesPer() {
		return lstTransaccionesPer;
	}

	public void setLstTransaccionesPer(List<TransaccionesPeriodicas> lstTransaccionesPer) {
		this.lstTransaccionesPer = lstTransaccionesPer;
	}

	public TransaccionesPeriodicas getTransaccionesPeriodicas() {
		return transaccionesPeriodicas;
	}

	public void setTransaccionesPeriodicas(TransaccionesPeriodicas transaccionesPeriodicas) {
		this.transaccionesPeriodicas = transaccionesPeriodicas;
	}

	public String getNumFactDetFrm() {
		return numFactDetFrm;
	}

	public void setNumFactDetFrm(String numFactDetFrm) {
		this.numFactDetFrm = numFactDetFrm;
	}

}
