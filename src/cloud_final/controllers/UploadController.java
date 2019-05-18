package cloud_final.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.FileBean;
import cloud_final.models.FileUploader;
import cloud_final.models.User;

@WebServlet("/Upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("CloudController");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		File repository = (File) this.getServletConfig().getServletContext().getAttribute("javax.servlet.context.tempdir");
		
		String uploadDirectory = this.getServletContext().getRealPath("/WEB-INF/uploads"); // don't forget to create this folder
		                                                                                   // it's not automatically created
		
		User user = (User) request.getSession().getAttribute("user");
		
		if ( user == null ) return;
		
		FileUploader uploader = new FileUploader(repository, uploadDirectory);		
		String fileName = uploader.parseRequest(request);
		
		if ( fileName != null ) {
			Connection c = null;
			try {
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu78";
				String username = "cs3220stu78";
				String password = "HhEABpU*";
				c = DriverManager.getConnection(url, username, password);
				
				String sql = "insert into files (file_id, file_name, upload_date, user_id) values ( 0, ?, CURDATE(), ? )";
				
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, fileName);
				pstmt.setInt(2, user.getUser_id());
				int rs = pstmt.executeUpdate();
				if ( rs <= 0 )
					request.getSession().setAttribute("error", "File could not be uploaded. Please try again.");
				else
					request.getSession().setAttribute("success", "File successfully uploaded.");
				
			} catch ( SQLException e ) {
				if (c != null) {
					try {
						c.close();
					} catch (SQLException e1) {
						throw new ServletException();
					}
				}
			} finally {
				if (c != null) {
					try {
						c.close();
					} catch (SQLException e) {
						throw new ServletException();
					}
				}
			}
		}
		
		doGet(request, response);
	}

}
