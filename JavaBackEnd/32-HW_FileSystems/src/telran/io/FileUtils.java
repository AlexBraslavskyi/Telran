package telran.io;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileUtils {

	public static void main(String[] args) throws Exception {
		FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath(".");
		System.out.println(path.toAbsolutePath().normalize());
		displayDirectoryContent(path, 4, "");

	}

	public static void displayDirectoryContent(Path path, int depth, String tab) throws Exception {
		File file = new File(path.toString());

		if (file.exists()) {
			System.out.println(tab + file.getName());
			File[] files = file.listFiles();
			if (file.isDirectory() && depth != 0) {

				for (int i = 0; i < files.length; i++) {
					displayDirectoryContent(files[i].toPath(), depth - 1, tab + "\t");

				}
			}

			// with 0 didn't work

//		    else if(file.isDirectory()) {
//			      for (int i = 0; i < files.length; i++) {
//			        displayDirectoryContent(files[i].toPath(),depth,tab + "\t");
//		    
//		    }
//		    }
		} else {
			System.out.printf("%s doesn't exist\n", file);
		}

	}
}
