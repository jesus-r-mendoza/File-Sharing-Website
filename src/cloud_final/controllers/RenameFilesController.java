package cloud_final.controllers;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.User;


@WebServlet("/RenameFilesController")
public class RenameFilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RenameFilesController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer fileId = Integer.valueOf( request.getParameter( "fileId" ) );
		Integer userId = Integer.valueOf( request.getParameter( "userId" ) );
		
		
		request.getRequestDispatcher("/WEB-INF/cloud_final/views/Rename.jsp").forward(request,response);
		
		
		
	}

	java.sql.Connection c= null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		String fileRename = request.getParameter("fileRename");
		
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu73" ;
		
		
		
		try {
			c= DriverManager.getConnection(url);
			
			
			
			
			//sql query 
			String sql = "UPDATE files SET fileName = ? WHERE userID = ? AND fileId=?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			
			pstmt.setString(1, fileRename);
			pstmt.setInt(2, user.getUser_id);
			pstmt.setInt(3, fileId);
			
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		doGet(request, response);
	}

}
