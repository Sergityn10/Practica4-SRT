package practica4;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class B implements ActionListener {
  B(Practica4 paramPractica4) {}

  public final void actionPerformed(ActionEvent paramActionEvent) {
    OptionsDialog optionsDialog = new OptionsDialog(this.getLocation.signtureOpts);
    optionsDialog.pack();
    Point point = Practica4.I(this.getLocation).getLocation();
    point.translate(20, 20);
    optionsDialog.setLocation(point);
    optionsDialog.setVisible(true);
  }
}*/

public class EventoMostrarOpcionesClaves implements ActionListener {
  private final Practica4 practica4;

  // Constructor
  public EventoMostrarOpcionesClaves(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public final void actionPerformed(ActionEvent paramActionEvent) {
    // Crear y configurar el cuadro de diálogo
    OptionsDialog optionsDialog = new OptionsDialog(practica4.getSignatureOptions());
    optionsDialog.pack();

    // Obtener la ubicación de Practica4 y desplazarla
    Point point = practica4.ventanaPrincipal.getLocation();
    point.translate(20, 20);
    optionsDialog.setLocation(point);

    // Hacer visible el cuadro de diálogo
    optionsDialog.setVisible(true);
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\B.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */