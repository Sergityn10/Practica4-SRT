package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class O implements ActionListener {
  O(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.U();
  }
}*/

public class O implements ActionListener {
  private final Practica4 practica4;

  public O(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public void actionPerformed(ActionEvent paramActionEvent) {
    try {
      // Llama al método U() de Practica4 para realizar la verificación de la firma
      practica4.U();
    } catch (Exception exception) {
      // Manejo de errores durante el proceso de verificación
      practica4.I("Error al verificar la firma: " + exception.getMessage());
    }
  }
}

/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\O.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */