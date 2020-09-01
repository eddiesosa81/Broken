package confia.reportes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@ManagedBean
@SessionScoped
/**
 * Clase que permite generar reportes previamente establecidos con Jasper y 
 * presentarlos en diferentes formatos. Esta clase utiliza un archivo recursos.properties,
 * que se encuetra dentro del directorio Reportes. Al ejecutarse la aplicación, este directorio Reportes deberá 
 * colocarse en la carpeta $JBOSS_HOME/modules (en Jboss7). En el archivo recursos.properties se puede definir
 * el path en el cual se van a ubicar los archivos .jasper y .jrxml
 * @author Clearminds
 *
 */
public class GeneradorReportes {
	/**
	 * Clase Jasper para generar el reporte
	 */
	private JasperPrint jasperPrint;
	/**
	 * Mapa de parámetros que se van a pasar al reporte
	 */
	// No se usa un Mapa de genéricos debido a que JasperFillManager.fillReport
	// no soporta este tipo de mapas.
	private Map parametrosReporte = new HashMap();
	/**
	 * Nombre del archivo .jasper que genera el reporte
	 */
	private String nombreJasper;
	/**
	 * Path en el cual se van a guardar los archivos .jasper y .jrxml del
	 * reporte. Este path lo lee del archivo reportes.properties
	 */
	private String path;
	/***
	 * Ruta del archivo reportes.properties. Es un path relativo a la carpeta
	 * bin del Jboss
	 */
	// esto es cuando le guardo dentro del jbos
	//private final String ARCHIVO_CONFIGURACION = "..\\modules\\Reportes\\Reportes.properties";
	private final String ARCHIVO_CONFIGURACION = "C:\\Java\\Reportes\\reportes.properties";
	/**
	 * Nombre con el cual se genera el archivo de reporte en los diferentes
	 * formatos
	 */
	private String nombreReporte;
	/**
	 * DataSource que se lo obtiene del servidor de aplicaciones
	 */
	@Resource(name = "java:/OracleOnlyControl")
	private DataSource dataSource;

	/**
	 * Constructor. Carga el archivo de propiedades resource.properties y lee la
	 * propiedad path
	 */
	public GeneradorReportes() {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream(ARCHIVO_CONFIGURACION));
			path = props.getProperty("path");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Obtiene la conexión a la base y genera el reporte
	 * 
	 * @throws Exception
	 *             si no pudo conectarse a la base de datos o no pudo generar el
	 *             reporte
	 */
	public void generarReporte() throws Exception {
		// JRBeanCollectionDataSource beanCollectionDataSource=new
		// JRBeanCollectionDataSource(listaEmpleados);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			// Class.forName("org.postgresql.Driver");
			//
			// conn = DriverManager
			// .getConnection("jdbc:postgresql://localhost:5432/test",
			// "postgres", "root");
			conn.setAutoCommit(false);

			jasperPrint = JasperFillManager.fillReport(path + "\\"
					+ nombreJasper, parametrosReporte, conn);
		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("Error al conectarse a la Base de Datos");

		} catch (JRException ex) {
			ex.printStackTrace();
			throw new Exception("No se pudo generar el reporte");
		}
	}

	/**
	 * Invoca a la generación del reporte y genera el Stream con la información
	 * del reporte que será devuelto a la página
	 * 
	 * @param extension
	 *            extensión del archivo a generarse (pdf, docx, xlsx. odt. pptx)
	 * @return el Stream con la información del reporte que será devuelto a la
	 *         página
	 * @throws Exception
	 *             si no logró generar el reporte
	 */

	public ServletOutputStream generarResponse(String extension)
			throws Exception {
		generarReporte();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + nombreReporte + "." + extension);
		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = httpServletResponse.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return servletOutputStream;
	}

	/**
	 * Retorna el reporte en formato PDF con el Stream que obtiene de
	 * generarResponse
	 * 
	 * @throws Exception
	 *             si no logra generar el reporte
	 */
	public void generarPDF() throws Exception {
		ServletOutputStream servletOutputStream = generarResponse("pdf");
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);
	}

	/**
	 * Retorna el reporte en formato DOCX con el Stream que obtiene de
	 * generarResponse
	 * 
	 * @throws Exception
	 *             si no logra generar el reporte
	 */
	public void generarDOCX() throws Exception {
		ServletOutputStream servletOutputStream = generarResponse("docx");
		JRDocxExporter docxExporter = new JRDocxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	/**
	 * Retorna el reporte en formato XLSX con el Stream que obtiene de
	 * generarResponse
	 * 
	 * @throws Exception
	 *             si no logra generar el reporte
	 */
	public void generarXLSX() throws Exception {
		ServletOutputStream servletOutputStream = generarResponse("xlsx");
		JRXlsxExporter docxExporter = new JRXlsxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	/**
	 * Retorna el reporte en formato ODT con el Stream que obtiene de
	 * generarResponse
	 * 
	 * @throws Exception
	 *             si no logra generar el reporte
	 */
	public void generarODT() throws Exception {
		ServletOutputStream servletOutputStream = generarResponse("odt");
		JROdtExporter docxExporter = new JROdtExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	/**
	 * Retorna el reporte en formato PPTX con el Stream que obtiene de
	 * generarResponse
	 * 
	 * @throws Exception
	 *             si no logra generar el reporte
	 */
	public void generarPPT() throws Exception {
		ServletOutputStream servletOutputStream = generarResponse("pptx");
		JRPptxExporter docxExporter = new JRPptxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	// getters y setters
	public String getNombreJasper() {
		return nombreJasper;
	}

	public void setNombreJasper(String nombreJasper) {
		this.nombreJasper = nombreJasper;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map getParametrosReporte() {
		return parametrosReporte;
	}

	public void setParametrosReporte(Map parametrosReporte) {
		this.parametrosReporte = parametrosReporte;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	
}

