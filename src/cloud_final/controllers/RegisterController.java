package cloud_final.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cloud_final/views/RegisterView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User info
		String registerName = request.getParameter("registerName");
		String registerPassword = request.getParameter("registerPassword");
		registerName = registerName.trim();
		registerPassword = registerPassword.trim();
		
		// Connect to database
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu78";
            String username = "cs3220stu78";
            String password = "HhEABpU*";

            c = DriverManager.getConnection( url, username, password );
            
            pst = c.prepareStatement("SELECT user_id FROM users where username=?");
            pst.setString(1, registerName);
    		rs = pst.executeQuery();
		
			if(rs.next())
    		{
    			// return username used
        		request.getSession().setAttribute("registerErrorMessage", "Username already in use. Try another");
    		}
    		else
    		{
	            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users (username,password) VALUES(?,?)");
	            pstmt.setString(1,registerName.toLowerCase());
	            pstmt.setString(2,registerPassword);
	            pstmt.executeUpdate();
	            
    			request.getSession().setAttribute("loginName", registerName);
    			request.getSession().setAttribute("loginPassword", registerPassword);
    			request.getRequestDispatcher("LoginController").forward(request, response);	
    		}    
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
		    try
	        {
	            if( rs != null ) rs.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
		    try
	        {
	            if( pst != null ) pst.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        try
	        {
	            if( c != null ) c.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
        }
		
	}

}
