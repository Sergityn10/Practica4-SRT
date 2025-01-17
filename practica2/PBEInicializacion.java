package practica2;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PBEInicializacion {
  public static final SecretKey I(String paramString, char[] paramArrayOfchar) {
    try {
      PBEKeySpec pBEKeySpec = new PBEKeySpec(paramArrayOfchar);
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(paramString);
      return secretKeyFactory.generateSecret(pBEKeySpec);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
  }
  
  public static final PBEParameterSpec I(byte[] paramArrayOfbyte, int paramInt) {
    try {
      return new PBEParameterSpec(paramArrayOfbyte, paramInt);
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