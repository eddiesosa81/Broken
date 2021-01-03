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
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
//import org.primefaces.model.UploadedFile;
import org.primefaces.model.file.UploadedFile;

import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.Deducibles;
import confia.entidades.basicos.DeduciblesEmitidas;
import confia.entidades.basicos.Dispositivos;
import confia.entidades.basicos.Marca;
import confia.entidades.basicos.Modelo;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.SubeArchivoObj;
import confia.entidades.basicos.TipoModuloCarta;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Archivos;
import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.entidades.transaccionales.Correspondencia;
import confia.entidades.transaccionales.Cotizacion;
import confia.entidades.transaccionales.DetalleFormaPago;
import confia.entidades.transaccionales.FormaPago;
import confia.entidades.transaccionales.NotasAclaratorias;
import confia.entidades.transaccionales.ObjetoCotizacion;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.transaccionales.SubObjetoCotizacion;
import confia.entidades.transaccionales.Ubicacion;
import confia.entidades.vistas.ConsultaAnexoPendienteView;
import confia.entidades.vistas.ConsultaObjetoAneView;
import confia.entidades.vistas.ConsultaObjetoPolView;
import confia.entidades.vistas.ConsultaPolizaView;
import confia.entidades.vistas.ConsultaSubObjetoAneView;
import confia.entidades.vistas.ConsultaSubObjetoPolView;
import confia.entidades.vistas.ConsultaUbicacionAneView;
import confia.entidades.vistas.ConsultaUbicacionPolView;
import confia.entidades.vistas.ProduccionEmitidaView;
import confia.procedures.servicioProcedures;
import confia.procedures.subeArchivo;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioContacto;
import confia.servicios.basicos.ServicioDeducibles;
import confia.servicios.basicos.ServicioMarca;
import confia.servicios.basicos.ServicioModelo;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.basicos.ServiciosDeduciblesEmitidas;
import confia.servicios.transaccionales.ServicioArchivos;
import confia.servicios.transaccionales.ServicioCaracteristicasVehiculos;
import confia.servicios.transaccionales.ServicioCorrespondencia;
import confia.servicios.transaccionales.ServicioCotizacion;
import confia.servicios.transaccionales.ServicioDetalleFormaPago;
import confia.servicios.transaccionales.ServicioFormaPago;
import confia.servicios.transaccionales.ServicioNotasAclaratorias;
import confia.servicios.transaccionales.ServicioObjetoCotizacion;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.transaccionales.ServicioSubObjetoCotizacion;
import confia.servicios.transaccionales.ServicioUbicacion;
import confia.servicios.vistas.ServicioConsultaAnexoPendienteView;
import confia.servicios.vistas.ServicioConsultaObjetoAneView;
import confia.servicios.vistas.ServicioConsultaObjetoPolView;
import confia.servicios.vistas.ServicioConsultaPolizaView;
import confia.servicios.vistas.ServicioConsultaSubObjetoAneView;
import confia.servicios.vistas.ServicioConsultaSubObjetoPolView;
import confia.servicios.vistas.ServicioConsultaUbicacionAneView;
import confia.servicios.vistas.ServicioConsultaUbicacionPolView;
import confia.servicios.vistas.ServicioProduccionEmitidaView;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@ManagedBean(name = "ControladorAnexos")
@ViewScoped
public class ControladorAnexos {
	@EJB
	private ServicioConsultaPolizaView srvConsultaPolizaView;
	@EJB
	private ServicioFormaPago srvFormaPago;
	@EJB
	private ServicioDetalleFormaPago srvDetalleFrmPago;
	@EJB
	private ServicioConsultaUbicacionPolView srvConsultaUbicacionView;
	@EJB
	private ServicioModelo srvModelos;
	@EJB
	private ServicioMarca srvMarcas;
	@EJB
	private ServicioConsultaObjetoPolView srvConsObjPolView;
	@EJB
	private ServicioConsultaSubObjetoPolView srvConsSubobjPolView;
	@EJB
	private ServicioCotizacion srvCotizacion;
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;
	@EJB
	private ServicioUbicacion srvUbicacion;
	@EJB
	private ServicioObjetoCotizacion srvObjetoCotizacion;
	@EJB
	private ServicioSubObjetoCotizacion srvSubObjetoCotizacion;
	@EJB
	private ServicioCaracteristicasVehiculos srvCaracteristicasVehiculos;
	@EJB
	private ServicioProduccionEmitidaView srvProduccionEmitida;
	@EJB
	private ServicioRubros srvRubrosCartas;
	@EJB
	private ServicioContacto srvContactosCarta;
	@EJB
	private ServicioUsuarios srvUsuario;
	@EJB
	private ServicioCorrespondencia srvCorrespondencia;
	@EJB
	private ServicioConsultaAnexoPendienteView srvConsAnexPend;
	@EJB
	private ServicioConsultaUbicacionAneView srvConsultaUbcAnePend;
	@EJB
	private ServicioConsultaObjetoAneView srvConsultaObjAnePend;
	@EJB
	private ServicioConsultaSubObjetoAneView srvConsultaSubObjAnePend;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioNotasAclaratorias srvNotasAcla;
	private servicioProcedures srvProcedimientos;
	@EJB
	private ServicioArchivos srvArchivos;
	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioDeducibles srvDeducibles;
	@EJB
	private ServiciosDeduciblesEmitidas srvDeduciblesEmitidas;

	private String codigoCompania;
	private String cdRamCotPolizaAnexo;
	private String numPolizaAnexo;
	private String clientePolizaAnexo;
	private String ramoPolizaAnexo;
	private String apellidoRazonSocial;
	private String apellidoRazonSocialAnexoP;
	private String polizaAnexoP;
	private String tipoAnexoSelec;
	private String afectaPoliza;
	private String factura;
	private String anexo;
	private Integer dias;
	private Integer llfcDesde, llfcHasta;
	private Date fcAnexo;
	private String codMarca;
	private String codModelo;
	private Date fcEmiAseg;

	// varialbes de anexos
	private String descUbicacion;

	private List<ConsultaPolizaView> lstConsultaPoliza;
	private ConsultaPolizaView PolizaSeleccionadaParaAnexo;
	private List<ConsultaUbicacionPolView> lstUbicacion;
	private ConsultaUbicacionPolView selectedUbicacion;
	private ConsultaUbicacionPolView selectedUbicacionEmision;
	private List<Dispositivos> listaDispositivos;

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
	private boolean aplicaIva;
	private boolean calculaIva;
	private boolean btnIncAsis;
	private String polizaAnexoNoCot;

	private List<FormaPago> lstFrmPago;
	private List<DetalleFormaPago> lstDetFrmPago;

	// variables Ubicaci�n
	private Ubicacion datosUbicacion;

	// variables objeto
	private ObjetoCotizacion datosObjetoCotizacion;
	private List<ObjetoCotizacion> lstObjetosIncluidos;
	private ObjetoCotizacion selectedDatosObjetoCotizacion;
	private List<ConsultaObjetoPolView> lstObjetosPoliza;
	private List<ConsultaObjetoPolView> filteredLstObjetosPoliza;
	private ConsultaObjetoPolView selectedObjetosPoliza;
	private List<ConsultaObjetoPolView> selectedLstObjetosPoliza;

	// variables extras
	private SubObjetoCotizacion datosSubObjetoCotizacion;
	private List<SubObjetoCotizacion> lstSubObjetoCot;
	private Integer exCantidad;
	private String exDetalle;
	private Double exValorSubobjeto;
	private Double exTasa;
	private Double exFactos;
	private Double exPrima;
	private ConsultaSubObjetoPolView selectedSubobjetoPol;
	private List<ConsultaSubObjetoPolView> lstSubobjetoPol;
	private List<ConsultaSubObjetoPolView> selectedLstSubobjetoPol;
	private List<ConsultaSubObjetoPolView> filteredLstSubobjetoPol;

	// variables caracteristicas
	private CaracteristicasVehiculos datosCaracteristicasVehiculos;
	private List<Modelo> listadoModelos;
	private List<Marca> listadoMarcas;
	private Boolean flgHabilitaUbicacion;
	private Boolean flgHabilitaUbicacionDataTable;
	private String caracteristicaRamv;
	private String caracteristicaPlaca;

	// variables cotizacion
	private String numCotizacion;
	private Cotizacion nuevaCot;
	private RamoCotizacion datosRamoCotizacion;

	private Boolean flgIngreaAnexo;

	// variables para la exclusion
	private Double valAsegExcAne;
	private Double tasaExcAne;
	private Double primaExcAne;
	private Integer factorExcAne;

	// variables para la anulacion cancelacion de objeto
	private List<ProduccionEmitidaView> lstProdEmitida;
	private ProduccionEmitidaView selectedProdEmitida;

	private subeArchivo archivoAdjunto;

	// CARTAS
	private List<Rubros> lstRubrosCarta;
	private List<Contacto> lstContactoCarta;
	private String nmCarta;
	private Contacto selectedContactoCarta;
	private String notasAdicionalesCarta;
	private Rubros objCarta;

	// ANEXOS PENDIENTES
	private List<ConsultaAnexoPendienteView> lstAnexoPendiente;
	private ConsultaAnexoPendienteView selectedConsAnePend;
	private List<ConsultaUbicacionAneView> lstUbicacionAnePend;
	private ConsultaUbicacionAneView selectedUbicacionAnePend;
	private List<ConsultaObjetoAneView> lstObjetoAnePend;
	private ConsultaObjetoAneView selectedObjetoAnePend;
	private List<ConsultaSubObjetoAneView> lstSubObjeAnePend;
	private ConsultaSubObjetoAneView selectedSubObjeAnePend;

	private Boolean flgBotonoEmite;
	private Boolean flgPagoFactura;

	// anexo Aclaratorio
	private String strAclaratorio;
	private String strAnexoAclaratorio;

	private String emiteAfectaPoliza;
	private String frmObservaciones;
	
	
	public String getEspecificacionDed() {
		return especificacionDed;
	}

	public void setEspecificacionDed(String especificacionDed) {
		this.especificacionDed = especificacionDed;
	}

	private String polizaClieBus;

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
	
	// deducibles
	private Double porcDedValSin;
	private Double porcDedValAseg;
	private Double valorDedMin;
	private Double valorDedFijo;
	private String especificacionDed;
	private List<Deducibles> lstDeducibles;
	private List<Deducibles> selectedlstDeducibles;
	private List<Deducibles> lstFilteredDeducibles;

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

	public ControladorAnexos() {
		lstDeducibles = new ArrayList<Deducibles>();
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();
		lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
		lstFrmPago = new ArrayList<FormaPago>();
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		PolizaSeleccionadaParaAnexo = new ConsultaPolizaView();
		lstObjetosIncluidos = new ArrayList<ObjetoCotizacion>();
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		selectedDatosObjetoCotizacion = new ObjetoCotizacion();
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		lstSubobjetoPol = new ArrayList<ConsultaSubObjetoPolView>();
		flgHabilitaUbicacion = false;
		flgHabilitaUbicacionDataTable = false;
		nuevaCot = new Cotizacion();
		datosRamoCotizacion = new RamoCotizacion();
		fcAnexo = new Date();
		srvProcedimientos = new servicioProcedures();
		flgIngreaAnexo = false;
		listadoMarcas = new ArrayList<Marca>();
		lstProdEmitida = new ArrayList<ProduccionEmitidaView>();
		archivoAdjunto = new subeArchivo();
		fcEmiAseg = new Date();
		lstUbicacionAnePend = new ArrayList<ConsultaUbicacionAneView>();
		lstRubrosCarta = new ArrayList<Rubros>();
		lstContactoCarta = new ArrayList<Contacto>();
		lstAnexoPendiente = new ArrayList<ConsultaAnexoPendienteView>();
		lstSubObjeAnePend = new ArrayList<ConsultaSubObjetoAneView>();
		flgBotonoEmite = false;
		btnIncAsis = true;
		flgPagoFactura = false;

		lstRubroGestDoc = new ArrayList<Rubros>();
		lstArchivos = new ArrayList<Archivos>();
		lstTipoGestDoc = new ArrayList<TipoModuloCarta>();
		lstObjetoGestDoc = new ArrayList<TipoModuloCarta>();
	}

	@PostConstruct
	public void recuperaDatos() {
		listadoMarcas = srvMarcas.listaMarca();
		lstRubrosCarta = srvRubrosCartas.listadoCartasPorModulo("MODULO_ANEXOS");
		lstTipoGestDoc = srvRubros.recuperaTipoGestionDocu();
		tipoArchivo = "CLIENTE";
		lstObjetoGestDoc = srvRubros.recuperaObjetoGestionDocu(tipoArchivo);
		flgPolizaDocu = true;
		porcDedValSin = 0.0;
		porcDedValAseg = 0.0;
		valorDedMin = 0.0;
		valorDedFijo = 0.0;
		lstFilteredDeducibles = new ArrayList<Deducibles>();
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
		System.out.println("datosCotizacion:" + nuevaCot.getCd_cliente());
		try {
			if (numCotizacion.isEmpty() || numCotizacion == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Cotizaci�n no identificado, comun�quese con el Administrador del Sistemas"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Cotizaci�n no identificada, comun�quese con el Administrador del Sistemas"));
			return;
		}
		lstArchivos = new ArrayList<Archivos>();
		System.out.println("tipoArchivo:" + tipoArchivo);
		if (tipoArchivo.equals("CLIENTE")) {
			lstArchivos = srvArchivos.recuperaArchivosCliente(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(nuevaCot.getCd_cliente()));
		}
		if (tipoArchivo.equals("POLIZA")) {
			lstArchivos = srvArchivos.recuperaArchivosCotizacion(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(nuevaCot.getCd_cotizacion()));
		}

	}

	public void onRowDeleteFile(Archivos objArch) {

		srvArchivos.eliminaArchivos(objArch);
		lstArchivos = new ArrayList<Archivos>();
		System.out.println("tipoArchivo:" + tipoArchivo);
		if (tipoArchivo.equals("CLIENTE")) {
			lstArchivos = srvArchivos.recuperaArchivosCliente(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(nuevaCot.getCd_cliente()));
		}
		if (tipoArchivo.equals("POLIZA")) {
			lstArchivos = srvArchivos.recuperaArchivosCotizacion(tipoArchivo, objetoArchivo, tipoArchivoDoc,
					String.valueOf(nuevaCot.getCd_cotizacion()));
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Registro eliminado exitosamente."));
	}

	public void gestionDocumental() {
		System.out.println("tipoArchivoDoc:" + tipoArchivoDoc);
		System.out.println("PolizaDocumento:" + polizaGestDocu);
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (nuevaCot.getCd_compania() == null) {
				FacesContext contextMsj = FacesContext.getCurrentInstance();
				contextMsj.addMessage(null,
						new FacesMessage("Advertencia", "Ingrese el Anexo para Poder Ingresar el Documento"));
				return;
			}
		} catch (Exception e) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese el Anexo para Poder Ingresar el Documento"));
			return;
		}
		System.out.println("numCotizacion:" + nuevaCot.getNum_cotizacion());
		System.out.println("Cliente:" + nuevaCot.getCd_cliente());

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
			// verifico que se haya ingresado la p�liza
			try {
				if (polizaGestDocu.isEmpty() || polizaGestDocu == null || polizaGestDocu.equals("")) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el n�mero de Factura o Anexo"));
					return;
				}
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el n�mero de Factura o Anexo"));
				return;
			}
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
		clieAux = srvClientes.listaClientesXId(nuevaCot.getCd_cliente());
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
		clieAux = srvClientes.listaClientesXId(nuevaCot.getCd_cliente());
		System.out.println("EXISTE CLIENTE:" + clieAux.getCd_cliente());
		Rubros rb = new Rubros();
		System.out.println("tipoArchivoDoc" + tipoArchivoDoc);
		rb = srvRubros.recuperaRubro(tipoArchivoDoc);
		Archivos arc = new Archivos();
		arc.setModulo("ANEXOS");
		arc.setUbicacion(nmArchivo);
		arc.setCd_rubro(String.valueOf(rb.getCd_rubro()));
		arc.setCd_cliente(String.valueOf(clieAux.getCd_cliente()));
		arc.setCd_cotizacion(nuevaCot.getCd_cotizacion());
		arc.setDesc_docu(rb.getDesc_rubro());
		if (tipoArchivo.equals("POLIZA")) {
			arc.setFactura_aseguradora(polizaGestDocu);
			System.out.println("numPolizaAnexo:" + numPolizaAnexo);
			arc.setPoliza(numPolizaAnexo);
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

	public void buscaPoliza() {

		try {
			if (apellidoRazonSocial.isEmpty() || apellidoRazonSocial == null) {
				apellidoRazonSocial = "%";
			} else {
				apellidoRazonSocial = apellidoRazonSocial.trim().toUpperCase();
			}

		} catch (Exception e) {
			apellidoRazonSocial = "%";
		}
		try {
			if (polizaClieBus.isEmpty() || polizaClieBus == null) {
				polizaClieBus = "%";
			} else {
				polizaClieBus = polizaClieBus.trim();
			}
		} catch (Exception e) {
			polizaClieBus = "%";
		}
		lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXClientePol(apellidoRazonSocial, polizaClieBus);
	}

	public void buscaAnexoPendiente() {
		System.out.println("INGRESO ANEXO");
		try {
			apellidoRazonSocialAnexoP = apellidoRazonSocialAnexoP.trim().toUpperCase();
		} catch (Exception e) {
			apellidoRazonSocialAnexoP = "%";
		}
		try {
			if (polizaAnexoP == null) {
				polizaAnexoP = "%";
			}
		} catch (Exception e) {
			polizaAnexoP = "%";
		}
		try {
			if (polizaAnexoNoCot == null) {
				polizaAnexoNoCot = "%";
			}
		} catch (Exception e) {
			polizaAnexoNoCot = "%";
		}

		System.out.println("apellidoRazonSocialAnexoP:" + apellidoRazonSocialAnexoP);
		System.out.println("polizaAnexoP:" + polizaAnexoP);
		if (!polizaAnexoNoCot.equals("%")) {
			lstAnexoPendiente = srvConsAnexPend.consultaAnexoPendientePolizaNumcot(polizaAnexoNoCot);
		} else if (apellidoRazonSocialAnexoP.equals("%")) {
			lstAnexoPendiente = srvConsAnexPend.consultaAnexoPendientePoliza(polizaAnexoP);
		} else if (polizaAnexoP.equals("%")) {
			lstAnexoPendiente = srvConsAnexPend.consultaAnexoPendiente(apellidoRazonSocialAnexoP);
		} else if (!apellidoRazonSocialAnexoP.equals("%") && !polizaAnexoP.equals("%")
				&& !polizaAnexoNoCot.equals("%")) {
			lstAnexoPendiente = srvConsAnexPend.consultaAnexoPendiente(apellidoRazonSocialAnexoP, polizaAnexoP);
		}
	}

	public void buscarPolizaXIdAneP() {
		// RECUPERO DATOS DE LA COTIZACION
		codigoCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
				.toString();
		Integer cdRamoCotizacionAne;
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		cdRamoCotizacionAne = Integer.parseInt(requestParameterMap.get("cdRamoCotizacionAne"));
		// recupera la cotizaci�n del anexo

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgEmitePendAnexo').show();");
		PrimeFaces.current().executeScript("PF('wDlgEmitePendAnexo').show();");
		lstAnexoPendiente = new ArrayList<ConsultaAnexoPendienteView>();
		lstAnexoPendiente.add(srvConsAnexPend.consultaAnexoPendienteRamCot(String.valueOf(cdRamoCotizacionAne)));
		lstUbicacionAnePend = new ArrayList<ConsultaUbicacionAneView>();
		lstUbicacionAnePend = srvConsultaUbcAnePend.consultaUbicacionxCrc(String.valueOf(cdRamoCotizacionAne));
		lstObjetoAnePend = new ArrayList<ConsultaObjetoAneView>();
		lstSubObjeAnePend = new ArrayList<ConsultaSubObjetoAneView>();
		RamoCotizacion cdrcAux = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(cdRamoCotizacionAne,
				Integer.valueOf(codigoCompania));
		nuevaCot = new Cotizacion();
		nuevaCot = srvCotizacion.recuperaCotizacionPorCodigo(cdrcAux.getCd_cotizacion(),
				Integer.valueOf(codigoCompania));
	}

	public void seleccionaAnexoPend() {
		SimpleDateFormat formato;
		String patron = "dd/MM/yyyy";
		formato = new SimpleDateFormat(patron);
		for (ConsultaAnexoPendienteView anexoP : lstAnexoPendiente) {
			PolizaSeleccionadaParaAnexo = srvConsultaPolizaView.consultaPolizaXCdRamCot(anexoP.getCd_ram_cot_ori());
			tipoAnexoSelec = anexoP.getDesc_rubro();
			try {
				fcAnexo = formato.parse(anexoP.getFc_fin_vig_date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		codigoCompania = PolizaSeleccionadaParaAnexo.getCd_compania();
		cdRamCotPolizaAnexo = PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion();
		numPolizaAnexo = PolizaSeleccionadaParaAnexo.getPoliza();
		clientePolizaAnexo = PolizaSeleccionadaParaAnexo.getCliente();
		ramoPolizaAnexo = PolizaSeleccionadaParaAnexo.getDesc_ramo();
		apellidoRazonSocial = null;

		flgBotonoEmite = true;

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgEmitePendAnexo').hide();");
		PrimeFaces.current().executeScript("PF('wDlgEmitePendAnexo').hide();");

	}

	public void onRowSelectUbcAneP(SelectEvent event) {
		String codUbc;
		codUbc = ((ConsultaUbicacionAneView) event.getObject()).getCd_ubicacion();
		lstObjetoAnePend = new ArrayList<ConsultaObjetoAneView>();
		lstObjetoAnePend = srvConsultaObjAnePend.consultaObjetosXUbc(codUbc);
		lstSubObjeAnePend = new ArrayList<ConsultaSubObjetoAneView>();
	}

	public void onRowSelectObjetoAneP(SelectEvent event) {
		String codObj;
		codObj = ((ConsultaObjetoAneView) event.getObject()).getCd_obj_cotizacion();
		lstSubObjeAnePend = new ArrayList<ConsultaSubObjetoAneView>();
		lstSubObjeAnePend = srvConsultaSubObjAnePend.consultaSubObjetoxCdObj(codObj);
	}

	public void eliminaAnexo() {
		Cotizacion cot = new Cotizacion();
		try {
			if (lstAnexoPendiente.size() != 0) {
				for (ConsultaAnexoPendienteView pend : lstAnexoPendiente) {
					cot = srvCotizacion.recuperaCotizacionPorCodigo(Integer.valueOf(pend.getCd_cotizacion()),
							Integer.valueOf(pend.getCd_compania()));
					srvCotizacion.eliminaCotizacion(cot);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Registro Eliminado Exitosamente"));
		lstSubObjeAnePend = new ArrayList<ConsultaSubObjetoAneView>();
		lstObjetoAnePend = new ArrayList<ConsultaObjetoAneView>();
		lstUbicacionAnePend = new ArrayList<ConsultaUbicacionAneView>();
		lstAnexoPendiente = new ArrayList<ConsultaAnexoPendienteView>();
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgEmitePendAnexo').hide();");
		// PrimeFaces.current().executeScript("PF('wDlgEmitePendAnexo').hide();");

		// ExternalContext ctx =
		// FacesContext.getCurrentInstance().getExternalContext();
		// try {
		// ctx.redirect("./Broken/index.jsf");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// action="/Broken/index.xhtml?faces-redirect=true"
	}

	public void buscarPolizaXId() {
		// RECUPERO DATOS DE LA COTIZACION
		codigoCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
				.toString();
		Integer cdRamoCotizacion;
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		cdRamoCotizacion = Integer.parseInt(requestParameterMap.get("cdRamoCotizacion"));
		PolizaSeleccionadaParaAnexo = srvConsultaPolizaView.consultaPolizaXCdRamCot(String.valueOf(cdRamoCotizacion));
		codigoCompania = PolizaSeleccionadaParaAnexo.getCd_compania();
		cdRamCotPolizaAnexo = PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion();
		numPolizaAnexo = PolizaSeleccionadaParaAnexo.getPoliza();
		clientePolizaAnexo = PolizaSeleccionadaParaAnexo.getCliente();
		ramoPolizaAnexo = PolizaSeleccionadaParaAnexo.getDesc_ramo();
		apellidoRazonSocial = null;
	}

	public void emiteAnexo() {
		int count;
		String tpFrmPago = null;
		System.out.println("flgBotonoEmite:" + flgBotonoEmite);
		System.out.println("afectaPoliza:" + afectaPoliza);
		emiteAfectaPoliza = afectaPoliza;
		if (flgBotonoEmite == true) {
			count = lstFrmPago.size();
			if (count == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la forma de Pago antes de Emitir."));
				return;
			} else {
				System.out.println("FormaPAgo:" + lstFrmPago.size());

				for (FormaPago frmp : lstFrmPago) {
					System.out.println("FORMA PAGO:" + frmp.getCd_forma_Pago());
					System.out.println("FORMA PAGO:" + frmp.getNum_alternativa_formaPago());
					tpFrmPago = frmp.getNum_alternativa_formaPago();

				}
				if (tpFrmPago.equals("PAGO FACTURA")) {
					flgPagoFactura = true;
					factura = "PAGOFACTURA";
				}else if(tpFrmPago.equals("PROVISIONAL")){
					flgPagoFactura = true;
					factura = "PROVISIONAL";
				}
				System.out.println("Tipo pago:" + tpFrmPago);
				System.out.println("Tipo bandera:" + flgPagoFactura);
				PrimeFaces.current().executeScript("PF('wDlgEmiteAnexo').show();");

			}
		} else {
			if (flgIngreaAnexo == false) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Anexo antes de Emitir."));
			} else {
				count = lstFrmPago.size();
				if (count == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
							new FacesMessage("Advertencia", "Ingrese la forma de Pago antes de Emitir."));
					return;
				} else {
					for (FormaPago frmp : lstFrmPago) {
						tpFrmPago = frmp.getNum_alternativa_formaPago();
						System.out.println("FORMA PAGO:" + frmp.getCd_forma_Pago());
						System.out.println("FORMA PAGO:" + frmp.getNum_alternativa_formaPago());
					}

					if (tpFrmPago.equals("PAGO FACTURA")) {
						flgPagoFactura = true;
						factura = "PAGOFACTURA";
					}else if(tpFrmPago.equals("PROVISIONAL")){
						flgPagoFactura = true;
						factura = "PROVISIONAL";
					}
					System.out.println("Tipo Pago:" + factura);
					System.out.println("Tipo vandera:" + flgPagoFactura);
					PrimeFaces.current().executeScript("PF('wDlgEmiteAnexo').show();");

				}
			}
		}
	}

	public void consultaPoliza() {
		System.out.println("INGRESOOOO Bonsulta Poliza");
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wbuscaPolizaAne').show();");
		PrimeFaces.current().executeScript("PF('wbuscaPolizaAne').show();");
	}

	public void consultaAnexoPendiente() {
		System.out.println("INGRESOOOO Bonsulta Poliza");
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wbuscaAnexoPend').show();");
		PrimeFaces.current().executeScript("PF('wbuscaAnexoPend').show();");
	}

	public void guardaEmision() {
		System.out.println("INGRESO EMITIR");
		// guardo la emisi�n del anexo
		int count = 0;
		int res = 0;
		// verifica si tiene ingresada la forma de pagon

		count = lstFrmPago.size();
		System.out.println("FORMAPAGO:" + count);
		if (count == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese la forma de Pago antes de Emitir."));
			return;
		}

		// Verifica que la factura no exista en la base de daots
		int existePol = 0;
		String poliza, fac, tpFrmPago = null;
		System.out.println("numPolizaAnexo:" + numPolizaAnexo);
		System.out.println("factura:" + factura);
		poliza = numPolizaAnexo;
		fac = factura;
		for (FormaPago frmp : lstFrmPago) {
			tpFrmPago = frmp.getNum_alternativa_formaPago();
		}
		if (!tpFrmPago.equals("PAGO FACTURA") && !tpFrmPago.equals("PROVISIONAL")) {
			existePol = srvRamoCotizacion.verificoExisteFacturaAnexo(poliza, fac);
			if (existePol > 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia", "N�mero de P�liza y Factura Registrado en el Sistema"));

				return;

			}
		}
		System.out.println("flgBotonoEmite:" + flgBotonoEmite);
		if (flgBotonoEmite == true) {
			String tpAne = null, afectAne = null, cdRamCot = null, cdComp = null;
			// recupero el ramo cotizacion
			datosRamoCotizacion = new RamoCotizacion();
			for (ConsultaAnexoPendienteView anex : lstAnexoPendiente) {
				cdRamCot = anex.getCd_ramo_cotizacion();
				cdComp = anex.getCd_compania();
				tpAne = anex.getDesc_rubro();
				afectAne = anex.getAfecta_anexo();
			}
			datosRamoCotizacion = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(Integer.valueOf(cdRamCot),
					Integer.valueOf(cdComp));
			datosRamoCotizacion.setFc_emision_aseguradora_date(fcEmiAseg);
			datosRamoCotizacion.setFactura_aseguradora(factura);
			datosRamoCotizacion.setAnexo(anexo);

			if (tpAne.equals("ANULACION") || tpAne.equals("CANCELACION")) {
				srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
			} else {
				System.out.println(
						"-------------------CD_RAMO_COTIZACION=" + datosRamoCotizacion.getCd_ramo_cotizacion());

				srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
				res = srvProcedimientos.act_valores_ramo_cot_anexos_sp(
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}

			if (tpAne.equals("INCLUSION") && afectAne.equals("UBICACION")) {
				res = srvProcedimientos.emiteAnexosInclusionUbc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}

			if (tpAne.equals("INCLUSION") && afectAne.equals("OBJETO")) {
				List<Ubicacion> lstUbc = new ArrayList<Ubicacion>();
				Ubicacion ubcAux = new Ubicacion();
				lstUbc = srvUbicacion.listarUbicaciones(datosRamoCotizacion.getCd_ramo_cotizacion());
				for (Ubicacion ubicacion : lstUbc) {
					ubcAux = srvUbicacion.recuperaUbicacionPolAne(String.valueOf(datosRamoCotizacion.getCdRamCotOri()),
							String.valueOf(ubicacion.getCd_compania()), ubicacion.getDsc_ubicacion());
					res = srvProcedimientos.emiteAnexosInclusionObj(String.valueOf(ubcAux.getCd_ubicacion()),
							String.valueOf(ubicacion.getCd_ubicacion()), String.valueOf(ubicacion.getCd_compania()));
				}
			}
			if (tpAne.equals("INCLUSION") && afectAne.equals("EXTRA")) {
				List<Ubicacion> lstUbc = new ArrayList<Ubicacion>();
				lstUbc = srvUbicacion.listarUbicaciones(datosRamoCotizacion.getCd_ramo_cotizacion());
				for (Ubicacion ubicacion : lstUbc) {
					List<ObjetoCotizacion> lstObj = new ArrayList<ObjetoCotizacion>();
					lstObj = srvObjetoCotizacion.recuperaObjetosPorUbicacion(ubicacion.getCd_ubicacion(),
							ubicacion.getCd_compania());
					for (ObjetoCotizacion objetoCotizacion : lstObj) {
						res = srvProcedimientos.emiteAnexosInclusionSubObj(
								String.valueOf(objetoCotizacion.getCdObjOri()),
								String.valueOf(objetoCotizacion.getCd_obj_cotizacion()),
								String.valueOf(objetoCotizacion.getCd_compania()));
					}
				}

			}
			if (tpAne.equals("EXCLUSION") && afectAne.equals("UBICACION")) {
				res = srvProcedimientos.emiteAnexosExclusionUbj(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}

			if (tpAne.equals("EXCLUSION") && afectAne.equals("OBJETO")) {

				List<Ubicacion> lstUbc = new ArrayList<Ubicacion>();
				lstUbc = srvUbicacion.listarUbicaciones(datosRamoCotizacion.getCd_ramo_cotizacion());
				for (Ubicacion ubicacion : lstUbc) {
					res = srvProcedimientos.emiteAnexoseXclusionObj(String.valueOf(ubicacion.getCdUbcOri()),
							String.valueOf(ubicacion.getCd_ubicacion()), String.valueOf(ubicacion.getCd_compania()));
				}

			}
			if (tpAne.equals("EXCLUSION") && afectAne.equals("EXTRA")) {
				List<Ubicacion> lstUbc = new ArrayList<Ubicacion>();
				lstUbc = srvUbicacion.listarUbicaciones(datosRamoCotizacion.getCd_ramo_cotizacion());
				for (Ubicacion ubicacion : lstUbc) {
					List<ObjetoCotizacion> lstObj = new ArrayList<ObjetoCotizacion>();
					lstObj = srvObjetoCotizacion.recuperaObjetosPorUbicacion(ubicacion.getCd_ubicacion(),
							ubicacion.getCd_compania());
					for (ObjetoCotizacion objetoCotizacion : lstObj) {
						res = srvProcedimientos.emiteAnexosExclusionSubObj(
								String.valueOf(objetoCotizacion.getCdObjOri()),
								String.valueOf(objetoCotizacion.getCd_obj_cotizacion()),
								String.valueOf(objetoCotizacion.getCd_compania()));
					}
				}
			}

			if (tpAne.equals("MODVALASEG") && afectAne.equals("OBJETO")) {
				List<Ubicacion> lstUbc = new ArrayList<Ubicacion>();
				lstUbc = srvUbicacion.listarUbicaciones(datosRamoCotizacion.getCd_ramo_cotizacion());
				for (Ubicacion ubicacion : lstUbc) {
					res = srvProcedimientos.emiteAnexosMODVALASEGObj(String.valueOf(ubicacion.getCdUbcOri()),
							String.valueOf(ubicacion.getCd_ubicacion()), String.valueOf(ubicacion.getCd_compania()));
				}
			}
			if (tpAne.equals("MODVALASEG") && afectAne.equals("EXTRA")) {
				List<Ubicacion> lstUbc = new ArrayList<Ubicacion>();
				lstUbc = srvUbicacion.listarUbicaciones(datosRamoCotizacion.getCd_ramo_cotizacion());
				for (Ubicacion ubicacion : lstUbc) {
					List<ObjetoCotizacion> lstObj = new ArrayList<ObjetoCotizacion>();
					lstObj = srvObjetoCotizacion.recuperaObjetosPorUbicacion(ubicacion.getCd_ubicacion(),
							ubicacion.getCd_compania());
					for (ObjetoCotizacion objetoCotizacion : lstObj) {
						res = srvProcedimientos.emiteAnexosMODVALASEGSubObj(
								String.valueOf(objetoCotizacion.getCdObjOri()),
								String.valueOf(objetoCotizacion.getCd_obj_cotizacion()),
								String.valueOf(objetoCotizacion.getCd_compania()));
					}
				}
			}
			if (tpAne.equals("ANULACION") || afectAne.equals("CANCELACION")) {
				res = srvProcedimientos.anexoAnulaCancelaPoliza(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			if (res == 0) {
				// RequestContext context = RequestContext.getCurrentInstance();
				// context.execute("PF('wDlgEmiteAnexo').hide();");
				PrimeFaces.current().executeScript("PF('wDlgEmiteAnexo').hide();");
				// ExternalContext ctx =
				// FacesContext.getCurrentInstance().getExternalContext();
				// try {
				// ctx.redirect("./index.jsf");
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al Emitir el Anexo Comun�quese con el Administrador del Sistema."));
				return;
			}

		} else {
			datosRamoCotizacion.setFc_emision_aseguradora_date(fcEmiAseg);
			datosRamoCotizacion.setFactura_aseguradora(factura);
			datosRamoCotizacion.setAnexo(anexo);

			if (tipoAnexoSelec.equals("ANULACION") || tipoAnexoSelec.equals("CANCELACION")) {
				datosRamoCotizacion.setTotal_asegurado(valAsegExcAne);
				datosRamoCotizacion.setTotal_prima(primaExcAne);
				datosRamoCotizacion.setFactor(Double.valueOf(factorExcAne));
				datosRamoCotizacion.setTasa(tasaExcAne);
				srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
			} else {
				srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
				System.out.println(
						"-------------------CD_RAMO_COTIZACION=" + datosRamoCotizacion.getCd_ramo_cotizacion());

				res = srvProcedimientos.act_valores_ramo_cot_anexos_sp(
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			System.out.println("tipoAnexoSelec:" + tipoAnexoSelec);
			System.out.println("afectaPoliza:" + emiteAfectaPoliza);
			System.out.println("PolizaSeleccionadaParaAnexo" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			if (tipoAnexoSelec.equals("INCLUSION") && emiteAfectaPoliza.equals("UBICACION")) {
				res = srvProcedimientos.emiteAnexosInclusionUbc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			if (tipoAnexoSelec.equals("INCLUSION") && emiteAfectaPoliza.equals("OBJETO")) {
				System.out.println(
						"************************INCLUSION OBJETO*************************************************");
				System.out.println("selectedUbicacion.getCd_ubicacion():" + selectedUbicacionEmision.getCd_ubicacion());
				System.out.println("datosUbicacion.getCd_ubicacion():" + datosUbicacion.getCd_ubicacion());
				System.out.println("datosRamoCotizacion.getCd_compania():" + datosRamoCotizacion.getCd_compania());

				res = srvProcedimientos.emiteAnexosInclusionObj(
						String.valueOf(selectedUbicacionEmision.getCd_ubicacion()),
						String.valueOf(datosUbicacion.getCd_ubicacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			if (tipoAnexoSelec.equals("INCLUSION") && emiteAfectaPoliza.equals("EXTRA")) {
				res = srvProcedimientos.emiteAnexosInclusionSubObj(
						String.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()),
						String.valueOf(datosObjetoCotizacion.getCd_obj_cotizacion()),
						String.valueOf(datosObjetoCotizacion.getCd_compania()));
			}
			if (tipoAnexoSelec.equals("EXCLUSION") && emiteAfectaPoliza.equals("UBICACION")) {
				res = srvProcedimientos.emiteAnexosExclusionUbj(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			if (tipoAnexoSelec.equals("EXCLUSION") && emiteAfectaPoliza.equals("OBJETO")) {
				res = srvProcedimientos.emiteAnexoseXclusionObj(
						String.valueOf(selectedUbicacionEmision.getCd_ubicacion()),
						String.valueOf(datosUbicacion.getCd_ubicacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			if (tipoAnexoSelec.equals("EXCLUSION") && emiteAfectaPoliza.equals("EXTRA")) {
				res = srvProcedimientos.emiteAnexosExclusionSubObj(
						String.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()),
						String.valueOf(datosObjetoCotizacion.getCd_obj_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			if (tipoAnexoSelec.equals("MODVALASEG") && emiteAfectaPoliza.equals("OBJETO")) {
				res = srvProcedimientos.emiteAnexosMODVALASEGObj(
						String.valueOf(selectedUbicacionEmision.getCd_ubicacion()),
						String.valueOf(datosUbicacion.getCd_ubicacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));

			}
			if (tipoAnexoSelec.equals("MODVALASEG") && emiteAfectaPoliza.equals("EXTRA")) {
				res = srvProcedimientos.emiteAnexosMODVALASEGSubObj(
						String.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()),
						String.valueOf(datosObjetoCotizacion.getCd_obj_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));

			}
			if (tipoAnexoSelec.equals("ANULACION") || tipoAnexoSelec.equals("CANCELACION")) {
				res = srvProcedimientos.anexoAnulaCancelaPoliza(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion(),
						String.valueOf(datosRamoCotizacion.getCd_ramo_cotizacion()),
						String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
			if (res == 0) {
				// RequestContext context = RequestContext.getCurrentInstance();
				// context.execute("PF('wDlgEmiteAnexo').hide();");
				// PrimeFaces.current().executeScript("PF('wDlgEmiteAnexo').hide();");
				// ExternalContext ctx =
				// FacesContext.getCurrentInstance().getExternalContext();
				// try {
				// ctx.redirect("./Broken/index.jsf");
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al Emitir el Anexo Comun�quese con el Administrador del Sistema."));
				return;
			}
		}
	}

	public void aceptaAnexo() {
		System.out.println("INGRESO ANEXO");

		int res = 0;
		if (tipoAnexoSelec.equals("DEDUCIBLE")) {
			System.out.println("NUEVACOT:" + nuevaCot);
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			System.out.println("RAMO:" + PolizaSeleccionadaParaAnexo.getDesc_ramo());
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getPoliza());
			PrimeFaces.current().executeScript("PF('wDlgDeducible').show();");
			flgIngreaAnexo = true;
			
			porcDedValSin = 0.0;
			porcDedValAseg = 0.0;
			valorDedMin = 0.0;
			valorDedFijo = 0.0;
			lstDeducibles = new ArrayList<Deducibles>();
			lstDeducibles = srvDeducibles.consultaDeducibles();
			PrimeFaces.current().executeScript("PF('wDlgNuevoDeducible').show();");
			return;
			
		}
		if (tipoAnexoSelec.equals("MODVALASEG") && afectaPoliza.equals("UBICACION")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Tipo de Anexo No Soportado por el Sistema"));
			return;
		}

		System.out.println("Tipo anexo:" + tipoAnexoSelec);
		System.out.println("afecta a:" + afectaPoliza);
		if (tipoAnexoSelec.equals("NO")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Tipo de Anexo"));
			return;
		}
		if (tipoAnexoSelec.equals("ANULACION") || tipoAnexoSelec.equals("CANCELACION")) {
			if (afectaPoliza.equals("POLIZA")) {
				// verifica si se trata de una anulaci�n o cancelaci�n
				res = srvConsultaPolizaView.polizaPagada(PolizaSeleccionadaParaAnexo.getCd_compania(),
						PolizaSeleccionadaParaAnexo.getCd_cotizacion());
				if (tipoAnexoSelec.equals("ANULACION") && res == 1) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Existe Pago Realizado a la P�liza. No Puede Generar el Anexo de Anulaci�n"));
					return;
				}
				if (tipoAnexoSelec.equals("CANCELACION") && res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"No Existe Pago Realizado a la P�liza. No Puede Generar el Anexo de Cancelaci�n"));
					return;
				}
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Anexo no soportado. Comun�quese con el Administrador del Sistema"));
				return;
			}
		} else if (afectaPoliza.equals("NO")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione el campo Afectaci�n"));
			return;
		}

		if (flgIngreaAnexo) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Anexo Generado Ingrese la Forma de Pago o Cancele el mismo."));
			return;
		}

		////////////////////////////////////////////////
		// a�ado la cotizaci�n para el anexo
		// COTIZACION
		////////////////////////////////////////////////
		String lsCompania;
		Integer liCompania;
		Integer fcJuliana;
		Boolean flgAnexo = false;
		lsCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA").toString();
		liCompania = Integer.parseInt(lsCompania);
		// ingresa nueva cotizaci�n
		nuevaCot.setCd_compania(liCompania);
		nuevaCot.setAfecta_anexo(afectaPoliza);
		if (tipoAnexoSelec.equals("INCLUSION") && !afectaPoliza.equals("POLIZA")) {
			nuevaCot.setCd_rubro(30);
			flgAnexo = true;
		}
		if (tipoAnexoSelec.equals("EXCLUSION") && !afectaPoliza.equals("POLIZA")) {
			nuevaCot.setCd_rubro(31);
			flgAnexo = true;
		}
		if (tipoAnexoSelec.equals("MODVALASEG") && !afectaPoliza.equals("POLIZA")) {
			nuevaCot.setCd_rubro(50);
			flgAnexo = true;
		}
		if (tipoAnexoSelec.equals("ANULACION") && afectaPoliza.equals("POLIZA")) {
			nuevaCot.setCd_rubro(51);
			flgAnexo = true;
		}
		if (tipoAnexoSelec.equals("CANCELACION") && afectaPoliza.equals("POLIZA")) {
			nuevaCot.setCd_rubro(52);
			flgAnexo = true;
		}
		if (tipoAnexoSelec.equals("ACLARATORIO")) {
			nuevaCot.setCd_rubro(671);
			flgAnexo = true;
		}

		if (flgAnexo == false) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Tipo de Anexo no Soportado. Comun�quese con el Administrador del Sistema"));
			return;
		}

		nuevaCot.setCd_cliente(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_cliente()));
		nuevaCot.setCd_aseguradora(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_aseguradora()));
		nuevaCot.setFact_periodica_cot(0);
		Integer usrId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO");
		nuevaCot.setUsrid(usrId);
		Integer cdCotMax = srvCotizacion.insertarCotizacion(nuevaCot);
		if (cdCotMax == 1) {
			cdCotMax = srvCotizacion.codigoMaxCotizacion();
		}
		nuevaCot = new Cotizacion();
		nuevaCot = srvCotizacion.recuperaCotizacionPorCodigo(cdCotMax, liCompania);
		numCotizacion = nuevaCot.getNum_cotizacion();
		System.out.println("NUMERO COTIZACION:" + numCotizacion);
		// INGRESO RAMO - COTIZACION
		datosRamoCotizacion = new RamoCotizacion();
		datosRamoCotizacion.setCdRamCotOri(Double.valueOf(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion()));
		datosRamoCotizacion.setCd_cotizacion(nuevaCot.getCd_cotizacion());
		datosRamoCotizacion.setCd_compania(nuevaCot.getCd_compania());
		datosRamoCotizacion.setCd_subagente(Integer.decode(PolizaSeleccionadaParaAnexo.getCdSubagente()));
		datosRamoCotizacion.setCd_ramo(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_ramo()));
		datosRamoCotizacion.setCd_ejecutivo(Integer.decode(PolizaSeleccionadaParaAnexo.getCdEjecutivo()));
		datosRamoCotizacion.setPoliza(PolizaSeleccionadaParaAnexo.getPoliza());
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
		// inicializo las caracteristicas del objeto
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();

		/////////////////////////////////////
		// verifica el tipo de anexo
		////////////////////////////////////

		// RequestContext context = RequestContext.getCurrentInstance();
		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("UBICACION")) {
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			PrimeFaces.current().executeScript("PF('wDlgInclusionObj').show();");
			System.out.println("ACTIVA BOTON ANEXO INLCUISION Anexo:" + PolizaSeleccionadaParaAnexo.getCd_plan());
			if (!PolizaSeleccionadaParaAnexo.getCd_plan().equals("0")) {
				btnIncAsis = false;
			}

			// habilito el controlador de texto y deshabilito la selecci�n de
			// ubicaci�n
			flgHabilitaUbicacion = false;
			flgHabilitaUbicacionDataTable = true;
		}
		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("OBJETO")) {

			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			System.out.println("ACTIVA BOTON ANEXO INLCUISION Anexo:" + PolizaSeleccionadaParaAnexo.getCd_plan());
			if (!PolizaSeleccionadaParaAnexo.getCd_plan().equals("0")) {
				btnIncAsis = false;
			}
			PrimeFaces.current().executeScript("PF('wDlgInclusionObj').show();");
			flgHabilitaUbicacion = true;
			flgHabilitaUbicacionDataTable = false;
		}
		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("EXTRA")) {
			System.out.println("INGRESO A INCLUSION EXTRA");
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstObjetosIncluidos = new ArrayList<ObjetoCotizacion>();
			lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			PrimeFaces.current().executeScript("PF('wDlgInclusionSubObj').show();");
		}
		if (tipoAnexoSelec.equals("EXCLUSION") && afectaPoliza.equals("UBICACION")) {
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION POLIZA:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
			valAsegExcAne = 0.0;
			tasaExcAne = 0.0;
			primaExcAne = 0.0;
			factorExcAne = 100;

			PrimeFaces.current().executeScript("PF('wDlgExclusionUbc').show();");
		}
		if (tipoAnexoSelec.equals("EXCLUSION") && afectaPoliza.equals("OBJETO")) {
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
			valAsegExcAne = 0.0;
			tasaExcAne = 0.0;
			primaExcAne = 0.0;
			factorExcAne = 100;
			PrimeFaces.current().executeScript("PF('wDlgExclusionObj').show();");
		}

		if (tipoAnexoSelec.equals("EXCLUSION") && afectaPoliza.equals("EXTRA")) {
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
			lstSubobjetoPol = new ArrayList<ConsultaSubObjetoPolView>();
			valAsegExcAne = 0.0;
			tasaExcAne = 0.0;
			primaExcAne = 0.0;
			factorExcAne = 100;
			PrimeFaces.current().executeScript("PF('wDlgExclusionExtra').show();");
		}

		if (tipoAnexoSelec.equals("MODVALASEG") && afectaPoliza.equals("OBJETO")) {
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
			valAsegExcAne = 0.0;
			tasaExcAne = 0.0;
			primaExcAne = 0.0;
			factorExcAne = 100;
			PrimeFaces.current().executeScript("PF('wDlgModValAsegObj').show();");
		}
		if (tipoAnexoSelec.equals("MODVALASEG") && afectaPoliza.equals("EXTRA")) {
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstUbicacion = new ArrayList<ConsultaUbicacionPolView>();
			lstUbicacion = srvConsultaUbicacionView
					.consultaUbicacionxCrc(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
			valAsegExcAne = 0.0;
			tasaExcAne = 0.0;
			primaExcAne = 0.0;
			factorExcAne = 100;
			PrimeFaces.current().executeScript("PF('wDlgModValAsegExtra').show();");
		}
		if ((tipoAnexoSelec.equals("ANULACION") || tipoAnexoSelec.equals("CANCELACION"))
				&& afectaPoliza.equals("POLIZA")) {
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());

			valAsegExcAne = PolizaSeleccionadaParaAnexo.getTotal_asegurado() * -1;
			tasaExcAne = 0.0;
			primaExcAne = 0.0;
			factorExcAne = 100;
			PrimeFaces.current().executeScript("PF('wDlgAnuCanPol').show();");
		}
		if (tipoAnexoSelec.equals("ACLARATORIO")) {
			System.out.println("NUEVACOT:" + nuevaCot);
			// RECUPERO LAS UBICACIONES DE LA P�LIZA
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion());
			System.out.println("RAMO:" + PolizaSeleccionadaParaAnexo.getDesc_ramo());
			System.out.println("RAMO COTIZACION:" + PolizaSeleccionadaParaAnexo.getPoliza());
			PrimeFaces.current().executeScript("PF('wDlgAclaratorio').show();");
		}
		flgIngreaAnexo = true;
	}
	
	public void guardaDeducible() {
		for (Deducibles dedTmp : selectedlstDeducibles) {
			System.out.println("DEDTMP" + dedTmp.getCd_deducible());
			// VERIFICA SI LA COBERTURA YA SE ENCUENTRA INGRESADA
			DeduciblesEmitidas dedAux = new DeduciblesEmitidas();
			dedAux.setCd_deducible(dedTmp.getCd_deducible());
			dedAux.setCd_compania(1);
			dedAux.setCd_ramo_cotizacion(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_ramo_cotizacion()));
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
				if(especificacionDed.isEmpty() || especificacionDed == null)
					especificacionDed = "";
			} catch (Exception e) {
				especificacionDed = "";
			}
			dedAux.setEspecificacion(especificacionDed);
			srvDeduciblesEmitidas.insertaDeduciblesEmitidas(dedAux);
		}
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

	public List<Deducibles> getLstDeducibles() {
		return lstDeducibles;
	}

	public void setLstDeducibles(List<Deducibles> lstDeducibles) {
		this.lstDeducibles = lstDeducibles;
	}

	public void onRowSelectAnlaAne(SelectEvent event) {
		valAsegExcAne = Double.valueOf(((ProduccionEmitidaView) event.getObject()).getTotalAsegurado());
		tasaExcAne = 0.00;
		primaExcAne = 0.00;
		factorExcAne = 100;
	}

	public void cancelaAnexo() {
		// try {
		// if (nuevaCot != null) {
		// srvCotizacion.eliminaCotizacion(nuevaCot);
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgEmiteAnexo').hide();");
		PrimeFaces.current().executeScript("PF('wDlgEmiteAnexo').hide();");
		// ExternalContext ctx =
		// FacesContext.getCurrentInstance().getExternalContext();
		// try {
		// ctx.redirect("./index.jsf");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public void cancelaAnexoAclaratorio() {
		System.out.println("NUEVACOT:" + nuevaCot.getCd_cotizacion());
		try {
			if (nuevaCot != null) {
				srvCotizacion.eliminaCotizacion(nuevaCot);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		flgIngreaAnexo = false;
	}
	
	public void cancelaAnexoDeducible() {
		
		flgIngreaAnexo = false;
	}

	public void saveAnexoAclaratorio() {
		System.out.println("INGRESO ACLARATORIO");
		String anexo, aclaratorio;
		NotasAclaratorias notaAcla = new NotasAclaratorias();
		try {
			if (strAnexoAclaratorio.isEmpty() || strAnexoAclaratorio == null) {
				anexo = "S/N";
			} else {
				anexo = strAnexoAclaratorio;
			}
		} catch (Exception e) {
			anexo = "S/N";
		}
		System.out.println("ANEXO:" + anexo);
		try {
			if (strAclaratorio.isEmpty() || strAclaratorio == null) {
				aclaratorio = "S/N";
			} else {
				aclaratorio = strAclaratorio;
			}
		} catch (Exception e) {
			aclaratorio = "S/N";
		}
		System.out.println("aclaratorio:" + aclaratorio);
		notaAcla.setDetalle_aclaratorio(aclaratorio);

		// recupero el ramo cotizacion
		datosRamoCotizacion = new RamoCotizacion();
		String tpAne = null, afectAne = null, cdRamCot = null, cdComp = null;

		System.out.println("nuevaCot:" + nuevaCot.getCd_cotizacion());
		datosRamoCotizacion = new RamoCotizacion();
		datosRamoCotizacion = srvRamoCotizacion.recuperaRamoCotizacionAnexo(nuevaCot.getCd_cotizacion(),
				nuevaCot.getCd_compania());
		datosRamoCotizacion.setFc_emision_aseguradora_date(fcEmiAseg);
		datosRamoCotizacion.setFactura_aseguradora("ACLARATORIO");
		datosRamoCotizacion.setAnexo(anexo);
		notaAcla.setCd_compania(datosRamoCotizacion.getCd_compania());
		notaAcla.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		// guarda registros
		srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);
		srvNotasAcla.insertarNotaAclaratoria(notaAcla);
		//

	}

	// -------------------ObJETO
	// COTIZACION--------------------------------------------
	public void onRowSelect(SelectEvent event) {
		// int cdobjCo, cdComp;
		// cdobjCo = ((ObjetoCotizacion)
		// event.getObject()).getCd_obj_cotizacion();
		// cdComp = ((ObjetoCotizacion) event.getObject()).getCd_compania();
		// lstSubObjetoCons = srvSubObjetoCotizacion.recuperaSubObjCot(cdobjCo,
		// cdComp);
		// FacesMessage msg = new FacesMessage("Car Selected", ((Car)
		// event.getObject()).getId());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowDeleteObj(ObjetoCotizacion obj) {
		Integer ubc, compania;
		System.out.println("INGRESOOOOO");
		System.out.println("OBJT:" + obj.getDescripcion_objeto());
		ubc = obj.getCd_ubicacion();
		compania = obj.getCd_compania();
		srvObjetoCotizacion.eliminaObjetoCotizacion(obj);
		lstObjetosIncluidos = new ArrayList<ObjetoCotizacion>();
		lstObjetosIncluidos = srvObjetoCotizacion.recuperaObjetosPorUbicacion(ubc, compania);
	}

	public void calculaPrimaObjeto() {
		System.out.println("INGRESO A CALCULAR OBJETO");
		Double ldValAseg, ldValPrima, llFactor, ldTasa;
		llFactor = datosObjetoCotizacion.getFactor_objeto();
		if (llFactor == null) {
			llFactor = 100.00;
			datosObjetoCotizacion.setFactor_objeto(llFactor);
		}
		System.out.println("llFactor:" + llFactor);
		ldValAseg = datosObjetoCotizacion.getValor_asegurador_objeto();
		if (ldValAseg == null) {
			ldValAseg = 0.00;
			datosObjetoCotizacion.setValor_asegurador_objeto(ldValAseg);
		}
		System.out.println("ldValAseg:" + ldValAseg);
		ldTasa = datosObjetoCotizacion.getTasa_objeto();
		if (ldTasa == null) {
			ldTasa = 0.00;
			datosObjetoCotizacion.setTasa_objeto(ldTasa);
		}
		System.out.println("ldTasa:" + ldTasa);
		ldValPrima = (ldValAseg * (ldTasa / llFactor)) / 365 * dias;
		System.out.println("dias:" + dias);
		System.out.println("ldValPrima:" + ldValPrima);
		datosObjetoCotizacion.setPrima_objeto(ldValPrima);
	}

	public void calculaPrimaExclusiones() {
		Double ldValAseg, ldValPrima, ldTasa;
		Integer llFactor;
		System.out.println("factorExcAne:" + factorExcAne);
		System.out.println("valAsegExcAne:" + valAsegExcAne);
		System.out.println("tasaExcAne:" + tasaExcAne);

		llFactor = factorExcAne;
		if (llFactor == null) {
			llFactor = 100;
		}
		ldValAseg = valAsegExcAne;
		if (ldValAseg == null) {
			ldValAseg = 0.0;
		}
		ldTasa = tasaExcAne;
		if (ldTasa == null) {
			ldTasa = 0.0;
		}
		primaExcAne = (ldValAseg * (ldTasa / llFactor)) / 365 * dias;
		System.out.println(
				"(" + ldValAseg + " * (" + ldTasa + " / " + llFactor + ")) / 365 *" + dias + " =" + primaExcAne);

	}

	public void calculaPrimaSubObjeto() {
		Double ldValAseg, ldValPrima, llFactor, ldTasa;
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

		ldValPrima = (ldValAseg * (ldTasa / llFactor)) / 365 * dias;
		exPrima = ldValPrima;
	}

	public void listaModelos() {
		listadoModelos = new ArrayList<Modelo>();
		listadoModelos = srvModelos.listaModelo(Integer.valueOf(codMarca));
	}

	public void agregarExtras() {
		System.out.println("item:" + exCantidad);
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		datosSubObjetoCotizacion.setCd_compania(datosUbicacion.getCd_compania());
		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("EXTRA")) {
			datosSubObjetoCotizacion.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
		}
		datosSubObjetoCotizacion.setItem_aseguradora_subobjeto(exCantidad);
		datosSubObjetoCotizacion.setDescripcion_subobjeto(exDetalle);
		datosSubObjetoCotizacion.setFc_ini_subobj_cot_date(fcAnexo);
		datosSubObjetoCotizacion.setFc_fin_subobj_cot_date(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
		datosSubObjetoCotizacion.setValor_asegurador_subobjeto(exValorSubobjeto);
		datosSubObjetoCotizacion.setFc_ini_subobj_cot(llfcDesde);
		datosSubObjetoCotizacion.setFc_fin_subobj_cot(llfcHasta);
		datosSubObjetoCotizacion.setDias_vigencia(dias);
		datosSubObjetoCotizacion.setTasa_subobjeto(exTasa);
		datosSubObjetoCotizacion.setFactor_subobjeto(exFactos);
		datosSubObjetoCotizacion.setPrima_subobjeto(exPrima);
		lstSubObjetoCot.add(datosSubObjetoCotizacion);
		exCantidad = 0;
		exDetalle = null;
		exValorSubobjeto = 0.0;
		exTasa = 0.0;
		exPrima = 0.0;
		exFactos = 100.00;
	}

	public void onRowSelectUbicacion(SelectEvent event) {
		String codUbc;
		codUbc = ((ConsultaUbicacionPolView) event.getObject()).getCd_ubicacion();
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		lstObjetosPoliza = srvConsObjPolView.consultaObjetosXUbc(codUbc);
		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("EXTRA")) {
			datosUbicacion = new Ubicacion();
			try {
				datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(Integer.parseInt(codUbc),
						Integer.parseInt(PolizaSeleccionadaParaAnexo.getCd_compania()));
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error",
						"Error al recuperar el objeto. Comun�quese con el Administrador del sistema"));
				return;
			}
		}

		if (tipoAnexoSelec.equals("EXCLUSION") && afectaPoliza.equals("UBICACION")) {
			datosUbicacion = new Ubicacion();
			try {
				datosUbicacion = srvUbicacion.recuperaUbicacionPorCodigo(Integer.parseInt(codUbc),
						Integer.parseInt(PolizaSeleccionadaParaAnexo.getCd_compania()));
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error",
						"Error al recuperar el objeto. Comun�quese con el Administrador del sistema"));
				return;
			}
			valAsegExcAne = datosUbicacion.getValor_asegurado_ubicacion() * -1;
		}

	}

	public void onRowSelectExcObj(SelectEvent event) {
		System.out.println("valAsegExcAne:" + valAsegExcAne);
		valAsegExcAne = valAsegExcAne
				+ (Double.valueOf(((ConsultaObjetoPolView) event.getObject()).getTotal_asegurado_objeto()) * -1);
		System.out.println("valAsegExcAne:" + valAsegExcAne);
	}

	public void onRowSelectObjeto(SelectEvent event) {
		String codObj;
		codObj = ((ConsultaObjetoPolView) event.getObject()).getCd_obj_cotizacion();
		lstSubobjetoPol = new ArrayList<ConsultaSubObjetoPolView>();
		lstSubobjetoPol = srvConsSubobjPolView.consultaSubObjetoxCdObj(codObj);
		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("EXTRA")) {
			datosObjetoCotizacion = new ObjetoCotizacion();
			try {
				datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(Integer.parseInt(codObj),
						Integer.parseInt(PolizaSeleccionadaParaAnexo.getCd_compania()));
			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error",
						"Error al recuperar el objeto. Comun�quese con el Administrador del sistema"));
				return;
			}

		}
	}

	public void onRowSelectExcSubObjeto(SelectEvent event) {
		Double aux;

		System.out.println("valAsegExcAne:" + valAsegExcAne);
		aux = Double.valueOf(((ConsultaSubObjetoPolView) event.getObject()).getValor_asegurador_subobjeto());
		aux = aux * -1;
		try {
			valAsegExcAne = aux + valAsegExcAne;
		} catch (Exception e) {
			valAsegExcAne = aux;
		}
		System.out.println("valAsegExcAne:" + valAsegExcAne);
	}

	public void DeleteLstExtra() {
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		exCantidad = 0;
		exDetalle = null;
		exValorSubobjeto = 0.0;
		exTasa = 0.0;
		exPrima = 0.0;
		exFactos = 100.0;
	}

	public void grabaUbicacion() {
		int res;
		System.out.println("UBICACION:" + descUbicacion);
		if (descUbicacion == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Digite la nueva Ubicaci�n y de click en el bot�n '+'"));
			return;
		}
		descUbicacion = descUbicacion.trim().toUpperCase();
		datosUbicacion = new Ubicacion();
		datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		datosUbicacion.setDsc_ubicacion(descUbicacion);
		System.out.println("UBICACION" + descUbicacion);
		res = srvUbicacion.insertarUbicacion(datosUbicacion);
		if (res == 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Objeto Asegurado"));
			datosUbicacion = new Ubicacion();
			datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
					datosRamoCotizacion.getCd_compania());
			flgHabilitaUbicacion = true;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al ingresar la Ubicaci�n Comuniquese con el Administrador del Sistema"));
			return;
		}
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

	public void onRowSelectIncObjUbc(SelectEvent event) {
		selectedUbicacionEmision = new ConsultaUbicacionPolView();
		selectedUbicacionEmision = (ConsultaUbicacionPolView) event.getObject();
	}

	public void nuevoObjetoAseg() {
		System.out.println("INGRESA NUEVO  OBJETO");
		System.out.println("tipoAnexoSelec:" + tipoAnexoSelec);
		System.out.println("afectaPoliza:" + afectaPoliza);

		int res;
		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("UBICACION")) {
			if (datosUbicacion == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
				return;
			} else {
				lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
				caracteristicaPlaca = null;
				caracteristicaRamv = null;

				// RequestContext context = RequestContext.getCurrentInstance();
				// context.execute("PF('nuevoObjeto').show();");
				PrimeFaces.current().executeScript("PF('nuevoObjeto').show();");
			}
		}

		if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("OBJETO")) {
			System.out.println("LISTADO tama�o:" + lstObjetosIncluidos.size());
			if (lstObjetosIncluidos.size() == 0) {
				datosUbicacion = new Ubicacion();
				datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
				datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
				try {
					if (selectedUbicacionEmision == null) {
						FacesContext fContextObj = FacesContext.getCurrentInstance();
						fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
						return;
					}
					datosUbicacion.setDsc_ubicacion(selectedUbicacionEmision.getDsc_ubicacion());
				} catch (Exception e) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
					return;
				}
				res = srvUbicacion.insertarUbicacion(datosUbicacion);
				if (res == 1) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Objeto Asegurado"));
					datosUbicacion = new Ubicacion();
					datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(
							datosRamoCotizacion.getCd_ramo_cotizacion(), datosRamoCotizacion.getCd_compania());

					lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
					caracteristicaPlaca = null;
					caracteristicaRamv = null;
					PrimeFaces.current().executeScript("PF('nuevoObjeto').show();");
				} else {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al ingresar la Ubicaci�n Comuniquese con el Administrador del Sistema"));
					return;
				}
			} else {
				lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
				caracteristicaPlaca = null;
				caracteristicaRamv = null;
				// RequestContext rContextObj =
				// RequestContext.getCurrentInstance();
				// rContextObj.execute("PF('nuevoObjeto').show();");
				PrimeFaces.current().executeScript("PF('nuevoObjeto').show();");
				// ingresa nuevo objeto en la ubicaci�n inicial
			}
		}
	}

	public void cargaArchivo() {
		// guarda la ubicacion
		int res;
		System.out.println("Tama�o Archivo Excel:" + lstObjetosIncluidos.size());
		if (lstObjetosIncluidos.size() == 0) {
			datosUbicacion = new Ubicacion();
			datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
			datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
			try {
				if (selectedUbicacion == null) {
					FacesContext fContextObj = FacesContext.getCurrentInstance();
					fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
					return;
				}
				datosUbicacion.setDsc_ubicacion(selectedUbicacion.getDsc_ubicacion());
			} catch (Exception e) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
				return;
			}
			res = srvUbicacion.insertarUbicacion(datosUbicacion);
			if (res == 1) {
				FacesContext fContextObj = FacesContext.getCurrentInstance();
				fContextObj.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Objeto Asegurado"));
				datosUbicacion = new Ubicacion();
				datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
						datosRamoCotizacion.getCd_compania());

				lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
				caracteristicaPlaca = null;
				caracteristicaRamv = null;
				// RequestContext context = RequestContext.getCurrentInstance();
				// context.execute("PF('wDlgCargaArchivo').show();");
				PrimeFaces.current().executeScript("PF('wDlgCargaArchivo').show();");
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar la Ubicaci�n Comuniquese con el Administrador del Sistema"));
				return;
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Elimine todos los Objetos para Subir el archivo"));
			return;
		}
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
		FacesMessage message = new FacesMessage("Succesful", evt.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
		lstObjetosIncluidos = new ArrayList<ObjetoCotizacion>();
		System.out.println("UBICACION:" + datosUbicacion.getCd_ubicacion());
		lstObjetosIncluidos = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosUbicacion.getCd_ubicacion(),
				datosUbicacion.getCd_compania());
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
		Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;

		for (SubeArchivoObj subeArchivoObj : lstSubeArchivoObj) {
			System.out.println("TIPO:" + subeArchivoObj.getTIPO());
			System.out.println("Descripcion Obj:" + subeArchivoObj.getDESCRIPCION_OBJETO());
			System.out.println("Placa:" + subeArchivoObj.getPLACA());
			System.out.println("Descripcion SubObj:" + subeArchivoObj.getDESCRIPCION_SUBOBJETO());
			System.out.println("Tipo:" + subeArchivoObj.getTIPO().equals("O"));
			if (subeArchivoObj.getTIPO().equals("O")) {
				datosObjetoCotizacion = new ObjetoCotizacion();
				datosObjetoCotizacion.setCd_compania(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_compania()));
				datosObjetoCotizacion.setFc_ini_obj_cot_date(fcAnexo);
				datosObjetoCotizacion.setFc_fin_obj_cot_date(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
				datosObjetoCotizacion.setFc_ini_obj_cot(llfcDesde);
				datosObjetoCotizacion.setFc_fin_obj_cot(llfcHasta);
				datosObjetoCotizacion.setDias_vigencia(dias);
				datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
				System.out.println("PASO:");
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
				System.out.println("PASO:1");
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
				System.out.println("PASO:2");
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
				System.out.println("PASO:3");
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
				System.out.println("PASO:4");
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
				System.out.println("PASO:5");
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
				System.out.println("PASO:6");
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
				System.out.println("PASO:7");
				try {
					strgAux = subeArchivoObj.getOBSERVACIONES_OBJETO();
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "S/N";
					}
				} catch (Exception e) {
					strgAux = "S/N";
				}
				datosObjetoCotizacion.setObservaciones_objeto(strgAux);

				System.out.println("PASO:8");
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

				System.out.println("PASO:9");
				try {
					strgAux = subeArchivoObj.getCEDULA_OBJETO();
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "S/N";
					} else {
						datosObjetoCotizacion.setCedula_obj(strgAux);
					}
				} catch (Exception e) {
					strgAux = "S/N";
					;
				}

				System.out.println("PASO:10");
				res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al importar el Archivo Comun�quese con el Administrador del Sistema"));
					return;
				}
				try {
					objaux = srvObjetoCotizacion.codigoMaxObjetoCot(datosObjetoCotizacion.getCd_ubicacion());
					System.out.println("CD_OBJ_COTIZACION:" + objaux);
				} catch (Exception e) {
					objaux = 0;
				}
				System.out.println("PASO:10.5");
				// inserta caracteristicas del objeto
				datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
				try {
					strgAux = subeArchivoObj.getPLACA();
					if (strgAux.isEmpty() || strgAux == null) {
						strgAux = "S/N";
					}
				} catch (Exception e) {
					strgAux = "S/N";
				}
				System.out.println("PASO:11");
				if (!strgAux.equals("S/N")) {
					datosCaracteristicasVehiculos
							.setCd_compania(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_compania()));
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
					System.out.println("PASO:12");
					try {
						strgAux = subeArchivoObj.getMARCA();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setMarca(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:13");
					try {
						strgAux = subeArchivoObj.getMODELO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setModelo(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:14");
					try {
						strgAux = subeArchivoObj.getCOLOR();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setColor(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:15");
					try {
						strgAux = subeArchivoObj.getNO_DE_MOTOR();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setNo_de_motor(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:16");
					try {
						strgAux = subeArchivoObj.getNO_DE_CHASIS();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setNo_de_chasis(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:17");
					try {
						strgAux = subeArchivoObj.getANIO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setAnio_de_fabricacion(Integer.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:18");
					try {
						strgAux = subeArchivoObj.getDISPOSITIVO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setDispositivo(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:19");
					try {
						strgAux = subeArchivoObj.getENDOSO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						datosCaracteristicasVehiculos.setEndoso(strgAux);
					} catch (Exception e) {
						strgAux = "S/N";
					}
					System.out.println("PASO:20");
					res = srvCaracteristicasVehiculos.insertaCaracteristicaVehiculos(datosCaracteristicasVehiculos);
					if (res == 0) {
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Advertencia",
								"Error al importar el Archivo Comun�quese con el Administrador del Sistema"));
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
					subObj.setCd_compania(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_compania()));
					subObj.setCd_obj_cotizacion(objaux);
					try {
						strgAux = subeArchivoObj.getITEM_ASEGURADORA_SUBOBJETO();
						if (strgAux == null) {
							strgAux = "0";
						}
					} catch (Exception e) {
						strgAux = "0";
					}
					subObj.setItem_aseguradora_subobjeto(Integer.valueOf(strgAux));
					System.out.println("PASO:21");
					try {
						strgAux = subeArchivoObj.getDESCRIPCION_SUBOBJETO().trim().toUpperCase();
						if (strgAux == null) {
							strgAux = "S/N";
						}
					} catch (Exception e) {
						strgAux = "S/N";
					}
					subObj.setDescripcion_subobjeto(strgAux);
					System.out.println("PASO:22");
					try {
						strgAux = subeArchivoObj.getVALOR_ASEGURADOR_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setValor_asegurador_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setValor_asegurador_subobjeto(0.00);
					}
					System.out.println("PASO:23");
					try {
						strgAux = subeArchivoObj.getTASA_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setTasa_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setTasa_subobjeto(0.00);
					}
					System.out.println("PASO:24");
					try {
						strgAux = subeArchivoObj.getFACTOR_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setFactor_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setFactor_subobjeto(100.00);
					}
					System.out.println("PASO:25");
					try {
						strgAux = subeArchivoObj.getPRIMA_SUBOBJETO();
						strgAux = strgAux.replace(",", ".");
						subObj.setPrima_subobjeto(Double.valueOf(strgAux));
					} catch (Exception e) {
						strgAux = "0";
						subObj.setPrima_subobjeto(0.00);
					}
					System.out.println("PASO:26");
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
					System.out.println("PASO:27");
					try {
						strgAux = subeArchivoObj.getPARENTESCO();
						if (strgAux == null) {
							strgAux = "S/N";
						}
						subObj.setParentesco(strgAux);
					} catch (Exception e) {
						strgAux = "0";
					}
					System.out.println("PASO:28");
					try {
						strgAux = subeArchivoObj.getCEDULA_SUBOBJETO();
						if (strgAux == null) {
							strgAux = "0";
						}
						subObj.setCedula_subobj(strgAux);
					} catch (Exception e) {
						strgAux = "0";
					}
					System.out.println("PASO:29");

					res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
					if (res == 0) {
						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Advertencia",
								"Error al importar el Archivo Comun�quese con el Administrador del Sistema"));
						return;
					}

				}

			}
		}
	}

	public void grabaObjetoAsegurado() {
		Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;
		int res = 0, objaux;

		datosObjetoCotizacion.setCd_compania(Integer.decode(PolizaSeleccionadaParaAnexo.getCd_compania()));
		datosObjetoCotizacion.setFc_ini_obj_cot_date(fcAnexo);
		datosObjetoCotizacion.setFc_fin_obj_cot_date(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
		datosObjetoCotizacion.setFc_ini_obj_cot(llfcDesde);
		datosObjetoCotizacion.setFc_fin_obj_cot(llfcHasta);
		datosObjetoCotizacion.setDias_vigencia(dias);
		datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
		primaTotaObj = datosObjetoCotizacion.getPrima_objeto();
		totalAseguradoObj = datosObjetoCotizacion.getValor_asegurador_objeto();
		res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al ingresar el Objeto Comun�quese con el Administrador del Sistema"));
			return;
		}
		res = srvObjetoCotizacion.codigoMaxObjetoCot(datosObjetoCotizacion.getCd_ubicacion());
		objaux = res;
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(res,
				Integer.decode(PolizaSeleccionadaParaAnexo.getCd_compania()));
		// GUARDA DATOS DE LOS EXTRAS EN CASO DE EXISTIR
		System.out.println("subOBJETOS INGRESADO:" + lstSubObjetoCot.size());
		System.out.println("primaTotal:" + primaTotaObj);
		for (SubObjetoCotizacion subObj : lstSubObjetoCot) {
			primaTotaObj = primaTotaObj + subObj.getPrima_subobjeto();
			totalAseguradoObj = totalAseguradoObj + subObj.getValor_asegurador_subobjeto();
			System.out.println("SUBOBJE primaTotal:" + primaTotaObj);
			subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
			subObj.setFc_ini_subobj_cot(llfcDesde);
			subObj.setFc_fin_subobj_cot(llfcHasta);
			subObj.setDias_vigencia(dias);
			res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
			if (res == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar el Objeto Comun�quese con el Administrador del Sistema"));
				return;
			}
		}
		// actualizo la prima del subobjeto con la sumatoria de las primas de
		// los extras
		datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
		datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
		srvObjetoCotizacion.actualizaObjetoCotizacion(datosObjetoCotizacion);
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorCodigo(objaux,
				Integer.decode(PolizaSeleccionadaParaAnexo.getCd_compania()));
		System.out.println("CARACTERISTICAS PLACA" + caracteristicaPlaca);
		System.out.println("CARACTERISTICAS RAM" + caracteristicaRamv);

		// GUARDO LAS CARACTERISTICAS DEL VEHICULO

		if (caracteristicaPlaca == null) {
			// no ingresa nada de caracteristicas
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"No se ingresaron Caracteristicas del Objeto Si desea Ingrearlas Elimine el Objeto Asegurado"));

		} else {
			try {
				datosCaracteristicasVehiculos.setMarca(codMarca);
				datosCaracteristicasVehiculos.setModelo(codModelo);
				System.out.println("PRINT MARCA: ---->" + codMarca);
				System.out.println("PRINT MODELO: ---->" + codModelo);
				datosCaracteristicasVehiculos.getPlaca();
				datosCaracteristicasVehiculos.setCd_compania(datosObjetoCotizacion.getCd_compania());
				datosCaracteristicasVehiculos.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
				datosCaracteristicasVehiculos.setPlaca(caracteristicaPlaca);
				datosCaracteristicasVehiculos.setRanv_cpn(caracteristicaRamv);
				res = srvCaracteristicasVehiculos.insertaCaracteristicaVehiculos(datosCaracteristicasVehiculos);
				if (res == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Advertencia",
							"Error al ingresar las caracteristicas del Objeto Comun�quese con el Administrador del Sistema"));
					return;
				}
			} catch (Exception e) {
				// TODO: No se ingreso las caracteristicas dle vehiculo
			}
		}

		// actualiza la lista de objetos ingresados
		lstObjetosIncluidos = new ArrayList<ObjetoCotizacion>();
		lstObjetosIncluidos = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosObjetoCotizacion.getCd_ubicacion(),
				datosObjetoCotizacion.getCd_compania());
		// encera valores
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosCaracteristicasVehiculos = new CaracteristicasVehiculos();
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		System.out.println("TAMA�O:" + lstObjetosIncluidos.size());
		// flgHabilitaUbicacion = false;
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('nuevoObjeto').hide();");
		PrimeFaces.current().executeScript("PF('nuevoObjeto').hide();");
	}

	public void excluirSubobjeto() {
		int res = 0;
		if (selectedUbicacion == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
			return;
		}

		if (selectedObjetosPoliza == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Asegurado"));
			return;
		}
		if (selectedLstSubobjetoPol.size() == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Sub Objeto  Asegurado / Extra"));
			return;
		}

		datosUbicacion = new Ubicacion();
		datosUbicacion.setCdUbcOri(Integer.valueOf(selectedUbicacion.getCd_ubicacion()));
		datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		datosUbicacion.setFc_fin_ubc_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosUbicacion.setFc_ini_ubc_date(fcAnexo);
		datosUbicacion.setFc_ini_ubicacion(llfcDesde);
		datosUbicacion.setFc_fin_ubicacion(llfcHasta);
		datosUbicacion.setDias_vigencia(dias);
		datosUbicacion.setDsc_ubicacion(selectedUbicacion.getDsc_ubicacion());
		datosUbicacion.setFactor_ubicacion(Double.valueOf(factorExcAne));
		datosUbicacion.setTasa_ubicacion(tasaExcAne);
		datosUbicacion.setValor_asegurado_ubicacion(valAsegExcAne);
		datosUbicacion.setValor_prima_ubicacion(primaExcAne);
		res = srvUbicacion.insertarUbicacion(datosUbicacion);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar la Ubicaci�n. Comun�quese con el Administrador del sistema"));
			return;
		}
		datosUbicacion = new Ubicacion();
		datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
				datosRamoCotizacion.getCd_compania());
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion.setCdObjOri(Integer.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()));
		datosObjetoCotizacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
		datosObjetoCotizacion.setDescripcion_objeto(selectedObjetosPoliza.getDescripcion_objeto());
		datosObjetoCotizacion.setDias_vigencia(dias);
		datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorExcAne));
		datosObjetoCotizacion.setFc_fin_obj_cot(llfcHasta);
		datosObjetoCotizacion.setFc_fin_obj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosObjetoCotizacion.setFc_ini_obj_cot(llfcDesde);
		datosObjetoCotizacion.setFc_ini_obj_cot_date(fcAnexo);
		datosObjetoCotizacion.setPrima_objeto(primaExcAne);
		datosObjetoCotizacion.setTasa_objeto(tasaExcAne);
		datosObjetoCotizacion.setValor_asegurador_objeto(valAsegExcAne);
		datosObjetoCotizacion.setTotal_asegurado_objeto(valAsegExcAne);
		res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar el Objeto Asegurado. Comun�quese con el Administrador del Sistema"));
			return;
		}
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorUbc(datosUbicacion.getCd_ubicacion(),
				datosUbicacion.getCd_compania());
		for (ConsultaSubObjetoPolView seleSubObj : selectedLstSubobjetoPol) {
			datosSubObjetoCotizacion = new SubObjetoCotizacion();
			datosSubObjetoCotizacion.setCdSubObjOri(Integer.valueOf(seleSubObj.getCd_subobj_cotizacion()));
			datosSubObjetoCotizacion.setCd_compania(datosObjetoCotizacion.getCd_compania());
			datosSubObjetoCotizacion.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
			datosSubObjetoCotizacion.setDescripcion_subobjeto(seleSubObj.getDescripcion_subobjeto());
			datosSubObjetoCotizacion.setDias_vigencia(dias);
			datosSubObjetoCotizacion.setFactor_subobjeto(Double.valueOf(factorExcAne));
			datosSubObjetoCotizacion.setFc_ini_subobj_cot_date(fcAnexo);
			datosSubObjetoCotizacion.setFc_ini_subobj_cot(llfcDesde);
			datosSubObjetoCotizacion.setFc_fin_subobj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
			datosSubObjetoCotizacion.setFc_fin_subobj_cot(llfcHasta);
			datosSubObjetoCotizacion
					.setValor_asegurador_subobjeto(Double.valueOf(seleSubObj.getValor_asegurador_subobjeto()));
			datosSubObjetoCotizacion.setTasa_subobjeto(Double.valueOf(seleSubObj.getTasa_subobjeto()));
			datosSubObjetoCotizacion.setPrima_subobjeto(Double.valueOf(seleSubObj.getPrima_subobjeto()));
			res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(datosSubObjetoCotizacion);
			if (res == 0) {
				FacesContext contextMsj = FacesContext.getCurrentInstance();
				contextMsj.addMessage(null, new FacesMessage("Advertencia",
						"Erro al Insertar el Sub - Objeto Asegurado. Comun�quese con el Administrador del Sistema"));
				return;
			}
		}
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgExclusionExtra').hide();");
		PrimeFaces.current().executeScript("PF('wDlgExclusionExtra').hide();");
	}

	public void excluirUbicacion() {
		int res = 0;
		if (selectedUbicacion == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
			return;
		}
		System.out.println("UBICACION SELECCIONADA:" + selectedUbicacion.getCd_ubicacion());
		System.out.println("RamoCotizacion SELECCIONADA:" + datosRamoCotizacion.getCd_ramo_cotizacion());
		System.out.println("VALOR ASEGURADO SELECCIONADA:" + valAsegExcAne);
		System.out.println("VALOR PRIMA SELECCIONADA:" + primaExcAne);
		System.out.println("---------------------------------------------");
		Ubicacion AuxdatosUbicacion = new Ubicacion();
		AuxdatosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		AuxdatosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		AuxdatosUbicacion.setFc_fin_ubc_date(datosRamoCotizacion.getFc_fin_vig_date());
		AuxdatosUbicacion.setFc_ini_ubc_date(fcAnexo);
		AuxdatosUbicacion.setFc_ini_ubicacion(llfcDesde);
		AuxdatosUbicacion.setFc_fin_ubicacion(llfcHasta);
		AuxdatosUbicacion.setDias_vigencia(dias);
		AuxdatosUbicacion.setDsc_ubicacion(selectedUbicacion.getDsc_ubicacion());
		AuxdatosUbicacion.setFactor_ubicacion(Double.valueOf(factorExcAne));
		AuxdatosUbicacion.setTasa_ubicacion(tasaExcAne);
		AuxdatosUbicacion.setValor_asegurado_ubicacion(valAsegExcAne);
		AuxdatosUbicacion.setValor_prima_ubicacion(primaExcAne);
		AuxdatosUbicacion.setCdUbcOri(Integer.valueOf(selectedUbicacion.getCd_ubicacion()));
		res = srvUbicacion.insertarUbicacion(AuxdatosUbicacion);
		res = srvUbicacion.codigoMaxUbc(datosRamoCotizacion.getCd_ramo_cotizacion());
		System.out.println("CD_UBICACION INSERTADA:" + res);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar la Ubicaci�n. Comun�quese con el Administrador del sistema"));
			return;
		}
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgExclusionUbc').hide();");
		PrimeFaces.current().executeScript("PF('wDlgExclusionUbc').hide();");
	}

	public void excluirObjeto() {
		// valida tasa y factur
		try {
			Double.valueOf(factorExcAne);
		} catch (Exception e) {
			factorExcAne = 100;
		}
		try {
			Double.valueOf(tasaExcAne);
		} catch (Exception e) {
			tasaExcAne = 0.00;
		}
		int res = 0;
		if (selectedUbicacion == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
			return;
		}
		if (selectedLstObjetosPoliza.size() == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Asegurado"));
			return;
		}
		selectedUbicacionEmision = new ConsultaUbicacionPolView();
		selectedUbicacionEmision = selectedUbicacion;

		datosUbicacion = new Ubicacion();
		datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosUbicacion.setCdUbcOri(Integer.valueOf(selectedUbicacion.getCd_ubicacion()));
		datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		datosUbicacion.setFc_fin_ubc_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosUbicacion.setFc_ini_ubc_date(fcAnexo);
		datosUbicacion.setFc_ini_ubicacion(llfcDesde);
		datosUbicacion.setFc_fin_ubicacion(llfcHasta);
		datosUbicacion.setDias_vigencia(dias);
		datosUbicacion.setDsc_ubicacion(selectedUbicacion.getDsc_ubicacion());
		datosUbicacion.setFactor_ubicacion(Double.valueOf(factorExcAne));
		datosUbicacion.setTasa_ubicacion(tasaExcAne);
		datosUbicacion.setValor_asegurado_ubicacion(valAsegExcAne);
		datosUbicacion.setValor_prima_ubicacion(primaExcAne);
		res = srvUbicacion.insertarUbicacion(datosUbicacion);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar la Ubicaci�n. Comun�quese con el Administrador del sistema"));
			return;
		}

		datosUbicacion = new Ubicacion();
		datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
				datosRamoCotizacion.getCd_compania());
		
		int numRegCar= 0;
		Integer codObjCar=0;
		
		for (ConsultaObjetoPolView objExc : selectedLstObjetosPoliza) {
			datosObjetoCotizacion = new ObjetoCotizacion();
			datosObjetoCotizacion.setCd_compania(datosRamoCotizacion.getCd_compania());
			datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
			datosObjetoCotizacion.setCdObjOri(Integer.parseInt(objExc.getCd_obj_cotizacion()));
			datosObjetoCotizacion.setDescripcion_objeto(objExc.getDescripcion_objeto());
			datosObjetoCotizacion.setDias_vigencia(dias);
			datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorExcAne));
			datosObjetoCotizacion.setFc_fin_obj_cot(llfcHasta);
			datosObjetoCotizacion.setFc_fin_obj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
			datosObjetoCotizacion.setFc_ini_obj_cot(llfcDesde);
			datosObjetoCotizacion.setFc_ini_obj_cot_date(fcAnexo);
			datosObjetoCotizacion.setPrima_objeto(Double.valueOf(objExc.getPrima_objeto()) * -1);
			try {
				datosObjetoCotizacion.setTasa_objeto(Double.valueOf(objExc.getTasa_objeto()) * -1);
			} catch (Exception e) {
				datosObjetoCotizacion.setTasa_objeto(0.00);
			}
			datosObjetoCotizacion.setValor_asegurador_objeto(Double.valueOf(objExc.getValor_asegurador_objeto()) * -1);
			datosObjetoCotizacion.setTotal_asegurado_objeto(Double.valueOf(objExc.getTotal_asegurado_objeto()) * -1);

			srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
			// verifico si existe caracteristicas del objeto original
			numRegCar = srvCaracteristicasVehiculos.existeCaracteristica(String.valueOf(objExc.getCd_obj_cotizacion()));
			codObjCar=0;
			
			if(numRegCar !=0 ) {
				// inserto las caracteristicas del objeto
				codObjCar = srvObjetoCotizacion.codigoMaxObjetoCot(datosUbicacion.getCd_ubicacion());
				srvCaracteristicasVehiculos.insertaCaraAneExclusion(String.valueOf(codObjCar),String.valueOf(objExc.getCd_obj_cotizacion()), String.valueOf(datosRamoCotizacion.getCd_compania()));
			}
		}

		System.out.println("hasta aqui bien");
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgExclusionObj').hide();");
		PrimeFaces.current().executeScript("PF('wDlgExclusionObj').hide();");
	}

	public void modValAsegObjeto() {
		int res = 0;
		if (selectedUbicacion == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
			return;
		}

		if (selectedObjetosPoliza == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Asegurado"));
			return;
		}
		selectedUbicacionEmision = new ConsultaUbicacionPolView();
		selectedUbicacionEmision = selectedUbicacion;
		datosUbicacion = new Ubicacion();
		datosUbicacion.setCdUbcOri(Integer.valueOf(selectedUbicacion.getCd_ubicacion()));
		datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		datosUbicacion.setFc_fin_ubc_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosUbicacion.setFc_ini_ubc_date(fcAnexo);
		datosUbicacion.setFc_ini_ubicacion(llfcDesde);
		datosUbicacion.setFc_fin_ubicacion(llfcHasta);
		datosUbicacion.setDias_vigencia(dias);
		datosUbicacion.setDsc_ubicacion(selectedUbicacion.getDsc_ubicacion());
		datosUbicacion.setFactor_ubicacion(Double.valueOf(factorExcAne));
		datosUbicacion.setTasa_ubicacion(tasaExcAne);
		datosUbicacion.setValor_asegurado_ubicacion(valAsegExcAne);
		datosUbicacion.setValor_prima_ubicacion(primaExcAne);
		res = srvUbicacion.insertarUbicacion(datosUbicacion);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar la Ubicaci�n. Comun�quese con el Administrador del sistema"));
			return;
		}
		datosUbicacion = new Ubicacion();
		datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
				datosRamoCotizacion.getCd_compania());
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion.setCdObjOri(Integer.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()));
		datosObjetoCotizacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
		datosObjetoCotizacion.setCdObjOri(Integer.parseInt(selectedObjetosPoliza.getCd_obj_cotizacion()));
		datosObjetoCotizacion.setDescripcion_objeto(selectedObjetosPoliza.getDescripcion_objeto());
		datosObjetoCotizacion.setDias_vigencia(dias);
		datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorExcAne));
		datosObjetoCotizacion.setFc_fin_obj_cot(llfcHasta);
		datosObjetoCotizacion.setFc_fin_obj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosObjetoCotizacion.setFc_ini_obj_cot(llfcDesde);
		datosObjetoCotizacion.setFc_ini_obj_cot_date(fcAnexo);
		datosObjetoCotizacion.setPrima_objeto(primaExcAne);
		datosObjetoCotizacion.setTasa_objeto(tasaExcAne);
		datosObjetoCotizacion.setValor_asegurador_objeto(valAsegExcAne);
		datosObjetoCotizacion.setTotal_asegurado_objeto(valAsegExcAne);
		srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgModValAsegObj').hide();");
		PrimeFaces.current().executeScript("PF('wDlgModValAsegObj').hide();");
	}

	public void modificaValAsegSubobjeto() {
		int res = 0;
		if (selectedUbicacion == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Ubicaci�n"));
			return;
		}

		if (selectedObjetosPoliza == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Asegurado"));
			return;
		}
		if (selectedSubobjetoPol == null) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Sub Objeto  Asegurado / Extra"));
			return;
		}
		datosUbicacion = new Ubicacion();
		datosUbicacion.setCdUbcOri(Integer.valueOf(selectedUbicacion.getCd_ubicacion()));
		datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		datosUbicacion.setFc_fin_ubc_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosUbicacion.setFc_ini_ubc_date(fcAnexo);
		datosUbicacion.setFc_ini_ubicacion(llfcDesde);
		datosUbicacion.setFc_fin_ubicacion(llfcHasta);
		datosUbicacion.setDias_vigencia(dias);
		datosUbicacion.setDsc_ubicacion(selectedUbicacion.getDsc_ubicacion());
		datosUbicacion.setFactor_ubicacion(Double.valueOf(factorExcAne));
		datosUbicacion.setTasa_ubicacion(tasaExcAne);
		datosUbicacion.setValor_asegurado_ubicacion(valAsegExcAne);
		datosUbicacion.setValor_prima_ubicacion(primaExcAne);
		res = srvUbicacion.insertarUbicacion(datosUbicacion);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar la Ubicaci�n. Comun�quese con el Administrador del sistema"));
			return;
		}
		datosUbicacion = new Ubicacion();
		datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
				datosRamoCotizacion.getCd_compania());
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion.setCdObjOri(Integer.valueOf(selectedObjetosPoliza.getCd_obj_cotizacion()));
		datosObjetoCotizacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosObjetoCotizacion.setCd_ubicacion(datosUbicacion.getCd_ubicacion());
		datosObjetoCotizacion.setDescripcion_objeto(selectedObjetosPoliza.getDescripcion_objeto());
		datosObjetoCotizacion.setDias_vigencia(dias);
		datosObjetoCotizacion.setFactor_objeto(Double.valueOf(factorExcAne));
		datosObjetoCotizacion.setFc_fin_obj_cot(llfcHasta);
		datosObjetoCotizacion.setFc_fin_obj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosObjetoCotizacion.setFc_ini_obj_cot(llfcDesde);
		datosObjetoCotizacion.setFc_ini_obj_cot_date(fcAnexo);
		datosObjetoCotizacion.setPrima_objeto(primaExcAne);
		datosObjetoCotizacion.setTasa_objeto(tasaExcAne);
		datosObjetoCotizacion.setValor_asegurador_objeto(valAsegExcAne);
		datosObjetoCotizacion.setTotal_asegurado_objeto(valAsegExcAne);
		res = srvObjetoCotizacion.insertarObjetoCotizacion(datosObjetoCotizacion);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar el Objeto Asegurado. Comun�quese con el Administrador del Sistema"));
			return;
		}
		datosObjetoCotizacion = new ObjetoCotizacion();
		datosObjetoCotizacion = srvObjetoCotizacion.recuperaObjCotPorUbc(datosUbicacion.getCd_ubicacion(),
				datosUbicacion.getCd_compania());
		datosSubObjetoCotizacion = new SubObjetoCotizacion();
		datosObjetoCotizacion.setCdObjOri(Integer.valueOf(selectedSubobjetoPol.getCd_subobj_cotizacion()));
		datosSubObjetoCotizacion.setCd_compania(datosObjetoCotizacion.getCd_compania());
		datosSubObjetoCotizacion.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
		datosSubObjetoCotizacion.setCdSubObjOri(Integer.decode(selectedSubobjetoPol.getCd_subobj_cotizacion()));
		datosSubObjetoCotizacion.setDescripcion_subobjeto(selectedSubobjetoPol.getDescripcion_subobjeto());
		datosSubObjetoCotizacion.setDias_vigencia(dias);
		datosSubObjetoCotizacion.setFactor_subobjeto(Double.valueOf(factorExcAne));
		datosSubObjetoCotizacion.setFc_ini_subobj_cot_date(fcAnexo);
		datosSubObjetoCotizacion.setFc_ini_subobj_cot(llfcDesde);
		datosSubObjetoCotizacion.setFc_fin_subobj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosSubObjetoCotizacion.setFc_fin_subobj_cot(llfcHasta);
		datosSubObjetoCotizacion.setValor_asegurador_subobjeto(valAsegExcAne);
		datosSubObjetoCotizacion.setTasa_subobjeto(tasaExcAne);
		datosSubObjetoCotizacion.setPrima_subobjeto(primaExcAne);
		res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(datosSubObjetoCotizacion);
		if (res == 0) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia",
					"Erro al Insertar el Sub - Objeto Asegurado. Comun�quese con el Administrador del Sistema"));
			return;
		}

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgModValAsegExtra').hide();");
		PrimeFaces.current().executeScript("PF('wDlgModValAsegExtra').hide();");
	}

	public void anulaCancelaPol() {
		datosRamoCotizacion.setTotal_asegurado(valAsegExcAne);
		datosRamoCotizacion.setTotal_prima(primaExcAne);
		datosRamoCotizacion.setFactor(Double.valueOf(factorExcAne));
		datosRamoCotizacion.setTasa(tasaExcAne);
		srvRamoCotizacion.actualizaRamoCotizacion(datosRamoCotizacion);

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgAnuCanPol').hide();");
		PrimeFaces.current().executeScript("PF('wDlgAnuCanPol').hide();");
	}

	// ------------------- EXTRAS -----------------------------
	// --------------------------------------------------------
	public void agregarSubobjeto() {
		// verifica que se haya seleccionado un objeto
		try {
			if (selectedObjetosPoliza == null) {
				FacesContext contextMsj = FacesContext.getCurrentInstance();
				contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Asegurado"));
				return;
			}
		} catch (Exception e) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null, new FacesMessage("Advertencia", "Seleccione el Objeto Asegurado"));
			return;
		}
		exCantidad = null;
		exDetalle = null;
		exValorSubobjeto = null;
		exTasa = null;
		exFactos = null;
		exPrima = null;
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('nuevoSubObjeto').show();");
		PrimeFaces.current().executeScript("PF('nuevoSubObjeto').show();");
	}

	public void aceptarSubObjAseg() {
		exCantidad = null;
		exDetalle = null;
		exValorSubobjeto = null;
		exTasa = null;
		exFactos = null;
		exPrima = null;
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('nuevoSubObjeto').hide();");
		PrimeFaces.current().executeScript("PF('nuevoSubObjeto').hide();");

	}

	public void grabaSubObjetoAsegurado() {
		Double primaTotaObj = 0.0, totalAseguradoObj = 0.0;
		int res = 0, objaux;

		// inserto la ubicaci�n
		datosUbicacion.setCd_compania(datosRamoCotizacion.getCd_compania());
		datosUbicacion.setCd_ramo_cotizacion(datosRamoCotizacion.getCd_ramo_cotizacion());
		res = srvUbicacion.insertaUbicacionAnexo(datosRamoCotizacion.getCd_ramo_cotizacion(),
				datosRamoCotizacion.getCd_compania(), datosUbicacion.getDsc_ubicacion());
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al ingresar la Ubicaci�n Comuniquese con el Administrador del Sistema"));
			return;
		} else {
			Ubicacion ubcAux = new Ubicacion();
			ubcAux = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
					datosRamoCotizacion.getCd_compania());
			ubcAux.setDias_vigencia(datosUbicacion.getDias_vigencia());
			ubcAux.setFc_fin_ubc_date(datosRamoCotizacion.getFc_fin_vig_date());
			ubcAux.setFc_ini_ubc_date(fcAnexo);
			ubcAux.setFc_fin_ubicacion(llfcHasta);
			ubcAux.setFc_ini_ubicacion(llfcDesde);
			ubcAux.setDias_vigencia(dias);
			srvUbicacion.actualizaUbicacion(ubcAux);
		}
		datosUbicacion = new Ubicacion();
		datosUbicacion = srvUbicacion.recuperaUbicacionAnexoxCrc(datosRamoCotizacion.getCd_ramo_cotizacion(),
				datosRamoCotizacion.getCd_compania());
		// inserto el Objeto
		res = srvObjetoCotizacion.insertaObjetoAnexo(datosUbicacion.getCd_ubicacion(), datosUbicacion.getCd_compania(),
				datosObjetoCotizacion.getDescripcion_objeto(), datosObjetoCotizacion.getCd_obj_cotizacion());
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al ingresar el Objeto Comuniquese con el Administrador del Sistema"));
			return;
		} else {
			ObjetoCotizacion objAux = new ObjetoCotizacion();
			objAux = srvObjetoCotizacion.recuperaObjCotPorUbc(datosUbicacion.getCd_ubicacion(),
					datosUbicacion.getCd_compania());
			datosObjetoCotizacion = new ObjetoCotizacion();
			datosObjetoCotizacion = objAux;
		}
		datosObjetoCotizacion.setFc_ini_obj_cot_date(fcAnexo);
		datosObjetoCotizacion.setFc_fin_obj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
		datosObjetoCotizacion.setFc_ini_obj_cot(llfcDesde);
		datosObjetoCotizacion.setFc_fin_obj_cot(llfcHasta);
		datosObjetoCotizacion.setDias_vigencia(dias);
		res = srvObjetoCotizacion.actualizaObjetoCotizacion(datosObjetoCotizacion);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Error al ingresar el Objeto Comun�quese con el Administrador del Sistema"));
			return;
		}
		// GUARDA DATOS DE LOS EXTRAS
		for (SubObjetoCotizacion subObj : lstSubObjetoCot) {
			primaTotaObj = primaTotaObj + subObj.getPrima_subobjeto();
			totalAseguradoObj = totalAseguradoObj + subObj.getValor_asegurador_subobjeto();
			subObj.setCd_obj_cotizacion(datosObjetoCotizacion.getCd_obj_cotizacion());
			subObj.setFc_ini_subobj_cot_date(fcAnexo);
			subObj.setFc_fin_subobj_cot_date(datosRamoCotizacion.getFc_fin_vig_date());
			subObj.setFc_ini_subobj_cot(llfcDesde);
			subObj.setFc_fin_subobj_cot(llfcHasta);
			subObj.setDias_vigencia(dias);
			res = srvSubObjetoCotizacion.insertarSubObjetoCotizacion(subObj);
			if (res == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia",
						"Error al ingresar el Objeto Comun�quese con el Administrador del Sistema"));
				return;
			}
		}
		// actualizo la prima de los Objetos
		datosObjetoCotizacion.setPrima_objeto(primaTotaObj);
		datosObjetoCotizacion.setValor_asegurador_objeto(0.0);
		datosObjetoCotizacion.setTotal_asegurado_objeto(totalAseguradoObj);
		srvObjetoCotizacion.actualizaObjetoCotizacion(datosObjetoCotizacion);
		// actualizo la prima de la ubicaci�n
		datosUbicacion.setValor_prima_ubicacion(primaTotaObj);
		datosUbicacion.setValor_asegurado_ubicacion(totalAseguradoObj);
		srvUbicacion.actualizaUbicacion(datosUbicacion);
		// actualizo la prima en el ramo Cotizacion

		// encera valores
		lstSubObjetoCot = new ArrayList<SubObjetoCotizacion>();
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Exitoso"));
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('wDlgInclusionSubObj').hide();");
		PrimeFaces.current().executeScript("PF('wDlgInclusionSubObj').hide();");
	}

	// --------------------- FORMA DE PAGO ------------------------
	// ---------------------------------------------------------------
	public void onRowDeleteFrmPag(FormaPago frm) {
		srvFormaPago.eliminaFormaPago(frm);
		lstFrmPago = new ArrayList<FormaPago>();
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
	}

	public void agregaFormaPago() {
		System.out.println("INGRESA A FORMA PAGO ANEXOS");
		int res = 0;
		Double primaObjTot = 0.0, totalValorAsegObj = 0.0, primaObjUbc = 0.0, totalValorAsegUbc = 0.0;
		lstFrmPago = new ArrayList<FormaPago>();
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		System.out.println("FORMAPAGO-flgBotonoEmite:" + flgBotonoEmite);
		frmObservaciones = "SIN OBSERVACIONES";
		if (flgBotonoEmite == true) {
			// anexos pendientes
			FormaPago frmPagoAux = new FormaPago();
			System.out.println("lstAnexoPendiente:" + lstAnexoPendiente.size());
			// recupera cotizacion anexo
			for (ConsultaAnexoPendienteView anex : lstAnexoPendiente) {
				res = srvFormaPago.verificaFormaPago(Integer.valueOf(anex.getCd_cotizacion()),
						Integer.valueOf(anex.getCd_compania()));
				System.out.println("RES:" + res);
			}

			if (res == 0) {
				List<ConsultaObjetoAneView> lstObjetoAnePendAux;
				Double valAsegPen = 0.00;
				primaObjTot = 0.00;
				for (ConsultaUbicacionAneView ubcPend : lstUbicacionAnePend) {
					lstObjetoAnePendAux = new ArrayList<ConsultaObjetoAneView>();
					lstObjetoAnePendAux = srvConsultaObjAnePend.consultaObjetosXUbc(ubcPend.getCd_ubicacion());
					valAsegPen = 0.00;
					primaObjTot = 0.00;
					for (ConsultaObjetoAneView objPenAux : lstObjetoAnePendAux) {
						valAsegPen = valAsegPen + Double.valueOf(objPenAux.getTotal_asegurado_objeto());
						primaObjTot = primaObjTot + Double.valueOf(objPenAux.getPrima_objeto());
					}
					// actualizo la ubicaci�n
					Ubicacion ubPenAux = new Ubicacion();
					ubPenAux = srvUbicacion.recuperaUbicacionPorCodigo(Integer.valueOf(ubcPend.getCd_ubicacion()),
							Integer.valueOf(ubcPend.getCd_compania()));
					ubPenAux.setValor_asegurado_ubicacion(valAsegPen);
					ubPenAux.setValor_prima_ubicacion(primaObjTot);
					srvUbicacion.actualizaUbicacion(ubPenAux);
				}
				// actualizo ramo
				valAsegPen = 0.00;
				primaObjTot = 0.00;
				List<Ubicacion> lstUbcPenAux;
				for (ConsultaAnexoPendienteView anex : lstAnexoPendiente) {
					lstUbcPenAux = new ArrayList<Ubicacion>();
					lstUbcPenAux = srvUbicacion.listarUbicaciones(Integer.valueOf(anex.getCd_ramo_cotizacion()));
					for (Ubicacion ubicacionAux : lstUbcPenAux) {
						valAsegPen = valAsegPen + ubicacionAux.getValor_asegurado_ubicacion();
						primaObjTot = primaObjTot + ubicacionAux.getValor_prima_ubicacion();
					}
					RamoCotizacion ramCot = new RamoCotizacion();
					ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
							Integer.valueOf(anex.getCd_ramo_cotizacion()), Integer.valueOf(anex.getCd_compania()));
					ramCot.setTotal_prima(primaObjTot);
					ramCot.setTotal_asegurado(valAsegPen);
					srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
				}

				for (ConsultaAnexoPendienteView anx : lstAnexoPendiente) {
					RamoCotizacion ramCot = new RamoCotizacion();
					ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
							Integer.valueOf(anx.getCd_ramo_cotizacion()), Integer.valueOf(anx.getCd_compania()));
					try {
						primaObjTot = ramCot.getTotal_prima();
					} catch (Exception e) {
						primaObjTot = 0.00;
					}

				}

				PrimeFaces.current().executeScript("PF('nuevaFormaPago').show();");
				// encero Variables
				tpFromaPago = null;
				frmPagoPrimaTot = 0.0;

				frmPagoOtroValor = 0.0;
				frmPagoIva = 0.0;
				frmPagoTotal = 0.0;
				frmPagoCuotaIni = 0.0;
				frmPagoNumPago = 1;
				frmPagoPrimaTot = primaObjTot;
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
				calculaPagoTotal();
			} else {
				System.out.println("YA EXISTE FORMA PAGO");
				System.out.println("INGRESOOOO FRM PAGO:" + nuevaCot.getCd_cotizacion());
				FormaPago frmA = new FormaPago();
				for (ConsultaAnexoPendienteView anx : lstAnexoPendiente) {
					frmA = srvFormaPago.recuperaFormaPagoxCdCot(Integer.valueOf(anx.getCd_cotizacion()),
							Integer.valueOf(anx.getCd_compania()));
				}
				lstFrmPago.add(frmA);
				lstDetFrmPago = new ArrayList<DetalleFormaPago>();
				lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(frmA.getCd_forma_Pago(),
						frmA.getCd_compania());
			}
		} else {
			if (PolizaSeleccionadaParaAnexo == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Advertencia", "Ingrese el anexo antes de agregar la forma de pago"));
				return;
			} else {
				res = srvFormaPago.verificaFormaPago(nuevaCot.getCd_cotizacion(), nuevaCot.getCd_compania());
				if (res == 0) {
					//////////////////////////////////////////
					//
					// ACTUALIZA LOS C�LCULO PARA EL ANEXO //
					//
					/////////////////////////////////////////
					// GUARDA DATOS DE LOS EXTRAS EN CASO DE EXISTIR
					////////////////////////////////////////
					if (tipoAnexoSelec.equals("INCLUSION") && afectaPoliza.equals("EXTRA")) {
						// actualizo el Ramo Cotizaci�n
						primaObjTot = datosUbicacion.getValor_prima_ubicacion();
						totalValorAsegObj = datosUbicacion.getValor_asegurado_ubicacion();
						RamoCotizacion ramCot = new RamoCotizacion();
						ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
								datosUbicacion.getCd_ramo_cotizacion(), datosUbicacion.getCd_compania());
						ramCot.setTotal_prima(primaObjTot);
						ramCot.setTotal_asegurado(totalValorAsegObj);
						srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
					}
					if (tipoAnexoSelec.equals("EXCLUSION") && afectaPoliza.equals("UBICACION")) {
						// actualizo el Ramo Cotizaci�n
						primaObjTot = datosUbicacion.getValor_prima_ubicacion();
						totalValorAsegObj = datosUbicacion.getValor_asegurado_ubicacion();
						RamoCotizacion ramCot = new RamoCotizacion();
						ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
								datosUbicacion.getCd_ramo_cotizacion(), datosUbicacion.getCd_compania());
						ramCot.setTotal_prima(primaObjTot);
						ramCot.setTotal_asegurado(totalValorAsegObj);
						srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
					}
					if (tipoAnexoSelec.equals("EXCLUSION") && afectaPoliza.equals("OBJETO")) {
						// actualizo el Ramo Cotizaci�n
						primaObjTot = datosUbicacion.getValor_prima_ubicacion();
						totalValorAsegObj = datosUbicacion.getValor_asegurado_ubicacion();
						RamoCotizacion ramCot = new RamoCotizacion();
						ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
								datosUbicacion.getCd_ramo_cotizacion(), datosUbicacion.getCd_compania());
						ramCot.setTotal_prima(primaObjTot);
						ramCot.setTotal_asegurado(totalValorAsegObj);
						srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
					}
					if (tipoAnexoSelec.equals("EXCLUSION") && afectaPoliza.equals("EXTRA")) {
						// actualizo el Ramo Cotizaci�n
						primaObjTot = datosUbicacion.getValor_prima_ubicacion();
						totalValorAsegObj = datosUbicacion.getValor_asegurado_ubicacion();
						RamoCotizacion ramCot = new RamoCotizacion();
						ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
								datosUbicacion.getCd_ramo_cotizacion(), datosUbicacion.getCd_compania());
						ramCot.setTotal_prima(primaObjTot);
						ramCot.setTotal_asegurado(totalValorAsegObj);
						srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
					}
					if (tipoAnexoSelec.equals("MODVALASEG") && afectaPoliza.equals("OBJETO")) {
						// actualizo el Ramo Cotizaci�n
						primaObjTot = datosUbicacion.getValor_prima_ubicacion();
						totalValorAsegObj = datosUbicacion.getValor_asegurado_ubicacion();
						RamoCotizacion ramCot = new RamoCotizacion();
						ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
								datosUbicacion.getCd_ramo_cotizacion(), datosUbicacion.getCd_compania());
						ramCot.setTotal_prima(primaObjTot);
						ramCot.setTotal_asegurado(totalValorAsegObj);
						srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
					}
					if (tipoAnexoSelec.equals("MODVALASEG") && afectaPoliza.equals("EXTRA")) {
						// actualizo el Ramo Cotizaci�n
						primaObjTot = datosUbicacion.getValor_prima_ubicacion();
						totalValorAsegObj = datosUbicacion.getValor_asegurado_ubicacion();
						RamoCotizacion ramCot = new RamoCotizacion();
						ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
								datosUbicacion.getCd_ramo_cotizacion(), datosUbicacion.getCd_compania());
						ramCot.setTotal_prima(primaObjTot);
						ramCot.setTotal_asegurado(totalValorAsegObj);
						srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
					}
					if (tipoAnexoSelec.equals("ANULACION") || tipoAnexoSelec.equals("CANCELACION")) {
						primaObjTot = datosRamoCotizacion.getTotal_prima();
					} else {
						for (ObjetoCotizacion objCot : lstObjetosIncluidos) {
							System.out.println("VALOR: " + primaObjTot);
							primaObjTot = primaObjTot + objCot.getPrima_objeto();
							totalValorAsegObj = totalValorAsegObj + objCot.getTotal_asegurado_objeto();
							System.out.println("VALOR: " + primaObjTot);
						}
						datosUbicacion.setFc_ini_ubc_date(fcAnexo);
						datosUbicacion.setFc_ini_ubicacion(llfcDesde);
						datosUbicacion.setFc_fin_ubc_date(PolizaSeleccionadaParaAnexo.getFc_fin_vig_date());
						datosUbicacion.setDias_vigencia(dias);
						datosUbicacion.setFc_fin_ubicacion(llfcHasta);
						datosUbicacion.setValor_prima_ubicacion(primaObjTot);
						datosUbicacion.setValor_asegurado_ubicacion(totalValorAsegObj);
						srvUbicacion.actualizaUbicacion(datosUbicacion);
						// actualizo el Ramo Cotizaci�n
						RamoCotizacion ramCot = new RamoCotizacion();
						ramCot = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(
								datosUbicacion.getCd_ramo_cotizacion(), datosUbicacion.getCd_compania());
						ramCot.setTotal_prima(primaObjTot);
						ramCot.setTotal_asegurado(totalValorAsegObj);
						srvRamoCotizacion.actualizaRamoCotizacion(ramCot);
					}

					// RequestContext context =
					// RequestContext.getCurrentInstance();
					// context.execute("PF('nuevaFormaPago').show();");
					PrimeFaces.current().executeScript("PF('nuevaFormaPago').show();");
					// encero Variables
					tpFromaPago = null;
					frmPagoPrimaTot = 0.0;

					frmPagoOtroValor = 0.0;
					frmPagoIva = 0.0;
					frmPagoTotal = 0.0;
					frmPagoCuotaIni = 0.0;
					frmPagoNumPago = 1;
					frmPagoPrimaTot = primaObjTot;
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
					calculaPagoTotal();
				} else {
					System.out.println("INGRESOOOO FRM PAGO:" + nuevaCot.getCd_cotizacion());
					FormaPago frmA = new FormaPago();
					frmA = srvFormaPago.recuperaFormaPagoxCdCot(nuevaCot.getCd_cotizacion(), nuevaCot.getCd_compania());
					lstFrmPago.add(frmA);
					lstDetFrmPago = new ArrayList<DetalleFormaPago>();
					lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(frmA.getCd_forma_Pago(),
							frmA.getCd_compania());
				}
			}
		}
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
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
		try {
			if(frmObservaciones.isEmpty() || frmObservaciones == null) {
				frmObservaciones = "Sin Observaciones.";
			}
		} catch (Exception e) {
			frmObservaciones = "Sin Observaciones.";
		}
		FormaPago formaPagoAux = new FormaPago();
		if (tpFromaPago.equals("PROVISIONAL")) {
			formaPagoAux = new FormaPago();
			if (flgBotonoEmite == true) {
				for (ConsultaAnexoPendienteView anex : lstAnexoPendiente) {
					formaPagoAux.setCd_cotizacion(Integer.valueOf(anex.getCd_cotizacion()));
					formaPagoAux.setCd_compania(Integer.valueOf(anex.getCd_compania()));
				}
			} else {
				formaPagoAux.setCd_cotizacion(nuevaCot.getCd_cotizacion());
				formaPagoAux.setCd_compania(nuevaCot.getCd_compania());
			}
			formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
			formaPagoAux.setSuperBanco_forma_Pago(0.0);
			formaPagoAux.setDerecho_Emision_formaPago(0.0);
			formaPagoAux.setSeguroCampesion_forma_Pago(0.0);
			formaPagoAux.setOtro_valor_forma_Pago(0.0);
			formaPagoAux.setIva_Forma_Pago(frmPagoIva);
			formaPagoAux.setTotal_Pago_FormaPago(0.0);
			formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
			formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
			formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
			formaPagoAux.setObservaciones(frmObservaciones);
			if (aplicaIva) {
				formaPagoAux.setSin_iva(0);
			} else {
				formaPagoAux.setSin_iva(1);
			}
		}else {
			calculaPagoTotal();
			formaPagoAux = new FormaPago();
			System.out.println("flgBotonoEmite:"+flgBotonoEmite);
			if (flgBotonoEmite == true) {
				for (ConsultaAnexoPendienteView anex : lstAnexoPendiente) {
					formaPagoAux.setCd_cotizacion(Integer.valueOf(anex.getCd_cotizacion()));
					formaPagoAux.setCd_compania(Integer.valueOf(anex.getCd_compania()));
				}
			} else {
				formaPagoAux.setCd_cotizacion(nuevaCot.getCd_cotizacion());
				formaPagoAux.setCd_compania(nuevaCot.getCd_compania());
			}
			
			
			formaPagoAux.setTotal_prima_frmPago(frmPagoPrimaTot);
			System.out.println("frmPagoPrimaTot:"+frmPagoPrimaTot);
			
			formaPagoAux.setSuperBanco_forma_Pago(frmPagoSuperBancos);
			System.out.println("frmPagoSuperBancos"+frmPagoSuperBancos);
			
			formaPagoAux.setDerecho_Emision_formaPago(frmPagoDerechoEmision);
			System.out.println("frmPagoDerechoEmision"+frmPagoDerechoEmision);
			
			formaPagoAux.setSeguroCampesion_forma_Pago(frmPagoSegCampesino);
			System.out.println("frmPagoSegCampesino"+frmPagoSegCampesino);
			
			formaPagoAux.setOtro_valor_forma_Pago(frmPagoOtroValor);
			System.out.println("frmPagoOtroValor"+frmPagoOtroValor);
			
			formaPagoAux.setIva_Forma_Pago(frmPagoIva);
			System.out.println("frmPagoIva"+frmPagoIva);
			
			formaPagoAux.setTotal_Pago_FormaPago(frmPagoTotal);
			System.out.println("frmPagoTotal"+frmPagoTotal);
			
			formaPagoAux.setNum_alternativa_formaPago(tpFromaPago);
			System.out.println("tpFromaPago"+tpFromaPago);
			
			formaPagoAux.setPct_cuota_Inicial_frmPago(frmPagoCuotaIni);
			System.out.println("frmPagoCuotaIni"+frmPagoCuotaIni);
			
			formaPagoAux.setNum_pago_formaPago(frmPagoNumPago);
			System.out.println("frmPagoNumPago"+frmPagoNumPago);
			
			formaPagoAux.setObservaciones(frmObservaciones);
			System.out.println("frmObservaciones"+frmObservaciones);
			if (aplicaIva) {
				formaPagoAux.setSin_iva(0);
			} else {
				formaPagoAux.setSin_iva(1);
			}
			System.out.println("aplicaIva"+aplicaIva);
		}
		int res = 0;
		res = srvFormaPago.insertaFormaPago(formaPagoAux);
		if (res == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Error al Grabar la forma de Pago."));
			return;
		}
		System.out.println("formaPagoAux.getCd_cotizacion():"+formaPagoAux.getCd_cotizacion());
		res = srvFormaPago.codigoMaxFormaPagoAnexo(formaPagoAux.getCd_cotizacion());
		System.out.println("CODIGO_FRMPAGO:"+res);		
		
		formaPagoAux = new FormaPago();
		try {
			if (flgBotonoEmite == true) {
				System.out.println("INGRESOOOOO");
				for (ConsultaAnexoPendienteView anex : lstAnexoPendiente) {
					formaPagoAux = srvFormaPago.recuperaFormaPagoxCod(res, 1);
				}
			} else {
				System.out.println("INGRESOOOOO 2");
				System.out.println("Recupera forma de pago");
				formaPagoAux = srvFormaPago.recuperaFormaPagoxCod(res, 1);
			}
			if(formaPagoAux == null) {
				PrimeFaces.current().executeScript("PF('nuevaFormaPago').hide();");
				System.out.println("Es Nulo");
				return;
			}
			
		} catch (Exception e) {
			PrimeFaces.current().executeScript("PF('nuevaFormaPago').hide();");
			System.out.println("ERROR Es Nulo");
			return;
		}
		
		tpFromaPago = null;
		frmPagoPrimaTot = 0.0;
		frmPagoOtroValor = 0.0;
		frmPagoIva = 0.0;
		frmPagoTotal = 0.0;
		frmPagoCuotaIni = 0.0;
		frmPagoNumPago = 1;
		
		lstFrmPago = new ArrayList<FormaPago>();
		lstFrmPago.add(formaPagoAux);
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(formaPagoAux.getCd_forma_Pago(),
				formaPagoAux.getCd_compania());
		lstDetFrmPago = new ArrayList<DetalleFormaPago>();
		lstDetFrmPago = srvDetalleFrmPago.recuperaDetalleCodFormaPago(formaPagoAux.getCd_forma_Pago(),
				formaPagoAux.getCd_compania());
		PrimeFaces.current().executeScript("PF('nuevaFormaPago').hide();");

	}

	public void recuperaContactosCarta() {
		lstContactoCarta = new ArrayList<Contacto>();
		if (PolizaSeleccionadaParaAnexo == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione el Siniestro antes de generar la carta"));
			return;
		} else {
			objCarta = new Rubros();
			objCarta = srvRubrosCartas.recuperaCartaPorNombre(nmCarta);
			if (objCarta.getTipo_rubro().equals("ASEG")) {
				lstContactoCarta = srvContactosCarta
						.listaContactosAseguradora(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_aseguradora()));
			} else {
				lstContactoCarta = srvContactosCarta
						.listaContactosCliente(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_cliente()));
			}
		}
	}

	public void tpFormaPagoSel() {
		System.out.println("tpFromaPago:" + tpFromaPago);
		if (tpFromaPago.equals("PROVISIONAL")) {
			frmPagoDerechoEmision = 0.0;
			frmPagoSuperBancos = 0.0;
			frmPagoSegCampesino = 0.0;
			frmPagoOtroValor = 0.0;
			frmPagoIva = 0.0;
			frmPagoTotal = 0.0;
		}

	}

	public void guardaCarta() {
		try {
			if (nuevaCot.getCd_compania() == null) {
				FacesContext contextMsj = FacesContext.getCurrentInstance();
				contextMsj.addMessage(null,
						new FacesMessage("Advertencia", "Ingrese el Anexo para Poder Generar el Documento"));
				return;
			}
		} catch (Exception e) {
			FacesContext contextMsj = FacesContext.getCurrentInstance();
			contextMsj.addMessage(null,
					new FacesMessage("Advertencia", "Ingrese el Anexo para Poder Generar el Documento"));
			return;
		}
		Correspondencia carta = new Correspondencia();

		if (nmCarta.equals("ANEXORESUMENCONDICIONESPARTICULARES")) {
			// verifico el tipo de anexo
			if (!tipoAnexoSelec.equals("INCLUSION")) {
				FacesContext contextMsj = FacesContext.getCurrentInstance();
				contextMsj.addMessage(null,
						new FacesMessage("Advertencia", "Resumen no disponible para este tipo de Anexo"));
				return;
			} else {
				System.out.println("P�liza Seleccionada:" + PolizaSeleccionadaParaAnexo.getCd_cotizacion());
				carta.setCd_cot_ane(Integer.valueOf(PolizaSeleccionadaParaAnexo.getCd_cotizacion()));
			}
		}

		String usuarioId, nmUsr, numeroCarta;
		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		System.out.println("GUARDAR CARTA USuario:" + usuarioId);
		Usuarios usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(usuarioId);
		nmUsr = usr.getUsrnombres();
		nmUsr = nmUsr.concat(" ");
		nmUsr = nmUsr.concat(usr.getUsrapellidos());
		carta.setCdCotizacion(nuevaCot.getCd_cotizacion());
		carta.setCd_compania(nuevaCot.getCd_compania());
		carta.setCdAseguradora(nuevaCot.getCd_aseguradora());
		carta.setCdCliente(nuevaCot.getCd_cliente());
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

	public String getCdRamCotPolizaAnexo() {
		return cdRamCotPolizaAnexo;
	}

	public void setCdRamCotPolizaAnexo(String cdRamCotPolizaAnexo) {
		this.cdRamCotPolizaAnexo = cdRamCotPolizaAnexo;
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

	public String getTipoAnexoSelec() {
		return tipoAnexoSelec;
	}

	public void setTipoAnexoSelec(String tipoAnexoSelec) {
		this.tipoAnexoSelec = tipoAnexoSelec;
	}

	public String getAfectaPoliza() {
		return afectaPoliza;
	}

	public void setAfectaPoliza(String afectaPoliza) {
		this.afectaPoliza = afectaPoliza;
	}

	public String getDescUbicacion() {
		return descUbicacion;
	}

	public void setDescUbicacion(String descUbicacion) {
		this.descUbicacion = descUbicacion;
	}

	public List<FormaPago> getLstFrmPago() {
		return lstFrmPago;
	}

	public void setLstFrmPago(List<FormaPago> lstFrmPago) {
		this.lstFrmPago = lstFrmPago;
	}

	public List<DetalleFormaPago> getLstDetFrmPago() {
		return lstDetFrmPago;
	}

	public void setLstDetFrmPago(List<DetalleFormaPago> lstDetFrmPago) {
		this.lstDetFrmPago = lstDetFrmPago;
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

	public Double getFrmPagoOtroValor() {
		return frmPagoOtroValor;
	}

	public void setFrmPagoOtroValor(Double frmPagoOtroValor) {
		this.frmPagoOtroValor = frmPagoOtroValor;
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

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
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

	public ObjetoCotizacion getDatosObjetoCotizacion() {
		return datosObjetoCotizacion;
	}

	public void setDatosObjetoCotizacion(ObjetoCotizacion datosObjetoCotizacion) {
		this.datosObjetoCotizacion = datosObjetoCotizacion;
	}

	public CaracteristicasVehiculos getDatosCaracteristicasVehiculos() {
		return datosCaracteristicasVehiculos;
	}

	public void setDatosCaracteristicasVehiculos(CaracteristicasVehiculos datosCaracteristicasVehiculos) {
		this.datosCaracteristicasVehiculos = datosCaracteristicasVehiculos;
	}

	public List<Modelo> getListadoModelos() {
		return listadoModelos;
	}

	public void setListadoModelos(List<Modelo> listadoModelos) {
		this.listadoModelos = listadoModelos;
	}

	public List<Marca> getListadoMarcas() {
		return listadoMarcas;
	}

	public void setListadoMarcas(List<Marca> listadoMarcas) {
		this.listadoMarcas = listadoMarcas;
	}

	public List<Dispositivos> getListaDispositivos() {
		return listaDispositivos;
	}

	public void setListaDispositivos(List<Dispositivos> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}

	public Integer getExCantidad() {
		return exCantidad;
	}

	public void setExCantidad(Integer exCantidad) {
		this.exCantidad = exCantidad;
	}

	public String getExDetalle() {
		return exDetalle;
	}

	public void setExDetalle(String exDetalle) {
		this.exDetalle = exDetalle;
	}

	public Double getExValorSubobjeto() {
		return exValorSubobjeto;
	}

	public void setExValorSubobjeto(Double exValorSubobjeto) {
		this.exValorSubobjeto = exValorSubobjeto;
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

	public List<SubObjetoCotizacion> getLstSubObjetoCot() {
		return lstSubObjetoCot;
	}

	public void setLstSubObjetoCot(List<SubObjetoCotizacion> lstSubObjetoCot) {
		this.lstSubObjetoCot = lstSubObjetoCot;
	}

	public List<ObjetoCotizacion> getLstObjetosIncluidos() {
		return lstObjetosIncluidos;
	}

	public void setLstObjetosIncluidos(List<ObjetoCotizacion> lstObjetosIncluidos) {
		this.lstObjetosIncluidos = lstObjetosIncluidos;
	}

	public ObjetoCotizacion getSelectedDatosObjetoCotizacion() {
		return selectedDatosObjetoCotizacion;
	}

	public void setSelectedDatosObjetoCotizacion(ObjetoCotizacion selectedDatosObjetoCotizacion) {
		this.selectedDatosObjetoCotizacion = selectedDatosObjetoCotizacion;
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

	public List<ConsultaSubObjetoPolView> getLstSubobjetoPol() {
		return lstSubobjetoPol;
	}

	public void setLstSubobjetoPol(List<ConsultaSubObjetoPolView> lstSubobjetoPol) {
		this.lstSubobjetoPol = lstSubobjetoPol;
	}

	public ConsultaSubObjetoPolView getSelectedSubobjetoPol() {
		return selectedSubobjetoPol;
	}

	public void setSelectedSubobjetoPol(ConsultaSubObjetoPolView selectedSubobjetoPol) {
		this.selectedSubobjetoPol = selectedSubobjetoPol;
	}

	public Boolean getFlgHabilitaUbicacion() {
		return flgHabilitaUbicacion;
	}

	public void setFlgHabilitaUbicacion(Boolean flgHabilitaUbicacion) {
		this.flgHabilitaUbicacion = flgHabilitaUbicacion;
	}

	public Boolean getFlgHabilitaUbicacionDataTable() {
		return flgHabilitaUbicacionDataTable;
	}

	public void setFlgHabilitaUbicacionDataTable(Boolean flgHabilitaUbicacionDataTable) {
		this.flgHabilitaUbicacionDataTable = flgHabilitaUbicacionDataTable;
	}

	public String getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
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

	public String getCaracteristicaRamv() {
		return caracteristicaRamv;
	}

	public void setCaracteristicaRamv(String caracteristicaRamv) {
		this.caracteristicaRamv = caracteristicaRamv;
	}

	public String getCaracteristicaPlaca() {
		return caracteristicaPlaca;
	}

	public void setCaracteristicaPlaca(String caracteristicaPlaca) {
		this.caracteristicaPlaca = caracteristicaPlaca;
	}

	public Double getValAsegExcAne() {
		return valAsegExcAne;
	}

	public void setValAsegExcAne(Double valAsegExcAne) {
		this.valAsegExcAne = valAsegExcAne;
	}

	public Double getTasaExcAne() {
		return tasaExcAne;
	}

	public void setTasaExcAne(Double tasaExcAne) {
		this.tasaExcAne = tasaExcAne;
	}

	public Double getPrimaExcAne() {
		return primaExcAne;
	}

	public void setPrimaExcAne(Double primaExcAne) {
		this.primaExcAne = primaExcAne;
	}

	public Integer getFactorExcAne() {
		return factorExcAne;
	}

	public void setFactorExcAne(Integer factorExcAne) {
		this.factorExcAne = factorExcAne;
	}

	public String getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}

	public String getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(String codModelo) {
		this.codModelo = codModelo;
	}

	public List<ProduccionEmitidaView> getLstProdEmitida() {
		return lstProdEmitida;
	}

	public void setLstProdEmitida(List<ProduccionEmitidaView> lstProdEmitida) {
		this.lstProdEmitida = lstProdEmitida;
	}

	public ProduccionEmitidaView getSelectedProdEmitida() {
		return selectedProdEmitida;
	}

	public void setSelectedProdEmitida(ProduccionEmitidaView selectedProdEmitida) {
		this.selectedProdEmitida = selectedProdEmitida;
	}

	public List<ConsultaObjetoPolView> getSelectedLstObjetosPoliza() {
		return selectedLstObjetosPoliza;
	}

	public void setSelectedLstObjetosPoliza(List<ConsultaObjetoPolView> selectedLstObjetosPoliza) {
		this.selectedLstObjetosPoliza = selectedLstObjetosPoliza;
	}

	public Date getFcEmiAseg() {
		return fcEmiAseg;
	}

	public void setFcEmiAseg(Date fcEmiAseg) {
		this.fcEmiAseg = fcEmiAseg;
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

	public List<ConsultaAnexoPendienteView> getLstAnexoPendiente() {
		return lstAnexoPendiente;
	}

	public void setLstAnexoPendiente(List<ConsultaAnexoPendienteView> lstAnexoPendiente) {
		this.lstAnexoPendiente = lstAnexoPendiente;
	}

	public String getApellidoRazonSocialAnexoP() {
		return apellidoRazonSocialAnexoP;
	}

	public void setApellidoRazonSocialAnexoP(String apellidoRazonSocialAnexoP) {
		this.apellidoRazonSocialAnexoP = apellidoRazonSocialAnexoP;
	}

	public String getPolizaAnexoP() {
		return polizaAnexoP;
	}

	public void setPolizaAnexoP(String polizaAnexoP) {
		this.polizaAnexoP = polizaAnexoP;
	}

	public List<ConsultaUbicacionAneView> getLstUbicacionAnePend() {
		return lstUbicacionAnePend;
	}

	public void setLstUbicacionAnePend(List<ConsultaUbicacionAneView> lstUbicacionAnePend) {
		this.lstUbicacionAnePend = lstUbicacionAnePend;
	}

	public ConsultaUbicacionAneView getSelectedUbicacionAnePend() {
		return selectedUbicacionAnePend;
	}

	public void setSelectedUbicacionAnePend(ConsultaUbicacionAneView selectedUbicacionAnePend) {
		this.selectedUbicacionAnePend = selectedUbicacionAnePend;
	}

	public ConsultaAnexoPendienteView getSelectedConsAnePend() {
		return selectedConsAnePend;
	}

	public void setSelectedConsAnePend(ConsultaAnexoPendienteView selectedConsAnePend) {
		this.selectedConsAnePend = selectedConsAnePend;
	}

	public List<ConsultaObjetoAneView> getLstObjetoAnePend() {
		return lstObjetoAnePend;
	}

	public void setLstObjetoAnePend(List<ConsultaObjetoAneView> lstObjetoAnePend) {
		this.lstObjetoAnePend = lstObjetoAnePend;
	}

	public ConsultaObjetoAneView getSelectedObjetoAnePend() {
		return selectedObjetoAnePend;
	}

	public void setSelectedObjetoAnePend(ConsultaObjetoAneView selectedObjetoAnePend) {
		this.selectedObjetoAnePend = selectedObjetoAnePend;
	}

	public List<ConsultaSubObjetoAneView> getLstSubObjeAnePend() {
		return lstSubObjeAnePend;
	}

	public void setLstSubObjeAnePend(List<ConsultaSubObjetoAneView> lstSubObjeAnePend) {
		this.lstSubObjeAnePend = lstSubObjeAnePend;
	}

	public ConsultaSubObjetoAneView getSelectedSubObjeAnePend() {
		return selectedSubObjeAnePend;
	}

	public void setSelectedSubObjeAnePend(ConsultaSubObjetoAneView selectedSubObjeAnePend) {
		this.selectedSubObjeAnePend = selectedSubObjeAnePend;
	}

	public Boolean getFlgBotonoEmite() {
		return flgBotonoEmite;
	}

	public void setFlgBotonoEmite(Boolean flgBotonoEmite) {
		this.flgBotonoEmite = flgBotonoEmite;
	}

	public List<ConsultaSubObjetoPolView> getSelectedLstSubobjetoPol() {
		return selectedLstSubobjetoPol;
	}

	public void setSelectedLstSubobjetoPol(List<ConsultaSubObjetoPolView> selectedLstSubobjetoPol) {
		this.selectedLstSubobjetoPol = selectedLstSubobjetoPol;
	}

	public boolean isAplicaIva() {
		return aplicaIva;
	}

	public void setAplicaIva(boolean aplicaIva) {
		this.aplicaIva = aplicaIva;
	}

	public boolean isCalculaIva() {
		return calculaIva;
	}

	public void setCalculaIva(boolean calculaIva) {
		this.calculaIva = calculaIva;
	}

	public boolean isBtnIncAsis() {
		return btnIncAsis;
	}

	public void setBtnIncAsis(boolean btnIncAsis) {
		this.btnIncAsis = btnIncAsis;
	}

	public Boolean getFlgPagoFactura() {
		return flgPagoFactura;
	}

	public void setFlgPagoFactura(Boolean flgPagoFactura) {
		this.flgPagoFactura = flgPagoFactura;
	}

	public String getStrAclaratorio() {
		return strAclaratorio;
	}

	public void setStrAclaratorio(String strAclaratorio) {
		this.strAclaratorio = strAclaratorio;
	}

	public String getStrAnexoAclaratorio() {
		return strAnexoAclaratorio;
	}

	public void setStrAnexoAclaratorio(String strAnexoAclaratorio) {
		this.strAnexoAclaratorio = strAnexoAclaratorio;
	}

	public List<ConsultaObjetoPolView> getFilteredLstObjetosPoliza() {
		return filteredLstObjetosPoliza;
	}

	public void setFilteredLstObjetosPoliza(List<ConsultaObjetoPolView> filteredLstObjetosPoliza) {
		this.filteredLstObjetosPoliza = filteredLstObjetosPoliza;
	}

	public String getPolizaAnexoNoCot() {
		return polizaAnexoNoCot;
	}

	public void setPolizaAnexoNoCot(String polizaAnexoNoCot) {
		this.polizaAnexoNoCot = polizaAnexoNoCot;
	}

	public String getPolizaClieBus() {
		return polizaClieBus;
	}

	public void setPolizaClieBus(String polizaClieBus) {
		this.polizaClieBus = polizaClieBus;
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

	public String getObjetoArchivo() {
		return objetoArchivo;
	}

	public void setObjetoArchivo(String objetoArchivo) {
		this.objetoArchivo = objetoArchivo;
	}

	public List<TipoModuloCarta> getLstObjetoGestDoc() {
		return lstObjetoGestDoc;
	}

	public void setLstObjetoGestDoc(List<TipoModuloCarta> lstObjetoGestDoc) {
		this.lstObjetoGestDoc = lstObjetoGestDoc;
	}

	public String getTipoArchivoDoc() {
		return tipoArchivoDoc;
	}

	public void setTipoArchivoDoc(String tipoArchivoDoc) {
		this.tipoArchivoDoc = tipoArchivoDoc;
	}

	public List<Rubros> getLstRubroGestDoc() {
		return lstRubroGestDoc;
	}

	public void setLstRubroGestDoc(List<Rubros> lstRubroGestDoc) {
		this.lstRubroGestDoc = lstRubroGestDoc;
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

	public List<Archivos> getLstArchivos() {
		return lstArchivos;
	}

	public void setLstArchivos(List<Archivos> lstArchivos) {
		this.lstArchivos = lstArchivos;
	}

	public List<ConsultaSubObjetoPolView> getFilteredLstSubobjetoPol() {
		return filteredLstSubobjetoPol;
	}

	public void setFilteredLstSubobjetoPol(List<ConsultaSubObjetoPolView> filteredLstSubobjetoPol) {
		this.filteredLstSubobjetoPol = filteredLstSubobjetoPol;
	}

	public String getFrmObservaciones() {
		return frmObservaciones;
	}

	public void setFrmObservaciones(String frmObservaciones) {
		this.frmObservaciones = frmObservaciones;
	}
	
	

}
