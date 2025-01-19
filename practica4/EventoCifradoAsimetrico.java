package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class L implements ActionListener {
  L(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.Q();
  }
}*/


public class EventoCifradoAsimetrico implements ActionListener {
  private final Practica4 practica4;

  public EventoCifradoAsimetrico(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public void actionPerformed(ActionEvent paramActionEvent) {
    try {
      // Llama al método Q() de Practica4 para realizar el cifrado
      practica4.procesoCifradoAsimetrico();
    } catch (Exception exception) {
      // Manejo de posibles excepciones durante el cifrado
      practica4.mostrarMensaje("Error al cifrar la clave pública: " + exception.getMessage());
    }
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\L.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */