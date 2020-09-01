package confia.reportes;

import java.util.HashMap;
import java.util.Map;



public class ServicioGeneraCartaRepo  extends AbstractReportBean{
	
	private final String  compile_file_name ;
	private final String param1,param2,param3,param4,param5;
	
	public ServicioGeneraCartaRepo(String compile_file_name, String param1, String param2, String param3, String param4,
			String param5) {
		super();
		this.compile_file_name = compile_file_name;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
		this.param5 = param5;
	}
	@Override
	protected String getCompileFileName() {
		return compile_file_name;
	}
	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("param1", param1.trim());
        reportParameters.put("param2", param2.trim());
        reportParameters.put("param3", param3.trim());
        reportParameters.put("param4", param4.trim());
        reportParameters.put("param5", param5.trim());
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
		
}
