package practica3;

import practica2.PBEInicializacion;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class GenerarSecretKey extends PBEInicializacion {
  public static final SecretKey I(char[] paramArrayOfchar, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    try {
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      PBEKeySpec pBEKeySpec = new PBEKeySpec(paramArrayOfchar, paramArrayOfbyte, paramInt1, paramInt2);
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