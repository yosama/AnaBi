package anabi.utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.CommunicationException;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ConnectionDB {

	private Connection conn = null;

	public ConnectionDB(String url, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public ResultSet runSql(String sql)  {
		
		//System.out.println(sql);
		Statement statment =  null;		
		ResultSet rs = null;
		
		try{
			statment = conn.createStatement();
			if ( (sql.contains("SELECT ")) && (sql.contains("FROM ")) ){
				//System.out.println(sql);
				rs = statment.executeQuery(sql);
			}else{
				//System.out.println(sql);
				 statment.executeUpdate(sql);
			}
			
		}catch ( MySQLIntegrityConstraintViolationException sqlcvex){
			
		
		} catch(SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return rs;
	}

	@Override
	protected void finalize() throws Throwable {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	public Connection getConnection(){

		return conn;
	}



}
