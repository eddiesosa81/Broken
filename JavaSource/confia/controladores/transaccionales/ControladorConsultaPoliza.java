package confia.controladores.transaccionales;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.Aseguradoras;
import confia.entidades.basicos.Ciudad;
import confia.entidades.basicos.ClausulasEmitidas;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.CoberturasEmitidas;
import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.DeduciblesEmitidas;
import confia.entidades.basicos.Direccion;
import confia.entidades.basicos.GrupoContratante;
import confia.entidades.basicos.Provincias;
import confia.entidades.basicos.Ramo;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.Telefono;
import confia.entidades.basicos.TipoModuloCarta;
import confia.entidades.basicos.Usuarios;
import confia.entidades.transaccionales.Archivos;
import confia.entidades.transaccionales.CaracteristicasVehiculos;
import confia.entidades.transaccionales.Correspondencia;
import confia.entidades.transaccionales.FormaPago;
import confia.entidades.transaccionales.Gestion;
import confia.entidades.transaccionales.NotasAclaratorias;
import confia.entidades.transaccionales.RamoCotizacion;
import confia.entidades.transaccionales.Ubicacion;
import confia.entidades.vistas.ConsultaCaractPolView;
import confia.entidades.vistas.ConsultaObjetoPolView;
import confia.entidades.vistas.ConsultaPagoPolView;
import confia.entidades.vistas.ConsultaPagoRealizadosView;
import confia.entidades.vistas.ConsultaPolizaView;
import confia.entidades.vistas.ConsultaSubObjetoPolView;
import confia.entidades.vistas.ConsultaUbicacionPolView;
import confia.entidades.vistas.CotizacionesPendientes;
import confia.entidades.vistas.PlanRamoAseguradoraView;
import confia.entidades.vistas.TransaccionPolizaView;
import confia.procedures.servicioProcedures;
import confia.reportes.EmailSenderService;
import confia.reportes.MeetingSenderService;
import confia.servicios.basicos.ServicioAseguradoras;
import confia.servicios.basicos.ServicioCiudad;
import confia.servicios.basicos.ServicioClausulasEmitidas;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioCoberturasEmitidas;
import confia.servicios.basicos.ServicioContacto;
import confia.servicios.basicos.ServicioDireccion;
import confia.servicios.basicos.ServicioGrupoContratante;
import confia.servicios.basicos.ServicioProvincias;
import confia.servicios.basicos.ServicioRamo;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioTelefono;
import confia.servicios.basicos.ServicioUsuarios;
import confia.servicios.basicos.ServiciosDeduciblesEmitidas;
import confia.servicios.transaccionales.ServicioArchivos;
import confia.servicios.transaccionales.ServicioCaracteristicasVehiculos;
import confia.servicios.transaccionales.ServicioCorrespondencia;
import confia.servicios.transaccionales.ServicioFormaPago;
import confia.servicios.transaccionales.ServicioGestion;
import confia.servicios.transaccionales.ServicioNotasAclaratorias;
import confia.servicios.transaccionales.ServicioRamoCotizacion;
import confia.servicios.vistas.ServicioConsultaCaractPolView;
import confia.servicios.vistas.ServicioConsultaObjetoPolView;
import confia.servicios.vistas.ServicioConsultaPagoPolView;
import confia.servicios.vistas.ServicioConsultaPagoRealizadosView;
import confia.servicios.vistas.ServicioConsultaPolizaView;
import confia.servicios.vistas.ServicioConsultaSubObjetoPolView;
import confia.servicios.vistas.ServicioConsultaUbicacionPolView;
import confia.servicios.vistas.ServicioPlanesRamoAseg;
import confia.servicios.vistas.ServicioTransaccionPolizaView;

@ViewScoped
@ManagedBean(name = "ControladorConsultaPoliza")
public class ControladorConsultaPoliza {
	@EJB
	private ServicioCoberturasEmitidas srvCoberturasEmitidas;
	@EJB
	private ServicioClausulasEmitidas srvClausulasEmitidas;
	@EJB
	private ServiciosDeduciblesEmitidas srvDeduciblesEmitidas;
	@EJB
	private ServicioAseguradoras srvAseguradoras;
	@EJB
	private ServicioPlanesRamoAseg srvPlanesRamoAseg;
	@EJB
	private ServicioGrupoContratante srvGrupoContratante;
	@EJB
	private ServicioConsultaPolizaView srvConsultaPolizaView;
	@EJB
	private ServicioConsultaCaractPolView srvConsCaractPolView;
	@EJB
	private ServicioConsultaUbicacionPolView srvConsultaUbcPolView;
	@EJB
	private ServicioConsultaPagoPolView srvConsPagoPolView;
	@EJB
	private ServicioConsultaObjetoPolView srvConsultaObjetoPolView;
	@EJB
	private ServicioCaracteristicasVehiculos srvCaracteristicaObj;
	@EJB
	private ServicioConsultaSubObjetoPolView srvConsultaSubObjetoPolView;
	@EJB
	private ServicioRubros srvRubrosCartas;
	@EJB
	private ServicioContacto srvContactosCarta;
	@EJB
	private ServicioCorrespondencia srvCorrespondencia;
	@EJB
	private ServicioUsuarios srvUsuario;
	@EJB
	private ServicioConsultaPagoRealizadosView srvPagosRealizados;
	@EJB
	private ServicioTransaccionPolizaView srvTransaccionPoliza;
	@EJB
	private ServicioNotasAclaratorias srvNotaAcaratoria;
	private servicioProcedures srvProcedimientos;
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
	private ServicioGestion srvGestion;
	@EJB
	private ServicioCoberturasEmitidas srvCoberturas;
	@EJB
	private ServicioClausulasEmitidas srvClausulas;
	@EJB
	private ServiciosDeduciblesEmitidas srvDeducibles;
	@EJB
	private ServicioArchivos srvArchivos;
	@EJB
	private ServicioRamo srvRamo;
	@EJB
	private ServicioRamoCotizacion srvRamoCotizacion;
	@EJB
	private ServicioFormaPago srvFormaPago;

	private String numPoliza;
	private String numFactura;
	private String nmCliente;
	private String codGrupoContratante;
	private List<GrupoContratante> listaGrupoContratante;
	private String placa;
	private String motor;
	private List<ConsultaPolizaView> lstConsultaPoliza;
	private ConsultaPolizaView selectedConsultaPoliza;
	private List<ConsultaObjetoPolView> lstObjetosPoliza;
	private List<ConsultaObjetoPolView> filteredLstlstObjetosPoliza;
	private List<ConsultaUbicacionPolView> lstUbicacionPoliza;
	private List<CaracteristicasVehiculos> LstCaracteristicas;
	private List<ConsultaPagoPolView> lstPagoPoliza;
	private ConsultaUbicacionPolView selectedUbicacionPoliza;
	private ConsultaObjetoPolView selectedObjetosPoliza;
	private List<ConsultaSubObjetoPolView> lstSubObjetosPoliza;
	private List<ConsultaSubObjetoPolView> filteredLstSubObjetosPoliza;
	private List<TransaccionPolizaView> lstTransaccionPoliza;

	// CARTAS
	private List<Rubros> lstRubrosCarta;
	private List<Contacto> lstContactoCarta;
	private String nmCarta;
	private Contacto selectedContactoCarta;
	private String notasAdicionalesCarta;
	private Rubros objCarta;
	private String rBotonPolAne;
	private Boolean lbTransaccion;
	private List<NotasAclaratorias> lstNotaAclaratoria;
	private String objAsegurado;
	// PAGOS
	private List<ConsultaPagoRealizadosView> lstPagoRealizado;
	private FormaPago frFormaPago;
	// gestión
	private Clientes cliente;
	private Direccion direccion;
	private Telefono telefono;
	private Contacto contacto;
	private List<Ciudad> lstCiudad;
	private List<Rubros> lsrRubroSectorDirec;
	private List<Provincias> lstProvincias;
	private List<ConsultaPolizaView> lstPolizaGestionada;
	private Gestion gestion;
	private List<Gestion> lstGestion;
	private Date fcPaga, fcSeg;
	private List<PlanRamoAseguradoraView> lstPlanRamAseg;
	private List<ClausulasEmitidas> lstCalusulas;
	private List<CoberturasEmitidas> lstCoberturas;
	private List<DeduciblesEmitidas> lstDeducibles;
	private List<Usuarios> lstUsuarios;
	private String[] selectedUsuarios;
	private MeetingSenderService eMeeting;
	private String asunto;
	private EmailSenderService email;
	private String usuarioId, nmUsr;
	private Usuarios usr;

	private String strNombreContacto;
	private String strNumerotelefono;
	private String strNumerocelular;
	private String strcorreo;
	private String strCargo;
	private String strDepartamento;
	private String strDireccion;
	private String tipoArchivo;

	// Gestion documentos
	private List<TipoModuloCarta> lstTipoGestDoc;
	private List<Archivos> lstArchivos;

	private List<Aseguradoras> listAseguradoras;
	private List<Ramo> lstRamo;

	private String lscdAseguradora;
	private String lscdRamo;

	private boolean caduca;

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

	public ControladorConsultaPoliza() {
		listaGrupoContratante = new ArrayList<GrupoContratante>();
		lstConsultaPoliza = new ArrayList<ConsultaPolizaView>();
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		lstUbicacionPoliza = new ArrayList<ConsultaUbicacionPolView>();
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		lstPagoPoliza = new ArrayList<ConsultaPagoPolView>();
		lstSubObjetosPoliza = new ArrayList<ConsultaSubObjetoPolView>();
		lstRubrosCarta = new ArrayList<Rubros>();
		lstContactoCarta = new ArrayList<Contacto>();
		rBotonPolAne = "poliza";
		lstPagoRealizado = new ArrayList<ConsultaPagoRealizadosView>();
		lstTransaccionPoliza = new ArrayList<TransaccionPolizaView>();
		lbTransaccion = false;
		lstNotaAclaratoria = new ArrayList<NotasAclaratorias>();
		srvProcedimientos = new servicioProcedures();
		lstCiudad = new ArrayList<Ciudad>();
		lsrRubroSectorDirec = new ArrayList<Rubros>();
		lstProvincias = new ArrayList<Provincias>();
		cliente = new Clientes();
		direccion = new Direccion();
		telefono = new Telefono();
		contacto = new Contacto();
		lstPolizaGestionada = new ArrayList<ConsultaPolizaView>();
		lstGestion = new ArrayList<Gestion>();
		gestion = new Gestion();
		lstPlanRamAseg = new ArrayList<PlanRamoAseguradoraView>();
		lstCalusulas = new ArrayList<ClausulasEmitidas>();
		lstCoberturas = new ArrayList<CoberturasEmitidas>();
		lstDeducibles = new ArrayList<DeduciblesEmitidas>();
		lstUsuarios = new ArrayList<Usuarios>();
		eMeeting = new MeetingSenderService();
		email = new EmailSenderService();
		lstTipoGestDoc = new ArrayList<TipoModuloCarta>();
		lstArchivos = new ArrayList<Archivos>();
		listAseguradoras = new ArrayList<Aseguradoras>();
		lstRamo = new ArrayList<Ramo>();
	}

	@PostConstruct
	public void recuperaInicio() {
		listAseguradoras = srvAseguradoras.BuscaAseguradoras();
		lstRamo = srvRamo.listaRamos();

		listaGrupoContratante = srvGrupoContratante.listaGruposContratantes();
		lstRubrosCarta = srvRubrosCartas.listadoCartasPorModulo("POLIZAS_EMITIDAS");
		lstCiudad = srvCiudad.recuperaListaCiudad();
		lsrRubroSectorDirec = srvRubros.listadoRubrosCod("102");
		lstProvincias = srvProvincias.listaProvincias();
		lstUsuarios = srvUsuario.listaUsuariosActivos();

		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		System.out.println("USuario Logeado:" + usuarioId);
		usr = new Usuarios();
		usr = srvUsuario.recuperaUsuario(usuarioId);
		nmUsr = usr.getUsrnombres();
		nmUsr = nmUsr.concat(" ");
		nmUsr = nmUsr.concat(usr.getUsrapellidos());

		lstTipoGestDoc = srvRubros.recuperaTipoGestionDocu();
		tipoArchivo = "CLIENTE";

	}

	public void consultaArchivosGuardados() {
		String codCotiza = null, codCliente = null;

		System.out.println("tipoArchivo:" + tipoArchivo);

		try {
			codCotiza = selectedConsultaPoliza.getCd_cotizacion();
			codCliente = selectedConsultaPoliza.getCd_cliente();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la póliza"));
			return;
		}

		if (tipoArchivo.equals("CLIENTE")) {
			codCotiza = "%";
		}
		lstArchivos = new ArrayList<Archivos>();
		lstArchivos = srvArchivos.recuperaArchivosCotizacionTipo(tipoArchivo, codCotiza, codCliente);

	}

	public void renuevaPol() {
		Integer ll_cod = 0, ll_comp = 0;
		String res;
		try {
			if (selectedConsultaPoliza.getCd_cotizacion().isEmpty()
					|| selectedConsultaPoliza.getCd_cotizacion() == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la póliza"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la póliza"));
			return;
		}

		ll_cod = Integer.valueOf(selectedConsultaPoliza.getCd_cotizacion());
		ll_comp = Integer.valueOf(selectedConsultaPoliza.getCd_compania());

		Integer ll_fecha_renovacion = Integer.valueOf(selectedConsultaPoliza.getFc_vig_fin_jul()) - 30;
		Integer ll_fecha_actual_jul = Integer.valueOf(srvProcedimientos.fechaJulianaActual());

		// solo puede renovar 30 dia antes del fin de vigencia
		// if (ll_fecha_actual_jul <= ll_fecha_renovacion) {
		res = srvProcedimientos.renuevaPoliza(selectedConsultaPoliza.getCd_cotizacion(),
				selectedConsultaPoliza.getCd_compania());
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Renovación Nómero:" + res));
		// } else {
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.addMessage(null,
		// new FacesMessage("Advertencia", "Usted puede renovar 30 dóas antes
		// del fin de vigencia."));
		// }
	}

	public void buscaPoliza() {
		String grpCont = null, aux;
		String crcCara = null;
		List<ConsultaCaractPolView> consCaracPol = new ArrayList<ConsultaCaractPolView>();
		aux = "%";
		try {
			objAsegurado = aux.concat(objAsegurado.trim());
			objAsegurado = objAsegurado.concat("%");
		} catch (Exception e) {
			// no realiza nada y retorna
			objAsegurado = "%";
		}
		aux = "%";
		try {
			numPoliza = aux.concat(numPoliza.trim());
			numPoliza = numPoliza.concat("%");
		} catch (Exception e) {
			// no realiza nada y retorna
			numPoliza = "%";
		}
		aux = "%";
		try {
			numFactura = aux.concat(numFactura.trim());
			numFactura = numFactura.concat("%");
		} catch (Exception e) {
			// no realiza nada y retorna
			numFactura = "%";
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
		if (codGrupoContratante.equals("0")) {
			grpCont = "%";
		} else {
			grpCont = codGrupoContratante;
		}
		System.out.println("Poliza:" + numPoliza);
		System.out.println("Factura:" + numFactura);
		System.out.println("nombre Cliente:" + nmCliente);
		System.out.println("placa:" + placa);
		System.out.println("motor:" + motor);
		System.out.println("grupo contratante:" + grpCont);
		System.out.println("Objeto Asegurado:" + objAsegurado);
		System.out.println("lscdAseguradora:" + lscdAseguradora);
		System.out.println("lscdRamo:" + lscdRamo);

		if (placa.equals("%") && motor.equals("%") && objAsegurado.equals("%")) {
			if (rBotonPolAne.equals("anexo")) {
				if (caduca) {
					lstConsultaPoliza = srvConsultaPolizaView.consultaPolizaAnexoXClienteGrpCont(nmCliente, grpCont,
							numPoliza, numFactura, lscdAseguradora, lscdRamo);
				} else {
					lstConsultaPoliza = srvConsultaPolizaView.consultaPolizaAnexoXClienteGrpContVig(nmCliente, grpCont,
							numPoliza, numFactura, lscdAseguradora, lscdRamo);
				}

			} else if (rBotonPolAne.equals("poliza")) {
				if (caduca) {
					lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXClienteGrpCont(nmCliente, grpCont,
							numPoliza, numFactura, lscdAseguradora, lscdRamo);
				} else {
					lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXClienteGrpContVig(nmCliente, grpCont,
							numPoliza, numFactura, lscdAseguradora, lscdRamo);
				}

			} else {
				if (caduca) {
					lstConsultaPoliza = srvConsultaPolizaView.consultaAnulaCancelXClienteGrpCont(nmCliente, grpCont,
							numPoliza, numFactura, lscdAseguradora, lscdRamo);
				} else {
					lstConsultaPoliza = srvConsultaPolizaView.consultaAnulaCancelXClienteGrpContVig(nmCliente, grpCont,
							numPoliza, numFactura, lscdAseguradora, lscdRamo);
				}

			}
		} else {
			if (rBotonPolAne.equals("anulaCancela")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Consulta no disponible en el Sistema"));
				nmCarta = "0";
			} else {
				crcCara = "";
				if (!placa.equals("%")) {
					String lsPlacaString, LsPlacaFinal = "%";
					lsPlacaString = placa.toUpperCase();
					lsPlacaString = lsPlacaString.trim();
					for (int n = 0; n < lsPlacaString.length(); n++) {
						char c = lsPlacaString.charAt(n);
						if (n == 3) {
							LsPlacaFinal = LsPlacaFinal.concat("%");
							LsPlacaFinal = LsPlacaFinal.concat(String.valueOf(c));
						} else {
							LsPlacaFinal = LsPlacaFinal.concat(String.valueOf(c));
						}
					}
					LsPlacaFinal = LsPlacaFinal.concat("%");
					System.out.println("PLACA A BUSCAR:" + LsPlacaFinal);
					List<ConsultaCaractPolView> lstconsCaracPol = new ArrayList<ConsultaCaractPolView>();
					lstconsCaracPol = srvConsCaractPolView.consultaCaracteristicaObjPolPlaca(LsPlacaFinal);

					String varString = "";
					Integer sizeListInteger = lstconsCaracPol.size();
					System.out.println("Tamaño lista:" + sizeListInteger);
					Integer contadorInteger = 0;

					for (ConsultaCaractPolView auxCRC : lstconsCaracPol) {
						contadorInteger = contadorInteger + 1;
						if (contadorInteger == sizeListInteger) {
							varString = varString.concat(auxCRC.getCd_ramo_cotizacion());
						} else {
							varString = varString.concat(auxCRC.getCd_ramo_cotizacion());
							varString = varString.concat(",");
						}
					}
					System.out.println("varString:" + varString);

					if (caduca) {
						lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasCaracteObj(crcCara);
					} else {
						lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasCaracteObjVig(crcCara);
					}

				}
				if (!motor.equals("%")) {
					consCaracPol = srvConsCaractPolView.consultaCaracteristicaPol("%", motor.trim().toUpperCase());
					for (ConsultaCaractPolView auxCar : consCaracPol) {
						crcCara = crcCara.concat(auxCar.getCd_ramo_cotizacion());
						crcCara = crcCara.concat(",");
					}
					crcCara = crcCara.substring(1, crcCara.length() - 1);
					System.out.println("INGRE cxc:" + crcCara);

					if (caduca) {
						lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasCaracteObj(crcCara);

					} else {
						lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasCaracteObjVig(crcCara);
					}

				}
				if (!objAsegurado.equals("%")) {
					if (caduca) {
						lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXDescObj(objAsegurado);
					} else {
						lstConsultaPoliza = srvConsultaPolizaView.consultaPolizasXDescObjVig(objAsegurado);
					}

				}
			}
		}
	}

	public void onRowSelectPol(SelectEvent event) {
		lstUbicacionPoliza = new ArrayList<ConsultaUbicacionPolView>();
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		String codRamCotAux;
		codRamCotAux = ((ConsultaPolizaView) event.getObject()).getCd_ramo_cotizacion();
		if (rBotonPolAne.equals("anexo")) {
			lstUbicacionPoliza = srvConsultaUbcPolView.consultaUbicacionAnexoxCrc(codRamCotAux);
		} else {
			lstUbicacionPoliza = srvConsultaUbcPolView.consultaUbicacionxCrc(codRamCotAux);
		}
		lstTransaccionPoliza = srvTransaccionPoliza.recuperaTransaccionesPol(codRamCotAux);
		lbTransaccion = true;

	}

	public void onRowNotaAclaratoria(ConsultaPolizaView polAux) {
		lstNotaAclaratoria = new ArrayList<NotasAclaratorias>();
		lstNotaAclaratoria = srvNotaAcaratoria.consultaNotasAclaratorias(polAux.getCd_ramo_cotizacion());
	}

	public void onRowPlanRamCot(ConsultaPolizaView ramCot) {
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		
		// bloquea si se trata de VAM
		Integer tpRam = srvRamo.tipoRamo(Integer.valueOf(ramCot.getCd_ramo()));
		
		if (tpRam.equals(0)) {
			lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidas(Integer.valueOf(ramCot.getCd_compania()),
					Integer.valueOf(ramCot.getCd_ramo_cotizacion()));
			System.out.println("lstCalusulaEmitida:" + lstCalusulaEmitida.size());
			lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidas(Integer.valueOf(ramCot.getCd_compania()),
					Integer.valueOf(ramCot.getCd_ramo_cotizacion()));
			System.out.println("lstCoberturasEmitidas:" + lstCoberturasEmitidas.size());
			lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidas(Integer.valueOf(ramCot.getCd_compania()),
					Integer.valueOf(ramCot.getCd_ramo_cotizacion()));
			System.out.println("lstDeducibleEmitida:" + lstDeducibleEmitida.size());

			PrimeFaces.current().executeScript("PF('wvPlanesRamCot').show();");
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia",
					"Planes no habilitados para este tipo de Ramo, revise a nivel de ubicación"));
			return;
		}

	}
	
	public void onRowPlanUbc(ConsultaUbicacionPolView ubicaTot) {
		lstCoberturasEmitidas = new ArrayList<CoberturasEmitidas>();
		lstDeducibleEmitida = new ArrayList<DeduciblesEmitidas>();
		lstCalusulaEmitida = new ArrayList<ClausulasEmitidas>();
		
		//bloquea si se trata de VAM
		RamoCotizacion crcTemp = srvRamoCotizacion.recuperaRamoCotizacionPorCodigo(Integer.valueOf(ubicaTot.getCd_ramo_cotizacion()),
				Integer.valueOf(ubicaTot.getCd_compania()));
		Integer tpRam = srvRamo.tipoRamo(crcTemp.getCd_ramo());
		if (tpRam.equals(0)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Planes no habilitados para este tipo de Ubicación, revise a nivel de Ramo"));
			return;
		} else {
			
			lstCalusulaEmitida = srvClausulasEmitidas.recuperaClausulasEmitidasUbc(Integer.valueOf(ubicaTot.getCd_compania()),
					Integer.valueOf(ubicaTot.getCd_ramo_cotizacion()),Integer.valueOf(ubicaTot.getCd_ubicacion()));
			
			lstCoberturasEmitidas = srvCoberturasEmitidas.recuperaCoberturasEmitidasUbc(Integer.valueOf(ubicaTot.getCd_compania()),
					Integer.valueOf(ubicaTot.getCd_ramo_cotizacion()),Integer.valueOf(ubicaTot.getCd_ubicacion()));
			
			lstDeducibleEmitida = srvDeduciblesEmitidas.recuperaDeduciblesEmitidasUbicacion(Integer.valueOf(ubicaTot.getCd_compania()),
					Integer.valueOf(ubicaTot.getCd_ramo_cotizacion()),Integer.valueOf(ubicaTot.getCd_ubicacion()));
			
			PrimeFaces.current().executeScript("PF('wvPlanesRamCot').show();");
		}
		
	}

	public void onRowSelectPago() {
		lstPagoPoliza = new ArrayList<ConsultaPagoPolView>();
		String codCotAux;
		if (rBotonPolAne.equals("anexo")) {
			try {
				codCotAux = selectedConsultaPoliza.getCd_cotizacion();
			} catch (Exception e) {
				return;
			}
			System.out.println("COTIZACION:" + codCotAux);
			lstPagoPoliza = srvConsPagoPolView.consultaPagoPolAnexoXCdCot(codCotAux);
			System.out.println("FROMAPAGO:" + lstPagoPoliza.size());
		} else {

			try {
				codCotAux = selectedConsultaPoliza.getCd_cotizacion();
			} catch (Exception e) {
				return;
			}
			lstPagoPoliza = srvConsPagoPolView.consultaPagoPolXCdCot(codCotAux);
			System.out.println("FROMAPAGO:" + lstPagoPoliza.size());
		}
		
		frFormaPago = new FormaPago();
		frFormaPago = srvFormaPago.recuperaFormaPagoxCod(Integer.valueOf(lstPagoPoliza.get(0).getCd_forma_pago()), Integer.valueOf(lstPagoPoliza.get(0).getCd_compania()));
		System.err.println("FORMA PAGO:"+frFormaPago.getTotal_Pago_FormaPago());
		
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('dlgPagoPolCons').show()");
		PrimeFaces.current().executeScript("PF('dlgPagoPolCons').show()");
	}

	public void salir() {
		// ExternalContext ctx =
		// FacesContext.getCurrentInstance().getExternalContext();
		// try {
		// ctx.redirect("./index.jsf");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public void onRowSelectUbicacion(SelectEvent event) {
		lstObjetosPoliza = new ArrayList<ConsultaObjetoPolView>();
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		String codUbcAux;
		codUbcAux = ((ConsultaUbicacionPolView) event.getObject()).getCd_ubicacion();
		if (rBotonPolAne.equals("anexo")) {
			lstObjetosPoliza = srvConsultaObjetoPolView.consultaObjetosAnexoXUbc(codUbcAux);
		} else {
			lstObjetosPoliza = srvConsultaObjetoPolView.consultaObjetosXUbc(codUbcAux);
		}
	}

	public void onRowSelectObjeto(SelectEvent event) {
		LstCaracteristicas = new ArrayList<CaracteristicasVehiculos>();
		String codObjAux, codComp;
		codObjAux = ((ConsultaObjetoPolView) event.getObject()).getCd_obj_cotizacion();
		codComp = ((ConsultaObjetoPolView) event.getObject()).getCd_compania();
		LstCaracteristicas = srvCaracteristicaObj.recuperaCaractVHporObjCot(Integer.valueOf(codObjAux),
				Integer.valueOf(codComp));
		lstSubObjetosPoliza = new ArrayList<ConsultaSubObjetoPolView>();
		if (rBotonPolAne.equals("anexo")) {
			lstSubObjetosPoliza = srvConsultaSubObjetoPolView.consultaSubObjetoAnexoxCdObj(codObjAux);
		} else {
			lstSubObjetosPoliza = srvConsultaSubObjetoPolView.consultaSubObjetoxCdObj(codObjAux);
		}
	}

	public void recuperaContactosCarta() {
		try {
			String tamano = selectedConsultaPoliza.getPoliza();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione la Póliza antes de generar la carta"));
			nmCarta = "0";
			return;
		}
		if (nmCarta.equals("0")) {
			return;
		}

		lstContactoCarta = new ArrayList<Contacto>();
		objCarta = new Rubros();
		objCarta = srvRubrosCartas.recuperaCartaPorNombre(nmCarta);
		if (objCarta.getTipo_rubro().equals("ASEG")) {
			lstContactoCarta = srvContactosCarta
					.listaContactosAseguradora(Integer.valueOf(selectedConsultaPoliza.getCd_aseguradora()));
		} else {
			lstContactoCarta = srvContactosCarta
					.listaContactosCliente(Integer.valueOf(selectedConsultaPoliza.getCd_cliente()));
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
		carta.setCdCotizacion(Integer.valueOf(selectedConsultaPoliza.getCd_cotizacion()));
		carta.setCd_compania(Integer.valueOf(selectedConsultaPoliza.getCd_compania()));
		carta.setCdAseguradora(Integer.valueOf(selectedConsultaPoliza.getCd_aseguradora()));
		carta.setCdCliente(Integer.valueOf(selectedConsultaPoliza.getCd_cliente()));
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
		if (nmCarta.equals("RESUMENSEGUROSCORPORATIVO")) {
			String auxCnumCarM = srvCorrespondencia.codCorresMax(String.valueOf(usr.getUsrid()));
			System.out.println("REGISTRO EXITOSO/CARTA:" + auxCnumCarM);
			srvProcedimientos.formaPagoResumenCorporativo(auxCnumCarM, selectedConsultaPoliza.getCd_compania());
		}

		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Registro Exitoso", "Se Generó el Documento Nómero " + numeroCarta
				+ ". Ingrese al Módulo de Correspondecia para Imprimirlo"));
	}

	public void buscarDetallePago() {
		String codDetalleFrmPago;
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		codDetalleFrmPago = requestParameterMap.get("codDetPago");

		lstPagoRealizado = new ArrayList<ConsultaPagoRealizadosView>();

		try {
			lstPagoRealizado = srvPagosRealizados.consultaPagoCodFrmPAgo(codDetalleFrmPago);
		} catch (Exception ex) {
			//
		}
		System.out.println("TAMAóO:" + lstPagoRealizado.size());

		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('pagoDialog').show()");
		PrimeFaces.current().executeScript("PF('pagoDialog').show()");
		PrimeFaces.current().ajax().update("frm_consultas:tab_consulta:dtDetPAgo");
		// context.update("frm_consultas:tab_consulta:dtDetPAgo");
	}

	public void gestionaCliente() {
		try {
			String tamano = selectedConsultaPoliza.getPoliza();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Advertencia", "Seleccione la Póliza antes de realizar la gestión del Cliente"));
			nmCarta = "0";
			return;
		}
		cliente = new Clientes();
		direccion = new Direccion();
		telefono = new Telefono();
		contacto = new Contacto();

		cliente = srvClientes.listaClientesXId(Integer.valueOf(selectedConsultaPoliza.getCd_cliente()));

		try {
			direccion = srvDireccion.BuscaDireccionCodCliente(selectedConsultaPoliza.getCd_cliente());
		} catch (Exception e) {
			System.out.println("No existe dirección ingresada");
		}
		try {
			telefono = srvTelefono.BuscaTelefonoCodCliente(selectedConsultaPoliza.getCd_cliente());
		} catch (Exception e) {
			System.out.println("No existe telefono ingresada");
		}
		try {
			contacto = srvContacto.consultaContactosCdCliente(selectedConsultaPoliza.getCd_cliente());
		} catch (Exception e) {
			System.out.println("No existe contacto ingresada");
		}
		try {
			if (contacto.getCd_contacto() > 0) {
				contacto.setNombre_contacto("S/N");
				strNumerotelefono = contacto.getTelefono_contacto();
				strNumerocelular = contacto.getCelular_contacto();
				strcorreo = contacto.getMail_contacto();
				strCargo = contacto.getCargo_contacto();
				strDepartamento = contacto.getDepartamento_contacto();
				strDireccion = contacto.getDireccion_contacto();
			}
		} catch (Exception e) {
			contacto = new Contacto();
			contacto.setCd_cliente(Integer.valueOf(selectedConsultaPoliza.getCd_cliente()));
			srvContacto.insertarContacto(contacto);
			contacto = srvContacto.consultaContactosCdCliente(selectedConsultaPoliza.getCd_cliente());
		}

		lstPolizaGestionada = srvConsultaPolizaView.consultaPolizasCodCotiza(selectedConsultaPoliza.getCd_cotizacion());
		System.out.println("POLIZA:" + lstPolizaGestionada.size());
		lstGestion = new ArrayList<Gestion>();
		lstGestion = srvGestion.recuperaGestionPoliza(selectedConsultaPoliza.getCd_cotizacion());
		gestion = new Gestion();
		lstPlanRamAseg = new ArrayList<PlanRamoAseguradoraView>();
		lstPlanRamAseg.add(srvPlanesRamoAseg.consultaPlanRamoAseguradora(selectedConsultaPoliza.getCd_plan()));
		lstCalusulas = new ArrayList<ClausulasEmitidas>();
		lstCoberturas = new ArrayList<CoberturasEmitidas>();
		lstDeducibles = new ArrayList<DeduciblesEmitidas>();
		lstCalusulas = srvClausulas.recuperaClausulasEmitidas(Integer.valueOf(selectedConsultaPoliza.getCd_compania()),
				Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
		lstCoberturas = srvCoberturas.recuperaCoberturasEmitidas(
				Integer.valueOf(selectedConsultaPoliza.getCd_compania()),
				Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
		lstDeducibles = srvDeducibles.recuperaDeduciblesEmitidas(
				Integer.valueOf(selectedConsultaPoliza.getCd_compania()),
				Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
		PrimeFaces.current().executeScript("PF('gestionaClie').show();");

	}

	public void guardaGestion() {
		System.out.println("INGRESO A GESTION");
		Boolean flg = false;
		try {
			if (selectedUsuarios == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Advertencia", "Seleccione los Destinatarios"));
				return;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione los Destinatarios"));
			return;
		}

		try {
			if (fcPaga != null) {
				gestion.setFecha_pago(fcPaga);
				flg = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (fcSeg != null) {
				gestion.setFecha_seguimiento(fcSeg);
				flg = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (gestion.getInstruccion().isEmpty() || gestion.getInstruccion() == null) {
				gestion.setInstruccion("Sin Instrucción");
			}
		} catch (Exception e) {
			gestion.setInstruccion("Sin Instrucción");
		}
		try {
			System.out.println("ID:USUARIO:" + usuarioId);
			gestion.setUsrid(usuarioId);
			gestion.setNm_usuario(nmUsr);
		} catch (Exception e) {
			System.out.println("ERROR EN EL INGRESO DEL USUARIO");
			gestion.setUsrid("S/N");
			gestion.setNm_usuario("S/N");
		}

		gestion.setCd_cotizacion(Integer.valueOf(selectedConsultaPoliza.getCd_cotizacion()));
		gestion.setCd_ramo_cotizacion(Integer.valueOf(selectedConsultaPoliza.getCd_ramo_cotizacion()));
		System.out.println("Pasa Validación");
		srvGestion.insertarGestion(gestion);
		System.out.println("INSERTO GESTION");

		try {
			if (contacto.getNombre_contacto().isEmpty() || contacto.getNombre_contacto() == null) {
				contacto.setNombre_contacto("S/N");
			}
			contacto.setTelefono_contacto(strNumerotelefono);
			contacto.setCelular_contacto(strNumerocelular);
			contacto.setMail_contacto(strcorreo);
			contacto.setCargo_contacto(strCargo);
			contacto.setDepartamento_contacto(strDepartamento);
			contacto.setDireccion_contacto(strDireccion);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			srvContacto.actualizaContacto(contacto);
		} catch (Exception e) {
			System.out.println("Errora al actualizar contacto");
		}
		try {
			srvClientes.actualizaClientes(cliente);
		} catch (Exception e) {
			System.out.println("Errora al actualizar cliente");
		}
		try {
			srvDireccion.actualizaDireccion(direccion);
		} catch (Exception e) {
			System.out.println("Errora al actualizar Direccion");
		}
		try {
			srvTelefono.actualizaTelefono(telefono);
		} catch (Exception e) {
			System.out.println("Errora al actualizar Telefono");
		}
		System.out.println("--Ingresa Meeting Request");
		String user = "", ubc = "";
		Boolean fec = true;
		int cont = 0;
		Date fcMeeting = new Date();
		Date fcFin = new Date();

		// envio de mail
		if (flg == false) {
			for (String usrSel : selectedUsuarios) {
				if (cont == 0) {
					user = usrSel;
					System.out.println("Destinatarios correo:" + user);
				} else {
					user = user.concat(",");
					user = user.concat(usrSel);
					System.out.println("Destinatarios correo:" + user);
				}
				cont = cont + 1;
			}

			ubc = "Gestión Sistema - Póliza: ";
			ubc = ubc.concat(selectedConsultaPoliza.getPoliza());
			ubc = ubc.concat(" Factura:");
			ubc = ubc.concat(selectedConsultaPoliza.getFactura_aseguradora());

			email.setReceptor(user);
			email.setSubject(ubc);
			email.setTexto("<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Estimad@<o:p></o:p></span></p>"
					+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'>Se ha generado una nueva gestión en el sistema"
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
					+ "mso-bidi-theme-font:minor-latin'>Cliente:</span></b><span style='mso-ascii-font-family:"
					+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
					+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
					+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
					+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>" + selectedConsultaPoliza.getCliente()
					+ "<o:p></o:p></span></p>" + "</td>" + "</tr>"

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
					+ "mso-bidi-theme-font:minor-latin'>" + selectedConsultaPoliza.getNombre_corto_aseguradora()
					+ "<o:p></o:p></span></p>" + "</td>" + "</tr>"

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
					+ "mso-bidi-theme-font:minor-latin'>" + selectedConsultaPoliza.getDesc_ramo()
					+ "<o:p></o:p></span></p>" + "</td>" + "</tr>"

					+ "<tr style='mso-yfti-irow:4'>"
					+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>Póliza:</span></b><span style='mso-ascii-font-family:"
					+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
					+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
					+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
					+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>" + selectedConsultaPoliza.getPoliza()
					+ "<o:p></o:p></span></p>" + "</td>" + "</tr>"

					+ "<tr style='mso-yfti-irow:5'>"
					+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>Factura:</span></b><span style='mso-ascii-font-family:"
					+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
					+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
					+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
					+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>" + selectedConsultaPoliza.getFactura_aseguradora()
					+ "<o:p></o:p></span></p>" + "</td>" + "</tr>"

					+ "<tr style='mso-yfti-irow:6'>"
					+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>Tipo Instrucción:</span></b><span style='mso-ascii-font-family:"
					+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
					+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
					+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
					+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>" + gestion.getTipo() + "<o:p></o:p></span></p>" + "</td>"
					+ "</tr>"

					+ "<tr style='mso-yfti-irow:6'>"
					+ "<td width=250 style='width:187.5pt;padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><b><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>Asunto:</span></b><span style='mso-ascii-font-family:"
					+ "Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:Times New Roman;"
					+ "mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"
					+ "Calibri;mso-bidi-theme-font:minor-latin'><o:p></o:p></span></p>" + "</td>"
					+ "<td style='padding:.75pt .75pt .75pt .75pt'>"
					+ "<p class=MsoNormal><span style='mso-ascii-font-family:Calibri;mso-ascii-theme-font:"
					+ "minor-latin;mso-fareast-font-family:Times New Roman;mso-hansi-font-family:"
					+ "Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:Calibri;"
					+ "mso-bidi-theme-font:minor-latin'>" + asunto + "<o:p></o:p></span></p>" + "</td>" + "</tr>"

					+ "<tr style='mso-yfti-irow:7'>"
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
					+ "mso-bidi-theme-font:minor-latin'>" + gestion.getInstruccion() + "<o:p></o:p></span></p>"
					+ "</td>" + "</tr>"

					+ "<p><span style='font-family:Times New Roman,sans-serif;mso-ascii-theme-font:minor-latin;"
					+ "mso-hansi-theme-font:minor-latin;mso-bidi-theme-font:minor-latin'> " + " <o:p></o:p></span></p>"
					+ "<p><strong>Nota: </strong>"
					+ "Este mensaje ha sido generado automóticamente, por favor no lo responda." + "</p> ");
			email.sendEmail();

		} else {
			System.out.println("---- ENVIO MEETING REQUEST ORGANIZADOR----");
			// eMeeting.setSubject("Planificación de Reuniones");
			try {
				if (asunto.isEmpty() || asunto == null) {
					asunto = "Gestión Sistema";
				}
			} catch (Exception e) {
				asunto = "Gestión Sistema";
			}
			eMeeting.setSubject(asunto);
			System.out.println("********* tema_subject*********" + asunto);

			for (String usrSel : selectedUsuarios) {
				if (cont == 0) {
					user = usrSel;
					System.out.println("Destinatarios correo:" + user);
				} else {
					user = user.concat(",");
					user = user.concat(usrSel);
					System.out.println("Destinatarios correo:" + user);
				}
				cont = cont + 1;
			}
			eMeeting.setReceptor(user);
			ubc = "Gestión Sistema - Póliza: ";
			ubc = ubc.concat(selectedConsultaPoliza.getPoliza());
			ubc = ubc.concat(" Factura:");
			ubc = ubc.concat(selectedConsultaPoliza.getFactura_aseguradora());
			System.out.println("Ubicacion:" + ubc);
			eMeeting.setUbicacion(ubc);

			try {
				if (gestion.getFecha_pago() == null) {
					fec = false;
				}
			} catch (Exception e) {
				fec = false;
			}

			if (fec) {
				eMeeting.setCuerpo("<p> Este mensaje ha sido generado automóticamente, por favor no lo responda. </p>"
						+ "<p>Estimad@,</p>" + "Se ha planificado un nuevo evento " + " con la siguiente referencia:"
						+ " " + "<p><strong>Asunto: </strong>" + asunto + "</p> " + "<p><strong>Descripción: </strong>"
						+ " " + gestion.getInstruccion() + "</p> " + "<p><strong>Fecha Pago: </strong>"
						+ gestion.getFecha_pago() + "</p> " + "<p><strong>Cliente: </strong>"
						+ selectedConsultaPoliza.getCliente() + "</p> " + "<p><strong>Póliza: </strong>"
						+ selectedConsultaPoliza.getPoliza() + "</p> " + "<p><strong>Factura: </strong>"
						+ selectedConsultaPoliza.getFactura_aseguradora() + "</p> " + "<p><strong>Ramo: </strong>"
						+ selectedConsultaPoliza.getDesc_ramo() + "</p> " + "<p><strong>Aseguradora: </strong>"
						+ selectedConsultaPoliza.getNombre_corto_aseguradora() + "</p> ");

				eMeeting.setTitulo(asunto);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(gestion.getFecha_pago());
				calendar.add(Calendar.HOUR, 5);
				fcMeeting = calendar.getTime();

				Calendar calendarFin = Calendar.getInstance();
				calendarFin.setTime(gestion.getFecha_pago());
				calendarFin.add(Calendar.HOUR, 5);
				fcFin = calendarFin.getTime();
				System.out.println("FECHA AUMENTA HORA:" + fcMeeting);
				System.out.println("FECHA FIN HORA:" + fcFin);
			} else {
				eMeeting.setCuerpo("<p> Este mensaje ha sido generado automóticamente, por favor no lo responda. </p>"
						+ "<p>Estimad@,</p>" + "Se ha planificado un nuevo evento " + " con la siguiente referencia:"
						+ " " + "<p><strong>Asunto: </strong>" + asunto + "</p> " + "<p><strong>Descripción: </strong>"
						+ " " + gestion.getInstruccion() + "</p> " + "<p><strong>Fecha Seguimiento: </strong>"
						+ gestion.getFecha_seguimiento() + "</p> " + "<p><strong>Cliente: </strong>"
						+ selectedConsultaPoliza.getCliente() + "</p> " + "<p><strong>Póliza: </strong>"
						+ selectedConsultaPoliza.getPoliza() + "</p> " + "<p><strong>Factura: </strong>"
						+ selectedConsultaPoliza.getFactura_aseguradora() + "</p> " + "<p><strong>Ramo: </strong>"
						+ selectedConsultaPoliza.getDesc_ramo() + "</p> " + "<p><strong>Aseguradora: </strong>"
						+ selectedConsultaPoliza.getNombre_corto_aseguradora() + "</p> ");

				eMeeting.setTitulo(asunto);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(gestion.getFecha_seguimiento());
				calendar.add(Calendar.HOUR, 5);
				fcMeeting = calendar.getTime();

				Calendar calendarFin = Calendar.getInstance();
				calendarFin.setTime(gestion.getFecha_seguimiento());
				calendarFin.add(Calendar.HOUR, 5);
				fcFin = calendarFin.getTime();
				System.out.println("FECHA AUMENTA HORA:" + fcMeeting);
				System.out.println("FECHA FIN HORA:" + fcFin);
			}

			eMeeting.setStart(fcMeeting);
			eMeeting.setEnd(fcFin);

			eMeeting.sendMeeting();
			eMeeting = new MeetingSenderService();
		}
		gestion = new Gestion();
		lstGestion = new ArrayList<Gestion>();
		lstGestion = srvGestion.recuperaGestionPoliza(selectedConsultaPoliza.getCd_cotizacion());
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Advertencia", "Proceso Exitoso"));

	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public List<ConsultaPagoRealizadosView> getLstPagoRealizado() {
		return lstPagoRealizado;
	}

	public void setLstPagoRealizado(List<ConsultaPagoRealizadosView> lstPagoRealizado) {
		this.lstPagoRealizado = lstPagoRealizado;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
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

	public List<ConsultaObjetoPolView> getLstObjetosPoliza() {
		return lstObjetosPoliza;
	}

	public void setLstObjetosPoliza(List<ConsultaObjetoPolView> lstObjetosPoliza) {
		this.lstObjetosPoliza = lstObjetosPoliza;
	}

	public List<ConsultaUbicacionPolView> getLstUbicacionPoliza() {
		return lstUbicacionPoliza;
	}

	public void setLstUbicacionPoliza(List<ConsultaUbicacionPolView> lstUbicacionPoliza) {
		this.lstUbicacionPoliza = lstUbicacionPoliza;
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

	public ConsultaUbicacionPolView getSelectedUbicacionPoliza() {
		return selectedUbicacionPoliza;
	}

	public void setSelectedUbicacionPoliza(ConsultaUbicacionPolView selectedUbicacionPoliza) {
		this.selectedUbicacionPoliza = selectedUbicacionPoliza;
	}

	public ConsultaObjetoPolView getSelectedObjetosPoliza() {
		return selectedObjetosPoliza;
	}

	public void setSelectedObjetosPoliza(ConsultaObjetoPolView selectedObjetosPoliza) {
		this.selectedObjetosPoliza = selectedObjetosPoliza;
	}

	public List<ConsultaSubObjetoPolView> getLstSubObjetosPoliza() {
		return lstSubObjetosPoliza;
	}

	public void setLstSubObjetosPoliza(List<ConsultaSubObjetoPolView> lstSubObjetosPoliza) {
		this.lstSubObjetosPoliza = lstSubObjetosPoliza;
	}

	public String getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
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

	public Rubros getObjCarta() {
		return objCarta;
	}

	public void setObjCarta(Rubros objCarta) {
		this.objCarta = objCarta;
	}

	public String getrBotonPolAne() {
		return rBotonPolAne;
	}

	public void setrBotonPolAne(String rBotonPolAne) {
		this.rBotonPolAne = rBotonPolAne;
	}

	public List<TransaccionPolizaView> getLstTransaccionPoliza() {
		return lstTransaccionPoliza;
	}

	public void setLstTransaccionPoliza(List<TransaccionPolizaView> lstTransaccionPoliza) {
		this.lstTransaccionPoliza = lstTransaccionPoliza;
	}

	public Boolean getLbTransaccion() {
		return lbTransaccion;
	}

	public void setLbTransaccion(Boolean lbTransaccion) {
		this.lbTransaccion = lbTransaccion;
	}

	public List<NotasAclaratorias> getLstNotaAclaratoria() {
		return lstNotaAclaratoria;
	}

	public void setLstNotaAclaratoria(List<NotasAclaratorias> lstNotaAclaratoria) {
		this.lstNotaAclaratoria = lstNotaAclaratoria;
	}

	public String getObjAsegurado() {
		return objAsegurado;
	}

	public void setObjAsegurado(String objAsegurado) {
		this.objAsegurado = objAsegurado;
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

	public List<ConsultaPolizaView> getLstPolizaGestionada() {
		return lstPolizaGestionada;
	}

	public void setLstPolizaGestionada(List<ConsultaPolizaView> lstPolizaGestionada) {
		this.lstPolizaGestionada = lstPolizaGestionada;
	}

	public Gestion getGestion() {
		return gestion;
	}

	public void setGestion(Gestion gestion) {
		this.gestion = gestion;
	}

	public List<Gestion> getLstGestion() {
		return lstGestion;
	}

	public void setLstGestion(List<Gestion> lstGestion) {
		this.lstGestion = lstGestion;
	}

	public Date getFcPaga() {
		return fcPaga;
	}

	public void setFcPaga(Date fcPaga) {
		this.fcPaga = fcPaga;
	}

	public Date getFcSeg() {
		return fcSeg;
	}

	public void setFcSeg(Date fcSeg) {
		this.fcSeg = fcSeg;
	}

	public List<PlanRamoAseguradoraView> getLstPlanRamAseg() {
		return lstPlanRamAseg;
	}

	public void setLstPlanRamAseg(List<PlanRamoAseguradoraView> lstPlanRamAseg) {
		this.lstPlanRamAseg = lstPlanRamAseg;
	}

	public List<ClausulasEmitidas> getLstCalusulas() {
		return lstCalusulas;
	}

	public void setLstCalusulas(List<ClausulasEmitidas> lstCalusulas) {
		this.lstCalusulas = lstCalusulas;
	}

	public List<CoberturasEmitidas> getLstCoberturas() {
		return lstCoberturas;
	}

	public void setLstCoberturas(List<CoberturasEmitidas> lstCoberturas) {
		this.lstCoberturas = lstCoberturas;
	}

	public List<DeduciblesEmitidas> getLstDeducibles() {
		return lstDeducibles;
	}

	public void setLstDeducibles(List<DeduciblesEmitidas> lstDeducibles) {
		this.lstDeducibles = lstDeducibles;
	}

	public List<Usuarios> getLstUsuarios() {
		return lstUsuarios;
	}

	public void setLstUsuarios(List<Usuarios> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

	public String[] getSelectedUsuarios() {
		return selectedUsuarios;
	}

	public void setSelectedUsuarios(String[] selectedUsuarios) {
		this.selectedUsuarios = selectedUsuarios;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getStrNombreContacto() {
		return strNombreContacto;
	}

	public void setStrNombreContacto(String strNombreContacto) {
		this.strNombreContacto = strNombreContacto;
	}

	public String getStrNumerotelefono() {
		return strNumerotelefono;
	}

	public void setStrNumerotelefono(String strNumerotelefono) {
		this.strNumerotelefono = strNumerotelefono;
	}

	public String getStrNumerocelular() {
		return strNumerocelular;
	}

	public void setStrNumerocelular(String strNumerocelular) {
		this.strNumerocelular = strNumerocelular;
	}

	public String getStrcorreo() {
		return strcorreo;
	}

	public void setStrcorreo(String strcorreo) {
		this.strcorreo = strcorreo;
	}

	public String getStrCargo() {
		return strCargo;
	}

	public void setStrCargo(String strCargo) {
		this.strCargo = strCargo;
	}

	public String getStrDepartamento() {
		return strDepartamento;
	}

	public void setStrDepartamento(String strDepartamento) {
		this.strDepartamento = strDepartamento;
	}

	public String getStrDireccion() {
		return strDireccion;
	}

	public void setStrDireccion(String strDireccion) {
		this.strDireccion = strDireccion;
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

	public List<Archivos> getLstArchivos() {
		return lstArchivos;
	}

	public void setLstArchivos(List<Archivos> lstArchivos) {
		this.lstArchivos = lstArchivos;
	}

	public List<Aseguradoras> getListAseguradoras() {
		return listAseguradoras;
	}

	public void setListAseguradoras(List<Aseguradoras> listAseguradoras) {
		this.listAseguradoras = listAseguradoras;
	}

	public List<Ramo> getLstRamo() {
		return lstRamo;
	}

	public void setLstRamo(List<Ramo> lstRamo) {
		this.lstRamo = lstRamo;
	}

	public String getLscdAseguradora() {
		return lscdAseguradora;
	}

	public void setLscdAseguradora(String lscdAseguradora) {
		this.lscdAseguradora = lscdAseguradora;
	}

	public String getLscdRamo() {
		return lscdRamo;
	}

	public void setLscdRamo(String lscdRamo) {
		this.lscdRamo = lscdRamo;
	}

	public boolean isCaduca() {
		return caduca;
	}

	public void setCaduca(boolean caduca) {
		this.caduca = caduca;
	}

	public List<ConsultaObjetoPolView> getFilteredLstlstObjetosPoliza() {
		return filteredLstlstObjetosPoliza;
	}

	public void setFilteredLstlstObjetosPoliza(List<ConsultaObjetoPolView> filteredLstlstObjetosPoliza) {
		this.filteredLstlstObjetosPoliza = filteredLstlstObjetosPoliza;
	}

	public List<ConsultaSubObjetoPolView> getFilteredLstSubObjetosPoliza() {
		return filteredLstSubObjetosPoliza;
	}

	public void setFilteredLstSubObjetosPoliza(List<ConsultaSubObjetoPolView> filteredLstSubObjetosPoliza) {
		this.filteredLstSubObjetosPoliza = filteredLstSubObjetosPoliza;
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

	public FormaPago getFrFormaPago() {
		return frFormaPago;
	}

	public void setFrFormaPago(FormaPago frFormaPago) {
		this.frFormaPago = frFormaPago;
	}
	
}
