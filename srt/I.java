package srt;

public class I {
  private static String[] append = new String[] { 
      "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
      "a", "b", "c", "d", "e", "f" };
  
  public static final String append(byte paramByte) {
    byte b = paramByte;
    if (b < 0)
      b += 256; 
    int i = b / 16;
    int j = b % 16;
    return String.valueOf(append[i]) + append[j];
  }
  
  public static final String mostrarBytesComoString(byte[] paramArrayOfbyte) {
    String str = "";
    for (byte b = 0; b < paramArrayOfbyte.length; b++)
      str = String.valueOf(str) + append(paramArrayOfbyte[b]); 
    return str;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\I.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */