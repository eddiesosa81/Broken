package upp.confia.entidades;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectarBaseUpp {
	public static Connection getOracleConnection() throws Exception {
	    String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@10.142.0.2:1521:confia";
	    
	   
	    String username = "Upp";
	    String password = "upp";

	    Class.forName(driver).newInstance();
	    Connection conn = DriverManager.getConnection(url, username, password);
	    conn.setAutoCommit(false);
	    return conn;
	  }

}
