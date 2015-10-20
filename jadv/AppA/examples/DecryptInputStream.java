package examples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

public class DecryptInputStream {
  public static void main(String args[]) {
    try {
      String s;
      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream("keyfile.dat"));
      Key theKey = (Key) ois.readObject();
      ois.close();

      Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c.init(Cipher.DECRYPT_MODE, theKey);

      FileInputStream fis = 
          new FileInputStream("encrypted.dat");
      CipherInputStream cis = new CipherInputStream(fis, c);
      BufferedReader br = new BufferedReader(
          new InputStreamReader(cis));

      while ((s = br.readLine()) != null) {
        System.out.println(s);
      }
      br.close();
    }
    catch (Exception e) {
      System.err.println("Error : " + e.getMessage());
    }
  }
}
