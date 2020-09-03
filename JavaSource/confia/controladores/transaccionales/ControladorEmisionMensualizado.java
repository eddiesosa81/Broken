package confia.controladores.transaccionales;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import confia.entidades.basicos.AseguradoraRamo;
import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Ciudad;
import confia.entidades.basicos.Clausulas;
import confia.entidades.basicos.ClausulasEmitidas;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Coberturas;
import confia.entidades.basicos.CoberturasAdicionales;
import confia.entidades.basicos.CoberturasEmitidas;
import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.Deducibles;
import confia.entidades.basicos.DeduciblesEmitidas;
import confia.entidades.basicos.Direccion;
import confia.entidades.basicos.Dispositivos;
import confia.entidades.basicos.Ejecutivos;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Marca;
import confia.entidades.basicos.Modelo;
import confia.entidades.basicos.Nacionalidad;
import confia.entidades.basicos.Plan;
import confia.entidades.basicos.PlanDepreciacion;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.Subagentes;
import confia.entidades.basicos.SubeArchivoObj;
import confia.entidades.basicos.Telefono;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.CaracteristicasObj;
import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.entidades.transaccionales.Correspondencia;
import confia.entidades.transaccionales.Cotizacion;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.FormaPago;
import confia.entidades.transaccionales.ObjetoCotizacion;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.transaccionales.SubObjetoCotizacion;
import confia.entidades.transaccionales.Ubicacion;
import confia.entidades.vistas.CoberturasRamoAsegView;
import confia.entidades.vistas.CotizacionesPendientes;
import confia.procedures.servicioProcedures;
import confia.procedures.subeArchivo;
import confia.servicios.basicos.ServicioAseguradoraRamo;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioCiudad;
import confia.servicios.basicos.ServicioClausulas;
import confia.servicios.basicos.ServicioClausulasEmitidas;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioCoberturas;
import confia.servicios.basicos.ServicioCoberturasAdicionales;
import confia.servicios.basicos.ServicioCoberturasEmitidas;
import confia.servicios.basicos.ServicioContacto;
import confia.servicios.basicos.ServicioDeducibles;
import confia.servicios.basicos.ServicioDireccion;
import confia.servicios.basicos.ServicioDispositivos;
import confia.servicios.basicos.ServicioEjecutivos;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioMarca;
import confia.servicios.basicos.ServicioModelo;
import confia.servicios.basicos.ServicioNacionalidad;
import confia.servicios.basicos.ServicioPlan;
import confia.servicios.basicos.ServicioPlanDepreciacion;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.basicos.ServicioTelefono;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.basicos.ServiciosDeduciblesEmitidas;
import confia.servicios.transaccionales.ServicioCaracteristicasVehiculos;
import confia.servicios.transaccionales.ServicioCorrespondencia;
import confia.servicios.transaccionales.ServicioCotizacion;
import confia.servicios.transaccionales.ServicioDetalleFormaPago;
import confia.servicios.transaccionales.ServicioFormaPago;
import confia.servicios.transaccionales.ServicioObjetoCotizacion;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.transaccionales.ServicioSubObjetoCotizacion;
import confia.servicios.transaccionales.ServicioUbicacion;
import confia.servicios.vistas.ServicioCoberturasRamoAsegView;
import confia.servicios.vistas.ServicioCotizacionesPendientes;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@ManagedBean(name = "ControladorEmisionMensualizado")
@ViewScoped
public class ControladorEmisionMensualizado {
	@EJB
	private ServicioUsuarios srvUsuario;
	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioSubagentes srvSubagentes;
	@EJB
	private ServicioUsuarios srvUsr;
	@EJB
	private ServicioAseguradoraRamo srvAsegramo;
	@EJB
	private ServicioPlan srvPlan;
	@EJB
	private ServicioEjecutivos srvEjecutivos;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioUbicacion srvUbicacion;
	@EJB
	private ServicioSubObjetoCotizacion srvSubObjetoCotizacion;
	@EJB
	private ServicioObjetoCotizacion srvObjetoCotizacion;
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;
	@EJB
	private ServicioCotizacion srvCotizacion;
	@EJB
	private ServicioCaracteristicasVehiculos srvCaracteristicasVehiculos;
	@EJB
	private ServicioMarca srvMarcas;
	@EJB
	private ServicioModelo srvModelos;
	@EJB
	private ServicioDispositivos srvDispositivos;
	@EJB
	private ServicioCotizacionesPendientes srvCotizacionesPendientes;
	@EJB
	private ServicioCoberturasRamoAsegView srvCoberturasAdicionalesView;
	@EJB
	private ServicioCoberturasAdicionales srvCoberturasAdicionalesNegocio;
	@EJB
	private ServicioFormaPago srvFormaPago;
	@EJB
	private ServicioDetalleFormaPago srvDetalleFrmPago;
	@EJB
	private ServicioNacionalidad srvNacionalidad;
	@EJB
	private ServicioCoberturasEmitidas srvCoberturasEmitidas;
	@EJB
	private ServicioClausulasEmitidas srvClausulasEmitidas;
	@EJB
	private ServiciosDeduciblesEmitidas srvDeduciblesEmitidas;
	@EJB
	private ServicioCoberturas srvCoberturas;
	@EJB
	private ServicioDeducibles srvDeducibles;
	@EJB
	private ServicioClausulas srvClausulas;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioCiudad srvCiudad;
	@EJB
	private ServicioDireccion srvDireccion;
	@EJB
	private ServicioTelefono srvTelefono;
	@EJB
	private ServicioContacto srvContacto;
	@EJB
	private ServicioRubros srvRubrosCartas;
	@EJB
	private ServicioContacto srvContactosCarta;
	@EJB
	private ServicioCorrespondencia srvCorrespondencia;
	@EJB
	private ServicioPlanDepreciacion srvPlanDepreciacion;

	private servicioProcedures srvProcedures;
	private subeArchivo archivoAdjunto;

	private String codigoCompania;
	private String numCotizacion;
	private String vigenciaDesde;
	private String vigenciaHasta;
	private Integer ramoaseg_id;
	private Integer ejecutivo_id;
	private Integer grupoContratante_id;
	private Integer cd_cliente;
	private Integer nombre_cliente;
	private Boolean factPeriodica;

	private Integer identificacion_asegurado;
	private Integer identificacion_cliente;
	private Integer identificacion_subagente;

	private List<Clientes> listaClientes;
	private Clientes datosCliente;
	private Clientes datosClienteSol;
	private Clientes nuevoCliente;
	private String str_cliente;
	private String usuario;
	private List<Nacionalidad> lstNacionalidad;
	private Direccion direccion;
	private Telefono telefono;
	private Contacto contacto;
	private List<Ciudad> lstCiudad;
	private List<Rubros> lsrRubroSectorDirec;

	private List<Subagentes> listaSubagentes;
	private Subagentes datosSubagente;
	private String str_subagente;
	private Integer cd_subagente;

	private boolean mismoSolicitante;
	private Date fechaActual;

	private List<CotizacionesPendientes> lstCotizacionPendienteSelec;
	private List<CotizacionesPendientes> lstEmitirPoliza;
	private List<CotizacionesPendientes> lstCotizacioneesPendientes;
	private CotizacionesPendientes SelectedCotizacioneesPendientes;
	private String apellidoRazonSocial;
	private CotizacionesPendientes cotizacionPendienteSelected;
	private Date fcDesde;
	private Date fcHasta;
	public SimpleDateFormat formato;
	public String patron = "dd/MMM/yyyy";
	private String txtCd_rubro;

	private List<AseguradoraRamo> listadoAsegRamo;
	private String aseguradora;

	private List<Plan> listaPlan;
	private Integer cd_ramoaseg;

	private List<Ejecutivos> listaEjecutivos;
	private List<GrupoContratante> listaGrupoContratante;
	private List<Aseguradoras> listadoAseguradoras;
	private List<CoberturasRamoAsegView> lstCaoberturas_adc;
	private List<CoberturasRamoAsegView> lstCaoberturas_adcUbc;
	private CoberturasRamoAsegView selectedLstCobAdc;
	private CoberturasRamoAsegView selectedLstCobAdcUbc;

	private String ubicacion;
	private Integer cd_ramo_cotizacion;
	private List<Ubicacion> listadoUbicaciones;
	private List<ObjetoCotizacion> lstObjetoCot;
	private List<SubObjetoCotizacion> lstSubObjetoCot;
	private List<SubObjetoCotizacion> lstSubObjetoCons;
	private List<Dispositivos> listaDispositivos;
	private List<ObjetoCotizacion> lstObjetoConsulta;
	private ObjetoCotizacion selectedObjetoConsulta;
	private List<SubObjetoCotizacion> lstSubObjetoConsulta;

	private Cotizacion datosCotizacion;
	private Ubicacion datosUbicacion;
	private Ubicacion selectedDatosUbicacion;
	private RamoCotizacion datosRamoCotizacion;
	private String codRamo;
	private String codEjecutivo;
	private String codPlan;
	private String codGrupoEconomico;
	private String dscUbicacion;
	private String auxEstadoUbicacion;

	private ObjetoCotizacion datosObjetoCotizacion;
	private ObjetoCotizacion SelectedDatosObjetoCotizacion;

	private SubObjetoCotizacion datosSubObjetoCotizacion;
	private Integer exCantidad;
	private String exDetalle;
	private Double exValorSubobjeto;
	private Double exTasa;
	private Double exFactos;
	private Double exPrima;

	private CaracteristicasVehiculos datosCaracteristicasVehiculos;
	private CaracteristicasObj datosCaracteristicasObj;

	private GrupoContratante datosGrupoContratante;

	private List<Marca> listadoMarcas;
	private List<Modelo> listadoModelos;
	private String factorObjeto;

	// varialbes coberturas adicionales
	private Double valorLimiteCob;
	private Double tasaCob;
	private Integer factorCob;
	private Double valorPrimaCob;
	private boolean afecValAsegCob;
	private boolean afecPrimaCob;
	private boolean afecAdicionalCob;
	private List<CoberturasAdicionales> lstCobAdc;
	private List<CoberturasAdicionales> lstCobAdcUbc;
	private List<CoberturasAdicionales> lstCobAdcUbcDel;
	private CoberturasAdicionales selectedCobAdcUbc;
	private List<CoberturasAdicionales> lstCobAdcObj;
	private CoberturasAdicionales selectedlstCobAdcObj;
	private Double valorLimiteCobUbc;
	private Double tasaCobUbc;
	private Integer factorCobUbc;
	private Double valorPrimaCobUbc;
	private boolean afecValAsegCobUbc;
	private boolean afecPrimaCobUbc;
	private boolean afecAdicionalCobUbc;

	// variables forma de pago
	private String tpFromaPago;
	private Double frmPagoPrimaTot;
	private Double frmPagoDerechoEmision;
	private Double frmPagoSegCampesino;
	private Double frmPagoOtroValor;
	private Double frmPagoSuperBancos;
	private Double frmPagoIva;
	private Double frmPagoTotal;
	private Double frmPagoCuotaIni;
	private Integer frmPagoNumPago;
	private String frmObservaciones;

	private List<FormaPago> lstFrmPago;
	private List<DetalleFormaPago> lstDetFrmPago;
	private boolean aplicaIva;
	private boolean calculaIva;

	// coberturas Emitidas
	private List<CoberturasEmitidas> lstCoberturasEmitidas;
	private CoberturasEmitidas selectedCoberturasEmitidas;
	private List<CoberturasEmitidas> lstFilteredCoberturasEmitidas;
	private List<DeduciblesEmitidas> lstDeducibleEmitida;
	private DeduciblesEmitidas selectedDeducibleEmitida;
	private List<DeduciblesEmitidas> lstFilteredDeducibleEmitida;
	private List<ClausulasEmitidas> lstCalusulaEmitida;
	private ClausulasEmitidas selectedClausulaEmitida;
	private List<ClausulasEmitidas> lstFilteredCalusulaEmitida;

	private Double porcCob;
	private Double valorCob;
	private List<Coberturas> lstCoberturas;
	private List<Coberturas> selectedLstCoberturas;
	private List<Coberturas> lstFilteredCoberturas;
	private List<Deducibles> lstDeducibles;
	private List<Deducibles> selectedlstDeducibles;
	private List<Deducibles> lstFilteredDeducibles;
	private Integer cdRamCotCobAdd;
	private Double porcDedValSin;
	private Double porcDedValAseg;
	private Double valorDedMin;
	private Double valorDedFijo;

	private List<Clausulas> lstClausulas;
	private List<Clausulas> selectedlstClausulas;
	private List<Clausulas> lstFilteredClausulas;
	private Double porcClau;
	private Double valorClau;

	private Boolean flgActivaCotiza;

	// CARTAS
	private List<Rubros> lstRubrosCarta;
	private List<Contacto> lstContactoCarta;
	private String nmCarta;
	private Contacto selectedContactoCarta;
	private String notasAdicionalesCarta;
	private Rubros objCarta;

	private Integer lsnumRenova;

	private String empresaUsuario;
	private String numCotBusq;

	public ControladorEmisionMensualizado() {
		empresaUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("EMPRESA")
				.toString();
		lsnumRenova = 0;
		Rubros objCarta = new Rubros();
		lstRubrosCarta = new ArrayList<Rubros>();
		lstContactoCarta = new ArrayList<Contacto>();
		nmCarta = null;
		notasAdicionalesCarta = null;
		factorObjeto = "100";
		srvClientes = new ServicioClientes();
		srvSubagentes = new ServicioSubagentes();
		srvAsegramo = new ServicioAseguradoraRamo();
		srvPlan = new ServicioPlan();
		srvEjecutivos = new ServicioEjecutivos();
		srvGrupoContratante = new ServicioGrupoContratante();
		srvAseguradoras = new ServicioAseguradoras();
		srvDispositivos = new ServicioDispositivos();

		srvUbicacion = new ServicioUbicacion();
		srvSubObjetoCotizacion = new ServicioSubObjetoCotizacion();
		srvObjetoCotizacion = new ServicioObjetoCotizacion();
		srvRamoCotizacion = new ServicioRamoCotizacion();
		srvCotizacion = new ServicioCotizacion();
		srvCaracteristicasVehiculos = new ServicioCaracteristicasVehiculos();

		datosCotizacion = new Cotizacion();
		datosRamoCotizacion = new RamoCotizacion();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosUbicacion = new Ubicacion();
		datosCaracteristicasObj = new CaracteristicasObj();
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		datosGrupoContratante = new GrupoContratante();
		lstCotizacioneesPendientes = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		listaClientes = new ArrayList<Clientes>();
		listaSubagentes = new ArrayList<Subagentes>();
		datosCliente = new Clientes();
		datosClienteSol = new Clientes();
		formato = new SimpleDateFormat(patron);
		fcDesde = new Date();
		fcHasta = new Date();
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		auxEstadoUbicacion = "NuevaUbc";
		lstCaoberturas_adc = new ArrayList<CoberturasRamoAsegView>();
		lstCaoberturas_adcUbc = new ArrayList<CoberturasRamoAsegView>();
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;
		Ubicacion selectedDatosUbicacion = new Ubicacion();
		lstFrmPago = new ArrayList<FormaPago>();
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		srvProcedures = new servicioProcedures();
		nuevoCliente = new Clientes();
		lstNacionalidad = new ArrayList<Nacionalidad>();
		// coberturas emitidas
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstCoberturas = new ArrayList<Coberturas>();
		lstDeducibles = new ArrayList<Deducibles>();
		lstClausulas = new ArrayList<Clausulas>();
		direccion = new Direccion();
		telefono = new Telefono();
		contacto = new Contacto();
		lstCiudad = new ArrayList<Ciudad>();
		lsrRubroSectorDirec = new ArrayList<Rubros>();
		archivoAdjunto = new subeArchivo();
		calculaIva = false;
		factPeriodica = true;
	}

	@PostConstruct
	public void datosIniciales() {
		factorObjeto = "100";
		listaEjecutivos = srvEjecutivos.listaEjecutivos();
		listaGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		listadoAseguradoras = srvAseguradoras.listaAseguradoras();
		listadoMarcas = srvMarcas.listaMarca();
		listaDispositivos = srvDispositivos.listaDispositivos();
		lstNacionalidad = srvNacionalidad.recuperaListaNacionalidad();
		mismoSolicitante = true;
		fechaActual = new Date();
		codRamo = "0";
		codEjecutivo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		flgActivaCotiza = false;
		lstCiudad = srvCiudad.recuperaListaCiudad();
		lsrRubroSectorDirec = srvRubros.listadoRubrosCod("102");
		lstRubrosCarta = srvRubrosCartas.listadoCartasPorModulo("MODULO_EMISION_MENSUALIZADO");
		numCotBusq = "";
	}
	public void rueba() {
		System.out.println("Ingreso al boton");
	}

	public void enceraDatos() {
		srvClientes = new ServicioClientes();
		srvSubagentes = new ServicioSubagentes();
		srvAsegramo = new ServicioAseguradoraRamo();
		srvPlan = new ServicioPlan();
		srvEjecutivos = new ServicioEjecutivos();
		srvGrupoContratante = new ServicioGrupoContratante();
		srvAseguradoras = new ServicioAseguradoras();
		srvDispositivos = new ServicioDispositivos();

		srvUbicacion = new ServicioUbicacion();
		srvSubObjetoCotizacion = new ServicioSubObjetoCotizacion();
		srvObjetoCotizacion = new ServicioObjetoCotizacion();
		srvRamoCotizacion = new ServicioRamoCotizacion();
		srvCotizacion = new ServicioCotizacion();
		srvCaracteristicasVehiculos = new ServicioCaracteristicasVehiculos();

		datosCotizacion = new Cotizacion();
		datosRamoCotizacion = new RamoCotizacion();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosUbicacion = new Ubicacion();
		datosCaracteristicasObj = new CaracteristicasObj();
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		datosGrupoContratante = new GrupoContratante();
		lstCotizacioneesPendientes = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		listaClientes = new ArrayList<Clientes>();
		listaSubagentes = new ArrayList<Subagentes>();
		datosCliente = new Clientes();
		datosClienteSol = new Clientes();
		formato = new SimpleDateFormat(patron);
		fcDesde = new Date();
		fcHasta = new Date();
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		auxEstadoUbicacion = "NuevaUbc";
		lstCaoberturas_adc = new ArrayList<CoberturasRamoAsegView>();
		lstCaoberturas_adcUbc = new ArrayList<CoberturasRamoAsegView>();
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;
		Ubicacion selectedDatosUbicacion = new Ubicacion();
		lstFrmPago = new ArrayList<FormaPago>();
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		datosIniciales();

	}

	public void buscaCotizacion() {
		lstCotizacioneesPendientes = new ArrayList<CotizacionesPendientes>();
		if (apellidoRazonSocial == null || apellidoRazonSocial.equals("")) {
			apellidoRazonSocial = "%";
		}
		if (numCotBusq == null || numCotBusq.equals("")){
			numCotBusq = "%";
		}
		lstCotizacioneesPendientes = srvCotizacionesPendientes.cotizacionesPendientesMensualizado(apellidoRazonSocial,numCotBusq);
	}

	public void recuperaContactosCarta() {
		Integer tamano = lstCotizacionPendienteSelec.size();
		lstContactoCarta = new ArrayList<Contacto>();
		if (tamano == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Cotizaci�n antes de generar la carta"));
			return;
		} else {
			objCarta = new Rubros();
			objCarta = srvRubrosCartas.recuperaCartaPorNombre(nmCarta);
			if (objCarta.getTipo_rubro().equals("ASEG")) {
				lstContactoCarta = srvContactosCarta.listaContactosAseguradora(datosCotizacion.getCd_aseguradora());
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
		carta.setCdCotizacion(datosCotizacion.getCd_cotizacion());
		carta.setCd_compania(datosCotizacion.getCd_compania());
		carta.setCdAseguradora(datosCotizacion.getCd_aseguradora());
		carta.setCdCliente(datosCotizacion.getCd_cliente());
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
		contextMsj.addMessage(null, new FacesMessage("Registro Exitoso", "Se Gener� el Documento N�mero " + numeroCarta
				+ ". Ingrese al M�dulo de Correspondecia para Imprimirlo"));
	}

	public void buscarCotizacionPendiente() {
		Date fcEmiAse, fcDesdePol, fcHastaPol;

		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		datosCliente = new Clientes();
		datosClienteSol = new Clientes();
		datosCotizacion = new Cotizacion();

		// RECUPERO DATOS DE LA COTIZACION
		try {
			codigoCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
					.toString();
		} catch (Exception e) {
			codigoCompania = "1";
		}

		System.out.println("Compania:" + codigoCompania);
		Integer cdCotizacion = 0;
		cdCotizacion = SelectedCotizacioneesPendientes.getCd_cotizacion();
		System.out.println("COTIZXACION:" + cdCotizacion);

		datosCotizacion = srvCotizacion.recuperaCotizacionMensualPorCodigo(cdCotizacion,
				Integer.parseInt(codigoCompania));
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());
		listarRamosAseguradora();
		numCotizacion = datosCotizacion.getNum_cotizacion();
		if (datosCotizacion.getFact_periodica_cot().equals(0)) {
			factPeriodica = false;
		} else {
			factPeriodica = true;
		}
		datosCotizacion.setTipo_cliente("MENSUALIZADO");
		// RECUPERO DATOS DEL CLIENTE

		datosCliente = srvClientes.listaClientesXId(datosCotizacion.getCd_cliente());
		try {
			if (datosCliente.getRazon_social_cliente() == null || datosCliente.getRazon_social_cliente().isEmpty()) {
				datosCliente.setRazon_social_cliente(
						datosCliente.getPrimer_nombre_cliente() + ' ' + datosCliente.getPrimer_apellido_cliente());
			}
		} catch (Exception e) {
			datosCliente.setRazon_social_cliente(
					datosCliente.getPrimer_nombre_cliente() + ' ' + datosCliente.getPrimer_apellido_cliente());
		}

		// RECUPERO DATOS CLIENTE ASEGURADRO

		datosClienteSol = srvClientes.listaClientesXId(datosCotizacion.getCd_cliente_asegurado());
		try {
			datosClienteSol.setRazon_social_cliente(datosClienteSol.getRazon_social_cliente().trim());
		} catch (Exception ex) {
			//
		}
		if (datosClienteSol.getRazon_social_cliente() == null || datosClienteSol.getRazon_social_cliente().isEmpty()) {
			datosClienteSol.setRazon_social_cliente(
					datosClienteSol.getPrimer_nombre_cliente() + ' ' + datosClienteSol.getPrimer_apellido_cliente());
		}
		// RECUPERO FECHAS INGRESADAS EN EL RAMO COTIZACI�N
		RamoCotizacion ramCotAux = new RamoCotizacion();
		ramCotAux = srvRamoCotizacion.obtieneIdRamoCotizacionXCotizacion(datosCotizacion.getCd_cotizacion());
		fcEmiAse = ramCotAux.getFc_emision_aseguradora_date();
		fcDesdePol = ramCotAux.getFc_ini_vig_date();
		fcHastaPol = ramCotAux.getFc_fin_vig_date();
		fechaActual = fcEmiAse;
		fcDesde = fcDesdePol;
		fcHasta = fcHastaPol;

		txtCd_rubro = String.valueOf(datosCotizacion.getCd_rubro());
		codEjecutivo = String.valueOf(ramCotAux.getCd_ejecutivo());
		flgActivaCotiza = true;

//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wbuscaCotizacion').hide();");
		PrimeFaces.current().executeScript("PF('wbuscaCotizacion').hide();");
	}

	public void nuevaCotizacion() {
		try {
			srvCotizacion.eliminaCotizacion(datosCotizacion);
		} catch (Exception e) {
			// TODO: handle exception
		}

		datosCotizacion = new Cotizacion();
		datosCotizacion.setTipo_cliente("MENSUALIZADO");
		datosRamoCotizacion = new RamoCotizacion();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosUbicacion = new Ubicacion();
		datosCaracteristicasObj = new CaracteristicasObj();
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		datosGrupoContratante = new GrupoContratante();
		lstCotizacioneesPendientes = new ArrayList<CotizacionesPendientes>();
		listaClientes = new ArrayList<Clientes>();
		listaSubagentes = new ArrayList<Subagentes>();
		factPeriodica = true;
		numCotizacion = "";
		datosCliente = new Clientes();
		listaEjecutivos = new ArrayList<Ejecutivos>();
		listaGrupoContratante = new ArrayList<GrupoContratante>();
		listadoAseguradoras = new ArrayList<Aseguradoras>();
		listadoMarcas = new ArrayList<Marca>();
		listaDispositivos = new ArrayList<Dispositivos>();
		datosIniciales();
		codRamo = "0";
		codEjecutivo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		auxEstadoUbicacion = "NuevaUbc";
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nuevoRamo() {
		codRamo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		datosSubagente = new Subagentes();
		datosRamoCotizacion = new RamoCotizacion();
		asignaVigencia();
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;
		datosCotizacion.setTipo_cliente("MENSUALIZADO");

	}
	public void buscarClieAseg() {
		if (str_cliente == null) {
			str_cliente = "";
		}
		listaClientes = srvClientes.listaClientes(str_cliente.toUpperCase(),empresaUsuario);
		
	}
	public void buscarClientes() {
		int res;
		System.out.println("INGRESO EL CLIENTE:" + nuevoCliente.getIdentificacion_cliente());
		if (str_cliente == null) {
			str_cliente = "";
		}
		res = srvClientes.verificaExisteClientes(nuevoCliente.getIdentificacion_cliente());
		System.out.println("# CLIENTE:"+res);
		if (res > 0) {
			datosCliente = new Clientes();
			datosCliente = srvClientes.listaClientesXIdentClie(nuevoCliente.getIdentificacion_cliente());
		} else {
			return;
		}

		if (datosCliente.getRazon_social_cliente() == null || datosCliente.getRazon_social_cliente().isEmpty()) {
			datosCliente.setRazon_social_cliente(
					datosCliente.getPrimer_nombre_cliente() + ' ' + datosCliente.getPrimer_apellido_cliente());
		}
		
		datosCliente.setEmpresa(empresaUsuario);
		srvClientes.actualizaClientes(datosCliente);
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "EL Cliente ya existe en la base."));

//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('winsertaCliente').hide();");
		PrimeFaces.current().executeScript("PF('winsertaCliente').hide();");
	}

	public void buscarClientesXId() {
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		identificacion_cliente = Integer.parseInt(requestParameterMap.get("identificacion_cliente"));

		datosCliente = srvClientes.listaClientesXId(identificacion_cliente);

		try {
			datosCliente.setRazon_social_cliente(datosCliente.getRazon_social_cliente().trim());
		} catch (Exception ex) {
			//
		}

		if (datosCliente.getRazon_social_cliente() == null || datosCliente.getRazon_social_cliente().isEmpty()) {
			datosCliente.setRazon_social_cliente(
					datosCliente.getPrimer_nombre_cliente() + ' ' + datosCliente.getPrimer_apellido_cliente());
		}
	}

	public void buscarAseguradosXId() {
		System.out.println("INGRESOOOO CLIENTE ASEGURADO");
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		identificacion_asegurado = Integer.parseInt(requestParameterMap.get("identificacion_asegurado"));

		datosClienteSol = srvClientes.listaClientesXId(identificacion_asegurado);

		try {
			datosClienteSol.setRazon_social_cliente(datosClienteSol.getRazon_social_cliente().trim());
		} catch (Exception ex) {
			//
		}

		if (datosClienteSol.getRazon_social_cliente() == null || datosClienteSol.getRazon_social_cliente().isEmpty()) {
			datosClienteSol.setRazon_social_cliente(
					datosClienteSol.getPrimer_nombre_cliente() + ' ' + datosClienteSol.getPrimer_apellido_cliente());
		}
	}

	public void buscarSubagente() {
		if (str_subagente == null) {
			str_subagente = "%";
		}
		listaSubagentes = srvSubagentes.listaSubagentes(str_subagente.toUpperCase());
	}

	public void buscarSubagenteXId() {
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		identificacion_subagente = Integer.parseInt(requestParameterMap.get("identificacion_subagente"));
		System.out.println("IDENTIFICACION SUBA" + identificacion_subagente);
		datosSubagente = srvSubagentes.buscaSubagenteXId(identificacion_subagente);
		try {
			datosSubagente.setRazonSocial_subagente(datosSubagente.getRazonSocial_subagente().trim());
		} catch (Exception e) {
			datosSubagente.setRazonSocial_subagente(
					datosSubagente.getPrimer_nombre_subagente() + ' ' + datosSubagente.getPrimer_apellido_subagente());
		}
	}

	public void listarRamosAseguradora() {
		listadoAsegRamo = new ArrayList<AseguradoraRamo>();
		listadoAsegRamo = srvAsegramo.listaAseguradoraRamos(datosCotizacion.getCd_aseguradora());
	}

	public void listarPlanes() {
		datosRamoCotizacion.setCd_ramo(Integer.decode(codRamo));
		listaPlan = new ArrayList<Plan>();
		System.out.println("INGRESOOO" + datosRamoCotizacion.getCd_ramo());
		listaPlan = srvPlan.listaPlanes(String.valueOf(datosRamoCotizacion.getCd_ramo()),
				String.valueOf(datosCotizacion.getCd_aseguradora()));
	}

	public void listaUbicaciones() {
		listadoUbicaciones = srvUbicacion.listarUbicaciones(cd_ramo_cotizacion);
	}

	public void listaModelos() {
		listadoModelos = srvModelos.listaModelo(datosCaracteristicasVehiculos.getCd_marca());
	}

	public void asignaNumRenova() {
		// numero de renovaciones
		System.out.println("Numero Renovacion:" + lsnumRenova);
		datosCotizacion.setNum_renovaciones_cot(lsnumRenova);
	}

	public void asignaVigencia() {
		System.out.println("FECHA DESDE:" + fcDesde);
		System.out.println("FECHA HASTA:" + fcHasta);
		datosCotizacion.setFc_ini_cot_date(fcDesde);
		datosCotizacion.setFc_fin_cot_date(fcHasta);
		txtCd_rubro = "8";
		datosCotizacion.setCd_rubro(Integer.parseInt(txtCd_rubro));
		datosRamoCotizacion.setCd_ejecutivo(Integer.decode(codEjecutivo));
	}

	public void asignaRamoCotizacion() {
		datosRamoCotizacion.setCd_plan(Integer.decode(codPlan));
		datosRamoCotizacion.setCd_ejecutivo(Integer.decode(codEjecutivo));
		datosRamoCotizacion.setCd_grupo_contratante(Integer.decode(codGrupoEconomico));
	}

	public void agregaRamo() {
		datosSubagente = new Subagentes();
		datosSubagente = srvSubagentes.consultaSubagente(115);
		System.out.println("CD SUBAGENTE" + datosSubagente.getCd_subagente());
		listarRamosAseguradora();
		PrimeFaces.current().executeScript("PF('nuevoRamo').show();");
	}

	public void guardaRamoCotizacion() {
		String lsCompania;
		Integer liCompania, flgComision = 0;
		lsCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA").toString();
		try {
			liCompania = Integer.parseInt(lsCompania);

		} catch (Exception e) {
			liCompania = 1;
		}
		// verifico que exista comisi�n del subagente
		System.out.println("CD SUBAGENTE" + datosSubagente.getCd_subagente() + " RAMO: " + codRamo);
		flgComision = srvSubagentes.consultaComisionesIngresadas(codRamo,
				String.valueOf(datosSubagente.getCd_subagente()));
		if (flgComision.equals(0)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("No se puede a�adir el Ramo", "Ingrese la Comisi�n del Canal"));
			return;
		}

		if (txtCd_rubro.equals("0")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Tipo"));
			return;
		}
		// verifica el numero de ramos ingresados en la cotizacion
		Integer numRow = 0;
		numRow = lstCotizacionPendienteSelec.size();
		System.out.println("# de Ramos:" + numRow);
		System.out.println("---------------- Num_cotizacion:" + datosCotizacion.getNum_cotizacion());
		if (datosCotizacion.getNum_cotizacion() == null) {
			// ingresa nueva cotizaci�n
			datosCotizacion.setCd_compania(liCompania);
			if (datosCliente.getCd_cliente() == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Solicitante"));
				return;
			} else {
				datosCotizacion.setCd_cliente(datosCliente.getCd_cliente());
			}
			if (mismoSolicitante == true) {
				datosCotizacion.setCd_cliente_asegurado(datosCliente.getCd_cliente());
			} else {
				if (datosClienteSol.getCd_cliente() == null) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Asegurado"));
					return;
				} else {
					datosCotizacion.setCd_cliente_asegurado(datosClienteSol.getCd_cliente());
				}
			}
			if (factPeriodica == true) {
				datosCotizacion.setFact_periodica_cot(1);
			} else {
				datosCotizacion.setFact_periodica_cot(0);
			}

			Integer usrId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("USUARIO");
			System.out.println("USUARIO:" + usrId);
			datosCotizacion.setUsrid(usrId);
			// numero de renovaciones
			System.out.println("Numero Renovacion:" + lsnumRenova);
			datosCotizacion.setNum_renovaciones_cot(lsnumRenova);

			Integer cdCotMax = srvCotizacion.insertarCotizacion(datosCotizacion);
			if (cdCotMax == 1) {
				cdCotMax = srvCotizacion.codigoMaxCotizacion();
			}
			datosCotizacion = new Cotizacion();
			System.out.println("INGRESOOOOO  guardaRamoCotizacion");

			datosCotizacion = srvCotizacion.recuperaCotizacionPorCodigo(cdCotMax, liCompania);
			numCotizacion = datosCotizacion.getNum_cotizacion();
			// INGRESO RAMO
			datosRamoCotizacion.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
			datosRamoCotizacion.setCd_compania(datosCotizacion.getCd_compania());
			System.out.println("FEHCA DESDE:" + fcDesde);
			System.out.println("FEHCA hasta:" + fcHasta);
			datosRamoCotizacion.setFc_ini_vig_date(fcDesde);
			datosRamoCotizacion.setFc_fin_vig_date(fcHasta);
			datosRamoCotizacion.setFc_emision_aseguradora_date(fechaActual);
			System.out.println("CD SUBAGENTE" + datosSubagente.getCd_subagente());
			datosRamoCotizacion.setCd_subagente(datosSubagente.getCd_subagente());
			Integer resInsert = srvRamoCotizacion.insertarRamoCotizacion(datosRamoCotizacion);
			if (resInsert == 1) {
				// recupero los ramos cotizaci�n
				lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
				lstCotizacionPendienteSelec = srvCotizacionesPendientes
						.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());
			}
			// inserto COBERTURAS, clausulas y deducibles
			srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
					String.valueOf(datosCotizacion.getCd_compania()));
			System.out.println("RAMO----->" + datosRamoCotizacion.getCd_ramo());
			System.out.println("plan----->" + datosRamoCotizacion.getCd_plan());
			System.out.println("Subagente----->" + datosRamoCotizacion.getCd_subagente());
			System.out.println("grupo contratante----->" + datosRamoCotizacion.getCd_grupo_contratante());
		} else {
			int res;
			datosRamoCotizacion.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
			datosRamoCotizacion.setCd_compania(datosCotizacion.getCd_compania());
			datosRamoCotizacion.setFc_ini_vig_date(datosCotizacion.getFc_ini_cot_date());
			datosRamoCotizacion.setFc_fin_vig_date(datosCotizacion.getFc_fin_cot_date());
			datosRamoCotizacion.setFc_emision_aseguradora_date(fechaActual);
			datosRamoCotizacion.setCd_subagente(datosSubagente.getCd_subagente());

			System.out.println("RAMO----->" + datosRamoCotizacion.getCd_ramo());
			System.out.println("plan----->" + datosRamoCotizacion.getCd_plan());
			System.out.println("Subagente----->" + datosRamoCotizacion.getCd_subagente());
			System.out.println("grupo contratante----->" + datosRamoCotizacion.getCd_grupo_contratante());

			srvRamoCotizacion.saveDetached(datosRamoCotizacion);
			// res = srvRamoCotizacion.insertarRamoCotizacion(nuevoRamoAux);
			// System.out.println("RESULTADO INSERT:"+res);
			// if (res == 1) {
			// recupero los ramos cotizaci�n
			lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
			lstCotizacionPendienteSelec = srvCotizacionesPendientes
					.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());
			// }
			// inserto las clausulas y deducibles
			srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
					String.valueOf(datosCotizacion.getCd_compania()));
		}

	}

	public void agregaUbicacion() {
		// funcion cuando presiona el boton agregar nueva Ubicación
		int ramoCotiza;
		try {
			ramoCotiza = cotizacionPendienteSelected.getCd_ramo_cotizacion();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Ramo"));
			PrimeFaces.current().executeScript("PF('nuevaUbicacion').hide();");
			return;
		}
		auxEstadoUbicacion = "NuevaUbc";
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('nuevaUbicacion').show();");
		PrimeFaces.current().executeScript("PF('nuevaUbicacion').show();");
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstSubObjetoCons = new ArrayList<SubObjetoCotizacion>();
		dscUbicacion = null;
	}

	public void aceptaUbicacion() {
		Double primaObjTot = 0.0, totalValorAsegObj = 0.0, primaObjUbc = 0.0, totalValorAsegUbc = 0.0;
		for (ObjetoCotizacion objCot : lstObjetoCot) {
			System.out.println("VALOR: " + primaObjTot);
			primaObjTot = primaObjTot + objCot.getPrima_objeto();
			totalValorAsegObj = totalValorAsegObj + objCot.getTotal_asegurado_objeto();
			System.out.println("VALOR: " + primaObjTot);
		}
		if (dscUbicacion == null) {
			dscUbicacion = "S/N";
		}
		datosUbicacion.setDsc_ubicacion(dscUbicacion.trim().toUpperCase());
		datosUbicacion.setValor_prima_ubicacion(primaObjTot);
		datosUbicacion.setValor_asegurado_ubicacion(totalValorAsegObj);
		srvUbicacion.actualizaUbicacion(datosUbicacion);
		// // Actualiza datos del ramo
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(datosUbicacion.getCd_ramo_cotizacion());
		for (Ubicacion ubc : listadoUbicaciones) {
			primaObjUbc = primaObjUbc + ubc.getValor_prima_ubicacion();
			totalValorAsegUbc = totalValorAsegUbc + ubc.getValor_asegurado_ubicacion();
		}
		RamoCotizacion ramCot = new RamoCotizacion();
		ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(datosUbicacion.getCd_ramo_cotizacion(),
				datosUbicacion.getCd_compania());
		ramCot.setTotal_prima(primaObjUbc);
		ramCot.setTotal_asegurado(totalValorAsegUbc);
		srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('nuevaUbicacion').hide();");
		PrimeFaces.current().executeScript("PF('nuevaUbicacion').hide();");
	}

	public void cargaArchivo() {
		// guarda la ubicacion
		int res;
		if (dscUbicacion == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Digite la Ubicación"));
			return;
		}
		if (auxEstadoUbicacion.equals("NuevaUbc")) {
			datosUbicacion = new Ubicacion();
			datosUbicacion.setCd_compania(cotizacionPendienteSelected.getCd_compania());
			datosUbicacion.setCd_ramo_cotizacion(cotizacionPendienteSelected.getCd_ramo_cotizacion());
			datosUbicacion.setFc_ini_ubc_date(cotizacionPendienteSelected.getFc_ini_vig());
			datosUbicacion.setFc_fin_ubc_date(cotizacionPendienteSelected.getFc_fin_vig());
			datosUbicacion.setFc_ini_ubicacion(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
			datosUbicacion.setFc_fin_ubicacion(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
			datosUbicacion.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
			datosUbicacion.setDsc_ubicacion(dscUbicacion);
			System.out.println("UBICACION" + dscUbicacion);
			res = srvUbicacion.insertarUbicacion(datosUbicacion);
			System.out.println("RESPUESTA:" + res);
			if (res != 1) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicación Comuniquese con el Administrador del Sistema"));
				return;
			}
			res = srvUbicacion.codigoMaxUbc(cotizacionPendienteSelected.getCd_ramo_cotizacion());
			datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(res, cotizacionPendienteSelected.getCd_compania());
			auxEstadoUbicacion = "UsoUbc";
		}
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgCargaArchivo').show();");
		PrimeFaces.current().executeScript("PF('wDlgCargaArchivo').show();");
	}

	public void subirArchivos(FileUploadEvent evt) {
		// UploadedFile miArchivo; // aqui se va a guardar el archivo que
		// escogemos en el componente
		String extension = "";
		SimpleDateFormat formatoDos;
		String patronDos = "dd-MM-yyyy";
		formatoDos = new SimpleDateFormat(patronDos);

		UploadedFile miArchivo = evt.getFile();
		long tamanio = miArchivo.getSize();// tama�o del archivo
		byte[] contenido = miArchivo.getContent();// contenido del archivo
		String tipoDeArchivo = miArchivo.getContentType();// que tipo de
															// archivo
		String nombre = miArchivo.getFileName();
		String ruta;
		extension = nombre.substring(nombre.lastIndexOf('.'));
		nombre = nombre.substring(0, nombre.lastIndexOf('.'));

		nombre = nombre.replaceAll("[^\\p{Alpha}\\p{Digit}]+", "_") + extension;

		System.out.println("-*****------- Tama�o: " + tamanio);
		System.out.println("-*****------- Contenido: " + contenido);
		System.out.println("-*****------- Tipo de Archivo: " + tipoDeArchivo);
		System.out.println("********** Nombre: " + nombre);
		archivoAdjunto.guardarArchivo(nombre, contenido);
		ruta = "C:/java/wildfly-9.0.2.Final/welcome-content/uploads/";
		ruta = ruta.concat(nombre);

		try {
			readXLSXFile(ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// actualiza la lista de objetos ingresados
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosUbicacion.getCd_ubicacion(),
				datosUbicacion.getCd_compania());

		FacesMessage message = new FacesMessage("Succesful", evt.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void readXLSXFile(String path) throws IOException {
		File inputWorkbook = new File(path);
		Workbook w;
		int flagEmpty;
		String valCampo;
		List<SubeArchivoObj> lstSubeArchivoObj = new ArrayList<SubeArchivoObj>();
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				if (i != 0) {
					SubeArchivoObj archivo = new SubeArchivoObj();
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j, i);
						CellType type = cell.getType();
						flagEmpty = 0;
						valCampo = null;

						switch (j) {
						case 0:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTIPO(valCampo);
							}
							break;
						case 1:
							if (type == CellType.EMPTY) {
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDESCRIPCION_OBJETO(valCampo);
							}

							break;
						case 2:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setMONTO_ASEGURADOR_OBJETO(valCampo);
							}
							break;
						case 3:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTASA_OBJETO(valCampo);
							}
							break;
						case 4:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setFACTOR_OBJETO(valCampo);
							}
							break;
						case 5:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPRIMA_OBJETO(valCampo);
							}
							break;
						case 6:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDEDUCIBLE_MINIMO(valCampo);
							}
							break;
						case 7:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setOBSERVACIONES_OBJETO(valCampo);
							}
							break;
						case 8:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTOTAL_ASEGURADOR_OBJETO(valCampo);
							}
							break;
						case 9:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPLACA(valCampo);
							}
							break;
						case 10:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setRANV_CPN(valCampo);
							}
							break;
						case 11:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setMARCA(valCampo);
							}
							break;
						case 12:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setMODELO(valCampo);
							}
							break;
						case 13:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCOLOR(valCampo);
							}
							break;
						case 14:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setNO_DE_MOTOR(valCampo);
							}
							break;
						case 15:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setNO_DE_CHASIS(valCampo);
							}
							break;
						case 16:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setANIO(valCampo);
							}
							break;
						case 17:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDISPOSITIVO(valCampo);
							}
							break;
						case 18:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setENDOSO(valCampo);
							}
							break;
						case 19:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setITEM_ASEGURADORA_SUBOBJETO(valCampo);
							}
							break;
						case 20:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDESCRIPCION_SUBOBJETO(valCampo);
							}
							break;
						case 21:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setVALOR_ASEGURADOR_SUBOBJETO(valCampo);
							}
							break;
						case 22:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTASA_SUBOBJETO(valCampo);
							}
							break;
						case 23:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setFACTOR_SUBOBJETO(valCampo);
							}
							break;
						case 24:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPRIMA_SUBOBJETO(valCampo);
							}
							break;

						}
					}
					lstSubeArchivoObj.add(archivo);
				}
			}

			System.out.println("TAMA�O ARRAY:" + lstSubeArchivoObj.size());
		} catch (BiffException e) {
			e.printStackTrace();
		}
		// guarda objeto asegurados
		String strgAux;
		int res, objaux = 0;
		for (SubeArchivoObj subeArchivoObj : lstSubeArchivoObj) {
			System.out.println("TIPO:" + subeArchivoObj.getTIPO());
			System.out.println("Descripcion Obj:" + subeArchivoObj.getDESCRIPCION_OBJETO());
			System.out.println("Placa:" + subeArchivoObj.getPLACA());
			System.out.println("Descripcion SubObj:" + subeArchivoObj.getDESCRIPCION_SUBOBJETO());
			if (subeArchivoObj.getTIPO().equals("O")) {
				datosObjetoCotizacion = new ObjetoCotizacion();
				datosObjetoCotizacion.setCd_compania(cotizacionPendienteSelected.getCd_compania());
				datosObjetoCotizacion.setFc_ini_obj_cot_date(cotizacionPendienteSelected.getFc_ini_vig());
				datosObjetoCotizacion.setFc_fin_obj_cot_date(cotizacionPendienteSelected.getFc_fin_vig());
				datosObjetoCotizacion.setFc_ini_obj_cot(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
				datosObjetoCotizacion.setFc_fin_obj_cot(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
				datosObjetoCotizacion.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
				datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
				try {
					strgAux = subeArchivoObj.getDESCRIPCION_OBJETO();
				} catch (Exception e) {
					strgAux = "S/N";
				}
				if (strgAux.isEmpty() || strgAux == null) {
					strgAux = "S/N";
				} else {
					datosObjetoCotizacion.setDescripcion_objeto(strgAux);
				}
				try {
					strgAux = subeArchivoObj.getMONTO_ASEGURADOR_OBJETO();
					strgAux = strgAux.replace(",", ".");
				} catch (Exception e) {
					strgAux = "0.00";
				}
				if (strgAux.isEmpty() || strgAux == null) {
					strgAux = "0.00";
				} else {
					datosObjetoCotizacion.setValor_asegurador_objeto(Double.valueOf(strgAux));
				}
				try {
					strgAux = subeArchivoObj.getTASA_OBJETO();
					strgAux = strgAux.replace(",", ".");
				} catch (Exception e) {
					strgAux = "0.00";
				}
				if (strgAux.isEmpty() || strgAux == null) {
					strgAux = "0.00";
				} else {
					datosObjetoCotizacion.setTasa_objeto(Double.valueOf(strgAux));
				}
				try {
					strgAux = subeArchivoObj.getFACTOR_OBJETO();
					strgAux = strgAux.replace(",", ".");
				} catch (Exception e) {
					strgAux = "100";
				}
				if (strgAux.isEmpty() || strgAux == null) {
					strgAux = "100";
				} else {
					datosObjetoCotizacion.setFactor_objeto(Double.valueOf(strgAux));
				}
				try {
					strgAux = subeArchivoObj.getPRIMA_OBJETO();
					strgAux = strgAux.replace(",", ".");
				} catch (Exception e) {
					strgAux = "0";
				}
				if (strgAux.isEmpty() || strgAux == null) {
					strgAux = "0";
				} else {
					datosObjetoCotizacion.setPrima_objeto(Double.valueOf(strgAux));
				}
				try {
					strgAux = subeArchivoObj.getDEDUCIBLE_MINIMO();
					strgAux = strgAux.replace(",", ".");
				} catch (Exception e) {
					strgAux = "0";
				}
				if (strgAux.isEmpty() || strgAux == null) {
					strgAux = "0";
				} else {
					datosObjetoCotizacion.setDeducibleMinimo(Double.valueOf(strgAux));
				}
				try {
					strgAux = subeArchivoObj.getTOTAL_ASEGURADOR_OBJETO();
					strgAux = strgAux.replace(",", ".");
				} catch (Exception e) {
					strgAux = "0";
				}
				if (strgAux.isEmpty() || strgAux == null) {
					strgAux = "0";
				} else {
					datosObjetoCotizacion.setTotal_asegurado_objeto(Double.valueOf(strgAux));
				}
				try {
					strgAux = subeArchivoObj.getOBSERVACIONES_OBJETO();
					if (strgAux == null) {
						strgAux = "S/N";
					}
					datosObjetoCotizacion.setObservaciones_objeto(strgAux);
				} catch (Exception e) {
					strgAux = "S/N";
				}
				res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al importar el Archivo Comuníquese con el Administrador del Sistema"));
					return;
				}
				objaux = srvObjetoCotizacion.codigoMaxObjetoCot(datosObjetoCotizacion.getCd_ubicacion());
				System.out.println("CD_OBJ_COTIZACION:" + objaux);
				// inserta caracteristicas del objeto
				datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
				try {
					strgAux = subeArchivoObj.getPLACA();
					if (strgAux == null) {
						strgAux = "S/N";
					}
				} catch (Exception e) {
					strgAux = "S/N";
				}
				if (!strgAux.equals("S/N")) {
					datosCaracteristicasVehiculos.setCd_compania(cotizacionPendienteSelected.getCd_compania());
					datosCaracteristicasVehiculos.setCd_obj_cotizacion(objaux);
					datosCaracteristicasVehiculos.setPlaca(strgAux);
					try {
						strgAux = subeArchivoObj.getRANV_CPN();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setRanv_cpn(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}

					try {
						strgAux = subeArchivoObj.getMARCA();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setMarca(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					try {
						strgAux = subeArchivoObj.getMODELO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setModelo(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					try {
						strgAux = subeArchivoObj.getCOLOR();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setColor(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					try {
						strgAux = subeArchivoObj.getNO_DE_MOTOR();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setNo_de_motor(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					try {
						strgAux = subeArchivoObj.getNO_DE_CHASIS();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setNo_de_chasis(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					try {
						strgAux = subeArchivoObj.getANIO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setAnio_de_fabricacion(Integer.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "S/N";
					}
					try {
						strgAux = subeArchivoObj.getDISPOSITIVO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setDispositivo(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					try {
						strgAux = subeArchivoObj.getENDOSO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setEndoso(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					res = srvCaracteristicasVehiculos.insertaCaracteristicaVehiculos(datosCaracteristicasVehiculos);
					if (res == 0) {
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Advertencia",
								"Error al importar el Archivo Comuníquese con el Administrador del Sistema"));
						return;
					}
				}
			} else {
				// inserto subojetos
				if (objaux == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
							new FacesMessage("Advertencia", "Error al importar el Archivo. Revise el archivo Excel"));
					return;
				} else {
					SubObjetoCotizacion subObj = new SubObjetoCotizacion();
					subObj.setCd_compania(cotizacionPendienteSelected.getCd_compania());
					subObj.setCd_obj_cotizacion(objaux);
					try {
						strgAux = subeArchivoObj.getITEM_ASEGURADORA_SUBOBJETO();
						if (strgAux == null) {
							strgAux = "0";
						}
						subObj.setItem_aseguradora_subobjeto(Integer.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
					}
					try {
						strgAux = subeArchivoObj.getDESCRIPCION_SUBOBJETO().trim().toUpperCase();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						subObj.setDescripcion_subobjeto(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
						subObj.setDescripcion_subobjeto(strgAux);
					}
					try {
						strgAux = subeArchivoObj.getVALOR_ASEGURADOR_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setValor_asegurador_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setValor_asegurador_subobjeto(0.00);
					}
					try {
						strgAux = subeArchivoObj.getTASA_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setTasa_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setTasa_subobjeto(0.00);
					}
					try {
						strgAux = subeArchivoObj.getFACTOR_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setFactor_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setFactor_subobjeto(100.00);
					}
					try {
						strgAux = subeArchivoObj.getPRIMA_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setPrima_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setPrima_subobjeto(0.00);
					}
					res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
					if (res == 0) {
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Advertencia",
								"Error al importar el Archivo Comuníquese con el Administrador del Sistema"));
						return;
					}

				}

			}
		}
	}

	public void grabaUbicacion() {
		int res;
		listadoMarcas = new ArrayList<Marca>();
		listadoModelos = new ArrayList<Modelo>();
		listaDispositivos = new ArrayList<Dispositivos>();
		System.out.println("MARCAS - MODELOS");
		listadoMarcas = srvMarcas.listaMarca();
		listaDispositivos = srvDispositivos.listaDispositivos();

		System.out.println("MARCAS - MODELOS:" + listadoMarcas.size());
		System.out.println("UBICACION:" + auxEstadoUbicacion);
		if (dscUbicacion == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Digite la Ubicación"));
			return;
		}
		if (auxEstadoUbicacion.equals("NuevaUbc")) {
			datosUbicacion = new Ubicacion();
			datosUbicacion.setCd_compania(cotizacionPendienteSelected.getCd_compania());
			datosUbicacion.setCd_ramo_cotizacion(cotizacionPendienteSelected.getCd_ramo_cotizacion());
			datosUbicacion.setFc_ini_ubc_date(cotizacionPendienteSelected.getFc_ini_vig());
			datosUbicacion.setFc_fin_ubc_date(cotizacionPendienteSelected.getFc_fin_vig());
			datosUbicacion.setFc_ini_ubicacion(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
			datosUbicacion.setFc_fin_ubicacion(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
			datosUbicacion.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
			datosUbicacion.setDsc_ubicacion(dscUbicacion);
			System.out.println("UBICACION" + dscUbicacion);
			res = srvUbicacion.insertarUbicacion(datosUbicacion);
			System.out.println("RESPUESTA:" + res);
			if (res == 1) {
//				RequestContext context = RequestContext.getCurrentInstance();
//				context.execute("PF('addObjt').show();");
				PrimeFaces.current().executeScript("PF('addObjt').show();");
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicación Comuniquese con el Administrador del Sistema"));
				return;
			}
			res = srvUbicacion.codigoMaxUbc(cotizacionPendienteSelected.getCd_ramo_cotizacion());
			datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(res, cotizacionPendienteSelected.getCd_compania());
			auxEstadoUbicacion = "UsoUbc";
		}

//		RequestContext contextObj = RequestContext.getCurrentInstance();
//		contextObj.execute("PF('addObjt').show()");
		PrimeFaces.current().executeScript("PF('addObjt').show()");
	}

	public void grabaObjetoAsegurado() {
		Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;
		String descObjeto;
		int res, objaux;
		datosObjetoCotizacion.setCd_compania(cotizacionPendienteSelected.getCd_compania());
		datosObjetoCotizacion.setFc_ini_obj_cot_date(cotizacionPendienteSelected.getFc_ini_vig());
		datosObjetoCotizacion.setFc_fin_obj_cot_date(cotizacionPendienteSelected.getFc_fin_vig());
		datosObjetoCotizacion.setFc_ini_obj_cot(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
		datosObjetoCotizacion.setFc_fin_obj_cot(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
		datosObjetoCotizacion.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
		datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
		datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorObjeto));
		descObjeto = datosObjetoCotizacion.getDescripcion_objeto();
		if (descObjeto == null) {
			descObjeto = "S/N";
		}
		datosObjetoCotizacion.setDescripcion_objeto(descObjeto.trim().toUpperCase());
		primaTotaObj = datosObjetoCotizacion.getPrima_objeto();
		totalAseguradoObj = datosObjetoCotizacion.getValor_asegurador_objeto();
		res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al ingresar el Objeto Comuníquese con el Administrador del Sistema"));
			return;
		}
		res = srvObjetoCotizacion.codigoMaxObjetoCot(datosObjetoCotizacion.getCd_ubicacion());
		objaux = res;
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(res,
				cotizacionPendienteSelected.getCd_compania());
		// GUARDA DATOS DE LOS EXTRAS EN CASO DE EXISTIR
		if (lstSubObjetoCot.size() > 0) {
			try {
				System.out.println("subOBJETOS INGRESADO:" + lstSubObjetoCot.size());
				System.out.println("primaTotal:" + primaTotaObj);
				for (SubObjetoCotizacion subObj : lstSubObjetoCot) {
					primaTotaObj = primaTotaObj + subObj.getPrima_subobjeto();
					totalAseguradoObj = totalAseguradoObj + subObj.getValor_asegurador_subobjeto();
					System.out.println("SUBOBJE primaTotal:" + primaTotaObj);
					subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
					subObj.setFc_ini_subobj_cot(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
					subObj.setFc_fin_subobj_cot(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
					subObj.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
					res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
					if (res == 0) {
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Advertencia",
								"Error al ingresar el Objeto Comuníquese con el Administrador del Sistema"));
						return;
					}
				}
			} catch (Exception e) {
				System.out.println("NO TIENE SUBOBJETOS");
			}
		}

		// actualizo la prima del subobjeto con la sumatoria de las primas de
		// los extras

		datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
		datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
		srvObjetoCotizacion.actualizaObjetoCotizacion(datosObjetoCotizacion);
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(objaux,
				cotizacionPendienteSelected.getCd_compania());
		// GUARDO LAS CARACTERISTICAS DEL VEHICULO
		try {
			datosCaracteristicasVehiculos.getPlaca();
			datosCaracteristicasVehiculos.setCd_compania(datosObjetoCotizacion.getCd_compania());
			datosCaracteristicasVehiculos.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
			res = srvCaracteristicasVehiculos.insertaCaracteristicaVehiculos(datosCaracteristicasVehiculos);
			if (res == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar las caracteristicas del Objeto Comuníquese con el Administrador del Sistema"));
				return;
			}
		} catch (Exception e) {
			// TODO: No se ingreso las caracteristicas dle vehiculo
			System.out.println("NO TIENE CARACTERISTICAS");
		}
		// actualiza la lista de objetos ingresados
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosObjetoCotizacion.getCd_ubicacion(),
				datosObjetoCotizacion.getCd_compania());
		// encera valores
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('addObjt').hide();");
		PrimeFaces.current().executeScript("PF('addObjt').hide();");
	}

	public void agregarExtras() {
		System.out.println("item:" + exCantidad);
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		datosSubObjetoCotizacion.setCd_compania(cotizacionPendienteSelected.getCd_compania());
		datosSubObjetoCotizacion.setItem_aseguradora_subobjeto(exCantidad);
		datosSubObjetoCotizacion.setDescripcion_subobjeto(exDetalle);
		datosSubObjetoCotizacion.setFc_ini_subobj_cot_date(cotizacionPendienteSelected.getFc_ini_vig());
		datosSubObjetoCotizacion.setFc_fin_subobj_cot_date(cotizacionPendienteSelected.getFc_fin_vig());
		datosSubObjetoCotizacion.setValor_asegurador_subobjeto(exValorSubobjeto);
		datosSubObjetoCotizacion.setFc_ini_subobj_cot(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
		datosSubObjetoCotizacion.setFc_fin_subobj_cot(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
		datosSubObjetoCotizacion.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
		datosSubObjetoCotizacion.setTasa_subobjeto(0.00);
		datosSubObjetoCotizacion.setFactor_subobjeto(0.00);
		datosSubObjetoCotizacion.setPrima_subobjeto(0.00);

		lstSubObjetoCot.add(datosSubObjetoCotizacion);
		exCantidad = 0;
		exDetalle = null;
		exValorSubobjeto = 0.0;
		exTasa = 0.0;
		exPrima = 0.0;
	}

	public void DeleteLstExtra() {
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		exCantidad = 0;
		exDetalle = null;
		exValorSubobjeto = 0.0;
		exTasa = 0.0;
		exPrima = 0.0;
	}

	public void onRowSelect(SelectEvent event) {
		int cdobjCo, cdComp;
		cdobjCo = ((ObjetoCotizacion) event.getObject()).getCd_obj_cotizacion();
		cdComp = ((ObjetoCotizacion) event.getObject()).getCd_compania();
		lstSubObjetoCons = srvSubObjetoCotizacion.recuperaSubObjCot(cdobjCo, cdComp);
		// FacesMessage msg = new FacesMessage("Car Selected", ((Car)
		// event.getObject()).getId());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelectRamCot(SelectEvent event) {
		lstObjetoConsulta = new ArrayList<ObjetoCotizacion>();
		lstSubObjetoConsulta = new ArrayList<SubObjetoCotizacion>();
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion
				.listarUbicaciones(((CotizacionesPendientes) event.getObject()).getCd_ramo_cotizacion());
	}

	public void onRowSelectcobAdcUbc(SelectEvent event) {
		System.out.println("Selecciono:" + selectedLstCobAdcUbc.getDesc_cobertura());
	}

	public void calculaPrimaObjeto() {
		Double ldValAseg, ldValPrima, llFactor, ldTasa;
		Integer llDiasVigencia;
		llFactor = Double.valueOf(factorObjeto);
		if (llFactor == null) {
			llFactor = 100.00;
		}
		ldValAseg = datosObjetoCotizacion.getValor_asegurador_objeto();
		if (ldValAseg == null) {
			ldValAseg = 0.0;
		}
		// ldTasa = datosObjetoCotizacion.getTasa_objeto();
		// if (ldTasa == null) {
		// ldTasa = 0.0;
		// }
		// consulto la tasa del objeto en el plan de la depreciaci�n
		ldTasa = srvPlanDepreciacion.consultaTasaObjPlanPrimerAno(cotizacionPendienteSelected.getCd_plan());
		datosObjetoCotizacion.setTasa_objeto(ldTasa);
		System.out.println("TASA DEPRECIACION:" + ldTasa);
		llDiasVigencia = cotizacionPendienteSelected.getDIAS_VIGENCIA();
		System.out.println("dias vigencia:" + llDiasVigencia);
		ldValPrima = (ldValAseg * (ldTasa / llFactor)) / 365 * llDiasVigencia;
		datosObjetoCotizacion.setPrima_objeto(ldValPrima);
	}

	public void calculaPrimaSubObjeto() {
		Double ldValAseg, ldValPrima, llFactor, ldTasa;
		Integer llDiasVigencia;
		llFactor = exFactos;
		if (llFactor == null) {
			llFactor = 100.00;
		}
		ldValAseg = exValorSubobjeto;
		if (ldValAseg == null) {
			ldValAseg = 0.0;
		}
		// ldTasa = exTasa;
		// if (ldTasa == null) {
		// ldTasa = 0.0;
		// }
		ldTasa = srvPlanDepreciacion.consultaTasaObjPlanPrimerAno(cotizacionPendienteSelected.getCd_plan());
		exTasa = ldTasa;
		System.out.println("TASA DEPRECIACION:" + ldTasa);
		llDiasVigencia = cotizacionPendienteSelected.getDIAS_VIGENCIA();
		ldValPrima = (ldValAseg * (ldTasa / llFactor)) / 365 * llDiasVigencia;
		exPrima = ldValPrima;
	}

	public void delCobAdcObj() {
		System.out.println("INGRESO ELIMINA ADICIONAL Objeto ");
		// recupero las coberturas ingresada al objeto
		lstCobAdcObj = new ArrayList<CoberturasAdicionales>();
		lstCobAdcObj = srvCoberturasAdicionalesNegocio.recuperaCoberturasObjeto(
				SelectedDatosObjetoCotizacion.getCd_compania(), SelectedDatosObjetoCotizacion.getCd_obj_cotizacion());
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgDelCoberturaAdcObj').show();");
		PrimeFaces.current().executeScript("PF('wDlgDelCoberturaAdcObj').show();");
	}

	public void eliminaCobAdcObj() {
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		System.err.println("INGRESO A GRABAR");
		ObjetoCotizacion objAuxCob = new ObjetoCotizacion();
		objAuxCob = SelectedDatosObjetoCotizacion;
		// verifico a que valor me va afectar
		if (selectedlstCobAdcObj.getAfecta_prima().equals("SI")) {
			primaAct = objAuxCob.getPrima_objeto();
			primaAct = primaAct - selectedlstCobAdcObj.getVal_prima();
			objAuxCob.setPrima_objeto(primaAct);
			System.out.println("PRIMA AFECTADA:" + primaAct);
		}
		if (selectedlstCobAdcObj.getAfecta_va().equals("SI")) {
			valAsegAct = objAuxCob.getTotal_asegurado_objeto();
			valAsegAct = valAsegAct - selectedlstCobAdcObj.getVal_prima();
			objAuxCob.setTotal_asegurado_objeto(valAsegAct);
			System.out.println("VALOR asEGURADO AFECTADA:" + valAsegAct);
		}
		System.out.println("CD_OBJ_COT:" + objAuxCob.getCd_obj_cotizacion());
		System.out.println("PRIMA:" + objAuxCob.getPrima_objeto());
		System.out.println("TOTAL_ASEG:" + objAuxCob.getTotal_asegurado_objeto());
		srvObjetoCotizacion.actualizaObjetoCotizacion(objAuxCob);

		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(objAuxCob.getCd_ubicacion(),
				objAuxCob.getCd_compania());
		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgDelCoberturaAdcObj').hide();");
//		contextDlg.execute("PF('wCoberturasAdc').hide();");
		PrimeFaces.current().executeScript("PF('wDlgDelCoberturaAdcObj').hide();");
		PrimeFaces.current().executeScript("PF('wCoberturasAdc').hide();");

	}

	public void agregaCoberturaObj() {
		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		lstCaoberturas_adc = new ArrayList<CoberturasRamoAsegView>();
		lstCaoberturas_adc = srvCoberturasAdicionalesView.consultaCoberturasNegocio(
				cotizacionPendienteSelected.getCd_plan(), cotizacionPendienteSelected.getCd_aseguradora());
		System.out.println("COTIACIONES PENDIENTES COB ADC: Plan:" + cotizacionPendienteSelected.getCd_plan());

		int aux = 0;

		try {
			SelectedDatosObjetoCotizacion.getCd_compania();
		} catch (Exception e) {
			aux = 1;
		}
		if (aux == 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Objeto Asegurado"));
			return;
		} else {
//			RequestContext contextDlg = RequestContext.getCurrentInstance();
//			contextDlg.execute("PF('wCoberturasAdc').show();");
			PrimeFaces.current().executeScript("PF('wCoberturasAdc').show();");
		}

		// recupero las coberturas ingresada al objeto
		lstCobAdc = new ArrayList<CoberturasAdicionales>();
		lstCobAdc = srvCoberturasAdicionalesNegocio.recuperaCoberturasObjeto(
				SelectedDatosObjetoCotizacion.getCd_compania(), SelectedDatosObjetoCotizacion.getCd_obj_cotizacion());

	}

	public void agregaCoberturaUbc() {
		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		lstCaoberturas_adcUbc = new ArrayList<CoberturasRamoAsegView>();
		lstCaoberturas_adcUbc = srvCoberturasAdicionalesView.consultaCoberturasNegocio(
				cotizacionPendienteSelected.getCd_plan(), cotizacionPendienteSelected.getCd_aseguradora());
		int aux = 0;

		try {
			cotizacionPendienteSelected.getCd_compania();
		} catch (Exception e) {
			aux = 1;
		}

		if (aux == 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Objeto Asegurado"));
			return;
		} else {
//			RequestContext contextDlg = RequestContext.getCurrentInstance();
//			contextDlg.execute("PF('wCoberturasAdcUbc').show();");
			PrimeFaces.current().executeScript("PF('wCoberturasAdcUbc').show();");
		}
	}

	public void onRowSelectUbc(SelectEvent event) {
		// recupero las coberturas ingresada a la ubicacion
		lstCobAdcUbc = new ArrayList<CoberturasAdicionales>();
		System.out.println("cd_compania:" + selectedDatosUbicacion.getCd_compania());
		System.out.println("cd_ubicacion:" + selectedDatosUbicacion.getCd_ubicacion());
		lstCobAdcUbc = srvCoberturasAdicionalesNegocio.recuperaCoberturasUbicacion(
				selectedDatosUbicacion.getCd_compania(), selectedDatosUbicacion.getCd_ubicacion());
		lstObjetoConsulta = new ArrayList<ObjetoCotizacion>();
		lstSubObjetoConsulta = new ArrayList<SubObjetoCotizacion>();
		lstObjetoConsulta = srvObjetoCotizacion.recuperaObjetosPorUbicacion(selectedDatosUbicacion.getCd_ubicacion(),
				selectedDatosUbicacion.getCd_compania());
	}

	public void onRowSelectObj(SelectEvent event) {
		lstSubObjetoConsulta = new ArrayList<SubObjetoCotizacion>();
		lstSubObjetoConsulta = srvSubObjetoCotizacion.recuperaSubObjCot(selectedObjetoConsulta.getCd_obj_cotizacion(),
				selectedObjetoConsulta.getCd_compania());
	}

	public void guardaCoberturaNegocio() {
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		System.err.println("INGRESO A GRABAR");
		ObjetoCotizacion objAuxCob = new ObjetoCotizacion();
		objAuxCob = SelectedDatosObjetoCotizacion;
		for (CoberturasAdicionales cobA : lstCobAdc) {
			if (cobA.getCd_cob_adc() == null) {
				srvCoberturasAdicionalesNegocio.insertaCoberturasNegocio(cobA);
				// verifico a que valor me va afectar
				if (cobA.getAfecta_prima().equals("SI")) {
					primaAct = objAuxCob.getPrima_objeto();
					primaAct = primaAct + cobA.getVal_prima();
					objAuxCob.setPrima_objeto(primaAct);
					System.out.println("PRIMA AFECTADA:" + primaAct);
				}
				if (cobA.getAfecta_va().equals("SI")) {
					valAsegAct = objAuxCob.getTotal_asegurado_objeto();
					valAsegAct = valAsegAct + cobA.getVal_prima();
					objAuxCob.setTotal_asegurado_objeto(valAsegAct);
					System.out.println("VALOR asEGURADO AFECTADA:" + valAsegAct);
				}
			}
		}
		System.out.println("CD_OBJ_COT:" + objAuxCob.getCd_obj_cotizacion());
		System.out.println("PRIMA:" + objAuxCob.getPrima_objeto());
		System.out.println("TOTAL_ASEG:" + objAuxCob.getTotal_asegurado_objeto());
		srvObjetoCotizacion.actualizaObjetoCotizacion(objAuxCob);

		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(objAuxCob.getCd_ubicacion(),
				objAuxCob.getCd_compania());
		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wCoberturasAdc').hide();");
		PrimeFaces.current().executeScript("PF('wCoberturasAdc').hide();");

	}

	public void delCobAdc() {
		System.out.println("INGRESO ELIMINA ADICIONAL UBICACION ");
		lstCobAdcUbcDel = new ArrayList<CoberturasAdicionales>();
		lstCobAdcUbcDel = srvCoberturasAdicionalesNegocio.recuperaCoberturasUbicacion(
				selectedDatosUbicacion.getCd_compania(), selectedDatosUbicacion.getCd_ubicacion());
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgDelCoberturaAdc').show();");
		PrimeFaces.current().executeScript("PF('wDlgDelCoberturaAdc').show();");
	}

	public void eliminaCoberturaNegocioUbicacion() {
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		Ubicacion objAuxCobUbc = new Ubicacion();
		objAuxCobUbc = selectedDatosUbicacion;

		// verifico a que valor me va afectar
		if (selectedCobAdcUbc.getAfecta_prima().equals("SI")) {
			primaAct = objAuxCobUbc.getValor_prima_ubicacion();
			primaAct = primaAct - selectedCobAdcUbc.getVal_prima();
			objAuxCobUbc.setValor_prima_ubicacion(primaAct);
			System.out.println("PRIMA AFECTADA:" + primaAct);
		}
		if (selectedCobAdcUbc.getAfecta_va().equals("SI")) {
			valAsegAct = objAuxCobUbc.getValor_asegurado_ubicacion();
			valAsegAct = valAsegAct - selectedCobAdcUbc.getVal_prima();
			objAuxCobUbc.setValor_asegurado_ubicacion(valAsegAct);
			System.out.println("VALOR asEGURADO AFECTADA:" + valAsegAct);
		}
		// elimina la cobertura Negocio
		srvCoberturasAdicionalesNegocio.eliminaCoberturasNegocio(selectedCobAdcUbc);

		System.out.println("cd_ubicacion:" + objAuxCobUbc.getCd_ubicacion());
		System.out.println("PRIMA:" + objAuxCobUbc.getValor_prima_ubicacion());
		System.out.println("TOTAL_ASEG:" + objAuxCobUbc.getValor_asegurado_ubicacion());
		srvUbicacion.actualizaUbicacion(objAuxCobUbc);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(cotizacionPendienteSelected.getCd_ramo_cotizacion());
		// actualiza la lista del ramo cotizacion
		Double primaObjUbc = 0.0;
		Double totalValorAsegUbc = 0.0;
		for (Ubicacion ubc : listadoUbicaciones) {
			primaObjUbc = primaObjUbc + ubc.getValor_prima_ubicacion();
			totalValorAsegUbc = totalValorAsegUbc + ubc.getValor_asegurado_ubicacion();
		}
		RamoCotizacion ramCot = new RamoCotizacion();
		ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(objAuxCobUbc.getCd_ramo_cotizacion(),
				objAuxCobUbc.getCd_compania());
		ramCot.setTotal_prima(primaObjUbc);
		ramCot.setTotal_asegurado(totalValorAsegUbc);

		srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		valorLimiteCobUbc = 0.0;
		valorPrimaCobUbc = 0.0;
		tasaCobUbc = 0.0;
		factorCobUbc = 100;
		afecAdicionalCobUbc = false;
		afecPrimaCobUbc = false;
		afecValAsegCobUbc = false;

//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgDelCoberturaAdc').hide();");
//		contextDlg.execute("PF('wCoberturasAdcUbc').hide();");
		PrimeFaces.current().executeScript("PF('wDlgDelCoberturaAdc').hide();");
		PrimeFaces.current().executeScript("PF('wCoberturasAdcUbc').hide();");

	}

	public void guardaCoberturaNegocioUbicacion() {
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		System.err.println("INGRESO A GRABAR");
		Ubicacion objAuxCobUbc = new Ubicacion();
		objAuxCobUbc = selectedDatosUbicacion;
		for (CoberturasAdicionales cobA : lstCobAdcUbc) {
			if (cobA.getCd_cob_adc() == null) {
				System.out.println("****** CODIGO COB ADCI:" + cobA.getCd_cob_adc());
				srvCoberturasAdicionalesNegocio.insertaCoberturasNegocio(cobA);
				// verifico a que valor me va afectar
				if (cobA.getAfecta_prima().equals("SI")) {
					primaAct = objAuxCobUbc.getValor_prima_ubicacion();
					primaAct = primaAct + cobA.getVal_prima();
					objAuxCobUbc.setValor_prima_ubicacion(primaAct);
					System.out.println("PRIMA AFECTADA:" + primaAct);
				}
				if (cobA.getAfecta_va().equals("SI")) {
					valAsegAct = objAuxCobUbc.getValor_asegurado_ubicacion();
					valAsegAct = valAsegAct + cobA.getVal_prima();
					objAuxCobUbc.setValor_asegurado_ubicacion(valAsegAct);
					System.out.println("VALOR asEGURADO AFECTADA:" + valAsegAct);
				}
			}
		}
		System.out.println("cd_ubicacion:" + objAuxCobUbc.getCd_ubicacion());
		System.out.println("PRIMA:" + objAuxCobUbc.getValor_prima_ubicacion());
		System.out.println("TOTAL_ASEG:" + objAuxCobUbc.getValor_asegurado_ubicacion());
		srvUbicacion.actualizaUbicacion(objAuxCobUbc);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(cotizacionPendienteSelected.getCd_ramo_cotizacion());
		// actualiza la lista del ramo cotizacion
		Double primaObjUbc = 0.0;
		Double totalValorAsegUbc = 0.0;
		for (Ubicacion ubc : listadoUbicaciones) {
			primaObjUbc = primaObjUbc + ubc.getValor_prima_ubicacion();
			totalValorAsegUbc = totalValorAsegUbc + ubc.getValor_asegurado_ubicacion();
		}
		RamoCotizacion ramCot = new RamoCotizacion();
		ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(objAuxCobUbc.getCd_ramo_cotizacion(),
				objAuxCobUbc.getCd_compania());
		ramCot.setTotal_prima(primaObjUbc);
		ramCot.setTotal_asegurado(totalValorAsegUbc);
		srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		valorLimiteCobUbc = 0.0;
		valorPrimaCobUbc = 0.0;
		tasaCobUbc = 0.0;
		factorCobUbc = 100;
		afecAdicionalCobUbc = false;
		afecPrimaCobUbc = false;
		afecValAsegCobUbc = false;

//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wCoberturasAdcUbc').hide();");
		PrimeFaces.current().executeScript("PF('wCoberturasAdcUbc').hide();");

	}

	public void eliminaCobObj() {
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wCoberturasAdcUbc').show();");
		PrimeFaces.current().executeScript("PF('wCoberturasAdcUbc').show();");
	}

	public void eliminaCoberturasAdcObjeto() {
		CoberturasAdicionales cobAdcAux = new CoberturasAdicionales();
		cobAdcAux.setCd_obj_cotizacion(SelectedDatosObjetoCotizacion.getCd_obj_cotizacion());
		cobAdcAux.setCd_compania(SelectedDatosObjetoCotizacion.getCd_compania());
		cobAdcAux.setCd_cobertura(selectedLstCobAdc.getCd_cobertura());
		if (afecAdicionalCob == true) {
			cobAdcAux.setAdicional("SI");
		} else {
			cobAdcAux.setAdicional("NO");
		}
		if (afecPrimaCob == true) {
			cobAdcAux.setAfecta_prima("SI");
		} else {
			cobAdcAux.setAfecta_prima("NO");
		}
		if (afecValAsegCob == true) {
			cobAdcAux.setAfecta_va("SI");
		} else {
			cobAdcAux.setAfecta_va("NO");
		}
		cobAdcAux.setVal_limite(valorLimiteCob);
		cobAdcAux.setVal_prima(valorPrimaCob);
		cobAdcAux.setTasa(tasaCob);
		cobAdcAux.setFactor(factorCob);
		cobAdcAux.setDescCobertura(selectedLstCobAdc.getDesc_cobertura());
		System.out.println("descCob:" + selectedLstCobAdc.getDesc_cobertura());
		lstCobAdc.add(cobAdcAux);

		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;

	}

	public void aceptaCoberturasAdcObjeto() {
		CoberturasAdicionales cobAdcAux = new CoberturasAdicionales();
		cobAdcAux.setCd_obj_cotizacion(SelectedDatosObjetoCotizacion.getCd_obj_cotizacion());
		cobAdcAux.setCd_compania(SelectedDatosObjetoCotizacion.getCd_compania());
		cobAdcAux.setCd_cobertura(selectedLstCobAdc.getCd_cobertura());
		if (afecAdicionalCob == true) {
			cobAdcAux.setAdicional("SI");
		} else {
			cobAdcAux.setAdicional("NO");
		}
		if (afecPrimaCob == true) {
			cobAdcAux.setAfecta_prima("SI");
		} else {
			cobAdcAux.setAfecta_prima("NO");
		}
		if (afecValAsegCob == true) {
			cobAdcAux.setAfecta_va("SI");
		} else {
			cobAdcAux.setAfecta_va("NO");
		}
		cobAdcAux.setVal_limite(valorLimiteCob);
		cobAdcAux.setVal_prima(valorPrimaCob);
		cobAdcAux.setTasa(tasaCob);
		cobAdcAux.setFactor(factorCob);
		cobAdcAux.setDescCobertura(selectedLstCobAdc.getDesc_cobertura());
		System.out.println("descCob:" + selectedLstCobAdc.getDesc_cobertura());
		lstCobAdc.add(cobAdcAux);

		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		afecAdicionalCob = false;
		afecPrimaCob = false;
		afecValAsegCob = false;

	}

	public void aceptaCoberturasAdcUbicacion() {
		CoberturasAdicionales cobAdcAux = new CoberturasAdicionales();
		cobAdcAux.setCd_ubicacion(selectedDatosUbicacion.getCd_ubicacion());
		cobAdcAux.setCd_compania(selectedDatosUbicacion.getCd_compania());
		cobAdcAux.setCd_cobertura(selectedLstCobAdcUbc.getCd_cobertura());
		if (afecAdicionalCobUbc == true) {
			cobAdcAux.setAdicional("SI");
		} else {
			cobAdcAux.setAdicional("NO");
		}
		if (afecPrimaCobUbc == true) {
			cobAdcAux.setAfecta_prima("SI");
		} else {
			cobAdcAux.setAfecta_prima("NO");
		}
		if (afecValAsegCobUbc == true) {
			cobAdcAux.setAfecta_va("SI");
		} else {
			cobAdcAux.setAfecta_va("NO");
		}
		cobAdcAux.setVal_limite(valorLimiteCobUbc);
		cobAdcAux.setVal_prima(valorPrimaCobUbc);
		cobAdcAux.setTasa(tasaCobUbc);
		cobAdcAux.setFactor(factorCobUbc);
		cobAdcAux.setDescCobertura(selectedLstCobAdcUbc.getDesc_cobertura());
		System.out.println("descCob:" + selectedLstCobAdcUbc.getDesc_cobertura());
		lstCobAdcUbc.add(cobAdcAux);
		valorLimiteCobUbc = 0.0;
		valorPrimaCobUbc = 0.0;
		tasaCobUbc = 0.0;
		factorCobUbc = 100;
		afecAdicionalCobUbc = false;
		afecPrimaCobUbc = false;
		afecValAsegCobUbc = false;

	}

	public void onRowDeleteObj(ObjetoCotizacion obj) {
		Integer ubc, compania;
		System.out.println("INGRESOOOOO");
		System.out.println("OBJT:" + obj.getDescripcion_objeto());
		ubc = obj.getCd_ubicacion();
		compania = obj.getCd_compania();
		srvObjetoCotizacion.eliminaObjetoCotizacion(obj);
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(ubc, compania);
		lstSubObjetoCons = new ArrayList<SubObjetoCotizacion>();
	}

	public void onRowDeleteUbc(Ubicacion ubc) {
		Integer crc;
		crc = ubc.getCd_ramo_cotizacion();
		srvUbicacion.eliminaUbicacion(ubc);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(crc);
	}

	public void onRowDeleteRamoCot(CotizacionesPendientes ramCot) {
		Integer cdcot;
		cdcot = ramCot.getCd_cotizacion();
		RamoCotizacion crc = new RamoCotizacion();
		crc = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(ramCot.getCd_ramo_cotizacion(),
				ramCot.getCd_compania());
		srvRamoCotizacion.eliminaRamoCotizacion(crc);
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes.cotizacionesPendientesXCdCot(cdcot);
	}

	public void onRowPlanRamCot(CotizacionesPendientes ramCot) {
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();

		lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidas(ramCot.getCd_compania(),
				ramCot.getCd_ramo_cotizacion());
		lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidas(ramCot.getCd_compania(),
				ramCot.getCd_ramo_cotizacion());
		lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(ramCot.getCd_compania(),
				ramCot.getCd_ramo_cotizacion());
		cdRamCotCobAdd = ramCot.getCd_ramo_cotizacion();
		System.out.println("CRC COB CLA DED" + cdRamCotCobAdd);
		PrimeFaces.current().executeScript("PF('wvPlanesRamCot').show();");
	}

	public void onRowEditUbc(Ubicacion ubc) {
		auxEstadoUbicacion = "UsoUbc";
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(ubc.getCd_ubicacion(), ubc.getCd_compania());
		lstSubObjetoCons = new ArrayList<SubObjetoCotizacion>();
		datosUbicacion = new Ubicacion();
		datosUbicacion = ubc;
		dscUbicacion = ubc.getDsc_ubicacion();
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('nuevaUbicacion').show();");
		PrimeFaces.current().executeScript("PF('nuevaUbicacion').show();");
	}

	/////////////////////////////////////////////////////////////////////
	/////////////////// FORMA DE PAGO/////////////////////////////////////
	/*******************************************************************/
	public void agregaFormaPago() {
		int tamano, res;
		tpFromaPago = null;
		frmPagoPrimaTot = 0.0;

		frmPagoOtroValor = 0.0;
		frmPagoIva = 0.0;
		frmPagoTotal = 0.0;
		frmPagoCuotaIni = 0.0;
		frmPagoNumPago = 1;

		tamano = lstCotizacionPendienteSelec.size();
		System.out.println("Tama�o Lista Ramos:"+tamano);
		if (tamano == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Cotizaci�n antes de ingresar la forma de pago"));
			return;
		} else {

			res = srvFormaPago.verificaFormaPago(datosCotizacion.getCd_cotizacion(), datosCotizacion.getCd_compania());
			if (res == 0) {
				System.out.println("INGRESOOOO Genera detalle FRM PAGO:" + datosCotizacion.getCd_cotizacion());
				RamoCotizacion ramCotAux = new RamoCotizacion();
				ramCotAux = srvRamoCotizacion.obtieneIdRamoCotizacionXCotizacion(datosCotizacion.getCd_cotizacion());
				Date fcDesdePol = ramCotAux.getFc_ini_vig_date();
				Date fcHastaPol = ramCotAux.getFc_fin_vig_date();

				frmPagoNumPago = srvFormaPago.numeroAniosVigencia(fcDesdePol, fcHastaPol);
				System.out.println("NUMERO DE A�OS" + frmPagoNumPago);
				guardaFormaPago();
			}

			FormaPago frmA = new FormaPago();
			frmA = srvFormaPago.recuperaFormaPagoxCdCot(datosCotizacion.getCd_cotizacion(),
					datosCotizacion.getCd_compania());
			lstFrmPago = new ArrayList<FormaPago>();
			lstFrmPago.add(frmA);
			lstDetFrmPago = new ArrayList<DetalleFormaPago>();
			lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(frmA.getCd_forma_Pago(), frmA.getCd_compania());

		}
	}

	public void guardaFormaPago() {
		FormaPago formaPagoAux = new FormaPago();
		tpFromaPago = "FACTURACION_MENSUAL";
		System.out.println("INGRESO " + tpFromaPago);
		RamoCotizacion ramCotAux = new RamoCotizacion();
		ramCotAux = srvRamoCotizacion.obtieneIdRamoCotizacionXCotizacion(datosCotizacion.getCd_cotizacion());
		Date fcDesdePol = ramCotAux.getFc_ini_vig_date();
		Date fcHastaPol = ramCotAux.getFc_fin_vig_date();

		frmPagoNumPago = srvFormaPago.numeroAniosVigencia(fcDesdePol, fcHastaPol);
		System.out.println("NUMERO DE Anios:" + frmPagoNumPago);
		frmPagoNumPago = frmPagoNumPago * 12;
		System.out.println("NUMERO DE Cuotas" + frmPagoNumPago);
		formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
		formaPagoAux.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
		formaPagoAux.setCd_compania(datosCotizacion.getCd_compania());

		PlanDepreciacion planDep = new PlanDepreciacion();
		planDep = srvPlanDepreciacion.consultaPlanDepreciacionAnio1(ramCotAux.getCd_plan());
		if(planDep != null){
			formaPagoAux.setSuperBanco_forma_Pago(planDep.getSuperbancos());
			formaPagoAux.setDerecho_Emision_formaPago(planDep.getDerechoemision());
			formaPagoAux.setSeguroCampesion_forma_Pago(planDep.getSegurocampesino());
		} else {
			formaPagoAux.setSuperBanco_forma_Pago(0.00);
			formaPagoAux.setDerecho_Emision_formaPago(0.00);
			formaPagoAux.setSeguroCampesion_forma_Pago(0.00);
		}
		formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
		formaPagoAux.setSin_iva(1);

		int res = 0;
		res = srvFormaPago.insertaFormaPago(formaPagoAux);

		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
			return;
		}
		System.out.println("GENERA CABECERA FORMA PAGO");
		tpFromaPago = null;
		frmPagoPrimaTot = 0.0;

		frmPagoOtroValor = 0.0;
		frmPagoIva = 0.0;
		frmPagoTotal = 0.0;
		frmPagoCuotaIni = 0.0;
		frmPagoNumPago = 0;
		res = srvFormaPago.codigoMaxFormaPago();
		formaPagoAux = new FormaPago();
		formaPagoAux = srvFormaPago.recuperaFormaPagoxCod(res, datosCotizacion.getCd_compania());

		// Ejecuta el procedimiento Almacenado
		Integer proceso;
		proceso = srvProcedures.detallFormaPagoMensualizado(String.valueOf(formaPagoAux.getCd_compania()),
				String.valueOf(formaPagoAux.getCd_forma_Pago()), String.valueOf(formaPagoAux.getNum_pago_formaPago()),
				String.valueOf(formaPagoAux.getCd_cotizacion()),
				String.valueOf(formaPagoAux.getNum_alternativa_formaPago()));
		if (proceso == 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al Grabar la forma de Pago. Comuníquese con el Administrador del Sistema"));
			return;
		}
		lstFrmPago = new ArrayList<FormaPago>();
		lstFrmPago.add(formaPagoAux);
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(formaPagoAux.getCd_forma_Pago(),
				formaPagoAux.getCd_compania());
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(formaPagoAux.getCd_forma_Pago(),
				formaPagoAux.getCd_compania());

		PrimeFaces.current().executeScript("PF('wfrmPago').hide();");
	}

	public void calculaPagoTotal() {

		Double Iva, frmPagoSubtotal;
		Iva = Double.valueOf(srvRubros.recuperaIva());
		frmPagoSubtotal = 0.0;
		System.out.println("APLICA IVA:" + aplicaIva);
		System.out.println("VALOR IVA:" + frmPagoIva);
		if (aplicaIva) {
			frmPagoSubtotal = frmPagoPrimaTot + frmPagoSuperBancos + frmPagoDerechoEmision + frmPagoSegCampesino
					+ frmPagoOtroValor;
			if (calculaIva == true) {
				frmPagoIva = 0.00;
			} else {
				frmPagoIva = frmPagoSubtotal * (Iva / 100);
				frmPagoIva = redondear(frmPagoIva);
			}
			frmPagoTotal = frmPagoSubtotal + frmPagoIva;
			System.out.println("INGRESO:frmPagoSubtotal=" + frmPagoSubtotal + " frmPagoIva=" + frmPagoIva);
		} else {
			frmPagoSubtotal = frmPagoPrimaTot + frmPagoSuperBancos + frmPagoDerechoEmision + frmPagoSegCampesino;
			if (calculaIva == true) {
				frmPagoIva = 0.00;
			} else {
				frmPagoIva = frmPagoSubtotal * (Iva / 100);
				frmPagoIva = redondear(frmPagoIva);
			}
			frmPagoTotal = frmPagoSubtotal + frmPagoIva + frmPagoOtroValor;
			System.out.println("INGRESO:frmPagoSubtotal=" + frmPagoSubtotal + " frmPagoIva=" + frmPagoIva
					+ " frmPagoOtroValor=" + frmPagoOtroValor);
		}

		frmPagoTotal = redondear(frmPagoTotal);

	}

	public void onRowDeleteFrmPag(FormaPago frm) {
		srvFormaPago.eliminaFormaPago(frm);
		lstFrmPago = new ArrayList<FormaPago>();
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
	}

	////////////////////////////////////////////////////////////////
	////////////// EMISION DE POLIZA //////////////////////////////
	//////////////////////////////////////////////////////////////

	public void emitirPoliza() {
		int count = 0;
		// verifico si esta seleccionado el tipo
		if (txtCd_rubro.equals("0")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Tipo antes de Emitir."));
			return;
		}
		if (codEjecutivo.equals("0")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Ejecutivo antes de Emitir."));
			return;
		}

		// verifica si tiene ingresada la forma de pago
		count = lstFrmPago.size();
		if (count == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la forma de Pago antes de Emitir."));
			return;
		}
		count = lstCotizacionPendienteSelec.size();
		if (count == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Ramo antes de Emitir."));
			return;
		}
		lstEmitirPoliza = new ArrayList<CotizacionesPendientes>();
		lstEmitirPoliza = srvCotizacionesPendientes.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());
		// Actualizo los Datos de la Cotizaci�n
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wemitePoliza').show();");
		PrimeFaces.current().executeScript("PF('wemitePoliza').show();");
	}

	public void onRowEditEmitePol(RowEditEvent event) {
		Integer existePol = 0;
		RamoCotizacion ramCotEmi = new RamoCotizacion();
		String pol, fact, anex;
		String numAsis;
		Date fcEmiAse;
		ramCotEmi = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
				((CotizacionesPendientes) event.getObject()).getCd_ramo_cotizacion(),
				((CotizacionesPendientes) event.getObject()).getCd_compania());
		pol = ((CotizacionesPendientes) event.getObject()).getPoliza();
		fact = ((CotizacionesPendientes) event.getObject()).getFactura();
		anex = ((CotizacionesPendientes) event.getObject()).getAnexo();
		numAsis = ((CotizacionesPendientes) event.getObject()).getNumero_asistencia();
		fcEmiAse = ((CotizacionesPendientes) event.getObject()).getFc_emision_aseguradora_date();
		if (numAsis == null) {
			numAsis = "S/N";
		}

		if (pol == null || fact == null || anex == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese el N�mero de P�liza, Factura y Anexo para Emitir"));
		} else {
			existePol = srvRamoCotizacion.verificoExisteFactura(pol, fact);
			if (existePol.equals(0)) {
				ramCotEmi.setPoliza(pol);
				ramCotEmi.setFactura_aseguradora(fact);
				ramCotEmi.setAnexo(anex);
				ramCotEmi.setFc_emision_aseguradora_date(fcEmiAse);
				ramCotEmi.setNumero_asistencia(numAsis);
				srvRamoCotizacion.actualizaRamoCotizacion(ramCotEmi);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia", "N�mero de P�liza y Factura Registrado en el Sistema"));

			}
		}
	}

	public void emitir() {
		int res = 0, existePol = 0;
		String poliza, ane, fac, codCot = null, codCompania = null;
		for (CotizacionesPendientes pol : lstEmitirPoliza) {
			codCot = Integer.toString(pol.getCd_cotizacion());
			codCompania = Integer.toString(pol.getCd_compania());
			poliza = pol.getPoliza();
			ane = pol.getAnexo();
			fac = pol.getFactura();
			if (poliza == null || ane == null || fac == null) {
				res = 1;
			}
			existePol = srvRamoCotizacion.verificoExisteFactura(poliza, fac);
			if (existePol > 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia", "Número de Póliza y Factura Registrado en el Sistema"));

				return;
			}
		}
		if (res == 0) {
			res = srvProcedures.emitePolizas(codCot, codCompania);
			if (res == 0) {
				enceraDatos();
				numCotizacion = null;
				PrimeFaces.current().executeScript("PF('emision').hide();");
				PrimeFaces.current().executeScript("PF('wemitePoliza').hide();");
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();

			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al Emitir Comuníquese con el Administrador del Sistema."));
				return;
			}

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese el Número de Póliza, Factura y Anexo para Emitir"));
			return;
		}

	}

	public void salir() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./indexMensualizado.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void guardaNuevoCliente() {
		System.out.println("Tipo Persona:" + nuevoCliente.getTipo_persona_cliente());
		String aux = null;
		try {
			if (nuevoCliente.getIdentificacion_cliente().isEmpty()
					|| nuevoCliente.getIdentificacion_cliente() == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el No. de Identificación "));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el No. de Identificación "));
			return;
		}

		try {
			aux = nuevoCliente.getPrimer_apellido_cliente();
			nuevoCliente.setPrimer_apellido_cliente(aux.trim().toUpperCase());
		} catch (Exception e) {
			aux = null;
		}
		aux = null;
		try {
			aux = nuevoCliente.getSegundo_apellido_cliente();
			nuevoCliente.setSegundo_apellido_cliente(aux.trim().toUpperCase());
		} catch (Exception e) {
			aux = null;
		}
		aux = null;
		try {
			aux = nuevoCliente.getPrimer_nombre_cliente();
			nuevoCliente.setPrimer_nombre_cliente(aux.trim().toUpperCase());
		} catch (Exception e) {
			aux = null;
		}
		aux = null;
		try {
			aux = nuevoCliente.getSegundo_nombre_cliente();
			nuevoCliente.setSegundo_nombre_cliente(aux.trim().toUpperCase());
		} catch (Exception e) {
			aux = null;
		}
		aux = null;
		try {
			aux = nuevoCliente.getRazon_social_cliente();
			nuevoCliente.setRazon_social_cliente(aux.trim().toUpperCase());
		} catch (Exception e) {
			aux = null;
		}
		aux = null;
		try {
			aux = nuevoCliente.getIdentificacion_cliente();
			nuevoCliente.setIdentificacion_cliente(aux.trim().toUpperCase());
		} catch (Exception e) {
			aux = null;
		}
		try {
			srvClientes.insertarClientes(nuevoCliente);
		} catch (Exception e) {
			System.out.println("ERROR INGRESO");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error Cliente Registrado en el Sistema",
					"Comuniquese con el Administrador del Sistema"));
			return;
		}
		Integer codClie;
		codClie = srvClientes.codigoMaxClientes();
		// GUARDO direccion
		System.out.println("INSERTA DIRECCION");
		direccion.setActestado("A");
		direccion.setCd_aseguradora(0);
		direccion.setCd_cliente(codClie);
		srvDireccion.insertarDireccion(direccion);
		// Guardo telefono
		System.out.println("INSERTA telefono");
		telefono.setActestado("A");
		telefono.setCd_aseguradora(0);
		telefono.setCd_cliente(codClie);
		srvTelefono.insertarTelefonos(telefono);
		// Guardo Contacto
		System.out.println("INSERTA contacto");
		contacto.setActestado("A");
		contacto.setCd_aseguradora(0);
		contacto.setCd_cliente(codClie);
		srvContacto.insertarContacto(contacto);

		datosCliente = new Clientes();
		datosCliente = srvClientes.listaClientesXIdentClie(nuevoCliente.getIdentificacion_cliente());

		try {
			datosCliente.setRazon_social_cliente(datosCliente.getRazon_social_cliente().trim());
		} catch (Exception ex) {
			//
		}

		if (datosCliente.getRazon_social_cliente() == null || datosCliente.getRazon_social_cliente().isEmpty()) {
			datosCliente.setRazon_social_cliente(
					datosCliente.getPrimer_nombre_cliente() + ' ' + datosCliente.getPrimer_apellido_cliente());
		}

//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('winsertaCliente').hide();");
		PrimeFaces.current().executeScript("PF('winsertaCliente').hide();");
	}

	public void onRowEditDetFin(RowEditEvent event) {
		DetalleFormaPago detFrmAux = new DetalleFormaPago();
		int cdDetFrmP, cdCompania;
		Double val = 0.00;
		Date fcVen;
		String numDoc;
		cdDetFrmP = ((DetalleFormaPago) event.getObject()).getCD_DET_FORMA_PAGO();
		cdCompania = ((DetalleFormaPago) event.getObject()).getCD_COMPANIA();
		fcVen = ((DetalleFormaPago) event.getObject()).getFECHA_VENCIMIENTO_DATE();
		numDoc = ((DetalleFormaPago) event.getObject()).getFACTURA_ASEGURADORA();
		val = ((DetalleFormaPago) event.getObject()).getVALOR();
		detFrmAux = srvDetalleFrmPago.recuperaDetallexCdFrmPago(cdDetFrmP, cdCompania);
		detFrmAux.setFECHA_VENCIMIENTO_DATE(fcVen);
		detFrmAux.setFACTURA_ASEGURADORA(numDoc);
		detFrmAux.setVALOR(val);
		srvDetalleFrmPago.actualizaDetFormaPago(detFrmAux);
	}

	public void onRowEditCobertura(RowEditEvent event) {
		CoberturasEmitidas CobAux = new CoberturasEmitidas();
		CobAux.setCd_cobertura(((CoberturasEmitidas) event.getObject()).getCd_cobertura());
		CobAux.setCd_compania(((CoberturasEmitidas) event.getObject()).getCd_compania());
		CobAux.setCd_ramo_cotizacion(((CoberturasEmitidas) event.getObject()).getCd_ramo_cotizacion());
		try {
			CobAux.setPorcentajeplancobertura(((CoberturasEmitidas) event.getObject()).getPorcentajeplancobertura());
		} catch (Exception e) {
			CobAux.setPorcentajeplancobertura(0.0);
		}
		try {
			CobAux.setValor_plancobertura(((CoberturasEmitidas) event.getObject()).getValor_plancobertura());
		} catch (Exception e) {
			CobAux.setValor_plancobertura(0.0);
		}
		Integer res;
		res = srvCoberturasEmitidas.actualizaCoberturasEmitidas(CobAux);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		}
	}

	public void nuevaCobertura() {
		porcCob = 0.0;
		valorCob = 0.0;
		lstCoberturas = new ArrayList<Coberturas>();
		lstCoberturas = srvCoberturas.consultaCoberturas();
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaCobertura').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaCobertura').show();");
	}

	public void guardaCobertura() {
		System.out.println("crc" + cdRamCotCobAdd);
		for (Coberturas cobTmp : selectedLstCoberturas) {
			System.out.println("COBTMP" + cobTmp.getCd_cobertura());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			CoberturasEmitidas cobAux = new CoberturasEmitidas();
			cobAux = srvCoberturasEmitidas.coberturasEmitidas(1, cdRamCotCobAdd, cobTmp.getCd_cobertura());
			if (cobAux == null) {
				cobAux = new CoberturasEmitidas();
				cobAux.setCd_cobertura(cobTmp.getCd_cobertura());
				cobAux.setCd_compania(1);
				cobAux.setCd_ramo_cotizacion(cdRamCotCobAdd);
				cobAux.setDesc_cobertura(cobTmp.getDesc_cobertura());
				if (porcCob != 0.0)
					cobAux.setPorcentajeplancobertura(porcCob);
				if (valorCob != 0.0)
					cobAux.setValor_plancobertura(valorCob);
				srvCoberturasEmitidas.insertaCoberturasEmitidas(cobAux);
			}
		}
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidas(1, cdRamCotCobAdd);
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaCobertura').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaCobertura').hide();");
	}

	public void nuevoDeducible() {
		porcDedValSin = 0.0;
		porcDedValAseg = 0.0;
		valorDedMin = 0.0;
		valorDedFijo = 0.0;
		lstDeducibles = new ArrayList<Deducibles>();
		lstDeducibles = srvDeducibles.consultaDeducibles();
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevoDeducible').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').show();");
	}

	public void guardaDeducible() {
		for (Deducibles dedTmp : selectedlstDeducibles) {
			System.out.println("DEDTMP" + dedTmp.getCd_deducible());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			DeduciblesEmitidas dedAux = new DeduciblesEmitidas();
			dedAux = srvDeduciblesEmitidas.deducibleEmitidas(1, cdRamCotCobAdd, dedTmp.getCd_deducible());
			if (dedAux == null) {
				dedAux = new DeduciblesEmitidas();
				dedAux.setCd_deducible(dedTmp.getCd_deducible());
				dedAux.setCd_compania(1);
				dedAux.setCd_ramo_cotizacion(cdRamCotCobAdd);
				dedAux.setDesc_deducible(dedTmp.getDesc_deducible());
				if (porcDedValSin != 0.0)
					dedAux.setPorcentaje_valor_siniestro(porcDedValSin);
				if (porcDedValAseg != 0.0)
					dedAux.setPorcentaje_valor_asegurado(porcDedValAseg);
				if (valorDedMin != 0.0)
					dedAux.setValor_minimo(valorDedMin);
				if (valorDedFijo != 0.0)
					dedAux.setValor_fijo(valorDedFijo);
				srvDeduciblesEmitidas.insertaDeduciblesEmitidas(dedAux);
			}
		}
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(1, cdRamCotCobAdd);
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevoDeducible').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').hide();");
	}

	public void nuevaClausula() {
		porcClau = 0.0;
		valorClau = 0.0;

		lstClausulas = new ArrayList<Clausulas>();
		lstClausulas = srvClausulas.consultaClausulas();
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaClausula').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaClausula').show();");
	}

	public void guardaClausula() {
		for (Clausulas clauTmp : selectedlstClausulas) {
			System.out.println("clauTMP" + clauTmp.getCd_clausula());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			ClausulasEmitidas clauAux = new ClausulasEmitidas();
			clauAux = srvClausulasEmitidas.clausulaEmitidas(1, cdRamCotCobAdd, clauAux.getCd_clausula());
			if (clauAux == null) {
				clauAux = new ClausulasEmitidas();
				clauAux.setCd_clausula(clauTmp.getCd_clausula());
				clauAux.setCd_compania(1);
				clauAux.setCd_ramo_cotizacion(cdRamCotCobAdd);
				clauAux.setDesc_clausula(clauTmp.getDesc_clausula());
				if (porcClau != 0.0)
					clauAux.setPorcentaje_planclausula(porcClau);
				if (valorClau != 0.0)
					clauAux.setValor_planclausula(valorClau);
				srvClausulasEmitidas.insertaClausulasEmitidas(clauAux);
			}
		}
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidas(1, cdRamCotCobAdd);
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgNuevaClausula').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaClausula').hide();");
	}

	public void eliminaCobertura() {
		Integer cdCob = 0, crc = 0, cdcomp = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			cdCob = selectedCoberturasEmitidas.getCd_cobertura();
			crc = selectedCoberturasEmitidas.getCd_ramo_cotizacion();
			cdcomp = selectedCoberturasEmitidas.getCd_compania();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Cobertura"));
		}
		CoberturasEmitidas cobAux = new CoberturasEmitidas();
		cobAux.setCd_cobertura(cdCob);
		cobAux.setCd_compania(cdcomp);
		cobAux.setCd_ramo_cotizacion(crc);
		srvCoberturasEmitidas.eliminaCoberturasEmitidas(cobAux);
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidas(cdcomp, crc);
	}

	public void onRowEditDeducible(RowEditEvent event) {
		DeduciblesEmitidas dedAux = new DeduciblesEmitidas();
		dedAux.setCd_compania(((DeduciblesEmitidas) event.getObject()).getCd_compania());
		dedAux.setCd_deducible(((DeduciblesEmitidas) event.getObject()).getCd_deducible());
		dedAux.setCd_ramo_cotizacion(((DeduciblesEmitidas) event.getObject()).getCd_ramo_cotizacion());
		try {
			dedAux.setValor_fijo(((DeduciblesEmitidas) event.getObject()).getValor_fijo());
		} catch (Exception e) {
			dedAux.setValor_fijo(0.0);
		}
		try {
			dedAux.setValor_minimo(((DeduciblesEmitidas) event.getObject()).getValor_minimo());
		} catch (Exception e) {
			dedAux.setValor_minimo(0.0);
		}
		try {
			dedAux.setPorcentaje_valor_asegurado(
					((DeduciblesEmitidas) event.getObject()).getPorcentaje_valor_asegurado());
		} catch (Exception e) {
			dedAux.setPorcentaje_valor_asegurado(0.0);
		}
		try {
			dedAux.setPorcentaje_valor_siniestro(
					((DeduciblesEmitidas) event.getObject()).getPorcentaje_valor_siniestro());
		} catch (Exception e) {
			dedAux.setPorcentaje_valor_siniestro(0.0);
		}

		Integer res;
		res = srvDeduciblesEmitidas.actualizaDeduciblesEmitidas(dedAux);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		}
	}

	public void eliminaDeducible() {
		Integer cdDed = 0, crc = 0, cdcomp = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			cdDed = selectedDeducibleEmitida.getCd_deducible();
			crc = selectedDeducibleEmitida.getCd_ramo_cotizacion();
			cdcomp = selectedDeducibleEmitida.getCd_compania();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Cobertura"));
		}
		DeduciblesEmitidas cobAux = new DeduciblesEmitidas();
		cobAux.setCd_deducible(cdDed);
		cobAux.setCd_compania(cdcomp);
		cobAux.setCd_ramo_cotizacion(crc);
		srvDeduciblesEmitidas.eliminaDeduciblesEmitidas(cobAux);
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(cdcomp, crc);
	}

	public void onRowEditClausula(RowEditEvent event) {
		ClausulasEmitidas clauAux = new ClausulasEmitidas();
		clauAux.setCd_compania(((ClausulasEmitidas) event.getObject()).getCd_compania());
		clauAux.setCd_clausula(((ClausulasEmitidas) event.getObject()).getCd_clausula());
		clauAux.setCd_ramo_cotizacion(((ClausulasEmitidas) event.getObject()).getCd_ramo_cotizacion());
		try {
			clauAux.setPorcentaje_planclausula(((ClausulasEmitidas) event.getObject()).getPorcentaje_planclausula());
		} catch (Exception e) {
			clauAux.setPorcentaje_planclausula(0.0);
		}
		try {
			clauAux.setValor_planclausula(((ClausulasEmitidas) event.getObject()).getValor_planclausula());
		} catch (Exception e) {
			clauAux.setValor_planclausula(0.0);
		}

		Integer res;
		res = srvClausulasEmitidas.actualizaClausulasEmitidas(clauAux);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		}
	}

	public void eliminaClausula() {
		Integer cdClau = 0, crc = 0, cdcomp = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			cdClau = selectedClausulaEmitida.getCd_clausula();
			crc = selectedClausulaEmitida.getCd_ramo_cotizacion();
			cdcomp = selectedClausulaEmitida.getCd_compania();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Cobertura"));
		}
		ClausulasEmitidas cobAux = new ClausulasEmitidas();
		cobAux.setCd_clausula(cdClau);
		;
		cobAux.setCd_compania(cdcomp);
		cobAux.setCd_ramo_cotizacion(crc);
		srvClausulasEmitidas.eliminaClausulasEmitidas(cobAux);
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidas(cdcomp, crc);
	}

	public void onRowEditClie(RowEditEvent event) {
		System.out.println("INGRESO");
		Clientes clieAux = new Clientes();
		clieAux = ((Clientes) event.getObject());
		srvClientes.actualizaClientes(clieAux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Actualizaci�n Exitosa"));
	}

	public List<DetalleFormaPago> getLstDetFrmPago() {
		return lstDetFrmPago;
	}

	public void setLstDetFrmPago(List<DetalleFormaPago> lstDetFrmPago) {
		this.lstDetFrmPago = lstDetFrmPago;
	}

	public List<FormaPago> getLstFrmPago() {
		return lstFrmPago;
	}

	public void setLstFrmPago(List<FormaPago> lstFrmPago) {
		this.lstFrmPago = lstFrmPago;
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public Double getFrmPagoCuotaIni() {
		return frmPagoCuotaIni;
	}

	public void setFrmPagoCuotaIni(Double frmPagoCuotaIni) {
		this.frmPagoCuotaIni = frmPagoCuotaIni;
	}

	public Integer getFrmPagoNumPago() {
		return frmPagoNumPago;
	}

	public void setFrmPagoNumPago(Integer frmPagoNumPago) {
		this.frmPagoNumPago = frmPagoNumPago;
	}

	public Double getFrmPagoTotal() {
		return frmPagoTotal;
	}

	public void setFrmPagoTotal(Double frmPagoTotal) {
		this.frmPagoTotal = frmPagoTotal;
	}

	public Double getFrmPagoIva() {
		return frmPagoIva;
	}

	public void setFrmPagoIva(Double frmPagoIva) {
		this.frmPagoIva = frmPagoIva;
	}

	public Double getFrmPagoSuperBancos() {
		return frmPagoSuperBancos;
	}

	public void setFrmPagoSuperBancos(Double frmPagoSuperBancos) {
		this.frmPagoSuperBancos = frmPagoSuperBancos;
	}

	public Double getFrmPagoOtroValor() {
		return frmPagoOtroValor;
	}

	public void setFrmPagoOtroValor(Double frmPagoOtroValor) {
		this.frmPagoOtroValor = frmPagoOtroValor;
	}

	public Double getFrmPagoSegCampesino() {
		return frmPagoSegCampesino;
	}

	public void setFrmPagoSegCampesino(Double frmPagoSegCampesino) {
		this.frmPagoSegCampesino = frmPagoSegCampesino;
	}

	public Double getFrmPagoDerechoEmision() {
		return frmPagoDerechoEmision;
	}

	public void setFrmPagoDerechoEmision(Double frmPagoDerechoEmision) {
		this.frmPagoDerechoEmision = frmPagoDerechoEmision;
	}

	public String getTpFromaPago() {
		return tpFromaPago;
	}

	public Double getFrmPagoPrimaTot() {
		return frmPagoPrimaTot;
	}

	public void setFrmPagoPrimaTot(Double frmPagoPrimaTot) {
		this.frmPagoPrimaTot = frmPagoPrimaTot;
	}

	public void setTpFromaPago(String tpFromaPago) {
		this.tpFromaPago = tpFromaPago;
	}

	public List<SubObjetoCotizacion> getLstSubObjetoCot() {
		return lstSubObjetoCot;
	}

	public void setLstSubObjetoCot(List<SubObjetoCotizacion> lstSubObjetoCot) {
		this.lstSubObjetoCot = lstSubObjetoCot;
	}

	public String getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(String vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public String getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(String vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	public Integer getRamoAseg_id() {
		return ramoaseg_id;
	}

	public void setRamoAseg_id(Integer ramoaseg_id) {
		this.ramoaseg_id = ramoaseg_id;
	}

	public Integer getEjecutivo_id() {
		return ejecutivo_id;
	}

	public void setEjecutivo_id(Integer ejecutivo_id) {
		this.ejecutivo_id = ejecutivo_id;
	}

	public Integer getGrupoContratante_id() {
		return grupoContratante_id;
	}

	public void setGrupoContratante_id(Integer grupoContratante_id) {
		this.grupoContratante_id = grupoContratante_id;
	}

	public Integer getCd_cliente() {
		return cd_cliente;
	}

	public void setCd_cliente(Integer cd_cliente) {
		this.cd_cliente = cd_cliente;
	}

	public Integer getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(Integer nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public Integer getRamoaseg_id() {
		return ramoaseg_id;
	}

	public void setRamoaseg_id(Integer ramoaseg_id) {
		this.ramoaseg_id = ramoaseg_id;
	}

	public List<Clientes> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Clientes> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Clientes getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(Clientes datosCliente) {
		this.datosCliente = datosCliente;
	}

	public String getStr_cliente() {
		return str_cliente;
	}

	public void setStr_cliente(String str_cliente) {
		this.str_cliente = str_cliente;
	}

	public List<Subagentes> getListaSubagentes() {
		return listaSubagentes;
	}

	public void setListaSubagentes(List<Subagentes> listaSubagentes) {
		this.listaSubagentes = listaSubagentes;
	}

	public Subagentes getDatosSubagente() {
		return datosSubagente;
	}

	public void setDatosSubagente(Subagentes datosSubagente) {
		this.datosSubagente = datosSubagente;
	}

	public String getStr_subagente() {
		return str_subagente;
	}

	public void setStr_subagente(String str_subagente) {
		this.str_subagente = str_subagente;
	}

	public Integer getCd_subagente() {
		return cd_subagente;
	}

	public void setCd_subagente(Integer cd_subagente) {
		this.cd_subagente = cd_subagente;
	}

	public boolean isMismoSolicitante() {
		return mismoSolicitante;
	}

	public void setMismoSolicitante(boolean mismoSolicitante) {
		this.mismoSolicitante = mismoSolicitante;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<AseguradoraRamo> getListadoAsegRamo() {
		return listadoAsegRamo;
	}

	public void setListadoAsegRamo(List<AseguradoraRamo> listadoAsegRamo) {
		this.listadoAsegRamo = listadoAsegRamo;
	}

	public String getAseguradora() {
		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}

	public List<Plan> getListaPlan() {
		return listaPlan;
	}

	public void setListaPlan(List<Plan> listaPlan) {
		this.listaPlan = listaPlan;
	}

	public Integer getCd_ramoaseg() {
		return cd_ramoaseg;
	}

	public void setCd_ramoaseg(Integer cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}

	public List<Ejecutivos> getListaEjecutivos() {
		return listaEjecutivos;
	}

	public void setListaEjecutivos(List<Ejecutivos> listaEjecutivos) {
		this.listaEjecutivos = listaEjecutivos;
	}

	public List<GrupoContratante> getListaGrupoContratante() {
		return listaGrupoContratante;
	}

	public void setListaGrupoContratante(List<GrupoContratante> listaGrupoContratante) {
		this.listaGrupoContratante = listaGrupoContratante;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Aseguradoras> getListadoAseguradoras() {
		return listadoAseguradoras;
	}

	public void setListadoAseguradoras(List<Aseguradoras> listadoAseguradoras) {
		this.listadoAseguradoras = listadoAseguradoras;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Ubicacion> getListadoUbicaciones() {
		return listadoUbicaciones;
	}

	public void setListadoUbicaciones(List<Ubicacion> listadoUbicaciones) {
		this.listadoUbicaciones = listadoUbicaciones;
	}

	public Cotizacion getDatosCotizacion() {
		return datosCotizacion;
	}

	public void setDatosCotizacion(Cotizacion datosCotizacion) {
		this.datosCotizacion = datosCotizacion;
	}

	public Ubicacion getDatosUbicacion() {
		return datosUbicacion;
	}

	public void setDatosUbicacion(Ubicacion datosUbicacion) {
		this.datosUbicacion = datosUbicacion;
	}

	public RamoCotizacion getDatosRamoCotizacion() {
		return datosRamoCotizacion;
	}

	public void setDatosRamoCotizacion(RamoCotizacion datosRamoCotizacion) {
		this.datosRamoCotizacion = datosRamoCotizacion;
	}

	public ObjetoCotizacion getDatosObjetoCotizacion() {
		return datosObjetoCotizacion;
	}

	public void setDatosObjetoCotizacion(ObjetoCotizacion datosObjetoCotizacion) {
		this.datosObjetoCotizacion = datosObjetoCotizacion;
	}

	public SubObjetoCotizacion getDatosSubObjetoCotizacion() {
		return datosSubObjetoCotizacion;
	}

	public void setDatosSubObjetoCotizacion(SubObjetoCotizacion datosSubObjetoCotizacion) {
		this.datosSubObjetoCotizacion = datosSubObjetoCotizacion;
	}

	public CaracteristicasObj getDatosCaracteristicasObj() {
		return datosCaracteristicasObj;
	}

	public void setDatosCaracteristicasObj(CaracteristicasObj datosCaracteristicasObj) {
		this.datosCaracteristicasObj = datosCaracteristicasObj;
	}

	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}

	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}

	public Double getExValorSubobjeto() {
		return exValorSubobjeto;
	}

	public void setExValorSubobjeto(Double exValorSubobjeto) {
		this.exValorSubobjeto = exValorSubobjeto;
	}

	public String getExDetalle() {
		return exDetalle;
	}

	public void setExDetalle(String exDetalle) {
		this.exDetalle = exDetalle;
	}

	public Integer getExCantidad() {
		return exCantidad;
	}

	public void setExCantidad(Integer exCantidad) {
		this.exCantidad = exCantidad;
	}

	public CaracteristicasVehiculos getDatosCaracteristicasVehiculos() {
		return datosCaracteristicasVehiculos;
	}

	public void setDatosCaracteristicasVehiculos(CaracteristicasVehiculos datosCaracteristicasVehiculos) {
		this.datosCaracteristicasVehiculos = datosCaracteristicasVehiculos;
	}

	public List<Marca> getListadoMarcas() {
		return listadoMarcas;
	}

	public void setListadoMarcas(List<Marca> listadoMarcas) {
		this.listadoMarcas = listadoMarcas;
	}

	public List<Modelo> getListadoModelos() {
		return listadoModelos;
	}

	public void setListadoModelos(List<Modelo> listadoModelos) {
		this.listadoModelos = listadoModelos;
	}

	public List<Dispositivos> getListaDispositivos() {
		return listaDispositivos;
	}

	public void setListaDispositivos(List<Dispositivos> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}

	public String getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}

	public String getApellidoRazonSocial() {
		return apellidoRazonSocial;
	}

	public void setApellidoRazonSocial(String apellidoRazonSocial) {
		this.apellidoRazonSocial = apellidoRazonSocial;
	}

	public List<CotizacionesPendientes> getLstCotizacioneesPendientes() {
		return lstCotizacioneesPendientes;
	}

	public void setLstCotizacioneesPendientes(List<CotizacionesPendientes> lstCotizacioneesPendientes) {
		this.lstCotizacioneesPendientes = lstCotizacioneesPendientes;
	}

	public Boolean getFactPeriodica() {
		return factPeriodica;
	}

	public void setFactPeriodica(Boolean factPeriodica) {
		this.factPeriodica = factPeriodica;
	}

	public List<CotizacionesPendientes> getLstCotizacionPendienteSelec() {
		return lstCotizacionPendienteSelec;
	}

	public void setLstCotizacionPendienteSelec(List<CotizacionesPendientes> lstCotizacionPendienteSelec) {
		this.lstCotizacionPendienteSelec = lstCotizacionPendienteSelec;
	}

	public CotizacionesPendientes getCotizacionPendienteSelected() {
		return cotizacionPendienteSelected;
	}

	public void setCotizacionPendienteSelected(CotizacionesPendientes cotizacionPendienteSelected) {
		this.cotizacionPendienteSelected = cotizacionPendienteSelected;
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

	public String getTxtCd_rubro() {
		return txtCd_rubro;
	}

	public void setTxtCd_rubro(String txtCd_rubro) {
		this.txtCd_rubro = txtCd_rubro;
	}

	public String getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}

	public String getCodEjecutivo() {
		return codEjecutivo;
	}

	public void setCodEjecutivo(String codEjecutivo) {
		this.codEjecutivo = codEjecutivo;
	}

	public String getCodPlan() {
		return codPlan;
	}

	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}

	public String getCodGrupoEconomico() {
		return codGrupoEconomico;
	}

	public void setCodGrupoEconomico(String codGrupoEconomico) {
		this.codGrupoEconomico = codGrupoEconomico;
	}

	public String getDscUbicacion() {
		return dscUbicacion;
	}

	public void setDscUbicacion(String dscUbicacion) {
		this.dscUbicacion = dscUbicacion;
	}

	public List<ObjetoCotizacion> getLstObjetoCot() {
		return lstObjetoCot;
	}

	public void setLstObjetoCot(List<ObjetoCotizacion> lstObjetoCot) {
		this.lstObjetoCot = lstObjetoCot;
	}

	public List<SubObjetoCotizacion> getLstSubObjetoCons() {
		return lstSubObjetoCons;
	}

	public void setLstSubObjetoCons(List<SubObjetoCotizacion> lstSubObjetoCons) {
		this.lstSubObjetoCons = lstSubObjetoCons;
	}

	public ObjetoCotizacion getSelectedDatosObjetoCotizacion() {
		return SelectedDatosObjetoCotizacion;
	}

	public void setSelectedDatosObjetoCotizacion(ObjetoCotizacion selectedDatosObjetoCotizacion) {
		SelectedDatosObjetoCotizacion = selectedDatosObjetoCotizacion;
	}

	public Double getExTasa() {
		return exTasa;
	}

	public void setExTasa(Double exTasa) {
		this.exTasa = exTasa;
	}

	public Double getExFactos() {
		return exFactos;
	}

	public void setExFactos(Double exFactos) {
		this.exFactos = exFactos;
	}

	public Double getExPrima() {
		return exPrima;
	}

	public void setExPrima(Double exPrima) {
		this.exPrima = exPrima;
	}

	public List<CoberturasRamoAsegView> getLstCaoberturas_adc() {
		return lstCaoberturas_adc;
	}

	public void setLstCaoberturas_adc(List<CoberturasRamoAsegView> lstCaoberturas_adc) {
		this.lstCaoberturas_adc = lstCaoberturas_adc;
	}

	public List<CoberturasRamoAsegView> getLstCaoberturas_adcUbc() {
		return lstCaoberturas_adcUbc;
	}

	public void setLstCaoberturas_adcUbc(List<CoberturasRamoAsegView> lstCaoberturas_adcUbc) {
		this.lstCaoberturas_adcUbc = lstCaoberturas_adcUbc;
	}

	public CoberturasRamoAsegView getSelectedLstCobAdc() {
		return selectedLstCobAdc;
	}

	public void setSelectedLstCobAdc(CoberturasRamoAsegView selectedLstCobAdc) {
		this.selectedLstCobAdc = selectedLstCobAdc;
	}

	public Double getValorLimiteCob() {
		return valorLimiteCob;
	}

	public void setValorLimiteCob(Double valorLimiteCob) {
		this.valorLimiteCob = valorLimiteCob;
	}

	public Double getTasaCob() {
		return tasaCob;
	}

	public void setTasaCob(Double tasaCob) {
		this.tasaCob = tasaCob;
	}

	public Integer getFactorCob() {
		return factorCob;
	}

	public void setFactorCob(Integer factorCob) {
		this.factorCob = factorCob;
	}

	public Double getValorPrimaCob() {
		return valorPrimaCob;
	}

	public void setValorPrimaCob(Double valorPrimaCob) {
		this.valorPrimaCob = valorPrimaCob;
	}

	public List<CoberturasAdicionales> getLstCobAdc() {
		return lstCobAdc;
	}

	public void setLstCobAdc(List<CoberturasAdicionales> lstCobAdc) {
		this.lstCobAdc = lstCobAdc;
	}

	public boolean isAfecValAsegCob() {
		return afecValAsegCob;
	}

	public void setAfecValAsegCob(boolean afecValAsegCob) {
		this.afecValAsegCob = afecValAsegCob;
	}

	public boolean isAfecPrimaCob() {
		return afecPrimaCob;
	}

	public void setAfecPrimaCob(boolean afecPrimaCob) {
		this.afecPrimaCob = afecPrimaCob;
	}

	public boolean isAfecAdicionalCob() {
		return afecAdicionalCob;
	}

	public void setAfecAdicionalCob(boolean afecAdicionalCob) {
		this.afecAdicionalCob = afecAdicionalCob;
	}

	public CoberturasRamoAsegView getSelectedLstCobAdcUbc() {
		return selectedLstCobAdcUbc;
	}

	public void setSelectedLstCobAdcUbc(CoberturasRamoAsegView selectedLstCobAdcUbc) {
		this.selectedLstCobAdcUbc = selectedLstCobAdcUbc;
	}

	public Double getValorLimiteCobUbc() {
		return valorLimiteCobUbc;
	}

	public void setValorLimiteCobUbc(Double valorLimiteCobUbc) {
		this.valorLimiteCobUbc = valorLimiteCobUbc;
	}

	public Double getTasaCobUbc() {
		return tasaCobUbc;
	}

	public void setTasaCobUbc(Double tasaCobUbc) {
		this.tasaCobUbc = tasaCobUbc;
	}

	public Integer getFactorCobUbc() {
		return factorCobUbc;
	}

	public void setFactorCobUbc(Integer factorCobUbc) {
		this.factorCobUbc = factorCobUbc;
	}

	public Double getValorPrimaCobUbc() {
		return valorPrimaCobUbc;
	}

	public void setValorPrimaCobUbc(Double valorPrimaCobUbc) {
		this.valorPrimaCobUbc = valorPrimaCobUbc;
	}

	public boolean isAfecValAsegCobUbc() {
		return afecValAsegCobUbc;
	}

	public void setAfecValAsegCobUbc(boolean afecValAsegCobUbc) {
		this.afecValAsegCobUbc = afecValAsegCobUbc;
	}

	public boolean isAfecPrimaCobUbc() {
		return afecPrimaCobUbc;
	}

	public void setAfecPrimaCobUbc(boolean afecPrimaCobUbc) {
		this.afecPrimaCobUbc = afecPrimaCobUbc;
	}

	public boolean isAfecAdicionalCobUbc() {
		return afecAdicionalCobUbc;
	}

	public void setAfecAdicionalCobUbc(boolean afecAdicionalCobUbc) {
		this.afecAdicionalCobUbc = afecAdicionalCobUbc;
	}

	public Ubicacion getSelectedDatosUbicacion() {
		return selectedDatosUbicacion;
	}

	public void setSelectedDatosUbicacion(Ubicacion selectedDatosUbicacion) {
		this.selectedDatosUbicacion = selectedDatosUbicacion;
	}

	public List<CoberturasAdicionales> getLstCobAdcUbc() {
		return lstCobAdcUbc;
	}

	public void setLstCobAdcUbc(List<CoberturasAdicionales> lstCobAdcUbc) {
		this.lstCobAdcUbc = lstCobAdcUbc;
	}

	public List<CotizacionesPendientes> getLstEmitirPoliza() {
		return lstEmitirPoliza;
	}

	public void setLstEmitirPoliza(List<CotizacionesPendientes> lstEmitirPoliza) {
		this.lstEmitirPoliza = lstEmitirPoliza;
	}

	public CotizacionesPendientes getSelectedCotizacioneesPendientes() {
		return SelectedCotizacioneesPendientes;
	}

	public void setSelectedCotizacioneesPendientes(CotizacionesPendientes selectedCotizacioneesPendientes) {
		SelectedCotizacioneesPendientes = selectedCotizacioneesPendientes;
	}

	public String getFactorObjeto() {
		return factorObjeto;
	}

	public void setFactorObjeto(String factorObjeto) {
		this.factorObjeto = factorObjeto;
	}

	public Clientes getNuevoCliente() {
		return nuevoCliente;
	}

	public void setNuevoCliente(Clientes nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}

	public List<Nacionalidad> getLstNacionalidad() {
		return lstNacionalidad;
	}

	public void setLstNacionalidad(List<Nacionalidad> lstNacionalidad) {
		this.lstNacionalidad = lstNacionalidad;
	}

	public String getFrmObservaciones() {
		return frmObservaciones;
	}

	public void setFrmObservaciones(String frmObservaciones) {
		this.frmObservaciones = frmObservaciones;
	}

	public List<CoberturasEmitidas> getLstCoberturasEmitidas() {
		return lstCoberturasEmitidas;
	}

	public void setLstCoberturasEmitidas(List<CoberturasEmitidas> lstCoberturasEmitidas) {
		this.lstCoberturasEmitidas = lstCoberturasEmitidas;
	}

	public CoberturasEmitidas getSelectedCoberturasEmitidas() {
		return selectedCoberturasEmitidas;
	}

	public void setSelectedCoberturasEmitidas(CoberturasEmitidas selectedCoberturasEmitidas) {
		this.selectedCoberturasEmitidas = selectedCoberturasEmitidas;
	}

	public List<CoberturasEmitidas> getLstFilteredCoberturasEmitidas() {
		return lstFilteredCoberturasEmitidas;
	}

	public void setLstFilteredCoberturasEmitidas(List<CoberturasEmitidas> lstFilteredCoberturasEmitidas) {
		this.lstFilteredCoberturasEmitidas = lstFilteredCoberturasEmitidas;
	}

	public List<DeduciblesEmitidas> getLstDeducibleEmitida() {
		return lstDeducibleEmitida;
	}

	public void setLstDeducibleEmitida(List<DeduciblesEmitidas> lstDeducibleEmitida) {
		this.lstDeducibleEmitida = lstDeducibleEmitida;
	}

	public List<DeduciblesEmitidas> getLstFilteredDeducibleEmitida() {
		return lstFilteredDeducibleEmitida;
	}

	public void setLstFilteredDeducibleEmitida(List<DeduciblesEmitidas> lstFilteredDeducibleEmitida) {
		this.lstFilteredDeducibleEmitida = lstFilteredDeducibleEmitida;
	}

	public DeduciblesEmitidas getSelectedDeducibleEmitida() {
		return selectedDeducibleEmitida;
	}

	public void setSelectedDeducibleEmitida(DeduciblesEmitidas selectedDeducibleEmitida) {
		this.selectedDeducibleEmitida = selectedDeducibleEmitida;
	}

	public List<ClausulasEmitidas> getLstCalusulaEmitida() {
		return lstCalusulaEmitida;
	}

	public void setLstCalusulaEmitida(List<ClausulasEmitidas> lstCalusulaEmitida) {
		this.lstCalusulaEmitida = lstCalusulaEmitida;
	}

	public ClausulasEmitidas getSelectedClausulaEmitida() {
		return selectedClausulaEmitida;
	}

	public void setSelectedClausulaEmitida(ClausulasEmitidas selectedClausulaEmitida) {
		this.selectedClausulaEmitida = selectedClausulaEmitida;
	}

	public List<ClausulasEmitidas> getLstFilteredCalusulaEmitida() {
		return lstFilteredCalusulaEmitida;
	}

	public void setLstFilteredCalusulaEmitida(List<ClausulasEmitidas> lstFilteredCalusulaEmitida) {
		this.lstFilteredCalusulaEmitida = lstFilteredCalusulaEmitida;
	}

	public Double getPorcCob() {
		return porcCob;
	}

	public void setPorcCob(Double porcCob) {
		this.porcCob = porcCob;
	}

	public Double getValorCob() {
		return valorCob;
	}

	public void setValorCob(Double valorCob) {
		this.valorCob = valorCob;
	}

	public List<Coberturas> getLstCoberturas() {
		return lstCoberturas;
	}

	public void setLstCoberturas(List<Coberturas> lstCoberturas) {
		this.lstCoberturas = lstCoberturas;
	}

	public List<Coberturas> getSelectedLstCoberturas() {
		return selectedLstCoberturas;
	}

	public void setSelectedLstCoberturas(List<Coberturas> selectedLstCoberturas) {
		this.selectedLstCoberturas = selectedLstCoberturas;
	}

	public List<Coberturas> getLstFilteredCoberturas() {
		return lstFilteredCoberturas;
	}

	public void setLstFilteredCoberturas(List<Coberturas> lstFilteredCoberturas) {
		this.lstFilteredCoberturas = lstFilteredCoberturas;
	}

	public List<Deducibles> getLstDeducibles() {
		return lstDeducibles;
	}

	public void setLstDeducibles(List<Deducibles> lstDeducibles) {
		this.lstDeducibles = lstDeducibles;
	}

	public List<Deducibles> getSelectedlstDeducibles() {
		return selectedlstDeducibles;
	}

	public void setSelectedlstDeducibles(List<Deducibles> selectedlstDeducibles) {
		this.selectedlstDeducibles = selectedlstDeducibles;
	}

	public List<Deducibles> getLstFilteredDeducibles() {
		return lstFilteredDeducibles;
	}

	public void setLstFilteredDeducibles(List<Deducibles> lstFilteredDeducibles) {
		this.lstFilteredDeducibles = lstFilteredDeducibles;
	}

	public Double getPorcDedValSin() {
		return porcDedValSin;
	}

	public void setPorcDedValSin(Double porcDedValSin) {
		this.porcDedValSin = porcDedValSin;
	}

	public Double getPorcDedValAseg() {
		return porcDedValAseg;
	}

	public void setPorcDedValAseg(Double porcDedValAseg) {
		this.porcDedValAseg = porcDedValAseg;
	}

	public Double getValorDedMin() {
		return valorDedMin;
	}

	public void setValorDedMin(Double valorDedMin) {
		this.valorDedMin = valorDedMin;
	}

	public Double getValorDedFijo() {
		return valorDedFijo;
	}

	public void setValorDedFijo(Double valorDedFijo) {
		this.valorDedFijo = valorDedFijo;
	}

	public List<Clausulas> getLstClausulas() {
		return lstClausulas;
	}

	public void setLstClausulas(List<Clausulas> lstClausulas) {
		this.lstClausulas = lstClausulas;
	}

	public List<Clausulas> getSelectedlstClausulas() {
		return selectedlstClausulas;
	}

	public void setSelectedlstClausulas(List<Clausulas> selectedlstClausulas) {
		this.selectedlstClausulas = selectedlstClausulas;
	}

	public List<Clausulas> getLstFilteredClausulas() {
		return lstFilteredClausulas;
	}

	public void setLstFilteredClausulas(List<Clausulas> lstFilteredClausulas) {
		this.lstFilteredClausulas = lstFilteredClausulas;
	}

	public Double getPorcClau() {
		return porcClau;
	}

	public void setPorcClau(Double porcClau) {
		this.porcClau = porcClau;
	}

	public Double getValorClau() {
		return valorClau;
	}

	public void setValorClau(Double valorClau) {
		this.valorClau = valorClau;
	}

	public boolean isAplicaIva() {
		return aplicaIva;
	}

	public void setAplicaIva(boolean aplicaIva) {
		this.aplicaIva = aplicaIva;
	}

	public Boolean getFlgActivaCotiza() {
		return flgActivaCotiza;
	}

	public void setFlgActivaCotiza(Boolean flgActivaCotiza) {
		this.flgActivaCotiza = flgActivaCotiza;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public List<Ciudad> getLstCiudad() {
		return lstCiudad;
	}

	public void setLstCiudad(List<Ciudad> lstCiudad) {
		this.lstCiudad = lstCiudad;
	}

	public List<Rubros> getLsrRubroSectorDirec() {
		return lsrRubroSectorDirec;
	}

	public void setLsrRubroSectorDirec(List<Rubros> lsrRubroSectorDirec) {
		this.lsrRubroSectorDirec = lsrRubroSectorDirec;
	}

	public List<ObjetoCotizacion> getLstObjetoConsulta() {
		return lstObjetoConsulta;
	}

	public void setLstObjetoConsulta(List<ObjetoCotizacion> lstObjetoConsulta) {
		this.lstObjetoConsulta = lstObjetoConsulta;
	}

	public ObjetoCotizacion getSelectedObjetoConsulta() {
		return selectedObjetoConsulta;
	}

	public void setSelectedObjetoConsulta(ObjetoCotizacion selectedObjetoConsulta) {
		this.selectedObjetoConsulta = selectedObjetoConsulta;
	}

	public List<SubObjetoCotizacion> getLstSubObjetoConsulta() {
		return lstSubObjetoConsulta;
	}

	public void setLstSubObjetoConsulta(List<SubObjetoCotizacion> lstSubObjetoConsulta) {
		this.lstSubObjetoConsulta = lstSubObjetoConsulta;
	}

	public CoberturasAdicionales getSelectedCobAdcUbc() {
		return selectedCobAdcUbc;
	}

	public void setSelectedCobAdcUbc(CoberturasAdicionales selectedCobAdcUbc) {
		this.selectedCobAdcUbc = selectedCobAdcUbc;
	}

	public List<CoberturasAdicionales> getLstCobAdcUbcDel() {
		return lstCobAdcUbcDel;
	}

	public void setLstCobAdcUbcDel(List<CoberturasAdicionales> lstCobAdcUbcDel) {
		this.lstCobAdcUbcDel = lstCobAdcUbcDel;
	}

	public List<CoberturasAdicionales> getLstCobAdcObj() {
		return lstCobAdcObj;
	}

	public void setLstCobAdcObj(List<CoberturasAdicionales> lstCobAdcObj) {
		this.lstCobAdcObj = lstCobAdcObj;
	}

	public CoberturasAdicionales getSelectedlstCobAdcObj() {
		return selectedlstCobAdcObj;
	}

	public void setSelectedlstCobAdcObj(CoberturasAdicionales selectedlstCobAdcObj) {
		this.selectedlstCobAdcObj = selectedlstCobAdcObj;
	}

	public List<Rubros> getLstRubrosCarta() {
		return lstRubrosCarta;
	}

	public void setLstRubrosCarta(List<Rubros> lstRubrosCarta) {
		this.lstRubrosCarta = lstRubrosCarta;
	}

	public List<Contacto> getLstContactoCarta() {
		return lstContactoCarta;
	}

	public void setLstContactoCarta(List<Contacto> lstContactoCarta) {
		this.lstContactoCarta = lstContactoCarta;
	}

	public String getNmCarta() {
		return nmCarta;
	}

	public void setNmCarta(String nmCarta) {
		this.nmCarta = nmCarta;
	}

	public Contacto getSelectedContactoCarta() {
		return selectedContactoCarta;
	}

	public void setSelectedContactoCarta(Contacto selectedContactoCarta) {
		this.selectedContactoCarta = selectedContactoCarta;
	}

	public String getNotasAdicionalesCarta() {
		return notasAdicionalesCarta;
	}

	public void setNotasAdicionalesCarta(String notasAdicionalesCarta) {
		this.notasAdicionalesCarta = notasAdicionalesCarta;
	}

	public Clientes getDatosClienteSol() {
		return datosClienteSol;
	}

	public void setDatosClienteSol(Clientes datosClienteSol) {
		this.datosClienteSol = datosClienteSol;
	}

	public boolean isCalculaIva() {
		return calculaIva;
	}

	public void setCalculaIva(boolean calculaIva) {
		this.calculaIva = calculaIva;
	}

	public Integer getLsnumRenova() {
		return lsnumRenova;
	}

	public void setLsnumRenova(Integer lsnumRenova) {
		this.lsnumRenova = lsnumRenova;
	}

	public String getNumCotBusq() {
		return numCotBusq;
	}

	public void setNumCotBusq(String numCotBusq) {
		this.numCotBusq = numCotBusq;
	}

}
