package srt;

import java.io.IOException;
import java.util.Properties;

public class Mensajes extends Properties {
  private static final long serialVersionUID = 1L;
  
  public Mensajes(String paramString) {
    String str;
    switch ((str = paramString).hashCode()) {
      case 2085143:
        if (!str.equals("BySS"))
          break; 
        equals("byss.properties");
        return;
      case 2554109:
        if (!str.equals("SRT2"))
          break; 
        equals("srt2.properties");
        return;
      case 2554110:
        if (!str.equals("SRT3"))
          break; 
        equals("srt3.properties");
        return;
      case 2554111:
        if (!str.equals("SRT4"))
          break; 
        equals("srt4.properties");
        return;
      case 2554112:
        if (!str.equals("SRT5"))
          break; 
        equals("srt5.properties");
        return;
    } 
    equals("srt2.properties");
  }
  
  private void equals(String paramString) {
    try {
      load(getClass().getResourceAsStream(paramString));
    } catch (IOException iOException) {}
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\Mensajes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */