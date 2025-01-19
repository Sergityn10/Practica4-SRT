package practica2;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PBEInicializacion {
  /**
   * Genera una clave de sesión a partir de una contraseña y un algoritmo
   *
   * @param password  Contraseña a partir de la cual se generará la clave de sesión
   * @param algorithm Algoritmo con el que se generará la clave de sesión
   * @return SecretKey Clave de sesión generada
   * @throws Exception Si el algoritmo no existe o si la clave generada no es válida
   */
  public static final SecretKey generateSessionKey(String algorithm, char[] password) {
    try {
      PBEKeySpec pBEKeySpec = new PBEKeySpec(password);
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
      return secretKeyFactory.generateSecret(pBEKeySpec);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
  }

  /**
   * Devuelve el parametro de preparacion PBE para el proceso de cifrado de nuestro archivo.
   *
   * @param salt Valor aleatorio que se agrega a los datos al realizar el proceso de cifrado
   * @param iteration Numero de iteraciones que realizará nuestro proceso de cifrado
   * @return
   */
  public static final PBEParameterSpec preparcionPBESpec(byte[] salt, int iteration) {
    try {
      return new PBEParameterSpec(salt, iteration);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\I.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */