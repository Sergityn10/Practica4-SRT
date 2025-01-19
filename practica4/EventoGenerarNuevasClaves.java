package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class E implements ActionListener {
  E(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.I(this.I.E, this.I.signtureOpts);
  }
}*/

public class EventoGenerarNuevasClaves implements ActionListener {
  private final Practica4 practica4;

  public EventoGenerarNuevasClaves(Practica4 practica4) {
    this.practica4 = practica4; // Guardar la referencia al objeto principal
  }

  @Override
  public final void actionPerformed(ActionEvent paramActionEvent) {
    // Ajustamos la lógica para referenciar correctamente los métodos y propiedades de Practica4
    practica4.cargarParClavesDesdeFichero(practica4.keypair,practica4.getSignatureOptions());
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\E.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */