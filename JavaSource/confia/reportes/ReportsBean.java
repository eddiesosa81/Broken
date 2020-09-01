package confia.reportes;

import java.util.HashMap;
import java.util.Map;

public class ReportsBean extends AbstractReportBean {
	private String batch;
	private String lsfecha;
	
	private final String COMPILE_FILE_NAME = "SolicitudChequeProveedor";

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}
	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("batch", batch.trim());
        reportParameters.put("fecha", lsfecha);
        return reportParameters;
	}
	public String execute() {
        try {
            super.prepareReport();
        } catch (Exception e) {
        	//logger.error("Error execute Exception "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getLsfecha() {
		return lsfecha;
	}
	public void setLsfecha(String lsfecha) {
		this.lsfecha = lsfecha;
	}

}
