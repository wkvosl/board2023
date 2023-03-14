package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

	public static Connection getConnection() {
		
		
		String url = "jdbc:mysql://localhost/jspboard?useUnicode=true&characterEncoding=utf8";
		String id = "root";
		String pw ="a123";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url,id,pw);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
