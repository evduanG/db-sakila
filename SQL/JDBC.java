package assig3.SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private static final String USER = "root", PASSWORD = "";
	
	protected Connection m_Con;

	public JDBC() {
		m_Con= null;
	}
	
	public boolean connect(String i_ConnectionURL) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			m_Con = DriverManager.getConnection (i_ConnectionURL, USER, PASSWORD);
		}
		catch (SQLException e) {
			m_Con = null;
			e.printStackTrace();
		}
		catch (Exception e) {
			m_Con = null;
			e.printStackTrace();
		}
		
		return isConnection() ;
	}
	
	public boolean isConnection() {
		return m_Con != null;
	}

	public void close() {
		try {
			m_Con.close();
		} catch (SQLException e) {
			System.err.println("ERROR! closeing the Connection" + e.getMessage());
		}
	}
	
	public boolean doQuery(String i_Query, String[]  i_colNameToPrint ) {
		boolean isSuccess = true;

		try {
			//Create a Statement class to execute the SQL statement
			Statement stmt = m_Con.createStatement();
	
			//Execute the SQL statement and get the results in a Resultset
			ResultSet rs = stmt.executeQuery(i_Query);
			if (i_colNameToPrint != null) {
				printSqlResults(rs, i_colNameToPrint);				
			}
			// Iterate through the ResultSet, displaying two values
			// for each row using the getString method
			
		}
		catch (SQLException e) {
		e.printStackTrace();
		isSuccess = false;
	}
	catch (Exception e) {
		e.printStackTrace();
		isSuccess = false;
	}
		return isSuccess;
	}

	private void printSqlResults(ResultSet i_Rs,String[]  i_colNameToPrint) {
		int idx=1;
		if (i_colNameToPrint[0] == null) {
			printSqlResults(i_Rs);
		}
		else {
			try {
				while (i_Rs.next()){
					String toPrint = String.valueOf(idx++);
					for (String col : i_colNameToPrint) {
						toPrint += " " + col + "   " +i_Rs.getString(col);
					}
					System.out.println(toPrint);
				}
				if (idx == 1) {
					System.out.println("no Results");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void printSqlResults(ResultSet i_Rs) {
		int idx=1;
		try {
			while (i_Rs.next()){		
				System.out.println(String.valueOf(idx++) + i_Rs.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
