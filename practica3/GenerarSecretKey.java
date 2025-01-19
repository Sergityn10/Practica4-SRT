package practica3;

import practica2.PBEInicializacion;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class GenerarSecretKey extends PBEInicializacion {

  /**
   * Crea una nueva secret key
   * @param secret Una clave privada para guardar el secreto
   * @param datos Datos que se quieren guardar.
   * @param iterationCount Numero de iteraciones de la funcion de creación de la clave secreta
   * @param macLength Tamaño que va a tener el mac
   * @return SecretKey El valor de la clave secreta creada.
   */
  public static final SecretKey generateSecretKey(char[] secret, byte[] datos, int iterationCount, int macLength) {
    try {
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      PBEKeySpec pBEKeySpec = new PBEKeySpec(secret, datos, iterationCount, macLength);
      return secretKeyFactory.generateSecret(pBEKeySpec);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\I.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */