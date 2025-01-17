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
  
  private byte[] close = new byte[] { 125, 96, 67, 95, 2, -23, -32, -82 };
  
  public int I = 1024;
  
  public CifradoOriginal() {}
  
  public CifradoOriginal(VentanaPrincipalP2 paramVentanaPrincipalP2) {
    this.append = paramVentanaPrincipalP2;
  }
  
  public final boolean leerFichero(String paramString, Header paramHeader) {
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
  
  public final void procesoCifrado(String paramString1, String paramString2, char[] password, String paramString3) {
    this.append.I("Proceso de cifrado de <" + paramString1 + "> con Algoritmo: " + paramString3 + "\n");
    try {
      FileInputStream fileInputStream = new FileInputStream(paramString1);
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      Header headerCifradoMAC = new Header((byte)1, paramString3, "none", this.close);
      SecretKey secretKey = PBEInicializacion.I(paramString3, password);
      PBEParameterSpec pBEParameterSpec = PBEInicializacion.I(this.close, this.I);
      headerCifradoMAC.save(fileOutputStream);
      Cipher cipher = Cipher.getInstance(paramString3);
      cipher.init(1, secretKey, pBEParameterSpec);
      CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
      int j = 0;
      byte[] arrayOfByte = new byte[1024];
      int i;
      while ((i = fileInputStream.read(arrayOfByte)) != -1) {
        cipherOutputStream.write(arrayOfByte, 0, i);
        j += i;
        this.append.I(String.valueOf(i) + ".");
      } 
      this.append.I("\nHecho: " + j + "\n");
      cipherOutputStream.flush();
      cipherOutputStream.close();
      fileOutputStream.close();
      fileInputStream.close();
    } catch (FileNotFoundException fileNotFoundException) {
      this.append.I("Fichero no se encuentra: " + paramString1 + "\n");
    } catch (IOException iOException) {
      this.append.I("Error de E/S en.\n");
    } catch (Exception exception) {
      this.append.I(String.valueOf(exception.getMessage()) + "\n");
    } 
  }
  
  public final void procesoDescrifrado(String nombreFichero, String paramString2, char[] paramArrayOfchar, String algoritmoCifrado) {
    try {
      this.append.I("Proceso de descifrado de <" + nombreFichero + "> con: " + algoritmoCifrado + "\n");
      FileInputStream fileInputStream = new FileInputStream(nombreFichero);
      FileOutputStream fileOutputStream = new FileOutputStream(paramString2);
      Header headerCifradoMAC = new Header();
      if (headerCifradoMAC.load(fileInputStream)) {
        SecretKey secretKey = PBEInicializacion.I(algoritmoCifrado, paramArrayOfchar);
        PBEParameterSpec pBEParameterSpec = PBEInicializacion.I(headerCifradoMAC.getData(), this.I);
        Cipher cipher = Cipher.getInstance(algoritmoCifrado);
        cipher.init(2, secretKey, pBEParameterSpec);
        CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipher);
        int j = 0;
        byte[] arrayOfByte = new byte[1024];
        int i;
        while ((i = cipherInputStream.read(arrayOfByte)) != -1) {
          fileOutputStream.write(arrayOfByte, 0, i);
          j += i;
          this.append.I(String.valueOf(i) + ".");
        } 
        this.append.I("\nHecho: " + j + "\n");
        cipherInputStream.close();
        fileInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
      } else {
        this.append.I("Error de cabecera.\n");
      } 
    } catch (IOException iOException) {
      this.append.I("Error de E/S.\n");
    } catch (Exception exception) {
      this.append.I(String.valueOf(exception.getMessage()) + "\n");
      exception.printStackTrace();
    } 
  }
  
  final void getAlgoritmosCifradoDisponibles() {
    Set<String> set = Security.getAlgorithms("Cipher");
    this.append.I("\nInformación sobre la JCE:");
    this.append.I("\nAlgoritmos de cifrado disponibles: \n");
    Iterator<String> iterator = set.iterator();
    while (iterator.hasNext())
      this.append.I((new StringBuilder()).append(iterator.next()).append(", ").toString()); 
    this.append.I("\n");
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\CifradoOriginal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */