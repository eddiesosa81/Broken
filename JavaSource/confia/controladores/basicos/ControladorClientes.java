package confia.controladores.basicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import confia.entidades.basicos.Ciudad;
import confia.entidades.basicos.Clientes;
import confia.entidades.basicos.Contacto;
import confia.entidades.basicos.Direccion;
import confia.entidades.basicos.Provincias;
import confia.entidades.basicos.Rubros;
import confia.entidades.basicos.Telefono;
import confia.servicios.basicos.ServicioCiudad;
import confia.servicios.basicos.ServicioClientes;
import confia.servicios.basicos.ServicioContacto;
import confia.servicios.basicos.ServicioDireccion;
import confia.servicios.basicos.ServicioProvincias;
import confia.servicios.basicos.ServicioRubros;
import confia.servicios.basicos.ServicioTelefono;

@ViewScoped
@ManagedBean

public class ControladorClientes {

	@EJB
	private ServicioClientes srvClientes;
	@EJB
	private ServicioTelefono srvTelefono;
	@EJB
	private ServicioDireccion srvDireccion;
	@EJB
	private ServicioContacto srvContacto;
	@EJB
	private ServicioCiudad srvCiudad;
	@EJB
	private ServicioRubros srvRubros;
	@EJB
	private ServicioProvincias srvProvincias;

	private List<Clientes> lstClientes;
	private List<Telefono> lstTelefono;
	private List<Direccion> lstDireccion;
	private List<Contacto> lstContacto;
	private List<Ciudad> lstCiudad;
	private List<Rubros> lsrRubroSectorDirec;
	private List<Clientes> filteredLstClientes;
	private List<Provincias> lstProvincias;

	private Direccion selectedDireccion;
	private Telefono selectedTelefono;
	private Clientes selectedCliente;
	private Contacto selectedContacto;

	private List<Telefono> selectedLstTelefono;
	private List<Clientes> selectedlstClientes;
	private List<Direccion> selectedLstDireccion;
	private List<Contacto> selectedLstContacto;

	private Clientes getServObj;
	private Telefono getServObjtel;
	private Direccion getServObjDir;
	private Contacto getServObjCont;

	private String nombre_corto_aseguradora;
	private Integer ruc_aseguradora;
	private String razon_social_aseguradora;

	private Integer cd_aseguradoraTel;
	private String cd_ciudad;
	private String tipo;
	private String telefono;
	private String telefonoCelu;
	private String telefonoOfi;
	private String ext;
	private String nombrerelacion;
	private String correo;
	private String cd_ciudadCons;
	private String tipoCons;
	private String telefonoCons;
	private String extCons;
	private String nombrerelacionCons;
	private String correoCons;

	private Integer cd_aseguradoraDir;
	private String direccion;
	private String referencia;
	private String direccionCons;
	private String referenciaCons;

	private Integer cd_aseguradoraCont;
	private String telefono_contacto;
	private String celular_contacto;
	private String mail_contacto;
	private String cargo_contacto;
	private String departamento_contacto;
	private String direccion_contacto;
	private String telefono_contactoCons;
	private String ciudad;

	private String celular_contactoCons;
	private String mail_contactoCons;
	private String cargo_contactoCons;
	private String departamento_contactoCons;
	private String direccion_contactoCons;
	private String nombreContacto;
	private String codProv;
	private String codCiud;
	private String txtCd_rubro;
	private Integer cd_rubro;
	private Boolean flgTelef;
	private Boolean flgDir;

	public ControladorClientes() {
		lstClientes = new ArrayList<Clientes>();
		lstTelefono = new ArrayList<Telefono>();
		lstDireccion = new ArrayList<Direccion>();
		lstContacto = new ArrayList<Contacto>();
		lstCiudad = new ArrayList<Ciudad>();
		lsrRubroSectorDirec = new ArrayList<Rubros>();
		lstProvincias = new ArrayList<Provincias>();
	}

	@PostConstruct
	public void recuperaDatos() {
		lstClientes = srvClientes.listaClientesCorrespon();
		lstCiudad = srvCiudad.recuperaListaCiudad();
		lsrRubroSectorDirec = srvRubros.listadoRubrosCod("102");
		lstProvincias = srvProvincias.listaProvincias();
		flgTelef = false;
		flgDir = false;
	}

	public void onRowSelectAsegu(SelectEvent event) {
		lstTelefono = srvTelefono.BuscaTelefonosCliente(((Clientes) event.getObject()).getCd_cliente());
		if (lstTelefono.size() > 0)
			flgTelef = true;
		else
			flgTelef = false;
		lstDireccion = srvDireccion.BuscaDireccionCliente(((Clientes) event.getObject()).getCd_cliente());
		if (lstDireccion.size() > 0)
			flgDir = true;
		else
			flgDir = false;
		lstContacto = srvContacto.listaContactosCliente(((Clientes) event.getObject()).getCd_cliente());
		// System.out.println("INGRESOOOOOO");
		// System.out.println("HOLA+++++++: "+ ((Aseguradoras)
		// event.getObject()).getCd_aseguradora());
		// Integer cdAseg = ((Telefono)event.getObject()).getCd_aseguradora();
		// lstTelefono = new ArrayList<Telefono>();
		// lstTelefono = srvTelefono.BuscaTelefonos(cdAseg);
		// System.out.println("cdAseg: "+ cdAseg);

	}

	public void AgregarAseguradora() {
		flgTelef = false;
		flgDir = false;
		nombre_corto_aseguradora = null;
		ruc_aseguradora = null;
		razon_social_aseguradora = null;
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgDatCons').show();");
		PrimeFaces.current().executeScript("PF('wDlgDatCons').show();");
	}

	public void AgregarTelefono() {

		try {
			selectedCliente.getCd_cliente();
		} catch (Exception e) {
			System.out.println("error aseguradora+++++++++" + e);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione una aseguradora"));
			return;
		}

		tipo = null;
		telefono = null;
		telefonoCelu = null;
		telefonoOfi = null;
		ext = null;
		nombrerelacion = null;
		correo = null;

		lstTelefono = new ArrayList<Telefono>();
		lstTelefono = srvTelefono.consultaTelefono();
		System.out.println("TAMAÑO:" + lstTelefono.size());

		if (!flgTelef) {
//			RequestContext contextDlg = RequestContext.getCurrentInstance();
//			contextDlg.execute("PF('wDlgDatTelef').show();");
			PrimeFaces.current().executeScript("PF('wDlgDatTelef').show();");
		}

	}

	public void AgregarDireccion() {

		try {
			selectedCliente.getCd_cliente();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione una aseguradora"));
			return;
		}
		direccion = null;
		referencia = null;

		lstDireccion = new ArrayList<Direccion>();
		lstDireccion = srvDireccion.consultaDireccion();
		if (!flgDir) {
//			RequestContext contextDlg = RequestContext.getCurrentInstance();
//			contextDlg.execute("PF('wDlgDatDirec').show();");
			PrimeFaces.current().executeScript("PF('wDlgDatDirec').show();");
		}
	}

	public void AgregarContacto() {

		try {
			selectedCliente.getCd_cliente();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Advertencia", "Seleccione una aseguradora"));
			return;
		}

		telefono_contacto = null;
		celular_contacto = null;
		mail_contacto = null;
		cargo_contacto = null;
		departamento_contacto = null;
		direccion_contacto = null;

		lstContacto = new ArrayList<Contacto>();
		lstContacto = srvContacto.consultaContactos();
//		RequestContext contextDlg = RequestContext.getCurrentInstance();
//		contextDlg.execute("PF('wDlgContact').show();");
		PrimeFaces.current().executeScript("PF('wDlgContact').show();");
	}

	public void guardaTelefonos() {
		Telefono tel = new Telefono();
		tel.setCd_aseguradora(0);
		tel.setCd_cliente(selectedCliente.getCd_cliente());
		tel.setTipo(tipo);
		tel.setTelefono(telefono);
		tel.setExt(ext);
		tel.setNombrerelacion(nombrerelacion);
		tel.setActestado("A");
		tel.setCorreo(correo);
		tel.setTelefono_celular(telefonoCelu);
		tel.setTelefono_oficina(telefonoOfi);
		tel.setTipo("CONVENCIONAL");
		srvTelefono.insertarTelefonos(tel);
		lstTelefono = new ArrayList<Telefono>();
		lstTelefono = srvTelefono.BuscaTelefonosCliente(selectedCliente.getCd_cliente());
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Guardado Exitosamente"));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgDatTelef').hide();");
		PrimeFaces.current().executeScript("PF('wDlgDatTelef').hide();");

	}

	public void guardaDireccion() {
		Direccion dir = new Direccion();
		dir.setActestado("A");
		dir.setCd_cliente(selectedCliente.getCd_cliente());
		dir.setCd_aseguradora(0);
		dir.setDireccion(direccion);
		dir.setReferencia(referencia);
		dir.setCd_provincia(codProv);
		System.out.println("ciudad" + ciudad);
		dir.setCd_ciudad(Integer.valueOf(ciudad));
		System.out.println("cdrubro" + cd_rubro);
		dir.setCd_rubro(cd_rubro);
		srvDireccion.insertarDireccion(dir);
		lstDireccion = new ArrayList<Direccion>();
		lstDireccion = srvDireccion.BuscaDireccionCliente(selectedCliente.getCd_cliente());
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Guardado Exitosamente"));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgDatDirec').hide();");
		PrimeFaces.current().executeScript("PF('wDlgDatDirec').hide();");

	}

	public void guardaContacto() {
		Contacto contac = new Contacto();
		contac.setActestado("A");
		contac.setCd_cliente(selectedCliente.getCd_cliente());
		contac.setCd_aseguradora(0);
		contac.setNombre_contacto(nombreContacto);
		contac.setTelefono_contacto(telefono_contacto);
		contac.setCelular_contacto(celular_contacto);
		contac.setMail_contacto(mail_contacto);
		contac.setCargo_contacto(cargo_contacto);
		contac.setDepartamento_contacto(departamento_contacto);
		contac.setDireccion_contacto(direccion_contacto);
		contac.setCiudad(ciudad);
		srvContacto.insertarContacto(contac);
		lstContacto = new ArrayList<Contacto>();
		lstContacto = srvContacto.listaContactosCliente(selectedCliente.getCd_cliente());
		FacesContext contextMsj = FacesContext.getCurrentInstance();
		contextMsj.addMessage(null, new FacesMessage("Advertencia", "Registro Guardado Exitosamente"));
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('wDlgContact').hide();");
		PrimeFaces.current().executeScript("PF('wDlgContact').hide();");

	}

	public void EliminaTelefono(Telefono telefonoSel) {
		srvTelefono.eliminaTelefono(telefonoSel);
		lstTelefono = new ArrayList<Telefono>();

	}

	public void EliminaDireccion(Direccion direccionSel) {
		srvDireccion.eliminaDireccion(direccionSel);
		lstDireccion = new ArrayList<Direccion>();
	}

	public void EliminaContacto(Contacto contactoSel) {
		srvContacto.eliminaContacto(contactoSel);
		lstContacto = new ArrayList<Contacto>();
	}

	public void onEdit(RowEditEvent event) {
		// getServObj = ((Clientes) event.getObject());
		// srvAseguradora.actualizaAseguradoras(getServObj);
		// FacesMessage msg = new FacesMessage("Registro Editado");
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onEditTelefono(RowEditEvent event) {
		getServObjtel = ((Telefono) event.getObject());
		srvTelefono.actualizaTelefono(getServObjtel);
		FacesMessage msg = new FacesMessage("Registro Editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onEditDireccion(RowEditEvent event) {
		getServObjDir = ((Direccion) event.getObject());
		srvDireccion.actualizaDireccion(getServObjDir);
		FacesMessage msg = new FacesMessage("Registro Editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onEditContacto(RowEditEvent event) {
		getServObjCont = ((Contacto) event.getObject());
		srvContacto.actualizaContacto(getServObjCont);
		FacesMessage msg = new FacesMessage("Registro Editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void Salir() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ctx.redirect("./indexGestion.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNombre_corto_aseguradora() {
		return nombre_corto_aseguradora;
	}

	public void setNombre_corto_aseguradora(String nombre_corto_aseguradora) {
		this.nombre_corto_aseguradora = nombre_corto_aseguradora;
	}

	public Integer getRuc_aseguradora() {
		return ruc_aseguradora;
	}

	public void setRuc_aseguradora(Integer ruc_aseguradora) {
		this.ruc_aseguradora = ruc_aseguradora;
	}

	public String getRazon_social_aseguradora() {
		return razon_social_aseguradora;
	}

	public void setRazon_social_aseguradora(String razon_social_aseguradora) {
		this.razon_social_aseguradora = razon_social_aseguradora;
	}

	public List<Clientes> getlstClientes() {
		return lstClientes;
	}

	public void setlstClientes(List<Clientes> lstClientes) {
		this.lstClientes = lstClientes;
	}

	public Clientes getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(Clientes selectedCliente) {
		this.selectedCliente = selectedCliente;
	}

	public List<Clientes> getSelectedlstClientes() {
		return selectedlstClientes;
	}

	public void setSelectedlstClientes(List<Clientes> selectedlstClientes) {
		this.lstClientes = selectedlstClientes;
	}

	public List<Telefono> getLstTelefono() {
		return lstTelefono;
	}

	public void setLstTelefono(List<Telefono> lstTelefono) {
		this.lstTelefono = lstTelefono;
	}

	public Telefono getSelectedTelefono() {
		return selectedTelefono;
	}

	public void setSelectedTelefono(Telefono selectedTelefono) {
		this.selectedTelefono = selectedTelefono;
	}

	public List<Telefono> getSelectedLstTelefono() {
		return selectedLstTelefono;
	}

	public void setSelectedLstTelefono(List<Telefono> selectedLstTelefono) {
		this.selectedLstTelefono = selectedLstTelefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getNombrerelacion() {
		return nombrerelacion;
	}

	public void setNombrerelacion(String nombrerelacion) {
		this.nombrerelacion = nombrerelacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Telefono getGetServObjtel() {
		return getServObjtel;
	}

	public void setGetServObjtel(Telefono getServObjtel) {
		this.getServObjtel = getServObjtel;
	}

	public List<Direccion> getLstDireccion() {
		return lstDireccion;
	}

	public void setLstDireccion(List<Direccion> lstDireccion) {
		this.lstDireccion = lstDireccion;
	}

	public Direccion getSelectedDireccion() {
		return selectedDireccion;
	}

	public void setSelectedDireccion(Direccion selectedDireccion) {
		this.selectedDireccion = selectedDireccion;
	}

	public List<Direccion> getSelectedLstDireccion() {
		return selectedLstDireccion;
	}

	public void setSelectedLstDireccion(List<Direccion> selectedLstDireccion) {
		this.selectedLstDireccion = selectedLstDireccion;
	}

	public Direccion getGetServObjDir() {
		return getServObjDir;
	}

	public void setGetServObjDir(Direccion getServObjDir) {
		this.getServObjDir = getServObjDir;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<Contacto> getLstContacto() {
		return lstContacto;
	}

	public void setLstContacto(List<Contacto> lstContacto) {
		this.lstContacto = lstContacto;
	}

	public Contacto getSelectedContacto() {
		return selectedContacto;
	}

	public void setSelectedContacto(Contacto selectedContacto) {
		this.selectedContacto = selectedContacto;
	}

	public List<Contacto> getSelectedLstContacto() {
		return selectedLstContacto;
	}

	public void setSelectedLstContacto(List<Contacto> selectedLstContacto) {
		this.selectedLstContacto = selectedLstContacto;
	}

	public Contacto getGetServObjCont() {
		return getServObjCont;
	}

	public void setGetServObjCont(Contacto getServObjCont) {
		this.getServObjCont = getServObjCont;
	}

	public String getTelefono_contacto() {
		return telefono_contacto;
	}

	public void setTelefono_contacto(String telefono_contacto) {
		this.telefono_contacto = telefono_contacto;
	}

	public String getCelular_contacto() {
		return celular_contacto;
	}

	public void setCelular_contacto(String celular_contacto) {
		this.celular_contacto = celular_contacto;
	}

	public String getMail_contacto() {
		return mail_contacto;
	}

	public void setMail_contacto(String mail_contacto) {
		this.mail_contacto = mail_contacto;
	}

	public String getCargo_contacto() {
		return cargo_contacto;
	}

	public void setCargo_contacto(String cargo_contacto) {
		this.cargo_contacto = cargo_contacto;
	}

	public String getDepartamento_contacto() {
		return departamento_contacto;
	}

	public void setDepartamento_contacto(String departamento_contacto) {
		this.departamento_contacto = departamento_contacto;
	}

	public String getDireccion_contacto() {
		return direccion_contacto;
	}

	public void setDireccion_contacto(String direccion_contacto) {
		this.direccion_contacto = direccion_contacto;
	}

	public String getTelefonoCons() {
		return telefonoCons;
	}

	public void setTelefonoCons(String telefonoCons) {
		this.telefonoCons = telefonoCons;
	}

	public String getExtCons() {
		return extCons;
	}

	public void setExtCons(String extCons) {
		this.extCons = extCons;
	}

	public String getNombrerelacionCons() {
		return nombrerelacionCons;
	}

	public void setNombrerelacionCons(String nombrerelacionCons) {
		this.nombrerelacionCons = nombrerelacionCons;
	}

	public String getCorreoCons() {
		return correoCons;
	}

	public void setCorreoCons(String correoCons) {
		this.correoCons = correoCons;
	}

	public String getDireccionCons() {
		return direccionCons;
	}

	public void setDireccionCons(String direccionCons) {
		this.direccionCons = direccionCons;
	}

	public String getReferenciaCons() {
		return referenciaCons;
	}

	public void setReferenciaCons(String referenciaCons) {
		this.referenciaCons = referenciaCons;
	}

	public String getTelefono_contactoCons() {
		return telefono_contactoCons;
	}

	public void setTelefono_contactoCons(String telefono_contactoCons) {
		this.telefono_contactoCons = telefono_contactoCons;
	}

	public String getCelular_contactoCons() {
		return celular_contactoCons;
	}

	public void setCelular_contactoCons(String celular_contactoCons) {
		this.celular_contactoCons = celular_contactoCons;
	}

	public String getMail_contactoCons() {
		return mail_contactoCons;
	}

	public void setMail_contactoCons(String mail_contactoCons) {
		this.mail_contactoCons = mail_contactoCons;
	}

	public String getCargo_contactoCons() {
		return cargo_contactoCons;
	}

	public void setCargo_contactoCons(String cargo_contactoCons) {
		this.cargo_contactoCons = cargo_contactoCons;
	}

	public String getDepartamento_contactoCons() {
		return departamento_contactoCons;
	}

	public void setDepartamento_contactoCons(String departamento_contactoCons) {
		this.departamento_contactoCons = departamento_contactoCons;
	}

	public String getDireccion_contactoCons() {
		return direccion_contactoCons;
	}

	public void setDireccion_contactoCons(String direccion_contactoCons) {
		this.direccion_contactoCons = direccion_contactoCons;
	}

	public String getTipoCons() {
		return tipoCons;
	}

	public void setTipoCons(String tipoCons) {
		this.tipoCons = tipoCons;
	}

	public String getCd_ciudad() {
		return cd_ciudad;
	}

	public void setCd_ciudad(String cd_ciudad) {
		this.cd_ciudad = cd_ciudad;
	}

	public List<Ciudad> getLstCiudad() {
		return lstCiudad;
	}

	public void setLstCiudad(List<Ciudad> lstCiudad) {
		this.lstCiudad = lstCiudad;
	}

	public String getCd_ciudadCons() {
		return cd_ciudadCons;
	}

	public void setCd_ciudadCons(String cd_ciudadCons) {
		this.cd_ciudadCons = cd_ciudadCons;
	}

	public List<Rubros> getLsrRubroSectorDirec() {
		return lsrRubroSectorDirec;
	}

	public void setLsrRubroSectorDirec(List<Rubros> lsrRubroSectorDirec) {
		this.lsrRubroSectorDirec = lsrRubroSectorDirec;
	}

	public String getTxtCd_rubro() {
		return txtCd_rubro;
	}

	public void setTxtCd_rubro(String txtCd_rubro) {
		this.txtCd_rubro = txtCd_rubro;
	}

	public Integer getCd_rubro() {
		return cd_rubro;
	}

	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}

	public Integer getCd_aseguradoraTel() {
		return cd_aseguradoraTel;
	}

	public void setCd_aseguradoraTel(Integer cd_aseguradoraTel) {
		this.cd_aseguradoraTel = cd_aseguradoraTel;
	}

	public Integer getCd_aseguradoraDir() {
		return cd_aseguradoraDir;
	}

	public void setCd_aseguradoraDir(Integer cd_aseguradoraDir) {
		this.cd_aseguradoraDir = cd_aseguradoraDir;
	}

	public Integer getCd_aseguradoraCont() {
		return cd_aseguradoraCont;
	}

	public void setCd_aseguradoraCont(Integer cd_aseguradoraCont) {
		this.cd_aseguradoraCont = cd_aseguradoraCont;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Clientes> getFilteredLstClientes() {
		return filteredLstClientes;
	}

	public void setFilteredLstClientes(List<Clientes> filteredLstClientes) {
		this.filteredLstClientes = filteredLstClientes;
	}

	public List<Provincias> getLstProvincias() {
		return lstProvincias;
	}

	public void setLstProvincias(List<Provincias> lstProvincias) {
		this.lstProvincias = lstProvincias;
	}

	public String getTelefonoCelu() {
		return telefonoCelu;
	}

	public void setTelefonoCelu(String telefonoCelu) {
		this.telefonoCelu = telefonoCelu;
	}

	public String getTelefonoOfi() {
		return telefonoOfi;
	}

	public void setTelefonoOfi(String telefonoOfi) {
		this.telefonoOfi = telefonoOfi;
	}

	public String getCodProv() {
		return codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getCodCiud() {
		return codCiud;
	}

	public void setCodCiud(String codCiud) {
		this.codCiud = codCiud;
	}

}
