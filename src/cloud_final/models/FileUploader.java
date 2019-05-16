package cloud_final.models;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploader {
	
	private ServletFileUpload upload;
	private String uploadDir;
	
	public FileUploader(File repository, String dir) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(repository);
		upload = new ServletFileUpload(factory);
		uploadDir = dir;
	}
	
	public int parseRequest(HttpServletRequest request) {
		int count = 0;		
		try {			
			List<FileItem> items = upload.parseRequest(request);
			for ( FileItem item : items ) {
				if( !item.isFormField() ) {
					String fileName = (new File( item.getName() )).getName();
					File file = new File( uploadDir, fileName );
					item.write(file);
					count++;
				}
			}			
		} catch ( Exception e ) {  return -1;  }
		return count;
	}
	
}
