package confia.controladores.transaccionales;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean(name = "ControladorSubeArchivo")
@ViewScoped
public class ControladorSubeArchivo {
	
	@PostConstruct
	public void name() {
		System.out.println("INGRESOO constructo");
	}
	
	public void ingreso(FileUploadEvent event) {
		UploadedFile miArchivo = event.getFile();
	
		long tamanio = miArchivo.getSize();// tamaño del archivo
		byte[] contenido = miArchivo.getContent();// contenido del archivo
		String tipoDeArchivo = miArchivo.getContentType();// que tipo de archivo
		String nombre = miArchivo.getFileName();
		int longitud = nombre.length();
		System.out.println("Longitud:"+longitud);
		String extencion = nombre.substring(longitud-4, longitud);
		
		System.out.println("--nombre--" + nombre);
		System.out.println("--extension--" + extencion);
		System.out.println("-**********-------Tamaño: " + tamanio);
		System.out.println("-**********-------Contenido: " + contenido);
		System.out.println("-********-------Tipo de Archivo: " + tipoDeArchivo);
		System.out.println("nmArchivo:" + nombre);
		
		guardarArchivo(nombre, contenido);
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
		String nmArchivo = "<a href=\"http://localhost:8081/documentos/clientes/" + nombre + "\" target=\"_blank\">"
				+ nombre + "</a><br/>";
		System.out.println("filename:" + nmArchivo);
	}

}
