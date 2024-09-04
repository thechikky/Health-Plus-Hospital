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
 * Servlet implementation class register4
 */
@WebServlet("/nursemapping")
public class register4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String qualification = request.getParameter("qualification");
		String dob = request.getParameter("dob");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","root");
			
			PreparedStatement ps = con.prepareStatement("insert into nurse values(?,?,?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, contact);
			ps.setString(3, address);
			ps.setString(4, email);
			ps.setString(5, qualification);
			ps.setString(6, dob);
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.print("nurse is registered!");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
			
		}catch(Exception e) {
			
		}
		out.close();
	}
	

}
