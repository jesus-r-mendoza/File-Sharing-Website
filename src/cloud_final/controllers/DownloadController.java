package cloud_final.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.FileBean;

@WebServlet("/download")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer fileId = Integer.valueOf( request.getParameter("fileId") );
		ArrayList<FileBean> files = (ArrayList<FileBean>) request.getSession().getAttribute("files");
		FileBean file = getFileById(files, fileId);
		
		response.setHeader("Content-Length", "" + file.getFile().length());
        response.setHeader( "Content-Disposition", "attachment; filename=" + file.getFile().getName() );
        
        FileInputStream in = new FileInputStream( file.getFile().getAbsolutePath() );
        OutputStream out = response.getOutputStream();
        
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );

        in.close();
        System.out.println("File downloaded");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
