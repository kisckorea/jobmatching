package kr.ac.kaist.ks.jobmatching.infrastructure.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class JDBCConnection {
	private static Connection connection;

	public static Connection getConnection(){
		try {
			if(connection == null || connection.isClosed()){
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					connection = DriverManager.getConnection("jdbc:mysql://143.248.90.131", "root", "0000");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//connection.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection() throws SQLException{
		if(connection!= null && !connection.isClosed())
			connection.close();
	}
}
