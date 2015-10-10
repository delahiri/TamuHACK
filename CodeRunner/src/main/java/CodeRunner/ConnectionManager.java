package CodeRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	private static Connection conn = null;
	
	protected ConnectionManager()
	{
		// Exists only to defeat instantiation.
	}
	
	public static Connection getConnection() 
	{
	      if(conn == null) 
	      {

	  		try 
	  		{
	  			Class.forName("com.mysql.jdbc.Driver");
	  		} 
	  		catch (ClassNotFoundException e) 
	  		{
	  			e.printStackTrace();
	  		}
	  		//Connection conn = null;
	  		try 
	  		{
	  			conn = DriverManager.getConnection("jdbc:mysql://localhost/codeclub","root", "");
	  		} 
	  		catch (SQLException e) 
	  		{
	  			e.printStackTrace();
	  		}
			// try
			// {
			// conn.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
	  
	      }
	      return conn;
	   }

}
