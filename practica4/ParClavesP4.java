package practica4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class ParClavesP4 {
  protected boolean close = false;
  
  protected KeyPair generateKeyPair;
  
  public ParClavesP4() {
    this.close = false;
    this.generateKeyPair = null;
  }
  
  public final boolean I() {
    return this.close;
  }
  
  public final PublicKey Z() {
    return this.generateKeyPair.getPublic();
  }
  
  public final PrivateKey C() {
    return this.generateKeyPair.getPrivate();
  }
  
  public final boolean B() {
    boolean bool = false;
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(512);
      this.generateKeyPair = keyPairGenerator.generateKeyPair();
      bool = generateSecret(this.generateKeyPair);
    } catch (Exception exception) {
      exception.printStackTrace();
      this.generateKeyPair = null;
    } 
    this.close = bool;
    return bool;
  }
  
  public final boolean I(String paramString, char[] paramArrayOfchar) {
    boolean bool = true;
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(paramString));
      PublicKey publicKey = (PublicKey)objectInputStream.readObject();
      byte[] arrayOfByte = (byte[])objectInputStream.readObject();
      PrivateKey privateKey = generateKeyPair(paramArrayOfchar, arrayOfByte);
      this.generateKeyPair = new KeyPair(publicKey, privateKey);
      objectInputStream.close();
    } catch (Exception exception) {
      bool = false;
    } 
    this.close = bool;
    return bool;
  }
  
  public final boolean Z(String paramString, char[] paramArrayOfchar) {
    boolean bool = true;
    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(paramString));
      objectOutputStream.writeObject(this.generateKeyPair.getPublic());
      objectOutputStream.writeObject(close(paramArrayOfchar));
      objectOutputStream.close();
    } catch (Exception exception) {
      bool = false;
    } 
    this.close = bool;
    return bool;
  }
  
  private byte[] close(char[] paramArrayOfchar) {
    byte[] arrayOfByte1 = null;
    byte[] arrayOfByte2 = { -57, 115, 33, -116, 126, -56, -18, -103 };
    byte b = 20;
    PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(arrayOfByte2, b);
    try {
      PBEKeySpec pBEKeySpec = new PBEKeySpec(paramArrayOfchar);
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      SecretKey secretKey = secretKeyFactory.generateSecret(pBEKeySpec);
      Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
      cipher.init(3, secretKey, pBEParameterSpec);
      arrayOfByte1 = cipher.wrap(this.generateKeyPair.getPrivate());
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
    return arrayOfByte1;
  }
  
  private PrivateKey generateKeyPair(char[] paramArrayOfchar, byte[] paramArrayOfbyte) {
    PrivateKey privateKey = null;
    byte[] arrayOfByte = { -57, 115, 33, -116, 126, -56, -18, -103 };
    byte b = 20;
    PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(arrayOfByte, b);
    try {
      PBEKeySpec pBEKeySpec = new PBEKeySpec(paramArrayOfchar);
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      SecretKey secretKey = secretKeyFactory.generateSecret(pBEKeySpec);
      Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
      cipher.init(4, secretKey, pBEParameterSpec);
      privateKey = (PrivateKey)cipher.unwrap(paramArrayOfbyte, "RSA", 2);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
    return privateKey;
  }
  
  public final boolean generateSecret(KeyPair paramKeyPair) {
    boolean bool = false;
    try {
      Signature signature = Signature.getInstance("SHA1withRSA");
      PrivateKey privateKey = paramKeyPair.getPrivate();
      signature.initSign(privateKey);
      String str = "Texto de prueba para la firma";
      signature.update(str.getBytes());
      byte[] arrayOfByte = signature.sign();
      PublicKey publicKey = paramKeyPair.getPublic();
      signature.initVerify(publicKey);
      signature.update(str.getBytes());
      boolean bool1 = signature.verify(arrayOfByte);
      bool = bool1;
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    return bool;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\I.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */