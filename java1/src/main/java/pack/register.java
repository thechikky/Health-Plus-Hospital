package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/adminmapping")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String contact = request.getParameter("contact");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","root");
			System.out.println("Connection Created!");
			
			PreparedStatement ps = con.prepareStatement("insert into admin values(?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, contact);
			ps.setString(3, email);
			ps.setString(4, password);
			
			int i=ps.executeUpdate();
			if (i>0)
			{
				out.print("you are registered");
				RequestDispatcher rd = request.getRequestDispatcher("login1.html");
				rd.include(request,response);
			}
			
	}catch(Exception e) {
		
	}
		out.close();
}
}
