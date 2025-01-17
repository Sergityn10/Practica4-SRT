package practica4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;
import practica3.FileProtectoMAC;
import srt.Header;

public class DigitalSignature extends FileProtectoMAC {
  private Practica4 append = null;
  
  public DigitalSignature() {}
  
  public DigitalSignature(Practica4 paramPractica4) {
    this.append = paramPractica4;
  }
  
  public final void firmarFicheroClavePrivada(String path, String paramString2, PrivateKey paramPrivateKey, String algoritmoClavePrivada, Z paramZ) {
    try {
      paramZ.I("Firma privada: " + paramPrivateKey.toString());
      paramZ.I("Firma algoritmo: " + algoritmoClavePrivada);
      FileInputStream fileInputStream = new FileInputStream(path);
      int j = 0;
      int k = fileInputStream.available();
      Signature signature = Signature.getInstance(algoritmoClavePrivada);
      signature.initSign(paramPrivateKey);
      byte[] arrayOfByte1 = new byte[16];
      int i;
      while ((i = fileInputStream.read(arrayOfByte1)) > -1) {
        j += i;
        signature.update(arrayOfByte1, 0, i);
        paramZ.I(j * 100 / k);
      }

      byte[] arrayOfByte2 = signature.sign(); //TODO AQUI ESTA EL FALLO
      paramZ.I("\nFirma (" + arrayOfByte2.length + " bytes): " + srt.I.I(arrayOfByte2) + "\n");
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      fileInputStream.close();

      FileInputStream newFileInputStream = new FileInputStream(path);
      Header headerCifradoMAC = new Header(SignatureOptions.OP_SIGNED, "none", algoritmoClavePrivada, arrayOfByte2);
      headerCifradoMAC.save(fileOutputStream);
      paramZ.I("\nEscribiendo fichero firmado: " + paramString2 + "\n");
      while ((i = newFileInputStream.read(arrayOfByte1)) != -1)
        fileOutputStream.write(arrayOfByte1, 0, i); 
      fileOutputStream.close();
      newFileInputStream.close();
    } catch (Exception exception) {
      System.err.println(exception);
    } 
  }
  
  public final boolean verificarFicheroFirmado(String pathEntrada, String pathSalida, PublicKey paramPublicKey, Z paramZ) {
    boolean bool = false;
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(pathSalida);
      FileInputStream fileInputStream = new FileInputStream(pathEntrada);
      Header z = new Header();
      paramZ.I("Firma publica: " + paramPublicKey.toString());
      if (z.load(fileInputStream)) {
        int j = 0;
        int k = fileInputStream.available();
        paramZ.I("\nFirma original (" + (z.getData()).length + " bytes): " + srt.I.I(z.getData()) + "\n");
        Signature signature = Signature.getInstance(z.getAlgorithm2());
        paramZ.I("Algoritmo utilizado: " + z.getAlgorithm2());
        signature.initVerify(paramPublicKey);
        byte[] arrayOfByte = new byte[1024];
        int i;
        while ((i = fileInputStream.read(arrayOfByte)) > -1) {
          j += i;
          paramZ.I(j * 100 / k);
          signature.update(arrayOfByte, 0, i);
          fileOutputStream.write(arrayOfByte, 0, i);
        } 
        if (signature.verify(z.getData())) {
          paramZ.I("\nFirma correcta.\n");
          bool = true;
        } else {
          paramZ.I("\nFirma no válida.\n");
          bool = false;
        } 
        fileOutputStream.close();
        fileInputStream.close();
      } 
    } catch (Exception exception) {
      System.err.println(exception);
    } 
    return bool;
  }
  
  public final void cifrarBloques(String paramString1, String paramString2, PublicKey paramPublicKey, String paramString3, practica4.Z paramZ) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      FileInputStream fileInputStream = new FileInputStream(paramString1);
      byte[] arrayOfByte1 = new byte[1];
      Header z = new Header((byte)20, paramString3, "none", arrayOfByte1);
      z.save(fileOutputStream);
      Cipher cipher = Cipher.getInstance(paramString3);
      cipher.init(1, paramPublicKey);
      byte b1 = 53;
      byte b2 = 0;
      int j = 0;
      int k = fileInputStream.available();
      byte[] arrayOfByte2 = new byte[b1];
      int i;
      while ((i = fileInputStream.read(arrayOfByte2)) != -1) {
        byte[] arrayOfByte = cipher.doFinal(arrayOfByte2, 0, i);
        fileOutputStream.write(arrayOfByte);
        b2++;
        j += i;
        paramZ.I(j * 100 / k);
      } 
      paramZ.I("\nCifrados " + b2 + " bloques; " + j + " bytes.\n");
      fileOutputStream.close();
      fileInputStream.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public final void descifrarBloques(String paramString1, String paramString2, PrivateKey paramPrivateKey, practica4.Z paramZ) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      FileInputStream fileInputStream = new FileInputStream(paramString1);
      Header z = new Header();
      if (z.load(fileInputStream)) {
        Cipher cipher = Cipher.getInstance(z.getAlgorithm1());
        cipher.init(2, paramPrivateKey);
        byte b1 = 64;
        byte b2 = 0;
        int j = 0;
        int k = fileInputStream.available();
        byte[] arrayOfByte = new byte[b1];
        int i;
        while ((i = fileInputStream.read(arrayOfByte)) != -1) {
          byte[] arrayOfByte1 = cipher.doFinal(arrayOfByte, 0, i);
          fileOutputStream.write(arrayOfByte1);
          b2++;
          j += i;
          paramZ.I(j * 100 / k);
        } 
        paramZ.I("\nDescifrados " + b2 + " bloques; " + j + " bytes.\n");
        fileOutputStream.close();
        fileInputStream.close();
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\C.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */