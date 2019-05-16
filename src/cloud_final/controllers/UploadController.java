package cloud_final.controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.FileUploader;

@WebServlet("/Upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cloud_final/views/CloudView.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		File repository = (File) this.getServletConfig().getServletContext().getAttribute("javax.servlet.context.tempdir");
		String uploadDirectory = this.getServletContext().getRealPath("/WEB-INF/uploads");		
		
		FileUploader uploader = new FileUploader(repository, uploadDirectory);		
		int filesUploaded = uploader.parseRequest(request);
		
		System.out.println("Uploaded " + filesUploaded + " file(s) to: " + uploadDirectory);
		
		doGet(request, response);
	}

}
