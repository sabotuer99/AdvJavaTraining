package examples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

public class EncryptOutputStream {
  public static void main(String args[]) {
    try {
      String s;
      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream("keyfile.dat"));
      Key theKey = (Key) ois.readObject();
      ois.close();

      BufferedReader br = new BufferedReader(
          new FileReader("file.txt"));
      Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c.init(Cipher.ENCRYPT_MODE, theKey);

      FileOutputStream fos = 
          new FileOutputStream("encrypted.dat");
      CipherOutputStream cos = new CipherOutputStream(fos, c);
      PrintWriter pw = new PrintWriter(cos);

      while ((s = br.readLine()) != null) {
        pw.println(s);
      }
      br.close();
      pw.close();
    }
    catch (Exception e) {
      System.err.println("Error : " + e.getMessage());
    }
  }
}
