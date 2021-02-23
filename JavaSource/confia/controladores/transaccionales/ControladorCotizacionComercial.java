package confia.controladores.transaccionales;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;

import confia.entidades.basicos.AseguradoraRamo;
import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Ciudad;
import confia.entidades.basicos.Clausulas;
import confia.entidades.basicos.ClausulasEmitidas;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Coberturas;
import confia.entidades.basicos.CoberturasEmitidas;
import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.Deducibles;
import confia.entidades.basicos.DeduciblesEmitidas;
import confia.entidades.basicos.Direccion;
import confia.entidades.basicos.Ejecutivos;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Marca;
import confia.entidades.basicos.Modelo;
import confia.entidades.basicos.Plan;
import confia.entidades.basicos.Provincias;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.SubagenteRamo;
import confia.entidades.basicos.Subagentes;
import confia.entidades.basicos.Telefono;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Archivos;
import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.entidades.transaccionales.Correspondencia;
import confia.entidades.transaccionales.Cotizacion;
import confia.entidades.transaccionales.FormaPago;
import confia.entidades.transaccionales.ObjetoCotizacion;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.transaccionales.SubObjetoCotizacion;
import confia.entidades.transaccionales.Ubicacion;
import confia.entidades.vistas.CotizacionesPendientes;
import confia.entidades.vistas.ProspeccionesView;
import confia.procedures.ProcedimientosAlmacenadosDB;
import confia.procedures.servicioProcedures;
import confia.reportes.EmailSenderService;
import confia.servicios.basicos.ServicioAseguradoraRamo;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioCiudad;
import confia.servicios.basicos.ServicioClausulas;
import confia.servicios.basicos.ServicioClausulasEmitidas;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioCoberturas;
import confia.servicios.basicos.ServicioCoberturasEmitidas;
import confia.servicios.basicos.ServicioContacto;
import confia.servicios.basicos.ServicioDeducibles;
import confia.servicios.basicos.ServicioDireccion;
import confia.servicios.basicos.ServicioEjecutivos;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioMarca;
import confia.servicios.basicos.ServicioModelo;
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
import confia.servicios.transaccionales.ServicioFormaPago;
import confia.servicios.transaccionales.ServicioObjetoCotizacion;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.transaccionales.ServicioSubObjetoCotizacion;
import confia.servicios.transaccionales.ServicioUbicacion;
import confia.servicios.vistas.ServicioProspecciones;
import upp.confia.controladores.UppSolicitud;
import upp.confia.servicios.ServicioUppNuevaSolicitud;

@ManagedBean(name = "ControladorCotizacionComercial")
@ViewScoped
public class ControladorCotizacionComercial {
	@EJB
	private ServicioDireccion srvDireccion;
	@EJB
	private ServicioTelefono srvTelefono;
	@EJB
	private ServicioContacto srvContacto;
	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioCiudad srvCiudad;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioProvincias srvProvincias;
	@EJB
	private ServicioUsuarios srvUsuario;
	@EJB
	private ServicioAseguradoraRamo srvAsegramo;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioPlan srvPlan;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioSubagentes srvSubagentes;
	@EJB
	private ServicioSubagenteRamo srvSubagenteRamo;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioEjecutivos srvEjecutivos;
	@EJB
	private ServicioCotizacion srvCotizacion;
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;
	private servicioProcedures srvProcedures;
	@EJB
	private ServicioArchivos srvArchivos;
	@EJB
	private ServicioProspecciones srvProspecciones;
	@EJB
	private ServicioMarca srvMarcas;
	@EJB
	private ServicioModelo srvModelos;
	@EJB
	private ServicioUbicacion srvUbicacion;
	@EJB
	private ServicioObjetoCotizacion srvObjetoCotizacion;
	@EJB
	private ServicioSubObjetoCotizacion srvSubObjetoCotizacion;
	@EJB
	private ServicioCaracteristicasVehiculos srvCaracteristicasVehiculos;
	@EJB
	private ServicioFormaPago srvFormaPago;
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
	private ServicioCorrespondencia srvCorrespondencia;
	@EJB
	private ServicioRubros srvRubrosCartas;
	@EJB
	private ServicioContacto srvContactosCarta;

	private ProcedimientosAlmacenadosDB srvStorePRocedure;

	private Clientes cliente;
	private Direccion direccion;
	private Telefono telefono;
	private Contacto contacto;
	private List<Ciudad> lstCiudad;
	private List<Rubros> lsrRubroSectorDirec;
	private List<Provincias> lstProvincias;
	private List<Usuarios> lstUsuarios;
	private String selectedUsuarios;
	private String usuarioId, nmUsr;
	private Usuarios usr;
	private String consCed;
	private String codciudad, codSector, codProvincia, codCiudadTelf;
	private String codAseguradora;
	private List<AseguradoraRamo> listadoAsegRamo;
	private String codRamo;
	private List<Plan> listaPlan;
	private List<Aseguradoras> listadoAseguradoras;
	private String codPlan;
	private String codGrupoEconomico;
	private String codCanal;
	private List<Subagentes> lstSubagentes;
	private List<SubagenteRamo> lstSubagentesRamo;
	private List<GrupoContratante> listaGrupoContratante;
	private String codEjecutivo;
	private List<Ejecutivos> listaEjecutivos;
	private Date fcDesde;
	private Date fcHasta;
	private String asunto;
	private String observaciones;
	private Boolean existeClie;
	private String tpPresona;
	private EmailSenderService email;
	private String filename;
	private List<Archivos> lstArchivos;
	private String nmClie;
	private String dirClie;
	private String refClie;
	private String numPa;
	private String numOfi;
	private String extClie;
	private String numCelClie;
	private String correoClie;
	private String nmContac;
	private String numTelCon;
	private String numCel;
	private String correo;
	private String cargoCont;
	private String depaCpmta;
	private String direccionConta;
	private List<Rubros> lstRubroGestDoc;
	private String tipoArchivoDoc;
	private List<Archivos> lstArchivosEx;
	private Boolean lbbtn;

	// variable prospeccion
	private String numCotGen;
	private String apellidoRazonSocial;
	private List<ProspeccionesView> lstProspeccion;
	private ProspeccionesView selectedProspeccion;
	private Cotizacion datosCotizacion;
	private RamoCotizacion datosRamoCotizacion;
	private ObjetoCotizacion datosObjetoCotizacion;
	public SimpleDateFormat formato;
	public String patron = "dd/MMM/yyyy";
	private String factorObjeto;
	private List<Marca> listadoMarcas;
	private List<Modelo> listadoModelos;
	private Double valorAseguradorExtra;

	private String tpFromaPago;
	private Double frmPagoPrimaTot;
	private Double frmPagoDerechoEmision;
	private Double frmPagoSegCampesino;
	private Double frmPagoSuperBancos;
	private Double frmPagoIva;
	private Double frmPagoTotal;
	private Double frmPagoCuotaIni;
	private Integer frmPagoNumPago;
	private String frmObservaciones;

	private CaracteristicasVehiculos datosCaracteristicasVehiculos;
	private Ubicacion datosUbicacion;

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

	// CARTAS
	private List<Rubros> lstRubrosCarta;
	private List<Contacto> lstContactoCarta;
	private String nmCarta;
	private Contacto selectedContactoCarta;
	private String notasAdicionalesCarta;
	private Rubros objCarta;

	public ControladorCotizacionComercial() {
		Rubros objCarta = new Rubros();
		lstRubrosCarta = new ArrayList<Rubros>();
		lstContactoCarta = new ArrayList<Contacto>();
		nmCarta = null;
		notasAdicionalesCarta = null;
		datosUbicacion = new Ubicacion();
		datosCotizacion = new Cotizacion();
		datosRamoCotizacion = new RamoCotizacion();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		lstProspeccion = new ArrayList<ProspeccionesView>();
		lstCiudad = new ArrayList<Ciudad>();
		lsrRubroSectorDirec = new ArrayList<Rubros>();
		lstProvincias = new ArrayList<Provincias>();
		cliente = new Clientes();
		direccion = new Direccion();
		telefono = new Telefono();
		contacto = new Contacto();
		lstSubagentes = new ArrayList<Subagentes>();
		lstSubagentesRamo = new ArrayList<SubagenteRamo>();
		listadoAseguradoras = new ArrayList<Aseguradoras>();
		listadoAsegRamo = new ArrayList<AseguradoraRamo>();
		listaGrupoContratante = new ArrayList<GrupoContratante>();
		listaEjecutivos = new ArrayList<Ejecutivos>();
		fcDesde = new Date();
		fcHasta = new Date();
		srvProcedures = new servicioProcedures();
		email = new EmailSenderService();
		lstArchivos = new ArrayList<Archivos>();
		nmClie = "";
		lstRubroGestDoc = new ArrayList<Rubros>();
		lstArchivosEx = new ArrayList<Archivos>();
		formato = new SimpleDateFormat(patron);
		listadoMarcas = new ArrayList<Marca>();
		srvStorePRocedure = new ProcedimientosAlmacenadosDB();
		// coberturas emitidas
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstCoberturas = new ArrayList<Coberturas>();
		lstDeducibles = new ArrayList<Deducibles>();
		lstClausulas = new ArrayList<Clausulas>();
	}

	@PostConstruct
	public void recuperaInicio() {
		listadoMarcas = srvMarcas.listaMarca();
		System.out.println("MArca:" + listadoMarcas.size());
		lstCiudad = srvCiudad.recuperaListaCiudad();
		lsrRubroSectorDirec = srvRubros.listadoRubrosCod("102");
		lstRubroGestDoc = srvRubros.listadoRubrosGestionDocu("400", "CLIENTE", "INDIVIDUAL");
		lstProvincias = srvProvincias.listaProvincias();
		lstUsuarios = srvUsuario.listaUsuariosActivos();
		listadoAseguradoras = srvAseguradoras.listaAseguradoras();
		listaGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		listaEjecutivos = srvEjecutivos.listaEjecutivos();

		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		System.out.println("USuario Logeado:" + usuarioId);
		usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(usuarioId);
		nmUsr = usr.getUsrnombres();
		nmUsr = nmUsr.concat(" ");
		nmUsr = nmUsr.concat(usr.getUsrapellidos());
		existeClie = false;
		nmClie = "";
		dirClie = "";
		refClie = "";
		numPa = "";
		numOfi = "";
		extClie = "";
		numCelClie = "";
		correoClie = "";
		nmContac = "";
		numTelCon = "";
		numCel = "";
		correo = "";
		cargoCont = "";
		depaCpmta = "";
		direccionConta = "";
		tipoArchivoDoc = "%";
		lbbtn = true;
		factorObjeto = "100";
		valorAseguradorExtra = 0.0;
		lstRubrosCarta = srvRubrosCartas.listadoCartasPorModulo("MODULO_COMERCIAL");
	}

	public void verificaDocuGest() {
		System.out.println("Gestion Documento:" + tipoArchivoDoc);
		System.out.println("tpPresona BTN:" + tpPresona);
		System.out.println("EXISTE CLIENTE:" + existeClie);
		if (existeClie == false) {
			// verifico datos cliente
			try {
				if (direccion == null) {
					direccion = new Direccion();
				}
			} catch (Exception e) {
				direccion = new Direccion();
			}
			try {
				if (telefono == null) {
					telefono = new Telefono();
				}
			} catch (Exception e) {
				telefono = new Telefono();
			}
			try {
				if (contacto == null) {
					contacto = new Contacto();
				}
			} catch (Exception e) {
				contacto = new Contacto();
			}

			System.out.println("PASA VALIDACION");

			try {
				direccion.setDireccion(dirClie);
			} catch (Exception e) {
				dirClie = "";
			}
			try {
				direccion.setReferencia(refClie);
			} catch (Exception e) {
				refClie = "";
			}
			try {
				telefono.setTelefono(numPa);
			} catch (Exception e) {
				numPa = "";
			}

			try {
				telefono.setTelefono_oficina(numOfi);
			} catch (Exception e) {
				numOfi = "";
			}
			try {
				telefono.setExt(extClie);
			} catch (Exception e) {
				extClie = "";
			}
			try {
				telefono.setTelefono_celular(numCelClie);
			} catch (Exception e) {
				numCelClie = "";
			}
			try {
				telefono.setCorreo(correoClie);
			} catch (Exception e) {
				correoClie = "";
			}
			try {
				contacto.setNombre_contacto(nmContac);
			} catch (Exception e) {
				nmContac = "";
			}
			try {
				contacto.setTelefono_contacto(numTelCon);
			} catch (Exception e) {
				numTelCon = "";
			}
			try {
				contacto.setCelular_contacto(numCel);
			} catch (Exception e) {
				numCel = "";
			}
			try {
				contacto.setMail_contacto(correo);
			} catch (Exception e) {
				correo = "";
			}
			try {
				contacto.setCargo_contacto(cargoCont);
			} catch (Exception e) {
				cargoCont = "";
			}
			try {
				contacto.setDepartamento_contacto(depaCpmta);
			} catch (Exception e) {
				depaCpmta = "";
			}
			try {
				contacto.setDireccion_contacto(direccionConta);
			} catch (Exception e) {
				direccionConta = "";
			}

			try {
				direccion.setCd_ciudad(Integer.valueOf(codciudad));
			} catch (Exception e) {
				System.out.println("Error actualizar codigo ciudad");
			}
			try {
				direccion.setCd_rubro(Integer.valueOf(codSector));
			} catch (Exception e) {
				System.out.println("Error actualizar codigo sector");
			}
			try {
				direccion.setCd_provincia(codProvincia);
			} catch (Exception e) {
				System.out.println("Error actualizar codigo provincia");
			}
			try {
				telefono.setCd_ciudad(Integer.valueOf(codCiudadTelf));
			} catch (Exception e) {
				System.out.println("Error actualizar codigo telefono ciudad");
			}

			cliente.setTipo_persona_cliente("NAT");
			cliente.setTipo_identificacion_cliente(tpPresona);
			cliente.setIdentificacion_cliente(consCed.trim());
			try {
				srvClientes.insertarClientes(cliente);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error al Ingresar el Cliente",
						"Comuníquese con el Administrador del sistema"));
				System.out.println("Error al ingresar el cliente");
				return;
			}
			cliente = new Clientes();
			consCed = consCed.trim();
			cliente = srvClientes.listaClientesXIdentClie(consCed);
			direccion.setCd_aseguradora(0);
			direccion.setCd_cliente(cliente.getCd_cliente());
			telefono.setCd_aseguradora(0);
			telefono.setCd_cliente(cliente.getCd_cliente());
			contacto.setCd_aseguradora(0);
			contacto.setCd_cliente(cliente.getCd_cliente());

			try {
				srvDireccion.insertarDireccion(direccion);
			} catch (Exception e) {
				System.out.println("Error al ingresar el direccion");
			}
			try {
				srvTelefono.insertarTelefonos(telefono);
			} catch (Exception e) {
				System.out.println("Error al ingresar el telefono");
			}
			try {
				srvContacto.insertarContacto(contacto);
			} catch (Exception e) {
				System.out.println("Error al ingresar el contacto");
			}
			existeClie = true;
			System.out.println("Se cuardo datos cliente exitosamente");
		}

	}

	public void gestionDocumental() {
		String tipoAux;
		String tpDoc = null;
		try {
			tpDoc = cliente.getTipo_cliente();
			if (cliente.getTipo_cliente().isEmpty()) {
				tpDoc = "IND";
			}
		} catch (Exception e) {
			tpDoc = "IND";
		}
		System.out.println("TIPO DOCUMENTO:" + tpDoc);
		if (tpDoc.equals("IND")) {
			tipoAux = "INDIVIDUAL";
		} else {
			tipoAux = "CORPORATIVO";
		}
		lstRubroGestDoc = new ArrayList<Rubros>();
		lstRubroGestDoc = srvRubros.listadoRubrosGestionDocu("400", "CLIENTE", tipoAux);
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("consCed:" + consCed);
		System.out.println("TIPO DOCU:" + tipoArchivoDoc);
		System.out.println("tpPresona:" + tpPresona);
		try {
			if (tpPresona.isEmpty() || tpPresona == null) {
				tpPresona = "CED";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (tipoArchivoDoc.isEmpty() || tipoArchivoDoc == "%") {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el tipo de documento"));
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (consCed.isEmpty() || consCed == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
			return;
		}

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
		String nmArchivo = getRandomImageNameFile(extension, cliente);
		System.out.println("nmArchivo:" + nmArchivo);
		guardarArchivo(nmArchivo, contenido);
		FacesMessage message = new FacesMessage("Advertencia", event.getFile().getFileName() + " Archivo cargado.");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void guardarArchivo(String nombre, byte[] contenido) {
		// esta clase no sirve para escribir en el archivo creado, xq maneja los
		// byte
		FileOutputStream fos = null;
		// tenemos un objeto de tipo file, aqui no se crea el archivo
		File carpetaPrincipal = new File("C:\\java\\wildfly-9.0.2.Final\\welcome-content\\documentos\\clientes");
		// se crea la carpeta
		carpetaPrincipal.mkdir();
		String nombreSinEspacios = "";
		filename = getRandomImageName();
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
				+ filename + "</a><br/>";
		System.out.println("filename:" + nmArchivo);

		Rubros rb = new Rubros();
		rb = srvRubros.recuperaRubro(tipoArchivoDoc);
		Archivos arc = new Archivos();
		arc.setModulo("EMISIONES PENDIENTES COMERCIAL");
		arc.setUbicacion(nmArchivo);
		arc.setCd_rubro(String.valueOf(rb.getCd_rubro()));
		arc.setDesc_docu(rb.getDesc_rubro());

		lstArchivos.add(arc);

	}

	public void buscarClientes() {
		System.out.println("**********BUsca cliente");
		consCed = consCed.trim();
		try {
			if (consCed.isEmpty() || consCed == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia", "Ingrese el número de identificación del Cliente"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese el número de identificación del Cliente"));
			return;
		}
		cliente = srvClientes.listaClientesXIdentClie(consCed);
		try {
			if (cliente != null) {
				existeClie = true;
			} else {
				existeClie = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (cliente.getCd_cliente() == null) {
				cliente = new Clientes();
				cliente.setIdentificacion_cliente(consCed);
				direccion = new Direccion();
				telefono = new Telefono();
				contacto = new Contacto();

			} else {
				direccion = new Direccion();
				direccion = srvDireccion.BuscaDireccionCodCliente(String.valueOf(cliente.getCd_cliente()));
				try {
					dirClie = direccion.getDireccion();
				} catch (Exception e) {
					dirClie = "";
				}
				try {
					refClie = direccion.getReferencia();
				} catch (Exception e) {
					refClie = "";
				}

				try {
					codciudad = String.valueOf(direccion.getCd_ciudad());
				} catch (Exception e) {
					codciudad = "0";
				}
				System.out.println("codciudad:" + codciudad);
				try {
					codSector = String.valueOf(direccion.getCd_rubro());
				} catch (Exception e) {
					codSector = "0";
				}
				System.out.println("codSector:" + codSector);
				try {
					codProvincia = String.valueOf(direccion.getCd_provincia());
				} catch (Exception e) {
					codProvincia = "0";
				}
				telefono = new Telefono();
				telefono = srvTelefono.BuscaTelefonoCodCliente(String.valueOf(cliente.getCd_cliente()));
				try {
					numPa = telefono.getTelefono();
				} catch (Exception e) {
					numPa = "";
				}

				try {
					numOfi = telefono.getTelefono_oficina();
				} catch (Exception e) {
					numOfi = "";
				}
				try {
					extClie = telefono.getExt();
				} catch (Exception e) {
					extClie = "";
				}
				try {
					numCelClie = telefono.getTelefono_celular();
				} catch (Exception e) {
					numCelClie = "";
				}
				try {
					correoClie = telefono.getCorreo();
				} catch (Exception e) {
					correoClie = "";
				}
				try {
					codCiudadTelf = String.valueOf(telefono.getCd_ciudad());
				} catch (Exception e) {
					codCiudadTelf = "0";
				}

				contacto = new Contacto();
				contacto = srvContacto.consultaContactosCdCliente(String.valueOf(cliente.getCd_cliente()));
				try {
					nmContac = contacto.getNombre_contacto();
				} catch (Exception e) {
					nmContac = "";
				}
				try {
					numTelCon = contacto.getTelefono_contacto();
				} catch (Exception e) {
					numTelCon = "";
				}
				try {
					numCel = contacto.getCelular_contacto();
				} catch (Exception e) {
					numCel = "";
				}
				try {
					correo = contacto.getMail_contacto();
				} catch (Exception e) {
					correo = "";
				}
				try {
					cargoCont = contacto.getCargo_contacto();
				} catch (Exception e) {
					cargoCont = "";
				}
				try {
					depaCpmta = contacto.getDepartamento_contacto();
				} catch (Exception e) {
					depaCpmta = "";
				}
				try {
					direccionConta = contacto.getDireccion_contacto();
				} catch (Exception e) {
					direccionConta = "";
				}
			}
		} catch (Exception e) {
			cliente = new Clientes();
			cliente.setIdentificacion_cliente(consCed);
			cliente.setTipo_identificacion_cliente(tpPresona);
		}

		lstArchivosEx = new ArrayList<Archivos>();
		lstArchivosEx = srvArchivos.recuperaArchivosCedula("CLIENTE", "%", consCed.trim());
	}

	public void listarRamosAseguradora() {
		listadoAsegRamo = new ArrayList<AseguradoraRamo>();
		listadoAsegRamo = srvAsegramo.listaAseguradoraRamos(Integer.valueOf(codAseguradora));
		listaPlan = new ArrayList<Plan>();
		codRamo = "0";
		codPlan = "0";
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
	}

	public void listarPlanes() {
		Integer tpRam = 0;

		// verifico configuraci�n del ramo para el manejo del plan en la
		// Ubicaci�n o RamoCotizacion
		tpRam = srvRamo.tipoRamo(Integer.decode(codRamo));
		if (tpRam.equals(1)) {
			// ramo asistencia medica no despliega plan
			listaPlan = new ArrayList<Plan>();
		} else {
			listaPlan = new ArrayList<Plan>();
			listaPlan = srvPlan.listaPlanes(String.valueOf(codRamo), String.valueOf(codAseguradora));
		}
		lstSubagentesRamo = new ArrayList<SubagenteRamo>();
		lstSubagentesRamo = srvSubagenteRamo.listaSubagenteRamo(codRamo);
		lstSubagentes = new ArrayList<Subagentes>();
		lstSubagentes = srvSubagentes.recuperaSubagenteRamo(lstSubagentesRamo);
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();

	}
	
	public void listarPlanesCambio() {
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		
	}

	public void guardaProspecion() {
		// valido el ingreso de todos los datos
		try {
			if (consCed.isEmpty() || consCed == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
			return;
		}
		try {
			if (codAseguradora.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion la Aseguradora"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion la Aseguradora"));
			return;
		}
		try {
			if (codRamo.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ramo"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ramo"));
			return;
		}

		try {
			if (codPlan.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Plan"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Plan"));
			return;
		}
		try {
			if (codCanal.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Canal"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Canal"));
			return;
		}
		try {
			if (codEjecutivo.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ejecutivo"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ejecutivo"));
			return;
		}

		// verifico datos cliente
		try {
			if (direccion == null) {
				direccion = new Direccion();
			}
		} catch (Exception e) {
			direccion = new Direccion();
		}
		try {
			if (telefono == null) {
				telefono = new Telefono();
			}
		} catch (Exception e) {
			telefono = new Telefono();
		}
		try {
			if (contacto == null) {
				contacto = new Contacto();
			}
		} catch (Exception e) {
			contacto = new Contacto();
		}
		try {
			direccion.setDireccion(dirClie);
		} catch (Exception e) {
			dirClie = "";
		}
		try {
			direccion.setReferencia(refClie);
		} catch (Exception e) {
			refClie = "";
		}
		try {
			telefono.setTelefono(numPa);
		} catch (Exception e) {
			numPa = "";
		}

		try {
			telefono.setTelefono_oficina(numOfi);
		} catch (Exception e) {
			numOfi = "";
		}
		try {
			telefono.setExt(extClie);
		} catch (Exception e) {
			extClie = "";
		}
		try {
			telefono.setTelefono_celular(numCelClie);
		} catch (Exception e) {
			numCelClie = "";
		}
		try {
			telefono.setCorreo(correoClie);
		} catch (Exception e) {
			correoClie = "";
		}
		try {
			contacto.setNombre_contacto(nmContac);
		} catch (Exception e) {
			nmContac = "";
		}
		try {
			contacto.setTelefono_contacto(numTelCon);
		} catch (Exception e) {
			numTelCon = "";
		}
		try {
			contacto.setCelular_contacto(numCel);
		} catch (Exception e) {
			numCel = "";
		}
		try {
			contacto.setMail_contacto(correo);
		} catch (Exception e) {
			correo = "";
		}
		try {
			contacto.setCargo_contacto(cargoCont);
		} catch (Exception e) {
			cargoCont = "";
		}
		try {
			contacto.setDepartamento_contacto(depaCpmta);
		} catch (Exception e) {
			depaCpmta = "";
		}
		try {
			contacto.setDireccion_contacto(direccionConta);
		} catch (Exception e) {
			direccionConta = "";
		}

		try {
			direccion.setCd_ciudad(Integer.valueOf(codciudad));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo ciudad");
		}
		try {
			direccion.setCd_rubro(Integer.valueOf(codSector));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo sector");
		}
		try {
			direccion.setCd_provincia(codProvincia);
		} catch (Exception e) {
			System.out.println("Error actualizar codigo provincia");
		}
		try {
			telefono.setCd_ciudad(Integer.valueOf(codCiudadTelf));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo telefono ciudad");
		}
		if (existeClie == true) {
			// actualizo datos cliente
			srvClientes.actualizaClientes(cliente);
			srvDireccion.actualizaDireccion(direccion);
			srvTelefono.actualizaTelefono(telefono);
			srvContacto.actualizaContacto(contacto);
		} else {
			cliente.setTipo_persona_cliente("NAT");
			cliente.setTipo_identificacion_cliente(tpPresona);
			cliente.setIdentificacion_cliente(consCed.trim());
			try {
				srvClientes.insertarClientes(cliente);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error al Ingresar el Cliente",
						"Comuníquese con el Administrador del sistema"));
				System.out.println("Error al ingresar el cliente");
				return;
			}
			cliente = new Clientes();
			consCed = consCed.trim();
			cliente = srvClientes.listaClientesXIdentClie(consCed);
			direccion.setCd_aseguradora(0);
			direccion.setCd_cliente(cliente.getCd_cliente());
			telefono.setCd_aseguradora(0);
			telefono.setCd_cliente(cliente.getCd_cliente());
			contacto.setCd_aseguradora(0);
			contacto.setCd_cliente(cliente.getCd_cliente());

			try {
				srvDireccion.insertarDireccion(direccion);
			} catch (Exception e) {
				System.out.println("Error al ingresar el direccion");
			}
			try {
				srvTelefono.insertarTelefonos(telefono);
			} catch (Exception e) {
				System.out.println("Error al ingresar el telefono");
			}
			try {
				srvContacto.insertarContacto(contacto);
			} catch (Exception e) {
				System.out.println("Error al ingresar el contacto");
			}
		}
		System.out.println("Se cuardo datos cliente exitosamente");
		// inserto la cotizaci�n
		Integer liCompania;
		String lsCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
				.toString();
		String numCotizacion;
		try {
			liCompania = Integer.parseInt(lsCompania);
		} catch (Exception e) {
			liCompania = 1;
		}
		System.out.println("liCompania:" + liCompania);
		Boolean flgExisteBoolean = false;
		try {
			if (datosCotizacion.getCd_cotizacion() == null) {
				flgExisteBoolean = true;
			}
		} catch (Exception e) {
			flgExisteBoolean = true;
		}

		System.out.println("flgExisteBoolean:" + flgExisteBoolean);
		// cotizacioón no existente
		if (flgExisteBoolean) {
			System.err.println("Ingreso nueva cotizacion prospeccion");
			datosCotizacion = new Cotizacion();
			datosCotizacion.setCd_aseguradora(Integer.valueOf(codAseguradora));
			datosCotizacion.setFc_ini_cot_date(fcDesde);
			datosCotizacion.setFc_fin_cot_date(fcHasta);
			datosCotizacion.setCd_rubro(1010); // PROSPECCION
			datosCotizacion.setCd_compania(liCompania);
			datosCotizacion.setCd_cliente(cliente.getCd_cliente());
			datosCotizacion.setCd_cliente_asegurado(cliente.getCd_cliente());
			datosCotizacion.setFact_periodica_cot(0);
			datosCotizacion.setNum_renovaciones_cot(0);
			datosCotizacion.setObservaciones(observaciones);
			datosCotizacion.setUsrid(Integer.valueOf(selectedUsuarios));

			Integer cdCotMax = srvCotizacion.insertarCotizacion(datosCotizacion);
			if (cdCotMax == 1) {
				cdCotMax = srvCotizacion.codigoMaxCotizacion();
			}
			datosCotizacion = new Cotizacion();
			datosCotizacion = srvCotizacion.recuperaCotizacionPorCodigo(cdCotMax, liCompania);
			numCotizacion = datosCotizacion.getNum_cotizacion();

			// INGRESO RAMO
			datosRamoCotizacion = new RamoCotizacion();

			datosRamoCotizacion.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
			datosRamoCotizacion.setCd_compania(liCompania);
			datosRamoCotizacion.setFc_ini_vig_date(fcDesde);
			datosRamoCotizacion.setFc_fin_vig_date(fcHasta);
			datosRamoCotizacion.setCd_subagente(Integer.valueOf(codCanal));
			datosRamoCotizacion.setCd_ramo(Integer.valueOf(codRamo));
			datosRamoCotizacion.setCd_ejecutivo(Integer.valueOf(codEjecutivo));
			datosRamoCotizacion.setCd_grupo_contratante(Integer.valueOf(codGrupoEconomico));
			datosRamoCotizacion.setCd_plan(Integer.valueOf(codPlan));
			Integer resInsert = srvRamoCotizacion.insertarRamoCotizacion(datosRamoCotizacion);

			// Clausulas coberturas y deducibles
			if (resInsert == 1) {
				srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
						String.valueOf(datosCotizacion.getCd_compania()));
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Registro Exitoso", "Se guardó la prospección No." + numCotizacion));
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Error al generar el proceso"));
				return;
			}
			datosRamoCotizacion = new RamoCotizacion();
			datosRamoCotizacion = srvRamoCotizacion.recuperaRamoCotizacionAnexo(datosCotizacion.getCd_cotizacion(),
					liCompania);
			// vigencia
			Integer fcDesdeJul, FcHastaJul, llDiasVigenciaNuevo;
			String sdFcDesde, asFcHasta;

			sdFcDesde = formato.format(fcDesde);
			fcDesdeJul = srvProcedures.fechaJuliana(sdFcDesde);
			asFcHasta = formato.format(fcHasta);
			FcHastaJul = srvProcedures.fechaJuliana(asFcHasta);
			llDiasVigenciaNuevo = srvProcedures.diasVigencias(fcDesdeJul, FcHastaJul);

			// guarda Ubicacion
			datosUbicacion = new Ubicacion();
			datosUbicacion.setCd_compania(liCompania);
			datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
			datosUbicacion.setFc_ini_ubc_date(fcDesde);
			datosUbicacion.setFc_fin_ubc_date(fcHasta);
			datosUbicacion.setFc_ini_ubicacion(fcDesdeJul);
			datosUbicacion.setFc_fin_ubicacion(FcHastaJul);
			datosUbicacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosUbicacion.setDsc_ubicacion("COMERCIAL");
			Integer res = srvUbicacion.insertarUbicacion(datosUbicacion);
			if (res != 1) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicación Comuníquese con el Administrador del Sistema"));
				return;
			}
			res = srvUbicacion.codigoMaxUbc(datosRamoCotizacion.getCd_ramo_cotizacion());
			datosUbicacion = new Ubicacion();
			datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(res, liCompania);

			// Guarda ObjetoCotizacion
			Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;
			String descObjeto;
			int objaux;

			datosObjetoCotizacion.setCd_compania(liCompania);
			datosObjetoCotizacion.setFc_ini_obj_cot_date(fcDesde);
			datosObjetoCotizacion.setFc_fin_obj_cot_date(fcHasta);
			datosObjetoCotizacion.setFc_ini_obj_cot(fcDesdeJul);
			datosObjetoCotizacion.setFc_fin_obj_cot(FcHastaJul);
			datosObjetoCotizacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
			datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorObjeto));

			try {
				descObjeto = datosObjetoCotizacion.getDescripcion_objeto();
				if (descObjeto == null) {
					datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
				} else {
					datosObjetoCotizacion.setDescripcion_objeto(descObjeto.trim().toUpperCase());
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
			}
			try {
				primaTotaObj = datosObjetoCotizacion.getPrima_objeto();
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
					datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setPrima_objeto(0.00);
			}
			try {
				totalAseguradoObj = datosObjetoCotizacion.getValor_asegurador_objeto();
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
				datosObjetoCotizacion.setValor_asegurador_objeto(0.00);
			}
			try {
				totalAseguradoObj = totalAseguradoObj + valorAseguradorExtra;
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
			}

			System.out.println("TOTAL ASEGURADO:" + totalAseguradoObj);
			datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
			res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);

			if (res == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
				return;
			}
			res = srvObjetoCotizacion.codigoMaxObjetoCot(datosUbicacion.getCd_ubicacion());
			objaux = res;
			datosObjetoCotizacion = new ObjetoCotizacion();
			datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(res, liCompania);

			// GUARDA DATOS DE LOS EXTRAS EN CASO DE EXISTIR
			if (valorAseguradorExtra > 0.0) {
				SubObjetoCotizacion subObj = new SubObjetoCotizacion();
				subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
				subObj.setCd_compania(liCompania);
				subObj.setDescripcion_subobjeto("EXTRA - COMERCIAL");
				subObj.setFc_ini_subobj_cot_date(fcDesde);
				subObj.setFc_fin_subobj_cot_date(fcHasta);
				subObj.setFc_ini_subobj_cot(fcDesdeJul);
				subObj.setFc_fin_subobj_cot(FcHastaJul);
				subObj.setDias_vigencia(llDiasVigenciaNuevo);
				subObj.setValor_asegurador_subobjeto(valorAseguradorExtra);
				subObj.setFactor_subobjeto(Double.valueOf(factorObjeto));
				subObj.setTasa_subobjeto(0.00);
				subObj.setPrima_subobjeto(0.00);
				res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
					return;
				}
			}

			// GUARDO LAS CARACTERISTICAS DEL VEHICULO
			try {
				datosCaracteristicasVehiculos.setCd_compania(liCompania);
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

			// actualizo la Ubicación actual
			try {
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
				}
			} catch (Exception e) {
				primaTotaObj = 0.00;
			}

			// actualizo la Ubicación actual
			datosUbicacion.setValor_asegurado_ubicacion(totalAseguradoObj);
			datosUbicacion.setValor_prima_ubicacion(primaTotaObj);
			srvUbicacion.actualizaUbicacion(datosUbicacion);

			// actualizo Ramo
			datosRamoCotizacion.setTotal_asegurado(totalAseguradoObj);
			datosRamoCotizacion.setTotal_prima(primaTotaObj);
			srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
			System.out.println("GUARDO COTIZACION PROSPECCION");

			// Inserta Forma Pago
			try {
				FormaPago formaPagoAux = new FormaPago();
				formaPagoAux.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
				formaPagoAux.setCd_compania(liCompania);
				formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
				formaPagoAux.setSuperBanco_forma_Pago(frmPagoSuperBancos);
				formaPagoAux.setDerecho_Emision_formaPago(frmPagoDerechoEmision);
				formaPagoAux.setSeguroCampesion_forma_Pago(frmPagoSegCampesino);
				formaPagoAux.setIva_Forma_Pago(frmPagoIva);
				formaPagoAux.setTotal_Pago_FormaPago(frmPagoTotal);
				formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
				formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
				formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
				formaPagoAux.setObservaciones(frmObservaciones);

				res = srvFormaPago.insertaFormaPago(formaPagoAux);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
					return;
				}
			} catch (Exception e) {
				System.err.println("No se actualiza la forma de pago");
			}

			// guarda los archivos adjuntos
			for (Archivos archivo : lstArchivos) {
				archivo.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
				archivo.setCd_cliente(String.valueOf(cliente.getCd_cliente()));
				srvArchivos.insertaArchivos(archivo);
			}
			// envia la tarea al UPP

			UppSolicitud uppSol = new UppSolicitud();
			uppSol.setAlmacen_id(121);
			// Comite Clientes
			System.out.println("USuario Logeado:" + usuarioId);
			ServicioUppNuevaSolicitud srvServicioUppNuevaSolicitud = new ServicioUppNuevaSolicitud();
			Integer tecRepoInteger = srvServicioUppNuevaSolicitud.codigoUsuario(usr.getUsrlogin());
			uppSol.setTecnico_reporta(tecRepoInteger);
			uppSol.setTecnico_asignado(tecRepoInteger);
			uppSol.setDetalle_problema("DAR SEGUIMIENTO SEMANAL DE LA PROSPECCION");
			uppSol.setSolucion_tecnica("EMISIÓN Y ENTREGA DE LA PÓLIZA");
			String problemaString;
			problemaString = "POSPECCION CLIENTE -";
			problemaString = problemaString.concat(nmClie);
			problemaString = problemaString.concat("-");
			problemaString = problemaString.concat(datosCotizacion.getNum_cotizacion());
			uppSol.setProblema(problemaString);
			uppSol.setEstados_id(1);
			uppSol.setFecha(new Date());
			uppSol.setFecha_asignada(fcHasta);
			uppSol.setCliente_id(77); // subcomite uppSol.setId_padre(0);
			uppSol.setUsrsupervisa(138);
			uppSol.setCod_comite_subcomite("COM-CLI_PRO-CLI");
			uppSol.setSolicitud_proy(0);

			srvServicioUppNuevaSolicitud = new ServicioUppNuevaSolicitud();
			srvServicioUppNuevaSolicitud.ingresa_solicitud_upp(uppSol);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Proceso Exitoso ", "Se envió la tarea al Sistema UPP"));

		} else {
			System.err.println("Edita cotizacion prospeccion");
			// actualiza cotizaciòn ya ingresada
			datosCotizacion.setCd_aseguradora(Integer.valueOf(codAseguradora));
			datosCotizacion.setCd_rubro(1010); // PROSPECCION
			datosCotizacion.setCd_compania(liCompania);
			datosCotizacion.setCd_cliente(cliente.getCd_cliente());
			datosCotizacion.setCd_cliente_asegurado(cliente.getCd_cliente());
			datosCotizacion.setFact_periodica_cot(0);
			datosCotizacion.setNum_renovaciones_cot(0);
			datosCotizacion.setObservaciones(observaciones);
			srvCotizacion.actualizaCotizacion(datosCotizacion);

			// INGRESO RAMO
			datosRamoCotizacion.setFc_ini_vig_date(fcDesde);
			datosRamoCotizacion.setFc_fin_vig_date(fcHasta);
			datosRamoCotizacion.setCd_subagente(Integer.valueOf(codCanal));
			datosRamoCotizacion.setCd_ramo(Integer.valueOf(codRamo));
			datosRamoCotizacion.setCd_ejecutivo(Integer.valueOf(codEjecutivo));
			datosRamoCotizacion.setCd_grupo_contratante(Integer.valueOf(codGrupoEconomico));
			datosRamoCotizacion.setCd_plan(Integer.valueOf(codPlan));
			srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);

			// Clausulas coberturas y deducibles
			srvProcedures.elimina_cob_clau_ded_sp(String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
					String.valueOf(datosRamoCotizacion.getCd_compania()));

			srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
					String.valueOf(datosCotizacion.getCd_compania()));

			// elimino forma de pago actual
			FormaPago formaPagoAux = new FormaPago();
			try {
				formaPagoAux = srvFormaPago.recuperaFormaPagoxCdCot(datosCotizacion.getCd_cotizacion(),
						datosCotizacion.getCd_compania());
				srvFormaPago.eliminaFormaPago(formaPagoAux);
			} catch (Exception e) {
				formaPagoAux = new FormaPago();
			}
			System.err.println("Elimino forma de pago");

			// Inserta Forma Pago
			Integer res;
			try {
				formaPagoAux = new FormaPago();
				formaPagoAux.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
				formaPagoAux.setCd_compania(liCompania);
				formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
				formaPagoAux.setSuperBanco_forma_Pago(frmPagoSuperBancos);
				formaPagoAux.setDerecho_Emision_formaPago(frmPagoDerechoEmision);
				formaPagoAux.setSeguroCampesion_forma_Pago(frmPagoSegCampesino);
				formaPagoAux.setIva_Forma_Pago(frmPagoIva);
				formaPagoAux.setTotal_Pago_FormaPago(frmPagoTotal);
				formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
				formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
				formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
				formaPagoAux.setObservaciones(frmObservaciones);

				res = srvFormaPago.insertaFormaPago(formaPagoAux);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
					return;
				}
			} catch (Exception e) {
				System.err.println("No se actualiza la forma de pago");
			}

			// Actualizo Ubicacion
			Integer fcDesdeJul, FcHastaJul, llDiasVigenciaNuevo;
			String sdFcDesde, asFcHasta;

			sdFcDesde = formato.format(fcDesde);
			fcDesdeJul = srvProcedures.fechaJuliana(sdFcDesde);
			asFcHasta = formato.format(fcHasta);
			FcHastaJul = srvProcedures.fechaJuliana(asFcHasta);
			llDiasVigenciaNuevo = srvProcedures.diasVigencias(fcDesdeJul, FcHastaJul);

			// guarda Ubicacion
			datosUbicacion.setFc_ini_ubc_date(fcDesde);
			datosUbicacion.setFc_fin_ubc_date(fcHasta);
			datosUbicacion.setFc_ini_ubicacion(fcDesdeJul);
			datosUbicacion.setFc_fin_ubicacion(FcHastaJul);
			datosUbicacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosUbicacion.setDsc_ubicacion("COMERCIAL");
			try {
				srvUbicacion.actualizaUbicacion(datosUbicacion);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicación Comuníquese con el Administrador del Sistema"));
				return;
			}
			System.err.println("Actualizo Ubicacion");
			// Guarda ObjetoCotizacion
			Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;
			String descObjeto;
			int objaux;

			try {
				primaTotaObj = datosObjetoCotizacion.getPrima_objeto();
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
					datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setPrima_objeto(0.00);
			}

			datosObjetoCotizacion.setFc_ini_obj_cot_date(fcDesde);
			datosObjetoCotizacion.setFc_fin_obj_cot_date(fcHasta);
			datosObjetoCotizacion.setFc_ini_obj_cot(fcDesdeJul);
			datosObjetoCotizacion.setFc_fin_obj_cot(FcHastaJul);
			datosObjetoCotizacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorObjeto));
			try {
				descObjeto = datosObjetoCotizacion.getDescripcion_objeto();
				if (descObjeto == null) {
					datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
				} else {
					datosObjetoCotizacion.setDescripcion_objeto(descObjeto.trim().toUpperCase());
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
			}
			try {
				totalAseguradoObj = datosObjetoCotizacion.getValor_asegurador_objeto();
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
				datosObjetoCotizacion.setValor_asegurador_objeto(0.00);
			}
			try {
				totalAseguradoObj = totalAseguradoObj + valorAseguradorExtra;
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
			}
			datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
			try {
				srvObjetoCotizacion.actualizaObjetoCotizacion(datosObjetoCotizacion);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
				return;
			}
			System.err.println("Actualizo Ocjeto Cot");
			// GUARDA DATOS DE LOS EXTRAS EN CASO DE EXISTIR
			if (valorAseguradorExtra > 0.0) {
				// elimino Objeto asegurador
				SubObjetoCotizacion subObj = new SubObjetoCotizacion();
				try {
					subObj = srvSubObjetoCotizacion.recuperaSubObjCotPros(datosObjetoCotizacion.getCd_obj_cotizacion(),
							datosObjetoCotizacion.getCd_compania());
					srvSubObjetoCotizacion.eliminaSubObjetoCotizacion(subObj);
				} catch (Exception e) {
					subObj = new SubObjetoCotizacion();
				}

				System.err.println("Elimino Subobjeto");
				subObj = new SubObjetoCotizacion();
				subObj.setCd_compania(datosObjetoCotizacion.getCd_compania());
				subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
				subObj.setFc_ini_subobj_cot_date(fcDesde);
				subObj.setFc_fin_subobj_cot_date(fcHasta);
				subObj.setFc_ini_subobj_cot(fcDesdeJul);
				subObj.setFc_fin_subobj_cot(FcHastaJul);
				subObj.setDias_vigencia(llDiasVigenciaNuevo);
				subObj.setValor_asegurador_subobjeto(valorAseguradorExtra);
				subObj.setFactor_subobjeto(Double.valueOf(factorObjeto));
				subObj.setTasa_subobjeto(0.00);
				subObj.setPrima_subobjeto(0.00);
				try {
					srvSubObjetoCotizacion.actualizaSubObjetoCotizacion(subObj);
				} catch (Exception e) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
					return;
				}
			}
			System.err.println("Actualizo Subobjeto");
			// GUARDO LAS CARACTERISTICAS DEL VEHICULO
			try {
				srvCaracteristicasVehiculos.actualizaCaracteristicaVehiculos(datosCaracteristicasVehiculos);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar las caracteristicas del Objeto Comunicación con el Administrador del Sistema"));
				return;
			}
			System.err.println("Actualizo Caracteristicas");
			// actualizo la Ubicación actual
			try {
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
				}
			} catch (Exception e) {
				primaTotaObj = 0.00;
			}
			datosUbicacion.setValor_asegurado_ubicacion(totalAseguradoObj);
			datosUbicacion.setValor_prima_ubicacion(primaTotaObj);
			srvUbicacion.actualizaUbicacion(datosUbicacion);

			// actualizo Ramo
			datosRamoCotizacion.setTotal_asegurado(totalAseguradoObj);
			datosRamoCotizacion.setTotal_prima(primaTotaObj);
			srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
			System.out.println("GUARDO COTIZACION PROSPECCION");

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Actualización Exitosa ",
					"Se guardó la prospección No." + datosCotizacion.getNum_cotizacion()));
			context.addMessage(null, new FacesMessage("Advertencia ",
					"Esta actualización no se ejecuta al sistema Upp, Edite las fechas manualmente"));

		}
		// se env�a el correo al usuario seleccionado
		String nmAsegu, nmRam, nmejec;

		try {
			nmClie = cliente.getPrimer_apellido_cliente();
		} catch (Exception e) {
			nmClie = "";
		}

		try {
			nmClie = nmClie.concat(" ");
			nmClie = nmClie.concat(cliente.getPrimer_nombre_cliente());
		} catch (Exception e) {

		}
		nmAsegu = srvAseguradoras.aseguradorasId(codAseguradora).getNombre_corto_aseguradora();
		nmRam = srvRamo.ramoId(codRamo).getDesc_ramo();
		Ejecutivos nmejec1 = srvEjecutivos.ejecutivoId(codEjecutivo);
		nmejec = nmejec1.getPrimer_apellido_ejecutivo();
		nmejec = nmejec.concat(" ");
		nmejec = nmejec.concat(nmejec1.getPrimer_nombre_ejecutivo());

		Usuarios usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(selectedUsuarios);

		// encera data para volver a ingresar
		datosRamoCotizacion = new RamoCotizacion();
		datosCotizacion = new Cotizacion();
		cliente = new Clientes();
		direccion = new Direccion();
		contacto = new Contacto();

		consCed = "";
		codAseguradora = "0";
		codRamo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		codCanal = "0";
		codEjecutivo = "0";
		asunto = "";
		observaciones = "";
		lstArchivos = new ArrayList<Archivos>();

		lbbtn = false;

		dirClie = "";
		refClie = "";
		numPa = "";
		numOfi = "";
		extClie = "";
		numCelClie = "";
		correoClie = "";

		valorAseguradorExtra = 0.00;
		datosCotizacion = new Cotizacion();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosUbicacion = new Ubicacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		lstProspeccion = new ArrayList<ProspeccionesView>();
		frmPagoPrimaTot = 0.00;
		frmPagoDerechoEmision = 0.00;
		frmPagoSuperBancos = 0.00;
		frmPagoSegCampesino = 0.00;
		frmPagoIva = 0.00;
		frmPagoTotal = 0.00;
		frmPagoCuotaIni = 0.00;
		frmPagoNumPago = 0;
		frmObservaciones = "";
		srvStorePRocedure.actualizaMarcaModelo();
		lstProspeccion = new ArrayList<ProspeccionesView>();
	}

	public void copyProspeccion() {
		// verifico que no exista
		String flgExisteInteger;
		try {
			flgExisteInteger = selectedProspeccion.getNum_cotizacion();
		} catch (Exception e) {
			flgExisteInteger = "SN";
			return;
		}

		lstProspeccion = new ArrayList<ProspeccionesView>();
		lstProspeccion = srvProspecciones.PospeccionesViewNumCot(flgExisteInteger);
		System.out.println("tamaño:" + lstProspeccion.size());
		if (lstProspeccion.size() > 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Opción válida unicamente para dos prospecciones"));
			return;
		}
		for (ProspeccionesView aux : lstProspeccion) {
			System.out.println("aux.getCd_cotizacion():" + aux.getCd_cotizacion());
			System.out.println("aux.getCd_compania():" + aux.getCd_compania());
			srvStorePRocedure.copiaProspeccion(String.valueOf(aux.getCd_cotizacion()),
					String.valueOf(aux.getCd_compania()));
		}

		lstProspeccion = new ArrayList<ProspeccionesView>();
		lstProspeccion = srvProspecciones.PospeccionesViewNumCot(flgExisteInteger);
		numCotGen = flgExisteInteger;

		// encero datos

		cliente = new Clientes();
		direccion = new Direccion();
		contacto = new Contacto();

		consCed = "";
		codAseguradora = "0";
		codRamo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		codCanal = "0";
		codEjecutivo = "0";
		asunto = "";
		observaciones = "";
		lstArchivos = new ArrayList<Archivos>();
		lstProspeccion = new ArrayList<ProspeccionesView>();
		lbbtn = false;
		dirClie = "";
		refClie = "";
		numPa = "";
		numOfi = "";
		extClie = "";
		numCelClie = "";
		correoClie = "";

		// encera data para volver a ingresar
		datosRamoCotizacion = new RamoCotizacion();
		datosCotizacion = new Cotizacion();
		cliente = new Clientes();
		direccion = new Direccion();
		contacto = new Contacto();

		consCed = "";
		codAseguradora = "0";
		codRamo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		codCanal = "0";
		codEjecutivo = "0";
		asunto = "";
		observaciones = "";
		lstArchivos = new ArrayList<Archivos>();

		lbbtn = false;

		dirClie = "";
		refClie = "";
		numPa = "";
		numOfi = "";
		extClie = "";
		numCelClie = "";
		correoClie = "";

		valorAseguradorExtra = 0.00;
		datosCotizacion = new Cotizacion();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosUbicacion = new Ubicacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		frmPagoPrimaTot = 0.00;
		frmPagoDerechoEmision = 0.00;
		frmPagoSuperBancos = 0.00;
		frmPagoSegCampesino = 0.00;
		frmPagoIva = 0.00;
		frmPagoTotal = 0.00;
		frmPagoCuotaIni = 0.00;
		frmObservaciones = "";
		frmPagoNumPago = 0;

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Copia Exitosa", "No.Prospección:" + flgExisteInteger));

	}

	public void guardaCotizacion() {
		// valido el ingreso de todos los datos
		try {
			if (consCed.isEmpty() || consCed == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
			return;
		}
		try {
			if (codAseguradora.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion la Aseguradora"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion la Aseguradora"));
			return;
		}
		try {
			if (codRamo.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ramo"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ramo"));
			return;
		}

		try {
			if (codPlan.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Plan"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Plan"));
			return;
		}
		try {
			if (codCanal.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Canal"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Canal"));
			return;
		}
		try {
			if (codEjecutivo.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ejecutivo"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccion el Ejecutivo"));
			return;
		}
		try {
			if (asunto.isEmpty() || asunto == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Asunto"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Asunto"));
			System.out.println("Error al ingresar el cliente");
			return;
		}
		try {
			if (selectedUsuarios.equals("0")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Destinatario de Correo"));
				System.out.println("Ingrese el Usuario");
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el usuario"));
			System.out.println("Error al ingresar el usuario");
			return;
		}

		// verifico datos cliente
		try {
			if (direccion == null) {
				direccion = new Direccion();
			}
		} catch (Exception e) {
			direccion = new Direccion();
		}
		try {
			if (telefono == null) {
				telefono = new Telefono();
			}
		} catch (Exception e) {
			telefono = new Telefono();
		}
		try {
			if (contacto == null) {
				contacto = new Contacto();
			}
		} catch (Exception e) {
			contacto = new Contacto();
		}

		System.out.println("PASA VALIDACION");

		try {
			direccion.setDireccion(dirClie);
		} catch (Exception e) {
			dirClie = "";
		}
		try {
			direccion.setReferencia(refClie);
		} catch (Exception e) {
			refClie = "";
		}
		try {
			telefono.setTelefono(numPa);
		} catch (Exception e) {
			numPa = "";
		}

		try {
			telefono.setTelefono_oficina(numOfi);
		} catch (Exception e) {
			numOfi = "";
		}
		try {
			telefono.setExt(extClie);
		} catch (Exception e) {
			extClie = "";
		}
		try {
			telefono.setTelefono_celular(numCelClie);
		} catch (Exception e) {
			numCelClie = "";
		}
		try {
			telefono.setCorreo(correoClie);
		} catch (Exception e) {
			correoClie = "";
		}
		try {
			contacto.setNombre_contacto(nmContac);
		} catch (Exception e) {
			nmContac = "";
		}
		try {
			contacto.setTelefono_contacto(numTelCon);
		} catch (Exception e) {
			numTelCon = "";
		}
		try {
			contacto.setCelular_contacto(numCel);
		} catch (Exception e) {
			numCel = "";
		}
		try {
			contacto.setMail_contacto(correo);
		} catch (Exception e) {
			correo = "";
		}
		try {
			contacto.setCargo_contacto(cargoCont);
		} catch (Exception e) {
			cargoCont = "";
		}
		try {
			contacto.setDepartamento_contacto(depaCpmta);
		} catch (Exception e) {
			depaCpmta = "";
		}
		try {
			contacto.setDireccion_contacto(direccionConta);
		} catch (Exception e) {
			direccionConta = "";
		}

		try {
			direccion.setCd_ciudad(Integer.valueOf(codciudad));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo ciudad");
		}
		try {
			direccion.setCd_rubro(Integer.valueOf(codSector));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo sector");
		}
		try {
			direccion.setCd_provincia(codProvincia);
		} catch (Exception e) {
			System.out.println("Error actualizar codigo provincia");
		}
		try {
			telefono.setCd_ciudad(Integer.valueOf(codCiudadTelf));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo telefono ciudad");
		}

		System.out.println("EXISTE CLIENTE:" + existeClie);
		if (existeClie == true) {
			// actualizo datos cliente
			srvClientes.actualizaClientes(cliente);
			srvDireccion.actualizaDireccion(direccion);
			srvTelefono.actualizaTelefono(telefono);
			srvContacto.actualizaContacto(contacto);
		} else {
			cliente.setTipo_persona_cliente("NAT");
			cliente.setTipo_identificacion_cliente(tpPresona);
			cliente.setIdentificacion_cliente(consCed.trim());
			try {
				srvClientes.insertarClientes(cliente);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error al Ingresar el Cliente",
						"Comuníquese con el Administrador del sistema"));
				System.out.println("Error al ingresar el cliente");
				return;
			}
			cliente = new Clientes();
			consCed = consCed.trim();
			cliente = srvClientes.listaClientesXIdentClie(consCed);
			direccion.setCd_aseguradora(0);
			direccion.setCd_cliente(cliente.getCd_cliente());
			telefono.setCd_aseguradora(0);
			telefono.setCd_cliente(cliente.getCd_cliente());
			contacto.setCd_aseguradora(0);
			contacto.setCd_cliente(cliente.getCd_cliente());

			try {
				srvDireccion.insertarDireccion(direccion);
			} catch (Exception e) {
				System.out.println("Error al ingresar el direccion");
			}
			try {
				srvTelefono.insertarTelefonos(telefono);
			} catch (Exception e) {
				System.out.println("Error al ingresar el telefono");
			}
			try {
				srvContacto.insertarContacto(contacto);
			} catch (Exception e) {
				System.out.println("Error al ingresar el contacto");
			}
		}
		// inserto la cotizaci�n
		Integer liCompania;
		String lsCompania;
		try {
			lsCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
					.toString();
		} catch (Exception e) {
			lsCompania = "1";
		}

		String numCotizacion;
		try {
			liCompania = Integer.parseInt(lsCompania);

		} catch (Exception e) {
			liCompania = 1;
		}
		Boolean flgExisteBoolean = false;
		try {
			if (datosCotizacion.getCd_cotizacion() == null) {
				flgExisteBoolean = true;
			}
		} catch (Exception e) {
			flgExisteBoolean = true;
		}
		System.err.println("Datos del Cliente Almacenado-flgExisteBoolean-:" + flgExisteBoolean);
		// cotizacioón no existente
		if (flgExisteBoolean) {
			System.err.println("Ingreso nueva cotizacion");
			datosCotizacion = new Cotizacion();
			datosCotizacion.setCd_aseguradora(Integer.valueOf(codAseguradora));
			datosCotizacion.setFc_ini_cot_date(fcDesde);
			datosCotizacion.setFc_fin_cot_date(fcHasta);
			datosCotizacion.setCd_rubro(8);
			datosCotizacion.setCd_compania(liCompania);
			datosCotizacion.setCd_cliente(cliente.getCd_cliente());
			datosCotizacion.setCd_cliente_asegurado(cliente.getCd_cliente());
			datosCotizacion.setFact_periodica_cot(0);
			datosCotizacion.setNum_renovaciones_cot(0);
			datosCotizacion.setObservaciones(observaciones);
			datosCotizacion.setUsrid(Integer.valueOf(selectedUsuarios));

			Integer cdCotMax = srvCotizacion.insertarCotizacion(datosCotizacion);
			if (cdCotMax == 1) {
				cdCotMax = srvCotizacion.codigoMaxCotizacion();
			}
			datosCotizacion = new Cotizacion();
			System.out.println("INGRESOOOOO  guardaRamoCotizacion");

			datosCotizacion = srvCotizacion.recuperaCotizacionPorCodigo(cdCotMax, liCompania);
			numCotizacion = datosCotizacion.getNum_cotizacion();

			// INGRESO RAMO
			datosRamoCotizacion = new RamoCotizacion();

			datosRamoCotizacion.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
			datosRamoCotizacion.setCd_compania(liCompania);
			datosRamoCotizacion.setFc_ini_vig_date(fcDesde);
			datosRamoCotizacion.setFc_fin_vig_date(fcHasta);
			datosRamoCotizacion.setCd_subagente(Integer.valueOf(codCanal));
			datosRamoCotizacion.setCd_ramo(Integer.valueOf(codRamo));
			datosRamoCotizacion.setCd_ejecutivo(Integer.valueOf(codEjecutivo));
			datosRamoCotizacion.setCd_grupo_contratante(Integer.valueOf(codGrupoEconomico));
			datosRamoCotizacion.setCd_plan(Integer.valueOf(codPlan));
			Integer resInsert = srvRamoCotizacion.insertarRamoCotizacion(datosRamoCotizacion);
			if (resInsert == 1) {
				srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
						String.valueOf(datosCotizacion.getCd_compania()));
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Registro Exitoso", "Se guardó la prospección No." + numCotizacion));
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Error al generar el proceso"));
				return;
			}
			datosRamoCotizacion = new RamoCotizacion();
			datosRamoCotizacion = srvRamoCotizacion.recuperaRamoCotizacionAnexo(datosCotizacion.getCd_cotizacion(),
					liCompania);

			// vigencia
			Integer fcDesdeJul, FcHastaJul, llDiasVigenciaNuevo;
			String sdFcDesde, asFcHasta;

			sdFcDesde = formato.format(fcDesde);
			fcDesdeJul = srvProcedures.fechaJuliana(sdFcDesde);
			asFcHasta = formato.format(fcHasta);
			FcHastaJul = srvProcedures.fechaJuliana(asFcHasta);
			llDiasVigenciaNuevo = srvProcedures.diasVigencias(fcDesdeJul, FcHastaJul);

			// guarda Ubicacion
			datosUbicacion = new Ubicacion();
			datosUbicacion.setCd_compania(liCompania);
			datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
			datosUbicacion.setFc_ini_ubc_date(fcDesde);
			datosUbicacion.setFc_fin_ubc_date(fcHasta);
			datosUbicacion.setFc_ini_ubicacion(fcDesdeJul);
			datosUbicacion.setFc_fin_ubicacion(FcHastaJul);
			datosUbicacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosUbicacion.setDsc_ubicacion("COMERCIAL");
			Integer res = srvUbicacion.insertarUbicacion(datosUbicacion);
			if (res != 1) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicación Comuníquese con el Administrador del Sistema"));
				return;
			}
			res = srvUbicacion.codigoMaxUbc(datosRamoCotizacion.getCd_ramo_cotizacion());
			datosUbicacion = new Ubicacion();
			datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(res, liCompania);

			// Guarda ObjetoCotizacion
			Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;
			String descObjeto;
			int objaux;

			datosObjetoCotizacion.setCd_compania(liCompania);
			datosObjetoCotizacion.setFc_ini_obj_cot_date(fcDesde);
			datosObjetoCotizacion.setFc_fin_obj_cot_date(fcHasta);
			datosObjetoCotizacion.setFc_ini_obj_cot(fcDesdeJul);
			datosObjetoCotizacion.setFc_fin_obj_cot(FcHastaJul);
			datosObjetoCotizacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
			datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorObjeto));

			try {
				descObjeto = datosObjetoCotizacion.getDescripcion_objeto();
				if (descObjeto == null) {
					datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
				} else {
					datosObjetoCotizacion.setDescripcion_objeto(descObjeto.trim().toUpperCase());
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
			}
			try {
				primaTotaObj = datosObjetoCotizacion.getPrima_objeto();
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
					datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setPrima_objeto(0.00);
			}
			try {
				totalAseguradoObj = datosObjetoCotizacion.getValor_asegurador_objeto();
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
				datosObjetoCotizacion.setValor_asegurador_objeto(0.00);
			}
			try {
				totalAseguradoObj = totalAseguradoObj + valorAseguradorExtra;
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
			}

			System.out.println("TOTAL ASEGURADO:" + totalAseguradoObj);
			datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
			res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
			if (res == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
				return;
			}
			res = srvObjetoCotizacion.codigoMaxObjetoCot(datosUbicacion.getCd_ubicacion());
			objaux = res;
			datosObjetoCotizacion = new ObjetoCotizacion();
			datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(res, liCompania);

			// GUARDA DATOS DE LOS EXTRAS EN CASO DE EXISTIR
			if (valorAseguradorExtra > 0.0) {
				SubObjetoCotizacion subObj = new SubObjetoCotizacion();
				subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
				subObj.setCd_compania(liCompania);
				subObj.setDescripcion_subobjeto("EXTRA - COMERCIAL");
				subObj.setFc_ini_subobj_cot_date(fcDesde);
				subObj.setFc_fin_subobj_cot_date(fcHasta);
				subObj.setFc_ini_subobj_cot(fcDesdeJul);
				subObj.setFc_fin_subobj_cot(FcHastaJul);
				subObj.setDias_vigencia(llDiasVigenciaNuevo);
				subObj.setValor_asegurador_subobjeto(valorAseguradorExtra);
				subObj.setFactor_subobjeto(Double.valueOf(factorObjeto));
				subObj.setTasa_subobjeto(0.00);
				subObj.setPrima_subobjeto(0.00);
				res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
					return;
				}
			}

			// GUARDO LAS CARACTERISTICAS DEL VEHICULO
			try {
				datosCaracteristicasVehiculos.setCd_compania(liCompania);
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

			// actualizo la Ubicación actual
			try {
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
				}
			} catch (Exception e) {
				primaTotaObj = 0.00;
			}

			// actualizo la Ubicación actual
			datosUbicacion.setValor_asegurado_ubicacion(totalAseguradoObj);
			datosUbicacion.setValor_prima_ubicacion(primaTotaObj);
			srvUbicacion.actualizaUbicacion(datosUbicacion);

			// actualizo Ramo
			datosRamoCotizacion.setTotal_asegurado(totalAseguradoObj);
			datosRamoCotizacion.setTotal_prima(primaTotaObj);
			srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
			System.out.println("GUARDO COTIZACION PROSPECCION");

			// Inserta Forma Pago
			try {
				FormaPago formaPagoAux = new FormaPago();
				formaPagoAux.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
				formaPagoAux.setCd_compania(liCompania);
				formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
				formaPagoAux.setSuperBanco_forma_Pago(frmPagoSuperBancos);
				formaPagoAux.setDerecho_Emision_formaPago(frmPagoDerechoEmision);
				formaPagoAux.setSeguroCampesion_forma_Pago(frmPagoSegCampesino);
				formaPagoAux.setIva_Forma_Pago(frmPagoIva);
				formaPagoAux.setTotal_Pago_FormaPago(frmPagoTotal);
				formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
				formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
				formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
				formaPagoAux.setObservaciones(frmObservaciones);

				res = srvFormaPago.insertaFormaPago(formaPagoAux);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
					return;
				}
			} catch (Exception e) {
				System.err.println("No se actualiza la forma de pago");
			}

			// guarda los archivos adjuntos
			for (Archivos archivo : lstArchivos) {
				archivo.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
				archivo.setCd_cliente(String.valueOf(cliente.getCd_cliente()));
				srvArchivos.insertaArchivos(archivo);
			}
		} else {
			String numeroCotactString, numFinal = "C";
			numeroCotactString = datosCotizacion.getNum_cotizacion().trim();
			System.out.println("Inicio numeroCotactString:" + numeroCotactString);

			for (int i = 0; i < numeroCotactString.length(); i++) {
				if (i != 0) {
					numFinal = numFinal.concat(String.valueOf(numeroCotactString.charAt(i)));
				}

			}

			System.out.println("Fin numFinal:" + numFinal);
			// actualiza cotizaciòn ya ingresada
			datosCotizacion.setNum_cotizacion(numFinal);
			datosCotizacion.setCd_aseguradora(Integer.valueOf(codAseguradora));
			datosCotizacion.setCd_rubro(8); // PROSPECCION
			datosCotizacion.setCd_compania(liCompania);
			datosCotizacion.setCd_cliente(cliente.getCd_cliente());
			datosCotizacion.setCd_cliente_asegurado(cliente.getCd_cliente());
			datosCotizacion.setFact_periodica_cot(0);
			datosCotizacion.setNum_renovaciones_cot(0);
			datosCotizacion.setObservaciones(observaciones);
			srvCotizacion.actualizaCotizacion(datosCotizacion);

			// INGRESO RAMO
			datosRamoCotizacion.setFc_ini_vig_date(fcDesde);
			datosRamoCotizacion.setFc_fin_vig_date(fcHasta);
			datosRamoCotizacion.setCd_subagente(Integer.valueOf(codCanal));
			datosRamoCotizacion.setCd_ramo(Integer.valueOf(codRamo));
			datosRamoCotizacion.setCd_ejecutivo(Integer.valueOf(codEjecutivo));
			datosRamoCotizacion.setCd_grupo_contratante(Integer.valueOf(codGrupoEconomico));
			datosRamoCotizacion.setCd_plan(Integer.valueOf(codPlan));
			srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);

			// Clausulas coberturas y deducibles
			srvProcedures.elimina_cob_clau_ded_sp(String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
					String.valueOf(datosRamoCotizacion.getCd_compania()));

			srvProcedures.generaCobeDedClauPolizas(String.valueOf(datosCotizacion.getCd_cotizacion()),
					String.valueOf(datosCotizacion.getCd_compania()));
			
			
			// elimino forma de pago actual
			FormaPago formaPagoAux = new FormaPago();
			try {
				formaPagoAux = srvFormaPago.recuperaFormaPagoxCdCot(datosCotizacion.getCd_cotizacion(),
						datosCotizacion.getCd_compania());
				srvFormaPago.eliminaFormaPago(formaPagoAux);
			} catch (Exception e) {
				formaPagoAux = new FormaPago();
			}
			System.err.println("Elimino forma de pago");

			// Inserta Forma Pago
			Integer res;
			try {
				formaPagoAux = new FormaPago();
				formaPagoAux.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
				formaPagoAux.setCd_compania(liCompania);
				formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
				formaPagoAux.setSuperBanco_forma_Pago(frmPagoSuperBancos);
				formaPagoAux.setDerecho_Emision_formaPago(frmPagoDerechoEmision);
				formaPagoAux.setSeguroCampesion_forma_Pago(frmPagoSegCampesino);
				formaPagoAux.setIva_Forma_Pago(frmPagoIva);
				formaPagoAux.setTotal_Pago_FormaPago(frmPagoTotal);
				formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
				formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
				formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
				formaPagoAux.setObservaciones(frmObservaciones);

				res = srvFormaPago.insertaFormaPago(formaPagoAux);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
					return;
				}
			} catch (Exception e) {
				System.err.println("No se actualiza la forma de pago");
			}

			// Actualizo Ubicacion
			Integer fcDesdeJul, FcHastaJul, llDiasVigenciaNuevo;
			String sdFcDesde, asFcHasta;

			sdFcDesde = formato.format(fcDesde);
			fcDesdeJul = srvProcedures.fechaJuliana(sdFcDesde);
			asFcHasta = formato.format(fcHasta);
			FcHastaJul = srvProcedures.fechaJuliana(asFcHasta);
			llDiasVigenciaNuevo = srvProcedures.diasVigencias(fcDesdeJul, FcHastaJul);

			// guarda Ubicacion
			datosUbicacion.setFc_ini_ubc_date(fcDesde);
			datosUbicacion.setFc_fin_ubc_date(fcHasta);
			datosUbicacion.setFc_ini_ubicacion(fcDesdeJul);
			datosUbicacion.setFc_fin_ubicacion(FcHastaJul);
			datosUbicacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosUbicacion.setDsc_ubicacion("COMERCIAL");
			try {
				srvUbicacion.actualizaUbicacion(datosUbicacion);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicación Comuníquese con el Administrador del Sistema"));
				return;
			}
			System.err.println("Actualizo Ubicacion");
			// Guarda ObjetoCotizacion
			Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;
			String descObjeto;
			int objaux;

			try {
				primaTotaObj = datosObjetoCotizacion.getPrima_objeto();
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
					datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setPrima_objeto(0.00);
			}

			datosObjetoCotizacion.setFc_ini_obj_cot_date(fcDesde);
			datosObjetoCotizacion.setFc_fin_obj_cot_date(fcHasta);
			datosObjetoCotizacion.setFc_ini_obj_cot(fcDesdeJul);
			datosObjetoCotizacion.setFc_fin_obj_cot(FcHastaJul);
			datosObjetoCotizacion.setDias_vigencia(llDiasVigenciaNuevo);
			datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorObjeto));
			try {
				descObjeto = datosObjetoCotizacion.getDescripcion_objeto();
				if (descObjeto == null) {
					datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
				} else {
					datosObjetoCotizacion.setDescripcion_objeto(descObjeto.trim().toUpperCase());
				}
			} catch (Exception e) {
				datosObjetoCotizacion.setDescripcion_objeto("COMERCIAL");
			}
			try {
				totalAseguradoObj = datosObjetoCotizacion.getValor_asegurador_objeto();
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
				datosObjetoCotizacion.setValor_asegurador_objeto(0.00);
			}
			try {
				totalAseguradoObj = totalAseguradoObj + valorAseguradorExtra;
			} catch (Exception e) {
				totalAseguradoObj = 0.00;
			}
			datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
			try {
				srvObjetoCotizacion.actualizaObjetoCotizacion(datosObjetoCotizacion);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
				return;
			}
			System.err.println("Actualizo Ocjeto Cot");
			// GUARDA DATOS DE LOS EXTRAS EN CASO DE EXISTIR
			if (valorAseguradorExtra > 0.0) {
				// elimino Objeto asegurador
				SubObjetoCotizacion subObj = new SubObjetoCotizacion();
				try {
					subObj = srvSubObjetoCotizacion.recuperaSubObjCotPros(datosObjetoCotizacion.getCd_obj_cotizacion(),
							datosObjetoCotizacion.getCd_compania());
					srvSubObjetoCotizacion.eliminaSubObjetoCotizacion(subObj);
				} catch (Exception e) {
					subObj = new SubObjetoCotizacion();
				}

				System.err.println("Elimino Subobjeto");
				subObj = new SubObjetoCotizacion();
				subObj.setCd_compania(datosObjetoCotizacion.getCd_compania());
				subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
				subObj.setFc_ini_subobj_cot_date(fcDesde);
				subObj.setFc_fin_subobj_cot_date(fcHasta);
				subObj.setFc_ini_subobj_cot(fcDesdeJul);
				subObj.setFc_fin_subobj_cot(FcHastaJul);
				subObj.setDias_vigencia(llDiasVigenciaNuevo);
				subObj.setValor_asegurador_subobjeto(valorAseguradorExtra);
				subObj.setFactor_subobjeto(Double.valueOf(factorObjeto));
				subObj.setTasa_subobjeto(0.00);
				subObj.setPrima_subobjeto(0.00);
				try {
					srvSubObjetoCotizacion.actualizaSubObjetoCotizacion(subObj);
				} catch (Exception e) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al ingresar el Objeto Comunicación con el Administrador del Sistema"));
					return;
				}
			}
			System.err.println("Actualizo Subobjeto");
			// GUARDO LAS CARACTERISTICAS DEL VEHICULO
			try {
				srvCaracteristicasVehiculos.actualizaCaracteristicaVehiculos(datosCaracteristicasVehiculos);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar las caracteristicas del Objeto Comunicación con el Administrador del Sistema"));
				return;
			}
			System.err.println("Actualizo Caracteristicas");
			// actualizo la Ubicación actual
			try {
				if (primaTotaObj == null) {
					primaTotaObj = 0.00;
				}
			} catch (Exception e) {
				primaTotaObj = 0.00;
			}
			datosUbicacion.setValor_asegurado_ubicacion(totalAseguradoObj);
			datosUbicacion.setValor_prima_ubicacion(primaTotaObj);
			srvUbicacion.actualizaUbicacion(datosUbicacion);

			// actualizo Ramo
			datosRamoCotizacion.setTotal_asegurado(totalAseguradoObj);
			datosRamoCotizacion.setTotal_prima(primaTotaObj);
			srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
			System.out.println("GUARDO COTIZACION PROSPECCION");

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Actualización Exitosa",
					"Se guardó la prospección No." + datosCotizacion.getNum_cotizacion()));
			
			lstProspeccion = new ArrayList<ProspeccionesView>();

		}

		// se env�a el correo al usuario seleccionado
		String nmAsegu, nmRam, nmejec;
		numCotizacion = datosCotizacion.getNum_cotizacion();
		try {
			nmClie = cliente.getPrimer_apellido_cliente();
		} catch (Exception e) {
			nmClie = "";
		}

		try {
			nmClie = nmClie.concat(" ");
			nmClie = nmClie.concat(cliente.getPrimer_nombre_cliente());
		} catch (Exception e) {

		}
		nmAsegu = srvAseguradoras.aseguradorasId(codAseguradora).getNombre_corto_aseguradora();
		nmRam = srvRamo.ramoId(codRamo).getDesc_ramo();
		Ejecutivos nmejec1 = srvEjecutivos.ejecutivoId(codEjecutivo);
		nmejec = nmejec1.getPrimer_apellido_ejecutivo();
		nmejec = nmejec.concat(" ");
		nmejec = nmejec.concat(nmejec1.getPrimer_nombre_ejecutivo());

		Usuarios usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(selectedUsuarios);
		email.setReceptor(usr.getCorreo());
		email.setSubject("Sistema Confia - " + asunto + " - " + numCotizacion);
		email.setTexto("<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Estimad@<o:p></o:p></span></p>"
				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Se ha generado una nueva emisión pendiente en el sistema"
				+ " con las siguientes referencias:" + " <o:p></o:p></span></p>"

				+ "<table class=MsoNormalTable border=0 cellpadding=0 width=600 style='width:450.0pt; mso-cellspacing:1.5pt;mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'>"
				+ "<tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>"
				+ "<td colspan=2 style='background:#CFDFFF;padding:.75pt .75pt .75pt .75pt' >"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:Calibri;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Descripción</span></b><span "
				+ "style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;"
				+ "mso-fareast-font-family:Times New Roman;mso-hansi-font-family:Calibri;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;mso-bidi-theme-font:"
				+ "minor-latin'><o:p></o:p></span></p>" + "</td>" + "</tr>"

				+ "<tr style='mso-yfti-irow:1'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>No. Emisión Pendiente:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
				+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>" + numCotizacion + "<o:p></o:p></span></p>" + "</td>" + "</tr>"

				+ "<tr style='mso-yfti-irow:1'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Cédula del Cliente:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
				+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>" + cliente.getIdentificacion_cliente() + "<o:p></o:p></span></p>"
				+ "</td>" + "</tr>"

				+ "<tr style='mso-yfti-irow:2'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Datos del Cliente:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
				+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>" + nmClie + "<o:p></o:p></span></p>" + "</td>" + "</tr>"

				+ "<tr style='mso-yfti-irow:2'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Aseguradora:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
				+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>" + nmAsegu + "<o:p></o:p></span></p>" + "</td>" + "</tr>"

				+ "<tr style='mso-yfti-irow:3'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Ramo:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
				+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>" + nmRam + "<o:p></o:p></span></p>" + "</td>" + "</tr>"

				+ "<tr style='mso-yfti-irow:5'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Ejecutivo:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
				+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>" + nmejec + "<o:p></o:p></span></p>" + "</td>" + "</tr>"

				+ "<tr style='mso-yfti-irow:6'>"
				+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>Instrucción:</span></b><span style='mso-ascii-font-family:"
				+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
				+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
				+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
				+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
				+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
				+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
				+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
				+ "mso-bidi-theme-font:minor-latin'>" + observaciones + "<o:p></o:p></span></p>" + "</td>" + "</tr>"

				+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
				+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'> " + " <o:p></o:p></span></p>"
				+ "<p><strong>Nota: </strong>"
				+ "Este mensaje ha sido generado automáticamente, por favor no lo responda." + "</p> ");
		email.sendEmail();

		cliente = new Clientes();
		direccion = new Direccion();
		contacto = new Contacto();

		consCed = "";
		codAseguradora = "0";
		codRamo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		codCanal = "0";
		codEjecutivo = "0";
		asunto = "";
		observaciones = "";
		lstArchivos = new ArrayList<Archivos>();
		lstProspeccion = new ArrayList<ProspeccionesView>();
		lbbtn = false;
		dirClie = "";
		refClie = "";
		numPa = "";
		numOfi = "";
		extClie = "";
		numCelClie = "";
		correoClie = "";

		// encera data para volver a ingresar
		datosRamoCotizacion = new RamoCotizacion();
		datosCotizacion = new Cotizacion();
		cliente = new Clientes();
		direccion = new Direccion();
		contacto = new Contacto();

		consCed = "";
		codAseguradora = "0";
		codRamo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		codCanal = "0";
		codEjecutivo = "0";
		asunto = "";
		observaciones = "";
		lstArchivos = new ArrayList<Archivos>();

		lbbtn = false;

		dirClie = "";
		refClie = "";
		numPa = "";
		numOfi = "";
		extClie = "";
		numCelClie = "";
		correoClie = "";

		valorAseguradorExtra = 0.00;
		datosCotizacion = new Cotizacion();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosUbicacion = new Ubicacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		frmPagoPrimaTot = 0.00;
		frmPagoDerechoEmision = 0.00;
		frmPagoSuperBancos = 0.00;
		frmPagoSegCampesino = 0.00;
		frmPagoIva = 0.00;
		frmPagoTotal = 0.00;
		frmPagoCuotaIni = 0.00;
		frmPagoNumPago = 0;
		frmObservaciones = "";

		srvStorePRocedure.actualizaMarcaModelo();
	}

	public void oncapture(CaptureEvent captureEvent) {

		try {
			if (consCed.isEmpty() || consCed == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la identificación del cliente"));
			return;
		}

		System.out.println("EXISTE CLIENTE:" + existeClie);
		if (existeClie == true) {
			// actualizo datos cliente
			srvClientes.actualizaClientes(cliente);
		} else {
			cliente.setTipo_persona_cliente("NAT");
			cliente.setTipo_identificacion_cliente(tpPresona);
			cliente.setIdentificacion_cliente(consCed.trim());
			try {
				srvClientes.insertarClientes(cliente);
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error al Ingresar el Cliente",
						"Comuníquese con el Administrador del sistema"));
				System.out.println("Error al ingresar el cliente");
				return;
			}
			cliente = new Clientes();
			consCed = consCed.trim();
			cliente = srvClientes.listaClientesXIdentClie(consCed);
			existeClie = true;
		}

		filename = getRandomImageName();
		byte[] data = captureEvent.getData();

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String newFileName = "C:\\java\\wildfly-9.0.2.Final\\welcome-content\\documentos\\clientes/" + filename
				+ ".jpeg";
		System.out.println("FILENAME:" + newFileName);

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}

		filename = "<a href=\"http://35.237.152.6:8081/documentos/clientes/" + filename + ".jpeg\" target=\"_blank\">"
				+ filename + "</a><br/>";
		System.out.println("filename:" + filename);
		Archivos arc = new Archivos();
		arc.setModulo("EMISIONES PENDIENTES COMERCIAL");
		arc.setUbicacion(filename);
		lstArchivos.add(arc);
	}

	public void onRowDelete(Archivos objArch) {
		lstArchivos.remove(objArch);
	}

	private String getRandomImageName() {
		String apClie, segApClie;

		int i = (int) (Math.random() * 10000000);
		cliente.getIdentificacion_cliente();

		try {
			apClie = cliente.getPrimer_apellido_cliente();
		} catch (Exception e) {
			apClie = "S/D";
		}

		try {
			segApClie = cliente.getSegundo_apellido_cliente();
		} catch (Exception e) {
			segApClie = "S/D";
		}
		apClie = apClie.concat(" ");
		apClie = apClie.concat(segApClie);
		apClie = apClie.concat("-");
		apClie = apClie.concat(cliente.getIdentificacion_cliente());
		apClie = apClie.concat("-");
		apClie = apClie.concat(String.valueOf(i));
		return apClie;
	}

	private String getRandomImageNameFile(String ext, Clientes clieAux) {
		String apClie, segApClie;

		int i = (int) (Math.random() * 10000000);
		System.out.println("INGRESO RANDOMIMGNAMEFILE");
		try {
			clieAux.getIdentificacion_cliente();
			System.out.println("1:" + clieAux.getIdentificacion_cliente());
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			apClie = clieAux.getPrimer_apellido_cliente();
			System.out.println("2:" + apClie);
		} catch (Exception e) {
			apClie = "S/D";
		}

		try {
			segApClie = clieAux.getSegundo_apellido_cliente();
			System.out.println("3:" + apClie);
		} catch (Exception e) {
			segApClie = "S/D";
		}
		apClie = apClie.concat(" ");
		apClie = apClie.concat(segApClie);
		apClie = apClie.concat("-");
		apClie = apClie.concat(clieAux.getIdentificacion_cliente());
		apClie = apClie.concat("-");
		apClie = apClie.concat(String.valueOf(i));
		apClie = apClie.concat(ext);
		System.out.println("ARCHIVO NAME:" + apClie);
		return apClie;
	}

	public void buscaProspeccion() {
		System.out.println("ingresa a buscar prospeccion");
		lstProspeccion = new ArrayList<ProspeccionesView>();
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

		System.out.println("apellidoRazonSocial:" + apellidoRazonSocial);
		System.out.println("numCotGen:" + numCotGen);

		if (numCotGen.equals("%") && !apellidoRazonSocial.equals("%")) {
			System.out.println("Ingreso1");
			lstProspeccion = srvProspecciones.PospeccionesViewCot(apellidoRazonSocial.toUpperCase());
		} else if (!numCotGen.equals("%") && apellidoRazonSocial.equals("%")) {
			System.out.println("Ingreso2");
			lstProspeccion = srvProspecciones.PospeccionesViewNumMCot(numCotGen);
		} else {
			System.out.println("Ingreso3");
			lstProspeccion = srvProspecciones.PospeccionesViewNumMCot(numCotGen);
		}

	}

	public void seleccionaProspeccion() {

		try {
			if (selectedProspeccion == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Prospección"));

				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Prospección"));

			return;
		}
		// datos cliente
		cliente = new Clientes();
		cliente = srvClientes.listaClientesXId(selectedProspeccion.getCd_cliente());
		consCed = cliente.getIdentificacion_cliente().trim();
		buscarClientes();
		// datos de la cotizacion

		datosCotizacion = new Cotizacion();
		System.out.println("INGRESOOOOO  guardaRamoCotizacion");

		datosCotizacion = srvCotizacion.recuperaCotizacionPorCodigo(selectedProspeccion.getCd_cotizacion(),
				selectedProspeccion.getCd_compania());

		// INGRESO RAMO
		datosRamoCotizacion = new RamoCotizacion();
		datosRamoCotizacion = srvRamoCotizacion.obtieneIdRamoCotizacionXCotizacion(datosCotizacion.getCd_cotizacion());

		// INGRESO UBICACION
		datosUbicacion = new Ubicacion();
		datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
				datosRamoCotizacion.getCd_compania());

		// Ingreso OBJETO ASEGURADO
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorUbc(datosUbicacion.getCd_ubicacion(),
				datosUbicacion.getCd_compania());
		valorAseguradorExtra = datosObjetoCotizacion.getTotal_asegurado_objeto()
				- datosObjetoCotizacion.getValor_asegurador_objeto();
		// recupero caracteristicas Objeto asegurado
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		datosCaracteristicasVehiculos = srvCaracteristicasVehiculos
				.recuperaCaractVH(datosObjetoCotizacion.getCd_obj_cotizacion(), datosObjetoCotizacion.getCd_compania());

		// Ingreso Forma PAGO
		System.err.println("HASta Aqui Bien");
		FormaPago formaPagoAux = new FormaPago();
		try {
			formaPagoAux = srvFormaPago.recuperaFormaPagoxCdCot(datosCotizacion.getCd_cotizacion(),
					datosCotizacion.getCd_compania());
		} catch (Exception e) {
			formaPagoAux = new FormaPago();
		}

		System.err.println("HASta Aqui Bien");
		try {
			frmPagoPrimaTot = formaPagoAux.getTotal_prima_frmPago();
		} catch (Exception e) {
			frmPagoPrimaTot = 0.00;
		}
		try {
			frmPagoSuperBancos = formaPagoAux.getSuperBanco_forma_Pago();
		} catch (Exception e) {
			frmPagoSuperBancos = 0.00;
		}
		try {
			frmPagoDerechoEmision = formaPagoAux.getDerecho_Emision_formaPago();
		} catch (Exception e) {
			frmPagoDerechoEmision = 0.00;
		}
		try {
			frmPagoSegCampesino = formaPagoAux.getSeguroCampesion_forma_Pago();
		} catch (Exception e) {
			frmPagoSegCampesino = 0.00;
		}
		try {
			frmPagoIva = formaPagoAux.getIva_Forma_Pago();
		} catch (Exception e) {
			frmPagoIva = 0.00;
		}
		try {
			frmPagoTotal = formaPagoAux.getTotal_Pago_FormaPago();
		} catch (Exception e) {
			frmPagoTotal = 0.00;
		}
		try {
			tpFromaPago = formaPagoAux.getNum_alternativa_formaPago();
		} catch (Exception e) {
			tpFromaPago = "CONTADO";
		}
		try {
			frmPagoCuotaIni = formaPagoAux.getPct_cuota_Inicial_frmPago();
		} catch (Exception e) {
			frmPagoCuotaIni = 0.00;
		}
		try {
			frmPagoNumPago = formaPagoAux.getNum_pago_formaPago();
		} catch (Exception e) {
			frmPagoNumPago = 0;
		}
		try {
			frmObservaciones = formaPagoAux.getObservaciones();
		} catch (Exception e) {
			frmObservaciones = "";
		}
		System.err.println("HASta Aqui Bien");

		codAseguradora = String.valueOf(selectedProspeccion.getCd_aseguradora());
		listarRamosAseguradora();
		codRamo = String.valueOf(selectedProspeccion.getCd_ramo());
		listarPlanes();
		codPlan = String.valueOf(selectedProspeccion.getCd_plan());
		codCanal = String.valueOf(selectedProspeccion.getCd_subagente());
		codGrupoEconomico = String.valueOf(selectedProspeccion.getCd_grupo_contratante());
		codEjecutivo = String.valueOf(datosRamoCotizacion.getCd_ejecutivo());
		fcHasta = datosRamoCotizacion.getFc_fin_vig_date();
		fcDesde = datosRamoCotizacion.getFc_ini_vig_date();
		observaciones = datosCotizacion.getObservaciones();
		lbbtn = false;

		// recupera cobertruas clausulas deducibles
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();

		lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidas(selectedProspeccion.getCd_compania(),
				selectedProspeccion.getCd_ramo_cotizacion());
		lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(selectedProspeccion.getCd_compania(),
				selectedProspeccion.getCd_ramo_cotizacion());
		lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidas(selectedProspeccion.getCd_compania(),
				selectedProspeccion.getCd_ramo_cotizacion());

	}

	public void calculaPrimaObjeto() {
		System.out.println("INGRESO AL CALCULO");
		Double ldValAseg, ldValPrima, llFactor, ldTasa;
		Integer diasVigencia;
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

		diasVigencia = DiasVigencia(fcDesde, fcHasta);
		System.out.println("dias vigencia:" + diasVigencia);
		System.out.println("ldValAseg" + ldValAseg);
		System.out.println("ldTasa" + ldTasa);
		System.out.println("llFactor" + llFactor);
		ldValPrima = (ldValAseg * (ldTasa / llFactor)) / 365 * diasVigencia;
		try {
			frmPagoPrimaTot = ldValPrima + valorAseguradorExtra;
		} catch (Exception e) {
			frmPagoPrimaTot = 0.0;
		}

		System.out.println("frmPagoPrimaTot:" + frmPagoPrimaTot);
		System.out.println("ldValPrima;" + ldValPrima);
		datosObjetoCotizacion.setPrima_objeto(ldValPrima);
	}

	public Integer DiasVigencia(Date lFcDesde, Date lFcDhasta) {
		Integer fcDesdeJul, FcHastaJul, llDiasVigenciaNuevo;
		String sdFcDesde, asFcHasta;

		sdFcDesde = formato.format(lFcDesde);
		fcDesdeJul = srvProcedures.fechaJuliana(sdFcDesde);
		asFcHasta = formato.format(lFcDhasta);
		FcHastaJul = srvProcedures.fechaJuliana(asFcHasta);
		llDiasVigenciaNuevo = srvProcedures.diasVigencias(fcDesdeJul, FcHastaJul);
		System.out.println("Dias Vigencia Cambio:" + llDiasVigenciaNuevo);

		return llDiasVigenciaNuevo;

	}

	public void calculaPagoTotal() {

		Double Iva, frmPagoSubtotal;
		try {
			frmPagoPrimaTot = datosObjetoCotizacion.getPrima_objeto();
		} catch (Exception e) {
			frmPagoPrimaTot = 0.0;
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
		frmPagoSegCampesino = frmPagoPrimaTot * (0.5 / 100);
		frmPagoSegCampesino = redondear(frmPagoSegCampesino);
		frmPagoSuperBancos = frmPagoPrimaTot * (3.5 / 100);
		frmPagoSuperBancos = redondear(frmPagoSuperBancos);

		Iva = Double.valueOf(srvRubros.recuperaIva());
		frmPagoSubtotal = 0.0;
		frmPagoSubtotal = frmPagoPrimaTot + frmPagoSuperBancos + frmPagoDerechoEmision + frmPagoSegCampesino;
		frmPagoIva = frmPagoSubtotal * (Iva / 100);
		frmPagoIva = redondear(frmPagoIva);
		frmPagoTotal = frmPagoSubtotal + frmPagoIva;
		System.out.println("INGRESO:frmPagoSubtotal=" + frmPagoSubtotal + " frmPagoIva=" + frmPagoIva);

		frmPagoTotal = redondear(frmPagoTotal);

	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void listaModelos() {
		listadoModelos = srvModelos.listaModelo(datosCaracteristicasVehiculos.getCd_marca());
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
		try {
			CobAux.setEspecificacion_cob(((CoberturasEmitidas) event.getObject()).getEspecificacion_cob());
		} catch (Exception e) {
			CobAux.setEspecificacion_cob("-");
		}
		Integer res;
		System.out.println("LISTO PARA ACTUALIZAR");
		res = srvCoberturasEmitidas.actualizaCoberturasEmitidas(CobAux);
		System.out.println("Se actualizo");
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
		cdRamCotCobAdd = datosRamoCotizacion.getCd_ramo_cotizacion();

		PrimeFaces.current().executeScript("PF('wDlgNuevaCobertura').show();");
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

		try {
			dedAux.setEspecificacion(((DeduciblesEmitidas) event.getObject()).getEspecificacion());
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

	public void nuevoDeducible() {
		porcDedValSin = 0.0;
		porcDedValAseg = 0.0;
		valorDedMin = 0.0;
		valorDedFijo = 0.0;
		lstDeducibles = new ArrayList<Deducibles>();
		lstDeducibles = srvDeducibles.consultaDeducibles();
		cdRamCotCobAdd = datosRamoCotizacion.getCd_ramo_cotizacion();
		PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').show();");
	}

	public void guardaDeducible() {
		for (Deducibles dedTmp : selectedlstDeducibles) {
			System.out.println("DEDTMP" + dedTmp.getCd_deducible());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			DeduciblesEmitidas dedAux = new DeduciblesEmitidas();
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

			try {
				if (especificacionDed.isEmpty() || especificacionDed == null)
					especificacionDed = "";
			} catch (Exception e) {
				especificacionDed = "";
			}
			dedAux.setEspecificacion(especificacionDed);
			srvDeduciblesEmitidas.insertaDeduciblesEmitidas(dedAux);
		}
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(1, cdRamCotCobAdd);
		PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').hide();");
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

	public void nuevaClausula() {
		porcClau = 0.0;
		valorClau = 0.0;

		lstClausulas = new ArrayList<Clausulas>();
		lstClausulas = srvClausulas.consultaClausulas();
		cdRamCotCobAdd = datosRamoCotizacion.getCd_ramo_cotizacion();
		PrimeFaces.current().executeScript("PF('wDlgNuevaClausula').show();");
	}

	public void guardaClausula() {
		for (Clausulas clauTmp : selectedlstClausulas) {
			System.out.println("clauTMP" + clauTmp.getCd_clausula());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			ClausulasEmitidas clauAux = new ClausulasEmitidas();

			Integer tpRam = srvRamo.tipoRamo(Integer.decode(codRamo));
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
				try {
					if (especificacionDCla.isEmpty() || especificacionDCla == null)
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
		PrimeFaces.current().executeScript("PF('wDlgNuevaClausula').hide();");
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

	public void guardaCobertura() {
		System.out.println("Ingresa Coberturacrc" + cdRamCotCobAdd);
		for (Coberturas cobTmp : selectedLstCoberturas) {
			System.out.println("COBTMP" + cobTmp.getCd_cobertura());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			CoberturasEmitidas cobAux = new CoberturasEmitidas();
			Integer tpRam = srvRamo.tipoRamo(Integer.decode(codRamo));
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

				try {
					if (especificacionCob.isEmpty() || especificacionCob == null)
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
		PrimeFaces.current().executeScript("PF('wDlgNuevaCobertura').hide();");
	}

	public void recuperaContactosCarta() {
		Integer flgRamo;

		try {
			flgRamo = datosRamoCotizacion.getCd_ramo_cotizacion();
		} catch (Exception e) {
			flgRamo = 0;
		}
		lstContactoCarta = new ArrayList<Contacto>();
		if (flgRamo == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Prospección antes de generar la carta"));
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
		Integer flgRamo;
		try {
			flgRamo = datosCotizacion.getCd_cotizacion();
		} catch (Exception e) {
			flgRamo = 0;
		}
		lstContactoCarta = new ArrayList<Contacto>();
		if (flgRamo == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Prospección antes de generar la carta"));
			return;
		}

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
		String codCorresInteger = srvCorrespondencia.codCorresMax(String.valueOf(usr.getUsrid()));
		srvProcedures.genera_cuadro_Comparativo(codCorresInteger,String.valueOf(datosCotizacion.getCd_compania()));
		
		
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Registro Exitoso", "Se Generó el Documento Número " + numeroCarta
				+ ". Ingrese al Módulo de Correspondecia para Imprimirlo"));
	}
	
	public void onRowDeletePros(ProspeccionesView prospect) {
		Integer cdcot;
		String numCotString = prospect.getNum_cotizacion();
		cdcot = prospect.getCd_cotizacion();
		Cotizacion cotAuxCotizacion = new Cotizacion();
		cotAuxCotizacion = srvCotizacion.recuperaCotizacionPorCodigo(cdcot, prospect.getCd_compania());
		srvCotizacion.eliminaCotizacion(cotAuxCotizacion);
		lstProspeccion = new ArrayList<ProspeccionesView>();
		lstProspeccion = srvProspecciones.PospeccionesViewNumCot(numCotString);
	}

	public Double getValorAseguradorExtra() {
		return valorAseguradorExtra;
	}

	public void setValorAseguradorExtra(Double valorAseguradorExtra) {
		this.valorAseguradorExtra = valorAseguradorExtra;
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

	public CaracteristicasVehiculos getDatosCaracteristicasVehiculos() {
		return datosCaracteristicasVehiculos;
	}

	public void setDatosCaracteristicasVehiculos(CaracteristicasVehiculos datosCaracteristicasVehiculos) {
		this.datosCaracteristicasVehiculos = datosCaracteristicasVehiculos;
	}

	public String getFactorObjeto() {
		return factorObjeto;
	}

	public void setFactorObjeto(String factorObjeto) {
		this.factorObjeto = factorObjeto;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
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

	public List<Provincias> getLstProvincias() {
		return lstProvincias;
	}

	public void setLstProvincias(List<Provincias> lstProvincias) {
		this.lstProvincias = lstProvincias;
	}

	public List<Usuarios> getLstUsuarios() {
		return lstUsuarios;
	}

	public void setLstUsuarios(List<Usuarios> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

	public String getConsCed() {
		return consCed;
	}

	public void setConsCed(String consCed) {
		this.consCed = consCed;
	}

	public String getCodciudad() {
		return codciudad;
	}

	public void setCodciudad(String codciudad) {
		this.codciudad = codciudad;
	}

	public String getCodSector() {
		return codSector;
	}

	public void setCodSector(String codSector) {
		this.codSector = codSector;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getCodCiudadTelf() {
		return codCiudadTelf;
	}

	public void setCodCiudadTelf(String codCiudadTelf) {
		this.codCiudadTelf = codCiudadTelf;
	}

	public String getCodAseguradora() {
		return codAseguradora;
	}

	public void setCodAseguradora(String codAseguradora) {
		this.codAseguradora = codAseguradora;
	}

	public List<AseguradoraRamo> getListadoAsegRamo() {
		return listadoAsegRamo;
	}

	public void setListadoAsegRamo(List<AseguradoraRamo> listadoAsegRamo) {
		this.listadoAsegRamo = listadoAsegRamo;
	}

	public String getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}

	public List<Plan> getListaPlan() {
		return listaPlan;
	}

	public void setListaPlan(List<Plan> listaPlan) {
		this.listaPlan = listaPlan;
	}

	public List<Aseguradoras> getListadoAseguradoras() {
		return listadoAseguradoras;
	}

	public void setListadoAseguradoras(List<Aseguradoras> listadoAseguradoras) {
		this.listadoAseguradoras = listadoAseguradoras;
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

	public String getCodCanal() {
		return codCanal;
	}

	public void setCodCanal(String codCanal) {
		this.codCanal = codCanal;
	}

	public List<Subagentes> getLstSubagentes() {
		return lstSubagentes;
	}

	public void setLstSubagentes(List<Subagentes> lstSubagentes) {
		this.lstSubagentes = lstSubagentes;
	}

	public List<GrupoContratante> getListaGrupoContratante() {
		return listaGrupoContratante;
	}

	public void setListaGrupoContratante(List<GrupoContratante> listaGrupoContratante) {
		this.listaGrupoContratante = listaGrupoContratante;
	}

	public String getCodEjecutivo() {
		return codEjecutivo;
	}

	public void setCodEjecutivo(String codEjecutivo) {
		this.codEjecutivo = codEjecutivo;
	}

	public List<Ejecutivos> getListaEjecutivos() {
		return listaEjecutivos;
	}

	public void setListaEjecutivos(List<Ejecutivos> listaEjecutivos) {
		this.listaEjecutivos = listaEjecutivos;
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

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getSelectedUsuarios() {
		return selectedUsuarios;
	}

	public void setSelectedUsuarios(String selectedUsuarios) {
		this.selectedUsuarios = selectedUsuarios;
	}

	public String getTpPresona() {
		return tpPresona;
	}

	public void setTpPresona(String tpPresona) {
		this.tpPresona = tpPresona;
	}

	public String getFilename() {
		return filename;
	}

	public List<Archivos> getLstArchivos() {
		return lstArchivos;
	}

	public void setLstArchivos(List<Archivos> lstArchivos) {
		this.lstArchivos = lstArchivos;
	}

	public String getNmUsr() {
		return nmUsr;
	}

	public void setNmUsr(String nmUsr) {
		this.nmUsr = nmUsr;
	}

	public Boolean getExisteClie() {
		return existeClie;
	}

	public void setExisteClie(Boolean existeClie) {
		this.existeClie = existeClie;
	}

	public EmailSenderService getEmail() {
		return email;
	}

	public void setEmail(EmailSenderService email) {
		this.email = email;
	}

	public String getNmClie() {
		return nmClie;
	}

	public void setNmClie(String nmClie) {
		this.nmClie = nmClie;
	}

	public String getDirClie() {
		return dirClie;
	}

	public void setDirClie(String dirClie) {
		this.dirClie = dirClie;
	}

	public String getRefClie() {
		return refClie;
	}

	public void setRefClie(String refClie) {
		this.refClie = refClie;
	}

	public String getNumPa() {
		return numPa;
	}

	public void setNumPa(String numPa) {
		this.numPa = numPa;
	}

	public String getNumOfi() {
		return numOfi;
	}

	public void setNumOfi(String numOfi) {
		this.numOfi = numOfi;
	}

	public String getExtClie() {
		return extClie;
	}

	public void setExtClie(String extClie) {
		this.extClie = extClie;
	}

	public String getNumCelClie() {
		return numCelClie;
	}

	public void setNumCelClie(String numCelClie) {
		this.numCelClie = numCelClie;
	}

	public String getCorreoClie() {
		return correoClie;
	}

	public void setCorreoClie(String correoClie) {
		this.correoClie = correoClie;
	}

	public String getNmContac() {
		return nmContac;
	}

	public void setNmContac(String nmContac) {
		this.nmContac = nmContac;
	}

	public String getNumTelCon() {
		return numTelCon;
	}

	public void setNumTelCon(String numTelCon) {
		this.numTelCon = numTelCon;
	}

	public String getNumCel() {
		return numCel;
	}

	public void setNumCel(String numCel) {
		this.numCel = numCel;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCargoCont() {
		return cargoCont;
	}

	public void setCargoCont(String cargoCont) {
		this.cargoCont = cargoCont;
	}

	public String getDepaCpmta() {
		return depaCpmta;
	}

	public void setDepaCpmta(String depaCpmta) {
		this.depaCpmta = depaCpmta;
	}

	public String getDireccionConta() {
		return direccionConta;
	}

	public void setDireccionConta(String direccionConta) {
		this.direccionConta = direccionConta;
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

	public List<Archivos> getLstArchivosEx() {
		return lstArchivosEx;
	}

	public void setLstArchivosEx(List<Archivos> lstArchivosEx) {
		this.lstArchivosEx = lstArchivosEx;
	}

	public Boolean getLbbtn() {
		return lbbtn;
	}

	public void setLbbtn(Boolean lbbtn) {
		this.lbbtn = lbbtn;
	}

	public String getNumCotGen() {
		return numCotGen;
	}

	public void setNumCotGen(String numCotGen) {
		this.numCotGen = numCotGen;
	}

	public String getApellidoRazonSocial() {
		return apellidoRazonSocial;
	}

	public void setApellidoRazonSocial(String apellidoRazonSocial) {
		this.apellidoRazonSocial = apellidoRazonSocial;
	}

	public List<ProspeccionesView> getLstProspeccion() {
		return lstProspeccion;
	}

	public void setLstProspeccion(List<ProspeccionesView> lstProspeccion) {
		this.lstProspeccion = lstProspeccion;
	}

	public ProspeccionesView getSelectedProspeccion() {
		return selectedProspeccion;
	}

	public void setSelectedProspeccion(ProspeccionesView selectedProspeccion) {
		this.selectedProspeccion = selectedProspeccion;
	}

	public ObjetoCotizacion getDatosObjetoCotizacion() {
		return datosObjetoCotizacion;
	}

	public void setDatosObjetoCotizacion(ObjetoCotizacion datosObjetoCotizacion) {
		this.datosObjetoCotizacion = datosObjetoCotizacion;
	}

	public String getTpFromaPago() {
		return tpFromaPago;
	}

	public void setTpFromaPago(String tpFromaPago) {
		this.tpFromaPago = tpFromaPago;
	}

	public Double getFrmPagoPrimaTot() {
		return frmPagoPrimaTot;
	}

	public void setFrmPagoPrimaTot(Double frmPagoPrimaTot) {
		this.frmPagoPrimaTot = frmPagoPrimaTot;
	}

	public Double getFrmPagoDerechoEmision() {
		return frmPagoDerechoEmision;
	}

	public void setFrmPagoDerechoEmision(Double frmPagoDerechoEmision) {
		this.frmPagoDerechoEmision = frmPagoDerechoEmision;
	}

	public Double getFrmPagoSegCampesino() {
		return frmPagoSegCampesino;
	}

	public void setFrmPagoSegCampesino(Double frmPagoSegCampesino) {
		this.frmPagoSegCampesino = frmPagoSegCampesino;
	}

	public Double getFrmPagoSuperBancos() {
		return frmPagoSuperBancos;
	}

	public void setFrmPagoSuperBancos(Double frmPagoSuperBancos) {
		this.frmPagoSuperBancos = frmPagoSuperBancos;
	}

	public Double getFrmPagoIva() {
		return frmPagoIva;
	}

	public void setFrmPagoIva(Double frmPagoIva) {
		this.frmPagoIva = frmPagoIva;
	}

	public Double getFrmPagoTotal() {
		return frmPagoTotal;
	}

	public void setFrmPagoTotal(Double frmPagoTotal) {
		this.frmPagoTotal = frmPagoTotal;
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

	public DeduciblesEmitidas getSelectedDeducibleEmitida() {
		return selectedDeducibleEmitida;
	}

	public void setSelectedDeducibleEmitida(DeduciblesEmitidas selectedDeducibleEmitida) {
		this.selectedDeducibleEmitida = selectedDeducibleEmitida;
	}

	public List<DeduciblesEmitidas> getLstFilteredDeducibleEmitida() {
		return lstFilteredDeducibleEmitida;
	}

	public void setLstFilteredDeducibleEmitida(List<DeduciblesEmitidas> lstFilteredDeducibleEmitida) {
		this.lstFilteredDeducibleEmitida = lstFilteredDeducibleEmitida;
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

	public String getEspecificacionDed() {
		return especificacionDed;
	}

	public void setEspecificacionDed(String especificacionDed) {
		this.especificacionDed = especificacionDed;
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

	public String getNmCarta() {
		return nmCarta;
	}

	public void setNmCarta(String nmCarta) {
		this.nmCarta = nmCarta;
	}

	public List<Rubros> getLstRubrosCarta() {
		return lstRubrosCarta;
	}

	public void setLstRubrosCarta(List<Rubros> lstRubrosCarta) {
		this.lstRubrosCarta = lstRubrosCarta;
	}

	public String getNotasAdicionalesCarta() {
		return notasAdicionalesCarta;
	}

	public void setNotasAdicionalesCarta(String notasAdicionalesCarta) {
		this.notasAdicionalesCarta = notasAdicionalesCarta;
	}

	public List<Contacto> getLstContactoCarta() {
		return lstContactoCarta;
	}

	public void setLstContactoCarta(List<Contacto> lstContactoCarta) {
		this.lstContactoCarta = lstContactoCarta;
	}

	public Contacto getSelectedContactoCarta() {
		return selectedContactoCarta;
	}

	public void setSelectedContactoCarta(Contacto selectedContactoCarta) {
		this.selectedContactoCarta = selectedContactoCarta;
	}

	public String getFrmObservaciones() {
		return frmObservaciones;
	}

	public void setFrmObservaciones(String frmObservaciones) {
		this.frmObservaciones = frmObservaciones;
	}

}
