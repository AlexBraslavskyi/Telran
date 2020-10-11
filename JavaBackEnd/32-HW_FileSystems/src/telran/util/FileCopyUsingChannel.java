package telran.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileCopyUsingChannel implements FileCopyInterface {

	@Override
	public void copyFile(File source, File dest) throws Exception {
		   FileChannel sourceChannel = null;
		    FileChannel destChannel = null;
		    try {
		        sourceChannel = new FileInputStream(source).getChannel();
		        destChannel = new FileOutputStream(dest).getChannel();
		        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		       }finally{
		           sourceChannel.close();
		           destChannel.close();
		       }
		
	}

}
