package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class adminlogin {
	
	public static boolean validate(String username, String password) {
		
		boolean status = false;
		
try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","root");
			System.out.println("Connection Created!");
			
			PreparedStatement ps = con.prepareStatement("select * from admin where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			status=rs.next();

			
	}catch(Exception e) {
		
	}
	return status;
	}

}
