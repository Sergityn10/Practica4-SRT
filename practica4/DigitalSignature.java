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

  /**
   * Realiza el proceso de firma con la clave privada, creando un nuevo fichero con la firma
   * @param inputFile Archivo que se quiere firmar
   * @param outputFile Archivo donde se guarda el nuevo archivo firmado
   * @param paramPrivateKey Clave privada con la que se realiza la firma
   * @param algoritmoClavePrivada Algoritmo utilizado para firmar
   * @param ventanaProgreso Ventana donde se muestra informacion sobre el proceso de firma
   */
  public final void firmarFicheroClavePrivada(String inputFile, String outputFile, PrivateKey paramPrivateKey, String algoritmoClavePrivada, VentanaProgressBarFicheros ventanaProgreso) {
    try {
      ventanaProgreso.I("Firma privada: " + paramPrivateKey.toString());
      ventanaProgreso.I("Firma algoritmo: " + algoritmoClavePrivada);
      FileInputStream fileInputStream = new FileInputStream(inputFile);
      int j = 0;
      int k = fileInputStream.available();
      Signature signature = Signature.getInstance(algoritmoClavePrivada);
      signature.initSign(paramPrivateKey);
      byte[] buffer = new byte[1024];
      int i;
      while ((i = fileInputStream.read(buffer)) > -1) {
        j += i;
        signature.update(buffer, 0, i);
        ventanaProgreso.I(j * 100 / k);
      }

      byte[] firma = signature.sign();

      ventanaProgreso.I("\nFirma (" + firma.length + " bytes): " + FileProtectoMAC.bytesToHex(firma) + "\n");
      FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
      fileInputStream.close();

      FileInputStream newFileInputStream = new FileInputStream(inputFile);
      Header headerCifradoMAC = new Header(SignatureOptions.OP_SIGNED, "none", algoritmoClavePrivada, firma);
      headerCifradoMAC.save(fileOutputStream);

      ventanaProgreso.I("\nEscribiendo fichero firmado: " + outputFile + "\n");
      while ((i = newFileInputStream.read(buffer)) != -1)
        fileOutputStream.write(buffer, 0, i);
      fileOutputStream.close();
      newFileInputStream.close();
    } catch (Exception exception) {
      System.err.println(exception);
    } 
  }

  /**
   * Realiza el proceso de verificacion de firma con la clave publica, creando un nuevo fichero
   * @param pathEntrada Fichero a verificar la firma
   * @param pathSalida Fichreo de salida con la firma verificada
   * @param paramPublicKey Clave publica utilizada para la verificacion de la firma
   * @param ventanaProgreso Ventana auxiliar para mostrar informacion sobre el proceso de verificacion
   * @return
   */
  public final boolean verificarFicheroFirmado(String pathEntrada, String pathSalida, PublicKey paramPublicKey, VentanaProgressBarFicheros ventanaProgreso) {
    boolean bool = false;
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(pathSalida);
      FileInputStream fileInputStream = new FileInputStream(pathEntrada);
      Header cabecera = new Header();
      if (cabecera.load(fileInputStream)) {
        int j = 0;
        int k = fileInputStream.available();
        ventanaProgreso.I("\nFirma original (" + (cabecera.getData()).length + " bytes): " + FileProtectoMAC.bytesToHex(cabecera.getData()) + "\n");
        Signature signature = Signature.getInstance(cabecera.getAlgorithm2());
        ventanaProgreso.I("Algoritmo utilizado: " + cabecera.getAlgorithm2());
        signature.initVerify(paramPublicKey);
        byte[] buffer = new byte[1024];
        int i;
        while ((i = fileInputStream.read(buffer)) > -1) {
          j += i;
          ventanaProgreso.I(j * 100 / k);
          signature.update(buffer, 0, i);
          fileOutputStream.write(buffer, 0, i);
        }

        if (signature.verify(cabecera.getData())) {
          ventanaProgreso.I("\nFirma correcta.\n");
          bool = true;
        } else {
          ventanaProgreso.I("\nFirma no válida.\n");
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

  /**
   * Función para cifrar un fichero utilizando cifrado asimétrico
   * @param inputFile Fichero de entrada a cifrar
   * @param ouputFile Fichero de salida con la información cifrada
   * @param paramPublicKey Clave pública a utilizar para el cifrado
   * @param algoritmoCifAsimetrico Algoritmo de cifrado asimétrico utilizado
   * @param ventanaProgreso Venatana auxiliar para mostrar información relacionada con el proceso de cifrado.
   */
  public final void cifrarBloques(String inputFile, String ouputFile, PublicKey paramPublicKey, String algoritmoCifAsimetrico, practica4.VentanaProgressBarFicheros ventanaProgreso) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(ouputFile);
      FileInputStream fileInputStream = new FileInputStream(inputFile);
      byte[] arrayOfByte1 = new byte[1];
      Header header = new Header((byte)20, algoritmoCifAsimetrico, "none", arrayOfByte1);
      header.save(fileOutputStream);
      Cipher cipher = Cipher.getInstance(algoritmoCifAsimetrico);
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
        ventanaProgreso.I(j * 100 / k);
      } 
      ventanaProgreso.I("\nCifrados " + b2 + " bloques; " + j + " bytes.\n");
      fileOutputStream.close();
      fileInputStream.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }

  /**
   * Función para descifrar un fichero utilizando cifrado asimétrico
   * @param inputFile Fichero a descifrar
   * @param outputFile Fichero donde se guardará el contenido del fichero descifrado
   * @param paramPrivateKey Clave privada a utilizar para descifrar el contenido del archivo
   * @param ventanaProgreso Ventana auxiliar para mostrar información relacionada con el proceso de descifrado.
   */
  
  public final void descifrarBloques(String inputFile, String outputFile, PrivateKey paramPrivateKey, practica4.VentanaProgressBarFicheros ventanaProgreso) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
      FileInputStream fileInputStream = new FileInputStream(inputFile);
      Header header = new Header();
      if (header.load(fileInputStream)) {
        Cipher cipher = Cipher.getInstance(header.getAlgorithm1());
        cipher.init(2, paramPrivateKey);
        byte b1 = 64;
        byte b2 = 0;
        int j = 0;
        int k = fileInputStream.available();
        byte[] buffer = new byte[b1];
        int i;
        while ((i = fileInputStream.read(buffer)) != -1) {
          byte[] arrayOfByte1 = cipher.doFinal(buffer, 0, i);
          fileOutputStream.write(arrayOfByte1);
          b2++;
          j += i;
          ventanaProgreso.I(j * 100 / k);
        } 
        ventanaProgreso.I("\nDescifrados " + b2 + " bloques; " + j + " bytes.\n");
        fileOutputStream.close();
        fileInputStream.close();
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
}


