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
 * Servlet implementation class register1
 */
@WebServlet("/bookmapping")
public class register1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register1() {
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
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String doc = request.getParameter("doc");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","root");
			System.out.println("Connection Created!");
			
			PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, contact);
			ps.setString(3, address);
			ps.setString(4, email);
			ps.setString(5, date);
			ps.setString(6, time);
			ps.setString(7, doc);
			
			int i=ps.executeUpdate();
			if (i>0)
			{
				out.print("appointment sent!");
				RequestDispatcher rd = request.getRequestDispatcher("home.html");
				rd.include(request,response);
			}
			
	}catch(Exception e) {
		
	}
	out.close();
	}

}
