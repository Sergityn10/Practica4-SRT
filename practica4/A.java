package practica4;

import javax.swing.SwingWorker;

/*class A extends SwingWorker {
  private final Practica4 practica4;
  private final String filePath;
  private final Z logger;

  A(Practica4 paramPractica4, String filePath, Z logger) {
      this.practica4 = paramPractica4;
      this.filePath = filePath;
      this.logger = logger;
  }
  
  public final Void append() {
    try {
      this.practica4.G.I(this.getMessage, this.practica4.I(this.getMessage, "cla"), this.practica4.E.Z(), this.I);
    } catch (Exception exception) {
      practica4.Z().add(exception.getMessage());
    } 
    return null;
  }

  @Override
  protected Object doInBackground() throws Exception {
    return null;
  }
}*/

public class A extends SwingWorker {
  private final Practica4 practica4;
  private final String filePath;
  private final Z logger;

  // Constructor
  public A(Practica4 practica4, String filePath, Z logger) {
    this.practica4 = practica4;
    this.filePath = filePath;
    this.logger = logger;
  }

  @Override
  protected Void doInBackground() {
    try {
      // Realiza la verificación llamando a los métodos necesarios de Practica4
      practica4.digitalSignature.verificarFicheroFirmado(
              filePath,                     // Ruta del archivo
              practica4.I(filePath, "cla"), // Clave asociada al archivo
              practica4.keypair.Z(),              // Algoritmo de configuración
              logger                        // Logger para los mensajes
      );
    } catch (Exception e) {
      // Registrar el error en el área de logs de Practica4
      practica4.I("Error durante la verificación: " + e.getMessage());
    }
    return null;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\A.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */