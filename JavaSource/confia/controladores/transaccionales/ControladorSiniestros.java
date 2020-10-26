package confia.controladores.transaccionales;

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

import org.apache.batik.svggen.ImageHandlerPNGEncoder;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.Canton;
import confia.entidades.basicos.Ciudad;
import confia.entidades.basicos.CoberturasAdicionales;
import confia.entidades.basicos.CoberturasEmitidas;
import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.DeduciblesEmitidas;
import confia.entidades.basicos.Diagnostico;
import confia.entidades.basicos.Ejecutivos;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Parroquia;
import confia.entidades.basicos.Plan;
import confia.entidades.basicos.Provincias;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.entidades.transaccionales.CoberturasSiniestro;
import confia.entidades.transaccionales.ComisionesPoliza;
import confia.entidades.transaccionales.Correspondencia;
import confia.entidades.transaccionales.DetalleSiniestros;
import confia.entidades.transaccionales.DiagnosticoReclamo;
import confia.entidades.transaccionales.DocumentoSiniestro;
import confia.entidades.transaccionales.PagoSiniestro;
import confia.entidades.transaccionales.ProformaSiniestro;
import confia.entidades.transaccionales.Reservas;
import confia.entidades.transaccionales.Siniestros;
import confia.entidades.vistas.ConsultaCaractPolView;
import confia.entidades.vistas.ConsultaObjetoPolView;
import confia.entidades.vistas.ConsultaPagoPolView;
import confia.entidades.vistas.ConsultaPolizaView;
import confia.entidades.vistas.ConsultaSubObjetoPolView;
import confia.entidades.vistas.ConsultaUbicacionPolView;
import confia.procedures.ProcedimientosAlmacenadosDB;
import confia.procedures.servicioProcedures;
import confia.reportes.EmailSenderService;
import confia.servicios.basicos.ServicioCanton;
import confia.servicios.basicos.ServicioCiudad;
import confia.servicios.basicos.ServicioCoberturasAdicionales;
import confia.servicios.basicos.ServicioCoberturasEmitidas;
import confia.servicios.basicos.ServicioContacto;
import confia.servicios.basicos.ServicioDiagnostico;
import confia.servicios.basicos.ServicioEjecutivos;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioParroquia;
import confia.servicios.basicos.ServicioPlan;
import confia.servicios.basicos.ServicioProvincias;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.basicos.ServiciosDeduciblesEmitidas;
import confia.servicios.transaccionales.ServicioCaracteristicasVehiculos;
import confia.servicios.transaccionales.ServicioCoberturasSiniestro;
import confia.servicios.transaccionales.ServicioComisionesPoliza;
import confia.servicios.transaccionales.ServicioCorrespondencia;
import confia.servicios.transaccionales.ServicioDetalleSiniestro;
import confia.servicios.transaccionales.ServicioDiagnosticoReclamo;
import confia.servicios.transaccionales.ServicioDocumentoSiniestro;
import confia.servicios.transaccionales.ServicioPagoSiniestro;
import confia.servicios.transaccionales.ServicioProformaSiniestro;
import confia.servicios.transaccionales.ServicioSiniestros;
import confia.servicios.transaccionales.ServiciosReservas;
import confia.servicios.vistas.ServicioConsultaCaractPolView;
import confia.servicios.vistas.ServicioConsultaObjetoPolView;
import confia.servicios.vistas.ServicioConsultaPagoPolView;
import confia.servicios.vistas.ServicioConsultaPolizaView;
import confia.servicios.vistas.ServicioConsultaSubObjetoPolView;
import confia.servicios.vistas.ServicioConsultaUbicacionPolView;

@ManagedBean(name = "ControladorSiniestros")
@ViewScoped
public class ControladorSiniestros {
	@EJB
	private ServicioUsuarios srvUsuario;
	@EJB
	private ServicioConsultaPolizaView srvConsultaPolizaView;
	@EJB
	private ServicioConsultaUbicacionPolView srvConsultaUbcPolView;
	@EJB
	private ServicioConsultaObjetoPolView srvConsultaObjetoPolView;
	@EJB
	private ServicioConsultaSubObjetoPolView srvConsultaSubObjetoPolView;
	@EJB
	private ServicioCaracteristicasVehiculos srvCaracteristicaObj;
	@EJB
	private ServicioConsultaCaractPolView srvConsCaractPolView;
	@EJB
	private ServicioConsultaPagoPolView srvConsPagoPolView;
	@EJB
	private ServicioSiniestros srvSiniestros;
	@EJB
	private ServicioDetalleSiniestro srvDetalleSiniestros;
	@EJB
	private ServicioCoberturasEmitidas srvCoberturasPlan;
	@EJB
	private ServiciosDeduciblesEmitidas srvDeduciblesPlan;
	@EJB
	private ServicioCoberturasSiniestro srvCoberturasSiniestro;
	@EJB
	private ServicioCiudad srvCiudad;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioDocumentoSiniestro srvDocumentosSiniestro;
	@EJB
	private ServiciosReservas srvReservas;
	@EJB
	private ServicioProformaSiniestro srvProformaSiniestro;
	@EJB
	private ServicioPagoSiniestro srvPagoSiniestro;
	@EJB
	private ServicioComisionesPoliza srvComisionPoliza;
	private ProcedimientosAlmacenadosDB spDataBAse;
	private servicioProcedures spServicioProcedure;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioRubros srvRubrosCartas;
	@EJB
	private ServicioContacto srvContactosCarta;
	@EJB
	private ServicioCorrespondencia srvCorrespondencia;
	@EJB
	private ServicioDiagnostico srvDiagnostico;
	@EJB
	private ServicioDiagnosticoReclamo srvDiagnosticoReclamo;
	@EJB
	private ServicioPlan srvPlan;
	@EJB
	private ServicioCoberturasAdicionales srvCoberturasAdc;
	@EJB
	private ServicioParroquia srvParroquia;
	@EJB
	private ServicioProvincias srvProvincias;
	@EJB
	private ServicioCanton srvCanton;
	@EJB
	private ServicioEjecutivos srvEjecutivos;
	@EJB
	private ServicioRamo srvRamo;

	private String numSinies;
	private String polizaSinies;
	private String poliza;
	private String nmCliente;
	private String placa;
	private String nmTitular;
	private String motor;
	private String nmClienteSinies;
	private String placaSinies;
	private String motorSinies;
	private List<ConsultaPolizaView> lstConsultaPoliza;
	private ConsultaPolizaView selectedConsultaPoliza;
	private List<ConsultaUbicacionPolView> lstUbicacionPoliza;
	private ConsultaUbicacionPolView selectedUbicacionPoliza;
	private List<ConsultaObjetoPolView> filteredLstObjetosPoliza;
	private List<ConsultaObjetoPolView> lstObjetosPoliza;
	private List<ConsultaSubObjetoPolView> lstSubObjetosPoliza;
	private ConsultaSubObjetoPolView selectedSubObjetosPoliza;
	private ConsultaObjetoPolView selectedObjetosPoliza;
	private List<CaracteristicasVehiculos> LstCaracteristicas;
	private List<CaracteristicasVehiculos> LstCaracteristicasCab;
	private List<ConsultaPagoPolView> lstPagoPoliza;
	private List<CoberturasEmitidas> lstCoberturasPlan;
	private List<CoberturasAdicionales> lstCoberturasAdcPlan;
	private CoberturasAdicionales selectedCoberturasAdcPlan;
	private CoberturasEmitidas selectedCoberturasPlan;
	private List<DeduciblesEmitidas> lstDeduciblesPlan;
	private DeduciblesEmitidas selectedDeduciblesPlan;
	private List<CoberturasSiniestro> lstCoberturasSiniestro;
	private CoberturasSiniestro selectedCoberturasSiniestro;
	private String cdCiudad;
	private List<Ciudad> lstCiudad;

	private String cdProvincia;
	private String cdCanton;
	private String cdParroquia;
	private List<Provincias> lstProvincia;
	private List<Canton> lstCanton;
	private List<Parroquia> lstParroquias;

	private List<Rubros> lsRubrosDocumentos;
	private List<Rubros> selectedLstRubDoc;
	private List<Rubros> filteredRubrosDocumentos;
	private List<Rubros> lsRubrosTalleres;
	private Rubros rubrosTalleres;
	private String codRubroTaller;
	private String formaPago;
	private String estado;
	private Boolean finiq;
	private Boolean panelCaracteristica;

	private Siniestros siniestro;
	private Siniestros selectedSiniestro;
	private Siniestros selectedSiniestroClose;
	private List<Siniestros> lstSiniestro;
	private List<Siniestros> lstSiniestroCerrado;
	private DetalleSiniestros detalleSiniestros;
	private DocumentoSiniestro documentosSiniestro;
	private List<DocumentoSiniestro> lstDocumentosSiniestro;
	private DocumentoSiniestro selectedDocumentoSiniestro;
	private List<ProformaSiniestro> lstProformaSinies;
	private ProformaSiniestro proformaSinies;
	private ProformaSiniestro selectedProformaSinies;
	private Reservas reservas;
	private Reservas selectedReservas;
	private List<Reservas> lstReservas;
	private PagoSiniestro pagoSiniesttro;
	private List<Diagnostico> lstDiagnostico;
	private Diagnostico selectedDiagnostico;
	private String diagnosticoSlm;

	private Double valorReclamo;
	private String docAdcSiniestro;
	private String nmClienteSiniesClose;
	private String numSiniesClos;
	private String passwd;
	private boolean flgEdita;
	private String codGrupoContratante;
	private List<GrupoContratante> listaGrupoContratante;
	private GrupoContratante grupoContratante;
	private Plan plan;

	private List<DiagnosticoReclamo> lstDiagnosticoReclamo;
	private DiagnosticoReclamo selectedDiagnosticoReclamo;

	// CARTAS
	private List<Rubros> lstRubrosCarta;
	private List<Contacto> lstContactoCarta;
	private String nmCarta;
	private Contacto selectedContactoCarta;
	private String notasAdicionalesCarta;
	private Rubros objCarta;
	private Date fcEstadoSiniestro;

	// fechas documentos
	private String selectedTipoFechaString;
	private Date fechaSelectDate;

	// Califica Siniestro
	private String calificaSin;
	private String obsSinies;

	// envio correo
	private EmailSenderService email;

	public ControladorSiniestros() {
		fcEstadoSiniestro = new Date();
		lstDiagnosticoReclamo = new ArrayList<DiagnosticoReclamo>();
		lstRubrosCarta = new ArrayList<Rubros>();
		lstContactoCarta = new ArrayList<Contacto>();
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();
		lstUbicacionPoliza = new ArrayList<ConsultaUbicacionPolView>();
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		siniestro = new Siniestros();
		lstCoberturasPlan = new ArrayList<CoberturasEmitidas>();
		lstDeduciblesPlan = new ArrayList<DeduciblesEmitidas>();
		lstCoberturasSiniestro = new ArrayList<CoberturasSiniestro>();
		lstCiudad = new ArrayList<Ciudad>();
		lsRubrosDocumentos = new ArrayList<Rubros>();
		detalleSiniestros = new DetalleSiniestros();
		documentosSiniestro = new DocumentoSiniestro();
		lsRubrosTalleres = new ArrayList<Rubros>();
		rubrosTalleres = new Rubros();
		reservas = new Reservas();
		lstProformaSinies = new ArrayList<ProformaSiniestro>();
		proformaSinies = new ProformaSiniestro();
		pagoSiniesttro = new PagoSiniestro();
		finiq = false;
		lstReservas = new ArrayList<Reservas>();
		spDataBAse = new ProcedimientosAlmacenadosDB();
		lstSiniestro = new ArrayList<Siniestros>();
		listaGrupoContratante = new ArrayList<GrupoContratante>();
		lstSubObjetosPoliza = new ArrayList<ConsultaSubObjetoPolView>();
		spServicioProcedure = new servicioProcedures();
		lstDiagnostico = new ArrayList<Diagnostico>();
		LstCaracteristicasCab = new ArrayList<CaracteristicasVehiculos>();
		panelCaracteristica = true;
		lstCoberturasAdcPlan = new ArrayList<CoberturasAdicionales>();
		email = new EmailSenderService();

	}

	@PostConstruct
	public void recuperaInicio() {
		fechaSelectDate = new Date();
		lstCiudad = srvCiudad.recuperaListaCiudad();
		docAdcSiniestro = null;
		listaGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		lstRubrosCarta = srvRubrosCartas.listadoCartasPorModulo("MODULO_SINIESTROS");
		selectedSubObjetosPoliza = new ConsultaSubObjetoPolView();
		lstDiagnostico = srvDiagnostico.consultaDiagnostico();
		diagnosticoSlm = "0";
		lstProvincia = srvProvincias.listaProvincias();
		cdCanton = "0";
		cdProvincia = "0";
		cdParroquia = "0";
	}

	public void buscaPoliza() {
		System.out.println("INGRESO");
		String grpCont = null;
		ConsultaCaractPolView consCaracPol = new ConsultaCaractPolView();
		try {
			poliza = poliza.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			poliza = "%";
		}
		try {
			nmCliente = nmCliente.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			nmCliente = "%";
		}
		try {
			placa = placa.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			placa = "%";
		}
		try {
			motor = motor.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			motor = "%";
		}
		try {
			nmTitular = nmTitular.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			nmTitular = "%";
		}
		if (codGrupoContratante.equals("0")) {
			grpCont = "%";
		} else {
			grpCont = codGrupoContratante;
		}

		System.out.println("nombre Cliente:" + nmCliente);
		System.out.println("placa:" + placa);
		System.out.println("motor:" + motor);
		System.out.println("grupo contratante:" + grpCont);
		System.out.println("Titular: " + nmTitular);
		if (placa.equals("%") && motor.equals("%") && nmTitular.equals("%")) {
			lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXClienteGrpCont(nmCliente, grpCont, poliza);
		} else {
			if (!placa.equals("%")) {
				consCaracPol = srvConsCaractPolView.consultaCaracteristicaObjPol(placa.trim().toUpperCase(), "%");
				lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXRamoCot(consCaracPol.getCd_ramo_cotizacion());
			}
			if (!motor.equals("%")) {
				consCaracPol = srvConsCaractPolView.consultaCaracteristicaObjPol("%", motor.trim().toUpperCase());
				lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXRamoCot(consCaracPol.getCd_ramo_cotizacion());
			}
			if (!nmTitular.equals("%")) {
				lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXDescObj(nmTitular);
			}
		}
	}

	public void onRowSelectPol(SelectEvent event) {
		lstUbicacionPoliza = new ArrayList<ConsultaUbicacionPolView>();
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		String codRamCotAux;
		codRamCotAux = ((ConsultaPolizaView) event.getObject()).getCd_ramo_cotizacion();
		lstUbicacionPoliza = srvConsultaUbcPolView.consultaUbicacionxCrc(codRamCotAux);
	}

	public void onRowSelectUbicacion(SelectEvent event) {
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		String codUbcAux;
		codUbcAux = ((ConsultaUbicacionPolView) event.getObject()).getCd_ubicacion();
		lstObjetosPoliza = srvConsultaObjetoPolView.consultaObjetosXUbc(codUbcAux);
	}

	public void onRowSelectObjeto(SelectEvent event) {
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		String codObjAux, codComp;
		codObjAux = ((ConsultaObjetoPolView) event.getObject()).getCd_obj_cotizacion();
		codComp = ((ConsultaObjetoPolView) event.getObject()).getCd_compania();
		LstCaracteristicas = srvCaracteristicaObj.recuperaCaractVHporObjCot(Integer.valueOf(codObjAux),
				Integer.valueOf(codComp));
		lstSubObjetosPoliza = new ArrayList<ConsultaSubObjetoPolView>();
		lstSubObjetosPoliza = srvConsultaSubObjetoPolView.consultaSubObjetoxCdObj(codObjAux);
	}

	public void onRowSelectSubObjeto(SelectEvent event) {
		selectedSubObjetosPoliza = new ConsultaSubObjetoPolView();
		selectedSubObjetosPoliza = (ConsultaSubObjetoPolView) event.getObject();
		System.out.println("POLIZA SELECCIONADA: " + selectedSubObjetosPoliza.getCd_obj_cotizacion());
	}

	public void onRowSelectPago() {
		lstPagoPoliza = new ArrayList<ConsultaPagoPolView>();
		String codCotAux;
		try {
			codCotAux = selectedConsultaPoliza.getCd_cotizacion();
		} catch (Exception e) {
			return;
		}
		lstPagoPoliza = srvConsPagoPolView.consultaPagoPolXCdCot(codCotAux);
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('dlgPagoPol').show()");
		PrimeFaces.current().executeScript("PF('dlgPagoPol').show()");
	}

	public void aceptaNuevoSiniestro() {
		int res = 0;
		siniestro = new Siniestros();
		siniestro.setCdAseguradora(Integer.valueOf(selectedConsultaPoliza.getCd_aseguradora()));
		siniestro.setNm_aseguradora(selectedConsultaPoliza.getNombre_corto_aseguradora());
		siniestro.setCdRamo(Integer.valueOf(selectedConsultaPoliza.getCd_ramo()));
		siniestro.setCdCompania(Integer.valueOf(selectedConsultaPoliza.getCd_compania()));
		siniestro.setCdRamoCotizacion(Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
		siniestro.setNm_Ramo(selectedConsultaPoliza.getDesc_ramo());
		siniestro.setCdCliente(Integer.valueOf(selectedConsultaPoliza.getCd_cliente()));
		siniestro.setNm_cliente(selectedConsultaPoliza.getCliente());
		siniestro.setPoliza(selectedConsultaPoliza.getPoliza());
		siniestro.setEstado("NUEVO");
		siniestro.setBloqueo(0);
		siniestro.setNumeroReporteAseg("0");
		siniestro.setNumeroSiniestroAseg("0");
		siniestro.setCausa("S/D");
		siniestro.setReferencias("S/D");
		siniestro.setFc_vig_pol_desde(selectedConsultaPoliza.getFc_ini_vig_date());
		siniestro.setFc_vig_pol_hasta(selectedConsultaPoliza.getFc_fin_vig_date());
		siniestro.setIdentificacion_cliente(selectedConsultaPoliza.getIdentificacion_cliente());
		siniestro.setCd_subagente(Integer.valueOf(selectedConsultaPoliza.getCdSubagente()));
		siniestro.setNm_subagente(selectedConsultaPoliza.getNmSubagente());
		res = srvSiniestros.insertarSiniestros(siniestro);
		if (res == 1) {
			res = srvSiniestros.codigoMaxSiniestro();
			siniestro = new Siniestros();
			siniestro = srvSiniestros.recuperaCodSiniestros(res);
			grupoContratante = new GrupoContratante();
			grupoContratante = srvGrupoContratante.buscaGruposContratanteCrC(siniestro.getCdRamoCotizacion());
			plan = new Plan();
			// verirfico si el ramo es
			// Ubicación o RamoCotizacion
			Integer tpRam = srvRamo.tipoRamo(siniestro.getCdRamo());
			
			if (tpRam.equals(1)) {
				try {
					plan = srvPlan.consultaPlanUbicacion(Integer.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()));
					if(plan == null) {
						plan = srvPlan.consultaPlanRamoCotizacion(siniestro.getCdRamoCotizacion());
					}
				} catch (Exception e) {
					plan = srvPlan.consultaPlanRamoCotizacion(siniestro.getCdRamoCotizacion());
				}
				
			} else {
				plan = srvPlan.consultaPlanRamoCotizacion(siniestro.getCdRamoCotizacion());
			}
			
			Double valAsegSubobj, primaNetaSubObj;
			String descSubobj, cdSubObje;

			try {
				cdSubObje = selectedSubObjetosPoliza.getCd_subobj_cotizacion();
				descSubobj = selectedSubObjetosPoliza.getDescripcion_subobjeto();
				valAsegSubobj = Double.valueOf(selectedSubObjetosPoliza.getValor_asegurador_subobjeto());
				primaNetaSubObj = Double.valueOf(selectedSubObjetosPoliza.getPrima_subobjeto());
			} catch (Exception e) {
				// NO EXISTE SUBOJETO
				cdSubObje = "0";
				descSubobj = "";
				valAsegSubobj = 0.0;
				primaNetaSubObj = 0.0;
			}
			// ingresamos el detalle del siniestros
			detalleSiniestros = new DetalleSiniestros();
			detalleSiniestros.setCd_compania(siniestro.getCdCompania());
			detalleSiniestros.setCd_siniestro(siniestro.getCdSiniestro());
			detalleSiniestros.setCd_obj_cotizacion(Integer.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()));
			detalleSiniestros.setDsc_objeto(selectedObjetosPoliza.getDescripcion_objeto());
			detalleSiniestros.setVal_asegurado(Double.valueOf(selectedObjetosPoliza.getTotal_asegurado_objeto()));
			detalleSiniestros.setPrima_neta_obj(Double.valueOf(selectedObjetosPoliza.getPrima_objeto()));
			detalleSiniestros.setDeducibleMinimo(Double.valueOf(selectedObjetosPoliza.getDeducible_Minimo()));
			detalleSiniestros.setValor_asegurador_subobjeto(valAsegSubobj);
			detalleSiniestros.setDescripcion_subobjeto(descSubobj);
			detalleSiniestros.setCd_subobj_cotizacion(Integer.valueOf(cdSubObje));
			detalleSiniestros.setPrima_neta_sub_obj(primaNetaSubObj);
			srvDetalleSiniestros.insertarDetalleSiniestros(detalleSiniestros);
			detalleSiniestros = new DetalleSiniestros();
			detalleSiniestros = srvDetalleSiniestros.recuperaDetSiniestrosxCdSini(siniestro.getCdSiniestro(),
					siniestro.getCdCompania());

			LstCaracteristicasCab = new ArrayList<CaracteristicasVehiculos>();
			LstCaracteristicasCab = srvCaracteristicaObj.recuperaCaractVHporObjCot(
					detalleSiniestros.getCd_obj_cotizacion(), detalleSiniestros.getCd_compania());
			if (LstCaracteristicasCab.size() == 0) {
				panelCaracteristica = true;
			}
			// Envio correo al ejecutivo comercial
			Ejecutivos ejecutivos = new Ejecutivos();
			ejecutivos = srvEjecutivos.ejecutivoIdRamCot(siniestro.getCdRamoCotizacion(), siniestro.getCdCompania());
			email.setReceptor(ejecutivos.getCorreo());
			email.setSubject("Sistema Confia - Aviso de Siniestro - " + siniestro.getCdSiniestro());
			email.setTexto("<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Estimad@ "
					+ ejecutivos.getPrimer_nombre_ejecutivo() + " " + ejecutivos.getPrimer_apellido_ejecutivo()
					+ "<o:p></o:p></span></p>"
					+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Por medio del presente se informa que el cliente "
					+ siniestro.getNm_cliente() + " tuvo un siniestro con fecha " + siniestro.getFcCreacion()
					+ ", asignado el caso número " + siniestro.getCdSiniestro()
					+ ", además se informa que se realiza el debido "
					+ "seguimiento con el cliente para la continuidad de su proceso." + " <o:p></o:p></span></p>"

					+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>En caso de necesitar su apoyo estaremos comentándole de inmediato.<o:p></o:p></span></p>"

					+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'> Particular que pongo en su conocimiento. <o:p></o:p></span></p>"
					+ "<p>Atentamente</p> " + "<p></p> " + "<p>Departamento de Siniestros</p> "

					+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'> " + " <o:p></o:p></span></p>"
					+ "<p><strong>Nota: </strong>"
					+ "Este mensaje ha sido generado automáticamente, por favor no lo responda." + "</p> ");
			email.sendEmail();

			// cierra la pantalla de busqueda
			PrimeFaces.current().executeScript("PF('wbuscaPoliza').hide();");
			// abre la pantalla para el ingreso del siniestros
			PrimeFaces.current().executeScript("PF('dlgSiniestro').show();");
		} else {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Error",
					"No se creó el Siniestros. Comuníquese con el Administrador del Sistema"));
			return;

		}
		recuperaDatosSiniestro();
	}

	public void recuperaDatosSiniestro() {
		// recupera datos coberturas, deducibles, siniestros
		lstCoberturasPlan = new ArrayList<CoberturasEmitidas>();
		lstDeduciblesPlan = new ArrayList<DeduciblesEmitidas>();
		lstCoberturasAdcPlan = new ArrayList<CoberturasAdicionales>();
		System.out.println("Recupera PLAN:" + selectedConsultaPoliza.getCd_plan());
		if (selectedConsultaPoliza.getCd_plan().equals("0")) {
			lstCoberturasPlan = srvCoberturasPlan.recuperaCoberturasEmitidas(
					Integer.valueOf(selectedConsultaPoliza.getCd_compania()),
					Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
			lstDeduciblesPlan = srvDeduciblesPlan.recuperaDeduciblesEmitidas(
					Integer.valueOf(selectedConsultaPoliza.getCd_compania()),
					Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
			lstCoberturasAdcPlan = srvCoberturasAdc.recuperaCoberturasAdcSiniestros(detalleSiniestros.getCd_compania(),
					detalleSiniestros.getCd_obj_cotizacion());
		} else {
			lstCoberturasPlan = srvCoberturasPlan.recuperaCoberturasEmitidas(
					Integer.valueOf(selectedConsultaPoliza.getCd_compania()),
					Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));

			lstDeduciblesPlan = srvDeduciblesPlan.recuperaDeduciblesEmitidas(
					Integer.valueOf(selectedConsultaPoliza.getCd_compania()),
					Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
			lstCoberturasAdcPlan = srvCoberturasAdc.recuperaCoberturasAdcSiniestros(detalleSiniestros.getCd_compania(),
					detalleSiniestros.getCd_obj_cotizacion());
		}

		lstCoberturasSiniestro = new ArrayList<CoberturasSiniestro>();
		lstCoberturasSiniestro = srvCoberturasSiniestro
				.consultaCoberturasSiniestro(String.valueOf(siniestro.getCdSiniestro()));
		lsRubrosDocumentos = new ArrayList<Rubros>();
		lsRubrosDocumentos = srvRubros.listadoDocumentosSiniestro("103", String.valueOf(siniestro.getCdRamo()));
		lstDocumentosSiniestro = new ArrayList<DocumentoSiniestro>();
		lstDocumentosSiniestro = srvDocumentosSiniestro
				.consultaDocumentosSiniestro(String.valueOf(siniestro.getCdSiniestro()));
		lsRubrosTalleres = new ArrayList<Rubros>();
		lsRubrosTalleres = srvRubros.listadoDocumentosSiniestro("104", String.valueOf(siniestro.getCdRamo()));
		lstProformaSinies = new ArrayList<ProformaSiniestro>();
		lstProformaSinies = srvProformaSiniestro.listadoProformaSiniestro(String.valueOf(siniestro.getCdRamo()));
		lstReservas = new ArrayList<Reservas>();
		lstReservas = srvReservas.recuperaReservasSinies(siniestro.getCdSiniestro(), siniestro.getCdSiniestro());
	}

	public void guardaDatosSiniestro() {
		String aux = null;
		// VERIFICA QUE LA FECHA DE OCURRENCIA SE ENCUENTRE DENTRO DE LA
		// VIGENCIA DE LA POLIZA
		Integer fcIni, fcFin, fcOcurrencia;

		String asFechaDesde;
		String asFechaHasta;
		String asFechaOcurrencia;
		String asFechaRecepcion;
		String asFechaAutorizacion;
		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";

		formato = new SimpleDateFormat(patron);

		try {
			asFechaRecepcion = formato.format(siniestro.getFechaRecepcion());
			System.out.println("FC_RECEPCION:" + asFechaRecepcion);
			asFechaDesde = formato.format(siniestro.getFc_vig_pol_desde());
			asFechaHasta = formato.format(siniestro.getFc_vig_pol_hasta());
			asFechaOcurrencia = formato.format(siniestro.getFcSiniestro());
			asFechaAutorizacion = formato.format(siniestro.getFechaAutorizacion());
			System.out.println("Fecha Autorizacion:" + asFechaAutorizacion);

			System.out.println("FC_INI:" + asFechaDesde);
			System.out.println("FC_FIN:" + asFechaHasta);
			System.out.println("FC_OCURRENCIA:" + asFechaOcurrencia);

			fcIni = spServicioProcedure.fechaJuliana(asFechaDesde);
			fcFin = spServicioProcedure.fechaJuliana(asFechaHasta);
			fcOcurrencia = spServicioProcedure.fechaJuliana(asFechaOcurrencia);

			System.out.println("FC_INI JUL Vigencia:" + fcIni);
			System.out.println("FC_FIN JUL Vigencia:" + fcFin);
			System.out.println("FC_OCURRENCIA JUL:" + fcOcurrencia);
			if (fcOcurrencia < fcIni) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Error",
						"La fecha de ocurrencia no puede ser menor a la fecha de inicio vigencia de la póliza"));
				return;
			}
			if (fcOcurrencia > fcFin) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Error",
						"La fecha de ocurrencia no puede ser mayor a la fecha de fin vigencia de la póliza"));
				return;
			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Error",
					"Ingrese todos los registros del siniestro, en caso de no tener la informaciòn escriba 'SIN DATOS'"));
			return;
		}

		try {
			aux = siniestro.getReferencias();

		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Error", "Ingrese la referencia del siniestro"));
			return;
		}
		siniestro.setReferencias(aux.toUpperCase().trim());
		try {
			aux = siniestro.getCausa();
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Error", "Ingrese la referencia del siniestro"));
			return;
		}
		siniestro.setCausa(aux.toUpperCase().trim());
		try {
//			siniestro.setCd_ciudad(Integer.valueOf(cdCiudad));
//			siniestro.setNm_ciudad(srvCiudad.recuperaCiudad(cdCiudad).getNm_ciudad());
			System.out.println("Provincia:" + cdProvincia);
			System.out.println("Canton:" + cdCanton);
			System.out.println("Parroquia:" + cdParroquia);
			siniestro.setCd_provincia(Integer.valueOf(cdProvincia));
			siniestro.setCd_canton(Integer.valueOf(cdCanton));
			siniestro.setCd_parroquia(Integer.valueOf(cdParroquia));
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Error", "Ingrese la Provincia, Cantón, Parroquia de Ocurrencia del Siniestro"));
			return;
		}
		try {
			srvSiniestros.actualizaSiniestros(siniestro);
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Error",
					"Ingrese todos los registros del siniestro, en caso de no tener la informaciòn escriba 'SIN DATOS'"));
			return;
		}

		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));

	}

	public void agregaCoberturaSiniestro() {
		int res = 0;
		try {
			if (selectedCoberturasPlan == null) {
				return;
			}
		} catch (Exception e) {
			return;
		}

		CoberturasSiniestro aux = new CoberturasSiniestro();
		aux.setCd_cobertura(Integer.valueOf(selectedCoberturasPlan.getCd_cobertura()));
		aux.setCd_compania(Integer.valueOf(siniestro.getCdCompania()));
		aux.setCd_obj_cotizacion(detalleSiniestros.getCd_obj_cotizacion());
		aux.setCd_ramo_cotizacion(siniestro.getCdRamoCotizacion());
		aux.setCd_siniestro(siniestro.getCdSiniestro());
		aux.setNm_cobertura(selectedCoberturasPlan.getDesc_cobertura());
		aux.setPct_v_aseg(0.0);
		aux.setVal_minimo(0.0);
		try {
			aux.setVal_limite(Double.valueOf(selectedCoberturasPlan.getValor_plancobertura()));
		} catch (Exception e) {
			aux.setVal_limite(0.0);
		}
		res = srvCoberturasSiniestro.insertarCoberturasSiniestro(aux);
		if (res == 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));
		}
		lstCoberturasSiniestro = new ArrayList<CoberturasSiniestro>();
		lstCoberturasSiniestro = srvCoberturasSiniestro
				.consultaCoberturasSiniestro(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void agregaCoberturaAdcSiniestro() {
		int res = 0;
		try {
			if (selectedCoberturasAdcPlan == null) {
				return;
			}
		} catch (Exception e) {
			return;
		}

		CoberturasSiniestro aux = new CoberturasSiniestro();
		aux.setCd_cobertura(Integer.valueOf(selectedCoberturasAdcPlan.getCd_cobertura()));
		aux.setCd_compania(Integer.valueOf(siniestro.getCdCompania()));
		aux.setCd_obj_cotizacion(detalleSiniestros.getCd_obj_cotizacion());
		aux.setCd_ramo_cotizacion(siniestro.getCdRamoCotizacion());
		aux.setCd_siniestro(siniestro.getCdSiniestro());
		aux.setNm_cobertura(selectedCoberturasAdcPlan.getDescCobertura());
		aux.setPct_v_aseg(0.0);
		aux.setVal_minimo(0.0);
		try {
			aux.setVal_limite(Double.valueOf(selectedCoberturasAdcPlan.getVal_limite()));
		} catch (Exception e) {
			aux.setVal_limite(0.0);
		}
		res = srvCoberturasSiniestro.insertarCoberturasSiniestro(aux);
		if (res == 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));
		}
		lstCoberturasSiniestro = new ArrayList<CoberturasSiniestro>();
		lstCoberturasSiniestro = srvCoberturasSiniestro
				.consultaCoberturasSiniestro(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void agregaDiagnosticoSiniestr() {

		int res = 0;
		try {
			if (selectedDiagnostico == null) {
				return;
			}
		} catch (Exception e) {
			return;
		}
		DiagnosticoReclamo aux = new DiagnosticoReclamo();
		aux.setCd_diagnostico(selectedDiagnostico.getCd_diagnostico());
		aux.setCd_siniestro(siniestro.getCdSiniestro());
		aux.setDiagnistico(selectedDiagnostico.getDiagnistico());
		aux.setValor_reclamo(valorReclamo);
		res = srvDiagnosticoReclamo.insertarDiagnosticoReclamo(aux);
		if (res == 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));
		}
		lstDiagnosticoReclamo = new ArrayList<DiagnosticoReclamo>();
		lstDiagnosticoReclamo = srvDiagnosticoReclamo
				.consultaDiagnosticoReclamo(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void agregaDeducibleSiniestro() {

		int res = 0;
		try {
			if (selectedDeduciblesPlan == null) {
				return;
			}
		} catch (Exception e) {
			return;
		}

		CoberturasSiniestro aux = new CoberturasSiniestro();
		aux.setCd_deducible(Integer.valueOf(selectedDeduciblesPlan.getCd_deducible()));
		aux.setCd_compania(Integer.valueOf(siniestro.getCdCompania()));
		aux.setCd_obj_cotizacion(detalleSiniestros.getCd_obj_cotizacion());
		aux.setCd_ramo_cotizacion(siniestro.getCdRamoCotizacion());
		aux.setCd_siniestro(siniestro.getCdSiniestro());
		aux.setNm_cobertura(selectedDeduciblesPlan.getDesc_deducible());
		aux.setVal_limite(0.0);
		aux.setPct_v_aseg(0.0);
		aux.setVal_minimo(selectedDeduciblesPlan.getValor_minimo());
		aux.setVal_fijo(selectedDeduciblesPlan.getValor_fijo());
		aux.setPorcentaje_valor_siniestro(selectedDeduciblesPlan.getPorcentaje_valor_siniestro());
		aux.setPorcentaje_valor_asegurado(selectedDeduciblesPlan.getPorcentaje_valor_asegurado());
		aux.setEspecificacionDeducible(selectedDeduciblesPlan.getEspecificacion());
		res = srvCoberturasSiniestro.insertarCoberturasSiniestro(aux);
		if (res == 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));
		}
		lstCoberturasSiniestro = new ArrayList<CoberturasSiniestro>();
		lstCoberturasSiniestro = srvCoberturasSiniestro
				.consultaCoberturasSiniestro(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void eliminaCobDedSiniestro() {

		try {
			if (selectedCoberturasSiniestro == null) {
				return;
			}
		} catch (Exception e) {
			return;
		}
		System.out.println("ELIMINA:" + selectedCoberturasSiniestro.getCd_cob_siniestro());
		srvCoberturasSiniestro.eliminaCoberturasSiniestro(selectedCoberturasSiniestro);
		lstCoberturasSiniestro = new ArrayList<CoberturasSiniestro>();
		lstCoberturasSiniestro = srvCoberturasSiniestro
				.consultaCoberturasSiniestro(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void eliminaDiagnosticoSiniestro() {

		try {
			if (selectedDiagnosticoReclamo == null) {
				return;
			}
		} catch (Exception e) {
			return;
		}
		srvDiagnosticoReclamo.eliminaDiagnosticoReclamo(selectedDiagnosticoReclamo);
		lstDiagnosticoReclamo = new ArrayList<DiagnosticoReclamo>();
		lstDiagnosticoReclamo = srvDiagnosticoReclamo
				.consultaDiagnosticoReclamo(String.valueOf(siniestro.getCdSiniestro()));

	}

	public void agregaDocumentoSinies() {
		int res = 0;
		documentosSiniestro = new DocumentoSiniestro();
		for (Rubros docu : selectedLstRubDoc) {
			documentosSiniestro.setCd_compania(siniestro.getCdCompania());
			documentosSiniestro.setCd_rubro(docu.getCd_rubro());
			documentosSiniestro.setCd_siniestro(siniestro.getCdSiniestro());
			documentosSiniestro.setTp_doc_siniestro(docu.getDesc_rubro());
			res = srvDocumentosSiniestro.insertarDocumentoSiniestro(documentosSiniestro);
			if (res == 0) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Error Documentos Siniesto", "Comuníquese con el Administrador del Sistema"));
				break;
			}
			documentosSiniestro = new DocumentoSiniestro();
		}
		lstDocumentosSiniestro = new ArrayList<DocumentoSiniestro>();
		lstDocumentosSiniestro = srvDocumentosSiniestro
				.consultaDocumentosSiniestro(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void agregaDocAdic() {
		docAdcSiniestro = null;
//		RequestContext rSiniestroDoc = RequestContext.getCurrentInstance();
//		rSiniestroDoc.execute("PF('DocAdcSinies').show();");
		PrimeFaces.current().executeScript("PF('DocAdcSinies').show();");
	}

	public void guardaDocAdc() {
		System.out.println("docAdcSiniestro:"+docAdcSiniestro);
		System.out.println("siniestro.getCdCompania()"+siniestro.getCdCompania());
		System.out.println("siniestro.getCdSiniestro()"+siniestro.getCdSiniestro());
		Integer res;
		documentosSiniestro = new DocumentoSiniestro();
		documentosSiniestro.setCd_compania(siniestro.getCdCompania());
		documentosSiniestro.setCd_rubro(0);
		documentosSiniestro.setCd_siniestro(siniestro.getCdSiniestro());
		documentosSiniestro.setTp_doc_siniestro(docAdcSiniestro);
		res = srvDocumentosSiniestro.insertarDocumentoSiniestro(documentosSiniestro);
		if (res == 0) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Error Documentos Siniesto", "Comuníquese con el Administrador del Sistema"));
			return;
		}
		documentosSiniestro = new DocumentoSiniestro();
		lstDocumentosSiniestro = new ArrayList<DocumentoSiniestro>();
		lstDocumentosSiniestro = srvDocumentosSiniestro
				.consultaDocumentosSiniestro(String.valueOf(siniestro.getCdSiniestro()));
//		RequestContext rSiniestroDoc = RequestContext.getCurrentInstance();
//		rSiniestroDoc.execute("PF('DocAdcSinies').hide();");
		PrimeFaces.current().executeScript("PF('DocAdcSinies').hide();");
	}

	public void eliminaDocumentoSinies() {
		try {
			if (selectedDocumentoSiniestro.getCd_doc_siniestro() == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Error", "Selecciones un Documento antes de Eliminar"));
				return;
			}
		} catch (Exception e) {
			return;
		}
		srvDocumentosSiniestro.eliminaDocumentoSiniestro(selectedDocumentoSiniestro);
		lstDocumentosSiniestro = new ArrayList<DocumentoSiniestro>();
		lstDocumentosSiniestro = srvDocumentosSiniestro
				.consultaDocumentosSiniestro(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void enviaCorreoCliente() {
		String correElecString = null;
		;
		try {
			correElecString = siniestro.getCorreo();
			if (correElecString.isEmpty() || correElecString == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null,
						new FacesMessage("Error", "Ingrese y Guarde el correo electrónico del cliente "));
				return;

			}
		} catch (Exception e) {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null,
					new FacesMessage("Error", "Ingrese y Guarde  el correo electrónico del cliente "));
			return;
		}

		// Documentos Pendiente
		String docuString = "";
		Date fcReciBrk;

		for (DocumentoSiniestro docuSel : lstDocumentosSiniestro) {
			Boolean flagBoolean = false;

			try {
				fcReciBrk = docuSel.getFc_recibo_brk();
				if (fcReciBrk == null) {
					flagBoolean = true;
				}
			} catch (Exception e) {
				flagBoolean = true;
			}
			if (flagBoolean) {
				docuString = docuString + "<tr style='mso-yfti-irow:1'><td style='padding:.75pt .75pt .75pt .75pt'>"
						+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
						+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
						+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
						+ "mso-bidi-theme-font:minor-latin'>" + docuSel.getTp_doc_siniestro()
						+ "<o:p></o:p></span></p></td></tr>";
				DocumentoSiniestro aux = new DocumentoSiniestro();
				aux = docuSel;
				aux.setFc_rec_cli_correo(new Date());
				srvDocumentosSiniestro.actualizaDocumentoSiniestro(aux);
			}
		}

		email.setReceptor(correElecString);
		email.setSubject("SOLICITUD DE DOCUMENTOS");
		email.setTexto("<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Estimad@ Sr./Sra. "
				+ siniestro.getNm_cliente() + "<o:p></o:p></span></p>"
				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Lamentamos mucho lo suscitado;"
				+ " <o:p></o:p></span></p>"
				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Por medio del presente, CONFÍA Tu Asesor Productor de Seguros solicita los "
				+ "siguientes documentos que son fundamentales para continuar con el proceso de su siniestro:"
				+ " <o:p></o:p></span></p>"

				+ "<table class=MsoNormalTable border=0 cellpadding=0 width=600 style='width:450.0pt; mso-cellspacing:1.5pt;mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'>"

				+ "<tr style='mso-yfti-irow:1'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Documentos:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td></tr>" + docuString
				+ "</table>"

				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Por favor enviar la documentación al siguiente correo: "
				+ "vgualpa@grupoconfia.com<o:p></o:p></span></p>"

				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Si necesita más información comuníquese al siguiente contacto:<o:p></o:p></span></p>"
				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>0994525315<o:p></o:p></span></p>"
				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Ejecutiva de Siniestros:<o:p></o:p></span></p>"
				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Viviana Gualpa<o:p></o:p></span></p>"

				+ "<p></p>" + "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>También nos puede visitar en nuestras oficinas ubicadas en Ambato- Av. Los"
				+ " Guaytambos y Juan Montalvo. Sector Ficoa, diagonal al Supermaxi.<o:p></o:p></span></p>"

				+ "<p></p>" + "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Estaremos gustosos de servirle<o:p></o:p></span></p>"

				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'> " + " <o:p></o:p></span></p>"
				+ "<p><strong>Nota: </strong>"
				+ "Este mensaje ha sido generado automáticamente, por favor no lo responda." + "</p> ");
		email.sendEmail();

		FacesContext fContextObj = FacesContext.getCurrentInstance();
		fContextObj.addMessage(null, new FacesMessage("Advertencia", "Envío Exitoso"));

	}

	public void actualizaFechasDocu() {
		System.out.println("INGRESO A EDITAR");
		try {
			if (selectedDocumentoSiniestro.getCd_doc_siniestro() == null) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Error", "Selecciones un Documento antes de Eliminar"));
				return;
			}
		} catch (Exception e) {
			return;
		}
		DocumentoSiniestro aux = new DocumentoSiniestro();
		aux = selectedDocumentoSiniestro;

		if (selectedTipoFechaString.equals("RECIBEBRK")) {
			aux.setFc_recibo_brk(fechaSelectDate);
		}
		if (selectedTipoFechaString.equals("ENVIAASEG")) {
			aux.setFc_envio_aseg(fechaSelectDate);
		}
		if (selectedTipoFechaString.equals("ENVIACLIE")) {
			aux.setFc_rec_cli_correo(fechaSelectDate);
		}
		if (selectedTipoFechaString.equals("RECORCLIE")) {
			aux.setFc_recuerda_cli(fechaSelectDate);
			;
		}

		srvDocumentosSiniestro.actualizaDocumentoSiniestro(aux);

	}

	public void onRowEditDocSinies(RowEditEvent<DocumentoSiniestro> event) {
		System.out.println("INGRESO");
		String obs = null;
		DocumentoSiniestro aux = new DocumentoSiniestro();
		aux = ((DocumentoSiniestro) event.getObject());
		obs = aux.getObs_doc_siniestro();
		aux.setObs_doc_siniestro(obs.trim().toUpperCase());
		srvDocumentosSiniestro.actualizaDocumentoSiniestro(aux);
		FacesContext msg = FacesContext.getCurrentInstance();
		msg.addMessage(null, new FacesMessage("Advertencia", "Registro Actualizado"));
	}

	public void agregarProforma() {
		int res;
		System.out.println("TALLER:" + codRubroTaller);
		System.out.println("PROVEEDOR:" + proformaSinies.getDescripcion_Proveedor());
		System.out.println("DIAGNOSTICO:" + diagnosticoSlm);
		if (codRubroTaller.equals("0") && proformaSinies.getDescripcion_Proveedor() == null
				&& diagnosticoSlm.equals("0")) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese la Descripción del Proveedor o Eleccione el Tealler"));
			return;
		}

		proformaSinies.setCd_compania(siniestro.getCdCompania());
		proformaSinies.setCd_siniestro(siniestro.getCdSiniestro());
		if (!diagnosticoSlm.equals("0")) {
			proformaSinies.setDescripcion_Proveedor(diagnosticoSlm);
			proformaSinies.setCd_rubro(0);
		} else {
			if (codRubroTaller.equals("0")) {
				proformaSinies.setDescripcion_Proveedor(proformaSinies.getDescripcion_Proveedor().toUpperCase().trim());
				proformaSinies.setCd_rubro(0);

			} else {
				rubrosTalleres = srvRubros.recuperaRubro(codRubroTaller);
				proformaSinies.setCd_rubro(rubrosTalleres.getCd_rubro());
				proformaSinies.setDescripcion_Proveedor(rubrosTalleres.getDesc_rubro().toUpperCase().trim());
			}
		}
		res = srvProformaSiniestro.insertarProformaSiniestro(proformaSinies);
		if (res == 0) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null,
					new FacesMessage("Error Ingreso Proforma", "Comuníquese con el Administrador del Sistema"));
			return;
		} else {
			lstProformaSinies = new ArrayList<ProformaSiniestro>();
			lstProformaSinies = srvProformaSiniestro
					.listadoProformaSiniestro(String.valueOf(siniestro.getCdSiniestro()));
			proformaSinies = new ProformaSiniestro();
		}

	}

	public void eliminaProforma() {
		try {
			selectedProformaSinies.getCd_compania();
		} catch (Exception e) {
			return;
		}
		srvProformaSiniestro.eliminaProformaSiniestro(selectedProformaSinies);
		lstProformaSinies = new ArrayList<ProformaSiniestro>();
		lstProformaSinies = srvProformaSiniestro.listadoProformaSiniestro(String.valueOf(siniestro.getCdSiniestro()));
	}

	public void nuevaLiquidacion() {
		Double totalValorProforma = 0.0;
		int res = 0;
		finiq = true;
		reservas = new Reservas();
		pagoSiniesttro = new PagoSiniestro();
		reservas.setCd_compania(siniestro.getCdCompania());
		reservas.setCd_siniestro(siniestro.getCdSiniestro());

		for (ProformaSiniestro listaProforma : lstProformaSinies) {
			totalValorProforma = totalValorProforma + listaProforma.getValor_presentado();
		}
		pagoSiniesttro.setVal_pago(totalValorProforma);
		pagoSiniesttro.setCd_siniestro(siniestro.getCdSiniestro());
		pagoSiniesttro.setCd_compania(siniestro.getCdCompania());
		pagoSiniesttro.setCd_aseguradora(siniestro.getCdAseguradora());
		pagoSiniesttro.setCd_cliente(siniestro.getCdCliente());
		reservas.setValor_perdida(totalValorProforma);
		reservas.setVal_deducible(0.0);
		reservas.setVal_depreciacion(0.0);
		reservas.setVal_indemnizacion(0.0);
		reservas.setVal_otros(0.0);
		reservas.setVal_rasa(0.0);
		reservas.setCopago_val(0.0);
		reservas.setGastos_no_cubiertos(0.0);
		reservas.setCopago_pct(0.0);
		res = srvReservas.insertarReservasSiniestros(reservas);
		if (res == 0) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null,
					new FacesMessage("Error Liquidación Siniestro", "Comuníquese con el Administrador del Sistema"));
			return;
		}

		reservas = new Reservas();
		res = srvReservas.codigoMaxReserva();
		reservas = srvReservas.recuperaReserva(res, siniestro.getCdCompania());
		pagoSiniesttro.setCd_reserva(reservas.getCd_reserva());
		obsSinies = "";
//		RequestContext rContextObj = RequestContext.getCurrentInstance();
//		rContextObj.execute("PF('wDlgLiquidaSiniestro').show();");
		PrimeFaces.current().executeScript("PF('wDlgLiquidaSiniestro').show();");
	}

	public void editaLiquidacion() {
		
		Double totalValorProforma = 0.0;
		int res = 0;
		finiq = true;
		reservas = new Reservas();
		pagoSiniesttro = new PagoSiniestro();
		reservas.setCd_compania(siniestro.getCdCompania());
		reservas.setCd_siniestro(siniestro.getCdSiniestro());

		for (ProformaSiniestro listaProforma : lstProformaSinies) {
			totalValorProforma = totalValorProforma + listaProforma.getValor_presentado();
		}
		pagoSiniesttro.setVal_pago(totalValorProforma);
		pagoSiniesttro.setCd_siniestro(siniestro.getCdSiniestro());
		pagoSiniesttro.setCd_compania(siniestro.getCdCompania());
		pagoSiniesttro.setCd_aseguradora(siniestro.getCdAseguradora());
		pagoSiniesttro.setCd_cliente(siniestro.getCdCliente());
		reservas.setValor_perdida(totalValorProforma);
		reservas.setVal_deducible(0.0);
		reservas.setVal_depreciacion(0.0);
		reservas.setVal_indemnizacion(0.0);
		reservas.setVal_otros(0.0);
		reservas.setVal_rasa(0.0);
		reservas.setGastos_no_cubiertos(0.0);
		reservas.setCopago_val(0.0);
		reservas.setCopago_pct(0.0);
		res = srvReservas.insertarReservasSiniestros(reservas);
		if (res == 0) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null,
					new FacesMessage("Error Liquidación Siniestro", "Comuníquese con el Administrador del Sistema"));
			return;
		}

		reservas = new Reservas();
		res = srvReservas.codigoMaxReserva();
		reservas = srvReservas.recuperaReserva(res, siniestro.getCdCompania());
		pagoSiniesttro.setCd_reserva(reservas.getCd_reserva());
//		RequestContext rContextObj = RequestContext.getCurrentInstance();
//		rContextObj.execute("PF('wDlgLiquidaSiniestroEdit').show();");
		PrimeFaces.current().executeScript("PF('wDlgLiquidaSiniestroEdit').show();");
	}

	public void calculaPagoLiq() {
		Double indenmiza = 0.0, subTotal = 0.0, total = 0.0, gastoNoCub = 0.0, pctCopago = 0.0, valCoPago = 0.0;
		String coPagoSel;
		indenmiza = reservas.getValor_perdida() - reservas.getVal_depreciacion() - reservas.getVal_deducible()
				- reservas.getVal_rasa() - reservas.getGastos_no_cubiertos();
		reservas.setVal_indemnizacion(indenmiza);
		subTotal = indenmiza;
		System.out.println("SubTotal:" + subTotal);
		total = subTotal - reservas.getVal_otros();
		System.out.println("Total:" + total);
		coPagoSel = String.valueOf(reservas.getCopago_pct());
		System.out.println("Copago Seleccionado:" + coPagoSel);
		if (!coPagoSel.equals('0')) {
			valCoPago = total * Double.valueOf(coPagoSel);
			reservas.setCopago_val(valCoPago);
			System.out.println("valCoPago:" + valCoPago);
			reservas.setVal_indemnizacion(total - valCoPago);
		}
		pagoSiniesttro.setVal_pago(total);
	}

	public void cierraLiquidacion() {
//		RequestContext rContextObj = RequestContext.getCurrentInstance();
//		rContextObj.execute("PF('wDlgLiquidaSiniestroEdit').hide();");
		PrimeFaces.current().executeScript("PF('wDlgLiquidaSiniestroEdit').hide();");

	}

	public void cierraLiquidacionIni() {
//		RequestContext rContextObj = RequestContext.getCurrentInstance();
//		rContextObj.execute("PF('wDlgLiquidaSiniestro').hide();");
		PrimeFaces.current().executeScript("PF('wDlgLiquidaSiniestro').hide();");

	}

	public void agregaLiquidacio() {
		int res = 0;
		if (formaPago.equals("0")) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null, new FacesMessage("Error", "Ingrese la forma de Pago"));
			return;
		}
		pagoSiniesttro.setForma_pago(formaPago);
		if (estado.equals("0")) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null, new FacesMessage("Error", "Ingrese el Estado del Siniestro"));
			return;
		}
		pagoSiniesttro.setEstado(estado);
		if (finiq = false) {
			pagoSiniesttro.setFiniquito(0);
		} else {
			pagoSiniesttro.setFiniquito(1);
		}
		pagoSiniesttro.setFc_estado_siniestro(fcEstadoSiniestro);
		srvReservas.actualizaReservasSiniestros(reservas);
		lstReservas = new ArrayList<Reservas>();
		lstReservas = srvReservas.recuperaReservasSinies(siniestro.getCdSiniestro(), siniestro.getCdSiniestro());

		res = srvPagoSiniestro.insertarPagoSiniestro(pagoSiniesttro);
		if (res == 0) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null,
					new FacesMessage("Error Liquidación Siniestro", "Comuníquese con el Administrador del Sistema"));
			return;
		}
		lstReservas = new ArrayList<Reservas>();
		lstReservas = srvReservas.recuperaReservasSinies(siniestro.getCdSiniestro(), siniestro.getCdCompania());
		// a�ade la COMISON DE LA RASA SI ESTA EXISTE
		if (reservas.getVal_rasa() > 0) {
			ComisionesPoliza comPol = new ComisionesPoliza();
			String aux = "RASA - No. Siniestro: ";
			comPol.setCd_compania(siniestro.getCdCompania());
			comPol.setCd_ramo_cotizacion(siniestro.getCdRamoCotizacion());
			comPol.setCd_reserva(reservas.getCd_reserva());
			comPol.setObservaciones(aux.concat(String.valueOf(siniestro.getCdSiniestro())));
			comPol.setTotal_asegurado(0.0);
			comPol.setTotal_prima(reservas.getVal_rasa());
			comPol.setPct_com_brk(0.0);
			comPol.setSaldo_com_brk(0.0);
			comPol.setVal_com_brk(0.0);
			srvComisionPoliza.insertarComisionesPoliza(comPol);
		}
		// ACTUALIZA EL OBJETO Y LOS DA DE BAJA EN CASO SEA PERDIDA TOTAL
		if (pagoSiniesttro.getEstado().equals("PERDIDA TOTAL")) {
			spDataBAse.bajaSiniestrosObjeto(siniestro.getCdCompania(), detalleSiniestros.getCd_obj_cotizacion());
		}
		if (estado.equals("PAGO PARCIAL SINIESTRO")) {
			siniestro.setBloqueo(0);
		} else {
			siniestro.setBloqueo(1);
		}
		try {
			if (obsSinies == null || obsSinies.equals("")) {
				obsSinies = "S/D";
			}
		} catch (Exception e) {
			obsSinies = "S/D";
		}
		siniestro.setObservacion_atencion(obsSinies);
		siniestro.setCalifica_atencion(calificaSin);
		srvSiniestros.actualizaSiniestros(siniestro);
		// en caso de que sea poliza mensualizada
		// verifica el pago pendiente del a�o en vigencia
		Integer resPolMen = 0;
		Siniestros sinAux = new Siniestros();
		sinAux = srvSiniestros.recuperaCodSiniestros(pagoSiniesttro.getCd_siniestro());
		resPolMen = srvConsultaPolizaView.polizaMensualizada(String.valueOf(sinAux.getCdCompania()),
				String.valueOf(sinAux.getCdRamoCotizacion()));
		if (pagoSiniesttro.getEstado().equals("PERDIDA TOTAL") && resPolMen.equals(1)) {
			spDataBAse.nivelacionFactSiniestrosPerdidaTotal(sinAux.getCdRamoCotizacion(), sinAux.getCdCompania());
		}
//		RequestContext rContextObj = RequestContext.getCurrentInstance();
//		rContextObj.execute("PF('wDlgLiquidaSiniestro').hide();");
		PrimeFaces.current().executeScript("PF('wDlgLiquidaSiniestro').hide();");
	}

	public void agregaLiquidacionEdit() {
		int res = 0;
		if (formaPago.equals("0")) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null, new FacesMessage("Error", "Ingrese la forma de Pago"));
			return;
		}
		pagoSiniesttro.setForma_pago(formaPago);
		if (estado.equals("0")) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null, new FacesMessage("Error", "Ingrese el Estado del Siniestro"));
			return;
		}
		pagoSiniesttro.setEstado(estado);
		if (finiq = false) {
			pagoSiniesttro.setFiniquito(0);
		} else {
			pagoSiniesttro.setFiniquito(1);
		}
		pagoSiniesttro.setFc_estado_siniestro(fcEstadoSiniestro);

		srvReservas.actualizaReservasSiniestros(reservas);
		lstReservas = new ArrayList<Reservas>();
		lstReservas = srvReservas.recuperaReservasSinies(siniestro.getCdSiniestro(), siniestro.getCdSiniestro());

		res = srvPagoSiniestro.insertarPagoSiniestro(pagoSiniesttro);
		if (res == 0) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null,
					new FacesMessage("Error Liquidación Siniestro", "Comuníquese con el Administrador del Sistema"));
			return;
		}
		lstReservas = new ArrayList<Reservas>();
		lstReservas = srvReservas.recuperaReservasSinies(siniestro.getCdSiniestro(), siniestro.getCdCompania());
		// a�ade la COMISON DE LA RASA SI ESTA EXISTE
		if (reservas.getVal_rasa() > 0) {
			ComisionesPoliza comPol = new ComisionesPoliza();
			String aux = "RASA - No. Siniestro: ";
			comPol.setCd_compania(siniestro.getCdCompania());
			comPol.setCd_ramo_cotizacion(siniestro.getCdRamoCotizacion());
			comPol.setCd_reserva(reservas.getCd_reserva());
			comPol.setObservaciones(aux.concat(String.valueOf(siniestro.getCdSiniestro())));
			comPol.setTotal_asegurado(0.0);
			comPol.setTotal_prima(reservas.getVal_rasa());
			comPol.setPct_com_brk(0.0);
			comPol.setSaldo_com_brk(0.0);
			comPol.setVal_com_brk(0.0);
			srvComisionPoliza.insertarComisionesPoliza(comPol);
		}
		// ACTUALIZA EL OBJETO Y LOS DA DE BAJA EN CASO SEA PERDIDA TOTAL
		if (pagoSiniesttro.getEstado().equals("PERDIDA TOTAL")) {
			spDataBAse.bajaSiniestrosObjeto(siniestro.getCdCompania(), detalleSiniestros.getCd_obj_cotizacion());
		}
		if (estado.equals("PAGO PARCIAL SINIESTRO")) {
			siniestro.setBloqueo(0);
		} else {
			siniestro.setBloqueo(1);
		}
		try {
			if (obsSinies == null || obsSinies.equals("")) {
				obsSinies = "S/D";
			}
		} catch (Exception e) {
			obsSinies = "S/D";
		}
		siniestro.setObservacion_atencion(obsSinies);
		siniestro.setCalifica_atencion(calificaSin);
		srvSiniestros.actualizaSiniestros(siniestro);

		// en caso de que sea poliza mensualizada
		// verifica el pago pendiente del a�o en vigencia
		Integer resPolMen = 0;
		Siniestros sinAux = new Siniestros();
		sinAux = srvSiniestros.recuperaCodSiniestros(siniestro.getCdSiniestro());
		resPolMen = srvConsultaPolizaView.polizaMensualizada(String.valueOf(sinAux.getCdCompania()),
				String.valueOf(sinAux.getCdRamoCotizacion()));
		if (pagoSiniesttro.getEstado().equals("PERDIDA TOTAL") && resPolMen.equals(1)) {
			spDataBAse.nivelacionFactSiniestrosPerdidaTotal(sinAux.getCdRamoCotizacion(), sinAux.getCdCompania());
		}
//		RequestContext rContextObj = RequestContext.getCurrentInstance();
//		rContextObj.execute("PF('wDlgLiquidaSiniestroEdit').hide();");
		PrimeFaces.current().executeScript("PF('wDlgLiquidaSiniestroEdit').hide();");
	}

	public void eliminaReserva() {
		try {
			selectedReservas.getCd_compania();
		} catch (Exception e) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null, new FacesMessage("Advertencia", "Seleccione un registro"));
			return;
		}
		srvReservas.eliminaReservasSiniestros(selectedReservas);
		lstReservas = new ArrayList<Reservas>();
		lstReservas = srvReservas.recuperaReservasSinies(siniestro.getCdSiniestro(), siniestro.getCdCompania());
	}

	public void guardaSiniestro() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {

			ctx.redirect("http://35.237.152.6:8081/Broken/index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void consultaSiniestrosCerrados() {
		try {
			nmClienteSiniesClose = nmClienteSiniesClose.trim().toUpperCase();
		} catch (Exception e) {
			nmClienteSiniesClose = "%";
		}
		try {
			numSiniesClos = numSiniesClos.trim().toUpperCase();
		} catch (Exception e) {
			numSiniesClos = "%";
		}
		lstSiniestroCerrado = new ArrayList<Siniestros>();
		lstSiniestroCerrado = srvSiniestros.recuperaSiniestrosCerrados(nmClienteSiniesClose, numSiniesClos);
	}

	public void consultaSiniestros() {
		ConsultaCaractPolView consCaracPol = new ConsultaCaractPolView();
		try {
			polizaSinies = polizaSinies.trim();
		} catch (Exception e) {
			// no realiza nada y retorna
			polizaSinies = "%";
		}
		try {
			numSinies = numSinies.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			numSinies = "%";
		}
		try {
			nmClienteSinies = nmClienteSinies.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			nmClienteSinies = "%";
		}
		try {
			placaSinies = placaSinies.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			placaSinies = "%";
		}
		try {
			motorSinies = motorSinies.trim().toUpperCase();
		} catch (Exception e) {
			// no realiza nada y retorna
			motorSinies = "%";
		}

		if (placaSinies.equals("%") || motorSinies.equals("%")) {
			// lstSiniestro =
			// srvSiniestros.recuperaSiniestrosNmClie(nmClienteSinies.trim().toUpperCase());
			lstSiniestro = srvSiniestros.recuperaSiniestros(nmClienteSinies.trim().toUpperCase(), polizaSinies,
					numSinies);
		} else {
			if (placaSinies.equals("%")) {
				consCaracPol = srvConsCaractPolView.consultaCaracteristicaObjPol("%", motor.trim().toUpperCase());
				lstSiniestro = srvSiniestros.recuperaSiniestrosRamoCot(consCaracPol.getCd_ramo_cotizacion());
			}
			if (motorSinies.equals("%")) {
				consCaracPol = srvConsCaractPolView.consultaCaracteristicaObjPol(placa.trim().toUpperCase(), "%");
				lstSiniestro = srvSiniestros.recuperaSiniestrosRamoCot(consCaracPol.getCd_ramo_cotizacion());
			}
		}
	}

	public void aperturaSiniestroCerrado() {

		if (flgEdita == false) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null,
					new FacesMessage("Advertencia", "Active la apertura de Siniestros para procesar su solicitud"));
			return;
		}

		try {
			selectedSiniestroClose.getCdSiniestro();
		} catch (Exception e) {
			FacesContext msg = FacesContext.getCurrentInstance();
			msg.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Registro para Aperturar"));
			return;
		}
		Siniestros siniesAux = new Siniestros();
		siniesAux = selectedSiniestroClose;
		siniesAux.setBloqueo(0);
		srvSiniestros.actualizaSiniestros(siniesAux);
		FacesContext msg = FacesContext.getCurrentInstance();
		msg.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstSiniestroCerrado = new ArrayList<Siniestros>();
	}

	public void eliminaSiniestroIngresado() {
		try {
			selectedSiniestro.getAnoSiniestro();
		} catch (Exception e) {
			return;
		}
		Siniestros siniestroAux = new Siniestros();
		siniestroAux = selectedSiniestro;
		srvSiniestros.eliminaSiniestros(siniestroAux);
		consultaSiniestros();
		FacesContext msg = FacesContext.getCurrentInstance();
		msg.addMessage(null, new FacesMessage("Advertencia", "Siniestro Eliminado"));
	}

	public void editaSiniestroExistente() {
		try {
			selectedSiniestro.getAnoSiniestro();
		} catch (Exception e) {
			return;
		}

		siniestro = new Siniestros();
		detalleSiniestros = new DetalleSiniestros();
		try {
			cdCiudad = String.valueOf(siniestro.getCd_ciudad());
		} catch (Exception e) {
			cdCiudad = "0";
		}

		try {
			cdProvincia = String.valueOf(siniestro.getCd_provincia());
		} catch (Exception e) {
			cdProvincia = "0";
		}
		try {
			cdCanton = String.valueOf(siniestro.getCd_canton());
		} catch (Exception e) {
			cdCanton = "0";
		}
		try {
			cdParroquia = String.valueOf(siniestro.getCd_parroquia());
		} catch (Exception e) {
			cdParroquia = "0";
		}

		siniestro = srvSiniestros.recuperaCodSiniestros(selectedSiniestro.getCdSiniestro());
		grupoContratante = new GrupoContratante();
		grupoContratante = srvGrupoContratante.buscaGruposContratanteCrC(siniestro.getCdRamoCotizacion());
		
		detalleSiniestros = srvDetalleSiniestros.recuperaDetSiniestrosxCdSini(siniestro.getCdSiniestro(),
				siniestro.getCdCompania());
		
		plan = new Plan();
		// verirfico si el ramo es
		// Ubicación o RamoCotizacion
		Integer tpRam = srvRamo.tipoRamo(siniestro.getCdRamo());
		System.out.println("Tipo Siniestro:"+tpRam);
		
		if (tpRam.equals(1)) {
			try {
				plan = srvPlan.consultaPlanUbicacion(Integer.valueOf(detalleSiniestros.getCd_obj_cotizacion()));
				if(plan == null) {
					plan = srvPlan.consultaPlanRamoCotizacion(siniestro.getCdRamoCotizacion());
				}
			} catch (Exception e) {
				plan = srvPlan.consultaPlanRamoCotizacion(siniestro.getCdRamoCotizacion());
			}
			
		} else {
			plan = srvPlan.consultaPlanRamoCotizacion(siniestro.getCdRamoCotizacion());
		}
		LstCaracteristicasCab = new ArrayList<CaracteristicasVehiculos>();
		LstCaracteristicasCab = srvCaracteristicaObj.recuperaCaractVHporObjCot(detalleSiniestros.getCd_obj_cotizacion(),
				detalleSiniestros.getCd_compania());
		if (LstCaracteristicasCab.size() == 0) {
			panelCaracteristica = true;
		}
		System.out.println("INGRESO CARACTERISTICAS DEL VEHICULO INGRESADO:" + LstCaracteristicasCab.size());
		System.out.println("OBJETO COTIZACION:" + detalleSiniestros.getCd_obj_cotizacion());
		System.out.println("*********************************************************************+");

		// recupera diagnostico
		lstDiagnosticoReclamo = new ArrayList<DiagnosticoReclamo>();
		lstDiagnosticoReclamo = srvDiagnosticoReclamo
				.consultaDiagnosticoReclamo(String.valueOf(siniestro.getCdSiniestro()));
		// recupera datos coberturas, deducibles, siniestros
		lstCoberturasPlan = new ArrayList<CoberturasEmitidas>();
		lstDeduciblesPlan = new ArrayList<DeduciblesEmitidas>();
		lstCoberturasAdcPlan = new ArrayList<CoberturasAdicionales>();
		lstCoberturasPlan = srvCoberturasPlan.recuperaCoberturasEmitidas(Integer.valueOf(siniestro.getCdCompania()),
				Integer.valueOf(siniestro.getCdRamoCotizacion()));
		lstDeduciblesPlan = srvDeduciblesPlan.recuperaDeduciblesEmitidas(Integer.valueOf(siniestro.getCdCompania()),
				Integer.valueOf(siniestro.getCdRamoCotizacion()));
		lstCoberturasAdcPlan = srvCoberturasAdc.recuperaCoberturasAdcSiniestros(detalleSiniestros.getCd_compania(),
				detalleSiniestros.getCd_obj_cotizacion());

		if (lstCoberturasPlan.size() == 0) {
			lstCoberturasPlan = new ArrayList<CoberturasEmitidas>();
			lstDeduciblesPlan = new ArrayList<DeduciblesEmitidas>();
			lstCoberturasPlan = srvCoberturasPlan.recuperaCoberturasEmitidas(Integer.valueOf(siniestro.getCdCompania()),
					Integer.valueOf(siniestro.getCdRamoCotizacion()));
			lstDeduciblesPlan = srvDeduciblesPlan.recuperaDeduciblesEmitidas(Integer.valueOf(siniestro.getCdCompania()),
					Integer.valueOf(siniestro.getCdRamoCotizacion()));
			lstCoberturasAdcPlan = srvCoberturasAdc.recuperaCoberturasAdcSiniestros(detalleSiniestros.getCd_compania(),
					detalleSiniestros.getCd_obj_cotizacion());
		}

		lstCoberturasSiniestro = new ArrayList<CoberturasSiniestro>();
		lstCoberturasSiniestro = srvCoberturasSiniestro
				.consultaCoberturasSiniestro(String.valueOf(siniestro.getCdSiniestro()));
		lsRubrosDocumentos = new ArrayList<Rubros>();
		lsRubrosDocumentos = srvRubros.listadoDocumentosSiniestro("103", String.valueOf(siniestro.getCdRamo()));
		lstDocumentosSiniestro = new ArrayList<DocumentoSiniestro>();
		lstDocumentosSiniestro = srvDocumentosSiniestro
				.consultaDocumentosSiniestro(String.valueOf(siniestro.getCdSiniestro()));
		lsRubrosTalleres = new ArrayList<Rubros>();
		lsRubrosTalleres = srvRubros.listadoDocumentosSiniestro("104", String.valueOf(siniestro.getCdRamo()));
		lstProformaSinies = new ArrayList<ProformaSiniestro>();
		lstProformaSinies = srvProformaSiniestro.listadoProformaSiniestro(String.valueOf(siniestro.getCdSiniestro()));
		lstReservas = new ArrayList<Reservas>();
		lstReservas = srvReservas.recuperaReservasSinies(siniestro.getCdSiniestro(), siniestro.getCdCompania());

//		RequestContext rSiniestroObj = RequestContext.getCurrentInstance();
//		rSiniestroObj.execute("PF('dlgEditaSiniestro').show();");
		PrimeFaces.current().executeScript("PF('dlgEditaSiniestro').show();");
	}

	public void controlAcceso() {
		if (flgEdita == true) {
//			RequestContext contextDlg = RequestContext.getCurrentInstance();
//			contextDlg.execute("PF('wControlAccesoSiniesClose').show();");
			PrimeFaces.current().executeScript("PF('wControlAccesoSiniesClose').show();");
		}
	}

	public void aceptaAcceso() {
		// recupera el usuario logeado
		Integer usrId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO");
		System.out.println("USUARIO:" + usrId);

		Integer acceso;
		acceso = srvRubros.accesoPantalla("105", "SINIESTROS", String.valueOf(usrId), passwd);
		if (acceso.equals(0)) {
			flgEdita = false;
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia",
					"No tiene permisos para ejecutar este proceso o su contraseña es incorrecta."));
			return;
		} else {
			flgEdita = true;
//			RequestContext contextDlg = RequestContext.getCurrentInstance();
//			contextDlg.execute("PF('wControlAccesoSiniesClose').hide();");
			PrimeFaces.current().executeScript("PF('wControlAccesoSiniesClose').hide();");
		}
	}

	public void recuperaContactosCarta() {
		lstContactoCarta = new ArrayList<Contacto>();
		if (siniestro == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione el Siniestro antes de generar la carta"));
			return;
		} else {
			objCarta = new Rubros();
			objCarta = srvRubrosCartas.recuperaCartaPorNombre(nmCarta);
			if (objCarta.getTipo_rubro().equals("ASEG")) {
				lstContactoCarta = srvContactosCarta.listaContactosAseguradora(siniestro.getCdAseguradora());
			}
		}
	}

	public void guardaCarta() {
		String usuarioId, nmUsr, numeroCarta;
		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		System.out.println("GUARDAR CARTA USuario:" + usuarioId);
		Usuarios usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(usuarioId);
		nmUsr = usr.getUsrnombres();
		nmUsr = nmUsr.concat(" ");
		nmUsr = nmUsr.concat(usr.getUsrapellidos());
		Correspondencia carta = new Correspondencia();
		carta.setCd_siniestro(siniestro.getCdSiniestro());
		;
		carta.setCd_compania(siniestro.getCdCompania());
		carta.setCdAseguradora(siniestro.getCdAseguradora());
		carta.setCdCliente(siniestro.getCdCliente());
		carta.setCdRubro(objCarta.getCd_rubro());
		carta.setModuloGenera(objCarta.getDesc_general());
		carta.setNmReporte(objCarta.getNm_reporte());
		carta.setTipo(objCarta.getDesc_rubro());
		carta.setRefCarta(objCarta.getReferenciaCarta());
		carta.setNotasAdicionales(notasAdicionalesCarta);
		carta.setElaboradorPor(nmUsr);
		carta.setUsrid(usr.getUsrid());
		carta.setCargoUsuario(usr.getCargo());
		try {
			if (selectedContactoCarta != null) {
				carta.setCdContacto(selectedContactoCarta.getCd_contacto());
				carta.setNmContacto(selectedContactoCarta.getNombre_contacto());
				carta.setCiudadContacto(selectedContactoCarta.getCiudad());
				carta.setCargoContacto(selectedContactoCarta.getCargo_contacto());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		srvCorrespondencia.insertarCorrespondencia(carta);
		numeroCarta = srvCorrespondencia.numCartaMax(String.valueOf(usr.getUsrid()));
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Registro Exitoso", "Se Generó el Documento Número " + numeroCarta
				+ ". Ingrese al Módulo de Correspondecia para Imprimirlo"));
	}

	public void recuperaCanton() {
		lstCanton = new ArrayList<Canton>();
		lstCanton = srvCanton.listaCantones(cdProvincia);
	}

	public void recuperaParroquia() {
		lstParroquias = new ArrayList<Parroquia>();
		lstParroquias = srvParroquia.listaParroquia(cdCanton);
	}

	public Contacto getSelectedContactoCarta() {
		return selectedContactoCarta;
	}

	public void setSelectedContactoCarta(Contacto selectedContactoCarta) {
		this.selectedContactoCarta = selectedContactoCarta;
	}

	public List<Contacto> getLstContactoCarta() {
		return lstContactoCarta;
	}

	public void setLstContactoCarta(List<Contacto> lstContactoCarta) {
		this.lstContactoCarta = lstContactoCarta;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public List<ConsultaPolizaView> getLstConsultaPoliza() {
		return lstConsultaPoliza;
	}

	public void setLstConsultaPoliza(List<ConsultaPolizaView> lstConsultaPoliza) {
		this.lstConsultaPoliza = lstConsultaPoliza;
	}

	public ConsultaPolizaView getSelectedConsultaPoliza() {
		return selectedConsultaPoliza;
	}

	public void setSelectedConsultaPoliza(ConsultaPolizaView selectedConsultaPoliza) {
		this.selectedConsultaPoliza = selectedConsultaPoliza;
	}

	public List<ConsultaUbicacionPolView> getLstUbicacionPoliza() {
		return lstUbicacionPoliza;
	}

	public void setLstUbicacionPoliza(List<ConsultaUbicacionPolView> lstUbicacionPoliza) {
		this.lstUbicacionPoliza = lstUbicacionPoliza;
	}

	public ConsultaUbicacionPolView getSelectedUbicacionPoliza() {
		return selectedUbicacionPoliza;
	}

	public void setSelectedUbicacionPoliza(ConsultaUbicacionPolView selectedUbicacionPoliza) {
		this.selectedUbicacionPoliza = selectedUbicacionPoliza;
	}

	public List<ConsultaObjetoPolView> getLstObjetosPoliza() {
		return lstObjetosPoliza;
	}

	public void setLstObjetosPoliza(List<ConsultaObjetoPolView> lstObjetosPoliza) {
		this.lstObjetosPoliza = lstObjetosPoliza;
	}

	public ConsultaObjetoPolView getSelectedObjetosPoliza() {
		return selectedObjetosPoliza;
	}

	public void setSelectedObjetosPoliza(ConsultaObjetoPolView selectedObjetosPoliza) {
		this.selectedObjetosPoliza = selectedObjetosPoliza;
	}

	public List<CaracteristicasVehiculos> getLstCaracteristicas() {
		return LstCaracteristicas;
	}

	public void setLstCaracteristicas(List<CaracteristicasVehiculos> lstCaracteristicas) {
		LstCaracteristicas = lstCaracteristicas;
	}

	public List<ConsultaPagoPolView> getLstPagoPoliza() {
		return lstPagoPoliza;
	}

	public void setLstPagoPoliza(List<ConsultaPagoPolView> lstPagoPoliza) {
		this.lstPagoPoliza = lstPagoPoliza;
	}

	public DetalleSiniestros getDetalleSiniestros() {
		return detalleSiniestros;
	}

	public void setDetalleSiniestros(DetalleSiniestros detalleSiniestros) {
		this.detalleSiniestros = detalleSiniestros;
	}

	public Siniestros getSiniestro() {
		return siniestro;
	}

	public void setSiniestro(Siniestros siniestro) {
		this.siniestro = siniestro;
	}

	public List<CoberturasEmitidas> getLstCoberturasPlan() {
		return lstCoberturasPlan;
	}

	public void setLstCoberturasPlan(List<CoberturasEmitidas> lstCoberturasPlan) {
		this.lstCoberturasPlan = lstCoberturasPlan;
	}

	public List<DeduciblesEmitidas> getLstDeduciblesPlan() {
		return lstDeduciblesPlan;
	}

	public void setLstDeduciblesPlan(List<DeduciblesEmitidas> lstDeduciblesPlan) {
		this.lstDeduciblesPlan = lstDeduciblesPlan;
	}

	public List<CoberturasSiniestro> getLstCoberturasSiniestro() {
		return lstCoberturasSiniestro;
	}

	public void setLstCoberturasSiniestro(List<CoberturasSiniestro> lstCoberturasSiniestro) {
		this.lstCoberturasSiniestro = lstCoberturasSiniestro;
	}

	public CoberturasEmitidas getSelectedCoberturasPlan() {
		return selectedCoberturasPlan;
	}

	public void setSelectedCoberturasPlan(CoberturasEmitidas selectedCoberturasPlan) {
		this.selectedCoberturasPlan = selectedCoberturasPlan;
	}

	public DeduciblesEmitidas getSelectedDeduciblesPlan() {
		return selectedDeduciblesPlan;
	}

	public void setSelectedDeduciblesPlan(DeduciblesEmitidas selectedDeduciblesPlan) {
		this.selectedDeduciblesPlan = selectedDeduciblesPlan;
	}

	public CoberturasSiniestro getSelectedCoberturasSiniestro() {
		return selectedCoberturasSiniestro;
	}

	public void setSelectedCoberturasSiniestro(CoberturasSiniestro selectedCoberturasSiniestro) {
		this.selectedCoberturasSiniestro = selectedCoberturasSiniestro;
	}

	public List<Ciudad> getLstCiudad() {
		return lstCiudad;
	}

	public void setLstCiudad(List<Ciudad> lstCiudad) {
		this.lstCiudad = lstCiudad;
	}

	public List<Rubros> getLsRubrosDocumentos() {
		return lsRubrosDocumentos;
	}

	public void setLsRubrosDocumentos(List<Rubros> lsRubrosDocumentos) {
		this.lsRubrosDocumentos = lsRubrosDocumentos;
	}

	public List<Rubros> getSelectedLstRubDoc() {
		return selectedLstRubDoc;
	}

	public void setSelectedLstRubDoc(List<Rubros> selectedLstRubDoc) {
		this.selectedLstRubDoc = selectedLstRubDoc;
	}

	public List<Rubros> getFilteredRubrosDocumentos() {
		return filteredRubrosDocumentos;
	}

	public void setFilteredRubrosDocumentos(List<Rubros> filteredRubrosDocumentos) {
		this.filteredRubrosDocumentos = filteredRubrosDocumentos;
	}

	public DocumentoSiniestro getDocumentosSiniestro() {
		return documentosSiniestro;
	}

	public void setDocumentosSiniestro(DocumentoSiniestro documentosSiniestro) {
		this.documentosSiniestro = documentosSiniestro;
	}

	public List<DocumentoSiniestro> getLstDocumentosSiniestro() {
		return lstDocumentosSiniestro;
	}

	public void setLstDocumentosSiniestro(List<DocumentoSiniestro> lstDocumentosSiniestro) {
		this.lstDocumentosSiniestro = lstDocumentosSiniestro;
	}

	public DocumentoSiniestro getSelectedDocumentoSiniestro() {
		return selectedDocumentoSiniestro;
	}

	public void setSelectedDocumentoSiniestro(DocumentoSiniestro selectedDocumentoSiniestro) {
		this.selectedDocumentoSiniestro = selectedDocumentoSiniestro;
	}

	public List<Rubros> getLsRubrosTalleres() {
		return lsRubrosTalleres;
	}

	public void setLsRubrosTalleres(List<Rubros> lsRubrosTalleres) {
		this.lsRubrosTalleres = lsRubrosTalleres;
	}

	public String getCodRubroTaller() {
		return codRubroTaller;
	}

	public void setCodRubroTaller(String codRubroTaller) {
		this.codRubroTaller = codRubroTaller;
	}

	public Rubros getRubrosTalleres() {
		return rubrosTalleres;
	}

	public void setRubrosTalleres(Rubros rubrosTalleres) {
		this.rubrosTalleres = rubrosTalleres;
	}

	public Reservas getReservas() {
		return reservas;
	}

	public void setReservas(Reservas reservas) {
		this.reservas = reservas;
	}

	public List<ProformaSiniestro> getLstProformaSinies() {
		return lstProformaSinies;
	}

	public void setLstProformaSinies(List<ProformaSiniestro> lstProformaSinies) {
		this.lstProformaSinies = lstProformaSinies;
	}

	public ProformaSiniestro getProformaSinies() {
		return proformaSinies;
	}

	public void setProformaSinies(ProformaSiniestro proformaSinies) {
		this.proformaSinies = proformaSinies;
	}

	public ProformaSiniestro getSelectedProformaSinies() {
		return selectedProformaSinies;
	}

	public void setSelectedProformaSinies(ProformaSiniestro selectedProformaSinies) {
		this.selectedProformaSinies = selectedProformaSinies;
	}

	public PagoSiniestro getPagoSiniesttro() {
		return pagoSiniesttro;
	}

	public void setPagoSiniesttro(PagoSiniestro pagoSiniesttro) {
		this.pagoSiniesttro = pagoSiniesttro;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getFiniq() {
		return finiq;
	}

	public void setFiniq(Boolean finiq) {
		this.finiq = finiq;
	}

	public Reservas getSelectedReservas() {
		return selectedReservas;
	}

	public void setSelectedReservas(Reservas selectedReservas) {
		this.selectedReservas = selectedReservas;
	}

	public List<Reservas> getLstReservas() {
		return lstReservas;
	}

	public void setLstReservas(List<Reservas> lstReservas) {
		this.lstReservas = lstReservas;
	}

	public List<Siniestros> getLstSiniestro() {
		return lstSiniestro;
	}

	public void setLstSiniestro(List<Siniestros> lstSiniestro) {
		this.lstSiniestro = lstSiniestro;
	}

	public Siniestros getSelectedSiniestro() {
		return selectedSiniestro;
	}

	public void setSelectedSiniestro(Siniestros selectedSiniestro) {
		this.selectedSiniestro = selectedSiniestro;
	}

	public String getNmClienteSinies() {
		return nmClienteSinies;
	}

	public void setNmClienteSinies(String nmClienteSinies) {
		this.nmClienteSinies = nmClienteSinies;
	}

	public String getPlacaSinies() {
		return placaSinies;
	}

	public void setPlacaSinies(String placaSinies) {
		this.placaSinies = placaSinies;
	}

	public String getMotorSinies() {
		return motorSinies;
	}

	public void setMotorSinies(String motorSinies) {
		this.motorSinies = motorSinies;
	}

	public String getCdCiudad() {
		return cdCiudad;
	}

	public void setCdCiudad(String cdCiudad) {
		this.cdCiudad = cdCiudad;
	}

	public String getDocAdcSiniestro() {
		return docAdcSiniestro;
	}

	public void setDocAdcSiniestro(String docAdcSiniestro) {
		this.docAdcSiniestro = docAdcSiniestro;
	}

	public String getNmClienteSiniesClose() {
		return nmClienteSiniesClose;
	}

	public void setNmClienteSiniesClose(String nmClienteSiniesClose) {
		this.nmClienteSiniesClose = nmClienteSiniesClose;
	}

	public String getNumSiniesClos() {
		return numSiniesClos;
	}

	public void setNumSiniesClos(String numSiniesClos) {
		this.numSiniesClos = numSiniesClos;
	}

	public List<Siniestros> getLstSiniestroCerrado() {
		return lstSiniestroCerrado;
	}

	public void setLstSiniestroCerrado(List<Siniestros> lstSiniestroCerrado) {
		this.lstSiniestroCerrado = lstSiniestroCerrado;
	}

	public Siniestros getSelectedSiniestroClose() {
		return selectedSiniestroClose;
	}

	public void setSelectedSiniestroClose(Siniestros selectedSiniestroClose) {
		this.selectedSiniestroClose = selectedSiniestroClose;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean isFlgEdita() {
		return flgEdita;
	}

	public void setFlgEdita(boolean flgEdita) {
		this.flgEdita = flgEdita;
	}

	public String getCodGrupoContratante() {
		return codGrupoContratante;
	}

	public void setCodGrupoContratante(String codGrupoContratante) {
		this.codGrupoContratante = codGrupoContratante;
	}

	public List<GrupoContratante> getListaGrupoContratante() {
		return listaGrupoContratante;
	}

	public void setListaGrupoContratante(List<GrupoContratante> listaGrupoContratante) {
		this.listaGrupoContratante = listaGrupoContratante;
	}

	public List<ConsultaSubObjetoPolView> getLstSubObjetosPoliza() {
		return lstSubObjetosPoliza;
	}

	public void setLstSubObjetosPoliza(List<ConsultaSubObjetoPolView> lstSubObjetosPoliza) {
		this.lstSubObjetosPoliza = lstSubObjetosPoliza;
	}

	public List<Rubros> getLstRubrosCarta() {
		return lstRubrosCarta;
	}

	public void setLstRubrosCarta(List<Rubros> lstRubrosCarta) {
		this.lstRubrosCarta = lstRubrosCarta;
	}

	public String getNmCarta() {
		return nmCarta;
	}

	public void setNmCarta(String nmCarta) {
		this.nmCarta = nmCarta;
	}

	public String getNotasAdicionalesCarta() {
		return notasAdicionalesCarta;
	}

	public void setNotasAdicionalesCarta(String notasAdicionalesCarta) {
		this.notasAdicionalesCarta = notasAdicionalesCarta;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public ConsultaSubObjetoPolView getSelectedSubObjetosPoliza() {
		return selectedSubObjetosPoliza;
	}

	public void setSelectedSubObjetosPoliza(ConsultaSubObjetoPolView selectedSubObjetosPoliza) {
		this.selectedSubObjetosPoliza = selectedSubObjetosPoliza;
	}

	public String getNumSinies() {
		return numSinies;
	}

	public void setNumSinies(String numSinies) {
		this.numSinies = numSinies;
	}

	public String getPolizaSinies() {
		return polizaSinies;
	}

	public void setPolizaSinies(String polizaSinies) {
		this.polizaSinies = polizaSinies;
	}

	public String getNmTitular() {
		return nmTitular;
	}

	public void setNmTitular(String nmTitular) {
		this.nmTitular = nmTitular;
	}

	public List<Diagnostico> getLstDiagnostico() {
		return lstDiagnostico;
	}

	public void setLstDiagnostico(List<Diagnostico> lstDiagnostico) {
		this.lstDiagnostico = lstDiagnostico;
	}

	public Diagnostico getSelectedDiagnostico() {
		return selectedDiagnostico;
	}

	public void setSelectedDiagnostico(Diagnostico selectedDiagnostico) {
		this.selectedDiagnostico = selectedDiagnostico;
	}

	public List<DiagnosticoReclamo> getLstDiagnosticoReclamo() {
		return lstDiagnosticoReclamo;
	}

	public void setLstDiagnosticoReclamo(List<DiagnosticoReclamo> lstDiagnosticoReclamo) {
		this.lstDiagnosticoReclamo = lstDiagnosticoReclamo;
	}

	public DiagnosticoReclamo getSelectedDiagnosticoReclamo() {
		return selectedDiagnosticoReclamo;
	}

	public void setSelectedDiagnosticoReclamo(DiagnosticoReclamo selectedDiagnosticoReclamo) {
		this.selectedDiagnosticoReclamo = selectedDiagnosticoReclamo;
	}

	public Double getValorReclamo() {
		return valorReclamo;
	}

	public void setValorReclamo(Double valorReclamo) {
		this.valorReclamo = valorReclamo;
	}

	public String getDiagnosticoSlm() {
		return diagnosticoSlm;
	}

	public void setDiagnosticoSlm(String diagnosticoSlm) {
		this.diagnosticoSlm = diagnosticoSlm;
	}

	public Date getFcEstadoSiniestro() {
		return fcEstadoSiniestro;
	}

	public void setFcEstadoSiniestro(Date fcEstadoSiniestro) {
		this.fcEstadoSiniestro = fcEstadoSiniestro;
	}

	public List<ConsultaObjetoPolView> getFilteredLstObjetosPoliza() {
		return filteredLstObjetosPoliza;
	}

	public void setFilteredLstObjetosPoliza(List<ConsultaObjetoPolView> filteredLstObjetosPoliza) {
		this.filteredLstObjetosPoliza = filteredLstObjetosPoliza;
	}

	public List<CaracteristicasVehiculos> getLstCaracteristicasCab() {
		return LstCaracteristicasCab;
	}

	public void setLstCaracteristicasCab(List<CaracteristicasVehiculos> lstCaracteristicasCab) {
		LstCaracteristicasCab = lstCaracteristicasCab;
	}

	public Boolean getPanelCaracteristica() {
		return panelCaracteristica;
	}

	public void setPanelCaracteristica(Boolean panelCaracteristica) {
		this.panelCaracteristica = panelCaracteristica;
	}

	public String getSelectedTipoFechaString() {
		return selectedTipoFechaString;
	}

	public void setSelectedTipoFechaString(String selectedTipoFechaString) {
		this.selectedTipoFechaString = selectedTipoFechaString;
	}

	public Date getFechaSelectDate() {
		return fechaSelectDate;
	}

	public void setFechaSelectDate(Date fechaSelectDate) {
		this.fechaSelectDate = fechaSelectDate;
	}

	public String getCalificaSin() {
		return calificaSin;
	}

	public void setCalificaSin(String calificaSin) {
		this.calificaSin = calificaSin;
	}

	public String getObsSinies() {
		return obsSinies;
	}

	public void setObsSinies(String obsSinies) {
		this.obsSinies = obsSinies;
	}

	public GrupoContratante getGrupoContratante() {
		return grupoContratante;
	}

	public void setGrupoContratante(GrupoContratante grupoContratante) {
		this.grupoContratante = grupoContratante;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<CoberturasAdicionales> getLstCoberturasAdcPlan() {
		return lstCoberturasAdcPlan;
	}

	public void setLstCoberturasAdcPlan(List<CoberturasAdicionales> lstCoberturasAdcPlan) {
		this.lstCoberturasAdcPlan = lstCoberturasAdcPlan;
	}

	public CoberturasAdicionales getSelectedCoberturasAdcPlan() {
		return selectedCoberturasAdcPlan;
	}

	public void setSelectedCoberturasAdcPlan(CoberturasAdicionales selectedCoberturasAdcPlan) {
		this.selectedCoberturasAdcPlan = selectedCoberturasAdcPlan;
	}

	public List<Provincias> getLstProvincia() {
		return lstProvincia;
	}

	public void setLstProvincia(List<Provincias> lstProvincia) {
		this.lstProvincia = lstProvincia;
	}

	public List<Canton> getLstCanton() {
		return lstCanton;
	}

	public void setLstCanton(List<Canton> lstCanton) {
		this.lstCanton = lstCanton;
	}

	public List<Parroquia> getLstParroquias() {
		return lstParroquias;
	}

	public void setLstParroquias(List<Parroquia> lstParroquias) {
		this.lstParroquias = lstParroquias;
	}

	public String getCdProvincia() {
		return cdProvincia;
	}

	public void setCdProvincia(String cdProvincia) {
		this.cdProvincia = cdProvincia;
	}

	public String getCdCanton() {
		return cdCanton;
	}

	public void setCdCanton(String cdCanton) {
		this.cdCanton = cdCanton;
	}

	public String getCdParroquia() {
		return cdParroquia;
	}

	public void setCdParroquia(String cdParroquia) {
		this.cdParroquia = cdParroquia;
	}

}
