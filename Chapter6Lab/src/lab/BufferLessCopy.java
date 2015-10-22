package lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class BufferLessCopy {

	public static void main(String[] args) {

		File source = new File("Scatter.java");
		File dest = new File("JavaSource");

		FileChannel inputChannel = null;
		FileChannel outputChannel = null;

		try {

			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				inputChannel.close();
				outputChannel.close();
			} catch (Exception e) {
			}

		}

	}
}
