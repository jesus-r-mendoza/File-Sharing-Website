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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
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
		request.getRequestDispatcher("/WEB-INF/cloud_final/views/LoginView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User info
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String user_id= null;

		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu78";
            String sqlUsername = "cs3220stu78";
            String sqlPassword = "HhEABpU*";

            c = DriverManager.getConnection( url, sqlUsername, sqlPassword );
            Statement stmt = c.createStatement();
            
            String sql = "SELECT user_id FROM users where username='"+userName+"' and password='"+userPassword+"'";
    		ResultSet rs = stmt.executeQuery(sql);
         
    		if(rs.next())
   		 	{
		   		user_id = rs.getString("user_Id");   

	    		request.getSession().setAttribute("user_id", user_id);
	    		request.getRequestDispatcher("/WEB-INF/cloud_final/views/CloudView.jsp").forward(request, response);
	   		}
    		else
    		{
    			request.getRequestDispatcher("/WEB-INF/cloud_final/views/LoginView.jsp").forward(request, response);
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
	}

}
