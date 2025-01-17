package practica4;

import javax.swing.SwingWorker;
import java.util.Arrays;

/*class F extends SwingWorker {
  F(Practica4 paramPractica4, String paramString, Z paramZ) {}
  
  public final Void append() {
    try {
      this.append.G.I(this.getMessage, this.append.I(this.getMessage, "cif"), this.append.E.Z(), this.append.A.getPublicCipher(), this.getPublicCipher);
    } catch (Exception exception) {
      Practica4.Z(this.append).append(exception.getMessage());
    } 
    return null;
  }
}*/

public class F extends SwingWorker<Void, Void> {
  private final Practica4 practica4;
  private final String filePath; // Ruta del archivo a cifrar
  private final Z logger;       // Objeto para mostrar mensajes o registrar progreso

  public F(Practica4 practica4, String filePath, Z logger) {
    this.practica4 = practica4;
    this.filePath = filePath;
    this.logger = logger;
  }

  @Override
  protected Void doInBackground() {
    try {
      // Obtener datos necesarios desde Practica4
      String publicCipher = practica4.getSignatureOptions().getPublicCipher(); // Algoritmo de cifrado
      String keyFilename = practica4.getSignatureOptions().getKeyFilename(); // Clave pública
      String keyFilePasswd = Arrays.toString(practica4.getSignatureOptions().getKeyFilePasswd()); // Contraseña de clave

      // Simular el proceso de cifrado
      logger.I("\nIniciando cifrado del archivo: " + filePath);
      logger.I("\nUsando algoritmo: " + publicCipher);
//      practica4.E.I(filePath, "cif", keyFilename, publicCipher, keyFilePasswd);
      practica4.digitalSignature.cifrarBloques(filePath, practica4.I(filePath,"cif"), practica4.keypair.Z(), practica4.A.getPublicCipher(),logger);
      logger.I("\nCifrado completado con éxito.");
    } catch (Exception e) {
      // Registrar cualquier excepción
      logger.I("Error durante el cifrado: " + e.getMessage());
    }
    return null;
  }

  @Override
  protected void done() {
    logger.I("\nOperación finalizada.");
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\F.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */