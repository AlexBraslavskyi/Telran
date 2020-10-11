package telran.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileCopyUsingStream implements FileCopyInterface{

	@Override
	public void copyFile(File source, File dest) throws Exception{
	FileSystem fs = FileSystems.getDefault();
		InputStream is = null;
		OutputStream os = null;
		 try {
		        is = new FileInputStream(source);
		        os = new FileOutputStream(dest);
		        byte[] buffer = new byte[(int) Runtime.getRuntime().freeMemory()];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		    } finally {
		        is.close();
		        os.close();
		    }
				
	}
	
}
