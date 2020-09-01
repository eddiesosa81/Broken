package confia.procedures;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class subeArchivo {
	
	private String ruta;

	public void guardarArchivo(String nombre, byte[] contenido) {
		FileOutputStream fos = null; // esta clase no sirve para escribir en el
										// archivo creado, xq maneja los byte
		File carpetaPrincipal = new File("C:\\java\\wildfly-9.0.2.Final\\welcome-content\\uploads");// tenemos un objeto de
														// tipo file, aqui no se
														// crea el archivo
		carpetaPrincipal.mkdir(); // se crea la carpeta
		File miArchivo = new File("C:\\java\\wildfly-9.0.2.Final\\welcome-content\\uploads/" + nombre);
		
		
		try {

			miArchivo.createNewFile();// se crea el archivo
			fos = new FileOutputStream(miArchivo);
			fos.write(contenido); // en memoria se escribe el archivo
			fos.flush();// escribir en el disco y tambien
			ruta = "nombre";
			System.out.println("path donde se guardo "
					+ carpetaPrincipal.getAbsolutePath());
			
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				fos.close();// permite liberar el archivo
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	

}
