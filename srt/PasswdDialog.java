package srt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

/**
 * Clase para mostrar una ventana con la logica de una contraseña o clave de paso segura.
 */
public class PasswdDialog extends JDialog {
  public static final byte PD_ONE_PASSWD = 0;
  
  public static final byte PD_TWO_PASSWD = 1;
  
  private static final long serialVersionUID = -110815213728026553L;
  
  private final JPanel contentPanel = new JPanel();
  
  private JPasswordField passwordField;
  
  private JPasswordField passwordField_1;
  
  private char[] passwd;
  
  public static final void main(String[] paramArrayOfString) {
    try {
      PasswdDialog passwdDialog = new PasswdDialog("Password", (byte)1);
      passwdDialog.setDefaultCloseOperation(2);
      passwdDialog.setVisible(true);
      if (passwdDialog.getPasswd() != null)
        System.out.println("Passwd: " + new String(passwdDialog.getPasswd())); 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public final char[] getPasswd() {
    return this.passwd;
  }
  public void setPasswd(char[] passwd) {
    this.passwd = passwd;
  }

  public JPasswordField getPasswordField() {
    return passwordField;
  }

  public JPasswordField getPasswordField1() {
    return passwordField_1;
  }
  
  private int add(char[] paramArrayOfchar) {
    return paramArrayOfchar.length * 3;
  }
  
  public PasswdDialog(String paramString, byte paramByte) {
    setModal(true);
    setTitle(paramString);
    setBounds(100, 100, 350, 156);
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
    JProgressBar jProgressBar = new JProgressBar();
    JLabel jLabel = new JLabel("Valor:");
    this.contentPanel.add(jLabel);
    this.passwordField = new JPasswordField();
    this.passwordField.addKeyListener(new KeyTypedHandler(this, jProgressBar));
    this.contentPanel.add(this.passwordField);
    if (paramByte == 1) {
      jLabel = new JLabel("Repita el valor:");
      this.contentPanel.add(jLabel);
    } 
    if (paramByte == 1) {
      this.passwordField_1 = new JPasswordField();
      this.contentPanel.add(this.passwordField_1);
    } 
    jLabel = new JLabel("Seguridad de la contraseña:");
    this.contentPanel.add(jLabel);
    this.contentPanel.add(jProgressBar);
    JPanel jPanel = new JPanel();
    jPanel.setLayout(new FlowLayout(2));
    getContentPane().add(jPanel, "South");
    JButton jButton = new JButton("OK");
    jButton.addActionListener(new EventoContrasenasNoCoinciden(this, paramByte));
    jButton.setActionCommand("OK");
    jPanel.add(jButton);
    getRootPane().setDefaultButton(jButton);
    jButton = new JButton("Cancel");
    jButton.addActionListener(new EventoMostrarDialogoContrasena(this));
    jButton.setActionCommand("Cancel");
    jPanel.add(jButton);
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\PasswdDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */