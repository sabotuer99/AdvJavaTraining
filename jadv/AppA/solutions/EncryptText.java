package solutions;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

public class EncryptText {
  public static void main(String args[]) {
    try {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter data for encrypted file.");
      System.out.println("Type EOF on line to end.");

      KeyGenerator keyGen = KeyGenerator.getInstance("DES");
      Key theKey = keyGen.generateKey();

      Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c.init(Cipher.ENCRYPT_MODE, theKey);

      // write encrypted input text to a file
      FileOutputStream fos = new FileOutputStream("EncFile.txt");
      CipherOutputStream cos = new CipherOutputStream(fos, c);
      PrintWriter pw = new PrintWriter(cos);

      String s;
      while (!(s = scanner.next()).equalsIgnoreCase("EOF")) {
        pw.println(s);
      }
      scanner.close();
      pw.close();

      // Save key to a file
      ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream("Key.dat"));
      oos.writeObject(theKey);
      oos.close();
    }
    catch (Exception e) {
      System.err.println("Error : " + e.getMessage());
    }
  }
}
