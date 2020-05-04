package sql_EX1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sun.net.httpserver.Authenticator.Result;

public class SQL_main {

	private static String url = "jdbc:mysql://localhost:3306/sql_ex2?autoReconnect=true&useSSL=false";
	private static String userName = "root";
	private static String password = "28021992";

	//Q1 A
	public static void Q1_A(int id_doc) throws SQLException {

		Connection connection = DriverManager.getConnection(url,userName,password);
		Statement statement = connection.createStatement();
		String query_A1 = "SELECT appointments.patient_id, appointment_time, patient_name\r\n" + 
				"FROM appointments\r\n" + 
				"JOIN patients ON patients.patient_id =  appointments.patient_id\r\n" + 
				"WHERE doctor_id = " + id_doc + "\r\n" + 
				"order by appointment_time;";
		ResultSet rs = statement.executeQuery(query_A1);
		while (rs.next()) {
			System.out.print(rs.getInt("patient_id"));
			System.out.print(" " + rs.getString("patient_name"));
			System.out.print(" " + rs.getString("appointment_time"));
			System.out.println();
		}
		rs.close();
		statement.close();
		connection.close();
	}
	//Q1 B
	public static void Q1_B(int id_pat, int id_doc) throws SQLException {
		Connection connection = DriverManager.getConnection(url,userName,password);
		Statement statement = connection.createStatement();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime now = LocalDateTime.now(); 
		System.out.println(dtf.format(now));
		String exec_sp = "CALL exec_time_enter(\"" + dtf.format(now) + "\"," + id_pat + ", " + id_doc + ");" ;
		String query = "select * from queue;";		
		statement.execute(exec_sp);
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			System.out.print(rs.getInt("appointment_id"));
			System.out.print(" " + rs.getString("actual_time"));			
			System.out.println();
		}		
	}


	public static void main(String[] args) throws SQLException {
		Q1_A(1001);
		Q1_B(2004,1000);





//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//				   LocalDateTime now = LocalDateTime.now();  
//				   System.out.println(dtf.format(now));  
	}    



}


