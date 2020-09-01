package confia.controladores.transaccionales;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import confia.entidades.transaccionales.EmisionMasiva;
import confia.procedures.subeArchivo;
import confia.servicios.transaccionales.ServicioEmisionesMasivas;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@ManagedBean(name = "ControladorEmisionesMasivas")
@ViewScoped
public class ControladorEmisionesMasivas {
	@EJB
	private ServicioEmisionesMasivas srvEmisionMasiva;
	private String usuarioId;
	private Date fcProceso;
	private Date fcCarga;
	private List<EmisionMasiva> lstEmisionMasiva;
	public String asFechaProc;
	public String asFechaCarg;
	public SimpleDateFormat formato;
	public String patron = "dd/MM/yyyy";
	
	private subeArchivo archivoAdjunto;
	public ControladorEmisionesMasivas() {
		archivoAdjunto = new subeArchivo();
		fcProceso = new Date();
		fcCarga = new Date();
		lstEmisionMasiva = new ArrayList<EmisionMasiva>();
		formato = new SimpleDateFormat(patron);
	}
	
	public void subirArchivos(FileUploadEvent evt) {
		// UploadedFile miArchivo; // aqui se va a guardar el archivo que
		// escogemos en el componente
		String extension = "";
		SimpleDateFormat formatoDos;
		String patronDos = "dd-MM-yyyy";
		formatoDos = new SimpleDateFormat(patronDos);

		UploadedFile miArchivo = evt.getFile();
		long tamanio = miArchivo.getSize();// tamaño del archivo
		byte[] contenido = miArchivo.getContent();// contenido del archivo
		String tipoDeArchivo = miArchivo.getContentType();// que tipo de
															// archivo
		String nombre = miArchivo.getFileName();
		String ruta;
		extension = nombre.substring(nombre.lastIndexOf('.'));
		nombre = nombre.substring(0, nombre.lastIndexOf('.'));

		nombre = nombre.replaceAll("[^\\p{Alpha}\\p{Digit}]+", "_") + extension;

		System.out.println("-*****------- Tamaño: " + tamanio);
		System.out.println("-*****------- Contenido: " + contenido);
		System.out.println("-*****------- Tipo de Archivo: " + tipoDeArchivo);
		System.out.println("********** Nombre: " + nombre);

		archivoAdjunto.guardarArchivo(nombre, contenido);
		ruta = "C:/java/wildfly-9.0.2.Final/welcome-content/uploads/";
		System.out.println("********** Ruta: " + ruta);
		ruta = ruta.concat(nombre);
		try {
			readXLSXFile(ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// actualiza la lista de objetos ingresados
//		lstEmisionMasiva = 
//		lstObjetoCot = srvObjetoCotizacion.recuperaObjetosPorUbicacion(datosUbicacion.getCd_ubicacion(),
//				datosUbicacion.getCd_compania());
//		FacesMessage message = new FacesMessage("Succesful", evt.getFile().getFileName() + " is uploaded.");
//		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void readXLSXFile(String path) throws IOException {
		File inputWorkbook = new File(path);
		Workbook w;
		int flagEmpty;
		String valCampo;
		List<EmisionMasiva> lstEmiAux = new ArrayList<EmisionMasiva>();
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				if (i != 0) {
					EmisionMasiva archivo = new EmisionMasiva();
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
								archivo.setTIPO_PERSONA_CLIENTE(valCampo);
							}
							break;
						case 1:
							if (type == CellType.EMPTY) {
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTIPO_IDENTIFICACION_CLIENTE(valCampo);
							}

							break;
						case 2:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setIDENTIFICACION_CLIENTE(valCampo);
							}
							break;
						case 3:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPRIMER_NOMBRE_CLIENTE(valCampo);
							}
							break;
						case 4:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setSEGUNDO_NOMBRE_CLIENTE(valCampo);
							}
							break;
						case 5:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPRIMER_APELLIDO_CLIENTE(valCampo);
							}
							break;
						case 6:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setSEGUNDO_APELLIDO_CLIENTE(valCampo);
							}
							break;
						case 7:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setRAZON_SOCIAL_CLIENTE(valCampo);
							}
							break;
						case 8:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setGENERO_CLIENTE(valCampo);
							}
							break;
						case 9:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setESTADO_CIVIL_CLIENTE(valCampo);
							}
							break;
						case 10:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTIPO_CLIENTE(valCampo);
							}
							break;
						case 11:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCD_NACIONALIDAD(valCampo);
							}
							break;
						case 12:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setEMPRESA(valCampo);
							}
							break;
						case 13:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCD_ASEGURADORA(valCampo);
							}
							break;
						case 14:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCD_RAMO(valCampo);
							}
							break;
						case 15:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCD_EJECUTIVO(valCampo);
							}
							break;
						case 16:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCD_CANAL(valCampo);
							}
							break;
						case 17:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCD_PLAN(valCampo);
							}
							break;
						case 18:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setCD_GRUPO_CONTRATANTE(valCampo);
							}
							break;
						case 19:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setPOLIZA(valCampo);
							}
							break;
						case 20:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setFACTURA_ASEGURADORA(valCampo);
							}
							break;
						case 21:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setFC_INI_VIG_DATE(valCampo);
							}
							break;
						case 22:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setFC_FIN_VIG_DATE(valCampo);
							}
							break;
						case 23:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDIAS_VIGENCIA(valCampo);
							}
							break;
						case 24:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTOTAL_ASEGURADO(valCampo);
							}
							break;
						case 25:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setTOTAL_PRIMA(valCampo);
							}
							break;
						case 26:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setANEXO(valCampo);
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
								archivo.setFC_EMISION_ASEGURADORA_DATE(valCampo);
							}
							break;
						case 28:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDSC_UBICACION(valCampo);
							}
							break;
						case 29:
							if (type == CellType.EMPTY) {

								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}

							if (flagEmpty != 1) {
								archivo.setDESCRIPCION_OBJETO(valCampo);
							}
							break;
						case 30:
							if (type == CellType.EMPTY) {
	
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
	
							if (flagEmpty != 1) {
								archivo.setVALOR_ASEGURADOR_OBJETO(valCampo);
							}
							break;
						case 31:
							if (type == CellType.EMPTY) {
		
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
		
							if (flagEmpty != 1) {
								archivo.setTASA_OBJETO(valCampo);
							}
							break;
						case 32:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setFACTOR_OBJETO(valCampo);
							}
							break;
						case 33:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setPRIMA_OBJETO(valCampo);
							}
							break;
						case 34:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setOBSERVACIONES_OBJETO(valCampo);
							}
							break;
						case 35:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setDEDUCIBLE_MINIMO(valCampo);
							}
							break;
						case 36:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setNUM_ALTERNATIVA_FORMAPAGO(valCampo);
							}
							break;
						case 37:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setNUM_PAGOS_FORMAPAGO(valCampo);
							}
							break;
						case 38:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setTOTAL_PRIMA_FORMAPAGO(valCampo);
							}
							break;
						case 39:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setTOTAL_PAGO_FORMAPAGO(valCampo);
							}
							break;
						case 40:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setDERECHO_EMISION_FORMAPAGO(valCampo);
							}
							break;
						case 41:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setIVA_FORMAPAGO(valCampo);
							}
							break;
						case 42:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setSUPER_BANCOS_FORMAPAGO(valCampo);
							}
							break;
						case 43:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setSEGURO_CAMPESINO(valCampo);
							}
							break;
						case 44:
							if (type == CellType.EMPTY) {
			
								flagEmpty = 1;
							} else {
								valCampo = cell.getContents();
							}
			
							if (flagEmpty != 1) {
								archivo.setOBSERVACIONES(valCampo);
							}
							break;
			
						}
		
					}
					// insert usuario y estado
					
					usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
					System.out.println("GUARDAR CARTA USuario:" + usuarioId);
					archivo.setUsrlogin(usuarioId);
					archivo.setESTADO("PENDIENTE");
					lstEmiAux.add(archivo);
				}
			}

			System.out.println("TAMAÑO ARRAY:" + lstEmiAux.size());
		} catch (BiffException e) {
			e.printStackTrace();
		}
		// guarda objeto asegurados
		for (EmisionMasiva subeArchivoObj : lstEmiAux) {
			srvEmisionMasiva.insertarEmisionMasiva(subeArchivoObj);
		}
	}
	
	public void consultaData() {
		
		asFechaProc = formato.format(fcProceso);
		asFechaCarg = formato.format(fcCarga);
		lstEmisionMasiva = new ArrayList<EmisionMasiva>();
		lstEmisionMasiva = srvEmisionMasiva.recuperaEmiMasivaFcCrea(asFechaCarg);
		
	}

	public List<EmisionMasiva> getLstEmisionMasiva() {
		return lstEmisionMasiva;
	}

	public void setLstEmisionMasiva(List<EmisionMasiva> lstEmisionMasiva) {
		this.lstEmisionMasiva = lstEmisionMasiva;
	}

	public Date getFcProceso() {
		return fcProceso;
	}

	public void setFcProceso(Date fcProceso) {
		this.fcProceso = fcProceso;
	}

	public Date getFcCarga() {
		return fcCarga;
	}

	public void setFcCarga(Date fcCarga) {
		this.fcCarga = fcCarga;
	}



}
