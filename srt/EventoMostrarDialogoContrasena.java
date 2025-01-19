package srt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EventoMostrarDialogoContrasena implements ActionListener {
  private final PasswdDialog dialog;

  // Constructor que inicializa el diálogo
  public EventoMostrarDialogoContrasena(PasswdDialog dialog) {
    this.dialog = dialog;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    // Limpia la contraseña y cierra el diálogo
    dialog.setPasswd(null); // Establece la contraseña como null
    dialog.setVisible(false); // Oculta el diálogo
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\J.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */