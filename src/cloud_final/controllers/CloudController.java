package cloud_final.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.FileBean;
import cloud_final.models.User;

@WebServlet("/CloudController")
public class CloudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if ( user == null )
			response.sendRedirect("LoginController");
		else {
			ArrayList<FileBean> files = getFilesFromDB(user, this.getServletContext().getRealPath("/WEB-INF/uploads"));
			request.getSession().setAttribute("files", files);
			request.getRequestDispatcher("/WEB-INF/cloud_final/views/CloudView.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private ArrayList<FileBean> getFilesFromDB(User u, String path) {
		ArrayList<FileBean> files = new ArrayList<>();
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu90";
			String username = "cs3220stu90";
			String password = "fRINU1iD";
			c = DriverManager.getConnection(url, username, password);
			
			String sql = "select * from files where user_id = ?";
			
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int fileId = rs.getInt("file_id");
				String fileName = rs.getString("file_name");
				File file = new File(path + fileName);
				FileBean bean = new FileBean(fileId, file, path);
				files.add(bean);
			}
			
		} catch ( SQLException e ) {
			if (c != null) c.close();
			return null;
		} finally {
			if (c != null) c.close();
		}
		return files;
	}

}
