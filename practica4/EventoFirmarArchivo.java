package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class N implements ActionListener {
  N(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.T();
  }
}*/

public class EventoFirmarArchivo implements ActionListener {
  private final Practica4 practica4;

  public EventoFirmarArchivo(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public void actionPerformed(ActionEvent paramActionEvent) {
    try {
      // Llama al método T() de Practica4 para realizar el proceso de firma
      practica4.procesoFirmaDigital();
    } catch (Exception exception) {
      // Manejo de errores durante el proceso de firma
      practica4.mostrarMensaje("Error al firmar: " + exception.getMessage());
    }
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\N.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */