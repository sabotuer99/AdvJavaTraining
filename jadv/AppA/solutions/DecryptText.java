package solutions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

public class DecryptText {
  public static void main(String args[]) {
    try {
      // read key from file
      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream("Key.dat"));
      Key theKey = (Key) ois.readObject();
      ois.close();

      Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c.init(Cipher.DECRYPT_MODE, theKey);

      // Read encrypted text from file
      FileInputStream fis = new FileInputStream("EncFile.txt");
      CipherInputStream cis = new CipherInputStream(fis, c);
      BufferedReader br = new BufferedReader(new InputStreamReader(cis));

      String s;
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
