package lab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyScatter {

	public static void main(String[] args) {
		
		File source = new File("Scatter.java");
		File dest = new File("JavaSource");
		
		
		try {
			dest.createNewFile();
			Files.copy(source.toPath(), dest.toPath(), 
					StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
