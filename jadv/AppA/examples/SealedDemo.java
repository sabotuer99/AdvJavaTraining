package examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

class StudentGrade implements Serializable {
  private static final long serialVersionUID = 1L;
  private String studentName;
  private float studentGPA;

  public StudentGrade(String n, float gpa) {
    studentName = n;
    studentGPA = gpa;
  }

  public String toString() {
    return studentName + " : " + studentGPA;
  }
}

public class SealedDemo {
  public static void main(String args[]) {
    try {
      StudentGrade sg = new StudentGrade("Tommy Tucker", 3.5F);
      KeyGenerator kg = KeyGenerator.getInstance("DES");
      SecretKey key = kg.generateKey();

      Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
      c.init(Cipher.ENCRYPT_MODE, key);
      SealedObject so = new SealedObject(sg, c);

      ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream("TuckerGrade.dat"));
      oos.writeObject(so);
      oos.close();

      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream("TuckerGrade.dat"));
      so = (SealedObject) ois.readObject();
      ois.close();

      c.init(Cipher.DECRYPT_MODE, key);
      StudentGrade grade = (StudentGrade) so.getObject(c);
      System.out.println(grade);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
