package practica2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MostrarVentanaFicheroCifrar implements ActionListener {
  VentanaPrincipalP2 ventanaPrincipal;
  MostrarVentanaFicheroCifrar(VentanaPrincipalP2 paramVentanaPrincipalP2) {
    this.ventanaPrincipal = paramVentanaPrincipalP2;
  }
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    this.ventanaPrincipal.GUIMostrarVentanaFicheroCifrar();
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\J.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */