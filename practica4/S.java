package practica4;

import javax.swing.SwingWorker;

/*class S extends SwingWorker {
  S(Practica4 paramPractica4, String paramString, Z paramZ) {}
  
  public final Void append() {
    try {
      this.append.G.I(this.getMessage, this.append.I(this.getMessage, "fir"), this.append.E.C(), this.append.A.getSignAlgorithm(), this.getSignAlgorithm);
    } catch (Exception exception) {
      Practica4.Z(this.append).append(exception.getMessage());
    } 
    return null;
  }
}*/

public class S extends SwingWorker {
  private final Practica4 practica4;
  private final String filePath;
  private final Z logger;

  // Constructor
  public S(Practica4 practica4, String filePath, Z logger) {
    this.practica4 = practica4;
    this.filePath = filePath;
    this.logger = logger;
  }

  @Override
  protected Void doInBackground() {
    try {
      // Realiza la verificación llamando a los métodos necesarios de Practica4
      practica4.digitalSignature.firmarFicheroClavePrivada(
              filePath,                     // Ruta del archivo
              this.practica4.I(filePath, "fir"), // Clave asociada al archivo
              this.practica4.keypair.C(),              // Algoritmo de configuración
              this.practica4.A.getSignAlgorithm(),
              logger                        // Logger para los mensajes
      );
    } catch (Exception e) {
      // Registrar el error en el área de logs de Practica4
      practica4.I("Error durante la verificación: " + e.getMessage());
    }
    return null;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\S.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */