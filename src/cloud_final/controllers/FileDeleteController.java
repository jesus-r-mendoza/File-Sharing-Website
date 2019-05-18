package cloud_final.controllers;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.FileBean;
import cloud_final.models.User;

/**
 * Servlet implementation class FileDeleteController
 */
@WebServlet("/FileDeleteController")
public class FileDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FileDeleteController() {
        super();
        
    }
	java.sql.Connection c= null;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer fileId = Integer.valueOf( request.getParameter( "fileId" ) );
		
		User user = (User) request.getSession().getAttribute("user");
		
		ArrayList<FileBean> files = (ArrayList<FileBean>) request.getSession().getAttribute("files");
	
		String userName ="cs3220stu78";
		String password ="fRINU1iD";
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu78" ;
		
		
		
		try {
			c= DriverManager.getConnection(url,userName,password);
			
			System.out.println("connected");
//			FileBean fb = getFileById(files,fileId);
//			if(fb==null) return;
//			
			//sql query 
			String sql = "DELETE FROM files WHERE file_id=?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			
			

			pstmt.setInt(1,fileId );	
			
			
			pstmt.executeUpdate();
			response.sendRedirect("CloudController");
			return;
//			fb.getFile().renameTo(new File(fb.getPath() + fileRename));
//			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	

}
