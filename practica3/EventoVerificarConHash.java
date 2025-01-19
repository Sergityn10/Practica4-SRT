package practica3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para crear la lógica del GUI para verificar el hash de un archivo.
 */
public class EventoVerificarConHash implements ActionListener {
  private final Practica3 ventana;

  public EventoVerificarConHash(Practica3 ventana) {
    this.ventana = ventana;
  }

  @Override
  public final void actionPerformed(ActionEvent paramActionEvent) {
    // Llamar al método L() en la clase S, que contiene la lógica para verificar el hash
    ventana.verificarHash();
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\crearMenuItemCifrar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */