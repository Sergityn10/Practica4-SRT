package practica4;

import javax.swing.SwingWorker;

/*class J extends SwingWorker {
  J(Practica4 paramPractica4, String paramString, Z paramZ) {}
  
  public final Void append() {
    try {
      this.append.G.I(this.getMessage, this.append.I(this.getMessage, "cla"), this.append.E.C(), this.I);
    } catch (Exception exception) {
      Practica4.Z(this.append).append(exception.getMessage());
    } 
    return null;
  }
}*/

public class VentanaDescifradoAsimetrico extends SwingWorker<Void, Void> {
  private final Practica4 practica4;
  private final String filePath; // Ruta del fichero a descifrar
  private final VentanaProgressBarFicheros logHandler;   // Objeto para manejar los logs (tipo Z)

  public VentanaDescifradoAsimetrico(Practica4 practica4, String filePath, VentanaProgressBarFicheros logHandler) {
    this.practica4 = practica4;
    this.filePath = filePath;
    this.logHandler = logHandler;
  }

  @Override
  protected Void doInBackground() {
    try {
      // Realiza el descifrado utilizando las claves almacenadas y el archivo proporcionado
      practica4.digitalSignature.descifrarBloques(
              filePath,
              practica4.cambioExtensionFichero(filePath, "cla"), // Generar nombre para la clave descifrada
              practica4.keypair.getPrivateKey(),               // Obtener claves privadas o necesarias
              //practica4.signtureOpts.getPublicCipher(),  // Algoritmo de cifrado/descifrado
              logHandler
      );

      // Actualizar log con éxito
      logHandler.I("\nDescifrado completado correctamente para el archivo: " + filePath);
    } catch (Exception exception) {
      // Captura cualquier excepción y registra el mensaje de error en el log
      logHandler.I("Error durante el descifrado: " + exception.getMessage());
    }
    return null;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\J.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */