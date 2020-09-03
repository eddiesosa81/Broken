package confia.procedures;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectarBase {
	public static Connection getOracleConnection() throws Exception {
	    String driver = "oracle.jdbc.driver.OracleDriver";
//	    String url = "jdbc:oracle:thin:@192.168.0.3:1521:confia";
//	    String url = "jdbc:oracle:thin:@35.237.152.6:1521:xe";
	    String url = "jdbc:oracle:thin:@10.142.0.2:1521:confia";
	    
	   
	    String username = "broken";
	    String password = "broken";

	    Class.forName(driver).newInstance();
	    Connection conn = DriverManager.getConnection(url, username, password);
	    conn.setAutoCommit(false);
	    return conn;
	  }

}
