package srt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class EventoContrasenasNoCoinciden implements ActionListener {
  private final PasswdDialog dialog;
  private final byte mode;

  // Constructor que inicializa el diálogo y el modo
  public EventoContrasenasNoCoinciden(PasswdDialog dialog, byte mode) {
    this.dialog = dialog;
    this.mode = mode;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    // Verifica si el modo requiere dos contraseñas
    if (mode == PasswdDialog.PD_TWO_PASSWD) {
      // Compara las contraseñas ingresadas
      if (Arrays.equals(dialog.getPasswordField().getPassword(),
              dialog.getPasswordField1().getPassword())) {
        dialog.setPasswd(dialog.getPasswordField().getPassword());
        dialog.setVisible(false);
      } else {
        // Aquí podrías agregar un mensaje de error al usuario si las contraseñas no coinciden
        System.out.println("Las contraseñas no coinciden.");
      }
    } else {
      // En modo de una sola contraseña, almacena la contraseña directamente
      dialog.setPasswd(dialog.getPasswordField().getPassword());
      dialog.setVisible(false);
    }
  }
}



/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\crearMenuItemCifrar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */