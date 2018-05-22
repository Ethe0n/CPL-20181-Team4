package cdp2.mindle.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManager {
	byte[] binary;

	public FileManager()
	{
		
	}
	
	public byte[] loadFile(String path) throws Exception
	{
		InputStream in = new FileInputStream(path);
		in.close();
		
		return binary;
	}
	
	public void saveFile(String path, byte[] bytes) throws Exception 
	{
		OutputStream out = new FileOutputStream(path);
		out.write(bytes);
		out.close();
	}
	
	public byte[] getBinary() {
		return binary;
	}

	public void setBinary(byte[] binary) {
		this.binary = binary;
	}
}
