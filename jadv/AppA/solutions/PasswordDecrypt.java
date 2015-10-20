package solutions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PasswordDecrypt {
  public static void main(String args[]) {
    try {
      Scanner scanner = new java.util.Scanner(System.in);
      System.out.print("Enter Password for Decryption: ");
      String password = scanner.next();
      scanner.close();

      byte[] salt = { (byte) 0xca, (byte) 0xfe, (byte) 0xba,
          (byte) 0xbe, (byte) 0x32, (byte) 0x9a, (byte) 0xfa,
          (byte) 0x71 };

      PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 10);

      PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
      SecretKeyFactory skf = SecretKeyFactory
          .getInstance("PBEWithMD5AndDES");
      SecretKey theKey = skf.generateSecret(keySpec);

      Cipher c = Cipher.getInstance("PBEWithMD5AndDES");
      c.init(Cipher.DECRYPT_MODE, theKey, paramSpec);

      FileInputStream fis = new FileInputStream("EncFile.dat");
      CipherInputStream cis = new CipherInputStream(fis, c);
      BufferedReader br = new BufferedReader(
          new InputStreamReader(cis));

      String s;
      while ((s = br.readLine()) != null)
        System.out.println(s);
      
      br.close();
    }
    catch (Exception e) {
      System.err.println("Error : " + e.getMessage());
    }
  }
}
