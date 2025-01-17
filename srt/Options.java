package srt;

import java.io.*;

public class Options implements Serializable {
  protected String symmetricalCipher;
  
  protected String authenticator;
  
  public static final byte OP_NONE = 0;
  
  public static final byte OP_SYMMETRIC_CIPHER = 1;
  
  public static final byte OP_HASH_MAC = 10;
  
  public static final byte OP_PUBLIC_CIPHER = 20;
  
  public static final byte OP_SIGNED = 30;
  
  public static final String OP_NONE_ALGORITHM = "none";
  
  public static final String[] cipherAlgorithms = new String[] { "none", "PBEWithMD5AndDES", "PBEWithMD5AndTripleDES", "PBEWithSHA1AndDESede", "PBEWithSHA1AndRC2_40", "RSA/ECB/PKCS1Padding" };
  
  public static final String[] authenticationAlgorithms = new String[] { 
      "none", "MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512", "HmacMD5", "HmacSHA1", "HmacSHA256", 
      "HmacSHA384", "HmacSHA512", "SHA1withRSA", "MD2withRSA", "MD5withRSA" };
  
  public static final String[] hashAlgorithms = new String[] { "MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512" };
  
  public static final String[] macAlgorithms = new String[] { "HmacMD5", "HmacSHA1", "HmacSHA256", "HmacSHA384", "HmacSHA512" };
  
  public static final String[] symmetricalAlgorithms = new String[] { "PBEWithMD5AndDES", "PBEWithMD5AndTripleDES", "PBEWithSHA1AndDESede", "PBEWithSHA1AndRC2_40" };
  
  public static final String[] publicAlgorithms = new String[] { "RSA/ECB/PKCS1Padding" };
  
  public static final String[] hashmacAlgorithms = new String[] { 
      "MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512", "HmacMD5", "HmacSHA1", "HmacSHA256", "HmacSHA384", 
      "HmacSHA512" };
  
  public static final String[] signAlgorithms = new String[] { "SHA1withRSA", "MD2withRSA", "MD5withRSA" };
  
  public Options() {
    setSymmetricalCipher(symmetricalAlgorithms[0]);
    setAuthenticator(hashmacAlgorithms[0]);
  }
  
  public Options(String paramString1, String paramString2) {
    this.symmetricalCipher = paramString1;
    this.authenticator = paramString2;
  }
  
  public String getSymmetricalCipher() {
    return this.symmetricalCipher;
  }
  
  public void setSymmetricalCipher(String paramString) {
    this.symmetricalCipher = paramString;
  }
  
  public String getAuthenticator() {
    return this.authenticator;
  }
  
  public void setAuthenticator(String paramString) {
    this.authenticator = paramString;
  }
  
  public static Options load(String paramString) throws IOException, ClassNotFoundException {
    Options options = null;
    FileInputStream fileInputStream = new FileInputStream(paramString);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    options = (Options)objectInputStream.readObject();
    objectInputStream.close();
    return options;
  }
  
  public boolean save(String paramString) throws IOException, ClassNotFoundException{
    boolean bool = true;
    FileOutputStream fileOutputStream = new FileOutputStream(paramString);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(this);
    objectOutputStream.close();
    return bool;
  }
  
  public static boolean isTypeAlgorithm(String[] paramArrayOfString, String paramString) {
    int i = search(paramArrayOfString, paramString);
    return (i != -1);
  }
  
  public static int search(String[] paramArrayOfString, String paramString) {
      int i;
      for (i = paramArrayOfString.length - 1; i != -1 && paramArrayOfString[i].compareTo(paramString) != 0; i--) ;
      return i;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\Options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */