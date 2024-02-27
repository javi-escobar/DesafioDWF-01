package sv.edu.udb.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/discografia2";
	static final String USER = "root";
	static final String PASS = "";
	
	Connection con = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public AppConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex) {
			Logger.getLogger(AppConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void Conectar() throws SQLException {
		con = DriverManager.getConnection(DB_URL,USER, PASS);
	}
	
	public void Desconectar() throws SQLException {
		if(con!=null) {
			con.close();
			con = null;
		}
	}
	
	
}
