package cloud_final.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		String userName = request.getParameter("loginName");
		String userPassword = request.getParameter("loginPassword");
		int user_id;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu78";
            String sqlUsername = "cs3220stu78";
            String sqlPassword = "HhEABpU*";

            c = DriverManager.getConnection( url, sqlUsername, sqlPassword );
            pst = c.prepareStatement("SELECT user_id FROM users where username=? and password=?");
            pst.setString(1, userName);
            pst.setString(2, userPassword);
            
            rs = pst.executeQuery();
            
    		if(rs.next())
   		 	{
		   		user_id = rs.getInt("user_id");   
		   		User user = new User(user_id,userName);
		   		
	    		request.getSession().setAttribute("user", user);
	    		request.getRequestDispatcher("CloudController").forward(request, response);
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
