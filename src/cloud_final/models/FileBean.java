package cloud_final.models;

import java.io.File;

public class FileBean {
	
	private File file;
	private int id;
	private String path;
	
	public FileBean(int num, File f, String pth) {
		id = num;
		file = f;
		path = pth;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPath() {
		return path;
	}
	
	public File getFile() {
		return file;
	}
	
}
