package confia.reportes;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.apache.log4j.Logger;

import confia.procedures.ConectarBase;

public abstract class AbstractReportBean {
	public enum ExportOption {

        PDF, HTML, EXCEL, RTF
    }
    private ExportOption exportOption;
    private final String COMPILE_DIR = "/report/design/";
    //private String compileFileName = "productlist";//name of your compiled report file
    private String message;
    private static final Logger logger = Logger.getLogger(AbstractReportBean.class);
    /*@Resource(name = "java:/PersonalDs")
	private DataSource dataSource;*/
    
    public AbstractReportBean() {
        super();
        setExportOption(ExportOption.PDF);
    }

    protected void prepareReport() throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));
        ///////////////////
        Connection conn = null;
        try {
            //conn = dataSource.getConnection();
        	conn = ConectarBase.getOracleConnection();
        } catch (Exception ex) {
            logger.error("Error Connection Exception "+ex.getMessage());
        }
        /////////////////////
        JasperPrint jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters(), conn);
        //logger.info("Uno "+jasperPrint.getLocaleCode());
        if (getExportOption().equals(ExportOption.HTML)) {
            ReportConfigUtil.exportReportAsHtml(jasperPrint, response.getWriter());
        } else if (getExportOption().equals(ExportOption.EXCEL)) {
            ReportConfigUtil.exportReportAsExcel(jasperPrint, response.getWriter());
        /*} else if(getExportOption().equals(ExportOption.PDF)) {
        	logger.info("Inicia");
        	ReportConfigUtil.generarReportePDF(jasperPrint);
        */} else {
    		   request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
               response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
            
        }

        FacesContext.getCurrentInstance().responseComplete();
    }

    public ExportOption getExportOption() {
        return exportOption;
    }

    public void setExportOption(ExportOption exportOption) {
        this.exportOption = exportOption;
    }

    protected Map<String, Object> getReportParameters() {
        return new HashMap<String, Object>();
    }

    protected String getCompileDir() {
        return COMPILE_DIR;
    }

    protected abstract String getCompileFileName();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
