package practica2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Console;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import practica4.SignatureOptions;
import srt.*;

public class VentanaPrincipalP2 extends S {
  protected Mensajes H;
  protected SignatureOptions signatureOptions;
  public JFrame I = null;
  
  protected JPanel Z = null;
  
  protected JMenuBar C = null;
  
  protected JMenu B = null;
  
  protected JMenu D = null;
  
  protected JMenu add = null;
  
  protected JMenuItem addActionListener = null;
  
  protected JMenuItem append = null;
  
  protected JMenuItem F = null;
  
  protected JMenuItem J = null;
  
  protected JMenuItem console = null;
  
  protected JMenuItem equals = null;
  
  protected JTextArea S = null;
  
  protected JScrollPane getAbsolutePath = null;
  
  private Options getDefaultToolkit = new Options();
  
  CifradoOriginal getKeyStroke = null;
  
  public VentanaPrincipalP2() {
    this.getKeyStroke = new CifradoOriginal(this);
    this.getDefaultToolkit.setSymmetricalCipher(Options.symmetricalAlgorithms[0]);
  }

  public SignatureOptions getSignatureOptions() {
    return this.signatureOptions; // Asumiendo que existe un campo llamado 'signatureOptions'
  }
  
  protected final JFrame I() {
    if (this.I == null) {
      this.I = new JFrame();
      this.I.setDefaultCloseOperation(3);
      this.I.setBounds(new Rectangle(0, 0, 553, 382));
      this.I.setPreferredSize(new Dimension(400, 300));
      this.I.setResizable(false);
      this.I.setJMenuBar(Z());
      this.I.setContentPane(add());
//      this.I.setTitle(this.H.getProperty("title"));
      this.I.setTitle("Practica 2");
      Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension dimension2 = this.I.getSize();
      if (dimension2.height > dimension1.height)
        dimension2.height = dimension1.height; 
      if (dimension2.width > dimension1.width)
        dimension2.width = dimension1.width; 
      this.I.setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
    } 
    return this.I;
  }
  
  protected final JPanel add() {
    if (this.Z == null) {
      this.Z = new JPanel();
      this.Z.setLayout((LayoutManager)null);
      this.Z.add(getDefaultToolkit());
    } 
    return this.Z;
  }
  
  protected JMenuBar Z() {
    if (this.C == null) {
      this.C = new JMenuBar();
      this.C.add(addActionListener());
      this.C.add(append());
      this.C.add(C());
    } 
    return this.C;
  }
  
  private JMenu addActionListener() {
    if (this.B == null) {
      this.B = new JMenu();
      this.B.setText("Fichero");
      this.B.add(F());
      this.B.add(J());
      this.B.add(funcionSalirVentana());
    } 
    return this.B;
  }
  
  private JMenu append() {
    if (this.D == null) {
      this.D = new JMenu();
      this.D.setText("Opciones");
      this.D.add(D());
      this.D.add(equals());
    } 
    return this.D;
  }
  
  protected final JMenu C() {
    if (this.add == null) {
      this.add = new JMenu();
      this.add.setText("Ayuda");
      this.add.add(console());
    } 
    return this.add;
  }
  
  protected final JMenuItem funcionSalirVentana() {
    if (this.addActionListener == null) {
      this.addActionListener = new JMenuItem();
      this.addActionListener.setText("Salir");
      this.addActionListener.addActionListener(new EventoSalir(this));
    } 
    return this.addActionListener;
  }
  
  protected final JMenuItem console() {
    if (this.append == null) {
      this.append = new JMenuItem();
      this.append.setText("Acerca de");
//      this.append.addActionListener(new B(this));
    } 
    return this.append;
  }
  
  protected JMenuItem D() {
    if (this.F == null) {
      this.F = new JMenuItem();
      this.F.setText("Opciones");
      this.F.setAccelerator(KeyStroke.getKeyStroke(79, 2, true));
//      this.F.addActionListener(new D(this));
    } 
    return this.F;
  }
  
  private JMenuItem equals() {
    if (this.J == null) {
      this.J = new JMenuItem();
      this.J.setText("Ver información");
      this.J.setAccelerator(KeyStroke.getKeyStroke(86, 2, true));
      this.J.addActionListener(new F(this));
    } 
    return this.J;
  }
  
  protected final JMenuItem F() {
    if (this.console == null) {
      this.console = new JMenuItem();
      this.console.setText("Cifrar");
      this.console.setAccelerator(KeyStroke.getKeyStroke(67, 2, true));
      this.console.addActionListener(new MostrarVentanaFicheroCifrar(this));
    } 
    return this.console;
  }
  
  protected final JMenuItem J() {
    if (this.equals == null) {
      this.equals = new JMenuItem();
      this.equals.setText("Descifrar");
      this.equals.setAccelerator(KeyStroke.getKeyStroke(68, 2, true));
      this.equals.addActionListener(new EventoMostrarVentanaFicheroDescifrar(this));

    } 
    return this.equals;
  }
  
  private JTextArea getAbsolutePath() {
    if (this.S == null) {
      this.S = new JTextArea();
      this.S.setForeground(Color.red);
    } 
    return this.S;
  }
  
  public void I(String paramString) {
    if (this.I != null) {
      this.S.append(paramString);
      this.I.repaint();
    } else {
      System.out.println(paramString);
    } 
  }
  
  protected final JScrollPane getDefaultToolkit() {
    if (this.getAbsolutePath == null) {
      this.getAbsolutePath = new JScrollPane();
      this.getAbsolutePath.setPreferredSize(new Dimension(40, 200));
      this.getAbsolutePath.setViewportView(getAbsolutePath());
      this.getAbsolutePath.setSize(new Dimension(547, 325));
    } 
    return this.getAbsolutePath;
  }
  
  public static void main(String[] paramArrayOfString) {
    if (paramArrayOfString.length == 0) {
      SwingUtilities.invokeLater(new VisibilizarVentanaPrincipal());
    } else {
      getLocation(paramArrayOfString);
    } 
  }
  
  public final void GUIMostrarVentanaFicheroCifrar() {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a cifrar");
    int i = jFileChooser.showOpenDialog(this.Z);
    if (i == 0) {
      String str = jFileChooser.getSelectedFile().getAbsolutePath();
      PasswdDialog passwdDialog = new PasswdDialog("Frase de paso", (byte)1);
      passwdDialog.pack();
      Point point = I().getLocation();
      point.translate(20, 20);
      passwdDialog.setLocation(point);
      passwdDialog.setVisible(true);
      char[] arrayOfChar = passwdDialog.getPasswd();
      if (arrayOfChar != null)
        try {
          this.getKeyStroke.procesoCifrado(str, I(str, "cif"), arrayOfChar, this.getDefaultToolkit.getSymmetricalCipher());
        } catch (Exception exception) {
          this.S.append(exception.getMessage());
        }  
    } 
  }
  
  public final void EleccionFicheroADescifrar() {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a descifrar");
    int i = jFileChooser.showOpenDialog(this.Z);
    if (i == 0) {
      String str = jFileChooser.getSelectedFile().getAbsolutePath();
      Header z = new Header();
      if (this.getKeyStroke.leerFichero(str, z)) {
        PasswdDialog passwdDialog = new PasswdDialog("Frase de paso", (byte)0);
        passwdDialog.pack();
        Point point = I().getLocation();
        point.translate(20, 20);
        passwdDialog.setLocation(point);
        passwdDialog.setVisible(true);
        char[] arrayOfChar = passwdDialog.getPasswd();
        if (arrayOfChar != null)
          try {
            this.getKeyStroke.procesoDescrifrado(str, I(str, "cla"), arrayOfChar, z.getAlgorithm1());
          } catch (Exception exception) {
            this.S.append(exception.getMessage());
          }  
      } else {
        this.S.append("El fichero no está cifrado.");
      } 
    } 
  }
  
  public void MostrarAlgoritmosCifradosDisponbibles() {
    this.getKeyStroke.getAlgoritmosCifradoDisponibles();
  }
  
  public final String I(String paramString1, String paramString2) {
    int i;
    return ((i = paramString1.lastIndexOf(".")) == -1) ? (String.valueOf(paramString1) + "." + paramString2) : (String.valueOf(paramString1.substring(0, i)) + "." + paramString2);
  }
  
  public final void getKeyStroke() {
    System.out.println("\nUso: practica2 fichero [algoritmo]\n");
    MostrarAlgoritmosCifradosDisponbibles();
  }
  
  public static final void getLocation(String[] paramArrayOfString) {
    VentanaPrincipalP2 ventanaPrincipalP2 = new VentanaPrincipalP2();
    System.out.println(ventanaPrincipalP2.H.getProperty("version"));
    System.out.println(ventanaPrincipalP2.H.getProperty("product"));
    System.out.println(ventanaPrincipalP2.H.getProperty("copyright"));
    Header z = new Header();
    try {
      char[] arrayOfChar;
      switch (paramArrayOfString.length) {
        case 2:
          System.out.println("Vamos a cifrar el fichero.");
          if ((arrayOfChar = ventanaPrincipalP2.getPasswd()) != null)
            ventanaPrincipalP2.getKeyStroke.procesoCifrado(paramArrayOfString[0], ventanaPrincipalP2.getMessage(paramArrayOfString[0], true), arrayOfChar, paramArrayOfString[1]);
          return;
        case 1:
          if ((new File(paramArrayOfString[0])).isFile()) {
            if (ventanaPrincipalP2.getKeyStroke.leerFichero(paramArrayOfString[0], z)) {
              System.out.println("Vamos a descifrar el fichero.");
              if ((arrayOfChar = ventanaPrincipalP2.getScreenSize()) != null)
                ventanaPrincipalP2.getKeyStroke.procesoDescrifrado(paramArrayOfString[0], ventanaPrincipalP2.getMessage(paramArrayOfString[0], false), arrayOfChar, z.getAlgorithm1());
            } else {
              System.out.println("Vamos a cifrar el fichero.");
              if ((arrayOfChar = ventanaPrincipalP2.getPasswd()) != null)
                ventanaPrincipalP2.getKeyStroke.procesoCifrado(paramArrayOfString[0], ventanaPrincipalP2.getMessage(paramArrayOfString[0], false), arrayOfChar, ventanaPrincipalP2.getDefaultToolkit.getSymmetricalCipher());
            } 
          } else {
            System.out.println("Problemas con el fichero: " + paramArrayOfString[0]);
          } 
          return;
      } 
      ventanaPrincipalP2.getKeyStroke();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  private String getMessage(String paramString, boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = String.valueOf(paramString) + ".cif";
    } else {
      str = String.valueOf(paramString) + ".cla";
    } 
    return str;
  }
  
  private char[] getPasswd() {
    char[] arrayOfChar;
    if ((arrayOfChar = getSelectedFile()) != null) {
      String str1 = new String(arrayOfChar);
      System.out.print("Repitir ");
      String str2 = new String(getSelectedFile());
      if (str1.equals(str2))
        return arrayOfChar; 
      System.out.println("Frases diferentes.");
      return null;
    } 
    return null;
  }
  
  private char[] getScreenSize() {
    return getSelectedFile();
  }
  
  private char[] getSelectedFile() {
    char[] arrayOfChar = null;
    Console console;
    return ((console = System.console()) != null && (arrayOfChar = console.readPassword("[%s]", new Object[] { "Frase de paso:" })) != null) ? arrayOfChar : null;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\E.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */