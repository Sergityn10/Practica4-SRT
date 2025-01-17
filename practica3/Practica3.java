package practica3;

import java.awt.Point;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import practica2.VentanaPrincipalP2;
import srt.Options;
import srt.PasswdDialog;
import srt.Header;

public class Practica3 extends VentanaPrincipalP2 {
  protected JMenuItem getAuthenticator = null;
  
  protected JMenuItem getLocation = null;
  
  private Options getMessage = new Options();
  
  FileProtectoMAC getPasswd = null;
  
  public Practica3() {
    this.getPasswd = new FileProtectoMAC(this);
    this.getMessage.setSymmetricalCipher(Options.symmetricalAlgorithms[0]);
    this.getMessage.setAuthenticator(Options.hashmacAlgorithms[0]);
  }

  protected JMenuBar Z() {
    if (this.C == null) {
      this.C = new JMenuBar();
      this.C.add(getAuthenticator());
      this.C.add(getLocation());
      this.C.add(C());
    } 
    return this.C;
  }
  
  private JMenu getAuthenticator() {
    if (this.B == null) {
      this.B = new JMenu();
      this.B.setText("Fichero");
      this.B.add(F());
      this.B.add(J());
      this.B.add(G());
      this.B.add(H());
      this.B.add(funcionSalirVentana());
    } 
    return this.B;
  }
  
  private JMenu getLocation() {
    if (this.D == null) {
      this.D = new JMenu();
      this.D.setText("Opciones");
      this.D.add(D());
      this.D.add(getMessage());
    } 
    return this.D;
  }
  
  protected JMenuItem D() {
    if (this.F == null) {
      this.F = new JMenuItem();
      this.F.setText("Opciones");
      this.F.setAccelerator(KeyStroke.getKeyStroke(79, 2, true));
      this.F.addActionListener(new EventoMostrarOpcionesFirmas(this));
    } 
    return this.F;
  }
  
  private JMenuItem getMessage() {
    if (this.J == null) {
      this.J = new JMenuItem();
      this.J.setText("Ver información");
      this.J.setAccelerator(KeyStroke.getKeyStroke(73, 2, true));
      this.J.addActionListener(new EventoVerInformacion(this));
    } 
    return this.J;
  }
  
  protected final JMenuItem G() {
    if (this.getAuthenticator == null) {
      this.getAuthenticator = new JMenuItem();
      this.getAuthenticator.setText("Proteger con hash");
      this.getAuthenticator.setAccelerator(KeyStroke.getKeyStroke(72, 2, true));
      this.getAuthenticator.addActionListener(new EventoProtegerConHash(this));
    } 
    return this.getAuthenticator;
  }
  
  protected final JMenuItem H() {
    if (this.getLocation == null) {
      this.getLocation = new JMenuItem();
      this.getLocation.setText("Verificar hash");
      this.getLocation.setAccelerator(KeyStroke.getKeyStroke(86, 2, true));
      this.getLocation.addActionListener(new EventoVerificarConHash(this));
    } 
    return this.getLocation;
  }
  
  public void I(String paramString) {
    this.S.append(paramString);
    this.I.repaint();
  }
  
  public static void main(String[] paramArrayOfString) {
    SwingUtilities.invokeLater(new EventoVisibilizarVentanaPractica3());
  }
  
  public final void protegerConHash() {
    String str;
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a proteger con Hash/HMac");
    if (Options.isTypeAlgorithm(Options.hashAlgorithms, this.getMessage.getAuthenticator())) {
      str = "Secreto";
    } else {
      str = "Contraseña";
    } 
    int i = jFileChooser.showOpenDialog(this.Z);
    if (i == 0) {
      String str1 = jFileChooser.getSelectedFile().getAbsolutePath();
      PasswdDialog passwdDialog = new PasswdDialog(str, (byte)1);
      passwdDialog.pack();
      Point point = I().getLocation();
      point.translate(20, 20);
      passwdDialog.setLocation(point);
      passwdDialog.setVisible(true);
      String str2 = new String(passwdDialog.getPasswd());
      try {
        if (Options.isTypeAlgorithm(Options.hashAlgorithms, this.getMessage.getAuthenticator())) {
          this.getPasswd.I(str1, I(str1, "hsh"), str2, this.getMessage.getAuthenticator());
        } else {
          this.getPasswd.C(str1, I(str1, "mac"), str2, this.getMessage.getAuthenticator());
        } 
      } catch (Exception exception) {
        this.S.append(exception.getMessage());
      } 
    } 
  }
  
  public final void verificarHash() {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a verificar");
    int i = jFileChooser.showOpenDialog(this.Z);
    if (i == 0) {
      String str = jFileChooser.getSelectedFile().getAbsolutePath();
      Header z = new Header();
      if (this.getPasswd.Z(str, z)) {
        String str1;
        if (Options.isTypeAlgorithm(Options.hashAlgorithms, z.getAlgorithm2())) {
          str1 = "Secreto";
        } else {
          str1 = "Contraseña";
        } 
        PasswdDialog passwdDialog = new PasswdDialog(str1, (byte)0);
        passwdDialog.pack();
        Point point = I().getLocation();
        point.translate(20, 20);
        passwdDialog.setLocation(point);
        passwdDialog.setVisible(true);
        String str2 = new String(passwdDialog.getPasswd());
        try {
          if (Options.isTypeAlgorithm(Options.hashAlgorithms, z.getAlgorithm2())) {
            this.getPasswd.Z(str, I(str, "cla"), str2, z.getAlgorithm2());
          } else {
            this.getPasswd.B(str, I(str, "cla"), str2, z.getAlgorithm2());
          } 
        } catch (Exception exception) {
          this.S.append(exception.getMessage());
        } 
      } else {
        this.S.append("El fichero no contine un hash.");
      } 
    } 
  }
  
  public final void verInformacion() {
    this.getPasswd.MostrarInformacionAlgoritmosCifrado();
    this.getPasswd.MostrarInformacionAlgoritmosResumen();
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\S.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */