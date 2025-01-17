package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class M implements ActionListener {
  M(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.R();
  }
}*/

public class M implements ActionListener {
  private final Practica4 practica4;

  public M(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public void actionPerformed(ActionEvent paramActionEvent) {
    try {
      // Llama al método R() de Practica4 para realizar el descifrado
      practica4.R();
    } catch (Exception exception) {
      // Manejo de posibles excepciones durante el descifrado
      practica4.I("Error al descifrar la clave privada: " + exception.getMessage());
    }
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\M.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */