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
 * Servlet implementation class bill
 */
@WebServlet("/billmapping")
public class bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bill() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String PatientName = request.getParameter("PatientName");
		String PatientID = request.getParameter("PatientID");
		String AdmissionDate = request.getParameter("AdmissionDate");
		String DischargeDate = request.getParameter("DischargeDate");
		String Charges = request.getParameter("days");
		String total = request.getParameter("total");
		String duedate = request.getParameter("duedate");
		String payment = request.getParameter("radio");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","root");
			
			PreparedStatement ps = con.prepareStatement("insert into bill values(?,?,?,?,?,?,?,?)");
			ps.setString(1, PatientName);
			ps.setString(2, PatientID);
			ps.setString(3, AdmissionDate);
			ps.setString(4, DischargeDate);
			ps.setString(5, Charges);
			ps.setString(6, total);
			ps.setString(7, duedate);
			ps.setString(8, payment);
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.print("bill is generated!");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
			
		}catch(Exception e) {
			
		}
		out.close();
	}
	

}

