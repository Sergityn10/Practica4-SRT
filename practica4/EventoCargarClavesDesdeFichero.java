package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
class H implements ActionListener {
  H(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.Z(this.I.E, this.I.signtureOpts);
  }
}
*/

public class EventoCargarClavesDesdeFichero implements ActionListener {
  private final Practica4 practica4;

  public EventoCargarClavesDesdeFichero(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Lógica para cargar claves desde un archivo
    if (practica4.keypair != null) { // Verificar si el gestor de claves (`E`) existe
      practica4.inicializarParClaves(practica4.keypair, practica4.getSignatureOptions()); // Cargar claves usando el método `Z` de `Practica4`
      practica4.mostrarMensaje("Claves cargadas desde el archivo."); // Mensaje de éxito
    } else {
      practica4.mostrarMensaje("Error: Gestor de claves no inicializado."); // Error si `E` no está disponible
    }
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\H.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */