package confia.controladores.transaccionales;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import confia.reportes.AbstractReportBean;

@ManagedBean
@ViewScoped
public class ControladorReporte extends AbstractReportBean {
	
	private final String COMPILE_FILE_NAME = "horariosAsignadosMes";

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}
	
	@Override
	protected Map<String, Object> getReportParameters() {
		String param1 = null,param2 = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("fcDesde", param1);
		parametros.put("fcHasta", param2);
		return parametros;

	}
	
	public String execute() {
		try {
			super.prepareReport();
		} catch (Exception e) {
			// logger.error("Error execute Exception "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
