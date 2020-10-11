package telran.util;

import java.io.File;
import java.nio.file.Files;

public class FileCopy  implements FileCopyInterface{



	@Override
	public void copyFile(File source, File dest) throws Exception {
		Files.copy(source.toPath(), dest.toPath());
	}

}
