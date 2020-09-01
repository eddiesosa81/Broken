package confia.controladores.transaccionales;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import confia.entidades.basicos.AseguradoraRamo;
import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Ciudad;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.Direccion;
import confia.entidades.basicos.Ejecutivos;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Plan;
import confia.entidades.basicos.Provincias;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.SubagenteRamo;
import confia.entidades.basicos.Subagentes;
import confia.entidades.basicos.Telefono;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Archivos;
import confia.entidades.transaccionales.Cotizacion;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.procedures.servicioProcedures;
import confia.reportes.EmailSenderService;
import confia.servicios.basicos.ServicioAseguradoraRamo;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioCiudad;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioContacto;
import confia.servicios.basicos.ServicioDireccion;
import confia.servicios.basicos.ServicioEjecutivos;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioPlan;
import confia.servicios.basicos.ServicioProvincias;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioSubagenteRamo;
import confia.servicios.basicos.ServicioSubagentes;
import confia.servicios.basicos.ServicioTelefono;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.transaccionales.ServicioArchivos;
import confia.servicios.transaccionales.ServicioCotizacion;
import confia.servicios.transaccionales.ServicioRamoCotizacion;

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

	public ControladorCotizacionComercial() {
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
	}

	@PostConstruct
	public void recuperaInicio() {
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
		long tamanio = miArchivo.getSize();// tamaño del archivo
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
		System.out.println("-**********-------Tamaño: " + tamanio);
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
	}

	public void listarPlanes() {
		Integer tpRam = 0;

		// verifico configuración del ramo para el manejo del plan en la
		// Ubicación o RamoCotizacion
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
		// try {
		// if (codGrupoEconomico.equals("0")) {
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.addMessage(null, new FacesMessage("Advertencia", "Seleccion
		// el Grupo Económico"));
		// return;
		//
		// }
		// } catch (Exception e) {
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.addMessage(null, new FacesMessage("Advertencia", "Seleccion
		// el Grupo Económico"));
		// return;
		// }
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
		System.out.println("Se cuardo datos cliente exitosamente");
		// inserto la cotización
		Integer liCompania;
		String lsCompania = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("COMPANIA")
				.toString();
		String numCotizacion;
		try {
			liCompania = Integer.parseInt(lsCompania);

		} catch (Exception e) {
			liCompania = 1;
		}

		Cotizacion datosCotizacion = new Cotizacion();
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
		RamoCotizacion datosRamoCotizacion = new RamoCotizacion();

		datosRamoCotizacion.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
		datosRamoCotizacion.setCd_compania(datosCotizacion.getCd_compania());
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
					new FacesMessage("Registro Exitoso", "Se asignó la Cotización No." + numCotizacion));
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Error al generar el proceso"));
			return;
		}
		// guarda los archivos adjuntos
		for (Archivos archivo : lstArchivos) {
			archivo.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
			archivo.setCd_cliente(String.valueOf(cliente.getCd_cliente()));
			srvArchivos.insertaArchivos(archivo);
		}
		// se envía el correo al usuario seleccionado
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
		
		lbbtn =false;
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

}
