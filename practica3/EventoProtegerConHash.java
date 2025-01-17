package practica3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//CLASE PARA HACER LA LOGICA DE LA INTERFAZ PARA PROTEGER UN FICHERO CON HASH
public class EventoProtegerConHash implements ActionListener {
  private final Practica3 ventana;

  // Constructor que recibe la referencia de la clase S
  public EventoProtegerConHash(Practica3 ventana) {
    this.ventana = ventana;
  }

  @Override
  public final void actionPerformed(ActionEvent paramActionEvent) {
    // Invocar el método correspondiente de la clase S
    ventana.protegerConHash(); // Método a implementar en la clase S
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */