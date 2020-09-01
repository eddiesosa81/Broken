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
import javax.faces.event.ComponentSystemEvent;

import confia.entidades.basicos.AseguradoraRamo;
import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Ciudad;
import confia.entidades.basicos.Clientes;
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
import confia.entidades.transaccionales.Correspondencia;
import confia.entidades.transaccionales.Cotizacion;
import confia.entidades.transaccionales.MensualComercial;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.procedures.servicioProcedures;
import confia.reportes.AbstractReportBean;
import confia.reportes.EmailSenderService;
import confia.servicios.basicos.ServicioAseguradoraRamo;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioCiudad;
import confia.servicios.basicos.ServicioClientes;
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
import confia.servicios.transaccionales.ServicioCorrespondencia;
import confia.servicios.transaccionales.ServicioCotizacion;
import confia.servicios.transaccionales.ServicioMensualComercial;
import confia.servicios.transaccionales.ServicioRamoCotizacion;

@ManagedBean(name = "ControladorMensualizadoComercial")
@ViewScoped
public class ControladorMensualizadoComercial extends AbstractReportBean {
	@EJB
	private ServicioDireccion srvDireccion;
	@EJB
	private ServicioTelefono srvTelefono;
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
	private ServicioMensualComercial srvMensualComercial;
	@EJB
	private ServicioCorrespondencia srvCorrespondencia;

	private Clientes cliente;
	private Direccion direccion;
	private Telefono telefono;
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
	private Boolean lbbtn;
	private MensualComercial mensualComercial;
	private Integer valAseg;
	private String modeloMarca;
	private String placaRam;
	private Integer anio;
	private String color;
	private String numeroCarta;
	private Boolean lbHabilita;
	// --------------- PROGRAMACION IMPRESIONES ------------------//
	private String COMPILE_FILE_NAME;
	private Integer codCorrespondencia;

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}

	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		System.out.println("codCorrespondencia: " + codCorrespondencia);
		parametros.put("codCorrespondencia", codCorrespondencia);
		return parametros;

	}
	// ----------------------------------------------------------------//

	public ControladorMensualizadoComercial() {
		lstCiudad = new ArrayList<Ciudad>();
		lsrRubroSectorDirec = new ArrayList<Rubros>();
		lstProvincias = new ArrayList<Provincias>();
		cliente = new Clientes();
		direccion = new Direccion();
		telefono = new Telefono();
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
		mensualComercial = new MensualComercial();
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
		lbbtn = true;
		lbHabilita = false;
		codAseguradora = "5";
		numeroCarta = null;
		listadoAsegRamo = srvAsegramo.listaAseguradoraRamos(Integer.valueOf(codAseguradora));
		codRamo = "1";
		Integer tpRam = 0;
		anio = 2020;
		numeroCarta = null;
		consCed = null;
		placaRam = null;
		color = null;
		modeloMarca = null;
		modeloMarca = null;
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
	}

	public void verificaDocuGest() {

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

			} else {
				direccion = new Direccion();
				direccion = srvDireccion.BuscaDireccionCodCliente(String.valueOf(cliente.getCd_cliente()));
				if (direccion == null) {
					direccion = new Direccion();
					direccion.setCd_cliente(cliente.getCd_cliente());
				}
				try {
					dirClie = direccion.getDireccion();
					if (dirClie == null || dirClie.isEmpty()) {
						dirClie = "SN";
					}
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
				if (telefono == null) {
					telefono = new Telefono();
					telefono.setCd_cliente(cliente.getCd_cliente());
				}
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

			}
		} catch (Exception e) {
			cliente = new Clientes();
			cliente.setIdentificacion_cliente(consCed);
			cliente.setTipo_identificacion_cliente(tpPresona);
		}

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

	public void validaInfoClie() {
		System.out.println("DATO 1:" + codCiudadTelf);
		System.out.println("DATO 2:" + codProvincia);
		System.out.println("DATO 3:" + codSector);
		System.out.println("DATO 4:" + codciudad);
		System.out.println("DATO 5:" + dirClie);

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
		// canal - Uninova
		codCanal = "115";
		// grupo contratante - uninova
		codGrupoEconomico = "2";
		codEjecutivo = "19";

		try {
			if (valAseg == 0.0 || valAseg == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Valor Asegurado"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Valor Asegurado"));
			System.out.println("Error al ingresar el Objeto");
			return;
		}

		try {
			if (anio == 0.0 || anio == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Año"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Año"));
			System.out.println("Error al ingresar el Objeto");
			return;
		}

		try {
			if (modeloMarca.isEmpty() || modeloMarca == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Modelo"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el Modelo"));
			System.out.println("Error al ingresar el Objeto");
			return;
		}
		try {
			if (color.isEmpty() || color == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el color"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el color"));
			System.out.println("Error al ingresar el Objeto");
			return;
		}
		try {
			if (placaRam.isEmpty() || placaRam == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el placa/Ram"));
				return;

			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Ingrese el placa/Ram"));
			System.out.println("Error al ingresar el Objeto");
			return;
		}

		System.out.println("PASA VALIDACION");

		try {
			System.out.println(dirClie);
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
			direccion.setCd_ciudad(Integer.valueOf(codciudad));
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			direccion.setCd_rubro(Integer.valueOf(codSector));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo sector:" + e.getMessage());
		}
		try {
			direccion.setCd_provincia(codProvincia);
		} catch (Exception e) {
			System.out.println("Error actualizar codigo provincia:" + e.getMessage());
		}
		try {
			telefono.setCd_ciudad(Integer.valueOf(codCiudadTelf));
		} catch (Exception e) {
			System.out.println("Error actualizar codigo telefono ciudad");
		}

		System.out.println("EXISTE CLIENTE:" + existeClie);
		if (existeClie == true) {
			Direccion verDir = new Direccion();
			Telefono verTel = new Telefono();
			// verifico si existe dirección
			try {
				verDir = srvDireccion.BuscaDireccionCodCliente(String.valueOf(cliente.getCd_cliente()));
				if (verDir == null) {
					verDir = direccion;
					srvDireccion.insertarDireccion(verDir);
				} else {
					System.out.println("ACTUALIZO DIRECCION:" + direccion.getDireccion());
					srvDireccion.actualizaDireccion(direccion);
				}
			} catch (Exception e) {
				// return
			}
			try {
				verTel = srvTelefono.BuscaTelefonoCodCliente(String.valueOf(cliente.getCd_cliente()));
				if (verTel == null) {
					verTel = telefono;
					srvTelefono.insertarTelefonos(verTel);
				} else {
					srvTelefono.actualizaTelefono(telefono);
				}
			} catch (Exception e) {
				// return
			}
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
			direccion.setCd_aseguradora(0);
			direccion.setCd_cliente(cliente.getCd_cliente());
			telefono.setCd_aseguradora(0);
			telefono.setCd_cliente(cliente.getCd_cliente());

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

		// ASIGNO DATOS INGRESADOS

		observaciones = "VALOR ASEGURADO: ";
		observaciones = observaciones.concat(String.valueOf(valAseg));
		observaciones = observaciones.concat(" MARCA/MODELO: ");
		observaciones = observaciones.concat(String.valueOf(modeloMarca));
		observaciones = observaciones.concat(" PLACA/RAM: ");
		observaciones = observaciones.concat(String.valueOf(placaRam));
		observaciones = observaciones.concat(" AÑO: ");
		observaciones = observaciones.concat(String.valueOf(anio));
		observaciones = observaciones.concat(" COLOR: ");
		observaciones = observaciones.concat(String.valueOf(color));

		Cotizacion datosCotizacion = new Cotizacion();
		datosCotizacion.setTipo_cliente("MENSUALIZADO");
		datosCotizacion.setCd_aseguradora(Integer.valueOf(codAseguradora));
		datosCotizacion.setFc_ini_cot_date(fcDesde);
		datosCotizacion.setFc_fin_cot_date(fcHasta);
		datosCotizacion.setCd_rubro(8);
		datosCotizacion.setCd_compania(liCompania);
		datosCotizacion.setCd_cliente(cliente.getCd_cliente());
		datosCotizacion.setCd_cliente_asegurado(cliente.getCd_cliente());
		datosCotizacion.setFact_periodica_cot(1);
		datosCotizacion.setNum_renovaciones_cot(0);
		datosCotizacion.setObservaciones(observaciones);
		datosCotizacion.setUsrid(Integer.valueOf(usuarioId));

		Integer cdCotMax = srvCotizacion.insertarCotizacion(datosCotizacion);
		if (cdCotMax == 1) {
			cdCotMax = srvCotizacion.codigoMaxCotizacion();
		}
		datosCotizacion = new Cotizacion();
		System.out.println("INGRESOOOOO  guardaRamoCotizacion");

		datosCotizacion = srvCotizacion.recuperaCotizacionPorCodigo(cdCotMax, liCompania);
		numCotizacion = datosCotizacion.getNum_cotizacion();

		// GUARDO MODULO MENSUAL COMERCIAL
		MensualComercial auxMen = new MensualComercial();

		auxMen.setAnio(anio);
		auxMen.setCd_compania(datosCotizacion.getCd_compania());
		auxMen.setCd_cotizacion(datosCotizacion.getCd_cotizacion());
		auxMen.setColor(color);
		auxMen.setPlacaRam(placaRam);
		auxMen.setValorAsegurado(valAseg);
		auxMen.setModelo_Marca(modeloMarca);
		srvMensualComercial.insertarMensualComercial(auxMen);
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
			context.addMessage(null, new FacesMessage("Registro Exitoso", "Cotización No." + numCotizacion));
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Error al generar el proceso"));
			return;
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

		email.setReceptor("jsalazar@grupoconfia.com,acata@grupoconfia.com");
		email.setSubject("Sistema Confia Mensualizado - Emisión No." + numCotizacion);
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

		consCed = "";
		codAseguradora = "0";
		codRamo = "0";
		codPlan = "0";
		codGrupoEconomico = "0";
		codCanal = "0";
		codEjecutivo = "0";
		asunto = "";
		observaciones = "";
		valAseg = 0;
		anio = null;
		modeloMarca = null;
		color = null;
		placaRam = null;
		lbbtn = false;
		lbHabilita = true;
		// GUARDA CARTA

		String usuarioId, nmUsr, nmCli;
		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		System.out.println("GUARDAR CARTA USuario:" + usuarioId);
		Usuarios usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(usuarioId);
		nmUsr = usr.getUsrnombres();
		nmUsr = nmUsr.concat(" ");
		nmUsr = nmUsr.concat(usr.getUsrapellidos());
		Correspondencia carta = new Correspondencia();
		Rubros objCarta = new Rubros();
		objCarta = srvRubros.recuperaCartaPorNombre("RESUMENDESEGUROSMENSUALIZADO");
		nmCli = srvClientes.nombreCliente(datosCotizacion.getCd_cliente());
		carta.setCdCotizacion(datosRamoCotizacion.getCd_cotizacion());
		carta.setCd_compania(datosRamoCotizacion.getCd_compania());
		carta.setCdAseguradora(datosCotizacion.getCd_aseguradora());
		carta.setCdCliente(datosCotizacion.getCd_cliente());
		carta.setNmContacto(nmCli);
		carta.setCdRubro(datosCotizacion.getCd_rubro());
		carta.setModuloGenera("MODULO_EMISION_MENSUALIZADO");
		carta.setNmReporte(objCarta.getNm_reporte());
		carta.setTipo(objCarta.getDesc_rubro());
		carta.setRefCarta(objCarta.getReferenciaCarta());
		carta.setElaboradorPor(nmUsr);
		carta.setUsrid(usr.getUsrid());
		carta.setCargoUsuario(usr.getCargo());
		srvCorrespondencia.insertarCorrespondencia(carta);
		numeroCarta = srvCorrespondencia.numCartaMax(String.valueOf(usr.getUsrid()));
		System.out.println("Número de carat:" + numeroCarta);
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Registro Exitoso", "Certificado Número " + numeroCarta));
	}

	public void execute() {
		// IMPRIME CARTA
		System.out.println("Número de carat:" + numeroCarta);
		Correspondencia auxDoc = new Correspondencia();
		auxDoc = srvCorrespondencia.documentoNumCarta(numeroCarta);

		COMPILE_FILE_NAME = auxDoc.getNmReporte().trim();
		codCorrespondencia = auxDoc.getCd_correspondencia();
		auxDoc.setEstado_impresion("SI");
		srvCorrespondencia.actualizaCorrespondencia(auxDoc);
		System.out.println("---------------------------- NomReporte:" + COMPILE_FILE_NAME);
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		numeroCarta = null;
		lbbtn = true;
		lbHabilita = false;
		consCed = null;
		placaRam = null;
		color = null;
		modeloMarca = null;
		modeloMarca = null;
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

	public Boolean getLbbtn() {
		return lbbtn;
	}

	public void setLbbtn(Boolean lbbtn) {
		this.lbbtn = lbbtn;
	}

	public MensualComercial getMensualComercial() {
		return mensualComercial;
	}

	public void setMensualComercial(MensualComercial mensualComercial) {
		this.mensualComercial = mensualComercial;
	}

	public Integer getValAseg() {
		return valAseg;
	}

	public void setValAseg(Integer valAseg) {
		this.valAseg = valAseg;
	}

	public String getModeloMarca() {
		return modeloMarca;
	}

	public void setModeloMarca(String modeloMarca) {
		this.modeloMarca = modeloMarca;
	}

	public String getPlacaRam() {
		return placaRam;
	}

	public void setPlacaRam(String placaRam) {
		this.placaRam = placaRam;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getLbHabilita() {
		return lbHabilita;
	}

	public void setLbHabilita(Boolean lbHabilita) {
		this.lbHabilita = lbHabilita;
	}

}
