package lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class GetImage {

	public static void main(String[] args) {
	    String contents = null;
	    URLConnection conn = null;
	    BufferedReader buf = null;
	    try {
	      URL myPage = new URL("https://upload.wikimedia.org/wikipedia/commons/0/09/Artist's_impression_of_supernova_1993J.jpg");

	      conn = myPage.openConnection();

	      System.out.println("File type: " + conn.getContentType());

	      int len = conn.getContentLength();
	      if (len == -1)
	        System.out.println("File length unknown");
	      else
	        System.out.println("File length: " + len);

	      File local = new File("supernova.jpg");
	      Files.copy(conn.getInputStream(), local.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
	      
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	      System.err.println(e.getMessage());
	    }

	}

}
