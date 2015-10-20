package examples;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Producer {
  public static void main(String[] args) {
    try {
      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream("keyfile.dat"));
      Key key = (Key) ois.readObject();
      ois.close();

      KeyGenerator kg = KeyGenerator.getInstance("DES");
      SecretKey sk = kg.generateKey();

      Cipher c = Cipher.getInstance("DES/ECB/NoPadding");
      c.init(Cipher.WRAP_MODE, key);

      byte[] wrappedKey = c.wrap(sk);
      DataOutputStream dos = new DataOutputStream(
          new FileOutputStream("WrappedKey.dat"));
      dos.write(wrappedKey, 0, wrappedKey.length);
      dos.close();

      Cipher c2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c2.init(Cipher.ENCRYPT_MODE, sk);

      FileOutputStream fos = new FileOutputStream("Enc.dat");
      CipherOutputStream cos = new CipherOutputStream(fos, c2);
      PrintWriter pw2 = new PrintWriter(cos);
      pw2.println("This is the encoded text");
      pw2.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
