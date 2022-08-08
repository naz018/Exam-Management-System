import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sql_connect {
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
			try {
				
		    try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println("connect to Microsoft SQL Server");
			String connString="jdbc:sqlserver://HAIER-PC\\SQLEXPRESS;" + "databaseName= Online Examination;integratedSecurity=true";
			Connection conn= DriverManager.getConnection(connString);
			Statement stmt= conn.createStatement();
			String query = "SELECT * FROM Course";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("OOPs, there is an error");
			e.printStackTrace();
		}

	}
}
