package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
class K implements ActionListener {
  K(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.C(this.I.E, this.I.A);
  }
}*/

public class K implements ActionListener {
  private final Practica4 practica4;

  public K(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public void actionPerformed(ActionEvent paramActionEvent) {
    try {
      // Guarda las claves en un fichero
      practica4.C(
              practica4.keypair, // Gestor de claves (E)
              practica4.A  // Información o configuración adicional (A)
      );
      // Mensaje de confirmación en la interfaz o log
      practica4.I("Claves guardadas correctamente en el fichero.");
    } catch (Exception exception) {
      // Captura y maneja posibles errores
      practica4.I("Error al guardar las claves: " + exception.getMessage());
    }
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\K.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */