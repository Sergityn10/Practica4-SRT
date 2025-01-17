package srt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;

/*class D extends KeyAdapter {
  D(PasswdDialog paramPasswdDialog, JProgressBar paramJProgressBar) {}

  public final void keyTyped(KeyEvent paramKeyEvent) {
    this.access$1.setValue(PasswdDialog.access$1(this.access$0, PasswdDialog.access$0(this.access$0).getPassword()));
  }
}*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;

public class KeyTypedHandler implements KeyListener {

  private final JProgressBar barraProgreso;
  private final JPasswordField campoPassword;
  private final PasswdDialog passwdDialog;

  // Constructor para inicializar los componentes
  public KeyTypedHandler(JProgressBar barraProgreso, JPasswordField campoPassword) {
    this.barraProgreso = barraProgreso;
    this.campoPassword = campoPassword;
    this.passwdDialog= null;
  }
  public KeyTypedHandler(PasswdDialog paramPasswdDialog, JProgressBar paramJProgressBar) {
    this.barraProgreso = paramJProgressBar;
    this.passwdDialog = paramPasswdDialog;
    this.campoPassword = null;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // Establece el valor de la barra de progreso según la longitud de la contraseña
    String password = new String(this.passwdDialog.getPasswordField().getPassword());
    barraProgreso.setValue(password.length() * 10); // Ejemplo: cada carácter añade 10%
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // No implementado
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // No implementado
  }
}



/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */