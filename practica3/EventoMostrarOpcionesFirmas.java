package practica3;

import practica4.OptionsDialog;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoMostrarOpcionesFirmas implements ActionListener {
  private final Practica3 ventana;

  // Constructor que recibe una referencia a la instancia de la clase S
  public EventoMostrarOpcionesFirmas(Practica3 ventana) {
    this.ventana = ventana;
  }

  @Override
  public final void actionPerformed(ActionEvent paramActionEvent) {
    // Crear un objeto OptionsDialog usando los parámetros necesarios
    OptionsDialog optionsDialog = new OptionsDialog(ventana.getSignatureOptions());
    optionsDialog.pack();

    // Obtener la ubicación de la ventana principal
    Point point = ventana.I.getLocation();
    point.translate(20, 20); // Desplazar la ubicación para posicionar el diálogo
    optionsDialog.setLocation(point);

    // Hacer visible el cuadro de diálogo
    optionsDialog.setVisible(true);
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\C.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */