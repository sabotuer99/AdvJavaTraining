package examples;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

public class Consumer {
  public static void main(String[] args) {
    try {
      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream("keyfile.dat"));
      Key key = (Key) ois.readObject();
      ois.close();

      DataInputStream dis = new DataInputStream(
          new FileInputStream("WrappedKey.dat"));
      byte[] encodedKey = new byte[dis.available()];
      dis.read(encodedKey, 0, dis.available());

      Cipher c = Cipher.getInstance("DES/ECB/NoPadding");
      c.init(Cipher.UNWRAP_MODE, key);

      Key k = c.unwrap(encodedKey, "DES", Cipher.SECRET_KEY);

      Cipher c2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c2.init(Cipher.DECRYPT_MODE, k);

      FileInputStream fis = new FileInputStream("Enc.dat");
      CipherInputStream cis = new CipherInputStream(fis, c2);
      BufferedReader br = new BufferedReader(
          new InputStreamReader(cis));
      String s;
      while ((s = br.readLine()) != null)
        System.out.println(s);
      
      dis.close();
      br.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
