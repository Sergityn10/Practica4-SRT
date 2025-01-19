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
  protected boolean isInicialized = false;
  
  protected KeyPair generateKeyPair;

  private static final byte[] DEFAULT_SALT = {-57, 115, 33, -116, 126, -56, -18, -103};
  
  public ParClavesP4() {
    this.isInicialized = false;
    this.generateKeyPair = null;
  }
  
  public final boolean getIsInicialized() {
    return this.isInicialized;
  }
  
  public final PublicKey getPublicKey() {
    return this.generateKeyPair.getPublic();
  }
  
  public final PrivateKey getPrivateKey() {
    return this.generateKeyPair.getPrivate();
  }

  /**
   * Generates a new RSA key pair and validates it.
   *
   * @return true if key pair was generated and validated successfully
   */
  public final boolean generateKeyPair() {
    boolean bool = false;
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(512);
      this.generateKeyPair = keyPairGenerator.generateKeyPair();
      bool = validateKeyPair(this.generateKeyPair);
    } catch (Exception exception) {
      exception.printStackTrace();
      this.generateKeyPair = null;
    } 
    this.isInicialized = bool;
    return bool;
  }
  /**
   * Loads a key pair from a file, decrypting the private key with the provided password.
   *
   * @param filename the file containing the encrypted key pair
   * @param password password to decrypt the private key
   * @return true if the key pair was loaded successfully
   */
  public final boolean loadKeyPair(String filename, char[] password) {
    boolean bool = true;
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
      PublicKey publicKey = (PublicKey)objectInputStream.readObject();
      byte[] arrayOfByte = (byte[])objectInputStream.readObject();
      PrivateKey privateKey = generateKeyPair(password, arrayOfByte);
      this.generateKeyPair = new KeyPair(publicKey, privateKey);
      objectInputStream.close();
    } catch (Exception exception) {
      bool = false;
    } 
    this.isInicialized = bool;
    return bool;
  }
  /**
   * Saves the current key pair to a file, encrypting the private key with the provided password.
   *
   * @param filename the file to save the key pair to
   * @param password password to encrypt the private key
   * @return true if the key pair was saved successfully
   */
  public final boolean saveKeyPair(String filename, char[] password) {
    boolean bool = true;
    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
      objectOutputStream.writeObject(this.generateKeyPair.getPublic());
      objectOutputStream.writeObject(wrapPrivateKey(password));
      objectOutputStream.close();
    } catch (Exception exception) {
      bool = false;
    } 
    this.isInicialized = bool;
    return bool;
  }
  /**
   * Encrypts (wraps) the private key using password-based encryption.
   *
   * @param password password to use for encryption
   * @return encrypted private key as byte array
   */
  private byte[] wrapPrivateKey(char[] password) {
    byte[] arrayOfByte1 = null;
    byte[] arrayOfByte2 = { -57, 115, 33, -116, 126, -56, -18, -103 };
    byte b = 20;
    PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(DEFAULT_SALT, b);
    try {
      PBEKeySpec pBEKeySpec = new PBEKeySpec(password);
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      SecretKey secretKey = secretKeyFactory.generateSecret(pBEKeySpec);
      Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
      cipher.init(Cipher.WRAP_MODE, secretKey, pBEParameterSpec);
      arrayOfByte1 = cipher.wrap(this.generateKeyPair.getPrivate());
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
    return arrayOfByte1;
  }

  /**
   * Decrypts (unwraps) the private key using password-based encryption.
   *
   * @param password password to use for decryption
   * @param wrappedKey encrypted private key data
   * @return decrypted PrivateKey object
   */
  private PrivateKey generateKeyPair(char[] password, byte[] wrappedKey) {
    PrivateKey privateKey = null;
    byte b = 20;
    PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(DEFAULT_SALT, b);
    try {
      PBEKeySpec pBEKeySpec = new PBEKeySpec(password);
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      SecretKey secretKey = secretKeyFactory.generateSecret(pBEKeySpec);
      Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
      cipher.init(Cipher.UNWRAP_MODE, secretKey, pBEParameterSpec);
      privateKey = (PrivateKey)cipher.unwrap(wrappedKey, "RSA", 2);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
    return privateKey;
  }
  /**
   * Validates a key pair by performing a test signature operation.
   * Signs test data with the private key and verifies it with the public key.
   *
   * @param keyPair the key pair to validate
   * @return true if the key pair successfully signs and verifies test data
   */
  public final boolean validateKeyPair(KeyPair keyPair) {
    boolean bool = false;
    try {
      Signature signature = Signature.getInstance("SHA1withRSA");
      PrivateKey privateKey = keyPair.getPrivate();
      signature.initSign(privateKey);
      String str = "Texto de prueba para la firma";
      signature.update(str.getBytes());
      byte[] arrayOfByte = signature.sign();
      PublicKey publicKey = keyPair.getPublic();
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