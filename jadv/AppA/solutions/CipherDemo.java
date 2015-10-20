package solutions;

import javax.crypto.*;
import java.security.Key;

public class CipherDemo {
  public static void main(String args[]) {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("DES");
      Key theKey = keyGen.generateKey();
      Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c.init(Cipher.ENCRYPT_MODE, theKey);

      String str1 = "Gilligans first name was Willie.";
      String str2 = "The Professor was originally a reporter.";

      byte[] encBytes1 = c.update(str1.getBytes());
      byte[] encBytes2 = c.doFinal(str2.getBytes());

      String encStr1 = new String(encBytes1);
      String encStr2 = new String(encBytes2);

      System.out.println("Encrypted String 1 : " + encStr1);
      System.out.println("Encrypted String 2 : " + encStr2);

      c.init(Cipher.DECRYPT_MODE, theKey);

      String decStr1 = new String(c.update(encBytes1));
      String decStr2 = new String(c.doFinal(encBytes2));

      System.out.println("String 1 : " + decStr1);
      System.out.println("String 2 : " + decStr2);
    }
    catch (Exception e) {
      System.err.println("Error : " + e.getMessage());
    }
  }
}
