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
import java.security.MessageDigest;

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
		User user = (User) request.getSession().getAttribute("user");
    	if(user != null) {
    		request.getSession().invalidate();
    	}
		request.removeAttribute("loginErrorMessage");
		request.getRequestDispatcher("/WEB-INF/cloud_final/views/LoginView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("registered");
		request.removeAttribute("loginErrorMessage");
		
		String salt = "Acervan#1";
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		int user_id;
		
		loginName = loginName.trim();
		loginPassword = loginPassword.trim();
		loginPassword = loginPassword + salt;
		
		byte[] plainText = loginPassword.getBytes();
		StringBuilder sb = new StringBuilder();
		
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");

            md.reset();
            md.update(plainText);
            byte[] encodedPassword = md.digest();

            
            for (int i = 0; i < encodedPassword.length; i++) {
                if ((encodedPassword[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }

            loginPassword = sb.toString();
            // System.out.println("Plain    : " + loginPassword);
            // System.out.println("Encrypted: " + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginPassword = sb.toString();
		
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
            pst.setString(1, loginName);
            pst.setString(2, loginPassword);
            
            rs = pst.executeQuery();
            
    		if(rs.next())
   		 	{
		   		user_id = rs.getInt("user_id");   
		   		User user = new User(user_id,loginName);
	    		request.getSession().setAttribute("user", user);
	    		response.sendRedirect("CloudController");

	    		return;
	    		// request.getRequestDispatcher("CloudController").forward(request, response);
   		 	}
    		else
    		{
    			if(loginName.length() == 0)
    				request.setAttribute("loginErrorMessage", "No username detected");
    			else
    				request.setAttribute("loginErrorMessage", "Username/Password Invalid. Try again.");
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
