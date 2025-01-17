package practica4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class G implements ActionListener {
  G(Practica4 paramPractica4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.I.I(this.I.E);
  }
}*/

public class G implements ActionListener {
  private final Practica4 practica4;

  public G(Practica4 practica4) {
    this.practica4 = practica4;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Lógica para mostrar claves actuales
    if (practica4.keypair != null && practica4.keypair.I()) { // Verificar si hay claves disponibles
      practica4.I(practica4.keypair); // Mostrar las claves usando el método `I` de `Practica4`
    } else {
      practica4.I("No tenemos claves."); // Mostrar mensaje si no hay claves disponibles
    }
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\G.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */