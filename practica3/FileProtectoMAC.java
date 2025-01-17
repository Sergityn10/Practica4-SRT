package practica3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Iterator;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import practica2.CifradoOriginal;
import srt.Header;

public class FileProtectoMAC extends CifradoOriginal {
  private Practica3 append = null;
  
  private static int close = 32768;
  
  private static final byte[] contentEquals = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
  
  public FileProtectoMAC() {}
  
  public FileProtectoMAC(Practica3 paramS) {
    this.append = paramS;
  }
  
  public final boolean Z(String paramString, Header paramHeader) {
    try {
      FileInputStream fileInputStream = new FileInputStream(paramString);
      boolean bool = paramHeader.load(fileInputStream);
      fileInputStream.close();
      return bool;
    } catch (Exception exception) {
      this.append.I("Problemas al leer el fichero: " + paramString + "\n");
      return false;
    } 
  }
  
  public final void I(String paramString1, String paramString2, String paramString3, String paramString4) {
    this.append.I("Proceso de hashing de <" + paramString1 + "> con Algoritmo: " + paramString4 + "\n");
    try {
      FileInputStream fileInputStream = new FileInputStream(paramString1);
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      MessageDigest messageDigest = MessageDigest.getInstance(paramString4);
      messageDigest.update(paramString3.getBytes());
      DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
      byte[] arrayOfByte = new byte[close];
      int i = 0;
      while (true) {
        int j = digestInputStream.read(arrayOfByte, 0, close);
        i += j;
        if (j != close) {
          messageDigest = digestInputStream.getMessageDigest();
          byte[] arrayOfByte1 = messageDigest.digest();
          digestInputStream.close();
          Header header = new Header((byte)10, "none", paramString4, arrayOfByte1);
          header.save(fileOutputStream);
          fileInputStream.close();
          fileInputStream = new FileInputStream(paramString1);
          while (true) {
            j = fileInputStream.read(arrayOfByte, 0, close);
            fileOutputStream.write(arrayOfByte, 0, j);
            if (j != close) {
              this.append.I("\nMD: " + srt.I.I(arrayOfByte1));
              this.append.I("\nHecho (" + i + " bytes).\n");
              fileOutputStream.flush();
              fileOutputStream.close();
              fileInputStream.close();
              return;
            } 
          } 
//          break;
        } 
      } 
    } catch (FileNotFoundException fileNotFoundException) {
      this.append.I("Fichero no se encuentra: " + paramString1 + "\n");
    } catch (IOException iOException) {
      this.append.I("Error de E/S en.\n");
    } catch (Exception exception) {
      this.append.I(String.valueOf(exception.getMessage()) + "\n");
    } 
  }
  
  public final void Z(String paramString1, String paramString2, String paramString3, String paramString4) {
    try {
      this.append.I("Proceso de verificación de <" + paramString1 + "> con: " + paramString4 + "\n");
      FileInputStream fileInputStream = new FileInputStream(paramString1);
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      Header headerCifradoMAC = new Header();
      headerCifradoMAC.load(fileInputStream);
      int i = 0;
      MessageDigest messageDigest = MessageDigest.getInstance(headerCifradoMAC.getAlgorithm2());
      messageDigest.update(paramString3.getBytes());
      DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
      byte[] arrayOfByte = new byte[close];
      while (true) {
        int j = digestInputStream.read(arrayOfByte, 0, close);
        i += j;
        fileOutputStream.write(arrayOfByte, 0, j);
        if (j != close) {
          messageDigest = digestInputStream.getMessageDigest();
          byte[] arrayOfByte1 = messageDigest.digest();
          digestInputStream.close();
          this.append.I("\nHecho (" + i + " bytes).\n");
          String str1 = srt.I.I(arrayOfByte1);
          String str2 = srt.I.I(headerCifradoMAC.getData());
          this.append.I("\nMD almacenado: " + str2);
          this.append.I("\nMD  calculado: " + str1);
          if (str1.contentEquals(str2)) {
            this.append.I("\nHash idénticos, el fichero no ha sido modificado.\n");
          } else {
            this.append.I("\nHash diferentes, el fichero ha sido modificado (o la contraseï¿½a no es correcta).\n");
            (new File(paramString2)).deleteOnExit();
          } 
          fileInputStream.close();
          fileOutputStream.flush();
          fileOutputStream.close();
          return;
        } 
      } 
    } catch (Exception exception) {
      this.append.I(String.valueOf(exception.getMessage()) + "\n");
    } 
  }
  
  public final void C(String paramString1, String paramString2, String paramString3, String paramString4) {
    this.append.I("Proceso de HMac de <" + paramString1 + "> con Algoritmo: " + paramString4 + "\n");
    try {
      FileInputStream fileInputStream = new FileInputStream(paramString1);
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      Mac mac = Mac.getInstance(paramString4);
      SecretKey secretKey = practica3.GenerarSecretKey.I(paramString3.toCharArray(), contentEquals, this.I, mac.getMacLength());
      mac.init(secretKey);
      byte[] arrayOfByte = new byte[close];
      while (true) {
        int i = fileInputStream.read(arrayOfByte, 0, close);
        mac.update(arrayOfByte, 0, i);
        if (i != close) {
          byte[] arrayOfByte1 = mac.doFinal();
          fileInputStream.close();
          Header headerCifradoMAC = new Header((byte)10, "none", paramString4, arrayOfByte1);
          headerCifradoMAC.save(fileOutputStream);
          fileInputStream.close();
          fileInputStream = new FileInputStream(paramString1);
          while (true) {
            i = fileInputStream.read(arrayOfByte, 0, close);
            fileOutputStream.write(arrayOfByte, 0, i);
            if (i != close) {
              this.append.I("\nMD: " + srt.I.I(arrayOfByte1));
              fileOutputStream.flush();
              fileOutputStream.close();
              fileInputStream.close();
              return;
            } 
          } 
//          break;
        } 
      } 
    } catch (FileNotFoundException fileNotFoundException) {
      this.append.I("Fichero no se encuentra: " + paramString1 + "\n");
    } catch (IOException iOException) {
      this.append.I("Error de E/S en.\n");
    } catch (Exception exception) {
      this.append.I(String.valueOf(exception.getMessage()) + "\n");
    } 
  }
  
  public final void B(String paramString1, String paramString2, String paramString3, String paramString4) {
    try {
      this.append.I("Proceso de verificación de <" + paramString1 + "> con: " + paramString4 + "\n");
      FileInputStream fileInputStream = new FileInputStream(paramString1);
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      Header headerCifradoMAC = new Header();
      headerCifradoMAC.load(fileInputStream);
      Mac mac = Mac.getInstance(paramString4);
      SecretKey secretKey = practica3.GenerarSecretKey.I(paramString3.toCharArray(), contentEquals, this.I, mac.getMacLength());
      mac.init(secretKey);
      byte[] arrayOfByte = new byte[close];
      while (true) {
        int i = fileInputStream.read(arrayOfByte, 0, close);
        mac.update(arrayOfByte, 0, i);
        fileOutputStream.write(arrayOfByte, 0, i);
        if (i != close) {
          byte[] arrayOfByte1 = mac.doFinal();
          this.append.I("\nHecho.\n");
          String str1 = srt.I.I(arrayOfByte1);
          String str2 = srt.I.I(headerCifradoMAC.getData());
          this.append.I("\nMD almacenado: " + str2);
          this.append.I("\nMD  calculado: " + str1);
          if (str1.contentEquals(str2)) {
            this.append.I("\nHMac idénticos, el fichero no ha sido modificado.\n");
          } else {
            this.append.I("\nHMac diferentes, el fichero ha sido modificado (o la contraseña no es correcta).\n");
            (new File(paramString2)).deleteOnExit();
          } 
          fileInputStream.close();
          fileOutputStream.flush();
          fileOutputStream.close();
          return;
        } 
      } 
    } catch (Exception exception) {
      this.append.I(String.valueOf(exception.getMessage()) + "\n");
    } 
  }
  
  final void MostrarInformacionAlgoritmosCifrado() {
    Set<String> set = Security.getAlgorithms("Cipher");
    this.append.I("\nInformación sobre la JCE:");
    this.append.I("\nAlgoritmos de cifrado disponibles: \n");
    Iterator<String> iterator = set.iterator();
    while (iterator.hasNext())
      this.append.I((new StringBuilder()).append(iterator.next()).append(", ").toString()); 
    this.append.I("\n");
  }
  
  final void MostrarInformacionAlgoritmosResumen() {
    Set<String> set = Security.getAlgorithms("MessageDigest");
    this.append.I("\nInformación sobre la JCE:");
    this.append.I("Algoritmos de resumen disponibles: ");
    Iterator<String> iterator = set.iterator();
    while (iterator.hasNext())
      this.append.I((new StringBuilder()).append(iterator.next()).append(", ").toString()); 
    this.append.I("\n");
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\CifradoOriginal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */