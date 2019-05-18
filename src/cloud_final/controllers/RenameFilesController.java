package cloud_final.controllers;

import java.io.File;
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

import cloud_final.models.User;

import cloud_final.models.FileBean;



@WebServlet("/renamefiles")
public class RenameFilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RenameFilesController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		getExt("test.jpg");
		
		System.out.print("Entered do get");
		Integer fileId = Integer.valueOf( request.getParameter( "fileId" ) );
		Integer userId = Integer.valueOf( request.getParameter( "userId" ) );
		
		ArrayList<FileBean> files = (ArrayList<FileBean>) request.getSession().getAttribute("files");
		
		FileBean fb = getFileById(files,fileId);
		
		String fileName = fb.getFile().getName();
		
		String extension = getExt(fileName);
		System.out.println(extension);
		request.getSession().setAttribute("extension", extension);
		request.getRequestDispatcher("/WEB-INF/cloud_final/views/Rename.jsp").forward(request,response);
		
		
		
	}

	java.sql.Connection c= null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Integer fileId = Integer.valueOf( request.getParameter( "fileId" ) );
		Integer userId = Integer.valueOf( request.getParameter( "userId" ) );
		
		User user = (User) request.getSession().getAttribute("user");
		
		ArrayList<FileBean> files = (ArrayList<FileBean>) request.getSession().getAttribute("files");
		
		String fileRename = request.getParameter("fileRename");
		String exten = (String) request.getSession().getAttribute("extension");
		System.out.print(exten);
		String fullFile = fileRename + exten;
		String userName ="cs3220stu78";
		String password ="HhEABpU*";
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu78" ;
		
		if(fileRename==null || fileRename.trim().length() ==0) {
			System.out.println("error works");
			request.getSession().setAttribute("error", "File Name Invalid. Try again.");
			response.sendRedirect("home");
			return;
			
		}
		
		try {
			c= DriverManager.getConnection(url,userName,password);
		
			FileBean fb = getFileById(files,fileId);
			if(fb==null) return;
			
			//sql query 
			String sql = "UPDATE files SET file_name = ? WHERE user_id = ? AND file_id=?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			
			
			pstmt.setString(1, fullFile);
			
			pstmt.setInt(2, user.getUser_id());
			
			pstmt.setInt(3, fb.getId() );
			
			
			
			int res = pstmt.executeUpdate();
			
			if (res ==1) {
				File f = new File(fb.getPath(),fullFile);
//				System.out.println(f.getAbsolutePath());
//				System.out.println(fb.getFile().getAbsolutePath());
				fb.getFile().renameTo(f);
				
			}
			else {
				request.getSession().setAttribute("error", "File Name Invalid. Try again.");
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		response.sendRedirect("home");
		
		
		
	}
	
	
	private String getExt(String fileName) {
		String extension = "";
		for(int i=fileName.length()-1; i>=0;i--) {
			extension = fileName.charAt(i)+ extension;
			if(fileName.charAt(i) =='.') {
				
				return extension;
			}
		}
		return null;
	}
	
	
	
	private FileBean getFileById(ArrayList<FileBean> list,int id) {
		for(FileBean fb: list) {
			if(fb.getId()==id) {
				return fb;
			}
		}
		return null;
	}

}
