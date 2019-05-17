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
import java.sql.Statement;

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
		// process register data
		// check if valid and make account
		// login automatically? or have them log in again if valid
		// if not valid, send back to register view telling them whats wrong
		
		// User info
		String registerName = request.getParameter("registerName");
		String registerPassword = request.getParameter("registerPassword");
		// String user_id= null;

		registerName = registerName.trim();
		registerPassword = registerPassword.trim();
		
		// Connect to database
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu78";
            String username = "cs3220stu78";
            String password = "HhEABpU*";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            
            String sql = "SELECT user_id FROM users where username='"+registerName;
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		if(rs.next())
    		{
    			// return username used
        		
    		}
    		else
    		{
    			String stmtQuery = "INSERT INTO users (username,password) VALUES(NULL,?,?);";
	            PreparedStatement pstmt = c.prepareStatement(stmtQuery);
	            pstmt.setString(1,registerName.toLowerCase());
	            pstmt.setString(2,registerPassword);
	            pstmt.executeUpdate();
	            
	            c.close();
    			request.getSession().setAttribute("username", registerName);
    			request.getSession().setAttribute("password", registerPassword);
    			response.sendRedirect("LoginController");
    			return;
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
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }
		
		
		
		doGet(request, response);
	}

}
