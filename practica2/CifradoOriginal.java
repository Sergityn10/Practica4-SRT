package practica2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Security;
import java.util.Iterator;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import srt.B;
import srt.Header;

public class CifradoOriginal extends B {
  private VentanaPrincipalP2 append = null;
  
  private byte[] SALT = new byte[] { 125, 96, 67, 95, 2, -23, -32, -82 };
  
  public int num_bytes = 1024;
  
  public CifradoOriginal() {}
  
  public CifradoOriginal(VentanaPrincipalP2 paramVentanaPrincipalP2) {
    this.append = paramVentanaPrincipalP2;
  }

  /**
   *  Calcula la posibilidad de cargar la cabecera de un archivo, dado dicho archivo por parametro de entrada.
   *
   * @param inputFile Path del fichero origen a leer la cabecera
   * @param paramHeader Cabecera donde se cargaría la cabecera existente en el fichero.
   * @return True si es posible cargar la cabecera. False en caso contrario
   */
  public final boolean leerFichero(String inputFile, Header paramHeader) {
    try {
      FileInputStream fileInputStream = new FileInputStream(inputFile);
      boolean bool = paramHeader.load(fileInputStream);
      fileInputStream.close();
      return bool;
    } catch (Exception exception) {
      this.append.mostrarMensaje("Problemas al leer el fichero: " + inputFile + "\n");
      return false;
    } 
  }
  /**
   * Encripta un archivo utilizando la clave pública proporcionada.
   *
   * @param inputFile Ruta del archivo que se desea encriptar.
   * @param encryptedFile Ruta del archivo donde se guardará el contenido encriptado.
   * @param password Contraseña o clave de paso utilizada para cifrar el fichero.
   * @param cipherAlgorithm Algoritmo de cifrado a utiliza en dicha funcion
   * @throws Exception Si ocurre un error al leer/escribir archivos o durante el proceso de encriptación.
   */
  public final void procesoCifrado(String inputFile, String encryptedFile, char[] password, String cipherAlgorithm) {
    this.append.mostrarMensaje("Proceso de cifrado de <" + inputFile + "> con Algoritmo: " + cipherAlgorithm + "\n");
    try {
      FileInputStream fileInputStream = new FileInputStream(inputFile);
      FileOutputStream fileOutputStream = new FileOutputStream(encryptedFile);
      Header headerCifradoMAC = new Header((byte)1, cipherAlgorithm, "none", this.SALT);
      SecretKey secretKey = PBEInicializacion.generateSessionKey(cipherAlgorithm, password);
      PBEParameterSpec pBEParameterSpec = PBEInicializacion.preparcionPBESpec(this.SALT, this.num_bytes);
      headerCifradoMAC.save(fileOutputStream);
      Cipher cipher = Cipher.getInstance(cipherAlgorithm);
      cipher.init(1, secretKey, pBEParameterSpec);
      CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
      int j = 0;
      byte[] arrayOfByte = new byte[1024];
      int i;
      while ((i = fileInputStream.read(arrayOfByte)) != -1) {
        cipherOutputStream.write(arrayOfByte, 0, i);
        j += i;
        this.append.mostrarMensaje(String.valueOf(i) + ".");
      } 
      this.append.mostrarMensaje("\nHecho: " + j + "\n");
      cipherOutputStream.flush();
      cipherOutputStream.close();
      fileOutputStream.close();
      fileInputStream.close();
    } catch (FileNotFoundException fileNotFoundException) {
      this.append.mostrarMensaje("Fichero no se encuentra: " + inputFile + "\n");
    } catch (IOException iOException) {
      this.append.mostrarMensaje("Error de E/S en.\n");
    } catch (Exception exception) {
      this.append.mostrarMensaje(String.valueOf(exception.getMessage()) + "\n");
    } 
  }

  /**
   * Desencripta un archivo utilizando la clave privada proporcionada.
   *
   * @param encryptedFile Ruta del archivo encriptado que se desea desencriptar.
   * @param outputFile Ruta del archivo donde se guardará el contenido desencriptado.
   * @param password Clave privada utilizada para desencriptar el contenido del archivo.
   * @param cipherAlgorithm Algoritmo utilizado para el proceso de descifrado del archivo.
   * @throws Exception Si ocurre un error al leer/escribir archivos o durante el proceso de desencriptación.
   */
  public final void procesoDescrifrado(String encryptedFile, String outputFile, char[] password, String cipherAlgorithm) {
    try {
      this.append.mostrarMensaje("Proceso de descifrado de <" + encryptedFile + "> con: " + cipherAlgorithm + "\n");
      FileInputStream fileInputStream = new FileInputStream(encryptedFile);
      FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
      Header headerCifradoMAC = new Header();
      if (headerCifradoMAC.load(fileInputStream)) {
        SecretKey secretKey = PBEInicializacion.generateSessionKey(cipherAlgorithm, password);
        PBEParameterSpec pBEParameterSpec = PBEInicializacion.preparcionPBESpec(headerCifradoMAC.getData(), this.num_bytes);
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(2, secretKey, pBEParameterSpec);
        CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipher);
        int j = 0;
        byte[] arrayOfByte = new byte[1024];
        int i;
        while ((i = cipherInputStream.read(arrayOfByte)) != -1) {
          fileOutputStream.write(arrayOfByte, 0, i);
          j += i;
          this.append.mostrarMensaje(String.valueOf(i) + ".");
        } 
        this.append.mostrarMensaje("\nHecho: " + j + "\n");
        cipherInputStream.close();
        fileInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
      } else {
        this.append.mostrarMensaje("Error de cabecera.\n");
      } 
    } catch (IOException iOException) {
      this.append.mostrarMensaje("Error de E/S.\n");
    } catch (Exception exception) {
      this.append.mostrarMensaje(String.valueOf(exception.getMessage()) + "\n");
      exception.printStackTrace();
    } 
  }
  /**
   * Muestra los algoritmos de cifrado disponibles
   */
  final void getAlgoritmosCifradoDisponibles() {
    Set<String> set = Security.getAlgorithms("Cipher");
    this.append.mostrarMensaje("\nInformación sobre la JCE:");
    this.append.mostrarMensaje("\nAlgoritmos de cifrado disponibles: \n");
    Iterator<String> iterator = set.iterator();
    while (iterator.hasNext())
      this.append.mostrarMensaje((new StringBuilder()).append(iterator.next()).append(", ").toString());
    this.append.mostrarMensaje("\n");
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\CifradoOriginal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */