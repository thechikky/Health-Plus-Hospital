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
 * Servlet implementation class register2
 */
@WebServlet("/patientmapping")
public class register2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String gender = request.getParameter("radio");
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String drname = request.getParameter("drname");
		String treatment = request.getParameter("treatment");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","root");
			
			PreparedStatement ps = con.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?)");
			ps.setString(1, gender);
			ps.setString(2, name);
			ps.setString(3, contact);
			ps.setString(4, city);
			ps.setString(5, email);
			ps.setString(6, dob);
			ps.setString(7, drname);
			ps.setString(8, treatment);
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.print("patient is registered!");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
			
		}catch(Exception e) {
			
		}
		out.close();
	}
	

}
