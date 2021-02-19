package confia.controladores.transaccionales;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
import confia.entidades.basicos.Provincias;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.SubagenteRamo;
import confia.entidades.basicos.Subagentes;
import confia.entidades.basicos.SubeArchivoObj;
import confia.entidades.basicos.Telefono;
import confia.entidades.basicos.TipoModuloCarta;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Archivos;
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
import confia.servicios.basicos.ServicioProvincias;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioSubagenteRamo;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.basicos.ServicioTelefono;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.basicos.ServiciosDeduciblesEmitidas;
import confia.servicios.transaccionales.ServicioArchivos;
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
import confia.servicios.vistas.ServicioComisionRamoAseg;
import confia.servicios.vistas.ServicioCotizacionesPendientes;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@ManagedBean(name = "ControladorEmision")
@ViewScoped
public class ControladorEmision {
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
	private ServicioComisionRamoAseg srvComisionAseg;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioProvincias srvProvincias;
	@EJB
	private ServicioSubagenteRamo srvSubagenteRamo;
	private servicioProcedures srvProcedures;
	@EJB
	private ServicioArchivos srvArchivos;

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
	public Integer crcPendienteSelectedEditar;
	private Date fcDesde;
	private Date fcHasta;
	private Date fcDesdeEdita;
	private Date fcHastaEdita;
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
	private List<Coberturas> lstCaoberturas_adc;
	private List<Coberturas> filteredLstCobAdc;
	private List<Coberturas> filteredlstCaoberturas_adcUbc;
	private List<Coberturas> lstCaoberturas_adcUbc;
	private Coberturas selectedLstCobAdc;
	private Coberturas selectedLstCobAdcUbc;

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
	private ObjetoCotizacion objCotSegCobAdc;
	private List<Provincias> lstProvincias;

	private Cotizacion datosCotizacion;
	private Ubicacion datosUbicacion;
	private Ubicacion selectedDatosUbicacion;
	private Ubicacion ubcSelectCobAdc;
	private RamoCotizacion datosRamoCotizacion;
	private String codRamo;
	private String codEjecutivo;
	private String codPlan;
	private String codGrupoEconomico;
	private String dscUbicacion;
	private String auxEstadoUbicacion;
	private String codPlanUbc;

	private ObjetoCotizacion datosObjetoCotizacion;
	private ObjetoCotizacion SelectedDatosObjetoCotizacion;

	private SubObjetoCotizacion datosSubObjetoCotizacion;
	private Integer exCantidad;
	private String exDetalle;
	private Double exValorSubobjeto;
	private Double exTasa;
	private Double exFactos;
	private Double exPrima;
	private Double exTasaEdit;
	private Double exFactorEdit;
	private String exDetalleEdit;
	private Double exValAsegEdit;
	private Double exPrimaEdit;
	private Double exDedMin;
	private String ObservacionesEdith;

	private String exParentesco;
	private Date exFechaNacSubObj;
	private String exCedulaIdent;

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
	private String numCotGen;

	private Double porcCob;
	private Double valorCob;
	private List<Coberturas> lstCoberturas;
	private List<Coberturas> selectedLstCoberturas;
	private List<Coberturas> lstFilteredCoberturas;
	private List<Deducibles> lstDeducibles;
	private List<Deducibles> selectedlstDeducibles;
	private List<Deducibles> lstFilteredDeducibles;
	private Integer cdRamCotCobAdd;
	private Integer cdUbcCotCobAdd;
	private Double porcDedValSin;
	private Double porcDedValAseg;
	private Double valorDedMin;
	private Double valorDedFijo;
	private String especificacionDed;
	private String especificacionCob;
	private String especificacionDCla;

	private List<Clausulas> lstClausulas;
	private List<Clausulas> selectedlstClausulas;
	private List<Clausulas> lstFilteredClausulas;
	private Double porcClau;
	private Double valorClau;

	private Boolean flgActivaCotiza;
	private Boolean flgActivaPlan;
	private Boolean flgActivaPlanUbc;

	// CARTAS
	private List<Rubros> lstRubrosCarta;
	private List<Contacto> lstContactoCarta;
	private String nmCarta;
	private Contacto selectedContactoCarta;
	private String notasAdicionalesCarta;
	private Rubros objCarta;

	private Integer lsnumRenova;

	private String txtCd_rubroEdith;
	private Integer lsnumRenovaEdit;
	private List<CaracteristicasVehiculos> lstCaractVeh;
	private List<SubObjetoCotizacion> lstSubObjetoEdit;

	private List<SubagenteRamo> lstSubagentesRamo;
	private List<Subagentes> lstSubagentesEdit;
	private String codCanalEdt;

	// coberturas adicionales

	private boolean flgDlgCobAddObj;
	private boolean flgDlgCobAddObj2;
	private boolean flgAdcUbPan1;
	private boolean flgAdcUbPan2;
	private boolean flgAnoBisiesto;

	// gestion documental
	private List<Rubros> lstRubroGestDoc;
	private String tipoArchivoDoc;
	private String filename;
	private List<Archivos> lstArchivos;
	private String tipoArchivo;
	private List<TipoModuloCarta> lstTipoGestDoc;
	private List<TipoModuloCarta> lstObjetoGestDoc;
	private String objetoArchivo;
	private String polizaGestDocu;
	private Boolean flgPolizaDocu;

	private String tipoCliente;
	private Boolean flgCargaArchivoObj;

	public ControladorEmision() {
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
		fcDesdeEdita = new Date();
		fcHastaEdita = new Date();
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		auxEstadoUbicacion = "NuevaUbc";
		lstCaoberturas_adc = new ArrayList<Coberturas>();
		lstCaoberturas_adcUbc = new ArrayList<Coberturas>();
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
		crcPendienteSelectedEditar = null;
		flgActivaPlan = false;
		flgActivaPlanUbc = true;
		exFechaNacSubObj = new Date();
		lstProvincias = new ArrayList<Provincias>();
		lstSubObjetoEdit = new ArrayList<SubObjetoCotizacion>();
		lstRubroGestDoc = new ArrayList<Rubros>();
		lstArchivos = new ArrayList<Archivos>();
		lstTipoGestDoc = new ArrayList<TipoModuloCarta>();
		lstObjetoGestDoc = new ArrayList<TipoModuloCarta>();
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
		// Calendar fecha = Calendar.getInstance();
		// int a�o = fecha.get(Calendar.YEAR);
		// int mes = fecha.get(Calendar.MONTH);
		// int dia = fecha.get(Calendar.DAY_OF_MONTH);
		// fechaActual = dia + "/" + (mes + 1) + "/" + a�o;
		fechaActual = new Date();
		codRamo = "0";
		codEjecutivo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		flgActivaCotiza = false;
		lstCiudad = srvCiudad.recuperaListaCiudad();
		lsrRubroSectorDirec = srvRubros.listadoRubrosCod("102");
		lstRubrosCarta = srvRubrosCartas.listadoCartasPorModulo("MODULO_EMISION");
		lstProvincias = srvProvincias.listaProvincias();
		txtCd_rubroEdith = "8";
		flgDlgCobAddObj = false;
		flgDlgCobAddObj2 = false;

		flgAdcUbPan1 = false;
		flgAdcUbPan2 = false;
		lstTipoGestDoc = srvRubros.recuperaTipoGestionDocu();
		tipoArchivo = "CLIENTE";
		lstObjetoGestDoc = srvRubros.recuperaObjetoGestionDocu(tipoArchivo);
		flgPolizaDocu = true;
		flgCargaArchivoObj = false;

	}

	public void enceraDocumentoGes() {
		flgPolizaDocu = true;
		lstTipoGestDoc = new ArrayList<TipoModuloCarta>();
		lstTipoGestDoc = srvRubros.recuperaTipoGestionDocu();
		tipoArchivo = "CLIENTE";
		lstObjetoGestDoc = new ArrayList<TipoModuloCarta>();
		lstObjetoGestDoc = srvRubros.recuperaObjetoGestionDocu(tipoArchivo);
		polizaGestDocu = null;
		lstArchivos = new ArrayList<Archivos>();
		lstRubroGestDoc = new ArrayList<Rubros>();
	}

	public void verificaDocuGest() {
		lstObjetoGestDoc = new ArrayList<TipoModuloCarta>();
		lstRubroGestDoc = new ArrayList<Rubros>();
		lstObjetoGestDoc = srvRubros.recuperaObjetoGestionDocu(tipoArchivo);
		if (tipoArchivo.equals("POLIZA")) {
			flgPolizaDocu = false;
		} else {
			flgPolizaDocu = true;
			polizaGestDocu = null;
		}

	}

	public void verificaDescGest() {
		lstRubroGestDoc = new ArrayList<Rubros>();
		lstRubroGestDoc = srvRubros.listadoRubrosGestionDocu("400", tipoArchivo, objetoArchivo);
	}

	public void consultaArchivosGuardados() {
		System.out.println("numCotizacion:" + numCotizacion);
		System.out.println("datosCotizacion:" + datosCotizacion.getCd_cliente());
		try {
			if (numCotizacion.isEmpty() || numCotizacion == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Cotización no identificado, comuníquese con el Administrador del Sistemas"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Cotización no identificada, comuníquese con el Administrador del Sistemas"));
			return;
		}
		lstArchivos = new ArrayList<Archivos>();
		System.out.println("tipoArchivo:" + tipoArchivo);
		if (tipoArchivo.equals("CLIENTE")) {
			lstArchivos = srvArchivos.recuperaArchivosCliente(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(datosCotizacion.getCd_cliente()));
		}
		if (tipoArchivo.equals("POLIZA")) {
			lstArchivos = srvArchivos.recuperaArchivosCotizacion(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(datosCotizacion.getCd_cotizacion()));
		}

	}

	public void onRowDeleteFile(Archivos objArch) {

		srvArchivos.eliminaArchivos(objArch);
		lstArchivos = new ArrayList<Archivos>();
		System.out.println("tipoArchivo:" + tipoArchivo);
		if (tipoArchivo.equals("CLIENTE")) {
			lstArchivos = srvArchivos.recuperaArchivosCliente(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(datosCotizacion.getCd_cliente()));
		}
		if (tipoArchivo.equals("POLIZA")) {
			lstArchivos = srvArchivos.recuperaArchivosCotizacion(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(datosCotizacion.getCd_cotizacion()));
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Registro eliminado exitosamente."));
	}

	public void gestionDocumental() {
		System.out.println("tipoArchivoDoc:" + tipoArchivoDoc);
		System.out.println("PolizaDocumento:" + polizaGestDocu);
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("numCotizacion:" + numCotizacion);
		System.out.println("datosCotizacion:" + datosCotizacion.getCd_cliente());
		try {
			if (polizaGestDocu.isEmpty() || polizaGestDocu == null) {
				polizaGestDocu = "";
			}
		} catch (Exception e) {
			polizaGestDocu = "";
		}
		System.out.println("POLIZA:" + polizaGestDocu);
		System.out.println("tipoArchivo:" + tipoArchivo);
		if (tipoArchivo.equals("POLIZA")) {
			// verifico que se haya ingresado la Póliza
			try {
				if (polizaGestDocu.isEmpty() || polizaGestDocu == null || polizaGestDocu.equals("")) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Número de Póliza"));
					return;
				}
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Número de Póliza"));
				return;
			}
		}

		try {
			if (numCotizacion.isEmpty() || numCotizacion == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Cotización no identificado, Comunicación con el Administrador del Sistemas"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Cotización no identificada, Comunicación con el Administrador del Sistemas"));
			return;
		}

		System.out.println("Desc DOCU:" + tipoArchivoDoc);
		try {
			if (tipoArchivoDoc.isEmpty() || tipoArchivoDoc == "%") {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el tipo de documento"));
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Paso validaci�n......");
		Clientes clieAux = new Clientes();
		clieAux = srvClientes.listaClientesXId(datosCotizacion.getCd_cliente());
		System.out.println("EXISTE CLIENTE:" + clieAux.getCd_cliente());

		UploadedFile miArchivo = event.getFile();
		long tamanio = miArchivo.getSize();// tama�o del archivo
		byte[] contenido = miArchivo.getContent();// contenido del archivo
		String tipoDeArchivo = miArchivo.getContentType();// que tipo de archivo
		String nombre = miArchivo.getFileName();
		int longitud = nombre.length();
		int punto = nombre.indexOf(".");
		System.out.println("Longitud:" + longitud);
		System.out.println("punto:" + punto);
		String extension = nombre.substring(punto, longitud);

		System.out.println("--nombre--" + nombre);
		System.out.println("--extension--" + extension);
		System.out.println("-**********-------Tama�o: " + tamanio);
		System.out.println("-**********-------Contenido: " + contenido);
		System.out.println("-********-------Tipo de Archivo: " + tipoDeArchivo);

		String nmArchivo = getRandomImageNameFile(extension, clieAux);
		System.out.println("nmArchivo:" + nmArchivo);
		guardarArchivo(nmArchivo, contenido);
		FacesMessage message = new FacesMessage("Advertencia",
				" Carga del Archivo Exitoso. Realice nuevamente la consulta para visualizarlo.");
		FacesContext.getCurrentInstance().addMessage(null, message);
		polizaGestDocu = null;

	}

	public void guardarArchivo(String nombre, byte[] contenido) {
		System.out.println("NOMBRE DEL ARCHIVO:" + nombre);
		// esta clase no sirve para escribir en el archivo creado, xq maneja los
		// byte
		FileOutputStream fos = null;
		// tenemos un objeto de tipo file, aqui no se crea el archivo
		File carpetaPrincipal = new File("C:\\java\\wildfly-9.0.2.Final\\welcome-content\\documentos\\clientes");
		// se crea la carpeta
		carpetaPrincipal.mkdir();
		String nombreSinEspacios = "";
		File miArchivo = new File("C:\\java\\wildfly-9.0.2.Final\\welcome-content\\documentos\\clientes\\" + nombre);
		try {
			miArchivo.createNewFile();// se crea el archivo
			fos = new FileOutputStream(miArchivo);
			fos.write(contenido); // en memoria se escribe el archivo
			fos.flush();// escribir en el disco y tambien
			System.out.println("path donde se guardo " + carpetaPrincipal.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();// permite liberar el archivo
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		String nmArchivo = "<a href=\"http://35.237.152.6:8081/documentos/clientes/" + nombre + "\" target=\"_blank\">"
				+ nombre + "</a><br/>";
		System.out.println("filename:" + nmArchivo);
		Clientes clieAux = new Clientes();
		clieAux = srvClientes.listaClientesXId(datosCotizacion.getCd_cliente());
		System.out.println("EXISTE CLIENTE:" + clieAux.getCd_cliente());
		Rubros rb = new Rubros();
		System.out.println("tipoArchivoDoc" + tipoArchivoDoc);
		rb = srvRubros.recuperaRubro(tipoArchivoDoc);
		Archivos arc = new Archivos();
		arc.setModulo("EMISIONES");
		arc.setUbicacion(nmArchivo);
		arc.setCd_rubro(String.valueOf(rb.getCd_rubro()));
		arc.setCd_cliente(String.valueOf(clieAux.getCd_cliente()));
		arc.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
		arc.setDesc_docu(rb.getDesc_rubro());
		if (tipoArchivo.equals("POLIZA")) {
			arc.setPoliza(polizaGestDocu);
		}
		srvArchivos.insertaArchivos(arc);
		consultaArchivosGuardados();

	}

	private String getRandomImageNameFile(String ext, Clientes clie) {
		String apClie, segApClie, pol;

		int i = (int) (Math.random() * 10000000);

		clie.getIdentificacion_cliente();
		try {
			apClie = clie.getPrimer_apellido_cliente();
			if (apClie.isEmpty() || apClie == null) {
				apClie = "SD";
			}
		} catch (Exception e) {
			apClie = "SD";
		}
		try {
			segApClie = clie.getSegundo_apellido_cliente();
			if (segApClie.isEmpty() || segApClie == null) {
				segApClie = "SD";
			}
		} catch (Exception e) {
			segApClie = "SD";
		}

		if (segApClie.equals("SD") && apClie.equals("SD")) {
			try {
				apClie = clie.getRazon_social_cliente();
				if (apClie.isEmpty() || apClie == null) {
					apClie = "SD";
				}
			} catch (Exception e) {
				apClie = "SD";
			}
			apClie = apClie.concat("-");
		} else {
			apClie = apClie.concat(" ");
			apClie = apClie.concat(segApClie);
			apClie = apClie.concat("-");
		}
		System.out.println("apClie:" + apClie);
		if (tipoArchivo.equals("POLIZA")) {
			pol = polizaGestDocu;
			apClie = apClie.concat(pol);
			apClie = apClie.concat("-");
		}
		apClie = apClie.concat(clie.getIdentificacion_cliente());
		apClie = apClie.concat("-");
		apClie = apClie.concat(String.valueOf(i));
		apClie = apClie.concat(ext);

		return apClie;
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
		lstCaoberturas_adc = new ArrayList<Coberturas>();
		lstCaoberturas_adcUbc = new ArrayList<Coberturas>();
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
		System.out.println("ingresa a buscar cotizacion");
		lstCotizacioneesPendientes = new ArrayList<CotizacionesPendientes>();
		try {
			if (apellidoRazonSocial == null) {
				apellidoRazonSocial = "%";
			}
		} catch (Exception e) {
			apellidoRazonSocial = "%";
		}
		try {
			if (numCotGen == null) {
				numCotGen = "%";
			}
		} catch (Exception e) {
			numCotGen = "%";
		}

		if (numCotGen.equals("%")) {
			lstCotizacioneesPendientes = srvCotizacionesPendientes.cotizacionesPendientesCot(apellidoRazonSocial);
		}

		if (apellidoRazonSocial.equals("%")) {
			lstCotizacioneesPendientes = srvCotizacionesPendientes.cotizacionesPendientesNumMCot(numCotGen);
		}

		if (!numCotGen.equals("%") && !apellidoRazonSocial.equals("%")) {
			lstCotizacioneesPendientes = srvCotizacionesPendientes.cotizacionesPendientesNumMCot(numCotGen);
		}

	}

	public void recuperaContactosCarta() {
		Integer tamano = lstCotizacionPendienteSelec.size();
		lstContactoCarta = new ArrayList<Contacto>();
		if (tamano == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Cotización antes de generar la carta"));
			return;
		} else {
			objCarta = new Rubros();
			objCarta = srvRubrosCartas.recuperaCartaPorNombre(nmCarta);
			if (objCarta.getTipo_rubro().equals("ASEG")) {
				lstContactoCarta = srvContactosCarta.listaContactosAseguradora(datosCotizacion.getCd_aseguradora());
			} else {
				lstContactoCarta = srvContactosCarta.listaContactosCliente(datosCotizacion.getCd_cliente());
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
		contextMsj.addMessage(null, new FacesMessage("Registro Exitoso", "Se Generó el Documento Número " + numeroCarta
				+ ". Ingrese al Módulo de Correspondecia para Imprimirlo"));
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
		datosCotizacion = srvCotizacion.recuperaCotizacionPorCodigo(cdCotizacion, Integer.parseInt(codigoCompania));
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());
		listarRamosAseguradora();
		numCotizacion = datosCotizacion.getNum_cotizacion();
		if (datosCotizacion.getFact_periodica_cot().equals(0)) {
			factPeriodica = false;
		} else {
			factPeriodica = true;
		}
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
		// RECUPERO FECHAS INGRESADAS EN EL RAMO Cotización
		RamoCotizacion ramCotAux = new RamoCotizacion();
		ramCotAux = srvRamoCotizacion.obtieneIdRamoCotizacionXCotizacion(datosCotizacion.getCd_cotizacion());
		fcEmiAse = ramCotAux.getFc_emision_aseguradora_date();
		fcDesdePol = ramCotAux.getFc_ini_vig_date();
		fcHastaPol = ramCotAux.getFc_fin_vig_date();
		fechaActual = fcEmiAse;
		fcDesde = fcDesdePol;
		fcHasta = fcHastaPol;

		txtCd_rubro = String.valueOf(datosCotizacion.getCd_rubro());
		lsnumRenova = datosCotizacion.getNum_renovaciones_cot();
		codEjecutivo = String.valueOf(ramCotAux.getCd_ejecutivo());
		flgActivaCotiza = true;

		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wbuscaCotizacion').hide();");
		PrimeFaces.current().executeScript("PF('wbuscaCotizacion').hide();");
	}

	public void nuevaCotizacion() {
		try {
			srvCotizacion.eliminaCotizacion(datosCotizacion);
		} catch (Exception e) {
			// TODO: handle exception
		}

		datosCotizacion = new Cotizacion();
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
		factPeriodica = false;
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
			ctx.redirect("/Broken/index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void editaCotizacion() {
		Integer numRow = 0;
		txtCd_rubroEdith = null;
		lsnumRenovaEdit = 0;
		numRow = lstCotizacionPendienteSelec.size();
		if (numRow <= 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione una Póliza para proceder a Editar."));
			return;
		}
		try {
			lsnumRenovaEdit = lsnumRenova;
		} catch (Exception e) {
			lsnumRenovaEdit = 0;
		}
		PrimeFaces.current().executeScript("PF('editaCotiza').show();");
	}

	public void guardaEdtCotizacion() {
		Integer llDiasVigencia, llDiasVigenciaNuevo, res = 0;
		String sdFcDesde, asFcHasta;
		Integer fcDesdeJul, FcHastaJul;
		Date fcDesdePol, fcHastaPol;
		String sdFcDesdePol, asFcHastaPol;
		Integer fcDesdeJulPol, FcHastaJulPol;
		System.out.println("Tipo:" + txtCd_rubroEdith);
		System.out.println("#Renovacion:" + lsnumRenovaEdit);
		System.out.println("Fc Desde:" + fcDesdeEdita);
		System.out.println("Fc Hasta:" + fcHastaEdita);

		// VERIFICA QUE EL Número DE Días SEA EL MISMO
		sdFcDesde = formato.format(fcDesdeEdita);
		fcDesdeJul = srvProcedures.fechaJuliana(sdFcDesde);
		asFcHasta = formato.format(fcHastaEdita);
		FcHastaJul = srvProcedures.fechaJuliana(asFcHasta);
		llDiasVigenciaNuevo = srvProcedures.diasVigencias(fcDesdeJul, FcHastaJul);
		System.out.println("Dias Vigencia Cambio:" + llDiasVigenciaNuevo);

		// VERIFICA DIAS VIGENCIA POLIZA
		fcDesdePol = datosCotizacion.getFc_ini_cot_date();
		fcHastaPol = datosCotizacion.getFc_fin_cot_date();
		sdFcDesdePol = formato.format(fcDesdePol);
		asFcHastaPol = formato.format(fcHastaPol);
		fcDesdeJulPol = srvProcedures.fechaJuliana(sdFcDesdePol);
		FcHastaJulPol = srvProcedures.fechaJuliana(asFcHastaPol);
		llDiasVigencia = srvProcedures.diasVigencias(fcDesdeJulPol, FcHastaJulPol);
		System.out.println("Dias Vigencia Poliza:" + llDiasVigencia);
		// controla a�o bisiesto
		System.out.println("Anio Bisiesto:" + flgAnoBisiesto);
		if (flgAnoBisiesto) {
			if (llDiasVigencia.equals(365)) {
				llDiasVigencia = llDiasVigencia + 1;
			} else if (llDiasVigencia.equals(366)) {
				llDiasVigencia = llDiasVigencia - 1;
			}

			System.out.println("Dias Vigencia Cambio Bisiesto:" + llDiasVigenciaNuevo);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		if (!flgAnoBisiesto) {
			if (!llDiasVigencia.equals(llDiasVigenciaNuevo)) {
				context.addMessage(null, new FacesMessage("No se Puede Modificar la Vigencia",
						"Días de Vigencia Ingresados " + llDiasVigenciaNuevo));
				return;
			}
		} else {
			System.out.println("INGRSO ELSE");
			System.out.println("llDiasVigenciaNuevo:" + llDiasVigenciaNuevo);
			System.out.println("llDiasVigencia:" + llDiasVigencia);
			if (!llDiasVigencia.equals(llDiasVigenciaNuevo)) {
				context.addMessage(null, new FacesMessage("Año Bisiesto Activado",
						"Días de Vigencia Ingresados " + llDiasVigencia + " Días"));
				return;
			}
		}
		if (lsnumRenovaEdit == null) {
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Número de Renovaciones"));
			return;
		}
		res = srvProcedures.actualizaVigenciasCot(String.valueOf(datosCotizacion.getCd_cotizacion()), sdFcDesde,
				asFcHasta, txtCd_rubroEdith, String.valueOf(lsnumRenovaEdit), codEjecutivo);
		if (res.equals(1)) {
			context.addMessage(null, new FacesMessage("Error",
					"No se puede actualizar la vigencia.Comuníquese con el Administrador del Sistema"));
			return;
		}
		datosCotizacion = new Cotizacion();
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
		factPeriodica = false;
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
		lsnumRenova = 0;
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("/Broken/index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.addMessage(null, new FacesMessage("Advertencia", "Registro Actualizado"));
		// RequestContext context2 = RequestContext.getCurrentInstance();
		// context2.execute("PF('editaCotiza').hide();");
		PrimeFaces.current().executeScript("PF('editaCotiza').hide();");
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

	}

	public void buscarClientes() {
		System.out.println("INGRESO EL CLIENTE");
		if (str_cliente == null) {
			str_cliente = "";
		}
		listaClientes = srvClientes.listaClientes(str_cliente.toUpperCase());
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
		System.out.println("CANAL SELECCIONADO:" + datosSubagente.getRazonSocial_subagente());
	}

	public void listarRamosAseguradora() {
		listadoAsegRamo = new ArrayList<AseguradoraRamo>();
		listadoAsegRamo = srvAsegramo.listaAseguradoraRamos(datosCotizacion.getCd_aseguradora());
		datosCotizacion.setTipo_cliente(tipoCliente);
		System.out.println("Tipo Cliente:" + tipoCliente);
	}

	public void listarPlanes() {
		Integer tpRam = 0;
		datosRamoCotizacion.setCd_ramo(Integer.decode(codRamo));
		// verifico configuraci�n del ramo para el manejo del plan en la
		// Ubicación o RamoCotizacion
		tpRam = srvRamo.tipoRamo(Integer.decode(codRamo));
		if (tpRam.equals(1)) {
			flgActivaPlan = true;
			flgActivaPlanUbc = false;
			datosRamoCotizacion.setCd_plan(0);
			datosRamoCotizacion.setCd_ejecutivo(Integer.decode(codEjecutivo));
			datosRamoCotizacion.setCd_grupo_contratante(Integer.decode(codGrupoEconomico));
		} else {
			flgActivaPlan = false;
			flgActivaPlanUbc = true;
			listaPlan = new ArrayList<Plan>();
			System.out.println("INGRESOOO" + datosRamoCotizacion.getCd_ramo());
			listaPlan = srvPlan.listaPlanes(String.valueOf(datosRamoCotizacion.getCd_ramo()),
					String.valueOf(datosCotizacion.getCd_aseguradora()));
		}

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
		datosCotizacion.setCd_rubro(Integer.parseInt(txtCd_rubro));
		datosCotizacion.setTipo_cliente(tipoCliente);
		datosRamoCotizacion.setCd_ejecutivo(Integer.decode(codEjecutivo));
	}

	public void asignaRamoCotizacion() {
		datosRamoCotizacion.setCd_plan(Integer.decode(codPlan));
		datosRamoCotizacion.setCd_ejecutivo(Integer.decode(codEjecutivo));
		datosRamoCotizacion.setCd_grupo_contratante(Integer.decode(codGrupoEconomico));
	}

	public void agregaRamo() {
		datosCotizacion.setTipo_cliente(tipoCliente);
		System.out.println("Tipo Cliente:" + tipoCliente);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		datosRamoCotizacion = new RamoCotizacion();
		datosSubagente = new Subagentes();
		datosSubagente = srvSubagentes.consultaSubagenteBrkDirecto();
		System.out.println("CD SUBAGENTE" + datosSubagente.getCd_subagente());
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('nuevoRamo').show();");
		PrimeFaces.current().executeScript("PF('nuevoRamo').show();");
		flgActivaPlan = false;
	}

	public void guardaRamoCotizacion() {
		String lsCompania;
		Integer liCompania, flgComision = 0;
		Integer tpRam = 0;

		// verifico configuraci�n del ramo para el manejo del plan en la
		// Ubicación o RamoCotizacion
		System.out.println("CODRAM:" + codRamo);
		System.out.println("CODPLAN:" + codPlan);
		tpRam = srvRamo.tipoRamo(Integer.decode(codRamo));
		System.out.println("CANAL SELECCIONADO:" + datosSubagente.getRazonSocial_subagente());
		if (tpRam.equals(0)) {
			try {
				if (codPlan.equals("0")) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Plan"));
					return;
				}
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Plan"));
				return;
			}
		}

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
			context.addMessage(null, new FacesMessage("No se puede añadir el Ramo", "Ingrese la Comisión del Canal"));
			return;
		}

		if (txtCd_rubro.equals("0")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Tipo"));
			return;
		}

		// verifica el numero de ramos ingresados en la cotizacion
		Integer numRow = 0;
		Boolean flg = false;
		numRow = lstCotizacionPendienteSelec.size();
		System.out.println("# de Ramos:" + numRow);
		try {
			System.out.println("---------------- Num_cotizacion:" + datosCotizacion.getNum_cotizacion());
			if (datosCotizacion.getNum_cotizacion() == null) {
				flg = true;
			}
		} catch (Exception e) {
			flg = true;
		}
		System.out.println("---------------- datosCliente.getCd_cliente():" + datosCliente.getCd_cliente());
		if (flg.equals(true)) {
			// ingresa nueva Cotización
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
			// Para facturaci�n mensualizada se utiliza el otro Módulo
			factPeriodica = false;
			datosCotizacion.setFact_periodica_cot(0);

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
			datosRamoCotizacion.setCd_ramo(Integer.valueOf(codRamo));
			Integer resInsert = srvRamoCotizacion.insertarRamoCotizacion(datosRamoCotizacion);

			// VERIFICA QUE EL Número DE Días
			String fcDesdeTmp, fcHastaTmp;
			Integer fcDesdeJulTemp, fcHastaJulTemp, noDiasTemp;
			fcDesdeTmp = formato.format(fcDesde);
			fcDesdeJulTemp = srvProcedures.fechaJuliana(fcDesdeTmp);
			fcHastaTmp = formato.format(fcHasta);
			fcHastaJulTemp = srvProcedures.fechaJuliana(fcHastaTmp);
			noDiasTemp = srvProcedures.diasVigencias(fcDesdeJulTemp, fcHastaJulTemp);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Días de Vigencias de la Póliza:" + noDiasTemp));

			if (resInsert == 1) {
				// recupero los ramos Cotización
				lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
				lstCotizacionPendienteSelec = srvCotizacionesPendientes
						.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());
			}

			tpRam = 0;
			// verifico configuraci�n del ramo para el manejo del plan en la
			// Ubicación o RamoCotizacion
			tpRam = srvRamo.tipoRamo(datosRamoCotizacion.getCd_ramo());

			if (tpRam.equals(0)) {
				// se carga las coberturas clausulas y deducibles al
				// ramo_cotizacion
				// inserto COBERTURAS, clausulas y deducibles
				srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
						String.valueOf(datosCotizacion.getCd_compania()));
				System.out.println("RAMO----->" + datosRamoCotizacion.getCd_ramo());
				System.out.println("plan----->" + datosRamoCotizacion.getCd_plan());
				System.out.println("Subagente----->" + datosRamoCotizacion.getCd_subagente());
				System.out.println("grupo contratante----->" + datosRamoCotizacion.getCd_grupo_contratante());
			}
		} else {
			System.out.println("Ingreso nuevo ramo coticación registrada");
			int res;
			datosRamoCotizacion.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
			System.out.println("Ingreso cotizacion");
			datosRamoCotizacion.setCd_compania(datosCotizacion.getCd_compania());
			System.out.println("Ingreso compania");
			datosRamoCotizacion.setFc_ini_vig_date(datosCotizacion.getFc_ini_cot_date());
			System.out.println("Ingreso fcdesde");
			datosRamoCotizacion.setFc_fin_vig_date(datosCotizacion.getFc_fin_cot_date());
			System.out.println("Ingreso fchasta");
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
			// recupero los ramos Cotización
			lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
			lstCotizacionPendienteSelec = srvCotizacionesPendientes
					.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());
			// }
			tpRam = 0;
			// verifico configuraci�n del ramo para el manejo del plan en la
			// Ubicación o RamoCotizacion
			tpRam = srvRamo.tipoRamo(datosRamoCotizacion.getCd_ramo());

			if (tpRam.equals(0)) {
				// inserto las clausulas y deducibles en el ramo_cotizacion
				srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
						String.valueOf(datosCotizacion.getCd_compania()));
			}
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
			// RequestContext contextDlg = RequestContext.getCurrentInstance();
			// contextDlg.execute("PF('nuevaUbicacion').hide();");
			PrimeFaces.current().executeScript("PF('nuevaUbicacion').hide();");
			return;
		}
		// en caso de ser ramo peri�dico recupera el plan
		if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
			listaPlan = new ArrayList<Plan>();
			System.out.println("INGRESOOO" + datosRamoCotizacion.getCd_ramo());
			listaPlan = srvPlan.listaPlanes(String.valueOf(cotizacionPendienteSelected.getCd_ramo()),
					String.valueOf(cotizacionPendienteSelected.getCd_aseguradora()));
		}

		auxEstadoUbicacion = "NuevaUbc";
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('nuevaUbicacion').show();");
		PrimeFaces.current().executeScript("PF('nuevaUbicacion').show();");
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstSubObjetoCons = new ArrayList<SubObjetoCotizacion>();
		dscUbicacion = null;
	}

	public void aceptaUbicacion() {
		int res;
		Plan planTemp = new Plan();

		if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
			if (codPlanUbc.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el plan para la Ubicación"));
				return;
			} else {
				planTemp = srvPlan.consultaPlan(Integer.valueOf(codPlanUbc));
			}
		}

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
			if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
				datosUbicacion.setCd_plan(Integer.valueOf(codPlanUbc));
				datosUbicacion.setDescripcion_plan(planTemp.getDescripcion_plan());
			}
			System.out.println("UBICACION" + dscUbicacion);
			res = srvUbicacion.insertarUbicacion(datosUbicacion);
			System.out.println("RESPUESTA:" + res);
			if (res != 1) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicación Comuníquese con el Administrador del Sistema"));
				return;
			}
			res = srvUbicacion.codigoMaxUbc(cotizacionPendienteSelected.getCd_ramo_cotizacion());
			datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(res, cotizacionPendienteSelected.getCd_compania());
			if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
				// inserto las coberturas, clausulas y deducibles de la
				// Ubicación
				srvProcedures.generaCobeDedClauPolizasUbc(String.valueOf(datosUbicacion.getCd_ubicacion()),
						String.valueOf(datosUbicacion.getCd_compania()));
			}
			auxEstadoUbicacion = "UsoUbc";
		}

		// --------------------------------------------------------------

		Double primaObjTot = 0.0, totalValorAsegObj = 0.0, primaObjUbc = 0.0, totalValorAsegUbc = 0.0,
				totAsegSubaAux = 0.0;

		for (ObjetoCotizacion objCot : lstObjetoCot) {
			// verifico si tiene subObjetos
			List<SubObjetoCotizacion> lstSubObjCotAux = new ArrayList<SubObjetoCotizacion>();
			lstSubObjCotAux = srvSubObjetoCotizacion.recuperaSubObjCot(objCot.getCd_obj_cotizacion(),
					objCot.getCd_compania());
			totAsegSubaAux = 0.0;
			if (lstSubObjCotAux.size() > 0) {
				for (SubObjetoCotizacion sba : lstSubObjCotAux) {
					totAsegSubaAux = totAsegSubaAux + sba.getValor_asegurador_subobjeto();
					System.out.println("------------ >VALOR ASEGURADO SUBOBJETO:" + totAsegSubaAux);
				}
			}
			totAsegSubaAux = objCot.getValor_asegurador_objeto() + totAsegSubaAux;
			objCot.setTotal_asegurado_objeto(totAsegSubaAux);
			srvObjetoCotizacion.actualizaObjetoCotizacion(objCot);
		}
		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosUbicacion.getCd_ubicacion(),
				datosUbicacion.getCd_compania());
		System.out.println("ACTUALIZA UBICACION");
		totalValorAsegObj = 0.0;
		for (ObjetoCotizacion objCot : lstObjetoCot) {
			System.out.println("VALOR: " + primaObjTot);
			primaObjTot = primaObjTot + objCot.getPrima_objeto();

			try {
				totalValorAsegObj = totalValorAsegObj + objCot.getTotal_asegurado_objeto();
			} catch (Exception e) {
				totalValorAsegObj = 0.00;
			}
		}
		System.out.println("VALOR: " + primaObjTot);
		if (dscUbicacion == null) {
			dscUbicacion = "S/N";
		}

		datosUbicacion.setDsc_ubicacion(dscUbicacion.trim().toUpperCase());
		datosUbicacion.setValor_prima_ubicacion(primaObjTot);
		datosUbicacion.setValor_asegurado_ubicacion(totalValorAsegObj);
		System.out.println("INGRESOOOOO");
		try {
			datosUbicacion.setCd_plan(Integer.valueOf(codPlanUbc));
			srvUbicacion.actualizaUbicacion(datosUbicacion);
			srvProcedures.generaCobeDedClauPolizasUbc(String.valueOf(datosUbicacion.getCd_ubicacion()),
					String.valueOf(datosUbicacion.getCd_compania()));
		} catch (Exception e) {
			srvUbicacion.actualizaUbicacion(datosUbicacion);
		}
		System.out.println("Actualiza datos del ramo");
		// Actualiza datos del ramo
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(datosUbicacion.getCd_ramo_cotizacion());
		System.out.println("SIZE" + listadoUbicaciones.size());
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
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('nuevaUbicacion').hide();");
		PrimeFaces.current().executeScript("PF('nuevaUbicacion').hide();");
	}
	
	public void cargaArchivo() {
		// guarda la ubicacion
		int res;
		Plan planTemp = new Plan();
		if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
			if (codPlanUbc.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el plan para la Ubicación"));
				return;
			} else {
				planTemp = srvPlan.consultaPlan(Integer.valueOf(codPlanUbc));
			}
		}
		System.out.println("Plan Seleccionado:"+planTemp.getDescripcion_plan());

		if (dscUbicacion == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Digite la Ubicaci�n"));
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
			if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
				datosUbicacion.setCd_plan(Integer.valueOf(codPlanUbc));
				datosUbicacion.setDescripcion_plan(planTemp.getDescripcion_plan());
			}
			System.out.println("UBICACION" + dscUbicacion);
			res = srvUbicacion.insertarUbicacion(datosUbicacion);
			System.out.println("RESPUESTA:" + res);
			if (res != 1) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicaci�n Comun�quese con el Administrador del Sistema"));
				return;
			}
			res = srvUbicacion.codigoMaxUbc(cotizacionPendienteSelected.getCd_ramo_cotizacion());
			datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(res, cotizacionPendienteSelected.getCd_compania());
			if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
				// inserto las coberturas, clausulas y deducibles de la
				// Ubicación
				srvProcedures.generaCobeDedClauPolizasUbc(String.valueOf(datosUbicacion.getCd_ubicacion()),
						String.valueOf(datosUbicacion.getCd_compania()));
			}
			auxEstadoUbicacion = "UsoUbc";
		}
		flgCargaArchivoObj = true;
		System.out.println("Fin guarda archivo");
//		PrimeFaces.current().executeScript("PF('wDlgCargaArchivoPrin').show();");
	}
	public void subirArchivos(FileUploadEvent evt) {
		System.out.println("Ingresa a subir archivo");
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
		System.out.println("datosUbicacion.getCd_ubicacion():"+datosUbicacion.getCd_ubicacion());
		System.out.println("datosUbicacion.getCd_compania():"+datosUbicacion.getCd_compania());
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosUbicacion.getCd_ubicacion(),
				datosUbicacion.getCd_compania());
		FacesMessage message = new FacesMessage("Succesful", evt.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
		flgCargaArchivoObj = false;
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
								archivo.setFECHA_NACIMIENTO_OBJETO(valCampo);
							}
							break;
						case 10:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCEDULA_OBJETO(valCampo);
							}
							break;
						case 11:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPLACA(valCampo);
							}
							break;
						case 12:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setRANV_CPN(valCampo);
							}
							break;
						case 13:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setMARCA(valCampo);
							}
							break;
						case 14:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setMODELO(valCampo);
							}
							break;
						case 15:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCOLOR(valCampo);
							}
							break;
						case 16:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setNO_DE_MOTOR(valCampo);
							}
							break;
						case 17:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setNO_DE_CHASIS(valCampo);
							}
							break;
						case 18:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setANIO(valCampo);
							}
							break;
						case 19:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDISPOSITIVO(valCampo);
							}
							break;
						case 20:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setENDOSO(valCampo);
							}
							break;
						case 21:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setITEM_ASEGURADORA_SUBOBJETO(valCampo);
							}
							break;
						case 22:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDESCRIPCION_SUBOBJETO(valCampo);
							}
							break;
						case 23:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setVALOR_ASEGURADOR_SUBOBJETO(valCampo);
							}
							break;
						case 24:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTASA_SUBOBJETO(valCampo);
							}
							break;
						case 25:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setFACTOR_SUBOBJETO(valCampo);
							}
							break;
						case 26:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPRIMA_SUBOBJETO(valCampo);
							}
							break;
						case 27:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								System.out.println("------------------ FECHA:" + valCampo);
								archivo.setFECHA_NACIMIENTO_SUBOBJETO(valCampo);
							}
							break;
						case 28:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPARENTESCO(valCampo);
							}
							break;
						case 29:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCEDULA_SUBOBJETO(valCampo);
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
			try {
				System.out.println("TIPO:" + subeArchivoObj.getTIPO());
				System.out.println("Descripcion Obj:" + subeArchivoObj.getDESCRIPCION_OBJETO());
				System.out.println("Placa:" + subeArchivoObj.getPLACA());
				System.out.println("Descripcion SubObj:" + subeArchivoObj.getDESCRIPCION_SUBOBJETO());
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (subeArchivoObj.getTIPO().equals("O")) {
				System.out.println("INGRESO AL OBJETO");
				System.out.println("INGRESO A LA O");
				datosObjetoCotizacion = new ObjetoCotizacion();
				datosObjetoCotizacion.setCd_compania(cotizacionPendienteSelected.getCd_compania());
				datosObjetoCotizacion.setFc_ini_obj_cot_date(cotizacionPendienteSelected.getFc_ini_vig());
				datosObjetoCotizacion.setFc_fin_obj_cot_date(cotizacionPendienteSelected.getFc_fin_vig());
				datosObjetoCotizacion.setFc_ini_obj_cot(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
				datosObjetoCotizacion.setFc_fin_obj_cot(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
				datosObjetoCotizacion.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
				datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
				System.out.println("INGRESO A LA O");
				try {
					strgAux = subeArchivoObj.getDESCRIPCION_OBJETO();
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "S/N";
					} else {
						datosObjetoCotizacion.setDescripcion_objeto(strgAux);
					}
				} catch (Exception e) {
					strgAux = "S/N";
				}

				try {
					strgAux = subeArchivoObj.getMONTO_ASEGURADOR_OBJETO();
					strgAux = strgAux.replace(",", ".");
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "0.00";
					} else {
						datosObjetoCotizacion.setValor_asegurador_objeto(Double.valueOf(strgAux));
					}
				} catch (Exception e) {
					strgAux = "0.00";
				}

				try {
					strgAux = subeArchivoObj.getTASA_OBJETO();
					strgAux = strgAux.replace(",", ".");
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "0.00";
					} else {
						datosObjetoCotizacion.setTasa_objeto(Double.valueOf(strgAux));
					}
				} catch (Exception e) {
					strgAux = "0.00";
				}

				try {
					strgAux = subeArchivoObj.getFACTOR_OBJETO();
					strgAux = strgAux.replace(",", ".");
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "100";
					} else {
						datosObjetoCotizacion.setFactor_objeto(Double.valueOf(strgAux));
					}
				} catch (Exception e) {
					strgAux = "100";
				}

				try {
					strgAux = subeArchivoObj.getPRIMA_OBJETO();
					strgAux = strgAux.replace(",", ".");
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "0";
					} else {
						datosObjetoCotizacion.setPrima_objeto(Double.valueOf(strgAux));
					}
				} catch (Exception e) {
					strgAux = "0";
				}

				try {
					strgAux = subeArchivoObj.getDEDUCIBLE_MINIMO();
					strgAux = strgAux.replace(",", ".");
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "0";
					} else {
						datosObjetoCotizacion.setDeducibleMinimo(Double.valueOf(strgAux));
					}
				} catch (Exception e) {
					strgAux = "0";
				}

				try {
					strgAux = subeArchivoObj.getTOTAL_ASEGURADOR_OBJETO();
					strgAux = strgAux.replace(",", ".");
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "0";
					} else {
						datosObjetoCotizacion.setTotal_asegurado_objeto(Double.valueOf(strgAux));
					}
				} catch (Exception e) {
					strgAux = "0";
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

				try {
					strgAux = subeArchivoObj.getFECHA_NACIMIENTO_OBJETO();
					if (!(strgAux.isEmpty() || strgAux == null)) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						Date date = null;
						try {
							date = formatter.parse(strgAux);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						datosObjetoCotizacion.setFecha_nacimiento_obj(date);
					}
				} catch (Exception e) {
					strgAux = null;
				}

				try {
					strgAux = subeArchivoObj.getCEDULA_OBJETO();
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "S/N";
					} else {
						datosObjetoCotizacion.setCedula_obj(strgAux);
					}
				} catch (Exception e) {
					strgAux = null;
				}

				res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al importar el Archivo Comunicación con el Administrador del Sistema"));
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
								"Error al importar el Archivo Comunicación con el Administrador del Sistema"));
						return;
					}
				}
			} else {
				System.out.println("INGRESO AL SUBOBJETO");
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

					try {
						strgAux = subeArchivoObj.getFECHA_NACIMIENTO_SUBOBJETO();
						if (strgAux != null) {
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							Date date = null;
							try {
								date = formatter.parse(strgAux);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							subObj.setFecha_nacimiento_subobj(date);
						}
					} catch (Exception e) {
						strgAux = "0";
					}
					try {
						strgAux = subeArchivoObj.getPARENTESCO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						subObj.setParentesco(strgAux);
					} catch (Exception e) {
						strgAux = "0";
					}
					try {
						strgAux = subeArchivoObj.getCEDULA_SUBOBJETO();
						if (strgAux == null) {
							strgAux = "0";
						}
						subObj.setCedula_subobj(strgAux);
					} catch (Exception e) {
						strgAux = "0";
					}
					res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
					if (res == 0) {
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Advertencia",
								"Error al importar el Archivo Comunicación con el Administrador del Sistema"));
						return;
					}
				}
			}
		}
	}

	public void grabaUbicacion() {
		try {
			System.out.println("Tama�o de la lista" + listadoUbicaciones.size());
			System.out.println("Tama�o de la lista" + selectedDatosUbicacion.getCd_ubicacion());
			if (selectedDatosUbicacion.getCd_ubicacion().equals(0)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia", "Seleccione la Ubicación antes de ingresa un nuevo objeto"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Ubicación antes de ingresa un nuevo objeto"));
			return;
		}

		System.out.println("Ingreso añadir objeto");
		listadoMarcas = new ArrayList<Marca>();
		listadoModelos = new ArrayList<Modelo>();
		listaDispositivos = new ArrayList<Dispositivos>();
		System.out.println("MARCAS - MODELOS");
		listadoMarcas = srvMarcas.listaMarca();
		listaDispositivos = srvDispositivos.listaDispositivos();

		System.out.println("MARCAS - MODELOS:" + listadoMarcas.size());
		System.out.println("UBICACION:" + auxEstadoUbicacion);

		if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
			// contextObj.execute("PF('addObjtASISM').show()");
			PrimeFaces.current().executeScript("PF('addObjtASISM').show()");
		} else {
			// contextObj.execute("PF('addObjt').show()");
			PrimeFaces.current().executeScript("PF('addObjt').show();");
		}
		datosObjetoCotizacion = new ObjetoCotizacion();

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
		datosObjetoCotizacion.setCd_ubicacion(selectedDatosUbicacion.getCd_ubicacion());
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
					"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
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
					try {
						System.out.println("Prima Subobjeto:" + subObj.getPrima_subobjeto());
						if (subObj.getPrima_subobjeto() == null) {
							subObj.setPrima_subobjeto(0.00);
						}
					} catch (Exception e) {
						subObj.setPrima_subobjeto(0.00);
					}
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
								"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
						return;
					}
				}
			} catch (Exception e) {
				System.out.println("NO TIENE SUBOBJETOS");
			}
		}

		// actualizo la prima del subobjeto con la sumatoria de las primas de
		// los extras
		System.out.println("primaTotaObj:" + primaTotaObj);
		System.out.println("totalAseguradoObj:" + totalAseguradoObj);

		datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
		datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
		srvObjetoCotizacion.actPrimaTotAseg(datosObjetoCotizacion);
		System.out.println("ACTUALIZO OBJETO ASEGURADO");
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
						"Error al ingresar las caracteristicas del Objeto Comunicación con el Administrador del Sistema"));
				return;
			}
		} catch (Exception e) {
			// TODO: No se ingreso las caracteristicas dle vehiculo
			System.out.println("NO TIENE CARACTERISTICAS");
		}
		// actualiza la lista de objetos ingresados
		Double primaObjUbc = 0.0, totalValorAsegUbc = 0.0;

		lstObjetoCot = new ArrayList<ObjetoCotizacion>();
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosObjetoCotizacion.getCd_ubicacion(),
				datosObjetoCotizacion.getCd_compania());
		primaObjUbc = 0.0;
		totalValorAsegUbc = 0.0;
		for (ObjetoCotizacion objFin : lstObjetoCot) {
			primaObjUbc = primaObjUbc + objFin.getPrima_objeto();
			totalValorAsegUbc = totalValorAsegUbc + objFin.getTotal_asegurado_objeto();
		}
		// actualizo la Ubicación actual
		selectedDatosUbicacion.setValor_prima_ubicacion(primaObjUbc);
		selectedDatosUbicacion.setValor_asegurado_ubicacion(totalValorAsegUbc);

		srvUbicacion.actualizaUbicacion(selectedDatosUbicacion);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(selectedDatosUbicacion.getCd_ramo_cotizacion());
		System.out.println("SIZE" + listadoUbicaciones.size());
		primaObjUbc = 0.0;
		totalValorAsegUbc = 0.0;
		for (Ubicacion ubc : listadoUbicaciones) {
			primaObjUbc = primaObjUbc + ubc.getValor_prima_ubicacion();
			totalValorAsegUbc = totalValorAsegUbc + ubc.getValor_asegurado_ubicacion();
		}
		// actualizo Ramo
		RamoCotizacion ramCot = new RamoCotizacion();
		ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(selectedDatosUbicacion.getCd_ramo_cotizacion(),
				selectedDatosUbicacion.getCd_compania());
		ramCot.setTotal_prima(primaObjUbc);
		ramCot.setTotal_asegurado(totalValorAsegUbc);
		srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		// encera valores
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();

		// Actualizo valores de la Ubicación y el ramo
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));
		PrimeFaces.current().executeScript("PF('addObjt').hide();");
	}

	public void grabaObjetoAseguradoAsisMed() {
		Double primaTotaObj = 0.0;
		String descObjeto;
		int res, objaux;
		Date fechNac;

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

		try {
			fechNac = datosObjetoCotizacion.getFecha_nacimiento_obj();
		} catch (Exception e) {
			fechNac = null;
			datosObjetoCotizacion.setFecha_nacimiento_obj(null);
		}

		res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
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
					try {
						System.out.println("Prima Subobjeto:" + subObj.getPrima_subobjeto());
						if (subObj.getPrima_subobjeto() == null) {
							subObj.setPrima_subobjeto(0.00);
						}
					} catch (Exception e) {
						subObj.setPrima_subobjeto(0.00);
					}
					primaTotaObj = primaTotaObj + subObj.getPrima_subobjeto();
					System.out.println("SUBOBJE primaTotal:" + primaTotaObj);
					subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
					subObj.setFc_ini_subobj_cot(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
					subObj.setFc_fin_subobj_cot(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
					subObj.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
					res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
					if (res == 0) {
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Advertencia",
								"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
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
		srvObjetoCotizacion.actualizaObjetoCotizacion(datosObjetoCotizacion);
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(objaux,
				cotizacionPendienteSelected.getCd_compania());

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
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('addObjtASISM').hide();");
		PrimeFaces.current().executeScript("PF('addObjtASISM').hide();");
	}

	public void agregarExtrasAsisMed() {
		try {
			if (exTasa == null) {
				exTasa = 0.00;
			}
		} catch (Exception e) {
			exTasa = 0.00;
		}
		try {
			if (exFactos == null) {
				exFactos = 0.00;
			}
		} catch (Exception e) {
			exFactos = 0.00;
		}
		try {
			if (exPrima == null) {
				exPrima = 0.00;
			}
		} catch (Exception e) {
			exPrima = 0.00;
		}
		System.out.println("item:" + exCantidad);
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		datosSubObjetoCotizacion.setCd_compania(cotizacionPendienteSelected.getCd_compania());
		datosSubObjetoCotizacion.setItem_aseguradora_subobjeto(exCantidad);
		datosSubObjetoCotizacion.setDescripcion_subobjeto(exDetalle);
		datosSubObjetoCotizacion.setFc_ini_subobj_cot_date(cotizacionPendienteSelected.getFc_ini_vig());
		datosSubObjetoCotizacion.setFc_fin_subobj_cot_date(cotizacionPendienteSelected.getFc_fin_vig());
		datosSubObjetoCotizacion.setFc_ini_subobj_cot(cotizacionPendienteSelected.getFC_INI_VIG_JUL());
		datosSubObjetoCotizacion.setFc_fin_subobj_cot(cotizacionPendienteSelected.getFC_FIN_VIG_JUL());
		datosSubObjetoCotizacion.setDias_vigencia(cotizacionPendienteSelected.getDIAS_VIGENCIA());
		datosSubObjetoCotizacion.setTasa_subobjeto(exTasa);
		datosSubObjetoCotizacion.setFactor_subobjeto(exFactos);
		datosSubObjetoCotizacion.setPrima_subobjeto(exPrima);
		datosSubObjetoCotizacion.setParentesco(exParentesco);
		datosSubObjetoCotizacion.setFecha_nacimiento_subobj(exFechaNacSubObj);
		datosSubObjetoCotizacion.setCedula_subobj(exCedulaIdent);

		lstSubObjetoCot.add(datosSubObjetoCotizacion);
		exCantidad = 0;
		exDetalle = null;
		exValorSubobjeto = 0.0;
		exTasa = 0.0;
		exPrima = 0.0;
		exCedulaIdent = null;
		exParentesco = null;
		exFechaNacSubObj = new Date();

	}

	public void DeleteLstExtraAsisMed() {
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		exCantidad = 0;
		exDetalle = null;
		exValorSubobjeto = 0.0;
		exTasa = 0.0;
		exPrima = 0.0;
		exCedulaIdent = null;
		exParentesco = null;
		exFechaNacSubObj = new Date();
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
		datosSubObjetoCotizacion.setTasa_subobjeto(exTasa);
		datosSubObjetoCotizacion.setFactor_subobjeto(exFactos);
		datosSubObjetoCotizacion.setPrima_subobjeto(exPrima);

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

	public void onRowSelect(SelectEvent<ObjetoCotizacion> event) {
		int cdobjCo, cdComp;
		cdobjCo = event.getObject().getCd_obj_cotizacion();
		cdComp = event.getObject().getCd_compania();
		lstSubObjetoCons = new ArrayList<SubObjetoCotizacion>();
		lstSubObjetoCons = srvSubObjetoCotizacion.recuperaSubObjCot(cdobjCo, cdComp);
		lstCaractVeh = new ArrayList<CaracteristicasVehiculos>();
		lstCaractVeh = srvCaracteristicasVehiculos.recuperaCaractVHporObjCot(cdobjCo, cdComp);
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

		Integer tpRam = 0;
		// verifico configuraci�n del ramo para el manejo del plan en la
		// Ubicación o RamoCotizacion
		tpRam = srvRamo.tipoRamo(((CotizacionesPendientes) event.getObject()).getCd_ramo());

		if (tpRam.equals(0)) {
			flgActivaPlanUbc = true;
		} else {
			flgActivaPlanUbc = false;
		}

	}

	public void calculaPrimaObjeto() {
		System.out.println("INGRESO AL CALCULO");
		Double ldValAseg, ldValPrima, llFactor, ldTasa;
		Integer llDiasVigencia;
		try {
			llFactor = Double.valueOf(factorObjeto);
			if (llFactor == null) {
				llFactor = 100.00;
			}
		} catch (Exception e) {
			llFactor = 100.00;
		}
		try {
			ldValAseg = datosObjetoCotizacion.getValor_asegurador_objeto();
			if (ldValAseg == null) {
				ldValAseg = 0.0;
			}
		} catch (Exception e) {
			ldValAseg = 0.0;
		}

		try {
			ldTasa = datosObjetoCotizacion.getTasa_objeto();
			if (ldTasa == null) {
				ldTasa = 0.0;
			}
		} catch (Exception e) {
			ldTasa = 0.0;
		}

		System.out.println("PASA VALIDACION");
		llDiasVigencia = cotizacionPendienteSelected.getDIAS_VIGENCIA();
		System.out.println("dias vigencia:" + llDiasVigencia);
		System.out.println("ldValAseg" + ldValAseg);
		System.out.println("ldTasa" + ldTasa);
		System.out.println("llFactor" + llFactor);
		ldValPrima = (ldValAseg * (ldTasa / llFactor)) / 365 * llDiasVigencia;
		System.out.println("ldValPrima" + ldValPrima);
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
		ldTasa = exTasa;
		if (ldTasa == null) {
			ldTasa = 0.0;
		}
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
		System.out.println("Tama�o Lista:" + lstCobAdcObj.size());
		flgDlgCobAddObj = true;
		flgDlgCobAddObj2 = false;
	}

	public void delCobAdcObjIng() {
		System.out.println("INGRESO ELIMINA ADICIONAL Objeto ");
		// recupero las coberturas ingresada al objeto
		lstCobAdc = new ArrayList<CoberturasAdicionales>();
	}

	public void verCoberturasAdcObjeto() {
		flgDlgCobAddObj = false;
		flgDlgCobAddObj2 = true;
	}

	public void verCoberturasAdcUbicacion() {
		lstCobAdcUbcDel = new ArrayList<CoberturasAdicionales>();
		lstCobAdcUbcDel = srvCoberturasAdicionalesNegocio.recuperaCoberturasUbicacion(ubcSelectCobAdc.getCd_compania(), ubcSelectCobAdc.getCd_ubicacion());
		flgAdcUbPan1 = true;
		flgAdcUbPan2 = false;
	}

	public void eliminaCobAdcObj() {
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		System.err.println("INGRESO A GRABAR");
		ObjetoCotizacion objAuxCob = new ObjetoCotizacion();
		objAuxCob = objCotSegCobAdc;
		System.out.println("selectedlstCobAdcObj:" + selectedlstCobAdcObj.getVal_limite());
		System.out.println("selectedlstCobAdcObj.getAfecta_va():" + selectedlstCobAdcObj.getAfecta_va());
		System.out.println("selectedlstCobAdcObj.getAfecta_prima():" + selectedlstCobAdcObj.getAfecta_prima());
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
		lstObjetoConsulta = new ArrayList<ObjetoCotizacion>();
		lstObjetoConsulta = srvObjetoCotizacion.recuperaObjetosPorUbicacion(selectedDatosUbicacion.getCd_ubicacion(),
				selectedDatosUbicacion.getCd_compania());

		// actualizar Ubicación y ramo
		Double primaObjUbc = 0.0, totalValorAsegUbc = 0.0;
		primaObjUbc = 0.0;
		totalValorAsegUbc = 0.0;
		for (ObjetoCotizacion objFin : lstObjetoConsulta) {
			primaObjUbc = primaObjUbc + objFin.getPrima_objeto();
			totalValorAsegUbc = totalValorAsegUbc + objFin.getTotal_asegurado_objeto();
		}
		// actualizo la Ubicación actual
		selectedDatosUbicacion.setValor_prima_ubicacion(primaObjUbc);
		selectedDatosUbicacion.setValor_asegurado_ubicacion(totalValorAsegUbc);

		srvUbicacion.actualizaUbicacion(selectedDatosUbicacion);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(selectedDatosUbicacion.getCd_ramo_cotizacion());
		System.out.println("SIZE" + listadoUbicaciones.size());
		primaObjUbc = 0.0;
		totalValorAsegUbc = 0.0;
		for (Ubicacion ubc : listadoUbicaciones) {
			primaObjUbc = primaObjUbc + ubc.getValor_prima_ubicacion();
			totalValorAsegUbc = totalValorAsegUbc + ubc.getValor_asegurado_ubicacion();
		}
		// actualizo Ramo
		RamoCotizacion ramCot = new RamoCotizacion();
		ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(selectedDatosUbicacion.getCd_ramo_cotizacion(),
				selectedDatosUbicacion.getCd_compania());
		ramCot.setTotal_prima(primaObjUbc);
		ramCot.setTotal_asegurado(totalValorAsegUbc);
		srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		// elimino la cobertura adicional
		srvCoberturasAdicionalesNegocio.eliminaCoberturasNegocio(selectedlstCobAdcObj);
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
		PrimeFaces.current().executeScript("PF('wCoberturasAdc').hide();");
		flgDlgCobAddObj = false;
		flgDlgCobAddObj2 = false;

	}

	public void agregaCoberturaObj() {
		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		lstCaoberturas_adc = new ArrayList<Coberturas>();
		lstCaoberturas_adc = srvCoberturas.consultaCoberturas();

		int aux = 0;

		try {
			selectedObjetoConsulta.getCd_compania();
		} catch (Exception e) {
			aux = 1;
		}
		if (aux == 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione un Objeto Asegurado"));
			return;
		} else {
			lstCobAdc = new ArrayList<CoberturasAdicionales>();
			PrimeFaces.current().executeScript("PF('wCoberturasAdc').show();");
			flgDlgCobAddObj2 = false;
			flgDlgCobAddObj = false;
		}

		// recupero las coberturas ingresada al objeto
		lstCobAdc = new ArrayList<CoberturasAdicionales>();
		lstCobAdcObj = new ArrayList<CoberturasAdicionales>();
		lstCobAdcObj = srvCoberturasAdicionalesNegocio.recuperaCoberturasObjeto(selectedObjetoConsulta.getCd_compania(),
				selectedObjetoConsulta.getCd_obj_cotizacion());

	}

	public void agregaCoberturaUbc() {
		valorLimiteCob = 0.0;
		valorPrimaCob = 0.0;
		tasaCob = 0.0;
		factorCob = 100;
		lstCaoberturas_adcUbc = new ArrayList<Coberturas>();
		lstCaoberturas_adcUbc = srvCoberturas.consultaCoberturas();
				
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
			PrimeFaces.current().executeScript("PF('wCoberturasAdcUbc').show();");
		}
		lstCobAdcUbc = new ArrayList<CoberturasAdicionales>();
		lstCobAdcUbcDel = new ArrayList<CoberturasAdicionales>();
		lstCobAdcUbcDel = srvCoberturasAdicionalesNegocio.recuperaCoberturasUbicacion(ubcSelectCobAdc.getCd_compania(), ubcSelectCobAdc.getCd_ubicacion());

		flgAdcUbPan1 = false;
		flgAdcUbPan2 = false;
	}

	public void onRowSelectUbc(SelectEvent event) {
		// recupero las coberturas ingresada a la ubicacion
		lstCobAdcUbc = new ArrayList<CoberturasAdicionales>();
		System.out.println("cd_compania:" + selectedDatosUbicacion.getCd_compania());
		System.out.println("cd_ubicacion:" + selectedDatosUbicacion.getCd_ubicacion());
		ubcSelectCobAdc = new Ubicacion();
		ubcSelectCobAdc = selectedDatosUbicacion;

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
		objCotSegCobAdc = new ObjetoCotizacion();
		objCotSegCobAdc = selectedObjetoConsulta;
		System.out.println("COD OBJCOT:" + objCotSegCobAdc.getCd_obj_cotizacion());

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
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wCoberturasAdc').hide();");
		PrimeFaces.current().executeScript("PF('wCoberturasAdc').hide();");

	}

	public void delCobAdc() {
		System.out.println("INGRESO ELIMINA ADICIONAL UBICACION ");
		lstCobAdcUbcDel = new ArrayList<CoberturasAdicionales>();
		lstCobAdcUbcDel = srvCoberturasAdicionalesNegocio.recuperaCoberturasUbicacion(
				ubcSelectCobAdc.getCd_compania(), ubcSelectCobAdc.getCd_ubicacion());

		flgAdcUbPan1 = false;
		flgAdcUbPan2 = true;
	}
	
	public void eliminaCobList() {
		lstCobAdcUbc = new ArrayList<CoberturasAdicionales>();
	}

	public void eliminaCoberturaNegocioUbicacion() {

		System.out.println("INGRESAR ELIMINA ADDCC");
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		Ubicacion objAuxCobUbc = new Ubicacion();
		objAuxCobUbc = ubcSelectCobAdc;

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
		flgAdcUbPan1 = false;
		flgAdcUbPan2 = false;
		PrimeFaces.current().executeScript("PF('wCoberturasAdcUbc').hide();");

	}

	public void guardaCoberturaNegocioUbicacion() {
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		System.err.println("INGRESO A GRABAR");
		Ubicacion objAuxCobUbc = new Ubicacion();
		objAuxCobUbc = ubcSelectCobAdc;
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
					valAsegAct = valAsegAct + cobA.getVal_limite();
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

		PrimeFaces.current().executeScript("PF('wCoberturasAdcUbc').hide();");
	}

	public void guardaCoberturaNegocioObjeto() {
		Double primaAct = 0.0;
		Double valAsegAct = 0.0;
		System.err.println("INGRESO A GRABAR");
		System.out.println("TAMA�O LISTA:" + lstCobAdc.size());

		ObjetoCotizacion objAuxCobAdd = new ObjetoCotizacion();
		// objAuxCobAdd = SelectedDatosObjetoCotizacion;
		objAuxCobAdd = objCotSegCobAdc;
		System.out.println("objAuxCobAdd:" + objAuxCobAdd);
		for (CoberturasAdicionales cobA : lstCobAdc) {
			if (cobA.getCd_cob_adc() == null) {
				System.out.println("****** CODIGO COB ADCI:" + cobA.getCd_cob_adc());
				System.out.println("****** CODIGO COB UBC:" + cobA.getCd_ubicacion());
				System.out.println("****** CODIGO COB OBJ:" + cobA.getCd_obj_cotizacion());
				srvCoberturasAdicionalesNegocio.insertaCoberturasNegocio(cobA);
				// verifico a que valor me va afectar
				if (cobA.getAfecta_prima().equals("SI")) {
					primaAct = objAuxCobAdd.getPrima_objeto();
					primaAct = primaAct + cobA.getVal_prima();
					objAuxCobAdd.setPrima_objeto(primaAct);
					System.out.println("PRIMA AFECTADA:" + primaAct);
				}
				if (cobA.getAfecta_va().equals("SI")) {
					valAsegAct = objAuxCobAdd.getTotal_asegurado_objeto();
					System.out.println("valAsegAct:" + valAsegAct);
					System.out.println("cobA.getVal_prima():" + cobA.getVal_limite());
					valAsegAct = valAsegAct + cobA.getVal_limite();
					objAuxCobAdd.setTotal_asegurado_objeto(valAsegAct);
					
					System.out.println("VALOR asEGURADO AFECTADA:" + valAsegAct);
				}
			}
		}
		srvObjetoCotizacion.actualizaObjetoCotizacion(objAuxCobAdd);
		lstObjetoConsulta = new ArrayList<ObjetoCotizacion>();
		lstObjetoConsulta = srvObjetoCotizacion.recuperaObjetosPorUbicacion(selectedDatosUbicacion.getCd_ubicacion(),
				selectedDatosUbicacion.getCd_compania());

		valorLimiteCobUbc = 0.0;
		valorPrimaCobUbc = 0.0;
		tasaCobUbc = 0.0;
		factorCobUbc = 100;
		afecAdicionalCobUbc = false;
		afecPrimaCobUbc = false;
		afecValAsegCobUbc = false;

		// actualizar Ubicación y ramo
		Double primaObjUbc = 0.0, totalValorAsegUbc = 0.0;

		primaObjUbc = 0.0;
		totalValorAsegUbc = 0.0;
		for (ObjetoCotizacion objFin : lstObjetoConsulta) {
			primaObjUbc = primaObjUbc + objFin.getPrima_objeto();
			totalValorAsegUbc = totalValorAsegUbc + objFin.getTotal_asegurado_objeto();
		}
		// actualizo la Ubicación actual
		selectedDatosUbicacion.setValor_prima_ubicacion(primaObjUbc);
		selectedDatosUbicacion.setValor_asegurado_ubicacion(totalValorAsegUbc);

		srvUbicacion.actualizaUbicacion(selectedDatosUbicacion);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(selectedDatosUbicacion.getCd_ramo_cotizacion());
		System.out.println("SIZE" + listadoUbicaciones.size());
		primaObjUbc = 0.0;
		totalValorAsegUbc = 0.0;
		for (Ubicacion ubc : listadoUbicaciones) {
			primaObjUbc = primaObjUbc + ubc.getValor_prima_ubicacion();
			totalValorAsegUbc = totalValorAsegUbc + ubc.getValor_asegurado_ubicacion();
		}
		// actualizo Ramo
		RamoCotizacion ramCot = new RamoCotizacion();
		ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(selectedDatosUbicacion.getCd_ramo_cotizacion(),
				selectedDatosUbicacion.getCd_compania());
		ramCot.setTotal_prima(primaObjUbc);
		ramCot.setTotal_asegurado(totalValorAsegUbc);
		srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes
				.cotizacionesPendientesXCdCot(datosCotizacion.getCd_cotizacion());

		PrimeFaces.current().executeScript("PF('wCoberturasAdc').hide();");
	}

	public void eliminaCobObj() {
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wCoberturasAdcUbc').show();");
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
		System.out.println("INGRESO AL BOTON COBERTURAS ADICONALES OBJETO");
		CoberturasAdicionales cobAdcAux = new CoberturasAdicionales();
		cobAdcAux.setCd_obj_cotizacion(objCotSegCobAdc.getCd_obj_cotizacion());
		cobAdcAux.setCd_compania(objCotSegCobAdc.getCd_compania());
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
	public void onRowSelectUbcCobAdc(SelectEvent<Coberturas> event) {
		selectedLstCobAdcUbc = new Coberturas();
		selectedLstCobAdcUbc = event.getObject();
        System.out.println("SELECCIONO:"+selectedLstCobAdcUbc.getDesc_cobertura());
    }
	public void validaValorUbcCobAdc() {
		try {
			System.out.println("valorLimiteCobUbc"+valorLimiteCobUbc);
			System.out.println("tasaCobUbc:"+tasaCobUbc);
			System.out.println("factorCobUbc:"+factorCobUbc);
			System.out.println("valorPrimaCobUbc:"+valorPrimaCobUbc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void aceptaCoberturasAdcUbicacion() {
		System.out.println("INGRESO UBICACION ADICIONALES");
		System.out.println("valorLimiteCobUbc"+valorLimiteCobUbc);
		System.out.println("tasaCobUbc:"+tasaCobUbc);
		System.out.println("factorCobUbc:"+factorCobUbc);
		System.out.println("valorPrimaCobUbc:"+valorPrimaCobUbc);
		CoberturasAdicionales cobAdcAux = new CoberturasAdicionales();
		cobAdcAux.setCd_ubicacion(ubcSelectCobAdc.getCd_ubicacion());
		cobAdcAux.setCd_compania(ubcSelectCobAdc.getCd_compania());
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
		flgAdcUbPan1 = true;
		flgAdcUbPan2 = false;
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
		Double primaObjUbc = 0.00;
		Double totalValorAsegUbc = 0.00;
		srvUbicacion.eliminaUbicacion(ubc);
		listadoUbicaciones = new ArrayList<Ubicacion>();
		listadoUbicaciones = srvUbicacion.listarUbicaciones(crc);

		for (Ubicacion ubcAux : listadoUbicaciones) {
			primaObjUbc = primaObjUbc + ubcAux.getValor_prima_ubicacion();
			totalValorAsegUbc = totalValorAsegUbc + ubcAux.getValor_asegurado_ubicacion();
		}
		RamoCotizacion crcObj = new RamoCotizacion();
		crcObj = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(ubc.getCd_ramo_cotizacion(), ubc.getCd_compania());
		crcObj.setTotal_prima(primaObjUbc);
		crcObj.setTotal_asegurado(totalValorAsegUbc);
		srvRamoCotizacion.actualizaRamoCotizacion(crcObj);
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes.cotizacionesPendientesXCdCot(crcObj.getCd_cotizacion());
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));

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
		//bloquea si se trata de VAM
		Integer tpRam = srvRamo.tipoRamo(ramCot.getCd_ramo());
		if (tpRam.equals(0)) {
			lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidas(ramCot.getCd_compania(),
					ramCot.getCd_ramo_cotizacion());
			System.out.println("lstCalusulaEmitida:"+lstCalusulaEmitida.size());
			lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidas(ramCot.getCd_compania(),
					ramCot.getCd_ramo_cotizacion());
			System.out.println("lstCoberturasEmitidas:"+lstCoberturasEmitidas.size());
			lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(ramCot.getCd_compania(),
					ramCot.getCd_ramo_cotizacion());
			System.out.println("lstDeducibleEmitida:"+lstDeducibleEmitida.size());
			
			cdRamCotCobAdd = ramCot.getCd_ramo_cotizacion();
			cdUbcCotCobAdd = 0;
			System.out.println("CRC COB CLA DED" + cdRamCotCobAdd);
			System.out.println("UBC COB CLA DED" + cdUbcCotCobAdd);
			PrimeFaces.current().executeScript("PF('wvPlanesRamCot').show();");
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Planes no habilitados para este tipo de Ramo, revise a nivel de ubicación"));
			return;
		}
		
	}
	
	public void onRowPlanUbc(Ubicacion ubicaTot) {
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		//bloquea si se trata de VAM
		RamoCotizacion crcTemp = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(ubicaTot.getCd_ramo_cotizacion(),
				ubicaTot.getCd_compania());
		Integer tpRam = srvRamo.tipoRamo(crcTemp.getCd_ramo());
		if (tpRam.equals(0)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Planes no habilitados para este tipo de Ubicación, revise a nivel de Ramo"));
			return;
		} else {
			
			lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidasUbc(ubicaTot.getCd_compania(),
					ubicaTot.getCd_ramo_cotizacion(),ubicaTot.getCd_ubicacion());
			
			lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidasUbc(ubicaTot.getCd_compania(),
					ubicaTot.getCd_ramo_cotizacion(),ubicaTot.getCd_ubicacion());
			
			lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidasUbicacion(ubicaTot.getCd_compania(),
					ubicaTot.getCd_ramo_cotizacion(),ubicaTot.getCd_ubicacion());
			
			cdRamCotCobAdd = ubicaTot.getCd_ramo_cotizacion();
			cdUbcCotCobAdd = ubicaTot.getCd_ubicacion();
			System.out.println("CRC COB CLA DED" + cdRamCotCobAdd);
			System.out.println("UBC COB CLA DED" + cdUbcCotCobAdd);
			PrimeFaces.current().executeScript("PF('wvPlanesRamCot').show();");
		}
		
	}

	public void onRowGrpContRamCot(CotizacionesPendientes ramCot) {
		System.out.println("cdRamCot:" + ramCot.getCd_ramo_cotizacion());
		crcPendienteSelectedEditar = ramCot.getCd_ramo_cotizacion();
		lstSubagentesRamo = new ArrayList<SubagenteRamo>();
		lstSubagentesRamo = srvSubagenteRamo.listaSubagenteRamo(String.valueOf(ramCot.getCd_ramo()));
		lstSubagentesEdit = new ArrayList<Subagentes>();
		lstSubagentesEdit = srvSubagentes.recuperaSubagenteRamo(lstSubagentesRamo);
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('editaGrupoContratante').show();");
		PrimeFaces.current().executeScript("PF('editaGrupoContratante').show();");
	}

	public void onRowEditSuboj(RowEditEvent event) {
		SubObjetoCotizacion aux = new SubObjetoCotizacion();
		aux = (SubObjetoCotizacion) event.getObject();
		srvSubObjetoCotizacion.actualizaSubObjetoCotizacion(aux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Registro actualizado."));
		return;
	}

	public void onRowEditObjPol(RowEditEvent<ObjetoCotizacion> event) {
		ObjetoCotizacion aux = new ObjetoCotizacion();
		aux = event.getObject();
		srvObjetoCotizacion.actualizaObjetoCotizacion(aux);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Registro actualizado."));
		return;
	}

	public void onRowDeleteSubObj(SubObjetoCotizacion obj) {
		Integer cdobj, compania;
		System.out.println("INGRESOOOOO");
		System.out.println("OBJT:" + obj.getCd_obj_cotizacion());
		cdobj = obj.getCd_obj_cotizacion();
		compania = obj.getCd_compania();
		srvSubObjetoCotizacion.eliminaSubObjetoCotizacion(obj);
		lstSubObjetoEdit = new ArrayList<SubObjetoCotizacion>();
		lstSubObjetoEdit = srvSubObjetoCotizacion.recuperaSubObjCot(cdobj, compania);
	}

	public void onAddNewSubObj() {
		SubObjetoCotizacion newSubObj = new SubObjetoCotizacion();
		newSubObj.setCd_compania(SelectedDatosObjetoCotizacion.getCd_compania());
		newSubObj.setCd_obj_cotizacion(SelectedDatosObjetoCotizacion.getCd_obj_cotizacion());
		newSubObj.setValor_asegurador_subobjeto(0.00);
		newSubObj.setTasa_subobjeto(0.00);
		newSubObj.setPrima_subobjeto(0.00);
		srvSubObjetoCotizacion.insertarSubObjetoCotizacion(newSubObj);
		lstSubObjetoEdit = new ArrayList<SubObjetoCotizacion>();
		lstSubObjetoEdit = srvSubObjetoCotizacion.recuperaSubObjCot(
				SelectedDatosObjetoCotizacion.getCd_obj_cotizacion(), SelectedDatosObjetoCotizacion.getCd_compania());
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Añadió un Ruevo Registro", "Complete la información"));
	}

	public void onRowEditaTasa() {
		exTasaEdit = 0.0;
		exFactorEdit = 100.0;
		ObservacionesEdith = null;
		exPrimaEdit = null;
		exValAsegEdit = null;
		exDetalleEdit = null;
		exFactorEdit = null;
		exTasaEdit = null;
		exDedMin = null;
		try {
			if (SelectedDatosObjetoCotizacion.getCd_compania() == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Aseguradro"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Aseguradro"));
			return;
		}
		exDetalleEdit = SelectedDatosObjetoCotizacion.getDescripcion_objeto();
		exValAsegEdit = SelectedDatosObjetoCotizacion.getValor_asegurador_objeto();
		exTasaEdit = SelectedDatosObjetoCotizacion.getTasa_objeto();
		exFactorEdit = SelectedDatosObjetoCotizacion.getFactor_objeto();
		exPrimaEdit = SelectedDatosObjetoCotizacion.getPrima_objeto();
		exDedMin = SelectedDatosObjetoCotizacion.getDeducibleMinimo();
		ObservacionesEdith = SelectedDatosObjetoCotizacion.getObservaciones_objeto();
		lstSubObjetoEdit = new ArrayList<SubObjetoCotizacion>();
		lstSubObjetoEdit = srvSubObjetoCotizacion.recuperaSubObjCot(
				SelectedDatosObjetoCotizacion.getCd_obj_cotizacion(), SelectedDatosObjetoCotizacion.getCd_compania());
		lstCaractVeh = new ArrayList<CaracteristicasVehiculos>();
		lstCaractVeh = srvCaracteristicasVehiculos.recuperaCaractVHporObjCot(
				SelectedDatosObjetoCotizacion.getCd_obj_cotizacion(), SelectedDatosObjetoCotizacion.getCd_compania());
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('editaTasaObj').show();");
		PrimeFaces.current().executeScript("PF('editaTasaObj').show();");

	}

	public void saveEditTasaObj() {
		Double ldValAseg, ldValPrima, llFactor, ldTasa;
		Integer llDiasVigencia, llcodUbc = null, llcomp = null;
		try {
			if (SelectedDatosObjetoCotizacion.getCd_compania() == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Aseguradro"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Aseguradro"));
			return;
		}

		if (ObservacionesEdith == null) {
			ObservacionesEdith = "S/N";
		}
		if (exPrimaEdit == null) {
			exPrimaEdit = 0.0;
		}
		if (exValAsegEdit == null) {
			exValAsegEdit = 0.0;
		}
		if (exDetalleEdit == null) {
			exDetalleEdit = "S/N";
		}
		if (exFactorEdit == null) {
			exFactorEdit = 100.00;
		}
		if (exTasaEdit == null) {
			exTasaEdit = 0.0;
		}
		if (exDedMin == null) {
			exDedMin = 0.0;
		}

		if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Proceso No Válido para este Tipo de Ramo"));
		} else {
			System.err.println("ObservacionesEdith:" + ObservacionesEdith);
			System.err.println("exPrimaEdit:" + exPrimaEdit);
			System.err.println("exValAsegEdit:" + exValAsegEdit);
			System.err.println("exDetalleEdit:" + exDetalleEdit);
			System.err.println("exFactorEdit:" + exFactorEdit);
			System.err.println("exTasaEdit:" + exTasaEdit);
			// recorro los subojeto
			Double valAsegSObj, primaSObj, valAsegSObjSum, primaSObjSum;
			valAsegSObj = 0.00;
			primaSObj = 0.00;
			valAsegSObjSum = 0.00;
			primaSObjSum = 0.00;

			for (SubObjetoCotizacion subObjeCot : lstSubObjetoEdit) {
				try {
					valAsegSObj = subObjeCot.getValor_asegurador_subobjeto();
				} catch (Exception e) {
					valAsegSObj = 0.00;
				}

				try {
					primaSObj = subObjeCot.getPrima_subobjeto();
				} catch (Exception e) {
					primaSObj = 0.00;
				}
				valAsegSObjSum = valAsegSObjSum + valAsegSObj;
				primaSObjSum = primaSObjSum + primaSObj;
			}
			// recorro los Objetos
			if (lstObjetoCot.size() > 0) {
				// for (ObjetoCotizacion objAux : lstObjetoCot) {
				ObjetoCotizacion objAux = new ObjetoCotizacion();
				objAux = SelectedDatosObjetoCotizacion;

				if (exValAsegEdit == 0.0) {
					ldValAseg = objAux.getValor_asegurador_objeto();
					if (ldValAseg == null) {
						ldValAseg = 0.0;
					}
				} else {
					ldValAseg = exValAsegEdit;
				}
				// llDiasVigencia = objAux.getDias_vigencia();
				// ldValPrima = (ldValAseg * (exTasaEdit / exFactorEdit)) / 365
				// * llDiasVigencia;
				// System.out.println("ldValPrima:" + ldValPrima);
				// System.out.println("llDiasVigencia:" + llDiasVigencia);
				// sumo los valor
				exPrimaEdit = exPrimaEdit + primaSObjSum;
				System.out.println("VALOR ASEGURADO:" + valAsegSObjSum);
				valAsegSObjSum = valAsegSObjSum + ldValAseg;
				System.out.println("PRIMA:" + exPrimaEdit);
				System.out.println("VALOR ASEGURADO:" + valAsegSObjSum);
				objAux.setTotal_asegurado_objeto(valAsegSObjSum);
				objAux.setDescripcion_objeto(exDetalleEdit);
				objAux.setValor_asegurador_objeto(ldValAseg);
				objAux.setPrima_objeto(exPrimaEdit);
				objAux.setTasa_objeto(exTasaEdit);
				objAux.setFactor_objeto(exFactorEdit);
				objAux.setObservaciones_objeto(ObservacionesEdith);
				objAux.setDeducibleMinimo(exDedMin);
				srvObjetoCotizacion.actualizaObjetoCotizacion(objAux);
				llcodUbc = objAux.getCd_ubicacion();
				llcomp = objAux.getCd_compania();
				// }
				lstObjetoCot = new ArrayList<ObjetoCotizacion>();
				lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(llcodUbc, llcomp);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));
				ObservacionesEdith = null;
				exPrimaEdit = null;
				exValAsegEdit = null;
				exDetalleEdit = null;
				exFactorEdit = null;
				exTasaEdit = null;
			}
		}
	}

	public void onRowEditCaract(RowEditEvent event) {
		srvCaracteristicasVehiculos.actualizaCaracteristicaVehiculos((CaracteristicasVehiculos) event.getObject());
		FacesMessage msg = new FacesMessage("Advertencia", "Caracteristicas Editadas");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveEditGrpContratante() {
		System.out.println("cdRamCot:" + crcPendienteSelectedEditar);
		System.out.println("GrupoContatante:" + codGrupoEconomico);
		RamoCotizacion ramAux = new RamoCotizacion();
		ramAux = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(Integer.valueOf(crcPendienteSelectedEditar), 1);
		ramAux.setCd_grupo_contratante(Integer.valueOf(codGrupoEconomico));
		ramAux.setCd_subagente(Integer.valueOf(codCanalEdt));
		srvRamoCotizacion.actualizaRamoCotizacion(ramAux);
		lstCotizacionPendienteSelec = new ArrayList<CotizacionesPendientes>();
		lstCotizacionPendienteSelec = srvCotizacionesPendientes.cotizacionesPendientesXCdCot(ramAux.getCd_cotizacion());

	}

	public void onRowEditUbc(Ubicacion ubc) {
		auxEstadoUbicacion = "UsoUbc";
		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(ubc.getCd_ubicacion(), ubc.getCd_compania());
		lstSubObjetoCons = new ArrayList<SubObjetoCotizacion>();
		datosUbicacion = new Ubicacion();
		datosUbicacion = ubc;
		dscUbicacion = ubc.getDsc_ubicacion();

		// en caso de ser ramo peri�dico recupera el plan
		if (cotizacionPendienteSelected.getCd_plan().equals(0)) {
			listaPlan = new ArrayList<Plan>();
			System.out.println("INGRESOOO" + datosRamoCotizacion.getCd_ramo());
			listaPlan = srvPlan.listaPlanes(String.valueOf(cotizacionPendienteSelected.getCd_ramo()),
					String.valueOf(cotizacionPendienteSelected.getCd_aseguradora()));
		}
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('nuevaUbicacion').show();");
		PrimeFaces.current().executeScript("PF('nuevaUbicacion').show();");
	}

	/////////////////////////////////////////////////////////////////////
	/////////////////// FORMA DE PAGO/////////////////////////////////////
	/*******************************************************************/
	public void agregaFormaPago() {
		System.out.println("INGRESO FORMA DE PAGO");
		int tamano, res = 0;
		String nmRamo = null;
		tpFromaPago = null;
		frmPagoPrimaTot = 0.0;

		frmPagoOtroValor = 0.0;
		frmPagoIva = 0.0;
		frmPagoTotal = 0.0;
		frmPagoCuotaIni = 0.0;
		frmPagoNumPago = 1;

		tamano = lstCotizacionPendienteSelec.size();
		if (tamano == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Cotización antes de ingresar la forma de pago"));
			return;
		} else {

			// Verifica si existe la comisi�n del broker
			for (CotizacionesPendientes cotPend : lstCotizacionPendienteSelec) {
				res = srvComisionAseg.verificaComision(String.valueOf(cotPend.getCd_ramo()),
						String.valueOf(cotPend.getCd_aseguradora()));
				if (res == 0) {
					nmRamo = cotPend.getDesc_ramo();
					break;
				}
			}
			if (res == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia", "Ingrese la Comisión del Broker para el Ramo: " + nmRamo));
				return;
			}

			res = srvFormaPago.verificaFormaPago(datosCotizacion.getCd_cotizacion(), datosCotizacion.getCd_compania());
			if (res == 0) {
				for (CotizacionesPendientes cotPend : lstCotizacionPendienteSelec) {
					frmPagoPrimaTot = frmPagoPrimaTot + cotPend.getTotal_prima();
				}
				if (frmPagoPrimaTot > 4000) {
					frmPagoDerechoEmision = 9.0;
				}
				if (frmPagoPrimaTot > 2000 && frmPagoPrimaTot <= 4000) {
					frmPagoDerechoEmision = 7.0;
				}
				if (frmPagoPrimaTot > 1000 && frmPagoPrimaTot <= 2000) {
					frmPagoDerechoEmision = 5.0;
				}
				if (frmPagoPrimaTot > 500 && frmPagoPrimaTot <= 1000) {
					frmPagoDerechoEmision = 3.0;
				}
				if (frmPagoPrimaTot > 250 && frmPagoPrimaTot <= 500) {
					frmPagoDerechoEmision = 1.0;
				}
				if (frmPagoPrimaTot <= 250) {
					frmPagoDerechoEmision = 0.5;
				}
				frmPagoOtroValor = 0.0;
				frmPagoSegCampesino = frmPagoPrimaTot * (0.5 / 100);
				frmPagoSegCampesino = redondear(frmPagoSegCampesino);
				frmPagoSuperBancos = frmPagoPrimaTot * (3.5 / 100);
				frmPagoSuperBancos = redondear(frmPagoSuperBancos);
				calculaPagoTotal();
				PrimeFaces.current().executeScript("PF('wfrmPago').show();");
			} else {
				System.out.println("INGRESOOOO FRM PAGO:" + datosCotizacion.getCd_cotizacion());
				FormaPago frmA = new FormaPago();
				frmA = srvFormaPago.recuperaFormaPagoxCdCot(datosCotizacion.getCd_cotizacion(),
						datosCotizacion.getCd_compania());
				lstFrmPago = new ArrayList<FormaPago>();
				lstFrmPago.add(frmA);
				lstDetFrmPago = new ArrayList<DetalleFormaPago>();
				lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(frmA.getCd_forma_Pago(),
						frmA.getCd_compania());

			}
		}
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

	public void guardaFormaPago() {
		calculaPagoTotal();
		FormaPago formaPagoAux = new FormaPago();
		// System.out.println("cd_cotizacion"+datosCotizacion.getCd_cotizacion());
		// System.out.println("cd_compania"+datosCotizacion.getCd_compania());
		// System.out.println("frmPagoPrimaTot"+frmPagoPrimaTot);
		// System.out.println("frmPagoSuperBancos"+frmPagoSuperBancos);
		// System.out.println("frmPagoDerechoEmision"+frmPagoDerechoEmision);
		// System.out.println("frmPagoSegCampesino"+frmPagoSegCampesino);
		// System.out.println("frmPagoOtroValor"+frmPagoOtroValor);
		// System.out.println("frmPagoIva"+frmPagoIva);
		// System.out.println("frmPagoTotal"+frmPagoTotal);
		// System.out.println("Num_alternativa_formaPago"+tpFromaPago);
		// System.out.println("frmPagoCuotaIni"+frmPagoCuotaIni);
		// System.out.println("frmPagoNumPago:"+frmPagoNumPago);

		formaPagoAux.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
		formaPagoAux.setCd_compania(datosCotizacion.getCd_compania());
		formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
		formaPagoAux.setSuperBanco_forma_Pago(frmPagoSuperBancos);
		formaPagoAux.setDerecho_Emision_formaPago(frmPagoDerechoEmision);
		formaPagoAux.setSeguroCampesion_forma_Pago(frmPagoSegCampesino);
		formaPagoAux.setOtro_valor_forma_Pago(frmPagoOtroValor);
		formaPagoAux.setIva_Forma_Pago(frmPagoIva);
		formaPagoAux.setTotal_Pago_FormaPago(frmPagoTotal);
		formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
		formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
		formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
		if (aplicaIva) {
			formaPagoAux.setSin_iva(0);
		} else {
			formaPagoAux.setSin_iva(1);
		}
		if (frmObservaciones != null) {
			formaPagoAux.setObservaciones(frmObservaciones);
		}

		int res = 0;
		res = srvFormaPago.insertaFormaPago(formaPagoAux);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
			return;
		}
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
		lstFrmPago = new ArrayList<FormaPago>();
		lstFrmPago.add(formaPagoAux);
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(formaPagoAux.getCd_forma_Pago(),
				formaPagoAux.getCd_compania());
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(formaPagoAux.getCd_forma_Pago(),
				formaPagoAux.getCd_compania());

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wfrmPago').hide();");
		PrimeFaces.current().executeScript("PF('wfrmPago').hide();");
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
		// Actualizo los Datos de la Cotización
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wemitePoliza').show();");
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
					new FacesMessage("Advertencia", "Ingrese el Número de Póliza, Factura y Anexo para Emitir"));
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
						new FacesMessage("Advertencia", "Número de Póliza y Factura Registrado en el Sistema"));

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
				// RequestContext context = RequestContext.getCurrentInstance();
				// context.execute("PF('emision').hide();");
				PrimeFaces.current().executeScript("PF('emision').hide();");
				// RequestContext context2 =
				// RequestContext.getCurrentInstance();
				// context2.execute("PF('wemitePoliza').hide();");
				PrimeFaces.current().executeScript("PF('wemitePoliza').hide();");
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
				// try {
				// ctx.redirect("./index.jsf");
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al Emitir Comunicación con el Administrador del Sistema."));
				return;
			}

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese el Número de Póliza, Factura y Anexo para Emitir"));
			return;
		}
		System.out.println("EJECUTO LA EMISION ETSE");
	}

	public void salir() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./index.jsf");
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
					"Comuníquese con el Administrador del Sistema"));
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

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('winsertaCliente').hide();");
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
		if(!cdUbcCotCobAdd.equals(0))
			CobAux.setCd_ubicacion(cdUbcCotCobAdd);
		
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
		try {
			CobAux.setEspecificacion_cob(((CoberturasEmitidas) event.getObject()).getEspecificacion_cob());
		} catch (Exception e) {
			CobAux.setEspecificacion_cob("-");
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
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wDlgNuevaCobertura').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaCobertura').show();");
	}

	public void guardaCobertura() {
		System.out.println("crc" + cdRamCotCobAdd);
		for (Coberturas cobTmp : selectedLstCoberturas) {
			System.out.println("COBTMP" + cobTmp.getCd_cobertura());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			CoberturasEmitidas cobAux = new CoberturasEmitidas();
			Integer tpRam = srvRamo.tipoRamo(Integer.decode(codRamo));
			if (tpRam.equals(0)) {
				cobAux = srvCoberturasEmitidas.coberturasEmitidas(1, cdRamCotCobAdd, cobTmp.getCd_cobertura());
			} else {
				cobAux = srvCoberturasEmitidas.coberturasEmitidasUbicacion(1, cdRamCotCobAdd, cobTmp.getCd_cobertura(),cdUbcCotCobAdd);
			}
			
			if (cobAux == null) {
				cobAux = new CoberturasEmitidas();
				cobAux.setCd_cobertura(cobTmp.getCd_cobertura());
				cobAux.setCd_compania(1);
				cobAux.setCd_ramo_cotizacion(cdRamCotCobAdd);
				cobAux.setDesc_cobertura(cobTmp.getDesc_cobertura());
				if(!cdUbcCotCobAdd.equals(0))
					cobAux.setCd_ubicacion(cdUbcCotCobAdd);
				if (porcCob != 0.0)
					cobAux.setPorcentajeplancobertura(porcCob);
				if (valorCob != 0.0)
					cobAux.setValor_plancobertura(valorCob);
				
				try {
					if(especificacionCob.isEmpty() || especificacionCob == null)
						especificacionCob = "-";
				} catch (Exception e) {
					especificacionCob = "-";
				}
				cobAux.setEspecificacion_cob(especificacionCob);
				srvCoberturasEmitidas.insertaCoberturasEmitidas(cobAux);
			}
		}
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidas(1, cdRamCotCobAdd);
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wDlgNuevaCobertura').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaCobertura').hide();");
	}

	public void nuevoDeducible() {
		porcDedValSin = 0.0;
		porcDedValAseg = 0.0;
		valorDedMin = 0.0;
		valorDedFijo = 0.0;
		lstDeducibles = new ArrayList<Deducibles>();
		lstDeducibles = srvDeducibles.consultaDeducibles();
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wDlgNuevoDeducible').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').show();");
	}

	public void guardaDeducible() {
		for (Deducibles dedTmp : selectedlstDeducibles) {
			System.out.println("DEDTMP" + dedTmp.getCd_deducible());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			DeduciblesEmitidas dedAux = new DeduciblesEmitidas();
//			dedAux = srvDeduciblesEmitidas.deducibleEmitidas(1, cdRamCotCobAdd, dedTmp.getCd_deducible());
//			if (dedAux == null) {
				dedAux = new DeduciblesEmitidas();
				dedAux.setCd_deducible(dedTmp.getCd_deducible());
				dedAux.setCd_compania(1);
				dedAux.setCd_ramo_cotizacion(cdRamCotCobAdd);
				dedAux.setDesc_deducible(dedTmp.getDesc_deducible());
				if(!cdUbcCotCobAdd.equals(0))
					dedAux.setCd_ubicacion(cdUbcCotCobAdd);
				if (porcDedValSin != 0.0)
					dedAux.setPorcentaje_valor_siniestro(porcDedValSin);
				if (porcDedValAseg != 0.0)
					dedAux.setPorcentaje_valor_asegurado(porcDedValAseg);
				if (valorDedMin != 0.0)
					dedAux.setValor_minimo(valorDedMin);
				if (valorDedFijo != 0.0)
					dedAux.setValor_fijo(valorDedFijo);
				
				try {
					if(especificacionDed.isEmpty() || especificacionDed == null)
						especificacionDed = "";
				} catch (Exception e) {
					especificacionDed = "";
				}
				dedAux.setEspecificacion(especificacionDed);
				srvDeduciblesEmitidas.insertaDeduciblesEmitidas(dedAux);
//			}
		}
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(1, cdRamCotCobAdd);
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wDlgNuevoDeducible').hide();");
		PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').hide();");
	}

	public void nuevaClausula() {
		porcClau = 0.0;
		valorClau = 0.0;

		lstClausulas = new ArrayList<Clausulas>();
		lstClausulas = srvClausulas.consultaClausulas();
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wDlgNuevaClausula').show();");
		PrimeFaces.current().executeScript("PF('wDlgNuevaClausula').show();");
	}

	public void guardaClausula() {
		for (Clausulas clauTmp : selectedlstClausulas) {
			System.out.println("clauTMP" + clauTmp.getCd_clausula());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			ClausulasEmitidas clauAux = new ClausulasEmitidas();
			
			Integer tpRam = srvRamo.tipoRamo(Integer.decode(codRamo));
			if (tpRam.equals(0)) {
				clauAux = srvClausulasEmitidas.clausulaEmitidas(1, cdRamCotCobAdd, clauAux.getCd_clausula());
			} else {
				clauAux = srvClausulasEmitidas.clausulaEmitidasUbicacion(1, cdRamCotCobAdd, clauAux.getCd_clausula(),cdUbcCotCobAdd);
			}
			if (clauAux == null) {
				clauAux = new ClausulasEmitidas();
				clauAux.setCd_clausula(clauTmp.getCd_clausula());
				clauAux.setCd_compania(1);
				clauAux.setCd_ramo_cotizacion(cdRamCotCobAdd);
				clauAux.setDesc_clausula(clauTmp.getDesc_clausula());
				if(!cdUbcCotCobAdd.equals(0))
					clauAux.setCd_ubicacion(cdUbcCotCobAdd);
				if (porcClau != 0.0)
					clauAux.setPorcentaje_planclausula(porcClau);
				if (valorClau != 0.0)
					clauAux.setValor_planclausula(valorClau);
				try {
					if(especificacionDCla.isEmpty() || especificacionDCla == null)
						especificacionDCla = "-";
				} catch (Exception e) {
					especificacionDCla = "-";
				}
				clauAux.setEspecificacion_cla(especificacionDCla);
				srvClausulasEmitidas.insertaClausulasEmitidas(clauAux);
			}
		}
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidas(1, cdRamCotCobAdd);
		// RequestContext contextDlg = RequestContext.getCurrentInstance();
		// contextDlg.execute("PF('wDlgNuevaClausula').hide();");
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
		if(!cdUbcCotCobAdd.equals(0))
			dedAux.setCd_ubicacion(cdUbcCotCobAdd);
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
		
		try {
			dedAux.setEspecificacion(
					((DeduciblesEmitidas) event.getObject()).getEspecificacion());
		} catch (Exception e) {
			dedAux.setEspecificacion("-");
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
		if(!cdUbcCotCobAdd.equals(0))
			clauAux.setCd_ubicacion(cdUbcCotCobAdd);
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
		try {
			clauAux.setEspecificacion_cla(((ClausulasEmitidas) event.getObject()).getEspecificacion_cla());
		} catch (Exception e) {
			clauAux.setEspecificacion_cla("-");
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
		context.addMessage(null, new FacesMessage("Advertencia", "Actualización Exitosa"));
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

	public List<Coberturas> getLstCaoberturas_adc() {
		return lstCaoberturas_adc;
	}

	public void setLstCaoberturas_adc(List<Coberturas> lstCaoberturas_adc) {
		this.lstCaoberturas_adc = lstCaoberturas_adc;
	}


	public Coberturas getSelectedLstCobAdc() {
		return selectedLstCobAdc;
	}

	public void setSelectedLstCobAdc(Coberturas selectedLstCobAdc) {
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


	public List<Coberturas> getLstCaoberturas_adcUbc() {
		return lstCaoberturas_adcUbc;
	}

	public void setLstCaoberturas_adcUbc(List<Coberturas> lstCaoberturas_adcUbc) {
		this.lstCaoberturas_adcUbc = lstCaoberturas_adcUbc;
	}

	public Coberturas getSelectedLstCobAdcUbc() {
		return selectedLstCobAdcUbc;
	}

	public void setSelectedLstCobAdcUbc(Coberturas selectedLstCobAdcUbc) {
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

	public Date getFcDesdeEdita() {
		return fcDesdeEdita;
	}

	public void setFcDesdeEdita(Date fcDesdeEdita) {
		this.fcDesdeEdita = fcDesdeEdita;
	}

	public Date getFcHastaEdita() {
		return fcHastaEdita;
	}

	public void setFcHastaEdita(Date fcHastaEdita) {
		this.fcHastaEdita = fcHastaEdita;
	}

	public String getTxtCd_rubroEdith() {
		return txtCd_rubroEdith;
	}

	public void setTxtCd_rubroEdith(String txtCd_rubroEdith) {
		this.txtCd_rubroEdith = txtCd_rubroEdith;
	}

	public Integer getLsnumRenovaEdit() {
		return lsnumRenovaEdit;
	}

	public void setLsnumRenovaEdit(Integer lsnumRenovaEdit) {
		this.lsnumRenovaEdit = lsnumRenovaEdit;
	}

	public Double getExTasaEdit() {
		return exTasaEdit;
	}

	public void setExTasaEdit(Double exTasaEdit) {
		this.exTasaEdit = exTasaEdit;
	}

	public Double getExFactorEdit() {
		return exFactorEdit;
	}

	public void setExFactorEdit(Double exFactorEdit) {
		this.exFactorEdit = exFactorEdit;
	}

	public Boolean getFlgActivaPlan() {
		return flgActivaPlan;
	}

	public void setFlgActivaPlan(Boolean flgActivaPlan) {
		this.flgActivaPlan = flgActivaPlan;
	}

	public String getCodPlanUbc() {
		return codPlanUbc;
	}

	public void setCodPlanUbc(String codPlanUbc) {
		this.codPlanUbc = codPlanUbc;
	}

	public Boolean getFlgActivaPlanUbc() {
		return flgActivaPlanUbc;
	}

	public void setFlgActivaPlanUbc(Boolean flgActivaPlanUbc) {
		this.flgActivaPlanUbc = flgActivaPlanUbc;
	}

	public String getExParentesco() {
		return exParentesco;
	}

	public void setExParentesco(String exParentesco) {
		this.exParentesco = exParentesco;
	}

	public Date getExFechaNacSubObj() {
		return exFechaNacSubObj;
	}

	public void setExFechaNacSubObj(Date exFechaNacSubObj) {
		this.exFechaNacSubObj = exFechaNacSubObj;
	}

	public String getExCedulaIdent() {
		return exCedulaIdent;
	}

	public void setExCedulaIdent(String exCedulaIdent) {
		this.exCedulaIdent = exCedulaIdent;
	}

	public String getExDetalleEdit() {
		return exDetalleEdit;
	}

	public void setExDetalleEdit(String exDetalleEdit) {
		this.exDetalleEdit = exDetalleEdit;
	}

	public Double getExValAsegEdit() {
		return exValAsegEdit;
	}

	public void setExValAsegEdit(Double exValAsegEdit) {
		this.exValAsegEdit = exValAsegEdit;
	}

	public Double getExPrimaEdit() {
		return exPrimaEdit;
	}

	public void setExPrimaEdit(Double exPrimaEdit) {
		this.exPrimaEdit = exPrimaEdit;
	}

	public String getObservacionesEdith() {
		return ObservacionesEdith;
	}

	public void setObservacionesEdith(String observacionesEdith) {
		ObservacionesEdith = observacionesEdith;
	}

	public List<CaracteristicasVehiculos> getLstCaractVeh() {
		return lstCaractVeh;
	}

	public void setLstCaractVeh(List<CaracteristicasVehiculos> lstCaractVeh) {
		this.lstCaractVeh = lstCaractVeh;
	}

	public Double getExDedMin() {
		return exDedMin;
	}

	public void setExDedMin(Double exDedMin) {
		this.exDedMin = exDedMin;
	}

	public String getNumCotGen() {
		return numCotGen;
	}

	public void setNumCotGen(String numCotGen) {
		this.numCotGen = numCotGen;
	}

	public List<Provincias> getLstProvincias() {
		return lstProvincias;
	}

	public void setLstProvincias(List<Provincias> lstProvincias) {
		this.lstProvincias = lstProvincias;
	}

	public List<SubObjetoCotizacion> getLstSubObjetoEdit() {
		return lstSubObjetoEdit;
	}

	public void setLstSubObjetoEdit(List<SubObjetoCotizacion> lstSubObjetoEdit) {
		this.lstSubObjetoEdit = lstSubObjetoEdit;
	}

	public List<Subagentes> getLstSubagentesEdit() {
		return lstSubagentesEdit;
	}

	public void setLstSubagentesEdit(List<Subagentes> lstSubagentesEdit) {
		this.lstSubagentesEdit = lstSubagentesEdit;
	}

	public String getCodCanalEdt() {
		return codCanalEdt;
	}

	public void setCodCanalEdt(String codCanalEdt) {
		this.codCanalEdt = codCanalEdt;
	}

	public boolean isFlgDlgCobAddObj() {
		return flgDlgCobAddObj;
	}

	public void setFlgDlgCobAddObj(boolean flgDlgCobAddObj) {
		this.flgDlgCobAddObj = flgDlgCobAddObj;
	}

	public boolean isFlgDlgCobAddObj2() {
		return flgDlgCobAddObj2;
	}

	public void setFlgDlgCobAddObj2(boolean flgDlgCobAddObj2) {
		this.flgDlgCobAddObj2 = flgDlgCobAddObj2;
	}

	public boolean isFlgAdcUbPan1() {
		return flgAdcUbPan1;
	}

	public void setFlgAdcUbPan1(boolean flgAdcUbPan1) {
		this.flgAdcUbPan1 = flgAdcUbPan1;
	}

	public boolean isFlgAdcUbPan2() {
		return flgAdcUbPan2;
	}

	public void setFlgAdcUbPan2(boolean flgAdcUbPan2) {
		this.flgAdcUbPan2 = flgAdcUbPan2;
	}

	public boolean isFlgAnoBisiesto() {
		return flgAnoBisiesto;
	}

	public void setFlgAnoBisiesto(boolean flgAnoBisiesto) {
		this.flgAnoBisiesto = flgAnoBisiesto;
	}

	public List<Rubros> getLstRubroGestDoc() {
		return lstRubroGestDoc;
	}

	public void setLstRubroGestDoc(List<Rubros> lstRubroGestDoc) {
		this.lstRubroGestDoc = lstRubroGestDoc;
	}

	public String getTipoArchivoDoc() {
		return tipoArchivoDoc;
	}

	public void setTipoArchivoDoc(String tipoArchivoDoc) {
		this.tipoArchivoDoc = tipoArchivoDoc;
	}

	public List<Archivos> getLstArchivos() {
		return lstArchivos;
	}

	public void setLstArchivos(List<Archivos> lstArchivos) {
		this.lstArchivos = lstArchivos;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public List<TipoModuloCarta> getLstTipoGestDoc() {
		return lstTipoGestDoc;
	}

	public void setLstTipoGestDoc(List<TipoModuloCarta> lstTipoGestDoc) {
		this.lstTipoGestDoc = lstTipoGestDoc;
	}

	public List<TipoModuloCarta> getLstObjetoGestDoc() {
		return lstObjetoGestDoc;
	}

	public void setLstObjetoGestDoc(List<TipoModuloCarta> lstObjetoGestDoc) {
		this.lstObjetoGestDoc = lstObjetoGestDoc;
	}

	public String getObjetoArchivo() {
		return objetoArchivo;
	}

	public void setObjetoArchivo(String objetoArchivo) {
		this.objetoArchivo = objetoArchivo;
	}

	public String getPolizaGestDocu() {
		return polizaGestDocu;
	}

	public void setPolizaGestDocu(String polizaGestDocu) {
		this.polizaGestDocu = polizaGestDocu;
	}

	public Boolean getFlgPolizaDocu() {
		return flgPolizaDocu;
	}

	public void setFlgPolizaDocu(Boolean flgPolizaDocu) {
		this.flgPolizaDocu = flgPolizaDocu;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public List<Coberturas> getFilteredLstCobAdc() {
		return filteredLstCobAdc;
	}

	public void setFilteredLstCobAdc(List<Coberturas> filteredLstCobAdc) {
		this.filteredLstCobAdc = filteredLstCobAdc;
	}

	public List<Coberturas> getFilteredlstCaoberturas_adcUbc() {
		return filteredlstCaoberturas_adcUbc;
	}

	public void setFilteredlstCaoberturas_adcUbc(List<Coberturas> filteredlstCaoberturas_adcUbc) {
		this.filteredlstCaoberturas_adcUbc = filteredlstCaoberturas_adcUbc;
	}

	public Ubicacion getUbcSelectCobAdc() {
		return ubcSelectCobAdc;
	}

	public void setUbcSelectCobAdc(Ubicacion ubcSelectCobAdc) {
		this.ubcSelectCobAdc = ubcSelectCobAdc;
	}

	public String getEspecificacionDed() {
		return especificacionDed;
	}

	public void setEspecificacionDed(String especificacionDed) {
		this.especificacionDed = especificacionDed;
	}

	public Boolean getFlgCargaArchivoObj() {
		return flgCargaArchivoObj;
	}

	public void setFlgCargaArchivoObj(Boolean flgCargaArchivoObj) {
		this.flgCargaArchivoObj = flgCargaArchivoObj;
	}

	public String getEspecificacionCob() {
		return especificacionCob;
	}

	public void setEspecificacionCob(String especificacionCob) {
		this.especificacionCob = especificacionCob;
	}

	public String getEspecificacionDCla() {
		return especificacionDCla;
	}

	public void setEspecificacionDCla(String especificacionDCla) {
		this.especificacionDCla = especificacionDCla;
	}

}
